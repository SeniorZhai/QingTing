package fm.qingting.qtradio.pushcontent;

public class TimeBean
{
  private long lastViewTime;
  private long updateTime;
  private long viewTime = 0L;

  public TimeBean(long paramLong1, long paramLong2)
  {
    this.lastViewTime = paramLong1;
    this.updateTime = paramLong2;
  }

  public void alignViewTime()
  {
    this.lastViewTime = this.updateTime;
  }

  public long getUpdateTime()
  {
    return this.updateTime;
  }

  public long getViewTime()
  {
    return this.lastViewTime;
  }

  public void setUpdateTime(long paramLong)
  {
    this.updateTime = paramLong;
    if (this.lastViewTime <= 0L)
      this.lastViewTime = paramLong;
  }

  public void setViewTime(long paramLong)
  {
    if (this.viewTime == 0L)
    {
      this.viewTime = paramLong;
      return;
    }
    this.lastViewTime = this.viewTime;
    this.viewTime = paramLong;
  }

  public String toString()
  {
    return "view:" + this.lastViewTime + ",update:" + this.updateTime;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushcontent.TimeBean
 * JD-Core Version:    0.6.2
 */