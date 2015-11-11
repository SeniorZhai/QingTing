package master.flame.danmaku.danmaku.model;

public class DanmakuTimer
{
  public long currMillisecond;
  private long lastInterval;

  public long add(long paramLong)
  {
    return update(this.currMillisecond + paramLong);
  }

  public long lastInterval()
  {
    return this.lastInterval;
  }

  public long update(long paramLong)
  {
    this.lastInterval = (paramLong - this.currMillisecond);
    this.currMillisecond = paramLong;
    return this.lastInterval;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.DanmakuTimer
 * JD-Core Version:    0.6.2
 */