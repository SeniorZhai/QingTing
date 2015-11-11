package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanSerializer
  implements ObjectSerializer
{
  public static final AtomicBooleanSerializer instance = new AtomicBooleanSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (((AtomicBoolean)paramObject1).get())
    {
      paramJSONSerializer.append("true");
      return;
    }
    paramJSONSerializer.append("false");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.AtomicBooleanSerializer
 * JD-Core Version:    0.6.2
 */