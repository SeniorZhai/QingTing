package com.intowow.sdk.j;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.intowow.sdk.a.e;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class i
{
  private static Map<String, Boolean> a = new HashMap();

  static
  {
    a.put("android.permission.INTERNET", Boolean.valueOf(false));
    a.put("android.permission.ACCESS_NETWORK_STATE", Boolean.valueOf(false));
    a.put("android.permission.WRITE_EXTERNAL_STORAGE", Boolean.valueOf(false));
    a.put("com.intowow.sdk.SplashAdActivity", Boolean.valueOf(false));
    a.put("com.intowow.sdk.WebViewActivity", Boolean.valueOf(false));
    a.put("com.intowow.sdk.ScheduleReceiver", Boolean.valueOf(false));
    a.put("CRYSTAL_ID", Boolean.valueOf(false));
  }

  public static String a()
  {
    return Build.VERSION.RELEASE;
  }

  public static void a(Context paramContext)
  {
    boolean bool = true;
    if (e());
    while (true)
    {
      return;
      Object localObject = paramContext.getPackageManager();
      String str = paramContext.getPackageName();
      try
      {
        PackageInfo localPackageInfo = ((PackageManager)localObject).getPackageInfo(str, 4096);
        if (localPackageInfo != null)
          a("permission", localPackageInfo.requestedPermissions);
        localPackageInfo = ((PackageManager)localObject).getPackageInfo(str, 1);
        if (localPackageInfo != null)
          a("activity", localPackageInfo.activities);
        localObject = ((PackageManager)localObject).getPackageInfo(str, 2);
        if (localObject != null)
          a("receiver", ((PackageInfo)localObject).receivers);
        localObject = a;
        if (b(paramContext) != null);
        while (true)
        {
          ((Map)localObject).put("CRYSTAL_ID", Boolean.valueOf(bool));
          if (e())
            break;
          Log.e("I2WAPI", f());
          a(null);
          return;
          bool = false;
        }
      }
      catch (Exception paramContext)
      {
        while (true)
        {
          Log.e("I2WAPI", paramContext.toString(), paramContext);
          a(null);
        }
      }
    }
  }

  private static void a(String paramString)
  {
    paramString.equals("");
  }

  private static void a(String paramString, Object paramObject)
  {
    int j = 0;
    int i = 0;
    if (paramObject == null);
    while (true)
    {
      return;
      if (paramString.equals("permission"))
      {
        paramString = (String[])paramObject;
        j = paramString.length;
        while (i < j)
        {
          paramObject = paramString[i];
          if (a.containsKey(paramObject))
            a.put(paramObject, Boolean.valueOf(true));
          i += 1;
        }
      }
      else
      {
        paramString = (ActivityInfo[])paramObject;
        int k = paramString.length;
        i = j;
        while (i < k)
        {
          paramObject = paramString[i];
          if (a.containsKey(paramObject.name))
            a.put(paramObject.name, Boolean.valueOf(true));
          i += 1;
        }
      }
    }
  }

  public static String b()
  {
    return Build.MODEL;
  }

  // ERROR //
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 63	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_0
    //   5: invokevirtual 66	android/content/Context:getPackageName	()Ljava/lang/String;
    //   8: sipush 128
    //   11: invokevirtual 144	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   14: getfield 150	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   17: ldc 42
    //   19: invokevirtual 156	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   22: astore_0
    //   23: aload_0
    //   24: invokestatic 161	com/intowow/sdk/j/l:a	(Ljava/lang/String;)Z
    //   27: ifne +14 -> 41
    //   30: aload_0
    //   31: invokevirtual 165	java/lang/String:length	()I
    //   34: istore_2
    //   35: iload_2
    //   36: bipush 32
    //   38: if_icmpeq +5 -> 43
    //   41: aconst_null
    //   42: astore_0
    //   43: aload_0
    //   44: areturn
    //   45: astore_0
    //   46: aconst_null
    //   47: areturn
    //   48: astore_1
    //   49: aload_0
    //   50: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	23	45	java/lang/Exception
    //   23	35	48	java/lang/Exception
  }

  public static String c()
  {
    return Build.MANUFACTURER;
  }

  public static String c(Context paramContext)
  {
    if (paramContext == null)
      return "0.0.0";
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return "0.0.0";
  }

  public static int d()
  {
    return e.b;
  }

  public static int d(Context paramContext)
  {
    if (paramContext == null)
      return 0;
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return 0;
  }

  public static String e(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext.getSimState() == 5)
        {
          paramContext = paramContext.getSimOperator();
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        return null;
      }
    return null;
  }

  private static boolean e()
  {
    Iterator localIterator = a.keySet().iterator();
    do
      if (!localIterator.hasNext())
        return true;
    while (((Boolean)a.get(localIterator.next())).booleanValue());
    return false;
  }

  private static String f()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\n\n=================\n\n");
    localStringBuilder.append("please adding these properties in the AndroidManifest.xml as bellow : \n\n");
    Iterator localIterator = a.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("=================\n\n");
        return localStringBuilder.toString();
      }
      String str = (String)localIterator.next();
      if (!((Boolean)a.get(str)).booleanValue())
        localStringBuilder.append(str).append("\n");
    }
  }

  public static boolean f(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    int j;
    int i;
    do
      try
      {
        paramContext = localPackageManager.getPackageInfo(paramContext, 4096);
        if (paramContext == null)
          break;
        paramContext = paramContext.requestedPermissions;
        j = paramContext.length;
        i = 0;
        continue;
        boolean bool = "android.permission.READ_PHONE_STATE".equals(paramContext[i]);
        if (bool)
          return true;
        i += 1;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    while (i < j);
    return false;
  }

  public static boolean g(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    int j;
    int i;
    do
      try
      {
        paramContext = localPackageManager.getPackageInfo(paramContext, 4096);
        if (paramContext == null)
          break;
        paramContext = paramContext.requestedPermissions;
        j = paramContext.length;
        i = 0;
        continue;
        boolean bool = "android.permission.ACCESS_WIFI_STATE".equals(paramContext[i]);
        if (bool)
          return true;
        i += 1;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    while (i < j);
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.i
 * JD-Core Version:    0.6.2
 */