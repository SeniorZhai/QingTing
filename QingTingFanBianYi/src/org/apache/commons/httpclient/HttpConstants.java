package org.apache.commons.httpclient;

import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConstants
{
  public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
  public static final String HTTP_ELEMENT_CHARSET = "US-ASCII";
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HttpConstants;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpConstants == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpConstants");
      class$org$apache$commons$httpclient$HttpConstants = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$HttpConstants;
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

  public static byte[] getAsciiBytes(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      paramString = paramString.getBytes("US-ASCII");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException("HttpClient requires ASCII support");
  }

  public static String getAsciiString(byte[] paramArrayOfByte)
  {
    return getAsciiString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static String getAsciiString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramInt1, paramInt2, "US-ASCII");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new RuntimeException("HttpClient requires ASCII support");
  }

  public static byte[] getBytes(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
    }
    return paramString.getBytes();
  }

  public static byte[] getContentBytes(String paramString)
  {
    return getContentBytes(paramString, null);
  }

  public static byte[] getContentBytes(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Parameter may not be null");
    String str;
    if (paramString2 != null)
    {
      str = paramString2;
      if (!paramString2.equals(""));
    }
    else
    {
      str = "ISO-8859-1";
    }
    try
    {
      paramString2 = paramString1.getBytes(str);
      return paramString2;
    }
    catch (UnsupportedEncodingException paramString2)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: " + str + ". HTTP default encoding used");
      try
      {
        paramString2 = paramString1.getBytes("ISO-8859-1");
        return paramString2;
      }
      catch (UnsupportedEncodingException paramString2)
      {
        if (LOG.isWarnEnabled())
          LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
      }
    }
    return paramString1.getBytes();
  }

  public static String getContentString(byte[] paramArrayOfByte)
  {
    return getContentString(paramArrayOfByte, null);
  }

  public static String getContentString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return getContentString(paramArrayOfByte, paramInt1, paramInt2, null);
  }

  public static String getContentString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (!paramString.equals(""));
    }
    else
    {
      str = "ISO-8859-1";
    }
    try
    {
      paramString = new String(paramArrayOfByte, paramInt1, paramInt2, str);
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: " + str + ". Default HTTP encoding used");
      try
      {
        paramString = new String(paramArrayOfByte, paramInt1, paramInt2, "ISO-8859-1");
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        if (LOG.isWarnEnabled())
          LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
      }
    }
    return new String(paramArrayOfByte, paramInt1, paramInt2);
  }

  public static String getContentString(byte[] paramArrayOfByte, String paramString)
  {
    return getContentString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }

  public static String getString(byte[] paramArrayOfByte)
  {
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, "US-ASCII");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
    }
    return new String(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpConstants
 * JD-Core Version:    0.6.2
 */