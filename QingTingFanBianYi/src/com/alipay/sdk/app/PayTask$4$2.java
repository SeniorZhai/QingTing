package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class PayTask$4$2
  implements DialogInterface.OnClickListener
{
  PayTask$4$2(PayTask.4 param4)
  {
  }

  public void onClick(DialogInterface arg1, int paramInt)
  {
    synchronized (PayTask.a)
    {
      Result.a(Result.b());
    }
    try
    {
      PayTask.a.notify();
      label18: return;
      localObject = finally;
      throw localObject;
    }
    catch (Exception localException)
    {
      break label18;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.PayTask.4.2
 * JD-Core Version:    0.6.2
 */