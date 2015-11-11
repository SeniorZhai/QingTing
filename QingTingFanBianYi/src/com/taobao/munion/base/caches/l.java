package com.taobao.munion.base.caches;

import java.io.ByteArrayOutputStream;

public class l
{
  private static String a = "0123456789ABCDEF";

  private static byte a(byte paramByte1, byte paramByte2)
  {
    return (byte)((byte)(Byte.decode("0x" + new String(new byte[] { paramByte1 })).byteValue() << 4) | Byte.decode("0x" + new String(new byte[] { paramByte2 })).byteValue());
  }

  private static byte a(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }

  public static String a(String paramString)
  {
    String str1 = "";
    int i = 0;
    while (i < paramString.length())
    {
      String str2 = Integer.toHexString(paramString.charAt(i));
      str1 = str1 + str2;
      i += 1;
    }
    return str1;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static String b(String paramString)
  {
    paramString = paramString.getBytes();
    StringBuilder localStringBuilder = new StringBuilder(paramString.length * 2);
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      int k = paramString[i];
      localStringBuilder.append(a.charAt((k & 0xF0) >> 4));
      localStringBuilder.append(a.charAt((k & 0xF) >> 0));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static String c(String paramString)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramString.length() / 2);
    int i = 0;
    while (i < paramString.length())
    {
      localByteArrayOutputStream.write(a.indexOf(paramString.charAt(i)) << 4 | a.indexOf(paramString.charAt(i + 1)));
      i += 2;
    }
    return new String(localByteArrayOutputStream.toByteArray());
  }

  public static byte[] d(String paramString)
  {
    byte[] arrayOfByte = new byte[6];
    paramString = paramString.getBytes();
    int i = 0;
    while (i < 6)
    {
      arrayOfByte[i] = a(paramString[(i * 2)], paramString[(i * 2 + 1)]);
      i += 1;
    }
    return arrayOfByte;
  }

  public static byte[] e(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
    {
      paramString = null;
      return paramString;
    }
    paramString = paramString.toUpperCase();
    int j = paramString.length() / 2;
    char[] arrayOfChar = paramString.toCharArray();
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (true)
    {
      paramString = arrayOfByte;
      if (i >= j)
        break;
      int k = i * 2;
      int m = a(arrayOfChar[k]);
      arrayOfByte[i] = ((byte)(a(arrayOfChar[(k + 1)]) | m << 4));
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.l
 * JD-Core Version:    0.6.2
 */