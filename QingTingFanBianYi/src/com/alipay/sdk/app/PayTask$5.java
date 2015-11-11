package com.alipay.sdk.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

class PayTask$5 extends BroadcastReceiver
{
  PayTask$5(PayTask paramPayTask)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("android.intent.action.PACKAGE_ADDED"))
    {
      paramContext = new PayTask.5.1(this);
      PayTask.e(this.a).post(paramContext);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.PayTask.5
 * JD-Core Version:    0.6.2
 */