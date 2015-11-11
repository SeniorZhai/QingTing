package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;

public class BigIntegerSerializer
  implements ObjectSerializer
{
  public static final BigIntegerSerializer instance = new BigIntegerSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
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
    paramJSONSerializer.write(((BigInteger)paramObject1).toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.BigIntegerSerializer
 * JD-Core Version:    0.6.2
 */