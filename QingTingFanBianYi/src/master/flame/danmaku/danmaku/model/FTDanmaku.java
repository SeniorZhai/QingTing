package master.flame.danmaku.danmaku.model;

public class FTDanmaku extends BaseDanmaku
{
  private float[] RECT = null;
  private int mLastDispWidth;
  private float mLastLeft;
  private float mLastPaintWidth;
  private float x = 0.0F;
  protected float y = -1.0F;

  public FTDanmaku(Duration paramDuration)
  {
    this.duration = paramDuration;
  }

  public float getBottom()
  {
    return this.y + this.paintHeight;
  }

  public float getLeft()
  {
    return this.x;
  }

  protected float getLeft(IDisplayer paramIDisplayer)
  {
    if ((this.mLastDispWidth == paramIDisplayer.getWidth()) && (this.mLastPaintWidth == this.paintWidth))
      return this.mLastLeft;
    float f = (paramIDisplayer.getWidth() - this.paintWidth) / 2.0F;
    this.mLastDispWidth = paramIDisplayer.getWidth();
    this.mLastPaintWidth = this.paintWidth;
    this.mLastLeft = f;
    return f;
  }

  public float[] getRectAtTime(IDisplayer paramIDisplayer, long paramLong)
  {
    if (!isMeasured())
      return null;
    float f = getLeft(paramIDisplayer);
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
    return 5;
  }

  public void layout(IDisplayer paramIDisplayer, float paramFloat1, float paramFloat2)
  {
    if (this.mTimer != null)
    {
      long l = this.mTimer.currMillisecond - this.time;
      if ((l <= 0L) || (l >= this.duration.value))
        break label68;
      if (!isShown())
      {
        this.x = getLeft(paramIDisplayer);
        this.y = paramFloat2;
        setVisibility(true);
      }
    }
    return;
    label68: setVisibility(false);
    this.y = -1.0F;
    this.x = paramIDisplayer.getWidth();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.FTDanmaku
 * JD-Core Version:    0.6.2
 */