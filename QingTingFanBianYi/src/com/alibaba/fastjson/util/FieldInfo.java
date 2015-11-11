package com.alibaba.fastjson.util;

import com.alibaba.fastjson.annotation.JSONField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class FieldInfo
  implements Comparable<FieldInfo>
{
  private final Class<?> declaringClass;
  private final Field field;
  private final Class<?> fieldClass;
  private final Type fieldType;
  private boolean getOnly = false;
  private final Method method;
  private final String name;

  public FieldInfo(String paramString, Class<?> paramClass1, Class<?> paramClass2, Type paramType, Field paramField)
  {
    this.name = paramString;
    this.declaringClass = paramClass1;
    this.fieldClass = paramClass2;
    this.fieldType = paramType;
    this.method = null;
    this.field = paramField;
    if (paramField != null)
      paramField.setAccessible(true);
  }

  public FieldInfo(String paramString, Method paramMethod, Field paramField)
  {
    this(paramString, paramMethod, paramField, null, null);
  }

  public FieldInfo(String paramString, Method paramMethod, Field paramField, Class<?> paramClass, Type paramType)
  {
    this.name = paramString;
    this.method = paramMethod;
    this.field = paramField;
    if (paramMethod != null)
      paramMethod.setAccessible(true);
    if (paramField != null)
      paramField.setAccessible(true);
    if (paramMethod != null)
      if (paramMethod.getParameterTypes().length == 1)
      {
        paramString = paramMethod.getParameterTypes()[0];
        paramField = paramMethod.getGenericParameterTypes()[0];
        this.declaringClass = paramMethod.getDeclaringClass();
      }
    while (true)
    {
      if ((paramClass == null) || (paramString != Object.class) || (!(paramField instanceof TypeVariable)))
        break label164;
      paramMethod = getInheritGenericType(paramClass, (TypeVariable)paramField);
      if (paramMethod == null)
        break label164;
      this.fieldClass = TypeUtils.getClass(paramMethod);
      this.fieldType = paramMethod;
      return;
      paramString = paramMethod.getReturnType();
      paramField = paramMethod.getGenericReturnType();
      this.getOnly = true;
      break;
      paramString = paramField.getType();
      paramMethod = paramField.getGenericType();
      this.declaringClass = paramField.getDeclaringClass();
      paramField = paramMethod;
    }
    label164: paramClass = getFieldType(paramClass, paramType, paramField);
    paramMethod = paramString;
    if (paramClass != paramField)
    {
      if (!(paramClass instanceof ParameterizedType))
        break label208;
      paramMethod = TypeUtils.getClass(paramClass);
    }
    while (true)
    {
      this.fieldType = paramClass;
      this.fieldClass = paramMethod;
      return;
      label208: paramMethod = paramString;
      if ((paramClass instanceof Class))
        paramMethod = TypeUtils.getClass(paramClass);
    }
  }

  public static Type getFieldType(Class<?> paramClass, Type paramType1, Type paramType2)
  {
    if ((paramClass == null) || (paramType1 == null))
      return paramType2;
    if (!(paramType1 instanceof ParameterizedType))
      return paramType2;
    ParameterizedType localParameterizedType1;
    Object localObject;
    int i;
    if ((paramType2 instanceof TypeVariable))
    {
      localParameterizedType1 = (ParameterizedType)paramType1;
      localObject = (TypeVariable)paramType2;
      i = 0;
      while (i < paramClass.getTypeParameters().length)
      {
        if (paramClass.getTypeParameters()[i].getName().equals(((TypeVariable)localObject).getName()))
          return localParameterizedType1.getActualTypeArguments()[i];
        i += 1;
      }
    }
    if ((paramType2 instanceof ParameterizedType))
    {
      localParameterizedType1 = (ParameterizedType)paramType2;
      localObject = localParameterizedType1.getActualTypeArguments();
      i = 0;
      int j = 0;
      while (j < localObject.length)
      {
        TypeVariable localTypeVariable = localObject[j];
        int m = i;
        if ((localTypeVariable instanceof TypeVariable))
        {
          localTypeVariable = (TypeVariable)localTypeVariable;
          m = i;
          if ((paramType1 instanceof ParameterizedType))
          {
            ParameterizedType localParameterizedType2 = (ParameterizedType)paramType1;
            int k = 0;
            while (true)
            {
              m = i;
              if (k >= paramClass.getTypeParameters().length)
                break;
              if (paramClass.getTypeParameters()[k].getName().equals(localTypeVariable.getName()))
              {
                localObject[j] = localParameterizedType2.getActualTypeArguments()[k];
                i = 1;
              }
              k += 1;
            }
          }
        }
        j += 1;
        i = m;
      }
      if (i != 0)
        return new ParameterizedTypeImpl((Type[])localObject, localParameterizedType1.getOwnerType(), localParameterizedType1.getRawType());
    }
    return paramType2;
  }

  public static Type getInheritGenericType(Class<?> paramClass, TypeVariable<?> paramTypeVariable)
  {
    Object localObject = paramTypeVariable.getGenericDeclaration();
    Type localType;
    do
    {
      localType = paramClass.getGenericSuperclass();
      if (localType == null);
      while (true)
      {
        return null;
        if (!(localType instanceof ParameterizedType))
          break;
        paramClass = (ParameterizedType)localType;
        if (paramClass.getRawType() != localObject)
          break;
        localObject = ((GenericDeclaration)localObject).getTypeParameters();
        paramClass = paramClass.getActualTypeArguments();
        int i = 0;
        while (i < localObject.length)
        {
          if (localObject[i] == paramTypeVariable)
            return paramClass[i];
          i += 1;
        }
      }
      paramClass = TypeUtils.getClass(localType);
    }
    while (localType != null);
    return null;
  }

  public int compareTo(FieldInfo paramFieldInfo)
  {
    return this.name.compareTo(paramFieldInfo.name);
  }

  public Object get(Object paramObject)
    throws IllegalAccessException, InvocationTargetException
  {
    if (this.method != null)
      return this.method.invoke(paramObject, new Object[0]);
    return this.field.get(paramObject);
  }

  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    Annotation localAnnotation1 = null;
    if (this.method != null)
      localAnnotation1 = this.method.getAnnotation(paramClass);
    Annotation localAnnotation2 = localAnnotation1;
    if (localAnnotation1 == null)
    {
      localAnnotation2 = localAnnotation1;
      if (this.field != null)
        localAnnotation2 = this.field.getAnnotation(paramClass);
    }
    return localAnnotation2;
  }

  public Class<?> getDeclaringClass()
  {
    return this.declaringClass;
  }

  public Field getField()
  {
    return this.field;
  }

  public Class<?> getFieldClass()
  {
    return this.fieldClass;
  }

  public Type getFieldType()
  {
    return this.fieldType;
  }

  public String getFormat()
  {
    Object localObject1 = null;
    Object localObject2 = (JSONField)getAnnotation(JSONField.class);
    if (localObject2 != null)
    {
      localObject2 = ((JSONField)localObject2).format();
      localObject1 = localObject2;
      if (((String)localObject2).trim().length() == 0)
        localObject1 = null;
    }
    return localObject1;
  }

  public Method getMethod()
  {
    return this.method;
  }

  public String getName()
  {
    return this.name;
  }

  public boolean isGetOnly()
  {
    return this.getOnly;
  }

  public void set(Object paramObject1, Object paramObject2)
    throws IllegalAccessException, InvocationTargetException
  {
    if (this.method != null)
    {
      this.method.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    }
    this.field.set(paramObject1, paramObject2);
  }

  public void setAccessible(boolean paramBoolean)
    throws SecurityException
  {
    if (this.method != null)
    {
      this.method.setAccessible(paramBoolean);
      return;
    }
    this.field.setAccessible(paramBoolean);
  }

  public String toString()
  {
    return this.name;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.FieldInfo
 * JD-Core Version:    0.6.2
 */