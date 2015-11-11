package u.aly;

import com.umeng.analytics.AnalyticsConfig;

public class m
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a;
  private t b;

  public m()
  {
    if (Thread.getDefaultUncaughtExceptionHandler() == this)
      return;
    this.a = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  private void a(Throwable paramThrowable)
  {
    if (AnalyticsConfig.CATCH_EXCEPTION)
    {
      this.b.a(paramThrowable);
      return;
    }
    this.b.a(null);
  }

  public void a(t paramt)
  {
    this.b = paramt;
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    a(paramThrowable);
    if ((this.a != null) && (this.a != Thread.getDefaultUncaughtExceptionHandler()))
      this.a.uncaughtException(paramThread, paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.m
 * JD-Core Version:    0.6.2
 */