package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
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

public final class j
  implements ao
{
  private static final long A = 30000L;
  private static final int B = 0;
  private static final int C = 5000;
  private static final int D = 0;
  private static final int E = 1;
  private static final int F = 2;
  private static final int G = 3;
  private static final int H = 4;
  private static final int I = 5;
  private static volatile boolean J = false;
  private static String K = "TalkingData";
  private static Context L;
  private static String M;
  private static String N;
  private static long O = 0L;
  private static boolean P = false;
  private static boolean Q = false;
  protected static final int a = 300000;
  protected static final int b = 6;
  protected static final int c = 7;
  protected static final int d = 8;
  static Long[][] e;
  static boolean f = false;
  static h g;
  static v h;
  static boolean i = false;
  static boolean j = false;
  private static final String k = "+V1.1.0";
  private static final String l = "Android+TD+V1.1.0";
  private static final String m = "TDpref.profile.key";
  private static final String n = "TDpref.session.key";
  private static final String o = "TDpref.lastactivity.key";
  private static final String p = "TDpref.start.key";
  private static final String q = "TDpref.init.key";
  private static final String r = "TDpref.actstart.key";
  private static final String s = "TDpref.end.key";
  private static final String t = "TDpref.apps_send_time.key";
  private static final String u = "TDpref.ip";
  private static final String v = "TDpref.last.sdk.check";
  private static final String w = "TD_APP_ID";
  private static final String x = "TD_CHANNEL_ID";
  private static final String y = "pref_longtime";
  private static final String z = "pref_shorttime";

  static
  {
    t();
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

  public static void a(int paramInt, long paramLong)
  {
    Handler localHandler = d.a();
    Message localMessage = Message.obtain(localHandler, paramInt);
    localHandler.removeMessages(paramInt);
    localHandler.sendMessageDelayed(localMessage, paramLong);
  }

  static void a(long paramLong)
  {
    aa.a(L, "pref_longtime", "TDpref.start.key", paramLong);
  }

  private static void a(long paramLong, String paramString)
  {
    long l1 = 0L;
    f(N);
    N = UUID.randomUUID().toString();
    long l2 = p();
    if (0L == l2)
      if ((L == null) || (!b.b(L)))
        break label85;
    label85: for (int i1 = 1; ; i1 = -1)
    {
      b(N);
      a(paramLong);
      e.a(N, paramLong, l1, i1);
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
      c(paramString1);
      O = e.a(N, paramString1, paramLong, 0, paramString2, SystemClock.elapsedRealtime());
      d.a().removeMessages(7);
    }
    a(6, 0L);
  }

  private void a(Activity paramActivity, String paramString)
  {
    if (Q)
      return;
    Q = false;
    a(paramActivity, paramString, 1);
  }

  private void a(Context paramContext, String paramString, int paramInt)
  {
    x.a(new al(this, paramInt, paramString, paramContext));
  }

  static void a(Message paramMessage)
  {
    int i2 = 0;
    int i1 = 1;
    try
    {
      if (paramMessage.what != 8)
      {
        i = true;
        e.a(h());
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
          break label218;
        d.c();
        i = false;
        if (i1 != 0)
          break label218;
        a(8, 300000L);
        return;
        if ((i) || (j))
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
        i2 = 1;
        i1 = 0;
        continue;
        i2 = 1;
        i1 = 0;
        continue;
        i2 = 1;
        continue;
        i1 = 0;
      }
      label218: return;
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

  static void b(long paramLong)
  {
    aa.a(L, "pref_longtime", "TDpref.init.key", paramLong);
  }

  private void b(Activity paramActivity, String paramString)
  {
    if ((paramActivity.getChangingConfigurations() & 0x80) == 128)
    {
      Q = true;
      return;
    }
    a(paramActivity, paramString, 3);
  }

  private static void b(Message paramMessage)
  {
    long l3 = System.currentTimeMillis();
    long l1 = m();
    long l2 = p();
    if (l2 > l1)
    {
      l1 = l2;
      if (paramMessage != null)
        break label48;
    }
    label48: for (paramMessage = null; ; paramMessage = (String)paramMessage.obj)
    {
      if (l3 - l1 <= 30000L)
        break label59;
      a(l3, paramMessage);
      return;
      break;
    }
    label59: a(l3, paramMessage, l());
  }

  static void b(String paramString)
  {
    aa.a(L, "pref_longtime", "TDpref.session.key", paramString);
  }

  private static void b(Throwable paramThrowable, boolean paramBoolean)
  {
    if (!J)
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
    locala.e = x.b(localStringBuilder.toString());
    if (paramBoolean)
    {
      paramThrowable = Message.obtain(d.a(), 5, locala);
      d.a().sendMessage(paramThrowable);
      return;
    }
    e.a(h());
    e.a(locala.c, locala.d);
    d(locala.c);
    e.b();
  }

  static void b(boolean paramBoolean)
  {
    Context localContext = L;
    if (paramBoolean);
    for (long l1 = 1L; ; l1 = 0L)
    {
      aa.a(localContext, "pref_longtime", "TDpref.profile.key", l1);
      return;
    }
  }

  private static af c(Context paramContext)
  {
    af localaf = new af();
    if ((x.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) || (x.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")))
      try
      {
        paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
        if ((paramContext instanceof GsmCellLocation))
        {
          paramContext = (GsmCellLocation)paramContext;
          if (paramContext != null)
          {
            localaf.d = paramContext.getCid();
            localaf.e = paramContext.getLac();
            localaf.c = "gsm";
            if (Build.VERSION.SDK_INT >= 9)
            {
              localaf.c += paramContext.getPsc();
              return localaf;
            }
          }
        }
        else if ((paramContext instanceof CdmaCellLocation))
        {
          paramContext = (CdmaCellLocation)paramContext;
          if (paramContext != null)
          {
            localaf.d = paramContext.getBaseStationId();
            localaf.e = paramContext.getNetworkId();
            localaf.c = ("cdma:" + paramContext.getSystemId() + ':' + paramContext.getBaseStationLatitude() + ':' + paramContext.getBaseStationLongitude());
            return localaf;
          }
        }
      }
      catch (Exception paramContext)
      {
      }
    return localaf;
  }

  static void c(long paramLong)
  {
    aa.a(L, "pref_shorttime", "TDpref.actstart.key", paramLong);
  }

  private static void c(Message paramMessage)
  {
    long l1 = System.currentTimeMillis();
    if (O != -1L)
      e.a(O, SystemClock.elapsedRealtime());
    d(l1);
    d.a().removeMessages(8);
    a(7, 5000L);
  }

  static void c(String paramString)
  {
    aa.a(L, "pref_shorttime", "TDpref.lastactivity.key", paramString);
  }

  static void d(long paramLong)
  {
    aa.a(L, "pref_shorttime", "TDpref.end.key", paramLong);
  }

  private static void d(Message paramMessage)
  {
    if (TextUtils.isEmpty(N))
      return;
    paramMessage = (a)paramMessage.obj;
    e.a(N, paramMessage.a, paramMessage.b, paramMessage.f, paramMessage.g);
  }

  static void d(String paramString)
  {
    aa.a(L, "pref_longtime", "TDpref.ip", paramString);
  }

  static void f()
  {
    w();
    e = (Long[][])null;
  }

  private static void f(String paramString)
  {
    long l1 = m();
    long l2 = p();
    if (!TextUtils.isEmpty(paramString))
    {
      l2 -= l1;
      l1 = l2;
      if (l2 < 500L)
        l1 = -1000L;
      e.a(paramString, (int)l1 / 1000);
    }
  }

  public static int g()
  {
    long l1 = aa.b(L, "pref_longtime", "TDpref.apps_send_time.key", 0L);
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(6);
    i1 = localCalendar.get(11) + i1 * 100;
    if (Math.abs(l1 / 100L - i1 / 100) >= 7L)
      return 2;
    if (l1 != i1)
      return 1;
    return 0;
  }

  protected static Context h()
  {
    return L;
  }

  public static String i()
  {
    return M;
  }

  static boolean j()
  {
    return aa.b(L, "pref_longtime", "TDpref.profile.key", 1L) != 0L;
  }

  static String k()
  {
    return aa.b(L, "pref_longtime", "TDpref.session.key", null);
  }

  static String l()
  {
    return aa.b(L, "pref_shorttime", "TDpref.lastactivity.key", "");
  }

  static long m()
  {
    return aa.b(L, "pref_longtime", "TDpref.start.key", 0L);
  }

  static long n()
  {
    return aa.b(L, "pref_longtime", "TDpref.init.key", 0L);
  }

  static long o()
  {
    return aa.b(L, "pref_shorttime", "TDpref.actstart.key", 0L);
  }

  static long p()
  {
    return aa.b(L, "pref_shorttime", "TDpref.end.key", 0L);
  }

  static String q()
  {
    return aa.b(L, "pref_longtime", "TDpref.ip", null);
  }

  static h r()
  {
    if (g != null)
      return g;
    if (L == null)
      return null;
    g = new h();
    g.a = L.getPackageName();
    g.b = i.d(L);
    g.c = String.valueOf(i.c(L));
    g.d = n();
    g.e = "Android+TD+V1.1.0";
    g.f = K;
    g.h = i.e(L);
    g.i = i.f(L);
    return g;
  }

  static v s()
  {
    if (h == null)
    {
      if (L == null)
        return null;
      h = new v();
      h.s = a.i(L);
      h.a = k.c();
      h.b = String.valueOf(k.d());
      h.d = Build.CPU_ABI;
      h.e = k.a(L);
      h.f = k.g();
      h.g = k.b(L);
      h.h = k.f();
      h.i = (TimeZone.getDefault().getRawOffset() / 1000 / 60 / 60);
      h.j = ("Android+" + Build.VERSION.RELEASE);
    }
    x();
    return h;
  }

  public static void t()
  {
    Thread.setDefaultUncaughtExceptionHandler(new b());
  }

  private static void w()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(6);
    int i2 = localCalendar.get(11);
    aa.a(L, "pref_longtime", "TDpref.apps_send_time.key", i2 + i1 * 100);
  }

  private static void x()
  {
    Object localObject2 = ae.a(L);
    Object localObject1 = null;
    Iterator localIterator = ((List)localObject2).iterator();
    Location localLocation;
    if (localIterator.hasNext())
    {
      localLocation = (Location)localIterator.next();
      localObject2 = localLocation;
      if (localObject1 != null)
        if (localLocation.getTime() <= ((Location)localObject1).getTime())
          break label221;
    }
    label221: for (localObject2 = localLocation; ; localObject2 = localObject1)
    {
      localObject1 = localObject2;
      break;
      localObject2 = new f();
      if (localObject1 != null)
      {
        ((f)localObject2).b = ((Location)localObject1).getLatitude();
        ((f)localObject2).a = ((Location)localObject1).getLongitude();
      }
      h.c = ((f)localObject2);
      localObject1 = h;
      if (b.c(L));
      for (int i1 = 0; ; i1 = 1)
      {
        ((v)localObject1).k = i1;
        h.l = b.d(L);
        h.o = b.e(L);
        h.n = b.f(L);
        h.p = ae.b(L);
        h.t = b.i(L);
        localObject1 = c(L);
        h.u = ((af)localObject1).c;
        h.v = ((af)localObject1).d;
        h.w = ((af)localObject1).e;
        return;
      }
    }
  }

  public String a()
  {
    return "+V1.1.0";
  }

  public void a(Activity paramActivity)
  {
    if (P)
      return;
    a(paramActivity, paramActivity.getLocalClassName());
  }

  public void a(Activity paramActivity, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      if (!TCAgent.LOG_ON);
    do
    {
      Log.e("TDLog", "APP ID not allow empty. Please check it.");
      do
        return;
      while (P);
      if (J)
        break;
      a(paramActivity, paramString1, paramString2);
      if (J)
        break;
    }
    while (!TCAgent.LOG_ON);
    Log.e("TDLog", "SDK initialize failed.");
    return;
    a(paramActivity, paramActivity.getLocalClassName());
  }

  public void a(Context paramContext)
  {
    while (true)
    {
      String str1;
      try
      {
        if (!J)
        {
          L = paramContext.getApplicationContext();
          try
          {
            localObject = L.getPackageManager().getApplicationInfo(L.getPackageName(), 128).metaData;
            String str2 = a((Bundle)localObject, "TD_APP_ID");
            str1 = a((Bundle)localObject, "TD_CHANNEL_ID");
            if (TextUtils.isEmpty(str2))
            {
              if (TCAgent.LOG_ON)
                Log.e("TDLog", "TD_APP_ID not found in AndroidManifest.xml!");
              return;
            }
            if (!TCAgent.LOG_ON)
              break label209;
            Log.i("TDLog", "TD_APP_ID in AndroidManifest.xml is:" + str2 + ".");
            Log.i("TDLog", "TD_CHANNEL_ID in AndroidManifest.xml is:" + str1 + ".");
            break label209;
            a(paramContext, str2, (String)localObject);
            continue;
          }
          catch (Throwable paramContext)
          {
            if (!TCAgent.LOG_ON)
              continue;
            Log.e("TDLog", "Failed to load meta-data", paramContext);
            continue;
          }
        }
      }
      finally
      {
      }
      paramContext = d.a();
      paramContext.sendMessage(Message.obtain(paramContext, 0));
      continue;
      label209: Object localObject = str1;
      if (str1 == null)
        localObject = "TalkingData";
    }
  }

  public void a(Context paramContext, String paramString)
  {
    if (paramContext != null)
      L = paramContext;
  }

  // ERROR //
  public void a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 110	com/tendcloud/tenddata/j:J	Z
    //   5: ifne +94 -> 99
    //   8: aload_1
    //   9: invokevirtual 728	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   12: putstatic 225	com/tendcloud/tenddata/j:L	Landroid/content/Context;
    //   15: getstatic 711	com/tendcloud/tenddata/TCAgent:LOG_ON	Z
    //   18: ifeq +51 -> 69
    //   21: ldc_w 713
    //   24: new 170	java/lang/StringBuilder
    //   27: dup
    //   28: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   31: ldc_w 764
    //   34: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: aload_2
    //   38: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: ldc_w 766
    //   44: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_3
    //   48: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: ldc_w 768
    //   54: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc 64
    //   59: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokestatic 754	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   68: pop
    //   69: aload_1
    //   70: ldc_w 770
    //   73: invokestatic 406	com/tendcloud/tenddata/x:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   76: ifne +26 -> 102
    //   79: getstatic 711	com/tendcloud/tenddata/TCAgent:LOG_ON	Z
    //   82: ifeq +13 -> 95
    //   85: ldc_w 713
    //   88: ldc_w 772
    //   91: invokestatic 720	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   94: pop
    //   95: iconst_1
    //   96: putstatic 110	com/tendcloud/tenddata/j:J	Z
    //   99: aload_0
    //   100: monitorexit
    //   101: return
    //   102: aload_2
    //   103: putstatic 531	com/tendcloud/tenddata/j:M	Ljava/lang/String;
    //   106: aload_3
    //   107: putstatic 114	com/tendcloud/tenddata/j:K	Ljava/lang/String;
    //   110: getstatic 442	android/os/Build$VERSION:SDK_INT	I
    //   113: istore 4
    //   115: iload 4
    //   117: bipush 14
    //   119: if_icmplt +73 -> 192
    //   122: aconst_null
    //   123: astore_2
    //   124: aload_1
    //   125: instanceof 350
    //   128: ifeq +84 -> 212
    //   131: aload_1
    //   132: checkcast 350	android/app/Activity
    //   135: invokevirtual 776	android/app/Activity:getApplication	()Landroid/app/Application;
    //   138: astore_2
    //   139: aload_2
    //   140: ifnull +52 -> 192
    //   143: ldc_w 778
    //   146: invokestatic 782	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   149: astore_3
    //   150: aload_2
    //   151: invokevirtual 375	java/lang/Object:getClass	()Ljava/lang/Class;
    //   154: ldc_w 784
    //   157: iconst_1
    //   158: anewarray 377	java/lang/Class
    //   161: dup
    //   162: iconst_0
    //   163: aload_3
    //   164: aastore
    //   165: invokevirtual 788	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   168: aload_2
    //   169: iconst_1
    //   170: anewarray 4	java/lang/Object
    //   173: dup
    //   174: iconst_0
    //   175: new 790	com/tendcloud/tenddata/l
    //   178: dup
    //   179: aload_0
    //   180: invokespecial 793	com/tendcloud/tenddata/l:<init>	(Lcom/tendcloud/tenddata/j;)V
    //   183: aastore
    //   184: invokevirtual 799	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   187: pop
    //   188: iconst_1
    //   189: putstatic 116	com/tendcloud/tenddata/j:P	Z
    //   192: new 801	com/tendcloud/tenddata/am
    //   195: dup
    //   196: aload_0
    //   197: aload_1
    //   198: invokespecial 804	com/tendcloud/tenddata/am:<init>	(Lcom/tendcloud/tenddata/j;Landroid/content/Context;)V
    //   201: invokestatic 293	com/tendcloud/tenddata/x:a	(Ljava/lang/Runnable;)V
    //   204: goto -109 -> 95
    //   207: astore_1
    //   208: aload_0
    //   209: monitorexit
    //   210: aload_1
    //   211: athrow
    //   212: aload_1
    //   213: instanceof 806
    //   216: ifeq -77 -> 139
    //   219: aload_1
    //   220: checkcast 806	android/app/Application
    //   223: astore_2
    //   224: goto -85 -> 139
    //   227: astore_2
    //   228: aload_2
    //   229: invokevirtual 809	java/lang/Exception:printStackTrace	()V
    //   232: goto -40 -> 192
    //
    // Exception table:
    //   from	to	target	type
    //   2	69	207	finally
    //   69	95	207	finally
    //   95	99	207	finally
    //   102	115	207	finally
    //   124	139	207	finally
    //   143	192	207	finally
    //   192	204	207	finally
    //   212	224	207	finally
    //   228	232	207	finally
    //   124	139	227	java/lang/Exception
    //   143	192	227	java/lang/Exception
    //   212	224	227	java/lang/Exception
  }

  public void a(Context paramContext, String paramString1, String paramString2, Map paramMap)
  {
    x.a(new ak(this, paramString1, paramString2, paramMap));
  }

  public void a(Context paramContext, Throwable paramThrowable)
  {
    if (paramThrowable == null)
      return;
    x.a(new aj(this, paramThrowable));
  }

  public void a(String paramString)
  {
    aa.a(L, "pref_longtime", "TDpref.last.sdk.check", System.currentTimeMillis());
  }

  public String b(Context paramContext)
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

  public void b(Activity paramActivity)
  {
    if (P)
      return;
    b(paramActivity, paramActivity.getLocalClassName());
  }

  public void b(Context paramContext, String paramString)
  {
    a(paramContext, paramString, 1);
  }

  public boolean b()
  {
    long l1 = aa.b(L, "pref_longtime", "TDpref.last.sdk.check", System.currentTimeMillis());
    l1 = Math.abs((System.currentTimeMillis() - l1) / 86400000L);
    double d1;
    if (l1 <= 7L)
      d1 = Math.random();
    return l1 * d1 > 2.0D;
  }

  public String c()
  {
    return "https://u.talkingdata.net/ota/a/TD/android/ver";
  }

  public void c(Context paramContext, String paramString)
  {
    a(paramContext, paramString, 3);
  }

  public void c(boolean paramBoolean)
  {
    TCAgent.d = paramBoolean;
  }

  public String d()
  {
    return "https://u.talkingdata.net/ota/a/TD/android/sdk.zip";
  }

  public void e()
  {
  }

  private static final class a
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
      if (TCAgent.d)
      {
        j.a(paramThrowable, false);
        Log.w("TDLog", "UncaughtException in Thread " + paramThread.getName(), paramThrowable);
      }
      if (this.a != null)
        this.a.uncaughtException(paramThread, paramThrowable);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.j
 * JD-Core Version:    0.6.2
 */