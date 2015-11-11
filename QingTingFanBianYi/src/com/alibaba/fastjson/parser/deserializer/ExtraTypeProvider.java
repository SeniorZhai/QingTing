package com.alibaba.fastjson.parser.deserializer;

import java.lang.reflect.Type;

public abstract interface ExtraTypeProvider extends ParseProcess
{
  public abstract Type getExtraType(Object paramObject, String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider
 * JD-Core Version:    0.6.2
 */