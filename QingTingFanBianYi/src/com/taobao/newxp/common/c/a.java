package com.taobao.newxp.common.c;

import android.util.Log;

public abstract class a
{
  protected static final String a = a.class.getName();

  public static <T extends a> T a(Class<T> paramClass)
  {
    try
    {
      paramClass = (a)paramClass.newInstance();
      return paramClass;
    }
    catch (InstantiationException paramClass)
    {
      Log.e(a, "", paramClass);
      return null;
    }
    catch (IllegalAccessException paramClass)
    {
      while (true)
        Log.e(a, "", paramClass);
    }
  }

  protected abstract void a(e parame);

  protected abstract boolean a();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.c.a
 * JD-Core Version:    0.6.2
 */