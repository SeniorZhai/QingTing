package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArraySerializer
  implements ObjectSerializer
{
  public static final AtomicLongArraySerializer instance = new AtomicLongArraySerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullListAsEmpty))
      {
        paramJSONSerializer.write("[]");
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramObject1 = (AtomicLongArray)paramObject1;
    int j = paramObject1.length();
    paramJSONSerializer.append('[');
    int i = 0;
    while (i < j)
    {
      long l = paramObject1.get(i);
      if (i != 0)
        paramJSONSerializer.write(',');
      paramJSONSerializer.writeLong(l);
      i += 1;
    }
    paramJSONSerializer.append(']');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.AtomicLongArraySerializer
 * JD-Core Version:    0.6.2
 */