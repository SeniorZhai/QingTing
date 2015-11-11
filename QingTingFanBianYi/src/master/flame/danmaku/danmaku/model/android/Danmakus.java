package master.flame.danmaku.danmaku.model.android;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Danmaku;
import master.flame.danmaku.danmaku.model.IDanmakuIterator;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

public class Danmakus
  implements IDanmakus
{
  public static final int ST_BY_LIST = 4;
  public static final int ST_BY_TIME = 0;
  public static final int ST_BY_YPOS = 1;
  public static final int ST_BY_YPOS_DESC = 2;
  private BaseDanmaku endItem;
  private BaseDanmaku endSubItem;
  public Collection<BaseDanmaku> items;
  private DanmakuIterator iterator;
  private BaseComparator mComparator;
  private boolean mDuplicateMergingEnabled;
  private int mSize = 0;
  private int mSortType = 0;
  private BaseDanmaku startItem;
  private BaseDanmaku startSubItem;
  private Danmakus subItems;

  public Danmakus()
  {
    this(0, false);
  }

  public Danmakus(int paramInt)
  {
    this(paramInt, false);
  }

  public Danmakus(int paramInt, boolean paramBoolean)
  {
    Object localObject = null;
    if (paramInt == 0)
    {
      localObject = new TimeComparator(paramBoolean);
      if (paramInt != 4)
        break label109;
      this.items = new LinkedList();
    }
    while (true)
    {
      this.mSortType = paramInt;
      this.mSize = 0;
      this.iterator = new DanmakuIterator(this.items);
      return;
      if (paramInt == 1)
      {
        localObject = new YPosComparator(paramBoolean);
        break;
      }
      if (paramInt != 2)
        break;
      localObject = new YPosDescComparator(paramBoolean);
      break;
      label109: this.mDuplicateMergingEnabled = paramBoolean;
      ((BaseComparator)localObject).setDuplicateMergingEnabled(paramBoolean);
      this.items = new TreeSet((Comparator)localObject);
      this.mComparator = ((BaseComparator)localObject);
    }
  }

  public Danmakus(Collection<BaseDanmaku> paramCollection)
  {
    setItems(paramCollection);
  }

  public Danmakus(boolean paramBoolean)
  {
    this(0, paramBoolean);
  }

  private BaseDanmaku createItem(String paramString)
  {
    return new Danmaku(paramString);
  }

  private void setDuplicateMergingEnabled(boolean paramBoolean)
  {
    this.mComparator.setDuplicateMergingEnabled(paramBoolean);
    this.mDuplicateMergingEnabled = paramBoolean;
  }

  private Collection<BaseDanmaku> subset(long paramLong1, long paramLong2)
  {
    if ((this.mSortType == 4) || (this.items == null) || (this.items.size() == 0))
      return null;
    if (this.subItems == null)
      this.subItems = new Danmakus(this.mDuplicateMergingEnabled);
    if (this.startSubItem == null)
      this.startSubItem = createItem("start");
    if (this.endSubItem == null)
      this.endSubItem = createItem("end");
    this.startSubItem.time = paramLong1;
    this.endSubItem.time = paramLong2;
    return ((SortedSet)this.items).subSet(this.startSubItem, this.endSubItem);
  }

  public boolean addItem(BaseDanmaku paramBaseDanmaku)
  {
    if (this.items != null)
      try
      {
        if (this.items.add(paramBaseDanmaku))
        {
          this.mSize += 1;
          return true;
        }
      }
      catch (Exception paramBaseDanmaku)
      {
        paramBaseDanmaku.printStackTrace();
      }
    return false;
  }

  public void clear()
  {
    if (this.items != null)
    {
      this.items.clear();
      this.mSize = 0;
    }
    if (this.subItems != null)
      this.subItems.clear();
  }

  public boolean contains(BaseDanmaku paramBaseDanmaku)
  {
    return (this.items != null) && (this.items.contains(paramBaseDanmaku));
  }

  public BaseDanmaku first()
  {
    if ((this.items != null) && (!this.items.isEmpty()))
    {
      if (this.mSortType == 4)
        return (BaseDanmaku)((LinkedList)this.items).getFirst();
      return (BaseDanmaku)((SortedSet)this.items).first();
    }
    return null;
  }

  public boolean isEmpty()
  {
    return (this.items == null) || (this.items.isEmpty());
  }

  public IDanmakuIterator iterator()
  {
    this.iterator.reset();
    return this.iterator;
  }

  public BaseDanmaku last()
  {
    if ((this.items != null) && (!this.items.isEmpty()))
    {
      if (this.mSortType == 4)
        return (BaseDanmaku)((LinkedList)this.items).getLast();
      return (BaseDanmaku)((SortedSet)this.items).last();
    }
    return null;
  }

  public boolean removeItem(BaseDanmaku paramBaseDanmaku)
  {
    if (paramBaseDanmaku == null);
    do
    {
      return false;
      if (paramBaseDanmaku.isOutside())
        paramBaseDanmaku.setVisibility(false);
    }
    while (!this.items.remove(paramBaseDanmaku));
    this.mSize -= 1;
    return true;
  }

  public void setItems(Collection<BaseDanmaku> paramCollection)
  {
    if ((this.mDuplicateMergingEnabled) && (this.mSortType != 4))
    {
      this.items.clear();
      this.items.addAll(paramCollection);
      paramCollection = this.items;
      if ((paramCollection instanceof List))
        this.mSortType = 4;
      if (paramCollection != null)
        break label92;
    }
    label92: for (int i = 0; ; i = paramCollection.size())
    {
      this.mSize = i;
      if (this.iterator != null)
        break label102;
      this.iterator = new DanmakuIterator(paramCollection);
      return;
      this.items = paramCollection;
      break;
    }
    label102: this.iterator.setDatas(paramCollection);
  }

  public void setSubItemsDuplicateMergingEnabled(boolean paramBoolean)
  {
    this.mDuplicateMergingEnabled = paramBoolean;
    this.endItem = null;
    this.startItem = null;
    if (this.subItems == null)
      this.subItems = new Danmakus(paramBoolean);
    this.subItems.setDuplicateMergingEnabled(paramBoolean);
  }

  public int size()
  {
    return this.mSize;
  }

  public IDanmakus sub(long paramLong1, long paramLong2)
  {
    if ((this.mSortType == 4) || (this.items == null) || (this.items.size() == 0))
      return null;
    if (this.subItems == null)
      this.subItems = new Danmakus(this.mDuplicateMergingEnabled);
    if (this.startItem == null)
      this.startItem = createItem("start");
    if (this.endItem == null)
      this.endItem = createItem("end");
    if ((this.subItems != null) && (paramLong1 - this.startItem.time >= 0L) && (paramLong2 <= this.endItem.time))
      return this.subItems;
    this.startItem.time = paramLong1;
    this.endItem.time = paramLong2;
    this.subItems.setItems(((SortedSet)this.items).subSet(this.startItem, this.endItem));
    return this.subItems;
  }

  public IDanmakus subnew(long paramLong1, long paramLong2)
  {
    return new Danmakus(subset(paramLong1, paramLong2));
  }

  private class BaseComparator
    implements Comparator<BaseDanmaku>
  {
    protected boolean mDuplicateMergingEnable;

    public BaseComparator(boolean arg2)
    {
      boolean bool;
      setDuplicateMergingEnabled(bool);
    }

    public int compare(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
    {
      if ((this.mDuplicateMergingEnable) && (DanmakuUtils.isDuplicate(paramBaseDanmaku1, paramBaseDanmaku2)))
        return 0;
      return DanmakuUtils.compare(paramBaseDanmaku1, paramBaseDanmaku2);
    }

    public void setDuplicateMergingEnabled(boolean paramBoolean)
    {
      this.mDuplicateMergingEnable = paramBoolean;
    }
  }

  private class DanmakuIterator
    implements IDanmakuIterator
  {
    private Iterator<BaseDanmaku> it;
    private Collection<BaseDanmaku> mData;
    private boolean mIteratorUsed;

    public DanmakuIterator()
    {
      Collection localCollection;
      setDatas(localCollection);
    }

    public boolean hasNext()
    {
      try
      {
        if (this.it != null)
        {
          bool = this.it.hasNext();
          if (bool)
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
      finally
      {
      }
    }

    public BaseDanmaku next()
    {
      try
      {
        this.mIteratorUsed = true;
        if (this.it != null)
        {
          localBaseDanmaku = (BaseDanmaku)this.it.next();
          return localBaseDanmaku;
        }
        BaseDanmaku localBaseDanmaku = null;
      }
      finally
      {
      }
    }

    public void remove()
    {
      try
      {
        this.mIteratorUsed = true;
        if (this.it != null)
          this.it.remove();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void reset()
    {
      while (true)
      {
        try
        {
          if (!this.mIteratorUsed)
          {
            Iterator localIterator = this.it;
            if (localIterator != null)
              return;
          }
          if ((this.mData != null) && (Danmakus.this.mSize > 0))
          {
            this.it = this.mData.iterator();
            continue;
          }
        }
        finally
        {
        }
        this.it = null;
      }
    }

    public void setDatas(Collection<BaseDanmaku> paramCollection)
    {
      try
      {
        if (this.mData != paramCollection)
        {
          this.mIteratorUsed = false;
          this.it = null;
        }
        this.mData = paramCollection;
        return;
      }
      finally
      {
      }
      throw paramCollection;
    }
  }

  private class TimeComparator extends Danmakus.BaseComparator
  {
    public TimeComparator(boolean arg2)
    {
      super(bool);
    }

    public int compare(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
    {
      return super.compare(paramBaseDanmaku1, paramBaseDanmaku2);
    }
  }

  private class YPosComparator extends Danmakus.BaseComparator
  {
    public YPosComparator(boolean arg2)
    {
      super(bool);
    }

    public int compare(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
    {
      int i;
      if ((this.mDuplicateMergingEnable) && (DanmakuUtils.isDuplicate(paramBaseDanmaku1, paramBaseDanmaku2)))
        i = 0;
      int j;
      do
      {
        return i;
        j = Float.compare(paramBaseDanmaku1.getTop(), paramBaseDanmaku2.getTop());
        i = j;
      }
      while (j != 0);
      return DanmakuUtils.compare(paramBaseDanmaku1, paramBaseDanmaku2);
    }
  }

  private class YPosDescComparator extends Danmakus.BaseComparator
  {
    public YPosDescComparator(boolean arg2)
    {
      super(bool);
    }

    public int compare(BaseDanmaku paramBaseDanmaku1, BaseDanmaku paramBaseDanmaku2)
    {
      int i;
      if ((this.mDuplicateMergingEnable) && (DanmakuUtils.isDuplicate(paramBaseDanmaku1, paramBaseDanmaku2)))
        i = 0;
      int j;
      do
      {
        return i;
        j = Float.compare(paramBaseDanmaku2.getTop(), paramBaseDanmaku1.getTop());
        i = j;
      }
      while (j != 0);
      return DanmakuUtils.compare(paramBaseDanmaku1, paramBaseDanmaku2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.android.Danmakus
 * JD-Core Version:    0.6.2
 */