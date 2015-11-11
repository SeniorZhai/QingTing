package cn.com.iresearch.mapptracker.fm.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import cn.com.iresearch.mapptracker.fm.dao.MATMessage;
import cn.com.iresearch.mapptracker.fm.dao.b;
import java.io.File;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class f
{
  private static SharedPreferences a;
  private static SharedPreferences.Editor b;

  public static MATMessage a(Context paramContext, String paramString1, String paramString2)
  {
    String str = a.a(paramString1, "UTF-8", paramString1.length());
    paramString1 = new MATMessage();
    try
    {
      paramContext = e.a(paramContext, str);
      System.out.println(paramContext);
      str = IRMonitor.a;
      paramContext = a(paramString2, "p=" + str + "&etype=" + "2" + "&msg=" + paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return paramString1;
  }

  private static MATMessage a(String paramString1, String paramString2)
  {
    MATMessage localMATMessage = new MATMessage();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      paramString2 = new StringEntity(paramString2, "UTF-8");
      localObject1 = localObject2;
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      localObject1 = localObject2;
      HttpClientParams.setCookiePolicy(localDefaultHttpClient.getParams(), "compatibility");
      localObject1 = localObject2;
      localDefaultHttpClient.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));
      localObject1 = localObject2;
      localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(10000));
      localObject1 = localObject2;
      if (IRMonitor.c)
      {
        localObject1 = localObject2;
        Log.e("MAT_SESSION", "send start---------- ");
      }
      localObject1 = localObject2;
      HttpClientParams.setCookiePolicy(localDefaultHttpClient.getParams(), "compatibility");
      localObject1 = localObject2;
      HttpPost localHttpPost = new HttpPost(paramString1);
      localObject1 = localObject2;
      paramString2.setContentType("application/x-www-form-urlencoded");
      localObject1 = localObject2;
      localHttpPost.setEntity(paramString2);
      localObject1 = localObject2;
      paramString2 = localDefaultHttpClient.execute(localHttpPost);
      localObject1 = localObject2;
      int i = paramString2.getStatusLine().getStatusCode();
      localObject1 = localObject2;
      paramString1 = URLDecoder.decode(EntityUtils.toString(paramString2.getEntity())) + " status:" + i + " url:" + paramString1;
      switch (i)
      {
      case 200:
        localObject1 = paramString1;
        localMATMessage.setFlag(false);
        localObject1 = paramString1;
        localMATMessage.setMsg(paramString1);
        while (true)
        {
          if (IRMonitor.c)
            Log.e("MAT_SESSION", "send end---------- ");
          return localMATMessage;
          localObject1 = paramString1;
          localMATMessage.setFlag(true);
          localObject1 = paramString1;
          localMATMessage.setMsg(paramString1);
        }
      }
    }
    catch (Exception paramString1)
    {
      while (true)
      {
        localMATMessage.setFlag(false);
        localMATMessage.setMsg((String)localObject1);
      }
    }
  }

  public static b a(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MATSharedPreferences", 0).edit();
    paramContext = new MATMessage();
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(5000));
    try
    {
      paramString = localDefaultHttpClient.execute(new HttpGet(paramString));
      int i = paramString.getStatusLine().getStatusCode();
      paramString = URLDecoder.decode(EntityUtils.toString(paramString.getEntity()));
      if (200 == i)
      {
        paramContext.setFlag(true);
        paramContext.setMsg(paramString);
        if (!"".equals(paramContext))
        {
          paramContext = new JSONObject(paramContext.msg);
          paramString = new b();
          paramString.a("http://" + paramContext.getString("sip") + "/rec/se?_iwt_t=i&sv=2");
          paramString.b("http://" + paramContext.getString("sip") + "/rec/cl?_iwt_t=i&sv=2");
          paramString.c("http://" + paramContext.getString("cip") + "/cfg/appkey-");
          paramString.a(Integer.parseInt(paramContext.getString("exp"), 10) * 60L + System.currentTimeMillis() / 1000L);
          paramString.a(Integer.parseInt(paramContext.getString("itl"), 10));
          paramString.b(Integer.parseInt(paramContext.getString("sm"), 10));
          paramString.c(Integer.parseInt(paramContext.getString("lc"), 10));
          if (paramContext.isNull("pd"));
          for (paramContext = ""; ; paramContext = paramContext.getString("pd"))
          {
            paramString.d(paramContext);
            localEditor.putString("SendDataUrl", paramString.a());
            localEditor.putString("SendClientUrl", paramString.b());
            localEditor.putLong("ConfigExpireTime", paramString.c());
            localEditor.putString("ConfigUrl", paramString.d());
            localEditor.putInt("LimitInterval", paramString.e());
            localEditor.putInt("sendMode", paramString.f());
            localEditor.putInt("LimitCount", paramString.g());
            localEditor.putString("Pd", paramString.h());
            localEditor.commit();
            return paramString;
          }
        }
      }
      else
      {
        paramContext = "配置获取失败,status: " + i;
        if (IRMonitor.c)
          Log.e("MAT_SESSION", paramContext);
        localEditor.putLong("ConfigExpireTime", System.currentTimeMillis() / 1000L);
      }
      return null;
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  @SuppressLint({"SimpleDateFormat"})
  public static String a()
  {
    try
    {
      Object localObject = new Date(System.currentTimeMillis());
      localObject = new SimpleDateFormat("yyyyMMdd").format((Date)localObject);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static String a(Context paramContext, int paramInt)
  {
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if (d(paramContext, "android.permission.GET_TASKS"))
        return ((ActivityManager.RunningTaskInfo)localActivityManager.getRunningTasks(3).get(paramInt)).topActivity.getClassName();
      if (IRMonitor.c)
        Log.e("lost permission", "android.permission.GET_TASKS");
      return "";
    }
    catch (SecurityException paramContext)
    {
    }
    return "";
  }

  public static String a(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramMap == null)
      return localJSONObject.toString();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localJSONObject.toString();
      String str = (String)((Map.Entry)localIterator.next()).getKey();
      try
      {
        localJSONObject.put(str, paramMap.get(str));
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  public static boolean a(Context paramContext)
  {
    while (true)
    {
      int i;
      try
      {
        if (d(paramContext, "android.permission.ACCESS_WIFI_STATE"))
        {
          paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
          if (paramContext != null)
          {
            paramContext = paramContext.getAllNetworkInfo();
            if (paramContext != null)
            {
              i = 0;
              if (i < paramContext.length)
              {
                if ((!paramContext[i].getTypeName().equals("WIFI")) || (!paramContext[i].isConnected()))
                  break label99;
                return true;
              }
            }
          }
        }
        else if (IRMonitor.c)
        {
          Log.e("lost permission", "lost--->android.permission.ACCESS_WIFI_STATE");
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
      return false;
      label99: i += 1;
    }
  }

  public static List<String> b(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ArrayList();
    int j;
    int i;
    do
      try
      {
        paramString = localPackageManager.getPackageInfo(paramString, 1).activities;
        j = paramString.length;
        i = 0;
        continue;
        paramContext.add(paramString[i].name);
        i += 1;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        paramString.printStackTrace();
        return paramContext;
      }
    while (i < j);
    return paramContext;
  }

  public static boolean b()
  {
    boolean bool2 = true;
    int j;
    int i;
    do
      try
      {
        String[] arrayOfString = new String[5];
        arrayOfString[0] = "/system/bin/";
        arrayOfString[1] = "/system/xbin/";
        arrayOfString[2] = "/data/local/xbin/";
        arrayOfString[3] = "/data/local/bin/";
        arrayOfString[4] = "/system/sd/xbin/";
        j = arrayOfString.length;
        i = 0;
        continue;
        boolean bool3 = new File(arrayOfString[i] + "su").exists();
        bool1 = bool2;
        if (bool3)
          break label110;
        i += 1;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        break;
      }
    while (i < j);
    boolean bool1 = false;
    label110: return bool1;
  }

  public static boolean b(Context paramContext)
  {
    if (d(paramContext, "android.permission.INTERNET"))
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isAvailable()))
        return true;
      if (IRMonitor.c)
        Log.e("error", "Network error");
      return false;
    }
    if (IRMonitor.c)
      Log.e(" lost  permission", "lost----> android.permission.INTERNET");
    return false;
  }

  private static String c()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "unKnown";
  }

  public static String c(Context paramContext)
  {
    try
    {
      if (d(paramContext, "android.permission.GET_TASKS"))
        return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      if (IRMonitor.c)
        Log.e("lost permission", "android.permission.GET_TASKS");
      return "";
    }
    catch (SecurityException paramContext)
    {
    }
    return "";
  }

  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      if (d(paramContext, "android.permission.GET_TASKS"))
      {
        paramContext = ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
        if ((paramContext != null) || (!"".equals(paramContext)))
        {
          if (!paramContext.equals(paramString))
            break label88;
          return true;
        }
      }
      else if (IRMonitor.c)
      {
        Log.e("MAT_SESSION", "缺少：android.permission.GET_TASKS 权限");
      }
      return false;
    }
    catch (SecurityException paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
    label88: return false;
  }

  private static String d()
  {
    return TimeZone.getDefault().getOffset(new Date().getTime()) / 1000;
  }

  public static String d(Context paramContext)
  {
    while (true)
    {
      String str;
      int i;
      try
      {
        if (d(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          str = "";
          if (paramContext.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", paramContext.getPackageName()) == 0)
            break label99;
          i = 0;
          if (i != 0)
          {
            str = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
            break label93;
            if (IRMonitor.c)
              Log.e("MAT_SESSION", "deviceId is null");
            return "";
          }
        }
        else
        {
          if (IRMonitor.c)
            Log.e("MAT_SESSION", "lost----->android.permission.READ_PHONE_STATE");
          return "";
        }
      }
      catch (Exception paramContext)
      {
        return "";
      }
      label93: if (str != null)
      {
        return str;
        label99: i = 1;
      }
    }
  }

  private static boolean d(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }

  public static JSONObject e(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject = (WindowManager)paramContext.getSystemService("window");
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)localObject).getDefaultDisplay().getMetrics(localDisplayMetrics);
      localJSONObject.put("app_key", IRMonitor.a);
      localJSONObject.put("uid", " " + g(paramContext));
      localJSONObject.put("uidtype", Build.MANUFACTURER + " " + Build.MODEL);
      localJSONObject.put("ip", "");
      localJSONObject.put("imei", IRMonitor.b);
      localJSONObject.put("appid", c(paramContext));
      localJSONObject.put("appver", k(paramContext));
      localJSONObject.put("mac_hash", "");
      localJSONObject.put("network", j(paramContext));
      localObject = l(paramContext);
      if (((String)localObject).equals(""))
      {
        localJSONObject.put("carrier", "--");
        localJSONObject.put("country", "--");
      }
      while (true)
      {
        localJSONObject.put("city", localDisplayMetrics.heightPixels + "*" + localDisplayMetrics.widthPixels);
        localJSONObject.put("timezone", d());
        localJSONObject.put("os_name", "Android");
        localJSONObject.put("os_ver", c());
        localJSONObject.put("sdk_ver", "2.3.2");
        localJSONObject.put("channel", h(paramContext));
        localJSONObject.put("col1", Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
        localJSONObject.put("col2", ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId());
        localJSONObject.put("col3", ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress());
        paramContext = paramContext.getSharedPreferences("MATSharedPreferences", 0);
        a = paramContext;
        b = paramContext.edit();
        localJSONObject.put("ts", a.getLong("ts", System.currentTimeMillis() / 1000L));
        b.putLong("ts", System.currentTimeMillis() / 1000L).commit();
        localJSONObject.put("dd", "3" + a().substring(1));
        localJSONObject.put("lac_cid", "0_0");
        return localJSONObject;
        localJSONObject.put("carrier", ((String)localObject).substring(3));
        localJSONObject.put("country", ((String)localObject).substring(0, 3));
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }

  public static JSONObject f(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject2 = (WindowManager)paramContext.getSystemService("window");
      Object localObject1 = new DisplayMetrics();
      ((WindowManager)localObject2).getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      localJSONObject.put("app_key", IRMonitor.a);
      localJSONObject.put("uid", " " + g(paramContext));
      localJSONObject.put("uidtype", Build.MANUFACTURER + " " + Build.MODEL);
      localJSONObject.put("ip", "");
      localJSONObject.put("imei", IRMonitor.b);
      localJSONObject.put("appid", c(paramContext));
      localJSONObject.put("appver", k(paramContext));
      localJSONObject.put("mac_hash", "");
      localJSONObject.put("network", j(paramContext));
      localObject2 = l(paramContext);
      if (((String)localObject2).equals(""))
      {
        localJSONObject.put("carrier", "--");
        localJSONObject.put("country", "--");
      }
      while (true)
      {
        localJSONObject.put("city", ((DisplayMetrics)localObject1).heightPixels + "*" + ((DisplayMetrics)localObject1).widthPixels);
        localJSONObject.put("timezone", d());
        localJSONObject.put("os_name", "Android");
        localJSONObject.put("os_ver", c());
        localJSONObject.put("sdk_ver", "2.3.2");
        localJSONObject.put("channel", h(paramContext));
        localJSONObject.put("col1", Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
        localJSONObject.put("col2", ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId());
        localJSONObject.put("col3", ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress());
        localObject1 = paramContext.getSharedPreferences("MATSharedPreferences", 0);
        a = (SharedPreferences)localObject1;
        b = ((SharedPreferences)localObject1).edit();
        localJSONObject.put("ts", a.getLong("ts", System.currentTimeMillis() / 1000L));
        b.putLong("ts", System.currentTimeMillis() / 1000L).commit();
        localJSONObject.put("dd", "3" + a().substring(1));
        paramContext = i(paramContext);
        localJSONObject.put("lac_cid", paramContext.a + "_" + paramContext.b);
        return localJSONObject;
        localJSONObject.put("carrier", ((String)localObject2).substring(3));
        localJSONObject.put("country", ((String)localObject2).substring(0, 3));
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }

  public static String g(Context paramContext)
  {
    try
    {
      Object localObject1 = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      Object localObject2 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      paramContext = ((WifiManager)paramContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      localObject1 = localObject1 + (String)localObject2 + str + paramContext;
      try
      {
        paramContext = MessageDigest.getInstance("MD5");
        paramContext.update(((String)localObject1).getBytes(), 0, ((String)localObject1).length());
        localObject2 = paramContext.digest();
        paramContext = new String();
        i = 0;
        if (i >= localObject2.length)
          return paramContext.toUpperCase();
      }
      catch (NoSuchAlgorithmException paramContext)
      {
        while (true)
        {
          int i;
          paramContext.printStackTrace();
          paramContext = null;
          continue;
          int j = localObject2[i] & 0xFF;
          localObject1 = paramContext;
          if (j <= 15)
            localObject1 = paramContext + "0";
          paramContext = localObject1 + Integer.toHexString(j);
          i += 1;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  private static String h(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("ire_channel");
        if (paramContext != null)
          return paramContext.toString();
        if (IRMonitor.c)
          Log.e("MAT", "Could not read ire_channel meta-data from AndroidManifest.xml.");
      }
      return "";
    }
    catch (Exception paramContext)
    {
      while (true)
        if (IRMonitor.c)
        {
          Log.e("MAT", "Could not read ire_channel meta-data from AndroidManifest.xml.");
          paramContext.printStackTrace();
        }
    }
  }

  private static g i(Context paramContext)
  {
    int j = 0;
    g localg = new g((byte)0);
    try
    {
      if (d(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        Object localObject = paramContext.getCellLocation();
        if (localObject == null)
          return localg;
        if ((localObject instanceof CdmaCellLocation))
        {
          localObject = (CdmaCellLocation)localObject;
          j = ((CdmaCellLocation)localObject).getNetworkId();
          i = ((CdmaCellLocation)localObject).getBaseStationId() / 16;
          paramContext = paramContext.getNetworkOperator();
          if ((paramContext != null) && (paramContext.length() >= 3))
            break label159;
        }
        while (true)
        {
          if ((paramContext != null) && (paramContext.length() >= 3))
            break label180;
          localg.a = j;
          localg.b = i;
          if (paramContext.equals(""))
            break label197;
          Integer.parseInt(paramContext);
          break label197;
          if (!(localObject instanceof GsmCellLocation))
            break label192;
          localObject = (GsmCellLocation)localObject;
          i = ((GsmCellLocation)localObject).getCid();
          j = ((GsmCellLocation)localObject).getLac();
          i &= 65535;
          break;
          label159: Integer.parseInt(paramContext.substring(0, 3));
        }
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        paramContext.printStackTrace();
        break;
        label180: Integer.parseInt(paramContext.substring(3));
        continue;
        label192: int i = 0;
      }
    }
    label197: return localg;
  }

  private static String j(Context paramContext)
  {
    try
    {
      if (d(paramContext, "android.permission.ACCESS_NETWORK_STATE"))
      {
        Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
        paramContext = ((ConnectivityManager)localObject).getNetworkInfo(0).getState();
        localObject = ((ConnectivityManager)localObject).getNetworkInfo(1).getState();
        if ((paramContext == NetworkInfo.State.CONNECTED) || (paramContext == NetworkInfo.State.CONNECTING))
          break label83;
        if ((localObject == NetworkInfo.State.CONNECTED) || (localObject == NetworkInfo.State.CONNECTING))
          return "WIFI";
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "UNKNOWN";
    label83: return "3G";
  }

  // ERROR //
  private static String k(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 475	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 569	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 484	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   12: getfield 898	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   15: astore_0
    //   16: aload_0
    //   17: ifnull +14 -> 31
    //   20: aload_0
    //   21: invokevirtual 19	java/lang/String:length	()I
    //   24: istore_3
    //   25: aload_0
    //   26: astore_1
    //   27: iload_3
    //   28: ifgt +6 -> 34
    //   31: ldc 219
    //   33: astore_1
    //   34: aload_1
    //   35: areturn
    //   36: astore_2
    //   37: ldc 219
    //   39: astore_0
    //   40: aload_0
    //   41: astore_1
    //   42: getstatic 128	cn/com/iresearch/mapptracker/fm/IRMonitor:c	Z
    //   45: ifeq -11 -> 34
    //   48: ldc_w 838
    //   51: ldc_w 900
    //   54: aload_2
    //   55: invokestatic 903	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   58: pop
    //   59: aload_0
    //   60: areturn
    //   61: astore_2
    //   62: goto -22 -> 40
    //
    // Exception table:
    //   from	to	target	type
    //   0	16	36	java/lang/Exception
    //   20	25	61	java/lang/Exception
  }

  private static String l(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (5 == paramContext.getSimState())
      {
        paramContext = paramContext.getSimOperator();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.util.f
 * JD-Core Version:    0.6.2
 */