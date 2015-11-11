package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.awt.Point;
import java.lang.reflect.Type;

public class PointDeserializer
  implements ObjectDeserializer
{
  public static final PointDeserializer instance = new PointDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramType = paramDefaultJSONParser.getLexer();
    if (paramType.token() == 8)
    {
      paramType.nextToken(16);
      return null;
    }
    if ((paramType.token() != 12) && (paramType.token() != 16))
      throw new JSONException("syntax error");
    paramType.nextToken();
    int j = 0;
    int i = 0;
    while (true)
    {
      if (paramType.token() == 13)
      {
        paramType.nextToken();
        return new Point(j, i);
      }
      if (paramType.token() != 4)
        break label216;
      paramObject = paramType.stringVal();
      if (!JSON.DEFAULT_TYPE_KEY.equals(paramObject))
        break;
      paramDefaultJSONParser.acceptType("java.awt.Point");
    }
    paramType.nextTokenWithColon(2);
    int k;
    int m;
    if (paramType.token() == 2)
    {
      k = paramType.intValue();
      paramType.nextToken();
      if (!paramObject.equalsIgnoreCase("x"))
        break label258;
      m = i;
    }
    while (true)
    {
      j = k;
      i = m;
      if (paramType.token() != 16)
        break;
      paramType.nextToken(4);
      j = k;
      i = m;
      break;
      label216: throw new JSONException("syntax error");
      throw new JSONException("syntax error : " + paramType.tokenName());
      label258: if (!paramObject.equalsIgnoreCase("y"))
        break label278;
      m = k;
      k = j;
    }
    label278: throw new JSONException("syntax error, " + paramObject);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.PointDeserializer
 * JD-Core Version:    0.6.2
 */