package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ThreadLocalCache;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

public class SerialWriterStringEncoder
{
  private final CharsetEncoder encoder;

  public SerialWriterStringEncoder(Charset paramCharset)
  {
    this(paramCharset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE));
  }

  public SerialWriterStringEncoder(CharsetEncoder paramCharsetEncoder)
  {
    this.encoder = paramCharsetEncoder;
  }

  private static int scale(int paramInt, float paramFloat)
  {
    return (int)(paramInt * paramFloat);
  }

  public byte[] encode(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
      return new byte[0];
    this.encoder.reset();
    return encode(paramArrayOfChar, paramInt1, paramInt2, ThreadLocalCache.getBytes(scale(paramInt2, this.encoder.maxBytesPerChar())));
  }

  public byte[] encode(char[] paramArrayOfChar, int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    ByteBuffer localByteBuffer = ByteBuffer.wrap(paramArrayOfByte);
    paramArrayOfChar = CharBuffer.wrap(paramArrayOfChar, paramInt1, paramInt2);
    try
    {
      paramArrayOfChar = this.encoder.encode(paramArrayOfChar, localByteBuffer, true);
      if (!paramArrayOfChar.isUnderflow())
        paramArrayOfChar.throwException();
      paramArrayOfChar = this.encoder.flush(localByteBuffer);
      if (!paramArrayOfChar.isUnderflow())
        paramArrayOfChar.throwException();
      paramInt1 = localByteBuffer.position();
      paramArrayOfChar = new byte[paramInt1];
      System.arraycopy(paramArrayOfByte, 0, paramArrayOfChar, 0, paramInt1);
      return paramArrayOfChar;
    }
    catch (CharacterCodingException paramArrayOfChar)
    {
    }
    throw new JSONException(paramArrayOfChar.getMessage(), paramArrayOfChar);
  }

  public CharsetEncoder getEncoder()
  {
    return this.encoder;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.SerialWriterStringEncoder
 * JD-Core Version:    0.6.2
 */