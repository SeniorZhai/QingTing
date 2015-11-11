package org.apache.commons.httpclient.cookie;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieSpecBase
  implements CookieSpec
{
  protected static final Log LOG;
  static Class class$org$apache$commons$httpclient$cookie$CookieSpec;
  private Collection datepatterns = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$cookie$CookieSpec == null)
    {
      localClass = class$("org.apache.commons.httpclient.cookie.CookieSpec");
      class$org$apache$commons$httpclient$cookie$CookieSpec = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$cookie$CookieSpec;
    }
  }

  private static void addInPathOrder(List paramList, Cookie paramCookie)
  {
    int i = 0;
    while (true)
    {
      if (i >= paramList.size());
      while (paramCookie.compare(paramCookie, (Cookie)paramList.get(i)) > 0)
      {
        paramList.add(i, paramCookie);
        return;
      }
      i += 1;
    }
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

  public boolean domainMatch(String paramString1, String paramString2)
  {
    if (paramString1.equals(paramString2));
    String str;
    do
    {
      return true;
      str = paramString2;
      if (!paramString2.startsWith("."))
        str = "." + paramString2;
    }
    while ((paramString1.endsWith(str)) || (paramString1.equals(str.substring(1))));
    return false;
  }

  public String formatCookie(Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookie(Cookie)");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramCookie.getName());
    localStringBuffer.append("=");
    paramCookie = paramCookie.getValue();
    if (paramCookie != null)
      localStringBuffer.append(paramCookie);
    return localStringBuffer.toString();
  }

  public Header formatCookieHeader(Cookie paramCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie)");
    return new Header("Cookie", formatCookie(paramCookie));
  }

  public Header formatCookieHeader(Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter CookieSpecBase.formatCookieHeader(Cookie[])");
    return new Header("Cookie", formatCookies(paramArrayOfCookie));
  }

  public String formatCookies(Cookie[] paramArrayOfCookie)
    throws IllegalArgumentException
  {
    LOG.trace("enter CookieSpecBase.formatCookies(Cookie[])");
    if (paramArrayOfCookie == null)
      throw new IllegalArgumentException("Cookie array may not be null");
    if (paramArrayOfCookie.length == 0)
      throw new IllegalArgumentException("Cookie array may not be empty");
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfCookie.length)
        return localStringBuffer.toString();
      if (i > 0)
        localStringBuffer.append("; ");
      localStringBuffer.append(formatCookie(paramArrayOfCookie[i]));
      i += 1;
    }
  }

  public Collection getValidDateFormats()
  {
    return this.datepatterns;
  }

  public boolean match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
  {
    boolean bool = true;
    LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
      throw new IllegalArgumentException("Invalid port: " + paramInt);
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null");
    String str = paramString2;
    if (paramString2.trim().equals(""))
      str = "/";
    paramString1 = paramString1.toLowerCase();
    if (paramCookie.getDomain() == null)
    {
      LOG.warn("Invalid cookie state: domain not specified");
      return false;
    }
    if (paramCookie.getPath() == null)
    {
      LOG.warn("Invalid cookie state: path not specified");
      return false;
    }
    if (((paramCookie.getExpiryDate() == null) || (paramCookie.getExpiryDate().after(new Date()))) && (domainMatch(paramString1, paramCookie.getDomain())) && (pathMatch(str, paramCookie.getPath())))
      if (paramCookie.getSecure())
        if (!paramBoolean)
          break label252;
    label252: for (paramBoolean = bool; ; paramBoolean = false)
    {
      return paramBoolean;
      paramBoolean = true;
      break;
    }
  }

  public Cookie[] match(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie[] paramArrayOfCookie)
  {
    LOG.trace("enter CookieSpecBase.match(String, int, String, boolean, Cookie[])");
    if (paramArrayOfCookie == null)
      return null;
    LinkedList localLinkedList = new LinkedList();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfCookie.length)
        return (Cookie[])localLinkedList.toArray(new Cookie[localLinkedList.size()]);
      if (match(paramString1, paramInt, paramString2, paramBoolean, paramArrayOfCookie[i]))
        addInPathOrder(localLinkedList, paramArrayOfCookie[i]);
      i += 1;
    }
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, String paramString3)
    throws MalformedCookieException
  {
    LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, Header)");
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
    Object localObject = paramString2;
    if (paramString2.trim().equals(""))
      localObject = "/";
    String str = paramString1.toLowerCase();
    int i = ((String)localObject).lastIndexOf("/");
    paramString1 = (String)localObject;
    if (i >= 0)
    {
      paramInt = i;
      if (i == 0)
        paramInt = 1;
      paramString1 = ((String)localObject).substring(0, paramInt);
    }
    i = 0;
    int j = paramString3.toLowerCase().indexOf("expires=");
    paramInt = i;
    int k;
    if (j != -1)
    {
      k = j + "expires=".length();
      j = paramString3.indexOf(";", k);
      paramInt = j;
      if (j == -1)
        paramInt = paramString3.length();
    }
    try
    {
      DateUtil.parseDate(paramString3.substring(k, paramInt), this.datepatterns);
      paramInt = 1;
      if (paramInt != 0)
      {
        paramString2 = new HeaderElement[1];
        paramString2[0] = new HeaderElement(paramString3.toCharArray());
      }
      while (true)
      {
        paramString3 = new Cookie[paramString2.length];
        paramInt = 0;
        if (paramInt < paramString2.length)
          break;
        return paramString3;
        paramString2 = HeaderElement.parseElements(paramString3.toCharArray());
      }
      NameValuePair[] arrayOfNameValuePair = paramString2[paramInt];
      while (true)
      {
        try
        {
          localObject = new Cookie(str, arrayOfNameValuePair.getName(), arrayOfNameValuePair.getValue(), paramString1, null, false);
          arrayOfNameValuePair = arrayOfNameValuePair.getParameters();
          if (arrayOfNameValuePair != null)
          {
            i = 0;
            if (i < arrayOfNameValuePair.length);
          }
          else
          {
            paramString3[paramInt] = localObject;
            paramInt += 1;
          }
        }
        catch (IllegalArgumentException paramString1)
        {
          throw new MalformedCookieException(paramString1.getMessage());
        }
        parseAttribute(arrayOfNameValuePair[i], (Cookie)localObject);
        i += 1;
      }
    }
    catch (DateParseException paramString2)
    {
      while (true)
        paramInt = i;
    }
  }

  public Cookie[] parse(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Header paramHeader)
    throws MalformedCookieException
  {
    LOG.trace("enter CookieSpecBase.parse(String, port, path, boolean, String)");
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null.");
    return parse(paramString1, paramInt, paramString2, paramBoolean, paramHeader.getValue());
  }

  public void parseAttribute(NameValuePair paramNameValuePair, Cookie paramCookie)
    throws MalformedCookieException
  {
    if (paramNameValuePair == null)
      throw new IllegalArgumentException("Attribute may not be null.");
    if (paramCookie == null)
      throw new IllegalArgumentException("Cookie may not be null.");
    String str2 = paramNameValuePair.getName().toLowerCase();
    String str1 = paramNameValuePair.getValue();
    if (str2.equals("path"))
    {
      if (str1 != null)
      {
        paramNameValuePair = str1;
        if (!str1.trim().equals(""));
      }
      else
      {
        paramNameValuePair = "/";
      }
      paramCookie.setPath(paramNameValuePair);
      paramCookie.setPathAttributeSpecified(true);
    }
    do
    {
      return;
      if (str2.equals("domain"))
      {
        if (str1 == null)
          throw new MalformedCookieException("Missing value for domain attribute");
        if (str1.trim().equals(""))
          throw new MalformedCookieException("Blank value for domain attribute");
        paramCookie.setDomain(str1);
        paramCookie.setDomainAttributeSpecified(true);
        return;
      }
      if (str2.equals("max-age"))
      {
        if (str1 == null)
          throw new MalformedCookieException("Missing value for max-age attribute");
        try
        {
          int i = Integer.parseInt(str1);
          paramCookie.setExpiryDate(new Date(System.currentTimeMillis() + i * 1000L));
          return;
        }
        catch (NumberFormatException paramNameValuePair)
        {
          throw new MalformedCookieException("Invalid max-age attribute: " + paramNameValuePair.getMessage());
        }
      }
      if (str2.equals("secure"))
      {
        paramCookie.setSecure(true);
        return;
      }
      if (str2.equals("comment"))
      {
        paramCookie.setComment(str1);
        return;
      }
      if (str2.equals("expires"))
      {
        if (str1 == null)
          throw new MalformedCookieException("Missing value for expires attribute");
        try
        {
          paramCookie.setExpiryDate(DateUtil.parseDate(str1, this.datepatterns));
          return;
        }
        catch (DateParseException paramNameValuePair)
        {
          LOG.debug("Error parsing cookie date", paramNameValuePair);
          throw new MalformedCookieException("Unable to parse expiration date parameter: " + str1);
        }
      }
    }
    while (!LOG.isDebugEnabled());
    LOG.debug("Unrecognized cookie attribute: " + paramNameValuePair.toString());
  }

  public boolean pathMatch(String paramString1, String paramString2)
  {
    boolean bool2 = paramString1.startsWith(paramString2);
    boolean bool1 = bool2;
    if (bool2)
    {
      bool1 = bool2;
      if (paramString1.length() != paramString2.length())
      {
        bool1 = bool2;
        if (!paramString2.endsWith("/"))
        {
          if (paramString1.charAt(paramString2.length()) != CookieSpec.PATH_DELIM_CHAR)
            break label59;
          bool1 = true;
        }
      }
    }
    return bool1;
    label59: return false;
  }

  public void setValidDateFormats(Collection paramCollection)
  {
    this.datepatterns = paramCollection;
  }

  public void validate(String paramString1, int paramInt, String paramString2, boolean paramBoolean, Cookie paramCookie)
    throws MalformedCookieException
  {
    LOG.trace("enter CookieSpecBase.validate(String, port, path, boolean, Cookie)");
    if (paramString1 == null)
      throw new IllegalArgumentException("Host of origin may not be null");
    if (paramString1.trim().equals(""))
      throw new IllegalArgumentException("Host of origin may not be blank");
    if (paramInt < 0)
      throw new IllegalArgumentException("Invalid port: " + paramInt);
    if (paramString2 == null)
      throw new IllegalArgumentException("Path of origin may not be null.");
    String str1 = paramString2;
    if (paramString2.trim().equals(""))
      str1 = "/";
    String str2 = paramString1.toLowerCase();
    if (paramCookie.getVersion() < 0)
      throw new MalformedCookieException("Illegal version number " + paramCookie.getValue());
    if (str2.indexOf(".") >= 0)
    {
      if (!str2.endsWith(paramCookie.getDomain()))
      {
        paramString2 = paramCookie.getDomain();
        paramString1 = paramString2;
        if (paramString2.startsWith("."))
          paramString1 = paramString2.substring(1, paramString2.length());
        if (!str2.equals(paramString1))
          throw new MalformedCookieException("Illegal domain attribute \"" + paramCookie.getDomain() + "\". Domain of origin: \"" + str2 + "\"");
      }
    }
    else if (!str2.equals(paramCookie.getDomain()))
      throw new MalformedCookieException("Illegal domain attribute \"" + paramCookie.getDomain() + "\". Domain of origin: \"" + str2 + "\"");
    if (!str1.startsWith(paramCookie.getPath()))
      throw new MalformedCookieException("Illegal path attribute \"" + paramCookie.getPath() + "\". Path of origin: \"" + str1 + "\"");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.cookie.CookieSpecBase
 * JD-Core Version:    0.6.2
 */