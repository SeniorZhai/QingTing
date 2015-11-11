package com.alipay.sdk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

class H5PayActivity$MyWebViewClient$1$1
  implements DialogInterface.OnClickListener
{
  H5PayActivity$MyWebViewClient$1$1(H5PayActivity.MyWebViewClient.1 param1)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    H5PayActivity.a(this.a.b.a, true);
    this.a.a.proceed();
    paramDialogInterface.dismiss();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5PayActivity.MyWebViewClient.1.1
 * JD-Core Version:    0.6.2
 */