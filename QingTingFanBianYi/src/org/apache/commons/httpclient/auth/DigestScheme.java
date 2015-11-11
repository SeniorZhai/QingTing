package org.apache.commons.httpclient.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DigestScheme extends RFC2617Scheme
{
  private static final char[] HEXADECIMAL;
  private static final Log LOG;
  private static final String NC = "00000001";
  private static final int QOP_AUTH = 2;
  private static final int QOP_AUTH_INT = 1;
  private static final int QOP_MISSING = 0;
  static Class class$org$apache$commons$httpclient$auth$DigestScheme;
  private String cnonce;
  private boolean complete = false;
  private final ParameterFormatter formatter = new ParameterFormatter();
  private int qopVariant = 0;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$DigestScheme == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.DigestScheme");
      class$org$apache$commons$httpclient$auth$DigestScheme = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      HEXADECIMAL = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
      return;
      localClass = class$org$apache$commons$httpclient$auth$DigestScheme;
    }
  }

  public DigestScheme()
  {
  }

  public DigestScheme(String paramString)
    throws MalformedChallengeException
  {
    this();
    processChallenge(paramString);
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

  public static String createCnonce()
  {
    LOG.trace("enter DigestScheme.createCnonce()");
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      return encode(localMessageDigest.digest(EncodingUtil.getAsciiBytes(Long.toString(System.currentTimeMillis()))));
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new HttpClientError("Unsupported algorithm in HTTP Digest authentication: MD5");
  }

  private String createDigest(String paramString1, String paramString2)
    throws AuthenticationException
  {
    LOG.trace("enter DigestScheme.createDigest(String, String, Map)");
    String str3 = getParameter("uri");
    String str5 = getParameter("realm");
    String str1 = getParameter("nonce");
    String str2 = getParameter("qop");
    String str4 = getParameter("methodname");
    Object localObject1 = getParameter("algorithm");
    Object localObject2 = localObject1;
    if (localObject1 == null)
      localObject2 = "MD5";
    Object localObject3 = getParameter("charset");
    localObject1 = localObject3;
    if (localObject3 == null)
      localObject1 = "ISO-8859-1";
    if (this.qopVariant == 1)
    {
      LOG.warn("qop=auth-int is not supported");
      throw new AuthenticationException("Unsupported qop in HTTP Digest authentication");
    }
    while (true)
    {
      try
      {
        localObject3 = MessageDigest.getInstance("MD5");
        StringBuffer localStringBuffer = new StringBuffer(paramString1.length() + str5.length() + paramString2.length() + 2);
        localStringBuffer.append(paramString1);
        localStringBuffer.append(':');
        localStringBuffer.append(str5);
        localStringBuffer.append(':');
        localStringBuffer.append(paramString2);
        paramString2 = localStringBuffer.toString();
        if (((String)localObject2).equals("MD5-sess"))
        {
          paramString1 = encode(((MessageDigest)localObject3).digest(EncodingUtil.getBytes(paramString2, (String)localObject1)));
          paramString2 = new StringBuffer(paramString1.length() + str1.length() + this.cnonce.length() + 2);
          paramString2.append(paramString1);
          paramString2.append(':');
          paramString2.append(str1);
          paramString2.append(':');
          paramString2.append(this.cnonce);
          paramString1 = paramString2.toString();
          paramString2 = encode(((MessageDigest)localObject3).digest(EncodingUtil.getBytes(paramString1, (String)localObject1)));
          paramString1 = null;
          if (this.qopVariant != 1)
            break label484;
          LOG.error("Unhandled qop auth-int");
          paramString1 = encode(((MessageDigest)localObject3).digest(EncodingUtil.getAsciiBytes(paramString1)));
          if (this.qopVariant != 0)
            break label513;
          LOG.debug("Using null qop method");
          localObject1 = new StringBuffer(paramString2.length() + str1.length() + paramString1.length());
          ((StringBuffer)localObject1).append(paramString2);
          ((StringBuffer)localObject1).append(':');
          ((StringBuffer)localObject1).append(str1);
          ((StringBuffer)localObject1).append(':');
          ((StringBuffer)localObject1).append(paramString1);
          paramString1 = ((StringBuffer)localObject1).toString();
          return encode(((MessageDigest)localObject3).digest(EncodingUtil.getAsciiBytes(paramString1)));
        }
      }
      catch (Exception paramString1)
      {
        throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
      }
      paramString1 = paramString2;
      if (!((String)localObject2).equals("MD5"))
      {
        LOG.warn("Unhandled algorithm " + (String)localObject2 + " requested");
        paramString1 = paramString2;
        continue;
        label484: paramString1 = str4 + ":" + str3;
        continue;
        label513: if (LOG.isDebugEnabled())
          LOG.debug("Using qop method " + str2);
        localObject1 = getQopVariantString();
        localObject2 = new StringBuffer(paramString2.length() + str1.length() + "00000001".length() + this.cnonce.length() + ((String)localObject1).length() + paramString1.length() + 5);
        ((StringBuffer)localObject2).append(paramString2);
        ((StringBuffer)localObject2).append(':');
        ((StringBuffer)localObject2).append(str1);
        ((StringBuffer)localObject2).append(':');
        ((StringBuffer)localObject2).append("00000001");
        ((StringBuffer)localObject2).append(':');
        ((StringBuffer)localObject2).append(this.cnonce);
        ((StringBuffer)localObject2).append(':');
        ((StringBuffer)localObject2).append((String)localObject1);
        ((StringBuffer)localObject2).append(':');
        ((StringBuffer)localObject2).append(paramString1);
        paramString1 = ((StringBuffer)localObject2).toString();
      }
    }
  }

  private String createDigestHeader(String paramString1, String paramString2)
    throws AuthenticationException
  {
    LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
    Object localObject = getParameter("uri");
    String str1 = getParameter("realm");
    String str2 = getParameter("nonce");
    String str3 = getParameter("opaque");
    String str4 = getParameter("algorithm");
    ArrayList localArrayList = new ArrayList(20);
    localArrayList.add(new NameValuePair("username", paramString1));
    localArrayList.add(new NameValuePair("realm", str1));
    localArrayList.add(new NameValuePair("nonce", str2));
    localArrayList.add(new NameValuePair("uri", (String)localObject));
    localArrayList.add(new NameValuePair("response", paramString2));
    if (this.qopVariant != 0)
    {
      localArrayList.add(new NameValuePair("qop", getQopVariantString()));
      localArrayList.add(new NameValuePair("nc", "00000001"));
      localArrayList.add(new NameValuePair("cnonce", this.cnonce));
    }
    if (str4 != null)
      localArrayList.add(new NameValuePair("algorithm", str4));
    if (str3 != null)
      localArrayList.add(new NameValuePair("opaque", str3));
    paramString1 = new StringBuffer();
    int i = 0;
    if (i >= localArrayList.size())
      return paramString1.toString();
    paramString2 = (NameValuePair)localArrayList.get(i);
    if (i > 0)
      paramString1.append(", ");
    int j;
    if (("nc".equals(paramString2.getName())) || ("qop".equals(paramString2.getName())))
    {
      j = 1;
      label343: localObject = this.formatter;
      if (j != 0)
        break label388;
    }
    label388: for (boolean bool = true; ; bool = false)
    {
      ((ParameterFormatter)localObject).setAlwaysUseQuotes(bool);
      this.formatter.format(paramString1, paramString2);
      i += 1;
      break;
      j = 0;
      break label343;
    }
  }

  private static String encode(byte[] paramArrayOfByte)
  {
    LOG.trace("enter DigestScheme.encode(byte[])");
    if (paramArrayOfByte.length != 16)
      return null;
    char[] arrayOfChar = new char[32];
    int i = 0;
    while (true)
    {
      if (i >= 16)
        return new String(arrayOfChar);
      int j = paramArrayOfByte[i];
      int k = paramArrayOfByte[i];
      arrayOfChar[(i * 2)] = HEXADECIMAL[((k & 0xF0) >> 4)];
      arrayOfChar[(i * 2 + 1)] = HEXADECIMAL[(j & 0xF)];
      i += 1;
    }
  }

  private String getQopVariantString()
  {
    if (this.qopVariant == 1)
      return "auth-int";
    return "auth";
  }

  public String authenticate(Credentials paramCredentials, String paramString1, String paramString2)
    throws AuthenticationException
  {
    LOG.trace("enter DigestScheme.authenticate(Credentials, String, String)");
    try
    {
      UsernamePasswordCredentials localUsernamePasswordCredentials = (UsernamePasswordCredentials)paramCredentials;
      getParameters().put("methodname", paramString1);
      getParameters().put("uri", paramString2);
      paramCredentials = createDigest(localUsernamePasswordCredentials.getUserName(), localUsernamePasswordCredentials.getPassword());
      return "Digest " + createDigestHeader(localUsernamePasswordCredentials.getUserName(), paramCredentials);
    }
    catch (ClassCastException paramString1)
    {
    }
    throw new InvalidCredentialsException("Credentials cannot be used for digest authentication: " + paramCredentials.getClass().getName());
  }

  public String authenticate(Credentials paramCredentials, HttpMethod paramHttpMethod)
    throws AuthenticationException
  {
    LOG.trace("enter DigestScheme.authenticate(Credentials, HttpMethod)");
    try
    {
      UsernamePasswordCredentials localUsernamePasswordCredentials = (UsernamePasswordCredentials)paramCredentials;
      getParameters().put("methodname", paramHttpMethod.getName());
      paramCredentials = new StringBuffer(paramHttpMethod.getPath());
      String str = paramHttpMethod.getQueryString();
      if (str != null)
      {
        if (str.indexOf("?") != 0)
          paramCredentials.append("?");
        paramCredentials.append(paramHttpMethod.getQueryString());
      }
      getParameters().put("uri", paramCredentials.toString());
      if (getParameter("charset") == null)
        getParameters().put("charset", paramHttpMethod.getParams().getCredentialCharset());
      paramCredentials = createDigest(localUsernamePasswordCredentials.getUserName(), localUsernamePasswordCredentials.getPassword());
      return "Digest " + createDigestHeader(localUsernamePasswordCredentials.getUserName(), paramCredentials);
    }
    catch (ClassCastException paramHttpMethod)
    {
    }
    throw new InvalidCredentialsException("Credentials cannot be used for digest authentication: " + paramCredentials.getClass().getName());
  }

  public String getID()
  {
    String str2 = getRealm();
    String str3 = getParameter("nonce");
    String str1 = str2;
    if (str3 != null)
      str1 = str2 + "-" + str3;
    return str1;
  }

  public String getSchemeName()
  {
    return "digest";
  }

  public boolean isComplete()
  {
    if ("true".equalsIgnoreCase(getParameter("stale")))
      return false;
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void processChallenge(String paramString)
    throws MalformedChallengeException
  {
    super.processChallenge(paramString);
    if (getParameter("realm") == null)
      throw new MalformedChallengeException("missing realm in challange");
    if (getParameter("nonce") == null)
      throw new MalformedChallengeException("missing nonce in challange");
    int i = 0;
    int j = 0;
    paramString = getParameter("qop");
    if (paramString != null)
    {
      paramString = new StringTokenizer(paramString, ",");
      i = j;
    }
    while (true)
    {
      if (!paramString.hasMoreTokens());
      String str;
      while (true)
      {
        if ((i == 0) || (this.qopVariant != 0))
          break label182;
        throw new MalformedChallengeException("None of the qop methods is supported");
        str = paramString.nextToken().trim();
        if (!str.equals("auth"))
          break;
        this.qopVariant = 2;
      }
      if (str.equals("auth-int"))
      {
        this.qopVariant = 1;
      }
      else
      {
        i = 1;
        LOG.warn("Unsupported qop detected: " + str);
      }
    }
    label182: this.cnonce = createCnonce();
    this.complete = true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.DigestScheme
 * JD-Core Version:    0.6.2
 */