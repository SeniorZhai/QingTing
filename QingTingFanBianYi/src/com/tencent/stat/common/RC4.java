package com.tencent.stat.common;

public class RC4
{
  static final byte[] key = "03a976511e2cbe3a7f26808fb7af3c05".getBytes();

  public static byte[] decrypt(byte[] paramArrayOfByte)
  {
    return decrypt(paramArrayOfByte, key);
  }

  static byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return encrypt(paramArrayOfByte1, paramArrayOfByte2);
  }

  public static byte[] encrypt(byte[] paramArrayOfByte)
  {
    return encrypt(paramArrayOfByte, key);
  }

  static byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int m = 0;
    int[] arrayOfInt1 = new int[256];
    int[] arrayOfInt2 = new int[256];
    int j = paramArrayOfByte2.length;
    if ((j < 1) || (j > 256))
      throw new IllegalArgumentException("key must be between 1 and 256 bytes");
    int i = 0;
    while (i < 256)
    {
      arrayOfInt1[i] = i;
      arrayOfInt2[i] = paramArrayOfByte2[(i % j)];
      i += 1;
    }
    i = 0;
    j = 0;
    while (i < 256)
    {
      j = j + arrayOfInt1[i] + arrayOfInt2[i] & 0xFF;
      k = arrayOfInt1[i];
      arrayOfInt1[i] = arrayOfInt1[j];
      arrayOfInt1[j] = k;
      i += 1;
    }
    paramArrayOfByte2 = new byte[paramArrayOfByte1.length];
    j = 0;
    int k = 0;
    i = m;
    while (i < paramArrayOfByte1.length)
    {
      k = k + 1 & 0xFF;
      j = j + arrayOfInt1[k] & 0xFF;
      m = arrayOfInt1[k];
      arrayOfInt1[k] = arrayOfInt1[j];
      arrayOfInt1[j] = m;
      paramArrayOfByte2[i] = ((byte)(arrayOfInt1[(arrayOfInt1[k] + arrayOfInt1[j] & 0xFF)] ^ paramArrayOfByte1[i]));
      i += 1;
    }
    return paramArrayOfByte2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.common.RC4
 * JD-Core Version:    0.6.2
 */