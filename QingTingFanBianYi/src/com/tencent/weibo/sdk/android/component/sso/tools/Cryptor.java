package com.tencent.weibo.sdk.android.component.sso.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

public class Cryptor
{
  public static final int QUOTIENT = 79764919;
  public static final int SALT_LEN = 2;
  public static final int ZERO_LEN = 7;
  private int contextStart;
  private int crypt;
  private boolean header = true;
  private byte[] key;
  private byte[] out;
  private int padding;
  private byte[] plain;
  private int pos;
  private int preCrypt;
  private byte[] prePlain;
  private Random random = new Random();

  public static int CRC32Hash(byte[] paramArrayOfByte)
  {
    int n = paramArrayOfByte.length;
    int i = -1;
    int j = 0;
    int m;
    int k;
    while (true)
    {
      if (j >= n)
        return i ^ 0xFFFFFFFF;
      m = paramArrayOfByte[j];
      k = 0;
      if (k < 8)
        break;
      j += 1;
    }
    if ((m ^ i) >>> 31 == 1)
      i = i << 1 ^ 0x4C11DB7;
    while (true)
    {
      m = (byte)(m << 1);
      k += 1;
      break;
      i <<= 1;
    }
  }

  public static byte[] MD5Hash(byte[] paramArrayOfByte, int paramInt)
  {
    return new byte[2];
  }

  public static int _4bytesDecryptAFrame(long paramLong, byte[] paramArrayOfByte)
  {
    short[] arrayOfShort1 = new short[2];
    arrayOfShort1[0] = ((short)(int)(0xFFFF & paramLong));
    arrayOfShort1[1] = ((short)(int)(paramLong >> 16));
    short[] arrayOfShort2 = new short[4];
    arrayOfShort2[0] = ((short)(paramArrayOfByte[1] << 8 | paramArrayOfByte[0]));
    arrayOfShort2[1] = ((short)(paramArrayOfByte[3] << 8 | paramArrayOfByte[2]));
    arrayOfShort2[2] = ((short)(paramArrayOfByte[5] << 8 | paramArrayOfByte[4]));
    arrayOfShort2[3] = ((short)(paramArrayOfByte[7] << 8 | paramArrayOfByte[6]));
    int i = arrayOfShort1[0];
    int j = arrayOfShort1[1];
    int m = (short)412640;
    int n;
    for (int k = 32; ; k = n)
    {
      n = (short)(k - 1);
      if (k <= 0)
      {
        arrayOfShort1[0] = i;
        arrayOfShort1[1] = j;
        return arrayOfShort1[1] << 16 | arrayOfShort1[0] & 0xFFFF;
      }
      j = (short)(j - ((i << 4) + arrayOfShort2[2] ^ i + m ^ (i >> 5) + arrayOfShort2[3]));
      i = (short)(i - ((j << 4) + arrayOfShort2[0] ^ j + m ^ (j >> 5) + arrayOfShort2[1]));
      m = (short)(m - 12895);
    }
  }

  public static byte[] _4bytesEncryptAFrame(int paramInt, byte[] paramArrayOfByte)
  {
    short[] arrayOfShort1 = new short[2];
    arrayOfShort1[0] = ((short)(0xFFFF & paramInt));
    arrayOfShort1[1] = ((short)(paramInt >>> 16));
    short[] arrayOfShort2 = new short[4];
    arrayOfShort2[0] = ((short)(paramArrayOfByte[1] << 8 | paramArrayOfByte[0]));
    arrayOfShort2[1] = ((short)(paramArrayOfByte[3] << 8 | paramArrayOfByte[2]));
    arrayOfShort2[2] = ((short)(paramArrayOfByte[5] << 8 | paramArrayOfByte[4]));
    arrayOfShort2[3] = ((short)(paramArrayOfByte[7] << 8 | paramArrayOfByte[6]));
    int i = arrayOfShort1[0];
    paramInt = arrayOfShort1[1];
    int k = 0;
    int m;
    for (int j = 32; ; j = m)
    {
      m = (short)(j - 1);
      if (j <= 0)
        return new byte[] { (byte)(paramInt >> 8), (byte)(paramInt & 0xFF), (byte)(i >> 8), (byte)(i & 0xFF) };
      k = (short)(k + 12895);
      i = (short)(((paramInt << 4) + arrayOfShort2[0] ^ paramInt + k ^ (paramInt >> 5) + arrayOfShort2[1]) + i);
      paramInt = (short)(((i << 4) + arrayOfShort2[2] ^ i + k ^ (i >> 5) + arrayOfShort2[3]) + paramInt);
    }
  }

