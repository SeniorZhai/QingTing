package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class LongSerializer
  implements ObjectSerializer
{
  public static LongSerializer instance = new LongSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
      if (paramObject2.isEnabled(SerializerFeature.WriteNullNumberAsZero))
        paramObject2.write('0');
    long l;
    do
    {
      return;
      paramObject2.writeNull();
      return;
      l = ((Long)paramObject1).longValue();
      paramObject2.writeLong(l);
    }
    while ((!paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName)) || (l > 2147483647L) || (l < -2147483648L) || (paramType == Long.class));
    paramObject2.write('L');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.LongSerializer
 * JD-Core Version:    0.6.2
 */