package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalSerializer
  implements ObjectSerializer
{
  public static final BigDecimalSerializer instance = new BigDecimalSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero))
        paramJSONSerializer.write('0');
    do
    {
      return;
      paramJSONSerializer.writeNull();
      return;
      paramObject1 = (BigDecimal)paramObject1;
      paramJSONSerializer.write(paramObject1.toString());
    }
    while ((!paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName)) || (paramType == BigDecimal.class) || (paramObject1.scale() != 0));
    paramJSONSerializer.write('.');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.BigDecimalSerializer
 * JD-Core Version:    0.6.2
 */