  private byte[] decipher(byte[] paramArrayOfByte)
  {
    return decipher(paramArrayOfByte, 0);
  }

  private byte[] decipher(byte[] paramArrayOfByte, int paramInt)
  {
    try
    {
      long l2 = getUnsignedInt(paramArrayOfByte, paramInt, 4);
      long l3 = getUnsignedInt(paramArrayOfByte, paramInt + 4, 4);
      long l4 = getUnsignedInt(this.key, 0, 4);
      long l5 = getUnsignedInt(this.key, 4, 4);
      long l6 = getUnsignedInt(this.key, 8, 4);
      long l7 = getUnsignedInt(this.key, 12, 4);
      long l1 = 0xE3779B90 & 0xFFFFFFFF;
      paramInt = 16;
      while (true)
      {
        if (paramInt <= 0)
        {
          paramArrayOfByte = new ByteArrayOutputStream(8);
          DataOutputStream localDataOutputStream = new DataOutputStream(paramArrayOfByte);
          localDataOutputStream.writeInt((int)l2);
          localDataOutputStream.writeInt((int)l3);
          localDataOutputStream.close();
          paramArrayOfByte = paramArrayOfByte.toByteArray();
          return paramArrayOfByte;
        }
        l3 = l3 - ((l2 << 4) + l6 ^ l2 + l1 ^ (l2 >>> 5) + l7) & 0xFFFFFFFF;
        l2 = l2 - ((l3 << 4) + l4 ^ l3 + l1 ^ (l3 >>> 5) + l5) & 0xFFFFFFFF;
        l1 = l1 - (0x9E3779B9 & 0xFFFFFFFF) & 0xFFFFFFFF;
        paramInt -= 1;
      }
    }
    catch (IOException paramArrayOfByte)
    {
    }
    return null;
  }

