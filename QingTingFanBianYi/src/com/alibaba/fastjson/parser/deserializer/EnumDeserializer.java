package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EnumDeserializer
  implements ObjectDeserializer
{
  private final Class<?> enumClass;
  private final Map<String, Enum> nameMap = new HashMap();
  private final Map<Integer, Enum> ordinalMap = new HashMap();

  public EnumDeserializer(Class<?> paramClass)
  {
    this.enumClass = paramClass;
    try
    {
      Object[] arrayOfObject = (Object[])paramClass.getMethod("values", new Class[0]).invoke(null, new Object[0]);
      int j = arrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        Enum localEnum = (Enum)arrayOfObject[i];
        this.ordinalMap.put(Integer.valueOf(localEnum.ordinal()), localEnum);
        this.nameMap.put(localEnum.name(), localEnum);
        i += 1;
      }
    }
    catch (Exception localException)
    {
      throw new JSONException("init enum values error, " + paramClass.getName());
    }
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    try
    {
      paramType = paramDefaultJSONParser.getLexer();
      if (paramType.token() == 2)
      {
        paramDefaultJSONParser = Integer.valueOf(paramType.intValue());
        paramType.nextToken(16);
        paramType = this.ordinalMap.get(paramDefaultJSONParser);
        if (paramType != null)
          break label232;
        throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + paramDefaultJSONParser);
      }
    }
    catch (JSONException paramDefaultJSONParser)
    {
      throw paramDefaultJSONParser;
      if (paramType.token() == 4)
      {
        paramDefaultJSONParser = paramType.stringVal();
        paramType.nextToken(16);
        if (paramDefaultJSONParser.length() == 0)
          return (Object)null;
        this.nameMap.get(paramDefaultJSONParser);
        return Enum.valueOf(this.enumClass, paramDefaultJSONParser);
      }
      if (paramType.token() == 8)
      {
        paramType.nextToken(16);
        return null;
      }
    }
    catch (Throwable paramDefaultJSONParser)
    {
      throw new JSONException(paramDefaultJSONParser.getMessage(), paramDefaultJSONParser);
    }
    paramDefaultJSONParser = paramDefaultJSONParser.parse();
    throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + paramDefaultJSONParser);
    label232: return paramType;
  }

  public int getFastMatchToken()
  {
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.EnumDeserializer
 * JD-Core Version:    0.6.2
 */