package com.taobao.newxp.imagecache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class b extends c
{
  private static final String e = "ImageFetcher";
  private static final int f = 10485760;
  private static final String g = "http";
  private static final int h = 8192;
  private static final int m = 0;
  private a i;
  private File j;
  private boolean k = true;
  private final Object l = new Object();

  public b(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  public b(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, paramInt1, paramInt2);
    a(paramContext);
  }

  // ERROR //
  private Bitmap a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: invokestatic 60	com/taobao/newxp/imagecache/utils/ImageCache:c	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 39	com/taobao/newxp/imagecache/utils/b:l	Ljava/lang/Object;
    //   13: astore 6
    //   15: aload 6
    //   17: monitorenter
    //   18: aload_0
    //   19: getfield 32	com/taobao/newxp/imagecache/utils/b:k	Z
    //   22: istore 7
    //   24: iload 7
    //   26: ifeq +17 -> 43
    //   29: aload_0
    //   30: getfield 39	com/taobao/newxp/imagecache/utils/b:l	Ljava/lang/Object;
    //   33: invokevirtual 63	java/lang/Object:wait	()V
    //   36: goto -18 -> 18
    //   39: astore_2
    //   40: goto -22 -> 18
    //   43: aload_0
    //   44: getfield 65	com/taobao/newxp/imagecache/utils/b:i	Lcom/taobao/newxp/imagecache/utils/a;
    //   47: astore_2
    //   48: aload_2
    //   49: ifnull +342 -> 391
    //   52: aload_0
    //   53: getfield 65	com/taobao/newxp/imagecache/utils/b:i	Lcom/taobao/newxp/imagecache/utils/a;
    //   56: aload 4
    //   58: invokevirtual 70	com/taobao/newxp/imagecache/utils/a:a	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$c;
    //   61: astore_3
    //   62: aload_3
    //   63: astore_2
    //   64: aload_3
    //   65: ifnonnull +44 -> 109
    //   68: aload_0
    //   69: getfield 65	com/taobao/newxp/imagecache/utils/b:i	Lcom/taobao/newxp/imagecache/utils/a;
    //   72: aload 4
    //   74: invokevirtual 74	com/taobao/newxp/imagecache/utils/a:b	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$a;
    //   77: astore_2
    //   78: aload_2
    //   79: ifnull +20 -> 99
    //   82: aload_0
    //   83: aload_1
    //   84: aload_2
    //   85: iconst_0
    //   86: invokevirtual 79	com/taobao/newxp/imagecache/utils/a$a:c	(I)Ljava/io/OutputStream;
    //   89: invokevirtual 82	com/taobao/newxp/imagecache/utils/b:a	(Ljava/lang/String;Ljava/io/OutputStream;)Z
    //   92: ifeq +104 -> 196
    //   95: aload_2
    //   96: invokevirtual 84	com/taobao/newxp/imagecache/utils/a$a:a	()V
    //   99: aload_0
    //   100: getfield 65	com/taobao/newxp/imagecache/utils/b:i	Lcom/taobao/newxp/imagecache/utils/a;
    //   103: aload 4
    //   105: invokevirtual 70	com/taobao/newxp/imagecache/utils/a:a	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$c;
    //   108: astore_2
    //   109: aload_2
    //   110: ifnull +273 -> 383
    //   113: aload_2
    //   114: iconst_0
    //   115: invokevirtual 89	com/taobao/newxp/imagecache/utils/a$c:a	(I)Ljava/io/InputStream;
    //   118: checkcast 91	java/io/FileInputStream
    //   121: astore_1
    //   122: aload_1
    //   123: invokevirtual 95	java/io/FileInputStream:getFD	()Ljava/io/FileDescriptor;
    //   126: astore_3
    //   127: aload_1
    //   128: astore 4
    //   130: aload_3
    //   131: astore_1
    //   132: aload 4
    //   134: astore_2
    //   135: aload_3
    //   136: ifnonnull +23 -> 159
    //   139: aload_3
    //   140: astore_1
    //   141: aload 4
    //   143: astore_2
    //   144: aload 4
    //   146: ifnull +13 -> 159
    //   149: aload 4
    //   151: invokevirtual 98	java/io/FileInputStream:close	()V
    //   154: aload 4
    //   156: astore_2
    //   157: aload_3
    //   158: astore_1
    //   159: aload 6
    //   161: monitorexit
    //   162: aload 5
    //   164: astore_3
    //   165: aload_1
    //   166: ifnull +20 -> 186
    //   169: aload_1
    //   170: aload_0
    //   171: getfield 100	com/taobao/newxp/imagecache/utils/b:a	I
    //   174: aload_0
    //   175: getfield 102	com/taobao/newxp/imagecache/utils/b:b	I
    //   178: aload_0
    //   179: invokevirtual 105	com/taobao/newxp/imagecache/utils/b:f	()Lcom/taobao/newxp/imagecache/utils/ImageCache;
    //   182: invokestatic 108	com/taobao/newxp/imagecache/utils/b:a	(Ljava/io/FileDescriptor;IILcom/taobao/newxp/imagecache/utils/ImageCache;)Landroid/graphics/Bitmap;
    //   185: astore_3
    //   186: aload_2
    //   187: ifnull +7 -> 194
    //   190: aload_2
    //   191: invokevirtual 98	java/io/FileInputStream:close	()V
    //   194: aload_3
    //   195: areturn
    //   196: aload_2
    //   197: invokevirtual 110	com/taobao/newxp/imagecache/utils/a$a:b	()V
    //   200: goto -101 -> 99
    //   203: astore_3
    //   204: aconst_null
    //   205: astore_2
    //   206: aload_2
    //   207: astore_1
    //   208: ldc 8
    //   210: new 112	java/lang/StringBuilder
    //   213: dup
    //   214: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   217: ldc 115
    //   219: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: aload_3
    //   223: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 131	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   232: pop
    //   233: aload_2
    //   234: astore_3
    //   235: iconst_0
    //   236: ifne +140 -> 376
    //   239: aload_2
    //   240: astore_3
    //   241: aload_2
    //   242: ifnull +134 -> 376
    //   245: aload_2
    //   246: invokevirtual 98	java/io/FileInputStream:close	()V
    //   249: aconst_null
    //   250: astore_1
    //   251: goto -92 -> 159
    //   254: astore_1
    //   255: aconst_null
    //   256: astore_1
    //   257: goto -98 -> 159
    //   260: astore_3
    //   261: aconst_null
    //   262: astore_2
    //   263: aload_2
    //   264: astore_1
    //   265: ldc 8
    //   267: new 112	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   274: ldc 115
    //   276: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: aload_3
    //   280: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   283: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   286: invokestatic 131	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   289: pop
    //   290: aload_2
    //   291: astore_3
    //   292: iconst_0
    //   293: ifne +83 -> 376
    //   296: aload_2
    //   297: astore_3
    //   298: aload_2
    //   299: ifnull +77 -> 376
    //   302: aload_2
    //   303: invokevirtual 98	java/io/FileInputStream:close	()V
    //   306: aconst_null
    //   307: astore_1
    //   308: goto -149 -> 159
    //   311: astore_1
    //   312: aconst_null
    //   313: astore_1
    //   314: goto -155 -> 159
    //   317: astore_2
    //   318: aconst_null
    //   319: astore_1
    //   320: iconst_0
    //   321: ifne +11 -> 332
    //   324: aload_1
    //   325: ifnull +7 -> 332
    //   328: aload_1
    //   329: invokevirtual 98	java/io/FileInputStream:close	()V
    //   332: aload_2
    //   333: athrow
    //   334: astore_1
    //   335: aload 6
    //   337: monitorexit
    //   338: aload_1
    //   339: athrow
    //   340: astore_1
    //   341: aload_3
    //   342: astore_1
    //   343: aload 4
    //   345: astore_2
    //   346: goto -187 -> 159
    //   349: astore_1
    //   350: goto -18 -> 332
    //   353: astore_1
    //   354: aload_3
    //   355: areturn
    //   356: astore_2
    //   357: goto -37 -> 320
    //   360: astore_2
    //   361: goto -41 -> 320
    //   364: astore_3
    //   365: aload_1
    //   366: astore_2
    //   367: goto -104 -> 263
    //   370: astore_3
    //   371: aload_1
    //   372: astore_2
    //   373: goto -167 -> 206
    //   376: aconst_null
    //   377: astore_1
    //   378: aload_3
    //   379: astore_2
    //   380: goto -221 -> 159
    //   383: aconst_null
    //   384: astore 4
    //   386: aconst_null
    //   387: astore_3
    //   388: goto -258 -> 130
    //   391: aconst_null
    //   392: astore_2
    //   393: aconst_null
    //   394: astore_1
    //   395: goto -236 -> 159
    //
    // Exception table:
    //   from	to	target	type
    //   29	36	39	java/lang/InterruptedException
    //   52	62	203	java/io/IOException
    //   68	78	203	java/io/IOException
    //   82	99	203	java/io/IOException
    //   99	109	203	java/io/IOException
    //   113	122	203	java/io/IOException
    //   196	200	203	java/io/IOException
    //   245	249	254	java/io/IOException
    //   52	62	260	java/lang/IllegalStateException
    //   68	78	260	java/lang/IllegalStateException
    //   82	99	260	java/lang/IllegalStateException
    //   99	109	260	java/lang/IllegalStateException
    //   113	122	260	java/lang/IllegalStateException
    //   196	200	260	java/lang/IllegalStateException
    //   302	306	311	java/io/IOException
    //   52	62	317	finally
    //   68	78	317	finally
    //   82	99	317	finally
    //   99	109	317	finally
    //   113	122	317	finally
    //   196	200	317	finally
    //   18	24	334	finally
    //   29	36	334	finally
    //   43	48	334	finally
    //   149	154	334	finally
    //   159	162	334	finally
    //   245	249	334	finally
    //   302	306	334	finally
    //   328	332	334	finally
    //   332	334	334	finally
    //   335	338	334	finally
    //   149	154	340	java/io/IOException
    //   328	332	349	java/io/IOException
    //   190	194	353	java/io/IOException
    //   122	127	356	finally
    //   208	233	360	finally
    //   265	290	360	finally
    //   122	127	364	java/lang/IllegalStateException
    //   122	127	370	java/io/IOException
  }

  private void a(Context paramContext)
  {
    b(paramContext);
    this.j = ImageCache.a(paramContext, "http");
  }

  private void b(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnectedOrConnecting()))
    {
      Toast.makeText(paramContext, "网络连接失败", 1).show();
      Log.e("ImageFetcher", "checkConnection - no connection found");
    }
  }

  public static void e()
  {
    if (Build.VERSION.SDK_INT < 8)
      System.setProperty("http.keepAlive", "false");
  }

  private void j()
  {
    if (!this.j.exists())
      this.j.mkdirs();
    synchronized (this.l)
    {
      long l1 = ImageCache.a(this.j);
      if (l1 > 10485760L);
      try
      {
        this.i = a.a(this.j, 1, 1, 10485760L);
        this.k = false;
        this.l.notifyAll();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.i = null;
      }
    }
  }

  protected Bitmap a(Object paramObject)
  {
    return a(String.valueOf(paramObject));
  }

  protected void a()
  {
    super.a();
    j();
  }

  // ERROR //
  public boolean a(String paramString, java.io.OutputStream paramOutputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: invokestatic 219	com/taobao/newxp/imagecache/utils/b:e	()V
    //   9: new 221	java/net/URL
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 224	java/net/URL:<init>	(Ljava/lang/String;)V
    //   17: invokevirtual 228	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   20: checkcast 230	java/net/HttpURLConnection
    //   23: astore_1
    //   24: new 232	java/io/BufferedInputStream
    //   27: dup
    //   28: aload_1
    //   29: invokevirtual 236	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   32: sipush 8192
    //   35: invokespecial 239	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   38: astore_3
    //   39: new 241	java/io/BufferedOutputStream
    //   42: dup
    //   43: aload_2
    //   44: sipush 8192
    //   47: invokespecial 244	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   50: astore 4
    //   52: aload_3
    //   53: invokevirtual 248	java/io/BufferedInputStream:read	()I
    //   56: istore 7
    //   58: iload 7
    //   60: iconst_m1
    //   61: if_icmpeq +81 -> 142
    //   64: aload 4
    //   66: iload 7
    //   68: invokevirtual 252	java/io/BufferedOutputStream:write	(I)V
    //   71: goto -19 -> 52
    //   74: astore 5
    //   76: aload_1
    //   77: astore_2
    //   78: aload 5
    //   80: astore_1
    //   81: aload_3
    //   82: astore 5
    //   84: aload_2
    //   85: astore_3
    //   86: aload 5
    //   88: astore_2
    //   89: ldc 8
    //   91: new 112	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   98: ldc 254
    //   100: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload_1
    //   104: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   107: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokestatic 131	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload_3
    //   115: ifnull +7 -> 122
    //   118: aload_3
    //   119: invokevirtual 257	java/net/HttpURLConnection:disconnect	()V
    //   122: aload 4
    //   124: ifnull +8 -> 132
    //   127: aload 4
    //   129: invokevirtual 258	java/io/BufferedOutputStream:close	()V
    //   132: aload_2
    //   133: ifnull +7 -> 140
    //   136: aload_2
    //   137: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   140: iconst_0
    //   141: ireturn
    //   142: aload_1
    //   143: ifnull +7 -> 150
    //   146: aload_1
    //   147: invokevirtual 257	java/net/HttpURLConnection:disconnect	()V
    //   150: aload 4
    //   152: ifnull +8 -> 160
    //   155: aload 4
    //   157: invokevirtual 258	java/io/BufferedOutputStream:close	()V
    //   160: aload_3
    //   161: ifnull +7 -> 168
    //   164: aload_3
    //   165: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   168: iconst_1
    //   169: ireturn
    //   170: astore_1
    //   171: aconst_null
    //   172: astore_2
    //   173: aconst_null
    //   174: astore_3
    //   175: aload 6
    //   177: astore 4
    //   179: aload_3
    //   180: ifnull +7 -> 187
    //   183: aload_3
    //   184: invokevirtual 257	java/net/HttpURLConnection:disconnect	()V
    //   187: aload 4
    //   189: ifnull +8 -> 197
    //   192: aload 4
    //   194: invokevirtual 258	java/io/BufferedOutputStream:close	()V
    //   197: aload_2
    //   198: ifnull +7 -> 205
    //   201: aload_2
    //   202: invokevirtual 259	java/io/BufferedInputStream:close	()V
    //   205: aload_1
    //   206: athrow
    //   207: astore_2
    //   208: goto -3 -> 205
    //   211: astore_2
    //   212: aload_1
    //   213: astore_3
    //   214: aload_2
    //   215: astore_1
    //   216: aconst_null
    //   217: astore_2
    //   218: aload 6
    //   220: astore 4
    //   222: goto -43 -> 179
    //   225: astore 4
    //   227: aload_3
    //   228: astore_2
    //   229: aload_1
    //   230: astore_3
    //   231: aload 4
    //   233: astore_1
    //   234: aload 6
    //   236: astore 4
    //   238: goto -59 -> 179
    //   241: astore_2
    //   242: aload_1
    //   243: astore 5
    //   245: aload_2
    //   246: astore_1
    //   247: aload_3
    //   248: astore_2
    //   249: aload 5
    //   251: astore_3
    //   252: goto -73 -> 179
    //   255: astore_1
    //   256: goto -77 -> 179
    //   259: astore_1
    //   260: goto -120 -> 140
    //   263: astore_1
    //   264: aconst_null
    //   265: astore_2
    //   266: aconst_null
    //   267: astore_3
    //   268: aload 5
    //   270: astore 4
    //   272: goto -183 -> 89
    //   275: astore_2
    //   276: aload_1
    //   277: astore_3
    //   278: aload_2
    //   279: astore_1
    //   280: aconst_null
    //   281: astore_2
    //   282: aload 5
    //   284: astore 4
    //   286: goto -197 -> 89
    //   289: astore 4
    //   291: aload_3
    //   292: astore_2
    //   293: aload_1
    //   294: astore_3
    //   295: aload 4
    //   297: astore_1
    //   298: aload 5
    //   300: astore 4
    //   302: goto -213 -> 89
    //   305: astore_1
    //   306: goto -138 -> 168
    //
    // Exception table:
    //   from	to	target	type
    //   52	58	74	java/io/IOException
    //   64	71	74	java/io/IOException
    //   9	24	170	finally
    //   192	197	207	java/io/IOException
    //   201	205	207	java/io/IOException
    //   24	39	211	finally
    //   39	52	225	finally
    //   52	58	241	finally
    //   64	71	241	finally
    //   89	114	255	finally
    //   127	132	259	java/io/IOException
    //   136	140	259	java/io/IOException
    //   9	24	263	java/io/IOException
    //   24	39	275	java/io/IOException
    //   39	52	289	java/io/IOException
    //   155	160	305	java/io/IOException
    //   164	168	305	java/io/IOException
  }

  protected void b()
  {
    super.b();
    synchronized (this.l)
    {
      if (this.i != null)
      {
        boolean bool = this.i.d();
        if (bool);
      }
      try
      {
        this.i.f();
        this.i = null;
        this.k = true;
        j();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageFetcher", "clearCacheInternal - " + localIOException);
      }
    }
  }

  protected void c()
  {
    super.c();
    synchronized (this.l)
    {
      a locala = this.i;
      if (locala != null);
      try
      {
        this.i.e();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageFetcher", "flush - " + localIOException);
      }
    }
  }

  protected void d()
  {
    super.d();
    synchronized (this.l)
    {
      a locala = this.i;
      if (locala != null);
      try
      {
        if (!this.i.d())
        {
          this.i.close();
          this.i = null;
        }
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageFetcher", "closeCacheInternal - " + localIOException);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.b
 * JD-Core Version:    0.6.2
 */