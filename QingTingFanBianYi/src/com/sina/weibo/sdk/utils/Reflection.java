package com.sina.weibo.sdk.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection
{
  public static Object getByArray(Object paramObject, int paramInt)
  {
    return Array.get(paramObject, paramInt);
  }

  public static Object getProperty(Object paramObject, String paramString)
    throws Exception
  {
    return paramObject.getClass().getField(paramString).get(paramObject);
  }

  public static Object getStaticProperty(String paramString1, String paramString2)
    throws Exception
  {
    paramString1 = Class.forName(paramString1);
    return paramString1.getField(paramString2).get(paramString1);
  }

  public static Object invokeMethod(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramObject = paramObject.getClass().getMethod(paramString, paramArrayOfClass).invoke(paramObject, paramArrayOfObject);
      return paramObject;
    }
    catch (SecurityException paramObject)
    {
      paramObject.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramObject)
    {
      while (true)
        paramObject.printStackTrace();
    }
    catch (IllegalArgumentException paramObject)
    {
      while (true)
        paramObject.printStackTrace();
    }
    catch (IllegalAccessException paramObject)
    {
      while (true)
        paramObject.printStackTrace();
    }
    catch (InvocationTargetException paramObject)
    {
      while (true)
        paramObject.printStackTrace();
    }
  }

  public static Object invokeMethod(Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws Exception
  {
    Class localClass = paramObject.getClass();
    Class[] arrayOfClass = new Class[paramArrayOfObject.length];
    int i = 0;
    int j = paramArrayOfObject.length;
    while (true)
    {
      if (i >= j)
        return localClass.getMethod(paramString, arrayOfClass).invoke(paramObject, paramArrayOfObject);
      arrayOfClass[i] = paramArrayOfObject[i].getClass();
      i += 1;
    }
  }

  public static Object invokeParamsMethod(Object paramObject, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
    throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    paramString = paramObject.getClass().getMethod(paramString, paramArrayOfClass);
    paramString.setAccessible(true);
    return paramString.invoke(paramObject, paramArrayOfObject);
  }

  public static Object invokeStaticMethod(Class paramClass, String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramArrayOfClass).invoke(null, paramArrayOfObject);
      return paramClass;
    }
    catch (SecurityException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (IllegalArgumentException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramClass)
    {
      paramClass.printStackTrace();
    }
    return null;
  }

  public static Object invokeStaticMethod(String paramString1, String paramString2, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
  {
    try
    {
      paramString1 = Class.forName(paramString1).getMethod(paramString2, paramArrayOfClass).invoke(null, paramArrayOfObject);
      return paramString1;
    }
    catch (ClassNotFoundException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (SecurityException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (NoSuchMethodException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (IllegalArgumentException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (IllegalAccessException paramString1)
    {
      paramString1.printStackTrace();
      return null;
    }
    catch (InvocationTargetException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static Object invokeStaticMethod(String paramString1, String paramString2, Object[] paramArrayOfObject)
    throws Exception
  {
    paramString1 = Class.forName(paramString1);
    Class[] arrayOfClass = new Class[paramArrayOfObject.length];
    int i = 0;
    int j = paramArrayOfObject.length;
    while (true)
    {
      if (i >= j)
        return paramString1.getMethod(paramString2, arrayOfClass).invoke(null, paramArrayOfObject);
      arrayOfClass[i] = paramArrayOfObject[i].getClass();
      i += 1;
    }
  }

  public static void invokeVoidMethod(Object paramObject, String paramString, boolean paramBoolean)
  {
    try
    {
      paramObject.getClass().getMethod(paramString, new Class[] { Boolean.TYPE }).invoke(paramObject, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (InvocationTargetException paramObject)
    {
    }
    catch (IllegalAccessException paramObject)
    {
    }
    catch (IllegalArgumentException paramObject)
    {
    }
    catch (NoSuchMethodException paramObject)
    {
    }
    catch (SecurityException paramObject)
    {
    }
  }

  public static boolean isInstance(Object paramObject, Class paramClass)
  {
    return paramClass.isInstance(paramObject);
  }

  public static Object newInstance(String paramString, Class<?>[] paramArrayOfClass, Object[] paramArrayOfObject)
    throws Exception
  {
    return Class.forName(paramString).getConstructor(paramArrayOfClass).newInstance(paramArrayOfObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.Reflection
 * JD-Core Version:    0.6.2
 */