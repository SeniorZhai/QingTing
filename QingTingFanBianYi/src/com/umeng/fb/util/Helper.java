package com.umeng.fb.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class Helper
{
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String TAG = Helper.class.getName();

  public static String MD5(String paramString)
  {
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
      int i = 0;
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

  public static String generateFeedbackId(Context paramContext)
  {
    long l = System.currentTimeMillis();
    return "FB[" + DeviceConfig.getAppkey(paramContext) + "_" + DeviceConfig.getDeviceIdUmengMD5(paramContext) + "]" + String.valueOf(l) + String.valueOf((int)(1000.0D + Math.random() * 9000.0D));
  }

  public static String generateReplyID()
  {
    long l = System.currentTimeMillis();
    return "RP" + String.valueOf(l) + String.valueOf((int)(1000.0D + Math.random() * 9000.0D));
  }

  public static String getDateTime()
  {
    return getTimeString(new Date());
  }

  public static String getFileMD5(File paramFile)
  {
    byte[] arrayOfByte = new byte[1024];
    try
    {
      if (!paramFile.isFile())
        return "";
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      paramFile = new FileInputStream(paramFile);
      try
      {
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
      }
      label57: paramFile.printStackTrace();
      return null;
      paramFile.close();
      return String.format("%1$032x", new Object[] { new BigInteger(1, localMessageDigest.digest()) });
    }
    catch (Exception paramFile)
    {
      break label57;
    }
  }

  public static String getFileSizeDescription(Context paramContext, long paramLong)
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

  public static String getFileSizeDescription(String paramString)
  {
    while (true)
    {
      long l;
      try
      {
        l = Long.valueOf(paramString).longValue();
        if (l < 1024L)
        {
          paramString = (int)l + "B";
          return paramString;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        return paramString;
      }
      if (l < 1048576L)
      {
        paramString = new DecimalFormat("#0.00");
        paramString = paramString.format((float)l / 1024.0D) + "K";
      }
      else if (l < 1073741824L)
      {
        paramString = new DecimalFormat("#0.00");
        paramString = paramString.format((float)l / 1048576.0D) + "M";
      }
      else
      {
        paramString = new DecimalFormat("#0.00");
        paramString = paramString.format((float)l / 1073741824.0D) + "G";
      }
    }
  }

  public static JSONObject getMessageHeader(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("device_id", DeviceConfig.getDeviceId(paramContext));
      localJSONObject.put("idmd5", DeviceConfig.getDeviceIdUmengMD5(paramContext));
      localJSONObject.put("device_model", Build.MODEL);
      localJSONObject.put("appkey", DeviceConfig.getAppkey(paramContext));
      localJSONObject.put("channel", DeviceConfig.getChannel(paramContext));
      localJSONObject.put("app_version", DeviceConfig.getAppVersionName(paramContext));
      localJSONObject.put("version_code", DeviceConfig.getAppVersionCode(paramContext));
      localJSONObject.put("sdk_type", "Android");
      localJSONObject.put("sdk_version", "4.3.2.20140520");
      localJSONObject.put("os", "Android");
      localJSONObject.put("os_version", Build.VERSION.RELEASE);
      localJSONObject.put("country", DeviceConfig.getLocaleInfo(paramContext)[0]);
      localJSONObject.put("language", DeviceConfig.getLocaleInfo(paramContext)[1]);
      localJSONObject.put("timezone", DeviceConfig.getTimeZone(paramContext));
      localJSONObject.put("resolution", DeviceConfig.getResolution(paramContext));
      localJSONObject.put("access", DeviceConfig.getNetworkAccessMode(paramContext)[0]);
      localJSONObject.put("access_subtype", DeviceConfig.getNetworkAccessMode(paramContext)[1]);
      localJSONObject.put("carrier", DeviceConfig.getNetworkOperatorName(paramContext));
      localJSONObject.put("cpu", DeviceConfig.getCPU());
      localJSONObject.put("package", DeviceConfig.getPackageName(paramContext));
      return localJSONObject;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return localJSONObject;
  }

  public static String getTimeString(Date paramDate)
  {
    if (paramDate == null)
      return "";
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(paramDate);
  }

  public static String getUmengMD5(String paramString)
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
      Log.i(TAG, "getMD5 error", paramString);
    }
    return "";
  }

  public static boolean isAbsoluteUrl(String paramString)
  {
    if (isEmpty(paramString));
    do
    {
      return false;
      paramString = paramString.trim().toLowerCase();
    }
    while ((!paramString.startsWith("http://")) && (!paramString.startsWith("https://")));
    return true;
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static void openApp(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }

  public static boolean openUrlSchema(Context paramContext, String paramString)
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.Helper
 * JD-Core Version:    0.6.2
 */