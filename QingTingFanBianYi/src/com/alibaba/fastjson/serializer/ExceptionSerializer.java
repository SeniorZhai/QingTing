package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;

public class ExceptionSerializer extends JavaBeanSerializer
{
  public ExceptionSerializer(Class<?> paramClass)
  {
    super(paramClass);
  }

  protected boolean isWriteClassName(JSONSerializer paramJSONSerializer, Object paramObject1, Type paramType, Object paramObject2)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ExceptionSerializer
 * JD-Core Version:    0.6.2
 */