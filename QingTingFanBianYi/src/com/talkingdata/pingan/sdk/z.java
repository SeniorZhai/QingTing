package com.talkingdata.pingan.sdk;

class z
  implements Runnable
{
  z(aj paramaj, String paramString)
  {
  }

  public void run()
  {
    try
    {
      ??? = aj.a(this.b, this.a);
      if (!this.b.g)
        this.b.a = ((t)???);
      synchronized (this.b)
      {
        this.b.notifyAll();
        Thread.sleep(3000L);
        if (this.b.a.b())
          aj.a(this.b, this.b.a);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.z
 * JD-Core Version:    0.6.2
 */