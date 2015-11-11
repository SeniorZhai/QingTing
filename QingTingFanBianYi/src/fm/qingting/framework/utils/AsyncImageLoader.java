package fm.qingting.framework.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class AsyncImageLoader
{
  private static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap();
  private static boolean mEnableCache;
  private AsyncTask<Void, Void, Void> mAsyncTask;
  private InputStream mIStream;
  private ImageCallback mImageCallback;
  private Thread mThread;

  public static void enableCache(boolean paramBoolean)
  {
    mEnableCache = paramBoolean;
  }

  public static Bitmap loadImageFromUrl(String paramString, AsyncImageLoader paramAsyncImageLoader)
  {
    Object localObject = null;
    try
    {
      paramAsyncImageLoader.mIStream = ((InputStream)new URL(paramString).getContent());
      paramString = localObject;
      if (paramAsyncImageLoader.mIStream != null)
      {
        paramString = new BitmapFactory.Options();
        paramString = BitmapFactory.decodeStream(paramAsyncImageLoader.mIStream, null, paramString);
      }
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public void cancel()
  {
    this.mImageCallback = null;
    if (this.mAsyncTask != null)
      this.mAsyncTask.cancel(true);
    if ((this.mThread == null) || (this.mIStream != null));
    try
    {
      this.mIStream.close();
      this.mThread.interrupt();
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public Bitmap loadBitmapWithAsyncTask(final String paramString, ImageCallback paramImageCallback)
  {
    this.mImageCallback = paramImageCallback;
    if ((mEnableCache) && (imageCache.containsKey(paramString)))
    {
      paramImageCallback = (Bitmap)((SoftReference)imageCache.get(paramString)).get();
      if (paramImageCallback != null)
        return paramImageCallback;
    }
    this.mAsyncTask = new AsyncTask()
    {
      protected Void doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        Bitmap localBitmap = AsyncImageLoader.loadImageFromUrl(paramString, AsyncImageLoader.this);
        paramAnonymousArrayOfVoid = localBitmap;
        if (localBitmap == null)
          paramAnonymousArrayOfVoid = AsyncImageLoader.loadImageFromUrl(paramString, AsyncImageLoader.this);
        if (AsyncImageLoader.mEnableCache)
          AsyncImageLoader.imageCache.put(paramString, new SoftReference(paramAnonymousArrayOfVoid));
        paramAnonymousArrayOfVoid = this.val$handler.obtainMessage(0, paramAnonymousArrayOfVoid);
        this.val$handler.sendMessage(paramAnonymousArrayOfVoid);
        return null;
      }

      protected void onCancelled()
      {
        if (AsyncImageLoader.this.mIStream != null);
        try
        {
          AsyncImageLoader.this.mIStream.close();
          return;
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }
    };
    this.mAsyncTask.execute(new Void[0]);
    return null;
  }

  public Bitmap loadBitmapWithThread(final String paramString, ImageCallback paramImageCallback)
  {
    this.mImageCallback = paramImageCallback;
    if ((mEnableCache) && (imageCache.containsKey(paramString)))
    {
      paramImageCallback = (Bitmap)((SoftReference)imageCache.get(paramString)).get();
      if (paramImageCallback != null)
        return paramImageCallback;
    }
    this.mThread = new Thread()
    {
      public void run()
      {
        Object localObject = AsyncImageLoader.loadImageFromUrl(paramString, AsyncImageLoader.this);
        if (AsyncImageLoader.mEnableCache)
          AsyncImageLoader.imageCache.put(paramString, new SoftReference(localObject));
        localObject = this.val$handler.obtainMessage(0, localObject);
        this.val$handler.sendMessage((Message)localObject);
      }
    };
    this.mThread.start();
    return null;
  }

  public static abstract interface ImageCallback
  {
    public abstract void imageLoaded(Bitmap paramBitmap, String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.AsyncImageLoader
 * JD-Core Version:    0.6.2
 */