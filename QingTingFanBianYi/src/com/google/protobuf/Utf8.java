package com.google.protobuf;

final class Utf8
{
  public static final int COMPLETE = 0;
  public static final int MALFORMED = -1;

  private static int incompleteStateFor(int paramInt)
  {
    int i = paramInt;
    if (paramInt > -12)
      i = -1;
    return i;
  }

  private static int incompleteStateFor(int paramInt1, int paramInt2)
  {
    if ((paramInt1 > -12) || (paramInt2 > -65))
      return -1;
    return paramInt2 << 8 ^ paramInt1;
  }

  private static int incompleteStateFor(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 > -12) || (paramInt2 > -65) || (paramInt3 > -65))
      return -1;
    return paramInt2 << 8 ^ paramInt1 ^ paramInt3 << 16;
  }

  private static int incompleteStateFor(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfByte[(paramInt1 - 1)];
    switch (paramInt2 - paramInt1)
    {
    default:
      throw new AssertionError();
    case 0:
      return incompleteStateFor(i);
    case 1:
      return incompleteStateFor(i, paramArrayOfByte[paramInt1]);
    case 2:
    }
    return incompleteStateFor(i, paramArrayOfByte[paramInt1], paramArrayOfByte[(paramInt1 + 1)]);
  }

  public static boolean isValidUtf8(byte[] paramArrayOfByte)
  {
    return isValidUtf8(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static boolean isValidUtf8(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return partialIsValidUtf8(paramArrayOfByte, paramInt1, paramInt2) == 0;
  }

  public static int partialIsValidUtf8(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    int i = paramInt2;
    int n;
    int j;
    if (paramInt1 != 0)
    {
      if (paramInt2 >= paramInt3)
        return paramInt1;
      n = (byte)paramInt1;
      if (n < -32)
      {
        if ((n < -62) || (paramArrayOfByte[paramInt2] > -65))
          return -1;
      }
      else if (n < -16)
      {
        i = (byte)(paramInt1 >> 8 ^ 0xFFFFFFFF);
        if (i == 0)
        {
          i = paramInt2 + 1;
          j = paramArrayOfByte[paramInt2];
          paramInt2 = j;
          paramInt1 = i;
          if (i >= paramInt3)
            return incompleteStateFor(n, j);
        }
        else
        {
          paramInt1 = paramInt2;
          paramInt2 = i;
        }
        if ((paramInt2 > -65) || ((n == -32) && (paramInt2 < -96)) || ((n == -19) && (paramInt2 >= -96)))
          break label308;
        i = paramInt1 + 1;
        if (paramArrayOfByte[paramInt1] <= -65)
          break label297;
      }
    }
    label297: label308: 
    while (true)
    {
      return -1;
      i = (byte)(paramInt1 >> 8 ^ 0xFFFFFFFF);
      j = 0;
      if (i == 0)
      {
        k = paramInt2 + 1;
        int m = paramArrayOfByte[paramInt2];
        i = m;
        paramInt2 = j;
        paramInt1 = k;
        if (k >= paramInt3)
          return incompleteStateFor(n, m);
      }
      else
      {
        j = (byte)(paramInt1 >> 16);
        paramInt1 = paramInt2;
        paramInt2 = j;
      }
      int k = paramInt2;
      j = paramInt1;
      if (paramInt2 == 0)
      {
        j = paramInt1 + 1;
        k = paramArrayOfByte[paramInt1];
        if (j >= paramInt3)
          return incompleteStateFor(n, i, k);
      }
      if ((i <= -65) && ((n << 28) + (i + 112) >> 30 == 0) && (k <= -65))
      {
        i = j + 1;
        if (paramArrayOfByte[j] <= -65);
      }
      while (true)
      {
        return -1;
        i = paramInt2 + 1;
        return partialIsValidUtf8(paramArrayOfByte, i, paramInt3);
      }
    }
  }

  public static int partialIsValidUtf8(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    while ((paramInt1 < paramInt2) && (paramArrayOfByte[paramInt1] >= 0))
      paramInt1 += 1;
    if (paramInt1 >= paramInt2)
      return 0;
    return partialIsValidUtf8NonAscii(paramArrayOfByte, paramInt1, paramInt2);
  }

  private static int partialIsValidUtf8NonAscii(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    while (true)
    {
      if (paramInt1 >= paramInt2)
        paramInt1 = 0;
      int i;
      int j;
      do
      {
        return paramInt1;
        i = paramInt1 + 1;
        j = paramArrayOfByte[paramInt1];
        if (j >= 0)
          break label229;
        if (j >= -32)
          break;
        paramInt1 = j;
      }
      while (i >= paramInt2);
      int k;
      if (j >= -62)
      {
        paramInt1 = i + 1;
        if (paramArrayOfByte[i] <= -65);
      }
      else
      {
        return -1;
        if (j < -16)
        {
          if (i >= paramInt2 - 1)
            return incompleteStateFor(paramArrayOfByte, i, paramInt2);
          k = i + 1;
          paramInt1 = paramArrayOfByte[i];
          if ((paramInt1 > -65) || ((j == -32) && (paramInt1 < -96)) || ((j == -19) && (paramInt1 >= -96)))
            break label226;
          paramInt1 = k + 1;
          if (paramArrayOfByte[k] <= -65)
            break label223;
        }
      }
      label223: label226: 
      while (true)
      {
        return -1;
        if (i >= paramInt2 - 2)
          return incompleteStateFor(paramArrayOfByte, i, paramInt2);
        k = i + 1;
        i = paramArrayOfByte[i];
        paramInt1 = k;
        if (i <= -65)
        {
          paramInt1 = k;
          if ((j << 28) + (i + 112) >> 30 == 0)
          {
            j = k + 1;
            if (paramArrayOfByte[k] <= -65)
            {
              i = j + 1;
              paramInt1 = i;
              if (paramArrayOfByte[j] <= -65)
                break label223;
              paramInt1 = i;
            }
          }
        }
        return -1;
        break;
      }
      label229: paramInt1 = i;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.Utf8
 * JD-Core Version:    0.6.2
 */