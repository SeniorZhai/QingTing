package fm.qingting.async.stream;

import fm.qingting.async.ByteBufferList;
import java.io.IOException;
import java.io.InputStream;

public class ByteBufferListInputStream extends InputStream
{
  ByteBufferList bb;

  public ByteBufferListInputStream(ByteBufferList paramByteBufferList)
  {
    this.bb = paramByteBufferList;
  }

  public int read()
    throws IOException
  {
    if (this.bb.remaining() <= 0)
      return -1;
    return this.bb.get();
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.bb.remaining() <= 0)
      return -1;
    paramInt2 = Math.min(paramInt2, this.bb.remaining());
    this.bb.get(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.stream.ByteBufferListInputStream
 * JD-Core Version:    0.6.2
 */