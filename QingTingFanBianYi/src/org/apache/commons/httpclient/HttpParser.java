package org.apache.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpParser
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HttpParser;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpParser == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpParser");
      class$org$apache$commons$httpclient$HttpParser = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$HttpParser;
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

  public static Header[] parseHeaders(InputStream paramInputStream)
    throws IOException, HttpException
  {
    LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
    return parseHeaders(paramInputStream, "US-ASCII");
  }

  public static Header[] parseHeaders(InputStream paramInputStream, String paramString)
    throws IOException, HttpException
  {
    LOG.trace("enter HeaderParser.parseHeaders(InputStream, String)");
    ArrayList localArrayList = new ArrayList();
    String str1 = null;
    StringBuffer localStringBuffer = null;
    while (true)
    {
      String str2 = readLine(paramInputStream, paramString);
      if ((str2 == null) || (str2.trim().length() < 1))
      {
        if (str1 != null)
          localArrayList.add(new Header(str1, localStringBuffer.toString()));
        return (Header[])localArrayList.toArray(new Header[localArrayList.size()]);
      }
      if ((str2.charAt(0) == ' ') || (str2.charAt(0) == '\t'))
      {
        if (localStringBuffer != null)
        {
          localStringBuffer.append(' ');
          localStringBuffer.append(str2.trim());
        }
      }
      else
      {
        if (str1 != null)
          localArrayList.add(new Header(str1, localStringBuffer.toString()));
        int i = str2.indexOf(":");
        if (i < 0)
          throw new ProtocolException("Unable to parse header: " + str2);
        str1 = str2.substring(0, i).trim();
        localStringBuffer = new StringBuffer(str2.substring(i + 1).trim());
      }
    }
  }

  public static String readLine(InputStream paramInputStream)
    throws IOException
  {
    LOG.trace("enter HttpParser.readLine(InputStream)");
    return readLine(paramInputStream, "US-ASCII");
  }

  public static String readLine(InputStream paramInputStream, String paramString)
    throws IOException
  {
    LOG.trace("enter HttpParser.readLine(InputStream, String)");
    paramInputStream = readRawLine(paramInputStream);
    if (paramInputStream == null)
      return null;
    int k = paramInputStream.length;
    int j = 0;
    int i = j;
    if (k > 0)
    {
      i = j;
      if (paramInputStream[(k - 1)] == 10)
      {
        j = 0 + 1;
        i = j;
        if (k > 1)
        {
          i = j;
          if (paramInputStream[(k - 2)] == 13)
            i = j + 1;
        }
      }
    }
    return EncodingUtil.getString(paramInputStream, 0, k - i, paramString);
  }

  public static byte[] readRawLine(InputStream paramInputStream)
    throws IOException
  {
    LOG.trace("enter HttpParser.readRawLine()");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = paramInputStream.read();
    if (i < 0);
    while (true)
    {
      if (localByteArrayOutputStream.size() != 0)
        break label50;
      return null;
      localByteArrayOutputStream.write(i);
      if (i != 10)
        break;
    }
    label50: return localByteArrayOutputStream.toByteArray();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpParser
 * JD-Core Version:    0.6.2
 */