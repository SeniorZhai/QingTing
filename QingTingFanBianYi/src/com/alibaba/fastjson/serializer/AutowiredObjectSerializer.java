package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;
import java.util.Set;

public abstract interface AutowiredObjectSerializer extends ObjectSerializer
{
  public abstract Set<Type> getAutowiredFor();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.AutowiredObjectSerializer
 * JD-Core Version:    0.6.2
 */