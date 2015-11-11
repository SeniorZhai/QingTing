package master.flame.danmaku.controller;

import android.view.View;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

public abstract interface IDanmakuView
{
  public static final int THREAD_TYPE_HIGH_PRIORITY = 2;
  public static final int THREAD_TYPE_LOW_PRIORITY = 3;
  public static final int THREAD_TYPE_MAIN_THREAD = 1;
  public static final int THREAD_TYPE_NORMAL_PRIORITY = 0;

  public abstract void addDanmaku(BaseDanmaku paramBaseDanmaku);

  public abstract void clearDanmakusOnScreen();

  public abstract void enableDanmakuDrawingCache(boolean paramBoolean);

  public abstract long getCurrentTime();

  public abstract int getHeight();

  public abstract View getView();

  public abstract int getWidth();

  public abstract void hide();

  public abstract long hideAndPauseDrawTask();

  public abstract boolean isDanmakuDrawingCacheEnabled();

  public abstract boolean isHardwareAccelerated();

  public abstract boolean isPaused();

  public abstract boolean isPrepared();

  public abstract boolean isShown();

  public abstract void pause();

  public abstract void prepare(BaseDanmakuParser paramBaseDanmakuParser);

  public abstract void release();

  public abstract void removeAllDanmakus();

  public abstract void removeAllLiveDanmakus();

  public abstract void resume();

  public abstract void seekTo(Long paramLong);

  public abstract void setCallback(DrawHandler.Callback paramCallback);

  public abstract void setDrawingThreadType(int paramInt);

  public abstract void setVisibility(int paramInt);

  public abstract void show();

  public abstract void showAndResumeDrawTask(Long paramLong);

  public abstract void showFPS(boolean paramBoolean);

  public abstract void start();

  public abstract void start(long paramLong);

  public abstract void stop();

  public abstract void toggle();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.IDanmakuView
 * JD-Core Version:    0.6.2
 */