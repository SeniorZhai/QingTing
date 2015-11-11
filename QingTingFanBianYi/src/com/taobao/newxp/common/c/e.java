package com.taobao.newxp.common.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class e
  implements d
{
  private static final String m = e.class.getName();
  private static String n = "%s,%s,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s,%d,%d";
  private static int o = 0;
  private static long p = 0L;
  private static long q = -9999L;
  String a = "1";
  String b = "-1";
  int c;
  int d;
  int e;
  int f;
  int g;
  int h;
  long i;
  long j;
  long k;
  long l;
  private String r;
  private String s;

  private String a(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    int i1 = 0;
    if (TextUtils.isEmpty(paramString))
      return "" + 0;
    if (paramLong2 > 0L)
      if (paramInt <= 0)
        break label94;
    int i2;
    while (true)
    {
      i2 = 0;
      while (i1 < (paramLong2 + paramLong1) % 9L)
      {
        i2 += paramString.charAt(i1 * paramInt % paramString.length());
        i1 += 1;
      }
      paramLong2 = 0L;
      break;
      label94: paramInt = 0;
    }
    return "" + i2;
  }

  public String a()
  {
    String str = String.format(n, new Object[] { this.a, a(this.b, this.i, this.j, o), Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Long.valueOf(this.i), Integer.valueOf(this.g), Integer.valueOf(this.h), Long.valueOf(this.j), Long.valueOf(this.k), Long.valueOf(this.l), this.b, Long.valueOf(q), Integer.valueOf(o) });
    Log.d(m, "Generate Paramater A " + str);
    return str;
  }

  public void a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.c = localDisplayMetrics.widthPixels;
    this.d = localDisplayMetrics.heightPixels;
  }

  public void a(a parama)
  {
    long l2;
    if ((parama instanceof f))
    {
      l2 = System.currentTimeMillis();
      if (p != 0L)
        break label47;
    }
    label47: for (long l1 = -9999L; ; l1 = l2 - p)
    {
      q = l1;
      p = l2;
      o += 1;
      parama.a(this);
      return;
    }
  }

  public void a(String paramString)
  {
    this.s = paramString;
  }

  public String b()
  {
    return this.s;
  }

  public void b(String paramString)
  {
    this.r = paramString;
  }

  public String c()
  {
    return this.r;
  }

  public void c(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      this.b = paramString;
      return;
    }
    this.b = "-1";
  }

  public void d()
  {
    this.k = (System.currentTimeMillis() / 1000L);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.c.e
 * JD-Core Version:    0.6.2
 */