package master.flame.danmaku.controller;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.DrawingCache;
import master.flame.danmaku.danmaku.model.android.DrawingCachePoolManager;
import master.flame.danmaku.danmaku.model.objectpool.Pool;
import master.flame.danmaku.danmaku.model.objectpool.Pools;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState;
import tv.cjump.jni.NativeBitmapFactory;

public class CacheManagingDrawTask extends DrawTask
{
  private static final int MAX_CACHE_SCREEN_SIZE = 3;
  private CacheManager mCacheManager;
  private DanmakuTimer mCacheTimer;
  private final Object mDrawingNotify = new Object();
  private int mMaxCacheSize = 2;

  static
  {
    if (!CacheManagingDrawTask.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public CacheManagingDrawTask(DanmakuTimer paramDanmakuTimer, Context paramContext, AbsDisplayer<?> paramAbsDisplayer, IDrawTask.TaskListener paramTaskListener, int paramInt)
  {
    super(paramDanmakuTimer, paramContext, paramAbsDisplayer, paramTaskListener);
    NativeBitmapFactory.loadLibs();
    this.mMaxCacheSize = paramInt;
    if (NativeBitmapFactory.isInNativeAlloc())
      this.mMaxCacheSize = (paramInt * 3);
    this.mCacheManager = new CacheManager(paramInt, 3);
  }

  public void addDanmaku(BaseDanmaku paramBaseDanmaku)
  {
    if (this.mCacheManager == null)
      return;
    this.mCacheManager.addDanmaku(paramBaseDanmaku);
  }

  public IRenderer.RenderingState draw(AbsDisplayer<?> paramAbsDisplayer)
  {
    synchronized (this.danmakuList)
    {
      paramAbsDisplayer = super.draw(paramAbsDisplayer);
    }
    synchronized (this.mDrawingNotify)
    {
      this.mDrawingNotify.notify();
      if ((paramAbsDisplayer != null) && (this.mCacheManager != null) && (paramAbsDisplayer.incrementCount < -20))
      {
        this.mCacheManager.requestClearTimeout();
        this.mCacheManager.requestBuild(-DanmakuFactory.MAX_DANMAKU_DURATION);
      }
      return paramAbsDisplayer;
      paramAbsDisplayer = finally;
      throw paramAbsDisplayer;
    }
  }

  protected void initTimer(DanmakuTimer paramDanmakuTimer)
  {
    this.mTimer = paramDanmakuTimer;
    this.mCacheTimer = new DanmakuTimer();
    this.mCacheTimer.update(paramDanmakuTimer.currMillisecond);
  }

  public boolean onDanmakuConfigChanged(DanmakuGlobalConfig paramDanmakuGlobalConfig, DanmakuGlobalConfig.DanmakuConfigTag paramDanmakuConfigTag, Object[] paramArrayOfObject)
  {
    if (super.handleOnDanmakuConfigChanged(paramDanmakuGlobalConfig, paramDanmakuConfigTag, paramArrayOfObject));
    while (true)
    {
      if ((this.mTaskListener != null) && (this.mCacheManager != null))
        this.mCacheManager.post(new Runnable()
        {
          public void run()
          {
            CacheManagingDrawTask.this.mTaskListener.onDanmakuConfigChanged();
          }
        });
      return true;
      if (DanmakuGlobalConfig.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(paramDanmakuConfigTag))
      {
        this.mDisp.resetSlopPixel(DanmakuGlobalConfig.DEFAULT.scaleTextSize);
        requestClear();
      }
      else if (paramDanmakuConfigTag.isVisibilityRelatedTag())
      {
        if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0) && (paramArrayOfObject[0] != null) && ((!(paramArrayOfObject[0] instanceof Boolean)) || (((Boolean)paramArrayOfObject[0]).booleanValue())) && (this.mCacheManager != null))
          this.mCacheManager.requestBuild(0L);
        requestClear();
      }
      else if ((DanmakuGlobalConfig.DanmakuConfigTag.TRANSPARENCY.equals(paramDanmakuConfigTag)) || (DanmakuGlobalConfig.DanmakuConfigTag.SCALE_TEXTSIZE.equals(paramDanmakuConfigTag)) || (DanmakuGlobalConfig.DanmakuConfigTag.DANMAKU_STYLE.equals(paramDanmakuConfigTag)))
      {
        if (DanmakuGlobalConfig.DanmakuConfigTag.SCALE_TEXTSIZE.equals(paramDanmakuConfigTag))
          this.mDisp.resetSlopPixel(DanmakuGlobalConfig.DEFAULT.scaleTextSize);
        if (this.mCacheManager != null)
        {
          this.mCacheManager.requestClearAll();
          this.mCacheManager.requestBuild(-DanmakuFactory.MAX_DANMAKU_DURATION);
        }
      }
      else if (this.mCacheManager != null)
      {
        this.mCacheManager.requestClearUnused();
        this.mCacheManager.requestBuild(0L);
      }
    }
  }

