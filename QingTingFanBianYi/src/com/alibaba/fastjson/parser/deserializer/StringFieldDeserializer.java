package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Map;

public class StringFieldDeserializer extends FieldDeserializer
{
  private final ObjectDeserializer fieldValueDeserilizer;

  public StringFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    super(paramClass, paramFieldInfo);
    this.fieldValueDeserilizer = paramParserConfig.getDeserializer(paramFieldInfo);
  }

  public int getFastMatchToken()
  {
    return this.fieldValueDeserilizer.getFastMatchToken();
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    paramType = paramDefaultJSONParser.getLexer();
    if (paramType.token() == 4)
    {
      paramDefaultJSONParser = paramType.stringVal();
      paramType.nextToken(16);
    }
    while (paramObject == null)
    {
      paramMap.put(this.fieldInfo.getName(), paramDefaultJSONParser);
      return;
      paramDefaultJSONParser = paramDefaultJSONParser.parse();
      if (paramDefaultJSONParser == null)
        paramDefaultJSONParser = null;
      else
        paramDefaultJSONParser = paramDefaultJSONParser.toString();
    }
    setValue(paramObject, paramDefaultJSONParser);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.StringFieldDeserializer
 * JD-Core Version:    0.6.2
 */