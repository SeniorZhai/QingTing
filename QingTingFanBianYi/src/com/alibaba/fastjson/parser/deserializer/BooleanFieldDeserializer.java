package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class BooleanFieldDeserializer extends FieldDeserializer
{
  public BooleanFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    super(paramClass, paramFieldInfo);
  }

  public int getFastMatchToken()
  {
    return 6;
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    boolean bool = true;
    paramType = paramDefaultJSONParser.getLexer();
    if (paramType.token() == 6)
    {
      paramType.nextToken(16);
      if (paramObject == null)
        paramMap.put(this.fieldInfo.getName(), Boolean.TRUE);
    }
    do
    {
      do
      {
        return;
        setValue(paramObject, true);
        return;
        if (paramType.token() == 2)
        {
          int i = paramType.intValue();
          paramType.nextToken(16);
          if (i == 1);
          while (paramObject == null)
          {
            paramMap.put(this.fieldInfo.getName(), Boolean.valueOf(bool));
            return;
            bool = false;
          }
          setValue(paramObject, bool);
          return;
        }
        if (paramType.token() != 8)
          break;
        paramType.nextToken(16);
      }
      while ((getFieldClass() == Boolean.TYPE) || (paramObject == null));
      setValue(paramObject, null);
      return;
      if (paramType.token() == 7)
      {
        paramType.nextToken(16);
        if (paramObject == null)
        {
          paramMap.put(this.fieldInfo.getName(), Boolean.FALSE);
          return;
        }
        setValue(paramObject, false);
        return;
      }
      paramDefaultJSONParser = TypeUtils.castToBoolean(paramDefaultJSONParser.parse());
    }
    while ((paramDefaultJSONParser == null) && (getFieldClass() == Boolean.TYPE));
    if (paramObject == null)
    {
      paramMap.put(this.fieldInfo.getName(), paramDefaultJSONParser);
      return;
    }
    setValue(paramObject, paramDefaultJSONParser);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.BooleanFieldDeserializer
 * JD-Core Version:    0.6.2
 */