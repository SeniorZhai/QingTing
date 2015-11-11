package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class IntArraySerializer
  implements ObjectSerializer
{
  public static IntArraySerializer instance = new IntArraySerializer();

  public final void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
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
    paramObject1 = (int[])paramObject1;
    paramJSONSerializer.write('[');
    int i = 0;
    while (i < paramObject1.length)
    {
      if (i != 0)
        paramJSONSerializer.write(',');
      paramJSONSerializer.writeInt(paramObject1[i]);
      i += 1;
    }
    paramJSONSerializer.write(']');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.IntArraySerializer
 * JD-Core Version:    0.6.2
 */