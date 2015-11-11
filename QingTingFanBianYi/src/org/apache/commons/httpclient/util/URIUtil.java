package org.apache.commons.httpclient.util;

import java.util.BitSet;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

public class URIUtil
{
  protected static final BitSet empty = new BitSet(1);

  public static String decode(String paramString)
    throws URIException
  {
    try
    {
      paramString = EncodingUtil.getString(URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(paramString)), URI.getDefaultProtocolCharset());
      return paramString;
    }
    catch (DecoderException paramString)
    {
    }
    throw new URIException(paramString.getMessage());
  }

  public static String decode(String paramString1, String paramString2)
    throws URIException
  {
    return Coder.decode(paramString1.toCharArray(), paramString2);
  }

  public static String encode(String paramString, BitSet paramBitSet)
    throws URIException
  {
    return encode(paramString, paramBitSet, URI.getDefaultProtocolCharset());
  }

  public static String encode(String paramString1, BitSet paramBitSet, String paramString2)
    throws URIException
  {
    return EncodingUtil.getAsciiString(URLCodec.encodeUrl(paramBitSet, EncodingUtil.getBytes(paramString1, paramString2)));
  }

  public static String encodeAll(String paramString)
    throws URIException
  {
    return encodeAll(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeAll(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, empty, paramString2);
  }

  public static String encodePath(String paramString)
    throws URIException
  {
    return encodePath(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodePath(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, URI.allowed_abs_path, paramString2);
  }

  public static String encodePathQuery(String paramString)
    throws URIException
  {
    return encodePathQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodePathQuery(String paramString1, String paramString2)
    throws URIException
  {
    int i = paramString1.indexOf('?');
    if (i < 0)
      return encode(paramString1, URI.allowed_abs_path, paramString2);
    return encode(paramString1.substring(0, i), URI.allowed_abs_path, paramString2) + '?' + encode(paramString1.substring(i + 1), URI.allowed_query, paramString2);
  }

  public static String encodeQuery(String paramString)
    throws URIException
  {
    return encodeQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeQuery(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, URI.allowed_query, paramString2);
  }

  public static String encodeWithinAuthority(String paramString)
    throws URIException
  {
    return encodeWithinAuthority(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinAuthority(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, URI.allowed_within_authority, paramString2);
  }

  public static String encodeWithinPath(String paramString)
    throws URIException
  {
    return encodeWithinPath(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinPath(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, URI.allowed_within_path, paramString2);
  }

  public static String encodeWithinQuery(String paramString)
    throws URIException
  {
    return encodeWithinQuery(paramString, URI.getDefaultProtocolCharset());
  }

  public static String encodeWithinQuery(String paramString1, String paramString2)
    throws URIException
  {
    return encode(paramString1, URI.allowed_within_query, paramString2);
  }

  public static String getFromPath(String paramString)
  {
    int j = 0;
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    int k = paramString.indexOf("//");
    int i = j;
    if (k >= 0)
      if (paramString.lastIndexOf("/", k - 1) < 0)
        break label56;
    label56: for (i = j; ; i = k + 2)
    {
      i = paramString.indexOf("/", i);
      if (i >= 0)
        break label63;
      if (k < 0)
        break;
      return "/";
    }
    label63: return paramString.substring(i);
  }

  public static String getName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0));
    String str;
    int i;
    int j;
    do
    {
      return paramString;
      str = getPath(paramString);
      i = str.lastIndexOf("/");
      j = str.length();
      paramString = str;
    }
    while (i < 0);
    return str.substring(i + 1, j);
  }

  public static String getPath(String paramString)
  {
    int j = 0;
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    int k = paramString.indexOf("//");
    int i = j;
    if (k >= 0)
      if (paramString.lastIndexOf("/", k - 1) < 0)
        break label116;
    int m;
    label116: for (i = j; ; i = k + 2)
    {
      m = paramString.indexOf("/", i);
      i = paramString.length();
      if (paramString.indexOf('?', m) != -1)
        i = paramString.indexOf('?', m);
      j = i;
      if (paramString.lastIndexOf("#") > m)
      {
        j = i;
        if (paramString.lastIndexOf("#") < i)
          j = paramString.lastIndexOf("#");
      }
      if (m >= 0)
        break label123;
      if (k < 0)
        break;
      return "/";
    }
    label123: return paramString.substring(m, j);
  }

  public static String getPathQuery(String paramString)
  {
    int j = 0;
    if (paramString == null)
    {
      paramString = null;
      return paramString;
    }
    int k = paramString.indexOf("//");
    int i = j;
    if (k >= 0)
      if (paramString.lastIndexOf("/", k - 1) < 0)
        break label78;
    label78: for (i = j; ; i = k + 2)
    {
      j = paramString.indexOf("/", i);
      i = paramString.length();
      if (paramString.lastIndexOf("#") > j)
        i = paramString.lastIndexOf("#");
      if (j >= 0)
        break label85;
      if (k < 0)
        break;
      return "/";
    }
    label85: return paramString.substring(j, i);
  }

  public static String getQuery(String paramString)
  {
    int j = 0;
    if ((paramString == null) || (paramString.length() == 0));
    int k;
    int i;
    do
    {
      return null;
      k = paramString.indexOf("//");
      i = j;
      if (k >= 0)
      {
        if (paramString.lastIndexOf("/", k - 1) < 0)
          break;
        i = j;
      }
      j = paramString.indexOf("/", i);
      i = paramString.length();
      j = paramString.indexOf("?", j);
    }
    while (j < 0);
    j += 1;
    if (paramString.lastIndexOf("#") > j)
      i = paramString.lastIndexOf("#");
    if ((j < 0) || (j == i));
    for (paramString = null; ; paramString = paramString.substring(j, i))
    {
      return paramString;
      i = k + 2;
      break;
    }
  }

  protected static class Coder extends URI
  {
    public static String decode(char[] paramArrayOfChar, String paramString)
      throws URIException
    {
      return URI.decode(paramArrayOfChar, paramString);
    }

    public static char[] encode(String paramString1, BitSet paramBitSet, String paramString2)
      throws URIException
    {
      return URI.encode(paramString1, paramBitSet, paramString2);
    }

    public static String replace(String paramString, char paramChar1, char paramChar2)
    {
      StringBuffer localStringBuffer = new StringBuffer(paramString.length());
      int i = 0;
      int j = paramString.indexOf(paramChar1);
      if (j >= 0)
      {
        localStringBuffer.append(paramString.substring(0, j));
        localStringBuffer.append(paramChar2);
      }
      while (true)
      {
        i = j;
        if (j >= 0)
          break;
        return localStringBuffer.toString();
        localStringBuffer.append(paramString.substring(i));
      }
    }

    public static String replace(String paramString, char[] paramArrayOfChar1, char[] paramArrayOfChar2)
    {
      int i = paramArrayOfChar1.length;
      while (true)
      {
        if (i <= 0)
          return paramString.toString();
        paramString = replace(paramString, paramArrayOfChar1[i], paramArrayOfChar2[i]);
        i -= 1;
      }
    }

    public static boolean verifyEscaped(char[] paramArrayOfChar)
    {
      boolean bool2 = false;
      int j;
      for (int i = 0; ; i = j + 1)
      {
        boolean bool1;
        if (i >= paramArrayOfChar.length)
          bool1 = true;
        do
        {
          do
          {
            int k;
            do
            {
              return bool1;
              k = paramArrayOfChar[i];
              bool1 = bool2;
            }
            while (k > 128);
            j = i;
            if (k != 37)
              break;
            i += 1;
            bool1 = bool2;
          }
          while (Character.digit(paramArrayOfChar[i], 16) == -1);
          j = i + 1;
          bool1 = bool2;
        }
        while (Character.digit(paramArrayOfChar[j], 16) == -1);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.URIUtil
 * JD-Core Version:    0.6.2
 */