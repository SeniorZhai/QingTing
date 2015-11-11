package org.apache.commons.httpclient;

import java.io.Serializable;
import org.apache.commons.httpclient.util.LangUtils;

public class NameValuePair
  implements Serializable
{
  private String name = null;
  private String value = null;

  public NameValuePair()
  {
    this(null, null);
  }

  public NameValuePair(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
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
    while (!(paramObject instanceof NameValuePair));
    paramObject = (NameValuePair)paramObject;
    if ((LangUtils.equals(this.name, paramObject.name)) && (LangUtils.equals(this.value, paramObject.value)));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return LangUtils.hashCode(LangUtils.hashCode(17, this.name), this.value);
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setValue(String paramString)
  {
    this.value = paramString;
  }

  public String toString()
  {
    return "name=" + this.name + ", " + "value=" + this.value;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.NameValuePair
 * JD-Core Version:    0.6.2
 */