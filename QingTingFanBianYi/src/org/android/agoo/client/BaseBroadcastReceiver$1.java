package org.android.agoo.client;

import android.content.Context;
import android.content.Intent;

class BaseBroadcastReceiver$1
  implements Runnable
{
  BaseBroadcastReceiver$1(BaseBroadcastReceiver paramBaseBroadcastReceiver, Context paramContext, Intent paramIntent)
  {
  }

  public void run()
  {
    try
    {
      this.c.a(this.a, this.b);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseBroadcastReceiver.1
 * JD-Core Version:    0.6.2
 */