package fm.qingting.qtradio;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.NetDS;
import fm.qingting.framework.data.ServerConfig;
import fm.qingting.qtradio.data.AlarmDS;
import fm.qingting.qtradio.data.BillboardNodeDS;
import fm.qingting.qtradio.data.CommonDS;
import fm.qingting.qtradio.data.DBManager;
import fm.qingting.qtradio.data.FavouriteChannelDS;
import fm.qingting.qtradio.data.NotifyDS;
import fm.qingting.qtradio.data.PlayListDS;
import fm.qingting.qtradio.data.PlayedMetaDS;
import fm.qingting.qtradio.data.ProfileDS;
import fm.qingting.qtradio.data.ProgramNodesDS;
import fm.qingting.qtradio.data.PullMsgStateDS;
import fm.qingting.qtradio.data.PullNodeDS;
import fm.qingting.qtradio.data.ReserveProgramDS;
import fm.qingting.qtradio.im.message.MessageSystem;
import fm.qingting.qtradio.localpush.ChannelUpdate;
import fm.qingting.qtradio.localpush.ContinueListen;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.PullMsgConfig;
import fm.qingting.qtradio.notification.ConnectManager;
import fm.qingting.qtradio.notification.ConnectivityReceiver;
import fm.qingting.qtradio.notification.HttpNotification;
import fm.qingting.qtradio.notification.INotificationServiceControl.Stub;
import fm.qingting.qtradio.notification.MessageManager;
import fm.qingting.qtradio.notification.WatchDogThread;
import fm.qingting.qtradio.parser.NetParser;
import fm.qingting.qtradio.pushmessage.IMHandler;
import fm.qingting.qtradio.pushmessage.MessageMainController;
import fm.qingting.qtradio.selfprotect.SelfProtect;
import fm.qingting.qtradio.view.floaticon.FloatToggleReceiver;
import fm.qingting.qtradio.view.floaticon.FloatViewManager;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.ProcessDetect;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service
{
  public static final String SERVICE_NAME = "fm.qingting.qtradio.NotificationService";
  private String KEY_ACTIVITY = "ACTIVITY_START";
  private String KEY_FIRST_START = "ACTIVITY_FIRST_START";
  private ConnectManager connectManager;
  private BroadcastReceiver connectivityReceiver = new ConnectivityReceiver(this);
  private String deviceId;
  private boolean enableFloat = false;
  private boolean enableSelfProtect = false;
  private Handler handler = new Handler();
  private boolean hasStartWatchDog = false;
  private boolean keepAlive = false;
  private Conversation.SyncListener listener = new Conversation.SyncListener()
  {
    public void onReceiveDevReply(List<DevReply> paramAnonymousList)
    {
      if ((paramAnonymousList == null) || (paramAnonymousList.size() == 0))
        return;
      NotificationService.this.handleRecvReply(((DevReply)paramAnonymousList.get(0)).getContent());
    }

    public void onSendUserReply(List<Reply> paramAnonymousList)
    {
    }
  };
  private final INotificationServiceControl.Stub mBinder = new INotificationServiceControl.Stub()
  {
    public void activityHasQuit()
    {
    }

    public void activityHasStart()
    {
    }

    public void addGroup(String paramAnonymousString1, String paramAnonymousString2)
    {
      NotificationService.log("addGroup");
      HashMap localHashMap = new HashMap();
      localHashMap.put("user", paramAnonymousString1);
      localHashMap.put("groupId", paramAnonymousString2);
    }

    public void clearNotificationMsg()
      throws RemoteException
    {
      MessageSystem.getInstance().clearNotificationMsg();
    }

    public void clearUnReadMsg(String paramAnonymousString)
      throws RemoteException
    {
      MessageSystem.getInstance().clearUnReadMsg(paramAnonymousString);
    }

    public void createGroup(String paramAnonymousString1, String paramAnonymousString2)
      throws RemoteException
    {
      NotificationService.log("createGroup");
      HashMap localHashMap = new HashMap();
      localHashMap.put("groupId", paramAnonymousString2);
      localHashMap.put("data", paramAnonymousString1);
    }

    public void disableGroup(String paramAnonymousString)
      throws RemoteException
    {
      MessageSystem.getInstance().disableGroup(paramAnonymousString);
    }

    public void disableUser(String paramAnonymousString)
      throws RemoteException
    {
    }

    public void enableGroup(String paramAnonymousString)
      throws RemoteException
    {
      MessageSystem.getInstance().enableGroup(paramAnonymousString);
    }

    public void exitGroup(String paramAnonymousString1, String paramAnonymousString2)
    {
      NotificationService.log("exitGroup");
      HashMap localHashMap = new HashMap();
      localHashMap.put("user", paramAnonymousString1);
      localHashMap.put("groupId", paramAnonymousString2);
    }

    public int getUnReadMsg(String paramAnonymousString)
      throws RemoteException
    {
      return 0;
    }

    public boolean hasDisabledGroup(String paramAnonymousString)
      throws RemoteException
    {
      return MessageSystem.getInstance().hasEnableGroups(paramAnonymousString);
    }

    public void loadLastMsg(String paramAnonymousString)
      throws RemoteException
    {
      NotificationService.log("loadLastMsg:" + paramAnonymousString);
      new HashMap().put("id", paramAnonymousString);
    }

    public void loadMoreGroupMsg(String paramAnonymousString, boolean paramAnonymousBoolean)
      throws RemoteException
    {
      NotificationService.log("loadMoreGroupMsg:" + paramAnonymousString);
      HashMap localHashMap = new HashMap();
      localHashMap.put("groupId", paramAnonymousString);
      localHashMap.put("currentAllMsg", Boolean.valueOf(paramAnonymousBoolean));
    }

    public void loadMoreUserMsg(String paramAnonymousString, boolean paramAnonymousBoolean)
      throws RemoteException
    {
      NotificationService.log("loadMoreUserMsg:" + paramAnonymousString);
      HashMap localHashMap = new HashMap();
      localHashMap.put("toUser", paramAnonymousString);
      localHashMap.put("currentAllMsg", Boolean.valueOf(paramAnonymousBoolean));
    }

    public void logout()
      throws RemoteException
    {
      NotificationService.log("logout");
      MessageSystem.getInstance().logout();
    }

    public void sendGroupMsg(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("fromUser", paramAnonymousString1);
      localHashMap.put("groupId", paramAnonymousString2);
      localHashMap.put("msg", paramAnonymousString3);
    }

    public void sendUserMsg(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("fromUser", paramAnonymousString1);
      localHashMap.put("toUser", paramAnonymousString2);
      localHashMap.put("msg", paramAnonymousString3);
    }

    public void setUserId(String paramAnonymousString)
      throws RemoteException
    {
      NotificationService.log("setUserId");
      new HashMap().put("userId", paramAnonymousString);
    }

    public void start(String paramAnonymousString)
      throws RemoteException
    {
      NotificationService.log("start(String userId)");
      new HashMap().put("userId", paramAnonymousString);
    }
  };
  private FloatToggleReceiver mFloatToggleReceiver;
  private List<String> mHomeNames;
  private boolean mPushSwitch = true;
  private boolean mRecvPushMsgBak = false;
  private Thread mWatchDogThread;
  private MessageManager msgManager;
  private int pullMsgFromUM = 0;
  private SharedPreferences sharedPrefs;
  private TelephonyManager telephonyManager;
  private Timer timer;
  private FeedbackAgent umengAgent;

  private void avoidKilled()
  {
    startForeground(0, new Notification());
  }

  private List<String> getHomes()
  {
    if (this.mHomeNames == null)
    {
      this.mHomeNames = new ArrayList();
      Object localObject1 = getPackageManager();
      Object localObject2 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject2).addCategory("android.intent.category.HOME");
      localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 65536).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        this.mHomeNames.add(((ResolveInfo)localObject2).activityInfo.packageName);
      }
    }
    return this.mHomeNames;
  }

  public static Intent getIntent()
  {
    return new Intent("fm.qingting.qtradio.NotificationService");
  }

  private void handleIntent(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getAction() != null) && (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE_BAK")))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("fm.qingting.qtradio.GEXIN_MESSAGE_BAK");
      paramIntent = paramIntent.getExtras();
      if (paramIntent != null)
      {
        localIntent.putExtra("msg", paramIntent.getString("msg"));
        localIntent.putExtra("alias", paramIntent.getString("alias"));
        localIntent.putExtra("topic", paramIntent.getString("topic"));
        localIntent.putExtra("reg", paramIntent.getString("reg"));
        localIntent.putExtra("type", String.valueOf(paramIntent.getString("type")));
        sendBroadcast(localIntent);
      }
    }
  }

  private void handleRecvReply(String paramString)
  {
  }

  private boolean isHome()
  {
    List localList = ((ActivityManager)getSystemService("activity")).getRunningTasks(1);
    return getHomes().contains(((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName());
  }

  private static void log(String paramString)
  {
  }

  private void registerConnectivityReceiver()
  {
    if (this.telephonyManager == null)
      this.telephonyManager = ((TelephonyManager)getSystemService("phone"));
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(this.connectivityReceiver, localIntentFilter);
  }

  private void registerNotificationReceiver()
  {
  }

  private void startPushUpdates()
  {
    if (GlobalCfg.getInstance(this).isPushSwitchEnabled())
    {
      ChannelUpdate.getInstance().init(this);
      ChannelUpdate.getInstance().start();
    }
    if (GlobalCfg.getInstance(this).getAliasPush())
    {
      ContinueListen.getInstance().init(this);
      ContinueListen.getInstance().start();
    }
  }

  private void stop()
  {
    unregisterNotificationReceiver();
    unregisterConnectivityReceiver();
    if (this.mFloatToggleReceiver != null)
      unregisterReceiver(this.mFloatToggleReceiver);
    stopPush();
    LogModule.getInstance().stop();
    if (this.keepAlive)
      MessageMainController.getInstance().stop();
  }

  private void stopPush()
  {
  }

  private void syncUmengReply()
  {
  }

  private void unregisterConnectivityReceiver()
  {
    unregisterReceiver(this.connectivityReceiver);
  }

  private void unregisterNotificationReceiver()
  {
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public MessageManager getMsgManager()
  {
    return this.msgManager;
  }

  public SharedPreferences getSharedPreferences()
  {
    return this.sharedPrefs;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onCreate()
  {
    try
    {
      this.telephonyManager = ((TelephonyManager)getSystemService("phone"));
      this.sharedPrefs = getSharedPreferences("client_preferences", 0);
      this.deviceId = DeviceInfo.getUniqueId(this);
      avoidKilled();
      Object localObject = this.sharedPrefs.edit();
      ((SharedPreferences.Editor)localObject).putString("DEVICE_ID", this.deviceId);
      ((SharedPreferences.Editor)localObject).commit();
      registerNotificationReceiver();
      registerConnectivityReceiver();
      localObject = getResources().openRawResource(2131165187);
      ServerConfig.getInstance().setServerConfig((InputStream)localObject);
      GlobalCfg.getInstance(this).setUseCache(true);
      DBManager.getInstance().init(this);
      DataManager.getInstance().addDataSource(ReserveProgramDS.getInstance());
      DataManager.getInstance().addDataSource(AlarmDS.getInstance());
      DataManager.getInstance().addDataSource(ProfileDS.getInstance());
      DataManager.getInstance().addDataSource(NotifyDS.getInstance());
      DataManager.getInstance().addDataSource(CommonDS.getInstance());
      DataManager.getInstance().addDataSource(PullMsgStateDS.getInstance());
      DataManager.getInstance().addDataSource(PlayListDS.getInstance());
      DataManager.getInstance().addDataSource(PullNodeDS.getInstance());
      DataManager.getInstance().addDataSource(ProgramNodesDS.getInstance());
      LogModule.getInstance().init(this);
      this.keepAlive = true;
      if (this.keepAlive)
      {
        boolean bool1 = GlobalCfg.getInstance(this).getGlobalPush();
        boolean bool2 = GlobalCfg.getInstance(this).getAliasPush();
        MessageMainController.getInstance().init(this, bool1, bool2);
        MessageMainController.getInstance().setDeviceId(this.deviceId);
        MessageMainController.getInstance().start();
      }
      this.enableFloat = GlobalCfg.getInstance(this).getFloatWindow();
      if (this.enableFloat)
      {
        DataManager.getInstance().addDataSource(BillboardNodeDS.getInstance());
        FloatViewManager.INSTANCE.setEnable(GlobalCfg.getInstance(this).getFloatState());
        this.mFloatToggleReceiver = new FloatToggleReceiver();
        registerReceiver(this.mFloatToggleReceiver, new IntentFilter("fm.qingting.qtradio.action_float_toggle"));
      }
      this.msgManager = new MessageManager(this);
      this.msgManager.restartThread();
      HttpNotification.getInstance().init(this);
      PullMsgConfig.getInstance().setContext(this);
      DataManager.getInstance().addDataSource(NetDS.getInstance());
      NetDS.getInstance().addParser(new NetParser());
      DataManager.getInstance().addDataSource(FavouriteChannelDS.getInstance());
      DataManager.getInstance().addDataSource(PlayedMetaDS.getInstance());
      QTLogger.getInstance().setContext(this);
      LogModule.getInstance().start();
      startPushUpdates();
      this.mPushSwitch = GlobalCfg.getInstance(this).isPushSwitchEnabled();
      this.connectManager = new ConnectManager(this, this.mPushSwitch);
      if (!this.keepAlive)
        this.connectManager.restartThread();
      if (this.enableSelfProtect)
      {
        SelfProtect.getInstance().init(this);
        SelfProtect.getInstance().run();
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        log("catch oncreate exception");
      }
    }
  }

  public void onDestroy()
  {
    if ((this.enableFloat) && (this.timer != null))
    {
      this.timer.cancel();
      this.timer = null;
    }
    stop();
    Process.killProcess(Process.myPid());
  }

  public void onRebind(Intent paramIntent)
  {
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null)
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    if (paramIntent.getAction() != null)
    {
      if (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.alarmintent"))
        break label106;
      if (this.msgManager != null)
        this.msgManager.restartThreadIfNeed();
      syncUmengReply();
    }
    while (true)
    {
      restartWatchDogThreadIfNeed();
      if ((this.enableFloat) && (this.timer == null))
      {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new RefreshTask(), 0L, 1000L);
      }
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
      label106: if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.reserveintent"))
      {
        if (this.msgManager != null)
          this.msgManager.restartThreadIfNeed();
        syncUmengReply();
      }
      else if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.start"))
      {
        stopPush();
        if (this.msgManager != null)
          this.msgManager.pauseThread();
        IMHandler.getInstance().clearMsgCnt();
      }
      else if (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.quit"))
      {
        if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.notifyintent"))
        {
          syncUmengReply();
        }
        else if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE_BAK"))
        {
          if (!this.mRecvPushMsgBak)
            handleIntent(paramIntent);
          this.mRecvPushMsgBak = true;
        }
        else if ((!paramIntent.getAction().equalsIgnoreCase("fm.qingting.protectintent")) && (paramIntent.getAction().equalsIgnoreCase("fm.qingting.killintent")))
        {
          int i = ProcessDetect.getProcessId("fm.qingting.qtradio", this);
          if (i != -1)
            Process.killProcess(i);
          stopSelf();
        }
      }
    }
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }

  public void restartWatchDogThreadIfNeed()
  {
    if (!this.hasStartWatchDog)
    {
      Log.e("notficationService", "restartWatchDogThreadIfNeed");
      this.mWatchDogThread = new WatchDogThread(this);
      this.mWatchDogThread.start();
      this.hasStartWatchDog = true;
    }
  }

  class RefreshTask extends TimerTask
  {
    RefreshTask()
    {
    }

    public void run()
    {
      if (FloatViewManager.INSTANCE.isEnabled())
      {
        bool = NotificationService.this.isHome();
        if ((!bool) || (FloatViewManager.INSTANCE.isWindowShowing()));
      }
      while (!FloatViewManager.INSTANCE.isWindowShowing())
      {
        boolean bool;
        NotificationService.this.handler.post(new Runnable()
        {
          public void run()
          {
            FloatViewManager.INSTANCE.createSmallWindow(NotificationService.this.getApplicationContext());
          }
        });
        do
          return;
        while ((bool) || (!FloatViewManager.INSTANCE.isWindowShowing()));
        NotificationService.this.handler.post(new Runnable()
        {
          public void run()
          {
            FloatViewManager.INSTANCE.removeSmallWindow(NotificationService.this.getApplicationContext());
            FloatViewManager.INSTANCE.removeBigWindow(NotificationService.this.getApplicationContext());
          }
        });
        return;
      }
      FloatViewManager.INSTANCE.removeBigWindow(NotificationService.this.getApplicationContext());
      FloatViewManager.INSTANCE.removeSmallWindow(NotificationService.this.getApplicationContext());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.NotificationService
 * JD-Core Version:    0.6.2
 */