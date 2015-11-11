package com.alibaba.fastjson.util;

public class IdentityHashMap<K, V>
{
  public static final int DEFAULT_TABLE_SIZE = 1024;
  private final Entry<K, V>[] buckets;
  private final int indexMask;

  public IdentityHashMap()
  {
    this(1024);
  }

  public IdentityHashMap(int paramInt)
  {
    this.indexMask = (paramInt - 1);
    this.buckets = new Entry[paramInt];
  }

  public final V get(K paramK)
  {
    int i = System.identityHashCode(paramK);
    int j = this.indexMask;
    for (Entry localEntry = this.buckets[(i & j)]; localEntry != null; localEntry = localEntry.next)
      if (paramK == localEntry.key)
        return localEntry.value;
    return null;
  }

  public boolean put(K paramK, V paramV)
  {
    int i = System.identityHashCode(paramK);
    int j = i & this.indexMask;
    for (Entry localEntry = this.buckets[j]; localEntry != null; localEntry = localEntry.next)
      if (paramK == localEntry.key)
      {
        localEntry.value = paramV;
        return true;
      }
    paramK = new Entry(paramK, paramV, i, this.buckets[j]);
    this.buckets[j] = paramK;
    return false;
  }

  public int size()
  {
    int j = 0;
    int i = 0;
    while (i < this.buckets.length)
    {
      for (Entry localEntry = this.buckets[i]; localEntry != null; localEntry = localEntry.next)
        j += 1;
      i += 1;
    }
    return j;
  }

  protected static final class Entry<K, V>
  {
    public final int hashCode;
    public final K key;
    public final Entry<K, V> next;
    public V value;

    public Entry(K paramK, V paramV, int paramInt, Entry<K, V> paramEntry)
    {
      this.key = paramK;
      this.value = paramV;
      this.next = paramEntry;
      this.hashCode = paramInt;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.IdentityHashMap
 * JD-Core Version:    0.6.2
 */