package fm.qingting.framework.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RequestQueue.RequestFilter;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader
{
  private static ImageLoader instance = null;
  private com.android.volley.toolbox.ImageLoader loader = null;
  private RequestQueue requestQueue = null;

  private ImageLoader(Context paramContext)
  {
    init(paramContext);
  }

  public static byte[] InputStreamToByte(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = paramInputStream.read();
      if (i == -1)
      {
        paramInputStream = localByteArrayOutputStream.toByteArray();
        localByteArrayOutputStream.close();
        return paramInputStream;
      }
      try
      {
        localByteArrayOutputStream.write(i);
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        System.gc();
      }
    }
  }

  private static int computeInitialSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    int j;
    if (paramInt2 == -1)
    {
      i = 1;
      if (paramInt1 != -1)
        break label60;
      j = 128;
      label31: if (j >= i)
        break label84;
    }
    label60: label84: 
    do
    {
      return i;
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
      break;
      j = (int)Math.min(Math.floor(d1 / paramInt1), Math.floor(d2 / paramInt1));
      break label31;
      if ((paramInt2 == -1) && (paramInt1 == -1))
        return 1;
    }
    while (paramInt1 == -1);
    return j;
  }

  public static int computeSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    paramInt2 = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
    if (paramInt2 <= 8)
    {
      paramInt1 = 1;
      while (true)
      {
        if (paramInt1 >= paramInt2)
          return paramInt1;
        paramInt1 <<= 1;
      }
    }
    return (paramInt2 + 7) / 8 * 8;
  }

  public static ImageLoader getInstance(Context paramContext)
  {
    try
    {
      if (instance == null)
        instance = new ImageLoader(paramContext);
      paramContext = instance;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private void init(Context paramContext)
  {
    int i = (int)(Runtime.getRuntime().maxMemory() / 1024L / 8L);
    this.requestQueue = Volley.newRequestQueue(paramContext, 4);
    this.loader = new com.android.volley.toolbox.ImageLoader(this.requestQueue, new ImageCacheImpl(i));
  }

  public static void log(String paramString)
  {
    Log.e("Volley ImageLoader", paramString);
  }

  public Bitmap getImage(String paramString, int paramInt1, int paramInt2)
  {
    return getImage(paramString, paramInt1, paramInt2, true);
  }

  public Bitmap getImage(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.trim().equalsIgnoreCase("")))
      return null;
    if (!paramBoolean)
    {
      paramInt2 = 1000;
      paramInt1 = 1000;
    }
    return this.loader.get(paramString, null, paramInt1, paramInt2).getBitmap();
  }

  public com.android.volley.toolbox.ImageLoader getVolleyImageLoader()
  {
    return this.loader;
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, int paramInt1, int paramInt2)
  {
    loadImage(paramString, paramImageView, paramObject, paramInt1, paramInt2, null);
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, int paramInt1, int paramInt2, ImageLoaderHandler paramImageLoaderHandler)
  {
    loadImage(paramString, paramObject, paramInt1, paramInt2, true, paramImageLoaderHandler);
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, ImageLoaderHandler paramImageLoaderHandler)
  {
    loadImage(paramString, paramImageView, paramObject, 0, 0, paramImageLoaderHandler);
  }

  public void loadImage(String paramString, Object paramObject, int paramInt1, int paramInt2, boolean paramBoolean, ImageLoaderHandler paramImageLoaderHandler)
  {
    if ((paramString == null) || (paramString.trim().equalsIgnoreCase("")))
      return;
    if (!paramBoolean)
    {
      paramInt2 = 0;
      paramInt1 = 0;
    }
    this.loader.get(paramString, new MyImageListener(paramImageLoaderHandler, paramInt1, paramInt2, paramString), paramInt1, paramInt2);
  }

  public void releaseAllCache()
  {
  }

  public void releaseCache(Object paramObject)
  {
  }

  public void reset()
  {
  }

  public void setDefDataPath(String paramString)
  {
  }

  public void setIsEnableProxy(boolean paramBoolean)
  {
    this.loader.setIsEnableProxy(paramBoolean);
  }

  public void setProxyInfo(String paramString1, int paramInt, String paramString2)
  {
    this.loader.setProxyURL(paramString1);
    this.loader.setProxyPort(paramInt);
    this.loader.setProxyPasswd(paramString2);
  }

  public void stopAllLoading()
  {
    this.requestQueue.cancelAll(new RequestQueue.RequestFilter()
    {
      public boolean apply(Request<?> paramAnonymousRequest)
      {
        return true;
      }
    });
  }

  public class MyImageListener
    implements ImageLoader.ImageListener
  {
    ImageLoaderHandler handler;
    int height;
    String url;
    int width;

    public MyImageListener(ImageLoaderHandler paramInt1, int paramInt2, int paramString, String arg5)
    {
      this.handler = paramInt1;
      this.width = paramInt2;
      this.height = paramString;
      Object localObject;
      this.url = localObject;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool1 = false;
      boolean bool3 = false;
      boolean bool2 = bool1;
      if (paramObject != null)
      {
        bool2 = bool1;
        if ((paramObject instanceof MyImageListener))
        {
          paramObject = (MyImageListener)paramObject;
          bool1 = bool3;
          if (this.handler == paramObject.handler)
          {
            bool1 = bool3;
            if (this.width == paramObject.width)
            {
              bool1 = bool3;
              if (this.height == paramObject.height)
                bool1 = true;
            }
          }
          bool2 = bool1;
          if (!bool1)
          {
            ImageLoader.log("(ML:false)" + this.handler + ":" + paramObject.handler);
            bool2 = bool1;
          }
        }
      }
      return bool2;
    }

    public int hashCode()
    {
      if (this.handler == null)
        return this.width + this.height;
      return this.handler.hashCode() + this.width + this.height;
    }

    public void onErrorResponse(VolleyError paramVolleyError)
    {
      if (this.handler != null)
        this.handler.loadImageFinish(false, this.url, null, this.width, this.height);
    }

    public void onResponse(ImageLoader.ImageContainer paramImageContainer, boolean paramBoolean)
    {
      if ((paramImageContainer.getBitmap() != null) && (this.handler != null) && (!paramBoolean))
        this.handler.loadImageFinish(true, paramImageContainer.getRequestUrl(), paramImageContainer.getBitmap(), this.width, this.height);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.ImageLoader
 * JD-Core Version:    0.6.2
 */