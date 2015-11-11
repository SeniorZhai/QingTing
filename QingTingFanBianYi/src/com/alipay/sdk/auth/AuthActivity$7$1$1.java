package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

class AuthActivity$7$1$1
  implements DialogInterface.OnClickListener
{
  AuthActivity$7$1$1(AuthActivity.7.1 param1)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    AuthActivity.a(this.a.b.a, true);
    this.a.a.proceed();
    paramDialogInterface.dismiss();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity.7.1.1
 * JD-Core Version:    0.6.2
 */