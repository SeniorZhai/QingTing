package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JavaBeanSerializer
  implements ObjectSerializer
{
  private final FieldSerializer[] getters;
  private final FieldSerializer[] sortedGetters;

  public JavaBeanSerializer(Class<?> paramClass)
  {
    this(paramClass, (Map)null);
  }

  public JavaBeanSerializer(Class<?> paramClass, Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = TypeUtils.computeGetters(paramClass, paramMap, false).iterator();
    while (localIterator.hasNext())
      localArrayList.add(createFieldSerializer((FieldInfo)localIterator.next()));
    this.getters = ((FieldSerializer[])localArrayList.toArray(new FieldSerializer[localArrayList.size()]));
    localArrayList = new ArrayList();
    paramClass = TypeUtils.computeGetters(paramClass, paramMap, true).iterator();
    while (paramClass.hasNext())
      localArrayList.add(createFieldSerializer((FieldInfo)paramClass.next()));
    this.sortedGetters = ((FieldSerializer[])localArrayList.toArray(new FieldSerializer[localArrayList.size()]));
  }

  public JavaBeanSerializer(Class<?> paramClass, String[] paramArrayOfString)
  {
    this(paramClass, createAliasMap(paramArrayOfString));
  }

  static Map<String, String> createAliasMap(String[] paramArrayOfString)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      localHashMap.put(str, str);
      i += 1;
    }
    return localHashMap;
  }

  public FieldSerializer createFieldSerializer(FieldInfo paramFieldInfo)
  {
    if (paramFieldInfo.getFieldClass() == Number.class)
      return new NumberFieldSerializer(paramFieldInfo);
    return new ObjectFieldSerializer(paramFieldInfo);
  }

  public FieldSerializer[] getGetters()
  {
    return this.getters;
  }

  protected boolean isWriteClassName(JSONSerializer paramJSONSerializer, Object paramObject1, Type paramType, Object paramObject2)
  {
    return paramJSONSerializer.isWriteClassName(paramType, paramObject1);
  }

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    SerializeWriter localSerializeWriter = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      localSerializeWriter.writeNull();
      return;
    }
    if (paramJSONSerializer.containsReference(paramObject1))
    {
      writeReference(paramJSONSerializer, paramObject1);
      return;
    }
    FieldSerializer[] arrayOfFieldSerializer;
    if (localSerializeWriter.isEnabled(SerializerFeature.SortField))
      arrayOfFieldSerializer = this.sortedGetters;
    SerialContext localSerialContext;
    boolean bool1;
    char c2;
    label80: char c1;
    label89: int j;
    int i;
    label176: label194: int k;
    label248: Object localObject;
    while (true)
    {
      localSerialContext = paramJSONSerializer.getContext();
      paramJSONSerializer.setContext(localSerialContext, paramObject1, paramObject2);
      bool1 = paramJSONSerializer.isWriteAsArray(paramObject1, paramType);
      if (bool1)
      {
        c2 = '[';
        if (!bool1)
          break label277;
        c1 = ']';
      }
      try
      {
        localSerializeWriter.append(c2);
        if ((arrayOfFieldSerializer.length > 0) && (localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat)))
        {
          paramJSONSerializer.incrementIndent();
          paramJSONSerializer.println();
        }
        j = 0;
        i = j;
        if (!isWriteClassName(paramJSONSerializer, paramObject1, paramType, paramObject2))
          break label578;
        i = j;
        if (paramObject1.getClass() == paramType)
          break label578;
        localSerializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
        paramJSONSerializer.write(paramObject1.getClass());
        i = 1;
        break label578;
        if (FilterUtils.writeBefore(paramJSONSerializer, paramObject1, c2) == ',')
        {
          i = 1;
          break label590;
          if (j >= arrayOfFieldSerializer.length)
            break label602;
          paramObject2 = arrayOfFieldSerializer[j];
          if (!paramJSONSerializer.isEnabled(SerializerFeature.SkipTransientField))
            break label296;
          paramType = paramObject2.getField();
          if (paramType == null)
            break label296;
          boolean bool2 = Modifier.isTransient(paramType.getModifiers());
          if (!bool2)
            break label296;
          k = i;
        }
        label277: label284: label296: String str;
        do
        {
          do
          {
            do
            {
              j += 1;
              i = k;
              break label194;
              arrayOfFieldSerializer = this.getters;
              break;
              c2 = '{';
              break label80;
              c1 = '}';
              break label89;
              c2 = '\000';
              break label176;
              i = 0;
              break label590;
              k = i;
            }
            while (!FilterUtils.applyName(paramJSONSerializer, paramObject1, paramObject2.getName()));
            paramType = paramObject2.getPropertyValue(paramObject1);
            k = i;
          }
          while (!FilterUtils.apply(paramJSONSerializer, paramObject1, paramObject2.getName(), paramType));
          str = FilterUtils.processKey(paramJSONSerializer, paramObject1, paramObject2.getName(), paramType);
          localObject = FilterUtils.processValue(paramJSONSerializer, paramObject1, paramObject2.getName(), paramType);
          if ((localObject != null) || (bool1) || (paramObject2.isWriteNull()))
            break label394;
          k = i;
        }
        while (!paramJSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue));
        label394: if (i != 0)
        {
          localSerializeWriter.append(',');
          if (localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat))
            paramJSONSerializer.println();
        }
        if (str != paramObject2.getName())
        {
          if (!bool1)
            localSerializeWriter.writeFieldName(str);
          paramJSONSerializer.write(localObject);
          break label596;
        }
        if (paramType != localObject)
          if (!bool1)
            paramObject2.writePrefix(paramJSONSerializer);
      }
      catch (Exception paramObject1)
      {
        throw new JSONException("write javaBean error", paramObject1);
      }
      finally
      {
        paramJSONSerializer.setContext(localSerialContext);
      }
    }
    if (!bool1)
      paramObject2.writeProperty(paramJSONSerializer, localObject);
    else
      paramObject2.writeValue(paramJSONSerializer, localObject);
    while (true)
    {
      label524: FilterUtils.writeAfter(paramJSONSerializer, paramObject1, c2);
      if ((arrayOfFieldSerializer.length > 0) && (localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat)))
      {
        paramJSONSerializer.decrementIdent();
        paramJSONSerializer.println();
      }
      localSerializeWriter.append(c1);
      paramJSONSerializer.setContext(localSerialContext);
      return;
      label578: label590: label596: label602: 
      do
      {
        c2 = '\000';
        break label524;
        if (i == 0)
          break label284;
        c2 = ',';
        break;
        j = 0;
        break label194;
        k = 1;
        break label248;
      }
      while (i == 0);
      c2 = ',';
    }
  }

  public void writeReference(JSONSerializer paramJSONSerializer, Object paramObject)
  {
    paramJSONSerializer.writeReference(paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.JavaBeanSerializer
 * JD-Core Version:    0.6.2
 */