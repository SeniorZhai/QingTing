package master.flame.danmaku.danmaku.renderer.android;

import master.flame.danmaku.controller.DanmakuFilters;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState;
import master.flame.danmaku.danmaku.renderer.Renderer;

public class DanmakuRenderer extends Renderer
{
  private final IRenderer.RenderingState mRenderingState = new IRenderer.RenderingState();
  private final DanmakuTimer mStartTimer = new DanmakuTimer();

  public void clear()
  {
    DanmakusRetainer.clear();
    DanmakuFilters.getDefault().clear();
  }

  public IRenderer.RenderingState draw(IDisplayer paramIDisplayer, IDanmakus paramIDanmakus, long paramLong)
  {
    int k = this.mRenderingState.totalDanmakuCount;
    this.mRenderingState.reset();
    IDanmakuIterator localIDanmakuIterator = paramIDanmakus.iterator();
    int j = 0;
    this.mStartTimer.update(System.currentTimeMillis());
    int m = paramIDanmakus.size();
    paramIDanmakus = null;
    BaseDanmaku localBaseDanmaku;
    boolean bool;
    if (localIDanmakuIterator.hasNext())
    {
      localBaseDanmaku = localIDanmakuIterator.next();
      if (localBaseDanmaku.isLate())
        paramIDanmakus = localBaseDanmaku;
    }
    else
    {
      paramIDisplayer = this.mRenderingState;
      if (this.mRenderingState.totalDanmakuCount != 0)
        break label410;
      bool = true;
      label96: paramIDisplayer.nothingRendered = bool;
      paramIDisplayer = this.mRenderingState;
      if (paramIDanmakus == null)
        break label416;
    }
    label410: label416: for (paramLong = paramIDanmakus.time; ; paramLong = -1L)
    {
      paramIDisplayer.endTime = paramLong;
      if (this.mRenderingState.nothingRendered)
        this.mRenderingState.beginTime = -1L;
      this.mRenderingState.incrementCount = (this.mRenderingState.totalDanmakuCount - k);
      this.mRenderingState.consumingTime = this.mStartTimer.update(System.currentTimeMillis());
      return this.mRenderingState;
      paramIDanmakus = localBaseDanmaku;
      if (localBaseDanmaku.time < paramLong)
        break;
      if (localBaseDanmaku.priority == 0)
      {
        paramIDanmakus = localBaseDanmaku;
        if (DanmakuFilters.getDefault().filter(localBaseDanmaku, j, m, this.mStartTimer, false))
          break;
      }
      int i = j;
      if (localBaseDanmaku.getType() == 1)
        i = j + 1;
      if (!localBaseDanmaku.isMeasured())
        localBaseDanmaku.measure(paramIDisplayer);
      DanmakusRetainer.fix(localBaseDanmaku, paramIDisplayer);
      paramIDanmakus = localBaseDanmaku;
      j = i;
      if (localBaseDanmaku.isOutside())
        break;
      paramIDanmakus = localBaseDanmaku;
      j = i;
      if (!localBaseDanmaku.isShown())
        break;
      if (localBaseDanmaku.lines == null)
      {
        paramIDanmakus = localBaseDanmaku;
        j = i;
        if (localBaseDanmaku.getBottom() > paramIDisplayer.getHeight())
          break;
      }
      j = localBaseDanmaku.draw(paramIDisplayer);
      if (j == 1)
      {
        paramIDanmakus = this.mRenderingState;
        paramIDanmakus.cacheHitCount += 1L;
      }
      while (true)
      {
        this.mRenderingState.addCount(localBaseDanmaku.getType(), 1);
        this.mRenderingState.addTotalCount(1);
        paramIDanmakus = localBaseDanmaku;
        j = i;
        break;
        if (j == 2)
        {
          paramIDanmakus = this.mRenderingState;
          paramIDanmakus.cacheMissCount += 1L;
        }
      }
      bool = false;
      break label96;
    }
  }

  public void release()
  {
    DanmakusRetainer.release();
    DanmakuFilters.getDefault().release();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer
 * JD-Core Version:    0.6.2
 */