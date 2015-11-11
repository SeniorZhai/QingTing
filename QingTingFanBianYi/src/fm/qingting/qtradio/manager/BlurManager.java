package fm.qingting.qtradio.manager;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BlurManager
{
  private static BlurManager sInstance;
  private List<ImageCache> mBlurList = new ArrayList();
  private HashMap<String, Bitmap> mCaches = new HashMap();
  private HashSet<String> mExisingList = new HashSet();
  private List<IImageBluredListener> mListeners;
  private boolean mLoading = false;
  private Handler mMainLoopHandler;
  private Bitmap mPlayBitmap;
  private String mPlayId;
  private TaskHandler mTaskHandler;
  private Bitmap mTempBitmap;
  private String mTempId;

  public static BlurManager getInstance()
  {
    if (sInstance == null)
      sInstance = new BlurManager();
    return sInstance;
  }

  private void init()
  {
    if (this.mTaskHandler == null)
    {
      HandlerThread localHandlerThread = new HandlerThread("blurtask");
      localHandlerThread.start();
      this.mTaskHandler = new TaskHandler(localHandlerThread.getLooper());
    }
    if (this.mMainLoopHandler == null)
      this.mMainLoopHandler = new MainLoopHandler(Looper.getMainLooper());
  }

  private void startAsycBlurProcess()
  {
    if (this.mBlurList.size() == 0)
      return;
    try
    {
      this.mLoading = true;
      Object localObject = (ImageCache)this.mBlurList.remove(0);
      BlurBitmap localBlurBitmap = new BlurBitmap(((ImageCache)localObject).getBitmap(), ((ImageCache)localObject).getRect());
      localBlurBitmap.process(30);
      if (TextUtils.equals(this.mTempId, ((ImageCache)localObject).getId()))
      {
        if (this.mTempBitmap != null)
        {
          this.mTempBitmap.recycle();
          this.mTempBitmap = null;
        }
        this.mTempBitmap = localBlurBitmap.returnBlurredImage(0);
        this.mExisingList.remove(((ImageCache)localObject).getId());
      }
      while (true)
      {
        localObject = Message.obtain(this.mMainLoopHandler, 0, ((ImageCache)localObject).getId());
        this.mMainLoopHandler.sendMessage((Message)localObject);
        label130: this.mLoading = false;
        this.mTaskHandler.sendEmptyMessage(0);
        return;
        if (TextUtils.equals(this.mPlayId, ((ImageCache)localObject).getId()))
        {
          this.mPlayBitmap = localBlurBitmap.returnBlurredImageInPlay(-2013265920);
          this.mExisingList.remove(((ImageCache)localObject).getId());
        }
        else
        {
          this.mCaches.put(((ImageCache)localObject).getId(), localBlurBitmap.returnBlurredImage(((ImageCache)localObject).getMaskBitmap(), ((ImageCache)localObject).getOffset()));
          this.mExisingList.remove(((ImageCache)localObject).getId());
        }
      }
    }
    catch (Exception localException)
    {
      break label130;
    }
    catch (Error localError)
    {
      break label130;
    }
  }

  public void addListener(IImageBluredListener paramIImageBluredListener)
  {
    init();
    if (this.mListeners == null)
      this.mListeners = new ArrayList();
    this.mListeners.add(paramIImageBluredListener);
  }

  public void blurBitmap(Bitmap paramBitmap1, Bitmap paramBitmap2, Rect paramRect, String paramString, int paramInt)
  {
    if (this.mExisingList.contains(paramString));
    do
    {
      return;
      this.mExisingList.add(paramString);
      this.mBlurList.add(new ImageCache(paramString, paramBitmap1, paramBitmap2, paramRect, paramInt));
    }
    while (this.mLoading);
    this.mTaskHandler.sendEmptyMessage(0);
  }

  public void blurBitmap(Bitmap paramBitmap1, Bitmap paramBitmap2, Rect paramRect, String paramString, int paramInt, boolean paramBoolean)
  {
    if (!paramBoolean)
      blurBitmap(paramBitmap1, paramBitmap2, paramRect, paramString, paramInt);
    do
    {
      do
        return;
      while (this.mExisingList.contains(paramString));
      this.mTempId = paramString;
      this.mExisingList.add(paramString);
      this.mBlurList.add(new ImageCache(paramString, paramBitmap1, paramBitmap2, paramRect, paramInt));
    }
    while (this.mLoading);
    this.mTaskHandler.sendEmptyMessage(0);
  }

  public void blurBitmapInPlay(Bitmap paramBitmap, int paramInt, Rect paramRect, String paramString)
  {
    if ((this.mExisingList.contains(paramString)) && (TextUtils.equals(this.mPlayId, paramString)));
    do
    {
      return;
      this.mPlayId = paramString;
      this.mExisingList.add(paramString);
      this.mBlurList.add(new ImageCache(paramString, paramBitmap, null, paramRect, 0));
    }
    while (this.mLoading);
    this.mTaskHandler.sendEmptyMessage(0);
  }

  public Bitmap getBluredBitmap(String paramString)
  {
    return (Bitmap)this.mCaches.get(paramString);
  }

  public Bitmap getBluredBitmap(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      return getBluredBitmap(paramString);
    if (TextUtils.equals(paramString, this.mTempId))
      return this.mTempBitmap;
    return null;
  }

  public Bitmap getBluredBitmapInPlay(String paramString)
  {
    if (TextUtils.equals(paramString, this.mPlayId))
      return this.mPlayBitmap;
    return null;
  }

  public void removeListener(IImageBluredListener paramIImageBluredListener)
  {
    if (this.mListeners != null)
      this.mListeners.remove(paramIImageBluredListener);
  }

  public static abstract interface IImageBluredListener
  {
    public abstract void onBlurFinished(String paramString);
  }

  private class MainLoopHandler extends Handler
  {
    public MainLoopHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (BlurManager.this.mListeners != null)
      {
        int i = 0;
        while (i < BlurManager.this.mListeners.size())
        {
          ((BlurManager.IImageBluredListener)BlurManager.this.mListeners.get(i)).onBlurFinished((String)paramMessage.obj);
          i += 1;
        }
      }
    }
  }

  class TaskHandler extends Handler
  {
    public TaskHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (BlurManager.this.mLoading)
        return;
      BlurManager.this.startAsycBlurProcess();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.BlurManager
 * JD-Core Version:    0.6.2
 */