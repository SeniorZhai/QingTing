package com.alipay.sdk.app;

import android.app.Activity;
import android.app.Dialog;

class PayTask$5$1
  implements Runnable
{
  PayTask$5$1(PayTask.5 param5)
  {
  }

  public void run()
  {
    if (PayTask.h(this.a.a) != null)
      PayTask.h(this.a.a).dismiss();
    PayTask.a(this.a.a, true);
    PayTask.a(this.a.a).unregisterReceiver(PayTask.c(this.a.a));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.PayTask.5.1
 * JD-Core Version:    0.6.2
 */