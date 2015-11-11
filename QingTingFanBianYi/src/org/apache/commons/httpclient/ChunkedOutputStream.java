package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;

public class ChunkedOutputStream extends OutputStream
{
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] ENDCHUNK = CRLF;
  private static final byte[] ZERO = { 48 };
  private byte[] cache;
  private int cachePosition = 0;
  private OutputStream stream = null;
  private boolean wroteLastChunk = false;

  public ChunkedOutputStream(OutputStream paramOutputStream)
    throws IOException
  {
    this(paramOutputStream, 2048);
  }

  public ChunkedOutputStream(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    this.cache = new byte[paramInt];
    this.stream = paramOutputStream;
  }

  public void close()
    throws IOException
  {
    finish();
    super.close();
  }

  public void finish()
    throws IOException
  {
    if (!this.wroteLastChunk)
    {
      flushCache();
      writeClosingChunk();
      this.wroteLastChunk = true;
    }
  }

  public void flush()
    throws IOException
  {
    this.stream.flush();
  }

  protected void flushCache()
    throws IOException
  {
    if (this.cachePosition > 0)
    {
      byte[] arrayOfByte = EncodingUtil.getAsciiBytes(Integer.toHexString(this.cachePosition) + "\r\n");
      this.stream.write(arrayOfByte, 0, arrayOfByte.length);
      this.stream.write(this.cache, 0, this.cachePosition);
      this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
      this.cachePosition = 0;
    }
  }

  protected void flushCacheWithAppend(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = EncodingUtil.getAsciiBytes(Integer.toHexString(this.cachePosition + paramInt2) + "\r\n");
    this.stream.write(arrayOfByte, 0, arrayOfByte.length);
    this.stream.write(this.cache, 0, this.cachePosition);
    this.stream.write(paramArrayOfByte, paramInt1, paramInt2);
    this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
    this.cachePosition = 0;
  }

  public void write(int paramInt)
    throws IOException
  {
    this.cache[this.cachePosition] = ((byte)paramInt);
    this.cachePosition += 1;
    if (this.cachePosition == this.cache.length)
      flushCache();
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 >= this.cache.length - this.cachePosition)
    {
      flushCacheWithAppend(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.cache, this.cachePosition, paramInt2);
    this.cachePosition += paramInt2;
  }

  protected void writeClosingChunk()
    throws IOException
  {
    this.stream.write(ZERO, 0, ZERO.length);
    this.stream.write(CRLF, 0, CRLF.length);
    this.stream.write(ENDCHUNK, 0, ENDCHUNK.length);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ChunkedOutputStream
 * JD-Core Version:    0.6.2
 */