  public void prepare()
  {
    assert (this.mParser != null);
    loadDanmakus(this.mParser);
    this.mCacheManager.begin();
  }

  public void quit()
  {
    super.quit();
    reset();
    if (this.mCacheManager != null)
    {
      this.mCacheManager.end();
      this.mCacheManager = null;
    }
    NativeBitmapFactory.releaseLibs();
  }

  public void reset()
  {
    if (this.mRenderer != null)
      this.mRenderer.clear();
  }

  public void seek(long paramLong)
  {
    super.seek(paramLong);
    this.mCacheManager.seek(paramLong);
  }

  public void start()
  {
    super.start();
    NativeBitmapFactory.loadLibs();
    if (this.mCacheManager == null)
    {
      this.mCacheManager = new CacheManager(this.mMaxCacheSize, 3);
      this.mCacheManager.begin();
      return;
    }
    this.mCacheManager.resume();
  }

  public class CacheManager
  {
    public static final byte RESULT_FAILED = 1;
    public static final byte RESULT_FAILED_OVERSIZE = 2;
    public static final byte RESULT_SUCCESS = 0;
    private static final String TAG = "CacheManager";
    int danmakuAddedCount = 0;
    Pool<DrawingCache> mCachePool = Pools.finitePool(this.mCachePoolManager, 800);
    DrawingCachePoolManager mCachePoolManager = new DrawingCachePoolManager();
    Danmakus mCaches = new Danmakus(4);
    private boolean mEndFlag = false;
    private CacheHandler mHandler;
    private int mMaxSize;
    private int mRealSize = 0;
    private int mScreenSize = 3;
    public HandlerThread mThread;

    public CacheManager(int paramInt1, int arg3)
    {
      this.mMaxSize = paramInt1;
      int i;
      this.mScreenSize = i;
    }

    private void clearCachePool()
    {
      while (true)
      {
        DrawingCache localDrawingCache = (DrawingCache)this.mCachePool.acquire();
        if (localDrawingCache == null)
          break;
        localDrawingCache.destroy();
      }
    }

    private void clearTimeOutCaches()
    {
      clearTimeOutCaches(CacheManagingDrawTask.this.mTimer.currMillisecond);
    }

    private void clearTimeOutCaches(long paramLong)
    {
      IDanmakuIterator localIDanmakuIterator = this.mCaches.iterator();
      while ((localIDanmakuIterator.hasNext()) && (!this.mEndFlag))
      {
        BaseDanmaku localBaseDanmaku = localIDanmakuIterator.next();
        if (localBaseDanmaku.isTimeOut())
          synchronized (CacheManagingDrawTask.this.mDrawingNotify)
          {
            try
            {
              CacheManagingDrawTask.this.mDrawingNotify.wait(30L);
              entryRemoved(false, localBaseDanmaku, null);
              localIDanmakuIterator.remove();
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
              return;
            }
          }
      }
    }

    private void evictAll()
    {
      if (this.mCaches != null)
      {
        IDanmakuIterator localIDanmakuIterator = this.mCaches.iterator();
        while (localIDanmakuIterator.hasNext())
          entryRemoved(true, localIDanmakuIterator.next(), null);
        this.mCaches.clear();
      }
      this.mRealSize = 0;
    }

    private void evictAllNotInScreen()
    {
      evictAllNotInScreen(false);
    }

    private void evictAllNotInScreen(boolean paramBoolean)
    {
      if (this.mCaches != null)
      {
        IDanmakuIterator localIDanmakuIterator = this.mCaches.iterator();
        while (localIDanmakuIterator.hasNext())
        {
          BaseDanmaku localBaseDanmaku = localIDanmakuIterator.next();
          IDrawingCache localIDrawingCache = localBaseDanmaku.cache;
          if ((localIDrawingCache != null) && (localIDrawingCache.hasReferences()));
          for (int i = 1; ; i = 0)
          {
            if ((!paramBoolean) || (i == 0))
              break label119;
            if (localIDrawingCache.get() != null)
            {
              this.mRealSize -= localIDrawingCache.size();
              localIDrawingCache.destroy();
            }
            entryRemoved(true, localBaseDanmaku, null);
            localIDanmakuIterator.remove();
            break;
          }
          label119: if ((!localBaseDanmaku.hasDrawingCache()) || (localBaseDanmaku.isOutside()))
          {
            entryRemoved(true, localBaseDanmaku, null);
            localIDanmakuIterator.remove();
          }
        }
      }
      this.mRealSize = 0;
    }

