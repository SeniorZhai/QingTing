package master.flame.danmaku.danmaku.parser;

import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;

public abstract class BaseDanmakuParser
{
  private IDanmakus mDanmakus;
  protected IDataSource<?> mDataSource;
  protected IDisplayer mDisp;
  protected float mDispDensity;
  protected int mDispHeight;
  protected int mDispWidth;
  protected float mScaledDensity;
  protected DanmakuTimer mTimer;

  public IDanmakus getDanmakus()
  {
    if (this.mDanmakus != null)
      return this.mDanmakus;
    DanmakuFactory.resetDurationsData();
    this.mDanmakus = parse();
    releaseDataSource();
    DanmakuFactory.updateMaxDanmakuDuration();
    return this.mDanmakus;
  }

  public IDisplayer getDisplayer()
  {
    return this.mDisp;
  }

  public DanmakuTimer getTimer()
  {
    return this.mTimer;
  }

  protected float getViewportSizeFactor()
  {
    return 1.0F / (this.mDispDensity - 0.6F);
  }

  public BaseDanmakuParser load(IDataSource<?> paramIDataSource)
  {
    this.mDataSource = paramIDataSource;
    return this;
  }

  protected abstract IDanmakus parse();

  public void release()
  {
    releaseDataSource();
  }

  protected void releaseDataSource()
  {
    if (this.mDataSource != null)
      this.mDataSource.release();
    this.mDataSource = null;
  }

  public BaseDanmakuParser setDisplayer(IDisplayer paramIDisplayer)
  {
    this.mDisp = paramIDisplayer;
    this.mDispWidth = paramIDisplayer.getWidth();
    this.mDispHeight = paramIDisplayer.getHeight();
    this.mDispDensity = paramIDisplayer.getDensity();
    this.mScaledDensity = paramIDisplayer.getScaledDensity();
    DanmakuFactory.updateViewportState(this.mDispWidth, this.mDispHeight, getViewportSizeFactor());
    DanmakuFactory.updateMaxDanmakuDuration();
    return this;
  }

  public BaseDanmakuParser setTimer(DanmakuTimer paramDanmakuTimer)
  {
    this.mTimer = paramDanmakuTimer;
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.BaseDanmakuParser
 * JD-Core Version:    0.6.2
 */