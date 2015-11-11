package master.flame.danmaku.controller;

import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState;

public abstract interface IDrawTask
{
  public abstract void addDanmaku(BaseDanmaku paramBaseDanmaku);

  public abstract void clearDanmakusOnScreen(long paramLong);

  public abstract IRenderer.RenderingState draw(AbsDisplayer<?> paramAbsDisplayer);

  public abstract void prepare();

  public abstract void quit();

  public abstract void removeAllDanmakus();

  public abstract void removeAllLiveDanmakus();

  public abstract void requestClear();

  public abstract void requestHide();

  public abstract void reset();

  public abstract void seek(long paramLong);

  public abstract void setParser(BaseDanmakuParser paramBaseDanmakuParser);

  public abstract void start();

  public static abstract interface TaskListener
  {
    public abstract void onDanmakuAdd(BaseDanmaku paramBaseDanmaku);

    public abstract void onDanmakuConfigChanged();

    public abstract void ready();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.IDrawTask
 * JD-Core Version:    0.6.2
 */