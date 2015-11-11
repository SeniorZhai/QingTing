package com.taobao.munion.base.download;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.m;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class f
{
  protected static final String a = "Unknown";
  static final int b = 0;
  static final int c = 1;
  static final int d = 1;
  static final int e = 2;
  private static final String f = f.class.getName();
  private static final String h = "2G/3G";
  private static final String i = "Wi-Fi";
  private m g;
  private SparseArray<c> j;
  private Map<c.a, Messenger> k;
  private i l;

  public f(SparseArray<c> paramSparseArray, Map<c.a, Messenger> paramMap, i parami)
  {
    this.j = paramSparseArray;
    this.k = paramMap;
    this.l = parami;
  }

  private d a(String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1))
      try
      {
        paramString1 = (d)Class.forName(paramString1).getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString2 });
        return paramString1;
      }
      catch (Exception paramString1)
      {
        Log.e(paramString1, "", new Object[0]);
        return null;
      }
    return new d(paramString2);
  }

  public static File a(String paramString, Context paramContext, boolean[] paramArrayOfBoolean)
    throws IOException
  {
    if (b())
    {
      String str = Environment.getExternalStorageDirectory().getCanonicalPath();
      paramString = new File(str + "/download/.taobao" + paramString);
      paramString.mkdirs();
      if (paramString.exists())
      {
        paramArrayOfBoolean[0] = true;
        return paramString;
      }
    }
    paramString = paramContext.getCacheDir().getAbsolutePath();
    new File(paramString).mkdir();
    a(paramString, 505, -1, -1);
    paramString = paramString + "/tbdownload";
    new File(paramString).mkdir();
    a(paramString, 505, -1, -1);
    paramString = new File(paramString);
    paramArrayOfBoolean[0] = false;
    return paramString;
  }

  public static String a()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(localDate);
  }

  public static String a(File paramFile)
  {
    byte[] arrayOfByte = new byte[1024];
    MessageDigest localMessageDigest;
    try
    {
      if (!paramFile.isFile())
        return "";
      localMessageDigest = MessageDigest.getInstance("MD5");
      paramFile = new FileInputStream(paramFile);
      while (true)
      {
        int m = paramFile.read(arrayOfByte, 0, 1024);
        if (m == -1)
          break;
        localMessageDigest.update(arrayOfByte, 0, m);
      }
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
      return null;
    }
    paramFile.close();
    return String.format("%1$032x", new Object[] { new BigInteger(1, localMessageDigest.digest()) });
  }

  public static String a(String paramString)
  {
    int m = 0;
    if (paramString == null)
      return null;
    try
    {
      Object localObject1 = paramString.getBytes();
      Object localObject2 = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject2).reset();
      ((MessageDigest)localObject2).update((byte[])localObject1);
      localObject1 = ((MessageDigest)localObject2).digest();
      localObject2 = new StringBuffer();
      while (m < localObject1.length)
      {
        ((StringBuffer)localObject2).append(String.format("%02X", new Object[] { Byte.valueOf(localObject1[m]) }));
        m += 1;
      }
      localObject1 = ((StringBuffer)localObject2).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return paramString.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
  }

  protected static boolean a(String paramString, int paramInt)
  {
    int m = 432;
    if ((paramInt & 0x1) != 0)
      m = 436;
    int n = m;
    if ((paramInt & 0x2) != 0)
      n = m | 0x2;
    return a(paramString, n, -1, -1);
  }

  protected static boolean a(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      Class.forName("android.os.FileUtils").getMethod("setPermissions", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE }).invoke(null, new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(-1), Integer.valueOf(-1) });
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      Log.e("error when set permissions:", new Object[] { paramString });
      return false;
    }
    catch (NoSuchMethodException paramString)
    {
      while (true)
        Log.e("error when set permissions:", new Object[] { paramString });
    }
    catch (IllegalArgumentException paramString)
    {
      while (true)
        Log.e("error when set permissions:", new Object[] { paramString });
    }
    catch (IllegalAccessException paramString)
    {
      while (true)
        Log.e("error when set permissions:", new Object[] { paramString });
    }
    catch (InvocationTargetException paramString)
    {
      while (true)
        Log.e("error when set permissions:", new Object[] { paramString });
    }
  }

  private static boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static boolean b(Context paramContext)
  {
    try
    {
      int m = paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName());
      boolean bool = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo().isConnectedOrConnecting();
      return (m == 0) && (bool);
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  public static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return "unknow";
  }

  public static String[] d(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      if (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      }
      paramContext = paramContext.getNetworkInfo(0);
      if (paramContext.getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = "2G/3G";
        arrayOfString[1] = paramContext.getSubtypeName();
        return arrayOfString;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return arrayOfString;
  }

  public static boolean e(Context paramContext)
  {
    return "Wi-Fi".equals(d(paramContext)[0]);
  }

  int a(c.a parama)
  {
    return Math.abs((int)((parama.c.hashCode() >> 2) + (parama.d.hashCode() >> 3) + System.currentTimeMillis()));
  }

  a a(Context paramContext, c.a parama, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getApplicationContext();
    a locala = new a(paramContext);
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(), 134217728);
    locala.c(k.e).a(17301633).a(localPendingIntent).a(System.currentTimeMillis());
    locala.b(k.f + parama.c).a(paramInt2 + "%").a(100, paramInt2, false);
    if (parama.m)
    {
      locala.b();
      a(paramContext, locala, paramInt1, 2);
    }
    locala.a(true).b(false);
    return locala;
  }

  final void a(long paramLong1, long paramLong2, long paramLong3, c.a parama)
  {
    String[] arrayOfString = parama.i;
    if (arrayOfString != null)
    {
      Log.i("sendFailedReport.", new Object[0]);
      int n = arrayOfString.length;
      int m = 0;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        ((d)localObject).a(new Long[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) });
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  void a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getApplicationContext();
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    c localc = (c)this.j.get(paramInt);
    localc.b.b();
    a(paramContext, localc.b, paramInt, 1);
    localc.b.b(k.g + localc.e.c).a(false).b(true);
    localNotificationManager.notify(paramInt, localc.b.a());
  }

  void a(Context paramContext, a parama, int paramInt1, int paramInt2)
  {
    PendingIntent localPendingIntent;
    if (Build.VERSION.SDK_INT >= 16)
    {
      localPendingIntent = j.b(paramContext, j.a(paramInt1, "continue"));
      paramContext = j.b(paramContext, j.a(paramInt1, "cancel"));
      switch (paramInt2)
      {
      default:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      parama.a(17301560, k.c, paramContext);
      return;
      parama.a(17301540, k.a, localPendingIntent);
      continue;
      parama.a(17301539, k.b, localPendingIntent);
    }
  }

  public void a(m paramm)
  {
    this.g = paramm;
  }

  boolean a(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null)
      return false;
    paramContext = paramContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.importance == 100) && (localRunningAppProcessInfo.processName.equals(paramContext)))
        return true;
    }
    return false;
  }

  // ERROR //
  boolean a(DownloadingService paramDownloadingService, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 574	com/taobao/munion/base/download/DownloadingService:getApplicationContext	()Landroid/content/Context;
    //   4: astore_3
    //   5: aload_2
    //   6: invokevirtual 578	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   9: ldc_w 580
    //   12: invokevirtual 585	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnonnull +5 -> 22
    //   20: iconst_0
    //   21: ireturn
    //   22: aload_2
    //   23: ldc_w 587
    //   26: invokevirtual 591	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   29: astore_2
    //   30: aload_2
    //   31: iconst_0
    //   32: aaload
    //   33: invokestatic 595	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   36: istore 6
    //   38: aload_2
    //   39: iconst_1
    //   40: aaload
    //   41: invokevirtual 598	java/lang/String:trim	()Ljava/lang/String;
    //   44: astore 4
    //   46: iload 6
    //   48: ifeq +257 -> 305
    //   51: aload 4
    //   53: invokestatic 73	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   56: ifne +249 -> 305
    //   59: aload_0
    //   60: getfield 58	com/taobao/munion/base/download/f:j	Landroid/util/SparseArray;
    //   63: iload 6
    //   65: invokevirtual 601	android/util/SparseArray:indexOfKey	(I)I
    //   68: iflt +237 -> 305
    //   71: aload_0
    //   72: getfield 58	com/taobao/munion/base/download/f:j	Landroid/util/SparseArray;
    //   75: iload 6
    //   77: invokevirtual 494	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   80: checkcast 12	com/taobao/munion/base/download/f$c
    //   83: astore_2
    //   84: aload_2
    //   85: getfield 604	com/taobao/munion/base/download/f$c:a	Lcom/taobao/munion/base/download/DownloadingService$b;
    //   88: astore 5
    //   90: aload_2
    //   91: getfield 607	com/taobao/munion/base/download/f$c:f	[J
    //   94: iconst_0
    //   95: laload
    //   96: lstore 7
    //   98: aload_2
    //   99: getfield 607	com/taobao/munion/base/download/f$c:f	[J
    //   102: iconst_1
    //   103: laload
    //   104: lstore 9
    //   106: aload_2
    //   107: getfield 607	com/taobao/munion/base/download/f$c:f	[J
    //   110: iconst_2
    //   111: laload
    //   112: lstore 11
    //   114: ldc_w 516
    //   117: aload 4
    //   119: invokevirtual 301	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   122: ifeq +315 -> 437
    //   125: aload 5
    //   127: ifnonnull +180 -> 307
    //   130: getstatic 51	com/taobao/munion/base/download/f:f	Ljava/lang/String;
    //   133: iconst_1
    //   134: anewarray 4	java/lang/Object
    //   137: dup
    //   138: iconst_0
    //   139: ldc_w 609
    //   142: aastore
    //   143: invokestatic 611	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   146: aload_3
    //   147: invokestatic 613	com/taobao/munion/base/download/f:b	(Landroid/content/Context;)Z
    //   150: ifne +16 -> 166
    //   153: aload_3
    //   154: getstatic 614	com/taobao/munion/base/download/k:d	Ljava/lang/String;
    //   157: iconst_1
    //   158: invokestatic 620	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   161: invokevirtual 623	android/widget/Toast:show	()V
    //   164: iconst_0
    //   165: ireturn
    //   166: aload_1
    //   167: invokevirtual 627	java/lang/Object:getClass	()Ljava/lang/Class;
    //   170: pop
    //   171: new 629	com/taobao/munion/base/download/DownloadingService$b
    //   174: dup
    //   175: aload_1
    //   176: aload_3
    //   177: aload_2
    //   178: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   181: iload 6
    //   183: aload_2
    //   184: getfield 631	com/taobao/munion/base/download/f$c:d	I
    //   187: aload_1
    //   188: getfield 635	com/taobao/munion/base/download/DownloadingService:r	Lcom/taobao/munion/base/download/DownloadingService$a;
    //   191: invokespecial 638	com/taobao/munion/base/download/DownloadingService$b:<init>	(Lcom/taobao/munion/base/download/DownloadingService;Landroid/content/Context;Lcom/taobao/munion/base/download/c$a;IILcom/taobao/munion/base/download/DownloadingService$a;)V
    //   194: astore_1
    //   195: aload_2
    //   196: aload_1
    //   197: putfield 604	com/taobao/munion/base/download/f$c:a	Lcom/taobao/munion/base/download/DownloadingService$b;
    //   200: aload_1
    //   201: invokevirtual 641	com/taobao/munion/base/download/DownloadingService$b:start	()V
    //   204: aload_0
    //   205: lload 7
    //   207: lload 9
    //   209: lload 11
    //   211: aload_2
    //   212: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   215: invokevirtual 643	com/taobao/munion/base/download/f:c	(JJJLcom/taobao/munion/base/download/c$a;)V
    //   218: invokestatic 649	android/os/Message:obtain	()Landroid/os/Message;
    //   221: astore_1
    //   222: aload_1
    //   223: iconst_2
    //   224: putfield 652	android/os/Message:what	I
    //   227: aload_1
    //   228: bipush 7
    //   230: putfield 655	android/os/Message:arg1	I
    //   233: aload_1
    //   234: iload 6
    //   236: putfield 658	android/os/Message:arg2	I
    //   239: aload_0
    //   240: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   243: aload_2
    //   244: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   247: invokeinterface 663 2 0
    //   252: ifnull +23 -> 275
    //   255: aload_0
    //   256: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   259: aload_2
    //   260: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   263: invokeinterface 663 2 0
    //   268: checkcast 665	android/os/Messenger
    //   271: aload_1
    //   272: invokevirtual 669	android/os/Messenger:send	(Landroid/os/Message;)V
    //   275: iconst_1
    //   276: ireturn
    //   277: astore_1
    //   278: getstatic 51	com/taobao/munion/base/download/f:f	Ljava/lang/String;
    //   281: iconst_2
    //   282: anewarray 4	java/lang/Object
    //   285: dup
    //   286: iconst_0
    //   287: ldc 93
    //   289: aastore
    //   290: dup
    //   291: iconst_1
    //   292: aload_1
    //   293: aastore
    //   294: invokestatic 292	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   297: goto -22 -> 275
    //   300: astore_1
    //   301: aload_1
    //   302: invokevirtual 204	java/lang/Exception:printStackTrace	()V
    //   305: iconst_0
    //   306: ireturn
    //   307: getstatic 51	com/taobao/munion/base/download/f:f	Ljava/lang/String;
    //   310: iconst_1
    //   311: anewarray 4	java/lang/Object
    //   314: dup
    //   315: iconst_0
    //   316: ldc_w 609
    //   319: aastore
    //   320: invokestatic 611	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   323: aload 5
    //   325: iconst_1
    //   326: invokevirtual 672	com/taobao/munion/base/download/DownloadingService$b:a	(I)V
    //   329: aload_2
    //   330: aconst_null
    //   331: putfield 604	com/taobao/munion/base/download/f$c:a	Lcom/taobao/munion/base/download/DownloadingService$b;
    //   334: aload_0
    //   335: aload_3
    //   336: iload 6
    //   338: invokevirtual 674	com/taobao/munion/base/download/f:a	(Landroid/content/Context;I)V
    //   341: aload_0
    //   342: lload 7
    //   344: lload 9
    //   346: lload 11
    //   348: aload_2
    //   349: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   352: invokevirtual 676	com/taobao/munion/base/download/f:b	(JJJLcom/taobao/munion/base/download/c$a;)V
    //   355: invokestatic 649	android/os/Message:obtain	()Landroid/os/Message;
    //   358: astore_1
    //   359: aload_1
    //   360: iconst_2
    //   361: putfield 652	android/os/Message:what	I
    //   364: aload_1
    //   365: bipush 6
    //   367: putfield 655	android/os/Message:arg1	I
    //   370: aload_1
    //   371: iload 6
    //   373: putfield 658	android/os/Message:arg2	I
    //   376: aload_0
    //   377: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   380: aload_2
    //   381: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   384: invokeinterface 663 2 0
    //   389: ifnull +23 -> 412
    //   392: aload_0
    //   393: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   396: aload_2
    //   397: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   400: invokeinterface 663 2 0
    //   405: checkcast 665	android/os/Messenger
    //   408: aload_1
    //   409: invokevirtual 669	android/os/Messenger:send	(Landroid/os/Message;)V
    //   412: iconst_1
    //   413: ireturn
    //   414: astore_1
    //   415: getstatic 51	com/taobao/munion/base/download/f:f	Ljava/lang/String;
    //   418: iconst_2
    //   419: anewarray 4	java/lang/Object
    //   422: dup
    //   423: iconst_0
    //   424: ldc 93
    //   426: aastore
    //   427: dup
    //   428: iconst_1
    //   429: aload_1
    //   430: aastore
    //   431: invokestatic 292	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   434: goto -22 -> 412
    //   437: ldc_w 526
    //   440: aload 4
    //   442: invokevirtual 301	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   445: ifeq -140 -> 305
    //   448: getstatic 51	com/taobao/munion/base/download/f:f	Ljava/lang/String;
    //   451: iconst_1
    //   452: anewarray 4	java/lang/Object
    //   455: dup
    //   456: iconst_0
    //   457: ldc_w 678
    //   460: aastore
    //   461: invokestatic 611	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   464: aload 5
    //   466: ifnull +74 -> 540
    //   469: aload 5
    //   471: iconst_2
    //   472: invokevirtual 672	com/taobao/munion/base/download/DownloadingService$b:a	(I)V
    //   475: invokestatic 649	android/os/Message:obtain	()Landroid/os/Message;
    //   478: astore_1
    //   479: aload_1
    //   480: iconst_5
    //   481: putfield 652	android/os/Message:what	I
    //   484: aload_1
    //   485: iconst_5
    //   486: putfield 655	android/os/Message:arg1	I
    //   489: aload_1
    //   490: iload 6
    //   492: putfield 658	android/os/Message:arg2	I
    //   495: aload_0
    //   496: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   499: aload_2
    //   500: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   503: invokeinterface 663 2 0
    //   508: ifnull +23 -> 531
    //   511: aload_0
    //   512: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   515: aload_2
    //   516: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   519: invokeinterface 663 2 0
    //   524: checkcast 665	android/os/Messenger
    //   527: aload_1
    //   528: invokevirtual 669	android/os/Messenger:send	(Landroid/os/Message;)V
    //   531: aload_0
    //   532: aload_3
    //   533: iload 6
    //   535: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   538: iconst_1
    //   539: ireturn
    //   540: aload_0
    //   541: lload 7
    //   543: lload 9
    //   545: lload 11
    //   547: aload_2
    //   548: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   551: invokevirtual 682	com/taobao/munion/base/download/f:d	(JJJLcom/taobao/munion/base/download/c$a;)V
    //   554: goto -79 -> 475
    //   557: astore_1
    //   558: invokestatic 649	android/os/Message:obtain	()Landroid/os/Message;
    //   561: astore_1
    //   562: aload_1
    //   563: iconst_5
    //   564: putfield 652	android/os/Message:what	I
    //   567: aload_1
    //   568: iconst_5
    //   569: putfield 655	android/os/Message:arg1	I
    //   572: aload_1
    //   573: iload 6
    //   575: putfield 658	android/os/Message:arg2	I
    //   578: aload_0
    //   579: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   582: aload_2
    //   583: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   586: invokeinterface 663 2 0
    //   591: ifnull +23 -> 614
    //   594: aload_0
    //   595: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   598: aload_2
    //   599: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   602: invokeinterface 663 2 0
    //   607: checkcast 665	android/os/Messenger
    //   610: aload_1
    //   611: invokevirtual 669	android/os/Messenger:send	(Landroid/os/Message;)V
    //   614: aload_0
    //   615: aload_3
    //   616: iload 6
    //   618: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   621: goto -83 -> 538
    //   624: astore_1
    //   625: aload_0
    //   626: aload_3
    //   627: iload 6
    //   629: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   632: goto -94 -> 538
    //   635: astore_1
    //   636: aload_0
    //   637: aload_3
    //   638: iload 6
    //   640: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   643: goto -105 -> 538
    //   646: astore_1
    //   647: invokestatic 649	android/os/Message:obtain	()Landroid/os/Message;
    //   650: astore 4
    //   652: aload 4
    //   654: iconst_5
    //   655: putfield 652	android/os/Message:what	I
    //   658: aload 4
    //   660: iconst_5
    //   661: putfield 655	android/os/Message:arg1	I
    //   664: aload 4
    //   666: iload 6
    //   668: putfield 658	android/os/Message:arg2	I
    //   671: aload_0
    //   672: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   675: aload_2
    //   676: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   679: invokeinterface 663 2 0
    //   684: ifnull +24 -> 708
    //   687: aload_0
    //   688: getfield 60	com/taobao/munion/base/download/f:k	Ljava/util/Map;
    //   691: aload_2
    //   692: getfield 502	com/taobao/munion/base/download/f$c:e	Lcom/taobao/munion/base/download/c$a;
    //   695: invokeinterface 663 2 0
    //   700: checkcast 665	android/os/Messenger
    //   703: aload 4
    //   705: invokevirtual 669	android/os/Messenger:send	(Landroid/os/Message;)V
    //   708: aload_0
    //   709: aload_3
    //   710: iload 6
    //   712: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   715: aload_1
    //   716: athrow
    //   717: astore_2
    //   718: aload_0
    //   719: aload_3
    //   720: iload 6
    //   722: invokevirtual 680	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
    //   725: goto -10 -> 715
    //
    // Exception table:
    //   from	to	target	type
    //   239	275	277	android/os/RemoteException
    //   0	16	300	java/lang/Exception
    //   22	46	300	java/lang/Exception
    //   51	125	300	java/lang/Exception
    //   130	164	300	java/lang/Exception
    //   166	239	300	java/lang/Exception
    //   239	275	300	java/lang/Exception
    //   278	297	300	java/lang/Exception
    //   307	376	300	java/lang/Exception
    //   376	412	300	java/lang/Exception
    //   415	434	300	java/lang/Exception
    //   437	464	300	java/lang/Exception
    //   475	495	300	java/lang/Exception
    //   495	531	300	java/lang/Exception
    //   531	538	300	java/lang/Exception
    //   558	578	300	java/lang/Exception
    //   578	614	300	java/lang/Exception
    //   614	621	300	java/lang/Exception
    //   625	632	300	java/lang/Exception
    //   636	643	300	java/lang/Exception
    //   647	671	300	java/lang/Exception
    //   671	708	300	java/lang/Exception
    //   708	715	300	java/lang/Exception
    //   715	717	300	java/lang/Exception
    //   718	725	300	java/lang/Exception
    //   376	412	414	android/os/RemoteException
    //   469	475	557	java/lang/Exception
    //   540	554	557	java/lang/Exception
    //   578	614	624	android/os/RemoteException
    //   614	621	624	android/os/RemoteException
    //   495	531	635	android/os/RemoteException
    //   531	538	635	android/os/RemoteException
    //   469	475	646	finally
    //   540	554	646	finally
    //   671	708	717	android/os/RemoteException
    //   708	715	717	android/os/RemoteException
  }

  boolean a(c.a parama, boolean paramBoolean, Messenger paramMessenger)
  {
    c.a locala;
    if (paramBoolean)
    {
      int m = new Random().nextInt(1000);
      if (this.k != null)
      {
        localIterator = this.k.keySet().iterator();
        while (localIterator.hasNext())
        {
          locala = (c.a)localIterator.next();
          Log.d(f, new Object[] { "_" + m + " downling  " + locala.c + "   " + locala.d });
        }
      }
      Log.d(f, new Object[] { "_" + m + "downling  null" });
    }
    if (this.k == null)
      return false;
    Iterator localIterator = this.k.keySet().iterator();
    while (localIterator.hasNext())
    {
      locala = (c.a)localIterator.next();
      if ((parama.f != null) && (parama.f.equals(locala.f)))
      {
        this.k.put(locala, paramMessenger);
        return true;
      }
      if (locala.d.equals(parama.d))
      {
        this.k.put(locala, paramMessenger);
        return true;
      }
    }
    return false;
  }

  int b(c.a parama)
  {
    int m = 0;
    while (m < this.j.size())
    {
      int n = this.j.keyAt(m);
      if ((parama.f != null) && (parama.f.equals(((c)this.j.get(n)).e.f)))
        return ((c)this.j.get(n)).c;
      if (((c)this.j.get(n)).e.d.equals(parama.d))
        return ((c)this.j.get(n)).c;
      m += 1;
    }
    return -1;
  }

  final void b(long paramLong1, long paramLong2, long paramLong3, c.a parama)
  {
    String[] arrayOfString = parama.k;
    if (arrayOfString != null)
    {
      Log.i("sendPauseReport.", new Object[0]);
      int n = arrayOfString.length;
      int m = 0;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        ((d)localObject).a(new Long[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) });
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  void b(Context paramContext, int paramInt)
  {
    paramContext = (NotificationManager)paramContext.getApplicationContext().getSystemService("notification");
    c localc = (c)this.j.get(paramInt);
    if (localc != null)
    {
      Log.d(f, new Object[] { "download service clear cache " + localc.e.c });
      if (localc.a != null)
        localc.a.a(2);
      paramContext.cancel(localc.c);
      if (this.k.containsKey(localc.e))
        this.k.remove(localc.e);
      localc.b(this.j);
      this.l.b(paramInt);
    }
  }

  final void c(long paramLong1, long paramLong2, long paramLong3, c.a parama)
  {
    String[] arrayOfString = parama.h;
    if (arrayOfString != null)
    {
      Log.i("sendGoOnReport.", new Object[0]);
      int n = arrayOfString.length;
      int m = 0;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        ((d)localObject).a(new Long[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) });
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  final void c(c.a parama)
  {
    int m = 0;
    String[] arrayOfString = parama.j;
    if (arrayOfString != null)
    {
      Log.i("sendStartReport.", new Object[0]);
      int n = arrayOfString.length;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  final void d(long paramLong1, long paramLong2, long paramLong3, c.a parama)
  {
    String[] arrayOfString = parama.l;
    if (arrayOfString != null)
    {
      Log.i("sendCancelReport.", new Object[0]);
      int n = arrayOfString.length;
      int m = 0;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        ((d)localObject).a(new Long[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) });
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  final void d(c.a parama)
  {
    int m = 0;
    String[] arrayOfString = parama.g;
    if (arrayOfString != null)
    {
      Log.i("sendSuccessReport.", new Object[0]);
      int n = arrayOfString.length;
      while (m < n)
      {
        Object localObject = arrayOfString[m];
        localObject = a(parama.a, (String)localObject);
        this.g.a((l)localObject);
        m += 1;
      }
    }
  }

  static class a extends h
  {
    String a;
    String b;
    String c;

    public a(Context paramContext)
    {
      super();
    }

    public Notification a()
    {
      if (Build.VERSION.SDK_INT >= 16)
        return this.f.build();
      this.e.setLatestEventInfo(this.d, this.a, this.c, this.g);
      return this.e;
    }

    public a a(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        this.f.setProgress(paramInt1, paramInt2, paramBoolean);
        return this;
      }
      this.c = (paramInt2 + "%");
      return this;
    }

    public a a(CharSequence paramCharSequence)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        this.f.setContentText(paramCharSequence);
        return this;
      }
      this.b = paramCharSequence.toString();
      return this;
    }

    public void a(int paramInt, String paramString, PendingIntent paramPendingIntent)
    {
      if (Build.VERSION.SDK_INT >= 16)
        this.f.addAction(paramInt, paramString, paramPendingIntent);
    }

    public a b(CharSequence paramCharSequence)
    {
      if (Build.VERSION.SDK_INT >= 16)
      {
        this.f.setContentTitle(paramCharSequence);
        return this;
      }
      this.a = paramCharSequence.toString();
      return this;
    }
  }

  static class b
  {
    public static final int a = 448;
    public static final int b = 256;
    public static final int c = 128;
    public static final int d = 64;
    public static final int e = 56;
    public static final int f = 32;
    public static final int g = 16;
    public static final int h = 8;
    public static final int i = 7;
    public static final int j = 4;
    public static final int k = 2;
    public static final int l = 1;
  }

  static class c
  {
    DownloadingService.b a;
    f.a b;
    int c;
    int d;
    c.a e;
    long[] f = new long[3];

    public c(c.a parama, int paramInt)
    {
      this.c = paramInt;
      this.e = parama;
    }

    public void a(SparseArray<c> paramSparseArray)
    {
      paramSparseArray.put(this.c, this);
    }

    public void b(SparseArray<c> paramSparseArray)
    {
      if (paramSparseArray.indexOfKey(this.c) >= 0)
        paramSparseArray.remove(this.c);
    }
  }

  class d extends AsyncTask<String, Void, Integer>
  {
    public int a;
    public String b;
    private c.a d;
    private Context e;
    private NotificationManager f;

    public d(Context paramInt, int parama, c.a paramString, String arg5)
    {
      this.e = paramInt.getApplicationContext();
      this.f = ((NotificationManager)this.e.getSystemService("notification"));
      this.a = parama;
      this.d = paramString;
      Object localObject;
      this.b = localObject;
    }

    protected Integer a(String[] paramArrayOfString)
    {
      return Integer.valueOf(1);
    }

    protected void a(Integer paramInteger)
    {
      if (paramInteger.intValue() == 1)
      {
        paramInteger = new Notification(17301634, k.h, System.currentTimeMillis());
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).addFlags(268435456);
        ((Intent)localObject).setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
        PendingIntent localPendingIntent = PendingIntent.getActivity(this.e, 0, (Intent)localObject, 134217728);
        paramInteger.setLatestEventInfo(this.e, f.c(this.e), k.h, localPendingIntent);
        paramInteger.flags = 16;
        this.f.notify(this.a + 1, paramInteger);
        if (f.this.a(this.e))
        {
          this.f.cancel(this.a + 1);
          this.e.startActivity((Intent)localObject);
        }
        paramInteger = new Bundle();
        paramInteger.putString("filename", this.b);
        localObject = Message.obtain();
        ((Message)localObject).what = 5;
        ((Message)localObject).arg1 = 1;
        ((Message)localObject).arg2 = this.a;
        ((Message)localObject).setData(paramInteger);
        try
        {
          if (f.a(f.this).get(this.d) != null)
            ((Messenger)f.a(f.this).get(this.d)).send((Message)localObject);
          f.this.b(this.e, this.a);
          return;
        }
        catch (RemoteException paramInteger)
        {
          f.this.b(this.e, this.a);
          return;
        }
      }
      this.f.cancel(this.a + 1);
      paramInteger = new Bundle();
      paramInteger.putString("filename", this.b);
      Object localObject = Message.obtain();
      ((Message)localObject).what = 5;
      ((Message)localObject).arg1 = 3;
      ((Message)localObject).arg2 = this.a;
      ((Message)localObject).setData(paramInteger);
      try
      {
        if (f.a(f.this).get(this.d) != null)
          ((Messenger)f.a(f.this).get(this.d)).send((Message)localObject);
        f.this.b(this.e, this.a);
        return;
      }
      catch (RemoteException paramInteger)
      {
        f.this.b(this.e, this.a);
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.download.f
 * JD-Core Version:    0.6.2
 */