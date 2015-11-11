package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.UUID;

public class Utility
{
  private static final String DEFAULT_CHARSET = "UTF-8";

  public static Bundle decodeUrl(String paramString)
  {
    int i = 0;
    Bundle localBundle = new Bundle();
    int j;
    if (paramString != null)
    {
      paramString = paramString.split("&");
      j = paramString.length;
    }
    while (true)
    {
      if (i >= j)
        return localBundle;
      String[] arrayOfString = paramString[i].split("=");
      try
      {
        localBundle.putString(URLDecoder.decode(arrayOfString[0], "UTF-8"), URLDecoder.decode(arrayOfString[1], "UTF-8"));
        i += 1;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
          localUnsupportedEncodingException.printStackTrace();
      }
    }
  }

  public static String generateGUID()
  {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static String generateUA(Context paramContext)
  {
    paramContext = new StringBuilder();
    paramContext.append(Build.MANUFACTURER).append("-").append(Build.MODEL);
    paramContext.append("_");
    paramContext.append(Build.VERSION.RELEASE);
    paramContext.append("_");
    paramContext.append("weibosdk");
    paramContext.append("_");
    paramContext.append("0030105000");
    paramContext.append("_android");
    return paramContext.toString();
  }

  public static String getAid(Context paramContext, String paramString)
  {
    paramContext = AidTask.getInstance(paramContext);
    String str = paramContext.loadAidFromCache();
    if (!TextUtils.isEmpty(str))
      return str;
    paramContext.aidTaskInit(paramString);
    return "";
  }

  public static String getSign(Context paramContext, String paramString)
  {
    while (true)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64);
        i = 0;
        if (i >= paramContext.signatures.length)
          return null;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return null;
      }
      paramString = paramContext.signatures[i].toByteArray();
      if (paramString != null)
        return MD5.hexdigest(paramString);
      i += 1;
    }
  }

  public static boolean isChineseLocale(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getConfiguration().locale;
      if ((!Locale.CHINA.equals(paramContext)) && (!Locale.CHINESE.equals(paramContext)) && (!Locale.SIMPLIFIED_CHINESE.equals(paramContext)))
      {
        boolean bool = Locale.TAIWAN.equals(paramContext);
        if (!bool);
      }
      else
      {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      return true;
    }
    return false;
  }

  public static Bundle parseUri(String paramString)
  {
    try
    {
      paramString = decodeUrl(new URI(paramString).getQuery());
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return new Bundle();
  }

  public static Bundle parseUrl(String paramString)
  {
    try
    {
      paramString = new URL(paramString);
      Bundle localBundle = decodeUrl(paramString.getQuery());
      localBundle.putAll(decodeUrl(paramString.getRef()));
      return localBundle;
    }
    catch (MalformedURLException paramString)
    {
    }
    return new Bundle();
  }

  public static String safeString(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
      str = "";
    return str;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.Utility
 * JD-Core Version:    0.6.2
 */