  private boolean decrypt8Bytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    boolean bool2 = true;
    for (this.pos = 0; ; this.pos += 1)
    {
      boolean bool1;
      if (this.pos >= 8)
      {
        this.prePlain = decipher(this.prePlain);
        if (this.prePlain != null)
          break;
        bool1 = false;
      }
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.contextStart + this.pos >= paramInt2);
      byte[] arrayOfByte = this.prePlain;
      int i = this.pos;
      arrayOfByte[i] = ((byte)(arrayOfByte[i] ^ paramArrayOfByte[(this.crypt + paramInt1 + this.pos)]));
    }
    this.contextStart += 8;
    this.crypt += 8;
    this.pos = 0;
    return true;
  }

  private byte[] encipher(byte[] paramArrayOfByte)
  {
    try
    {
      long l2 = getUnsignedInt(paramArrayOfByte, 0, 4);
      long l1 = getUnsignedInt(paramArrayOfByte, 4, 4);
      long l4 = getUnsignedInt(this.key, 0, 4);
      long l5 = getUnsignedInt(this.key, 4, 4);
      long l6 = getUnsignedInt(this.key, 8, 4);
      long l7 = getUnsignedInt(this.key, 12, 4);
      long l3 = 0L;
      int i = 16;
      while (true)
      {
        if (i <= 0)
        {
          paramArrayOfByte = new ByteArrayOutputStream(8);
          DataOutputStream localDataOutputStream = new DataOutputStream(paramArrayOfByte);
          localDataOutputStream.writeInt((int)l2);
          localDataOutputStream.writeInt((int)l1);
          localDataOutputStream.close();
          paramArrayOfByte = paramArrayOfByte.toByteArray();
          return paramArrayOfByte;
        }
        l3 = l3 + (0x9E3779B9 & 0xFFFFFFFF) & 0xFFFFFFFF;
        l2 = l2 + ((l1 << 4) + l4 ^ l1 + l3 ^ (l1 >>> 5) + l5) & 0xFFFFFFFF;
        l1 = l1 + ((l2 << 4) + l6 ^ l2 + l3 ^ (l2 >>> 5) + l7) & 0xFFFFFFFF;
        i -= 1;
      }
    }
    catch (IOException paramArrayOfByte)
    {
    }
    return null;
  }

  private void encrypt8Bytes()
  {
    this.pos = 0;
    if (this.pos >= 8)
      System.arraycopy(encipher(this.plain), 0, this.out, this.crypt, 8);
    for (this.pos = 0; ; this.pos += 1)
    {
      if (this.pos >= 8)
      {
        System.arraycopy(this.plain, 0, this.prePlain, 0, 8);
        this.preCrypt = this.crypt;
        this.crypt += 8;
        this.pos = 0;
        this.header = false;
        return;
        if (this.header)
        {
          arrayOfByte = this.plain;
          i = this.pos;
          arrayOfByte[i] = ((byte)(arrayOfByte[i] ^ this.prePlain[this.pos]));
        }
        while (true)
        {
          this.pos += 1;
          break;
          arrayOfByte = this.plain;
          i = this.pos;
          arrayOfByte[i] = ((byte)(arrayOfByte[i] ^ this.out[(this.preCrypt + this.pos)]));
        }
      }
      byte[] arrayOfByte = this.out;
      int i = this.crypt + this.pos;
      arrayOfByte[i] = ((byte)(arrayOfByte[i] ^ this.prePlain[this.pos]));
    }
  }

  private byte[] getRandomByte(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    this.random.nextBytes(arrayOfByte);
    return arrayOfByte;
  }

  public static long getUnsignedInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l = 0L;
    if (paramInt2 > 8)
      paramInt2 = paramInt1 + 8;
    while (true)
      if (paramInt1 >= paramInt2)
      {
        return 0xFFFFFFFF & l | l >>> 32;
        paramInt2 = paramInt1 + paramInt2;
      }
      else
      {
        l = l << 8 | paramArrayOfByte[paramInt1] & 0xFF;
        paramInt1 += 1;
      }
  }

  private int rand()
  {
    return this.random.nextInt();
  }

  public byte[] decrypt(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    this.preCrypt = 0;
    this.crypt = 0;
    this.key = paramArrayOfByte2;
    paramArrayOfByte2 = new byte[paramInt1 + 8];
    if ((paramInt2 % 8 != 0) || (paramInt2 < 16));
    int k;
    do
    {
      return null;
      this.prePlain = decipher(paramArrayOfByte1, paramInt1);
      this.pos = (this.prePlain[0] & 0x7);
      k = paramInt2 - this.pos - 10;
    }
    while (k < 0);
    int i = paramInt1;
    label81: int j;
    if (i >= paramArrayOfByte2.length)
    {
      this.out = new byte[k];
      this.preCrypt = 0;
      this.crypt = 8;
      this.contextStart = 8;
      this.pos += 1;
      this.padding = 1;
      label129: if (this.padding <= 2)
        break label183;
      j = 0;
      i = k;
      label144: if (i != 0)
        break label236;
    }
    for (this.padding = 1; ; this.padding += 1)
    {
      if (this.padding >= 8)
      {
        return this.out;
        paramArrayOfByte2[i] = 0;
        i += 1;
        break label81;
        label183: if (this.pos < 8)
        {
          this.pos += 1;
          this.padding += 1;
        }
        if (this.pos != 8)
          break label129;
        paramArrayOfByte2 = paramArrayOfByte1;
        if (decrypt8Bytes(paramArrayOfByte1, paramInt1, paramInt2))
          break label129;
        return null;
        label236: int m = i;
        k = j;
        if (this.pos < 8)
        {
          this.out[j] = ((byte)(paramArrayOfByte2[(this.preCrypt + paramInt1 + this.pos)] ^ this.prePlain[this.pos]));
          k = j + 1;
          m = i - 1;
          this.pos += 1;
        }
        i = m;
        j = k;
        if (this.pos != 8)
          break label144;
        paramArrayOfByte2 = paramArrayOfByte1;
        this.preCrypt = (this.crypt - 8);
        i = m;
        j = k;
        if (decrypt8Bytes(paramArrayOfByte1, paramInt1, paramInt2))
          break label144;
        return null;
      }
      if (this.pos < 8)
      {
        if ((paramArrayOfByte2[(this.preCrypt + paramInt1 + this.pos)] ^ this.prePlain[this.pos]) != 0)
          break;
        this.pos += 1;
      }
      if (this.pos == 8)
      {
        paramArrayOfByte2 = paramArrayOfByte1;
        this.preCrypt = this.crypt;
        if (!decrypt8Bytes(paramArrayOfByte1, paramInt1, paramInt2))
          break;
      }
    }
  }

  public byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return decrypt(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte2);
  }

  public byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    paramArrayOfByte2 = decrypt(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte2);
    paramArrayOfByte1 = paramArrayOfByte2;
    if (paramArrayOfByte2 == null)
      paramArrayOfByte1 = getRandomByte(paramInt);
    return paramArrayOfByte1;
  }

  public byte[] encrypt(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2)
  {
    this.plain = new byte[8];
    this.prePlain = new byte[8];
    this.pos = 1;
    this.padding = 0;
    this.preCrypt = 0;
    this.crypt = 0;
    this.key = paramArrayOfByte2;
    this.header = true;
    this.pos = ((paramInt2 + 10) % 8);
    if (this.pos != 0)
      this.pos = (8 - this.pos);
    this.out = new byte[this.pos + paramInt2 + 10];
    this.plain[0] = ((byte)(rand() & 0xF8 | this.pos));
    int i = 1;
    if (i > this.pos)
    {
      this.pos += 1;
      i = 0;
      label136: if (i < 8)
        break label204;
      this.padding = 1;
    }
    while (true)
    {
      if (this.padding > 2)
      {
        if (paramInt2 > 0)
          break label290;
        this.padding = 1;
        label165: if (this.padding <= 7)
          break label355;
        return this.out;
        this.plain[i] = ((byte)(rand() & 0xFF));
        i += 1;
        break;
        label204: this.prePlain[i] = 0;
        i += 1;
        break label136;
      }
      if (this.pos < 8)
      {
        paramArrayOfByte2 = this.plain;
        i = this.pos;
        this.pos = (i + 1);
        paramArrayOfByte2[i] = ((byte)(rand() & 0xFF));
        this.padding += 1;
      }
      if (this.pos == 8)
        encrypt8Bytes();
    }
    label290: if (this.pos < 8)
    {
      paramArrayOfByte2 = this.plain;
      int j = this.pos;
      this.pos = (j + 1);
      i = paramInt1 + 1;
      paramArrayOfByte2[j] = paramArrayOfByte1[paramInt1];
      paramInt2 -= 1;
      paramInt1 = i;
    }
    while (true)
    {
      if (this.pos == 8)
        encrypt8Bytes();
      break;
      label355: if (this.pos < 8)
      {
        paramArrayOfByte1 = this.plain;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte1[paramInt1] = 0;
        this.padding += 1;
      }
      if (this.pos != 8)
        break label165;
      encrypt8Bytes();
      break label165;
    }
  }

  public byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    return encrypt(paramArrayOfByte1, 0, paramArrayOfByte1.length, paramArrayOfByte2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.tools.Cryptor
 * JD-Core Version:    0.6.2
 */