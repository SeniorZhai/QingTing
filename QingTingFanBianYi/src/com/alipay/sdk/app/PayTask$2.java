package com.alipay.sdk.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import com.alipay.sdk.util.FileDownloader;
import com.alipay.sdk.widget.Loading;

class PayTask$2
  implements DialogInterface.OnCancelListener
{
  PayTask$2(PayTask paramPayTask, Loading paramLoading)
  {
  }

  public void onCancel(DialogInterface arg1)
  {
    this.a.c();
    PayTask.b(this.b).c();
    PayTask.a(this.b).unregisterReceiver(PayTask.c(this.b));
    PayTask.e(this.b).removeCallbacks(PayTask.d(this.b));
    synchronized (PayTask.a)
    {
      Result.a(Result.b());
    }
    try
    {
      PayTask.a.notify();
      label69: return;
      localObject = finally;
      throw localObject;
    }
    catch (Exception localException)
    {
      break label69;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.PayTask.2
 * JD-Core Version:    0.6.2
 */