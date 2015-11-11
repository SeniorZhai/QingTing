package com.sina.weibo.sdk.utils;

public final class Base64
{
  private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
  private static byte[] codes = new byte[256];

  static
  {
    int i = 0;
    if (i >= 256)
    {
      i = 65;
      label28: if (i <= 90)
        break label82;
      i = 97;
      label37: if (i <= 122)
        break label99;
      i = 48;
    }
    while (true)
    {
      if (i > 57)
      {
        codes[43] = 62;
        codes[47] = 63;
        return;
        codes[i] = -1;
        i += 1;
        break;
        label82: codes[i] = ((byte)(i - 65));
        i += 1;
        break label28;
        label99: codes[i] = ((byte)(i + 26 - 97));
        i += 1;
        break label37;
      }
      codes[i] = ((byte)(i + 52 - 48));
      i += 1;
    }
  }

  public static byte[] decode(byte[] paramArrayOfByte)
  {
    int j = (paramArrayOfByte.length + 3) / 4 * 3;
    int i = j;
    if (paramArrayOfByte.length > 0)
    {
      i = j;
      if (paramArrayOfByte[(paramArrayOfByte.length - 1)] == 61)
        i = j - 1;
    }
    j = i;
    if (paramArrayOfByte.length > 1)
    {
      j = i;
      if (paramArrayOfByte[(paramArrayOfByte.length - 2)] == 61)
        j = i - 1;
    }
    byte[] arrayOfByte = new byte[j];
    int m = 0;
    int i2 = 0;
    int k = 0;
    j = 0;
    while (true)
    {
      if (j >= paramArrayOfByte.length)
      {
        if (k == arrayOfByte.length)
          break;
        throw new RuntimeException("miscalculated data length!");
      }
      int i3 = codes[(paramArrayOfByte[j] & 0xFF)];
      int n = i2;
      int i1 = k;
      i = m;
      if (i3 >= 0)
      {
        m += 6;
        i2 = i2 << 6 | i3;
        n = i2;
        i1 = k;
        i = m;
        if (m >= 8)
        {
          i = m - 8;
          arrayOfByte[k] = ((byte)(i2 >> i & 0xFF));
          i1 = k + 1;
          n = i2;
        }
      }
      j += 1;
      i2 = n;
      k = i1;
      m = i;
    }
    return arrayOfByte;
  }

  public static char[] encode(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[(paramArrayOfByte.length + 2) / 3 * 4];
    int j = 0;
    int i = 0;
    if (j >= paramArrayOfByte.length)
      return arrayOfChar1;
    int n = 0;
    int k = 0;
    int i1 = (paramArrayOfByte[j] & 0xFF) << 8;
    int m = i1;
    if (j + 1 < paramArrayOfByte.length)
    {
      m = i1 | paramArrayOfByte[(j + 1)] & 0xFF;
      k = 1;
    }
    i1 = m << 8;
    m = i1;
    if (j + 2 < paramArrayOfByte.length)
    {
      m = i1 | paramArrayOfByte[(j + 2)] & 0xFF;
      n = 1;
    }
    char[] arrayOfChar2 = alphabet;
    if (n != 0)
    {
      n = m & 0x3F;
      label129: arrayOfChar1[(i + 3)] = arrayOfChar2[n];
      m >>= 6;
      arrayOfChar2 = alphabet;
      if (k == 0)
        break label228;
    }
    label228: for (k = m & 0x3F; ; k = 64)
    {
      arrayOfChar1[(i + 2)] = arrayOfChar2[k];
      k = m >> 6;
      arrayOfChar1[(i + 1)] = alphabet[(k & 0x3F)];
      arrayOfChar1[(i + 0)] = alphabet[(k >> 6 & 0x3F)];
      j += 3;
      i += 4;
      break;
      n = 64;
      break label129;
    }
  }

  public static byte[] encodebyte(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[(paramArrayOfByte.length + 2) / 3 * 4];
    int j = 0;
    int i = 0;
    if (j >= paramArrayOfByte.length)
      return arrayOfByte;
    int n = 0;
    int k = 0;
    int i1 = (paramArrayOfByte[j] & 0xFF) << 8;
    int m = i1;
    if (j + 1 < paramArrayOfByte.length)
    {
      m = i1 | paramArrayOfByte[(j + 1)] & 0xFF;
      k = 1;
    }
    i1 = m << 8;
    m = i1;
    if (j + 2 < paramArrayOfByte.length)
    {
      m = i1 | paramArrayOfByte[(j + 2)] & 0xFF;
      n = 1;
    }
    char[] arrayOfChar = alphabet;
    if (n != 0)
    {
      n = m & 0x3F;
      label129: arrayOfByte[(i + 3)] = ((byte)arrayOfChar[n]);
      m >>= 6;
      arrayOfChar = alphabet;
      if (k == 0)
        break label232;
    }
    label232: for (k = m & 0x3F; ; k = 64)
    {
      arrayOfByte[(i + 2)] = ((byte)arrayOfChar[k]);
      k = m >> 6;
      arrayOfByte[(i + 1)] = ((byte)alphabet[(k & 0x3F)]);
      arrayOfByte[(i + 0)] = ((byte)alphabet[(k >> 6 & 0x3F)]);
      j += 3;
      i += 4;
      break;
      n = 64;
      break label129;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.Base64
 * JD-Core Version:    0.6.2
 */