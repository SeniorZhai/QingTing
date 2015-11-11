package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class CharacterSerializer
  implements ObjectSerializer
{
  public static final CharacterSerializer instance = new CharacterSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Character)paramObject1;
    if (paramObject1 == null)
    {
      paramJSONSerializer.writeString("");
      return;
    }
    if (paramObject1.charValue() == 0)
    {
      paramJSONSerializer.writeString("");
      return;
    }
    paramJSONSerializer.writeString(paramObject1.toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.CharacterSerializer
 * JD-Core Version:    0.6.2
 */