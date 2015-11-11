package fm.qingting.async.http.libcore;

final class HeaderParser
{
  public static void parseCacheControl(String paramString, CacheControlHandler paramCacheControlHandler)
  {
    int i = 0;
    while (i < paramString.length())
    {
      int j = skipUntil(paramString, i, "=,");
      String str2 = paramString.substring(i, j).trim();
      if ((j == paramString.length()) || (paramString.charAt(j) == ','))
      {
        i = j + 1;
        paramCacheControlHandler.handle(str2, null);
      }
      else
      {
        j = skipWhitespace(paramString, j + 1);
        String str1;
        if ((j < paramString.length()) && (paramString.charAt(j) == '"'))
        {
          i = j + 1;
          j = skipUntil(paramString, i, "\"");
          str1 = paramString.substring(i, j);
          i = j + 1;
        }
        while (true)
        {
          paramCacheControlHandler.handle(str2, str1);
          break;
          i = skipUntil(paramString, j, ",");
          str1 = paramString.substring(j, i).trim();
        }
      }
    }
  }

  public static int parseSeconds(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      if (l > 2147483647L)
        return 2147483647;
      if (l < 0L)
        return 0;
      return (int)l;
    }
    catch (NumberFormatException paramString)
    {
    }
    return -1;
  }

  private static int skipUntil(String paramString1, int paramInt, String paramString2)
  {
    while (true)
    {
      if ((paramInt >= paramString1.length()) || (paramString2.indexOf(paramString1.charAt(paramInt)) != -1))
        return paramInt;
      paramInt += 1;
    }
  }

  private static int skipWhitespace(String paramString, int paramInt)
  {
    while (true)
    {
      if (paramInt < paramString.length())
      {
        int i = paramString.charAt(paramInt);
        if ((i == 32) || (i == 9));
      }
      else
      {
        return paramInt;
      }
      paramInt += 1;
    }
  }

  public static abstract interface CacheControlHandler
  {
    public abstract void handle(String paramString1, String paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.HeaderParser
 * JD-Core Version:    0.6.2
 */