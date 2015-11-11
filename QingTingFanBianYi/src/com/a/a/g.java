package com.a.a;

import com.a.b.a;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class g
  implements Cloneable
{
  private static final h i = new d();
  private static final h j = new b();
  private static Class[] k = { Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class };
  private static Class[] l = { Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class };
  private static Class[] m = { Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class };
  private static final HashMap<Class, HashMap<String, Method>> n = new HashMap();
  private static final HashMap<Class, HashMap<String, Method>> o = new HashMap();
  String a;
  protected a b;
  Method c = null;
  Class d;
  f e = null;
  final ReentrantReadWriteLock f = new ReentrantReadWriteLock();
  final Object[] g = new Object[1];
  private Method h = null;
  private h p;
  private Object q;

  private g(String paramString)
  {
    this.a = paramString;
  }

  public static g a(String paramString, float[] paramArrayOfFloat)
  {
    return new a(paramString, paramArrayOfFloat);
  }

  public g a()
  {
    try
    {
      g localg = (g)super.clone();
      localg.a = this.a;
      localg.b = this.b;
      localg.e = this.e.b();
      localg.p = this.p;
      return localg;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    return null;
  }

  void a(float paramFloat)
  {
    this.q = this.e.a(paramFloat);
  }

  public void a(float[] paramArrayOfFloat)
  {
    this.d = Float.TYPE;
    this.e = f.a(paramArrayOfFloat);
  }

  void b()
  {
    h localh;
    if (this.p == null)
    {
      if (this.d != Integer.class)
        break label44;
      localh = i;
    }
    while (true)
    {
      this.p = localh;
      if (this.p != null)
        this.e.a(this.p);
      return;
      label44: if (this.d == Float.class)
        localh = j;
      else
        localh = null;
    }
  }

  public String c()
  {
    return this.a;
  }

  public String toString()
  {
    return this.a + ": " + this.e.toString();
  }

  static class a extends g
  {
    c h;
    float i;

    public a(String paramString, float[] paramArrayOfFloat)
    {
      super(null);
      a(paramArrayOfFloat);
    }

    void a(float paramFloat)
    {
      this.i = this.h.b(paramFloat);
    }

    public void a(float[] paramArrayOfFloat)
    {
      super.a(paramArrayOfFloat);
      this.h = ((c)this.e);
    }

    public a d()
    {
      a locala = (a)super.a();
      locala.h = ((c)locala.e);
      return locala;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.g
 * JD-Core Version:    0.6.2
 */