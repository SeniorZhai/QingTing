package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimestampDeserializer extends AbstractDateDeserializer
  implements ObjectDeserializer
{
  public static final TimestampDeserializer instance = new TimestampDeserializer();

  protected <T> T cast(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null);
    do
    {
      return null;
      if ((paramObject2 instanceof Date))
        return new Timestamp(((Date)paramObject2).getTime());
      if ((paramObject2 instanceof Number))
        return new Timestamp(((Number)paramObject2).longValue());
      if (!(paramObject2 instanceof String))
        break;
      paramType = (String)paramObject2;
    }
    while (paramType.length() == 0);
    paramDefaultJSONParser = paramDefaultJSONParser.getDateFormat();
    try
    {
      paramDefaultJSONParser = new Timestamp(paramDefaultJSONParser.parse(paramType).getTime());
      return paramDefaultJSONParser;
    }
    catch (ParseException paramDefaultJSONParser)
    {
      return new Timestamp(Long.parseLong(paramType));
    }
    throw new JSONException("parse error");
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.TimestampDeserializer
 * JD-Core Version:    0.6.2
 */