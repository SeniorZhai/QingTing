package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class FloatArraySerializer
  implements ObjectSerializer
{
  public static final FloatArraySerializer instance = new FloatArraySerializer();

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
    paramObject1 = (float[])paramObject1;
    int j = paramObject1.length - 1;
    if (j == -1)
    {
      paramJSONSerializer.append("[]");
      return;
    }
    paramJSONSerializer.append('[');
    int i = 0;
    if (i < j)
    {
      f = paramObject1[i];
      if (Float.isNaN(f))
        paramJSONSerializer.writeNull();
      while (true)
      {
        paramJSONSerializer.append(',');
        i += 1;
        break;
        paramJSONSerializer.append(Float.toString(f));
      }
    }
    float f = paramObject1[j];
    if (Float.isNaN(f))
      paramJSONSerializer.writeNull();
    while (true)
    {
      paramJSONSerializer.append(']');
      return;
      paramJSONSerializer.append(Float.toString(f));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.FloatArraySerializer
 * JD-Core Version:    0.6.2
 */