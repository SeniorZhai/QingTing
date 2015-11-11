package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class FieldDeserializer
{
  protected final Class<?> clazz;
  protected final FieldInfo fieldInfo;

  public FieldDeserializer(Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    this.clazz = paramClass;
    this.fieldInfo = paramFieldInfo;
  }

  public int getFastMatchToken()
  {
    return 0;
  }

  public Field getField()
  {
    return this.fieldInfo.getField();
  }

  public Class<?> getFieldClass()
  {
    return this.fieldInfo.getFieldClass();
  }

  public Type getFieldType()
  {
    return this.fieldInfo.getFieldType();
  }

  public Method getMethod()
  {
    return this.fieldInfo.getMethod();
  }

  public abstract void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap);

  public void setValue(Object paramObject, int paramInt)
  {
    setValue(paramObject, Integer.valueOf(paramInt));
  }

  public void setValue(Object paramObject, long paramLong)
  {
    setValue(paramObject, Long.valueOf(paramLong));
  }

  public void setValue(Object paramObject1, Object paramObject2)
  {
    Object localObject = this.fieldInfo.getMethod();
    if (localObject != null)
    {
      try
      {
        if (!this.fieldInfo.isGetOnly())
          break label249;
        if (this.fieldInfo.getFieldClass() == AtomicInteger.class)
        {
          paramObject1 = (AtomicInteger)((Method)localObject).invoke(paramObject1, new Object[0]);
          if (paramObject1 == null)
            return;
          paramObject1.set(((AtomicInteger)paramObject2).get());
          return;
        }
        if (this.fieldInfo.getFieldClass() == AtomicLong.class)
        {
          paramObject1 = (AtomicLong)((Method)localObject).invoke(paramObject1, new Object[0]);
          if (paramObject1 == null)
            return;
          paramObject1.set(((AtomicLong)paramObject2).get());
          return;
        }
      }
      catch (Exception paramObject1)
      {
        throw new JSONException("set property error, " + this.fieldInfo.getName(), paramObject1);
      }
      if (this.fieldInfo.getFieldClass() == AtomicBoolean.class)
      {
        paramObject1 = (AtomicBoolean)((Method)localObject).invoke(paramObject1, new Object[0]);
        if (paramObject1 != null)
          paramObject1.set(((AtomicBoolean)paramObject2).get());
      }
      else if (Map.class.isAssignableFrom(((Method)localObject).getReturnType()))
      {
        paramObject1 = (Map)((Method)localObject).invoke(paramObject1, new Object[0]);
        if (paramObject1 != null)
          paramObject1.putAll((Map)paramObject2);
      }
      else
      {
        paramObject1 = (Collection)((Method)localObject).invoke(paramObject1, new Object[0]);
        if (paramObject1 != null)
        {
          paramObject1.addAll((Collection)paramObject2);
          return;
          label249: ((Method)localObject).invoke(paramObject1, new Object[] { paramObject2 });
        }
      }
    }
    else
    {
      localObject = this.fieldInfo.getField();
      if (localObject != null)
        try
        {
          ((Field)localObject).set(paramObject1, paramObject2);
          return;
        }
        catch (Exception paramObject1)
        {
          throw new JSONException("set property error, " + this.fieldInfo.getName(), paramObject1);
        }
    }
  }

  public void setValue(Object paramObject, String paramString)
  {
    setValue(paramObject, paramString);
  }

  public void setValue(Object paramObject, boolean paramBoolean)
  {
    setValue(paramObject, Boolean.valueOf(paramBoolean));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.FieldDeserializer
 * JD-Core Version:    0.6.2
 */