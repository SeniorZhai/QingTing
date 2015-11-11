package tv.cjump.jni;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Field;

public class NativeBitmapFactory
{
  static Field nativeIntField = null;
  static boolean nativeLibLoaded = false;

  private static native Bitmap createBitmap(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

  public static Bitmap createBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig)
  {
    return createBitmap(paramInt1, paramInt2, paramConfig, paramConfig.equals(Bitmap.Config.ARGB_8888));
  }

  public static Bitmap createBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig, boolean paramBoolean)
  {
    if ((!nativeLibLoaded) || (nativeIntField == null))
      return Bitmap.createBitmap(paramInt1, paramInt2, paramConfig);
    return createNativeBitmap(paramInt1, paramInt2, paramConfig, paramBoolean);
  }

  private static native Bitmap createBitmap19(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

  private static Bitmap createNativeBitmap(int paramInt1, int paramInt2, Bitmap.Config paramConfig, boolean paramBoolean)
  {
    int i = getNativeConfig(paramConfig);
    if (Build.VERSION.SDK_INT == 19)
      return createBitmap19(paramInt1, paramInt2, i, paramBoolean);
    return createBitmap(paramInt1, paramInt2, i, paramBoolean);
  }

  public static int getNativeConfig(Bitmap.Config paramConfig)
  {
    try
    {
      if (nativeIntField == null)
        return 0;
      int i = nativeIntField.getInt(paramConfig);
      return i;
    }
    catch (IllegalArgumentException paramConfig)
    {
      paramConfig.printStackTrace();
      return 0;
    }
    catch (IllegalAccessException paramConfig)
    {
      paramConfig.printStackTrace();
    }
    return 0;
  }

  private static native boolean init();

  static void initField()
  {
    try
    {
      nativeIntField = Bitmap.Config.class.getDeclaredField("nativeInt");
      nativeIntField.setAccessible(true);
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      nativeIntField = null;
      localNoSuchFieldException.printStackTrace();
    }
  }

  public static boolean isInNativeAlloc()
  {
    return (Build.VERSION.SDK_INT < 11) || ((nativeLibLoaded) && (nativeIntField != null));
  }

  public static void loadLibs()
  {
    if ((!DeviceUtils.isRealARMArch()) && (!DeviceUtils.isRealX86Arch()))
      nativeLibLoaded = false;
    while (nativeLibLoaded)
      return;
    try
    {
      if (Build.VERSION.SDK_INT >= 11)
        System.loadLibrary("ndkbitmap");
      for (nativeLibLoaded = true; ; nativeLibLoaded = false)
      {
        if (nativeLibLoaded)
        {
          if (init())
            break;
          release();
          nativeLibLoaded = false;
        }
        Log.e("NativeBitmapFactory", "loaded" + nativeLibLoaded);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        nativeLibLoaded = false;
      }
    }
    catch (Error localError)
    {
      while (true)
      {
        localError.printStackTrace();
        nativeLibLoaded = false;
        continue;
        initField();
        if (!testLib())
        {
          release();
          nativeLibLoaded = false;
        }
      }
    }
  }

  public static void recycle(Bitmap paramBitmap)
  {
    paramBitmap.recycle();
  }

  private static native boolean release();

  public static void releaseLibs()
  {
    if (nativeLibLoaded)
      release();
    nativeIntField = null;
    nativeLibLoaded = false;
  }

  // ERROR //
  @android.annotation.SuppressLint({"NewApi"})
  private static boolean testLib()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: getstatic 12	tv/cjump/jni/NativeBitmapFactory:nativeIntField	Ljava/lang/reflect/Field;
    //   5: ifnonnull +9 -> 14
    //   8: iconst_0
    //   9: istore 4
    //   11: iload 4
    //   13: ireturn
    //   14: aconst_null
    //   15: astore_0
    //   16: iconst_2
    //   17: iconst_2
    //   18: getstatic 27	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   21: iconst_1
    //   22: invokestatic 41	tv/cjump/jni/NativeBitmapFactory:createNativeBitmap	(IILandroid/graphics/Bitmap$Config;Z)Landroid/graphics/Bitmap;
    //   25: astore_1
    //   26: aload_1
    //   27: astore_0
    //   28: aload_0
    //   29: ifnull +124 -> 153
    //   32: aload_0
    //   33: invokevirtual 158	android/graphics/Bitmap:getWidth	()I
    //   36: iconst_2
    //   37: if_icmpne +116 -> 153
    //   40: aload_0
    //   41: invokevirtual 161	android/graphics/Bitmap:getHeight	()I
    //   44: iconst_2
    //   45: if_icmpne +108 -> 153
    //   48: iload_3
    //   49: ifeq +190 -> 239
    //   52: getstatic 52	android/os/Build$VERSION:SDK_INT	I
    //   55: bipush 17
    //   57: if_icmplt +15 -> 72
    //   60: aload_0
    //   61: invokevirtual 164	android/graphics/Bitmap:isPremultiplied	()Z
    //   64: ifne +8 -> 72
    //   67: aload_0
    //   68: iconst_1
    //   69: invokevirtual 167	android/graphics/Bitmap:setPremultiplied	(Z)V
    //   72: new 169	android/graphics/Canvas
    //   75: dup
    //   76: aload_0
    //   77: invokespecial 171	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   80: astore_1
    //   81: new 173	android/graphics/Paint
    //   84: dup
    //   85: invokespecial 174	android/graphics/Paint:<init>	()V
    //   88: astore_2
    //   89: aload_2
    //   90: ldc 175
    //   92: invokevirtual 179	android/graphics/Paint:setColor	(I)V
    //   95: aload_2
    //   96: ldc 180
    //   98: invokevirtual 184	android/graphics/Paint:setTextSize	(F)V
    //   101: aload_1
    //   102: fconst_0
    //   103: fconst_0
    //   104: aload_0
    //   105: invokevirtual 158	android/graphics/Bitmap:getWidth	()I
    //   108: i2f
    //   109: aload_0
    //   110: invokevirtual 161	android/graphics/Bitmap:getHeight	()I
    //   113: i2f
    //   114: aload_2
    //   115: invokevirtual 188	android/graphics/Canvas:drawRect	(FFFFLandroid/graphics/Paint;)V
    //   118: aload_1
    //   119: ldc 190
    //   121: fconst_0
    //   122: fconst_0
    //   123: aload_2
    //   124: invokevirtual 194	android/graphics/Canvas:drawText	(Ljava/lang/String;FFLandroid/graphics/Paint;)V
    //   127: getstatic 52	android/os/Build$VERSION:SDK_INT	I
    //   130: bipush 17
    //   132: if_icmplt +107 -> 239
    //   135: aload_0
    //   136: invokevirtual 164	android/graphics/Bitmap:isPremultiplied	()Z
    //   139: istore_3
    //   140: iload_3
    //   141: istore 4
    //   143: aload_0
    //   144: ifnull -133 -> 11
    //   147: aload_0
    //   148: invokevirtual 150	android/graphics/Bitmap:recycle	()V
    //   151: iload_3
    //   152: ireturn
    //   153: iconst_0
    //   154: istore_3
    //   155: goto -107 -> 48
    //   158: astore_1
    //   159: ldc 117
    //   161: new 119	java/lang/StringBuilder
    //   164: dup
    //   165: invokespecial 120	java/lang/StringBuilder:<init>	()V
    //   168: ldc 196
    //   170: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: aload_1
    //   174: invokevirtual 197	java/lang/Exception:toString	()Ljava/lang/String;
    //   177: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 139	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: aload_0
    //   188: ifnull +7 -> 195
    //   191: aload_0
    //   192: invokevirtual 150	android/graphics/Bitmap:recycle	()V
    //   195: iconst_0
    //   196: ireturn
    //   197: astore_0
    //   198: aconst_null
    //   199: astore_0
    //   200: aload_0
    //   201: ifnull +7 -> 208
    //   204: aload_0
    //   205: invokevirtual 150	android/graphics/Bitmap:recycle	()V
    //   208: iconst_0
    //   209: ireturn
    //   210: astore_1
    //   211: aconst_null
    //   212: astore_0
    //   213: aload_0
    //   214: ifnull +7 -> 221
    //   217: aload_0
    //   218: invokevirtual 150	android/graphics/Bitmap:recycle	()V
    //   221: aload_1
    //   222: athrow
    //   223: astore_1
    //   224: goto -11 -> 213
    //   227: astore_1
    //   228: goto -15 -> 213
    //   231: astore_1
    //   232: goto -32 -> 200
    //   235: astore_1
    //   236: goto -77 -> 159
    //   239: goto -99 -> 140
    //
    // Exception table:
    //   from	to	target	type
    //   16	26	158	java/lang/Exception
    //   16	26	197	java/lang/Error
    //   16	26	210	finally
    //   32	48	223	finally
    //   52	72	223	finally
    //   72	140	223	finally
    //   159	187	227	finally
    //   32	48	231	java/lang/Error
    //   52	72	231	java/lang/Error
    //   72	140	231	java/lang/Error
    //   32	48	235	java/lang/Exception
    //   52	72	235	java/lang/Exception
    //   72	140	235	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     tv.cjump.jni.NativeBitmapFactory
 * JD-Core Version:    0.6.2
 */