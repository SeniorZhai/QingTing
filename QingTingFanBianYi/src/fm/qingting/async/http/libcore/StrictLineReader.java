package fm.qingting.async.http.libcore;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class StrictLineReader
  implements Closeable
{
  private static final byte CR = 13;
  private static final byte LF = 10;
  private byte[] buf;
  private int end;
  private final InputStream in;
  private int pos;

  public StrictLineReader(InputStream paramInputStream)
  {
    this(paramInputStream, 8192);
  }

  public StrictLineReader(InputStream paramInputStream, int paramInt)
  {
    this(paramInputStream, paramInt, Charsets.US_ASCII);
  }

  public StrictLineReader(InputStream paramInputStream, int paramInt, Charset paramCharset)
  {
    if (paramInputStream == null)
      throw new NullPointerException("in == null");
    if (paramCharset == null)
      throw new NullPointerException("charset == null");
    if (paramInt < 0)
      throw new IllegalArgumentException("capacity <= 0");
    if ((!paramCharset.equals(Charsets.US_ASCII)) && (!paramCharset.equals(Charsets.UTF_8)))
      throw new IllegalArgumentException("Unsupported encoding");
    this.in = paramInputStream;
    this.buf = new byte[paramInt];
  }

  public StrictLineReader(InputStream paramInputStream, Charset paramCharset)
  {
    this(paramInputStream, 8192, paramCharset);
  }

  private void fillBuf()
    throws IOException
  {
    int i = this.in.read(this.buf, 0, this.buf.length);
    if (i == -1)
      throw new EOFException();
    this.pos = 0;
    this.end = i;
  }

  public void close()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf != null)
      {
        this.buf = null;
        this.in.close();
      }
      return;
    }
  }

  public boolean hasUnterminatedLine()
  {
    return this.end == -1;
  }

  public int readInt()
    throws IOException
  {
    String str = readLine();
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new IOException("expected an int but was \"" + str + "\"");
  }

  public String readLine()
    throws IOException
  {
    synchronized (this.in)
    {
      if (this.buf == null)
        throw new IOException("LineReader is closed");
    }
    if (this.pos >= this.end)
      fillBuf();
    int i = this.pos;
    while (true)
    {
      if (i != this.end)
      {
        if (this.buf[i] != 10)
          break label258;
        if ((i == this.pos) || (this.buf[(i - 1)] != 13))
          break label252;
      }
      label252: for (int j = i - 1; ; j = i)
      {
        Object localObject2 = new String(this.buf, this.pos, j - this.pos);
        this.pos = (i + 1);
        return localObject2;
        localObject2 = new ByteArrayOutputStream(this.end - this.pos + 80)
        {
          public String toString()
          {
            if ((this.count > 0) && (this.buf[(this.count - 1)] == 13));
            for (int i = this.count - 1; ; i = this.count)
              return new String(this.buf, 0, i);
          }
        };
        while (true)
        {
          ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, this.end - this.pos);
          this.end = -1;
          fillBuf();
          i = this.pos;
          while (i != this.end)
          {
            if (this.buf[i] == 10)
            {
              if (i != this.pos)
                ((ByteArrayOutputStream)localObject2).write(this.buf, this.pos, i - this.pos);
              this.pos = (i + 1);
              localObject2 = ((ByteArrayOutputStream)localObject2).toString();
              return localObject2;
            }
            i += 1;
          }
        }
      }
      label258: i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.StrictLineReader
 * JD-Core Version:    0.6.2
 */