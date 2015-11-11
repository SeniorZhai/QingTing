package fm.qingting.utils;

import fm.qingting.framework.view.IView;

public class ViewCaptureUtil
{
  private static final int STANDAR_WIDTH = 320;
  private static IView screenView;
  private static String viewPath;

  // ERROR //
  public static void captureViewPath()
  {
    // Byte code:
    //   0: getstatic 25	fm/qingting/utils/ViewCaptureUtil:screenView	Lfm/qingting/framework/view/IView;
    //   3: ifnonnull +4 -> 7
    //   6: return
    //   7: aconst_null
    //   8: putstatic 27	fm/qingting/utils/ViewCaptureUtil:viewPath	Ljava/lang/String;
    //   11: getstatic 25	fm/qingting/utils/ViewCaptureUtil:screenView	Lfm/qingting/framework/view/IView;
    //   14: invokeinterface 33 1 0
    //   19: invokevirtual 39	android/view/View:getMeasuredWidth	()I
    //   22: istore 4
    //   24: getstatic 25	fm/qingting/utils/ViewCaptureUtil:screenView	Lfm/qingting/framework/view/IView;
    //   27: invokeinterface 33 1 0
    //   32: invokevirtual 42	android/view/View:getMeasuredHeight	()I
    //   35: istore 5
    //   37: iload 4
    //   39: ifle -33 -> 6
    //   42: iload 5
    //   44: ifle -38 -> 6
    //   47: ldc 43
    //   49: iload 4
    //   51: i2f
    //   52: fdiv
    //   53: fstore_0
    //   54: iload 4
    //   56: i2f
    //   57: fload_0
    //   58: fmul
    //   59: f2i
    //   60: iload 5
    //   62: i2f
    //   63: fload_0
    //   64: fmul
    //   65: f2i
    //   66: iconst_0
    //   67: iadd
    //   68: getstatic 49	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   71: invokestatic 55	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   74: astore_1
    //   75: new 57	android/graphics/Canvas
    //   78: dup
    //   79: aload_1
    //   80: invokespecial 60	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   83: astore_2
    //   84: aload_2
    //   85: invokevirtual 63	android/graphics/Canvas:save	()I
    //   88: istore 4
    //   90: aload_2
    //   91: fconst_0
    //   92: iconst_0
    //   93: i2f
    //   94: invokevirtual 67	android/graphics/Canvas:translate	(FF)V
    //   97: aload_2
    //   98: fload_0
    //   99: fload_0
    //   100: invokevirtual 70	android/graphics/Canvas:scale	(FF)V
    //   103: getstatic 25	fm/qingting/utils/ViewCaptureUtil:screenView	Lfm/qingting/framework/view/IView;
    //   106: invokeinterface 33 1 0
    //   111: aload_2
    //   112: invokevirtual 74	android/view/View:draw	(Landroid/graphics/Canvas;)V
    //   115: aload_2
    //   116: iload 4
    //   118: invokevirtual 78	android/graphics/Canvas:restoreToCount	(I)V
    //   121: aload_2
    //   122: aload_2
    //   123: invokevirtual 63	android/graphics/Canvas:save	()I
    //   126: invokevirtual 78	android/graphics/Canvas:restoreToCount	(I)V
    //   129: invokestatic 84	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   132: invokevirtual 88	fm/qingting/qtradio/model/InfoManager:getContext	()Landroid/content/Context;
    //   135: invokevirtual 93	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   138: invokevirtual 97	android/content/Context:getFilesDir	()Ljava/io/File;
    //   141: invokevirtual 103	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   144: astore_2
    //   145: new 99	java/io/File
    //   148: dup
    //   149: new 105	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   156: aload_2
    //   157: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: ldc 112
    //   162: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 115	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokespecial 118	java/io/File:<init>	(Ljava/lang/String;)V
    //   171: astore_3
    //   172: aload_3
    //   173: invokevirtual 122	java/io/File:createNewFile	()Z
    //   176: pop
    //   177: new 124	java/io/FileOutputStream
    //   180: dup
    //   181: aload_3
    //   182: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   185: astore_3
    //   186: aload_1
    //   187: getstatic 133	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   190: bipush 100
    //   192: aload_3
    //   193: invokevirtual 137	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   196: pop
    //   197: aload_3
    //   198: invokevirtual 140	java/io/FileOutputStream:flush	()V
    //   201: aload_3
    //   202: invokevirtual 143	java/io/FileOutputStream:close	()V
    //   205: new 105	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   212: aload_2
    //   213: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: ldc 112
    //   218: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 115	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: putstatic 27	fm/qingting/utils/ViewCaptureUtil:viewPath	Ljava/lang/String;
    //   227: return
    //   228: astore_1
    //   229: aload_1
    //   230: invokevirtual 146	java/io/IOException:printStackTrace	()V
    //   233: return
    //   234: astore_1
    //   235: aload_1
    //   236: invokevirtual 147	java/io/FileNotFoundException:printStackTrace	()V
    //   239: return
    //   240: astore_1
    //   241: aload_1
    //   242: invokevirtual 146	java/io/IOException:printStackTrace	()V
    //   245: return
    //   246: astore_1
    //   247: aload_1
    //   248: invokevirtual 146	java/io/IOException:printStackTrace	()V
    //   251: return
    //   252: astore_1
    //   253: return
    //
    // Exception table:
    //   from	to	target	type
    //   172	177	228	java/io/IOException
    //   177	186	234	java/io/FileNotFoundException
    //   197	201	240	java/io/IOException
    //   201	205	246	java/io/IOException
    //   7	37	252	java/lang/Exception
    //   47	172	252	java/lang/Exception
    //   172	177	252	java/lang/Exception
    //   177	186	252	java/lang/Exception
    //   186	197	252	java/lang/Exception
    //   197	201	252	java/lang/Exception
    //   201	205	252	java/lang/Exception
    //   205	227	252	java/lang/Exception
    //   229	233	252	java/lang/Exception
    //   235	239	252	java/lang/Exception
    //   241	245	252	java/lang/Exception
    //   247	251	252	java/lang/Exception
  }

  public static String getViewPath()
  {
    return viewPath;
  }

  public static void setScreenView(IView paramIView)
  {
    screenView = paramIView;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ViewCaptureUtil
 * JD-Core Version:    0.6.2
 */