package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.awt.Color;
import java.lang.reflect.Type;

public class ColorDeserializer
  implements ObjectDeserializer
{
  public static final ColorDeserializer instance = new ColorDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = paramDefaultJSONParser.getLexer();
    if ((paramDefaultJSONParser.token() != 12) && (paramDefaultJSONParser.token() != 16))
      throw new JSONException("syntax error");
    paramDefaultJSONParser.nextToken();
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    if (paramDefaultJSONParser.token() == 13)
    {
      paramDefaultJSONParser.nextToken();
      return new Color(j, k, m, n);
    }
    int i;
    int i3;
    int i2;
    int i1;
    if (paramDefaultJSONParser.token() == 4)
    {
      paramType = paramDefaultJSONParser.stringVal();
      paramDefaultJSONParser.nextTokenWithColon(2);
      if (paramDefaultJSONParser.token() != 2)
        break label224;
      i = paramDefaultJSONParser.intValue();
      paramDefaultJSONParser.nextToken();
      if (!paramType.equalsIgnoreCase("r"))
        break label234;
      i3 = i;
      i2 = k;
      i1 = m;
      i = n;
    }
    while (true)
    {
      n = i;
      m = i1;
      k = i2;
      j = i3;
      if (paramDefaultJSONParser.token() != 16)
        break;
      paramDefaultJSONParser.nextToken(4);
      n = i;
      m = i1;
      k = i2;
      j = i3;
      break;
      throw new JSONException("syntax error");
      label224: throw new JSONException("syntax error");
      label234: if (paramType.equalsIgnoreCase("g"))
      {
        i2 = i;
        i = n;
        i1 = m;
        i3 = j;
      }
      else if (paramType.equalsIgnoreCase("b"))
      {
        i1 = i;
        i = n;
        i2 = k;
        i3 = j;
      }
      else
      {
        if (!paramType.equalsIgnoreCase("alpha"))
          break label314;
        i1 = m;
        i2 = k;
        i3 = j;
      }
    }
    label314: throw new JSONException("syntax error, " + paramType);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ColorDeserializer
 * JD-Core Version:    0.6.2
 */