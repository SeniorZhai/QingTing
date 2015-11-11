package master.flame.danmaku.danmaku.model;

public class R2LDanmaku extends BaseDanmaku
{
  protected static final long CORDON_RENDERING_TIME = 40L;
  protected static final long MAX_RENDERING_TIME = 100L;
  protected float[] RECT = null;
  protected int mDistance;
  protected long mLastTime;
  protected float mStepX;
  protected float x = 0.0F;
  protected float y = -1.0F;

  public R2LDanmaku(Duration paramDuration)
  {
    this.duration = paramDuration;
  }

  protected float getAccurateLeft(IDisplayer paramIDisplayer, long paramLong)
  {
    paramLong -= this.time;
    if (paramLong >= this.duration.value)
      return -this.paintWidth;
    return paramIDisplayer.getWidth() - (float)paramLong * this.mStepX;
  }

  public float getBottom()
  {
    return this.y + this.paintHeight;
  }

  public float getLeft()
  {
    return this.x;
  }

  public float[] getRectAtTime(IDisplayer paramIDisplayer, long paramLong)
  {
    if (!isMeasured())
      return null;
    float f = getAccurateLeft(paramIDisplayer, paramLong);
    if (this.RECT == null)
      this.RECT = new float[4];
    this.RECT[0] = f;
    this.RECT[1] = this.y;
    this.RECT[2] = (f + this.paintWidth);
    this.RECT[3] = (this.y + this.paintHeight);
    return this.RECT;
  }

  public float getRight()
  {
    return this.x + this.paintWidth;
  }

  public float getTop()
  {
    return this.y;
  }

  public int getType()
  {
    return 1;
  }

  public void layout(IDisplayer paramIDisplayer, float paramFloat1, float paramFloat2)
  {
    if (this.mTimer != null)
    {
      long l1 = this.mTimer.currMillisecond;
      long l2 = l1 - this.time;
      if ((l2 > 0L) && (l2 < this.duration.value))
      {
        this.x = getAccurateLeft(paramIDisplayer, l1);
        if (!isShown())
        {
          this.y = paramFloat2;
          setVisibility(true);
        }
        this.mLastTime = l1;
        return;
      }
      this.mLastTime = l1;
    }
    setVisibility(false);
  }

  public void measure(IDisplayer paramIDisplayer)
  {
    super.measure(paramIDisplayer);
    this.mDistance = ((int)(paramIDisplayer.getWidth() + this.paintWidth));
    this.mStepX = (this.mDistance / (float)this.duration.value);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.R2LDanmaku
 * JD-Core Version:    0.6.2
 */