package org.apache.commons.httpclient.params;

public class HostParams extends DefaultHttpParams
{
  public static final String DEFAULT_HEADERS = "http.default-headers";

  public HostParams()
  {
  }

  public HostParams(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public String getVirtualHost()
  {
    return (String)getParameter("http.virtual-host");
  }

  public void setVirtualHost(String paramString)
  {
    setParameter("http.virtual-host", paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HostParams
 * JD-Core Version:    0.6.2
 */