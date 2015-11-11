package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class IntegerSerializer
  implements ObjectSerializer
{
  public static IntegerSerializer instance = new IntegerSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Number)paramObject1;
    if (paramObject1 == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero))
      {
        paramJSONSerializer.write('0');
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.writeInt(paramObject1.intValue());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.IntegerSerializer
 * JD-Core Version:    0.6.2
 */