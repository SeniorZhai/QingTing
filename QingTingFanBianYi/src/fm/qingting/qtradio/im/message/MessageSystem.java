package fm.qingting.qtradio.im.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import fm.qingting.qtradio.im.group.GroupManager;
import fm.qingting.qtradio.manager.INETEventListener;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.pushmessage.MessageNotification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MessageSystem
  implements IMMessagePump.IRecvIMMsgListener, INETEventListener
{
  private static final int MSG_PAGE_SIZE = 20;
  private static final String MUTI_MSG_MODEL = "%d个联系人发来%d条消息";
  private static final String SINGLE_MSG_MODEL = "%s发%d条消息";
  private static MessageSystem _instance;
  private HashSet<String> mContactIds = new HashSet();
  private Context mContext;
  private QTIMReceiver mImReceiver;
  private List<String> mLstEnableGroups = new ArrayList();
  private IMMessage mRecvMessage = new IMMessage();
  private boolean mRegister = false;
  private int mUnReadMsgCnt;
  private String mUserId;
  private Map<String, String> mapMsg = new HashMap();

  public static MessageSystem getInstance()
  {
    if (_instance == null)
      _instance = new MessageSystem();
    return _instance;
  }

  private void initIMReceiver()
  {
    if (this.mContext == null)
      return;
    this.mImReceiver = new QTIMReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("recv_msg");
    localIntentFilter.setPriority(1);
    this.mContext.registerReceiver(this.mImReceiver, localIntentFilter);
  }

  private void register()
  {
  }

  private void releaseIMReceiver()
  {
    try
    {
      if (this.mImReceiver != null)
      {
        this.mContext.unregisterReceiver(this.mImReceiver);
        this.mImReceiver = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void saveEnableGroup()
  {
    String str = null;
    int i = 0;
    if (i < this.mLstEnableGroups.size())
    {
      if (str != null);
      for (str = str + "_"; ; str = "")
      {
        str = str + (String)this.mLstEnableGroups.get(i);
        i += 1;
        break;
      }
    }
    if (str != null)
      GlobalCfg.getInstance(this.mContext).setEnableGroups(str);
  }

  private void unRegister()
  {
  }

  public void addGroup(String paramString1, String paramString2)
  {
    GroupManager.getInstance().addGroup(paramString1, paramString2);
  }

  public void clearNotificationMsg()
  {
    this.mContactIds.clear();
    this.mUnReadMsgCnt = 0;
    MessageNotification.clearNotification(this.mContext);
  }

  public void clearUnReadMsg(String paramString)
  {
  }

  public void disableGroup(String paramString)
  {
    int i = 0;
    while (true)
    {
      if (i < this.mLstEnableGroups.size())
      {
        if (((String)this.mLstEnableGroups.get(i)).equalsIgnoreCase(paramString))
        {
          this.mLstEnableGroups.remove(i);
          saveEnableGroup();
        }
      }
      else
        return;
      i += 1;
    }
  }

  public void disableUserID(String paramString)
  {
  }

  public void enableGroup(String paramString)
  {
    if (paramString == null);
    while (hasEnableGroups(paramString))
      return;
    this.mLstEnableGroups.add(paramString);
    saveEnableGroup();
  }

  public void enableUserId(String paramString)
  {
  }

  public void exitGroup(String paramString1, String paramString2)
  {
    GroupManager.getInstance().exitGroup(paramString1, paramString2);
  }

  public String getUserId()
  {
    return this.mUserId;
  }

  public boolean hasEnableGroups(String paramString)
  {
    if ((paramString == null) || (this.mLstEnableGroups == null));
    while (true)
    {
      return false;
      int i = 0;
      while (i < this.mLstEnableGroups.size())
      {
        if (((String)this.mLstEnableGroups.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
    IMMessagePump.getInstance().init(this.mContext);
    initIMReceiver();
  }

  public void initEnableGroups()
  {
    Object localObject = GlobalCfg.getInstance(this.mContext).getEnableGroups();
    if (localObject != null)
    {
      localObject = ((String)localObject).split("_");
      if (localObject != null)
        break label27;
    }
    while (true)
    {
      return;
      label27: int i = 0;
      while (i < localObject.length)
      {
        this.mLstEnableGroups.add(localObject[i]);
        i += 1;
      }
    }
  }

  public void loadLastMsg(String paramString)
  {
    if (paramString == null);
  }

  public void loadMoreGroupMsg(String paramString, boolean paramBoolean)
  {
    if (paramString == null);
  }

  public void loadMoreUserMsg(String paramString, boolean paramBoolean)
  {
    if (paramString == null);
  }

  public void logout()
  {
    this.mRegister = false;
    this.mUserId = "";
  }

  public void onNetChanged(String paramString)
  {
  }

  public boolean onRecvIMMsg(IMMessage paramIMMessage, String paramString)
  {
    if (paramIMMessage == null);
    return false;
  }

  public void sendGroupMsg(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramString2 == null));
  }

  public void sendUserMsg(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramString2 == null));
  }

  public void setUserId(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      if (this.mUserId == null)
        break;
    }
    while (this.mUserId.equalsIgnoreCase(paramString));
    unRegister();
    this.mUserId = paramString;
    register();
    this.mRegister = true;
  }

  public boolean start(String paramString)
  {
    IMMessagePump.getInstance().registerRecvMsg(this);
    setUserId(paramString);
    this.mUnReadMsgCnt = 0;
    this.mContactIds.clear();
    return true;
  }

  public void stop()
  {
    IMMessagePump.getInstance().release();
    releaseIMReceiver();
  }

  class QTIMReceiver extends BroadcastReceiver
  {
    QTIMReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null);
      Bundle localBundle;
      do
      {
        return;
        localBundle = paramIntent.getExtras();
      }
      while ((localBundle == null) || (!paramIntent.getAction().equalsIgnoreCase("recv_msg")));
      MessageSystem.access$008(MessageSystem.this);
      int i = localBundle.getInt("chatType");
      paramContext = "";
      paramIntent = localBundle.getString("msg");
      if (i == 1);
      while (true)
      {
        MessageNotification.sendImNotification(paramContext, paramIntent, localBundle, MessageSystem.this.mContext, true);
        return;
        paramContext = localBundle.getString("fromUserId");
        String str = localBundle.getString("fromName");
        MessageSystem.this.mContactIds.add(paramContext);
        if (MessageSystem.this.mContactIds.size() == 1)
          paramContext = String.format(Locale.CHINESE, "%s发%d条消息", new Object[] { str, Integer.valueOf(MessageSystem.this.mUnReadMsgCnt) });
        else
          paramContext = String.format(Locale.CHINESE, "%d个联系人发来%d条消息", new Object[] { Integer.valueOf(MessageSystem.this.mContactIds.size()), Integer.valueOf(MessageSystem.this.mUnReadMsgCnt) });
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.message.MessageSystem
 * JD-Core Version:    0.6.2
 */