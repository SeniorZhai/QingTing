package com.sina.weibo.sdk.net;

import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.StatusLine;
import org.apache.http.client.RedirectHandler;
import org.apache.http.protocol.HttpContext;

public abstract class CustomRedirectHandler
  implements RedirectHandler
{
  private static final int MAX_REDIRECT_COUNT = 15;
  private static final String TAG = CustomRedirectHandler.class.getCanonicalName();
  int redirectCount;
  String redirectUrl;
  private String tempRedirectUrl;

  public URI getLocationURI(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws ProtocolException
  {
    LogUtil.d(TAG, "CustomRedirectHandler getLocationURI getRedirectUrl : " + this.tempRedirectUrl);
    if (!TextUtils.isEmpty(this.tempRedirectUrl))
      return URI.create(this.tempRedirectUrl);
    return null;
  }

  public int getRedirectCount()
  {
    return this.redirectCount;
  }

  public String getRedirectUrl()
  {
    return this.redirectUrl;
  }

  public boolean isRedirectRequested(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if ((i == 301) || (i == 302))
    {
      this.tempRedirectUrl = paramHttpResponse.getFirstHeader("Location").getValue();
      if ((!TextUtils.isEmpty(this.tempRedirectUrl)) && (this.redirectCount < 15) && (shouldRedirectUrl(this.tempRedirectUrl)))
      {
        this.redirectCount += 1;
        return true;
      }
    }
    else
    {
      if (i != 200)
        break label102;
      this.redirectUrl = this.tempRedirectUrl;
    }
    while (true)
    {
      return false;
      label102: onReceivedException();
    }
  }

  public abstract void onReceivedException();

  public abstract boolean shouldRedirectUrl(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.CustomRedirectHandler
 * JD-Core Version:    0.6.2
 */