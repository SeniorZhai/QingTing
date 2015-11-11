package com.alipay.sdk.app;

import android.webkit.WebView;

class H5AuthActivity$14
  implements Runnable
{
  H5AuthActivity$14(H5AuthActivity paramH5AuthActivity, String paramString)
  {
  }

  public void run()
  {
    try
    {
      H5AuthActivity.i(this.b).loadUrl("javascript:" + this.a);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5AuthActivity.14
 * JD-Core Version:    0.6.2
 */