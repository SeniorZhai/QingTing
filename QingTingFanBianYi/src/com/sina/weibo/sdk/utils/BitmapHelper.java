package com.sina.weibo.sdk.utils;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class BitmapHelper
{
  public static int getSampleSizeAutoFitToScreen(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 == 0) || (paramInt1 == 0))
      return 1;
    return Math.min(Math.max(paramInt3 / paramInt1, paramInt4 / paramInt2), Math.max(paramInt4 / paramInt1, paramInt3 / paramInt2));
  }

  public static int getSampleSizeOfNotTooLarge(Rect paramRect)
  {
    double d = paramRect.width() * paramRect.height() * 2.0D / 5242880.0D;
    if (d >= 1.0D)
      return (int)d;
    return 1;
  }

  public static boolean makesureSizeNotTooLarge(Rect paramRect)
  {
    return paramRect.width() * paramRect.height() * 2 <= 5242880;
  }

  public static boolean verifyBitmap(InputStream paramInputStream)
  {
    if (paramInputStream == null)
      return false;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    if ((paramInputStream instanceof BufferedInputStream));
    while (true)
    {
      BitmapFactory.decodeStream(paramInputStream, null, localOptions);
      try
      {
        paramInputStream.close();
        if ((localOptions.outHeight > 0) && (localOptions.outWidth > 0))
        {
          return true;
          paramInputStream = new BufferedInputStream(paramInputStream);
        }
      }
      catch (IOException paramInputStream)
      {
        while (true)
          paramInputStream.printStackTrace();
      }
    }
    return false;
  }

  public static boolean verifyBitmap(String paramString)
  {
    try
    {
      boolean bool = verifyBitmap(new FileInputStream(paramString));
      return bool;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }

  public static boolean verifyBitmap(byte[] paramArrayOfByte)
  {
    return verifyBitmap(new ByteArrayInputStream(paramArrayOfByte));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.BitmapHelper
 * JD-Core Version:    0.6.2
 */