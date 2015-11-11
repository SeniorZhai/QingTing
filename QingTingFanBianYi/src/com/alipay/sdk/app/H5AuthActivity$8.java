package com.alipay.sdk.app;

import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class H5AuthActivity$8 extends WebChromeClient
{
  H5AuthActivity$8(H5AuthActivity paramH5AuthActivity)
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
    H5AuthActivity.b(this.a, str1);
    return super.onConsoleMessage(paramConsoleMessage);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5AuthActivity.8
 * JD-Core Version:    0.6.2
 */