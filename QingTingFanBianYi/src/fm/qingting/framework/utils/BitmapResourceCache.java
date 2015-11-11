package fm.qingting.framework.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;

public enum BitmapResourceCache
{
  INSTANCE;

  private SparseArray<BitmapCache> caches = new SparseArray();
  private SparseArray<HashSet<Integer>> usingResHolder = new SparseArray();

  public static BitmapResourceCache getInstance()
  {
    return INSTANCE;
  }

  private int[] hash2array(HashSet<Integer> paramHashSet)
  {
    int[] arrayOfInt = new int[paramHashSet.size()];
    paramHashSet = paramHashSet.iterator();
    int i = 0;
    while (true)
    {
      if (!paramHashSet.hasNext())
        return arrayOfInt;
      arrayOfInt[i] = ((Integer)paramHashSet.next()).intValue();
      i += 1;
    }
  }

  private void releaseCaches(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null)
      return;
    int j = paramArrayOfInt.length;
    int i = 0;
    label11: int k;
    BitmapCache localBitmapCache;
    if (i < j)
    {
      k = paramArrayOfInt[i];
      localBitmapCache = (BitmapCache)this.caches.get(k);
      if (localBitmapCache != null)
        break label46;
    }
    while (true)
    {
      i += 1;
      break label11;
      break;
      label46: localBitmapCache.retain -= 1;
      if (localBitmapCache.retain <= 0)
      {
        if ((localBitmapCache.cache != null) && (!localBitmapCache.cache.isRecycled()))
          localBitmapCache.cache.recycle();
        localBitmapCache.cache = null;
        this.caches.remove(k);
      }
    }
  }

  // ERROR //
  private void retainCaches(Resources paramResources, int[] paramArrayOfInt)
  {
    // Byte code:
    //   0: aload_2
    //   1: arraylength
    //   2: istore 9
    //   4: iconst_0
    //   5: istore 8
    //   7: iload 8
    //   9: iload 9
    //   11: if_icmplt +4 -> 15
    //   14: return
    //   15: aload_2
    //   16: iload 8
    //   18: iaload
    //   19: istore 10
    //   21: aload_0
    //   22: getfield 33	fm/qingting/framework/utils/BitmapResourceCache:caches	Landroid/util/SparseArray;
    //   25: iload 10
    //   27: invokevirtual 72	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   30: checkcast 74	fm/qingting/framework/utils/BitmapCache
    //   33: astore 7
    //   35: aload 7
    //   37: astore 4
    //   39: aload 7
    //   41: ifnonnull +70 -> 111
    //   44: aconst_null
    //   45: astore 5
    //   47: aconst_null
    //   48: astore 6
    //   50: aload_1
    //   51: iload 10
    //   53: new 102	android/util/TypedValue
    //   56: dup
    //   57: invokespecial 103	android/util/TypedValue:<init>	()V
    //   60: invokevirtual 109	android/content/res/Resources:openRawResource	(ILandroid/util/TypedValue;)Ljava/io/InputStream;
    //   63: astore_3
    //   64: aload_3
    //   65: astore 6
    //   67: aload_3
    //   68: astore 5
    //   70: new 74	fm/qingting/framework/utils/BitmapCache
    //   73: dup
    //   74: aload_3
    //   75: invokestatic 115	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   78: iload 10
    //   80: invokespecial 118	fm/qingting/framework/utils/BitmapCache:<init>	(Landroid/graphics/Bitmap;I)V
    //   83: astore 4
    //   85: aload 4
    //   87: astore 5
    //   89: aload_3
    //   90: astore 5
    //   92: aload_0
    //   93: getfield 33	fm/qingting/framework/utils/BitmapResourceCache:caches	Landroid/util/SparseArray;
    //   96: iload 10
    //   98: aload 4
    //   100: invokevirtual 122	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   103: aload_3
    //   104: ifnull +155 -> 259
    //   107: aload_3
    //   108: invokevirtual 127	java/io/InputStream:close	()V
    //   111: aload 4
    //   113: ifnull +15 -> 128
    //   116: aload 4
    //   118: aload 4
    //   120: getfield 78	fm/qingting/framework/utils/BitmapCache:retain	I
    //   123: iconst_1
    //   124: iadd
    //   125: putfield 78	fm/qingting/framework/utils/BitmapCache:retain	I
    //   128: iload 8
    //   130: iconst_1
    //   131: iadd
    //   132: istore 8
    //   134: goto -127 -> 7
    //   137: astore 5
    //   139: aload 7
    //   141: astore 4
    //   143: aload 6
    //   145: astore_3
    //   146: aload 5
    //   148: astore 6
    //   150: aload 4
    //   152: astore 5
    //   154: aload_3
    //   155: astore 5
    //   157: aload 6
    //   159: invokevirtual 130	java/lang/OutOfMemoryError:printStackTrace	()V
    //   162: aload 4
    //   164: astore 5
    //   166: aload_3
    //   167: astore 5
    //   169: new 74	fm/qingting/framework/utils/BitmapCache
    //   172: dup
    //   173: iconst_1
    //   174: iconst_1
    //   175: getstatic 136	android/graphics/Bitmap$Config:RGB_565	Landroid/graphics/Bitmap$Config;
    //   178: invokestatic 140	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   181: iload 10
    //   183: invokespecial 118	fm/qingting/framework/utils/BitmapCache:<init>	(Landroid/graphics/Bitmap;I)V
    //   186: astore 6
    //   188: aload_3
    //   189: astore 5
    //   191: aload_0
    //   192: getfield 33	fm/qingting/framework/utils/BitmapResourceCache:caches	Landroid/util/SparseArray;
    //   195: iload 10
    //   197: aload 6
    //   199: invokevirtual 122	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   202: aload 6
    //   204: astore 4
    //   206: aload_3
    //   207: ifnull -96 -> 111
    //   210: aload_3
    //   211: invokevirtual 127	java/io/InputStream:close	()V
    //   214: aload 6
    //   216: astore 4
    //   218: goto -107 -> 111
    //   221: astore_3
    //   222: aload_3
    //   223: invokevirtual 141	java/io/IOException:printStackTrace	()V
    //   226: aload 6
    //   228: astore 4
    //   230: goto -119 -> 111
    //   233: astore_1
    //   234: aload 5
    //   236: ifnull +8 -> 244
    //   239: aload 5
    //   241: invokevirtual 127	java/io/InputStream:close	()V
    //   244: aload_1
    //   245: athrow
    //   246: astore_2
    //   247: aload_2
    //   248: invokevirtual 141	java/io/IOException:printStackTrace	()V
    //   251: goto -7 -> 244
    //   254: astore_3
    //   255: aload_3
    //   256: invokevirtual 141	java/io/IOException:printStackTrace	()V
    //   259: goto -148 -> 111
    //   262: astore_1
    //   263: goto -29 -> 234
    //   266: astore 6
    //   268: goto -118 -> 150
    //
    // Exception table:
    //   from	to	target	type
    //   50	64	137	java/lang/OutOfMemoryError
    //   70	85	137	java/lang/OutOfMemoryError
    //   210	214	221	java/io/IOException
    //   50	64	233	finally
    //   70	85	233	finally
    //   191	202	233	finally
    //   239	244	246	java/io/IOException
    //   107	111	254	java/io/IOException
    //   92	103	262	finally
    //   157	162	262	finally
    //   169	188	262	finally
    //   92	103	266	java/lang/OutOfMemoryError
  }

  @TargetApi(11)
  public void clearAllResourceCaches()
  {
    int i = 0;
    if (i >= this.caches.size())
    {
      if (Build.VERSION.SDK_INT < 11)
        this.caches.clear();
      this.usingResHolder.clear();
      return;
    }
    BitmapCache localBitmapCache = (BitmapCache)this.caches.valueAt(i);
    if (localBitmapCache == null);
    while (true)
    {
      i += 1;
      break;
      if ((localBitmapCache.cache != null) && (!localBitmapCache.cache.isRecycled()))
        localBitmapCache.cache.recycle();
      localBitmapCache.cache = null;
      if (Build.VERSION.SDK_INT >= 11)
        this.caches.removeAt(i);
    }
  }

  public void clearResourceCacheOfOne(Object paramObject, int paramInt)
  {
    paramInt = paramObject.hashCode();
    paramObject = (HashSet)this.usingResHolder.get(paramInt);
    this.usingResHolder.remove(paramInt);
    if (paramObject != null)
      releaseCaches(hash2array(paramObject));
  }

  public Bitmap getResourceCache(Resources paramResources, Object paramObject, int paramInt)
  {
    HashSet localHashSet2 = (HashSet)this.usingResHolder.get(paramObject.hashCode());
    HashSet localHashSet1 = localHashSet2;
    if (localHashSet2 == null)
    {
      localHashSet1 = new HashSet();
      this.usingResHolder.put(paramObject.hashCode(), localHashSet1);
    }
    if (!localHashSet1.contains(Integer.valueOf(paramInt)))
    {
      localHashSet1.add(Integer.valueOf(paramInt));
      retainCaches(paramResources, new int[] { paramInt });
    }
    if (this.caches.get(paramInt) == null)
      retainCaches(paramResources, new int[] { paramInt });
    return ((BitmapCache)this.caches.get(paramInt)).cache;
  }

  public Bitmap getResourceCacheByParent(Resources paramResources, int paramInt1, int paramInt2)
  {
    HashSet localHashSet2 = (HashSet)this.usingResHolder.get(paramInt1);
    HashSet localHashSet1 = localHashSet2;
    if (localHashSet2 == null)
    {
      localHashSet1 = new HashSet();
      this.usingResHolder.put(paramInt1, localHashSet1);
    }
    if (!localHashSet1.contains(Integer.valueOf(paramInt2)))
    {
      localHashSet1.add(Integer.valueOf(paramInt2));
      retainCaches(paramResources, new int[] { paramInt2 });
    }
    if (this.caches.get(paramInt2) == null)
      retainCaches(paramResources, new int[] { paramInt2 });
    return ((BitmapCache)this.caches.get(paramInt2)).cache;
  }

  public void saveResourceCache(Object paramObject)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.BitmapResourceCache
 * JD-Core Version:    0.6.2
 */