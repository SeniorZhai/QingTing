package master.flame.danmaku.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.Danmakus;

public class DanmakuFilters
{
  public static final String TAG_DUPLICATE_FILTER = "1017_Filter";
  public static final String TAG_ELAPSED_TIME_FILTER = "1012_Filter";
  public static final String TAG_GUEST_FILTER = "1016_Filter";
  public static final String TAG_QUANTITY_DANMAKU_FILTER = "1011_Filter";
  public static final String TAG_TEXT_COLOR_DANMAKU_FILTER = "1013_Filter";
  public static final String TAG_TYPE_DANMAKU_FILTER = "1010_Filter";
  public static final String TAG_USER_HASH_FILTER = "1015_Filter";
  public static final String TAG_USER_ID_FILTER = "1014_Filter";
  private static final Map<String, IDanmakuFilter<?>> filters = Collections.synchronizedSortedMap(new TreeMap());
  private static DanmakuFilters instance = null;
  public final Exception filterException = new Exception("not suuport this filter tag");
  IDanmakuFilter<?>[] mFilterArray = new IDanmakuFilter[0];

  public static DanmakuFilters getDefault()
  {
    if (instance == null)
      instance = new DanmakuFilters();
    return instance;
  }

  private void throwFilterException()
  {
    try
    {
      throw this.filterException;
    }
    catch (Exception localException)
    {
    }
  }

  public void clear()
  {
    IDanmakuFilter[] arrayOfIDanmakuFilter = this.mFilterArray;
    int j = arrayOfIDanmakuFilter.length;
    int i = 0;
    while (i < j)
    {
      IDanmakuFilter localIDanmakuFilter = arrayOfIDanmakuFilter[i];
      if (localIDanmakuFilter != null)
        localIDanmakuFilter.clear();
      i += 1;
    }
  }

  public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
  {
    IDanmakuFilter[] arrayOfIDanmakuFilter = this.mFilterArray;
    int j = arrayOfIDanmakuFilter.length;
    int i = 0;
    while (i < j)
    {
      IDanmakuFilter localIDanmakuFilter = arrayOfIDanmakuFilter[i];
      if ((localIDanmakuFilter != null) && (localIDanmakuFilter.filter(paramBaseDanmaku, paramInt1, paramInt2, paramDanmakuTimer, paramBoolean)))
        return true;
      i += 1;
    }
    return false;
  }

  public IDanmakuFilter<?> get(String paramString)
  {
    IDanmakuFilter localIDanmakuFilter2 = (IDanmakuFilter)filters.get(paramString);
    IDanmakuFilter localIDanmakuFilter1 = localIDanmakuFilter2;
    if (localIDanmakuFilter2 == null)
      localIDanmakuFilter1 = registerFilter(paramString);
    return localIDanmakuFilter1;
  }

  public IDanmakuFilter<?> registerFilter(String paramString)
  {
    if (paramString == null)
    {
      throwFilterException();
      return null;
    }
    Object localObject = (IDanmakuFilter)filters.get(paramString);
    if (localObject == null)
      if ("1010_Filter".equals(paramString))
        localObject = new TypeDanmakuFilter();
    while (true)
      if (localObject == null)
      {
        throwFilterException();
        return null;
        if ("1011_Filter".equals(paramString))
          localObject = new QuantityDanmakuFilter();
        else if ("1012_Filter".equals(paramString))
          localObject = new ElapsedTimeFilter();
        else if ("1013_Filter".equals(paramString))
          localObject = new TextColorFilter();
        else if ("1014_Filter".equals(paramString))
          localObject = new UserIdFilter();
        else if ("1015_Filter".equals(paramString))
          localObject = new UserHashFilter();
        else if ("1016_Filter".equals(paramString))
          localObject = new GuestFilter();
        else if ("1017_Filter".equals(paramString))
          localObject = new DuplicateMergingFilter();
      }
      else
      {
        ((IDanmakuFilter)localObject).setData(null);
        filters.put(paramString, localObject);
        this.mFilterArray = ((IDanmakuFilter[])filters.values().toArray(this.mFilterArray));
        return localObject;
      }
  }

  public void release()
  {
    clear();
    filters.clear();
    this.mFilterArray = new IDanmakuFilter[0];
  }

  public void reset()
  {
    IDanmakuFilter[] arrayOfIDanmakuFilter = this.mFilterArray;
    int j = arrayOfIDanmakuFilter.length;
    int i = 0;
    while (i < j)
    {
      IDanmakuFilter localIDanmakuFilter = arrayOfIDanmakuFilter[i];
      if (localIDanmakuFilter != null)
        localIDanmakuFilter.reset();
      i += 1;
    }
  }

  public void unregisterFilter(String paramString)
  {
    paramString = (IDanmakuFilter)filters.remove(paramString);
    if (paramString != null)
    {
      paramString.clear();
      this.mFilterArray = ((IDanmakuFilter[])filters.values().toArray(this.mFilterArray));
    }
  }

