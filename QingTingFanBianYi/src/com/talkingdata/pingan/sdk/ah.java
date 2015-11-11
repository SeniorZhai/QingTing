package com.talkingdata.pingan.sdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ah
{
  private static final ExecutorService a = Executors.newSingleThreadExecutor();

  public static void a(Runnable paramRunnable)
  {
    a.execute(paramRunnable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.ah
 * JD-Core Version:    0.6.2
 */