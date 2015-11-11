package com.taobao.munion.view.webview.windvane;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class b
{
  private static a a;

  public static <T> c<T> a(Class<T> paramClass)
  {
    return new c(paramClass);
  }

  public static <T> c<T> a(String paramString)
    throws b.b.a
  {
    try
    {
      paramString = new c(Class.forName(paramString));
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      b(new b.b.a(paramString));
    }
    return new c(null);
  }

  public static void a(a parama)
  {
    a = parama;
  }

  private static void b(b.b.a parama)
    throws b.b.a
  {
    if ((a == null) || (!a.a(parama)))
      throw parama;
  }

  public static abstract interface a
  {
    public abstract boolean a(b.b.a parama);
  }

  public static abstract class b
  {
    public static class a extends Throwable
    {
      private static final long d = 1L;
      private Class<?> a;
      private String b;
      private String c;

      public a(Exception paramException)
      {
        super();
      }

      public a(String paramString)
      {
        super();
      }

      public Class<?> a()
      {
        return this.a;
      }

      public void a(Class<?> paramClass)
      {
        this.a = paramClass;
      }

      public void a(String paramString)
      {
        this.c = paramString;
      }

      public String b()
      {
        return this.c;
      }

      public void b(String paramString)
      {
        this.b = paramString;
      }

      public String c()
      {
        return this.b;
      }

      public String toString()
      {
        if (getCause() != null)
          return getClass().getName() + ": " + getCause();
        return super.toString();
      }
    }
  }

  public static class c<C>
  {
    protected Class<C> a;

    public c(Class<C> paramClass)
    {
      this.a = paramClass;
    }

    public b.d a(Class<?>[] paramArrayOfClass)
      throws b.b.a
    {
      return new b.d(this.a, paramArrayOfClass);
    }

    public b.e<C, Object> a(String paramString)
      throws b.b.a
    {
      return new b.e(this.a, paramString, 8);
    }

    public b.f a(String paramString, Class<?>[] paramArrayOfClass)
      throws b.b.a
    {
      return new b.f(this.a, paramString, paramArrayOfClass, 8);
    }

    public Class<C> a()
    {
      return this.a;
    }

    public b.e<C, Object> b(String paramString)
      throws b.b.a
    {
      return new b.e(this.a, paramString, 0);
    }

    public b.f b(String paramString, Class<?>[] paramArrayOfClass)
      throws b.b.a
    {
      return new b.f(this.a, paramString, paramArrayOfClass, 0);
    }
  }

  public static class d
  {
    protected Constructor<?> a;

    d(Class<?> paramClass, Class<?>[] paramArrayOfClass)
      throws b.b.a
    {
      if (paramClass == null)
        return;
      try
      {
        this.a = paramClass.getDeclaredConstructor(paramArrayOfClass);
        return;
      }
      catch (NoSuchMethodException paramArrayOfClass)
      {
        paramArrayOfClass = new b.b.a(paramArrayOfClass);
        paramArrayOfClass.a(paramClass);
        b.a(paramArrayOfClass);
      }
    }

    public Object a(Object[] paramArrayOfObject)
      throws IllegalArgumentException
    {
      this.a.setAccessible(true);
      try
      {
        paramArrayOfObject = this.a.newInstance(paramArrayOfObject);
        return paramArrayOfObject;
      }
      catch (Exception paramArrayOfObject)
      {
        paramArrayOfObject.printStackTrace();
      }
      return null;
    }
  }

  public static class e<C, T>
  {
    private Object a;
    private final Field b;

    e(Class<C> paramClass, String paramString, int paramInt)
      throws b.b.a
    {
      if (paramClass == null)
      {
        this.b = null;
        return;
      }
      Field localField1 = localField2;
      Object localObject1 = localObject2;
      try
      {
        this.a = null;
        localField1 = localField2;
        localObject1 = localObject2;
        localField2 = paramClass.getDeclaredField(paramString);
        if (paramInt > 0)
        {
          localField1 = localField2;
          localObject1 = localField2;
          if ((localField2.getModifiers() & paramInt) != paramInt)
          {
            localField1 = localField2;
            localObject1 = localField2;
            b.a(new b.b.a(localField2 + " does not match modifiers: " + paramInt));
          }
        }
        localField1 = localField2;
        localObject1 = localField2;
        localField2.setAccessible(true);
        this.b = localField2;
        return;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        localObject1 = localField1;
        b.b.a locala = new b.b.a(localNoSuchFieldException);
        localObject1 = localField1;
        locala.a(paramClass);
        localObject1 = localField1;
        locala.b(paramString);
        localObject1 = localField1;
        b.a(locala);
        this.b = localField1;
        return;
      }
      finally
      {
        this.b = ((Field)localObject1);
      }
      throw paramClass;
    }

    public <T2> e<C, T2> a(Class<?> paramClass)
      throws b.b.a
    {
      if ((this.b != null) && (!paramClass.isAssignableFrom(this.b.getType())))
        b.a(new b.b.a(new ClassCastException(this.b + " is not of type " + paramClass)));
      return this;
    }

    public e<C, T> a(String paramString)
      throws b.b.a
    {
      try
      {
        paramString = b(Class.forName(paramString));
        return paramString;
      }
      catch (ClassNotFoundException paramString)
      {
        b.a(new b.b.a(paramString));
      }
      return this;
    }

    public T a()
    {
      try
      {
        Object localObject = this.b.get(this.a);
        return localObject;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
      }
      return null;
    }

    public void a(e.b<?> paramb)
    {
      Object localObject = a();
      if (localObject == null)
        throw new IllegalStateException("Cannot hijack null");
      a(e.a(localObject, paramb, localObject.getClass().getInterfaces()));
    }

    public void a(Object paramObject)
    {
      try
      {
        this.b.set(this.a, paramObject);
        return;
      }
      catch (IllegalAccessException paramObject)
      {
        paramObject.printStackTrace();
      }
    }

    public <T2> e<C, T2> b(Class<T2> paramClass)
      throws b.b.a
    {
      if ((this.b != null) && (!paramClass.isAssignableFrom(this.b.getType())))
        b.a(new b.b.a(new ClassCastException(this.b + " is not of type " + paramClass)));
      return this;
    }

    public e<C, T> b(C paramC)
    {
      this.a = paramC;
      return this;
    }
  }

  public static class f
  {
    protected final Method a;

    f(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass, int paramInt)
      throws b.b.a
    {
      Object localObject = null;
      Class<?>[] arrayOfClass = null;
      if (paramClass == null)
      {
        this.a = null;
        return;
      }
      try
      {
        paramArrayOfClass = paramClass.getDeclaredMethod(paramString, paramArrayOfClass);
        if (paramInt > 0)
        {
          arrayOfClass = paramArrayOfClass;
          localObject = paramArrayOfClass;
          if ((paramArrayOfClass.getModifiers() & paramInt) != paramInt)
          {
            arrayOfClass = paramArrayOfClass;
            localObject = paramArrayOfClass;
            b.a(new b.b.a(paramArrayOfClass + " does not match modifiers: " + paramInt));
          }
        }
        arrayOfClass = paramArrayOfClass;
        localObject = paramArrayOfClass;
        paramArrayOfClass.setAccessible(true);
        this.a = paramArrayOfClass;
        return;
      }
      catch (NoSuchMethodException paramArrayOfClass)
      {
        localObject = arrayOfClass;
        paramArrayOfClass = new b.b.a(paramArrayOfClass);
        localObject = arrayOfClass;
        paramArrayOfClass.a(paramClass);
        localObject = arrayOfClass;
        paramArrayOfClass.a(paramString);
        localObject = arrayOfClass;
        b.a(paramArrayOfClass);
        this.a = arrayOfClass;
        return;
      }
      finally
      {
        this.a = ((Method)localObject);
      }
      throw paramClass;
    }

    public Object a(Object paramObject, Object[] paramArrayOfObject)
      throws IllegalArgumentException
    {
      try
      {
        paramObject = this.a.invoke(paramObject, paramArrayOfObject);
        return paramObject;
      }
      catch (Exception paramObject)
      {
        paramObject.printStackTrace();
      }
      return null;
    }

    public Method a()
    {
      return this.a;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.b
 * JD-Core Version:    0.6.2
 */