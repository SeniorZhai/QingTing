package org.apache.commons.httpclient;

import org.apache.commons.httpclient.util.URIUtil;

public class HttpsURL extends HttpURL
{
  public static final int DEFAULT_PORT = 443;
  public static final char[] DEFAULT_SCHEME = { 104, 116, 116, 112, 115 };
  public static final int _default_port = 443;
  public static final char[] _default_scheme = DEFAULT_SCHEME;
  static final long serialVersionUID = 887844277028676648L;

  protected HttpsURL()
  {
  }

  public HttpsURL(String paramString)
    throws URIException
  {
    parseUriReference(paramString, false);
    checkValid();
  }

  public HttpsURL(String paramString1, int paramInt, String paramString2)
    throws URIException
  {
    this(null, paramString1, paramInt, paramString2, null, null);
  }

  public HttpsURL(String paramString1, int paramInt, String paramString2, String paramString3)
    throws URIException
  {
    this(null, paramString1, paramInt, paramString2, paramString3, null);
  }

  public HttpsURL(String paramString1, String paramString2)
    throws URIException
  {
    this.protocolCharset = paramString2;
    parseUriReference(paramString1, false);
    checkValid();
  }

  public HttpsURL(String paramString1, String paramString2, int paramInt, String paramString3)
    throws URIException
  {
    this(paramString1, paramString2, paramInt, paramString3, null, null);
  }

  public HttpsURL(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
    throws URIException
  {
    this(paramString1, paramString2, paramInt, paramString3, paramString4, null);
  }

  public HttpsURL(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
    throws URIException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramString1 != null) || (paramString2 != null) || (paramInt != -1))
    {
      this._scheme = DEFAULT_SCHEME;
      localStringBuffer.append(_default_scheme);
      localStringBuffer.append("://");
      if (paramString1 != null)
      {
        localStringBuffer.append(paramString1);
        localStringBuffer.append('@');
      }
      if (paramString2 != null)
      {
        localStringBuffer.append(URIUtil.encode(paramString2, URI.allowed_host));
        if ((paramInt != -1) || (paramInt != 443))
        {
          localStringBuffer.append(':');
          localStringBuffer.append(paramInt);
        }
      }
    }
    if (paramString3 != null)
    {
      if ((URI.scheme != null) && (!paramString3.startsWith("/")))
        throw new URIException(1, "abs_path requested");
      localStringBuffer.append(URIUtil.encode(paramString3, URI.allowed_abs_path));
    }
    if (paramString4 != null)
    {
      localStringBuffer.append('?');
      localStringBuffer.append(URIUtil.encode(paramString4, URI.allowed_query));
    }
    if (paramString5 != null)
    {
      localStringBuffer.append('#');
      localStringBuffer.append(URIUtil.encode(paramString5, URI.allowed_fragment));
    }
    parseUriReference(localStringBuffer.toString(), true);
    checkValid();
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3)
    throws URIException
  {
    this(paramString1, paramString2, paramString3, -1, null, null, null);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, int paramInt)
    throws URIException
  {
    this(paramString1, paramString2, paramString3, paramInt, null, null, null);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
    throws URIException
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, null, null);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
    throws URIException
  {
    this(paramString1, paramString2, paramString3, paramInt, paramString4, paramString5, null);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5, String paramString6)
    throws URIException
  {
    this(HttpURL.toUserinfo(paramString1, paramString2), paramString3, paramInt, paramString4, paramString5, paramString6);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, String paramString4)
    throws URIException
  {
    this(null, paramString1, -1, paramString2, paramString3, paramString4);
  }

  public HttpsURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws URIException
  {
    this(paramString1, paramString2, -1, paramString3, paramString4, paramString5);
  }

  public HttpsURL(HttpsURL paramHttpsURL, String paramString)
    throws URIException
  {
    this(paramHttpsURL, new HttpsURL(paramString));
  }

  public HttpsURL(HttpsURL paramHttpsURL1, HttpsURL paramHttpsURL2)
    throws URIException
  {
    super(paramHttpsURL1, paramHttpsURL2);
    checkValid();
  }

  public HttpsURL(char[] paramArrayOfChar)
    throws URIException, NullPointerException
  {
    parseUriReference(new String(paramArrayOfChar), true);
    checkValid();
  }

  public HttpsURL(char[] paramArrayOfChar, String paramString)
    throws URIException, NullPointerException
  {
    this.protocolCharset = paramString;
    parseUriReference(new String(paramArrayOfChar), true);
    checkValid();
  }

  protected void checkValid()
    throws URIException
  {
    if ((!equals(this._scheme, DEFAULT_SCHEME)) && (this._scheme != null))
      throw new URIException(1, "wrong class use");
  }

  public int getPort()
  {
    if (this._port == -1)
      return 443;
    return this._port;
  }

  public char[] getRawScheme()
  {
    if (this._scheme == null)
      return null;
    return DEFAULT_SCHEME;
  }

  public String getScheme()
  {
    if (this._scheme == null)
      return null;
    return new String(DEFAULT_SCHEME);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpsURL
 * JD-Core Version:    0.6.2
 */