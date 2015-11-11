package cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.Charsets;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;

@SuppressLint({"NewApi"})
public class StringUtils
{
  public static boolean checkPermissions(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }

  private static byte[] getBytes(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return null;
    return paramString.getBytes(paramCharset);
  }

  public static byte[] getBytesIso8859_1(String paramString)
  {
    return getBytes(paramString, Charsets.ISO_8859_1);
  }

  public static byte[] getBytesUnchecked(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    try
    {
      paramString1 = paramString1.getBytes(paramString2);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
    }
    throw newIllegalStateException(paramString2, paramString1);
  }

  public static byte[] getBytesUsAscii(String paramString)
  {
    return getBytes(paramString, Charsets.US_ASCII);
  }

  public static byte[] getBytesUtf16(String paramString)
  {
    return getBytes(paramString, Charsets.UTF_16);
  }

  public static byte[] getBytesUtf16Be(String paramString)
  {
    return getBytes(paramString, Charsets.UTF_16BE);
  }

  public static byte[] getBytesUtf16Le(String paramString)
  {
    return getBytes(paramString, Charsets.UTF_16LE);
  }

  public static byte[] getBytesUtf8(String paramString)
  {
    return getBytes(paramString, Charsets.UTF_8);
  }

  public static String getPName(Context paramContext)
  {
    try
    {
      if (checkPermissions(paramContext, "android.permission.GET_TASKS"))
        return ((ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.getPackageName();
      if (IRMonitor.c)
        Log.e("lost permission", "android.permission.GET_TASKS");
      return "";
    }
    catch (SecurityException paramContext)
    {
    }
    return "";
  }

  private static IllegalStateException newIllegalStateException(String paramString, UnsupportedEncodingException paramUnsupportedEncodingException)
  {
    return new IllegalStateException(paramString + ": " + paramUnsupportedEncodingException);
  }

  public static String newString(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, paramString);
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw newIllegalStateException(paramString, paramArrayOfByte);
  }

  private static String newString(byte[] paramArrayOfByte, Charset paramCharset)
  {
    if (paramArrayOfByte == null)
      return null;
    return new String(paramArrayOfByte, paramCharset);
  }

  public static String newStringIso8859_1(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.ISO_8859_1);
  }

  public static String newStringUsAscii(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.US_ASCII);
  }

  public static String newStringUtf16(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.UTF_16);
  }

  public static String newStringUtf16Be(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.UTF_16BE);
  }

  public static String newStringUtf16Le(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charsets.UTF_16LE);
  }

  public static String newStringUtf8(byte[] paramArrayOfByte)
  {
    return newString(paramArrayOfByte, Charsets.UTF_8);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.StringUtils
 * JD-Core Version:    0.6.2
 */