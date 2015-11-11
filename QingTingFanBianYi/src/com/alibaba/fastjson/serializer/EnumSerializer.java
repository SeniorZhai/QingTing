package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class EnumSerializer
  implements ObjectSerializer
{
  public static final EnumSerializer instance = new EnumSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      paramJSONSerializer.getWriter().writeNull();
      return;
    }
    if (paramJSONSerializer.isEnabled(SerializerFeature.WriteEnumUsingToString))
    {
      paramJSONSerializer.write(((Enum)paramObject1).name());
      return;
    }
    paramObject2.writeInt(((Enum)paramObject1).ordinal());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.EnumSerializer
 * JD-Core Version:    0.6.2
 */