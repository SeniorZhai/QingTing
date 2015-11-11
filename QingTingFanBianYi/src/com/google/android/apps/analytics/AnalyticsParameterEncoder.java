package com.google.android.apps.analytics;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AnalyticsParameterEncoder
{
  public static String encode(String paramString)
  {
    return encode(paramString, "UTF-8");
  }

  static String encode(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = URLEncoder.encode(paramString1, paramString2).replace("+", "%20");
      return paramString2;
    }
    catch (UnsupportedEncodingException paramString2)
    {
    }
    throw new AssertionError("URL encoding failed for: " + paramString1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.AnalyticsParameterEncoder
 * JD-Core Version:    0.6.2
 */