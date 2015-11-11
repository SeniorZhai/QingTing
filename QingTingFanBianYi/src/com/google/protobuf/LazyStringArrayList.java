package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractList<String>
  implements LazyStringList, RandomAccess
{
  public static final LazyStringList EMPTY = new UnmodifiableLazyStringList(new LazyStringArrayList());
  private final List<Object> list;

  public LazyStringArrayList()
  {
    this.list = new ArrayList();
  }

  public LazyStringArrayList(LazyStringList paramLazyStringList)
  {
    this.list = new ArrayList(paramLazyStringList.size());
    addAll(paramLazyStringList);
  }

  public LazyStringArrayList(List<String> paramList)
  {
    this.list = new ArrayList(paramList);
  }

  private String asString(Object paramObject)
  {
    if ((paramObject instanceof String))
      return (String)paramObject;
    return ((ByteString)paramObject).toStringUtf8();
  }

  public void add(int paramInt, String paramString)
  {
    this.list.add(paramInt, paramString);
    this.modCount += 1;
  }

  public void add(ByteString paramByteString)
  {
    this.list.add(paramByteString);
    this.modCount += 1;
  }

  public boolean addAll(int paramInt, Collection<? extends String> paramCollection)
  {
    if ((paramCollection instanceof LazyStringList))
      paramCollection = ((LazyStringList)paramCollection).getUnderlyingElements();
    while (true)
    {
      boolean bool = this.list.addAll(paramInt, paramCollection);
      this.modCount += 1;
      return bool;
    }
  }

  public boolean addAll(Collection<? extends String> paramCollection)
  {
    return addAll(size(), paramCollection);
  }

  public void clear()
  {
    this.list.clear();
    this.modCount += 1;
  }

  public String get(int paramInt)
  {
    Object localObject = this.list.get(paramInt);
    if ((localObject instanceof String))
      return (String)localObject;
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (((ByteString)localObject).isValidUtf8())
      this.list.set(paramInt, str);
    return str;
  }

  public ByteString getByteString(int paramInt)
  {
    Object localObject = this.list.get(paramInt);
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      this.list.set(paramInt, localObject);
      return localObject;
    }
    return (ByteString)localObject;
  }

  public List<?> getUnderlyingElements()
  {
    return Collections.unmodifiableList(this.list);
  }

  public String remove(int paramInt)
  {
    Object localObject = this.list.remove(paramInt);
    this.modCount += 1;
    return asString(localObject);
  }

  public String set(int paramInt, String paramString)
  {
    return asString(this.list.set(paramInt, paramString));
  }

  public int size()
  {
    return this.list.size();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.LazyStringArrayList
 * JD-Core Version:    0.6.2
 */