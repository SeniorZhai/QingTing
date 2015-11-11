package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.TimeZone;

public class TimeZoneDeserializer
  implements ObjectDeserializer
{
  public static final TimeZoneDeserializer instance = new TimeZoneDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (String)paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null)
      return null;
    return TimeZone.getTimeZone(paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.TimeZoneDeserializer
 * JD-Core Version:    0.6.2
 */