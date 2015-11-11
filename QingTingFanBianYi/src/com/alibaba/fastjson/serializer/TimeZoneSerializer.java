package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.TimeZone;

public class TimeZoneSerializer
  implements ObjectSerializer
{
  public static final TimeZoneSerializer instance = new TimeZoneSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if (paramObject1 == null)
    {
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.write(((TimeZone)paramObject1).getID());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.TimeZoneSerializer
 * JD-Core Version:    0.6.2
 */