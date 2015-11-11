package com.intowow.sdk.f;

import java.math.BigDecimal;

public class c
{
  protected static final a a = a.a;
  protected int b = 0;
  protected int c = 0;
  protected a d = a;
  protected float e = 1.0F;
  protected double f = 1.0D;
  protected int[] g = null;
  protected boolean h = false;

  protected double a(int paramInt1, int paramInt2)
  {
    double d1 = new BigDecimal(paramInt1 / paramInt2).setScale(2, 4).doubleValue();
    if (d1 >= 1.779999971389771D)
    {
      this.d = a.a;
      return d1;
    }
    if (d1 >= 1.669999957084656D)
    {
      this.d = a.b;
      return d1;
    }
    if (d1 >= 1.600000023841858D)
    {
      this.d = a.c;
      return d1;
    }
    this.d = a.d;
    return d1;
  }

  public a a()
  {
    return this.d;
  }

  protected void a(int paramInt)
  {
    int i = (int)Math.floor(this.g[paramInt] * this.f);
    this.g[paramInt] = i;
  }

  public int b()
  {
    return this.b;
  }

  protected void b(int paramInt)
  {
    int i = this.g[paramInt];
    if (this.h)
    {
      i = (int)Math.floor(i / 2.0F * 2.0F);
      this.g[paramInt] = i;
      return;
    }
    if (this.f >= 1.0D)
    {
      i = (int)Math.floor(i / 2.0F * this.e);
      this.g[paramInt] = i;
      return;
    }
    double d1 = ((this.f + 1.0D) / 2.0D + 1.0D) / 2.0D;
    i = (int)Math.floor(i / 2.0F * this.e * d1);
    this.g[paramInt] = i;
  }

  public int c()
  {
    return this.c;
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.f.c
 * JD-Core Version:    0.6.2
 */