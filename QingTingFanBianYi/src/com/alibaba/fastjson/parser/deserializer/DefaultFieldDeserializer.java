package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class DefaultFieldDeserializer extends FieldDeserializer
{
  private ObjectDeserializer fieldValueDeserilizer;

  public DefaultFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    super(paramClass, paramFieldInfo);
  }

  public int getFastMatchToken()
  {
    if (this.fieldValueDeserilizer != null)
      return this.fieldValueDeserilizer.getFastMatchToken();
    return 2;
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    if (this.fieldValueDeserilizer == null)
      this.fieldValueDeserilizer = paramDefaultJSONParser.getConfig().getDeserializer(this.fieldInfo);
    if ((paramType instanceof ParameterizedType))
      paramDefaultJSONParser.getContext().setType(paramType);
    paramType = this.fieldValueDeserilizer.deserialze(paramDefaultJSONParser, getFieldType(), this.fieldInfo.getName());
    if (paramDefaultJSONParser.getResolveStatus() == 1)
    {
      paramObject = paramDefaultJSONParser.getLastResolveTask();
      paramObject.setFieldDeserializer(this);
      paramObject.setOwnerContext(paramDefaultJSONParser.getContext());
      paramDefaultJSONParser.setResolveStatus(0);
      return;
    }
    if (paramObject == null)
    {
      paramMap.put(this.fieldInfo.getName(), paramType);
      return;
    }
    setValue(paramObject, paramType);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer
 * JD-Core Version:    0.6.2
 */