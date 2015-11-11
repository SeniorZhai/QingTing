package com.intowow.sdk.i.c.a;

import android.graphics.drawable.Drawable;
import com.intowow.sdk.f.a;
import com.intowow.sdk.f.d;
import com.intowow.sdk.f.e;
import com.intowow.sdk.f.f;
import com.intowow.sdk.model.j;

public abstract class b
{
  protected a a = null;
  protected d b = null;
  protected e c = null;
  protected f d = null;
  protected j e = null;

  public b(j paramj, a parama, d paramd)
  {
    this.a = parama;
    this.b = paramd;
    this.e = paramj;
  }

  public b(j paramj, a parama, e parame)
  {
    this.a = parama;
    this.c = parame;
    this.e = paramj;
  }

  public b(j paramj, a parama, f paramf)
  {
    this.a = parama;
    this.d = paramf;
    this.e = paramj;
  }

  public abstract int a();

  public abstract int b();

  public abstract int c();

  public abstract int d();

  public abstract int e();

  public abstract int f();

  public abstract int g();

  public abstract int h();

  public abstract Drawable i();

  public abstract Drawable j();

  public abstract Drawable k();

  public abstract Drawable l();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.a.b
 * JD-Core Version:    0.6.2
 */