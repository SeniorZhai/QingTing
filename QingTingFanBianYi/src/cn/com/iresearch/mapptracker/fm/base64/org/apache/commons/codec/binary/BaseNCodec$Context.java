package cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary;

import java.util.Arrays;

class BaseNCodec$Context
{
  byte[] buffer;
  int currentLinePos;
  boolean eof;
  int ibitWorkArea;
  long lbitWorkArea;
  int modulus;
  int pos;
  int readPos;

  public String toString()
  {
    return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", new Object[] { getClass().getSimpleName(), Arrays.toString(this.buffer), Integer.valueOf(this.currentLinePos), Boolean.valueOf(this.eof), Integer.valueOf(this.ibitWorkArea), Long.valueOf(this.lbitWorkArea), Integer.valueOf(this.modulus), Integer.valueOf(this.pos), Integer.valueOf(this.readPos) });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.BaseNCodec.Context
 * JD-Core Version:    0.6.2
 */