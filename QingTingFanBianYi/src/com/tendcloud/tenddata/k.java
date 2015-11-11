package com.tendcloud.tenddata;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class k
{
  private static final int a = 3600000;

  private static int a(String paramString)
  {
    String str = "";
    try
    {
      Matcher localMatcher = Pattern.compile("([0-9]+)").matcher(paramString);
      paramString = str;
      if (localMatcher.find())
        paramString = localMatcher.toMatchResult().group(0);
      int i = Integer.valueOf(paramString).intValue();
      return i;
    }
    catch (Exception paramString)
    {
    }
    return 0;
  }

  public static String a()
  {
    return "Android+" + Build.VERSION.RELEASE;
  }

  public static String a(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    if (paramContext != null)
    {
      int i = paramContext.widthPixels;
      int j = paramContext.heightPixels;
      return Math.min(i, j) + "*" + Math.max(i, j) + "*" + paramContext.densityDpi;
    }
    return "";
  }

  private static String a(String paramString1, String paramString2)
  {
    String str = paramString1.toLowerCase();
    if ((str.startsWith("unknown")) || (str.startsWith("alps")) || (str.startsWith("android")) || (str.startsWith("sprd")) || (str.startsWith("spreadtrum")) || (str.startsWith("rockchip")) || (str.startsWith("wondermedia")) || (str.startsWith("mtk")) || (str.startsWith("mt65")) || (str.startsWith("nvidia")) || (str.startsWith("brcm")) || (str.startsWith("marvell")) || (paramString2.toLowerCase().contains(str)))
      paramString1 = null;
    return paramString1;
  }

  public static int b()
  {
    return TimeZone.getDefault().getRawOffset() / 3600000;
  }

  public static String b(Context paramContext)
  {
    if (!x.a(paramContext, "android.permission.READ_PHONE_STATE"))
      return "";
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }

  // ERROR //
  private static String b(String paramString)
  {
    // Byte code:
    //   0: ldc 17
    //   2: astore_1
    //   3: aload_1
    //   4: astore_2
    //   5: new 185	java/io/FileReader
    //   8: dup
    //   9: aload_0
    //   10: invokespecial 188	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   13: astore 4
    //   15: aload_1
    //   16: astore_2
    //   17: aload_1
    //   18: astore_3
    //   19: sipush 1024
    //   22: newarray char
    //   24: astore 5
    //   26: aload_1
    //   27: astore_2
    //   28: aload_1
    //   29: astore_3
    //   30: new 190	java/io/BufferedReader
    //   33: dup
    //   34: aload 4
    //   36: sipush 1024
    //   39: invokespecial 193	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   42: astore 6
    //   44: aload_1
    //   45: astore_0
    //   46: aload_0
    //   47: astore_2
    //   48: aload_0
    //   49: astore_3
    //   50: aload 6
    //   52: aload 5
    //   54: iconst_0
    //   55: sipush 1024
    //   58: invokevirtual 197	java/io/BufferedReader:read	([CII)I
    //   61: istore 7
    //   63: iconst_m1
    //   64: iload 7
    //   66: if_icmpeq +40 -> 106
    //   69: aload_0
    //   70: astore_2
    //   71: aload_0
    //   72: astore_3
    //   73: new 58	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 59	java/lang/StringBuilder:<init>	()V
    //   80: aload_0
    //   81: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: new 115	java/lang/String
    //   87: dup
    //   88: aload 5
    //   90: iconst_0
    //   91: iload 7
    //   93: invokespecial 200	java/lang/String:<init>	([CII)V
    //   96: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: astore_0
    //   103: goto -57 -> 46
    //   106: aload_0
    //   107: astore_2
    //   108: aload_0
    //   109: astore_3
    //   110: aload 6
    //   112: invokevirtual 203	java/io/BufferedReader:close	()V
    //   115: aload_0
    //   116: astore_2
    //   117: aload_0
    //   118: astore_3
    //   119: aload 4
    //   121: invokevirtual 204	java/io/FileReader:close	()V
    //   124: aload_0
    //   125: areturn
    //   126: astore_0
    //   127: aload_2
    //   128: areturn
    //   129: astore_0
    //   130: aload_3
    //   131: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   5	15	126	java/io/FileNotFoundException
    //   19	26	126	java/io/FileNotFoundException
    //   30	44	126	java/io/FileNotFoundException
    //   50	63	126	java/io/FileNotFoundException
    //   73	103	126	java/io/FileNotFoundException
    //   110	115	126	java/io/FileNotFoundException
    //   119	124	126	java/io/FileNotFoundException
    //   19	26	129	java/io/IOException
    //   30	44	129	java/io/IOException
    //   50	63	129	java/io/IOException
    //   73	103	129	java/io/IOException
    //   110	115	129	java/io/IOException
    //   119	124	129	java/io/IOException
  }

  public static String c()
  {
    String str = Build.MODEL.trim();
    Object localObject2 = a(Build.MANUFACTURER.trim(), str);
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
      localObject1 = a(Build.BRAND.trim(), str);
    localObject2 = localObject1;
    if (localObject1 == null)
      localObject2 = "";
    return (String)localObject2 + ":" + str;
  }

  public static String c(Context paramContext)
  {
    if (!x.a(paramContext, "android.permission.READ_PHONE_STATE"))
      return "";
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
  }

  public static int d()
  {
    return Build.VERSION.SDK_INT;
  }

  public static String d(Context paramContext)
  {
    if (!x.a(paramContext, "android.permission.READ_PHONE_STATE"))
      return "";
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
  }

  public static int e(Context paramContext)
  {
    if (!x.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
      return -1;
    paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getCellLocation();
    if ((paramContext instanceof GsmCellLocation))
      return ((GsmCellLocation)paramContext).getCid();
    return -1;
  }

  public static String e()
  {
    return Build.VERSION.RELEASE;
  }

  public static String f()
  {
    return Locale.getDefault().getLanguage();
  }

  public static String g()
  {
    return Locale.getDefault().getCountry();
  }

  // ERROR //
  public static String[] h()
  {
    // Byte code:
    //   0: iconst_4
    //   1: anewarray 115	java/lang/String
    //   4: astore_0
    //   5: iconst_0
    //   6: istore 5
    //   8: iload 5
    //   10: iconst_4
    //   11: if_icmpge +18 -> 29
    //   14: aload_0
    //   15: iload 5
    //   17: ldc 17
    //   19: aastore
    //   20: iload 5
    //   22: iconst_1
    //   23: iadd
    //   24: istore 5
    //   26: goto -18 -> 8
    //   29: new 269	java/util/ArrayList
    //   32: dup
    //   33: invokespecial 270	java/util/ArrayList:<init>	()V
    //   36: astore_1
    //   37: new 185	java/io/FileReader
    //   40: dup
    //   41: ldc_w 272
    //   44: invokespecial 188	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   47: astore_2
    //   48: new 190	java/io/BufferedReader
    //   51: dup
    //   52: aload_2
    //   53: sipush 1024
    //   56: invokespecial 193	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   59: astore_3
    //   60: aload_3
    //   61: invokevirtual 275	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 4
    //   66: aload 4
    //   68: ifnull +135 -> 203
    //   71: aload_1
    //   72: aload 4
    //   74: invokeinterface 281 2 0
    //   79: pop
    //   80: goto -20 -> 60
    //   83: astore 4
    //   85: aload_3
    //   86: invokevirtual 203	java/io/BufferedReader:close	()V
    //   89: aload_2
    //   90: invokevirtual 204	java/io/FileReader:close	()V
    //   93: iconst_0
    //   94: istore 5
    //   96: iload 5
    //   98: ifeq +162 -> 260
    //   101: aload_1
    //   102: invokeinterface 284 1 0
    //   107: istore 7
    //   109: iconst_0
    //   110: istore 5
    //   112: iload 5
    //   114: iconst_3
    //   115: if_icmpge +145 -> 260
    //   118: iconst_3
    //   119: anewarray 115	java/lang/String
    //   122: dup
    //   123: iconst_0
    //   124: ldc_w 286
    //   127: aastore
    //   128: dup
    //   129: iconst_1
    //   130: ldc_w 288
    //   133: aastore
    //   134: dup
    //   135: iconst_2
    //   136: ldc_w 290
    //   139: aastore
    //   140: iload 5
    //   142: aaload
    //   143: invokestatic 25	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   146: astore_2
    //   147: iconst_0
    //   148: istore 6
    //   150: iload 6
    //   152: iload 7
    //   154: if_icmpge +97 -> 251
    //   157: aload_2
    //   158: aload_1
    //   159: iload 6
    //   161: invokeinterface 294 2 0
    //   166: checkcast 115	java/lang/String
    //   169: invokevirtual 29	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   172: astore_3
    //   173: aload_3
    //   174: invokevirtual 35	java/util/regex/Matcher:find	()Z
    //   177: ifeq +17 -> 194
    //   180: aload_0
    //   181: iload 5
    //   183: aload_3
    //   184: invokevirtual 39	java/util/regex/Matcher:toMatchResult	()Ljava/util/regex/MatchResult;
    //   187: iconst_1
    //   188: invokeinterface 45 2 0
    //   193: aastore
    //   194: iload 6
    //   196: iconst_1
    //   197: iadd
    //   198: istore 6
    //   200: goto -50 -> 150
    //   203: aload_3
    //   204: invokevirtual 203	java/io/BufferedReader:close	()V
    //   207: aload_2
    //   208: invokevirtual 204	java/io/FileReader:close	()V
    //   211: iconst_1
    //   212: istore 5
    //   214: goto -118 -> 96
    //   217: astore_2
    //   218: iconst_1
    //   219: istore 5
    //   221: goto -125 -> 96
    //   224: astore_2
    //   225: iconst_0
    //   226: istore 5
    //   228: goto -132 -> 96
    //   231: astore 4
    //   233: aload_3
    //   234: invokevirtual 203	java/io/BufferedReader:close	()V
    //   237: aload_2
    //   238: invokevirtual 204	java/io/FileReader:close	()V
    //   241: aload 4
    //   243: athrow
    //   244: astore_2
    //   245: iconst_0
    //   246: istore 5
    //   248: goto -152 -> 96
    //   251: iload 5
    //   253: iconst_1
    //   254: iadd
    //   255: istore 5
    //   257: goto -145 -> 112
    //   260: aload_0
    //   261: iconst_3
    //   262: ldc_w 296
    //   265: invokestatic 298	com/tendcloud/tenddata/k:b	(Ljava/lang/String;)Ljava/lang/String;
    //   268: aastore
    //   269: aload_0
    //   270: areturn
    //   271: astore_2
    //   272: iconst_1
    //   273: istore 5
    //   275: goto -179 -> 96
    //   278: astore_2
    //   279: goto -38 -> 241
    //
    // Exception table:
    //   from	to	target	type
    //   60	66	83	java/io/IOException
    //   71	80	83	java/io/IOException
    //   203	211	217	java/io/IOException
    //   85	93	224	java/io/IOException
    //   60	66	231	finally
    //   71	80	231	finally
    //   37	60	244	java/io/FileNotFoundException
    //   85	93	244	java/io/FileNotFoundException
    //   233	241	244	java/io/FileNotFoundException
    //   241	244	244	java/io/FileNotFoundException
    //   203	211	271	java/io/FileNotFoundException
    //   233	241	278	java/io/IOException
  }

  public static String[] i()
  {
    return new String[] { "vendor", "Renderder" };
  }

  // ERROR //
  public static int[] j()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_2
    //   4: newarray int
    //   6: astore_0
    //   7: aload_0
    //   8: iconst_0
    //   9: iconst_0
    //   10: iastore
    //   11: aload_0
    //   12: iconst_1
    //   13: iconst_0
    //   14: iastore
    //   15: iconst_4
    //   16: newarray int
    //   18: astore_3
    //   19: iconst_0
    //   20: istore 4
    //   22: iload 4
    //   24: iconst_4
    //   25: if_icmpge +17 -> 42
    //   28: aload_3
    //   29: iload 4
    //   31: iconst_0
    //   32: iastore
    //   33: iload 4
    //   35: iconst_1
    //   36: iadd
    //   37: istore 4
    //   39: goto -17 -> 22
    //   42: new 185	java/io/FileReader
    //   45: dup
    //   46: ldc_w 307
    //   49: invokespecial 188	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   52: astore_1
    //   53: new 190	java/io/BufferedReader
    //   56: dup
    //   57: aload_1
    //   58: sipush 1024
    //   61: invokespecial 193	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   64: astore_2
    //   65: iload 5
    //   67: istore 4
    //   69: iload 4
    //   71: iconst_4
    //   72: if_icmpge +23 -> 95
    //   75: aload_3
    //   76: iload 4
    //   78: aload_2
    //   79: invokevirtual 275	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   82: invokestatic 309	com/tendcloud/tenddata/k:a	(Ljava/lang/String;)I
    //   85: iastore
    //   86: iload 4
    //   88: iconst_1
    //   89: iadd
    //   90: istore 4
    //   92: goto -23 -> 69
    //   95: aload_0
    //   96: iconst_0
    //   97: aload_3
    //   98: iconst_0
    //   99: iaload
    //   100: iastore
    //   101: aload_3
    //   102: iconst_1
    //   103: iaload
    //   104: istore 4
    //   106: aload_3
    //   107: iconst_2
    //   108: iaload
    //   109: istore 5
    //   111: aload_0
    //   112: iconst_1
    //   113: aload_3
    //   114: iconst_3
    //   115: iaload
    //   116: iload 4
    //   118: iload 5
    //   120: iadd
    //   121: iadd
    //   122: iastore
    //   123: aload_2
    //   124: invokevirtual 203	java/io/BufferedReader:close	()V
    //   127: aload_1
    //   128: invokevirtual 204	java/io/FileReader:close	()V
    //   131: aload_0
    //   132: areturn
    //   133: astore_3
    //   134: aload_2
    //   135: invokevirtual 203	java/io/BufferedReader:close	()V
    //   138: aload_1
    //   139: invokevirtual 204	java/io/FileReader:close	()V
    //   142: aload_0
    //   143: areturn
    //   144: astore_1
    //   145: aload_0
    //   146: areturn
    //   147: astore_3
    //   148: aload_2
    //   149: invokevirtual 203	java/io/BufferedReader:close	()V
    //   152: aload_1
    //   153: invokevirtual 204	java/io/FileReader:close	()V
    //   156: aload_3
    //   157: athrow
    //   158: astore_1
    //   159: aload_0
    //   160: areturn
    //   161: astore_1
    //   162: goto -6 -> 156
    //   165: astore_1
    //   166: aload_0
    //   167: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   75	86	133	java/io/IOException
    //   134	142	144	java/io/IOException
    //   75	86	147	finally
    //   42	65	158	java/io/FileNotFoundException
    //   123	131	158	java/io/FileNotFoundException
    //   134	142	158	java/io/FileNotFoundException
    //   148	156	158	java/io/FileNotFoundException
    //   156	158	158	java/io/FileNotFoundException
    //   148	156	161	java/io/IOException
    //   123	131	165	java/io/IOException
  }

  public static int[] k()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
    int i = localStatFs.getBlockCount() * (localStatFs.getBlockSize() / 512) / 2;
    int j = localStatFs.getAvailableBlocks();
    j = localStatFs.getBlockSize() / 512 * j / 2;
    localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    int k = localStatFs.getBlockCount() * (localStatFs.getBlockSize() / 512) / 2;
    int m = localStatFs.getAvailableBlocks();
    return new int[] { i, j, k, localStatFs.getBlockSize() / 512 * m / 2 };
  }

  public static int l()
  {
    int i = 0;
    Object localObject = b("/sys/class/power_supply/battery/full_bat");
    localObject = Pattern.compile("\\s*([0-9]+)").matcher((CharSequence)localObject);
    if (((Matcher)localObject).find())
      localObject = ((Matcher)localObject).toMatchResult().group(0);
    try
    {
      i = Integer.valueOf((String)localObject).intValue();
      return i;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.k
 * JD-Core Version:    0.6.2
 */