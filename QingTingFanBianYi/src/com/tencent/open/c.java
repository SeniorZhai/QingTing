package com.tencent.open;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class c extends AsyncTask<Bitmap, Void, HashMap<String, Object>>
{
  private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.CHINA);
  private a b;

  public c(a parama)
  {
    this.b = parama;
  }

  private int a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    int i = 1;
    if ((k > paramInt2) || (j > paramInt1))
    {
      i = Math.round(k / paramInt2);
      paramInt1 = Math.round(j / paramInt1);
      if (i >= paramInt1);
    }
    else
    {
      return i;
    }
    return paramInt1;
  }

  private Bitmap a(Bitmap paramBitmap)
  {
    int i = 1;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localByteArrayOutputStream);
    if (localByteArrayOutputStream.toByteArray().length / 1024 > 1024)
    {
      localByteArrayOutputStream.reset();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 50, localByteArrayOutputStream);
    }
    paramBitmap = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramBitmap, null, localOptions);
    localOptions.inJustDecodeBounds = false;
    int j = a(localOptions, 320, 320);
    if (j <= 0);
    while (true)
    {
      Log.i("comp", "comp be=" + i);
      localOptions.inSampleSize = i;
      return BitmapFactory.decodeStream(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()), null, localOptions);
      i = j;
    }
  }

  private String a(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return a.format(localDate);
  }

  public static void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString));
    try
    {
      paramString = new File(paramString);
      if (paramString.exists())
        paramString.delete();
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public static boolean a()
  {
    if (Environment.getExternalStorageState().equals("mounted"));
    while (new File("/mnt/sdcard-ext").isDirectory())
      return true;
    return false;
  }

  private String b()
  {
    String str = ".";
    if (Environment.getExternalStorageState().equals("mounted"))
      str = Environment.getExternalStorageDirectory().getAbsolutePath();
    while (!new File("/mnt/sdcard-ext").isDirectory())
      return str;
    return "/mnt/sdcard-ext";
  }

  // ERROR //
  private String b(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: new 96	java/lang/StringBuilder
    //   8: dup
    //   9: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   12: aload_0
    //   13: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   16: invokespecial 187	com/tencent/open/c:a	(J)Ljava/lang/String;
    //   19: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: ldc 189
    //   24: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: astore 4
    //   32: new 96	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   39: aload_0
    //   40: invokespecial 191	com/tencent/open/c:b	()Ljava/lang/String;
    //   43: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: getstatic 195	java/io/File:separator	Ljava/lang/String;
    //   49: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc 197
    //   54: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: astore_2
    //   61: new 96	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   68: aload_2
    //   69: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: getstatic 195	java/io/File:separator	Ljava/lang/String;
    //   75: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: aload 4
    //   80: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: astore 4
    //   88: new 140	java/io/File
    //   91: dup
    //   92: aload_2
    //   93: invokespecial 142	java/io/File:<init>	(Ljava/lang/String;)V
    //   96: astore_2
    //   97: aload_2
    //   98: invokevirtual 146	java/io/File:exists	()Z
    //   101: ifne +10 -> 111
    //   104: aload_2
    //   105: invokevirtual 200	java/io/File:mkdirs	()Z
    //   108: ifne +3 -> 111
    //   111: new 140	java/io/File
    //   114: dup
    //   115: aload 4
    //   117: invokespecial 142	java/io/File:<init>	(Ljava/lang/String;)V
    //   120: astore_2
    //   121: aload_2
    //   122: invokevirtual 146	java/io/File:exists	()Z
    //   125: ifeq +8 -> 133
    //   128: aload_2
    //   129: invokevirtual 149	java/io/File:delete	()Z
    //   132: pop
    //   133: aload_2
    //   134: invokevirtual 203	java/io/File:createNewFile	()Z
    //   137: pop
    //   138: new 205	java/io/FileOutputStream
    //   141: dup
    //   142: aload_2
    //   143: invokespecial 208	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   146: astore_2
    //   147: aload_1
    //   148: getstatic 211	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   151: bipush 100
    //   153: aload_2
    //   154: invokevirtual 67	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   157: pop
    //   158: aload_2
    //   159: invokevirtual 214	java/io/FileOutputStream:flush	()V
    //   162: aload 4
    //   164: astore_1
    //   165: aload_2
    //   166: ifnull +10 -> 176
    //   169: aload_2
    //   170: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   173: aload 4
    //   175: astore_1
    //   176: aload_1
    //   177: areturn
    //   178: astore_1
    //   179: aload_3
    //   180: astore_2
    //   181: ldc 219
    //   183: astore_1
    //   184: aload_2
    //   185: ifnull -9 -> 176
    //   188: aload_2
    //   189: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   192: ldc 219
    //   194: areturn
    //   195: astore_1
    //   196: ldc 219
    //   198: areturn
    //   199: astore_1
    //   200: aload 5
    //   202: astore_2
    //   203: aload_2
    //   204: ifnull +7 -> 211
    //   207: aload_2
    //   208: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   211: aload_1
    //   212: athrow
    //   213: astore_1
    //   214: aload 4
    //   216: areturn
    //   217: astore_2
    //   218: goto -7 -> 211
    //   221: astore_1
    //   222: goto -19 -> 203
    //   225: astore_1
    //   226: goto -45 -> 181
    //
    // Exception table:
    //   from	to	target	type
    //   5	111	178	java/lang/Exception
    //   111	133	178	java/lang/Exception
    //   133	147	178	java/lang/Exception
    //   188	192	195	java/io/IOException
    //   5	111	199	finally
    //   111	133	199	finally
    //   133	147	199	finally
    //   169	173	213	java/io/IOException
    //   207	211	217	java/io/IOException
    //   147	162	221	finally
    //   147	162	225	java/lang/Exception
  }

  protected HashMap<String, Object> a(Bitmap[] paramArrayOfBitmap)
  {
    HashMap localHashMap = new HashMap();
    Bitmap localBitmap1 = paramArrayOfBitmap[0];
    if (localBitmap1 != null)
      try
      {
        if ((localBitmap1.getWidth() > 320) || (localBitmap1.getHeight() > 320))
        {
          Bitmap localBitmap2 = a(localBitmap1);
          paramArrayOfBitmap = b(localBitmap2);
          localBitmap2.recycle();
        }
        while (true)
        {
          localBitmap1.recycle();
          localHashMap.put("ResultType", Integer.valueOf(1));
          localHashMap.put("ResultValue", paramArrayOfBitmap);
          return localHashMap;
          paramArrayOfBitmap = b(localBitmap1);
        }
      }
      catch (Exception paramArrayOfBitmap)
      {
        localHashMap.put("ResultType", Integer.valueOf(0));
        localHashMap.put("ResultValue", paramArrayOfBitmap.getMessage());
      }
    return localHashMap;
  }

  protected void a(HashMap<String, Object> paramHashMap)
  {
    if (((Integer)paramHashMap.get("ResultType")).intValue() == 1)
      this.b.a((String)paramHashMap.get("ResultValue"));
    while (true)
    {
      super.onPostExecute(paramHashMap);
      return;
      this.b.b((String)paramHashMap.get("ResultValue"));
    }
  }

  public static abstract interface a
  {
    public abstract void a(String paramString);

    public abstract void b(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.c
 * JD-Core Version:    0.6.2
 */