package com.talkingdata.pingan.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

final class x
  implements Runnable
{
  x(Context paramContext)
  {
  }

  public void run()
  {
    PAAgent.d(PAAgent.f());
    if (PAAgent.i() == 0L)
      PAAgent.b(System.currentTimeMillis());
    int i = PAAgent.b();
    if (i > 0)
    {
      PAAgent.e = ai.c(this.a);
      if (i < 2)
        PAAgent.e[2] = null;
    }
    q.a(new String[] { "TCAgent init" });
    o.b();
    Handler localHandler = d.a();
    localHandler.sendMessage(Message.obtain(localHandler, 0));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.x
 * JD-Core Version:    0.6.2
 */