package com.sina.weibo.sdk.utils;

import java.io.PrintStream;
import java.security.MessageDigest;

public class MD5
{
  private static final char[] hexDigits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

  public static String hexdigest(String paramString)
  {
    try
    {
      paramString = hexdigest(paramString.getBytes());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String hexdigest(byte[] paramArrayOfByte)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramArrayOfByte);
      paramArrayOfByte = ((MessageDigest)localObject).digest();
      localObject = new char[32];
      int i = 0;
      int j = 0;
      while (true)
      {
        if (i >= 16)
          return new String((char[])localObject);
        int k = paramArrayOfByte[i];
        int m = j + 1;
        localObject[j] = hexDigits[(k >>> 4 & 0xF)];
        j = m + 1;
        localObject[m] = hexDigits[(k & 0xF)];
        i += 1;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(hexdigest("c"));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.MD5
 * JD-Core Version:    0.6.2
 */