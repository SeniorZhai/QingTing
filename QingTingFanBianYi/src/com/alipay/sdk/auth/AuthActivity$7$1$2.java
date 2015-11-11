package com.alipay.sdk.auth;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;
import com.alipay.sdk.app.Result;

class AuthActivity$7$1$2
  implements DialogInterface.OnClickListener
{
  AuthActivity$7$1$2(AuthActivity.7.1 param1)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this.a.a.cancel();
    AuthActivity.a(this.a.b.a, false);
    Result.a(Result.b());
    this.a.b.a.finish();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity.7.1.2
 * JD-Core Version:    0.6.2
 */