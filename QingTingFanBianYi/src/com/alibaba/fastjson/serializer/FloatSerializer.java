package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class FloatSerializer
  implements ObjectSerializer
{
  public static FloatSerializer instance = new FloatSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramType = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero))
        paramType.write('0');
    do
    {
      return;
      paramType.writeNull();
      return;
      float f = ((Float)paramObject1).floatValue();
      if (Float.isNaN(f))
      {
        paramType.writeNull();
        return;
      }
      if (Float.isInfinite(f))
      {
        paramType.writeNull();
        return;
      }
      paramObject2 = Float.toString(f);
      paramObject1 = paramObject2;
      if (paramObject2.endsWith(".0"))
        paramObject1 = paramObject2.substring(0, paramObject2.length() - 2);
      paramType.write(paramObject1);
    }
    while (!paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName));
    paramType.write('F');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.FloatSerializer
 * JD-Core Version:    0.6.2
 */