package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public class BooleanDeserializer
  implements ObjectDeserializer
{
  public static final BooleanDeserializer instance = new BooleanDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramObject = paramDefaultJSONParser.getLexer();
    if (paramObject.token() == 6)
    {
      paramObject.nextToken(16);
      paramDefaultJSONParser = Boolean.TRUE;
    }
    while (true)
    {
      paramObject = paramDefaultJSONParser;
      if (paramType == AtomicBoolean.class)
        paramObject = new AtomicBoolean(paramDefaultJSONParser.booleanValue());
      return paramObject;
      if (paramObject.token() == 7)
      {
        paramObject.nextToken(16);
        paramDefaultJSONParser = Boolean.FALSE;
      }
      else if (paramObject.token() == 2)
      {
        int i = paramObject.intValue();
        paramObject.nextToken(16);
        if (i == 1)
          paramDefaultJSONParser = Boolean.TRUE;
        else
          paramDefaultJSONParser = Boolean.FALSE;
      }
      else
      {
        paramDefaultJSONParser = paramDefaultJSONParser.parse();
        if (paramDefaultJSONParser == null)
          return null;
        paramDefaultJSONParser = TypeUtils.castToBoolean(paramDefaultJSONParser);
      }
    }
  }

  public int getFastMatchToken()
  {
    return 6;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.BooleanDeserializer
 * JD-Core Version:    0.6.2
 */