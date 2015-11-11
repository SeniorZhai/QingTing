package com.taobao.munion.base.volley;

public class d
  implements p
{
  public static final int a = 2500;
  public static final int b = 1;
  public static final float c = 1.0F;
  private int d;
  private int e;
  private final int f;
  private final float g;

  public d()
  {
    this(2500, 1, 1.0F);
  }

  public d(int paramInt1, int paramInt2, float paramFloat)
  {
    this.d = paramInt1;
    this.f = paramInt2;
    this.g = paramFloat;
  }

  public int a()
  {
    return this.d;
  }

  public void a(s params)
    throws s
  {
    this.e += 1;
    this.d = ((int)(this.d + this.d * this.g));
    if (!c())
      throw params;
  }

  public int b()
  {
    return this.e;
  }

  protected boolean c()
  {
    return this.e <= this.f;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.d
 * JD-Core Version:    0.6.2
 */