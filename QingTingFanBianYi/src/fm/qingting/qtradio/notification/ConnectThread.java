package fm.qingting.qtradio.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.NotificationService;
import fm.qingting.qtradio.model.PullMsgConfig;
import java.util.ArrayList;
import java.util.List;

public class ConnectThread extends Thread
{
  private static final int LATEST_INTERVAL = 300;
  private Context context;
  private int duration = 1800;
  private boolean mDontPull = false;
  private SharedPreferences.Editor mEditor;
  private String mHasSendMsgIds;
  List<receivedMessage> messageList;
  private NotifyManager notifyManager;
  private SharedPreferences sharedPrefs;

  ConnectThread(NotificationService paramNotificationService)
  {
    this.context = paramNotificationService;
    this.notifyManager = new NotifyManager(paramNotificationService);
    this.sharedPrefs = this.context.getSharedPreferences("client_preferences", 0);
    this.mEditor = this.sharedPrefs.edit();
    this.messageList = new ArrayList();
    this.mHasSendMsgIds = this.sharedPrefs.getString("key_sentids", "init");
    if (this.mHasSendMsgIds.equalsIgnoreCase("init"))
    {
      paramNotificationService = (List)DataManager.getInstance().getData("getdb_pullmsgstate", null, null).getResult().getData();
      if ((paramNotificationService != null) && (paramNotificationService.size() > 0))
        moveData(paramNotificationService);
    }
    else
    {
      return;
    }
    this.mHasSendMsgIds = ",";
    this.mEditor.putString("key_sentids", this.mHasSendMsgIds);
    this.mEditor.commit();
  }

  private void _sendMessage(receivedMessage paramreceivedMessage)
  {
    if (paramreceivedMessage == null)
      return;
    new Notifier(this.context).notify("11", "", paramreceivedMessage.title, paramreceivedMessage.content, "", String.valueOf(paramreceivedMessage.startTime), paramreceivedMessage.channelname, Integer.valueOf(paramreceivedMessage.channelid).intValue(), "pullmsg", Integer.valueOf(paramreceivedMessage.catId).intValue(), Integer.valueOf(paramreceivedMessage.programId).intValue(), Integer.valueOf(paramreceivedMessage.parentid).intValue(), paramreceivedMessage.msgType, 0, null, null);
  }

  private String bulidParamString()
  {
    Object localObject = "?deviceid=" + this.sharedPrefs.getString("DEVICE_ID", "");
    String str2 = (String)localObject + "&country=中国";
    String str1 = PullMsgConfig.getInstance().getPullRegion();
    if (str1 != null)
    {
      localObject = str1;
      if (!str1.equalsIgnoreCase(""));
    }
    else
    {
      localObject = "上海市";
    }
    str2 = str2 + "&province=" + (String)localObject;
    str1 = PullMsgConfig.getInstance().getPullCity();
    if (str1 != null)
    {
      localObject = str1;
      if (!str1.equalsIgnoreCase(""));
    }
    else
    {
      localObject = "上海";
    }
    localObject = str2 + "&city=" + (String)localObject;
    localObject = (String)localObject + "&phonetype=";
    localObject = (String)localObject + PullMsgConfig.getInstance().getPhoneType();
    return (String)localObject + "&isp=" + getTypeOfNetwork(this.context);
  }

  private boolean hasSendMsg(String paramString)
  {
    if ((this.mHasSendMsgIds == null) || (paramString == null))
      return false;
    return isIdSent(paramString);
  }

  private void insertSentId(String paramString)
  {
    if (this.mHasSendMsgIds == null)
      this.mHasSendMsgIds = ("," + paramString + ",");
    while (true)
    {
      this.mEditor.putString("key_sentids", this.mHasSendMsgIds);
      this.mEditor.commit();
      return;
      if (!this.mHasSendMsgIds.contains("," + paramString + ","))
        this.mHasSendMsgIds = (this.mHasSendMsgIds + paramString + ",");
    }
  }

  private boolean isIdSent(String paramString)
  {
    if (this.mHasSendMsgIds == null)
      return false;
    return this.mHasSendMsgIds.contains("," + paramString + ",");
  }

  private void moveData(List<String> paramList)
  {
    String str = ",";
    int i = 0;
    while (i < paramList.size())
    {
      str = str + (String)paramList.get(i) + ",";
      i += 1;
    }
    this.mHasSendMsgIds = str;
    this.mEditor.putString("key_sentids", this.mHasSendMsgIds);
    this.mEditor.commit();
  }

  private List<receivedMessage> parsePullMsg(String paramString)
  {
    return null;
  }

  private receivedMessage pickLatestMessage()
  {
    if ((this.messageList == null) || (this.messageList.size() == 0))
      return null;
    long l1 = 9223372036854775807L;
    int j = -1;
    int i = 0;
    while (i < this.messageList.size())
    {
      int k = j;
      long l2 = l1;
      if (l1 > ((receivedMessage)this.messageList.get(i)).startTime)
      {
        k = j;
        l2 = l1;
        if (!hasSendMsg(String.valueOf(((receivedMessage)this.messageList.get(i)).id)))
        {
          l2 = ((receivedMessage)this.messageList.get(i)).startTime;
          k = i;
        }
      }
      i += 1;
      j = k;
      l1 = l2;
    }
    if (j != -1)
      return (receivedMessage)this.messageList.get(j);
    return null;
  }

  private void pullMessage()
  {
    Object localObject = this.notifyManager.getNotify("http://api.qingting.fm/api/newpush/getMessagev2" + bulidParamString(), null, null);
    if (localObject != null);
    try
    {
      if (!((String)localObject).equalsIgnoreCase(""))
      {
        localObject = parsePullMsg((String)localObject);
        if (localObject != null)
          saveMessages((List)localObject);
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void saveMessages(List<receivedMessage> paramList)
  {
    this.messageList = paramList;
  }

  private boolean sendMessage()
  {
    receivedMessage localreceivedMessage = pickLatestMessage();
    if (localreceivedMessage == null)
      this.mDontPull = false;
    long l;
    do
    {
      return false;
      l = System.currentTimeMillis() / 1000L;
      if ((localreceivedMessage.startTime > l) || (hasSendMsg(String.valueOf(localreceivedMessage.id))))
        break;
      this.mDontPull = false;
      insertSentId(String.valueOf(localreceivedMessage.id));
    }
    while ((localreceivedMessage.startTime != localreceivedMessage.endTime) && (l > localreceivedMessage.endTime));
    _sendMessage(localreceivedMessage);
    return true;
  }

  private long waiting()
  {
    return this.duration * 1000L;
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

  public void run()
  {
    try
    {
      while (!isInterrupted())
      {
        if (!this.mDontPull)
          pullMessage();
        sendMessage();
        Thread.sleep(waiting());
      }
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  public void saveMessage(receivedMessage paramreceivedMessage)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.ConnectThread
 * JD-Core Version:    0.6.2
 */