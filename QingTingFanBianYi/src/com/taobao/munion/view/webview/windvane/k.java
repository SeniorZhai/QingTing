package com.taobao.munion.view.webview.windvane;

import java.util.regex.Pattern;

public class k
{
  private static final String[] a = { "wv_hybrid:", "mraid:", "ssp:" };
  private static final Pattern b = Pattern.compile("hybrid://(.+?):(.+?)/(.+?)(\\?(.*?))?");
  private static final Pattern c = Pattern.compile("mraid://(.+?):(.+?)/(.+?)(\\?(.*?))?");
  private static final Pattern d = Pattern.compile("ssp://(.+?):(.+?)/(.+?)(\\?(.*?))?");

  public static boolean a(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equals(paramString))
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  public static Pattern b(String paramString)
  {
    if ("wv_hybrid:".equals(paramString))
      return b;
    if ("mraid:".equals(paramString))
      return c;
    if ("ssp:".equals(paramString))
      return d;
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.k
 * JD-Core Version:    0.6.2
 */