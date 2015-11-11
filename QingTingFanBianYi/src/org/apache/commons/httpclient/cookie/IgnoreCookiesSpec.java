package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public class IgnoreCookiesSpec
  implements CookieSpec
{
  public boolean domainMatch(String paramString1, String paramString2)
  {
    return false;
  }

  public String formatCookie(Cookie paramCookie)
  {
    return null;
  }

  public Header formatCookieHeader(Cookie paramCookie)
    throws IllegalArgumentException
  {
    return null;
  }

  public Header formatCookieHeader(Cookie[] paramArrayOfCookie)
    throws IllegalArgumentException
  {
    return null;
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
    throws IllegalArgumentException
  {
    return null;
  }

  public Collection getValidDateFormats()
  {
    return null;
  }

  public boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    return false;
  }

  public Cookie[] match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie[] paramArrayOfCookie)
  {
    return new Cookie[0];
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
    throws MalformedCookieException
  {
    return new Cookie[0];
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader)
    throws MalformedCookieException, IllegalArgumentException
  {
    return new Cookie[0];
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
    throws MalformedCookieException, IllegalArgumentException
  {
  }

  public boolean pathMatch(String paramString1, String paramString2)
  {
    return false;
  }

  public void setValidDateFormats(Collection paramCollection)
  {
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
    throws MalformedCookieException, IllegalArgumentException
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.IgnoreCookiesSpec
 * JD-Core Version:    0.6.2
 */