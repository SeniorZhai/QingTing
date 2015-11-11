package com.taobao.munion.base;

import com.taobao.munion.base.caches.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

public class e
{
  private static final String a = "MD5";
  private static final String b = "SHA-1";
  private static final String c = "SHA-256";

  private static String a(InputStream paramInputStream, String paramString)
    throws IOException
  {
    try
    {
      paramString = MessageDigest.getInstance(paramString);
      byte[] arrayOfByte = new byte[1024];
      for (int i = paramInputStream.read(arrayOfByte, 0, 1024); i > -1; i = paramInputStream.read(arrayOfByte, 0, 1024))
        paramString.update(arrayOfByte, 0, i);
      paramInputStream = l.a(paramString.digest());
      return paramInputStream;
    }
    catch (GeneralSecurityException paramInputStream)
    {
    }
    throw new IllegalStateException("Security exception", paramInputStream);
  }

  public static String a(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      paramString = a(paramString.getBytes("utf-8"), "MD5");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    return a(paramArrayOfByte, "SHA-256");
  }

  private static String a(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramArrayOfByte = l.a(MessageDigest.getInstance(paramString).digest(paramArrayOfByte));
      return paramArrayOfByte;
    }
    catch (GeneralSecurityException paramArrayOfByte)
    {
    }
    throw new IllegalStateException("Security exception", paramArrayOfByte);
  }

  public static String b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    return a(paramArrayOfByte, "SHA-1");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.e
 * JD-Core Version:    0.6.2
 */