package com.taobao.newxp.imagecache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.util.Log;
import com.taobao.newxp.view.widget.RecyclingBitmapDrawable;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;

public class ImageCache
{
  static ImageCache a;
  private static final String b = "ImageCache";
  private static final int c = 5120;
  private static final int d = 10485760;
  private static final Bitmap.CompressFormat e = Bitmap.CompressFormat.PNG;
  private static final int f = 70;
  private static final int g = 0;
  private static final boolean h = true;
  private static final boolean i = true;
  private static final boolean j = false;
  private a k;
  private LruCache<String, BitmapDrawable> l;
  private a m;
  private final Object n = new Object();
  private boolean o = true;
  private HashSet<SoftReference<Bitmap>> p;

  private ImageCache(a parama)
  {
    b(parama);
  }

  public static int a(BitmapDrawable paramBitmapDrawable)
  {
    paramBitmapDrawable = paramBitmapDrawable.getBitmap();
    if (e.e())
      return paramBitmapDrawable.getByteCount();
    int i1 = paramBitmapDrawable.getRowBytes();
    return paramBitmapDrawable.getHeight() * i1;
  }

  public static long a(File paramFile)
  {
    if (e.c())
      return paramFile.getUsableSpace();
    paramFile = new StatFs(paramFile.getPath());
    return paramFile.getBlockSize() * paramFile.getAvailableBlocks();
  }

  private static RetainFragment a(FragmentManager paramFragmentManager)
  {
    RetainFragment localRetainFragment2 = (RetainFragment)paramFragmentManager.findFragmentByTag("ImageCache");
    RetainFragment localRetainFragment1 = localRetainFragment2;
    if (localRetainFragment2 == null)
    {
      localRetainFragment1 = new RetainFragment();
      paramFragmentManager.beginTransaction().add(localRetainFragment1, "ImageCache").commitAllowingStateLoss();
    }
    return localRetainFragment1;
  }

  public static ImageCache a(FragmentManager paramFragmentManager, a parama)
  {
    RetainFragment localRetainFragment = a(paramFragmentManager);
    ImageCache localImageCache = (ImageCache)localRetainFragment.a();
    paramFragmentManager = localImageCache;
    if (localImageCache == null)
    {
      paramFragmentManager = new ImageCache(parama);
      localRetainFragment.a(paramFragmentManager);
    }
    return paramFragmentManager;
  }

  public static ImageCache a(a parama)
  {
    if (a == null)
      a = new ImageCache(parama);
    return a;
  }

  public static File a(Context paramContext)
  {
    if (e.b())
      return paramContext.getExternalCacheDir();
    paramContext = "/Android/data/" + paramContext.getPackageName() + "/cache/";
    return new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
  }

