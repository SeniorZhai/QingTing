package master.flame.danmaku.ui.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import java.util.LinkedList;
import java.util.Locale;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.DrawHandler.Callback;
import master.flame.danmaku.controller.DrawHelper;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.controller.IDanmakuViewController;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState;

@SuppressLint({"NewApi"})
public class DanmakuTextureView extends TextureView
  implements IDanmakuView, IDanmakuViewController, TextureView.SurfaceTextureListener
{
  private static final int MAX_RECORD_SIZE = 50;
  private static final int ONE_SECOND = 1000;
  public static final String TAG = "DanmakuTextureView";
  private DrawHandler handler;
  private boolean isSurfaceCreated;
  private DrawHandler.Callback mCallback;
  private boolean mDanmakuVisible = true;
  private LinkedList<Long> mDrawTimes;
  protected int mDrawingThreadType = 0;
  private boolean mEnableDanmakuDrwaingCache = true;
  private HandlerThread mHandlerThread;
  private boolean mShowFps;

  public DanmakuTextureView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public DanmakuTextureView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public DanmakuTextureView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private float fps()
  {
    long l = System.currentTimeMillis();
    this.mDrawTimes.addLast(Long.valueOf(l));
    float f = (float)(l - ((Long)this.mDrawTimes.getFirst()).longValue());
    if (this.mDrawTimes.size() > 50)
      this.mDrawTimes.removeFirst();
    if (f > 0.0F)
      return this.mDrawTimes.size() * 1000 / f;
    return 0.0F;
  }

  @TargetApi(11)
  private void init()
  {
    setLayerType(2, null);
    setOpaque(false);
    setWillNotCacheDrawing(true);
    setDrawingCacheEnabled(false);
    setWillNotDraw(true);
    setSurfaceTextureListener(this);
    DrawHelper.useDrawColorToClearCanvas(true, true);
  }

  private void prepare()
  {
    if (this.handler == null)
      this.handler = new DrawHandler(getLooper(this.mDrawingThreadType), this, this.mDanmakuVisible);
  }

  private void stopDraw()
  {
    if (this.handler != null)
    {
      this.handler.quit();
      this.handler = null;
    }
    if (this.mHandlerThread != null);
    try
    {
      this.mHandlerThread.join();
      this.mHandlerThread.quit();
      this.mHandlerThread = null;
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }

  public void addDanmaku(BaseDanmaku paramBaseDanmaku)
  {
    if (this.handler != null)
      this.handler.addDanmaku(paramBaseDanmaku);
  }

  public void clear()
  {
    try
    {
      boolean bool = isViewReady();
      if (!bool);
      while (true)
      {
        return;
        Canvas localCanvas = lockCanvas();
        if (localCanvas != null)
        {
          DrawHelper.clearCanvas(localCanvas);
          unlockCanvasAndPost(localCanvas);
        }
      }
    }
    finally
    {
    }
  }

  public void clearDanmakusOnScreen()
  {
    if (this.handler != null)
      this.handler.clearDanmakusOnScreen();
  }

  public long drawDanmakus()
  {
    try
    {
      boolean bool = this.isSurfaceCreated;
      long l1;
      if (!bool)
        l1 = 0L;
      while (true)
      {
        return l1;
        l1 = System.currentTimeMillis();
        if (!isShown())
        {
          l1 = -1L;
        }
        else
        {
          Canvas localCanvas = lockCanvas();
          if (localCanvas != null)
          {
            if (this.handler != null)
            {
              IRenderer.RenderingState localRenderingState = this.handler.draw(localCanvas);
              if (this.mShowFps)
              {
                if (this.mDrawTimes == null)
                  this.mDrawTimes = new LinkedList();
                System.currentTimeMillis();
                DrawHelper.drawFPS(localCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[] { Float.valueOf(fps()), Long.valueOf(getCurrentTime() / 1000L), Long.valueOf(localRenderingState.cacheHitCount), Long.valueOf(localRenderingState.cacheMissCount) }));
              }
            }
            if (this.isSurfaceCreated)
              unlockCanvasAndPost(localCanvas);
          }
          long l2 = System.currentTimeMillis();
          l1 = l2 - l1;
        }
      }
    }
    finally
    {
    }
  }

  public void enableDanmakuDrawingCache(boolean paramBoolean)
  {
    this.mEnableDanmakuDrwaingCache = paramBoolean;
  }

  public long getCurrentTime()
  {
    if (this.handler != null)
      return this.handler.getCurrentTime();
    return 0L;
  }

  protected Looper getLooper(int paramInt)
  {
    if (this.mHandlerThread != null)
    {
      this.mHandlerThread.quit();
      this.mHandlerThread = null;
    }
    switch (paramInt)
    {
    default:
      paramInt = 0;
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      this.mHandlerThread = new HandlerThread("DFM Handler Thread #" + paramInt, paramInt);
      this.mHandlerThread.start();
      return this.mHandlerThread.getLooper();
      return Looper.getMainLooper();
      paramInt = -8;
      continue;
      paramInt = 19;
    }
  }

  public View getView()
  {
    return this;
  }

  public void hide()
  {
    this.mDanmakuVisible = false;
    if (this.handler == null)
      return;
    this.handler.hideDanmakus(false);
  }

  public long hideAndPauseDrawTask()
  {
    this.mDanmakuVisible = false;
    if (this.handler == null)
      return 0L;
    return this.handler.hideDanmakus(true);
  }

  public boolean isDanmakuDrawingCacheEnabled()
  {
    return this.mEnableDanmakuDrwaingCache;
  }

  public boolean isHardwareAccelerated()
  {
    return false;
  }

  public boolean isPaused()
  {
    if (this.handler != null)
      return this.handler.isStop();
    return false;
  }

  public boolean isPrepared()
  {
    return (this.handler != null) && (this.handler.isPrepared());
  }

  public boolean isShown()
  {
    if ((this.handler == null) || (!isViewReady()))
      return false;
    return this.handler.getVisibility();
  }

  public boolean isViewReady()
  {
    return this.isSurfaceCreated;
  }

  public void onSurfaceTextureAvailable(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    this.isSurfaceCreated = true;
  }

  public boolean onSurfaceTextureDestroyed(SurfaceTexture paramSurfaceTexture)
  {
    try
    {
      this.isSurfaceCreated = false;
      return true;
    }
    finally
    {
      paramSurfaceTexture = finally;
    }
    throw paramSurfaceTexture;
  }

  public void onSurfaceTextureSizeChanged(SurfaceTexture paramSurfaceTexture, int paramInt1, int paramInt2)
  {
    if (this.handler != null)
      this.handler.notifyDispSizeChanged(paramInt1, paramInt2);
  }

  public void onSurfaceTextureUpdated(SurfaceTexture paramSurfaceTexture)
  {
  }

  public void pause()
  {
    if (this.handler != null)
      this.handler.pause();
  }

  public void prepare(BaseDanmakuParser paramBaseDanmakuParser)
  {
    prepare();
    this.handler.setParser(paramBaseDanmakuParser);
    this.handler.setCallback(this.mCallback);
    this.handler.prepare();
  }

  public void release()
  {
    stop();
    if (this.mDrawTimes != null)
      this.mDrawTimes.clear();
  }

  public void removeAllDanmakus()
  {
    if (this.handler != null)
      this.handler.removeAllDanmakus();
  }

  public void removeAllLiveDanmakus()
  {
    if (this.handler != null)
      this.handler.removeAllLiveDanmakus();
  }

  public void restart()
  {
    stop();
    start();
  }

  public void resume()
  {
    if ((this.handler != null) && (this.mHandlerThread != null) && (this.handler.isPrepared()))
    {
      this.handler.resume();
      return;
    }
    restart();
  }

  public void seekTo(Long paramLong)
  {
    if (this.handler != null)
      this.handler.seekTo(paramLong);
  }

  public void setCallback(DrawHandler.Callback paramCallback)
  {
    this.mCallback = paramCallback;
    if (this.handler != null)
      this.handler.setCallback(paramCallback);
  }

  public void setDrawingThreadType(int paramInt)
  {
    this.mDrawingThreadType = paramInt;
  }

  public void show()
  {
    showAndResumeDrawTask(null);
  }

  public void showAndResumeDrawTask(Long paramLong)
  {
    this.mDanmakuVisible = true;
    if (this.handler == null)
      return;
    this.handler.showDanmakus(paramLong);
  }

  public void showFPS(boolean paramBoolean)
  {
    this.mShowFps = paramBoolean;
  }

  public void start()
  {
    start(0L);
  }

  public void start(long paramLong)
  {
    if (this.handler == null)
      prepare();
    while (true)
    {
      this.handler.obtainMessage(1, Long.valueOf(paramLong)).sendToTarget();
      return;
      this.handler.removeCallbacksAndMessages(null);
    }
  }

  public void stop()
  {
    stopDraw();
  }

  public void toggle()
  {
    if (this.isSurfaceCreated)
    {
      if (this.handler == null)
        start();
    }
    else
      return;
    if (this.handler.isStop())
    {
      resume();
      return;
    }
    pause();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.ui.widget.DanmakuTextureView
 * JD-Core Version:    0.6.2
 */