package master.flame.danmaku.controller;

import android.content.Context;

public abstract interface IDanmakuViewController
{
  public abstract void clear();

  public abstract long drawDanmakus();

  public abstract Context getContext();

  public abstract int getHeight();

  public abstract int getWidth();

  public abstract boolean isDanmakuDrawingCacheEnabled();

  public abstract boolean isHardwareAccelerated();

  public abstract boolean isViewReady();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.IDanmakuViewController
 * JD-Core Version:    0.6.2
 */