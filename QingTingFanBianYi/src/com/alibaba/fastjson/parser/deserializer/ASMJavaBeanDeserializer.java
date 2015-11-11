package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ASMJavaBeanDeserializer
  implements ObjectDeserializer
{
  protected InnerJavaBeanDeserializer serializer;

  public ASMJavaBeanDeserializer(ParserConfig paramParserConfig, Class<?> paramClass)
  {
    this.serializer = new InnerJavaBeanDeserializer(paramParserConfig, paramClass, null);
    this.serializer.getFieldDeserializerMap();
  }

  public FieldDeserializer createFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    return paramParserConfig.createFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
  }

  public Object createInstance(DefaultJSONParser paramDefaultJSONParser)
  {
    return this.serializer.createInstance(paramDefaultJSONParser, this.serializer.getClazz());
  }

  public abstract Object createInstance(DefaultJSONParser paramDefaultJSONParser, Type paramType);

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    return this.serializer.deserialze(paramDefaultJSONParser, paramType, paramObject);
  }

  public int getFastMatchToken()
  {
    return this.serializer.getFastMatchToken();
  }

  public FieldDeserializer getFieldDeserializer(String paramString)
  {
    return (FieldDeserializer)this.serializer.getFieldDeserializerMap().get(paramString);
  }

  public Type getFieldType(String paramString)
  {
    return ((FieldDeserializer)this.serializer.getFieldDeserializerMap().get(paramString)).getFieldType();
  }

  public InnerJavaBeanDeserializer getInnterSerializer()
  {
    return this.serializer;
  }

  public boolean parseField(DefaultJSONParser paramDefaultJSONParser, String paramString, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    Object localObject2 = this.serializer.getFieldDeserializerMap();
    FieldDeserializer localFieldDeserializer = (FieldDeserializer)((Map)localObject2).get(paramString);
    Object localObject1 = localFieldDeserializer;
    if (localFieldDeserializer == null)
    {
      localObject2 = ((Map)localObject2).entrySet().iterator();
      do
      {
        localObject1 = localFieldDeserializer;
        if (!((Iterator)localObject2).hasNext())
          break;
        localObject1 = (Map.Entry)((Iterator)localObject2).next();
      }
      while (!((String)((Map.Entry)localObject1).getKey()).equalsIgnoreCase(paramString));
      localObject1 = (FieldDeserializer)((Map.Entry)localObject1).getValue();
    }
    if (localObject1 == null)
    {
      this.serializer.parseExtra(paramDefaultJSONParser, paramObject, paramString);
      return false;
    }
    localJSONLexer.nextTokenWithColon(((FieldDeserializer)localObject1).getFastMatchToken());
    ((FieldDeserializer)localObject1).parseField(paramDefaultJSONParser, paramObject, paramType, paramMap);
    return true;
  }

  public Object parseRest(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2)
  {
    return this.serializer.deserialze(paramDefaultJSONParser, paramType, paramObject1, paramObject2);
  }

  public final class InnerJavaBeanDeserializer extends JavaBeanDeserializer
  {
    private InnerJavaBeanDeserializer(Class<?> arg2)
    {
      super(localClass);
    }

    public FieldDeserializer createFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
    {
      return ASMJavaBeanDeserializer.this.createFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    }

    public boolean parseField(DefaultJSONParser paramDefaultJSONParser, String paramString, Object paramObject, Type paramType, Map<String, Object> paramMap)
    {
      return ASMJavaBeanDeserializer.this.parseField(paramDefaultJSONParser, paramString, paramObject, paramType, paramMap);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer
 * JD-Core Version:    0.6.2
 */