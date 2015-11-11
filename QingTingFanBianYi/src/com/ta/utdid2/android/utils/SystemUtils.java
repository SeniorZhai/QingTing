package com.ta.utdid2.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.File;
import java.lang.reflect.Field;

public class SystemUtils
{
  public static String getAppLabel(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = paramContext.getPackageName();
      if ((localPackageManager != null) && (paramContext != null))
      {
        paramContext = localPackageManager.getApplicationLabel(localPackageManager.getPackageInfo(paramContext, 1).applicationInfo).toString();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public static String getCpuInfo()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_1
    //   4: aconst_null
    //   5: astore_0
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 5
    //   14: new 52	java/io/FileReader
    //   17: dup
    //   18: ldc 54
    //   20: invokespecial 57	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   23: astore 6
    //   25: aload 5
    //   27: astore_0
    //   28: aload 6
    //   30: ifnull +48 -> 78
    //   33: new 59	java/io/BufferedReader
    //   36: dup
    //   37: aload 6
    //   39: sipush 1024
    //   42: invokespecial 62	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   45: astore 5
    //   47: aload 4
    //   49: astore_0
    //   50: aload_3
    //   51: astore_1
    //   52: aload 5
    //   54: invokevirtual 65	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   57: astore_2
    //   58: aload_2
    //   59: astore_0
    //   60: aload_2
    //   61: astore_1
    //   62: aload 5
    //   64: invokevirtual 68	java/io/BufferedReader:close	()V
    //   67: aload_2
    //   68: astore_0
    //   69: aload_2
    //   70: astore_1
    //   71: aload 6
    //   73: invokevirtual 69	java/io/FileReader:close	()V
    //   76: aload_2
    //   77: astore_0
    //   78: aload_0
    //   79: ifnull +57 -> 136
    //   82: aload_0
    //   83: aload_0
    //   84: bipush 58
    //   86: invokevirtual 75	java/lang/String:indexOf	(I)I
    //   89: iconst_1
    //   90: iadd
    //   91: invokevirtual 79	java/lang/String:substring	(I)Ljava/lang/String;
    //   94: invokevirtual 82	java/lang/String:trim	()Ljava/lang/String;
    //   97: areturn
    //   98: astore_1
    //   99: aload_2
    //   100: astore_0
    //   101: aload_1
    //   102: astore_2
    //   103: aload_0
    //   104: astore_1
    //   105: ldc 84
    //   107: aload_2
    //   108: invokevirtual 85	java/io/IOException:toString	()Ljava/lang/String;
    //   111: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   114: pop
    //   115: goto -37 -> 78
    //   118: astore_2
    //   119: aload_1
    //   120: astore_0
    //   121: aload_2
    //   122: astore_1
    //   123: ldc 93
    //   125: aload_1
    //   126: invokevirtual 94	java/io/FileNotFoundException:toString	()Ljava/lang/String;
    //   129: invokestatic 91	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   132: pop
    //   133: goto -55 -> 78
    //   136: ldc 96
    //   138: areturn
    //   139: astore_1
    //   140: goto -17 -> 123
    //   143: astore_1
    //   144: goto -21 -> 123
    //   147: astore_2
    //   148: aload_1
    //   149: astore_0
    //   150: goto -47 -> 103
    //
    // Exception table:
    //   from	to	target	type
    //   33	47	98	java/io/IOException
    //   33	47	118	java/io/FileNotFoundException
    //   105	115	118	java/io/FileNotFoundException
    //   14	25	139	java/io/FileNotFoundException
    //   52	58	143	java/io/FileNotFoundException
    //   62	67	143	java/io/FileNotFoundException
    //   71	76	143	java/io/FileNotFoundException
    //   52	58	147	java/io/IOException
    //   62	67	147	java/io/IOException
    //   71	76	147	java/io/IOException
  }

  public static File getRootFolder(String paramString)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      paramString = new File(String.format("%s%s%s", new Object[] { localFile.getAbsolutePath(), File.separator, paramString }));
      if ((paramString != null) && (!paramString.exists()))
        paramString.mkdirs();
      return paramString;
    }
    return null;
  }

  public static int getSystemVersion()
  {
    try
    {
      i = Build.VERSION.class.getField("SDK_INT").getInt(null);
      return i;
    }
    catch (Exception localException1)
    {
      try
      {
        int i = Integer.parseInt((String)Build.VERSION.class.getField("SDK").get(null));
        return i;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.SystemUtils
 * JD-Core Version:    0.6.2
 */