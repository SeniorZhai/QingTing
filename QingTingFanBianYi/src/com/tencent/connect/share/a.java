package com.tencent.connect.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.utils.AsynLoadImgBack;
import com.tencent.utils.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class a
{
  public static final int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = b(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      paramInt1 = 1;
      while (true)
      {
        paramInt2 = paramInt1;
        if (paramInt1 >= i)
          break;
        paramInt1 <<= 1;
      }
    }
    paramInt2 = (i + 7) / 8 * 8;
    return paramInt2;
  }

  private static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Matrix localMatrix = new Matrix();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (i > j);
    while (true)
    {
      float f = paramInt / i;
      localMatrix.postScale(f, f);
      return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
      i = j;
    }
  }

  public static final Bitmap a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString))
    {
      paramString = null;
      return paramString;
    }
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    int i = localOptions.outWidth;
    int j = localOptions.outHeight;
    if ((localOptions.mCancel) || (localOptions.outWidth == -1) || (localOptions.outHeight == -1))
      return null;
    if (i > j);
    Bitmap localBitmap;
    while (true)
    {
      localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
      if (i > paramInt)
        localOptions.inSampleSize = a(localOptions, -1, paramInt * paramInt);
      localOptions.inJustDecodeBounds = false;
      localBitmap = BitmapFactory.decodeFile(paramString, localOptions);
      if (localBitmap != null)
        break;
      return null;
      i = j;
    }
    i = localOptions.outWidth;
    j = localOptions.outHeight;
    if (i > j);
    while (true)
    {
      paramString = localBitmap;
      if (i <= paramInt)
        break;
      return a(localBitmap, paramInt);
      i = j;
    }
  }

  protected static final String a(Bitmap paramBitmap, String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists())
      localFile.mkdirs();
    paramString1 = paramString1 + paramString2;
    paramString2 = new File(paramString1);
    if (paramString2.exists())
      paramString2.delete();
    if (paramBitmap != null);
    try
    {
      paramString2 = new FileOutputStream(paramString2);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 80, paramString2);
      paramString2.flush();
      paramString2.close();
      paramBitmap.recycle();
      return paramString1;
    }
    catch (FileNotFoundException paramBitmap)
    {
      paramBitmap.printStackTrace();
      return null;
    }
    catch (IOException paramBitmap)
    {
      while (true)
        paramBitmap.printStackTrace();
    }
  }

  public static final void a(Context paramContext, String paramString, final AsynLoadImgBack paramAsynLoadImgBack)
  {
    Log.d("AsynScaleCompressImage", "scaleCompressImage");
    if (TextUtils.isEmpty(paramString))
    {
      paramAsynLoadImgBack.saved(1, null);
      return;
    }
    if (!Util.hasSDCard())
    {
      paramAsynLoadImgBack.saved(2, null);
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = a.a(this.a, 140);
        if (localObject1 != null)
        {
          Object localObject2 = Environment.getExternalStorageDirectory() + "/tmp/";
          String str = Util.encrypt(this.a);
          str = "share2qq_temp" + str + ".jpg";
          if (!a.a(this.a, 140, 140))
            Log.d("AsynScaleCompressImage", "not out of bound,not compress!");
          for (localObject1 = this.a; localObject1 != null; localObject1 = a.a((Bitmap)localObject1, (String)localObject2, str))
          {
            localObject2 = this.b.obtainMessage(101);
            ((Message)localObject2).obj = localObject1;
            this.b.sendMessage((Message)localObject2);
            return;
            Log.d("AsynScaleCompressImage", "out of bound,compress!");
          }
        }
        localObject1 = this.b.obtainMessage(102);
        ((Message)localObject1).arg1 = 3;
        this.b.sendMessage((Message)localObject1);
      }
    }).start();
  }

  public static final void a(Context paramContext, ArrayList<String> paramArrayList, final AsynLoadImgBack paramAsynLoadImgBack)
  {
    Log.d("AsynScaleCompressImage", "batchScaleCompressImage");
    if (paramArrayList == null)
    {
      paramAsynLoadImgBack.saved(1, null);
      return;
    }
    if (!Util.hasSDCard())
    {
      paramAsynLoadImgBack.saved(2, null);
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        if (i < this.a.size())
        {
          localObject1 = (String)this.a.get(i);
          String str1;
          String str2;
          if ((!Util.isValidUrl((String)localObject1)) && (Util.fileExists((String)localObject1)))
          {
            localObject2 = a.a((String)localObject1, 10000);
            if (localObject2 != null)
            {
              str1 = Environment.getExternalStorageDirectory() + "/tmp/";
              str2 = Util.encrypt((String)localObject1);
              str2 = "share2qzone_temp" + str2 + ".jpg";
              if (a.a((String)localObject1, 640, 10000))
                break label154;
              Log.d("AsynScaleCompressImage", "not out of bound,not compress!");
            }
          }
          while (true)
          {
            if (localObject1 != null)
              this.a.set(i, localObject1);
            i += 1;
            break;
            label154: Log.d("AsynScaleCompressImage", "out of bound, compress!");
            localObject1 = a.a((Bitmap)localObject2, str1, str2);
          }
        }
        Object localObject1 = this.b.obtainMessage(101);
        Object localObject2 = new Bundle();
        ((Bundle)localObject2).putStringArrayList("images", this.a);
        ((Message)localObject1).setData((Bundle)localObject2);
        this.b.sendMessage((Message)localObject1);
      }
    }).start();
  }

  private static int b(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
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

  private static final boolean b(String paramString, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    int i = localOptions.outWidth;
    int j = localOptions.outHeight;
    if ((localOptions.mCancel) || (localOptions.outWidth == -1) || (localOptions.outHeight == -1))
      return false;
    int k;
    if (i > j)
    {
      k = i;
      if (i >= j)
        break label147;
    }
    while (true)
    {
      Log.d("AsynScaleCompressImage", "longSide=" + k + "shortSide=" + i);
      localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
      if ((k <= paramInt2) && (i <= paramInt1))
        break label154;
      return true;
      k = j;
      break;
      label147: i = j;
    }
    label154: return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.share.a
 * JD-Core Version:    0.6.2
 */