  public static abstract class BaseDanmakuFilter<T>
    implements DanmakuFilters.IDanmakuFilter<T>
  {
    public void clear()
    {
    }
  }

  public static class DuplicateMergingFilter extends DanmakuFilters.BaseDanmakuFilter<Void>
  {
    protected final IDanmakus blockedDanmakus = new Danmakus(4);
    protected final LinkedHashMap<String, BaseDanmaku> currentDanmakus = new LinkedHashMap();
    private final IDanmakus passedDanmakus = new Danmakus(4);

    private void removeTimeoutDanmakus(LinkedHashMap<String, BaseDanmaku> paramLinkedHashMap, int paramInt)
    {
      paramLinkedHashMap = paramLinkedHashMap.entrySet().iterator();
      long l = System.currentTimeMillis();
      while (true)
      {
        if (paramLinkedHashMap.hasNext());
        try
        {
          if (((BaseDanmaku)((Map.Entry)paramLinkedHashMap.next()).getValue()).isTimeOut())
          {
            paramLinkedHashMap.remove();
            if (System.currentTimeMillis() - l <= paramInt)
              break;
          }
          else;
        }
        catch (Exception paramLinkedHashMap)
        {
        }
      }
    }

    private final void removeTimeoutDanmakus(IDanmakus paramIDanmakus, long paramLong)
    {
      paramIDanmakus = paramIDanmakus.iterator();
      long l = System.currentTimeMillis();
      while (true)
      {
        if (paramIDanmakus.hasNext());
        try
        {
          if (paramIDanmakus.next().isTimeOut())
          {
            paramIDanmakus.remove();
            if (System.currentTimeMillis() - l <= paramLong)
              break;
          }
          else;
        }
        catch (Exception paramIDanmakus)
        {
        }
      }
    }

    public void clear()
    {
      reset();
    }

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      paramBoolean = true;
      while (true)
      {
        try
        {
          removeTimeoutDanmakus(this.blockedDanmakus, 2L);
          removeTimeoutDanmakus(this.passedDanmakus, 2L);
          removeTimeoutDanmakus(this.currentDanmakus, 3);
          if (this.blockedDanmakus.contains(paramBaseDanmaku))
          {
            boolean bool = paramBaseDanmaku.isOutside();
            if (!bool)
              return paramBoolean;
          }
          if (this.passedDanmakus.contains(paramBaseDanmaku))
          {
            paramBoolean = false;
            continue;
          }
          if (this.currentDanmakus.containsKey(paramBaseDanmaku.text))
          {
            this.currentDanmakus.put(paramBaseDanmaku.text, paramBaseDanmaku);
            this.blockedDanmakus.removeItem(paramBaseDanmaku);
            this.blockedDanmakus.addItem(paramBaseDanmaku);
            continue;
          }
        }
        finally
        {
        }
        this.currentDanmakus.put(paramBaseDanmaku.text, paramBaseDanmaku);
        this.passedDanmakus.addItem(paramBaseDanmaku);
        paramBoolean = false;
      }
    }

    public void reset()
    {
      try
      {
        this.passedDanmakus.clear();
        this.blockedDanmakus.clear();
        this.currentDanmakus.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void setData(Void paramVoid)
    {
    }
  }

  public static class ElapsedTimeFilter extends DanmakuFilters.BaseDanmakuFilter<Object>
  {
    protected final IDanmakus danmakus = new Danmakus();
    long mMaxTime = 20L;

    public void clear()
    {
      reset();
    }

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      paramBoolean = true;
      while (true)
      {
        try
        {
          if ((this.danmakus.last() != null) && (this.danmakus.last().isTimeOut()))
            this.danmakus.clear();
          boolean bool = this.danmakus.contains(paramBaseDanmaku);
          if (bool)
            return paramBoolean;
          if ((paramDanmakuTimer == null) || (!paramBaseDanmaku.isOutside()))
            break label120;
          if (System.currentTimeMillis() - paramDanmakuTimer.currMillisecond >= this.mMaxTime)
          {
            this.danmakus.addItem(paramBaseDanmaku);
            continue;
          }
        }
        finally
        {
        }
        paramBoolean = false;
        continue;
        label120: paramBoolean = false;
      }
    }

    public void reset()
    {
      try
      {
        this.danmakus.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void setData(Object paramObject)
    {
      reset();
    }
  }

  public static class GuestFilter extends DanmakuFilters.BaseDanmakuFilter<Boolean>
  {
    private Boolean mBlock = Boolean.valueOf(false);

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      if (!this.mBlock.booleanValue())
        return false;
      return paramBaseDanmaku.isGuest;
    }

    public void reset()
    {
      this.mBlock = Boolean.valueOf(false);
    }

    public void setData(Boolean paramBoolean)
    {
      this.mBlock = paramBoolean;
    }
  }

  public static abstract interface IDanmakuFilter<T>
  {
    public abstract void clear();

    public abstract boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean);

    public abstract void reset();

    public abstract void setData(T paramT);
  }

