package com.umeng.message.proguard;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class aN
{
  private static final String a = "SHA-1";
  private static final String b = "MD5";
  private static final String c = "SHA-256";

  public static String a(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = a(paramInputStream, "MD5");
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
    }
    return null;
  }

  private static String a(InputStream paramInputStream, String paramString)
    throws IOException
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      byte[] arrayOfByte = new byte[1024];
      for (int i = paramInputStream.read(arrayOfByte, 0, 1024); i > -1; i = paramInputStream.read(arrayOfByte, 0, 1024))
        paramString.update(arrayOfByte, 0, i);
      paramInputStream = aP.a(paramString.digest());
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
    }
    return null;
  }

  public static String a(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      paramString = a(paramString.getBytes("UTF-8"), "MD5");
      return paramString;
    }
    catch (Throwable paramString)
    {
    }
    return null;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, "SHA-256");
  }

  private static String a(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      paramString.update(paramArrayOfByte, 0, paramArrayOfByte.length);
      paramArrayOfByte = aP.a(paramString.digest());
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
    }
    return null;
  }

  public static String b(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = a(paramInputStream, "SHA-1");
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
    }
    return null;
  }

  public static String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      paramString = a(paramString.getBytes("UTF-8"), "SHA-1");
      return paramString;
    }
    catch (Throwable paramString)
    {
    }
    return null;
  }

  public static String c(InputStream paramInputStream)
  {
    try
    {
      paramInputStream = a(paramInputStream, "SHA-256");
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
    }
    return null;
  }

  public static String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    try
    {
      paramString = a(paramString.getBytes("UTF-8"), "SHA-256");
      return paramString;
    }
    catch (Throwable paramString)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aN
 * JD-Core Version:    0.6.2
 */