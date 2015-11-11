package com.intowow.sdk.i.b;

import android.os.Handler;

public class a
{
  private Handler a = null;
  private int b = 0;
  private long c = 0L;
  private a d = null;
  private Runnable e = new Runnable()
  {
    public void run()
    {
      if (a.a(a.this) == 0)
      {
        if (a.b(a.this) != null)
          a.b(a.this).a((int)(System.currentTimeMillis() - a.c(a.this)));
        a.a(a.this, 0L);
      }
    }
  };

  public a(a parama)
  {
    this.d = parama;
  }

  public void a()
  {
    int i = this.b;
    this.b = (i + 1);
    if ((i == 0) && (this.c == 0L))
    {
      if (this.d != null)
        this.d.a();
      this.a.removeCallbacks(this.e);
      this.c = System.currentTimeMillis();
    }
  }

  public void b()
  {
    int i = this.b - 1;
    this.b = i;
    if (i == 0)
    {
      this.a.removeCallbacks(this.e);
      this.a.postDelayed(this.e, 2000L);
    }
  }

  public static abstract interface a
  {
    public abstract void a();

    public abstract void a(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.b.a
 * JD-Core Version:    0.6.2
 */