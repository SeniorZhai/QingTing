package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;

public class JSONArrayDeserializer
  implements ObjectDeserializer
{
  public static final JSONArrayDeserializer instance = new JSONArrayDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramType = new JSONArray();
    paramDefaultJSONParser.parseArray(paramType);
    return paramType;
  }

  public int getFastMatchToken()
  {
    return 14;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.JSONArrayDeserializer
 * JD-Core Version:    0.6.2
 */