    private BaseDanmaku findReuseableCache(BaseDanmaku paramBaseDanmaku, boolean paramBoolean)
    {
      IDanmakuIterator localIDanmakuIterator = this.mCaches.iterator();
      int i = 0;
      if (!paramBoolean)
        i = CacheManagingDrawTask.this.mDisp.getSlopPixel() * 2;
      BaseDanmaku localBaseDanmaku;
      label151: float f1;
      float f2;
      do
      {
        do
        {
          while (localIDanmakuIterator.hasNext())
          {
            localBaseDanmaku = localIDanmakuIterator.next();
            if (localBaseDanmaku.hasDrawingCache())
            {
              if ((localBaseDanmaku.paintWidth == paramBaseDanmaku.paintWidth) && (localBaseDanmaku.paintHeight == paramBaseDanmaku.paintHeight) && (localBaseDanmaku.underlineColor == paramBaseDanmaku.underlineColor) && (localBaseDanmaku.borderColor == paramBaseDanmaku.borderColor) && (localBaseDanmaku.textColor == paramBaseDanmaku.textColor) && (localBaseDanmaku.text.equals(paramBaseDanmaku.text)))
                return localBaseDanmaku;
              if (!paramBoolean)
                if (localBaseDanmaku.isTimeOut())
                  break label151;
            }
          }
          return null;
        }
        while (localBaseDanmaku.cache.hasReferences());
        f1 = localBaseDanmaku.cache.width() - paramBaseDanmaku.paintWidth;
        f2 = localBaseDanmaku.cache.height() - paramBaseDanmaku.paintHeight;
      }
      while ((f1 < 0.0F) || (f1 > i) || (f2 < 0.0F) || (f2 > i));
      return localBaseDanmaku;
    }

    private boolean push(BaseDanmaku paramBaseDanmaku, int paramInt)
    {
      boolean bool2 = false;
      while ((this.mRealSize + paramInt > this.mMaxSize) && (this.mCaches.size() > 0))
      {
        BaseDanmaku localBaseDanmaku = this.mCaches.first();
        bool1 = bool2;
        if (!localBaseDanmaku.isTimeOut())
          break label86;
        entryRemoved(false, localBaseDanmaku, paramBaseDanmaku);
        this.mCaches.removeItem(localBaseDanmaku);
      }
      this.mCaches.addItem(paramBaseDanmaku);
      this.mRealSize += paramInt;
      boolean bool1 = true;
      label86: return bool1;
    }

    public void addDanmaku(BaseDanmaku paramBaseDanmaku)
    {
      if (this.mHandler != null)
        this.mHandler.obtainMessage(2, paramBaseDanmaku).sendToTarget();
    }

    public void begin()
    {
      if (this.mThread == null)
      {
        this.mThread = new HandlerThread("DFM Cache-Building Thread");
        this.mThread.start();
      }
      if (this.mHandler == null)
        this.mHandler = new CacheHandler(this.mThread.getLooper());
      this.mHandler.begin();
    }

    public void end()
    {
      this.mEndFlag = true;
      synchronized (CacheManagingDrawTask.this.mDrawingNotify)
      {
        CacheManagingDrawTask.this.mDrawingNotify.notifyAll();
        if (this.mHandler != null)
        {
          this.mHandler.pause();
          this.mHandler = null;
        }
        if (this.mThread == null);
      }
      try
      {
        this.mThread.join();
        this.mThread.quit();
        this.mThread = null;
        return;
        localObject2 = finally;
        throw localObject2;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          localInterruptedException.printStackTrace();
      }
    }

