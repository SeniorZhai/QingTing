package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class BooleanSerializer
  implements ObjectSerializer
{
  public static final BooleanSerializer instance = new BooleanSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Boolean)paramObject1;
    if (paramObject1 == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullBooleanAsFalse))
      {
        paramJSONSerializer.write("false");
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    if (paramObject1.booleanValue())
    {
      paramJSONSerializer.write("true");
      return;
    }
    paramJSONSerializer.write("false");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.BooleanSerializer
 * JD-Core Version:    0.6.2
 */