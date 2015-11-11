package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLongArray;

public class AtomicLongArrayDeserializer
  implements ObjectDeserializer
{
  public static final AtomicLongArrayDeserializer instance = new AtomicLongArrayDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    if (paramDefaultJSONParser.getLexer().token() == 8)
    {
      paramDefaultJSONParser.getLexer().nextToken(16);
      paramDefaultJSONParser = null;
      return paramDefaultJSONParser;
    }
    paramObject = new JSONArray();
    paramDefaultJSONParser.parseArray(paramObject);
    paramType = new AtomicLongArray(paramObject.size());
    int i = 0;
    while (true)
    {
      paramDefaultJSONParser = paramType;
      if (i >= paramObject.size())
        break;
      paramType.set(i, paramObject.getLong(i).longValue());
      i += 1;
    }
  }

  public int getFastMatchToken()
  {
    return 14;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.AtomicLongArrayDeserializer
 * JD-Core Version:    0.6.2
 */