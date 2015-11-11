package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class HttpAuthenticator
{
  private static final Log LOG;
  public static final String PROXY_AUTH = "Proxy-Authenticate";
  public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
  public static final String WWW_AUTH = "WWW-Authenticate";
  public static final String WWW_AUTH_RESP = "Authorization";
  static Class class$org$apache$commons$httpclient$auth$HttpAuthenticator;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$HttpAuthenticator == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.HttpAuthenticator");
      class$org$apache$commons$httpclient$auth$HttpAuthenticator = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$auth$HttpAuthenticator;
    }
  }

  public static boolean authenticate(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
    throws AuthenticationException
  {
    LOG.trace("enter HttpAuthenticator.authenticate(AuthScheme, HttpMethod, HttpConnection, HttpState)");
    return doAuthenticate(paramAuthScheme, paramHttpMethod, paramHttpConnection, paramHttpState, false);
  }

  public static boolean authenticateDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
    throws AuthenticationException
  {
    LOG.trace("enter HttpAuthenticator.authenticateDefault(HttpMethod, HttpConnection, HttpState)");
    return doAuthenticateDefault(paramHttpMethod, paramHttpConnection, paramHttpState, false);
  }

  public static boolean authenticateProxy(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
    throws AuthenticationException
  {
    LOG.trace("enter HttpAuthenticator.authenticateProxy(AuthScheme, HttpMethod, HttpState)");
    return doAuthenticate(paramAuthScheme, paramHttpMethod, paramHttpConnection, paramHttpState, true);
  }

  public static boolean authenticateProxyDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState)
    throws AuthenticationException
  {
    LOG.trace("enter HttpAuthenticator.authenticateProxyDefault(HttpMethod, HttpState)");
    return doAuthenticateDefault(paramHttpMethod, paramHttpConnection, paramHttpState, true);
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

  private static boolean doAuthenticate(AuthScheme paramAuthScheme, HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState, boolean paramBoolean)
    throws AuthenticationException
  {
    if (paramAuthScheme == null)
      throw new IllegalArgumentException("Authentication scheme may not be null");
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HTTP method may not be null");
    if (paramHttpState == null)
      throw new IllegalArgumentException("HTTP state may not be null");
    Object localObject = null;
    String str;
    if (paramHttpConnection != null)
    {
      if (paramBoolean)
        localObject = paramHttpConnection.getProxyHost();
    }
    else
    {
      str = paramAuthScheme.getRealm();
      if (LOG.isDebugEnabled())
      {
        paramHttpConnection = new StringBuffer();
        paramHttpConnection.append("Using credentials for ");
        if (str != null)
          break label232;
        paramHttpConnection.append("default");
        label106: paramHttpConnection.append(" authentication realm at ");
        paramHttpConnection.append((String)localObject);
        LOG.debug(paramHttpConnection.toString());
      }
      if (!paramBoolean)
        break label256;
      paramHttpConnection = paramHttpState.getProxyCredentials(str, (String)localObject);
      label146: if (paramHttpConnection != null)
        break label292;
      paramAuthScheme = new StringBuffer();
      paramAuthScheme.append("No credentials available for the ");
      if (str != null)
        break label268;
      paramAuthScheme.append("default");
    }
    while (true)
    {
      paramAuthScheme.append(" authentication realm at ");
      paramAuthScheme.append((String)localObject);
      throw new CredentialsNotAvailableException(paramAuthScheme.toString());
      str = paramHttpMethod.getParams().getVirtualHost();
      localObject = str;
      if (str != null)
        break;
      localObject = paramHttpConnection.getHost();
      break;
      label232: paramHttpConnection.append('\'');
      paramHttpConnection.append(str);
      paramHttpConnection.append('\'');
      break label106;
      label256: paramHttpConnection = paramHttpState.getCredentials(str, (String)localObject);
      break label146;
      label268: paramAuthScheme.append('\'');
      paramAuthScheme.append(str);
      paramAuthScheme.append('\'');
    }
    label292: paramHttpConnection = paramAuthScheme.authenticate(paramHttpConnection, paramHttpMethod);
    if (paramHttpConnection != null)
    {
      if (paramBoolean);
      for (paramAuthScheme = "Proxy-Authorization"; ; paramAuthScheme = "Authorization")
      {
        paramHttpMethod.addRequestHeader(new Header(paramAuthScheme, paramHttpConnection, true));
        return true;
      }
    }
    return false;
  }

  private static boolean doAuthenticateDefault(HttpMethod paramHttpMethod, HttpConnection paramHttpConnection, HttpState paramHttpState, boolean paramBoolean)
    throws AuthenticationException
  {
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("HTTP method may not be null");
    if (paramHttpState == null)
      throw new IllegalArgumentException("HTTP state may not be null");
    String str = null;
    if (paramHttpConnection != null)
    {
      if (paramBoolean)
        str = paramHttpConnection.getProxyHost();
    }
    else
    {
      if (!paramBoolean)
        break label72;
      paramHttpConnection = paramHttpState.getProxyCredentials(null, str);
      label57: if (paramHttpConnection != null)
        break label83;
    }
    label72: label83: 
    do
    {
      return false;
      str = paramHttpConnection.getHost();
      break;
      paramHttpConnection = paramHttpState.getCredentials(null, str);
      break label57;
      if (!(paramHttpConnection instanceof UsernamePasswordCredentials))
        throw new InvalidCredentialsException("Credentials cannot be used for basic authentication: " + paramHttpConnection.toString());
      paramHttpState = BasicScheme.authenticate((UsernamePasswordCredentials)paramHttpConnection, paramHttpMethod.getParams().getCredentialCharset());
    }
    while (paramHttpState == null);
    if (paramBoolean);
    for (paramHttpConnection = "Proxy-Authorization"; ; paramHttpConnection = "Authorization")
    {
      paramHttpMethod.addRequestHeader(new Header(paramHttpConnection, paramHttpState, true));
      return true;
    }
  }

  public static AuthScheme selectAuthScheme(Header[] paramArrayOfHeader)
    throws MalformedChallengeException
  {
    LOG.trace("enter HttpAuthenticator.selectAuthScheme(Header[])");
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Array of challenges may not be null");
    if (paramArrayOfHeader.length == 0)
      throw new IllegalArgumentException("Array of challenges may not be empty");
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfHeader.length)
      {
        paramArrayOfHeader = (String)localHashMap.get("ntlm");
        if (paramArrayOfHeader == null)
          break;
        return new NTLMScheme(paramArrayOfHeader);
      }
      String str = paramArrayOfHeader[i].getValue();
      localHashMap.put(AuthChallengeParser.extractScheme(str), str);
      i += 1;
    }
    paramArrayOfHeader = (String)localHashMap.get("digest");
    if (paramArrayOfHeader != null)
      return new DigestScheme(paramArrayOfHeader);
    paramArrayOfHeader = (String)localHashMap.get("basic");
    if (paramArrayOfHeader != null)
      return new BasicScheme(paramArrayOfHeader);
    throw new UnsupportedOperationException("Authentication scheme(s) not supported: " + localHashMap.toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.HttpAuthenticator
 * JD-Core Version:    0.6.2
 */