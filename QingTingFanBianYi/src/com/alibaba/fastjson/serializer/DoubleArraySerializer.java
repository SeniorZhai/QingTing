package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class DoubleArraySerializer
  implements ObjectSerializer
{
  public static final DoubleArraySerializer instance = new DoubleArraySerializer();

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
    paramObject1 = (double[])paramObject1;
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
      d = paramObject1[i];
      if (Double.isNaN(d))
        paramJSONSerializer.writeNull();
      while (true)
      {
        paramJSONSerializer.append(',');
        i += 1;
        break;
        paramJSONSerializer.append(Double.toString(d));
      }
    }
    double d = paramObject1[j];
    if (Double.isNaN(d))
      paramJSONSerializer.writeNull();
    while (true)
    {
      paramJSONSerializer.append(']');
      return;
      paramJSONSerializer.append(Double.toString(d));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.DoubleArraySerializer
 * JD-Core Version:    0.6.2
 */