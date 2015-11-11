package com.a.a;

import android.view.animation.Interpolator;

public abstract class e
  implements Cloneable
{
  float a;
  Class b;
  boolean c = false;
  private Interpolator d = null;

  public static e a(float paramFloat)
  {
    return new a(paramFloat);
  }

  public static e a(float paramFloat1, float paramFloat2)
  {
    return new a(paramFloat1, paramFloat2);
  }

  public abstract Object a();

  public void a(Interpolator paramInterpolator)
  {
    this.d = paramInterpolator;
  }

  public float b()
  {
    return this.a;
  }

  public Interpolator c()
  {
    return this.d;
  }

  public abstract e d();

  static class a extends e
  {
    float d;

    a(float paramFloat)
    {
      this.a = paramFloat;
      this.b = Float.TYPE;
    }

    a(float paramFloat1, float paramFloat2)
    {
      this.a = paramFloat1;
      this.d = paramFloat2;
      this.b = Float.TYPE;
      this.c = true;
    }

    public Object a()
    {
      return Float.valueOf(this.d);
    }

    public float e()
    {
      return this.d;
    }

    public a f()
    {
      a locala = new a(b(), this.d);
      locala.a(c());
      return locala;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.a.e
 * JD-Core Version:    0.6.2
 */