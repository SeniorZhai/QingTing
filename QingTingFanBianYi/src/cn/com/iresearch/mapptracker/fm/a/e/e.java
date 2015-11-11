package cn.com.iresearch.mapptracker.fm.a.e;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class e
{
  private static SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private String a;
  private String b;
  private Class<?> c;
  private Field d;
  private Method e;
  private Method f;

  public static void b()
  {
  }

  private static java.util.Date c(String paramString)
  {
    if (paramString != null)
      try
      {
        paramString = g.parse(paramString);
        return paramString;
      }
      catch (ParseException paramString)
      {
        paramString.printStackTrace();
      }
    return null;
  }

  public final <T> T a(Object paramObject)
  {
    if ((paramObject != null) && (this.e != null));
    try
    {
      paramObject = this.e.invoke(paramObject, new Object[0]);
      return paramObject;
    }
    catch (IllegalArgumentException paramObject)
    {
      paramObject.printStackTrace();
      return null;
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

  public final void a(Class<?> paramClass)
  {
    this.c = paramClass;
  }

  public final void a(Object paramObject1, Object paramObject2)
  {
    Method localMethod1 = null;
    if ((this.f != null) && (paramObject2 != null))
    {
      while (true)
      {
        try
        {
          if (this.c == String.class)
          {
            this.f.invoke(paramObject1, new Object[] { paramObject2.toString() });
            return;
          }
          if ((this.c != Integer.TYPE) && (this.c != Integer.class))
            break;
          localMethod1 = this.f;
          if (paramObject2 == null)
          {
            throw new NullPointerException();
            localMethod1.invoke(paramObject1, new Object[] { Integer.valueOf(i) });
            return;
          }
        }
        catch (Exception paramObject1)
        {
          paramObject1.printStackTrace();
          return;
        }
        int i = Integer.parseInt(paramObject2.toString());
      }
      if ((this.c == Float.TYPE) || (this.c == Float.class))
      {
        localMethod1 = this.f;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject2;
          localMethod1.invoke(paramObject1, new Object[] { Float.valueOf(localObject2) });
          return;
          float f1 = Float.parseFloat(paramObject2.toString());
        }
      }
      if ((this.c == Double.TYPE) || (this.c == Double.class))
      {
        localMethod1 = this.f;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject1;
          localMethod1.invoke(paramObject1, new Object[] { Double.valueOf(localObject1) });
          return;
          double d1 = Double.parseDouble(paramObject2.toString());
        }
      }
      if ((this.c == Long.TYPE) || (this.c == Long.class))
      {
        localMethod1 = this.f;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          Object localObject3;
          localMethod1.invoke(paramObject1, new Object[] { Long.valueOf(localObject3) });
          return;
          long l = Long.parseLong(paramObject2.toString());
        }
      }
      if ((this.c == java.util.Date.class) || (this.c == java.sql.Date.class))
      {
        Method localMethod2 = this.f;
        if (paramObject2 == null);
        for (paramObject2 = localMethod1; ; paramObject2 = c(paramObject2.toString()))
        {
          localMethod2.invoke(paramObject1, new Object[] { paramObject2 });
          return;
        }
      }
      if ((this.c == Boolean.TYPE) || (this.c == Boolean.class))
      {
        localMethod1 = this.f;
        if (paramObject2 == null)
          throw new NullPointerException();
        while (true)
        {
          localMethod1.invoke(paramObject1, new Object[] { Boolean.valueOf(bool) });
          return;
          boolean bool = "1".equals(paramObject2.toString());
        }
      }
      this.f.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    }
    try
    {
      this.d.setAccessible(true);
      this.d.set(paramObject1, paramObject2);
      return;
    }
    catch (Exception paramObject1)
    {
    }
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final void a(Field paramField)
  {
    this.d = paramField;
  }

  public final void a(Method paramMethod)
  {
    this.e = paramMethod;
  }

  public final void b(String paramString)
  {
    this.b = paramString;
  }

  public final void b(Method paramMethod)
  {
    this.f = paramMethod;
  }

  public final String c()
  {
    return this.a;
  }

  public final String d()
  {
    return this.b;
  }

  public final Class<?> e()
  {
    return this.c;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.a.e.e
 * JD-Core Version:    0.6.2
 */