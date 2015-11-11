package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Locale;

public class LocaleDeserializer
  implements ObjectDeserializer
{
  public static final LocaleDeserializer instance = new LocaleDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (String)paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null)
      return null;
    paramDefaultJSONParser = paramDefaultJSONParser.split("_");
    if (paramDefaultJSONParser.length == 1)
      return new Locale(paramDefaultJSONParser[0]);
    if (paramDefaultJSONParser.length == 2)
      return new Locale(paramDefaultJSONParser[0], paramDefaultJSONParser[1]);
    return new Locale(paramDefaultJSONParser[0], paramDefaultJSONParser[1], paramDefaultJSONParser[2]);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.LocaleDeserializer
 * JD-Core Version:    0.6.2
 */