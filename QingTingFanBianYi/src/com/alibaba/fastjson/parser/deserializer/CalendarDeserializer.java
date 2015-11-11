package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

public class CalendarDeserializer
  implements ObjectDeserializer
{
  public static final CalendarDeserializer instance = new CalendarDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (Date)DateDeserializer.instance.deserialze(paramDefaultJSONParser, paramType, paramObject);
    paramType = Calendar.getInstance();
    paramType.setTime(paramDefaultJSONParser);
    return paramType;
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.CalendarDeserializer
 * JD-Core Version:    0.6.2
 */