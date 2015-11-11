package org.apache.commons.httpclient;

import java.io.Serializable;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.util.LangUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cookie extends NameValuePair
  implements Serializable, Comparator
{
  private static final Log LOG;
  private static final RuleBasedCollator STRING_COLLATOR = (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
  static Class class$org$apache$commons$httpclient$Cookie;
  private String cookieComment;
  private String cookieDomain;
  private Date cookieExpiryDate;
  private String cookiePath;
  private int cookieVersion = 0;
  private boolean hasDomainAttribute = false;
  private boolean hasPathAttribute = false;
  private boolean isSecure;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$Cookie == null)
    {
      localClass = class$("org.apache.commons.httpclient.Cookie");
      class$org$apache$commons$httpclient$Cookie = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$Cookie;
    }
  }

  public Cookie()
  {
    this(null, "noname", null, null, null, false);
  }

  public Cookie(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, null, null, false);
  }

  public Cookie(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
  {
    this(paramString1, paramString2, paramString3, paramString4, null, paramBoolean);
    if (paramInt < -1)
      throw new IllegalArgumentException("Invalid max age:  " + Integer.toString(paramInt));
    if (paramInt >= 0)
      setExpiryDate(new Date(System.currentTimeMillis() + paramInt * 1000L));
  }

  public Cookie(String paramString1, String paramString2, String paramString3, String paramString4, Date paramDate, boolean paramBoolean)
  {
    super(paramString2, paramString3);
    LOG.trace("enter Cookie(String, String, String, String, Date, boolean)");
    if (paramString2 == null)
      throw new IllegalArgumentException("Cookie name may not be null");
    if (paramString2.trim().equals(""))
      throw new IllegalArgumentException("Cookie name may not be blank");
    setPath(paramString4);
    setDomain(paramString1);
    setExpiryDate(paramDate);
    setSecure(paramBoolean);
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

  public int compare(Object paramObject1, Object paramObject2)
  {
    LOG.trace("enter Cookie.compare(Object, Object)");
    if (!(paramObject1 instanceof Cookie))
      throw new ClassCastException(paramObject1.getClass().getName());
    if (!(paramObject2 instanceof Cookie))
      throw new ClassCastException(paramObject2.getClass().getName());
    paramObject1 = (Cookie)paramObject1;
    paramObject2 = (Cookie)paramObject2;
    if ((paramObject1.getPath() == null) && (paramObject2.getPath() == null));
    do
    {
      do
      {
        return 0;
        if (paramObject1.getPath() != null)
          break;
      }
      while (paramObject2.getPath().equals("/"));
      return -1;
      if (paramObject2.getPath() != null)
        break;
    }
    while (paramObject1.getPath().equals("/"));
    return 1;
    return STRING_COLLATOR.compare(paramObject1.getPath(), paramObject2.getPath());
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null);
    do
    {
      return false;
      if (this == paramObject)
        return true;
    }
    while (!(paramObject instanceof Cookie));
    paramObject = (Cookie)paramObject;
    if ((LangUtils.equals(getName(), paramObject.getName())) && (LangUtils.equals(this.cookieDomain, paramObject.cookieDomain)) && (LangUtils.equals(this.cookiePath, paramObject.cookiePath)));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public String getComment()
  {
    return this.cookieComment;
  }

  public String getDomain()
  {
    return this.cookieDomain;
  }

  public Date getExpiryDate()
  {
    return this.cookieExpiryDate;
  }

  public String getPath()
  {
    return this.cookiePath;
  }

  public boolean getSecure()
  {
    return this.isSecure;
  }

  public int getVersion()
  {
    return this.cookieVersion;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, getName()), this.cookieDomain), this.cookiePath);
  }

  public boolean isDomainAttributeSpecified()
  {
    return this.hasDomainAttribute;
  }

  public boolean isExpired()
  {
    return (this.cookieExpiryDate != null) && (this.cookieExpiryDate.getTime() <= System.currentTimeMillis());
  }

  public boolean isExpired(Date paramDate)
  {
    return (this.cookieExpiryDate != null) && (this.cookieExpiryDate.getTime() <= paramDate.getTime());
  }

  public boolean isPathAttributeSpecified()
  {
    return this.hasPathAttribute;
  }

  public boolean isPersistent()
  {
    return this.cookieExpiryDate != null;
  }

  public void setComment(String paramString)
  {
    this.cookieComment = paramString;
  }

  public void setDomain(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.indexOf(":");
      String str = paramString;
      if (i != -1)
        str = paramString.substring(0, i);
      this.cookieDomain = str.toLowerCase();
    }
  }

  public void setDomainAttributeSpecified(boolean paramBoolean)
  {
    this.hasDomainAttribute = paramBoolean;
  }

  public void setExpiryDate(Date paramDate)
  {
    this.cookieExpiryDate = paramDate;
  }

  public void setPath(String paramString)
  {
    this.cookiePath = paramString;
  }

  public void setPathAttributeSpecified(boolean paramBoolean)
  {
    this.hasPathAttribute = paramBoolean;
  }

  public void setSecure(boolean paramBoolean)
  {
    this.isSecure = paramBoolean;
  }

  public void setVersion(int paramInt)
  {
    this.cookieVersion = paramInt;
  }

  public String toExternalForm()
  {
    if (getVersion() > 0);
    for (CookieSpec localCookieSpec = CookiePolicy.getDefaultSpec(); ; localCookieSpec = CookiePolicy.getCookieSpec("netscape"))
      return localCookieSpec.formatCookie(this);
  }

  public String toString()
  {
    return toExternalForm();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.Cookie
 * JD-Core Version:    0.6.2
 */