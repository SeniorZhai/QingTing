package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class CharArraySerializer
  implements ObjectSerializer
{
  public static CharArraySerializer instance = new CharArraySerializer();

  public final void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullListAsEmpty))
      {
        paramJSONSerializer.write("[]");
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.writeString(new String((char[])paramObject1));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.CharArraySerializer
 * JD-Core Version:    0.6.2
 */