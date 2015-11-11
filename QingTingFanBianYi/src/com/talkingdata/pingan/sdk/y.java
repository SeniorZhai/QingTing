package com.talkingdata.pingan.sdk;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

final class y
  implements Runnable
{
  y(int paramInt, String paramString, Activity paramActivity)
  {
  }

  public void run()
  {
    try
    {
      q.a(new String[] { "onPage:", String.valueOf(this.a), this.b });
      if (!PAAgent.p())
        PAAgent.init(this.c);
      if (PAAgent.q() == null)
        return;
      Message localMessage = Message.obtain(d.a(), this.a, this.b);
      d.a().sendMessage(localMessage);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.y
 * JD-Core Version:    0.6.2
 */