package com.taobao.newxp.view.common.gif;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

public class c
{
  private j a = null;
  private boolean b = false;
  private Handler c = new Handler(Looper.getMainLooper());
  private a d = new a(null);

  public void a()
  {
    synchronized (this.d)
    {
      this.c.removeCallbacks(this.d);
      this.b = true;
      return;
    }
  }

  public void a(j paramj)
  {
    this.a = paramj;
  }

  public void b()
  {
    synchronized (this.d)
    {
      this.b = false;
      this.c.post(this.d);
      return;
    }
  }

  public void c()
  {
    a();
  }

  public void d()
  {
    this.b = false;
    this.c.post(this.d);
  }

  public void e()
  {
    c();
    this.a = null;
  }

  private class a
    implements Runnable
  {
    private a()
    {
    }

    public void run()
    {
      int i = c.a(c.this).reDraw();
      if (!c.b(c.this))
      {
        if (i > 0)
          SystemClock.sleep(i);
        synchronized (c.c(c.this))
        {
          if (!c.b(c.this))
            c.d(c.this).post(c.c(c.this));
          return;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.gif.c
 * JD-Core Version:    0.6.2
 */