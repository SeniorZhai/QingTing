package com.alibaba.fastjson.serializer;

public abstract interface PropertyPreFilter extends SerializeFilter
{
  public abstract boolean apply(JSONSerializer paramJSONSerializer, Object paramObject, String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.PropertyPreFilter
 * JD-Core Version:    0.6.2
 */