  public static File a(Context paramContext, String paramString)
  {
    try
    {
      if (("mounted".equals(Environment.getExternalStorageState())) || (!e()));
      for (String str = a(paramContext).getPath(); ; str = paramContext.getCacheDir().getPath())
        return new File(str + File.separator + paramString);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new File(paramContext.getCacheDir().getPath() + File.separator + paramString);
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = 0;
    while (i1 < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i1] & 0xFF);
      if (str.length() == 1)
        localStringBuilder.append('0');
      localStringBuilder.append(str);
      i1 += 1;
    }
    return localStringBuilder.toString();
  }

  private static boolean a(Bitmap paramBitmap, BitmapFactory.Options paramOptions)
  {
    int i1 = paramOptions.outWidth / paramOptions.inSampleSize;
    int i2 = paramOptions.outHeight / paramOptions.inSampleSize;
    return (paramBitmap.getWidth() == i1) && (paramBitmap.getHeight() == i2);
  }

  private void b(a parama)
  {
    this.m = parama;
    if (this.m.f)
    {
      if (e.d())
        this.p = new HashSet();
      this.l = new LruCache(this.m.a)
      {
        protected int a(String paramAnonymousString, BitmapDrawable paramAnonymousBitmapDrawable)
        {
          int j = ImageCache.a(paramAnonymousBitmapDrawable) / 1024;
          int i = j;
          if (j == 0)
            i = 1;
          return i;
        }

        protected void a(boolean paramAnonymousBoolean, String paramAnonymousString, BitmapDrawable paramAnonymousBitmapDrawable1, BitmapDrawable paramAnonymousBitmapDrawable2)
        {
          if (RecyclingBitmapDrawable.class.isInstance(paramAnonymousBitmapDrawable1))
            ((RecyclingBitmapDrawable)paramAnonymousBitmapDrawable1).setIsCached(false);
          while (!e.d())
            return;
          ImageCache.a(ImageCache.this).add(new SoftReference(paramAnonymousBitmapDrawable1.getBitmap()));
        }
      };
    }
    if (parama.h)
      a();
  }

  public static String c(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      localObject = a(((MessageDigest)localObject).digest());
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    return String.valueOf(paramString.hashCode());
  }

  public static boolean e()
  {
    if (e.c())
      return Environment.isExternalStorageRemovable();
    return true;
  }

  protected Bitmap a(BitmapFactory.Options paramOptions)
  {
    if ((this.p != null) && (!this.p.isEmpty()))
    {
      Iterator localIterator = this.p.iterator();
      while (localIterator.hasNext())
      {
        Bitmap localBitmap = (Bitmap)((SoftReference)localIterator.next()).get();
        if ((localBitmap != null) && (localBitmap.isMutable()))
        {
          if (a(localBitmap, paramOptions))
          {
            localIterator.remove();
            return localBitmap;
          }
        }
        else
          localIterator.remove();
      }
    }
    return null;
  }

  public BitmapDrawable a(String paramString)
  {
    BitmapDrawable localBitmapDrawable = null;
    if (this.l != null)
      localBitmapDrawable = (BitmapDrawable)this.l.get(paramString);
    return localBitmapDrawable;
  }

  public void a()
  {
    synchronized (this.n)
    {
      File localFile;
      if ((this.k == null) || (this.k.d()))
      {
        localFile = this.m.c;
        if ((this.m.g) && (localFile != null))
        {
          if (!localFile.exists())
            localFile.mkdirs();
          long l1 = a(localFile);
          int i1 = this.m.b;
          if (l1 <= i1);
        }
      }
      try
      {
        this.k = a.a(localFile, 1, 1, this.m.b);
        this.o = false;
        this.n.notifyAll();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          this.m.c = null;
          Log.e("ImageCache", "initDiskCache - " + localIOException);
        }
      }
    }
  }

  // ERROR //
  public void a(String paramString, BitmapDrawable paramBitmapDrawable)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +7 -> 8
    //   4: aload_2
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: aload_0
    //   10: getfield 254	com/taobao/newxp/imagecache/utils/ImageCache:l	Landroid/support/v4/util/LruCache;
    //   13: ifnull +31 -> 44
    //   16: ldc_w 368
    //   19: aload_2
    //   20: invokevirtual 373	java/lang/Class:isInstance	(Ljava/lang/Object;)Z
    //   23: ifeq +11 -> 34
    //   26: aload_2
    //   27: checkcast 368	com/taobao/newxp/view/widget/RecyclingBitmapDrawable
    //   30: iconst_1
    //   31: invokevirtual 377	com/taobao/newxp/view/widget/RecyclingBitmapDrawable:setIsCached	(Z)V
    //   34: aload_0
    //   35: getfield 254	com/taobao/newxp/imagecache/utils/ImageCache:l	Landroid/support/v4/util/LruCache;
    //   38: aload_1
    //   39: aload_2
    //   40: invokevirtual 381	android/support/v4/util/LruCache:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   43: pop
    //   44: aload_0
    //   45: getfield 62	com/taobao/newxp/imagecache/utils/ImageCache:n	Ljava/lang/Object;
    //   48: astore 4
    //   50: aload 4
    //   52: monitorenter
    //   53: aload_0
    //   54: getfield 331	com/taobao/newxp/imagecache/utils/ImageCache:k	Lcom/taobao/newxp/imagecache/utils/a;
    //   57: ifnull +83 -> 140
    //   60: aload_1
    //   61: invokestatic 383	com/taobao/newxp/imagecache/utils/ImageCache:c	(Ljava/lang/String;)Ljava/lang/String;
    //   64: astore_3
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_0
    //   68: getfield 331	com/taobao/newxp/imagecache/utils/ImageCache:k	Lcom/taobao/newxp/imagecache/utils/a;
    //   71: aload_3
    //   72: invokevirtual 386	com/taobao/newxp/imagecache/utils/a:a	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$c;
    //   75: astore 5
    //   77: aload 5
    //   79: ifnonnull +71 -> 150
    //   82: aload_0
    //   83: getfield 331	com/taobao/newxp/imagecache/utils/ImageCache:k	Lcom/taobao/newxp/imagecache/utils/a;
    //   86: aload_3
    //   87: invokevirtual 389	com/taobao/newxp/imagecache/utils/a:b	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$a;
    //   90: astore_3
    //   91: aload_3
    //   92: ifnull +40 -> 132
    //   95: aload_3
    //   96: iconst_0
    //   97: invokevirtual 394	com/taobao/newxp/imagecache/utils/a$a:c	(I)Ljava/io/OutputStream;
    //   100: astore_1
    //   101: aload_2
    //   102: invokevirtual 73	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   105: aload_0
    //   106: getfield 240	com/taobao/newxp/imagecache/utils/ImageCache:m	Lcom/taobao/newxp/imagecache/utils/ImageCache$a;
    //   109: getfield 396	com/taobao/newxp/imagecache/utils/ImageCache$a:d	Landroid/graphics/Bitmap$CompressFormat;
    //   112: aload_0
    //   113: getfield 240	com/taobao/newxp/imagecache/utils/ImageCache:m	Lcom/taobao/newxp/imagecache/utils/ImageCache$a;
    //   116: getfield 398	com/taobao/newxp/imagecache/utils/ImageCache$a:e	I
    //   119: aload_1
    //   120: invokevirtual 402	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   123: pop
    //   124: aload_3
    //   125: invokevirtual 403	com/taobao/newxp/imagecache/utils/a$a:a	()V
    //   128: aload_1
    //   129: invokevirtual 408	java/io/OutputStream:close	()V
    //   132: aload_1
    //   133: ifnull +7 -> 140
    //   136: aload_1
    //   137: invokevirtual 408	java/io/OutputStream:close	()V
    //   140: aload 4
    //   142: monitorexit
    //   143: return
    //   144: astore_1
    //   145: aload 4
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    //   150: aload 5
    //   152: iconst_0
    //   153: invokevirtual 413	com/taobao/newxp/imagecache/utils/a$c:a	(I)Ljava/io/InputStream;
    //   156: invokevirtual 416	java/io/InputStream:close	()V
    //   159: goto -27 -> 132
    //   162: astore_3
    //   163: aconst_null
    //   164: astore_2
    //   165: aload_2
    //   166: astore_1
    //   167: ldc 17
    //   169: new 160	java/lang/StringBuilder
    //   172: dup
    //   173: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   176: ldc_w 418
    //   179: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_3
    //   183: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokestatic 365	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   192: pop
    //   193: aload_2
    //   194: ifnull -54 -> 140
    //   197: aload_2
    //   198: invokevirtual 408	java/io/OutputStream:close	()V
    //   201: goto -61 -> 140
    //   204: astore_1
    //   205: goto -65 -> 140
    //   208: astore_3
    //   209: aconst_null
    //   210: astore_2
    //   211: aload_2
    //   212: astore_1
    //   213: ldc 17
    //   215: new 160	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   222: ldc_w 418
    //   225: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload_3
    //   229: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: invokestatic 365	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   238: pop
    //   239: aload_2
    //   240: ifnull -100 -> 140
    //   243: aload_2
    //   244: invokevirtual 408	java/io/OutputStream:close	()V
    //   247: goto -107 -> 140
    //   250: astore_1
    //   251: goto -111 -> 140
    //   254: astore_2
    //   255: aconst_null
    //   256: astore_1
    //   257: aload_1
    //   258: ifnull +7 -> 265
    //   261: aload_1
    //   262: invokevirtual 408	java/io/OutputStream:close	()V
    //   265: aload_2
    //   266: athrow
    //   267: astore_1
    //   268: goto -128 -> 140
    //   271: astore_1
    //   272: goto -7 -> 265
    //   275: astore_2
    //   276: goto -19 -> 257
    //   279: astore_2
    //   280: goto -23 -> 257
    //   283: astore_3
    //   284: aload_1
    //   285: astore_2
    //   286: goto -75 -> 211
    //   289: astore_3
    //   290: aload_1
    //   291: astore_2
    //   292: goto -127 -> 165
    //
    // Exception table:
    //   from	to	target	type
    //   53	65	144	finally
    //   136	140	144	finally
    //   140	143	144	finally
    //   145	148	144	finally
    //   197	201	144	finally
    //   243	247	144	finally
    //   261	265	144	finally
    //   265	267	144	finally
    //   67	77	162	java/io/IOException
    //   82	91	162	java/io/IOException
    //   95	101	162	java/io/IOException
    //   150	159	162	java/io/IOException
    //   197	201	204	java/io/IOException
    //   67	77	208	java/lang/Exception
    //   82	91	208	java/lang/Exception
    //   95	101	208	java/lang/Exception
    //   150	159	208	java/lang/Exception
    //   243	247	250	java/io/IOException
    //   67	77	254	finally
    //   82	91	254	finally
    //   95	101	254	finally
    //   150	159	254	finally
    //   136	140	267	java/io/IOException
    //   261	265	271	java/io/IOException
    //   101	132	275	finally
    //   167	193	279	finally
    //   213	239	279	finally
    //   101	132	283	java/lang/Exception
    //   101	132	289	java/io/IOException
  }

  // ERROR //
  public Bitmap b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_2
    //   5: aload_1
    //   6: invokestatic 383	com/taobao/newxp/imagecache/utils/ImageCache:c	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore_1
    //   10: aload_0
    //   11: getfield 62	com/taobao/newxp/imagecache/utils/ImageCache:n	Ljava/lang/Object;
    //   14: astore 6
    //   16: aload 6
    //   18: monitorenter
    //   19: aload_0
    //   20: getfield 64	com/taobao/newxp/imagecache/utils/ImageCache:o	Z
    //   23: istore 7
    //   25: iload 7
    //   27: ifeq +17 -> 44
    //   30: aload_0
    //   31: getfield 62	com/taobao/newxp/imagecache/utils/ImageCache:n	Ljava/lang/Object;
    //   34: invokevirtual 424	java/lang/Object:wait	()V
    //   37: goto -18 -> 19
    //   40: astore_3
    //   41: goto -22 -> 19
    //   44: aload_0
    //   45: getfield 331	com/taobao/newxp/imagecache/utils/ImageCache:k	Lcom/taobao/newxp/imagecache/utils/a;
    //   48: astore 4
    //   50: aload 5
    //   52: astore_3
    //   53: aload 4
    //   55: ifnull +68 -> 123
    //   58: aload_0
    //   59: getfield 331	com/taobao/newxp/imagecache/utils/ImageCache:k	Lcom/taobao/newxp/imagecache/utils/a;
    //   62: aload_1
    //   63: invokevirtual 386	com/taobao/newxp/imagecache/utils/a:a	(Ljava/lang/String;)Lcom/taobao/newxp/imagecache/utils/a$c;
    //   66: astore_1
    //   67: aload_1
    //   68: ifnull +152 -> 220
    //   71: aload_1
    //   72: iconst_0
    //   73: invokevirtual 413	com/taobao/newxp/imagecache/utils/a$c:a	(I)Ljava/io/InputStream;
    //   76: astore_1
    //   77: aload_1
    //   78: astore 4
    //   80: aload_1
    //   81: ifnull +28 -> 109
    //   84: aload_1
    //   85: astore_2
    //   86: aload_1
    //   87: checkcast 426	java/io/FileInputStream
    //   90: invokevirtual 430	java/io/FileInputStream:getFD	()Ljava/io/FileDescriptor;
    //   93: ldc_w 431
    //   96: ldc_w 431
    //   99: aload_0
    //   100: invokestatic 436	com/taobao/newxp/imagecache/utils/c:a	(Ljava/io/FileDescriptor;IILcom/taobao/newxp/imagecache/utils/ImageCache;)Landroid/graphics/Bitmap;
    //   103: astore_3
    //   104: aload_3
    //   105: astore_2
    //   106: aload_1
    //   107: astore 4
    //   109: aload_2
    //   110: astore_3
    //   111: aload 4
    //   113: ifnull +10 -> 123
    //   116: aload 4
    //   118: invokevirtual 416	java/io/InputStream:close	()V
    //   121: aload_2
    //   122: astore_3
    //   123: aload 6
    //   125: monitorexit
    //   126: aload_3
    //   127: areturn
    //   128: astore_3
    //   129: aconst_null
    //   130: astore_1
    //   131: aload_1
    //   132: astore_2
    //   133: ldc 17
    //   135: new 160	java/lang/StringBuilder
    //   138: dup
    //   139: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   142: ldc_w 438
    //   145: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload_3
    //   149: invokevirtual 360	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   152: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   155: invokestatic 365	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   158: pop
    //   159: aload 5
    //   161: astore_3
    //   162: aload_1
    //   163: ifnull -40 -> 123
    //   166: aload_1
    //   167: invokevirtual 416	java/io/InputStream:close	()V
    //   170: aload 5
    //   172: astore_3
    //   173: goto -50 -> 123
    //   176: astore_1
    //   177: aload 5
    //   179: astore_3
    //   180: goto -57 -> 123
    //   183: astore_1
    //   184: aconst_null
    //   185: astore_2
    //   186: aload_2
    //   187: ifnull +7 -> 194
    //   190: aload_2
    //   191: invokevirtual 416	java/io/InputStream:close	()V
    //   194: aload_1
    //   195: athrow
    //   196: astore_1
    //   197: aload 6
    //   199: monitorexit
    //   200: aload_1
    //   201: athrow
    //   202: astore_1
    //   203: aload_2
    //   204: astore_3
    //   205: goto -82 -> 123
    //   208: astore_2
    //   209: goto -15 -> 194
    //   212: astore_1
    //   213: goto -27 -> 186
    //   216: astore_3
    //   217: goto -86 -> 131
    //   220: aconst_null
    //   221: astore 4
    //   223: goto -114 -> 109
    //
    // Exception table:
    //   from	to	target	type
    //   30	37	40	java/lang/InterruptedException
    //   58	67	128	java/io/IOException
    //   71	77	128	java/io/IOException
    //   166	170	176	java/io/IOException
    //   58	67	183	finally
    //   71	77	183	finally
    //   19	25	196	finally
    //   30	37	196	finally
    //   44	50	196	finally
    //   116	121	196	finally
    //   123	126	196	finally
    //   166	170	196	finally
    //   190	194	196	finally
    //   194	196	196	finally
    //   197	200	196	finally
    //   116	121	202	java/io/IOException
    //   190	194	208	java/io/IOException
    //   86	104	212	finally
    //   133	159	212	finally
    //   86	104	216	java/io/IOException
  }

  public void b()
  {
    if (this.l != null)
      this.l.evictAll();
    synchronized (this.n)
    {
      this.o = true;
      if (this.k != null)
      {
        boolean bool = this.k.d();
        if (bool);
      }
      try
      {
        this.k.f();
        this.k = null;
        a();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageCache", "clearCache - " + localIOException);
      }
    }
  }

  public void c()
  {
    synchronized (this.n)
    {
      a locala = this.k;
      if (locala != null);
      try
      {
        this.k.e();
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageCache", "flush - " + localIOException);
      }
    }
  }

  public void d()
  {
    synchronized (this.n)
    {
      a locala = this.k;
      if (locala != null);
      try
      {
        if (!this.k.d())
        {
          this.k.close();
          this.k = null;
        }
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          Log.e("ImageCache", "close - " + localIOException);
      }
    }
  }

  public static class RetainFragment extends Fragment
  {
    private Object a;

    public Object a()
    {
      return this.a;
    }

    public void a(Object paramObject)
    {
      this.a = paramObject;
    }

    public void onCreate(Bundle paramBundle)
    {
      super.onCreate(paramBundle);
      setRetainInstance(true);
    }
  }

  public static class a
  {
    public int a = 5120;
    public int b = 10485760;
    public File c;
    public Bitmap.CompressFormat d = ImageCache.f();
    public int e = 70;
    public boolean f = true;
    public boolean g = true;
    public boolean h = false;

    public a(Context paramContext, String paramString)
    {
      this.c = ImageCache.a(paramContext, paramString);
    }

    public void a(float paramFloat)
    {
      if ((paramFloat < 0.05F) || (paramFloat > 0.8F))
        throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
      this.a = Math.round((float)Runtime.getRuntime().maxMemory() * paramFloat / 1024.0F);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.ImageCache
 * JD-Core Version:    0.6.2
 */