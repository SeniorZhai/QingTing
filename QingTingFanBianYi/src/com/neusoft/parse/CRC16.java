package com.neusoft.parse;

public class CRC16
{
  public static int crcProcess(byte[] paramArrayOfByte)
  {
    int k = 0;
    if (paramArrayOfByte == null)
      return -1;
    int i = 0;
    int m;
    while (true)
    {
      if (k >= paramArrayOfByte.length)
        return 0xFFFF & i;
      m = 128;
      if (m != 0)
        break;
      k += 1;
    }
    if ((0x8000 & i) != 0);
    for (int j = i << 1 ^ 0x1021; ; j = i << 1)
    {
      i = j;
      if ((paramArrayOfByte[k] & m) != 0)
        i = j ^ 0x1021;
      m = (char)(m >> 1);
      break;
    }
  }

  public static int crcProcess(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt1;
    paramInt1 = i;
    int k;
    while (true)
    {
      if (j >= paramInt2)
        return paramInt1 & 0xFFFF;
      k = 128;
      if (k != 0)
        break;
      j += 1;
    }
    if ((0x8000 & paramInt1) != 0);
    for (i = paramInt1 << 1 ^ 0x1021; ; i = paramInt1 << 1)
    {
      paramInt1 = i;
      if ((paramArrayOfByte[j] & k) != 0)
        paramInt1 = i ^ 0x1021;
      k = (char)(k >> 1);
      break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.parse.CRC16
 * JD-Core Version:    0.6.2
 */