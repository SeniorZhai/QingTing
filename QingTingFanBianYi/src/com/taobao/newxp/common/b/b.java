package com.taobao.newxp.common.b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.taobao.newxp.common.Log;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class b
{
  public static final String a = System.getProperty("line.separator");
  private static final String b = "helper";
  private static final String c = "SHA-1";

  public static String a()
  {
    return a(new Date());
  }

  public static String a(Context paramContext, long paramLong)
  {
    if (paramLong < 1000L)
      return (int)paramLong + "B";
    if (paramLong < 1000000L)
      return Math.round((float)paramLong / 1000.0D) + "K";
    if (paramLong < 1000000000L)
    {
      paramContext = new DecimalFormat("#0.0");
      return paramContext.format((float)paramLong / 1000000.0D) + "M";
    }
    paramContext = new DecimalFormat("#0.00");
    return paramContext.format((float)paramLong / 1000000000.0D) + "G";
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
        int i = paramFile.read(arrayOfByte, 0, 1024);
        if (i == -1)
          break;
        localMessageDigest.update(arrayOfByte, 0, i);
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
    int i = 0;
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
      while (i < localObject1.length)
      {
        ((StringBuffer)localObject2).append(String.format("%02X", new Object[] { Byte.valueOf(localObject1[i]) }));
        i += 1;
      }
      localObject1 = ((StringBuffer)localObject2).toString();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return paramString.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
  }

  public static String a(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }

  public static void a(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }

  public static byte[] a(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramArrayOfByte != null)
    {
      localObject1 = localObject2;
      if (paramArrayOfByte.length <= 0);
    }
    try
    {
      localObject1 = MessageDigest.getInstance("SHA-1");
      ((MessageDigest)localObject1).update(paramArrayOfByte);
      localObject1 = ((MessageDigest)localObject1).digest();
      return localObject1;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      Log.c("helper", "SHA-1 encode exception,info:" + paramArrayOfByte.toString());
      return null;
    }
    catch (Exception paramArrayOfByte)
    {
      Log.c("helper", "SHA-1 encode exception,info:" + paramArrayOfByte.toString());
    }
    return null;
  }

  public static String b(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      while (i < paramString.length)
      {
        ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      Log.a("helper", "getMD5 error", paramString);
    }
    return "";
  }

  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }

  public static boolean c(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean d(String paramString)
  {
    if (c(paramString));
    do
    {
      return false;
      paramString = paramString.trim().toLowerCase();
    }
    while ((!paramString.startsWith("http://")) && (!paramString.startsWith("https://")));
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.b.b
 * JD-Core Version:    0.6.2
 */