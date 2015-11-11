package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class LongFieldDeserializer extends FieldDeserializer
{
  private final ObjectDeserializer fieldValueDeserilizer;

  public LongFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
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
    long l;
    if (paramType.token() == 2)
    {
      l = paramType.longValue();
      paramType.nextToken(16);
      if (paramObject == null)
        paramMap.put(this.fieldInfo.getName(), Long.valueOf(l));
    }
    while (true)
    {
      return;
      setValue(paramObject, l);
      return;
      if (paramType.token() == 8)
      {
        paramDefaultJSONParser = null;
        paramType.nextToken(16);
      }
      while ((paramDefaultJSONParser != null) || (getFieldClass() != Long.TYPE))
      {
        if (paramObject != null)
          break label131;
        paramMap.put(this.fieldInfo.getName(), paramDefaultJSONParser);
        return;
        paramDefaultJSONParser = TypeUtils.castToLong(paramDefaultJSONParser.parse());
      }
    }
    label131: setValue(paramObject, paramDefaultJSONParser);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.LongFieldDeserializer
 * JD-Core Version:    0.6.2
 */