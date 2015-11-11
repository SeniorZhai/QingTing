package com.alipay.sdk.auth;

import android.webkit.WebView;

class AuthActivity$14
  implements Runnable
{
  AuthActivity$14(AuthActivity paramAuthActivity, String paramString)
  {
  }

  public void run()
  {
    try
    {
      AuthActivity.i(this.b).loadUrl("javascript:" + this.a);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity.14
 * JD-Core Version:    0.6.2
 */