package com.intowow.sdk.f;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class e extends c
{
  private static e i = null;

  private e()
  {
    this.b = 1280;
    this.c = 720;
    this.g = new int[a.values().length];
  }

  public static e a(Activity paramActivity)
  {
    try
    {
      if (i == null)
      {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        i = new e();
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
    a(a.a, 1280);
    a(a.b, 720);
    a(a.g, 1200);
    a(a.h, 674);
    a(a.c, 280);
    a(a.d, 120);
    a(a.e, 280);
    a(a.f, 120);
    a(a.o, 15);
    a(a.i, 19);
    a(a.j, 17);
    a(a.k, 26);
    a(a.l, 80);
    a(a.m, 300);
    a(a.n, 140);
    a(a.p, 38);
    a(a.q, 8);
    a(a.r, 32);
    a(a.s, 4);
    a(a.t, 16);
    a(a.u, 160);
    a(a.v, 20);
    a(a.w, 10);
    a(a.x, 140);
    a(a.y, 80);
    a(a.z, 300);
    a(a.A, 140);
    a(a.B, 35);
    a(a.C, 100);
    a(a.D, 720);
    a(a.E, 140);
    a(a.F, 180);
    a(a.G, 30);
    a(a.H, 10);
    a(a.I, 100);
    a(a.J, 100);
    a(a.K, 20);
    a(a.L, 30);
    a(a.M, 570);
    a(a.N, 30);
    a(a.O, 32);
    a(a.P, 30);
    a(a.Q, 360);
    a(a.R, 110);
    a(a.S, 360);
    a(a.T, 110);
    a(a.U, 20);
    a(a.V, 15);
    a(a.W, 50);
    a(a.X, 25);
    a(a.Y, 160);
    a(a.Z, 46);
    a(a.aa, 25);
    a(a.ab, 12);
    if (this.d == c.a.b)
    {
      a(a.a, 1202);
      a(a.b, 720);
      a(a.c, 271);
      a(a.d, 116);
      a(a.e, 271);
      a(a.f, 116);
      a(a.g, 1162);
      a(a.h, 652);
      a(a.o, 14);
      a(a.i, 18);
      a(a.j, 16);
      a(a.k, 25);
      a(a.l, 77);
      a(a.m, 290);
      a(a.n, 135);
      a(a.p, 36);
      a(a.q, 7);
      a(a.r, 30);
      a(a.s, 3);
      a(a.t, 15);
      a(a.u, 154);
      a(a.v, 19);
      a(a.w, 9);
      a(a.x, 135);
      a(a.y, 77);
      a(a.z, 290);
      a(a.A, 135);
      a(a.B, 33);
      a(a.C, 97);
      a(a.D, 697);
      a(a.E, 135);
      a(a.F, 174);
      a(a.G, 29);
      a(a.H, 9);
      a(a.I, 96);
      a(a.J, 96);
      a(a.K, 19);
      a(a.L, 29);
      a(a.M, 551);
      a(a.N, 29);
      a(a.O, 30);
      a(a.P, 29);
      a(a.Q, 348);
      a(a.R, 106);
      a(a.S, 348);
      a(a.T, 106);
      a(a.U, 19);
      a(a.V, 14);
      a(a.W, 48);
      a(a.X, 24);
      a(a.Y, 154);
      a(a.Z, 44);
      a(a.aa, 24);
      a(a.ab, 11);
    }
    do
    {
      return;
      if (this.d == c.a.c)
      {
        a(a.a, 1152);
        a(a.b, 720);
        a(a.c, 259);
        a(a.d, 111);
        a(a.e, 259);
        a(a.f, 111);
        a(a.g, 1112);
        a(a.h, 624);
        a(a.o, 13);
        a(a.i, 17);
        a(a.j, 15);
        a(a.k, 24);
        a(a.l, 74);
        a(a.m, 278);
        a(a.n, 129);
        a(a.p, 35);
        a(a.q, 7);
        a(a.r, 29);
        a(a.s, 3);
        a(a.t, 14);
        a(a.u, 148);
        a(a.v, 18);
        a(a.w, 9);
        a(a.x, 129);
        a(a.y, 74);
        a(a.z, 278);
        a(a.A, 129);
        a(a.B, 32);
        a(a.C, 93);
        a(a.D, 667);
        a(a.E, 129);
        a(a.F, 166);
        a(a.G, 27);
        a(a.H, 9);
        a(a.I, 92);
        a(a.J, 92);
        a(a.K, 18);
        a(a.L, 27);
        a(a.M, 528);
        a(a.N, 27);
        a(a.O, 29);
        a(a.P, 27);
        a(a.Q, 333);
        a(a.R, 101);
        a(a.S, 333);
        a(a.T, 101);
        a(a.U, 18);
        a(a.V, 13);
        a(a.W, 46);
        a(a.X, 23);
        a(a.Y, 148);
        a(a.Z, 42);
        a(a.aa, 23);
        a(a.ab, 11);
        return;
      }
    }
    while (this.d != c.a.d);
    a(a.a, 1080);
    a(a.b, 720);
    a(a.c, 242);
    a(a.d, 104);
    a(a.e, 242);
    a(a.f, 104);
    a(a.g, 1040);
    a(a.h, 585);
    a(a.o, 13);
    a(a.i, 16);
    a(a.j, 14);
    a(a.k, 22);
    a(a.l, 69);
    a(a.m, 260);
    a(a.n, 121);
    a(a.p, 32);
    a(a.q, 6);
    a(a.r, 27);
    a(a.s, 3);
    a(a.t, 13);
    a(a.u, 138);
    a(a.v, 17);
    a(a.w, 8);
    a(a.x, 121);
    a(a.y, 69);
    a(a.z, 260);
    a(a.A, 121);
    a(a.B, 30);
    a(a.C, 87);
    a(a.D, 624);
    a(a.E, 121);
    a(a.F, 156);
    a(a.G, 26);
    a(a.H, 8);
    a(a.I, 86);
    a(a.J, 86);
    a(a.K, 17);
    a(a.L, 26);
    a(a.M, 494);
    a(a.N, 26);
    a(a.O, 27);
    a(a.P, 26);
    a(a.Q, 312);
    a(a.R, 95);
    a(a.S, 312);
    a(a.T, 95);
    a(a.U, 17);
    a(a.V, 13);
    a(a.W, 43);
    a(a.X, 21);
    a(a.Y, 138);
    a(a.Z, 39);
    a(a.aa, 21);
    a(a.ab, 10);
  }

  private void d(a parama)
  {
    b(parama.ordinal());
  }

  private void e()
  {
    a(a.a, this.b);
    a(a.b, this.c);
    c(a.g);
    c(a.h);
    c(a.o);
    c(a.i);
    c(a.j);
    d(a.k);
    c(a.l);
    c(a.m);
    c(a.n);
    c(a.p);
    c(a.q);
    c(a.r);
    c(a.s);
    c(a.t);
    c(a.u);
    c(a.v);
    c(a.w);
    c(a.x);
    c(a.y);
    c(a.z);
    c(a.A);
    c(a.B);
    c(a.C);
    c(a.D);
    c(a.E);
    c(a.F);
    c(a.G);
    c(a.H);
    c(a.I);
    c(a.J);
    c(a.K);
    d(a.L);
    c(a.M);
    d(a.N);
    d(a.O);
    d(a.P);
    c(a.Q);
    c(a.R);
    c(a.S);
    c(a.T);
    c(a.U);
    c(a.c);
    c(a.d);
    c(a.e);
    c(a.f);
    c(a.V);
    c(a.W);
    d(a.X);
    c(a.Y);
    c(a.Z);
    c(a.aa);
    c(a.ab);
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
    if (paramInt1 < paramInt2)
    {
      this.c = paramInt1;
      this.b = paramInt2;
    }
    this.f = (this.b / 1280.0D);
    this.h = com.intowow.sdk.j.c.b();
    double d = a(this.b, this.c);
    if (d >= 1.669999957084656D)
      this.f = (this.b / 1202.0D);
    while (true)
    {
      d();
      e();
      return;
      if (d >= 1.600000023841858D)
        this.f = (this.b / 1152.0D);
      else
        this.f = (this.b / 1080.0D);
    }
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.f.e
 * JD-Core Version:    0.6.2
 */