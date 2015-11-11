package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.ReportPolicy.a;
import com.umeng.analytics.ReportPolicy.b;
import com.umeng.analytics.ReportPolicy.c;
import com.umeng.analytics.ReportPolicy.d;
import com.umeng.analytics.ReportPolicy.e;
import com.umeng.analytics.ReportPolicy.f;
import com.umeng.analytics.f;

public final class j
  implements com.umeng.analytics.onlineconfig.c, o
{
  private q a = null;
  private r b = null;
  private ReportPolicy.e c = null;
  private f d = null;
  private w e = null;
  private d f = null;
  private int g = 10;
  private Context h;

  public j(Context paramContext)
  {
    this.h = paramContext;
    this.a = new q(paramContext);
    this.f = h.a(paramContext);
    this.e = new w(paramContext);
    this.b = new r(paramContext);
    this.b.a(this.e);
    this.d = f.a(paramContext);
    paramContext = AnalyticsConfig.getReportPolicy(this.h);
    a(paramContext[0], paramContext[1]);
  }

  private bf a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      bf localbf = new bf();
      new cc().a(localbf, paramArrayOfByte);
      return localbf;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  private void a(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 2:
    case 3:
    default:
      this.c = new ReportPolicy.a();
    case 1:
    case 6:
    case 4:
    case 0:
    case 5:
    case 7:
    }
    while (true)
    {
      bj.c("MobclickAgent", "report policy:" + paramInt1 + " interval:" + paramInt2);
      return;
      this.c = new ReportPolicy.a();
      continue;
      this.c = new ReportPolicy.b(this.e, paramInt2);
      continue;
      this.c = new ReportPolicy.d(this.e);
      continue;
      this.c = new ReportPolicy.e();
      continue;
      this.c = new ReportPolicy.f(this.h);
      continue;
      this.c = new ReportPolicy.c(this.a, paramInt2);
    }
  }

  private boolean a(boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!bi.l(this.h))
    {
      if (bj.a)
        bj.c("MobclickAgent", "network is unavailable");
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.e.b());
      if (!bj.a)
        break;
      bool1 = bool2;
    }
    while (bi.w(this.h));
    return this.c.a(paramBoolean);
  }

  private byte[] a(bf parambf)
  {
    try
    {
      parambf = new ci().a(parambf);
      return parambf;
    }
    catch (Exception parambf)
    {
      parambf.printStackTrace();
    }
    return null;
  }

  private boolean d()
  {
    return this.a.b() > this.g;
  }

  private void e()
  {
    try
    {
      if (this.e.b())
        this.a.a(new ah(this.e.j()));
      f();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (((localThrowable instanceof OutOfMemoryError)) && (localThrowable == null));
      localThrowable.printStackTrace();
    }
  }

  private void f()
  {
    f localf = f.a(this.h);
    boolean bool = localf.f();
    byte[] arrayOfByte;
    if (bool)
      arrayOfByte = localf.d();
    switch (this.b.a(arrayOfByte))
    {
    default:
    case 2:
    case 3:
      do
      {
        do
        {
          return;
          this.f.a();
          arrayOfByte = c();
          if (arrayOfByte == null)
          {
            bj.e("MobclickAgent", "message is null");
            return;
          }
          arrayOfByte = c.a(this.h, AnalyticsConfig.getAppkey(this.h), arrayOfByte).c();
          localf.c();
          break;
          if (this.e.i())
            this.e.h();
          this.f.d();
          this.e.g();
        }
        while (!bool);
        localf.e();
        return;
        this.e.g();
      }
      while (!bool);
      localf.e();
      return;
    case 1:
    }
    if (!bool)
      localf.b(arrayOfByte);
    bj.b("MobclickAgent", "connection error");
  }

  public void a()
  {
    if (bi.l(this.h))
      e();
    while (!bj.a)
      return;
    bj.c("MobclickAgent", "network is unavailable");
  }

  public void a(int paramInt, long paramLong)
  {
    AnalyticsConfig.setReportPolicy(paramInt, (int)paramLong);
    a(paramInt, (int)paramLong);
  }

  public void a(p paramp)
  {
    if (paramp != null)
      this.a.a(paramp);
    if (a(paramp instanceof bd))
      e();
    while (!d())
      return;
    b();
  }

  public void b()
  {
    if (this.a.b() > 0);
    try
    {
      byte[] arrayOfByte = c();
      if (arrayOfByte != null)
        this.d.a(arrayOfByte);
      return;
    }
    catch (Throwable localThrowable)
    {
      do
        if ((localThrowable instanceof OutOfMemoryError))
          this.d.c();
      while (localThrowable == null);
      localThrowable.printStackTrace();
    }
  }

  public void b(p paramp)
  {
    this.a.a(paramp);
  }

  // ERROR //
  protected byte[] c()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	u/aly/j:h	Landroid/content/Context;
    //   4: invokestatic 212	com/umeng/analytics/AnalyticsConfig:getAppkey	(Landroid/content/Context;)Ljava/lang/String;
    //   7: invokestatic 261	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   10: ifeq +13 -> 23
    //   13: ldc 98
    //   15: ldc_w 263
    //   18: invokestatic 238	u/aly/bj:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: aconst_null
    //   22: areturn
    //   23: aload_0
    //   24: getfield 45	u/aly/j:h	Landroid/content/Context;
    //   27: invokestatic 68	com/umeng/analytics/f:a	(Landroid/content/Context;)Lcom/umeng/analytics/f;
    //   30: invokevirtual 265	com/umeng/analytics/f:b	()[B
    //   33: astore_1
    //   34: aload_1
    //   35: ifnonnull +21 -> 56
    //   38: aconst_null
    //   39: astore_1
    //   40: aload_1
    //   41: ifnonnull +24 -> 65
    //   44: aload_0
    //   45: getfield 31	u/aly/j:a	Lu/aly/q;
    //   48: invokevirtual 171	u/aly/q:b	()I
    //   51: ifne +14 -> 65
    //   54: aconst_null
    //   55: areturn
    //   56: aload_0
    //   57: aload_1
    //   58: invokespecial 267	u/aly/j:a	([B)Lu/aly/bf;
    //   61: astore_1
    //   62: goto -22 -> 40
    //   65: aload_1
    //   66: ifnonnull +150 -> 216
    //   69: new 83	u/aly/bf
    //   72: dup
    //   73: invokespecial 84	u/aly/bf:<init>	()V
    //   76: astore_1
    //   77: aload_0
    //   78: getfield 31	u/aly/j:a	Lu/aly/q;
    //   81: aload_1
    //   82: invokevirtual 270	u/aly/q:a	(Lu/aly/bf;)V
    //   85: getstatic 151	u/aly/bj:a	Z
    //   88: ifeq +63 -> 151
    //   91: aload_1
    //   92: invokevirtual 273	u/aly/bf:B	()Z
    //   95: ifeq +56 -> 151
    //   98: iconst_0
    //   99: istore_3
    //   100: aload_1
    //   101: invokevirtual 277	u/aly/bf:z	()Ljava/util/List;
    //   104: invokeinterface 283 1 0
    //   109: astore_2
    //   110: aload_2
    //   111: invokeinterface 288 1 0
    //   116: ifeq +23 -> 139
    //   119: aload_2
    //   120: invokeinterface 292 1 0
    //   125: checkcast 248	u/aly/bd
    //   128: invokevirtual 295	u/aly/bd:p	()I
    //   131: ifle +82 -> 213
    //   134: iconst_1
    //   135: istore_3
    //   136: goto +85 -> 221
    //   139: iload_3
    //   140: ifne +11 -> 151
    //   143: ldc 98
    //   145: ldc_w 297
    //   148: invokestatic 208	u/aly/bj:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: aload_0
    //   152: aload_1
    //   153: invokespecial 299	u/aly/j:a	(Lu/aly/bf;)[B
    //   156: astore_2
    //   157: getstatic 151	u/aly/bj:a	Z
    //   160: ifeq +59 -> 219
    //   163: ldc 98
    //   165: aload_1
    //   166: invokevirtual 300	u/aly/bf:toString	()Ljava/lang/String;
    //   169: invokestatic 121	u/aly/bj:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: aload_2
    //   173: areturn
    //   174: astore_1
    //   175: ldc 98
    //   177: ldc_w 302
    //   180: invokestatic 238	u/aly/bj:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   183: aload_2
    //   184: areturn
    //   185: astore_1
    //   186: ldc 98
    //   188: ldc_w 304
    //   191: aload_1
    //   192: invokestatic 307	u/aly/bj:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   195: aload_0
    //   196: getfield 45	u/aly/j:h	Landroid/content/Context;
    //   199: invokestatic 68	com/umeng/analytics/f:a	(Landroid/content/Context;)Lcom/umeng/analytics/f;
    //   202: invokevirtual 220	com/umeng/analytics/f:c	()V
    //   205: aconst_null
    //   206: areturn
    //   207: astore_1
    //   208: aconst_null
    //   209: astore_2
    //   210: goto -35 -> 175
    //   213: goto +8 -> 221
    //   216: goto -139 -> 77
    //   219: aload_2
    //   220: areturn
    //   221: goto -111 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   157	172	174	java/lang/Exception
    //   0	21	185	java/lang/Exception
    //   23	34	185	java/lang/Exception
    //   44	54	185	java/lang/Exception
    //   56	62	185	java/lang/Exception
    //   69	77	185	java/lang/Exception
    //   77	98	185	java/lang/Exception
    //   100	110	185	java/lang/Exception
    //   110	134	185	java/lang/Exception
    //   143	151	185	java/lang/Exception
    //   175	183	185	java/lang/Exception
    //   151	157	207	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.j
 * JD-Core Version:    0.6.2
 */