package org.apache.commons.httpclient.cookie;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;

public class NetscapeDraftSpec extends CookieSpecBase
{
  private static boolean isSpecialDomain(String paramString)
  {
    paramString = paramString.toUpperCase();
    return (paramString.endsWith(".COM")) || (paramString.endsWith(".EDU")) || (paramString.endsWith(".NET")) || (paramString.endsWith(".GOV")) || (paramString.endsWith(".MIL")) || (paramString.endsWith(".ORG")) || (paramString.endsWith(".INT"));
  }

  public boolean domainMatch(String paramString1, String paramString2)
  {
    return paramString1.endsWith(paramString2);
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
    throws MalformedCookieException
  {
    CookieSpecBase.LOG.trace("enter NetscapeDraftSpec.parse(String, port, path, boolean, Header)");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
      throw new IllegalArgumentException("Invalid port: " + paramInt);
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    if (paramString3 == null)
      throw new IllegalArgumentException("Header may not be null.");
    String str = paramString2;
    if (paramString2.trim().equals(""))
      str = "/";
    paramString2 = paramString1.toLowerCase();
    int i = str.lastIndexOf("/");
    paramString1 = str;
    if (i >= 0)
    {
      paramInt = i;
      if (i == 0)
        paramInt = 1;
      paramString1 = str.substring(0, paramInt);
    }
    paramString3 = new HeaderElement(paramString3.toCharArray());
    paramString1 = new Cookie(paramString2, paramString3.getName(), paramString3.getValue(), paramString1, null, false);
    paramString2 = paramString3.getParameters();
    if (paramString2 != null)
      paramInt = 0;
    while (true)
    {
      if (paramInt >= paramString2.length)
        return new Cookie[] { paramString1 };
      parseAttribute(paramString2[paramInt], paramString1);
      paramInt += 1;
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
    if (str1.equals("expires"))
    {
      if (str2 == null)
        throw new MalformedCookieException("Missing value for expires attribute");
      try
      {
        paramCookie.setExpiryDate(new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US).parse(str2));
        return;
      }
      catch (ParseException paramNameValuePair)
      {
        throw new MalformedCookieException("Invalid expires attribute: " + paramNameValuePair.getMessage());
      }
    }
    super.parseAttribute(paramNameValuePair, paramCookie);
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
    throws MalformedCookieException
  {
    CookieSpecBase.LOG.trace("enterNetscapeDraftCookieProcessor RCF2109CookieProcessor.validate(Cookie)");
    super.validate(paramString1, paramInt, paramString2, paramBoolean, paramCookie);
    if (paramString1.indexOf(".") >= 0)
    {
      paramInt = new StringTokenizer(paramCookie.getDomain(), ".").countTokens();
      if (isSpecialDomain(paramCookie.getDomain()))
      {
        if (paramInt < 2)
          throw new MalformedCookieException("Domain attribute \"" + paramCookie.getDomain() + "\" violates the Netscape cookie specification for " + "special domains");
      }
      else if (paramInt < 3)
        throw new MalformedCookieException("Domain attribute \"" + paramCookie.getDomain() + "\" violates the Netscape cookie specification");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.NetscapeDraftSpec
 * JD-Core Version:    0.6.2
 */