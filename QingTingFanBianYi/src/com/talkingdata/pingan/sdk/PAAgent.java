package com.talkingdata.pingan.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import java.io.Closeable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

public final class PAAgent
{
  private static final int A = 5000;
  private static final int B = 0;
  private static final int C = 1;
  private static final int D = 2;
  public static final boolean DEBUG = false;
  private static final int E = 3;
  private static final int F = 4;
  private static final int G = 5;
  private static volatile boolean H = false;
  private static String I;
  private static boolean J = false;
  private static Context K;
  private static String L;
  public static boolean LOG_ON = false;
  private static String M;
  private static long N = 0L;
  private static boolean O = false;
  private static boolean P = false;
  protected static final int a = 300000;
  protected static final int b = 6;
  protected static final int c = 7;
  protected static final int d = 8;
  static Long[][] e;
  static boolean f = false;
  static final String g = "pinganLog";
  static boolean h = false;
  static boolean i = false;
  private static final String j = "+V1.0.16";
  private static final String k = "Android+pingan+V1.0.16";
  private static final String l = "pinganpref.profile.key";
  private static final String m = "pinganpref.session.key";
  private static final String n = "pinganpref.lastactivity.key";
  private static final String o = "pinganpref.start.key";
  private static final String p = "pinganpref.init.key";
  private static final String q = "pinganpref.actstart.key";
  private static final String r = "pinganpref.end.key";
  private static final String s = "pinganpref.apps_send_time.key";
  private static final String t = "pinganpref.ip";
  private static final String u = "pingan_APP_ID";
  private static final String v = "pingan_CHANNEL_ID";
  private static final String w = "pref_longtime";
  private static final String x = "pref_shorttime";
  private static final long y = 30000L;
  private static final int z = 0;

  static
  {
    H = false;
    I = "TalkingData";
    J = false;
    O = false;
    P = false;
    o();
  }

  private static ak a(Context paramContext)
  {
    ak localak = new ak();
    if ((aa.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) || (aa.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")))
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
        if ((paramContext instanceof GsmCellLocation))
        {
          paramContext = (GsmCellLocation)paramContext;
          if (paramContext != null)
          {
            localak.d = paramContext.getCid();
            localak.e = paramContext.getLac();
            localak.c = "gsm";
            if (Build.VERSION.SDK_INT >= 9)
            {
              localak.c += paramContext.getPsc();
              return localak;
            }
          }
        }
        else if ((paramContext instanceof CdmaCellLocation))
        {
          paramContext = (CdmaCellLocation)paramContext;
          if (paramContext != null)
          {
            localak.d = paramContext.getBaseStationId();
            localak.e = paramContext.getNetworkId();
            localak.c = ("cdma:" + paramContext.getSystemId() + ':' + paramContext.getBaseStationLatitude() + ':' + paramContext.getBaseStationLongitude());
            return localak;
          }
        }
      }
      catch (Exception paramContext)
      {
      }
    return localak;
  }

  private static String a(Bundle paramBundle, String paramString)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
      if (((String)localIterator.next()).equalsIgnoreCase(paramString))
        return String.valueOf(paramBundle.get(paramString));
    return "";
  }

