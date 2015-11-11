package master.flame.danmaku.controller;

import android.content.Context;
import android.graphics.Canvas;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.GlobalFlagValues;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig.ConfigChangedCallback;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState;
import master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer;
import master.flame.danmaku.danmaku.renderer.android.DanmakusRetainer;

public class DrawTask
  implements IDrawTask, DanmakuGlobalConfig.ConfigChangedCallback
{
  protected boolean clearRetainerFlag;
  protected IDanmakus danmakuList;
  private IDanmakus danmakus = new Danmakus(4);
  Context mContext;
  protected AbsDisplayer<?> mDisp;
  private boolean mIsHidden;
  private long mLastBeginMills;
  private long mLastEndMills;
  protected BaseDanmakuParser mParser;
  protected boolean mReadyState;
  IRenderer mRenderer;
  private IRenderer.RenderingState mRenderingState = new IRenderer.RenderingState();
  private long mStartRenderTime = 0L;
  IDrawTask.TaskListener mTaskListener;
  DanmakuTimer mTimer;

  static
  {
    if (!DrawTask.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public DrawTask(DanmakuTimer paramDanmakuTimer, Context paramContext, AbsDisplayer<?> paramAbsDisplayer, IDrawTask.TaskListener paramTaskListener)
  {
    this.mTaskListener = paramTaskListener;
    this.mContext = paramContext;
    this.mRenderer = new DanmakuRenderer();
    this.mDisp = paramAbsDisplayer;
    initTimer(paramDanmakuTimer);
    paramDanmakuTimer = Boolean.valueOf(DanmakuGlobalConfig.DEFAULT.isDuplicateMergingEnabled());
    if (paramDanmakuTimer != null)
    {
      if (paramDanmakuTimer.booleanValue())
        DanmakuFilters.getDefault().registerFilter("1017_Filter");
    }
    else
      return;
    DanmakuFilters.getDefault().unregisterFilter("1017_Filter");
  }

  public void addDanmaku(BaseDanmaku paramBaseDanmaku)
  {
    if (this.danmakuList == null)
      return;
    while (true)
    {
      synchronized (this.danmakuList)
      {
        while (true)
        {
          if (paramBaseDanmaku.isLive)
            removeUnusedLiveDanmakusIn(10);
          paramBaseDanmaku.index = this.danmakuList.size();
          if ((this.mLastBeginMills <= paramBaseDanmaku.time) && (paramBaseDanmaku.time <= this.mLastEndMills))
            synchronized (this.danmakus)
            {
              this.danmakus.addItem(paramBaseDanmaku);
              boolean bool = this.danmakuList.addItem(paramBaseDanmaku);
              if ((!bool) || (this.mTaskListener == null))
                break;
              this.mTaskListener.onDanmakuAdd(paramBaseDanmaku);
              return;
            }
        }
      }
      if (paramBaseDanmaku.isLive)
      {
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
      }
    }
  }

  public void clearDanmakusOnScreen(long paramLong)
  {
    reset();
    GlobalFlagValues.updateVisibleFlag();
    this.mStartRenderTime = paramLong;
  }

  public IRenderer.RenderingState draw(AbsDisplayer<?> paramAbsDisplayer)
  {
    return drawDanmakus(paramAbsDisplayer, this.mTimer);
  }

  protected IRenderer.RenderingState drawDanmakus(AbsDisplayer<?> paramAbsDisplayer, DanmakuTimer paramDanmakuTimer)
  {
    if (this.clearRetainerFlag)
    {
      DanmakusRetainer.clear();
      this.clearRetainerFlag = false;
    }
    if (this.danmakuList != null)
    {
      DrawHelper.clearCanvas((Canvas)paramAbsDisplayer.getExtraData());
      if (this.mIsHidden)
        return this.mRenderingState;
      long l2 = paramDanmakuTimer.currMillisecond - DanmakuFactory.MAX_DANMAKU_DURATION - 100L;
      long l1 = paramDanmakuTimer.currMillisecond + DanmakuFactory.MAX_DANMAKU_DURATION;
      if ((this.mLastBeginMills > l2) || (paramDanmakuTimer.currMillisecond > this.mLastEndMills))
      {
        paramAbsDisplayer = this.danmakuList.sub(l2, l1);
        if (paramAbsDisplayer != null)
        {
          this.danmakus = paramAbsDisplayer;
          this.mLastBeginMills = l2;
          this.mLastEndMills = l1;
        }
      }
      while (true)
      {
        if ((this.danmakus == null) || (this.danmakus.isEmpty()))
          break label236;
        paramAbsDisplayer = this.mRenderer.draw(this.mDisp, this.danmakus, this.mStartRenderTime);
        this.mRenderingState = paramAbsDisplayer;
        if (paramAbsDisplayer.nothingRendered)
        {
          if (paramAbsDisplayer.beginTime == -1L)
            paramAbsDisplayer.beginTime = l2;
          if (paramAbsDisplayer.endTime == -1L)
            paramAbsDisplayer.endTime = l1;
        }
        return paramAbsDisplayer;
        this.danmakus.clear();
        break;
        l2 = this.mLastBeginMills;
        l1 = this.mLastEndMills;
      }
      label236: this.mRenderingState.nothingRendered = true;
      this.mRenderingState.beginTime = l2;
      this.mRenderingState.endTime = l1;
      return this.mRenderingState;
    }
    return null;
  }

  protected boolean handleOnDanmakuConfigChanged(DanmakuGlobalConfig paramDanmakuGlobalConfig, DanmakuGlobalConfig.DanmakuConfigTag paramDanmakuConfigTag, Object[] paramArrayOfObject)
  {
    boolean bool = false;
    if ((paramDanmakuConfigTag == null) || (DanmakuGlobalConfig.DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(paramDanmakuConfigTag)))
    {
      bool = true;
      return bool;
    }
    if (DanmakuGlobalConfig.DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(paramDanmakuConfigTag))
    {
      paramDanmakuGlobalConfig = (Boolean)paramArrayOfObject[0];
      if (paramDanmakuGlobalConfig == null)
        break label103;
      if (paramDanmakuGlobalConfig.booleanValue())
        DanmakuFilters.getDefault().registerFilter("1017_Filter");
    }
    label60: label103: for (bool = true; ; bool = false)
    {
      return bool;
      DanmakuFilters.getDefault().unregisterFilter("1017_Filter");
      break label60;
      if ((!DanmakuGlobalConfig.DanmakuConfigTag.SCALE_TEXTSIZE.equals(paramDanmakuConfigTag)) && (!DanmakuGlobalConfig.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(paramDanmakuConfigTag)))
        break;
      requestClearRetainer();
      return false;
    }
  }

  protected void initTimer(DanmakuTimer paramDanmakuTimer)
  {
    this.mTimer = paramDanmakuTimer;
  }

  protected void loadDanmakus(BaseDanmakuParser paramBaseDanmakuParser)
  {
    this.danmakuList = paramBaseDanmakuParser.setDisplayer(this.mDisp).setTimer(this.mTimer).getDanmakus();
    GlobalFlagValues.resetAll();
  }

  public boolean onDanmakuConfigChanged(DanmakuGlobalConfig paramDanmakuGlobalConfig, DanmakuGlobalConfig.DanmakuConfigTag paramDanmakuConfigTag, Object[] paramArrayOfObject)
  {
    boolean bool = handleOnDanmakuConfigChanged(paramDanmakuGlobalConfig, paramDanmakuConfigTag, paramArrayOfObject);
    if (this.mTaskListener != null)
      this.mTaskListener.onDanmakuConfigChanged();
    return bool;
  }

  public void prepare()
  {
    assert (this.mParser != null);
    loadDanmakus(this.mParser);
    if (this.mTaskListener != null)
    {
      this.mTaskListener.ready();
      this.mReadyState = true;
    }
  }

  public void quit()
  {
    if (this.mRenderer != null)
      this.mRenderer.release();
    DanmakuGlobalConfig.DEFAULT.unregisterConfigChangedCallback(this);
  }

  public void removeAllDanmakus()
  {
    if ((this.danmakuList == null) || (this.danmakuList.isEmpty()))
      return;
    synchronized (this.danmakuList)
    {
      this.danmakuList.clear();
      return;
    }
  }

  public void removeAllLiveDanmakus()
  {
    if ((this.danmakus == null) || (this.danmakus.isEmpty()))
      return;
    synchronized (this.danmakus)
    {
      IDanmakuIterator localIDanmakuIterator = this.danmakus.iterator();
      while (localIDanmakuIterator.hasNext())
        if (localIDanmakuIterator.next().isLive)
          localIDanmakuIterator.remove();
    }
  }

  protected void removeUnusedLiveDanmakusIn(int paramInt)
  {
    if ((this.danmakuList == null) || (this.danmakuList.isEmpty()))
      return;
    synchronized (this.danmakuList)
    {
      long l = System.currentTimeMillis();
      IDanmakuIterator localIDanmakuIterator = this.danmakuList.iterator();
      boolean bool;
      do
      {
        if (!localIDanmakuIterator.hasNext())
          break;
        BaseDanmaku localBaseDanmaku = localIDanmakuIterator.next();
        bool = localBaseDanmaku.isTimeOut();
        if ((bool) && (localBaseDanmaku.isLive))
          localIDanmakuIterator.remove();
      }
      while ((bool) && (System.currentTimeMillis() - l <= paramInt));
      return;
    }
  }

  public void requestClear()
  {
    this.mLastEndMills = 0L;
    this.mLastBeginMills = 0L;
    this.mIsHidden = false;
  }

  public void requestClearRetainer()
  {
    this.clearRetainerFlag = true;
  }

  public void requestHide()
  {
    this.mIsHidden = true;
  }

  public void reset()
  {
    if (this.danmakus != null)
      this.danmakus.clear();
    if (this.mRenderer != null)
      this.mRenderer.clear();
  }

  public void seek(long paramLong)
  {
    reset();
    GlobalFlagValues.updateVisibleFlag();
    long l = paramLong;
    if (paramLong < 1000L)
      l = 0L;
    this.mStartRenderTime = l;
  }

  public void setParser(BaseDanmakuParser paramBaseDanmakuParser)
  {
    this.mParser = paramBaseDanmakuParser;
    this.mReadyState = false;
  }

  public void start()
  {
    DanmakuGlobalConfig.DEFAULT.registerConfigChangedCallback(this);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.DrawTask
 * JD-Core Version:    0.6.2
 */