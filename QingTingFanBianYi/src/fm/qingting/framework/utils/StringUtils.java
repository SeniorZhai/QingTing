package fm.qingting.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
  public static String changeCharset(String paramString1, String paramString2)
  {
    String str = paramString1;
    if (paramString1 != null);
    try
    {
      str = new String(paramString1.getBytes(), paramString2);
      return str;
    }
    catch (Exception paramString2)
    {
      paramString2.printStackTrace();
    }
    return paramString1;
  }

  public static <T extends Enum<T>> T getEnumWithString(Class<T> paramClass, String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      paramClass = Enum.valueOf(paramClass, paramString.toUpperCase());
      return paramClass;
    }
    catch (IllegalArgumentException paramClass)
    {
    }
    return null;
  }

  public static Map<String, Object> searchBody(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    return searchBody(paramString, paramArrayOfString1, paramArrayOfString2, -1);
  }

  public static Map<String, Object> searchBody(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt)
  {
    int i2 = 0;
    int i5 = 0;
    int i3 = 0;
    int i4 = 0;
    int i;
    int j;
    int k;
    int i1;
    int m;
    int n;
    if (paramInt < 0)
    {
      paramInt = 0;
      i = 0;
      j = 0;
      k = 0;
      i1 = i;
      m = j;
      n = k;
      if (paramArrayOfString1 != null)
      {
        n = 0;
        m = k;
        k = i5;
      }
    }
    while (true)
    {
      if (n >= paramArrayOfString1.length)
      {
        n = m;
        i2 = k;
        m = j;
        i1 = i;
        if (i2 != 0)
          break label163;
        return null;
        m = paramInt;
        i = paramInt;
        j = paramInt;
        k = paramInt;
        paramInt = m;
        break;
      }
      Matcher localMatcher = Pattern.compile(paramArrayOfString1[n]).matcher(paramString);
      i1 = j;
      if (localMatcher.find(j))
      {
        k = 1;
        m = localMatcher.start();
        i1 = localMatcher.end();
        i = i1;
      }
      n += 1;
      j = i1;
    }
    label163: if (paramArrayOfString2 == null)
    {
      i2 = paramString.length();
      k = i4;
      i3 = paramInt;
      if (k == 0)
        return null;
    }
    else
    {
      j = 0;
      i = i3;
      while (true)
      {
        i3 = paramInt;
        i2 = m;
        k = i;
        if (j >= paramArrayOfString2.length)
          break;
        paramArrayOfString1 = Pattern.compile(paramArrayOfString2[j]).matcher(paramString);
        k = m;
        if (paramArrayOfString1.find(m))
        {
          i = 1;
          paramInt = paramArrayOfString1.start();
          k = paramArrayOfString1.end();
        }
        j += 1;
        m = k;
      }
    }
    paramArrayOfString1 = new HashMap();
    paramArrayOfString1.put("body", paramString.substring(n, i2));
    paramArrayOfString1.put("content", paramString.substring(i1, i3));
    paramArrayOfString1.put("bodyFrom", Integer.valueOf(n));
    paramArrayOfString1.put("bodyTo", Integer.valueOf(i2));
    paramArrayOfString1.put("contentFrom", Integer.valueOf(i1));
    paramArrayOfString1.put("contentTo", Integer.valueOf(i3));
    return paramArrayOfString1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.StringUtils
 * JD-Core Version:    0.6.2
 */