    protected void entryRemoved(boolean paramBoolean, BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
    {
      if (paramBaseDanmaku1.cache != null)
      {
        if (paramBaseDanmaku1.cache.hasReferences())
        {
          paramBaseDanmaku1.cache.decreaseReference();
          paramBaseDanmaku1.cache = null;
        }
      }
      else
        return;
      this.mRealSize -= sizeOf(paramBaseDanmaku1);
      paramBaseDanmaku1.cache.destroy();
      this.mCachePool.release((DrawingCache)paramBaseDanmaku1.cache);
      paramBaseDanmaku1.cache = null;
    }

    public long getFirstCacheTime()
    {
      BaseDanmaku localBaseDanmaku;
      if ((this.mCaches != null) && (this.mCaches.size() > 0))
      {
        localBaseDanmaku = this.mCaches.first();
        if (localBaseDanmaku != null);
      }
      else
      {
        return 0L;
      }
      return localBaseDanmaku.time;
    }

    public float getPoolPercent()
    {
      if (this.mMaxSize == 0)
        return 0.0F;
      return this.mRealSize / this.mMaxSize;
    }

    public boolean isPoolFull()
    {
      return this.mRealSize + 5120 >= this.mMaxSize;
    }

    public void post(Runnable paramRunnable)
    {
      if (this.mHandler == null)
        return;
      this.mHandler.post(paramRunnable);
    }

    public void requestBuild(long paramLong)
    {
      if (this.mHandler != null)
        this.mHandler.requestBuildCacheAndDraw(paramLong);
    }

    public void requestClearAll()
    {
      if (this.mHandler == null)
        return;
      this.mHandler.removeMessages(3);
      this.mHandler.requestCancelCaching();
      this.mHandler.removeMessages(7);
      this.mHandler.sendEmptyMessage(7);
    }

    public void requestClearTimeout()
    {
      if (this.mHandler == null)
        return;
      this.mHandler.removeMessages(4);
      this.mHandler.sendEmptyMessage(4);
    }

    public void requestClearUnused()
    {
      if (this.mHandler == null)
        return;
      this.mHandler.removeMessages(9);
      this.mHandler.sendEmptyMessage(9);
    }

    public void resume()
    {
      if (this.mHandler != null)
      {
        this.mHandler.resume();
        return;
      }
      begin();
    }

    public void seek(long paramLong)
    {
      if (this.mHandler == null)
        return;
      this.mHandler.requestCancelCaching();
      this.mHandler.removeMessages(3);
      this.mHandler.obtainMessage(5, Long.valueOf(paramLong)).sendToTarget();
    }

    protected int sizeOf(BaseDanmaku paramBaseDanmaku)
    {
      if ((paramBaseDanmaku.cache != null) && (!paramBaseDanmaku.cache.hasReferences()))
        return paramBaseDanmaku.cache.size();
      return 0;
    }

    public class CacheHandler extends Handler
    {
      public static final int ADD_DANMAKKU = 2;
      public static final int BUILD_CACHES = 3;
      public static final int CLEAR_ALL_CACHES = 7;
      public static final int CLEAR_OUTSIDE_CACHES = 8;
      public static final int CLEAR_OUTSIDE_CACHES_AND_RESET = 9;
      public static final int CLEAR_TIMEOUT_CACHES = 4;
      public static final int DISPATCH_ACTIONS = 16;
      private static final int PREPARE = 1;
      public static final int QUIT = 6;
      public static final int SEEK = 5;
      private boolean mCancelFlag;
      private boolean mPause;
      private boolean mSeekedFlag;

      public CacheHandler(Looper arg2)
      {
        super();
      }

      // ERROR //
      private byte buildCache(BaseDanmaku paramBaseDanmaku)
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: aconst_null
        //   4: astore_3
        //   5: aload_1
        //   6: invokevirtual 57	master/flame/danmaku/danmaku/model/BaseDanmaku:isMeasured	()Z
        //   9: ifne +17 -> 26
        //   12: aload_1
        //   13: aload_0
        //   14: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   17: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   20: getfield 65	master/flame/danmaku/controller/CacheManagingDrawTask:mDisp	Lmaster/flame/danmaku/danmaku/model/AbsDisplayer;
        //   23: invokevirtual 69	master/flame/danmaku/danmaku/model/BaseDanmaku:measure	(Lmaster/flame/danmaku/danmaku/model/IDisplayer;)V
        //   26: aload 5
        //   28: astore 4
        //   30: aload_0
        //   31: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   34: aload_1
        //   35: iconst_1
        //   36: invokestatic 73	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$900	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Z)Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;
        //   39: astore 6
        //   41: aload 6
        //   43: ifnull +16 -> 59
        //   46: aload 5
        //   48: astore 4
        //   50: aload 6
        //   52: getfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   55: checkcast 79	master/flame/danmaku/danmaku/model/android/DrawingCache
        //   58: astore_3
        //   59: aload_3
        //   60: ifnull +55 -> 115
        //   63: aload_3
        //   64: astore 4
        //   66: aload_3
        //   67: astore 6
        //   69: aload_3
        //   70: invokevirtual 83	master/flame/danmaku/danmaku/model/android/DrawingCache:increaseReference	()V
        //   73: aload_3
        //   74: astore 4
        //   76: aload_3
        //   77: astore 6
        //   79: aload_1
        //   80: aload_3
        //   81: putfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   84: aload_3
        //   85: astore 4
        //   87: aload_3
        //   88: astore 6
        //   90: aload_0
        //   91: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   94: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   97: invokestatic 87	master/flame/danmaku/controller/CacheManagingDrawTask:access$1000	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask;)Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   100: aload_1
        //   101: aload_0
        //   102: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   105: aload_1
        //   106: invokevirtual 91	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:sizeOf	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;)I
        //   109: invokestatic 95	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$1100	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;I)Z
        //   112: pop
        //   113: iconst_0
        //   114: ireturn
        //   115: aload_3
        //   116: astore 4
        //   118: aload_3
        //   119: astore 6
        //   121: aload_0
        //   122: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   125: aload_1
        //   126: iconst_0
        //   127: invokestatic 73	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$900	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Z)Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;
        //   130: astore 7
        //   132: aload_3
        //   133: astore 5
        //   135: aload 7
        //   137: ifnull +19 -> 156
        //   140: aload_3
        //   141: astore 4
        //   143: aload_3
        //   144: astore 6
        //   146: aload 7
        //   148: getfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   151: checkcast 79	master/flame/danmaku/danmaku/model/android/DrawingCache
        //   154: astore 5
        //   156: aload 5
        //   158: ifnull +77 -> 235
        //   161: aload 5
        //   163: astore 4
        //   165: aload 5
        //   167: astore 6
        //   169: aload 7
        //   171: aconst_null
        //   172: putfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   175: aload 5
        //   177: astore 4
        //   179: aload 5
        //   181: astore 6
        //   183: aload_1
        //   184: aload_0
        //   185: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   188: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   191: getfield 65	master/flame/danmaku/controller/CacheManagingDrawTask:mDisp	Lmaster/flame/danmaku/danmaku/model/AbsDisplayer;
        //   194: aload 5
        //   196: invokestatic 101	master/flame/danmaku/danmaku/util/DanmakuUtils:buildDanmakuDrawingCache	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Lmaster/flame/danmaku/danmaku/model/IDisplayer;Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;)Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;
        //   199: astore_3
        //   200: aload_3
        //   201: astore 4
        //   203: aload_3
        //   204: astore 5
        //   206: aload_1
        //   207: aload_3
        //   208: putfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   211: aload_3
        //   212: astore 4
        //   214: aload_3
        //   215: astore 5
        //   217: aload_0
        //   218: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   221: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   224: invokestatic 87	master/flame/danmaku/controller/CacheManagingDrawTask:access$1000	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask;)Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   227: aload_1
        //   228: iconst_0
        //   229: invokestatic 95	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$1100	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;I)Z
        //   232: pop
        //   233: iconst_0
        //   234: ireturn
        //   235: aload 5
        //   237: astore 4
        //   239: aload 5
        //   241: astore 6
        //   243: aload_1
        //   244: getfield 105	master/flame/danmaku/danmaku/model/BaseDanmaku:paintWidth	F
        //   247: f2i
        //   248: aload_1
        //   249: getfield 108	master/flame/danmaku/danmaku/model/BaseDanmaku:paintHeight	F
        //   252: f2i
        //   253: invokestatic 112	master/flame/danmaku/danmaku/util/DanmakuUtils:getCacheSize	(II)I
        //   256: aload_0
        //   257: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   260: invokestatic 116	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$1200	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;)I
        //   263: iadd
        //   264: aload_0
        //   265: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   268: invokestatic 119	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$1300	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;)I
        //   271: if_icmple +5 -> 276
        //   274: iconst_1
        //   275: ireturn
        //   276: aload 5
        //   278: astore 4
        //   280: aload 5
        //   282: astore 6
        //   284: aload_0
        //   285: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   288: getfield 123	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:mCachePool	Lmaster/flame/danmaku/danmaku/model/objectpool/Pool;
        //   291: invokeinterface 129 1 0
        //   296: checkcast 79	master/flame/danmaku/danmaku/model/android/DrawingCache
        //   299: astore_3
        //   300: aload_3
        //   301: astore 4
        //   303: aload_3
        //   304: astore 5
        //   306: aload_0
        //   307: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   310: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   313: getfield 133	master/flame/danmaku/controller/CacheManagingDrawTask:danmakuList	Lmaster/flame/danmaku/danmaku/model/IDanmakus;
        //   316: astore 6
        //   318: aload_3
        //   319: astore 4
        //   321: aload_3
        //   322: astore 5
        //   324: aload 6
        //   326: monitorenter
        //   327: aload_1
        //   328: aload_0
        //   329: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   332: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   335: getfield 65	master/flame/danmaku/controller/CacheManagingDrawTask:mDisp	Lmaster/flame/danmaku/danmaku/model/AbsDisplayer;
        //   338: aload_3
        //   339: invokestatic 101	master/flame/danmaku/danmaku/util/DanmakuUtils:buildDanmakuDrawingCache	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Lmaster/flame/danmaku/danmaku/model/IDisplayer;Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;)Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;
        //   342: astore 5
        //   344: aload_1
        //   345: aload 5
        //   347: putfield 77	master/flame/danmaku/danmaku/model/BaseDanmaku:cache	Lmaster/flame/danmaku/danmaku/model/IDrawingCache;
        //   350: aload_0
        //   351: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   354: getfield 61	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:this$0	Lmaster/flame/danmaku/controller/CacheManagingDrawTask;
        //   357: invokestatic 87	master/flame/danmaku/controller/CacheManagingDrawTask:access$1000	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask;)Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   360: aload_1
        //   361: aload_0
        //   362: getfield 41	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:this$1	Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;
        //   365: aload_1
        //   366: invokevirtual 91	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:sizeOf	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;)I
        //   369: invokestatic 95	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager:access$1100	(Lmaster/flame/danmaku/controller/CacheManagingDrawTask$CacheManager;Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;I)Z
        //   372: istore 8
        //   374: iload 8
        //   376: ifne +96 -> 472
        //   379: aload_0
        //   380: aload_1
        //   381: aload 5
        //   383: invokespecial 137	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:releaseDanmakuCache	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;)V
        //   386: goto +86 -> 472
        //   389: aload 6
        //   391: monitorexit
        //   392: iload_2
        //   393: ireturn
        //   394: astore 4
        //   396: aload 5
        //   398: astore_3
        //   399: aload 6
        //   401: monitorexit
        //   402: aload 4
        //   404: athrow
        //   405: astore 4
        //   407: aload_0
        //   408: aload_1
        //   409: aload_3
        //   410: invokespecial 137	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:releaseDanmakuCache	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;)V
        //   413: iconst_1
        //   414: ireturn
        //   415: iconst_1
        //   416: istore_2
        //   417: goto -28 -> 389
        //   420: astore_3
        //   421: aload_0
        //   422: aload_1
        //   423: aload 4
        //   425: invokespecial 137	master/flame/danmaku/controller/CacheManagingDrawTask$CacheManager$CacheHandler:releaseDanmakuCache	(Lmaster/flame/danmaku/danmaku/model/BaseDanmaku;Lmaster/flame/danmaku/danmaku/model/android/DrawingCache;)V
        //   428: iconst_1
        //   429: ireturn
        //   430: astore_3
        //   431: goto -10 -> 421
        //   434: astore 4
        //   436: aload_3
        //   437: astore 4
        //   439: goto -18 -> 421
        //   442: astore_3
        //   443: aconst_null
        //   444: astore_3
        //   445: goto -38 -> 407
        //   448: astore_3
        //   449: aload 6
        //   451: astore_3
        //   452: goto -45 -> 407
        //   455: astore_3
        //   456: aload 5
        //   458: astore_3
        //   459: goto -52 -> 407
        //   462: astore 4
        //   464: goto -65 -> 399
        //   467: astore 4
        //   469: goto -70 -> 399
        //   472: iload 8
        //   474: ifeq -59 -> 415
        //   477: iconst_0
        //   478: istore_2
        //   479: goto -90 -> 389
        //
        // Exception table:
        //   from	to	target	type
        //   344	374	394	finally
        //   379	386	394	finally
        //   389	392	394	finally
        //   402	405	405	java/lang/OutOfMemoryError
        //   30	41	420	java/lang/Exception
        //   50	59	420	java/lang/Exception
        //   69	73	420	java/lang/Exception
        //   79	84	420	java/lang/Exception
        //   90	113	420	java/lang/Exception
        //   121	132	420	java/lang/Exception
        //   146	156	420	java/lang/Exception
        //   169	175	420	java/lang/Exception
        //   183	200	420	java/lang/Exception
        //   243	274	420	java/lang/Exception
        //   284	300	420	java/lang/Exception
        //   206	211	430	java/lang/Exception
        //   217	233	430	java/lang/Exception
        //   306	318	430	java/lang/Exception
        //   324	327	430	java/lang/Exception
        //   402	405	434	java/lang/Exception
        //   30	41	442	java/lang/OutOfMemoryError
        //   50	59	442	java/lang/OutOfMemoryError
        //   69	73	448	java/lang/OutOfMemoryError
        //   79	84	448	java/lang/OutOfMemoryError
        //   90	113	448	java/lang/OutOfMemoryError
        //   121	132	448	java/lang/OutOfMemoryError
        //   146	156	448	java/lang/OutOfMemoryError
        //   169	175	448	java/lang/OutOfMemoryError
        //   183	200	448	java/lang/OutOfMemoryError
        //   243	274	448	java/lang/OutOfMemoryError
        //   284	300	448	java/lang/OutOfMemoryError
        //   206	211	455	java/lang/OutOfMemoryError
        //   217	233	455	java/lang/OutOfMemoryError
        //   306	318	455	java/lang/OutOfMemoryError
        //   324	327	455	java/lang/OutOfMemoryError
        //   327	344	462	finally
        //   399	402	467	finally
      }

      private long dispatchAction()
      {
        float f = CacheManagingDrawTask.CacheManager.this.getPoolPercent();
        BaseDanmaku localBaseDanmaku = CacheManagingDrawTask.CacheManager.this.mCaches.first();
        long l1;
        long l2;
        if (localBaseDanmaku != null)
        {
          l1 = localBaseDanmaku.time - CacheManagingDrawTask.this.mTimer.currMillisecond;
          l2 = DanmakuFactory.MAX_DANMAKU_DURATION * 2L;
          if ((f >= 0.6F) || (l1 <= DanmakuFactory.MAX_DANMAKU_DURATION))
            break label111;
          CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
          removeMessages(3);
          sendEmptyMessage(3);
        }
        label111: 
        do
        {
          do
          {
            return 0L;
            l1 = 0L;
            break;
            if ((f > 0.4F) && (l1 < -l2))
            {
              removeMessages(4);
              sendEmptyMessage(4);
              return 0L;
            }
          }
          while (f >= 0.9F);
          l1 = CacheManagingDrawTask.this.mCacheTimer.currMillisecond - CacheManagingDrawTask.this.mTimer.currMillisecond;
          if (l1 < 0L)
          {
            CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
            sendEmptyMessage(8);
            sendEmptyMessage(3);
            return 0L;
          }
        }
        while (l1 > l2);
        removeMessages(3);
        sendEmptyMessage(3);
        return 0L;
      }

      private long prepareCaches(boolean paramBoolean)
      {
        long l4 = CacheManagingDrawTask.this.mCacheTimer.currMillisecond;
        long l2 = l4 + DanmakuFactory.MAX_DANMAKU_DURATION * CacheManagingDrawTask.CacheManager.this.mScreenSize * 3L;
        if (l2 < CacheManagingDrawTask.this.mTimer.currMillisecond)
          return 0L;
        long l3 = System.currentTimeMillis();
        Object localObject1 = CacheManagingDrawTask.this.danmakuList.subnew(l4, l2);
        if ((localObject1 == null) || (((IDanmakus)localObject1).isEmpty()))
        {
          CacheManagingDrawTask.this.mCacheTimer.update(l2);
          return 0L;
        }
        ??? = ((IDanmakus)localObject1).first();
        BaseDanmaku localBaseDanmaku = ((IDanmakus)localObject1).last();
        long l1 = Math.min(100L, (((BaseDanmaku)???).time - CacheManagingDrawTask.this.mTimer.currMillisecond) * 10L / DanmakuFactory.MAX_DANMAKU_DURATION + 30L);
        if (paramBoolean)
          l1 = 0L;
        while (true)
        {
          IDanmakuIterator localIDanmakuIterator = ((IDanmakus)localObject1).iterator();
          int k = 0;
          int n = ((IDanmakus)localObject1).size();
          int j = 0;
          int i = 0;
          localObject1 = null;
          while (true)
          {
            ??? = localObject1;
            if (!this.mPause)
            {
              ??? = localObject1;
              if (!this.mCancelFlag)
              {
                if (localIDanmakuIterator.hasNext())
                  break label263;
                ??? = localObject1;
              }
            }
            label230: 
            do
            {
              l1 = System.currentTimeMillis();
              if (??? == null)
                break;
              CacheManagingDrawTask.this.mCacheTimer.update(((BaseDanmaku)???).time);
              return l1 - l3;
              localObject1 = localIDanmakuIterator.next();
              i += 1;
              ??? = localObject1;
            }
            while (localBaseDanmaku.time < CacheManagingDrawTask.this.mTimer.currMillisecond);
            label257: label263: if (!((BaseDanmaku)localObject1).hasDrawingCache())
              if (!paramBoolean)
              {
                if (!((BaseDanmaku)localObject1).isTimeOut())
                  if (!((BaseDanmaku)localObject1).isOutside());
              }
              else if (!DanmakuFilters.getDefault().filter((BaseDanmaku)localObject1, k, n, null, true))
              {
                int m;
                if (((BaseDanmaku)localObject1).getType() == 1)
                {
                  m = (int)((((BaseDanmaku)localObject1).time - l4) / DanmakuFactory.MAX_DANMAKU_DURATION);
                  if (j == m)
                    k += 1;
                }
                while (true)
                {
                  while (true)
                  {
                    if (!paramBoolean);
                    try
                    {
                      synchronized (CacheManagingDrawTask.this.mDrawingNotify)
                      {
                        CacheManagingDrawTask.this.mDrawingNotify.wait(l1);
                        ??? = localObject1;
                        if (buildCache((BaseDanmaku)localObject1) == 1)
                          break label230;
                        if (!paramBoolean)
                        {
                          ??? = localObject1;
                          if (System.currentTimeMillis() - l3 >= 3800L * CacheManagingDrawTask.CacheManager.this.mScreenSize)
                            break label230;
                        }
                        break;
                        k = 0;
                        j = m;
                      }
                    }
                    catch (InterruptedException localInterruptedException)
                    {
                      localInterruptedException.printStackTrace();
                      Object localObject3 = localObject1;
                    }
                  }
                  break label230;
                  CacheManagingDrawTask.this.mCacheTimer.update(l2);
                  break label257;
                }
              }
          }
        }
      }

      private void releaseDanmakuCache(BaseDanmaku paramBaseDanmaku, DrawingCache paramDrawingCache)
      {
        if (paramDrawingCache == null)
          paramDrawingCache = (DrawingCache)paramBaseDanmaku.cache;
        while (true)
        {
          paramBaseDanmaku.cache = null;
          if (paramDrawingCache == null)
            return;
          paramDrawingCache.destroy();
          CacheManagingDrawTask.CacheManager.this.mCachePool.release(paramDrawingCache);
          return;
        }
      }

      public void begin()
      {
        sendEmptyMessage(1);
        sendEmptyMessageDelayed(4, DanmakuFactory.MAX_DANMAKU_DURATION);
      }

      public void handleMessage(Message paramMessage)
      {
        int i = 0;
        switch (paramMessage.what)
        {
        case 10:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        default:
        case 1:
        case 16:
        case 3:
        case 2:
        case 4:
        case 5:
          do
          {
            return;
            CacheManagingDrawTask.CacheManager.this.evictAllNotInScreen();
            while (i < 300)
            {
              CacheManagingDrawTask.CacheManager.this.mCachePool.release(new DrawingCache());
              i += 1;
            }
            long l2 = dispatchAction();
            long l1 = l2;
            if (l2 <= 0L)
              l1 = DanmakuFactory.MAX_DANMAKU_DURATION / 2L;
            sendEmptyMessageDelayed(16, l1);
            return;
            removeMessages(3);
            if (((CacheManagingDrawTask.this.mTaskListener != null) && (!CacheManagingDrawTask.this.mReadyState)) || (this.mSeekedFlag));
            for (boolean bool = true; ; bool = false)
            {
              prepareCaches(bool);
              if (bool)
                this.mSeekedFlag = false;
              if ((CacheManagingDrawTask.this.mTaskListener == null) || (CacheManagingDrawTask.this.mReadyState))
                break;
              CacheManagingDrawTask.this.mTaskListener.ready();
              CacheManagingDrawTask.this.mReadyState = true;
              return;
            }
            synchronized (CacheManagingDrawTask.this.danmakuList)
            {
              paramMessage = (BaseDanmaku)paramMessage.obj;
              if (paramMessage.isTimeOut())
                return;
            }
            if (!paramMessage.hasDrawingCache())
              buildCache(paramMessage);
            if (paramMessage.isLive)
              CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond + DanmakuFactory.MAX_DANMAKU_DURATION * CacheManagingDrawTask.CacheManager.this.mScreenSize);
            CacheManagingDrawTask.this.addDanmaku(paramMessage);
            return;
            CacheManagingDrawTask.CacheManager.this.clearTimeOutCaches();
            return;
            paramMessage = (Long)paramMessage.obj;
          }
          while (paramMessage == null);
          CacheManagingDrawTask.this.mCacheTimer.update(paramMessage.longValue());
          this.mSeekedFlag = true;
          CacheManagingDrawTask.CacheManager.this.evictAllNotInScreen();
          resume();
          return;
        case 6:
          removeCallbacksAndMessages(null);
          this.mPause = true;
          CacheManagingDrawTask.CacheManager.this.evictAll();
          CacheManagingDrawTask.CacheManager.this.clearCachePool();
          getLooper().quit();
          return;
        case 7:
          CacheManagingDrawTask.CacheManager.this.evictAll();
          CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond - DanmakuFactory.MAX_DANMAKU_DURATION);
          this.mSeekedFlag = true;
          return;
        case 8:
          CacheManagingDrawTask.CacheManager.this.evictAllNotInScreen(true);
          CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
          return;
        case 9:
        }
        CacheManagingDrawTask.CacheManager.this.evictAllNotInScreen(true);
        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
        CacheManagingDrawTask.this.requestClear();
      }

      public boolean isPause()
      {
        return this.mPause;
      }

      public void pause()
      {
        this.mPause = true;
        removeCallbacksAndMessages(null);
        sendEmptyMessage(6);
      }

      public void requestBuildCacheAndDraw(long paramLong)
      {
        removeMessages(3);
        this.mSeekedFlag = true;
        this.mCancelFlag = false;
        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond + paramLong);
        sendEmptyMessage(3);
      }

      public void requestCancelCaching()
      {
        this.mCancelFlag = true;
      }

      public void resume()
      {
        this.mCancelFlag = false;
        this.mPause = false;
        removeMessages(16);
        sendEmptyMessage(16);
        sendEmptyMessageDelayed(4, DanmakuFactory.MAX_DANMAKU_DURATION);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.CacheManagingDrawTask
 * JD-Core Version:    0.6.2
 */