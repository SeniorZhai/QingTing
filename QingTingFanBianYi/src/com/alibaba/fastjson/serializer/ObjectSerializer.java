package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public abstract interface ObjectSerializer
{
  public abstract void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ObjectSerializer
 * JD-Core Version:    0.6.2
 */