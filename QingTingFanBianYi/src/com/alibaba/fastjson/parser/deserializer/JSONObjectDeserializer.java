package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;

public class JSONObjectDeserializer
  implements ObjectDeserializer
{
  public static final JSONObjectDeserializer instance = new JSONObjectDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    return paramDefaultJSONParser.parseObject();
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.JSONObjectDeserializer
 * JD-Core Version:    0.6.2
 */