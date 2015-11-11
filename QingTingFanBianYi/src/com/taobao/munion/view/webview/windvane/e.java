package com.taobao.munion.view.webview.windvane;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class e
{
  static <T> T a(Object paramObject, b<T> paramb, Class<?>[] paramArrayOfClass)
    throws IllegalArgumentException
  {
    if (Proxy.isProxyClass(paramObject.getClass()))
      return paramObject;
    paramb.a(paramObject);
    return Proxy.newProxyInstance(e.class.getClassLoader(), paramArrayOfClass, paramb);
  }

  public static <T> T a(Object paramObject, Class<T> paramClass, b<T> paramb)
    throws IllegalArgumentException
  {
    if ((paramObject instanceof a))
      return paramObject;
    paramb.a(paramObject);
    return Proxy.newProxyInstance(e.class.getClassLoader(), new Class[] { paramClass, a.class }, paramb);
  }

  private static abstract interface a
  {
  }

  public static abstract class b<T>
    implements InvocationHandler
  {
    private T a;

    protected T a()
    {
      return this.a;
    }

    void a(T paramT)
    {
      this.a = paramT;
    }

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      return paramMethod.invoke(a(), paramArrayOfObject);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.e
 * JD-Core Version:    0.6.2
 */