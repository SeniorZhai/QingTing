package fm.qingting.qtradio.im;

import android.text.TextUtils;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.room.UserInfo;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LatestMessages
{
  private static final String AFTERNOON = "下午";
  private static final String DATEFORMAT = "%d月%d日";
  private static final String DAWN = "凌晨";
  private static final String DAYBEFOREYESTERDAY = "前天 ";
  private static final String MORNING = "早上";
  private static final String NIGHT = "晚上";
  private static final String NOON = "中午";
  private static final String TIMEFORMAT = "%s%02d:%02d";
  private static final String YESTERDAY = "昨天 ";
  private static Calendar sCalendar;
  private static int sDay;
  private static String sLatestContactId;
  private static IMMessage sLatestMessage;
  private static long sLatestPublishTime = -1L;
  private static HashMap<String, IMMessage> sMessages = new HashMap();

  public static String getLatestContactId()
  {
    return sLatestContactId;
  }

  public static IMMessage getMessage(String paramString)
  {
    return (IMMessage)sMessages.get(paramString);
  }

  public static int getSize()
  {
    return sMessages.size();
  }

  private static String getTimeInDay(int paramInt1, int paramInt2)
  {
    String str;
    if (paramInt1 < 6)
      str = "凌晨";
    while (true)
    {
      return String.format(Locale.CHINESE, "%s%02d:%02d", new Object[] { str, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      if (paramInt1 < 12)
        str = "早上";
      else if (paramInt1 < 13)
        str = "中午";
      else if (paramInt1 < 18)
        str = "下午";
      else
        str = "晚上";
    }
  }

  public static String getTimestampBySecond(long paramLong)
  {
    int i = 11;
    sCalendar.setTimeInMillis(1000L * paramLong);
    int j = sCalendar.get(6);
    int k = sCalendar.get(11);
    int m = sCalendar.get(12);
    if (j == sDay)
      return getTimeInDay(k, m);
    if (j == sDay - 1)
      return "昨天 " + getTimeInDay(k, m);
    if (j == sDay - 2)
      return "前天 " + getTimeInDay(k, m);
    j = sCalendar.get(2);
    k = sCalendar.get(5);
    if (j == 12);
    while (true)
    {
      return String.format(Locale.CHINESE, "%d月%d日", new Object[] { Integer.valueOf(i + 1), Integer.valueOf(k) });
      i = j;
    }
  }

  public static int loadUnreadMsgs(boolean paramBoolean)
  {
    Object localObject1 = IMContacts.getInstance().getRecentGroupContacts();
    List localList = IMContacts.getInstance().getRecentUserContacts();
    Object localObject2;
    if (localObject1 != null)
    {
      i = 0;
      while (i < ((List)localObject1).size())
      {
        localObject2 = ((GroupInfo)((List)localObject1).get(i)).groupId;
        if (!paramBoolean)
        {
          IMMessage localIMMessage = IMAgent.getInstance().loadLatestMsg((String)localObject2, 1);
          if (localIMMessage != null)
            putMessage((String)localObject2, localIMMessage);
        }
        i += 1;
      }
    }
    for (int i = 0 + ((List)localObject1).size(); ; i = 0)
    {
      if (localList != null)
      {
        int j = 0;
        while (j < localList.size())
        {
          localObject1 = ((UserInfo)localList.get(j)).userKey;
          if (!paramBoolean)
          {
            localObject2 = IMAgent.getInstance().loadLatestMsg((String)localObject1, 0);
            if (localObject2 != null)
              putMessage((String)localObject1, (IMMessage)localObject2);
          }
          j += 1;
        }
        i = localList.size() + i;
      }
      while (true)
      {
        if (!paramBoolean)
          IMAgent.getInstance().loadLatestPeerMsgFromNet();
        return i;
      }
    }
  }

  public static IMMessage pickLatestMessage()
  {
    return sLatestMessage;
  }

  public static void putMessage(String paramString, IMMessage paramIMMessage)
  {
    long l1 = paramIMMessage.publish;
    long l2 = System.currentTimeMillis() / 1000L;
    if (l1 > l2)
      l1 = l2;
    while (true)
    {
      if (l1 > sLatestPublishTime)
      {
        sLatestPublishTime = l1;
        sLatestContactId = paramString;
        sLatestMessage = paramIMMessage;
      }
      for (int i = 1; ; i = 0)
      {
        if (sMessages.containsKey(paramString))
        {
          sMessages.put(paramString, paramIMMessage);
          if (i != 0)
            refreshViewifNeed(paramString);
          return;
        }
        sMessages.put(paramString, paramIMMessage);
        resetDataIfNeed();
        return;
      }
    }
  }

  private static void refreshViewifNeed(String paramString)
  {
    if (!TextUtils.equals(paramString, sLatestContactId));
    for (boolean bool = true; ; bool = false)
    {
      paramString = ControllerManager.getInstance().getLastViewController();
      if ((paramString != null) && (paramString.controllerName.equalsIgnoreCase("conversations")))
        paramString.config("resetList", Boolean.valueOf(bool));
      return;
    }
  }

  public static void resetBaseTime()
  {
    if (sCalendar == null)
      sCalendar = Calendar.getInstance();
    sCalendar.setTimeInMillis(System.currentTimeMillis());
    sDay = sCalendar.get(6);
  }

  private static void resetDataIfNeed()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("conversations")))
      localViewController.config("resetData", null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.LatestMessages
 * JD-Core Version:    0.6.2
 */