package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public class LongDeserializer
  implements ObjectDeserializer
{
  public static final LongDeserializer instance = new LongDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramObject = paramDefaultJSONParser.getLexer();
    long l;
    if (paramObject.token() == 2)
    {
      l = paramObject.longValue();
      paramObject.nextToken(16);
    }
    for (paramDefaultJSONParser = Long.valueOf(l); ; paramDefaultJSONParser = TypeUtils.castToLong(paramDefaultJSONParser))
    {
      paramObject = paramDefaultJSONParser;
      if (paramType == AtomicLong.class)
        paramObject = new AtomicLong(paramDefaultJSONParser.longValue());
      return paramObject;
      paramDefaultJSONParser = paramDefaultJSONParser.parse();
      if (paramDefaultJSONParser == null)
        return null;
    }
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.LongDeserializer
 * JD-Core Version:    0.6.2
 */