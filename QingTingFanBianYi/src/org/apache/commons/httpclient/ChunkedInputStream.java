package org.apache.commons.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChunkedInputStream extends InputStream
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$ChunkedInputStream;
  private boolean bof = true;
  private int chunkSize;
  private boolean closed = false;
  private boolean eof = false;
  private InputStream in;
  private HttpMethod method = null;
  private int pos;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$ChunkedInputStream == null)
    {
      localClass = class$("org.apache.commons.httpclient.ChunkedInputStream");
      class$org$apache$commons$httpclient$ChunkedInputStream = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$ChunkedInputStream;
    }
  }

  public ChunkedInputStream(InputStream paramInputStream)
    throws IOException
  {
    this(paramInputStream, null);
  }

  public ChunkedInputStream(InputStream paramInputStream, HttpMethod paramHttpMethod)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("InputStream parameter may not be null");
    this.in = paramInputStream;
    this.method = paramHttpMethod;
    this.pos = 0;
  }

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  static void exhaustInputStream(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    while (paramInputStream.read(arrayOfByte) >= 0);
  }

  private static int getChunkSizeFromInputStream(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int i = 0;
    while (true)
    {
      if (i == -1)
      {
        paramInputStream = EncodingUtil.getAsciiString(localByteArrayOutputStream.toByteArray());
        i = paramInputStream.indexOf(';');
        if (i <= 0)
          break label232;
        paramInputStream = paramInputStream.substring(0, i).trim();
      }
      try
      {
        while (true)
        {
          i = Integer.parseInt(paramInputStream.trim(), 16);
          return i;
          int j = paramInputStream.read();
          if (j == -1)
            throw new IOException("chunked stream ended unexpectedly");
          switch (i)
          {
          default:
            throw new RuntimeException("assertion failed");
          case 0:
            switch (j)
            {
            default:
            case 13:
            case 34:
            }
            while (true)
            {
              localByteArrayOutputStream.write(j);
              break;
              i = 1;
              break;
              i = 2;
            }
          case 1:
            if (j == 10)
            {
              i = -1;
              break;
            }
            throw new IOException("Protocol violation: Unexpected single newline character in chunk size");
          case 2:
            switch (j)
            {
            default:
            case 92:
            case 34:
            }
            while (true)
            {
              localByteArrayOutputStream.write(j);
              break;
              localByteArrayOutputStream.write(paramInputStream.read());
              break;
              i = 0;
            }
            label232: paramInputStream = paramInputStream.trim();
          }
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
    throw new IOException("Bad chunk size: " + paramInputStream);
  }

  private void nextChunk()
    throws IOException
  {
    if (!this.bof)
      readCRLF();
    this.chunkSize = getChunkSizeFromInputStream(this.in);
    this.bof = false;
    this.pos = 0;
    if (this.chunkSize == 0)
    {
      this.eof = true;
      parseTrailerHeaders();
    }
  }

  private void parseTrailerHeaders()
    throws IOException
  {
    Object localObject = "US-ASCII";
    while (true)
    {
      int i;
      try
      {
        if (this.method != null)
          localObject = this.method.getParams().getHttpElementCharset();
        localObject = HttpParser.parseHeaders(this.in, (String)localObject);
        if (this.method != null)
        {
          i = 0;
          if (i < localObject.length);
        }
        else
        {
          return;
        }
      }
      catch (HttpException localHttpException)
      {
        LOG.error("Error parsing trailer headers", localHttpException);
        IOException localIOException = new IOException(localHttpException.getMessage());
        ExceptionUtil.initCause(localIOException, localHttpException);
        throw localIOException;
      }
      this.method.addResponseFooter(localHttpException[i]);
      i += 1;
    }
  }

  private void readCRLF()
    throws IOException
  {
    int i = this.in.read();
    int j = this.in.read();
    if ((i != 13) || (j != 10))
      throw new IOException("CRLF expected at end of chunk: " + i + "/" + j);
  }

  public void close()
    throws IOException
  {
    if (!this.closed);
    try
    {
      if (!this.eof)
        exhaustInputStream(this);
      return;
    }
    finally
    {
      this.eof = true;
      this.closed = true;
    }
  }

  public int read()
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof);
    do
    {
      return -1;
      if (this.pos < this.chunkSize)
        break;
      nextChunk();
    }
    while (this.eof);
    this.pos += 1;
    return this.in.read();
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.closed)
      throw new IOException("Attempted read from closed stream.");
    if (this.eof);
    do
    {
      return -1;
      if (this.pos < this.chunkSize)
        break;
      nextChunk();
    }
    while (this.eof);
    paramInt2 = Math.min(paramInt2, this.chunkSize - this.pos);
    paramInt1 = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    this.pos += paramInt1;
    return paramInt1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ChunkedInputStream
 * JD-Core Version:    0.6.2
 */