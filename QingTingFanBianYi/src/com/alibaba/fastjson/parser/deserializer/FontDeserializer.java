package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.awt.Font;
import java.lang.reflect.Type;

public class FontDeserializer
  implements ObjectDeserializer
{
  public static final FontDeserializer instance = new FontDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramObject = paramDefaultJSONParser.getLexer();
    if (paramObject.token() == 8)
    {
      paramObject.nextToken(16);
      return null;
    }
    if ((paramObject.token() != 12) && (paramObject.token() != 16))
      throw new JSONException("syntax error");
    paramObject.nextToken();
    int j = 0;
    int i = 0;
    paramDefaultJSONParser = null;
    if (paramObject.token() == 13)
    {
      paramObject.nextToken();
      return new Font(paramDefaultJSONParser, i, j);
    }
    int m;
    int k;
    if (paramObject.token() == 4)
    {
      paramType = paramObject.stringVal();
      paramObject.nextTokenWithColon(2);
      if (!paramType.equalsIgnoreCase("name"))
        break label227;
      if (paramObject.token() != 4)
        break label217;
      paramType = paramObject.stringVal();
      paramObject.nextToken();
      m = i;
      k = j;
    }
    while (true)
    {
      paramDefaultJSONParser = paramType;
      j = k;
      i = m;
      if (paramObject.token() != 16)
        break;
      paramObject.nextToken(4);
      paramDefaultJSONParser = paramType;
      j = k;
      i = m;
      break;
      throw new JSONException("syntax error");
      label217: throw new JSONException("syntax error");
      label227: if (paramType.equalsIgnoreCase("style"))
      {
        if (paramObject.token() == 2)
        {
          m = paramObject.intValue();
          paramObject.nextToken();
          paramType = paramDefaultJSONParser;
          k = j;
        }
        else
        {
          throw new JSONException("syntax error");
        }
      }
      else
      {
        if (!paramType.equalsIgnoreCase("size"))
          break label331;
        if (paramObject.token() != 2)
          break label321;
        k = paramObject.intValue();
        paramObject.nextToken();
        paramType = paramDefaultJSONParser;
        m = i;
      }
    }
    label321: throw new JSONException("syntax error");
    label331: throw new JSONException("syntax error, " + paramType);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.FontDeserializer
 * JD-Core Version:    0.6.2
 */