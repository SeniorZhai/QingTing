package com.tendcloud.tenddata;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

class am
  implements Runnable
{
  am(j paramj, Context paramContext)
  {
  }

  public void run()
  {
    j.e(j.k());
    if (j.n() == 0L)
      j.b(System.currentTimeMillis());
    j.g();
    j.e = ae.c(this.a);
    Handler localHandler = d.a();
    localHandler.sendMessage(Message.obtain(localHandler, 0));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.am
 * JD-Core Version:    0.6.2
 */