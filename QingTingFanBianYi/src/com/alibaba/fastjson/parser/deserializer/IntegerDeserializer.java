package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class IntegerDeserializer
  implements ObjectDeserializer
{
  public static final IntegerDeserializer instance = new IntegerDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramObject = paramDefaultJSONParser.getLexer();
    if (paramObject.token() == 8)
    {
      paramObject.nextToken(16);
      paramObject = null;
      return paramObject;
    }
    if (paramObject.token() == 2)
    {
      int i = paramObject.intValue();
      paramObject.nextToken(16);
      paramDefaultJSONParser = Integer.valueOf(i);
    }
    while (true)
    {
      paramObject = paramDefaultJSONParser;
      if (paramType != AtomicInteger.class)
        break;
      return new AtomicInteger(paramDefaultJSONParser.intValue());
      if (paramObject.token() == 3)
      {
        paramDefaultJSONParser = paramObject.decimalValue();
        paramObject.nextToken(16);
        paramDefaultJSONParser = Integer.valueOf(paramDefaultJSONParser.intValue());
      }
      else
      {
        paramDefaultJSONParser = TypeUtils.castToInt(paramDefaultJSONParser.parse());
      }
    }
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.IntegerDeserializer
 * JD-Core Version:    0.6.2
 */