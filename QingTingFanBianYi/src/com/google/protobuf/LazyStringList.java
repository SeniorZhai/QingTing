package com.google.protobuf;

import java.util.List;

public abstract interface LazyStringList extends List<String>
{
  public abstract void add(ByteString paramByteString);

  public abstract ByteString getByteString(int paramInt);

  public abstract List<?> getUnderlyingElements();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.LazyStringList
 * JD-Core Version:    0.6.2
 */