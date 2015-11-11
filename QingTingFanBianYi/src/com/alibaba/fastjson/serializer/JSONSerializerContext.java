package com.alibaba.fastjson.serializer;

public final class JSONSerializerContext
{
  public static final int DEFAULT_TABLE_SIZE = 128;
  private final Entry[] buckets;
  private final int indexMask;

  public JSONSerializerContext()
  {
    this(128);
  }

  public JSONSerializerContext(int paramInt)
  {
    this.indexMask = (paramInt - 1);
    this.buckets = new Entry[paramInt];
  }

  public final boolean put(Object paramObject)
  {
    int i = System.identityHashCode(paramObject);
    int j = i & this.indexMask;
    for (Entry localEntry = this.buckets[j]; localEntry != null; localEntry = localEntry.next)
      if (paramObject == localEntry.object)
        return true;
    paramObject = new Entry(paramObject, i, this.buckets[j]);
    this.buckets[j] = paramObject;
    return false;
  }

  protected static final class Entry
  {
    public final int hashCode;
    public Entry next;
    public final Object object;

    public Entry(Object paramObject, int paramInt, Entry paramEntry)
    {
      this.object = paramObject;
      this.next = paramEntry;
      this.hashCode = paramInt;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.JSONSerializerContext
 * JD-Core Version:    0.6.2
 */