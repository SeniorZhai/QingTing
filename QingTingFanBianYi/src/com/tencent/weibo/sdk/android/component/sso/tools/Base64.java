package com.tencent.weibo.sdk.android.component.sso.tools;

public class Base64
{
  public static byte[] decode(String paramString)
  {
    int j = 0;
    int i = paramString.length() - 1;
    byte[] arrayOfByte;
    while (true)
    {
      if (paramString.charAt(i) != '=')
      {
        arrayOfByte = new byte[paramString.length() * 6 / 8 - j];
        j = 0;
        i = 0;
        if (i < paramString.length())
          break;
        return arrayOfByte;
      }
      j += 1;
      i -= 1;
    }
    int m = getValue(paramString.charAt(i));
    int n = getValue(paramString.charAt(i + 1));
    int i1 = getValue(paramString.charAt(i + 2));
    int i2 = getValue(paramString.charAt(i + 3));
    int k = 0;
    while (true)
    {
      if ((k >= 3) || (j + k >= arrayOfByte.length))
      {
        j += 3;
        i += 4;
        break;
      }
      arrayOfByte[(j + k)] = ((byte)((m << 18) + (n << 12) + (i1 << 6) + i2 >> (2 - k) * 8 & 0xFF));
      k += 1;
    }
  }

  public static String encode(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuffer.toString();
      localStringBuffer.append(encodeBlock(paramArrayOfByte, i));
      i += 3;
    }
  }

  protected static char[] encodeBlock(byte[] paramArrayOfByte, int paramInt)
  {
    int j = 0;
    int n = paramArrayOfByte.length - paramInt - 1;
    int i;
    int k;
    if (n >= 2)
    {
      i = 2;
      k = 0;
      if (k <= i)
        break label68;
      paramArrayOfByte = new char[4];
      paramInt = 0;
    }
    while (true)
    {
      if (paramInt >= 4)
      {
        if (n < 1)
          paramArrayOfByte[2] = 61;
        if (n < 2)
          paramArrayOfByte[3] = 61;
        return paramArrayOfByte;
        i = n;
        break;
        label68: int m = paramArrayOfByte[(paramInt + k)];
        if (m < 0)
          m += 256;
        while (true)
        {
          j += (m << (2 - k) * 8);
          k += 1;
          break;
        }
      }
      paramArrayOfByte[paramInt] = getChar(j >>> (3 - paramInt) * 6 & 0x3F);
      paramInt += 1;
    }
  }

  protected static char getChar(int paramInt)
  {
    char c = '?';
    if ((paramInt >= 0) && (paramInt <= 25))
      c = (char)(paramInt + 65);
    do
    {
      return c;
      if ((paramInt >= 26) && (paramInt <= 51))
        return (char)(paramInt - 26 + 97);
      if ((paramInt >= 52) && (paramInt <= 61))
        return (char)(paramInt - 52 + 48);
      if (paramInt == 62)
        return '+';
    }
    while (paramInt != 63);
    return '/';
  }

  protected static int getValue(char paramChar)
  {
    if ((paramChar >= 'A') && (paramChar <= 'Z'))
      return paramChar - 'A';
    if ((paramChar >= 'a') && (paramChar <= 'z'))
      return paramChar - 'a' + 26;
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0' + 52;
    if (paramChar == '+')
      return 62;
    if (paramChar == '/')
      return 63;
    if (paramChar == '=')
      return 0;
    return -1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.sso.tools.Base64
 * JD-Core Version:    0.6.2
 */