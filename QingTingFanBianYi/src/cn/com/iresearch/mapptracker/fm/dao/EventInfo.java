package cn.com.iresearch.mapptracker.fm.dao;

import cn.com.iresearch.mapptracker.fm.a.a.a.e;

@e(a="EventInfo")
public class EventInfo extends a
{

  @cn.com.iresearch.mapptracker.fm.a.a.a.a(a="infoId")
  public int _id;
  public long duration;
  private long end_time;
  public String event_id;
  private String event_params;
  public boolean eventisStart = false;
  public long first_start_time;
  private String label;
  public long open_count = 0L;
  private long start_time;

  public long getDuration()
  {
    return this.duration;
  }

  public long getEnd_time()
  {
    return this.end_time;
  }

  public String getEvent_id()
  {
    return this.event_id;
  }

  public String getEvent_params()
  {
    return this.event_params;
  }

  public String getLabel()
  {
    return this.label;
  }

  public long getOpen_count()
  {
    return this.open_count;
  }

  public long getStart_time()
  {
    return this.start_time;
  }

  public int get_id()
  {
    return this._id;
  }

  public void setDuration(long paramLong)
  {
    this.duration = paramLong;
  }

  public void setEnd_time(long paramLong)
  {
    this.end_time = paramLong;
  }

  public void setEvent_id(String paramString)
  {
    this.event_id = paramString;
  }

  public void setEvent_params(String paramString)
  {
    this.event_params = paramString;
  }

  public void setLabel(String paramString)
  {
    this.label = paramString;
  }

  public void setOpen_count(long paramLong)
  {
    this.open_count = paramLong;
  }

  public void setStart_time(long paramLong)
  {
    this.start_time = paramLong;
  }

  public void set_id(int paramInt)
  {
    this._id = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.dao.EventInfo
 * JD-Core Version:    0.6.2
 */