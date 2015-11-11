package master.flame.danmaku.danmaku.renderer;

import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;

public abstract interface IRenderer
{
  public static final int CACHE_RENDERING = 1;
  public static final int NOTHING_RENDERING = 0;
  public static final int TEXT_RENDERING = 2;

  public abstract void clear();

  public abstract RenderingState draw(IDisplayer paramIDisplayer, IDanmakus paramIDanmakus, long paramLong);

  public abstract void release();

  public static class Area
  {
    private int mMaxHeight;
    private int mMaxWidth;
    public final float[] mRefreshRect = new float[4];

    public void reset()
    {
      set(this.mMaxWidth, this.mMaxHeight, 0.0F, 0.0F);
    }

    public void resizeToMax()
    {
      set(0.0F, 0.0F, this.mMaxWidth, this.mMaxHeight);
    }

    public void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.mRefreshRect[0] = paramFloat1;
      this.mRefreshRect[1] = paramFloat2;
      this.mRefreshRect[2] = paramFloat3;
      this.mRefreshRect[3] = paramFloat4;
    }

    public void setEdge(int paramInt1, int paramInt2)
    {
      this.mMaxWidth = paramInt1;
      this.mMaxHeight = paramInt2;
    }
  }

  public static class RenderingState
  {
    public static final int UNKNOWN_TIME = -1;
    public long beginTime;
    public long cacheHitCount;
    public long cacheMissCount;
    public long consumingTime;
    public long endTime;
    public int fbDanmakuCount;
    public int ftDanmakuCount;
    public int incrementCount;
    public int l2rDanmakuCount;
    public boolean nothingRendered;
    public int r2lDanmakuCount;
    public int specialDanmakuCount;
    public long sysTime;
    public int totalDanmakuCount;

    public int addCount(int paramInt1, int paramInt2)
    {
      switch (paramInt1)
      {
      case 2:
      case 3:
      default:
        return 0;
      case 1:
        this.r2lDanmakuCount += paramInt2;
        return this.r2lDanmakuCount;
      case 6:
        this.l2rDanmakuCount += paramInt2;
        return this.l2rDanmakuCount;
      case 5:
        this.ftDanmakuCount += paramInt2;
        return this.ftDanmakuCount;
      case 4:
        this.fbDanmakuCount += paramInt2;
        return this.fbDanmakuCount;
      case 7:
      }
      this.specialDanmakuCount += paramInt2;
      return this.specialDanmakuCount;
    }

    public int addTotalCount(int paramInt)
    {
      this.totalDanmakuCount += paramInt;
      return this.totalDanmakuCount;
    }

    public void reset()
    {
      this.totalDanmakuCount = 0;
      this.specialDanmakuCount = 0;
      this.fbDanmakuCount = 0;
      this.ftDanmakuCount = 0;
      this.l2rDanmakuCount = 0;
      this.r2lDanmakuCount = 0;
      this.consumingTime = 0L;
      this.endTime = 0L;
      this.beginTime = 0L;
      this.sysTime = 0L;
      this.nothingRendered = false;
    }

    public void set(RenderingState paramRenderingState)
    {
      if (paramRenderingState == null)
        return;
      this.r2lDanmakuCount = paramRenderingState.r2lDanmakuCount;
      this.l2rDanmakuCount = paramRenderingState.l2rDanmakuCount;
      this.ftDanmakuCount = paramRenderingState.ftDanmakuCount;
      this.fbDanmakuCount = paramRenderingState.fbDanmakuCount;
      this.specialDanmakuCount = paramRenderingState.specialDanmakuCount;
      this.totalDanmakuCount = paramRenderingState.totalDanmakuCount;
      this.incrementCount = paramRenderingState.incrementCount;
      this.consumingTime = paramRenderingState.consumingTime;
      this.beginTime = paramRenderingState.beginTime;
      this.endTime = paramRenderingState.endTime;
      this.nothingRendered = paramRenderingState.nothingRendered;
      this.sysTime = paramRenderingState.sysTime;
      this.cacheHitCount = paramRenderingState.cacheHitCount;
      this.cacheMissCount = paramRenderingState.cacheMissCount;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.renderer.IRenderer
 * JD-Core Version:    0.6.2
 */