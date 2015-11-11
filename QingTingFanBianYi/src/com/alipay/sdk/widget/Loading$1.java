package com.alipay.sdk.widget;

import android.app.ProgressDialog;
import android.content.DialogInterface.OnCancelListener;

class Loading$1
  implements Runnable
{
  Loading$1(Loading paramLoading, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener, CharSequence paramCharSequence)
  {
  }

  public void run()
  {
    if (Loading.a(this.d) == null)
      Loading.a(this.d, new ProgressDialog(Loading.b(this.d)));
    Loading.a(this.d).setCancelable(this.a);
    Loading.a(this.d).setOnCancelListener(this.b);
    Loading.a(this.d).setMessage(this.c);
    try
    {
      Loading.a(this.d).show();
      return;
    }
    catch (Exception localException)
    {
      Loading.a(this.d, null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.widget.Loading.1
 * JD-Core Version:    0.6.2
 */