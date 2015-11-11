package master.flame.danmaku.danmaku.model;

import android.content.Context;

public abstract interface IDisplayer
{
  public abstract int draw(BaseDanmaku paramBaseDanmaku);

  public abstract Context getContext();

  public abstract float getDensity();

  public abstract int getDensityDpi();

  public abstract int getHeight();

  public abstract int getMaximumCacheHeight();

  public abstract int getMaximumCacheWidth();

  public abstract float getScaledDensity();

  public abstract int getSlopPixel();

  public abstract float getStrokeWidth();

  public abstract int getWidth();

  public abstract boolean isHardwareAccelerated();

  public abstract void measure(BaseDanmaku paramBaseDanmaku);

  public abstract void resetSlopPixel(float paramFloat);

  public abstract void setContext(Context paramContext);

  public abstract void setDensities(float paramFloat1, int paramInt, float paramFloat2);

  public abstract void setHardwareAccelerated(boolean paramBoolean);

  public abstract void setSize(int paramInt1, int paramInt2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.IDisplayer
 * JD-Core Version:    0.6.2
 */