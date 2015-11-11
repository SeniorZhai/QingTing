package fm.qingting.framework.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.widget.ImageView;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OldVersionImageLoader
{
  private static OldVersionImageLoader instance = null;
  private String DefDataBasePath = "/data/data/fm.qingting.qtradio/pics/";
  private HashMap<String, HashMap<String, ImageCache>> globalImageCache = new HashMap();
  private HashMap<ImageView, ImageLoaderHandler> imageViewHandlers = new HashMap();
  private HashMap<ImageView, ImageCache> imagesOrder = new HashMap();
  private HashMap<ImageCache, HashSet<ImageLoaderHandler>> loadingOrder = new HashMap();
  private HashMap<SoftReference<Object>, HashSet<ImageCache>> localImageCache = new HashMap();
  private ReferenceQueue<Object> ownerReferenceQueue = new ReferenceQueue();

  private void addCache(ImageCache paramImageCache, Object paramObject)
  {
    Iterator localIterator = this.localImageCache.keySet().iterator();
    Object localObject = null;
    if (!localIterator.hasNext())
    {
      label26: if (localObject != null)
        break label164;
      localObject = new SoftReference(paramObject, this.ownerReferenceQueue);
      paramObject = new HashSet();
      this.localImageCache.put(localObject, paramObject);
      label61: if (!paramObject.contains(paramImageCache))
      {
        paramObject.add(paramImageCache);
        paramImageCache.retain();
      }
      if (this.globalImageCache.containsKey(paramImageCache.url))
        break label179;
      paramObject = new HashMap();
      this.globalImageCache.put(paramImageCache.url, paramObject);
    }
    while (true)
    {
      if (!paramObject.containsKey(paramImageCache.tag))
        paramObject.put(paramImageCache.tag, paramImageCache);
      return;
      SoftReference localSoftReference = (SoftReference)localIterator.next();
      if (localSoftReference.get() != paramObject)
        break;
      localObject = localSoftReference;
      break label26;
      label164: paramObject = (HashSet)this.localImageCache.get(localObject);
      break label61;
      label179: paramObject = (HashMap)this.globalImageCache.get(paramImageCache.url);
    }
  }

  private void clearCacheMap(Set<ImageCache> paramSet)
  {
    if (paramSet == null);
    while (true)
    {
      return;
      paramSet = paramSet.iterator();
      while (paramSet.hasNext())
      {
        ImageCache localImageCache1 = (ImageCache)paramSet.next();
        if (localImageCache1.release() == 0)
        {
          Map localMap = (Map)this.globalImageCache.get(localImageCache1.url);
          localMap.remove(localImageCache1.tag);
          if (!localMap.keySet().iterator().hasNext())
            this.globalImageCache.remove(localImageCache1.url);
        }
      }
    }
  }

  private void clearReleasedOwnerCache()
  {
    while (true)
    {
      SoftReference localSoftReference = (SoftReference)this.ownerReferenceQueue.poll();
      if (localSoftReference == null)
        return;
      releaseLocalCache(localSoftReference);
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

  private ImageCache getCache(String paramString1, String paramString2)
  {
    if (!this.globalImageCache.containsKey(paramString1));
    do
    {
      return null;
      paramString1 = (Map)this.globalImageCache.get(paramString1);
    }
    while (!paramString1.containsKey(paramString2));
    return (ImageCache)paramString1.get(paramString2);
  }

  public static OldVersionImageLoader getInstance()
  {
    try
    {
      if (instance == null)
        instance = new OldVersionImageLoader();
      OldVersionImageLoader localOldVersionImageLoader = instance;
      return localOldVersionImageLoader;
    }
    finally
    {
    }
  }

  private String getTag(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0))
      return "";
    return String.format("%1$-4d_%1$-4d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }

  private void loadCompleteHandler(ImageCache paramImageCache, Bitmap paramBitmap)
  {
    updateCache(paramImageCache, paramBitmap);
  }

  private void loadFailedHandler(ImageCache paramImageCache)
  {
    updateCache(paramImageCache, null);
  }

  private void releaseLocalCache(SoftReference<Object> paramSoftReference)
  {
    if (paramSoftReference == null);
    do
    {
      return;
      paramSoftReference = (HashSet)this.localImageCache.remove(paramSoftReference);
    }
    while (paramSoftReference == null);
    clearCacheMap(paramSoftReference);
    paramSoftReference.clear();
  }

  private void updateCache(ImageCache paramImageCache, Bitmap paramBitmap)
  {
    if (paramBitmap != null)
      paramImageCache.image = paramBitmap;
    Object localObject1 = this.imagesOrder.keySet().iterator();
    ImageLoaderHandler localImageLoaderHandler;
    do
    {
      do
      {
        if (!((Iterator)localObject1).hasNext())
        {
          localObject1 = (HashSet)this.loadingOrder.remove(paramImageCache);
          if (localObject1 != null)
            break;
          return;
        }
        localObject2 = (ImageView)((Iterator)localObject1).next();
      }
      while ((ImageCache)this.imagesOrder.get(localObject2) != paramImageCache);
      ((Iterator)localObject1).remove();
      if (paramBitmap != null)
        ((ImageView)localObject2).setImageBitmap(paramBitmap);
      localImageLoaderHandler = (ImageLoaderHandler)this.imageViewHandlers.remove(localObject2);
    }
    while (localImageLoaderHandler == null);
    if (paramBitmap != null);
    for (boolean bool = true; ; bool = false)
    {
      localImageLoaderHandler.updateImageViewFinish(bool, (ImageView)localObject2, paramImageCache.url, paramImageCache.image);
      break;
    }
    Object localObject2 = ((HashSet)localObject1).iterator();
    do
    {
      if (!((Iterator)localObject2).hasNext())
      {
        ((HashSet)localObject1).clear();
        return;
      }
      localImageLoaderHandler = (ImageLoaderHandler)((Iterator)localObject2).next();
    }
    while (localImageLoaderHandler == null);
    if (paramBitmap != null);
    for (bool = true; ; bool = false)
    {
      localImageLoaderHandler.loadImageFinish(bool, paramImageCache.url, paramImageCache.image, paramImageCache.width, paramImageCache.height);
      break;
    }
  }

  public Bitmap getImage(String paramString, int paramInt1, int paramInt2)
  {
    paramString = getCache(paramString, getTag(paramInt1, paramInt2));
    if ((paramString != null) && (paramString.available()))
      return paramString.image;
    return null;
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject)
  {
    loadImage(paramString, paramImageView, paramObject, 0, 0, null);
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, int paramInt1, int paramInt2)
  {
    loadImage(paramString, paramImageView, paramObject, paramInt1, paramInt2, null);
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, int paramInt1, int paramInt2, ImageLoaderHandler paramImageLoaderHandler)
  {
    ImageCache localImageCache2 = getCache(paramString, getTag(paramInt1, paramInt2));
    ImageCache localImageCache1 = localImageCache2;
    if (localImageCache2 == null)
      localImageCache1 = new ImageCache(null, paramString, 0, getTag(paramInt1, paramInt2), paramInt1, paramInt2);
    addCache(localImageCache1, paramObject);
    if (paramImageView != null)
    {
      this.imagesOrder.remove(paramImageView);
      this.imageViewHandlers.remove(paramImageView);
    }
    if (localImageCache1.available())
    {
      if (paramImageView != null)
        paramImageView.setImageBitmap(localImageCache1.image);
      if (paramImageLoaderHandler != null)
      {
        paramImageLoaderHandler.loadImageFinish(true, paramString, localImageCache1.image, localImageCache1.width, localImageCache1.height);
        if (paramImageView != null)
          paramImageLoaderHandler.updateImageViewFinish(true, paramImageView, paramString, localImageCache1.image);
      }
    }
    while (true)
    {
      clearReleasedOwnerCache();
      return;
      if (paramImageView != null)
      {
        this.imagesOrder.put(paramImageView, localImageCache1);
        if (paramImageLoaderHandler != null)
          this.imageViewHandlers.put(paramImageView, paramImageLoaderHandler);
      }
      if (!this.loadingOrder.containsKey(localImageCache1))
      {
        new LoadTask().execute(new ImageCache[] { localImageCache1 });
        this.loadingOrder.put(localImageCache1, new HashSet());
      }
      if (paramImageLoaderHandler != null)
        ((HashSet)this.loadingOrder.get(localImageCache1)).add(paramImageLoaderHandler);
    }
  }

  public void loadImage(String paramString, ImageView paramImageView, Object paramObject, ImageLoaderHandler paramImageLoaderHandler)
  {
    loadImage(paramString, paramImageView, paramObject, 0, 0, paramImageLoaderHandler);
  }

  public void releaseAllCache()
  {
    this.localImageCache.clear();
    this.globalImageCache.clear();
  }

  public void releaseCache(Object paramObject)
  {
    if (paramObject == null)
      return;
    Object localObject = null;
    Iterator localIterator = this.localImageCache.keySet().iterator();
    if (!localIterator.hasNext());
    SoftReference localSoftReference;
    for (paramObject = localObject; ; paramObject = localSoftReference)
    {
      releaseLocalCache(paramObject);
      return;
      localSoftReference = (SoftReference)localIterator.next();
      if (localSoftReference.get() != paramObject)
        break;
    }
  }

  public void reset()
  {
    stopAllLoading();
    releaseAllCache();
    this.loadingOrder.clear();
    this.imagesOrder.clear();
    this.imageViewHandlers.clear();
    this.ownerReferenceQueue = new ReferenceQueue();
  }

  public void setDefDataPath(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.DefDataBasePath = paramString;
  }

  public void stopAllLoading()
  {
    Iterator localIterator = new ArrayList(this.imagesOrder.values()).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      loadFailedHandler((ImageCache)localIterator.next());
    }
  }

  private class ImageCache
  {
    public int height;
    public Bitmap image;
    public int retainCount;
    public String tag;
    public String url;
    public int width;

    public ImageCache(Bitmap paramString1, String paramInt1, int paramString2, String paramInt2, int paramInt3, int arg7)
    {
      this.image = paramString1;
      this.url = paramInt1;
      this.retainCount = paramString2;
      this.tag = paramInt2;
      this.width = paramInt3;
      int i;
      this.height = i;
    }

    public boolean available()
    {
      return this.image != null;
    }

    public int release()
    {
      this.retainCount -= 1;
      return this.retainCount;
    }

    public int retain()
    {
      this.retainCount += 1;
      return this.retainCount;
    }
  }

  public class LoadTask extends AsyncTask<OldVersionImageLoader.ImageCache, Void, Bitmap>
  {
    private OldVersionImageLoader.ImageCache cache;

    public LoadTask()
    {
    }

    private Bitmap getImageByUrl(String paramString)
    {
      Object localObject;
      if (paramString == null)
        localObject = null;
      Bitmap localBitmap;
      do
      {
        return localObject;
        localBitmap = getImageByUrlFromDir(paramString);
        localObject = localBitmap;
      }
      while (localBitmap != null);
      return getImageByUrlFromNet(paramString);
    }

    // ERROR //
    private Bitmap getImageByUrlFromDir(String paramString)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +12 -> 13
      //   4: aload_1
      //   5: ldc 34
      //   7: invokevirtual 40	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   10: ifeq +5 -> 15
      //   13: aconst_null
      //   14: areturn
      //   15: aload_1
      //   16: ldc 42
      //   18: invokevirtual 46	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   21: ifeq -8 -> 13
      //   24: aload_0
      //   25: aload_1
      //   26: invokespecial 50	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:getImageID	(Ljava/lang/String;)Ljava/lang/String;
      //   29: astore_1
      //   30: aload_1
      //   31: ifnull -18 -> 13
      //   34: new 52	java/lang/StringBuilder
      //   37: dup
      //   38: aload_0
      //   39: getfield 16	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:this$0	Lfm/qingting/framework/utils/OldVersionImageLoader;
      //   42: invokestatic 56	fm/qingting/framework/utils/OldVersionImageLoader:access$0	(Lfm/qingting/framework/utils/OldVersionImageLoader;)Ljava/lang/String;
      //   45: invokestatic 60	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   48: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   51: aload_1
      //   52: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   55: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   58: astore_1
      //   59: new 73	java/io/File
      //   62: dup
      //   63: aload_1
      //   64: invokespecial 74	java/io/File:<init>	(Ljava/lang/String;)V
      //   67: astore_2
      //   68: aload_2
      //   69: invokevirtual 78	java/io/File:exists	()Z
      //   72: istore 5
      //   74: iload 5
      //   76: ifeq -63 -> 13
      //   79: new 80	android/graphics/BitmapFactory$Options
      //   82: dup
      //   83: invokespecial 81	android/graphics/BitmapFactory$Options:<init>	()V
      //   86: astore_2
      //   87: aload_2
      //   88: getstatic 87	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
      //   91: putfield 90	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
      //   94: aload_2
      //   95: iconst_1
      //   96: putfield 94	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   99: aload_0
      //   100: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   103: getfield 102	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:width	I
      //   106: ifeq +13 -> 119
      //   109: aload_0
      //   110: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   113: getfield 105	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:height	I
      //   116: ifne +149 -> 265
      //   119: iconst_0
      //   120: istore_3
      //   121: iconst_0
      //   122: istore 4
      //   124: aload_2
      //   125: getfield 108	android/graphics/BitmapFactory$Options:outHeight	I
      //   128: sipush 1024
      //   131: if_icmple +7 -> 138
      //   134: sipush 1024
      //   137: istore_3
      //   138: aload_2
      //   139: getfield 111	android/graphics/BitmapFactory$Options:outWidth	I
      //   142: sipush 1024
      //   145: if_icmple +8 -> 153
      //   148: sipush 1024
      //   151: istore 4
      //   153: iload_3
      //   154: ifne +33 -> 187
      //   157: iload 4
      //   159: ifne +28 -> 187
      //   162: aload_2
      //   163: iconst_1
      //   164: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   167: aload_2
      //   168: iconst_0
      //   169: putfield 94	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   172: aload_1
      //   173: aload_2
      //   174: invokestatic 120	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   177: astore_1
      //   178: aload_1
      //   179: areturn
      //   180: astore_1
      //   181: aload_1
      //   182: invokevirtual 123	java/lang/Exception:printStackTrace	()V
      //   185: aconst_null
      //   186: areturn
      //   187: iload_3
      //   188: ifle +33 -> 221
      //   191: iload 4
      //   193: ifne +28 -> 221
      //   196: aload_2
      //   197: iconst_m1
      //   198: aload_2
      //   199: getfield 108	android/graphics/BitmapFactory$Options:outHeight	I
      //   202: iload_3
      //   203: imul
      //   204: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   207: istore_3
      //   208: aload_2
      //   209: iload_3
      //   210: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   213: aload_2
      //   214: iload_3
      //   215: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   218: goto -51 -> 167
      //   221: iload 4
      //   223: ifle +26 -> 249
      //   226: iload_3
      //   227: ifne +22 -> 249
      //   230: aload_2
      //   231: aload_2
      //   232: iconst_m1
      //   233: aload_2
      //   234: getfield 111	android/graphics/BitmapFactory$Options:outWidth	I
      //   237: iload 4
      //   239: imul
      //   240: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   243: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   246: goto -79 -> 167
      //   249: aload_2
      //   250: aload_2
      //   251: iconst_m1
      //   252: iload_3
      //   253: iload 4
      //   255: imul
      //   256: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   259: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   262: goto -95 -> 167
      //   265: aload_2
      //   266: aload_2
      //   267: iconst_m1
      //   268: aload_0
      //   269: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   272: getfield 102	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:width	I
      //   275: aload_0
      //   276: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   279: getfield 105	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:height	I
      //   282: imul
      //   283: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   286: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   289: goto -122 -> 167
      //   292: astore_1
      //   293: aload_1
      //   294: invokevirtual 123	java/lang/Exception:printStackTrace	()V
      //   297: aconst_null
      //   298: areturn
      //   299: astore_1
      //   300: aconst_null
      //   301: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   68	74	180	java/lang/Exception
      //   172	178	292	java/lang/Exception
      //   172	178	299	java/lang/OutOfMemoryError
    }

    // ERROR //
    private Bitmap getImageByUrlFromNet(String paramString)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +5 -> 6
      //   4: aconst_null
      //   5: areturn
      //   6: new 131	fm/qingting/framework/utils/HTTPStream
      //   9: dup
      //   10: invokespecial 132	fm/qingting/framework/utils/HTTPStream:<init>	()V
      //   13: aload_1
      //   14: invokevirtual 136	fm/qingting/framework/utils/HTTPStream:getStream	(Ljava/lang/String;)Ljava/io/InputStream;
      //   17: astore_2
      //   18: aload_2
      //   19: ifnull -15 -> 4
      //   22: aconst_null
      //   23: astore_1
      //   24: aload_0
      //   25: aload_2
      //   26: invokevirtual 140	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:InputStreamToByte	(Ljava/io/InputStream;)[B
      //   29: astore_2
      //   30: aload_2
      //   31: astore_1
      //   32: new 80	android/graphics/BitmapFactory$Options
      //   35: dup
      //   36: invokespecial 81	android/graphics/BitmapFactory$Options:<init>	()V
      //   39: astore_2
      //   40: aload_2
      //   41: getstatic 87	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
      //   44: putfield 90	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
      //   47: aload_2
      //   48: iconst_1
      //   49: putfield 94	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   52: aload_0
      //   53: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   56: getfield 102	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:width	I
      //   59: ifeq +13 -> 72
      //   62: aload_0
      //   63: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   66: getfield 105	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:height	I
      //   69: ifne +156 -> 225
      //   72: iconst_0
      //   73: istore_3
      //   74: iconst_0
      //   75: istore 4
      //   77: aload_2
      //   78: getfield 108	android/graphics/BitmapFactory$Options:outHeight	I
      //   81: sipush 1024
      //   84: if_icmple +7 -> 91
      //   87: sipush 1024
      //   90: istore_3
      //   91: aload_2
      //   92: getfield 111	android/graphics/BitmapFactory$Options:outWidth	I
      //   95: sipush 1024
      //   98: if_icmple +8 -> 106
      //   101: sipush 1024
      //   104: istore 4
      //   106: iload_3
      //   107: ifne +40 -> 147
      //   110: iload 4
      //   112: ifne +35 -> 147
      //   115: aload_2
      //   116: iconst_1
      //   117: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   120: aload_2
      //   121: iconst_0
      //   122: putfield 94	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
      //   125: aload_1
      //   126: iconst_0
      //   127: aload_1
      //   128: arraylength
      //   129: aload_2
      //   130: invokestatic 144	android/graphics/BitmapFactory:decodeByteArray	([BIILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
      //   133: astore_1
      //   134: aload_1
      //   135: areturn
      //   136: astore_2
      //   137: aload_2
      //   138: invokevirtual 145	java/io/IOException:printStackTrace	()V
      //   141: goto -109 -> 32
      //   144: astore_1
      //   145: aconst_null
      //   146: areturn
      //   147: iload_3
      //   148: ifle +33 -> 181
      //   151: iload 4
      //   153: ifne +28 -> 181
      //   156: aload_2
      //   157: iconst_m1
      //   158: aload_2
      //   159: getfield 108	android/graphics/BitmapFactory$Options:outHeight	I
      //   162: iload_3
      //   163: imul
      //   164: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   167: istore_3
      //   168: aload_2
      //   169: iload_3
      //   170: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   173: aload_2
      //   174: iload_3
      //   175: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   178: goto -58 -> 120
      //   181: iload 4
      //   183: ifle +26 -> 209
      //   186: iload_3
      //   187: ifne +22 -> 209
      //   190: aload_2
      //   191: aload_2
      //   192: iconst_m1
      //   193: aload_2
      //   194: getfield 111	android/graphics/BitmapFactory$Options:outWidth	I
      //   197: iload 4
      //   199: imul
      //   200: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   203: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   206: goto -86 -> 120
      //   209: aload_2
      //   210: aload_2
      //   211: iconst_m1
      //   212: iload_3
      //   213: iload 4
      //   215: imul
      //   216: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   219: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   222: goto -102 -> 120
      //   225: aload_2
      //   226: aload_2
      //   227: iconst_m1
      //   228: aload_0
      //   229: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   232: getfield 102	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:width	I
      //   235: aload_0
      //   236: getfield 96	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:cache	Lfm/qingting/framework/utils/OldVersionImageLoader$ImageCache;
      //   239: getfield 105	fm/qingting/framework/utils/OldVersionImageLoader$ImageCache:height	I
      //   242: imul
      //   243: invokestatic 127	fm/qingting/framework/utils/OldVersionImageLoader:computeSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
      //   246: putfield 114	android/graphics/BitmapFactory$Options:inSampleSize	I
      //   249: goto -129 -> 120
      //   252: astore_1
      //   253: aconst_null
      //   254: areturn
      //   255: astore_1
      //   256: aconst_null
      //   257: areturn
      //
      // Exception table:
      //   from	to	target	type
      //   24	30	136	java/io/IOException
      //   24	30	144	java/lang/OutOfMemoryError
      //   125	134	252	java/lang/OutOfMemoryError
      //   125	134	255	java/lang/Exception
    }

    private byte[] getImageFromDBByUrl(String paramString)
    {
      if (paramString == null);
      do
      {
        return null;
        HashMap localHashMap = new HashMap();
        localHashMap.put("url", paramString);
        paramString = DataManager.getInstance().getData("getdb_image_info", null, localHashMap).getResult();
      }
      while (!paramString.getSuccess());
      return (byte[])paramString.getData();
    }

    private String getImageID(String paramString)
    {
      if (paramString == null);
      int i;
      do
      {
        do
        {
          return null;
          paramString = paramString.split("/");
        }
        while (paramString == null);
        i = paramString.length - 1;
      }
      while (i < 0);
      return paramString[i];
    }

    // ERROR //
    private void saveImageByUrlToDir(String paramString, Bitmap paramBitmap)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnull +7 -> 8
      //   4: aload_2
      //   5: ifnonnull +4 -> 9
      //   8: return
      //   9: aload_1
      //   10: ldc 42
      //   12: invokevirtual 46	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   15: ifeq -7 -> 8
      //   18: aload_0
      //   19: aload_1
      //   20: invokespecial 50	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:getImageID	(Ljava/lang/String;)Ljava/lang/String;
      //   23: astore_1
      //   24: aload_1
      //   25: ifnull -17 -> 8
      //   28: new 73	java/io/File
      //   31: dup
      //   32: aload_0
      //   33: getfield 16	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:this$0	Lfm/qingting/framework/utils/OldVersionImageLoader;
      //   36: invokestatic 56	fm/qingting/framework/utils/OldVersionImageLoader:access$0	(Lfm/qingting/framework/utils/OldVersionImageLoader;)Ljava/lang/String;
      //   39: invokespecial 74	java/io/File:<init>	(Ljava/lang/String;)V
      //   42: astore_3
      //   43: aload_3
      //   44: invokevirtual 78	java/io/File:exists	()Z
      //   47: ifne +8 -> 55
      //   50: aload_3
      //   51: invokevirtual 197	java/io/File:mkdir	()Z
      //   54: pop
      //   55: new 73	java/io/File
      //   58: dup
      //   59: new 52	java/lang/StringBuilder
      //   62: dup
      //   63: aload_0
      //   64: getfield 16	fm/qingting/framework/utils/OldVersionImageLoader$LoadTask:this$0	Lfm/qingting/framework/utils/OldVersionImageLoader;
      //   67: invokestatic 56	fm/qingting/framework/utils/OldVersionImageLoader:access$0	(Lfm/qingting/framework/utils/OldVersionImageLoader;)Ljava/lang/String;
      //   70: invokestatic 60	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   73: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   76: aload_1
      //   77: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   80: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   83: invokespecial 74	java/io/File:<init>	(Ljava/lang/String;)V
      //   86: astore_1
      //   87: aload_1
      //   88: invokevirtual 200	java/io/File:createNewFile	()Z
      //   91: istore 4
      //   93: iload 4
      //   95: ifeq -87 -> 8
      //   98: new 202	java/io/FileOutputStream
      //   101: dup
      //   102: aload_1
      //   103: invokespecial 205	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   106: astore_1
      //   107: aload_2
      //   108: getstatic 211	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
      //   111: bipush 100
      //   113: aload_1
      //   114: invokevirtual 217	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
      //   117: pop
      //   118: aload_1
      //   119: invokevirtual 220	java/io/FileOutputStream:flush	()V
      //   122: aload_1
      //   123: invokevirtual 223	java/io/FileOutputStream:close	()V
      //   126: return
      //   127: astore_1
      //   128: aload_1
      //   129: invokevirtual 145	java/io/IOException:printStackTrace	()V
      //   132: return
      //   133: astore_1
      //   134: aload_1
      //   135: invokevirtual 123	java/lang/Exception:printStackTrace	()V
      //   138: return
      //   139: astore_1
      //   140: aload_1
      //   141: invokevirtual 123	java/lang/Exception:printStackTrace	()V
      //   144: return
      //   145: astore_2
      //   146: aload_2
      //   147: invokevirtual 145	java/io/IOException:printStackTrace	()V
      //   150: goto -28 -> 122
      //
      // Exception table:
      //   from	to	target	type
      //   122	126	127	java/io/IOException
      //   87	93	133	java/lang/Exception
      //   98	107	139	java/lang/Exception
      //   118	122	145	java/io/IOException
    }

    public byte[] InputStreamToByte(InputStream paramInputStream)
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

    protected Bitmap doInBackground(OldVersionImageLoader.ImageCache[] paramArrayOfImageCache)
    {
      Object localObject = null;
      Bitmap localBitmap2 = null;
      Bitmap localBitmap1 = localBitmap2;
      try
      {
        this.cache = paramArrayOfImageCache[0];
        localBitmap1 = localBitmap2;
        paramArrayOfImageCache = localObject;
        if (this.cache.url != null)
        {
          localBitmap1 = localBitmap2;
          if (this.cache.url.equalsIgnoreCase(""))
            return null;
          localBitmap1 = localBitmap2;
          localBitmap2 = getImageByUrl(this.cache.url);
          paramArrayOfImageCache = localObject;
          if (localBitmap2 != null)
          {
            localBitmap1 = localBitmap2;
            saveImageByUrlToDir(this.cache.url, localBitmap2);
            return localBitmap2;
          }
        }
      }
      catch (Exception paramArrayOfImageCache)
      {
        paramArrayOfImageCache = localBitmap1;
      }
      return paramArrayOfImageCache;
    }

    protected void onPostExecute(Bitmap paramBitmap)
    {
      if (paramBitmap != null)
      {
        OldVersionImageLoader.this.loadCompleteHandler(this.cache, paramBitmap);
        return;
      }
      if (this.cache.url == null);
      while (true)
      {
        OldVersionImageLoader.this.loadFailedHandler(this.cache);
        return;
        paramBitmap = this.cache.url;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.OldVersionImageLoader
 * JD-Core Version:    0.6.2
 */