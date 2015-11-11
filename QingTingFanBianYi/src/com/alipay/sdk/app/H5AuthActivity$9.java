package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

class H5AuthActivity$9
  implements DownloadListener
{
  H5AuthActivity$9(H5AuthActivity paramH5AuthActivity)
  {
  }

  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
    this.a.startActivity(paramString1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5AuthActivity.9
 * JD-Core Version:    0.6.2
 */