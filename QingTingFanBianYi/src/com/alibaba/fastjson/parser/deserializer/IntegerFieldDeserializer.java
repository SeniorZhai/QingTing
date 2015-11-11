package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class IntegerFieldDeserializer extends FieldDeserializer
{
  public IntegerFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    super(paramClass, paramFieldInfo);
  }

  public int getFastMatchToken()
  {
    return 2;
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    paramType = paramDefaultJSONParser.getLexer();
    int i;
    if (paramType.token() == 2)
    {
      i = paramType.intValue();
      paramType.nextToken(16);
      if (paramObject == null)
        paramMap.put(this.fieldInfo.getName(), Integer.valueOf(i));
    }
    while (true)
    {
      return;
      setValue(paramObject, i);
      return;
      if (paramType.token() == 8)
      {
        paramDefaultJSONParser = null;
        paramType.nextToken(16);
      }
      while ((paramDefaultJSONParser != null) || (getFieldClass() != Integer.TYPE))
      {
        if (paramObject != null)
          break label131;
        paramMap.put(this.fieldInfo.getName(), paramDefaultJSONParser);
        return;
        paramDefaultJSONParser = TypeUtils.castToInt(paramDefaultJSONParser.parse());
      }
    }
    label131: setValue(paramObject, paramDefaultJSONParser);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.IntegerFieldDeserializer
 * JD-Core Version:    0.6.2
 */