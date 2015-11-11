package com.umeng.message.proguard;

import java.math.BigInteger;

public class u extends v
{
  static final byte[] a = { 13, 10 };
  private static final int m = 6;
  private static final int n = 3;
  private static final int o = 4;
  private static final byte[] p = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] q = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private static final byte[] r = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
  private static final int s = 63;
  private final byte[] t;
  private final byte[] u;
  private final byte[] v;
  private final int w;
  private final int x;
  private int y;

  public u()
  {
    this(0);
  }

  public u(int paramInt)
  {
    this(paramInt, a);
  }

  public u(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, false);
  }

  public u(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
  }

  public u(boolean paramBoolean)
  {
    this(76, a, paramBoolean);
  }

  public static boolean a(byte paramByte)
  {
    return (paramByte == 61) || ((paramByte >= 0) && (paramByte < r.length) && (r[paramByte] != -1));
  }

  public static boolean a(String paramString)
  {
    return b(s.f(paramString));
  }

  public static boolean a(byte[] paramArrayOfByte)
  {
    return b(paramArrayOfByte);
  }

  public static byte[] a(BigInteger paramBigInteger)
  {
    if (paramBigInteger == null)
      throw new NullPointerException("encodeInteger called with null parameter");
    return a(b(paramBigInteger), false);
  }

  public static byte[] a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return a(paramArrayOfByte, paramBoolean, false);
  }

  public static byte[] a(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2)
  {
    return a(paramArrayOfByte, paramBoolean1, paramBoolean2, 2147483647);
  }

  public static byte[] a(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return paramArrayOfByte;
    if (paramBoolean1);
    for (u localu = new u(paramBoolean2); ; localu = new u(0, a, paramBoolean2))
    {
      long l = localu.o(paramArrayOfByte);
      if (l <= paramInt)
        break;
      throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + l + ") than the specified maximum size of " + paramInt);
    }
    return localu.l(paramArrayOfByte);
  }

  public static boolean b(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if ((!a(paramArrayOfByte[i])) && (!c(paramArrayOfByte[i])))
        return false;
      i += 1;
    }
    return true;
  }

  public static byte[] b(String paramString)
  {
    return new u().c(paramString);
  }

  static byte[] b(BigInteger paramBigInteger)
  {
    int i1 = paramBigInteger.bitLength() + 7 >> 3 << 3;
    byte[] arrayOfByte = paramBigInteger.toByteArray();
    if ((paramBigInteger.bitLength() % 8 != 0) && (paramBigInteger.bitLength() / 8 + 1 == i1 / 8))
      return arrayOfByte;
    int j = 0;
    int k = arrayOfByte.length;
    int i = k;
    if (paramBigInteger.bitLength() % 8 == 0)
    {
      j = 1;
      i = k - 1;
    }
    k = i1 / 8;
    paramBigInteger = new byte[i1 / 8];
    System.arraycopy(arrayOfByte, j, paramBigInteger, k - i, i);
    return paramBigInteger;
  }

  public static byte[] c(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, false);
  }

  public static String d(byte[] paramArrayOfByte)
  {
    return s.f(a(paramArrayOfByte, false));
  }

  public static byte[] e(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, false, true);
  }

  public static String f(byte[] paramArrayOfByte)
  {
    return s.f(a(paramArrayOfByte, false, true));
  }

  public static byte[] g(byte[] paramArrayOfByte)
  {
    return a(paramArrayOfByte, true);
  }

  public static byte[] h(byte[] paramArrayOfByte)
  {
    return new u().k(paramArrayOfByte);
  }

  public static BigInteger i(byte[] paramArrayOfByte)
  {
    return new BigInteger(1, h(paramArrayOfByte));
  }

  void a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.j);
    while (true)
    {
      return;
      if (paramInt2 < 0)
      {
        this.j = true;
        if ((this.l != 0) || (this.g != 0))
        {
          a(this.x);
          paramInt1 = this.i;
          switch (this.l)
          {
          default:
          case 1:
          case 2:
          }
          while (true)
          {
            paramInt2 = this.k;
            this.k = (this.i - paramInt1 + paramInt2);
            if ((this.g <= 0) || (this.k <= 0))
              break;
            System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
            this.i += this.v.length;
            return;
            paramArrayOfByte = this.h;
            paramInt2 = this.i;
            this.i = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.t[(this.y >> 2 & 0x3F)];
            paramArrayOfByte = this.h;
            paramInt2 = this.i;
            this.i = (paramInt2 + 1);
            paramArrayOfByte[paramInt2] = this.t[(this.y << 4 & 0x3F)];
            if (this.t == p)
            {
              paramArrayOfByte = this.h;
              paramInt2 = this.i;
              this.i = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = 61;
              paramArrayOfByte = this.h;
              paramInt2 = this.i;
              this.i = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = 61;
              continue;
              paramArrayOfByte = this.h;
              paramInt2 = this.i;
              this.i = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = this.t[(this.y >> 10 & 0x3F)];
              paramArrayOfByte = this.h;
              paramInt2 = this.i;
              this.i = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = this.t[(this.y >> 4 & 0x3F)];
              paramArrayOfByte = this.h;
              paramInt2 = this.i;
              this.i = (paramInt2 + 1);
              paramArrayOfByte[paramInt2] = this.t[(this.y << 2 & 0x3F)];
              if (this.t == p)
              {
                paramArrayOfByte = this.h;
                paramInt2 = this.i;
                this.i = (paramInt2 + 1);
                paramArrayOfByte[paramInt2] = 61;
              }
            }
          }
        }
      }
      else
      {
        int i = 0;
        while (i < paramInt2)
        {
          a(this.x);
          this.l = ((this.l + 1) % 3);
          int k = paramArrayOfByte[paramInt1];
          int j = k;
          if (k < 0)
            j = k + 256;
          this.y = (j + (this.y << 8));
          if (this.l == 0)
          {
            byte[] arrayOfByte = this.h;
            j = this.i;
            this.i = (j + 1);
            arrayOfByte[j] = this.t[(this.y >> 18 & 0x3F)];
            arrayOfByte = this.h;
            j = this.i;
            this.i = (j + 1);
            arrayOfByte[j] = this.t[(this.y >> 12 & 0x3F)];
            arrayOfByte = this.h;
            j = this.i;
            this.i = (j + 1);
            arrayOfByte[j] = this.t[(this.y >> 6 & 0x3F)];
            arrayOfByte = this.h;
            j = this.i;
            this.i = (j + 1);
            arrayOfByte[j] = this.t[(this.y & 0x3F)];
            this.k += 4;
            if ((this.g > 0) && (this.g <= this.k))
            {
              System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
              this.i += this.v.length;
              this.k = 0;
            }
          }
          i += 1;
          paramInt1 += 1;
        }
      }
    }
  }

  public boolean a()
  {
    return this.t == q;
  }

  void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.j)
      return;
    if (paramInt2 < 0)
      this.j = true;
    int i = 0;
    while (true)
    {
      int j;
      if (i < paramInt2)
      {
        a(this.w);
        j = paramArrayOfByte[paramInt1];
        if (j == 61)
          this.j = true;
      }
      else
      {
        if ((!this.j) || (this.l == 0))
          break;
        a(this.w);
      }
      switch (this.l)
      {
      default:
        return;
      case 2:
        this.y >>= 4;
        paramArrayOfByte = this.h;
        paramInt1 = this.i;
        this.i = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = ((byte)(this.y & 0xFF));
        return;
        if ((j >= 0) && (j < r.length))
        {
          j = r[j];
          if (j >= 0)
          {
            this.l = ((this.l + 1) % 4);
            this.y = (j + (this.y << 6));
            if (this.l == 0)
            {
              byte[] arrayOfByte = this.h;
              j = this.i;
              this.i = (j + 1);
              arrayOfByte[j] = ((byte)(this.y >> 16 & 0xFF));
              arrayOfByte = this.h;
              j = this.i;
              this.i = (j + 1);
              arrayOfByte[j] = ((byte)(this.y >> 8 & 0xFF));
              arrayOfByte = this.h;
              j = this.i;
              this.i = (j + 1);
              arrayOfByte[j] = ((byte)(this.y & 0xFF));
            }
          }
        }
        i += 1;
        paramInt1 += 1;
      case 3:
      }
    }
    this.y >>= 2;
    paramArrayOfByte = this.h;
    paramInt1 = this.i;
    this.i = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = ((byte)(this.y >> 8 & 0xFF));
    paramArrayOfByte = this.h;
    paramInt1 = this.i;
    this.i = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = ((byte)(this.y & 0xFF));
  }

  protected boolean b(byte paramByte)
  {
    return (paramByte >= 0) && (paramByte < this.u.length) && (this.u[paramByte] != -1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.u
 * JD-Core Version:    0.6.2
 */