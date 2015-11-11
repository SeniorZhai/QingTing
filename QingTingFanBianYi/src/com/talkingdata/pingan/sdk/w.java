package com.talkingdata.pingan.sdk;

final class w
  implements Runnable
{
  w(Throwable paramThrowable)
  {
  }

  public void run()
  {
    try
    {
      if (!PAAgent.p())
        return;
      if (PAAgent.q() != null)
      {
        PAAgent.a(this.a, true);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.w
 * JD-Core Version:    0.6.2
 */