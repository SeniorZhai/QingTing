package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class w
{
  private static final String h = "successful_request";
  private static final String i = "failed_requests ";
  private static final String j = "last_request_spent_ms";
  private static final String k = "last_request_time";
  private static final String l = "first_activate_time";
  public int a;
  public int b;
  public long c;
  private final int d = 3600000;
  private int e;
  private long f = 0L;
  private long g = 0L;
  private Context m;

  public w(Context paramContext)
  {
    b(paramContext);
  }

  public static aj a(Context paramContext)
  {
    paramContext = u.a(paramContext);
    aj localaj = new aj();
    localaj.c(paramContext.getInt("failed_requests ", 0));
    localaj.d(paramContext.getInt("last_request_spent_ms", 0));
    localaj.a(paramContext.getInt("successful_request", 0));
    return localaj;
  }

  private void b(Context paramContext)
  {
    this.m = paramContext.getApplicationContext();
    paramContext = u.a(paramContext);
    this.a = paramContext.getInt("successful_request", 0);
    this.b = paramContext.getInt("failed_requests ", 0);
    this.e = paramContext.getInt("last_request_spent_ms", 0);
    this.c = paramContext.getLong("last_request_time", 0L);
  }

  public int a()
  {
    if (this.e > 3600000)
      return 3600000;
    return this.e;
  }

  public boolean b()
  {
    return this.c == 0L;
  }

  public void c()
  {
    this.a += 1;
    this.c = this.f;
  }

  public void d()
  {
    this.b += 1;
  }

  public void e()
  {
    this.f = System.currentTimeMillis();
  }

  public void f()
  {
    this.e = ((int)(System.currentTimeMillis() - this.f));
  }

  public void g()
  {
    u.a(this.m).edit().putInt("successful_request", this.a).putInt("failed_requests ", this.b).putInt("last_request_spent_ms", this.e).putLong("last_request_time", this.c).commit();
  }

  public void h()
  {
    u.a(this.m).edit().putLong("first_activate_time", System.currentTimeMillis()).commit();
  }

  public boolean i()
  {
    if (this.g == 0L)
      this.g = u.a(this.m).getLong("first_activate_time", 0L);
    return this.g == 0L;
  }

  public long j()
  {
    if (i())
      return System.currentTimeMillis();
    return this.g;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.w
 * JD-Core Version:    0.6.2
 */