package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;

public class ClassDerializer
  implements ObjectDeserializer
{
  public static final ClassDerializer instance = new ClassDerializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = paramDefaultJSONParser.getLexer();
    if (paramDefaultJSONParser.token() == 8)
    {
      paramDefaultJSONParser.nextToken();
      return null;
    }
    if (paramDefaultJSONParser.token() != 4)
      throw new JSONException("expect className");
    paramType = paramDefaultJSONParser.stringVal();
    paramDefaultJSONParser.nextToken(16);
    return TypeUtils.loadClass(paramType);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ClassDerializer
 * JD-Core Version:    0.6.2
 */