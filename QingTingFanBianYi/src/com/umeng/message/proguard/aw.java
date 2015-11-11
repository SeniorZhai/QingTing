package com.umeng.message.proguard;

class aw
  implements Runnable
{
  aw(at paramat, int paramInt)
  {
  }

  public void run()
  {
    try
    {
      this.b.f();
      this.b.g();
      Q.c("HttpChunked", "http chunked connect[" + this.a + "] connection disconnecting");
      this.b.c();
      Q.c("HttpChunked", "http chunked connect[" + this.a + "] connection disconnected");
      this.b.h();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aw
 * JD-Core Version:    0.6.2
 */