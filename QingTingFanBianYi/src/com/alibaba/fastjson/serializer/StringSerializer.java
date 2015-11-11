package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class StringSerializer
  implements ObjectSerializer
{
  public static StringSerializer instance = new StringSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    write(paramJSONSerializer, (String)paramObject1);
  }

  public void write(JSONSerializer paramJSONSerializer, String paramString)
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (paramString == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullStringAsEmpty))
      {
        paramJSONSerializer.writeString("");
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.writeString(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.StringSerializer
 * JD-Core Version:    0.6.2
 */