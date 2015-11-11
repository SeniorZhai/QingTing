package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DeserializeBeanInfo
{
  private final Class<?> clazz;
  private Constructor<?> creatorConstructor;
  private Constructor<?> defaultConstructor;
  private Method factoryMethod;
  private final List<FieldInfo> fieldList = new ArrayList();
  private final List<FieldInfo> sortedFieldList = new ArrayList();

  public DeserializeBeanInfo(Class<?> paramClass)
  {
    this.clazz = paramClass;
  }

  public static DeserializeBeanInfo computeSetters(Class<?> paramClass, Type paramType)
  {
    DeserializeBeanInfo localDeserializeBeanInfo = new DeserializeBeanInfo(paramClass);
    Object localObject1 = getDefaultConstructor(paramClass);
    Object localObject4;
    label44: Object localObject5;
    if (localObject1 != null)
    {
      ((Constructor)localObject1).setAccessible(true);
      localDeserializeBeanInfo.setDefaultConstructor((Constructor)localObject1);
      localObject4 = paramClass.getMethods();
      j = localObject4.length;
      i = 0;
      if (i >= j)
        break label881;
      localObject5 = localObject4[i];
      localObject3 = ((Method)localObject5).getName();
      if (((String)localObject3).length() >= 4)
        break label477;
    }
    Object localObject2;
    label853: 
    while (true)
    {
      i += 1;
      break label44;
      if ((localObject1 != null) || (paramClass.isInterface()) || (Modifier.isAbstract(paramClass.getModifiers())))
        break;
      localObject2 = getCreatorConstructor(paramClass);
      if (localObject2 != null)
      {
        ((Constructor)localObject2).setAccessible(true);
        localDeserializeBeanInfo.setCreatorConstructor((Constructor)localObject2);
        i = 0;
        while (i < ((Constructor)localObject2).getParameterTypes().length)
        {
          localObject3 = localObject2.getParameterAnnotations()[i];
          localObject1 = null;
          k = localObject3.length;
          j = 0;
          while (true)
          {
            paramType = (Type)localObject1;
            if (j < k)
            {
              paramType = localObject3[j];
              if ((paramType instanceof JSONField))
                paramType = (JSONField)paramType;
            }
            else
            {
              if (paramType != null)
                break;
              throw new JSONException("illegal json creator");
            }
            j += 1;
          }
          localObject1 = localObject2.getParameterTypes()[i];
          localObject3 = localObject2.getGenericParameterTypes()[i];
          localObject4 = getField(paramClass, paramType.name());
          localDeserializeBeanInfo.add(new FieldInfo(paramType.name(), paramClass, (Class)localObject1, (Type)localObject3, (Field)localObject4));
          i += 1;
        }
      }
      localObject2 = getFactoryMethod(paramClass);
      if (localObject2 != null)
      {
        ((Method)localObject2).setAccessible(true);
        localDeserializeBeanInfo.setFactoryMethod((Method)localObject2);
        i = 0;
        while (i < ((Method)localObject2).getParameterTypes().length)
        {
          localObject3 = localObject2.getParameterAnnotations()[i];
          localObject1 = null;
          k = localObject3.length;
          j = 0;
          while (true)
          {
            paramType = (Type)localObject1;
            if (j < k)
            {
              paramType = localObject3[j];
              if ((paramType instanceof JSONField))
                paramType = (JSONField)paramType;
            }
            else
            {
              if (paramType != null)
                break;
              throw new JSONException("illegal json creator");
            }
            j += 1;
          }
          localObject1 = localObject2.getParameterTypes()[i];
          localObject3 = localObject2.getGenericParameterTypes()[i];
          localObject4 = getField(paramClass, paramType.name());
          localDeserializeBeanInfo.add(new FieldInfo(paramType.name(), paramClass, (Class)localObject1, (Type)localObject3, (Field)localObject4));
          i += 1;
        }
      }
      throw new JSONException("default constructor not found. " + paramClass);
      label477: if ((!Modifier.isStatic(((Method)localObject5).getModifiers())) && ((((Method)localObject5).getReturnType().equals(Void.TYPE)) || (((Method)localObject5).getReturnType().equals(paramClass))) && (((Method)localObject5).getParameterTypes().length == 1))
      {
        localObject2 = (JSONField)((Method)localObject5).getAnnotation(JSONField.class);
        localObject1 = localObject2;
        if (localObject2 == null)
          localObject1 = TypeUtils.getSupperMethodAnnotation(paramClass, (Method)localObject5);
        if (localObject1 != null)
        {
          if (((JSONField)localObject1).deserialize())
            if (((JSONField)localObject1).name().length() != 0)
            {
              localDeserializeBeanInfo.add(new FieldInfo(((JSONField)localObject1).name(), (Method)localObject5, null, paramClass, paramType));
              ((Method)localObject5).setAccessible(true);
            }
        }
        else if (((String)localObject3).startsWith("set"))
        {
          char c = ((String)localObject3).charAt(3);
          if (Character.isUpperCase(c))
            if (TypeUtils.compatibleWithJavaBean)
              localObject1 = Introspector.decapitalize(((String)localObject3).substring(3));
          while (true)
          {
            localObject3 = getField(paramClass, (String)localObject1);
            localObject2 = localObject3;
            if (localObject3 == null)
            {
              localObject2 = localObject3;
              if (localObject5.getParameterTypes()[0] == Boolean.TYPE)
                localObject2 = getField(paramClass, "is" + Character.toUpperCase(((String)localObject1).charAt(0)) + ((String)localObject1).substring(1));
            }
            if (localObject2 == null)
              break label853;
            localObject3 = (JSONField)((Field)localObject2).getAnnotation(JSONField.class);
            if ((localObject3 == null) || (((JSONField)localObject3).name().length() == 0))
              break label853;
            localDeserializeBeanInfo.add(new FieldInfo(((JSONField)localObject3).name(), (Method)localObject5, (Field)localObject2, paramClass, paramType));
            break;
            localObject1 = Character.toLowerCase(((String)localObject3).charAt(3)) + ((String)localObject3).substring(4);
            continue;
            if (c == '_')
            {
              localObject1 = ((String)localObject3).substring(4);
            }
            else
            {
              if (c != 'f')
                break;
              localObject1 = ((String)localObject3).substring(3);
            }
          }
          localDeserializeBeanInfo.add(new FieldInfo((String)localObject1, (Method)localObject5, null, paramClass, paramType));
          ((Method)localObject5).setAccessible(true);
        }
      }
    }
    label881: Object localObject3 = paramClass.getFields();
    int k = localObject3.length;
    int i = 0;
    if (i < k)
    {
      localObject4 = localObject3[i];
      if (Modifier.isStatic(((Field)localObject4).getModifiers()));
      while (true)
      {
        i += 1;
        break;
        j = 0;
        localObject1 = localDeserializeBeanInfo.getFieldList().iterator();
        while (((Iterator)localObject1).hasNext())
          if (((FieldInfo)((Iterator)localObject1).next()).getName().equals(((Field)localObject4).getName()))
            j = 1;
        if (j == 0)
        {
          localObject2 = ((Field)localObject4).getName();
          localObject5 = (JSONField)((Field)localObject4).getAnnotation(JSONField.class);
          localObject1 = localObject2;
          if (localObject5 != null)
          {
            localObject1 = localObject2;
            if (((JSONField)localObject5).name().length() != 0)
              localObject1 = ((JSONField)localObject5).name();
          }
          localDeserializeBeanInfo.add(new FieldInfo((String)localObject1, null, (Field)localObject4, paramClass, paramType));
        }
      }
    }
    localObject1 = paramClass.getMethods();
    int j = localObject1.length;
    i = 0;
    if (i < j)
    {
      localObject2 = localObject1[i];
      localObject3 = ((Method)localObject2).getName();
      if (((String)localObject3).length() < 4);
      while (true)
      {
        i += 1;
        break;
        if ((!Modifier.isStatic(((Method)localObject2).getModifiers())) && (((String)localObject3).startsWith("get")) && (Character.isUpperCase(((String)localObject3).charAt(3))) && (((Method)localObject2).getParameterTypes().length == 0) && ((Collection.class.isAssignableFrom(((Method)localObject2).getReturnType())) || (Map.class.isAssignableFrom(((Method)localObject2).getReturnType())) || (AtomicBoolean.class == ((Method)localObject2).getReturnType()) || (AtomicInteger.class == ((Method)localObject2).getReturnType()) || (AtomicLong.class == ((Method)localObject2).getReturnType())))
        {
          localObject3 = Character.toLowerCase(((String)localObject3).charAt(3)) + ((String)localObject3).substring(4);
          if (localDeserializeBeanInfo.getField((String)localObject3) == null)
          {
            localDeserializeBeanInfo.add(new FieldInfo((String)localObject3, (Method)localObject2, null, paramClass, paramType));
            ((Method)localObject2).setAccessible(true);
          }
        }
      }
    }
    return localDeserializeBeanInfo;
  }

  public static Constructor<?> getCreatorConstructor(Class<?> paramClass)
  {
    Object localObject = null;
    Constructor[] arrayOfConstructor = paramClass.getDeclaredConstructors();
    int j = arrayOfConstructor.length;
    int i = 0;
    while (true)
    {
      paramClass = localObject;
      if (i < j)
      {
        paramClass = arrayOfConstructor[i];
        if ((JSONCreator)paramClass.getAnnotation(JSONCreator.class) == null)
          break label55;
        if (0 != 0)
          throw new JSONException("multi-json creator");
      }
      return paramClass;
      label55: i += 1;
    }
  }

  public static Constructor<?> getDefaultConstructor(Class<?> paramClass)
  {
    Object localObject2;
    if (Modifier.isAbstract(paramClass.getModifiers()))
      localObject2 = null;
    label28: Object localObject1;
    do
    {
      do
      {
        do
        {
          return localObject2;
          localObject2 = null;
          arrayOfConstructor = paramClass.getDeclaredConstructors();
          j = arrayOfConstructor.length;
          i = 0;
          localObject1 = localObject2;
          if (i < j)
          {
            localObject1 = arrayOfConstructor[i];
            if (((Constructor)localObject1).getParameterTypes().length != 0)
              break;
          }
          localObject2 = localObject1;
        }
        while (localObject1 != null);
        localObject2 = localObject1;
      }
      while (!paramClass.isMemberClass());
      localObject2 = localObject1;
    }
    while (Modifier.isStatic(paramClass.getModifiers()));
    Constructor[] arrayOfConstructor = paramClass.getDeclaredConstructors();
    int j = arrayOfConstructor.length;
    int i = 0;
    while (true)
    {
      localObject2 = localObject1;
      if (i >= j)
        break;
      localObject2 = arrayOfConstructor[i];
      if ((((Constructor)localObject2).getParameterTypes().length == 1) && (localObject2.getParameterTypes()[0].equals(paramClass.getDeclaringClass())))
      {
        return localObject2;
        i += 1;
        break label28;
      }
      i += 1;
    }
  }

  public static Method getFactoryMethod(Class<?> paramClass)
  {
    Object localObject2 = null;
    Method[] arrayOfMethod = paramClass.getDeclaredMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    Object localObject1 = localObject2;
    if (i < j)
    {
      localObject1 = arrayOfMethod[i];
      if (!Modifier.isStatic(((Method)localObject1).getModifiers()));
      while ((!paramClass.isAssignableFrom(((Method)localObject1).getReturnType())) || ((JSONCreator)((Method)localObject1).getAnnotation(JSONCreator.class) == null))
      {
        i += 1;
        break;
      }
      if (0 != 0)
        throw new JSONException("multi-json creator");
    }
    return localObject1;
  }

  public static Field getField(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      return paramClass;
    }
    catch (Exception paramClass)
    {
    }
    return null;
  }

  public boolean add(FieldInfo paramFieldInfo)
  {
    Iterator localIterator = this.fieldList.iterator();
    while (localIterator.hasNext())
      if (((FieldInfo)localIterator.next()).getName().equals(paramFieldInfo.getName()))
        return false;
    this.fieldList.add(paramFieldInfo);
    this.sortedFieldList.add(paramFieldInfo);
    Collections.sort(this.sortedFieldList);
    return true;
  }

  public Class<?> getClazz()
  {
    return this.clazz;
  }

  public Constructor<?> getCreatorConstructor()
  {
    return this.creatorConstructor;
  }

  public Constructor<?> getDefaultConstructor()
  {
    return this.defaultConstructor;
  }

  public Method getFactoryMethod()
  {
    return this.factoryMethod;
  }

  public FieldInfo getField(String paramString)
  {
    Iterator localIterator = this.fieldList.iterator();
    while (localIterator.hasNext())
    {
      FieldInfo localFieldInfo = (FieldInfo)localIterator.next();
      if (localFieldInfo.getName().equals(paramString))
        return localFieldInfo;
    }
    return null;
  }

  public List<FieldInfo> getFieldList()
  {
    return this.fieldList;
  }

  public List<FieldInfo> getSortedFieldList()
  {
    return this.sortedFieldList;
  }

  public void setCreatorConstructor(Constructor<?> paramConstructor)
  {
    this.creatorConstructor = paramConstructor;
  }

  public void setDefaultConstructor(Constructor<?> paramConstructor)
  {
    this.defaultConstructor = paramConstructor;
  }

  public void setFactoryMethod(Method paramMethod)
  {
    this.factoryMethod = paramMethod;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.DeserializeBeanInfo
 * JD-Core Version:    0.6.2
 */