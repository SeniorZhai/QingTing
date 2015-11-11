package com.alipay.sdk.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.sys.UserLocation;
import com.alipay.sdk.tid.TidInfo;
import com.alipay.sdk.util.DeviceInfo;
import com.alipay.sdk.util.NetConnectionType;
import com.alipay.sdk.util.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MspConfig
{
  private static final String a = "virtualImeiAndImsi";
  private static final String b = "virtual_imei";
  private static final String c = "virtual_imsi";
  private static MspConfig d;
  private String e;
  private String f = "sdk-and-lite";
  private String g;

  public static MspConfig a()
  {
    try
    {
      if (d == null)
        d = new MspConfig();
      MspConfig localMspConfig = d;
      return localMspConfig;
    }
    finally
    {
    }
  }

  private static String a(Context paramContext)
  {
    return Float.toString(new TextView(paramContext).getTextSize());
  }

  private static String a(Context paramContext, HashMap paramHashMap)
  {
    return SecurityClientMobile.GetApdid(paramContext, paramHashMap);
  }

  private String b()
  {
    return this.g;
  }

  private static String b(Context paramContext)
  {
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (paramContext != null)
      return paramContext.getSSID();
    return "-1";
  }

  private static String c()
  {
    return "1";
  }

  private static String c(Context paramContext)
  {
    paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if (paramContext != null)
      return paramContext.getBSSID();
    return "00";
  }

  private static String d()
  {
    Context localContext = GlobalContext.a().b();
    SharedPreferences localSharedPreferences = localContext.getSharedPreferences("virtualImeiAndImsi", 0);
    String str2 = localSharedPreferences.getString("virtual_imei", null);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      if (!TextUtils.isEmpty(TidInfo.c().a()))
        break label72;
    label72: for (str1 = f(); ; str1 = DeviceInfo.a(localContext).b())
    {
      localSharedPreferences.edit().putString("virtual_imei", str1).commit();
      return str1;
    }
  }

  private static String e()
  {
    Context localContext = GlobalContext.a().b();
    SharedPreferences localSharedPreferences = localContext.getSharedPreferences("virtualImeiAndImsi", 0);
    String str2 = localSharedPreferences.getString("virtual_imsi", null);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      if (!TextUtils.isEmpty(TidInfo.c().a()))
        break label97;
      str1 = GlobalContext.a().g();
      if (!TextUtils.isEmpty(str1))
        break label86;
      str1 = f();
    }
    while (true)
    {
      localSharedPreferences.edit().putString("virtual_imsi", str1).commit();
      return str1;
      label86: str1 = str1.substring(3, 18);
      continue;
      label97: str1 = DeviceInfo.a(localContext).a();
    }
  }

  private static String f()
  {
    String str = Long.toHexString(System.currentTimeMillis());
    Random localRandom = new Random();
    return str + (localRandom.nextInt(9000) + 1000);
  }

  public final String a(TidInfo paramTidInfo)
  {
    Context localContext = GlobalContext.a().b();
    Object localObject4 = DeviceInfo.a(localContext);
    if (TextUtils.isEmpty(this.e))
    {
      localObject1 = Utils.a();
      localObject2 = Utils.b();
      localObject3 = Utils.d(localContext);
      str1 = Utils.c();
      str2 = Utils.e(localContext);
      str3 = Float.toString(new TextView(localContext).getTextSize());
      this.e = ("Msp/9.1.8" + " (" + (String)localObject1 + ";" + (String)localObject2 + ";" + (String)localObject3 + ";" + str1 + ";" + str2 + ";" + str3);
    }
    String str1 = DeviceInfo.b(localContext).a();
    String str2 = Utils.f(localContext);
    String str3 = ((DeviceInfo)localObject4).a();
    String str4 = ((DeviceInfo)localObject4).b();
    Object localObject5 = GlobalContext.a().b();
    Object localObject3 = ((Context)localObject5).getSharedPreferences("virtualImeiAndImsi", 0);
    Object localObject1 = ((SharedPreferences)localObject3).getString("virtual_imsi", null);
    Object localObject2 = localObject1;
    Object localObject6;
    label325: boolean bool;
    String str5;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      if (!TextUtils.isEmpty(TidInfo.c().a()))
        break label717;
      localObject1 = GlobalContext.a().g();
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = f();
        ((SharedPreferences)localObject3).edit().putString("virtual_imsi", (String)localObject1).commit();
        localObject2 = localObject1;
      }
    }
    else
    {
      localObject6 = GlobalContext.a().b();
      localObject5 = ((Context)localObject6).getSharedPreferences("virtualImeiAndImsi", 0);
      localObject3 = ((SharedPreferences)localObject5).getString("virtual_imei", null);
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        if (!TextUtils.isEmpty(TidInfo.c().a()))
          break label729;
        localObject1 = f();
        ((SharedPreferences)localObject5).edit().putString("virtual_imei", (String)localObject1).commit();
      }
      if (paramTidInfo != null)
        this.g = paramTidInfo.b();
      localObject5 = Build.MANUFACTURER.replace(";", " ");
      localObject6 = Build.MODEL.replace(";", " ");
      bool = GlobalContext.e();
      str5 = ((DeviceInfo)localObject4).c();
      localObject3 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo();
      if (localObject3 == null)
        break label741;
      localObject3 = ((WifiInfo)localObject3).getSSID();
      label421: localObject4 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo();
      if (localObject4 == null)
        break label748;
    }
    label717: label729: label741: label748: for (localObject4 = ((WifiInfo)localObject4).getBSSID(); ; localObject4 = "00")
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.e).append(";").append(str1).append(";").append(str2).append(";").append("1").append(";").append(str3).append(";").append(str4).append(";").append(this.g).append(";").append((String)localObject5).append(";").append((String)localObject6).append(";").append(bool).append(";").append(str5).append(";").append(UserLocation.a()).append(";").append(this.f).append(";").append((String)localObject2).append(";").append((String)localObject1).append(";").append((String)localObject3).append(";").append((String)localObject4);
      if (paramTidInfo != null)
      {
        localObject1 = new HashMap();
        ((HashMap)localObject1).put("tid", paramTidInfo.a());
        ((HashMap)localObject1).put("utdid", GlobalContext.a().g());
        paramTidInfo = SecurityClientMobile.GetApdid(localContext, (Map)localObject1);
        if (!TextUtils.isEmpty(paramTidInfo))
          localStringBuilder.append(";").append(paramTidInfo);
      }
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localObject1 = ((String)localObject1).substring(3, 18);
      break;
      localObject1 = DeviceInfo.a((Context)localObject5).a();
      break;
      localObject1 = DeviceInfo.a((Context)localObject6).b();
      break label325;
      localObject3 = "-1";
      break label421;
    }
  }

  public final void a(String paramString)
  {
    try
    {
      boolean bool = TextUtils.isEmpty(paramString);
      if (bool);
      while (true)
      {
        return;
        PreferenceManager.getDefaultSharedPreferences(GlobalContext.a().b()).edit().putString("trideskey", paramString).commit();
        com.alipay.sdk.cons.GlobalConstants.d = paramString;
      }
    }
    finally
    {
    }
    throw paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.data.MspConfig
 * JD-Core Version:    0.6.2
 */