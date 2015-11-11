package com.alibaba.fastjson.util;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class UTF8Decoder extends CharsetDecoder
{
  private static final Charset charset = Charset.forName("UTF-8");

  public UTF8Decoder()
  {
    super(charset, 1.0F, 1.0F);
  }

  private CoderResult decodeArrayLoop(ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer)
  {
    byte[] arrayOfByte = paramByteBuffer.array();
    int i = paramByteBuffer.arrayOffset() + paramByteBuffer.position();
    int m = paramByteBuffer.arrayOffset() + paramByteBuffer.limit();
    char[] arrayOfChar = paramCharBuffer.array();
    int k = paramCharBuffer.arrayOffset() + paramCharBuffer.position();
    int n = paramCharBuffer.arrayOffset() + paramCharBuffer.limit();
    int i1 = Math.min(m - i, n - k);
    int j = k;
    while ((j < k + i1) && (arrayOfByte[i] >= 0))
    {
      arrayOfChar[j] = ((char)arrayOfByte[i]);
      j += 1;
      i += 1;
      continue;
      arrayOfChar[j] = ((char)k);
      i += 1;
      j += 1;
    }
    while (true)
    {
      if (i < m)
      {
        k = arrayOfByte[i];
        if (k >= 0)
        {
          if (j < n)
            break;
          return xflow(paramByteBuffer, i, m, paramCharBuffer, j, 1);
        }
        if (k >> 5 == -2)
        {
          if ((m - i < 2) || (j >= n))
            return xflow(paramByteBuffer, i, m, paramCharBuffer, j, 2);
          i1 = arrayOfByte[(i + 1)];
          if (isMalformed2(k, i1))
            return malformed(paramByteBuffer, i, paramCharBuffer, j, 2);
          arrayOfChar[j] = ((char)(k << 6 ^ i1 ^ 0xF80));
          i += 2;
          j += 1;
          continue;
        }
        int i2;
        if (k >> 4 == -2)
        {
          if ((m - i < 3) || (j >= n))
            return xflow(paramByteBuffer, i, m, paramCharBuffer, j, 3);
          i1 = arrayOfByte[(i + 1)];
          i2 = arrayOfByte[(i + 2)];
          if (isMalformed3(k, i1, i2))
            return malformed(paramByteBuffer, i, paramCharBuffer, j, 3);
          arrayOfChar[j] = ((char)(k << 12 ^ i1 << 6 ^ i2 ^ 0x1F80));
          i += 3;
          j += 1;
          continue;
        }
        if (k >> 3 == -2)
        {
          if ((m - i < 4) || (n - j < 2))
            return xflow(paramByteBuffer, i, m, paramCharBuffer, j, 4);
          i1 = arrayOfByte[(i + 1)];
          i2 = arrayOfByte[(i + 2)];
          int i3 = arrayOfByte[(i + 3)];
          k = (k & 0x7) << 18 | (i1 & 0x3F) << 12 | (i2 & 0x3F) << 6 | i3 & 0x3F;
          if ((isMalformed4(i1, i2, i3)) || (!Surrogate.neededFor(k)))
            return malformed(paramByteBuffer, i, paramCharBuffer, j, 4);
          i1 = j + 1;
          arrayOfChar[j] = Surrogate.high(k);
          j = i1 + 1;
          arrayOfChar[i1] = Surrogate.low(k);
          i += 4;
          continue;
        }
        return malformed(paramByteBuffer, i, paramCharBuffer, j, 1);
      }
      return xflow(paramByteBuffer, i, m, paramCharBuffer, j, 0);
    }
  }

  private static final boolean isMalformed2(int paramInt1, int paramInt2)
  {
    return ((paramInt1 & 0x1E) == 0) || ((paramInt2 & 0xC0) != 128);
  }

  private static boolean isMalformed3(int paramInt1, int paramInt2, int paramInt3)
  {
    return ((paramInt1 == -32) && ((paramInt2 & 0xE0) == 128)) || ((paramInt2 & 0xC0) != 128) || ((paramInt3 & 0xC0) != 128);
  }

  private static final boolean isMalformed4(int paramInt1, int paramInt2, int paramInt3)
  {
    return ((paramInt1 & 0xC0) != 128) || ((paramInt2 & 0xC0) != 128) || ((paramInt3 & 0xC0) != 128);
  }

  private static boolean isNotContinuation(int paramInt)
  {
    return (paramInt & 0xC0) != 128;
  }

  private static CoderResult lookupN(ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = 1;
    while (i < paramInt)
    {
      if (isNotContinuation(paramByteBuffer.get()))
        return CoderResult.malformedForLength(i);
      i += 1;
    }
    return CoderResult.malformedForLength(paramInt);
  }

  private static CoderResult malformed(ByteBuffer paramByteBuffer, int paramInt1, CharBuffer paramCharBuffer, int paramInt2, int paramInt3)
  {
    paramByteBuffer.position(paramInt1 - paramByteBuffer.arrayOffset());
    CoderResult localCoderResult = malformedN(paramByteBuffer, paramInt3);
    updatePositions(paramByteBuffer, paramInt1, paramCharBuffer, paramInt2);
    return localCoderResult;
  }

  public static CoderResult malformedN(ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = 2;
    switch (paramInt)
    {
    default:
      throw new IllegalStateException();
    case 1:
      paramInt = paramByteBuffer.get();
      if (paramInt >> 2 == -2)
      {
        if (paramByteBuffer.remaining() < 4)
          return CoderResult.UNDERFLOW;
        return lookupN(paramByteBuffer, 5);
      }
      if (paramInt >> 1 == -2)
      {
        if (paramByteBuffer.remaining() < 5)
          return CoderResult.UNDERFLOW;
        return lookupN(paramByteBuffer, 6);
      }
      return CoderResult.malformedForLength(1);
    case 2:
      return CoderResult.malformedForLength(1);
    case 3:
      paramInt = paramByteBuffer.get();
      int j = paramByteBuffer.get();
      if ((paramInt != -32) || ((j & 0xE0) != 128))
      {
        paramInt = i;
        if (!isNotContinuation(j));
      }
      else
      {
        paramInt = 1;
      }
      return CoderResult.malformedForLength(paramInt);
    case 4:
    }
    paramInt = paramByteBuffer.get() & 0xFF;
    i = paramByteBuffer.get() & 0xFF;
    if ((paramInt > 244) || ((paramInt == 240) && ((i < 144) || (i > 191))) || ((paramInt == 244) && ((i & 0xF0) != 128)) || (isNotContinuation(i)))
      return CoderResult.malformedForLength(1);
    if (isNotContinuation(paramByteBuffer.get()))
      return CoderResult.malformedForLength(2);
    return CoderResult.malformedForLength(3);
  }

  static final void updatePositions(Buffer paramBuffer1, int paramInt1, Buffer paramBuffer2, int paramInt2)
  {
    paramBuffer1.position(paramInt1);
    paramBuffer2.position(paramInt2);
  }

  private static CoderResult xflow(Buffer paramBuffer1, int paramInt1, int paramInt2, Buffer paramBuffer2, int paramInt3, int paramInt4)
  {
    updatePositions(paramBuffer1, paramInt1, paramBuffer2, paramInt3);
    if ((paramInt4 == 0) || (paramInt2 - paramInt1 < paramInt4))
      return CoderResult.UNDERFLOW;
    return CoderResult.OVERFLOW;
  }

  protected CoderResult decodeLoop(ByteBuffer paramByteBuffer, CharBuffer paramCharBuffer)
  {
    return decodeArrayLoop(paramByteBuffer, paramCharBuffer);
  }

  private static class Surrogate
  {
    public static final int UCS4_MAX = 1114111;
    public static final int UCS4_MIN = 65536;

    static
    {
      if (!UTF8Decoder.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    public static char high(int paramInt)
    {
      assert (neededFor(paramInt));
      return (char)(0xD800 | paramInt - 65536 >> 10 & 0x3FF);
    }

    public static char low(int paramInt)
    {
      assert (neededFor(paramInt));
      return (char)(0xDC00 | paramInt - 65536 & 0x3FF);
    }

    public static boolean neededFor(int paramInt)
    {
      return (paramInt >= 65536) && (paramInt <= 1114111);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.UTF8Decoder
 * JD-Core Version:    0.6.2
 */