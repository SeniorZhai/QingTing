package com.tendcloud.tenddata;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class q
{
  protected byte[] a = new byte[9];
  protected OutputStream b;

  public q(OutputStream paramOutputStream)
  {
    this.b = paramOutputStream;
  }

  public static int b(double paramDouble)
  {
    return 9;
  }

  public static int b(float paramFloat)
  {
    return 5;
  }

  public static int b(boolean paramBoolean)
  {
    return 1;
  }

  public static int b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return 1;
    return c(paramArrayOfByte.length) + paramArrayOfByte.length;
  }

  public static int c(int paramInt)
  {
    if (paramInt < 16)
      return 1;
    if (paramInt < 65536)
      return 3;
    return 5;
  }

  public static int c(long paramLong)
  {
    if (paramLong < -32L)
      if (paramLong < -32768L)
        if (paramLong >= -2147483648L);
    do
    {
      return 9;
      return 5;
      if (paramLong < -128L)
        return 3;
      return 2;
      if (paramLong < 128L)
        return 1;
      if (paramLong < 65536L)
      {
        if (paramLong < 256L)
          return 2;
        return 3;
      }
    }
    while (paramLong >= 4294967296L);
    return 5;
  }

  public static int c(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      int i = c(paramString.length);
      int j = paramString.length;
      return j + i;
    }
    catch (Exception paramString)
    {
    }
    return 0;
  }

  public q a()
  {
    this.b.write(-62);
    return this;
  }

  public q a(byte paramByte)
  {
    return b(paramByte);
  }

  public q a(double paramDouble)
  {
    return c(paramDouble);
  }

  public q a(float paramFloat)
  {
    return c(paramFloat);
  }

  public q a(int paramInt)
  {
    return d(paramInt);
  }

  public q a(long paramLong)
  {
    return b(paramLong);
  }

  public q a(g paramg)
  {
    if (paramg == null)
      return b();
    paramg.a(this);
    return this;
  }

  public q a(Boolean paramBoolean)
  {
    if (paramBoolean == null)
      return b();
    if (paramBoolean.booleanValue())
      return c();
    return a();
  }

  public q a(Byte paramByte)
  {
    if (paramByte == null)
      return b();
    return b(paramByte.byteValue());
  }

  public q a(Double paramDouble)
  {
    if (paramDouble == null)
      return b();
    return c(paramDouble.doubleValue());
  }

  public q a(Float paramFloat)
  {
    if (paramFloat == null)
      return b();
    return c(paramFloat.floatValue());
  }

  public q a(Integer paramInteger)
  {
    if (paramInteger == null)
      return b();
    return d(paramInteger.intValue());
  }

  public q a(Long paramLong)
  {
    if (paramLong == null)
      return b();
    return b(paramLong.longValue());
  }

  public q a(Short paramShort)
  {
    if (paramShort == null)
      return b();
    return b(paramShort.shortValue());
  }

  public q a(String paramString)
  {
    if (paramString == null)
      return b("");
    return b(paramString);
  }

  public q a(BigInteger paramBigInteger)
  {
    if (paramBigInteger == null)
      return b();
    return b(paramBigInteger);
  }

  public q a(ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer == null)
      return b();
    f(paramByteBuffer.remaining());
    return b(paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining());
  }

  public q a(List paramList)
  {
    if (paramList == null)
    {
      paramList = b();
      return paramList;
    }
    b(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      paramList = this;
      if (!localIterator.hasNext())
        break;
      a((String)localIterator.next());
    }
  }

  public q a(Map paramMap)
  {
    if (paramMap == null)
    {
      paramMap = b();
      return paramMap;
    }
    Object localObject1 = new HashMap();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject2 = paramMap.get(str);
      if ((localObject2 instanceof String))
        ((Map)localObject1).put(str, localObject2.toString());
      else if ((localObject2 instanceof Number))
        ((Map)localObject1).put(str, Double.valueOf(((Number)localObject2).doubleValue()));
    }
    e(((Map)localObject1).size());
    localObject1 = ((Map)localObject1).entrySet().iterator();
    while (true)
    {
      paramMap = this;
      if (!((Iterator)localObject1).hasNext())
        break;
      paramMap = (Map.Entry)((Iterator)localObject1).next();
      a((String)paramMap.getKey());
      paramMap = paramMap.getValue();
      if ((paramMap instanceof Number))
        a(((Number)paramMap).doubleValue());
      else if ((paramMap instanceof String))
        a(paramMap.toString());
    }
  }

  public q a(short paramShort)
  {
    return b(paramShort);
  }

  public q a(boolean paramBoolean)
  {
    if (paramBoolean)
      return c();
    return a();
  }

  public q a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return b();
    f(paramArrayOfByte.length);
    return d(paramArrayOfByte);
  }

  public q a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    f(paramInt2);
    return b(paramArrayOfByte, paramInt1, paramInt2);
  }

  public q a(Long[] paramArrayOfLong)
  {
    q localq;
    if ((paramArrayOfLong == null) || (paramArrayOfLong.length == 0))
    {
      localq = b();
      return localq;
    }
    b(paramArrayOfLong.length);
    int j = paramArrayOfLong.length;
    int i = 0;
    while (true)
    {
      localq = this;
      if (i >= j)
        break;
      a(paramArrayOfLong[i].longValue());
      i += 1;
    }
  }

  public q b()
  {
    this.b.write(-64);
    return this;
  }

  public q b(byte paramByte)
  {
    if (paramByte < -32)
    {
      this.a[0] = -48;
      this.a[1] = paramByte;
      this.b.write(this.a, 0, 2);
      return this;
    }
    this.b.write(paramByte);
    return this;
  }

  public q b(int paramInt)
  {
    if (paramInt < 16)
    {
      this.b.write((byte)(paramInt | 0x90));
      return this;
    }
    if (paramInt < 65536)
    {
      this.a[0] = -36;
      this.a[1] = ((byte)(paramInt >> 8));
      this.a[2] = ((byte)(paramInt >> 0));
      this.b.write(this.a, 0, 3);
      return this;
    }
    this.a[0] = -35;
    this.a[1] = ((byte)(paramInt >> 24));
    this.a[2] = ((byte)(paramInt >> 16));
    this.a[3] = ((byte)(paramInt >> 8));
    this.a[4] = ((byte)(paramInt >> 0));
    this.b.write(this.a, 0, 5);
    return this;
  }

  public q b(long paramLong)
  {
    if (paramLong < -32L)
    {
      if (paramLong < -32768L)
      {
        if (paramLong < -2147483648L)
        {
          this.a[0] = -45;
          this.a[1] = ((byte)(int)(paramLong >> 56));
          this.a[2] = ((byte)(int)(paramLong >> 48));
          this.a[3] = ((byte)(int)(paramLong >> 40));
          this.a[4] = ((byte)(int)(paramLong >> 32));
          this.a[5] = ((byte)(int)(paramLong >> 24));
          this.a[6] = ((byte)(int)(paramLong >> 16));
          this.a[7] = ((byte)(int)(paramLong >> 8));
          this.a[8] = ((byte)(int)(paramLong >> 0));
          this.b.write(this.a, 0, 9);
          return this;
        }
        this.a[0] = -46;
        this.a[1] = ((byte)(int)(paramLong >> 24));
        this.a[2] = ((byte)(int)(paramLong >> 16));
        this.a[3] = ((byte)(int)(paramLong >> 8));
        this.a[4] = ((byte)(int)(paramLong >> 0));
        this.b.write(this.a, 0, 5);
        return this;
      }
      if (paramLong < -128L)
      {
        this.a[0] = -47;
        this.a[1] = ((byte)(int)(paramLong >> 8));
        this.a[2] = ((byte)(int)(paramLong >> 0));
        this.b.write(this.a, 0, 3);
        return this;
      }
      this.a[0] = -48;
      this.a[1] = ((byte)(int)paramLong);
      this.b.write(this.a, 0, 2);
      return this;
    }
    if (paramLong < 128L)
    {
      this.b.write((byte)(int)paramLong);
      return this;
    }
    if (paramLong < 65536L)
    {
      if (paramLong < 256L)
      {
        this.a[0] = -52;
        this.a[1] = ((byte)(int)paramLong);
        this.b.write(this.a, 0, 2);
        return this;
      }
      this.a[0] = -51;
      this.a[1] = ((byte)(int)((0xFF00 & paramLong) >> 8));
      this.a[2] = ((byte)(int)((0xFF & paramLong) >> 0));
      this.b.write(this.a, 0, 3);
      return this;
    }
    if (paramLong < 4294967296L)
    {
      this.a[0] = -50;
      this.a[1] = ((byte)(int)((0xFF000000 & paramLong) >> 24));
      this.a[2] = ((byte)(int)((0xFF0000 & paramLong) >> 16));
      this.a[3] = ((byte)(int)((0xFF00 & paramLong) >> 8));
      this.a[4] = ((byte)(int)((0xFF & paramLong) >> 0));
      this.b.write(this.a, 0, 5);
      return this;
    }
    this.a[0] = -49;
    this.a[1] = ((byte)(int)(paramLong >> 56));
    this.a[2] = ((byte)(int)(paramLong >> 48));
    this.a[3] = ((byte)(int)(paramLong >> 40));
    this.a[4] = ((byte)(int)(paramLong >> 32));
    this.a[5] = ((byte)(int)(paramLong >> 24));
    this.a[6] = ((byte)(int)(paramLong >> 16));
    this.a[7] = ((byte)(int)(paramLong >> 8));
    this.a[8] = ((byte)(int)(paramLong >> 0));
    this.b.write(this.a, 0, 9);
    return this;
  }

  public q b(String paramString)
  {
    paramString = paramString.getBytes("UTF-8");
    f(paramString.length);
    return d(paramString);
  }

  public q b(BigInteger paramBigInteger)
  {
    if (paramBigInteger.bitLength() <= 63)
      return b(paramBigInteger.longValue());
    if ((paramBigInteger.bitLength() <= 64) && (paramBigInteger.signum() >= 0))
    {
      this.a[0] = -49;
      paramBigInteger = paramBigInteger.toByteArray();
      this.a[1] = paramBigInteger[(paramBigInteger.length - 8)];
      this.a[2] = paramBigInteger[(paramBigInteger.length - 7)];
      this.a[3] = paramBigInteger[(paramBigInteger.length - 6)];
      this.a[4] = paramBigInteger[(paramBigInteger.length - 5)];
      this.a[5] = paramBigInteger[(paramBigInteger.length - 4)];
      this.a[6] = paramBigInteger[(paramBigInteger.length - 3)];
      this.a[7] = paramBigInteger[(paramBigInteger.length - 2)];
      this.a[8] = paramBigInteger[(paramBigInteger.length - 1)];
      this.b.write(this.a, 0, 9);
      return this;
    }
    throw new IOException("can't pack BigInteger larger than 0xffffffffffffffff");
  }

  public q b(ByteBuffer paramByteBuffer)
  {
    f(paramByteBuffer.remaining());
    return b(paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining());
  }

  public q b(short paramShort)
  {
    if (paramShort < -32)
    {
      if (paramShort < -128)
      {
        this.a[0] = -47;
        this.a[1] = ((byte)(paramShort >> 8));
        this.a[2] = ((byte)(paramShort >> 0));
        this.b.write(this.a, 0, 3);
        return this;
      }
      this.a[0] = -48;
      this.a[1] = ((byte)paramShort);
      this.b.write(this.a, 0, 2);
      return this;
    }
    if (paramShort < 128)
    {
      this.b.write((byte)paramShort);
      return this;
    }
    if (paramShort < 256)
    {
      this.a[0] = -52;
      this.a[1] = ((byte)paramShort);
      this.b.write(this.a, 0, 2);
      return this;
    }
    this.a[0] = -51;
    this.a[1] = ((byte)(paramShort >> 8));
    this.a[2] = ((byte)(paramShort >> 0));
    this.b.write(this.a, 0, 3);
    return this;
  }

  public q b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.b.write(paramArrayOfByte, paramInt1, paramInt2);
    return this;
  }

  public q c()
  {
    this.b.write(-61);
    return this;
  }

  public q c(double paramDouble)
  {
    this.a[0] = -53;
    long l = Double.doubleToRawLongBits(paramDouble);
    this.a[1] = ((byte)(int)(l >> 56));
    this.a[2] = ((byte)(int)(l >> 48));
    this.a[3] = ((byte)(int)(l >> 40));
    this.a[4] = ((byte)(int)(l >> 32));
    this.a[5] = ((byte)(int)(l >> 24));
    this.a[6] = ((byte)(int)(l >> 16));
    this.a[7] = ((byte)(int)(l >> 8));
    this.a[8] = ((byte)(int)(l >> 0));
    this.b.write(this.a, 0, 9);
    return this;
  }

  public q c(float paramFloat)
  {
    this.a[0] = -54;
    int i = Float.floatToRawIntBits(paramFloat);
    this.a[1] = ((byte)(i >> 24));
    this.a[2] = ((byte)(i >> 16));
    this.a[3] = ((byte)(i >> 8));
    this.a[4] = ((byte)(i >> 0));
    this.b.write(this.a, 0, 5);
    return this;
  }

  public q c(boolean paramBoolean)
  {
    if (paramBoolean)
      return c();
    return a();
  }

  public q c(byte[] paramArrayOfByte)
  {
    f(paramArrayOfByte.length);
    return b(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public q d(int paramInt)
  {
    if (paramInt < -32)
    {
      if (paramInt < -32768)
      {
        this.a[0] = -46;
        this.a[1] = ((byte)(paramInt >> 24));
        this.a[2] = ((byte)(paramInt >> 16));
        this.a[3] = ((byte)(paramInt >> 8));
        this.a[4] = ((byte)(paramInt >> 0));
        this.b.write(this.a, 0, 5);
        return this;
      }
      if (paramInt < -128)
      {
        this.a[0] = -47;
        this.a[1] = ((byte)(paramInt >> 8));
        this.a[2] = ((byte)(paramInt >> 0));
        this.b.write(this.a, 0, 3);
        return this;
      }
      this.a[0] = -48;
      this.a[1] = ((byte)paramInt);
      this.b.write(this.a, 0, 2);
      return this;
    }
    if (paramInt < 128)
    {
      this.b.write((byte)paramInt);
      return this;
    }
    if (paramInt < 256)
    {
      this.a[0] = -52;
      this.a[1] = ((byte)paramInt);
      this.b.write(this.a, 0, 2);
      return this;
    }
    if (paramInt < 65536)
    {
      this.a[0] = -51;
      this.a[1] = ((byte)(paramInt >> 8));
      this.a[2] = ((byte)(paramInt >> 0));
      this.b.write(this.a, 0, 3);
      return this;
    }
    this.a[0] = -50;
    this.a[1] = ((byte)(paramInt >> 24));
    this.a[2] = ((byte)(paramInt >> 16));
    this.a[3] = ((byte)(paramInt >> 8));
    this.a[4] = ((byte)(paramInt >> 0));
    this.b.write(this.a, 0, 5);
    return this;
  }

  public q d(byte[] paramArrayOfByte)
  {
    this.b.write(paramArrayOfByte);
    return this;
  }

  public q e(int paramInt)
  {
    if (paramInt < 16)
    {
      this.b.write((byte)(paramInt | 0x80));
      return this;
    }
    if (paramInt < 65536)
    {
      this.a[0] = -34;
      this.a[1] = ((byte)(paramInt >> 8));
      this.a[2] = ((byte)(paramInt >> 0));
      this.b.write(this.a, 0, 3);
      return this;
    }
    this.a[0] = -33;
    this.a[1] = ((byte)(paramInt >> 24));
    this.a[2] = ((byte)(paramInt >> 16));
    this.a[3] = ((byte)(paramInt >> 8));
    this.a[4] = ((byte)(paramInt >> 0));
    this.b.write(this.a, 0, 5);
    return this;
  }

  public q f(int paramInt)
  {
    if (paramInt < 32)
    {
      this.b.write((byte)(paramInt | 0xA0));
      return this;
    }
    if (paramInt < 65536)
    {
      this.a[0] = -38;
      this.a[1] = ((byte)(paramInt >> 8));
      this.a[2] = ((byte)(paramInt >> 0));
      this.b.write(this.a, 0, 3);
      return this;
    }
    this.a[0] = -37;
    this.a[1] = ((byte)(paramInt >> 24));
    this.a[2] = ((byte)(paramInt >> 16));
    this.a[3] = ((byte)(paramInt >> 8));
    this.a[4] = ((byte)(paramInt >> 0));
    this.b.write(this.a, 0, 5);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.q
 * JD-Core Version:    0.6.2
 */