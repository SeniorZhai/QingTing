package com.alibaba.fastjson.util;

import java.util.Arrays;

public class Base64
{
  public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  public static final int[] IA = new int[256];

  static
  {
    Arrays.fill(IA, -1);
    int i = 0;
    int j = CA.length;
    while (i < j)
    {
      IA[CA[i]] = i;
      i += 1;
    }
    IA[61] = 0;
  }

  public static final byte[] decodeFast(String paramString)
  {
    int m = paramString.length();
    if (m == 0)
      return new byte[0];
    int i = 0;
    int j = m - 1;
    int n;
    while (true)
    {
      n = j;
      if (i >= j)
        break;
      n = j;
      if (IA[(paramString.charAt(i) & 0xFF)] >= 0)
        break;
      i += 1;
    }
    while ((n > 0) && (IA[(paramString.charAt(n) & 0xFF)] < 0))
      n -= 1;
    int k;
    int i2;
    label151: int i1;
    label157: int i3;
    byte[] arrayOfByte;
    if (paramString.charAt(n) == '=')
      if (paramString.charAt(n - 1) == '=')
      {
        k = 2;
        i2 = n - i + 1;
        if (m <= 76)
          break label403;
        if (paramString.charAt(76) != '\r')
          break label397;
        j = i2 / 78;
        i1 = j << 1;
        i3 = ((i2 - i1) * 6 >> 3) - k;
        arrayOfByte = new byte[i3];
        m = 0;
        int i4 = i3 / 3;
        i2 = 0;
        j = i;
        i = i2;
        label195: if (i >= i4 * 3)
          break label409;
        int[] arrayOfInt = IA;
        int i5 = j + 1;
        i2 = arrayOfInt[paramString.charAt(j)];
        arrayOfInt = IA;
        j = i5 + 1;
        i5 = arrayOfInt[paramString.charAt(i5)];
        arrayOfInt = IA;
        int i6 = j + 1;
        int i7 = arrayOfInt[paramString.charAt(j)];
        arrayOfInt = IA;
        j = i6 + 1;
        i5 = i2 << 18 | i5 << 12 | i7 << 6 | arrayOfInt[paramString.charAt(i6)];
        i6 = i + 1;
        arrayOfByte[i] = ((byte)(i5 >> 16));
        i2 = i6 + 1;
        arrayOfByte[i6] = ((byte)(i5 >> 8));
        arrayOfByte[i2] = ((byte)i5);
        i = m;
        if (i1 <= 0)
          break label519;
        m += 1;
        i = m;
        if (m != 19)
          break label519;
        i = j + 2;
        m = 0;
      }
    while (true)
    {
      i2 += 1;
      j = i;
      i = i2;
      break label195;
      k = 1;
      break;
      k = 0;
      break;
      label397: j = 0;
      break label151;
      label403: i1 = 0;
      break label157;
      label409: i1 = i;
      i1 = j;
      if (i < i3)
      {
        m = 0;
        i1 = 0;
        while (j <= n - k)
        {
          m |= IA[paramString.charAt(j)] << 18 - i1 * 6;
          i1 += 1;
          j += 1;
        }
        k = 16;
        while (true)
        {
          i1 = i;
          i1 = j;
          if (i >= i3)
            break;
          arrayOfByte[i] = ((byte)(m >> k));
          k -= 8;
          i += 1;
        }
      }
      return arrayOfByte;
      label519: m = i;
      i = j;
    }
  }

  public static final byte[] decodeFast(String paramString, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
      return new byte[0];
    int i = paramInt1;
    int j = paramInt1 + paramInt2 - 1;
    paramInt1 = i;
    int k;
    while (true)
    {
      k = j;
      if (paramInt1 >= j)
        break;
      k = j;
      if (IA[paramString.charAt(paramInt1)] >= 0)
        break;
      paramInt1 += 1;
    }
    while ((k > 0) && (IA[paramString.charAt(k)] < 0))
      k -= 1;
    label139: int m;
    label144: int i1;
    byte[] arrayOfByte;
    int n;
    if (paramString.charAt(k) == '=')
      if (paramString.charAt(k - 1) == '=')
      {
        i = 2;
        j = k - paramInt1 + 1;
        if (paramInt2 <= 76)
          break label388;
        if (paramString.charAt(76) != '\r')
          break label383;
        paramInt2 = j / 78;
        m = paramInt2 << 1;
        i1 = ((j - m) * 6 >> 3) - i;
        arrayOfByte = new byte[i1];
        j = 0;
        int i2 = i1 / 3;
        n = 0;
        paramInt2 = paramInt1;
        paramInt1 = n;
        label181: if (paramInt1 >= i2 * 3)
          break label394;
        int[] arrayOfInt = IA;
        int i3 = paramInt2 + 1;
        n = arrayOfInt[paramString.charAt(paramInt2)];
        arrayOfInt = IA;
        paramInt2 = i3 + 1;
        i3 = arrayOfInt[paramString.charAt(i3)];
        arrayOfInt = IA;
        int i4 = paramInt2 + 1;
        int i5 = arrayOfInt[paramString.charAt(paramInt2)];
        arrayOfInt = IA;
        paramInt2 = i4 + 1;
        i3 = n << 18 | i3 << 12 | i5 << 6 | arrayOfInt[paramString.charAt(i4)];
        i4 = paramInt1 + 1;
        arrayOfByte[paramInt1] = ((byte)(i3 >> 16));
        n = i4 + 1;
        arrayOfByte[i4] = ((byte)(i3 >> 8));
        arrayOfByte[n] = ((byte)i3);
        paramInt1 = j;
        if (m <= 0)
          break label498;
        j += 1;
        paramInt1 = j;
        if (j != 19)
          break label498;
        paramInt1 = paramInt2 + 2;
        j = 0;
      }
    while (true)
    {
      n += 1;
      paramInt2 = paramInt1;
      paramInt1 = n;
      break label181;
      i = 1;
      break;
      i = 0;
      break;
      label383: paramInt2 = 0;
      break label139;
      label388: m = 0;
      break label144;
      label394: m = paramInt1;
      m = paramInt2;
      if (paramInt1 < i1)
      {
        j = 0;
        m = 0;
        while (paramInt2 <= k - i)
        {
          j |= IA[paramString.charAt(paramInt2)] << 18 - m * 6;
          m += 1;
          paramInt2 += 1;
        }
        i = 16;
        while (true)
        {
          m = paramInt1;
          m = paramInt2;
          if (paramInt1 >= i1)
            break;
          arrayOfByte[paramInt1] = ((byte)(j >> i));
          i -= 8;
          paramInt1 += 1;
        }
      }
      return arrayOfByte;
      label498: j = paramInt1;
      paramInt1 = paramInt2;
    }
  }

