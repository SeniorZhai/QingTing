package fm.qingting.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.ByteArrayOutputStream;

public class ImageUtil
{
  private static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    if ((j > paramInt2) || (k > paramInt1))
    {
      if (k > j)
        i = Math.round(j / paramInt2);
    }
    else
      return i;
    return Math.round(k / paramInt1);
  }

  public static byte[] compressImage(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 80, localByteArrayOutputStream);
    int i = 100;
    while ((localByteArrayOutputStream.toByteArray().length / 1024 > 100) && (i > 0))
    {
      i -= 10;
      localByteArrayOutputStream.reset();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, i, localByteArrayOutputStream);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] getCompressImage(String paramString)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      localOptions.inSampleSize = calculateInSampleSize(localOptions, 480, localOptions.outHeight * 480 / localOptions.outWidth);
      localOptions.inJustDecodeBounds = false;
      paramString = compressImage(BitmapFactory.decodeFile(paramString, localOptions));
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ImageUtil
 * JD-Core Version:    0.6.2
 */