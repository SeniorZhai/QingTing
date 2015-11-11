package fm.qingting.qtradio.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.NotificationService;
import fm.qingting.qtradio.model.AlarmInfo;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ReserveNode;
import fm.qingting.utils.MobileState;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageCenter
{
  private static final int ALARM_DELAY_THRESHOLD = 3600;
  private static final int ALARM_TIME_THRESHOLD = 6;
  private static final int FIVEDAY = 432000;
  private static final String KEY_LOCALNOTIFICATION_INDEX = "key_localnotification_index";
  private static final String KEY_PUSHLOCALNOTIFICATION = "key_pushlocalnotification";
  private static final int MAX_NOTIFICATION_INDEX = 4;
  private static final int ONEDAY = 86400;
  private static final int ONEWEEK = 604800;
  private static final int RECOMMEND_TIME_INTERVAL = 600;
  private static final int RECOMMEND_TIME_THRESHOLD = 5400;
  private static final int TESTDAY = 60;
  private static final int TIME_THRESHOLD = 15;
  private static final int TWODAYS = 172800;
  private long appFirstStartTime = 0L;
  private Context context;
  private int getProgramTopicTime = 0;
  private boolean hasAlarmInfo = true;
  private boolean hasReserveMsg = true;
  private List<AlarmInfo> lstAlarmInfo = new ArrayList();
  private List<Node> lstReservePrograms = new ArrayList();
  private SharedPreferences sharedPrefs;
  private long startActivityTime = 0L;

  public MessageCenter(NotificationService paramNotificationService)
  {
    if (paramNotificationService != null)
    {
      this.startActivityTime = (GlobalCfg.getInstance(paramNotificationService).getActivityStartTime() * 1000L);
      this.appFirstStartTime = (GlobalCfg.getInstance(paramNotificationService).getAppFirstStartTime() * 1000L);
    }
    this.context = paramNotificationService;
    this.sharedPrefs = this.context.getSharedPreferences("client_preferences", 0);
    init();
  }

  private boolean acquireProperDuration()
  {
    int i = Calendar.getInstance().get(11);
    return (i >= 7) && (i <= 23);
  }

  private boolean acquireProperNet()
  {
    if (this.context == null);
    while (MobileState.getNetWorkType(this.context) != 1)
      return false;
    return true;
  }

  private boolean acquireProperNotification()
  {
    return getLocalNotificationIndex() < 4;
  }

  private boolean acquireProperTime()
  {
    long l1 = getBootStrapTime();
    long l2 = System.currentTimeMillis();
    long l3 = (l2 - l1) / 1000L;
    int i = getLocalNotificationIndex();
    if ((i == 0) && (this.appFirstStartTime > 0L))
      if ((l2 - this.appFirstStartTime) / 1000L <= 86400L);
    do
    {
      return true;
      return false;
      if ((i < 1) || (l1 <= 0L))
        break;
    }
    while (l3 > 604800L);
    return false;
    return false;
  }

  private void getAlarmInfoFromDB()
  {
    if ((this.lstAlarmInfo != null) && (this.lstAlarmInfo.size() != 0));
    do
    {
      do
        return;
      while (!this.hasAlarmInfo);
      Result localResult = DataManager.getInstance().getData("getdb_alarm_info", null, null).getResult();
      if (localResult.getSuccess())
        this.lstAlarmInfo = ((List)localResult.getData());
    }
    while (this.lstAlarmInfo.size() != 0);
    this.hasAlarmInfo = false;
  }

  private long getBootStrapTime()
  {
    if (this.context != null)
      return this.startActivityTime;
    return System.currentTimeMillis();
  }

  private int getDayOfWeek()
  {
    return Calendar.getInstance().get(7);
  }

  private int getLocalNotificationIndex()
  {
    int i = 0;
    if (this.sharedPrefs != null)
      i = this.sharedPrefs.getInt("key_localnotification_index", 0);
    return i;
  }

  private localMessage getMessageByIndex(int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    case 2:
    case 3:
    case 4:
    }
    return null;
  }

  private int getRelativeTime(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    return localCalendar.get(13) + (i * 60 * 60 + j * 60);
  }

  private void getReserveProgramFromDB()
  {
    if ((this.lstReservePrograms != null) && (this.lstReservePrograms.size() != 0));
    do
    {
      do
        return;
      while (!this.hasReserveMsg);
      Result localResult = DataManager.getInstance().getData("get_reserve_program", null, null).getResult();
      if (localResult.getSuccess())
        this.lstReservePrograms = ((List)localResult.getData());
    }
    while (this.lstReservePrograms.size() != 0);
    this.hasReserveMsg = false;
  }

  private localMessage handleAlarm()
  {
    long l = System.currentTimeMillis();
    int k = (int)Math.pow(2.0D, getDayOfWeek());
    int m = getRelativeTime(l);
    int n = GlobalCfg.getInstance(getContext()).getAlarmDayOfWeek();
    if (GlobalCfg.getInstance(getContext()).getAlarmShouted());
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    int i;
    do
    {
      do
      {
        return null;
        str1 = GlobalCfg.getInstance(getContext()).getAlarmChannelName();
        str2 = GlobalCfg.getInstance(getContext()).getAlarmChannelId();
        str3 = GlobalCfg.getInstance(getContext()).getAlarmCategoryId();
        str4 = GlobalCfg.getInstance(getContext()).getAlarmRingToneId();
        l = GlobalCfg.getInstance(getContext()).getAlarmTime();
        str5 = GlobalCfg.getInstance(getContext()).getAlarmType();
        int j = 0;
        i = j;
        if (str5 != null)
        {
          i = j;
          if (!str5.equalsIgnoreCase(""))
            i = Integer.valueOf(str5).intValue();
        }
        str5 = GlobalCfg.getInstance(getContext()).getAlarmProgramId();
      }
      while ((str2 == null) || (str2.equalsIgnoreCase("")) || (str3 == null) || (str3.equalsIgnoreCase("")) || (str4 == null) || (str4.equalsIgnoreCase("")) || (n != k));
      if ((m < l) && (l < m + 6))
        return new localMessage(str1, "蜻蜓闹钟提醒：到时间啦。", str1, Integer.valueOf(str2).intValue(), Integer.valueOf(str5).intValue(), String.valueOf(l), "alarm", Integer.valueOf(str4).intValue(), i, Integer.valueOf(str3).intValue());
    }
    while ((l >= m) || (m >= 3600L + l));
    return new localMessage(str1, "蜻蜓闹钟提醒：到时间啦。", str1, Integer.valueOf(str2).intValue(), Integer.valueOf(str5).intValue(), String.valueOf(l), "alarm", Integer.valueOf(str4).intValue(), i, Integer.valueOf(str3).intValue());
  }

  private localMessage handleLocalNotification()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (acquireProperNotification())
    {
      localObject1 = localObject2;
      if (acquireProperDuration())
      {
        localObject1 = localObject2;
        if (acquireProperNet())
        {
          localObject1 = localObject2;
          if (acquireProperTime())
          {
            int i = getLocalNotificationIndex() + 1;
            setLocalNotificationIndex(i);
            localObject1 = getMessageByIndex(i);
          }
        }
      }
    }
    return localObject1;
  }

  private localMessage handleRecommend()
  {
    GlobalCfg.getInstance(getContext()).getLocalRecommend();
    return null;
  }

  private localMessage handleReply()
  {
    return null;
  }

  private void init()
  {
    getReserveProgramFromDB();
    getAlarmInfoFromDB();
  }

  public static String msToDate(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat("yyyy-MM-dd").format(localDate);
  }

  private void setLocalNotificationIndex(int paramInt)
  {
    if (this.sharedPrefs != null)
    {
      SharedPreferences.Editor localEditor = this.sharedPrefs.edit();
      localEditor.putInt("key_localnotification_index", paramInt);
      localEditor.commit();
    }
  }

  public Context getContext()
  {
    return this.context;
  }

  public localMessage getMessage(String paramString, Map<String, Object> paramMap)
  {
    Object localObject = null;
    long l1 = System.currentTimeMillis() / 1000L;
    int i;
    if (paramString.equalsIgnoreCase("reserve"))
    {
      paramMap = localObject;
      if (this.lstReservePrograms != null)
      {
        paramMap = localObject;
        if (this.lstReservePrograms.size() != 0)
        {
          i = 0;
          if (i >= this.lstReservePrograms.size())
            break label608;
          if ((this.lstReservePrograms.get(i) == null) || (!((Node)this.lstReservePrograms.get(i)).nodeName.equalsIgnoreCase("reserve")))
            break label355;
          paramString = (ProgramNode)((ReserveNode)this.lstReservePrograms.get(i)).reserveNode;
          long l2 = ((ReserveNode)this.lstReservePrograms.get(i)).reserveTime;
          int j = 0;
          if (paramString.mLiveInVirtual)
            j = 1;
          if ((l2 < l1) || (l2 >= 15L + l1))
            break label355;
          if ((((ReserveNode)this.lstReservePrograms.get(i)).channelId == 0) || (((ReserveNode)this.lstReservePrograms.get(i)).uniqueId == 0))
            break label603;
          paramString = new localMessage("节目智能提醒", "<<" + paramString.title + ">>马上就要开始啦，点击即可收听哦^_^", ((ReserveNode)this.lstReservePrograms.get(i)).programName, ((ReserveNode)this.lstReservePrograms.get(i)).channelId, ((ReserveNode)this.lstReservePrograms.get(i)).uniqueId, String.valueOf(l2), "reserve", Integer.valueOf(((ReserveNode)this.lstReservePrograms.get(i)).uniqueId).intValue(), j, 0);
          label339: this.lstReservePrograms.remove(i);
        }
      }
    }
    while (true)
    {
      paramMap = paramString;
      label355: 
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return paramMap;
                i += 1;
                break;
                if (!paramString.equalsIgnoreCase("alarm"))
                  break label418;
                paramString = handleAlarm();
                paramMap = paramString;
              }
              while (paramString == null);
              GlobalCfg.getInstance(getContext()).setAlarmShouted(true);
              GlobalCfg.getInstance(this.context).saveValueToDB();
              GlobalCfg.getInstance(this.context).clearCache();
              return paramString;
              if (!paramString.equalsIgnoreCase("reply"))
                break label473;
              paramString = handleReply();
              paramMap = paramString;
            }
            while (paramString == null);
            GlobalCfg.getInstance(getContext()).setRecvReply("");
            GlobalCfg.getInstance(this.context).saveValueToDB();
            GlobalCfg.getInstance(this.context).clearCache();
            return paramString;
            if (!paramString.equalsIgnoreCase("localrecommend"))
              break label544;
            paramString = handleRecommend();
            paramMap = paramString;
          }
          while (paramString == null);
          GlobalCfg.getInstance(getContext()).setInterestShouted(true);
          GlobalCfg.getInstance(getContext()).setInterestNotify(System.currentTimeMillis() / 1000L);
          GlobalCfg.getInstance(this.context).saveValueToDB();
          GlobalCfg.getInstance(this.context).clearCache();
          return paramString;
          paramMap = localObject;
        }
        while (!paramString.equalsIgnoreCase("localNotification"));
        paramString = handleLocalNotification();
        paramMap = paramString;
      }
      while (paramString == null);
      label418: label473: GlobalCfg.getInstance(getContext()).setLocalNotify(paramString.page);
      label544: GlobalCfg.getInstance(this.context).saveValueToDB();
      GlobalCfg.getInstance(this.context).clearCache();
      return paramString;
      label603: paramString = null;
      break label339;
      label608: paramString = null;
    }
  }

  public boolean hasAlarmInfo()
  {
    return this.hasAlarmInfo;
  }

  public boolean hasReserveMsg()
  {
    return this.hasReserveMsg;
  }

  public boolean parseProgramTopics(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramString1 != null)
    {
      bool1 = bool2;
      if (paramString2 == null);
    }
    try
    {
      paramString1 = new JSONObject(paramString1).getJSONArray("data");
      int i = 0;
      while (true)
      {
        bool1 = bool2;
        if (i < paramString1.length())
        {
          Object localObject = paramString1.getJSONObject(i);
          if (((JSONObject)localObject).getString("type").equalsIgnoreCase("program"))
          {
            localObject = ((JSONObject)localObject).getJSONObject("textinfo").getString("program");
            if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
            {
              bool1 = ((String)localObject).equalsIgnoreCase(paramString2);
              if (bool1)
                bool1 = true;
            }
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.MessageCenter
 * JD-Core Version:    0.6.2
 */