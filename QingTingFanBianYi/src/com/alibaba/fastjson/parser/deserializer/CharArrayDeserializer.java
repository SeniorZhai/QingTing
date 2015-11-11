package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;

public class CharArrayDeserializer
  implements ObjectDeserializer
{
  public static final CharArrayDeserializer instance = new CharArrayDeserializer();

  public static <T> T deserialze(DefaultJSONParser paramDefaultJSONParser)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if (localJSONLexer.token() == 4)
    {
      paramDefaultJSONParser = localJSONLexer.stringVal();
      localJSONLexer.nextToken(16);
      return paramDefaultJSONParser.toCharArray();
    }
    if (localJSONLexer.token() == 2)
    {
      paramDefaultJSONParser = localJSONLexer.integerValue();
      localJSONLexer.nextToken(16);
      return paramDefaultJSONParser.toString().toCharArray();
    }
    paramDefaultJSONParser = paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null)
      return null;
    return JSON.toJSONString(paramDefaultJSONParser).toCharArray();
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    return deserialze(paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.CharArrayDeserializer
 * JD-Core Version:    0.6.2
 */