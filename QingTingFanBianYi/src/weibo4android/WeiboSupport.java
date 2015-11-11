package weibo4android;

import weibo4android.http.HttpClient;

class WeiboSupport
{
  protected final boolean USE_SSL = Configuration.useSSL();
  protected HttpClient http = new HttpClient();
  protected String source = Configuration.getSource();

  WeiboSupport()
  {
    this(null, null);
  }

  WeiboSupport(String paramString1, String paramString2)
  {
    setClientVersion(null);
    setClientURL(null);
  }

  public void forceUsePost(boolean paramBoolean)
  {
  }

  public String getClientURL()
  {
    return this.http.getRequestHeader("X-Weibo-Client-URL");
  }

  public String getClientVersion()
  {
    return this.http.getRequestHeader("X-Weibo-Client-Version");
  }

  public String getPassword()
  {
    return this.http.getPassword();
  }

  public String getSource()
  {
    return this.source;
  }

  public String getUserAgent()
  {
    return this.http.getUserAgent();
  }

  public String getUserId()
  {
    return this.http.getUserId();
  }

  public boolean isUsePostForced()
  {
    return false;
  }

  public void setClientURL(String paramString)
  {
    setRequestHeader("X-Weibo-Client-URL", Configuration.getClientURL(paramString));
  }

  public void setClientVersion(String paramString)
  {
    setRequestHeader("X-Weibo-Client-Version", Configuration.getCilentVersion(paramString));
  }

  public void setHttpConnectionTimeout(int paramInt)
  {
    this.http.setConnectionTimeout(paramInt);
  }

  public void setHttpProxy(String paramString, int paramInt)
  {
    this.http.setProxyHost(paramString);
    this.http.setProxyPort(paramInt);
  }

  public void setHttpProxyAuth(String paramString1, String paramString2)
  {
    this.http.setProxyAuthUser(paramString1);
    this.http.setProxyAuthPassword(paramString2);
  }

  public void setHttpReadTimeout(int paramInt)
  {
    this.http.setReadTimeout(paramInt);
  }

  public void setRequestHeader(String paramString1, String paramString2)
  {
    this.http.setRequestHeader(paramString1, paramString2);
  }

  public void setRetryCount(int paramInt)
  {
    this.http.setRetryCount(paramInt);
  }

  public void setRetryIntervalSecs(int paramInt)
  {
    this.http.setRetryIntervalSecs(paramInt);
  }

  public void setSource(String paramString)
  {
    this.source = Configuration.getSource(paramString);
    setRequestHeader("X-Weibo-Client", this.source);
  }

  public void setUserAgent(String paramString)
  {
    this.http.setUserAgent(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.WeiboSupport
 * JD-Core Version:    0.6.2
 */