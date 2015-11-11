package com.umeng.analytics.social;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public abstract class f
{
  private static Map<String, String> a;

  protected static String a(Context paramContext)
  {
    String str = e.d;
    if (!TextUtils.isEmpty(str))
    {
      b.b("MobclickAgent", "use usefully appkey from constant field.");
      return str;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null)
          return paramContext.trim();
        b.b("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
      return null;
    }
    catch (Exception paramContext)
    {
      while (true)
        b.b("MobclickAgent", "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
    }
  }

  private static String a(List<NameValuePair> paramList)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      new UrlEncodedFormEntity(paramList, "UTF-8").writeTo(localByteArrayOutputStream);
      paramList = localByteArrayOutputStream.toString();
      return paramList;
    }
    catch (Exception paramList)
    {
      paramList.printStackTrace();
    }
    return null;
  }

  private static List<NameValuePair> a(UMPlatformData[] paramArrayOfUMPlatformData)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    StringBuilder localStringBuilder3 = new StringBuilder();
    int j = paramArrayOfUMPlatformData.length;
    int i = 0;
    while (i < j)
    {
      UMPlatformData localUMPlatformData = paramArrayOfUMPlatformData[i];
      localStringBuilder1.append(localUMPlatformData.getMeida().toString());
      localStringBuilder1.append(',');
      localStringBuilder2.append(localUMPlatformData.getUsid());
      localStringBuilder2.append(',');
      localStringBuilder3.append(localUMPlatformData.getWeiboId());
      localStringBuilder3.append(',');
      i += 1;
    }
    if (paramArrayOfUMPlatformData.length > 0)
    {
      localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1);
      localStringBuilder2.deleteCharAt(localStringBuilder2.length() - 1);
      localStringBuilder3.deleteCharAt(localStringBuilder3.length() - 1);
    }
    paramArrayOfUMPlatformData = new ArrayList();
    paramArrayOfUMPlatformData.add(new BasicNameValuePair("platform", localStringBuilder1.toString()));
    paramArrayOfUMPlatformData.add(new BasicNameValuePair("usid", localStringBuilder2.toString()));
    if (localStringBuilder3.length() > 0)
      paramArrayOfUMPlatformData.add(new BasicNameValuePair("weiboid", localStringBuilder3.toString()));
    return paramArrayOfUMPlatformData;
  }

  private static boolean a(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }

  protected static String[] a(Context paramContext, String paramString, UMPlatformData[] paramArrayOfUMPlatformData)
    throws a
  {
    if ((paramArrayOfUMPlatformData == null) || (paramArrayOfUMPlatformData.length == 0))
      throw new a("platform data is null");
    String str = a(paramContext);
    if (TextUtils.isEmpty(str))
      throw new a("can`t get appkey.");
    ArrayList localArrayList = new ArrayList();
    str = "http://log.umsns.com/share/api/" + str + "/";
    if ((a == null) || (a.isEmpty()))
      a = c(paramContext);
    if ((a != null) && (!a.isEmpty()))
    {
      paramContext = a.entrySet().iterator();
      while (paramContext.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramContext.next();
        localArrayList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
      }
    }
    localArrayList.add(new BasicNameValuePair("date", String.valueOf(System.currentTimeMillis())));
    localArrayList.add(new BasicNameValuePair("channel", e.e));
    if (!TextUtils.isEmpty(paramString))
      localArrayList.add(new BasicNameValuePair("topic", paramString));
    localArrayList.addAll(a(paramArrayOfUMPlatformData));
    paramString = b(paramArrayOfUMPlatformData);
    paramContext = paramString;
    if (paramString == null)
      paramContext = "null";
    paramString = str + "?" + a(localArrayList);
    b.c("MobclickAgent", "URL:" + paramString);
    b.c("MobclickAgent", "BODY:" + paramContext);
    return new String[] { paramString, paramContext };
  }

  private static String b(UMPlatformData[] paramArrayOfUMPlatformData)
  {
    JSONObject localJSONObject1 = new JSONObject();
    int j = paramArrayOfUMPlatformData.length;
    int i = 0;
    while (true)
    {
      if (i < j)
      {
        UMPlatformData localUMPlatformData = paramArrayOfUMPlatformData[i];
        Object localObject = localUMPlatformData.getGender();
        String str = localUMPlatformData.getName();
        if (localObject == null);
        try
        {
          if (TextUtils.isEmpty(str))
            break label160;
          JSONObject localJSONObject2 = new JSONObject();
          if (localObject == null)
          {
            localObject = "";
            localJSONObject2.put("gender", localObject);
            if (str != null)
              break label124;
          }
          label124: for (localObject = ""; ; localObject = String.valueOf(str))
          {
            localJSONObject2.put("name", localObject);
            localJSONObject1.put(localUMPlatformData.getMeida().toString(), localJSONObject2);
            break label160;
            localObject = String.valueOf(((UMPlatformData.GENDER)localObject).value);
            break;
          }
        }
        catch (Exception paramArrayOfUMPlatformData)
        {
          throw new a("build body exception", paramArrayOfUMPlatformData);
        }
      }
      else
      {
        if (localJSONObject1.length() == 0)
          return null;
        return localJSONObject1.toString();
      }
      label160: i += 1;
    }
  }

  public static Map<String, String> b(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject1 == null)
      b.e("MobclickAgent", "No IMEI.");
    try
    {
      if (a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        localObject1 = ((TelephonyManager)localObject1).getDeviceId();
        String str = d(paramContext);
        paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(str))
          localHashMap.put("mac", str);
        if (!TextUtils.isEmpty((CharSequence)localObject1))
          localHashMap.put("imei", localObject1);
        if (!TextUtils.isEmpty(paramContext))
          localHashMap.put("android_id", paramContext);
        return localHashMap;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        b.e("MobclickAgent", "No IMEI.", localException);
        Object localObject2 = null;
      }
    }
  }

  private static Map<String, String> c(Context paramContext)
    throws a
  {
    HashMap localHashMap = new HashMap();
    Object localObject = b(paramContext);
    if ((localObject != null) && (!((Map)localObject).isEmpty()))
    {
      paramContext = new StringBuilder();
      StringBuilder localStringBuilder = new StringBuilder();
      localObject = ((Map)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        if (!TextUtils.isEmpty((CharSequence)localEntry.getValue()))
        {
          localStringBuilder.append((String)localEntry.getKey()).append(",");
          paramContext.append((String)localEntry.getValue()).append(",");
        }
      }
      if (paramContext.length() > 0)
      {
        paramContext.deleteCharAt(paramContext.length() - 1);
        localHashMap.put("deviceid", paramContext.toString());
      }
      if (localStringBuilder.length() > 0)
      {
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        localHashMap.put("idtype", localStringBuilder.toString());
      }
      return localHashMap;
    }
    throw new a("can`t get device id.");
  }

  private static String d(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
        return localWifiManager.getConnectionInfo().getMacAddress();
      b.e("MobclickAgent", "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
      return "";
    }
    catch (Exception paramContext)
    {
      while (true)
        b.e("MobclickAgent", "Could not get mac address." + paramContext.toString());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.social.f
 * JD-Core Version:    0.6.2
 */