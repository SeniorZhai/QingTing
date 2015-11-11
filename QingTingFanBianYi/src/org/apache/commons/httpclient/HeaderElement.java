package org.apache.commons.httpclient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.util.ParameterParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeaderElement extends NameValuePair
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HeaderElement;
  private NameValuePair[] parameters = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HeaderElement == null)
    {
      localClass = class$("org.apache.commons.httpclient.HeaderElement");
      class$org$apache$commons$httpclient$HeaderElement = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$HeaderElement;
    }
  }

  public HeaderElement()
  {
    this(null, null, null);
  }

  public HeaderElement(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }

  public HeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    super(paramString1, paramString2);
    this.parameters = paramArrayOfNameValuePair;
  }

  public HeaderElement(char[] paramArrayOfChar)
  {
    this(paramArrayOfChar, 0, paramArrayOfChar.length);
  }

  public HeaderElement(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this();
    if (paramArrayOfChar == null);
    do
    {
      do
      {
        return;
        paramArrayOfChar = new ParameterParser().parse(paramArrayOfChar, paramInt1, paramInt2, ';');
      }
      while (paramArrayOfChar.size() <= 0);
      NameValuePair localNameValuePair = (NameValuePair)paramArrayOfChar.remove(0);
      setName(localNameValuePair.getName());
      setValue(localNameValuePair.getValue());
    }
    while (paramArrayOfChar.size() <= 0);
    this.parameters = ((NameValuePair[])paramArrayOfChar.toArray(new NameValuePair[paramArrayOfChar.size()]));
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

  public static final HeaderElement[] parse(String paramString)
    throws HttpException
  {
    LOG.trace("enter HeaderElement.parse(String)");
    if (paramString == null)
      return new HeaderElement[0];
    return parseElements(paramString.toCharArray());
  }

  public static final HeaderElement[] parseElements(String paramString)
  {
    LOG.trace("enter HeaderElement.parseElements(String)");
    if (paramString == null)
      return new HeaderElement[0];
    return parseElements(paramString.toCharArray());
  }

  public static final HeaderElement[] parseElements(char[] paramArrayOfChar)
  {
    LOG.trace("enter HeaderElement.parseElements(char[])");
    if (paramArrayOfChar == null)
      return new HeaderElement[0];
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    int k = 0;
    int n = paramArrayOfChar.length;
    int m = 0;
    if (j >= n)
      return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
    int i1 = paramArrayOfChar[j];
    int i = m;
    label89: HeaderElement localHeaderElement;
    if (i1 == 34)
    {
      if (m == 0)
        i = 1;
    }
    else
    {
      localHeaderElement = null;
      if ((i != 0) || (i1 != 44))
        break label161;
      localHeaderElement = new HeaderElement(paramArrayOfChar, k, j);
      m = j + 1;
    }
    while (true)
    {
      if ((localHeaderElement != null) && (localHeaderElement.getName() != null))
        localArrayList.add(localHeaderElement);
      j += 1;
      k = m;
      m = i;
      break;
      i = 0;
      break label89;
      label161: m = k;
      if (j == n - 1)
      {
        localHeaderElement = new HeaderElement(paramArrayOfChar, k, n);
        m = k;
      }
    }
  }

  public NameValuePair getParameterByName(String paramString)
  {
    LOG.trace("enter HeaderElement.getParameterByName(String)");
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    NameValuePair[] arrayOfNameValuePair = getParameters();
    int i;
    if (arrayOfNameValuePair != null)
      i = 0;
    while (true)
    {
      if (i >= arrayOfNameValuePair.length)
        return null;
      NameValuePair localNameValuePair = arrayOfNameValuePair[i];
      if (localNameValuePair.getName().equalsIgnoreCase(paramString))
        return localNameValuePair;
      i += 1;
    }
  }

  public NameValuePair[] getParameters()
  {
    return this.parameters;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HeaderElement
 * JD-Core Version:    0.6.2
 */