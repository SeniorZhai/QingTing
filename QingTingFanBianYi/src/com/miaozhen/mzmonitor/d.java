package com.miaozhen.mzmonitor;

final class d
{
  private static final byte[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

  public static String a(String paramString)
  {
    if (paramString == null)
      return "";
    paramString = paramString.getBytes();
    int j = paramString.length;
    byte[] arrayOfByte = new byte[(j + 2) / 3 << 2];
    int i = 0;
    int k = 0;
    int m;
    if (j <= 2)
    {
      if (j == 0)
        break label273;
      m = k + 1;
      arrayOfByte[k] = a[(paramString[i] >> 2)];
      if (j <= 1)
        break label238;
      j = m + 1;
      arrayOfByte[m] = a[(((paramString[i] & 0x3) << 4) + (paramString[(i + 1)] >> 4))];
      arrayOfByte[j] = a[((paramString[(i + 1)] & 0xF) << 2)];
      arrayOfByte[(j + 1)] = 61;
    }
    label273: 
    while (true)
    {
      return new String(arrayOfByte);
      m = k + 1;
      arrayOfByte[k] = a[(paramString[i] >> 2)];
      k = m + 1;
      arrayOfByte[m] = a[(((paramString[i] & 0x3) << 4) + (paramString[(i + 1)] >> 4))];
      m = k + 1;
      arrayOfByte[k] = a[(((paramString[(i + 1)] & 0xF) << 2) + (paramString[(i + 2)] >> 6))];
      k = m + 1;
      arrayOfByte[m] = a[(paramString[(i + 2)] & 0x3F)];
      i += 3;
      j -= 3;
      break;
      label238: j = m + 1;
      arrayOfByte[m] = a[((paramString[i] & 0x3) << 4)];
      arrayOfByte[j] = 61;
      arrayOfByte[(j + 1)] = 61;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.d
 * JD-Core Version:    0.6.2
 */