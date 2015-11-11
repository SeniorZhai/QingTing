package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class IOUtils
{
  static final char[] DigitOnes = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
  static final char[] DigitTens;
  static final char[] digits = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
  static final int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 2147483647 };

  static
  {
    DigitTens = new char[] { 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 53, 53, 53, 53, 53, 53, 53, 53, 53, 53, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57 };
  }

  public static void close(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  public static void decode(CharsetDecoder paramCharsetDecoder, ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer)
  {
    try
    {
      paramByteBuffer = paramCharsetDecoder.decode(paramByteBuffer, paramCharBuffer, true);
      if (!paramByteBuffer.isUnderflow())
        paramByteBuffer.throwException();
      paramCharsetDecoder = paramCharsetDecoder.flush(paramCharBuffer);
      if (!paramCharsetDecoder.isUnderflow())
        paramCharsetDecoder.throwException();
      return;
    }
    catch (CharacterCodingException paramCharsetDecoder)
    {
    }
    throw new JSONException(paramCharsetDecoder.getMessage(), paramCharsetDecoder);
  }

  public static void getChars(byte paramByte, int paramInt, char[] paramArrayOfChar)
  {
    byte b = paramByte;
    int j = paramInt;
    int i = 0;
    paramInt = j;
    paramByte = b;
    if (b < 0)
    {
      i = 45;
      paramByte = -b;
      paramInt = j;
    }
    do
    {
      b = 52429 * paramByte >>> 19;
      j = paramInt - 1;
      paramArrayOfChar[j] = digits[(paramByte - ((b << 3) + (b << 1)))];
      paramInt = j;
      paramByte = b;
    }
    while (b != 0);
    if (i != 0)
      paramArrayOfChar[(j - 1)] = i;
  }

  public static void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar)
  {
    int k = paramInt2;
    int i = 0;
    int j = k;
    paramInt2 = paramInt1;
    if (paramInt1 < 0)
    {
      i = 45;
      paramInt2 = -paramInt1;
      j = k;
    }
    while (true)
    {
      paramInt1 = j;
      k = paramInt2;
      if (paramInt2 < 65536)
        break;
      paramInt1 = paramInt2 / 100;
      k = paramInt2 - ((paramInt1 << 6) + (paramInt1 << 5) + (paramInt1 << 2));
      paramInt2 = paramInt1;
      paramInt1 = j - 1;
      paramArrayOfChar[paramInt1] = DigitOnes[k];
      j = paramInt1 - 1;
      paramArrayOfChar[j] = DigitTens[k];
    }
    do
    {
      paramInt2 = 52429 * k >>> 19;
      j = paramInt1 - 1;
      paramArrayOfChar[j] = digits[(k - ((paramInt2 << 3) + (paramInt2 << 1)))];
      paramInt1 = j;
      k = paramInt2;
    }
    while (paramInt2 != 0);
    if (i != 0)
      paramArrayOfChar[(j - 1)] = i;
  }

  public static void getChars(long paramLong, int paramInt, char[] paramArrayOfChar)
  {
    int j = paramInt;
    int i = 0;
    paramInt = j;
    long l = paramLong;
    if (paramLong < 0L)
    {
      i = 45;
      l = -paramLong;
      paramInt = j;
    }
    while (l > 2147483647L)
    {
      paramLong = l / 100L;
      j = (int)(l - ((paramLong << 6) + (paramLong << 5) + (paramLong << 2)));
      l = paramLong;
      paramInt -= 1;
      paramArrayOfChar[paramInt] = DigitOnes[j];
      paramInt -= 1;
      paramArrayOfChar[paramInt] = DigitTens[j];
    }
    j = (int)l;
    int k;
    int m;
    while (true)
    {
      k = paramInt;
      m = j;
      if (j < 65536)
        break;
      k = j / 100;
      m = j - ((k << 6) + (k << 5) + (k << 2));
      j = k;
      paramInt -= 1;
      paramArrayOfChar[paramInt] = DigitOnes[m];
      paramInt -= 1;
      paramArrayOfChar[paramInt] = DigitTens[m];
    }
    do
    {
      paramInt = 52429 * m >>> 19;
      j = k - 1;
      paramArrayOfChar[j] = digits[(m - ((paramInt << 3) + (paramInt << 1)))];
      k = j;
      m = paramInt;
    }
    while (paramInt != 0);
    if (i != 0)
      paramArrayOfChar[(j - 1)] = i;
  }

  public static int stringSize(int paramInt)
  {
    int i = 0;
    while (true)
    {
      if (paramInt <= sizeTable[i])
        return i + 1;
      i += 1;
    }
  }

  public static int stringSize(long paramLong)
  {
    long l = 10L;
    int i = 1;
    while (i < 19)
    {
      if (paramLong < l)
        return i;
      l *= 10L;
      i += 1;
    }
    return 19;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.IOUtils
 * JD-Core Version:    0.6.2
 */