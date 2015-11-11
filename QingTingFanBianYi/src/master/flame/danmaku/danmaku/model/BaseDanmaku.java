package master.flame.danmaku.danmaku.model;

public abstract class BaseDanmaku
{
  public static final String DANMAKU_BR_CHAR = "/n";
  public static final int INVISIBLE = 0;
  public static final int TYPE_FIX_BOTTOM = 4;
  public static final int TYPE_FIX_TOP = 5;
  public static final int TYPE_MOVEABLE_XXX = 0;
  public static final int TYPE_SCROLL_LR = 6;
  public static final int TYPE_SCROLL_RL = 1;
  public static final int TYPE_SPECIAL = 7;
  public static final int VISIBLE = 1;
  protected int alpha = AlphaValue.MAX;
  public int bgColor = -1;
  public int borderColor = 0;
  public IDrawingCache<?> cache;
  public int drawableLeftResid = 0;
  public Duration duration;
  public int index;
  public boolean isGuest;
  public boolean isLive;
  public String[] lines;
  protected DanmakuTimer mTimer;
  private int measureResetFlag = 0;
  public int padding = 0;
  public float paintHeight = -1.0F;
  public float paintWidth = -1.0F;
  public byte priority = 0;
  public float rotationY;
  public float rotationZ;
  public String text;
  public int textColor;
  public int textShadowColor;
  public float textSize = -1.0F;
  public long time;
  public int underlineColor = 0;
  public String userHash;
  public int userId = 0;
  public int visibility;
  private int visibleResetFlag = 0;

  public int draw(IDisplayer paramIDisplayer)
  {
    return paramIDisplayer.draw(this);
  }

  public int getAlpha()
  {
    return this.alpha;
  }

  public abstract float getBottom();

  public long getDuration()
  {
    return this.duration.value;
  }

  public abstract float getLeft();

  public abstract float[] getRectAtTime(IDisplayer paramIDisplayer, long paramLong);

  public abstract float getRight();

  public DanmakuTimer getTimer()
  {
    return this.mTimer;
  }

  public abstract float getTop();

  public abstract int getType();

  public boolean hasDrawingCache()
  {
    return (this.cache != null) && (this.cache.get() != null);
  }

  public boolean isLate()
  {
    return (this.mTimer == null) || (this.mTimer.currMillisecond < this.time);
  }

  public boolean isMeasured()
  {
    return (this.paintWidth >= 0.0F) && (this.paintHeight >= 0.0F) && (this.measureResetFlag == GlobalFlagValues.MEASURE_RESET_FLAG);
  }

  public boolean isOutside()
  {
    return (this.mTimer == null) || (isOutside(this.mTimer.currMillisecond));
  }

  public boolean isOutside(long paramLong)
  {
    paramLong -= this.time;
    return (paramLong <= 0L) || (paramLong >= this.duration.value);
  }

  public boolean isShown()
  {
    return (this.visibility == 1) && (this.visibleResetFlag == GlobalFlagValues.VISIBLE_RESET_FLAG);
  }

  public boolean isTimeOut()
  {
    return (this.mTimer == null) || (isTimeOut(this.mTimer.currMillisecond));
  }

  public boolean isTimeOut(long paramLong)
  {
    return paramLong - this.time >= this.duration.value;
  }

  public abstract void layout(IDisplayer paramIDisplayer, float paramFloat1, float paramFloat2);

  public void measure(IDisplayer paramIDisplayer)
  {
    paramIDisplayer.measure(this);
    this.measureResetFlag = GlobalFlagValues.MEASURE_RESET_FLAG;
  }

  public void setDuration(Duration paramDuration)
  {
    this.duration = paramDuration;
  }

  public void setTimer(DanmakuTimer paramDanmakuTimer)
  {
    this.mTimer = paramDanmakuTimer;
  }

  public void setVisibility(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.visibleResetFlag = GlobalFlagValues.VISIBLE_RESET_FLAG;
      this.visibility = 1;
      return;
    }
    this.visibility = 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.BaseDanmaku
 * JD-Core Version:    0.6.2
 */