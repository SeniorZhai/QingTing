package fm.qingting.qtradio.model;

public class AlarmInfoLegacy
{
  public long alarmTime;
  public int categoryId;
  public int channelId;
  public String channelName;
  public int dayOfWeek = 0;
  public boolean hasShouted = false;
  public boolean isAvailable = true;
  public boolean repeat = true;

  public void update(AlarmInfoLegacy paramAlarmInfoLegacy)
  {
    if (paramAlarmInfoLegacy == null)
      return;
    this.channelName = paramAlarmInfoLegacy.channelName;
    this.channelId = paramAlarmInfoLegacy.channelId;
    this.categoryId = paramAlarmInfoLegacy.categoryId;
    this.alarmTime = paramAlarmInfoLegacy.alarmTime;
    this.repeat = paramAlarmInfoLegacy.repeat;
    this.dayOfWeek = paramAlarmInfoLegacy.dayOfWeek;
    this.isAvailable = paramAlarmInfoLegacy.isAvailable;
    this.hasShouted = paramAlarmInfoLegacy.hasShouted;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlarmInfoLegacy
 * JD-Core Version:    0.6.2
 */