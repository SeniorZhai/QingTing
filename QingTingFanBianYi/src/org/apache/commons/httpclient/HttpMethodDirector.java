package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.auth.AuthChallengeException;
import org.apache.commons.httpclient.auth.AuthChallengeParser;
import org.apache.commons.httpclient.auth.AuthChallengeProcessor;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.AuthState;
import org.apache.commons.httpclient.auth.AuthenticationException;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.auth.MalformedChallengeException;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class HttpMethodDirector
{
  private static final Log LOG;
  public static final String PROXY_AUTH_CHALLENGE = "Proxy-Authenticate";
  public static final String PROXY_AUTH_RESP = "Proxy-Authorization";
  public static final String WWW_AUTH_CHALLENGE = "WWW-Authenticate";
  public static final String WWW_AUTH_RESP = "Authorization";
  static Class class$org$apache$commons$httpclient$HttpMethodDirector;
  private AuthChallengeProcessor authProcessor = null;
  private HttpConnection conn;
  private ConnectMethod connectMethod;
  private HttpConnectionManager connectionManager;
  private HostConfiguration hostConfiguration;
  private HttpClientParams params;
  private Set redirectLocations = null;
  private boolean releaseConnection = false;
  private HttpState state;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpMethodDirector == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpMethodDirector");
      class$org$apache$commons$httpclient$HttpMethodDirector = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$HttpMethodDirector;
    }
  }

  public HttpMethodDirector(HttpConnectionManager paramHttpConnectionManager, HostConfiguration paramHostConfiguration, HttpClientParams paramHttpClientParams, HttpState paramHttpState)
  {
    this.connectionManager = paramHttpConnectionManager;
    this.hostConfiguration = paramHostConfiguration;
    this.params = paramHttpClientParams;
    this.state = paramHttpState;
    this.authProcessor = new AuthChallengeProcessor(this.params);
  }

  private void applyConnectionParams(HttpMethod paramHttpMethod)
    throws IOException
  {
    int i = 0;
    Object localObject = paramHttpMethod.getParams().getParameter("http.socket.timeout");
    paramHttpMethod = localObject;
    if (localObject == null)
      paramHttpMethod = this.conn.getParams().getParameter("http.socket.timeout");
    if (paramHttpMethod != null)
      i = ((Integer)paramHttpMethod).intValue();
    this.conn.setSocketTimeout(i);
  }

  private void authenticate(HttpMethod paramHttpMethod)
  {
    try
    {
      if ((this.conn.isProxied()) && (!this.conn.isSecure()))
        authenticateProxy(paramHttpMethod);
      authenticateHost(paramHttpMethod);
      return;
    }
    catch (AuthenticationException paramHttpMethod)
    {
      LOG.error(paramHttpMethod.getMessage(), paramHttpMethod);
    }
  }

  private void authenticateHost(HttpMethod paramHttpMethod)
    throws AuthenticationException
  {
    if (!cleanAuthHeaders(paramHttpMethod, "Authorization"));
    do
    {
      Object localObject1;
      do
      {
        do
        {
          AuthScheme localAuthScheme;
          do
          {
            return;
            localObject1 = paramHttpMethod.getHostAuthState();
            localAuthScheme = ((AuthState)localObject1).getAuthScheme();
          }
          while ((localAuthScheme == null) || ((!((AuthState)localObject1).isAuthRequested()) && (localAuthScheme.isConnectionBased())));
          Object localObject2 = paramHttpMethod.getParams().getVirtualHost();
          localObject1 = localObject2;
          if (localObject2 == null)
            localObject1 = this.conn.getHost();
          localObject1 = new AuthScope((String)localObject1, this.conn.getPort(), localAuthScheme.getRealm(), localAuthScheme.getSchemeName());
          if (LOG.isDebugEnabled())
            LOG.debug("Authenticating with " + localObject1);
          localObject2 = this.state.getCredentials((AuthScope)localObject1);
          if (localObject2 == null)
            break;
          localObject1 = localAuthScheme.authenticate((Credentials)localObject2, paramHttpMethod);
        }
        while (localObject1 == null);
        paramHttpMethod.addRequestHeader(new Header("Authorization", (String)localObject1, true));
        return;
      }
      while (!LOG.isWarnEnabled());
      LOG.warn("Required credentials not available for " + localObject1);
    }
    while (!paramHttpMethod.getHostAuthState().isPreemptive());
    LOG.warn("Preemptive authentication requested but no default credentials available");
  }

  private void authenticateProxy(HttpMethod paramHttpMethod)
    throws AuthenticationException
  {
    if (!cleanAuthHeaders(paramHttpMethod, "Proxy-Authorization"));
    do
    {
      Object localObject2;
      do
      {
        Object localObject1;
        do
        {
          do
          {
            return;
            localObject2 = paramHttpMethod.getProxyAuthState();
            localObject1 = ((AuthState)localObject2).getAuthScheme();
          }
          while ((localObject1 == null) || ((!((AuthState)localObject2).isAuthRequested()) && (((AuthScheme)localObject1).isConnectionBased())));
          localObject2 = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), ((AuthScheme)localObject1).getRealm(), ((AuthScheme)localObject1).getSchemeName());
          if (LOG.isDebugEnabled())
            LOG.debug("Authenticating with " + localObject2);
          Credentials localCredentials = this.state.getProxyCredentials((AuthScope)localObject2);
          if (localCredentials == null)
            break;
          localObject1 = ((AuthScheme)localObject1).authenticate(localCredentials, paramHttpMethod);
        }
        while (localObject1 == null);
        paramHttpMethod.addRequestHeader(new Header("Proxy-Authorization", (String)localObject1, true));
        return;
      }
      while (!LOG.isWarnEnabled());
      LOG.warn("Required proxy credentials not available for " + localObject2);
    }
    while (!paramHttpMethod.getProxyAuthState().isPreemptive());
    LOG.warn("Preemptive authentication requested but no default proxy credentials available");
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

  private boolean cleanAuthHeaders(HttpMethod paramHttpMethod, String paramString)
  {
    paramString = paramHttpMethod.getRequestHeaders(paramString);
    boolean bool = true;
    int i = 0;
    if (i >= paramString.length)
      return bool;
    Header localHeader = paramString[i];
    if (localHeader.isAutogenerated())
      paramHttpMethod.removeRequestHeader(localHeader);
    while (true)
    {
      i += 1;
      break;
      bool = false;
    }
  }

  private boolean executeConnect()
    throws IOException, HttpException
  {
    this.connectMethod = new ConnectMethod();
    this.connectMethod.getParams().setDefaults(this.hostConfiguration.getParams());
    while (true)
    {
      if (!this.conn.isOpen())
        this.conn.open();
      if ((this.params.isAuthenticationPreemptive()) || (this.state.isAuthenticationPreemptive()))
      {
        LOG.debug("Preemptively sending default basic credentials");
        this.connectMethod.getProxyAuthState().setPreemptive();
        this.connectMethod.getProxyAuthState().setAuthAttempted(true);
      }
      try
      {
        authenticateProxy(this.connectMethod);
        applyConnectionParams(this.connectMethod);
        this.connectMethod.execute(this.state, this.conn);
        int k = this.connectMethod.getStatusCode();
        int j = 0;
        AuthState localAuthState = this.connectMethod.getProxyAuthState();
        if (k == 407)
        {
          bool = true;
          localAuthState.setAuthRequested(bool);
          int i = j;
          if (localAuthState.isAuthRequested())
          {
            i = j;
            if (processAuthenticationResponse(this.connectMethod))
              i = 1;
          }
          if (i != 0)
            break label246;
          if ((k < 200) || (k >= 300))
            break;
          this.conn.tunnelCreated();
          this.connectMethod = null;
          return true;
        }
      }
      catch (AuthenticationException localAuthenticationException)
      {
        while (true)
        {
          LOG.error(localAuthenticationException.getMessage(), localAuthenticationException);
          continue;
          boolean bool = false;
        }
      }
      label246: if (this.connectMethod.getResponseBodyAsStream() != null)
        this.connectMethod.getResponseBodyAsStream().close();
    }
    return false;
  }

  private void executeWithRetry(HttpMethod paramHttpMethod)
    throws IOException, HttpException
  {
    int i = 0;
    while (true)
    {
      i += 1;
      try
      {
        if (LOG.isTraceEnabled())
          LOG.trace("Attempt number " + i + " to process request");
        if (this.conn.getParams().isStaleCheckingEnabled())
          this.conn.closeIfStale();
        if (!this.conn.isOpen())
        {
          this.conn.open();
          if ((this.conn.isProxied()) && (this.conn.isSecure()) && (!(paramHttpMethod instanceof ConnectMethod)) && (!executeConnect()))
            return;
        }
        applyConnectionParams(paramHttpMethod);
        paramHttpMethod.execute(this.state, this.conn);
        return;
      }
      catch (HttpException paramHttpMethod)
      {
        try
        {
          throw paramHttpMethod;
        }
        catch (IOException paramHttpMethod)
        {
          if (this.conn.isOpen())
          {
            LOG.debug("Closing the connection.");
            this.conn.close();
          }
          this.releaseConnection = true;
          throw paramHttpMethod;
        }
      }
      catch (IOException localIOException)
      {
        LOG.debug("Closing the connection.");
        this.conn.close();
        if ((paramHttpMethod instanceof HttpMethodBase))
        {
          localObject = ((HttpMethodBase)paramHttpMethod).getMethodRetryHandler();
          if ((localObject != null) && (!((MethodRetryHandler)localObject).retryMethod(paramHttpMethod, this.conn, new HttpRecoverableException(localIOException.getMessage()), i, paramHttpMethod.isRequestSent())))
          {
            LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
            throw localIOException;
          }
        }
      }
      catch (RuntimeException paramHttpMethod)
      {
        if (this.conn.isOpen)
        {
          LOG.debug("Closing the connection.");
          this.conn.close();
        }
        this.releaseConnection = true;
        throw paramHttpMethod;
      }
      HttpMethodRetryHandler localHttpMethodRetryHandler = (HttpMethodRetryHandler)paramHttpMethod.getParams().getParameter("http.method.retry-handler");
      Object localObject = localHttpMethodRetryHandler;
      if (localHttpMethodRetryHandler == null)
        localObject = new DefaultHttpMethodRetryHandler();
      if (!((HttpMethodRetryHandler)localObject).retryMethod(paramHttpMethod, localIOException, i))
      {
        LOG.debug("Method retry handler returned false. Automatic recovery will not be attempted");
        throw localIOException;
      }
      if (LOG.isInfoEnabled())
        LOG.info("I/O exception (" + localIOException.getClass().getName() + ") caught when processing request: " + localIOException.getMessage());
      if (LOG.isDebugEnabled())
        LOG.debug(localIOException.getMessage(), localIOException);
      LOG.info("Retrying request");
    }
  }

  private void fakeResponse(HttpMethod paramHttpMethod)
    throws IOException, HttpException
  {
    LOG.debug("CONNECT failed, fake the response for the original method");
    if ((paramHttpMethod instanceof HttpMethodBase))
    {
      ((HttpMethodBase)paramHttpMethod).fakeResponse(this.connectMethod.getStatusLine(), this.connectMethod.getResponseHeaderGroup(), this.connectMethod.getResponseBodyAsStream());
      paramHttpMethod.getProxyAuthState().setAuthScheme(this.connectMethod.getProxyAuthState().getAuthScheme());
      this.connectMethod = null;
      return;
    }
    this.releaseConnection = true;
    LOG.warn("Unable to fake response on method as it is not derived from HttpMethodBase.");
  }

  private boolean isAuthenticationNeeded(HttpMethod paramHttpMethod)
  {
    boolean bool2 = false;
    AuthState localAuthState = paramHttpMethod.getHostAuthState();
    if (paramHttpMethod.getStatusCode() == 401)
    {
      bool1 = true;
      localAuthState.setAuthRequested(bool1);
      localAuthState = paramHttpMethod.getProxyAuthState();
      if (paramHttpMethod.getStatusCode() != 407)
        break label111;
    }
    label111: for (boolean bool1 = true; ; bool1 = false)
    {
      localAuthState.setAuthRequested(bool1);
      if (!paramHttpMethod.getHostAuthState().isAuthRequested())
      {
        bool1 = bool2;
        if (!paramHttpMethod.getProxyAuthState().isAuthRequested());
      }
      else
      {
        LOG.debug("Authorization required");
        if (!paramHttpMethod.getDoAuthentication())
          break label116;
        bool1 = true;
      }
      return bool1;
      bool1 = false;
      break;
    }
    label116: LOG.info("Authentication requested but doAuthentication is disabled");
    return false;
  }

  private boolean isRedirectNeeded(HttpMethod paramHttpMethod)
  {
    switch (paramHttpMethod.getStatusCode())
    {
    case 304:
    case 305:
    case 306:
    default:
      return false;
    case 301:
    case 302:
    case 303:
    case 307:
    }
    LOG.debug("Redirect required");
    if (paramHttpMethod.getFollowRedirects())
      return true;
    LOG.info("Redirect requested but followRedirects is disabled");
    return false;
  }

  private boolean processAuthenticationResponse(HttpMethod paramHttpMethod)
  {
    LOG.trace("enter HttpMethodBase.processAuthenticationResponse(HttpState, HttpConnection)");
    try
    {
      switch (paramHttpMethod.getStatusCode())
      {
      case 401:
        return processWWWAuthChallenge(paramHttpMethod);
      case 407:
        boolean bool = processProxyAuthChallenge(paramHttpMethod);
        return bool;
      }
    }
    catch (Exception paramHttpMethod)
    {
      if (LOG.isErrorEnabled())
      {
        LOG.error(paramHttpMethod.getMessage(), paramHttpMethod);
        return false;
      }
    }
    return false;
  }

  private boolean processProxyAuthChallenge(HttpMethod paramHttpMethod)
    throws MalformedChallengeException, AuthenticationException
  {
    Object localObject3 = paramHttpMethod.getProxyAuthState();
    Object localObject1 = AuthChallengeParser.parseChallenges(paramHttpMethod.getResponseHeaders("Proxy-Authenticate"));
    if (((Map)localObject1).isEmpty())
      LOG.debug("Proxy authentication challenge(s) not found");
    label244: 
    do
    {
      while (true)
      {
        return false;
        localObject2 = null;
        try
        {
          localObject1 = this.authProcessor.processChallenge((AuthState)localObject3, (Map)localObject1);
          if (localObject1 != null)
          {
            AuthScope localAuthScope = new AuthScope(this.conn.getProxyHost(), this.conn.getProxyPort(), ((AuthScheme)localObject1).getRealm(), ((AuthScheme)localObject1).getSchemeName());
            if (LOG.isDebugEnabled())
              LOG.debug("Proxy authentication scope: " + localAuthScope);
            if ((!((AuthState)localObject3).isAuthAttempted()) || (!((AuthScheme)localObject1).isComplete()))
              break label244;
            if (promptForProxyCredentials((AuthScheme)localObject1, paramHttpMethod.getParams(), localAuthScope) == null)
            {
              if (!LOG.isInfoEnabled())
                continue;
              LOG.info("Failure authenticating with " + localAuthScope);
              return false;
            }
          }
        }
        catch (AuthChallengeException localAuthChallengeException)
        {
          while (true)
          {
            localObject1 = localObject2;
            if (LOG.isWarnEnabled())
            {
              LOG.warn(localAuthChallengeException.getMessage());
              localObject1 = localObject2;
            }
          }
        }
      }
      return true;
      ((AuthState)localObject3).setAuthAttempted(true);
      localObject3 = this.state.getProxyCredentials(localAuthChallengeException);
      Object localObject2 = localObject3;
      if (localObject3 == null)
        localObject2 = promptForProxyCredentials((AuthScheme)localObject1, paramHttpMethod.getParams(), localAuthChallengeException);
      if (localObject2 != null)
        break;
    }
    while (!LOG.isInfoEnabled());
    LOG.info("No credentials available for " + localAuthChallengeException);
    return false;
    return true;
  }

  // ERROR //
  private boolean processRedirectResponse(HttpMethod paramHttpMethod)
    throws RedirectException
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 527
    //   4: invokeinterface 531 2 0
    //   9: astore_2
    //   10: aload_2
    //   11: ifnonnull +44 -> 55
    //   14: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   17: new 194	java/lang/StringBuffer
    //   20: dup
    //   21: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   24: ldc_w 533
    //   27: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   30: aload_1
    //   31: invokeinterface 452 1 0
    //   36: invokevirtual 360	java/lang/StringBuffer:append	(I)Ljava/lang/StringBuffer;
    //   39: ldc_w 535
    //   42: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   45: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   48: invokeinterface 537 2 0
    //   53: iconst_0
    //   54: ireturn
    //   55: aload_2
    //   56: invokevirtual 542	org/apache/commons/httpclient/NameValuePair:getValue	()Ljava/lang/String;
    //   59: astore_3
    //   60: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   63: invokeinterface 192 1 0
    //   68: ifeq +37 -> 105
    //   71: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   74: new 194	java/lang/StringBuffer
    //   77: dup
    //   78: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   81: ldc_w 544
    //   84: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   87: aload_3
    //   88: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   91: ldc_w 546
    //   94: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   97: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   100: invokeinterface 211 2 0
    //   105: new 548	org/apache/commons/httpclient/URI
    //   108: dup
    //   109: aload_0
    //   110: getfield 101	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   113: invokevirtual 552	org/apache/commons/httpclient/HttpConnection:getProtocol	()Lorg/apache/commons/httpclient/protocol/Protocol;
    //   116: invokevirtual 557	org/apache/commons/httpclient/protocol/Protocol:getScheme	()Ljava/lang/String;
    //   119: aconst_null
    //   120: aload_0
    //   121: getfield 101	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   124: invokevirtual 175	org/apache/commons/httpclient/HttpConnection:getHost	()Ljava/lang/String;
    //   127: aload_0
    //   128: getfield 101	org/apache/commons/httpclient/HttpMethodDirector:conn	Lorg/apache/commons/httpclient/HttpConnection;
    //   131: invokevirtual 180	org/apache/commons/httpclient/HttpConnection:getPort	()I
    //   134: aload_1
    //   135: invokeinterface 560 1 0
    //   140: invokespecial 563	org/apache/commons/httpclient/URI:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V
    //   143: astore 4
    //   145: new 548	org/apache/commons/httpclient/URI
    //   148: dup
    //   149: aload_3
    //   150: iconst_1
    //   151: invokespecial 566	org/apache/commons/httpclient/URI:<init>	(Ljava/lang/String;Z)V
    //   154: astore_2
    //   155: aload_2
    //   156: invokevirtual 569	org/apache/commons/httpclient/URI:isRelativeURI	()Z
    //   159: ifeq +191 -> 350
    //   162: aload_0
    //   163: getfield 74	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   166: ldc_w 571
    //   169: invokevirtual 575	org/apache/commons/httpclient/params/DefaultHttpParams:isParameterTrue	(Ljava/lang/String;)Z
    //   172: ifeq +39 -> 211
    //   175: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   178: new 194	java/lang/StringBuffer
    //   181: dup
    //   182: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   185: ldc_w 577
    //   188: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   191: aload_3
    //   192: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   195: ldc_w 579
    //   198: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   201: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   204: invokeinterface 237 2 0
    //   209: iconst_0
    //   210: ireturn
    //   211: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   214: ldc_w 581
    //   217: invokeinterface 211 2 0
    //   222: new 548	org/apache/commons/httpclient/URI
    //   225: dup
    //   226: aload 4
    //   228: aload_2
    //   229: invokespecial 584	org/apache/commons/httpclient/URI:<init>	(Lorg/apache/commons/httpclient/URI;Lorg/apache/commons/httpclient/URI;)V
    //   232: astore_2
    //   233: aload_1
    //   234: aload_2
    //   235: invokeinterface 588 2 0
    //   240: aload_0
    //   241: getfield 72	org/apache/commons/httpclient/HttpMethodDirector:hostConfiguration	Lorg/apache/commons/httpclient/HostConfiguration;
    //   244: aload_2
    //   245: invokevirtual 591	org/apache/commons/httpclient/HostConfiguration:setHost	(Lorg/apache/commons/httpclient/URI;)V
    //   248: aload_0
    //   249: getfield 74	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   252: ldc_w 593
    //   255: invokevirtual 596	org/apache/commons/httpclient/params/DefaultHttpParams:isParameterFalse	(Ljava/lang/String;)Z
    //   258: ifeq +148 -> 406
    //   261: aload_0
    //   262: getfield 68	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   265: ifnonnull +14 -> 279
    //   268: aload_0
    //   269: new 598	java/util/HashSet
    //   272: dup
    //   273: invokespecial 599	java/util/HashSet:<init>	()V
    //   276: putfield 68	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   279: aload_0
    //   280: getfield 68	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   283: aload 4
    //   285: invokeinterface 605 2 0
    //   290: pop
    //   291: aload_2
    //   292: invokevirtual 608	org/apache/commons/httpclient/URI:hasQuery	()Z
    //   295: ifeq +8 -> 303
    //   298: aload_2
    //   299: aconst_null
    //   300: invokevirtual 611	org/apache/commons/httpclient/URI:setQuery	(Ljava/lang/String;)V
    //   303: aload_0
    //   304: getfield 68	org/apache/commons/httpclient/HttpMethodDirector:redirectLocations	Ljava/util/Set;
    //   307: aload_2
    //   308: invokeinterface 614 2 0
    //   313: ifeq +93 -> 406
    //   316: new 616	org/apache/commons/httpclient/CircularRedirectException
    //   319: dup
    //   320: new 194	java/lang/StringBuffer
    //   323: dup
    //   324: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   327: ldc_w 618
    //   330: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   333: aload_2
    //   334: invokevirtual 204	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   337: ldc_w 546
    //   340: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   343: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   346: invokespecial 619	org/apache/commons/httpclient/CircularRedirectException:<init>	(Ljava/lang/String;)V
    //   349: athrow
    //   350: aload_1
    //   351: invokeinterface 91 1 0
    //   356: aload_0
    //   357: getfield 74	org/apache/commons/httpclient/HttpMethodDirector:params	Lorg/apache/commons/httpclient/params/HttpClientParams;
    //   360: invokevirtual 299	org/apache/commons/httpclient/params/DefaultHttpParams:setDefaults	(Lorg/apache/commons/httpclient/params/HttpParams;)V
    //   363: goto -130 -> 233
    //   366: astore_1
    //   367: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   370: new 194	java/lang/StringBuffer
    //   373: dup
    //   374: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   377: ldc_w 621
    //   380: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   383: aload_3
    //   384: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   387: ldc_w 623
    //   390: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   393: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   396: invokeinterface 237 2 0
    //   401: iconst_0
    //   402: ireturn
    //   403: astore_1
    //   404: iconst_0
    //   405: ireturn
    //   406: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   409: invokeinterface 192 1 0
    //   414: ifeq +48 -> 462
    //   417: getstatic 57	org/apache/commons/httpclient/HttpMethodDirector:LOG	Lorg/apache/commons/logging/Log;
    //   420: new 194	java/lang/StringBuffer
    //   423: dup
    //   424: invokespecial 195	java/lang/StringBuffer:<init>	()V
    //   427: ldc_w 625
    //   430: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   433: aload 4
    //   435: invokevirtual 628	org/apache/commons/httpclient/URI:getEscapedURI	()Ljava/lang/String;
    //   438: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   441: ldc_w 630
    //   444: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   447: aload_2
    //   448: invokevirtual 628	org/apache/commons/httpclient/URI:getEscapedURI	()Ljava/lang/String;
    //   451: invokevirtual 201	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   454: invokevirtual 207	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   457: invokeinterface 211 2 0
    //   462: aload_1
    //   463: invokeinterface 153 1 0
    //   468: invokevirtual 633	org/apache/commons/httpclient/auth/AuthState:invalidate	()V
    //   471: iconst_1
    //   472: ireturn
    //   473: astore_1
    //   474: goto -107 -> 367
    //   477: astore_1
    //   478: goto -111 -> 367
    //
    // Exception table:
    //   from	to	target	type
    //   105	145	366	org/apache/commons/httpclient/URIException
    //   291	303	403	org/apache/commons/httpclient/URIException
    //   145	155	473	org/apache/commons/httpclient/URIException
    //   233	248	473	org/apache/commons/httpclient/URIException
    //   155	209	477	org/apache/commons/httpclient/URIException
    //   211	233	477	org/apache/commons/httpclient/URIException
    //   350	363	477	org/apache/commons/httpclient/URIException
  }

  private boolean processWWWAuthChallenge(HttpMethod paramHttpMethod)
    throws MalformedChallengeException, AuthenticationException
  {
    AuthState localAuthState = paramHttpMethod.getHostAuthState();
    Object localObject1 = AuthChallengeParser.parseChallenges(paramHttpMethod.getResponseHeaders("WWW-Authenticate"));
    if (((Map)localObject1).isEmpty())
      LOG.debug("Authentication challenge(s) not found");
    AuthScope localAuthScope;
    label265: 
    do
    {
      while (true)
      {
        return false;
        localObject2 = null;
        try
        {
          localObject1 = this.authProcessor.processChallenge(localAuthState, (Map)localObject1);
          if (localObject1 != null)
          {
            String str = paramHttpMethod.getParams().getVirtualHost();
            localObject2 = str;
            if (str == null)
              localObject2 = this.conn.getHost();
            localAuthScope = new AuthScope((String)localObject2, this.conn.getPort(), ((AuthScheme)localObject1).getRealm(), ((AuthScheme)localObject1).getSchemeName());
            if (LOG.isDebugEnabled())
              LOG.debug("Authentication scope: " + localAuthScope);
            if ((!localAuthState.isAuthAttempted()) || (!((AuthScheme)localObject1).isComplete()))
              break label265;
            if (promptForCredentials((AuthScheme)localObject1, paramHttpMethod.getParams(), localAuthScope) == null)
            {
              if (!LOG.isInfoEnabled())
                continue;
              LOG.info("Failure authenticating with " + localAuthScope);
              return false;
            }
          }
        }
        catch (AuthChallengeException localAuthChallengeException)
        {
          while (true)
          {
            localObject1 = localObject2;
            if (LOG.isWarnEnabled())
            {
              LOG.warn(localAuthChallengeException.getMessage());
              localObject1 = localObject2;
            }
          }
        }
      }
      return true;
      localAuthState.setAuthAttempted(true);
      Credentials localCredentials = this.state.getCredentials(localAuthScope);
      Object localObject2 = localCredentials;
      if (localCredentials == null)
        localObject2 = promptForCredentials((AuthScheme)localObject1, paramHttpMethod.getParams(), localAuthScope);
      if (localObject2 != null)
        break;
    }
    while (!LOG.isInfoEnabled());
    LOG.info("No credentials available for " + localAuthScope);
    return false;
    return true;
  }

  private Credentials promptForCredentials(AuthScheme paramAuthScheme, HttpParams paramHttpParams, AuthScope paramAuthScope)
  {
    LOG.debug("Credentials required");
    Object localObject = null;
    paramHttpParams = (CredentialsProvider)paramHttpParams.getParameter("http.authentication.credential-provider");
    if (paramHttpParams != null)
      try
      {
        paramAuthScheme = paramHttpParams.getCredentials(paramAuthScheme, paramAuthScope.getHost(), paramAuthScope.getPort(), false);
        if (paramAuthScheme != null)
        {
          this.state.setCredentials(paramAuthScope, paramAuthScheme);
          if (LOG.isDebugEnabled())
            LOG.debug(paramAuthScope + " new credentials given");
        }
        return paramAuthScheme;
      }
      catch (CredentialsNotAvailableException paramAuthScheme)
      {
        while (true)
        {
          LOG.warn(paramAuthScheme.getMessage());
          paramAuthScheme = localObject;
        }
      }
    LOG.debug("Credentials provider not available");
    return null;
  }

  private Credentials promptForProxyCredentials(AuthScheme paramAuthScheme, HttpParams paramHttpParams, AuthScope paramAuthScope)
  {
    LOG.debug("Proxy credentials required");
    Object localObject = null;
    paramHttpParams = (CredentialsProvider)paramHttpParams.getParameter("http.authentication.credential-provider");
    if (paramHttpParams != null)
      try
      {
        paramAuthScheme = paramHttpParams.getCredentials(paramAuthScheme, paramAuthScope.getHost(), paramAuthScope.getPort(), true);
        if (paramAuthScheme != null)
        {
          this.state.setProxyCredentials(paramAuthScope, paramAuthScheme);
          if (LOG.isDebugEnabled())
            LOG.debug(paramAuthScope + " new credentials given");
        }
        return paramAuthScheme;
      }
      catch (CredentialsNotAvailableException paramAuthScheme)
      {
        while (true)
        {
          LOG.warn(paramAuthScheme.getMessage());
          paramAuthScheme = localObject;
        }
      }
    LOG.debug("Proxy credentials provider not available");
    return null;
  }

  public void executeMethod(HttpMethod paramHttpMethod)
    throws IOException, HttpException
  {
    if (paramHttpMethod == null)
      throw new IllegalArgumentException("Method may not be null");
    this.hostConfiguration.getParams().setDefaults(this.params);
    paramHttpMethod.getParams().setDefaults(this.hostConfiguration.getParams());
    Object localObject1 = (Collection)this.hostConfiguration.getParams().getParameter("http.default-headers");
    if (localObject1 != null)
    {
      localObject1 = ((Collection)localObject1).iterator();
      if (((Iterator)localObject1).hasNext())
        break label336;
    }
    while (true)
    {
      int n;
      label336: int m;
      try
      {
        n = this.params.getIntParameter("http.protocol.max-redirects", 100);
        k = 0;
        if ((this.conn != null) && (!this.hostConfiguration.hostEquals(this.conn)))
        {
          this.conn.setLocked(false);
          this.conn.releaseConnection();
          this.conn = null;
        }
        if (this.conn == null)
        {
          this.conn = this.connectionManager.getConnectionWithTimeout(this.hostConfiguration, this.params.getConnectionManagerTimeout());
          this.conn.setLocked(true);
          if ((this.params.isAuthenticationPreemptive()) || (this.state.isAuthenticationPreemptive()))
          {
            LOG.debug("Preemptively sending default basic credentials");
            paramHttpMethod.getHostAuthState().setPreemptive();
            paramHttpMethod.getHostAuthState().setAuthAttempted(true);
            if ((this.conn.isProxied()) && (!this.conn.isSecure()))
            {
              paramHttpMethod.getProxyAuthState().setPreemptive();
              paramHttpMethod.getProxyAuthState().setAuthAttempted(true);
            }
          }
        }
        authenticate(paramHttpMethod);
        executeWithRetry(paramHttpMethod);
        if (this.connectMethod != null)
        {
          fakeResponse(paramHttpMethod);
          return;
          paramHttpMethod.addRequestHeader((Header)((Iterator)localObject1).next());
          break;
        }
        m = 0;
        j = k;
        i = m;
        if (!isRedirectNeeded(paramHttpMethod))
          break label562;
        j = k;
        i = m;
        if (!processRedirectResponse(paramHttpMethod))
          break label562;
        m = 1;
        k += 1;
        if (k >= n)
        {
          LOG.error("Narrowly avoided an infinite loop in execute");
          throw new RedirectException("Maximum redirects (" + n + ") exceeded");
        }
      }
      finally
      {
        if (this.conn != null)
          this.conn.setLocked(false);
        if (((this.releaseConnection) || (paramHttpMethod.getResponseBodyAsStream() == null)) && (this.conn != null))
          this.conn.releaseConnection();
      }
      int j = k;
      int i = m;
      if (LOG.isDebugEnabled())
      {
        LOG.debug("Execute redirect " + k + " of " + n);
        i = m;
        j = k;
      }
      label562: int k = i;
      if (isAuthenticationNeeded(paramHttpMethod))
      {
        k = i;
        if (processAuthenticationResponse(paramHttpMethod))
        {
          LOG.debug("Retry authentication");
          k = 1;
        }
      }
      if (k != 0)
      {
        k = j;
        if (paramHttpMethod.getResponseBodyAsStream() != null)
        {
          paramHttpMethod.getResponseBodyAsStream().close();
          k = j;
        }
      }
    }
  }

  public HttpConnectionManager getConnectionManager()
  {
    return this.connectionManager;
  }

  public HostConfiguration getHostConfiguration()
  {
    return this.hostConfiguration;
  }

  public HttpParams getParams()
  {
    return this.params;
  }

  public HttpState getState()
  {
    return this.state;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpMethodDirector
 * JD-Core Version:    0.6.2
 */