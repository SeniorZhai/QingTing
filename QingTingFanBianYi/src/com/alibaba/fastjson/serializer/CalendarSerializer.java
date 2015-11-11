package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarSerializer
  implements ObjectSerializer
{
  public static final CalendarSerializer instance = new CalendarSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer.write(((Calendar)paramObject1).getTime());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.CalendarSerializer
 * JD-Core Version:    0.6.2
 */