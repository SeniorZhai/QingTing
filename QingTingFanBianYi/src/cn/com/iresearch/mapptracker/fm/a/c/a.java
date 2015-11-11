package cn.com.iresearch.mapptracker.fm.a.c;

import cn.com.iresearch.mapptracker.fm.a.a.a.b;
import cn.com.iresearch.mapptracker.fm.a.a.a.c;
import cn.com.iresearch.mapptracker.fm.a.a.a.d;
import cn.com.iresearch.mapptracker.fm.a.a.a.f;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public final class a
{
  static
  {
    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  }

  public static String a(Field paramField)
  {
    Object localObject = (d)paramField.getAnnotation(d.class);
    if ((localObject != null) && (((d)localObject).a().trim().length() != 0))
      return ((d)localObject).a();
    localObject = (b)paramField.getAnnotation(b.class);
    if ((localObject != null) && (((b)localObject).a().trim().length() != 0))
      return ((b)localObject).a();
    localObject = (c)paramField.getAnnotation(c.class);
    if ((localObject != null) && (((c)localObject).a() != null) && (((c)localObject).a().trim().length() != 0))
      return ((c)localObject).a();
    localObject = (cn.com.iresearch.mapptracker.fm.a.a.a.a)paramField.getAnnotation(cn.com.iresearch.mapptracker.fm.a.a.a.a.class);
    if ((localObject != null) && (((cn.com.iresearch.mapptracker.fm.a.a.a.a)localObject).a().trim().length() != 0))
      return ((cn.com.iresearch.mapptracker.fm.a.a.a.a)localObject).a();
    return paramField.getName();
  }

  private static Method a(Class<?> paramClass, String paramString)
  {
    String str = "is" + paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
    if (a(paramString));
    while (true)
    {
      try
      {
        paramClass = paramClass.getDeclaredMethod(paramString, new Class[0]);
        return paramClass;
      }
      catch (NoSuchMethodException paramClass)
      {
        return null;
      }
      paramString = str;
    }
  }

  public static Method a(Class<?> paramClass, Field paramField)
  {
    String str = paramField.getName();
    Method localMethod = null;
    if (paramField.getType() == Boolean.TYPE)
      localMethod = a(paramClass, str);
    paramField = localMethod;
    if (localMethod == null)
      paramField = b(paramClass, str);
    return paramField;
  }

  private static boolean a(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0));
    while ((!paramString.startsWith("is")) || (Character.isLowerCase(paramString.charAt(2))))
      return false;
    return true;
  }

  public static String b(Field paramField)
  {
    paramField = (d)paramField.getAnnotation(d.class);
    if ((paramField != null) && (paramField.b().trim().length() != 0))
      return paramField.b();
    return null;
  }

  private static Method b(Class<?> paramClass, String paramString)
  {
    paramString = "get" + paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
    try
    {
      paramClass = paramClass.getDeclaredMethod(paramString, new Class[0]);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
    }
    return null;
  }

  public static Method b(Class<?> paramClass, Field paramField)
  {
    Object localObject = paramField.getName();
    localObject = "set" + ((String)localObject).substring(0, 1).toUpperCase() + ((String)localObject).substring(1);
    try
    {
      localObject = paramClass.getDeclaredMethod((String)localObject, new Class[] { paramField.getType() });
      return localObject;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      if (paramField.getType() == Boolean.TYPE)
        return c(paramClass, paramField);
    }
    return null;
  }

  private static Method c(Class<?> paramClass, Field paramField)
  {
    String str2 = paramField.getName();
    String str1 = "set" + str2.substring(0, 1).toUpperCase() + str2.substring(1);
    if (a(paramField.getName()))
      str1 = "set" + str2.substring(2, 3).toUpperCase() + str2.substring(3);
    try
    {
      paramClass = paramClass.getDeclaredMethod(str1, new Class[] { paramField.getType() });
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
    }
    return null;
  }

  public static boolean c(Field paramField)
  {
    return paramField.getAnnotation(f.class) != null;
  }

  public static boolean d(Field paramField)
  {
    return paramField.getAnnotation(b.class) != null;
  }

  public static boolean e(Field paramField)
  {
    return paramField.getAnnotation(c.class) != null;
  }

  public static boolean f(Field paramField)
  {
    paramField = paramField.getType();
    return (paramField.equals(String.class)) || (paramField.equals(Integer.class)) || (paramField.equals(Byte.class)) || (paramField.equals(Long.class)) || (paramField.equals(Double.class)) || (paramField.equals(Float.class)) || (paramField.equals(Character.class)) || (paramField.equals(Short.class)) || (paramField.equals(Boolean.class)) || (paramField.equals(java.util.Date.class)) || (paramField.equals(java.util.Date.class)) || (paramField.equals(java.sql.Date.class)) || (paramField.isPrimitive());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.c.a
 * JD-Core Version:    0.6.2
 */