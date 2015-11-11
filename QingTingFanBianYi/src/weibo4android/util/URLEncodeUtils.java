package weibo4android.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.BitSet;

public class URLEncodeUtils
{
  static BitSet dontNeedEncoding = new BitSet(256);

  static
  {
    int i = 97;
    while (i <= 122)
    {
      dontNeedEncoding.set(i);
      i += 1;
    }
    i = 65;
    while (i <= 90)
    {
      dontNeedEncoding.set(i);
      i += 1;
    }
    i = 48;
    while (i <= 57)
    {
      dontNeedEncoding.set(i);
      i += 1;
    }
    dontNeedEncoding.set(32);
    dontNeedEncoding.set(45);
    dontNeedEncoding.set(95);
    dontNeedEncoding.set(46);
    dontNeedEncoding.set(42);
    dontNeedEncoding.set(43);
    dontNeedEncoding.set(37);
  }

  public static final String decodeURL(String paramString)
  {
    try
    {
      paramString = URLDecoder.decode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  public static final String encodeURL(String paramString)
  {
    try
    {
      paramString = URLEncoder.encode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  public static final boolean isURLEncoded(String paramString)
  {
    boolean bool = true;
    if ((paramString == null) || ("".equals(paramString)))
      bool = false;
    int j;
    do
    {
      return bool;
      paramString = paramString.toCharArray();
      int k = paramString.length;
      int i = 0;
      j = 0;
      while (i < k)
      {
        char c = paramString[i];
        if (Character.isWhitespace(c))
          return false;
        if (!dontNeedEncoding.get(c))
          return false;
        if (c == '%')
          j = 1;
        i += 1;
      }
    }
    while (j != 0);
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.util.URLEncodeUtils
 * JD-Core Version:    0.6.2
 */