package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Calendar;

public class TimeDeserializer
  implements ObjectDeserializer
{
  public static final TimeDeserializer instance = new TimeDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramType = paramDefaultJSONParser.getLexer();
    long l;
    if (paramType.token() == 16)
    {
      paramType.nextToken(4);
      if (paramType.token() != 4)
        throw new JSONException("syntax error");
      paramType.nextTokenWithColon(2);
      if (paramType.token() != 2)
        throw new JSONException("syntax error");
      l = paramType.longValue();
      paramType.nextToken(13);
      if (paramType.token() != 13)
        throw new JSONException("syntax error");
      paramType.nextToken(16);
      paramDefaultJSONParser = new Time(l);
    }
    do
    {
      return paramDefaultJSONParser;
      paramType = paramDefaultJSONParser.parse();
      if (paramType == null)
        return null;
      paramDefaultJSONParser = paramType;
    }
    while ((paramType instanceof Time));
    if ((paramType instanceof Number))
      return new Time(((Number)paramType).longValue());
    if ((paramType instanceof String))
    {
      paramDefaultJSONParser = (String)paramType;
      if (paramDefaultJSONParser.length() == 0)
        return null;
      paramType = new JSONScanner(paramDefaultJSONParser);
      if (paramType.scanISO8601DateIfMatch());
      for (l = paramType.getCalendar().getTimeInMillis(); ; l = Long.parseLong(paramDefaultJSONParser))
      {
        paramType.close();
        return new Time(l);
      }
    }
    throw new JSONException("parse error");
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.TimeDeserializer
 * JD-Core Version:    0.6.2
 */