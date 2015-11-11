package org.apache.commons.httpclient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class Wire
{
  public static Wire CONTENT_WIRE = new Wire(LogFactory.getLog("httpclient.wire.content"));
  public static Wire HEADER_WIRE = new Wire(LogFactory.getLog("httpclient.wire.header"));
  private Log log;

  private Wire(Log paramLog)
  {
    this.log = paramLog;
  }

  private void wire(String paramString, InputStream paramInputStream)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      int i = paramInputStream.read();
      if (i == -1)
      {
        if (localStringBuffer.length() > 0)
        {
          localStringBuffer.append("\"");
          localStringBuffer.insert(0, "\"");
          localStringBuffer.insert(0, paramString);
          this.log.debug(localStringBuffer.toString());
        }
        return;
      }
      if (i == 13)
      {
        localStringBuffer.append("[\\r]");
      }
      else if (i == 10)
      {
        localStringBuffer.append("[\\n]\"");
        localStringBuffer.insert(0, "\"");
        localStringBuffer.insert(0, paramString);
        this.log.debug(localStringBuffer.toString());
        localStringBuffer.setLength(0);
      }
      else if ((i < 32) || (i > 127))
      {
        localStringBuffer.append("[0x");
        localStringBuffer.append(Integer.toHexString(i));
        localStringBuffer.append("]");
      }
      else
      {
        localStringBuffer.append((char)i);
      }
    }
  }

  public boolean enabled()
  {
    return this.log.isDebugEnabled();
  }

  public void input(int paramInt)
    throws IOException
  {
    input(new byte[] { (byte)paramInt });
  }

  public void input(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", paramInputStream);
  }

  public void input(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Input may not be null");
    input(paramString.getBytes());
  }

  public void input(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void input(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Input may not be null");
    wire("<< ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void output(int paramInt)
    throws IOException
  {
    output(new byte[] { (byte)paramInt });
  }

  public void output(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", paramInputStream);
  }

  public void output(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Output may not be null");
    output(paramString.getBytes());
  }

  public void output(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte));
  }

  public void output(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Output may not be null");
    wire(">> ", new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.Wire
 * JD-Core Version:    0.6.2
 */