package com.alibaba.fastjson.serializer;

public abstract interface PropertyFilter extends SerializeFilter
{
  public abstract boolean apply(Object paramObject1, String paramString, Object paramObject2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.PropertyFilter
 * JD-Core Version:    0.6.2
 */