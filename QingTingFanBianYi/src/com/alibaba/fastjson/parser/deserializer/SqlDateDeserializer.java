package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class SqlDateDeserializer extends AbstractDateDeserializer
  implements ObjectDeserializer
{
  public static final SqlDateDeserializer instance = new SqlDateDeserializer();

  protected <T> T cast(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null);
    do
    {
      return null;
      if ((paramObject2 instanceof java.util.Date));
      for (paramDefaultJSONParser = new java.sql.Date(((java.util.Date)paramObject2).getTime()); ; paramDefaultJSONParser = new java.sql.Date(((Number)paramObject2).longValue()))
      {
        return paramDefaultJSONParser;
        if (!(paramObject2 instanceof Number))
          break;
      }
      if (!(paramObject2 instanceof String))
        break;
      paramObject1 = (String)paramObject2;
    }
    while (paramObject1.length() == 0);
    paramType = new JSONScanner(paramObject1);
    try
    {
      long l;
      if (paramType.scanISO8601DateIfMatch())
        l = paramType.getCalendar().getTimeInMillis();
      while (true)
      {
        return new java.sql.Date(l);
        paramDefaultJSONParser = paramDefaultJSONParser.getDateFormat();
        try
        {
          paramDefaultJSONParser = new java.sql.Date(paramDefaultJSONParser.parse(paramObject1).getTime());
          return paramDefaultJSONParser;
        }
        catch (ParseException paramDefaultJSONParser)
        {
          l = Long.parseLong(paramObject1);
        }
      }
    }
    finally
    {
      paramType.close();
    }
    throw new JSONException("parse error : " + paramObject2);
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer
 * JD-Core Version:    0.6.2
 */