package com.taobao.munion.base.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

public class e
  implements o
{
  private final Executor a;

  public e(final Handler paramHandler)
  {
    this.a = new Executor()
    {
      public void execute(Runnable paramAnonymousRunnable)
      {
        paramHandler.post(paramAnonymousRunnable);
      }
    };
  }

  public e(Executor paramExecutor)
  {
    this.a = paramExecutor;
  }

  public void a(l<?> paraml, n<?> paramn)
  {
    a(paraml, paramn, null);
  }

  public void a(l<?> paraml, n<?> paramn, Runnable paramRunnable)
  {
    paraml.x();
    paraml.a("post-response");
    this.a.execute(new a(paraml, paramn, paramRunnable));
  }

  public void a(l<?> paraml, s params)
  {
    paraml.a("post-error");
    params = n.a(params);
    this.a.execute(new a(paraml, params, null));
  }

  private class a
    implements Runnable
  {
    private final l b;
    private final n c;
    private final Runnable d;

    public a(l paramn, n paramRunnable, Runnable arg4)
    {
      this.b = paramn;
      this.c = paramRunnable;
      Object localObject;
      this.d = localObject;
    }

    public void run()
    {
      if (this.b.j())
        this.b.b("canceled-at-delivery");
      label97: label107: 
      while (true)
      {
        return;
        if (this.c.a())
        {
          this.b.c(this.c.a);
          if (!this.c.d)
            break label97;
          this.b.a("intermediate-response");
        }
        while (true)
        {
          if (this.d == null)
            break label107;
          this.d.run();
          return;
          this.b.b(this.c.c);
          break;
          this.b.b("done");
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.e
 * JD-Core Version:    0.6.2
 */