package org.apache.commons.httpclient.params;

public class HttpClientParams extends HttpMethodParams
{
  public static final String ALLOW_CIRCULAR_REDIRECTS = "http.protocol.allow-circular-redirects";
  public static final String CONNECTION_MANAGER_CLASS = "http.connection-manager.class";
  public static final String CONNECTION_MANAGER_TIMEOUT = "http.connection-manager.timeout";
  public static final String MAX_REDIRECTS = "http.protocol.max-redirects";
  public static final String PREEMPTIVE_AUTHENTICATION = "http.authentication.preemptive";
  private static final String[] PROTOCOL_STRICTNESS_PARAMETERS = { "http.protocol.reject-relative-redirect", "http.protocol.allow-circular-redirects" };
  public static final String REJECT_RELATIVE_REDIRECT = "http.protocol.reject-relative-redirect";

  public HttpClientParams()
  {
  }

  public HttpClientParams(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public Class getConnectionManagerClass()
  {
    return (Class)getParameter("http.connection-manager.class");
  }

  public long getConnectionManagerTimeout()
  {
    return getLongParameter("http.connection-manager.timeout", 0L);
  }

  public boolean isAuthenticationPreemptive()
  {
    return getBooleanParameter("http.authentication.preemptive", false);
  }

  public void makeLenient()
  {
    super.makeLenient();
    setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.FALSE);
  }

  public void makeStrict()
  {
    super.makeStrict();
    setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.TRUE);
  }

  public void setAuthenticationPreemptive(boolean paramBoolean)
  {
    setBooleanParameter("http.authentication.preemptive", paramBoolean);
  }

  public void setConnectionManagerClass(Class paramClass)
  {
    setParameter("http.connection-manager.class", paramClass);
  }

  public void setConnectionManagerTimeout(long paramLong)
  {
    setLongParameter("http.connection-manager.timeout", paramLong);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HttpClientParams
 * JD-Core Version:    0.6.2
 */