package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.sdk.cons.GlobalConstants;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
  static final String a = "com.alipay.android.app";
  static final String b = "com.eg.android.AlipayGphone";
  private static final String c = "7.0.0";
  private static final String d = "5.0.0";

  public static String a()
  {
    return "Android " + Build.VERSION.RELEASE;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte))).getPublicKey().toString();
      if (paramArrayOfByte.indexOf("modulus") != -1)
      {
        paramArrayOfByte = paramArrayOfByte.substring(paramArrayOfByte.indexOf("modulus") + 8, paramArrayOfByte.lastIndexOf(",")).trim();
        return paramArrayOfByte;
      }
    }
    catch (Exception paramArrayOfByte)
    {
    }
    return null;
  }

  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      Object localObject = "chmod " + "777" + " " + paramString;
      Runtime.getRuntime().exec((String)localObject);
      label35: localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).addFlags(268435456);
      ((Intent)localObject).setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    catch (IOException localIOException)
    {
      break label35;
    }
  }

  private static void a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = "chmod " + paramString1 + " " + paramString2;
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
    }
  }

  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.alipay.android.app", 128);
      return paramContext != null;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  private static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString1);
      paramString1 = new File(paramString2);
      paramString1.createNewFile();
      paramString1 = new FileOutputStream(paramString1);
      paramString2 = new byte[1024];
      while (true)
      {
        int i = paramContext.read(paramString2);
        if (i <= 0)
          break;
        paramString1.write(paramString2, 0, i);
      }
      paramString1.close();
      paramContext.close();
      return true;
    }
    catch (IOException paramContext)
    {
    }
    return false;
  }

  public static boolean a(String paramString)
  {
    return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(paramString).matches();
  }

  public static byte[] a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals(paramString))
        return localPackageInfo.signatures[0].toByteArray();
    }
    return null;
  }

  private static int b(String paramString1, String paramString2)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localArrayList1.addAll(Arrays.asList(paramString1.split("\\.")));
    localArrayList2.addAll(Arrays.asList(paramString2.split("\\.")));
    int j = Math.max(localArrayList1.size(), localArrayList2.size());
    while (localArrayList1.size() < j)
      localArrayList1.add("0");
    while (localArrayList2.size() < j)
      localArrayList2.add("0");
    int i = 0;
    while (i < j)
    {
      if (Integer.parseInt((String)localArrayList1.get(i)) != Integer.parseInt((String)localArrayList2.get(i)))
        return Integer.parseInt((String)localArrayList1.get(i)) - Integer.parseInt((String)localArrayList2.get(i));
      i += 1;
    }
    return 0;
  }

  public static String b()
  {
    Object localObject2 = e();
    int i = ((String)localObject2).indexOf("-");
    Object localObject1 = localObject2;
    if (i != -1)
      localObject1 = ((String)localObject2).substring(0, i);
    i = ((String)localObject1).indexOf("\n");
    localObject2 = localObject1;
    if (i != -1)
      localObject2 = ((String)localObject1).substring(0, i);
    return "Linux " + (String)localObject2;
  }

  public static boolean b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
      if (paramContext == null)
        return false;
      int i = b(paramContext.versionName, "7.0.0");
      if (i >= 0)
        return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
      return paramContext != null;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  public static String c()
  {
    String str = GlobalConstants.b;
    return str.substring(0, str.indexOf("://"));
  }

  public static String c(Context paramContext)
  {
    String str1 = a();
    String str2 = b();
    String str3 = d(paramContext);
    paramContext = e(paramContext);
    return " (" + str1 + ";" + str2 + ";" + str3 + ";;" + paramContext + ")(sdk android)";
  }

  public static String d()
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < 24)
    {
      switch (localRandom.nextInt(3))
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        i += 1;
        break;
        localStringBuffer.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 65.0D)));
        continue;
        localStringBuffer.append(String.valueOf((char)(int)Math.round(Math.random() * 25.0D + 97.0D)));
        continue;
        localStringBuffer.append(String.valueOf(new Random().nextInt(10)));
      }
    }
    return localStringBuffer.toString();
  }

  public static String d(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString();
  }

  private static String e()
  {
    try
    {
      Object localObject1 = new BufferedReader(new FileReader("/proc/version"), 256);
      try
      {
        String str2 = ((BufferedReader)localObject1).readLine();
        ((BufferedReader)localObject1).close();
        localObject1 = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(str2);
        if (!((Matcher)localObject1).matches())
          return "Unavailable";
      }
      finally
      {
        ((BufferedReader)localObject1).close();
      }
    }
    catch (IOException localIOException)
    {
      return "Unavailable";
    }
    if (localIOException.groupCount() < 4)
      return "Unavailable";
    String str1 = localIOException.group(1) + "\n" + localIOException.group(2) + " " + localIOException.group(3) + "\n" + localIOException.group(4);
    return str1;
  }

  public static String e(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    paramContext = new StringBuilder();
    paramContext.append(localDisplayMetrics.widthPixels);
    paramContext.append("*");
    paramContext.append(localDisplayMetrics.heightPixels);
    return paramContext.toString();
  }

  public static String f(Context paramContext)
  {
    try
    {
      paramContext = (GsmCellLocation)((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
      if (paramContext != null)
      {
        int i = paramContext.getCid();
        int j = paramContext.getLac();
        paramContext = new StringBuilder();
        paramContext.append(j);
        paramContext.append(";");
        paramContext.append(i);
        paramContext = paramContext.toString();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
    }
    return "-1;-1";
  }

  private static boolean g(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = b(paramContext.getPackageManager().getPackageInfo("com.alipay.android.app", 128).versionName, "5.0.0");
      if (i >= 0)
        bool = true;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  private static DisplayMetrics h(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.Utils
 * JD-Core Version:    0.6.2
 */