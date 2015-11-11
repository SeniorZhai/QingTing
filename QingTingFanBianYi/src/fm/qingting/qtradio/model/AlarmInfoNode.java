package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.alarm.ClockManager;
import fm.qingting.qtradio.alarm.ClockManager.IClockListener;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.notification.Notifier;
import fm.qingting.utils.LifeTime;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AlarmInfoNode extends Node
  implements ClockManager.IClockListener
{
  private static final String[] NEXTWEEKS = { "", "下周日", "下周一", "下周二", "下周三", "下周四", "下周五", "下周六" };
  private static final int TIME_THRESHOLD = 2;
  private static final String[] WEEKS = { "", "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
  private boolean hasRestored = false;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
      }
      int i = ((Integer)paramAnonymousMessage.obj).intValue();
      AlarmInfoNode.this.chooseAlarm(i);
    }
  };
  public List<AlarmInfo> mLstAlarms;
  private boolean needToWriteToDB = false;

  public AlarmInfoNode()
  {
    this.nodeName = "alarminfo";
  }

  private void chooseAlarm(long paramLong)
  {
    if ((this.mLstAlarms == null) || (this.mLstAlarms.size() == 0));
    label273: label280: 
    while (true)
    {
      return;
      Object localObject = Calendar.getInstance();
      int j = ((Calendar)localObject).get(7);
      int k = (int)Math.pow(2.0D, j);
      ((Calendar)localObject).setTimeInMillis(1000L * paramLong);
      int m = ((Calendar)localObject).get(11) * 60 * 60 + ((Calendar)localObject).get(12) * 60 + ((Calendar)localObject).get(13);
      int i = 0;
      while (true)
      {
        if (i >= this.mLstAlarms.size())
          break label280;
        localObject = (AlarmInfo)this.mLstAlarms.get(i);
        if (((AlarmInfo)localObject).isAvailable)
        {
          if ((((AlarmInfo)localObject).dayOfWeek & k) != 0)
          {
            if (!hitAlarm((AlarmInfo)localObject, m))
              break label273;
            sendAlarmNotification((AlarmInfo)localObject);
            ((AlarmInfo)localObject).hasShouted = true;
            if (((AlarmInfo)localObject).repeat)
              break;
            ((AlarmInfo)localObject).isAvailable = false;
            ((AlarmInfo)localObject).hasShouted = false;
            return;
          }
          if ((((AlarmInfo)localObject).dayOfWeek == 0) && (isWorkDayToday(j)))
          {
            if (!hitAlarm((AlarmInfo)localObject, m))
              break label273;
            sendAlarmNotification((AlarmInfo)localObject);
            ((AlarmInfo)localObject).hasShouted = true;
            if (((AlarmInfo)localObject).repeat)
              break;
            ((AlarmInfo)localObject).isAvailable = false;
            ((AlarmInfo)localObject).hasShouted = false;
            return;
          }
          if ((!((AlarmInfo)localObject).repeat) && (hitAlarm((AlarmInfo)localObject, m)))
          {
            sendAlarmNotification((AlarmInfo)localObject);
            ((AlarmInfo)localObject).hasShouted = true;
            if (((AlarmInfo)localObject).repeat)
              break;
            ((AlarmInfo)localObject).isAvailable = false;
            ((AlarmInfo)localObject).hasShouted = false;
            return;
          }
        }
        i += 1;
      }
    }
  }

  private void deleteFromDB()
  {
    DataManager.getInstance().getData("deletedb_alarm_info", null, null);
  }

  private int getDayOfWeek(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar.get(7);
  }

  private int getHourOfDay()
  {
    return Calendar.getInstance().get(11);
  }

  private boolean hitAlarm(AlarmInfo paramAlarmInfo, long paramLong)
  {
    if (paramAlarmInfo == null);
    do
    {
      return false;
      if ((paramAlarmInfo.alarmTime < paramLong) && (paramLong < paramAlarmInfo.alarmTime + 2L) && (!paramAlarmInfo.hasShouted))
        return true;
    }
    while ((paramLong <= paramAlarmInfo.alarmTime + 2L) || (!paramAlarmInfo.hasShouted));
    paramAlarmInfo.hasShouted = false;
    return false;
  }

  private int isExisted(AlarmInfo paramAlarmInfo)
  {
    int j;
    if (paramAlarmInfo == null)
    {
      j = -1;
      return j;
    }
    if ((this.mLstAlarms == null) || (this.mLstAlarms.size() == 0))
      return -1;
    int i = 0;
    while (true)
    {
      if (i >= this.mLstAlarms.size())
        break label100;
      if (((AlarmInfo)this.mLstAlarms.get(i)).alarmTime == paramAlarmInfo.alarmTime)
      {
        j = i;
        if (((AlarmInfo)this.mLstAlarms.get(i)).dayOfWeek == paramAlarmInfo.dayOfWeek)
          break;
      }
      i += 1;
    }
    label100: return -1;
  }

  private boolean isWorkDayToday(int paramInt)
  {
    return (paramInt >= 2) && (paramInt <= 6);
  }

  private boolean needToWriteToDB()
  {
    return this.needToWriteToDB;
  }

  private void sendAlarmNotification(AlarmInfo paramAlarmInfo)
  {
    String str;
    if (paramAlarmInfo != null)
    {
      int i = getHourOfDay();
      QTMSGManage.getInstance().sendStatistcsMessage("ClockActive", String.valueOf(i));
      QTMSGManage.getInstance().sendStatistcsMessage("StartActivityByClock", String.valueOf(i));
      if (paramAlarmInfo.channelName == null)
        break label227;
      str = "蜻蜓闹钟提醒：" + paramAlarmInfo.channelName;
      str = str + "开始广播啦";
      new Notifier(ControllerManager.getInstance().getContext()).notify("11", "", "闹钟", str, null, "", paramAlarmInfo.channelName, paramAlarmInfo.channelId, "alarm", paramAlarmInfo.categoryId, Integer.valueOf(paramAlarmInfo.ringToneId).intValue(), 0, 0, paramAlarmInfo.alarmType, null, null);
      if ((paramAlarmInfo.ringToneId == null) || (InfoManager.getInstance().isNetworkAvailable()))
        break label252;
      InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARMPLAY);
      if (InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById(paramAlarmInfo.ringToneId) != null)
      {
        InfoManager.getInstance().root().mRingToneInfoNode.setAvaliableRingId(paramAlarmInfo.ringToneId);
        PlayerAgent.getInstance().playRingTone(InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById(paramAlarmInfo.ringToneId));
      }
    }
    label227: label252: 
    while ((!InfoManager.getInstance().isNetworkAvailable()) || (PlayerAgent.getInstance().getCurrentPlayStatus() == 4096))
    {
      return;
      str = "蜻蜓闹钟提醒：" + "您有一个闹钟到点了";
      break;
    }
    ControllerManager.getInstance().openPlayViewForAlarm(paramAlarmInfo.categoryId, paramAlarmInfo.channelId, paramAlarmInfo.programId, paramAlarmInfo.alarmType);
  }

  public void WriteToDB()
  {
    if (!needToWriteToDB());
    do
    {
      return;
      this.needToWriteToDB = false;
      deleteFromDB();
    }
    while ((this.mLstAlarms == null) || (this.mLstAlarms.size() == 0));
    HashMap localHashMap = new HashMap();
    localHashMap.put("alarmInfos", this.mLstAlarms);
    DataManager.getInstance().getData("insertdb_alarm_info", null, localHashMap);
  }

  public void addDefaultAlarm(AlarmInfo paramAlarmInfo)
  {
    if (paramAlarmInfo == null);
    do
    {
      return;
      if (this.mLstAlarms == null)
        this.mLstAlarms = new ArrayList();
    }
    while (this.mLstAlarms.size() != 0);
    this.mLstAlarms.add(paramAlarmInfo);
    this.needToWriteToDB = true;
  }

  public String getNearestAlarmInfo()
  {
    Object localObject = this.mLstAlarms;
    if ((localObject == null) || (((List)localObject).size() == 0))
      return null;
    long l1 = 9223372036854775807L;
    int i = 0;
    if (i < ((List)localObject).size())
    {
      long l2 = ((AlarmInfo)((List)localObject).get(i)).getNextShoutTime();
      if (l2 == 9223372036854775807L)
        l2 = l1;
      while (true)
      {
        i += 1;
        l1 = l2;
        break;
        long l3 = l2 * 1000L + System.currentTimeMillis();
        l2 = l1;
        if (l1 > l3)
          l2 = l3;
      }
    }
    if (l1 == 9223372036854775807L)
      return null;
    localObject = new StringBuilder();
    Calendar localCalendar = Calendar.getInstance();
    i = localCalendar.get(7);
    int j = localCalendar.get(11);
    int k = localCalendar.get(12);
    localCalendar.setTimeInMillis(l1);
    int m = localCalendar.get(7);
    int n = localCalendar.get(11);
    int i1 = localCalendar.get(12);
    if ((m - i == 1) || ((i == 7) && (m == 1)))
    {
      ((StringBuilder)localObject).append("明天 ");
      ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
    }
    while (true)
    {
      return ((StringBuilder)localObject).toString();
      if (m == i)
      {
        if (j < n)
        {
          ((StringBuilder)localObject).append("今天 ");
          ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
        }
        else if (j == n)
        {
          if (k < i1)
          {
            ((StringBuilder)localObject).append("今天 ");
            ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
          }
          else
          {
            ((StringBuilder)localObject).append(NEXTWEEKS[m]);
            ((StringBuilder)localObject).append(" ");
            ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
          }
        }
        else
        {
          ((StringBuilder)localObject).append(NEXTWEEKS[m]);
          ((StringBuilder)localObject).append(" ");
          ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
        }
      }
      else if (m < i)
      {
        ((StringBuilder)localObject).append(NEXTWEEKS[m]);
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
      }
      else
      {
        ((StringBuilder)localObject).append(WEEKS[m]);
        ((StringBuilder)localObject).append(" ");
        ((StringBuilder)localObject).append(String.format(Locale.US, "%02d:%02d", new Object[] { Integer.valueOf(n), Integer.valueOf(i1) }));
      }
    }
  }

  public void init()
  {
    if (!LifeTime.isFirstLaunchAfterInstall)
      restoreFromDB();
    ClockManager.getInstance().addListener(this);
  }

  public boolean isEmpty()
  {
    if (this.mLstAlarms == null);
    while (this.mLstAlarms.size() == 0)
      return true;
    return false;
  }

  public void onClockTime(int paramInt)
  {
    if (this.mHandler != null)
    {
      Message localMessage = Message.obtain(this.mHandler, 1, Integer.valueOf(paramInt));
      this.mHandler.sendMessage(localMessage);
    }
  }

  public void onTime(Clock paramClock)
  {
  }

  public void onTimeStart(Clock paramClock)
  {
  }

  public void onTimeStop(Clock paramClock)
  {
  }

  public void onTimerRemoved()
  {
  }

  public void removeAlarm(int paramInt)
  {
    if (paramInt >= this.mLstAlarms.size())
      return;
    Object localObject = (AlarmInfo)this.mLstAlarms.get(paramInt);
    int i = (int)(((AlarmInfo)localObject).alarmTime / 3600L);
    if (((AlarmInfo)localObject).repeat);
    for (localObject = "repeat"; ; localObject = "not")
    {
      this.mLstAlarms.remove(paramInt);
      this.needToWriteToDB = true;
      QTMSGManage.getInstance().sendStatistcsMessage("ClockRemove", String.valueOf(i));
      QTMSGManage.getInstance().sendStatistcsMessage("alarm_remove", (String)localObject);
      return;
    }
  }

  public void removeAlarm(AlarmInfo paramAlarmInfo)
  {
    if (paramAlarmInfo == null)
      return;
    int i = isExisted(paramAlarmInfo);
    if (i != -1)
    {
      this.mLstAlarms.remove(i);
      this.needToWriteToDB = true;
    }
    i = (int)(paramAlarmInfo.alarmTime / 3600L);
    QTMSGManage.getInstance().sendStatistcsMessage("ClockRemove", String.valueOf(i));
  }

  public boolean restoreFromDB()
  {
    if (this.hasRestored);
    List localList;
    do
    {
      return true;
      this.hasRestored = true;
      localList = (List)DataManager.getInstance().getData("getdb_alarm_info", null, null).getResult().getData();
    }
    while ((localList == null) || (localList.size() == 0));
    this.mLstAlarms = localList;
    return true;
  }

  public void updateAlarm(AlarmInfo paramAlarmInfo)
  {
    if (paramAlarmInfo == null)
      return;
    if (!paramAlarmInfo.repeat)
      i = (int)Math.pow(2.0D, getDayOfWeek(paramAlarmInfo.getNextShoutTime() * 1000L + System.currentTimeMillis()));
    int i = isExisted(paramAlarmInfo);
    if (i == -1)
    {
      AlarmInfo localAlarmInfo = new AlarmInfo();
      localAlarmInfo.update(paramAlarmInfo);
      localAlarmInfo.hasShouted = false;
      if ((localAlarmInfo.channelId == 0) || (localAlarmInfo.categoryId == 0))
      {
        localAlarmInfo.categoryId = 54;
        localAlarmInfo.channelId = 386;
        localAlarmInfo.channelName = "CNR中国之声";
      }
      this.mLstAlarms.add(localAlarmInfo);
    }
    while (true)
    {
      this.needToWriteToDB = true;
      if (!paramAlarmInfo.isAvailable)
        break;
      i = (int)(paramAlarmInfo.alarmTime / 3600L);
      int j = paramAlarmInfo.dayOfWeek;
      QTMSGManage.getInstance().sendStatistcsMessage("ClockSettingNew", String.valueOf(i));
      return;
      ((AlarmInfo)this.mLstAlarms.get(i)).update(paramAlarmInfo);
      ((AlarmInfo)this.mLstAlarms.get(i)).hasShouted = false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlarmInfoNode
 * JD-Core Version:    0.6.2
 */