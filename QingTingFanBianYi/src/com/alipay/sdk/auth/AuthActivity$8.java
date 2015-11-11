package com.alipay.sdk.auth;

import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class AuthActivity$8 extends WebChromeClient
{
  AuthActivity$8(AuthActivity paramAuthActivity)
  {
  }

  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    String str2 = paramConsoleMessage.message();
    if (TextUtils.isEmpty(str2))
      return super.onConsoleMessage(paramConsoleMessage);
    String str1 = null;
    if (str2.startsWith("h5container.message: "))
      str1 = str2.replaceFirst("h5container.message: ", "");
    if (TextUtils.isEmpty(str1))
      return super.onConsoleMessage(paramConsoleMessage);
    AuthActivity.c(this.a, str1);
    return super.onConsoleMessage(paramConsoleMessage);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthActivity.8
 * JD-Core Version:    0.6.2
 */