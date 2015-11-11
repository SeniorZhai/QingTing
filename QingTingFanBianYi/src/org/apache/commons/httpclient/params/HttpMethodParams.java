package org.apache.commons.httpclient.params;

import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpMethodParams extends DefaultHttpParams
{
  public static final String BUFFER_WARN_TRIGGER_LIMIT = "http.method.response.buffer.warnlimit";
  public static final String COOKIE_POLICY = "http.protocol.cookie-policy";
  public static final String CREDENTIAL_CHARSET = "http.protocol.credential-charset";
  public static final String DATE_PATTERNS = "http.dateparser.patterns";
  public static final String HEAD_BODY_CHECK_TIMEOUT = "http.protocol.head-body-timeout";
  public static final String HTTP_CONTENT_CHARSET = "http.protocol.content-charset";
  public static final String HTTP_ELEMENT_CHARSET = "http.protocol.element-charset";
  private static final Log LOG;
  public static final String MULTIPART_BOUNDARY = "http.method.multipart.boundary";
  private static final String[] PROTOCOL_STRICTNESS_PARAMETERS;
  public static final String PROTOCOL_VERSION = "http.protocol.version";
  public static final String REJECT_HEAD_BODY = "http.protocol.reject-head-body";
  public static final String RETRY_HANDLER = "http.method.retry-handler";
  public static final String SINGLE_COOKIE_HEADER = "http.protocol.single-cookie-header";
  public static final String SO_TIMEOUT = "http.socket.timeout";
  public static final String STATUS_LINE_GARBAGE_LIMIT = "http.protocol.status-line-garbage-limit";
  public static final String STRICT_TRANSFER_ENCODING = "http.protocol.strict-transfer-encoding";
  public static final String UNAMBIGUOUS_STATUS_LINE = "http.protocol.unambiguous-statusline";
  public static final String USER_AGENT = "http.useragent";
  public static final String USE_EXPECT_CONTINUE = "http.protocol.expect-continue";
  public static final String VIRTUAL_HOST = "http.virtual-host";
  public static final String WARN_EXTRA_INPUT = "http.protocol.warn-extra-input";
  static Class class$org$apache$commons$httpclient$params$HttpMethodParams;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$params$HttpMethodParams == null)
    {
      localClass = class$("org.apache.commons.httpclient.params.HttpMethodParams");
      class$org$apache$commons$httpclient$params$HttpMethodParams = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      PROTOCOL_STRICTNESS_PARAMETERS = new String[] { "http.protocol.unambiguous-statusline", "http.protocol.single-cookie-header", "http.protocol.strict-transfer-encoding", "http.protocol.reject-head-body", "http.protocol.warn-extra-input" };
      return;
      localClass = class$org$apache$commons$httpclient$params$HttpMethodParams;
    }
  }

  public HttpMethodParams()
  {
    super(DefaultHttpParams.getDefaultParams());
  }

  public HttpMethodParams(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  public String getContentCharset()
  {
    String str2 = (String)getParameter("http.protocol.content-charset");
    String str1 = str2;
    if (str2 == null)
    {
      LOG.warn("Default content charset not configured, using ISO-8859-1");
      str1 = "ISO-8859-1";
    }
    return str1;
  }

  public String getCookiePolicy()
  {
    Object localObject = getParameter("http.protocol.cookie-policy");
    if (localObject == null)
      return "default";
    return (String)localObject;
  }

  public String getCredentialCharset()
  {
    String str2 = (String)getParameter("http.protocol.credential-charset");
    String str1 = str2;
    if (str2 == null)
    {
      LOG.debug("Credential charset not configured, using HTTP element charset");
      str1 = getHttpElementCharset();
    }
    return str1;
  }

  public String getHttpElementCharset()
  {
    String str2 = (String)getParameter("http.protocol.element-charset");
    String str1 = str2;
    if (str2 == null)
    {
      LOG.warn("HTTP element charset not configured, using US-ASCII");
      str1 = "US-ASCII";
    }
    return str1;
  }

  public int getSoTimeout()
  {
    return getIntParameter("http.socket.timeout", 0);
  }

  public HttpVersion getVersion()
  {
    Object localObject = getParameter("http.protocol.version");
    if (localObject == null)
      return HttpVersion.HTTP_1_1;
    return (HttpVersion)localObject;
  }

  public String getVirtualHost()
  {
    return (String)getParameter("http.virtual-host");
  }

  public void makeLenient()
  {
    setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.FALSE);
    setIntParameter("http.protocol.status-line-garbage-limit", 2147483647);
  }

  public void makeStrict()
  {
    setParameters(PROTOCOL_STRICTNESS_PARAMETERS, Boolean.TRUE);
    setIntParameter("http.protocol.status-line-garbage-limit", 0);
  }

  public void setContentCharset(String paramString)
  {
    setParameter("http.protocol.content-charset", paramString);
  }

  public void setCookiePolicy(String paramString)
  {
    setParameter("http.protocol.cookie-policy", paramString);
  }

  public void setCredentialCharset(String paramString)
  {
    setParameter("http.protocol.credential-charset", paramString);
  }

  public void setHttpElementCharset(String paramString)
  {
    setParameter("http.protocol.element-charset", paramString);
  }

  public void setSoTimeout(int paramInt)
  {
    setIntParameter("http.socket.timeout", paramInt);
  }

  public void setVersion(HttpVersion paramHttpVersion)
  {
    setParameter("http.protocol.version", paramHttpVersion);
  }

  public void setVirtualHost(String paramString)
  {
    setParameter("http.virtual-host", paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HttpMethodParams
 * JD-Core Version:    0.6.2
 */