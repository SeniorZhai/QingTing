package com.alibaba.fastjson.parser.deserializer;

public abstract interface ExtraProcessor extends ParseProcess
{
  public abstract void processExtra(Object paramObject1, String paramString, Object paramObject2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ExtraProcessor
 * JD-Core Version:    0.6.2
 */