  public static class QuantityDanmakuFilter extends DanmakuFilters.BaseDanmakuFilter<Integer>
  {
    protected final IDanmakus danmakus = new Danmakus();
    protected BaseDanmaku mLastSkipped = null;
    protected int mMaximumSize = -1;

    public void clear()
    {
      reset();
    }

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      boolean bool = true;
      while (true)
      {
        try
        {
          paramDanmakuTimer = this.danmakus.last();
          if ((paramDanmakuTimer != null) && (paramDanmakuTimer.isTimeOut()))
            this.danmakus.clear();
          if (this.mMaximumSize > 0)
          {
            int i = paramBaseDanmaku.getType();
            if (i == 1);
          }
          else
          {
            paramBoolean = false;
            return paramBoolean;
          }
          paramBoolean = bool;
          if (this.danmakus.contains(paramBaseDanmaku))
            continue;
          if ((paramInt2 < this.mMaximumSize) || (paramBaseDanmaku.isShown()) || ((this.mLastSkipped != null) && (paramBaseDanmaku.time - this.mLastSkipped.time > 500L)))
          {
            this.mLastSkipped = paramBaseDanmaku;
            paramBoolean = false;
            continue;
          }
          if ((paramInt1 > this.mMaximumSize) && (!paramBaseDanmaku.isTimeOut()))
          {
            this.danmakus.addItem(paramBaseDanmaku);
            paramBoolean = bool;
            continue;
          }
        }
        finally
        {
        }
        this.mLastSkipped = paramBaseDanmaku;
        paramBoolean = false;
      }
    }

    public void reset()
    {
      try
      {
        this.danmakus.clear();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void setData(Integer paramInteger)
    {
      reset();
      if (paramInteger == null);
      while (paramInteger.intValue() == this.mMaximumSize)
        return;
      this.mMaximumSize = paramInteger.intValue();
    }
  }

  public static class TextColorFilter extends DanmakuFilters.BaseDanmakuFilter<List<Integer>>
  {
    public List<Integer> mWhiteList = new ArrayList();

    private void addToWhiteList(Integer paramInteger)
    {
      if (!this.mWhiteList.contains(paramInteger))
        this.mWhiteList.add(paramInteger);
    }

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      return (paramBaseDanmaku != null) && (!this.mWhiteList.contains(Integer.valueOf(paramBaseDanmaku.textColor)));
    }

    public void reset()
    {
      this.mWhiteList.clear();
    }

    public void setData(List<Integer> paramList)
    {
      reset();
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
          addToWhiteList((Integer)paramList.next());
      }
    }
  }

  public static class TypeDanmakuFilter extends DanmakuFilters.BaseDanmakuFilter<List<Integer>>
  {
    final List<Integer> mFilterTypes = Collections.synchronizedList(new ArrayList());

    public void disableType(Integer paramInteger)
    {
      if (this.mFilterTypes.contains(paramInteger))
        this.mFilterTypes.remove(paramInteger);
    }

    public void enableType(Integer paramInteger)
    {
      if (!this.mFilterTypes.contains(paramInteger))
        this.mFilterTypes.add(paramInteger);
    }

    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      return (paramBaseDanmaku != null) && (this.mFilterTypes.contains(Integer.valueOf(paramBaseDanmaku.getType())));
    }

    public void reset()
    {
      this.mFilterTypes.clear();
    }

    public void setData(List<Integer> paramList)
    {
      reset();
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
          enableType((Integer)paramList.next());
      }
    }
  }

  public static abstract class UserFilter<T> extends DanmakuFilters.BaseDanmakuFilter<List<T>>
  {
    public List<T> mBlackList = new ArrayList();

    private void addToBlackList(T paramT)
    {
      if (!this.mBlackList.contains(paramT))
        this.mBlackList.add(paramT);
    }

    public abstract boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean);

    public void reset()
    {
      this.mBlackList.clear();
    }

    public void setData(List<T> paramList)
    {
      reset();
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
          addToBlackList(paramList.next());
      }
    }
  }

  public static class UserHashFilter extends DanmakuFilters.UserFilter<String>
  {
    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      return (paramBaseDanmaku != null) && (this.mBlackList.contains(paramBaseDanmaku.userHash));
    }
  }

  public static class UserIdFilter extends DanmakuFilters.UserFilter<Integer>
  {
    public boolean filter(BaseDanmaku paramBaseDanmaku, int paramInt1, int paramInt2, DanmakuTimer paramDanmakuTimer, boolean paramBoolean)
    {
      return (paramBaseDanmaku != null) && (this.mBlackList.contains(Integer.valueOf(paramBaseDanmaku.userId)));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.DanmakuFilters
 * JD-Core Version:    0.6.2
 */