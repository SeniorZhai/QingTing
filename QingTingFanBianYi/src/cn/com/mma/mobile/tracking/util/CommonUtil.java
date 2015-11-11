package cn.com.mma.mobile.tracking.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.com.mma.mobile.tracking.bean.Argument;
import cn.com.mma.mobile.tracking.bean.Company;
import cn.com.mma.mobile.tracking.bean.Switch;
import cn.mmachina.JniClient;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil
{
  public static String encodingUTF8(String paramString)
  {
    if (paramString == null)
      return "";
    try
    {
      paramString = URLEncoder.encode(paramString, "utf-8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    return "";
  }

  public static String encodingUTF8(String paramString, Argument paramArgument, Company paramCompany)
  {
    String str = paramString;
    label122: 
    do
    {
      while (true)
      {
        try
        {
          if (paramCompany.sswitch.encrypt.containsKey(paramArgument.key))
          {
            str = paramString;
            if ("md5".equals(paramCompany.sswitch.encrypt.get(paramArgument.key)))
            {
              paramCompany = paramString;
              if (!"MAC".equals(paramArgument.key))
                break label122;
              if (paramString != null)
                continue;
              paramCompany = "";
              break label122;
            }
          }
          if (paramArgument.urlEncode)
            break label134;
          if (str != null)
            break;
          return "";
          paramCompany = paramString.replaceAll(":", "");
          break label122;
          str = md5(paramCompany.toUpperCase());
          continue;
          paramString = URLEncoder.encode(str, "utf-8");
        }
        catch (UnsupportedEncodingException paramString)
        {
          return "";
        }
        if (paramCompany == null)
          str = "";
      }
      return str;
    }
    while (str != null);
    label134: paramString = "";
    return paramString;
  }

  public static String getHostURL(String paramString)
  {
    String str = "";
    Matcher localMatcher = Pattern.compile("^([\\w\\d]+):\\/\\/([\\w\\d\\-_]+(?:\\.[\\w\\d\\-_]+)*)").matcher(paramString);
    paramString = str;
    if (localMatcher.find())
      paramString = localMatcher.group(0);
    return paramString;
  }

  public static String getSignature(Context paramContext, String paramString)
  {
    paramString.toLowerCase();
    return JniClient.MDString("", paramContext, paramString);
  }

  public static boolean isMobileConnected(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(0);
      bool1 = bool2;
      if (paramContext != null)
        bool1 = paramContext.isAvailable();
    }
    return bool1;
  }

  public static boolean isNetWorkEnable(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }

  public static boolean isWifiConnected(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
      bool1 = bool2;
      if (paramContext != null)
        bool1 = paramContext.isAvailable();
    }
    return bool1;
  }

  public static String md5(String paramString)
  {
    String str2 = paramString;
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (!"".equals(paramString))
      {
        str1 = str2;
        try
        {
          MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
          str1 = str2;
          localMessageDigest.update(paramString.getBytes());
          str1 = str2;
          for (paramString = new BigInteger(1, localMessageDigest.digest()).toString(16); ; paramString = "0" + paramString)
          {
            str1 = paramString;
            if (paramString.length() >= 32)
              return paramString;
            str1 = paramString;
          }
        }
        catch (NoSuchAlgorithmException paramString)
        {
          paramString.printStackTrace();
        }
      }
    }
    return str1;
  }

  public static Map removeExistArgmentAndGetRedirectURL(String paramString1, List<String> paramList, String paramString2, String paramString3, String paramString4)
  {
    Object localObject = sortByLength(paramList);
    paramList = new HashMap();
    localObject = ((List)localObject).iterator();
    while (true)
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramList.put("URL", paramString1);
        return paramList;
      }
      String str = (String)((Iterator)localObject).next();
      if (paramString1.contains(paramString2 + str))
      {
        if (str.equals(paramString4))
        {
          Matcher localMatcher = Pattern.compile(paramString2 + str + "[^" + paramString2 + "]*").matcher(paramString1);
          if (localMatcher.find())
            localMatcher.group(0).replace(paramString2 + str, "");
        }
        paramString1 = paramString1.replaceAll(paramString2 + str + paramString3 + "[^" + paramString2 + "]*", "");
      }
    }
  }

  public static String removeExistEvent(String paramString1, List<String> paramList, String paramString2, String paramString3)
  {
    paramList = paramList.iterator();
    while (true)
    {
      if (!paramList.hasNext())
        return paramString1;
      String str = (String)paramList.next();
      if (paramString1.contains(paramString2 + str))
      {
        Logger.d("mma_" + paramString2 + str + paramString3 + "[^" + paramString2 + "]*");
        paramString1 = paramString1.replaceAll(paramString2 + str + paramString3 + "[^" + paramString2 + "]*", "");
      }
    }
  }

  private static List<String> sortByLength(List paramList)
  {
    Collections.sort(paramList, new Comparator()
    {
      public int compare(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (paramAnonymousString1.length() > paramAnonymousString2.length())
          return -1;
        return 1;
      }
    });
    return paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.util.CommonUtil
 * JD-Core Version:    0.6.2
 */