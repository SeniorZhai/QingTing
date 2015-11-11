package com.neusoft.parse;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncImageLoader
{
  private static HashSet a = new HashSet();
  private static Map b = new HashMap();
  private static LoaderImpl c = new LoaderImpl(b);
  private static ExecutorService d;

  public AsyncImageLoader(Context paramContext)
  {
    startThreadPoolIfNecessary();
    setCachedDir(paramContext.getCacheDir().getAbsolutePath());
  }

  public static void startThreadPoolIfNecessary()
  {
    if ((d == null) || (d.isShutdown()) || (d.isTerminated()))
      d = Executors.newFixedThreadPool(3);
  }

  public void downloadImage(String paramString, AsyncImageLoader.ImageCallback paramImageCallback)
  {
    downloadImage(paramString, true, paramImageCallback);
  }

  public void downloadImage(String paramString, boolean paramBoolean, AsyncImageLoader.ImageCallback paramImageCallback)
  {
    if (a.contains(paramString))
      Log.e("chuxl", "###该图片正在下载，不能重复下载！");
    Bitmap localBitmap;
    do
    {
      return;
      localBitmap = c.getBitmapFromMemory(paramString);
      if (localBitmap == null)
        break;
    }
    while (paramImageCallback == null);
    Log.e("chuxl", "从内存中取");
    paramImageCallback.onImageLoaded(localBitmap, paramString);
    return;
    Log.e("chuxl", "从网络端下载图片");
    a.add(paramString);
    d.submit(new a(this, paramString, paramBoolean, paramImageCallback));
  }

  public void preLoadNextImage(String paramString)
  {
    downloadImage(paramString, null);
  }

  public void setCache2File(boolean paramBoolean)
  {
    c.setCache2File(paramBoolean);
  }

  public void setCachedDir(String paramString)
  {
    c.setCachedDir(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.parse.AsyncImageLoader
 * JD-Core Version:    0.6.2
 */