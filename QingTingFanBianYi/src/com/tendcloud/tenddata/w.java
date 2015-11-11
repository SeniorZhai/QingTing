package com.tendcloud.tenddata;

class w
  implements Runnable
{
  w(ag paramag, String paramString)
  {
  }

  public void run()
  {
    try
    {
      ??? = ag.a(this.b, this.a);
      if (!this.b.l)
        this.b.a = ((u)???);
      synchronized (this.b)
      {
        this.b.notifyAll();
        Thread.sleep(3000L);
        if (this.b.a.b())
          ag.a(this.b, this.b.a);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.w
 * JD-Core Version:    0.6.2
 */