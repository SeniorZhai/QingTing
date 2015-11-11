package com.taobao.newxp.imagecache.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import java.io.FileDescriptor;

public class c extends d
{
  private static final String e = "ImageResizer";
  protected int a;
  protected int b;

  public c(Context paramContext, int paramInt)
  {
    super(paramContext);
    a(paramInt);
  }

  public c(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    a(paramInt1, paramInt2);
  }

  public static int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int m = paramOptions.outWidth;
    int j = 1;
    if ((k > paramInt2) || (m > paramInt1))
    {
      int i = Math.round(k / paramInt2);
      j = Math.round(m / paramInt1);
      if (i < j);
      while (true)
      {
        float f1 = m * k;
        float f2 = paramInt1 * paramInt2 * 2;
        while (true)
        {
          j = i;
          if (f1 / (i * i) <= f2)
            break;
          i += 1;
        }
        i = j;
      }
    }
    return j;
  }

  public static Bitmap a(Resources paramResources, int paramInt1, int paramInt2, int paramInt3, ImageCache paramImageCache)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inSampleSize = a(localOptions, paramInt2, paramInt3);
    if (e.d())
      a(localOptions, paramImageCache);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
  }

  public static Bitmap a(FileDescriptor paramFileDescriptor, int paramInt1, int paramInt2, ImageCache paramImageCache)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, localOptions);
    localOptions.inSampleSize = a(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    if (e.d())
      a(localOptions, paramImageCache);
    try
    {
      paramFileDescriptor = BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, localOptions);
      return paramFileDescriptor;
    }
    catch (OutOfMemoryError paramFileDescriptor)
    {
      Log.w("ImageResizer", "OutOfMemoryError on decodeFileDescriptor.");
    }
    return null;
  }

  public static Bitmap a(String paramString, int paramInt1, int paramInt2, ImageCache paramImageCache)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = a(localOptions, paramInt1, paramInt2);
    if (e.d())
      a(localOptions, paramImageCache);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  private static void a(BitmapFactory.Options paramOptions, ImageCache paramImageCache)
  {
    paramOptions.inMutable = true;
    if (paramImageCache != null)
    {
      paramImageCache = paramImageCache.a(paramOptions);
      if (paramImageCache != null)
        paramOptions.inBitmap = paramImageCache;
    }
  }

  private Bitmap c(int paramInt)
  {
    return a(this.d, paramInt, this.a, this.b, f());
  }

  protected Bitmap a(Object paramObject)
  {
    return c(Integer.parseInt(String.valueOf(paramObject)));
  }

  public void a(int paramInt)
  {
    a(paramInt, paramInt);
  }

  public void a(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.c
 * JD-Core Version:    0.6.2
 */