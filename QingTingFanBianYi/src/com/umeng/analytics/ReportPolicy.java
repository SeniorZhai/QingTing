package com.umeng.analytics;

import android.content.Context;
import u.aly.bi;
import u.aly.q;
import u.aly.w;

public class ReportPolicy
{
  public static final int BATCH_AT_LAUNCH = 1;
  public static final int BATCH_BY_INTERVAL = 6;
  public static final int BATCH_BY_SIZE = 7;
  public static final int DAILY = 4;
  public static final int REALTIME = 0;
  public static final int WIFIONLY = 5;
  static final int a = 2;
  static final int b = 3;

  public static class a extends ReportPolicy.e
  {
    public boolean a(boolean paramBoolean)
    {
      return paramBoolean;
    }
  }

  public static class b extends ReportPolicy.e
  {
    private long a = 10000L;
    private long b;
    private w c;

    public b(w paramw, long paramLong)
    {
      this.c = paramw;
      long l = paramLong;
      if (paramLong < this.a)
        l = this.a;
      this.b = l;
    }

    public long a()
    {
      return this.b;
    }

    public boolean a(boolean paramBoolean)
    {
      return System.currentTimeMillis() - this.c.c >= this.b;
    }
  }

  public static class c extends ReportPolicy.e
  {
    private final int a;
    private q b;

    public c(q paramq, int paramInt)
    {
      this.a = paramInt;
      this.b = paramq;
    }

    public boolean a(boolean paramBoolean)
    {
      return this.b.b() > this.a;
    }
  }

  public static class d extends ReportPolicy.e
  {
    private long a = 86400000L;
    private w b;

    public d(w paramw)
    {
      this.b = paramw;
    }

    public boolean a(boolean paramBoolean)
    {
      return System.currentTimeMillis() - this.b.c >= this.a;
    }
  }

  public static class e
  {
    public boolean a(boolean paramBoolean)
    {
      return true;
    }
  }

  public static class f extends ReportPolicy.e
  {
    private Context a = null;

    public f(Context paramContext)
    {
      this.a = paramContext;
    }

    public boolean a(boolean paramBoolean)
    {
      return bi.k(this.a);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.ReportPolicy
 * JD-Core Version:    0.6.2
 */