package master.flame.danmaku.danmaku.model;

public class Duration
{
  private float factor = 1.0F;
  private long mInitialDuration;
  public long value;

  public Duration(long paramLong)
  {
    this.mInitialDuration = paramLong;
    this.value = paramLong;
  }

  public void setFactor(float paramFloat)
  {
    if (this.factor != paramFloat)
    {
      this.factor = paramFloat;
      this.value = (()((float)this.mInitialDuration * paramFloat));
    }
  }

  public void setValue(long paramLong)
  {
    this.mInitialDuration = paramLong;
    this.value = (()((float)this.mInitialDuration * this.factor));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.Duration
 * JD-Core Version:    0.6.2
 */