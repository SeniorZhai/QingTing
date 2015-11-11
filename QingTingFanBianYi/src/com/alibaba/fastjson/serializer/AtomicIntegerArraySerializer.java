package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArraySerializer
  implements ObjectSerializer
{
  public static final AtomicIntegerArraySerializer instance = new AtomicIntegerArraySerializer();

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
    paramObject1 = (AtomicIntegerArray)paramObject1;
    int j = paramObject1.length();
    paramJSONSerializer.append('[');
    int i = 0;
    while (i < j)
    {
      int k = paramObject1.get(i);
      if (i != 0)
        paramJSONSerializer.write(',');
      paramJSONSerializer.writeInt(k);
      i += 1;
    }
    paramJSONSerializer.append(']');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.AtomicIntegerArraySerializer
 * JD-Core Version:    0.6.2
 */