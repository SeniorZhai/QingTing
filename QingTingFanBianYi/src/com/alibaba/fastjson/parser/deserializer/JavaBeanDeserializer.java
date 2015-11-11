package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.FilterUtils;
import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JavaBeanDeserializer
  implements ObjectDeserializer
{
  private DeserializeBeanInfo beanInfo;
  private final Class<?> clazz;
  private final Map<String, FieldDeserializer> feildDeserializerMap = new IdentityHashMap();
  private final List<FieldDeserializer> fieldDeserializers = new ArrayList();
  private final List<FieldDeserializer> sortedFieldDeserializers = new ArrayList();

  public JavaBeanDeserializer(ParserConfig paramParserConfig, Class<?> paramClass)
  {
    this(paramParserConfig, paramClass, paramClass);
  }

  public JavaBeanDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, Type paramType)
  {
    this.clazz = paramClass;
    this.beanInfo = DeserializeBeanInfo.computeSetters(paramClass, paramType);
    paramType = this.beanInfo.getFieldList().iterator();
    while (paramType.hasNext())
      addFieldDeserializer(paramParserConfig, paramClass, (FieldInfo)paramType.next());
    paramParserConfig = this.beanInfo.getSortedFieldList().iterator();
    while (paramParserConfig.hasNext())
    {
      paramClass = (FieldInfo)paramParserConfig.next();
      paramClass = (FieldDeserializer)this.feildDeserializerMap.get(paramClass.getName().intern());
      this.sortedFieldDeserializers.add(paramClass);
    }
  }

  private void addFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    String str = paramFieldInfo.getName().intern();
    paramParserConfig = createFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    this.feildDeserializerMap.put(str, paramParserConfig);
    this.fieldDeserializers.add(paramParserConfig);
  }

  public FieldDeserializer createFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    return paramParserConfig.createFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
  }

  public Object createInstance(DefaultJSONParser paramDefaultJSONParser, Type paramType)
  {
    Object localObject;
    if (((paramType instanceof Class)) && (this.clazz.isInterface()))
    {
      paramDefaultJSONParser = (Class)paramType;
      paramType = Thread.currentThread().getContextClassLoader();
      localObject = new JSONObject();
      return Proxy.newProxyInstance(paramType, new Class[] { paramDefaultJSONParser }, (InvocationHandler)localObject);
    }
    if (this.beanInfo.getDefaultConstructor() == null)
      return null;
    try
    {
      paramType = this.beanInfo.getDefaultConstructor();
      if (paramType.getParameterTypes().length == 0);
      for (paramType = paramType.newInstance(new Object[0]); paramDefaultJSONParser.isEnabled(Feature.InitStringFieldAsEmpty); paramType = paramType.newInstance(new Object[] { paramDefaultJSONParser.getContext().getObject() }))
      {
        paramDefaultJSONParser = this.beanInfo.getFieldList().iterator();
        while (true)
        {
          if (!paramDefaultJSONParser.hasNext())
            break label241;
          localObject = (FieldInfo)paramDefaultJSONParser.next();
          if (((FieldInfo)localObject).getFieldClass() == String.class)
            try
            {
              ((FieldInfo)localObject).set(paramType, "");
            }
            catch (Exception paramDefaultJSONParser)
            {
              throw new JSONException("create instance error, class " + this.clazz.getName(), paramDefaultJSONParser);
            }
        }
      }
    }
    catch (Exception paramDefaultJSONParser)
    {
      throw new JSONException("create instance error, class " + this.clazz.getName(), paramDefaultJSONParser);
    }
    label241: return paramType;
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    return deserialze(paramDefaultJSONParser, paramType, paramObject, null);
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if (localJSONLexer.token() == 8)
    {
      localJSONLexer.nextToken(16);
      return null;
    }
    Object localObject1 = paramDefaultJSONParser.getContext();
    Object localObject5 = localObject1;
    if (paramObject2 != null)
      localObject5 = ((ParseContext)localObject1).getParentContext();
    Object localObject9 = null;
    Object localObject3 = null;
    Object localObject4 = null;
    Object localObject2 = localObject3;
    localObject1 = paramObject2;
    try
    {
      if (localJSONLexer.token() == 13)
      {
        localObject2 = localObject3;
        localObject1 = paramObject2;
        localJSONLexer.nextToken(16);
        paramObject1 = paramObject2;
        if (paramObject2 == null)
        {
          localObject2 = localObject3;
          localObject1 = paramObject2;
          paramObject1 = createInstance(paramDefaultJSONParser, paramType);
        }
        if (0 != 0)
          throw new NullPointerException();
        paramDefaultJSONParser.setContext((ParseContext)localObject5);
        return paramObject1;
      }
      localObject2 = localObject3;
      localObject1 = paramObject2;
      if (localJSONLexer.token() == 14)
      {
        localObject2 = localObject3;
        localObject1 = paramObject2;
        if (localJSONLexer.isEnabled(Feature.SupportArrayToBean))
        {
          localObject2 = localObject3;
          localObject1 = paramObject2;
          paramType = deserialzeArrayMapping(paramDefaultJSONParser, paramType, paramObject1, paramObject2);
          if (0 != 0)
            throw new NullPointerException();
          paramDefaultJSONParser.setContext((ParseContext)localObject5);
          return paramType;
        }
      }
      localObject2 = localObject3;
      localObject1 = paramObject2;
      if (localJSONLexer.token() == 12)
        break label382;
      localObject2 = localObject3;
      localObject1 = paramObject2;
      if (localJSONLexer.token() == 16)
        break label382;
      localObject2 = localObject3;
      localObject1 = paramObject2;
      paramType = new StringBuffer().append("syntax error, expect {, actual ").append(localJSONLexer.tokenName()).append(", pos ").append(localJSONLexer.pos());
      localObject2 = localObject3;
      localObject1 = paramObject2;
      if ((paramObject1 instanceof String))
      {
        localObject2 = localObject3;
        localObject1 = paramObject2;
        paramType.append(", fieldName ").append(paramObject1);
      }
      localObject2 = localObject3;
      localObject1 = paramObject2;
      throw new JSONException(paramType.toString());
    }
    finally
    {
    }
    if (localObject2 != null)
      localObject2.setObject(localObject1);
    paramDefaultJSONParser.setContext((ParseContext)localObject5);
    throw paramType;
    label382: localObject2 = localObject3;
    localObject1 = paramObject2;
    Object localObject8 = localObject4;
    Object localObject6 = localObject9;
    Object localObject7 = paramObject2;
    if (paramDefaultJSONParser.getResolveStatus() == 2)
    {
      localObject2 = localObject3;
      localObject1 = paramObject2;
      paramDefaultJSONParser.setResolveStatus(0);
      localObject7 = paramObject2;
      localObject6 = localObject9;
      localObject8 = localObject4;
    }
    paramObject2 = localObject8;
    localObject1 = localObject7;
    localObject2 = localObject6;
    while (true)
    {
      while (true)
      {
        label447: localObject3 = localObject1;
        try
        {
          localObject7 = localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable());
          if (localObject7 == null)
          {
            localObject3 = localObject1;
            if (localJSONLexer.token() == 13)
            {
              localObject3 = localObject1;
              localJSONLexer.nextToken(16);
              localObject3 = localObject1;
              localObject4 = localObject2;
            }
          }
          while (true)
          {
            label506: localObject1 = localObject3;
            if (localObject3 != null)
              break label1663;
            if (paramObject2 != null)
              break label1522;
            localObject2 = localObject4;
            localObject1 = localObject3;
            paramObject2 = createInstance(paramDefaultJSONParser, paramType);
            paramType = (Type)localObject4;
            if (localObject4 == null)
            {
              localObject2 = localObject4;
              localObject1 = paramObject2;
              paramType = paramDefaultJSONParser.setContext((ParseContext)localObject5, paramObject2, paramObject1);
            }
            if (paramType != null)
              paramType.setObject(paramObject2);
            paramDefaultJSONParser.setContext((ParseContext)localObject5);
            return paramObject2;
            localObject3 = localObject1;
            if (localJSONLexer.token() == 16)
            {
              localObject3 = localObject1;
              if (paramDefaultJSONParser.isEnabled(Feature.AllowArbitraryCommas))
                break;
            }
            if ("$ref" == localObject7)
            {
              localObject3 = localObject1;
              localJSONLexer.nextTokenWithColon(4);
              localObject3 = localObject1;
              if (localJSONLexer.token() == 4)
              {
                localObject3 = localObject1;
                paramObject2 = localJSONLexer.stringVal();
                localObject3 = localObject1;
                if ("@".equals(paramObject2))
                {
                  localObject3 = localObject1;
                  localObject1 = ((ParseContext)localObject5).getObject();
                }
                while (true)
                {
                  localObject3 = localObject1;
                  localJSONLexer.nextToken(13);
                  localObject3 = localObject1;
                  if (localJSONLexer.token() == 13)
                    break;
                  throw new JSONException("illegal ref");
                  localObject3 = localObject1;
                  if ("..".equals(paramObject2))
                  {
                    localObject3 = localObject1;
                    paramType = ((ParseContext)localObject5).getParentContext();
                    localObject3 = localObject1;
                    if (paramType.getObject() != null)
                    {
                      localObject3 = localObject1;
                      localObject1 = paramType.getObject();
                    }
                    else
                    {
                      localObject3 = localObject1;
                      paramDefaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(paramType, paramObject2));
                      localObject3 = localObject1;
                      paramDefaultJSONParser.setResolveStatus(1);
                    }
                  }
                  else
                  {
                    localObject3 = localObject1;
                    if ("$".equals(paramObject2))
                    {
                      for (paramType = (Type)localObject5; ; paramType = paramType.getParentContext())
                      {
                        localObject3 = localObject1;
                        if (paramType.getParentContext() == null)
                          break;
                        localObject3 = localObject1;
                      }
                      localObject3 = localObject1;
                      if (paramType.getObject() != null)
                      {
                        localObject3 = localObject1;
                        localObject1 = paramType.getObject();
                      }
                      else
                      {
                        localObject3 = localObject1;
                        paramDefaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(paramType, paramObject2));
                        localObject3 = localObject1;
                        paramDefaultJSONParser.setResolveStatus(1);
                      }
                    }
                    else
                    {
                      localObject3 = localObject1;
                      paramDefaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask((ParseContext)localObject5, paramObject2));
                      localObject3 = localObject1;
                      paramDefaultJSONParser.setResolveStatus(1);
                    }
                  }
                }
              }
              throw new JSONException("illegal ref, " + JSONToken.name(localJSONLexer.token()));
              localObject3 = localObject1;
              localJSONLexer.nextToken(16);
              localObject3 = localObject1;
              paramDefaultJSONParser.setContext((ParseContext)localObject5, localObject1, paramObject1);
              if (localObject2 != null)
                localObject2.setObject(localObject1);
              paramDefaultJSONParser.setContext((ParseContext)localObject5);
              return localObject1;
            }
            localObject3 = localObject1;
            if (JSON.DEFAULT_TYPE_KEY != localObject7)
              break label1222;
            localObject3 = localObject1;
            localJSONLexer.nextTokenWithColon(4);
            localObject3 = localObject1;
            if (localJSONLexer.token() != 4)
              break label1207;
            localObject3 = localObject1;
            localObject4 = localJSONLexer.stringVal();
            localObject3 = localObject1;
            localJSONLexer.nextToken(16);
            localObject3 = localObject1;
            if (!(paramType instanceof Class))
              break label1156;
            localObject3 = localObject1;
            if (!((String)localObject4).equals(((Class)paramType).getName()))
              break label1156;
            localObject3 = localObject1;
            if (localJSONLexer.token() != 13)
              break;
            localObject3 = localObject1;
            localJSONLexer.nextToken();
            localObject4 = localObject2;
            localObject3 = localObject1;
          }
          label1156: localObject3 = localObject1;
          paramType = TypeUtils.loadClass((String)localObject4);
          localObject3 = localObject1;
          paramType = paramDefaultJSONParser.getConfig().getDeserializer(paramType).deserialze(paramDefaultJSONParser, paramType, paramObject1);
          if (localObject2 != null)
            localObject2.setObject(localObject1);
          paramDefaultJSONParser.setContext((ParseContext)localObject5);
          return paramType;
          label1207: throw new JSONException("syntax error");
          label1222: if ((localObject1 == null) && (paramObject2 == null))
          {
            localObject3 = localObject1;
            localObject4 = createInstance(paramDefaultJSONParser, paramType);
            if (localObject4 == null)
            {
              localObject3 = localObject4;
              paramObject2 = new HashMap(this.fieldDeserializers.size());
              label1271: localObject1 = localObject4;
              localObject6 = paramDefaultJSONParser.setContext((ParseContext)localObject5, localObject4, paramObject1);
              localObject3 = localObject4;
              localObject4 = localObject6;
            }
          }
          while (true)
          {
            localObject2 = localObject4;
            localObject1 = localObject3;
            if (!parseField(paramDefaultJSONParser, (String)localObject7, localObject3, paramType, paramObject2))
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
              localObject8 = paramObject2;
              localObject6 = localObject4;
              localObject7 = localObject3;
              if (localJSONLexer.token() != 13)
                break;
              localObject2 = localObject4;
              localObject1 = localObject3;
              localJSONLexer.nextToken();
              break label506;
            }
            localObject2 = localObject4;
            localObject1 = localObject3;
            if (localJSONLexer.token() == 16)
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
              break label447;
            }
            localObject2 = localObject4;
            localObject1 = localObject3;
            if (localJSONLexer.token() == 13)
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
              localJSONLexer.nextToken(16);
              break label506;
            }
            localObject2 = localObject4;
            localObject1 = localObject3;
            if (localJSONLexer.token() != 18)
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
              if (localJSONLexer.token() != 1)
                break label1851;
            }
            localObject2 = localObject4;
            throw new JSONException("syntax error, unexpect token " + JSONToken.name(localJSONLexer.token()));
            label1522: localObject2 = localObject4;
            localObject1 = localObject3;
            paramObject1 = this.beanInfo.getFieldList();
            localObject2 = localObject4;
            localObject1 = localObject3;
            int j = paramObject1.size();
            localObject2 = localObject4;
            localObject1 = localObject3;
            paramType = new Object[j];
            int i = 0;
            while (i < j)
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
              paramType[i] = paramObject2.get(((FieldInfo)paramObject1.get(i)).getName());
              i += 1;
            }
            localObject2 = localObject4;
            localObject1 = localObject3;
            paramObject1 = this.beanInfo.getCreatorConstructor();
            if (paramObject1 != null)
            {
              localObject2 = localObject4;
              localObject1 = localObject3;
            }
            while (true)
            {
              try
              {
                paramType = this.beanInfo.getCreatorConstructor().newInstance(paramType);
                localObject1 = paramType;
                label1663: if (localObject4 != null)
                  ((ParseContext)localObject4).setObject(localObject1);
                paramDefaultJSONParser.setContext((ParseContext)localObject5);
                return localObject1;
              }
              catch (Exception paramType)
              {
                localObject2 = localObject4;
                throw new JSONException("create instance error, " + this.beanInfo.getCreatorConstructor().toGenericString(), paramType);
              }
              localObject2 = localObject4;
              localObject1 = localObject3;
              paramObject1 = this.beanInfo.getFactoryMethod();
              localObject1 = localObject3;
              if (paramObject1 != null)
              {
                localObject2 = localObject4;
                localObject1 = localObject3;
                try
                {
                  paramType = this.beanInfo.getFactoryMethod().invoke(null, paramType);
                }
                catch (Exception paramType)
                {
                  localObject2 = localObject4;
                  throw new JSONException("create factory method error, " + this.beanInfo.getFactoryMethod().toString(), paramType);
                }
              }
            }
            break label1271;
            localObject4 = localObject2;
            localObject3 = localObject1;
          }
        }
        finally
        {
          localObject1 = localObject3;
        }
      }
      break;
      label1851: localObject2 = localObject4;
      localObject1 = localObject3;
    }
  }

  public <T> T deserialzeArrayMapping(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2)
  {
    paramObject1 = paramDefaultJSONParser.getLexer();
    if (paramObject1.token() != 14)
      throw new JSONException("error");
    paramType = createInstance(paramDefaultJSONParser, paramType);
    int j = this.sortedFieldDeserializers.size();
    int i = 0;
    if (i < j)
    {
      char c;
      label68: Class localClass;
      if (i == j - 1)
      {
        c = ']';
        paramObject2 = (FieldDeserializer)this.sortedFieldDeserializers.get(i);
        localClass = paramObject2.getFieldClass();
        if (localClass != Integer.TYPE)
          break label129;
        paramObject2.setValue(paramType, paramObject1.scanInt(c));
      }
      label129: label272: 
      do
        while (true)
        {
          i += 1;
          break;
          c = ',';
          break label68;
          if (localClass == String.class)
          {
            paramObject2.setValue(paramType, paramObject1.scanString(c));
          }
          else if (localClass == Long.TYPE)
          {
            paramObject2.setValue(paramType, paramObject1.scanLong(c));
          }
          else if (localClass.isEnum())
          {
            paramObject2.setValue(paramType, paramObject1.scanEnum(localClass, paramDefaultJSONParser.getSymbolTable(), c));
          }
          else
          {
            paramObject1.nextToken(14);
            paramObject2.setValue(paramType, paramDefaultJSONParser.parseObject(paramObject2.getFieldType()));
            if (c != ']')
              break label272;
            if (paramObject1.token() != 15)
              throw new JSONException("syntax error");
            paramObject1.nextToken(16);
          }
        }
      while ((c != ',') || (paramObject1.token() == 16));
      throw new JSONException("syntax error");
    }
    paramObject1.nextToken(16);
    return paramType;
  }

  public Class<?> getClazz()
  {
    return this.clazz;
  }

  public int getFastMatchToken()
  {
    return 12;
  }

  public Map<String, FieldDeserializer> getFieldDeserializerMap()
  {
    return this.feildDeserializerMap;
  }

  void parseExtra(DefaultJSONParser paramDefaultJSONParser, Object paramObject, String paramString)
  {
    Object localObject = paramDefaultJSONParser.getLexer();
    if (!((JSONLexer)localObject).isEnabled(Feature.IgnoreNotMatch))
      throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + paramString);
    ((JSONLexer)localObject).nextTokenWithColon();
    localObject = FilterUtils.getExtratype(paramDefaultJSONParser, paramObject, paramString);
    if (localObject == null);
    for (localObject = paramDefaultJSONParser.parse(); ; localObject = paramDefaultJSONParser.parseObject((Type)localObject))
    {
      FilterUtils.processExtra(paramDefaultJSONParser, paramObject, paramString, localObject);
      return;
    }
  }

  public boolean parseField(DefaultJSONParser paramDefaultJSONParser, String paramString, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    FieldDeserializer localFieldDeserializer = (FieldDeserializer)this.feildDeserializerMap.get(paramString);
    Object localObject = localFieldDeserializer;
    if (localFieldDeserializer == null)
    {
      Iterator localIterator = this.feildDeserializerMap.entrySet().iterator();
      do
      {
        localObject = localFieldDeserializer;
        if (!localIterator.hasNext())
          break;
        localObject = (Map.Entry)localIterator.next();
      }
      while (!((String)((Map.Entry)localObject).getKey()).equalsIgnoreCase(paramString));
      localObject = (FieldDeserializer)((Map.Entry)localObject).getValue();
    }
    if (localObject == null)
    {
      parseExtra(paramDefaultJSONParser, paramObject, paramString);
      return false;
    }
    localJSONLexer.nextTokenWithColon(((FieldDeserializer)localObject).getFastMatchToken());
    ((FieldDeserializer)localObject).parseField(paramDefaultJSONParser, paramObject, paramType, paramMap);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer
 * JD-Core Version:    0.6.2
 */