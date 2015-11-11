package com.umeng.message.proguard;

import java.io.UnsupportedEncodingException;

public class b
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 4;
  public static final int e = 8;
  public static final int f = 16;

  static
  {
    if (!b.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      g = bool;
      return;
    }
  }

  public static byte[] a(String paramString, int paramInt)
  {
    return a(paramString.getBytes(), paramInt);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    return a(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    b localb = new b(paramInt3, new byte[paramInt2 * 3 / 4]);
    if (!localb.a(paramArrayOfByte, paramInt1, paramInt2, true))
      throw new IllegalArgumentException("bad base-64");
    if (localb.b == localb.a.length)
      return localb.a;
    paramArrayOfByte = new byte[localb.b];
    System.arraycopy(localb.a, 0, paramArrayOfByte, 0, localb.b);
    return paramArrayOfByte;
  }

  public static String b(byte[] paramArrayOfByte, int paramInt)
  {
    try
    {
      paramArrayOfByte = new String(c(paramArrayOfByte, paramInt), "US-ASCII");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new AssertionError(paramArrayOfByte);
  }

  public static String b(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      paramArrayOfByte = new String(c(paramArrayOfByte, paramInt1, paramInt2, paramInt3), "US-ASCII");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new AssertionError(paramArrayOfByte);
  }

  public static byte[] c(byte[] paramArrayOfByte, int paramInt)
  {
    return c(paramArrayOfByte, 0, paramArrayOfByte.length, paramInt);
  }

  public static byte[] c(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    c localc = new c(paramInt3, null);
    int i = paramInt2 / 3 * 4;
    int j;
    if (localc.e)
    {
      paramInt3 = i;
      if (paramInt2 % 3 > 0)
        paramInt3 = i + 4;
      i = paramInt3;
      if (localc.f)
      {
        i = paramInt3;
        if (paramInt2 > 0)
        {
          j = (paramInt2 - 1) / 57;
          if (!localc.g)
            break label186;
        }
      }
    }
    label186: for (i = 2; ; i = 1)
    {
      i = paramInt3 + i * (j + 1);
      localc.a = new byte[i];
      localc.a(paramArrayOfByte, paramInt1, paramInt2, true);
      if ((g) || (localc.b == i))
        break label192;
      throw new AssertionError();
      paramInt3 = i;
      switch (paramInt2 % 3)
      {
      case 0:
      default:
        paramInt3 = i;
        break;
      case 1:
        paramInt3 = i + 2;
        break;
      case 2:
        paramInt3 = i + 3;
        break;
      }
    }
    label192: return localc.a;
  }

  static abstract class a
  {
    public byte[] a;
    public int b;

    public abstract int a(int paramInt);

    public abstract boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean);
  }

  static class b extends b.a
  {
    private static final int[] c = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    private static final int[] d = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    private static final int e = -1;
    private static final int f = -2;
    private int g;
    private int h;
    private final int[] i;

    public b(int paramInt, byte[] paramArrayOfByte)
    {
      this.a = paramArrayOfByte;
      if ((paramInt & 0x8) == 0);
      for (paramArrayOfByte = c; ; paramArrayOfByte = d)
      {
        this.i = paramArrayOfByte;
        this.g = 0;
        this.h = 0;
        return;
      }
    }

    public int a(int paramInt)
    {
      return paramInt * 3 / 4 + 10;
    }

    public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (this.g == 6)
        return false;
      int i3 = paramInt2 + paramInt1;
      int k = this.g;
      paramInt2 = this.h;
      int j = 0;
      byte[] arrayOfByte = this.a;
      int[] arrayOfInt = this.i;
      int m;
      int n;
      int i2;
      if (paramInt1 < i3)
      {
        m = j;
        n = paramInt2;
        i2 = paramInt1;
        if (k == 0)
        {
          int i1 = paramInt1;
          for (paramInt1 = paramInt2; i1 + 4 <= i3; paramInt1 = paramInt2)
          {
            paramInt2 = arrayOfInt[(paramArrayOfByte[i1] & 0xFF)] << 18 | arrayOfInt[(paramArrayOfByte[(i1 + 1)] & 0xFF)] << 12 | arrayOfInt[(paramArrayOfByte[(i1 + 2)] & 0xFF)] << 6 | arrayOfInt[(paramArrayOfByte[(i1 + 3)] & 0xFF)];
            paramInt1 = paramInt2;
            if (paramInt2 < 0)
              break;
            arrayOfByte[(j + 2)] = ((byte)paramInt2);
            arrayOfByte[(j + 1)] = ((byte)(paramInt2 >> 8));
            arrayOfByte[j] = ((byte)(paramInt2 >> 16));
            j += 3;
            i1 += 4;
          }
          m = j;
          n = paramInt1;
          i2 = i1;
          if (i1 >= i3)
            paramInt2 = paramInt1;
        }
      }
      while (true)
      {
        if (!paramBoolean)
        {
          this.g = k;
          this.h = paramInt2;
          this.b = j;
          return true;
          paramInt2 = arrayOfInt[(paramArrayOfByte[i2] & 0xFF)];
          switch (k)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          }
          label292: label577: 
          do
          {
            do
            {
              paramInt1 = k;
              paramInt2 = n;
              j = m;
              while (true)
              {
                k = paramInt1;
                paramInt1 = i2 + 1;
                break;
                if (paramInt2 >= 0)
                {
                  paramInt1 = k + 1;
                  j = m;
                }
                else
                {
                  if (paramInt2 == -1)
                    break label292;
                  this.g = 6;
                  return false;
                  if (paramInt2 >= 0)
                  {
                    paramInt2 = n << 6 | paramInt2;
                    paramInt1 = k + 1;
                    j = m;
                  }
                  else
                  {
                    if (paramInt2 == -1)
                      break label292;
                    this.g = 6;
                    return false;
                    if (paramInt2 >= 0)
                    {
                      paramInt2 = n << 6 | paramInt2;
                      paramInt1 = k + 1;
                      j = m;
                    }
                    else if (paramInt2 == -2)
                    {
                      arrayOfByte[m] = ((byte)(n >> 4));
                      paramInt1 = 4;
                      j = m + 1;
                      paramInt2 = n;
                    }
                    else
                    {
                      if (paramInt2 == -1)
                        break label292;
                      this.g = 6;
                      return false;
                      if (paramInt2 >= 0)
                      {
                        paramInt2 = n << 6 | paramInt2;
                        arrayOfByte[(m + 2)] = ((byte)paramInt2);
                        arrayOfByte[(m + 1)] = ((byte)(paramInt2 >> 8));
                        arrayOfByte[m] = ((byte)(paramInt2 >> 16));
                        j = m + 3;
                        paramInt1 = 0;
                      }
                      else if (paramInt2 == -2)
                      {
                        arrayOfByte[(m + 1)] = ((byte)(n >> 2));
                        arrayOfByte[m] = ((byte)(n >> 10));
                        j = m + 2;
                        paramInt1 = 5;
                        paramInt2 = n;
                      }
                      else
                      {
                        if (paramInt2 == -1)
                          break label292;
                        this.g = 6;
                        return false;
                        if (paramInt2 != -2)
                          break label577;
                        paramInt1 = k + 1;
                        j = m;
                        paramInt2 = n;
                      }
                    }
                  }
                }
              }
            }
            while (paramInt2 == -1);
            this.g = 6;
            return false;
          }
          while (paramInt2 == -1);
          this.g = 6;
          return false;
        }
        paramInt1 = j;
        switch (k)
        {
        default:
          paramInt1 = j;
        case 0:
        case 1:
        case 2:
        case 3:
          while (true)
          {
            this.g = k;
            this.b = paramInt1;
            return true;
            this.g = 6;
            return false;
            arrayOfByte[j] = ((byte)(paramInt2 >> 4));
            paramInt1 = j + 1;
            continue;
            m = j + 1;
            arrayOfByte[j] = ((byte)(paramInt2 >> 10));
            paramInt1 = m + 1;
            arrayOfByte[m] = ((byte)(paramInt2 >> 2));
          }
        case 4:
        }
        this.g = 6;
        return false;
      }
    }
  }

  static class c extends b.a
  {
    public static final int c = 19;
    private static final byte[] i;
    private static final byte[] j;
    int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    private final byte[] k;
    private int l;
    private final byte[] m;

    static
    {
      if (!b.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        h = bool;
        i = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        j = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        return;
      }
    }

    public c(int paramInt, byte[] paramArrayOfByte)
    {
      this.a = paramArrayOfByte;
      boolean bool1;
      if ((paramInt & 0x1) == 0)
      {
        bool1 = true;
        this.e = bool1;
        if ((paramInt & 0x2) != 0)
          break label101;
        bool1 = true;
        label33: this.f = bool1;
        if ((paramInt & 0x4) == 0)
          break label106;
        bool1 = bool2;
        label47: this.g = bool1;
        if ((paramInt & 0x8) != 0)
          break label111;
        paramArrayOfByte = i;
        label63: this.m = paramArrayOfByte;
        this.k = new byte[2];
        this.d = 0;
        if (!this.f)
          break label118;
      }
      label101: label106: label111: label118: for (paramInt = 19; ; paramInt = -1)
      {
        this.l = paramInt;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label33;
        bool1 = false;
        break label47;
        paramArrayOfByte = j;
        break label63;
      }
    }

    public int a(int paramInt)
    {
      return paramInt * 8 / 5 + 10;
    }

    public boolean a(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
    {
      byte[] arrayOfByte1 = this.m;
      byte[] arrayOfByte2 = this.a;
      int n = 0;
      int i3 = this.l;
      int i5 = paramInt2 + paramInt1;
      int i2 = -1;
      label62: int i1;
      switch (this.d)
      {
      default:
        paramInt2 = paramInt1;
        paramInt1 = i3;
        i1 = paramInt2;
        if (i2 != -1)
        {
          arrayOfByte2[0] = arrayOfByte1[(i2 >> 18 & 0x3F)];
          arrayOfByte2[1] = arrayOfByte1[(i2 >> 12 & 0x3F)];
          arrayOfByte2[2] = arrayOfByte1[(i2 >> 6 & 0x3F)];
          n = 4;
          arrayOfByte2[3] = arrayOfByte1[(i2 & 0x3F)];
          i2 = i3 - 1;
          paramInt1 = i2;
          i1 = paramInt2;
          if (i2 == 0)
          {
            if (!this.g)
              break label1226;
            paramInt1 = 5;
            arrayOfByte2[4] = 13;
          }
        }
        break;
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        arrayOfByte2[paramInt1] = 10;
        i1 = 19;
        paramInt1 += 1;
        while (true)
        {
          label180: if (paramInt2 + 3 <= i5)
          {
            n = (paramArrayOfByte[paramInt2] & 0xFF) << 16 | (paramArrayOfByte[(paramInt2 + 1)] & 0xFF) << 8 | paramArrayOfByte[(paramInt2 + 2)] & 0xFF;
            arrayOfByte2[paramInt1] = arrayOfByte1[(n >> 18 & 0x3F)];
            arrayOfByte2[(paramInt1 + 1)] = arrayOfByte1[(n >> 12 & 0x3F)];
            arrayOfByte2[(paramInt1 + 2)] = arrayOfByte1[(n >> 6 & 0x3F)];
            arrayOfByte2[(paramInt1 + 3)] = arrayOfByte1[(n & 0x3F)];
            paramInt2 += 3;
            i2 = paramInt1 + 4;
            i3 = i1 - 1;
            paramInt1 = i3;
            n = i2;
            i1 = paramInt2;
            if (i3 != 0)
              break label1210;
            if (!this.g)
              break label1204;
            paramInt1 = i2 + 1;
            arrayOfByte2[i2] = 13;
          }
          while (true)
          {
            arrayOfByte2[paramInt1] = 10;
            i1 = 19;
            paramInt1 += 1;
            break label180;
            paramInt2 = paramInt1;
            break label62;
            if (paramInt1 + 2 > i5)
              break;
            i1 = this.k[0];
            paramInt2 = paramInt1 + 1;
            i2 = (i1 & 0xFF) << 16 | (paramArrayOfByte[paramInt1] & 0xFF) << 8 | paramArrayOfByte[paramInt2] & 0xFF;
            this.d = 0;
            paramInt2 += 1;
            break label62;
            if (paramInt1 + 1 > i5)
              break;
            i1 = this.k[0];
            i2 = this.k[1];
            paramInt2 = paramInt1 + 1;
            i2 = (i1 & 0xFF) << 16 | (i2 & 0xFF) << 8 | paramArrayOfByte[paramInt1] & 0xFF;
            this.d = 0;
            break label62;
            label742: int i4;
            if (paramBoolean)
            {
              if (paramInt2 - this.d == i5 - 1)
              {
                if (this.d > 0)
                {
                  paramArrayOfByte = this.k;
                  n = 1;
                  i2 = paramArrayOfByte[0];
                }
                while (true)
                {
                  i2 = (i2 & 0xFF) << 4;
                  this.d -= n;
                  i3 = paramInt1 + 1;
                  arrayOfByte2[paramInt1] = arrayOfByte1[(i2 >> 6 & 0x3F)];
                  n = i3 + 1;
                  arrayOfByte2[i3] = arrayOfByte1[(i2 & 0x3F)];
                  paramInt1 = n;
                  if (this.e)
                  {
                    i2 = n + 1;
                    arrayOfByte2[n] = 61;
                    paramInt1 = i2 + 1;
                    arrayOfByte2[i2] = 61;
                  }
                  n = paramInt1;
                  if (this.f)
                  {
                    n = paramInt1;
                    if (this.g)
                    {
                      arrayOfByte2[paramInt1] = 13;
                      n = paramInt1 + 1;
                    }
                    arrayOfByte2[n] = 10;
                    n += 1;
                  }
                  i2 = paramInt2;
                  if ((h) || (this.d == 0))
                    break;
                  throw new AssertionError();
                  i2 = paramArrayOfByte[paramInt2];
                  paramInt2 += 1;
                  n = 0;
                }
              }
              if (paramInt2 - this.d == i5 - 2)
                if (this.d > 1)
                {
                  byte[] arrayOfByte3 = this.k;
                  i3 = 1;
                  i2 = arrayOfByte3[0];
                  n = paramInt2;
                  paramInt2 = i3;
                  if (this.d <= 0)
                    break label946;
                  i3 = this.k[paramInt2];
                  i4 = paramInt2 + 1;
                  paramInt2 = n;
                  n = i4;
                  label769: i2 = (i3 & 0xFF) << 2 | (i2 & 0xFF) << 10;
                  this.d -= n;
                  n = paramInt1 + 1;
                  arrayOfByte2[paramInt1] = arrayOfByte1[(i2 >> 12 & 0x3F)];
                  i3 = n + 1;
                  arrayOfByte2[n] = arrayOfByte1[(i2 >> 6 & 0x3F)];
                  paramInt1 = i3 + 1;
                  arrayOfByte2[i3] = arrayOfByte1[(i2 & 0x3F)];
                  if (!this.e)
                    break label1201;
                  n = paramInt1 + 1;
                  arrayOfByte2[paramInt1] = 61;
                  paramInt1 = n;
                }
            }
            label1201: 
            while (true)
            {
              n = paramInt1;
              if (this.f)
              {
                n = paramInt1;
                if (this.g)
                {
                  arrayOfByte2[paramInt1] = 13;
                  n = paramInt1 + 1;
                }
                arrayOfByte2[n] = 10;
                n += 1;
              }
              i2 = paramInt2;
              break;
              i2 = paramArrayOfByte[paramInt2];
              n = paramInt2 + 1;
              paramInt2 = 0;
              break label742;
              label946: i3 = paramArrayOfByte[n];
              i4 = n + 1;
              n = paramInt2;
              paramInt2 = i4;
              break label769;
              i2 = paramInt2;
              n = paramInt1;
              if (!this.f)
                break;
              i2 = paramInt2;
              n = paramInt1;
              if (paramInt1 <= 0)
                break;
              i2 = paramInt2;
              n = paramInt1;
              if (i1 == 19)
                break;
              if (this.g)
              {
                n = paramInt1 + 1;
                arrayOfByte2[paramInt1] = 13;
                paramInt1 = n;
              }
              while (true)
              {
                n = paramInt1 + 1;
                arrayOfByte2[paramInt1] = 10;
                i2 = paramInt2;
                break;
                i3 = n;
                if (!h)
                {
                  i3 = n;
                  if (i2 != i5)
                  {
                    throw new AssertionError();
                    if (paramInt2 != i5 - 1)
                      break label1123;
                    arrayOfByte1 = this.k;
                    n = this.d;
                    this.d = (n + 1);
                    arrayOfByte1[n] = paramArrayOfByte[paramInt2];
                    i3 = paramInt1;
                  }
                }
                while (true)
                {
                  this.b = i3;
                  this.l = i1;
                  return true;
                  label1123: i3 = paramInt1;
                  if (paramInt2 == i5 - 2)
                  {
                    arrayOfByte1 = this.k;
                    n = this.d;
                    this.d = (n + 1);
                    arrayOfByte1[n] = paramArrayOfByte[paramInt2];
                    arrayOfByte1 = this.k;
                    n = this.d;
                    this.d = (n + 1);
                    arrayOfByte1[n] = paramArrayOfByte[(paramInt2 + 1)];
                    i3 = paramInt1;
                  }
                }
              }
            }
            label1204: paramInt1 = i2;
          }
          label1210: i2 = paramInt1;
          paramInt1 = n;
          paramInt2 = i1;
          i1 = i2;
        }
        label1226: paramInt1 = 4;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.b
 * JD-Core Version:    0.6.2
 */