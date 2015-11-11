package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class AppendableSerializer
  implements ObjectSerializer
{
  public static final AppendableSerializer instance = new AppendableSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if (paramObject1 == null)
    {
      paramJSONSerializer = paramJSONSerializer.getWriter();
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullStringAsEmpty))
      {
        paramJSONSerializer.writeString("");
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.write(paramObject1.toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.AppendableSerializer
 * JD-Core Version:    0.6.2
 */