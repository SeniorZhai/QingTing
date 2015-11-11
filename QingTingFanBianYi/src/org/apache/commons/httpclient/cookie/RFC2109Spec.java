package org.apache.commons.httpclient.cookie;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;

public class RFC2109Spec extends CookieSpecBase
{
  private final ParameterFormatter formatter = new ParameterFormatter();

  public RFC2109Spec()
  {
    this.formatter.setAlwaysUseQuotes(true);
  }

  private void formatCookieAsVer(StringBuffer paramStringBuffer, Cookie paramCookie, int paramInt)
  {
    String str2 = paramCookie.getValue();
    String str1 = str2;
    if (str2 == null)
      str1 = "";
    formatParam(paramStringBuffer, new NameValuePair(paramCookie.getName(), str1), paramInt);
    if ((paramCookie.getPath() != null) && (paramCookie.isPathAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      formatParam(paramStringBuffer, new NameValuePair("$Path", paramCookie.getPath()), paramInt);
    }
    if ((paramCookie.getDomain() != null) && (paramCookie.isDomainAttributeSpecified()))
    {
      paramStringBuffer.append("; ");
      formatParam(paramStringBuffer, new NameValuePair("$Domain", paramCookie.getDomain()), paramInt);
    }
  }

  private void formatParam(StringBuffer paramStringBuffer, NameValuePair paramNameValuePair, int paramInt)
  {
    if (paramInt < 1)
    {
      paramStringBuffer.append(paramNameValuePair.getName());
      paramStringBuffer.append("=");
      if (paramNameValuePair.getValue() != null)
        paramStringBuffer.append(paramNameValuePair.getValue());
      return;
    }
    this.formatter.format(paramStringBuffer, paramNameValuePair);
  }

  public boolean domainMatch(String paramString1, String paramString2)
  {
    return (paramString1.equals(paramString2)) || ((paramString2.startsWith(".")) && (paramString1.endsWith(paramString2)));
  }

  public String formatCookie(Cookie paramCookie)
  {
    CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookie(Cookie)");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    int i = paramCookie.getVersion();
    StringBuffer localStringBuffer = new StringBuffer();
    formatParam(localStringBuffer, new NameValuePair("$Version", Integer.toString(i)), i);
    localStringBuffer.append("; ");
    formatCookieAsVer(localStringBuffer, paramCookie, i);
    return localStringBuffer.toString();
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
  {
    CookieSpecBase.LOG.trace("enter RFC2109Spec.formatCookieHeader(Cookie[])");
    int i = 2147483647;
    int j = 0;
    Object localObject;
    if (j >= paramArrayOfCookie.length)
    {
      localObject = new StringBuffer();
      formatParam((StringBuffer)localObject, new NameValuePair("$Version", Integer.toString(i)), i);
      j = 0;
    }
    while (true)
    {
      if (j >= paramArrayOfCookie.length)
      {
        return ((StringBuffer)localObject).toString();
        localObject = paramArrayOfCookie[j];
        int k = i;
        if (((Cookie)localObject).getVersion() < i)
          k = ((Cookie)localObject).getVersion();
        j += 1;
        i = k;
        break;
      }
      ((StringBuffer)localObject).append("; ");
      formatCookieAsVer((StringBuffer)localObject, paramArrayOfCookie[j], i);
      j += 1;
    }
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
    throws MalformedCookieException
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Attribute may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null.");
    String str1 = paramNameValuePair.getName().toLowerCase();
    String str2 = paramNameValuePair.getValue();
    if (str1.equals("path"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for path attribute");
      if (str2.trim().equals(""))
        throw new MalformedCookieException("Blank value for path attribute");
      paramCookie.setPath(str2);
      paramCookie.setPathAttributeSpecified(true);
      return;
    }
    if (str1.equals("version"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for version attribute");
      try
      {
        paramCookie.setVersion(Integer.parseInt(str2));
        return;
      }
      catch (NumberFormatException paramNameValuePair)
      {
        throw new MalformedCookieException("Invalid version: " + paramNameValuePair.getMessage());
      }
    }
    super.parseAttribute(paramNameValuePair, paramCookie);
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
    throws MalformedCookieException
  {
    CookieSpecBase.LOG.trace("enter RFC2109Spec.validate(String, int, String, boolean, Cookie)");
    super.validate(paramString1, paramInt, paramString2, paramBoolean, paramCookie);
    if (paramCookie.getName().indexOf(' ') != -1)
      throw new MalformedCookieException("Cookie name may not contain blanks");
    if (paramCookie.getName().startsWith("$"))
      throw new MalformedCookieException("Cookie name may not start with $");
    if ((paramCookie.isDomainAttributeSpecified()) && (!paramCookie.getDomain().equals(paramString1)))
    {
      if (!paramCookie.getDomain().startsWith("."))
        throw new MalformedCookieException("Domain attribute \"" + paramCookie.getDomain() + "\" violates RFC 2109: domain must start with a dot");
      paramInt = paramCookie.getDomain().indexOf('.', 1);
      if ((paramInt < 0) || (paramInt == paramCookie.getDomain().length() - 1))
        throw new MalformedCookieException("Domain attribute \"" + paramCookie.getDomain() + "\" violates RFC 2109: domain must contain an embedded dot");
      paramString1 = paramString1.toLowerCase();
      if (!paramString1.endsWith(paramCookie.getDomain()))
        throw new MalformedCookieException("Illegal domain attribute \"" + paramCookie.getDomain() + "\". Domain of origin: \"" + paramString1 + "\"");
      if (paramString1.substring(0, paramString1.length() - paramCookie.getDomain().length()).indexOf('.') != -1)
        throw new MalformedCookieException("Domain attribute \"" + paramCookie.getDomain() + "\" violates RFC 2109: host minus domain may not contain any dots");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.RFC2109Spec
 * JD-Core Version:    0.6.2
 */