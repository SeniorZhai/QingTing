package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDeserializer
  implements ObjectDeserializer
{
  public static final URLDeserializer instance = new URLDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (String)paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null)
      return null;
    try
    {
      paramDefaultJSONParser = new URL(paramDefaultJSONParser);
      return paramDefaultJSONParser;
    }
    catch (MalformedURLException paramDefaultJSONParser)
    {
    }
    throw new JSONException("create url error", paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.URLDeserializer
 * JD-Core Version:    0.6.2
 */