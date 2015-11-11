package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ByteSerializer
  implements ObjectSerializer
{
  public static ByteSerializer instance = new ByteSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if ((Number)paramObject1 == null)
      if (paramObject2.isEnabled(SerializerFeature.WriteNullNumberAsZero))
        paramObject2.write('0');
    do
    {
      return;
      paramObject2.writeNull();
      return;
      paramObject2.writeInt(((Number)paramObject1).shortValue());
    }
    while (!paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName));
    paramObject2.write('B');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ByteSerializer
 * JD-Core Version:    0.6.2
 */