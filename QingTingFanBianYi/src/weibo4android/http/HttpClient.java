package weibo4android.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.security.AccessControlException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import weibo4android.Configuration;
import weibo4android.Weibo;
import weibo4android.WeiboException;

public class HttpClient
  implements Serializable
{
  private static final int BAD_GATEWAY = 502;
  private static final int BAD_REQUEST = 400;
  private static final boolean DEBUG;
  private static final int FORBIDDEN = 403;
  private static final int INTERNAL_SERVER_ERROR = 500;
  private static final int NOT_ACCEPTABLE = 406;
  private static final int NOT_AUTHORIZED = 401;
  private static final int NOT_FOUND = 404;
  private static final int NOT_MODIFIED = 304;
  private static final int OK = 200;
  private static final int SERVICE_UNAVAILABLE = 503;
  private static boolean isJDK14orEarlier = false;
  private static final long serialVersionUID = 808018030183407996L;
  private String accessTokenURL = Configuration.getScheme() + "api.t.sina.com.cn/oauth/access_token";
  private String authenticationURL = Configuration.getScheme() + "api.t.sina.com.cn/oauth/authenticate";
  private String authorizationURL = Configuration.getScheme() + "api.t.sina.com.cn/oauth/authorize";
  private int connectionTimeout = Configuration.getConnectionTimeout();
  private OAuth oauth = null;
  private OAuthToken oauthToken = null;
  private String password = Configuration.getPassword();
  private String proxyAuthPassword = Configuration.getProxyPassword();
  private String proxyAuthUser = Configuration.getProxyUser();
  private String proxyHost = Configuration.getProxyHost();
  private int proxyPort = Configuration.getProxyPort();
  private int readTimeout = Configuration.getReadTimeout();
  private Map<String, String> requestHeaders = new HashMap();
  private String requestTokenURL = Configuration.getScheme() + "api.t.sina.com.cn/oauth/request_token";
  private int retryCount = Configuration.getRetryCount();
  private int retryIntervalMillis = Configuration.getRetryIntervalSecs() * 1000;
  private String token = null;
  private String userId = Configuration.getUser();

  static
  {
    boolean bool = false;
    DEBUG = Configuration.getDebug();
    isJDK14orEarlier = false;
    try
    {
      String str = System.getProperty("java.specification.version");
      if (str != null)
      {
        if (1.5D > Double.parseDouble(str))
          bool = true;
        isJDK14orEarlier = bool;
      }
      return;
    }
    catch (AccessControlException localAccessControlException)
    {
      isJDK14orEarlier = true;
    }
  }

  public HttpClient()
  {
    setUserAgent(null);
    setOAuthConsumer(null, null);
    setRequestHeader("Accept-Encoding", "gzip");
  }

  public static String encodeParameters(PostParameter[] paramArrayOfPostParameter)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i < paramArrayOfPostParameter.length)
        if (i != 0)
          localStringBuffer.append("&");
      try
      {
        localStringBuffer.append(URLEncoder.encode(paramArrayOfPostParameter[i].name, "UTF-8")).append("=").append(URLEncoder.encode(paramArrayOfPostParameter[i].value, "UTF-8"));
        label62: i += 1;
        continue;
        return localStringBuffer.toString();
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        break label62;
      }
    }
  }

  private static String getCause(int paramInt)
  {
    String str = null;
    switch (paramInt)
    {
    default:
      str = "";
    case 304:
    case 400:
    case 401:
    case 403:
    case 404:
    case 406:
    case 500:
    case 502:
    case 503:
    }
    while (true)
    {
      return paramInt + ":" + str;
      str = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
      continue;
      str = "Authentication credentials were missing or incorrect.";
      continue;
      str = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
      continue;
      str = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
      continue;
      str = "Returned by the Search API when an invalid format is specified in the request.";
      continue;
      str = "Something is broken.  Please post to the group so the Weibo team can investigate.";
      continue;
      str = "Weibo is down or being upgraded.";
      continue;
      str = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
    }
  }

  private HttpURLConnection getConnection(String paramString)
    throws IOException
  {
    Proxy localProxy;
    if ((this.proxyHost != null) && (!this.proxyHost.equals("")))
    {
      if ((this.proxyAuthUser != null) && (!this.proxyAuthUser.equals("")))
      {
        log("Proxy AuthUser: " + this.proxyAuthUser);
        log("Proxy AuthPassword: " + this.proxyAuthPassword);
        Authenticator.setDefault(new Authenticator()
        {
          protected PasswordAuthentication getPasswordAuthentication()
          {
            if (getRequestorType().equals(Authenticator.RequestorType.PROXY))
              return new PasswordAuthentication(HttpClient.this.proxyAuthUser, HttpClient.this.proxyAuthPassword.toCharArray());
            return null;
          }
        });
      }
      localProxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(this.proxyHost, this.proxyPort));
      if (DEBUG)
        log("Opening proxied connection(" + this.proxyHost + ":" + this.proxyPort + ")");
    }
    for (paramString = (HttpURLConnection)new URL(paramString).openConnection(localProxy); ; paramString = (HttpURLConnection)new URL(paramString).openConnection())
    {
      if ((this.connectionTimeout > 0) && (!isJDK14orEarlier))
        paramString.setConnectTimeout(this.connectionTimeout);
      if ((this.readTimeout > 0) && (!isJDK14orEarlier))
        paramString.setReadTimeout(this.readTimeout);
      return paramString;
    }
  }

  private static void log(String paramString)
  {
    if (DEBUG)
      System.out.println("[" + new Date() + "]" + paramString);
  }

  private static void log(String paramString1, String paramString2)
  {
    if (DEBUG)
      log(paramString1 + paramString2);
  }

  private void setHeaders(String paramString1, PostParameter[] paramArrayOfPostParameter, HttpURLConnection paramHttpURLConnection, boolean paramBoolean, String paramString2)
  {
    log("Request: ");
    log(paramString2 + " ", paramString1);
    if (paramBoolean)
    {
      if ((this.oauth != null) || (this.oauth != null))
      {
        paramString1 = this.oauth.generateAuthorizationHeader(paramString2, paramString1, paramArrayOfPostParameter, this.oauthToken);
        paramHttpURLConnection.addRequestProperty("Authorization", paramString1);
        log("Authorization: " + paramString1);
      }
    }
    else
      paramString1 = this.requestHeaders.keySet().iterator();
    while (paramString1.hasNext())
    {
      paramArrayOfPostParameter = (String)paramString1.next();
      paramHttpURLConnection.addRequestProperty(paramArrayOfPostParameter, (String)this.requestHeaders.get(paramArrayOfPostParameter));
      log(paramArrayOfPostParameter + ": " + (String)this.requestHeaders.get(paramArrayOfPostParameter));
      continue;
      throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
    }
  }

  public Response delete(String paramString, boolean paramBoolean)
    throws WeiboException
  {
    return httpRequest(paramString, null, paramBoolean, "DELETE");
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        paramObject = (HttpClient)paramObject;
        if (this.accessTokenURL == null)
        {
          if (paramObject.accessTokenURL != null)
            return false;
        }
        else if (!this.accessTokenURL.equals(paramObject.accessTokenURL))
          return false;
        if (this.authenticationURL == null)
        {
          if (paramObject.authenticationURL != null)
            return false;
        }
        else if (!this.authenticationURL.equals(paramObject.authenticationURL))
          return false;
        if (this.authorizationURL == null)
        {
          if (paramObject.authorizationURL != null)
            return false;
        }
        else if (!this.authorizationURL.equals(paramObject.authorizationURL))
          return false;
        if (this.connectionTimeout != paramObject.connectionTimeout)
          return false;
        if (this.oauth == null)
        {
          if (paramObject.oauth != null)
            return false;
        }
        else if (!this.oauth.equals(paramObject.oauth))
          return false;
        if (this.oauthToken == null)
        {
          if (paramObject.oauthToken != null)
            return false;
        }
        else if (!this.oauthToken.equals(paramObject.oauthToken))
          return false;
        if (this.proxyAuthPassword == null)
        {
          if (paramObject.proxyAuthPassword != null)
            return false;
        }
        else if (!this.proxyAuthPassword.equals(paramObject.proxyAuthPassword))
          return false;
        if (this.proxyAuthUser == null)
        {
          if (paramObject.proxyAuthUser != null)
            return false;
        }
        else if (!this.proxyAuthUser.equals(paramObject.proxyAuthUser))
          return false;
        if (this.proxyHost == null)
        {
          if (paramObject.proxyHost != null)
            return false;
        }
        else if (!this.proxyHost.equals(paramObject.proxyHost))
          return false;
        if (this.proxyPort != paramObject.proxyPort)
          return false;
        if (this.readTimeout != paramObject.readTimeout)
          return false;
        if (this.requestHeaders == null)
        {
          if (paramObject.requestHeaders != null)
            return false;
        }
        else if (!this.requestHeaders.equals(paramObject.requestHeaders))
          return false;
        if (this.requestTokenURL == null)
        {
          if (paramObject.requestTokenURL != null)
            return false;
        }
        else if (!this.requestTokenURL.equals(paramObject.requestTokenURL))
          return false;
        if (this.retryCount != paramObject.retryCount)
          return false;
        if (this.retryIntervalMillis != paramObject.retryIntervalMillis)
          return false;
        if (this.token != null)
          break;
      }
      while (paramObject.token == null);
      return false;
    }
    while (this.token.equals(paramObject.token));
    return false;
  }

  public Response get(String paramString)
    throws WeiboException
  {
    return httpRequest(paramString, null, false);
  }

  public Response get(String paramString, boolean paramBoolean)
    throws WeiboException
  {
    return httpRequest(paramString, null, paramBoolean);
  }

  public String getAccessTokenURL()
  {
    return this.accessTokenURL;
  }

  public String getAuthenticationRL()
  {
    return this.authenticationURL;
  }

  public String getAuthenticationURL()
  {
    return this.authenticationURL;
  }

  public String getAuthorizationURL()
  {
    return this.authorizationURL;
  }

  public int getConnectionTimeout()
  {
    return this.connectionTimeout;
  }

  public AccessToken getOAuthAccessToken(String paramString1, String paramString2)
    throws WeiboException
  {
    try
    {
      this.oauthToken = new OAuthToken(paramString1, paramString2)
      {
      };
      this.oauthToken = new AccessToken(httpRequest(this.accessTokenURL, new PostParameter[0], true));
      return (AccessToken)this.oauthToken;
    }
    catch (WeiboException paramString1)
    {
    }
    throw new WeiboException("The user has not given access to the account.", paramString1, paramString1.getStatusCode());
  }

  public AccessToken getOAuthAccessToken(String paramString1, String paramString2, String paramString3)
    throws WeiboException
  {
    try
    {
      this.oauthToken = new OAuthToken(paramString1, paramString2)
      {
      };
      this.oauthToken = new AccessToken(httpRequest(this.accessTokenURL, new PostParameter[] { new PostParameter("oauth_verifier", paramString3) }, true));
      return (AccessToken)this.oauthToken;
    }
    catch (WeiboException paramString1)
    {
    }
    throw new WeiboException("The user has not given access to the account.", paramString1, paramString1.getStatusCode());
  }

  public AccessToken getOAuthAccessToken(RequestToken paramRequestToken)
    throws WeiboException
  {
    try
    {
      this.oauthToken = paramRequestToken;
      this.oauthToken = new AccessToken(httpRequest(this.accessTokenURL, new PostParameter[0], true));
      return (AccessToken)this.oauthToken;
    }
    catch (WeiboException paramRequestToken)
    {
    }
    throw new WeiboException("The user has not given access to the account.", paramRequestToken, paramRequestToken.getStatusCode());
  }

  public AccessToken getOAuthAccessToken(RequestToken paramRequestToken, String paramString)
    throws WeiboException
  {
    try
    {
      this.oauthToken = paramRequestToken;
      this.oauthToken = new AccessToken(httpRequest(this.accessTokenURL, new PostParameter[] { new PostParameter("oauth_verifier", paramString) }, true));
      return (AccessToken)this.oauthToken;
    }
    catch (WeiboException paramRequestToken)
    {
    }
    throw new WeiboException("The user has not given access to the account.", paramRequestToken, paramRequestToken.getStatusCode());
  }

  public RequestToken getOAuthRequestToken()
    throws WeiboException
  {
    this.oauthToken = new RequestToken(httpRequest(this.requestTokenURL, null, true), this);
    return (RequestToken)this.oauthToken;
  }

  public RequestToken getOauthRequestToken(String paramString)
    throws WeiboException
  {
    this.oauthToken = new RequestToken(httpRequest(this.requestTokenURL, new PostParameter[] { new PostParameter("oauth_callback", paramString) }, true), this);
    return (RequestToken)this.oauthToken;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getProxyAuthPassword()
  {
    return this.proxyAuthPassword;
  }

  public String getProxyAuthUser()
  {
    return this.proxyAuthUser;
  }

  public String getProxyHost()
  {
    return this.proxyHost;
  }

  public int getProxyPort()
  {
    return this.proxyPort;
  }

  public int getReadTimeout()
  {
    return this.readTimeout;
  }

  public String getRequestHeader(String paramString)
  {
    return (String)this.requestHeaders.get(paramString);
  }

  public String getRequestTokenURL()
  {
    return this.requestTokenURL;
  }

  public String getUserAgent()
  {
    return getRequestHeader("User-Agent");
  }

  public String getUserId()
  {
    return this.userId;
  }

  public AccessToken getXAuthAccessToken(String paramString1, String paramString2, String paramString3)
    throws WeiboException
  {
    this.oauthToken = new AccessToken(httpRequest(this.accessTokenURL, new PostParameter[] { new PostParameter("x_auth_username", paramString1), new PostParameter("x_auth_password", paramString2), new PostParameter("x_auth_mode", paramString3) }, true));
    return (AccessToken)this.oauthToken;
  }

  public int hashCode()
  {
    int i6 = 0;
    int i;
    int j;
    label21: int k;
    label30: int i7;
    int m;
    label46: int n;
    label56: int i1;
    label66: int i2;
    label76: int i3;
    label86: int i8;
    int i9;
    int i4;
    label108: int i5;
    label118: int i10;
    int i11;
    if (this.accessTokenURL == null)
    {
      i = 0;
      if (this.authenticationURL != null)
        break label241;
      j = 0;
      if (this.authorizationURL != null)
        break label252;
      k = 0;
      i7 = this.connectionTimeout;
      if (this.oauth != null)
        break label263;
      m = 0;
      if (this.oauthToken != null)
        break label275;
      n = 0;
      if (this.proxyAuthPassword != null)
        break label287;
      i1 = 0;
      if (this.proxyAuthUser != null)
        break label299;
      i2 = 0;
      if (this.proxyHost != null)
        break label311;
      i3 = 0;
      i8 = this.proxyPort;
      i9 = this.readTimeout;
      if (this.requestHeaders != null)
        break label323;
      i4 = 0;
      if (this.requestTokenURL != null)
        break label337;
      i5 = 0;
      i10 = this.retryCount;
      i11 = this.retryIntervalMillis;
      if (this.token != null)
        break label349;
    }
    while (true)
    {
      return (((i5 + (i4 + (((i3 + (i2 + (i1 + (n + (m + ((k + (j + (i + 31) * 31) * 31) * 31 + i7) * 31) * 31) * 31) * 31) * 31) * 31 + i8) * 31 + i9) * 31) * 31) * 31 + i10) * 31 + i11) * 31 + i6;
      i = this.accessTokenURL.hashCode();
      break;
      label241: j = this.authenticationURL.hashCode();
      break label21;
      label252: k = this.authorizationURL.hashCode();
      break label30;
      label263: m = this.oauth.hashCode();
      break label46;
      label275: n = this.oauthToken.hashCode();
      break label56;
      label287: i1 = this.proxyAuthPassword.hashCode();
      break label66;
      label299: i2 = this.proxyAuthUser.hashCode();
      break label76;
      label311: i3 = this.proxyHost.hashCode();
      break label86;
      label323: i4 = this.requestHeaders.hashCode();
      break label108;
      label337: i5 = this.requestTokenURL.hashCode();
      break label118;
      label349: i6 = this.token.hashCode();
    }
  }

  protected Response httpRequest(String paramString, PostParameter[] paramArrayOfPostParameter, boolean paramBoolean)
    throws WeiboException
  {
    String str = "GET";
    PostParameter[] arrayOfPostParameter = paramArrayOfPostParameter;
    if (paramArrayOfPostParameter != null)
    {
      arrayOfPostParameter = new PostParameter[paramArrayOfPostParameter.length + 1];
      int i = 0;
      while (i < paramArrayOfPostParameter.length)
      {
        arrayOfPostParameter[i] = paramArrayOfPostParameter[i];
        i += 1;
      }
      arrayOfPostParameter[paramArrayOfPostParameter.length] = new PostParameter("source", Weibo.CONSUMER_KEY);
      str = "POST";
    }
    return httpRequest(paramString, arrayOfPostParameter, paramBoolean, str);
  }

  // ERROR //
  public Response httpRequest(String paramString1, PostParameter[] paramArrayOfPostParameter, boolean paramBoolean, String paramString2)
    throws WeiboException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 104	weibo4android/http/HttpClient:retryCount	I
    //   4: istore 14
    //   6: aconst_null
    //   7: astore 7
    //   9: iconst_0
    //   10: istore 13
    //   12: iload 13
    //   14: iload 14
    //   16: iconst_1
    //   17: iadd
    //   18: if_icmpge +629 -> 647
    //   21: aload_0
    //   22: aload_1
    //   23: invokespecial 508	weibo4android/http/HttpClient:getConnection	(Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   26: astore 8
    //   28: aload 8
    //   30: iconst_1
    //   31: invokevirtual 512	java/net/HttpURLConnection:setDoInput	(Z)V
    //   34: aload_0
    //   35: aload_1
    //   36: aload_2
    //   37: aload 8
    //   39: iload_3
    //   40: aload 4
    //   42: invokespecial 514	weibo4android/http/HttpClient:setHeaders	(Ljava/lang/String;[Lweibo4android/http/PostParameter;Ljava/net/HttpURLConnection;ZLjava/lang/String;)V
    //   45: aload_2
    //   46: ifnonnull +14 -> 60
    //   49: ldc_w 502
    //   52: aload 4
    //   54: invokevirtual 274	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   57: ifeq +306 -> 363
    //   60: aload 8
    //   62: ldc_w 502
    //   65: invokevirtual 517	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   68: aload 8
    //   70: ldc_w 519
    //   73: ldc_w 521
    //   76: invokevirtual 524	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload 8
    //   81: iconst_1
    //   82: invokevirtual 527	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   85: ldc 243
    //   87: astore 5
    //   89: aload_2
    //   90: ifnull +9 -> 99
    //   93: aload_2
    //   94: invokestatic 529	weibo4android/http/HttpClient:encodeParameters	([Lweibo4android/http/PostParameter;)Ljava/lang/String;
    //   97: astore 5
    //   99: ldc_w 531
    //   102: aload 5
    //   104: invokestatic 359	weibo4android/http/HttpClient:log	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: aload 5
    //   109: ldc 227
    //   111: invokevirtual 535	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   114: astore 5
    //   116: aload 8
    //   118: ldc_w 537
    //   121: aload 5
    //   123: arraylength
    //   124: invokestatic 541	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   127: invokevirtual 524	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   130: aload 8
    //   132: invokevirtual 545	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   135: astore 6
    //   137: aload 6
    //   139: aload 5
    //   141: invokevirtual 551	java/io/OutputStream:write	([B)V
    //   144: aload 6
    //   146: invokevirtual 554	java/io/OutputStream:flush	()V
    //   149: aload 6
    //   151: invokevirtual 557	java/io/OutputStream:close	()V
    //   154: aload 6
    //   156: astore 5
    //   158: new 559	weibo4android/http/Response
    //   161: dup
    //   162: aload 8
    //   164: invokespecial 562	weibo4android/http/Response:<init>	(Ljava/net/HttpURLConnection;)V
    //   167: astore 6
    //   169: aload 8
    //   171: invokevirtual 565	java/net/HttpURLConnection:getResponseCode	()I
    //   174: istore 12
    //   176: getstatic 76	weibo4android/http/HttpClient:DEBUG	Z
    //   179: ifeq +231 -> 410
    //   182: ldc_w 567
    //   185: invokestatic 279	weibo4android/http/HttpClient:log	(Ljava/lang/String;)V
    //   188: aload 8
    //   190: invokevirtual 571	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   193: astore 7
    //   195: aload 7
    //   197: invokeinterface 378 1 0
    //   202: invokeinterface 384 1 0
    //   207: astore 8
    //   209: aload 8
    //   211: invokeinterface 389 1 0
    //   216: ifeq +194 -> 410
    //   219: aload 8
    //   221: invokeinterface 393 1 0
    //   226: checkcast 270	java/lang/String
    //   229: astore 9
    //   231: aload 7
    //   233: aload 9
    //   235: invokeinterface 397 2 0
    //   240: checkcast 573	java/util/List
    //   243: invokeinterface 574 1 0
    //   248: astore 10
    //   250: aload 10
    //   252: invokeinterface 389 1 0
    //   257: ifeq -48 -> 209
    //   260: aload 10
    //   262: invokeinterface 393 1 0
    //   267: checkcast 270	java/lang/String
    //   270: astore 11
    //   272: aload 9
    //   274: ifnull +128 -> 402
    //   277: new 159	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   284: aload 9
    //   286: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: ldc_w 399
    //   292: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: aload 11
    //   297: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokestatic 279	weibo4android/http/HttpClient:log	(Ljava/lang/String;)V
    //   306: goto -56 -> 250
    //   309: astore 8
    //   311: aload 6
    //   313: astore 7
    //   315: aload 5
    //   317: astore 6
    //   319: aload 8
    //   321: astore 5
    //   323: aload 6
    //   325: invokevirtual 557	java/io/OutputStream:close	()V
    //   328: aload 5
    //   330: athrow
    //   331: astore 5
    //   333: aload 7
    //   335: astore 6
    //   337: iload 13
    //   339: aload_0
    //   340: getfield 104	weibo4android/http/HttpClient:retryCount	I
    //   343: if_icmpne +307 -> 650
    //   346: new 408	weibo4android/WeiboException
    //   349: dup
    //   350: aload 5
    //   352: invokevirtual 577	java/io/IOException:getMessage	()Ljava/lang/String;
    //   355: aload 5
    //   357: iload 12
    //   359: invokespecial 449	weibo4android/WeiboException:<init>	(Ljava/lang/String;Ljava/lang/Exception;I)V
    //   362: athrow
    //   363: ldc_w 410
    //   366: aload 4
    //   368: invokevirtual 274	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   371: ifeq +17 -> 388
    //   374: aload 8
    //   376: ldc_w 410
    //   379: invokevirtual 517	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   382: aconst_null
    //   383: astore 5
    //   385: goto -227 -> 158
    //   388: aload 8
    //   390: ldc_w 493
    //   393: invokevirtual 517	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   396: aconst_null
    //   397: astore 5
    //   399: goto -241 -> 158
    //   402: aload 11
    //   404: invokestatic 279	weibo4android/http/HttpClient:log	(Ljava/lang/String;)V
    //   407: goto -157 -> 250
    //   410: iload 12
    //   412: sipush 200
    //   415: if_icmpeq +62 -> 477
    //   418: iload 12
    //   420: sipush 500
    //   423: if_icmplt +12 -> 435
    //   426: iload 13
    //   428: aload_0
    //   429: getfield 104	weibo4android/http/HttpClient:retryCount	I
    //   432: if_icmpne +53 -> 485
    //   435: new 408	weibo4android/WeiboException
    //   438: dup
    //   439: new 159	java/lang/StringBuilder
    //   442: dup
    //   443: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   446: iload 12
    //   448: invokestatic 579	weibo4android/http/HttpClient:getCause	(I)Ljava/lang/String;
    //   451: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: ldc_w 581
    //   457: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: aload 6
    //   462: invokevirtual 584	weibo4android/http/Response:asString	()Ljava/lang/String;
    //   465: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: iload 12
    //   473: invokespecial 587	weibo4android/WeiboException:<init>	(Ljava/lang/String;I)V
    //   476: athrow
    //   477: aload 5
    //   479: invokevirtual 557	java/io/OutputStream:close	()V
    //   482: aload 6
    //   484: areturn
    //   485: aload 5
    //   487: invokevirtual 557	java/io/OutputStream:close	()V
    //   490: aload 6
    //   492: astore 7
    //   494: getstatic 76	weibo4android/http/HttpClient:DEBUG	Z
    //   497: ifeq +14 -> 511
    //   500: aload 7
    //   502: ifnull +9 -> 511
    //   505: aload 7
    //   507: invokevirtual 584	weibo4android/http/Response:asString	()Ljava/lang/String;
    //   510: pop
    //   511: new 159	java/lang/StringBuilder
    //   514: dup
    //   515: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   518: ldc_w 589
    //   521: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: aload_0
    //   525: getfield 109	weibo4android/http/HttpClient:retryIntervalMillis	I
    //   528: invokevirtual 246	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   531: ldc_w 591
    //   534: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: invokevirtual 172	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   540: invokestatic 279	weibo4android/http/HttpClient:log	(Ljava/lang/String;)V
    //   543: aload_0
    //   544: getfield 109	weibo4android/http/HttpClient:retryIntervalMillis	I
    //   547: i2l
    //   548: invokestatic 597	java/lang/Thread:sleep	(J)V
    //   551: iload 13
    //   553: iconst_1
    //   554: iadd
    //   555: istore 13
    //   557: goto -545 -> 12
    //   560: astore_1
    //   561: goto -79 -> 482
    //   564: astore 5
    //   566: goto -76 -> 490
    //   569: astore 6
    //   571: goto -243 -> 328
    //   574: astore 5
    //   576: goto -25 -> 551
    //   579: astore 5
    //   581: goto -244 -> 337
    //   584: astore 5
    //   586: aconst_null
    //   587: astore 6
    //   589: iconst_m1
    //   590: istore 12
    //   592: goto -269 -> 323
    //   595: astore 5
    //   597: iconst_m1
    //   598: istore 12
    //   600: goto -277 -> 323
    //   603: astore 8
    //   605: aload 5
    //   607: astore 6
    //   609: iconst_m1
    //   610: istore 12
    //   612: aload 8
    //   614: astore 5
    //   616: goto -293 -> 323
    //   619: astore 9
    //   621: aload 5
    //   623: astore 7
    //   625: aload 6
    //   627: astore 8
    //   629: iconst_m1
    //   630: istore 12
    //   632: aload 9
    //   634: astore 5
    //   636: aload 7
    //   638: astore 6
    //   640: aload 8
    //   642: astore 7
    //   644: goto -321 -> 323
    //   647: aload 7
    //   649: areturn
    //   650: aload 6
    //   652: astore 7
    //   654: goto -160 -> 494
    //
    // Exception table:
    //   from	to	target	type
    //   176	209	309	finally
    //   209	250	309	finally
    //   250	272	309	finally
    //   277	306	309	finally
    //   402	407	309	finally
    //   426	435	309	finally
    //   435	477	309	finally
    //   323	328	331	java/io/IOException
    //   328	331	331	java/io/IOException
    //   477	482	560	java/lang/Exception
    //   485	490	564	java/lang/Exception
    //   323	328	569	java/lang/Exception
    //   494	500	574	java/lang/InterruptedException
    //   505	511	574	java/lang/InterruptedException
    //   511	551	574	java/lang/InterruptedException
    //   477	482	579	java/io/IOException
    //   485	490	579	java/io/IOException
    //   21	45	584	finally
    //   49	60	584	finally
    //   60	85	584	finally
    //   93	99	584	finally
    //   99	137	584	finally
    //   363	382	584	finally
    //   388	396	584	finally
    //   137	154	595	finally
    //   158	169	603	finally
    //   169	176	619	finally
  }

  public boolean isAuthenticationEnabled()
  {
    return this.oauth != null;
  }

  public Response multPartURL(String paramString1, String paramString2, PostParameter[] paramArrayOfPostParameter, File paramFile, boolean paramBoolean)
    throws WeiboException
  {
    return null;
  }

  public Response multPartURL(String paramString, PostParameter[] paramArrayOfPostParameter, ImageItem paramImageItem, boolean paramBoolean)
    throws WeiboException
  {
    return null;
  }

  public Response post(String paramString)
    throws WeiboException
  {
    return httpRequest(paramString, new PostParameter[0], false);
  }

  public Response post(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws WeiboException
  {
    return post(paramString1, new PostParameter[] { new PostParameter(paramString2, paramString3) }, paramBoolean);
  }

  public Response post(String paramString, boolean paramBoolean)
    throws WeiboException
  {
    return httpRequest(paramString, new PostParameter[0], paramBoolean);
  }

  public Response post(String paramString, PostParameter[] paramArrayOfPostParameter)
    throws WeiboException
  {
    return httpRequest(paramString, paramArrayOfPostParameter, false);
  }

  public Response post(String paramString, PostParameter[] paramArrayOfPostParameter, boolean paramBoolean)
    throws WeiboException
  {
    PostParameter[] arrayOfPostParameter = new PostParameter[paramArrayOfPostParameter.length + 1];
    int i = 0;
    while (i < paramArrayOfPostParameter.length)
    {
      arrayOfPostParameter[i] = paramArrayOfPostParameter[i];
      i += 1;
    }
    arrayOfPostParameter[paramArrayOfPostParameter.length] = new PostParameter("source", Weibo.CONSUMER_KEY);
    return httpRequest(paramString, arrayOfPostParameter, paramBoolean);
  }

  public void setAccessTokenURL(String paramString)
  {
    this.accessTokenURL = paramString;
  }

  public void setAuthenticationURL(String paramString)
  {
    this.authenticationURL = paramString;
  }

  public void setAuthorizationURL(String paramString)
  {
    this.authorizationURL = paramString;
  }

  public void setConnectionTimeout(int paramInt)
  {
    this.connectionTimeout = Configuration.getConnectionTimeout(paramInt);
  }

  public void setOAuthAccessToken(AccessToken paramAccessToken)
  {
    this.oauthToken = paramAccessToken;
  }

  public void setOAuthConsumer(String paramString1, String paramString2)
  {
    paramString1 = Configuration.getOAuthConsumerKey(paramString1);
    paramString2 = Configuration.getOAuthConsumerSecret(paramString2);
    if ((paramString1 != null) && (paramString2 != null) && (paramString1.length() != 0) && (paramString2.length() != 0))
      this.oauth = new OAuth(paramString1, paramString2);
  }

  public void setProxyAuthPassword(String paramString)
  {
    this.proxyAuthPassword = Configuration.getProxyPassword(paramString);
  }

  public void setProxyAuthUser(String paramString)
  {
    this.proxyAuthUser = Configuration.getProxyUser(paramString);
  }

  public void setProxyHost(String paramString)
  {
    this.proxyHost = Configuration.getProxyHost(paramString);
  }

  public void setProxyPort(int paramInt)
  {
    this.proxyPort = Configuration.getProxyPort(paramInt);
  }

  public void setReadTimeout(int paramInt)
  {
    this.readTimeout = Configuration.getReadTimeout(paramInt);
  }

  public void setRequestHeader(String paramString1, String paramString2)
  {
    this.requestHeaders.put(paramString1, paramString2);
  }

  public void setRequestTokenURL(String paramString)
  {
    this.requestTokenURL = paramString;
  }

  public void setRetryCount(int paramInt)
  {
    if (paramInt >= 0)
    {
      this.retryCount = Configuration.getRetryCount(paramInt);
      return;
    }
    throw new IllegalArgumentException("RetryCount cannot be negative.");
  }

  public void setRetryIntervalSecs(int paramInt)
  {
    if (paramInt >= 0)
    {
      this.retryIntervalMillis = (Configuration.getRetryIntervalSecs(paramInt) * 1000);
      return;
    }
    throw new IllegalArgumentException("RetryInterval cannot be negative.");
  }

  public RequestToken setToken(String paramString1, String paramString2)
  {
    this.token = paramString1;
    this.oauthToken = new RequestToken(paramString1, paramString2);
    return (RequestToken)this.oauthToken;
  }

  public void setUserAgent(String paramString)
  {
    setRequestHeader("User-Agent", Configuration.getUserAgent(paramString));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.HttpClient
 * JD-Core Version:    0.6.2
 */