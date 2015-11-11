package master.flame.danmaku.danmaku.util;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.AndroidDisplayer;
import master.flame.danmaku.danmaku.model.android.DrawingCache;
import master.flame.danmaku.danmaku.model.android.DrawingCacheHolder;

public class DanmakuUtils
{
  public static DrawingCache buildDanmakuDrawingCache(BaseDanmaku paramBaseDanmaku, IDisplayer paramIDisplayer, DrawingCache paramDrawingCache)
  {
    DrawingCache localDrawingCache = paramDrawingCache;
    if (paramDrawingCache == null)
      localDrawingCache = new DrawingCache();
    int m = (int)Math.ceil(paramBaseDanmaku.paintWidth);
    int k = (int)Math.ceil(paramBaseDanmaku.paintHeight);
    int j = k;
    int i = m;
    if (paramBaseDanmaku.bgColor != 0)
    {
      m += 48;
      k += 24;
      j = k;
      i = m;
      if (paramBaseDanmaku.drawableLeftResid != 0)
      {
        i = m + 64;
        j = k;
      }
    }
    localDrawingCache.build(i, j, paramIDisplayer.getDensityDpi(), false);
    paramDrawingCache = localDrawingCache.get();
    if (paramDrawingCache != null)
    {
      AndroidDisplayer.drawDanmaku(paramIDisplayer.getContext(), paramBaseDanmaku, paramDrawingCache.canvas, 0.0F, 0.0F, false);
      if (paramIDisplayer.isHardwareAccelerated())
        paramDrawingCache.splitWith(paramIDisplayer.getWidth(), paramIDisplayer.getHeight(), paramIDisplayer.getMaximumCacheWidth(), paramIDisplayer.getMaximumCacheHeight());
    }
    return localDrawingCache;
  }

  private static boolean checkHit(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if (paramInt1 != paramInt2);
    do
    {
      return false;
      if (paramInt1 == 1)
      {
        if (paramArrayOfFloat2[0] < paramArrayOfFloat1[2]);
        while (true)
        {
          return bool1;
          bool1 = false;
        }
      }
    }
    while (paramInt1 != 6);
    if (paramArrayOfFloat2[2] > paramArrayOfFloat1[0]);
    for (bool1 = bool2; ; bool1 = false)
      return bool1;
  }

  private static boolean checkHitAtTime(IDisplayer paramIDisplayer, BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2, long paramLong)
  {
    float[] arrayOfFloat = paramBaseDanmaku1.getRectAtTime(paramIDisplayer, paramLong);
    paramIDisplayer = paramBaseDanmaku2.getRectAtTime(paramIDisplayer, paramLong);
    if ((arrayOfFloat == null) || (paramIDisplayer == null))
      return false;
    return checkHit(paramBaseDanmaku1.getType(), paramBaseDanmaku2.getType(), arrayOfFloat, paramIDisplayer);
  }

  public static final int compare(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
  {
    int j = -1;
    int i;
    if (paramBaseDanmaku1 == paramBaseDanmaku2)
      i = 0;
    int k;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              long l;
              do
              {
                do
                {
                  return i;
                  i = j;
                }
                while (paramBaseDanmaku1 == null);
                if (paramBaseDanmaku2 == null)
                  return 1;
                l = paramBaseDanmaku1.time - paramBaseDanmaku2.time;
                if (l > 0L)
                  return 1;
                i = j;
              }
              while (l < 0L);
              k = paramBaseDanmaku1.index - paramBaseDanmaku2.index;
              if (k > 0)
                return 1;
              i = j;
            }
            while (k < 0);
            k = paramBaseDanmaku1.getType() - paramBaseDanmaku2.getType();
            if (k > 0)
              return 1;
            i = j;
          }
          while (k < 0);
          i = j;
        }
        while (paramBaseDanmaku1.text == null);
        if (paramBaseDanmaku2.text == null)
          return 1;
        i = paramBaseDanmaku1.text.compareTo(paramBaseDanmaku2.text);
        if (i != 0)
          return i;
        k = paramBaseDanmaku1.textColor - paramBaseDanmaku2.textColor;
        if (k == 0)
          break;
        i = j;
      }
      while (k < 0);
      return 1;
      k = paramBaseDanmaku1.index - paramBaseDanmaku2.index;
      if (k == 0)
        break;
      i = j;
    }
    while (k < 0);
    return 1;
    return paramBaseDanmaku1.hashCode() - paramBaseDanmaku1.hashCode();
  }

  public static int getCacheSize(int paramInt1, int paramInt2)
  {
    return paramInt1 * paramInt2 * 4;
  }

  public static final boolean isDuplicate(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
  {
    if (paramBaseDanmaku1 == paramBaseDanmaku2);
    do
    {
      return false;
      if (paramBaseDanmaku1.text == paramBaseDanmaku2.text)
        return true;
    }
    while ((paramBaseDanmaku1.text == null) || (!paramBaseDanmaku1.text.equals(paramBaseDanmaku2.text)));
    return true;
  }

  public static final boolean isOverSize(IDisplayer paramIDisplayer, BaseDanmaku paramBaseDanmaku)
  {
    return (paramIDisplayer.isHardwareAccelerated()) && ((paramBaseDanmaku.paintWidth > paramIDisplayer.getMaximumCacheWidth()) || (paramBaseDanmaku.paintHeight > paramIDisplayer.getMaximumCacheHeight()));
  }

  public static boolean willHitInDuration(IDisplayer paramIDisplayer, BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2, long paramLong1, long paramLong2)
  {
    int i = paramBaseDanmaku1.getType();
    if (i != paramBaseDanmaku2.getType());
    do
    {
      long l;
      do
      {
        do
          return false;
        while (paramBaseDanmaku1.isOutside());
        l = paramBaseDanmaku2.time - paramBaseDanmaku1.time;
        if (l < 0L)
          return true;
      }
      while ((Math.abs(l) >= paramLong1) || (paramBaseDanmaku1.isTimeOut()) || (paramBaseDanmaku2.isTimeOut()));
      if ((i == 5) || (i == 4))
        return true;
    }
    while ((!checkHitAtTime(paramIDisplayer, paramBaseDanmaku1, paramBaseDanmaku2, paramLong2)) && (!checkHitAtTime(paramIDisplayer, paramBaseDanmaku1, paramBaseDanmaku2, paramBaseDanmaku1.time + paramBaseDanmaku1.getDuration())));
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.util.DanmakuUtils
 * JD-Core Version:    0.6.2
 */