  private static String a(Throwable paramThrowable)
  {
    int i1 = 50;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramThrowable.toString());
    localStringBuilder.append("\r\n");
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    if (arrayOfStackTraceElement.length > 50);
    while (true)
    {
      int i2 = 0;
      while (i2 < i1)
      {
        localStringBuilder.append("\t" + arrayOfStackTraceElement[i2] + "\r\n");
        i2 += 1;
      }
      i1 = arrayOfStackTraceElement.length;
    }
    paramThrowable = paramThrowable.getCause();
    if (paramThrowable != null)
      a(localStringBuilder, arrayOfStackTraceElement, paramThrowable, 1);
    return localStringBuilder.toString();
  }

  static void a()
  {
    s();
    e = (Long[][])null;
  }

  public static void a(int paramInt, long paramLong)
  {
    Handler localHandler = d.a();
    Message localMessage = Message.obtain(localHandler, paramInt);
    localHandler.removeMessages(paramInt);
    localHandler.sendMessageDelayed(localMessage, paramLong);
  }

  static void a(long paramLong)
  {
    c("pinganpref.start.key", paramLong);
  }

  private static void a(long paramLong, String paramString)
  {
    long l1 = 0L;
    e(M);
    M = UUID.randomUUID().toString();
    long l2 = k();
    if (0L == l2)
      if ((K == null) || (!b.b(K)))
        break label86;
    label86: for (int i1 = 1; ; i1 = -1)
    {
      a(M);
      a(paramLong);
      e.a(M, paramLong, l1, i1);
      a(paramLong, paramString, "");
      return;
      l1 = paramLong - l2;
      break;
    }
  }

  private static void a(long paramLong, String paramString1, String paramString2)
  {
    if (paramString1 != null)
    {
      c(paramLong);
      b(paramString1);
      N = e.a(M, paramString1, paramLong, 0, paramString2, SystemClock.elapsedRealtime());
      d.a().removeMessages(7);
    }
    a(6, 0L);
  }

  private static void a(Activity paramActivity, String paramString, int paramInt)
  {
    ah.a(new y(paramInt, paramString, paramActivity));
  }

  static void a(Message paramMessage)
  {
    int i2 = 0;
    int i1 = 1;
    try
    {
      if (paramMessage.what != 8)
      {
        h = true;
        e.a(c());
        switch (paramMessage.what)
        {
        case 0:
        case 1:
        case 3:
        case 4:
        case 5:
        case 6:
        case 8:
        case 7:
        case 2:
        }
      }
      while (true)
      {
        e.b();
        if (i2 == 0)
          break label270;
        d.c();
        h = false;
        if (i1 != 0)
          break label270;
        q.a(new String[] { "Schedule next loop send." });
        a(8, 300000L);
        return;
        if ((h) || (i))
          break;
        a(8, 300000L);
        return;
        b(null);
        i2 = 1;
        i1 = 0;
        continue;
        b(paramMessage);
        i1 = 0;
        continue;
        c(paramMessage);
        i1 = 0;
        continue;
        d(paramMessage);
        i1 = 0;
        continue;
        paramMessage = (a)paramMessage.obj;
        e.a(paramMessage.c, paramMessage.d);
        d(paramMessage.c);
        i1 = 0;
        continue;
        q.a(new String[] { "Send data at resume" });
        i2 = 1;
        i1 = 0;
        continue;
        q.a(new String[] { "Send data at loop" });
        i2 = 1;
        i1 = 0;
        continue;
        q.a(new String[] { "Send data at pause" });
        i2 = 1;
        continue;
        i1 = 0;
      }
      label270: return;
    }
    catch (Throwable paramMessage)
    {
    }
  }

  static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable)
    {
    }
  }

  static void a(String paramString)
  {
    c("pinganpref.session.key", paramString);
  }

  private static void a(String paramString, long paramLong)
  {
    K.getSharedPreferences("pref_shorttime", 0).edit().putLong(paramString, paramLong).commit();
  }

  private static void a(String paramString1, String paramString2)
  {
    K.getSharedPreferences("pref_shorttime", 0).edit().putString(paramString1, paramString2).commit();
  }

  private static void a(StringBuilder paramStringBuilder, StackTraceElement[] paramArrayOfStackTraceElement, Throwable paramThrowable, int paramInt)
  {
    int i3 = 50;
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    int i1 = arrayOfStackTraceElement.length;
    int i2 = paramArrayOfStackTraceElement.length;
    i1 -= 1;
    i2 -= 1;
    while ((i1 >= 0) && (i2 >= 0) && (arrayOfStackTraceElement[i1].equals(paramArrayOfStackTraceElement[i2])))
    {
      i2 -= 1;
      i1 -= 1;
    }
    if (i1 > 50)
      i1 = i3;
    while (true)
    {
      paramStringBuilder.append("Caused by : " + paramThrowable + "\r\n");
      i2 = 0;
      while (i2 <= i1)
      {
        paramStringBuilder.append("\t" + arrayOfStackTraceElement[i2] + "\r\n");
        i2 += 1;
      }
    }
    if (paramInt >= 5);
    while (paramThrowable.getCause() == null)
      return;
    a(paramStringBuilder, arrayOfStackTraceElement, paramThrowable, paramInt + 1);
  }

  public static void a(boolean paramBoolean)
  {
    f = paramBoolean;
  }

  public static int b()
  {
    long l1 = d("pinganpref.apps_send_time.key", 0L);
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(6);
    i1 = localCalendar.get(11) + i1 * 100;
    if (Math.abs(l1 / 100L - i1 / 100) >= 7L)
      return 2;
    if (l1 != i1)
      return 1;
    return 0;
  }

  private static long b(String paramString, long paramLong)
  {
    return K.getSharedPreferences("pref_shorttime", 0).getLong(paramString, paramLong);
  }

  private static String b(String paramString1, String paramString2)
  {
    return K.getSharedPreferences("pref_shorttime", 0).getString(paramString1, paramString2);
  }

  static void b(long paramLong)
  {
    c("pinganpref.init.key", paramLong);
  }

  private static void b(Message paramMessage)
  {
    q.a(new String[] { "api on resume" });
    long l3 = System.currentTimeMillis();
    long l1 = h();
    long l2 = k();
    if (l2 > l1)
    {
      l1 = l2;
      if (paramMessage != null)
        break label74;
    }
    label74: for (paramMessage = null; ; paramMessage = (String)paramMessage.obj)
    {
      if (l3 - l1 <= 30000L)
        break label85;
      q.a(new String[] { "new launch..." });
      a(l3, paramMessage);
      return;
      break;
    }
    label85: q.a(new String[] { "session continue..." });
    a(l3, paramMessage, g());
  }

  static void b(String paramString)
  {
    a("pinganpref.lastactivity.key", paramString);
  }

  static void b(boolean paramBoolean)
  {
    if (paramBoolean);
    for (long l1 = 1L; ; l1 = 0L)
    {
      c("pinganpref.profile.key", l1);
      return;
    }
  }

  protected static Context c()
  {
    return K;
  }

  static void c(long paramLong)
  {
    a("pinganpref.actstart.key", paramLong);
  }

  private static void c(Message paramMessage)
  {
    q.a(new String[] { "api on pause" });
    long l1 = System.currentTimeMillis();
    if (N != -1L)
      e.a(N, SystemClock.elapsedRealtime());
    d(l1);
    d.a().removeMessages(8);
    a(7, 5000L);
  }

  static void c(String paramString)
  {
    c("pinganpref.ip", paramString);
  }

  private static void c(String paramString, long paramLong)
  {
    K.getSharedPreferences("pref_longtime", 0).edit().putLong(paramString, paramLong).commit();
  }

  private static void c(String paramString1, String paramString2)
  {
    K.getSharedPreferences("pref_longtime", 0).edit().putString(paramString1, paramString2).commit();
  }

  private static long d(String paramString, long paramLong)
  {
    return K.getSharedPreferences("pref_longtime", 0).getLong(paramString, paramLong);
  }

  public static String d()
  {
    return L;
  }

  private static String d(String paramString1, String paramString2)
  {
    return K.getSharedPreferences("pref_longtime", 0).getString(paramString1, paramString2);
  }

  static void d(long paramLong)
  {
    a("pinganpref.end.key", paramLong);
  }

  private static void d(Message paramMessage)
  {
    if (TextUtils.isEmpty(M))
    {
      q.a(new String[] { "Not Found Session Id" });
      return;
    }
    paramMessage = (a)paramMessage.obj;
    e.a(M, paramMessage.a, paramMessage.b, paramMessage.f, paramMessage.g);
  }

  private static void e(String paramString)
  {
    long l1 = h();
    long l2 = k();
    if (!TextUtils.isEmpty(paramString))
    {
      l2 -= l1;
      l1 = l2;
      if (l2 < 500L)
        l1 = -1000L;
      e.a(paramString, (int)l1 / 1000);
    }
  }

  static boolean e()
  {
    long l1 = d("pinganpref.profile.key", 1L);
    q.a(new String[] { "need Post Init:" + l1 });
    return l1 != 0L;
  }

  static String f()
  {
    return d("pinganpref.session.key", null);
  }

  static String g()
  {
    return b("pinganpref.lastactivity.key", "");
  }

  public static String getDeviceId(Context paramContext)
  {
    try
    {
      paramContext = a.b(paramContext);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  static long h()
  {
    return d("pinganpref.start.key", 0L);
  }

  static long i()
  {
    return d("pinganpref.init.key", 0L);
  }

  public static void init(Context paramContext)
  {
    if (!H)
      K = paramContext.getApplicationContext();
    while (true)
    {
      String str1;
      try
      {
        localObject = K.getPackageManager().getApplicationInfo(K.getPackageName(), 128).metaData;
        String str2 = a((Bundle)localObject, "pingan_APP_ID");
        str1 = a((Bundle)localObject, "pingan_CHANNEL_ID");
        if (TextUtils.isEmpty(str2))
        {
          if (!LOG_ON)
            break label190;
          Log.e("pinganLog", "pingan_APP_ID not found in AndroidManifest.xml!");
          return;
        }
        if (LOG_ON)
          Log.i("pinganLog", "pingan_APP_ID in AndroidManifest.xml is:" + str2 + ".");
        if (!LOG_ON)
          break label191;
        Log.i("pinganLog", "pingan_CHANNEL_ID in AndroidManifest.xml is:" + str1 + ".");
        break label191;
        init(paramContext, str2, (String)localObject);
        return;
      }
      catch (Throwable paramContext)
      {
        if (!LOG_ON)
          break label190;
      }
      Log.e("pinganLog", "Failed to load meta-data", paramContext);
      return;
      paramContext = d.a();
      paramContext.sendMessage(Message.obtain(paramContext, 0));
      label190: return;
      label191: Object localObject = str1;
      if (str1 == null)
        localObject = "TalkingData";
    }
  }

  // ERROR //
  public static void init(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 109	com/talkingdata/pingan/sdk/PAAgent:H	Z
    //   6: ifne +81 -> 87
    //   9: aload_0
    //   10: invokevirtual 575	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   13: putstatic 328	com/talkingdata/pingan/sdk/PAAgent:K	Landroid/content/Context;
    //   16: getstatic 107	com/talkingdata/pingan/sdk/PAAgent:LOG_ON	Z
    //   19: ifeq +39 -> 58
    //   22: ldc 52
    //   24: new 182	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 183	java/lang/StringBuilder:<init>	()V
    //   31: ldc_w 625
    //   34: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_1
    //   38: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc_w 627
    //   44: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_2
    //   48: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokestatic 609	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   57: pop
    //   58: aload_0
    //   59: ldc_w 629
    //   62: invokestatic 142	com/talkingdata/pingan/sdk/aa:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   65: ifne +26 -> 91
    //   68: getstatic 107	com/talkingdata/pingan/sdk/PAAgent:LOG_ON	Z
    //   71: ifeq +12 -> 83
    //   74: ldc 52
    //   76: ldc_w 631
    //   79: invokestatic 603	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: iconst_1
    //   84: putstatic 109	com/talkingdata/pingan/sdk/PAAgent:H	Z
    //   87: ldc 2
    //   89: monitorexit
    //   90: return
    //   91: aload_1
    //   92: putstatic 528	com/talkingdata/pingan/sdk/PAAgent:L	Ljava/lang/String;
    //   95: aload_2
    //   96: putstatic 113	com/talkingdata/pingan/sdk/PAAgent:I	Ljava/lang/String;
    //   99: iconst_1
    //   100: anewarray 244	java/lang/String
    //   103: dup
    //   104: iconst_0
    //   105: new 182	java/lang/StringBuilder
    //   108: dup
    //   109: invokespecial 183	java/lang/StringBuilder:<init>	()V
    //   112: ldc_w 633
    //   115: invokevirtual 187	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: getstatic 180	android/os/Build$VERSION:SDK_INT	I
    //   121: invokevirtual 193	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   124: invokevirtual 197	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: aastore
    //   128: invokestatic 393	com/talkingdata/pingan/sdk/q:a	([Ljava/lang/String;)V
    //   131: getstatic 180	android/os/Build$VERSION:SDK_INT	I
    //   134: bipush 14
    //   136: if_icmplt +77 -> 213
    //   139: aload_0
    //   140: instanceof 635
    //   143: istore 4
    //   145: iload 4
    //   147: ifeq +66 -> 213
    //   150: aload_0
    //   151: checkcast 635	android/app/Activity
    //   154: invokevirtual 639	android/app/Activity:getApplication	()Landroid/app/Application;
    //   157: astore_1
    //   158: ldc_w 641
    //   161: invokevirtual 646	java/lang/Class:getCanonicalName	()Ljava/lang/String;
    //   164: invokestatic 650	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   167: astore_2
    //   168: ldc_w 652
    //   171: invokestatic 650	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   174: astore_3
    //   175: aload_1
    //   176: invokevirtual 656	java/lang/Object:getClass	()Ljava/lang/Class;
    //   179: ldc_w 658
    //   182: iconst_1
    //   183: anewarray 643	java/lang/Class
    //   186: dup
    //   187: iconst_0
    //   188: aload_3
    //   189: aastore
    //   190: invokevirtual 662	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   193: aload_1
    //   194: iconst_1
    //   195: anewarray 4	java/lang/Object
    //   198: dup
    //   199: iconst_0
    //   200: aload_2
    //   201: invokevirtual 665	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   204: aastore
    //   205: invokevirtual 671	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   208: pop
    //   209: iconst_1
    //   210: putstatic 117	com/talkingdata/pingan/sdk/PAAgent:O	Z
    //   213: new 673	com/talkingdata/pingan/sdk/x
    //   216: dup
    //   217: aload_0
    //   218: invokespecial 675	com/talkingdata/pingan/sdk/x:<init>	(Landroid/content/Context;)V
    //   221: invokestatic 372	com/talkingdata/pingan/sdk/ah:a	(Ljava/lang/Runnable;)V
    //   224: goto -141 -> 83
    //   227: astore_0
    //   228: ldc 2
    //   230: monitorexit
    //   231: aload_0
    //   232: athrow
    //   233: astore_1
    //   234: aload_1
    //   235: invokevirtual 678	java/lang/Exception:printStackTrace	()V
    //   238: goto -25 -> 213
    //
    // Exception table:
    //   from	to	target	type
    //   3	58	227	finally
    //   58	83	227	finally
    //   83	87	227	finally
    //   91	145	227	finally
    //   150	213	227	finally
    //   213	224	227	finally
    //   234	238	227	finally
    //   150	213	233	java/lang/Exception
  }

  static long j()
  {
    return b("pinganpref.actstart.key", 0L);
  }

  static long k()
  {
    return b("pinganpref.end.key", 0L);
  }

  static String l()
  {
    return d("pinganpref.ip", null);
  }

  static h m()
  {
    if (K == null)
      return null;
    h localh = new h();
    localh.a = K.getPackageName();
    localh.b = i.d(K);
    localh.c = String.valueOf(i.c(K));
    localh.d = i();
    localh.e = "Android+pingan+V1.0.16";
    localh.f = I;
    localh.h = i.e(K);
    localh.i = i.f(K);
    return localh;
  }

  static u n()
  {
    Object localObject1 = null;
    if (K == null)
      return null;
    u localu = new u();
    localu.s = a.b(K);
    localu.a = j.c();
    localu.b = String.valueOf(j.d());
    Object localObject2 = ai.a(K);
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = ((List)localObject2).iterator();
    Location localLocation;
    if (localIterator.hasNext())
    {
      localLocation = (Location)localIterator.next();
      localStringBuffer.append(localLocation.getLatitude()).append(',').append(localLocation.getLongitude()).append(',').append(localLocation.getAltitude()).append(',').append(localLocation.getTime()).append(',').append(localLocation.getAccuracy()).append(',').append(localLocation.getBearing()).append(',').append(localLocation.getSpeed()).append(',').append((short)localLocation.getProvider().hashCode()).append(':');
      localObject2 = localLocation;
      if (localObject1 != null)
        if (localLocation.getTime() <= ((Location)localObject1).getTime())
          break label444;
    }
    label444: for (localObject2 = localLocation; ; localObject2 = localObject1)
    {
      localObject1 = localObject2;
      break;
      localObject2 = new f();
      if (localObject1 != null)
      {
        ((f)localObject2).b = ((Location)localObject1).getLatitude();
        ((f)localObject2).a = ((Location)localObject1).getLongitude();
      }
      localu.c = ((f)localObject2);
      localu.d = Build.CPU_ABI;
      localu.e = j.a(K);
      localu.f = j.g();
      localu.g = j.b(K);
      localu.h = j.f();
      localu.i = (TimeZone.getDefault().getRawOffset() / 1000 / 60 / 60);
      localu.j = ("Android+" + Build.VERSION.RELEASE);
      if (b.c(K));
      for (int i1 = 0; ; i1 = 1)
      {
        localu.k = i1;
        localu.l = b.d(K);
        localu.o = b.e(K);
        localu.n = b.f(K);
        localu.p = localStringBuffer.toString();
        localu.t = b.i(K);
        localObject1 = a(K);
        localu.u = ((ak)localObject1).c;
        localu.v = ((ak)localObject1).d;
        localu.w = ((ak)localObject1).e;
        return localu;
      }
    }
  }

  public static void o()
  {
    Thread.setDefaultUncaughtExceptionHandler(new b());
  }

  public static void onError(Context paramContext, Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return;
    ah.a(new w(paramThrowable));
  }

  private static void onError(Throwable paramThrowable, boolean paramBoolean)
  {
    if (!H)
      return;
    a locala = new a();
    locala.c = System.currentTimeMillis();
    locala.d = a(paramThrowable);
    while (paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause();
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramThrowable.getClass().getName()).append(":");
    int i1 = 0;
    while ((i1 < 3) && (i1 < arrayOfStackTraceElement.length))
    {
      localStringBuilder.append(arrayOfStackTraceElement[i1].toString()).append(":");
      i1 += 1;
    }
    locala.e = aa.b(localStringBuilder.toString());
    if (paramBoolean)
    {
      paramThrowable = Message.obtain(d.a(), 5, locala);
      d.a().sendMessage(paramThrowable);
      return;
    }
    e.a(c());
    e.a(locala.c, locala.d);
    d(locala.c);
    e.b();
  }

  public static void onEvent(Context paramContext, String paramString)
  {
    onEvent(paramContext, paramString, "");
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2)
  {
    onEvent(paramContext, paramString1, paramString2, null);
  }

  public static void onEvent(Context paramContext, String paramString1, String paramString2, Map paramMap)
  {
    ah.a(new v(paramString1, paramString2, paramMap));
  }

  public static void onPageEnd(Activity paramActivity, String paramString)
  {
    if ((paramActivity.getChangingConfigurations() & 0x80) == 128)
    {
      P = true;
      return;
    }
    a(paramActivity, paramString, 3);
  }

  public static void onPageStart(Activity paramActivity, String paramString)
  {
    if (P)
      return;
    P = false;
    a(paramActivity, paramString, 1);
  }

  public static void onPause(Activity paramActivity)
  {
    if (O)
      return;
    onPageEnd(paramActivity, paramActivity.getLocalClassName());
  }

  public static void onResume(Activity paramActivity)
  {
    if (O)
      return;
    onPageStart(paramActivity, paramActivity.getLocalClassName());
  }

  protected static void onResume(Activity paramActivity, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      q.a(new String[] { "APP ID not allow empty. Please check it." });
    while (O)
      return;
    if (!H)
    {
      init(paramActivity, paramString1, paramString2);
      if (!H)
      {
        q.a(new String[] { "SDK not initialized. TCAgent.onResume()" });
        return;
      }
    }
    onPageStart(paramActivity, paramActivity.getLocalClassName());
  }

  private static void s()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(6);
    c("pinganpref.apps_send_time.key", localCalendar.get(11) + i1 * 100);
  }

  public static void setReportUncaughtExceptions(boolean paramBoolean)
  {
    J = paramBoolean;
  }

  private static class a
  {
    String a;
    String b;
    long c;
    String d;
    String e;
    long f = System.currentTimeMillis();
    Map g = null;
  }

  private static class b
    implements Thread.UncaughtExceptionHandler
  {
    private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      if (PAAgent.r())
      {
        PAAgent.a(paramThrowable, false);
        Log.w("pinganLog", "UncaughtException in Thread " + paramThread.getName(), paramThrowable);
      }
      if (this.a != null)
        this.a.uncaughtException(paramThread, paramThrowable);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.PAAgent
 * JD-Core Version:    0.6.2
 */