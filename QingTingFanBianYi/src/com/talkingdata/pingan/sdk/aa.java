package com.talkingdata.pingan.sdk;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.InflaterInputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class aa
{
  static final boolean a = false;
  private static final String c = "UTF-8";
  private static final ExecutorService d;
  private static final byte e = 61;
  private static final String f = "US-ASCII";
  private static final byte[] g;

  static
  {
    if (!aa.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      b = bool;
      d = Executors.newSingleThreadExecutor();
      g = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
      return;
    }
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(Integer.toHexString(paramArrayOfByte[i] & 0xFF));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte = b(paramArrayOfByte, paramInt1, paramInt2);
    try
    {
      String str = new String(paramArrayOfByte, "US-ASCII");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return new String(paramArrayOfByte);
  }

  public static void a(Runnable paramRunnable)
  {
    d.execute(paramRunnable);
  }

  public static boolean a(Context paramContext, String paramString)
  {
    return paramContext.checkCallingOrSelfPermission(paramString) == 0;
  }

  public static final boolean a(String paramString)
  {
    return (paramString == null) || ("".equals(paramString.trim()));
  }

  private static byte[] a(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    int k = 0;
    byte[] arrayOfByte = g;
    int i;
    if (paramInt2 > 0)
    {
      i = paramArrayOfByte1[paramInt1] << 24 >>> 8;
      label23: if (paramInt2 <= 1)
        break label104;
    }
    label104: for (int j = paramArrayOfByte1[(paramInt1 + 1)] << 24 >>> 16; ; j = 0)
    {
      if (paramInt2 > 2)
        k = paramArrayOfByte1[(paramInt1 + 2)] << 24 >>> 24;
      paramInt1 = k | (j | i);
      switch (paramInt2)
      {
      default:
        return paramArrayOfByte2;
        i = 0;
        break label23;
      case 3:
      case 2:
      case 1:
      }
    }
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = arrayOfByte[(paramInt1 & 0x3F)];
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = arrayOfByte[(paramInt1 >>> 6 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
    paramArrayOfByte2[paramInt3] = arrayOfByte[(paramInt1 >>> 18)];
    paramArrayOfByte2[(paramInt3 + 1)] = arrayOfByte[(paramInt1 >>> 12 & 0x3F)];
    paramArrayOfByte2[(paramInt3 + 2)] = 61;
    paramArrayOfByte2[(paramInt3 + 3)] = 61;
    return paramArrayOfByte2;
  }

  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Object localObject = new InflaterInputStream(new ByteArrayInputStream(paramArrayOfByte2));
    paramArrayOfByte2 = new ByteArrayOutputStream();
    while (true)
    {
      int i = ((InflaterInputStream)localObject).read();
      if (i == -1)
        break;
      paramArrayOfByte2.write(i);
    }
    localObject = new SecureRandom();
    paramArrayOfByte1 = new DESKeySpec(paramArrayOfByte1);
    paramArrayOfByte1 = SecretKeyFactory.getInstance("DES").generateSecret(paramArrayOfByte1);
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(2, paramArrayOfByte1, new IvParameterSpec(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }), (SecureRandom)localObject);
    return localCipher.doFinal(paramArrayOfByte2.toByteArray());
  }

  public static byte[] a(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = 0;
    while (i < paramArrayOfInt1.length)
    {
      paramArrayOfInt1[i] = (paramArrayOfInt1[i] * paramArrayOfInt2[(paramArrayOfInt2.length - 1 - i)] - paramArrayOfInt1[(paramArrayOfInt1.length - 1 - i)] * paramArrayOfInt2[i] + "kiG9w0BAQUFADCBqjELMAkGA0JFSUpJTkcxEDAOBgNVBAcMB0JFSUpJTkcxFjAUBgNVB".charAt(i));
      paramArrayOfInt2[i] = (paramArrayOfInt2[i] * paramArrayOfInt1[(paramArrayOfInt1.length - 1 - i)] + paramArrayOfInt2[(paramArrayOfInt2.length - 1 - i)] * paramArrayOfInt1[i] - "kiG9w0BAQUFADCBqjELMAkGA0JFSUpJTkcxEDAOBgNVBAcMB0JFSUpJTkcxFjAUBgNVB".charAt("kiG9w0BAQUFADCBqjELMAkGA0JFSUpJTkcxEDAOBgNVBAcMB0JFSUpJTkcxFjAUBgNVB".length() - 1 - i));
      i += 1;
    }
    return (Arrays.toString(paramArrayOfInt1) + Arrays.hashCode(paramArrayOfInt2)).getBytes();
  }

  public static String b(String paramString)
  {
    try
    {
      paramString = a(MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8")));
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public static String b(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      paramArrayOfByte = a(paramArrayOfByte, 0, paramArrayOfByte.length);
      if ((!b) && (paramArrayOfByte == null))
        throw new AssertionError();
    }
    catch (IOException localIOException)
    {
      do
        paramArrayOfByte = localObject;
      while (b);
      throw new AssertionError(localIOException.getMessage());
    }
    return paramArrayOfByte;
  }

  public static byte[] b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException("Cannot serialize a null array.");
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Cannot have negative offset: " + paramInt1);
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Cannot have length offset: " + paramInt2);
    if (paramInt1 + paramInt2 > paramArrayOfByte.length)
      throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramArrayOfByte.length) }));
    int j = paramInt2 / 3;
    if (paramInt2 % 3 > 0);
    byte[] arrayOfByte;
    for (int i = 4; ; i = 0)
    {
      arrayOfByte = new byte[i + j * 4];
      i = 0;
      j = 0;
      while (j < paramInt2 - 2)
      {
        a(paramArrayOfByte, j + paramInt1, 3, arrayOfByte, i);
        j += 3;
        i += 4;
      }
    }
    int k = i;
    if (j < paramInt2)
    {
      a(paramArrayOfByte, j + paramInt1, paramInt2 - j, arrayOfByte, i);
      k = i + 4;
    }
    if (k <= arrayOfByte.length - 1)
    {
      paramArrayOfByte = new byte[k];
      System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, k);
      return paramArrayOfByte;
    }
    return arrayOfByte;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.aa
 * JD-Core Version:    0.6.2
 */