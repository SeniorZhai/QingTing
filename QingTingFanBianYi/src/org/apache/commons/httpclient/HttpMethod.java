package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.params.HttpMethodParams;

public abstract interface HttpMethod
{
  public abstract void abort();

  public abstract void addRequestHeader(String paramString1, String paramString2);

  public abstract void addRequestHeader(Header paramHeader);

  public abstract void addResponseFooter(Header paramHeader);

  public abstract int execute(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws HttpException, IOException;

  public abstract boolean getDoAuthentication();

  public abstract boolean getFollowRedirects();

  public abstract AuthState getHostAuthState();

  public abstract HostConfiguration getHostConfiguration();

  public abstract String getName();

  public abstract HttpMethodParams getParams();

  public abstract String getPath();

  public abstract AuthState getProxyAuthState();

  public abstract String getQueryString();

  public abstract Header getRequestHeader(String paramString);

  public abstract Header[] getRequestHeaders();

  public abstract Header[] getRequestHeaders(String paramString);

  public abstract byte[] getResponseBody()
    throws IOException;

  public abstract InputStream getResponseBodyAsStream()
    throws IOException;

  public abstract String getResponseBodyAsString()
    throws IOException;

  public abstract Header getResponseFooter(String paramString);

  public abstract Header[] getResponseFooters();

  public abstract Header getResponseHeader(String paramString);

  public abstract Header[] getResponseHeaders();

  public abstract Header[] getResponseHeaders(String paramString);

  public abstract int getStatusCode();

  public abstract StatusLine getStatusLine();

  public abstract String getStatusText();

  public abstract URI getURI()
    throws URIException;

  public abstract boolean hasBeenUsed();

  public abstract boolean isRequestSent();

  public abstract boolean isStrictMode();

  public abstract void recycle();

  public abstract void releaseConnection();

  public abstract void removeRequestHeader(String paramString);

  public abstract void removeRequestHeader(Header paramHeader);

  public abstract void setDoAuthentication(boolean paramBoolean);

  public abstract void setFollowRedirects(boolean paramBoolean);

  public abstract void setParams(HttpMethodParams paramHttpMethodParams);

  public abstract void setPath(String paramString);

  public abstract void setQueryString(String paramString);

  public abstract void setQueryString(NameValuePair[] paramArrayOfNameValuePair);

  public abstract void setRequestHeader(String paramString1, String paramString2);

  public abstract void setRequestHeader(Header paramHeader);

  public abstract void setStrictMode(boolean paramBoolean);

  public abstract void setURI(URI paramURI)
    throws URIException;

  public abstract boolean validate();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethod
 * JD-Core Version:    0.6.2
 */