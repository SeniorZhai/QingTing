package org.apache.commons.httpclient.util;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodingUtil
{
  private static final String DEFAULT_CHARSET = "ISO-8859-1";
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$util$EncodingUtil;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$EncodingUtil == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.EncodingUtil");
      class$org$apache$commons$httpclient$util$EncodingUtil = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$util$EncodingUtil;
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

  private static String doFormUrlEncode(NameValuePair[] paramArrayOfNameValuePair, String paramString)
    throws UnsupportedEncodingException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfNameValuePair.length)
        return localStringBuffer.toString();
      URLCodec localURLCodec = new URLCodec();
      NameValuePair localNameValuePair = paramArrayOfNameValuePair[i];
      if (localNameValuePair.getName() != null)
      {
        if (i > 0)
          localStringBuffer.append("&");
        localStringBuffer.append(localURLCodec.encode(localNameValuePair.getName(), paramString));
        localStringBuffer.append("=");
        if (localNameValuePair.getValue() != null)
          localStringBuffer.append(localURLCodec.encode(localNameValuePair.getValue(), paramString));
      }
      i += 1;
    }
  }

  public static String formUrlEncode(NameValuePair[] paramArrayOfNameValuePair, String paramString)
  {
    try
    {
      String str = doFormUrlEncode(paramArrayOfNameValuePair, paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      LOG.error("Encoding not supported: " + paramString);
      try
      {
        paramArrayOfNameValuePair = doFormUrlEncode(paramArrayOfNameValuePair, "ISO-8859-1");
        return paramArrayOfNameValuePair;
      }
      catch (UnsupportedEncodingException paramArrayOfNameValuePair)
      {
      }
    }
    throw new HttpClientError("Encoding not supported: ISO-8859-1");
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
    throw new HttpClientError("HttpClient requires ASCII support");
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
    throw new HttpClientError("HttpClient requires ASCII support");
  }

  public static byte[] getBytes(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("data may not be null");
    if ((paramString2 == null) || (paramString2.length() == 0))
      throw new IllegalArgumentException("charset may not be null or empty");
    try
    {
      byte[] arrayOfByte = paramString1.getBytes(paramString2);
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: " + paramString2 + ". System encoding used.");
    }
    return paramString1.getBytes();
  }

  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("charset may not be null or empty");
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      if (LOG.isWarnEnabled())
        LOG.warn("Unsupported encoding: " + paramString + ". System encoding used");
    }
    return new String(paramArrayOfByte, paramInt1, paramInt2);
  }

  public static String getString(byte[] paramArrayOfByte, String paramString)
  {
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.EncodingUtil
 * JD-Core Version:    0.6.2
 */