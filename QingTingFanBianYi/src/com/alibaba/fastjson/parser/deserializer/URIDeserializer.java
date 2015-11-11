package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.net.URI;

public class URIDeserializer
  implements ObjectDeserializer
{
  public static final URIDeserializer instance = new URIDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (String)paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null)
      return null;
    return URI.create(paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.URIDeserializer
 * JD-Core Version:    0.6.2
 */