package cn.com.iresearch.mapptracker.fm.dao;

import cn.com.iresearch.mapptracker.fm.a.a.a.e;

@e(a="SessionInfo")
public class SessionInfo extends a
{

  @cn.com.iresearch.mapptracker.fm.a.a.a.a(a="infoId")
  public int _id;
  public String dd = "";
  public long duration;
  public long end_time = 0L;
  public long inapp = 3L;
  public String page_name = "";
  public String sessionid = "";
  public long start_time = 0L;

  public String getDd()
  {
    return this.dd;
  }

  public long getDuration()
  {
    return this.duration;
  }

  public long getEnd_time()
  {
    return this.end_time;
  }

  public long getInapp()
  {
    return this.inapp;
  }

  public String getPage_name()
  {
    return this.page_name;
  }

  public String getSessionid()
  {
    return this.sessionid;
  }

  public long getStart_time()
  {
    return this.start_time;
  }

  public int get_id()
  {
    return this._id;
  }

  public void setDd(String paramString)
  {
    this.dd = paramString;
  }

  public void setDuration(long paramLong)
  {
    this.duration = paramLong;
  }

  public void setEnd_time(long paramLong)
  {
    this.end_time = paramLong;
  }

  public void setInapp(long paramLong)
  {
    this.inapp = paramLong;
  }

  public void setPage_name(String paramString)
  {
    this.page_name = paramString;
  }

  public void setSessionid(String paramString)
  {
    this.sessionid = paramString;
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
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.dao.SessionInfo
 * JD-Core Version:    0.6.2
 */