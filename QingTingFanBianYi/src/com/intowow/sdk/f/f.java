package com.intowow.sdk.f;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class f extends c
{
  private static f i = null;

  private f()
  {
    this.b = 720;
    this.c = 1280;
    this.g = new int[a.values().length];
  }

  public static f a(Activity paramActivity)
  {
    try
    {
      if (i == null)
      {
        i = new f();
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        i.a(localDisplayMetrics.density, localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels);
      }
      paramActivity = i;
      return paramActivity;
    }
    finally
    {
    }
    throw paramActivity;
  }

  private void a(a parama, int paramInt)
  {
    this.g[parama.ordinal()] = paramInt;
  }

  private int b(a parama)
  {
    return this.g[parama.ordinal()];
  }

  private void c(a parama)
  {
    a(parama.ordinal());
  }

  private void d()
  {
    a(a.g, 680);
    a(a.h, 1220);
    a(a.i, 1220);
    a(a.l, 100);
    a(a.w, 680);
    a(a.x, 120);
    a(a.m, 20);
    a(a.n, 28);
    a(a.o, 88);
    a(a.p, 44);
    a(a.q, 15);
    a(a.r, 38);
    a(a.s, 8);
    a(a.t, 32);
    a(a.u, 4);
    a(a.v, 16);
    a(a.y, 160);
    a(a.z, 1100);
    a(a.A, 180);
    a(a.B, 120);
    a(a.C, 120);
    a(a.D, 100);
    a(a.E, 100);
    a(a.F, 30);
    a(a.G, 30);
    a(a.H, 30);
    a(a.I, 490);
    a(a.J, 30);
    a(a.K, 32);
    a(a.L, 640);
    a(a.M, 360);
    a(a.R, 140);
    a(a.S, 372);
    a(a.O, 642);
    a(a.P, 364);
    a(a.Q, 260);
    a(a.N, 262);
    a(a.j, 280);
    a(a.k, 120);
    a(a.e, 280);
    a(a.f, 120);
    a(a.T, 15);
    a(a.U, 30);
    a(a.V, 25);
    a(a.W, 160);
    a(a.X, 46);
    a(a.Y, 25);
    a(a.Z, 12);
    a(a.aa, 550);
    a(a.c, 130);
    a(a.d, 100);
    a(a.b, 475);
    a(a.a, 30);
    if (this.d == c.a.b)
    {
      a(a.g, 646);
      a(a.h, 1160);
      a(a.i, 1160);
      a(a.l, 95);
      a(a.w, 646);
      a(a.x, 114);
      a(a.m, 19);
      a(a.n, 26);
      a(a.o, 83);
      a(a.p, 41);
      a(a.q, 14);
      a(a.r, 36);
      a(a.s, 7);
      a(a.t, 30);
      a(a.u, 3);
      a(a.v, 15);
      a(a.y, 152);
      a(a.z, 1045);
      a(a.A, 171);
      a(a.B, 114);
      a(a.C, 114);
      a(a.D, 95);
      a(a.E, 95);
      a(a.F, 28);
      a(a.G, 28);
      a(a.H, 28);
      a(a.I, 465);
      a(a.J, 28);
      a(a.K, 30);
      a(a.L, 608);
      a(a.M, 342);
      a(a.R, 133);
      a(a.S, 353);
      a(a.O, 610);
      a(a.P, 346);
      a(a.Q, 247);
      a(a.N, 249);
      a(a.T, 14);
      a(a.U, 28);
      a(a.V, 23);
      a(a.W, 152);
      a(a.X, 43);
      a(a.Y, 23);
      a(a.Z, 11);
      a(a.aa, 522);
      a(a.j, 266);
      a(a.k, 114);
      a(a.e, 266);
      a(a.f, 114);
      a(a.c, 123);
      a(a.d, 95);
      a(a.b, 451);
      a(a.a, 28);
    }
    do
    {
      return;
      if (this.d == c.a.c)
      {
        a(a.g, 624);
        a(a.h, 1120);
        a(a.i, 1120);
        a(a.l, 92);
        a(a.w, 624);
        a(a.x, 110);
        a(a.m, 18);
        a(a.n, 25);
        a(a.o, 80);
        a(a.p, 40);
        a(a.q, 13);
        a(a.r, 34);
        a(a.s, 7);
        a(a.t, 29);
        a(a.u, 3);
        a(a.v, 14);
        a(a.y, 146);
        a(a.z, 1009);
        a(a.A, 165);
        a(a.B, 110);
        a(a.C, 110);
        a(a.D, 91);
        a(a.E, 91);
        a(a.F, 27);
        a(a.G, 27);
        a(a.H, 27);
        a(a.I, 449);
        a(a.J, 27);
        a(a.K, 29);
        a(a.L, 587);
        a(a.M, 330);
        a(a.R, 128);
        a(a.S, 341);
        a(a.O, 589);
        a(a.P, 334);
        a(a.Q, 238);
        a(a.N, 240);
        a(a.T, 13);
        a(a.U, 27);
        a(a.V, 22);
        a(a.W, 146);
        a(a.X, 42);
        a(a.Y, 22);
        a(a.Z, 11);
        a(a.aa, 504);
        a(a.j, 257);
        a(a.k, 110);
        a(a.e, 257);
        a(a.f, 110);
        a(a.c, 119);
        a(a.d, 91);
        a(a.b, 436);
        a(a.a, 27);
        return;
      }
    }
    while (this.d != c.a.d);
    a(a.g, 584);
    a(a.h, 1048);
    a(a.i, 1048);
    a(a.l, 86);
    a(a.w, 584);
    a(a.x, 103);
    a(a.m, 17);
    a(a.n, 24);
    a(a.o, 75);
    a(a.p, 37);
    a(a.q, 12);
    a(a.r, 32);
    a(a.s, 6);
    a(a.t, 27);
    a(a.u, 3);
    a(a.v, 13);
    a(a.y, 137);
    a(a.z, 944);
    a(a.A, 154);
    a(a.B, 103);
    a(a.C, 103);
    a(a.D, 85);
    a(a.E, 85);
    a(a.F, 25);
    a(a.G, 25);
    a(a.H, 25);
    a(a.I, 420);
    a(a.J, 25);
    a(a.K, 27);
    a(a.L, 549);
    a(a.M, 309);
    a(a.R, 120);
    a(a.S, 319);
    a(a.O, 551);
    a(a.P, 312);
    a(a.Q, 223);
    a(a.N, 225);
    a(a.T, 12);
    a(a.U, 25);
    a(a.V, 21);
    a(a.W, 137);
    a(a.X, 39);
    a(a.Y, 21);
    a(a.Z, 10);
    a(a.aa, 472);
    a(a.j, 240);
    a(a.k, 103);
    a(a.e, 240);
    a(a.f, 103);
    a(a.c, 111);
    a(a.d, 85);
    a(a.b, 408);
    a(a.a, 25);
  }

  private void d(a parama)
  {
    b(parama.ordinal());
  }

  private void e()
  {
    c(a.g);
    c(a.h);
    c(a.i);
    c(a.l);
    c(a.m);
    d(a.n);
    c(a.o);
    c(a.p);
    c(a.q);
    c(a.r);
    c(a.s);
    c(a.t);
    c(a.u);
    c(a.v);
    c(a.y);
    c(a.w);
    c(a.x);
    c(a.z);
    c(a.A);
    c(a.B);
    c(a.C);
    c(a.D);
    c(a.E);
    c(a.F);
    c(a.G);
    d(a.H);
    c(a.I);
    d(a.J);
    d(a.K);
    c(a.L);
    c(a.M);
    c(a.R);
    c(a.S);
    c(a.O);
    c(a.P);
    c(a.Q);
    c(a.N);
    c(a.T);
    c(a.U);
    d(a.V);
    c(a.W);
    c(a.X);
    c(a.Y);
    c(a.Z);
    c(a.aa);
    c(a.j);
    c(a.k);
    c(a.e);
    c(a.f);
    c(a.c);
    c(a.d);
    c(a.b);
    c(a.a);
  }

  public int a(a parama)
  {
    return b(parama);
  }

  public void a(float paramFloat, int paramInt1, int paramInt2)
  {
    this.e = paramFloat;
    this.b = paramInt1;
    this.c = paramInt2;
    if (paramInt1 > paramInt2)
    {
      this.c = paramInt1;
      this.b = paramInt2;
    }
    this.f = (this.b / 720.0D);
    this.h = com.intowow.sdk.j.c.b();
    a(this.c, this.b);
    d();
    e();
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.f.f
 * JD-Core Version:    0.6.2
 */