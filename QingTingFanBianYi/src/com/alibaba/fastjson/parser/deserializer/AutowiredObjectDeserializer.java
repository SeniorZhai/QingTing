package com.alibaba.fastjson.parser.deserializer;

import java.lang.reflect.Type;
import java.util.Set;

public abstract interface AutowiredObjectDeserializer extends ObjectDeserializer
{
  public abstract Set<Type> getAutowiredFor();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer
 * JD-Core Version:    0.6.2
 */