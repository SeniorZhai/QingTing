package com.android.volley.toolbox;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ImageLoader
{
  private boolean isEnableProxy = false;
  private int mBatchResponseDelayMs = 100;
  private final HashMap<String, BatchedImageRequest> mBatchedResponses = new HashMap();
  private final ImageCache mCache;
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private final HashMap<String, BatchedImageRequest> mInFlightRequests = new HashMap();
  private final RequestQueue mRequestQueue;
  private Runnable mRunnable;
  public String passwd = "";
  public int proxyPortNumber = 0;
  public String proxyServer = "";

  public ImageLoader(RequestQueue paramRequestQueue, ImageCache paramImageCache)
  {
    this.mRequestQueue = paramRequestQueue;
    this.mCache = paramImageCache;
  }

  private void batchResponse(String paramString, BatchedImageRequest paramBatchedImageRequest)
  {
    this.mBatchedResponses.put(paramString, paramBatchedImageRequest);
    if (this.mRunnable == null)
    {
      this.mRunnable = new Runnable()
      {
        public void run()
        {
          Iterator localIterator1 = ImageLoader.this.mBatchedResponses.values().iterator();
          while (true)
          {
            if (!localIterator1.hasNext())
            {
              ImageLoader.this.mBatchedResponses.clear();
              ImageLoader.this.mRunnable = null;
              return;
            }
            ImageLoader.BatchedImageRequest localBatchedImageRequest = (ImageLoader.BatchedImageRequest)localIterator1.next();
            Iterator localIterator2 = ImageLoader.BatchedImageRequest.access$0(localBatchedImageRequest).iterator();
            while (localIterator2.hasNext())
            {
              ImageLoader.ImageContainer localImageContainer = (ImageLoader.ImageContainer)localIterator2.next();
              if (ImageLoader.ImageContainer.access$1(localImageContainer) != null)
                if (localBatchedImageRequest.getError() == null)
                {
                  ImageLoader.ImageContainer.access$2(localImageContainer, ImageLoader.BatchedImageRequest.access$2(localBatchedImageRequest));
                  ImageLoader.ImageContainer.access$1(localImageContainer).onResponse(localImageContainer, false);
                }
                else
                {
                  ImageLoader.ImageContainer.access$1(localImageContainer).onErrorResponse(localBatchedImageRequest.getError());
                }
            }
          }
        }
      };
      this.mHandler.postDelayed(this.mRunnable, this.mBatchResponseDelayMs);
    }
  }

  private static String getCacheKey(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.length() + 12 + "#W" + paramInt1 + "#H" + paramInt2 + paramString;
  }

  public static ImageListener getImageListener(final ImageView paramImageView, final int paramInt1, int paramInt2)
  {
    return new ImageListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        if (this.val$errorImageResId != 0)
          paramImageView.setImageResource(this.val$errorImageResId);
      }

      public void onResponse(ImageLoader.ImageContainer paramAnonymousImageContainer, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousImageContainer.getBitmap() != null)
          paramImageView.setImageBitmap(paramAnonymousImageContainer.getBitmap());
        while (paramInt1 == 0)
          return;
        paramImageView.setImageResource(paramInt1);
      }
    };
  }

  public static void log(String paramString)
  {
  }

  private void onGetImageError(String paramString, VolleyError paramVolleyError)
  {
    BatchedImageRequest localBatchedImageRequest = (BatchedImageRequest)this.mInFlightRequests.remove(paramString);
    localBatchedImageRequest.setError(paramVolleyError);
    if (localBatchedImageRequest != null)
      batchResponse(paramString, localBatchedImageRequest);
  }

  private void onGetImageSuccess(String paramString, Bitmap paramBitmap)
  {
    this.mCache.putBitmap(paramString, paramBitmap);
    BatchedImageRequest localBatchedImageRequest = (BatchedImageRequest)this.mInFlightRequests.remove(paramString);
    if (localBatchedImageRequest != null)
    {
      localBatchedImageRequest.mResponseBitmap = paramBitmap;
      batchResponse(paramString, localBatchedImageRequest);
    }
  }

  private void throwIfNotOnMainThread()
  {
    if (Looper.myLooper() != Looper.getMainLooper())
      throw new IllegalStateException("ImageLoader must be invoked from the main thread.");
  }

  public ImageContainer get(String paramString, ImageListener paramImageListener)
  {
    return get(paramString, paramImageListener, 0, 0);
  }

  public ImageContainer get(String paramString, ImageListener paramImageListener, int paramInt1, int paramInt2)
  {
    throwIfNotOnMainThread();
    final String str1 = getCacheKey(paramString, paramInt1, paramInt2);
    Object localObject = this.mCache.getBitmap(str1);
    if (localObject != null)
    {
      paramString = new ImageContainer((Bitmap)localObject, paramString, null, null);
      if (paramImageListener != null)
        paramImageListener.onResponse(paramString, true);
    }
    do
    {
      return paramString;
      localObject = new ImageContainer(null, paramString, str1, paramImageListener);
      if (paramImageListener != null)
        paramImageListener.onResponse((ImageContainer)localObject, true);
      paramImageListener = (BatchedImageRequest)this.mInFlightRequests.get(str1);
      if (paramImageListener == null)
        break;
      paramString = (String)localObject;
    }
    while (((ImageContainer)localObject).mListener == null);
    paramImageListener.addContainer((ImageContainer)localObject);
    return localObject;
    paramString = new ImageRequest(paramString, new Response.Listener()
    {
      public void onResponse(Bitmap paramAnonymousBitmap)
      {
        ImageLoader.this.onGetImageSuccess(str1, paramAnonymousBitmap);
      }
    }
    , paramInt1, paramInt2, Bitmap.Config.RGB_565, new Response.ErrorListener()
    {
      public void onErrorResponse(VolleyError paramAnonymousVolleyError)
      {
        ImageLoader.this.onGetImageError(str1, paramAnonymousVolleyError);
      }
    });
    log("new reqst:" + str1);
    if ((this.isEnableProxy) && ((!"".equals(this.proxyServer)) || ("".equals(this.passwd))))
    {
      VolleyLog.d("%s", new Object[] { "Woproxy is enabled for Volley" });
      paramImageListener = InetSocketAddress.createUnresolved(this.proxyServer, this.proxyPortNumber);
      paramImageListener = new Proxy(Proxy.Type.HTTP, paramImageListener);
    }
    try
    {
      String str2 = Base64.encodeToString(this.passwd.getBytes("UTF-8"), 0).replace("\n", "").replace("\r", "");
      paramString.addHeader("Authorization", "Basic " + str2);
      paramString.setProxy(paramImageListener);
      paramString.setIsEnableProxy(this.isEnableProxy);
      this.mRequestQueue.add(paramString);
      this.mInFlightRequests.put(str1, new BatchedImageRequest(paramString, (ImageContainer)localObject));
      return localObject;
    }
    catch (UnsupportedEncodingException paramImageListener)
    {
      while (true)
        VolleyLog.e("Volley proxy errpr", new Object[] { paramImageListener.getMessage() });
    }
  }

  public boolean getIsEnableProxy()
  {
    return this.isEnableProxy;
  }

  public boolean isCached(String paramString, int paramInt1, int paramInt2)
  {
    throwIfNotOnMainThread();
    paramString = getCacheKey(paramString, paramInt1, paramInt2);
    return this.mCache.getBitmap(paramString) != null;
  }

  public void setBatchedResponseDelay(int paramInt)
  {
    this.mBatchResponseDelayMs = paramInt;
  }

  public void setIsEnableProxy(boolean paramBoolean)
  {
    this.isEnableProxy = paramBoolean;
  }

  public void setProxyPasswd(String paramString)
  {
    this.passwd = paramString;
  }

  public void setProxyPort(int paramInt)
  {
    this.proxyPortNumber = paramInt;
  }

  public void setProxyURL(String paramString)
  {
    this.proxyServer = paramString;
  }

  private class BatchedImageRequest
  {
    private final Set<ImageLoader.ImageContainer> mContainers = new HashSet();
    private VolleyError mError;
    private final Request<?> mRequest;
    private Bitmap mResponseBitmap;

    public BatchedImageRequest(ImageLoader.ImageContainer arg2)
    {
      Object localObject1;
      this.mRequest = localObject1;
      Object localObject2;
      this.mContainers.add(localObject2);
    }

    public void addContainer(ImageLoader.ImageContainer paramImageContainer)
    {
      if (!this.mContainers.add(paramImageContainer))
      {
        ImageLoader.log("did not add in:" + ImageLoader.ImageContainer.access$0(paramImageContainer));
        return;
      }
      ImageLoader.log("        add in:" + ImageLoader.ImageContainer.access$0(paramImageContainer));
    }

    public VolleyError getError()
    {
      return this.mError;
    }

    public boolean removeContainerAndCancelIfNecessary(ImageLoader.ImageContainer paramImageContainer)
    {
      this.mContainers.remove(paramImageContainer);
      if (this.mContainers.size() == 0)
      {
        this.mRequest.cancel();
        return true;
      }
      return false;
    }

    public void setError(VolleyError paramVolleyError)
    {
      this.mError = paramVolleyError;
    }
  }

  public static abstract interface ImageCache
  {
    public abstract Bitmap getBitmap(String paramString);

    public abstract void putBitmap(String paramString, Bitmap paramBitmap);
  }

  public class ImageContainer
  {
    private Bitmap mBitmap;
    private final String mCacheKey;
    private final ImageLoader.ImageListener mListener;
    private final String mRequestUrl;

    public ImageContainer(Bitmap paramString1, String paramString2, String paramImageListener, ImageLoader.ImageListener arg5)
    {
      this.mBitmap = paramString1;
      this.mRequestUrl = paramString2;
      this.mCacheKey = paramImageListener;
      Object localObject;
      this.mListener = localObject;
    }

    public void cancelRequest()
    {
      if (this.mListener == null);
      ImageLoader.BatchedImageRequest localBatchedImageRequest;
      do
      {
        do
        {
          do
          {
            return;
            localBatchedImageRequest = (ImageLoader.BatchedImageRequest)ImageLoader.this.mInFlightRequests.get(this.mCacheKey);
            if (localBatchedImageRequest == null)
              break;
          }
          while (!localBatchedImageRequest.removeContainerAndCancelIfNecessary(this));
          ImageLoader.this.mInFlightRequests.remove(this.mCacheKey);
          return;
          localBatchedImageRequest = (ImageLoader.BatchedImageRequest)ImageLoader.this.mBatchedResponses.get(this.mCacheKey);
        }
        while (localBatchedImageRequest == null);
        localBatchedImageRequest.removeContainerAndCancelIfNecessary(this);
      }
      while (localBatchedImageRequest.mContainers.size() != 0);
      ImageLoader.this.mBatchedResponses.remove(this.mCacheKey);
    }

    public boolean equals(Object paramObject)
    {
      boolean bool1 = false;
      boolean bool3 = false;
      boolean bool2 = bool1;
      if (paramObject != null)
      {
        bool2 = bool1;
        if ((paramObject instanceof ImageContainer))
        {
          paramObject = (ImageContainer)paramObject;
          bool1 = bool3;
          if (this.mListener.equals(paramObject.mListener))
          {
            bool1 = bool3;
            if (this.mCacheKey.equalsIgnoreCase(paramObject.mCacheKey))
              bool1 = true;
          }
          bool2 = bool1;
          if (!bool1)
          {
            ImageLoader.log("(IC:false)" + this.mListener + ":" + paramObject.mListener);
            bool2 = bool1;
          }
        }
      }
      return bool2;
    }

    public Bitmap getBitmap()
    {
      return this.mBitmap;
    }

    public String getRequestUrl()
    {
      return this.mRequestUrl;
    }

    public int hashCode()
    {
      if (this.mListener != null)
        return this.mListener.hashCode();
      return 0;
    }
  }

  public static abstract interface ImageListener extends Response.ErrorListener
  {
    public abstract void onResponse(ImageLoader.ImageContainer paramImageContainer, boolean paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.ImageLoader
 * JD-Core Version:    0.6.2
 */