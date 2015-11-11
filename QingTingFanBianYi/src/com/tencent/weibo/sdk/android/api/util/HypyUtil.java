package com.tencent.weibo.sdk.android.api.util;

public class HypyUtil
{
  private static int BEGIN = 45217;
  private static int END = 63486;
  private static char[] chartable = { 21834, -32083, 25830, 25645, -30978, 21457, 22134, 21704, 21704, 20987, 21888, 22403, 22920, 25343, 21734, 21866, 26399, 28982, 25746, 22604, 22604, 22604, 25366, 26132, 21387, 21277 };
  private static char[] initialtable;
  private static int[] table = new int[27];

  static
  {
    initialtable = new char[] { 97, 98, 99, 100, 101, 102, 103, 104, 104, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 116, 116, 119, 120, 121, 122 };
    int i = 0;
    while (true)
    {
      if (i >= 26)
      {
        table[26] = END;
        return;
      }
      table[i] = gbValue(chartable[i]);
      i += 1;
    }
  }

  private static char Char2Initial(char paramChar)
  {
    char c;
    if ((paramChar >= 'a') && (paramChar <= 'z'))
      c = (char)(paramChar - 'a' + 65);
    int j;
    do
    {
      do
      {
        do
        {
          return c;
          if (paramChar < 'A')
            break;
          c = paramChar;
        }
        while (paramChar <= 'Z');
        j = gbValue(paramChar);
        c = paramChar;
      }
      while (j < BEGIN);
      c = paramChar;
    }
    while (j > END);
    int i = 0;
    while (true)
    {
      if (i >= 26);
      while ((j >= table[i]) && (j < table[(i + 1)]))
      {
        if (j == END)
          i = 25;
        return initialtable[i];
      }
      i += 1;
    }
  }

  public static String cn2py(String paramString)
  {
    String str = "";
    int j = paramString.length();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return str;
      try
      {
        str = str + Char2Initial(paramString.charAt(i));
        i += 1;
      }
      catch (Exception paramString)
      {
      }
    }
    return "";
  }

  private static int gbValue(char paramChar)
  {
    Object localObject = new String() + paramChar;
    try
    {
      localObject = ((String)localObject).getBytes("GB2312");
      if (localObject.length < 2)
        return 0;
      int i = localObject[0];
      int j = localObject[1];
      return (j & 0xFF) + (i << 8 & 0xFF00);
    }
    catch (Exception localException)
    {
    }
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.HypyUtil
 * JD-Core Version:    0.6.2
 */