  public static final byte[] decodeFast(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
      return new byte[0];
    int i = paramInt1;
    int j = paramInt1 + paramInt2 - 1;
    paramInt1 = i;
    int k;
    while (true)
    {
      k = j;
      if (paramInt1 >= j)
        break;
      k = j;
      if (IA[paramArrayOfChar[paramInt1]] >= 0)
        break;
      paramInt1 += 1;
    }
    while ((k > 0) && (IA[paramArrayOfChar[k]] < 0))
      k -= 1;
    label129: int m;
    label134: int i1;
    byte[] arrayOfByte;
    int n;
    if (paramArrayOfChar[k] == '=')
      if (paramArrayOfChar[(k - 1)] == '=')
      {
        i = 2;
        j = k - paramInt1 + 1;
        if (paramInt2 <= 76)
          break label370;
        if (paramArrayOfChar[76] != '\r')
          break label365;
        paramInt2 = j / 78;
        m = paramInt2 << 1;
        i1 = ((j - m) * 6 >> 3) - i;
        arrayOfByte = new byte[i1];
        j = 0;
        int i2 = i1 / 3;
        n = 0;
        paramInt2 = paramInt1;
        paramInt1 = n;
        label171: if (paramInt1 >= i2 * 3)
          break label376;
        int[] arrayOfInt = IA;
        int i3 = paramInt2 + 1;
        n = arrayOfInt[paramArrayOfChar[paramInt2]];
        arrayOfInt = IA;
        paramInt2 = i3 + 1;
        i3 = arrayOfInt[paramArrayOfChar[i3]];
        arrayOfInt = IA;
        int i4 = paramInt2 + 1;
        int i5 = arrayOfInt[paramArrayOfChar[paramInt2]];
        arrayOfInt = IA;
        paramInt2 = i4 + 1;
        i3 = n << 18 | i3 << 12 | i5 << 6 | arrayOfInt[paramArrayOfChar[i4]];
        i4 = paramInt1 + 1;
        arrayOfByte[paramInt1] = ((byte)(i3 >> 16));
        n = i4 + 1;
        arrayOfByte[i4] = ((byte)(i3 >> 8));
        arrayOfByte[n] = ((byte)i3);
        paramInt1 = j;
        if (m <= 0)
          break label478;
        j += 1;
        paramInt1 = j;
        if (j != 19)
          break label478;
        paramInt1 = paramInt2 + 2;
        j = 0;
      }
    while (true)
    {
      n += 1;
      paramInt2 = paramInt1;
      paramInt1 = n;
      break label171;
      i = 1;
      break;
      i = 0;
      break;
      label365: paramInt2 = 0;
      break label129;
      label370: m = 0;
      break label134;
      label376: m = paramInt1;
      m = paramInt2;
      if (paramInt1 < i1)
      {
        j = 0;
        m = 0;
        while (paramInt2 <= k - i)
        {
          j |= IA[paramArrayOfChar[paramInt2]] << 18 - m * 6;
          m += 1;
          paramInt2 += 1;
        }
        i = 16;
        while (true)
        {
          m = paramInt1;
          m = paramInt2;
          if (paramInt1 >= i1)
            break;
          arrayOfByte[paramInt1] = ((byte)(j >> i));
          i -= 8;
          paramInt1 += 1;
        }
      }
      return arrayOfByte;
      label478: j = paramInt1;
      paramInt1 = paramInt2;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.Base64
 * JD-Core Version:    0.6.2
 */