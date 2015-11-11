package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.util.LangUtils;

public class AuthScope
{
  public static final AuthScope ANY = new AuthScope(ANY_HOST, -1, ANY_REALM, ANY_SCHEME);
  public static final String ANY_HOST = null;
  public static final int ANY_PORT = -1;
  public static final String ANY_REALM = null;
  public static final String ANY_SCHEME = null;
  private String host = null;
  private int port = -1;
  private String realm = null;
  private String scheme = null;

  public AuthScope(String paramString, int paramInt)
  {
    this(paramString, paramInt, ANY_REALM, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2)
  {
    this(paramString1, paramInt, paramString2, ANY_SCHEME);
  }

  public AuthScope(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    if (paramString1 == null)
    {
      paramString1 = ANY_HOST;
      this.host = paramString1;
      int i = paramInt;
      if (paramInt < 0)
        i = -1;
      this.port = i;
      paramString1 = paramString2;
      if (paramString2 == null)
        paramString1 = ANY_REALM;
      this.realm = paramString1;
      if (paramString3 != null)
        break label91;
    }
    label91: for (paramString1 = ANY_SCHEME; ; paramString1 = paramString3.toUpperCase())
    {
      this.scheme = paramString1;
      return;
      paramString1 = paramString1.toLowerCase();
      break;
    }
  }

  public AuthScope(AuthScope paramAuthScope)
  {
    if (paramAuthScope == null)
      throw new IllegalArgumentException("Scope may not be null");
    this.host = paramAuthScope.getHost();
    this.port = paramAuthScope.getPort();
    this.realm = paramAuthScope.getRealm();
    this.scheme = paramAuthScope.getScheme();
  }

  private static boolean paramsEqual(int paramInt1, int paramInt2)
  {
    return paramInt1 == paramInt2;
  }

  private static boolean paramsEqual(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString1 == paramString2;
    return paramString1.equals(paramString2);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramObject == null)
      bool1 = false;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (paramObject == this);
      if (!(paramObject instanceof AuthScope))
        return super.equals(paramObject);
      paramObject = (AuthScope)paramObject;
      if ((!paramsEqual(this.host, paramObject.host)) || (!paramsEqual(this.port, paramObject.port)) || (!paramsEqual(this.realm, paramObject.realm)))
        break;
      bool1 = bool2;
    }
    while (paramsEqual(this.scheme, paramObject.scheme));
    return false;
  }

  public String getHost()
  {
    return this.host;
  }

  public int getPort()
  {
    return this.port;
  }

  public String getRealm()
  {
    return this.realm;
  }

  public String getScheme()
  {
    return this.scheme;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.host), this.port), this.realm), this.scheme);
  }

  public int match(AuthScope paramAuthScope)
  {
    int j = 0;
    int i;
    if (paramsEqual(this.scheme, paramAuthScope.scheme))
    {
      i = 0 + 1;
      if (!paramsEqual(this.realm, paramAuthScope.realm))
        break label103;
      j = i + 2;
      label38: if (!paramsEqual(this.port, paramAuthScope.port))
        break label129;
      i = j + 4;
      label56: if (!paramsEqual(this.host, paramAuthScope.host))
        break label151;
      j = i + 8;
    }
    label103: 
    do
    {
      do
      {
        return j;
        i = j;
        if (this.scheme == ANY_SCHEME)
          break;
        i = j;
        if (paramAuthScope.scheme == ANY_SCHEME)
          break;
        return -1;
        j = i;
        if (this.realm == ANY_REALM)
          break label38;
        j = i;
        if (paramAuthScope.realm == ANY_REALM)
          break label38;
        return -1;
        i = j;
        if (this.port == -1)
          break label56;
        i = j;
        if (paramAuthScope.port == -1)
          break label56;
        return -1;
        j = i;
      }
      while (this.host == ANY_HOST);
      j = i;
    }
    while (paramAuthScope.host == ANY_HOST);
    label129: label151: return -1;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.scheme != null)
    {
      localStringBuffer.append(this.scheme.toUpperCase());
      localStringBuffer.append(' ');
    }
    if (this.realm != null)
    {
      localStringBuffer.append('\'');
      localStringBuffer.append(this.realm);
      localStringBuffer.append('\'');
    }
    while (true)
    {
      if (this.host != null)
      {
        localStringBuffer.append('@');
        localStringBuffer.append(this.host);
        if (this.port >= 0)
        {
          localStringBuffer.append(':');
          localStringBuffer.append(this.port);
        }
      }
      return localStringBuffer.toString();
      localStringBuffer.append("<any realm>");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthScope
 * JD-Core Version:    0.6.2
 */