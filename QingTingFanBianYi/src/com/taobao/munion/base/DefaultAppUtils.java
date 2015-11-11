package com.taobao.munion.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.TypedValue;
import java.util.List;
import java.util.Locale;

public class DefaultAppUtils
  implements a
{
  private a a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private String n;
  private String o;
  private float p;
  private int q;
  private int r;
  private String s;
  private String t;
  private Context u;
  private boolean v = false;
  private boolean w = false;
  private boolean x = false;
  private boolean y = false;
  private String z = null;

  public static Intent a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext = localPackageManager.queryIntentActivities(localIntent, 0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = (ResolveInfo)paramContext.get(0);
      localIntent.setFlags(67108864);
      localIntent.setComponent(new ComponentName(paramContext.activityInfo.applicationInfo.packageName, paramContext.activityInfo.name));
      return localIntent;
    }
    return null;
  }

  public static boolean a(Activity paramActivity)
  {
    Intent localIntent = a(paramActivity);
    if (localIntent != null)
    {
      paramActivity.startActivity(localIntent);
      return true;
    }
    return false;
  }

  private String g(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return this.a.b;
    return paramString.replaceAll(" ", "");
  }

  public int A()
  {
    Intent localIntent = this.u.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    return localIntent.getIntExtra("level", 0) * 100 / localIntent.getIntExtra("scale", 100);
  }

  public String B()
  {
    return this.i;
  }

  public String C()
  {
    return this.g;
  }

  public String[] D()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.a.b;
    arrayOfString[1] = this.a.b;
    if (this.u != null)
    {
      if (!e("android.permission.ACCESS_NETWORK_STATE"))
        return arrayOfString;
      Object localObject = (ConnectivityManager)this.u.getSystemService("connectivity");
      if (localObject == null)
        return arrayOfString;
      if (((ConnectivityManager)localObject).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = this.a.d;
        return arrayOfString;
      }
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
      if (((NetworkInfo)localObject).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = this.a.c;
        arrayOfString[1] = ((NetworkInfo)localObject).getSubtypeName();
        return arrayOfString;
      }
    }
    return arrayOfString;
  }

  public String[] E()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.a.b;
    arrayOfString[1] = this.a.b;
    if (this.u != null)
    {
      if (!e("android.permission.ACCESS_NETWORK_STATE"))
        return arrayOfString;
      Object localObject = (ConnectivityManager)this.u.getSystemService("connectivity");
      if (localObject == null)
        return arrayOfString;
      if (((ConnectivityManager)localObject).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = "wifi";
        return arrayOfString;
      }
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
      if (((NetworkInfo)localObject).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = this.a.c;
        arrayOfString[1] = ((NetworkInfo)localObject).getSubtypeName();
        return arrayOfString;
      }
    }
    return arrayOfString;
  }

  public Location F()
  {
    try
    {
      Object localObject = (LocationManager)this.u.getSystemService("location");
      if (e("android.permission.ACCESS_FINE_LOCATION"))
      {
        Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
        if (localLocation != null)
        {
          Log.d("get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude(), new Object[0]);
          return localLocation;
        }
      }
      if (e("android.permission.ACCESS_COARSE_LOCATION"))
      {
        localObject = ((LocationManager)localObject).getLastKnownLocation("network");
        if (localObject != null)
        {
          Log.d("get location from network:" + ((Location)localObject).getLatitude() + "," + ((Location)localObject).getLongitude(), new Object[0]);
          return localObject;
        }
      }
    }
    catch (Exception localException)
    {
      Log.e(localException, "", new Object[0]);
    }
    while (true)
    {
      return null;
      Log.i("Could not get loction from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?", new Object[0]);
    }
  }

  public String G()
  {
    return this.s;
  }

  public String H()
  {
    return this.t;
  }

  public float a(float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, this.u.getResources().getDisplayMetrics());
  }

  protected void a(Context paramContext, Class<?>[] paramArrayOfClass)
  {
    int i2 = paramArrayOfClass.length;
    int i1 = 0;
    while (i1 < i2)
    {
      paramContext = paramArrayOfClass[i1];
      if (c(paramContext) == null)
        Log.w("No activity element declared for [" + paramContext.getName() + "].  Please ensure you have included this in your AndroidManifest.xml", new Object[0]);
      i1 += 1;
    }
  }

  void a(boolean paramBoolean)
  {
    this.y = paramBoolean;
  }

  public boolean a()
  {
    return false;
  }

  public boolean a(Intent paramIntent)
  {
    return this.u.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }

  public boolean a(Class<?> paramClass)
  {
    return a(new Intent(this.u, paramClass));
  }

  public boolean a(String paramString)
  {
    try
    {
      boolean bool = a(Class.forName(paramString));
      return bool;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    return false;
  }

  public int b(Context paramContext)
  {
    return paramContext.getApplicationInfo().icon;
  }

  public boolean b()
  {
    return false;
  }

  public boolean b(Class<?> paramClass)
  {
    return this.u.getPackageManager().queryIntentServices(new Intent(this.u, paramClass), 65536).size() > 0;
  }

  public boolean b(String paramString)
  {
    return a(new Intent(paramString));
  }

  public ActivityInfo c(Class<?> paramClass)
  {
    try
    {
      ActivityInfo localActivityInfo = this.u.getPackageManager().getActivityInfo(new ComponentName(this.u, paramClass), 0);
      return localActivityInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e(localNameNotFoundException, "Failed to locate info for activity [" + paramClass.getName() + "]", new Object[0]);
    }
    return null;
  }

  public boolean c()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.u.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isConnectedOrConnecting();
        return bool;
      }
    }
    catch (Exception localException)
    {
      return false;
    }
    return false;
  }

  public boolean c(String paramString)
  {
    if (this.u != null)
    {
      PackageManager localPackageManager = this.u.getPackageManager();
      try
      {
        localPackageManager.getPackageInfo(paramString, 1);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        return false;
      }
    }
    return false;
  }

  public String d(String paramString)
  {
    if ((paramString != null) && (paramString.equalsIgnoreCase("amazon")))
      return "amz";
    return null;
  }

  public boolean d()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public boolean d(Class<?> paramClass)
  {
    return this.u.getPackageManager().queryBroadcastReceivers(new Intent(this.u, paramClass), 65536).size() > 0;
  }

  public boolean e()
  {
    boolean bool = false;
    if (this.u != null)
      bool = this.u.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    return bool;
  }

  public boolean e(String paramString)
  {
    return this.u.getPackageManager().checkPermission(paramString, this.u.getPackageName()) == 0;
  }

  public String f()
  {
    return this.c;
  }

  public String f(String paramString)
  {
    if (this.u != null)
      try
      {
        ApplicationInfo localApplicationInfo = this.u.getPackageManager().getApplicationInfo(this.u.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          paramString = localApplicationInfo.metaData.get(paramString);
          if (paramString != null)
          {
            paramString = paramString.toString();
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
        Log.e("Could not read meta-data from AndroidManifest.xml.", new Object[0]);
      }
    return null;
  }

  public String g()
  {
    return this.b;
  }

  public String h()
  {
    return this.e;
  }

  public String i()
  {
    return this.f;
  }

  public void init(Context paramContext)
  {
    init(paramContext, new a());
  }

  // ERROR //
  public void init(Context paramContext, a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 169	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   5: putfield 165	com/taobao/munion/base/DefaultAppUtils:u	Landroid/content/Context;
    //   8: aload_0
    //   9: aload_2
    //   10: putfield 150	com/taobao/munion/base/DefaultAppUtils:a	Lcom/taobao/munion/base/DefaultAppUtils$a;
    //   13: aload_0
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 79	android/content/Context:getPackageName	()Ljava/lang/String;
    //   19: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   22: putfield 444	com/taobao/munion/base/DefaultAppUtils:b	Ljava/lang/String;
    //   25: aload_1
    //   26: invokevirtual 62	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   29: astore_2
    //   30: aload_0
    //   31: aload_2
    //   32: aload_2
    //   33: aload_0
    //   34: getfield 444	com/taobao/munion/base/DefaultAppUtils:b	Ljava/lang/String;
    //   37: iconst_0
    //   38: invokevirtual 430	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   41: invokevirtual 462	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   44: invokeinterface 465 1 0
    //   49: putfield 427	com/taobao/munion/base/DefaultAppUtils:c	Ljava/lang/String;
    //   52: aload_0
    //   53: getfield 427	com/taobao/munion/base/DefaultAppUtils:c	Ljava/lang/String;
    //   56: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   59: ifeq +11 -> 70
    //   62: aload_0
    //   63: aload_0
    //   64: getfield 444	com/taobao/munion/base/DefaultAppUtils:b	Ljava/lang/String;
    //   67: putfield 427	com/taobao/munion/base/DefaultAppUtils:c	Ljava/lang/String;
    //   70: aload_1
    //   71: invokevirtual 62	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   74: aload_1
    //   75: invokevirtual 79	android/content/Context:getPackageName	()Ljava/lang/String;
    //   78: iconst_0
    //   79: invokevirtual 385	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   82: astore_2
    //   83: aload_0
    //   84: aload_0
    //   85: aload_2
    //   86: getfield 470	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   89: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   92: putfield 446	com/taobao/munion/base/DefaultAppUtils:e	Ljava/lang/String;
    //   95: aload_0
    //   96: aload_0
    //   97: aload_2
    //   98: getfield 473	android/content/pm/PackageInfo:versionCode	I
    //   101: invokestatic 477	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   104: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   107: putfield 448	com/taobao/munion/base/DefaultAppUtils:f	Ljava/lang/String;
    //   110: aload_0
    //   111: ldc_w 479
    //   114: invokevirtual 199	com/taobao/munion/base/DefaultAppUtils:e	(Ljava/lang/String;)Z
    //   117: ifeq +58 -> 175
    //   120: aload_1
    //   121: ldc_w 481
    //   124: invokevirtual 205	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   127: checkcast 483	android/telephony/TelephonyManager
    //   130: astore_2
    //   131: aload_0
    //   132: aload_2
    //   133: invokevirtual 486	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   136: putfield 189	com/taobao/munion/base/DefaultAppUtils:i	Ljava/lang/String;
    //   139: aload_0
    //   140: aload_0
    //   141: aload_2
    //   142: invokevirtual 489	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   145: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   148: putfield 491	com/taobao/munion/base/DefaultAppUtils:l	Ljava/lang/String;
    //   151: aload_0
    //   152: aload_0
    //   153: aload_2
    //   154: invokevirtual 494	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   157: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   160: putfield 496	com/taobao/munion/base/DefaultAppUtils:m	Ljava/lang/String;
    //   163: aload_0
    //   164: aload_0
    //   165: aload_2
    //   166: invokevirtual 499	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   169: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   172: putfield 295	com/taobao/munion/base/DefaultAppUtils:s	Ljava/lang/String;
    //   175: aload_0
    //   176: getfield 189	com/taobao/munion/base/DefaultAppUtils:i	Ljava/lang/String;
    //   179: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   182: ifeq +17 -> 199
    //   185: aload_0
    //   186: aload_0
    //   187: invokestatic 503	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   190: invokevirtual 506	java/util/Locale:getCountry	()Ljava/lang/String;
    //   193: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   196: putfield 189	com/taobao/munion/base/DefaultAppUtils:i	Ljava/lang/String;
    //   199: aload_0
    //   200: ldc_w 508
    //   203: invokevirtual 199	com/taobao/munion/base/DefaultAppUtils:e	(Ljava/lang/String;)Z
    //   206: ifeq +26 -> 232
    //   209: aload_0
    //   210: aload_0
    //   211: aload_1
    //   212: ldc 233
    //   214: invokevirtual 205	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   217: checkcast 510	android/net/wifi/WifiManager
    //   220: invokevirtual 514	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   223: invokevirtual 519	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   226: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   229: putfield 521	com/taobao/munion/base/DefaultAppUtils:n	Ljava/lang/String;
    //   232: new 523	android/util/DisplayMetrics
    //   235: dup
    //   236: invokespecial 524	android/util/DisplayMetrics:<init>	()V
    //   239: astore_2
    //   240: aload_1
    //   241: ldc_w 526
    //   244: invokevirtual 205	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   247: checkcast 528	android/view/WindowManager
    //   250: checkcast 528	android/view/WindowManager
    //   253: invokeinterface 532 1 0
    //   258: aload_2
    //   259: invokevirtual 538	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   262: aload_0
    //   263: aload_2
    //   264: getfield 541	android/util/DisplayMetrics:widthPixels	I
    //   267: putfield 543	com/taobao/munion/base/DefaultAppUtils:q	I
    //   270: aload_0
    //   271: aload_2
    //   272: getfield 546	android/util/DisplayMetrics:heightPixels	I
    //   275: putfield 548	com/taobao/munion/base/DefaultAppUtils:r	I
    //   278: aload_0
    //   279: new 250	java/lang/StringBuilder
    //   282: dup
    //   283: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   286: aload_0
    //   287: getfield 548	com/taobao/munion/base/DefaultAppUtils:r	I
    //   290: invokestatic 477	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   293: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: aload_0
    //   297: getfield 150	com/taobao/munion/base/DefaultAppUtils:a	Lcom/taobao/munion/base/DefaultAppUtils$a;
    //   300: getfield 550	com/taobao/munion/base/DefaultAppUtils$a:a	Ljava/lang/String;
    //   303: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: aload_0
    //   307: getfield 543	com/taobao/munion/base/DefaultAppUtils:q	I
    //   310: invokestatic 477	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   313: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: putfield 552	com/taobao/munion/base/DefaultAppUtils:o	Ljava/lang/String;
    //   322: aload_0
    //   323: aload_2
    //   324: getfield 555	android/util/DisplayMetrics:density	F
    //   327: putfield 557	com/taobao/munion/base/DefaultAppUtils:p	F
    //   330: aload_1
    //   331: invokevirtual 303	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   334: invokevirtual 410	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   337: astore_2
    //   338: aload_2
    //   339: ifnull +237 -> 576
    //   342: aload_2
    //   343: getfield 416	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   346: ifnull +230 -> 576
    //   349: aload_0
    //   350: aload_0
    //   351: aload_2
    //   352: getfield 416	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   355: invokevirtual 560	java/util/Locale:getDisplayName	()Ljava/lang/String;
    //   358: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   361: putfield 562	com/taobao/munion/base/DefaultAppUtils:j	Ljava/lang/String;
    //   364: aload_0
    //   365: aload_0
    //   366: aload_2
    //   367: getfield 416	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   370: invokevirtual 419	java/util/Locale:toString	()Ljava/lang/String;
    //   373: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   376: putfield 564	com/taobao/munion/base/DefaultAppUtils:h	Ljava/lang/String;
    //   379: aload_0
    //   380: aload_0
    //   381: aload_2
    //   382: getfield 416	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   385: invokestatic 570	java/util/Calendar:getInstance	(Ljava/util/Locale;)Ljava/util/Calendar;
    //   388: invokevirtual 574	java/util/Calendar:getTimeZone	()Ljava/util/TimeZone;
    //   391: invokevirtual 579	java/util/TimeZone:getRawOffset	()I
    //   394: ldc_w 580
    //   397: idiv
    //   398: invokestatic 477	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   401: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   404: putfield 582	com/taobao/munion/base/DefaultAppUtils:k	Ljava/lang/String;
    //   407: new 584	java/io/FileReader
    //   410: dup
    //   411: ldc_w 586
    //   414: invokespecial 587	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   417: astore 5
    //   419: aload 5
    //   421: ifnull +71 -> 492
    //   424: new 589	java/io/BufferedReader
    //   427: dup
    //   428: aload 5
    //   430: sipush 1024
    //   433: invokespecial 592	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   436: astore_3
    //   437: aload_3
    //   438: astore_2
    //   439: aload_3
    //   440: invokevirtual 595	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   443: astore 4
    //   445: aload 4
    //   447: ifnull +27 -> 474
    //   450: aload_3
    //   451: astore_2
    //   452: aload_0
    //   453: aload_0
    //   454: aload 4
    //   456: aload 4
    //   458: bipush 58
    //   460: invokevirtual 599	java/lang/String:indexOf	(I)I
    //   463: iconst_1
    //   464: iadd
    //   465: invokevirtual 602	java/lang/String:substring	(I)Ljava/lang/String;
    //   468: invokespecial 458	com/taobao/munion/base/DefaultAppUtils:g	(Ljava/lang/String;)Ljava/lang/String;
    //   471: putfield 298	com/taobao/munion/base/DefaultAppUtils:t	Ljava/lang/String;
    //   474: aload_3
    //   475: ifnull +7 -> 482
    //   478: aload_3
    //   479: invokevirtual 605	java/io/BufferedReader:close	()V
    //   482: aload 5
    //   484: ifnull +8 -> 492
    //   487: aload 5
    //   489: invokevirtual 606	java/io/FileReader:close	()V
    //   492: aload_0
    //   493: aload_1
    //   494: invokestatic 611	com/taobao/munion/base/utdid/a:a	(Landroid/content/Context;)Ljava/lang/String;
    //   497: putfield 192	com/taobao/munion/base/DefaultAppUtils:g	Ljava/lang/String;
    //   500: return
    //   501: astore_2
    //   502: aload_2
    //   503: ldc_w 613
    //   506: iconst_0
    //   507: anewarray 4	java/lang/Object
    //   510: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   513: goto -461 -> 52
    //   516: astore_2
    //   517: aload_2
    //   518: ldc_w 615
    //   521: iconst_0
    //   522: anewarray 4	java/lang/Object
    //   525: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   528: goto -418 -> 110
    //   531: astore_2
    //   532: aload_2
    //   533: ldc_w 617
    //   536: iconst_0
    //   537: anewarray 4	java/lang/Object
    //   540: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   543: goto -368 -> 175
    //   546: astore_2
    //   547: aload_2
    //   548: ldc_w 619
    //   551: iconst_0
    //   552: anewarray 4	java/lang/Object
    //   555: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   558: goto -326 -> 232
    //   561: astore_2
    //   562: aload_2
    //   563: ldc_w 621
    //   566: iconst_0
    //   567: anewarray 4	java/lang/Object
    //   570: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   573: goto -243 -> 330
    //   576: aload_0
    //   577: getfield 150	com/taobao/munion/base/DefaultAppUtils:a	Lcom/taobao/munion/base/DefaultAppUtils$a;
    //   580: getfield 152	com/taobao/munion/base/DefaultAppUtils$a:b	Ljava/lang/String;
    //   583: astore_2
    //   584: aload_0
    //   585: aload_2
    //   586: putfield 564	com/taobao/munion/base/DefaultAppUtils:h	Ljava/lang/String;
    //   589: aload_0
    //   590: aload_2
    //   591: putfield 562	com/taobao/munion/base/DefaultAppUtils:j	Ljava/lang/String;
    //   594: goto -187 -> 407
    //   597: astore_2
    //   598: aload_2
    //   599: ldc_w 623
    //   602: iconst_0
    //   603: anewarray 4	java/lang/Object
    //   606: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   609: goto -202 -> 407
    //   612: astore 4
    //   614: aconst_null
    //   615: astore_3
    //   616: aload_3
    //   617: astore_2
    //   618: ldc_w 625
    //   621: iconst_1
    //   622: anewarray 4	java/lang/Object
    //   625: dup
    //   626: iconst_0
    //   627: aload 4
    //   629: aastore
    //   630: invokestatic 443	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   633: aload_3
    //   634: ifnull +7 -> 641
    //   637: aload_3
    //   638: invokevirtual 605	java/io/BufferedReader:close	()V
    //   641: aload 5
    //   643: ifnull -151 -> 492
    //   646: aload 5
    //   648: invokevirtual 606	java/io/FileReader:close	()V
    //   651: goto -159 -> 492
    //   654: astore_2
    //   655: ldc_w 627
    //   658: iconst_1
    //   659: anewarray 4	java/lang/Object
    //   662: dup
    //   663: iconst_0
    //   664: aload_2
    //   665: aastore
    //   666: invokestatic 443	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   669: goto -177 -> 492
    //   672: astore_3
    //   673: aconst_null
    //   674: astore_2
    //   675: aload_2
    //   676: ifnull +7 -> 683
    //   679: aload_2
    //   680: invokevirtual 605	java/io/BufferedReader:close	()V
    //   683: aload 5
    //   685: ifnull +8 -> 693
    //   688: aload 5
    //   690: invokevirtual 606	java/io/FileReader:close	()V
    //   693: aload_3
    //   694: athrow
    //   695: astore_1
    //   696: aload_1
    //   697: ldc_w 629
    //   700: iconst_0
    //   701: anewarray 4	java/lang/Object
    //   704: invokestatic 288	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   707: return
    //   708: astore_3
    //   709: goto -34 -> 675
    //   712: astore 4
    //   714: goto -98 -> 616
    //
    // Exception table:
    //   from	to	target	type
    //   25	52	501	java/lang/Exception
    //   70	110	516	java/lang/Exception
    //   110	175	531	java/lang/Exception
    //   199	232	546	java/lang/Exception
    //   232	330	561	java/lang/Exception
    //   330	338	597	java/lang/Exception
    //   342	407	597	java/lang/Exception
    //   576	594	597	java/lang/Exception
    //   424	437	612	java/io/IOException
    //   407	419	654	java/lang/Exception
    //   478	482	654	java/lang/Exception
    //   487	492	654	java/lang/Exception
    //   637	641	654	java/lang/Exception
    //   646	651	654	java/lang/Exception
    //   679	683	654	java/lang/Exception
    //   688	693	654	java/lang/Exception
    //   693	695	654	java/lang/Exception
    //   424	437	672	finally
    //   492	500	695	java/lang/Exception
    //   439	445	708	finally
    //   452	474	708	finally
    //   618	633	708	finally
    //   439	445	712	java/io/IOException
    //   452	474	712	java/io/IOException
  }

  public String j()
  {
    return this.d;
  }

  public void k()
  {
  }

  public boolean l()
  {
    return (this.u != null) && (this.u.getResources().getConfiguration().orientation == 1);
  }

  public String m()
  {
    return this.h;
  }

  public String n()
  {
    return this.j;
  }

  public String o()
  {
    return this.k;
  }

  public String p()
  {
    return this.n;
  }

  public String q()
  {
    return this.l;
  }

  public String r()
  {
    if ((!TextUtils.isEmpty(this.l)) && (!this.a.b.equals(this.l)))
      return this.l;
    if ((!TextUtils.isEmpty(this.n)) && (!this.a.b.equals(this.n)))
      return this.n;
    return Settings.Secure.getString(this.u.getContentResolver(), "android_id");
  }

  public String s()
  {
    return this.m;
  }

  public String t()
  {
    return this.o;
  }

  public float u()
  {
    return this.p;
  }

  public int v()
  {
    int i1 = 0;
    if (this.u != null);
    try
    {
      i1 = Settings.System.getInt(this.u.getContentResolver(), "screen_brightness");
      return i1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      Log.w("Get screen bright exception,info:" + localSettingNotFoundException.toString(), new Object[0]);
    }
    return 0;
  }

  // ERROR //
  public int w()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: iconst_m1
    //   3: istore 7
    //   5: new 584	java/io/FileReader
    //   8: dup
    //   9: ldc_w 661
    //   12: invokespecial 587	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_1
    //   16: new 589	java/io/BufferedReader
    //   19: dup
    //   20: aload_1
    //   21: sipush 1024
    //   24: invokespecial 592	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_2
    //   28: aload_2
    //   29: astore_3
    //   30: aload_1
    //   31: astore 4
    //   33: aload_2
    //   34: invokevirtual 595	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   37: astore 5
    //   39: iload 7
    //   41: istore 6
    //   43: aload 5
    //   45: ifnull +50 -> 95
    //   48: iload 7
    //   50: istore 6
    //   52: aload_2
    //   53: astore_3
    //   54: aload_1
    //   55: astore 4
    //   57: aload 5
    //   59: invokevirtual 664	java/lang/String:trim	()Ljava/lang/String;
    //   62: invokevirtual 667	java/lang/String:length	()I
    //   65: ifle +30 -> 95
    //   68: aload_2
    //   69: astore_3
    //   70: aload_1
    //   71: astore 4
    //   73: aload 5
    //   75: ldc_w 669
    //   78: invokevirtual 673	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   81: iconst_1
    //   82: aaload
    //   83: invokestatic 678	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   86: invokevirtual 681	java/lang/Integer:intValue	()I
    //   89: sipush 1024
    //   92: idiv
    //   93: istore 6
    //   95: aload_2
    //   96: ifnull +7 -> 103
    //   99: aload_2
    //   100: invokevirtual 605	java/io/BufferedReader:close	()V
    //   103: iload 6
    //   105: istore 7
    //   107: aload_1
    //   108: ifnull +11 -> 119
    //   111: aload_1
    //   112: invokevirtual 606	java/io/FileReader:close	()V
    //   115: iload 6
    //   117: istore 7
    //   119: iload 7
    //   121: ireturn
    //   122: astore_2
    //   123: new 250	java/lang/StringBuilder
    //   126: dup
    //   127: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   130: ldc_w 683
    //   133: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload_2
    //   137: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   140: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: iconst_0
    //   147: anewarray 4	java/lang/Object
    //   150: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   153: goto -50 -> 103
    //   156: astore_1
    //   157: new 250	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   164: ldc_w 686
    //   167: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: aload_1
    //   171: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   174: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: iconst_0
    //   181: anewarray 4	java/lang/Object
    //   184: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   187: iload 6
    //   189: ireturn
    //   190: astore_3
    //   191: aconst_null
    //   192: astore_1
    //   193: new 250	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   200: ldc_w 688
    //   203: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload_3
    //   207: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   210: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: iconst_0
    //   217: anewarray 4	java/lang/Object
    //   220: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   223: aload_1
    //   224: ifnull +7 -> 231
    //   227: aload_1
    //   228: invokevirtual 605	java/io/BufferedReader:close	()V
    //   231: aload_2
    //   232: ifnull -113 -> 119
    //   235: aload_2
    //   236: invokevirtual 606	java/io/FileReader:close	()V
    //   239: iconst_m1
    //   240: ireturn
    //   241: astore_1
    //   242: new 250	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   249: ldc_w 686
    //   252: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: aload_1
    //   256: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   259: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: iconst_0
    //   266: anewarray 4	java/lang/Object
    //   269: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   272: iconst_m1
    //   273: ireturn
    //   274: astore_1
    //   275: new 250	java/lang/StringBuilder
    //   278: dup
    //   279: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   282: ldc_w 683
    //   285: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: aload_1
    //   289: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   292: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   298: iconst_0
    //   299: anewarray 4	java/lang/Object
    //   302: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   305: goto -74 -> 231
    //   308: astore 5
    //   310: aconst_null
    //   311: astore_2
    //   312: aconst_null
    //   313: astore_1
    //   314: aload_2
    //   315: astore_3
    //   316: aload_1
    //   317: astore 4
    //   319: new 250	java/lang/StringBuilder
    //   322: dup
    //   323: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   326: ldc_w 688
    //   329: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: aload 5
    //   334: invokevirtual 689	java/lang/Exception:toString	()Ljava/lang/String;
    //   337: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: iconst_0
    //   344: anewarray 4	java/lang/Object
    //   347: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   350: aload_2
    //   351: ifnull +7 -> 358
    //   354: aload_2
    //   355: invokevirtual 605	java/io/BufferedReader:close	()V
    //   358: aload_1
    //   359: ifnull -240 -> 119
    //   362: aload_1
    //   363: invokevirtual 606	java/io/FileReader:close	()V
    //   366: iconst_m1
    //   367: ireturn
    //   368: astore_1
    //   369: new 250	java/lang/StringBuilder
    //   372: dup
    //   373: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   376: ldc_w 686
    //   379: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: aload_1
    //   383: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   386: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   392: iconst_0
    //   393: anewarray 4	java/lang/Object
    //   396: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   399: iconst_m1
    //   400: ireturn
    //   401: astore_2
    //   402: new 250	java/lang/StringBuilder
    //   405: dup
    //   406: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   409: ldc_w 683
    //   412: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload_2
    //   416: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   419: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   422: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   425: iconst_0
    //   426: anewarray 4	java/lang/Object
    //   429: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   432: goto -74 -> 358
    //   435: astore_2
    //   436: aconst_null
    //   437: astore_3
    //   438: aconst_null
    //   439: astore_1
    //   440: aload_3
    //   441: ifnull +7 -> 448
    //   444: aload_3
    //   445: invokevirtual 605	java/io/BufferedReader:close	()V
    //   448: aload_1
    //   449: ifnull +7 -> 456
    //   452: aload_1
    //   453: invokevirtual 606	java/io/FileReader:close	()V
    //   456: aload_2
    //   457: athrow
    //   458: astore_3
    //   459: new 250	java/lang/StringBuilder
    //   462: dup
    //   463: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   466: ldc_w 683
    //   469: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: aload_3
    //   473: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   476: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   479: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   482: iconst_0
    //   483: anewarray 4	java/lang/Object
    //   486: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   489: goto -41 -> 448
    //   492: astore_1
    //   493: new 250	java/lang/StringBuilder
    //   496: dup
    //   497: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   500: ldc_w 686
    //   503: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: aload_1
    //   507: invokevirtual 684	java/io/IOException:toString	()Ljava/lang/String;
    //   510: invokevirtual 257	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: invokevirtual 274	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   516: iconst_0
    //   517: anewarray 4	java/lang/Object
    //   520: invokestatic 330	com/taobao/munion/base/Log:w	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   523: goto -67 -> 456
    //   526: astore_2
    //   527: aconst_null
    //   528: astore_3
    //   529: goto -89 -> 440
    //   532: astore_2
    //   533: aload 4
    //   535: astore_1
    //   536: goto -96 -> 440
    //   539: astore_3
    //   540: aload_2
    //   541: astore 4
    //   543: aload_3
    //   544: astore_2
    //   545: aload_1
    //   546: astore_3
    //   547: aload 4
    //   549: astore_1
    //   550: goto -110 -> 440
    //   553: astore 5
    //   555: aconst_null
    //   556: astore_2
    //   557: goto -243 -> 314
    //   560: astore 5
    //   562: goto -248 -> 314
    //   565: astore_3
    //   566: aconst_null
    //   567: astore 4
    //   569: aload_1
    //   570: astore_2
    //   571: aload 4
    //   573: astore_1
    //   574: goto -381 -> 193
    //   577: astore_3
    //   578: aload_1
    //   579: astore 4
    //   581: aload_2
    //   582: astore_1
    //   583: aload 4
    //   585: astore_2
    //   586: goto -393 -> 193
    //
    // Exception table:
    //   from	to	target	type
    //   99	103	122	java/io/IOException
    //   111	115	156	java/io/IOException
    //   5	16	190	java/io/IOException
    //   235	239	241	java/io/IOException
    //   227	231	274	java/io/IOException
    //   5	16	308	java/lang/Exception
    //   362	366	368	java/io/IOException
    //   354	358	401	java/io/IOException
    //   5	16	435	finally
    //   444	448	458	java/io/IOException
    //   452	456	492	java/io/IOException
    //   16	28	526	finally
    //   33	39	532	finally
    //   57	68	532	finally
    //   73	95	532	finally
    //   319	350	532	finally
    //   193	223	539	finally
    //   16	28	553	java/lang/Exception
    //   33	39	560	java/lang/Exception
    //   57	68	560	java/lang/Exception
    //   73	95	560	java/lang/Exception
    //   16	28	565	java/io/IOException
    //   33	39	577	java/io/IOException
    //   57	68	577	java/io/IOException
    //   73	95	577	java/io/IOException
  }

  public int x()
  {
    int i1 = -1;
    if (this.u != null)
    {
      ActivityManager localActivityManager = (ActivityManager)this.u.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      i1 = new Long(localMemoryInfo.availMem / 1048576L).intValue();
    }
    return i1;
  }

  public int y()
  {
    return 0;
  }

  public int z()
  {
    return 0;
  }

  public static class a
  {
    public String a = "x";
    public String b = "unknown";
    public String c = "cell";
    public String d = "wifi";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.DefaultAppUtils
 * JD-Core Version:    0.6.2
 */