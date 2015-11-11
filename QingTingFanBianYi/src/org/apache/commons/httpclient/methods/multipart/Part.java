package org.apache.commons.httpclient.methods.multipart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Part
{
  protected static final String BOUNDARY = "----------------314159265358979323846";
  protected static final byte[] BOUNDARY_BYTES;
  protected static final String CHARSET = "; charset=";
  protected static final byte[] CHARSET_BYTES;
  protected static final String CONTENT_DISPOSITION = "Content-Disposition: form-data; name=";
  protected static final byte[] CONTENT_DISPOSITION_BYTES;
  protected static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding: ";
  protected static final byte[] CONTENT_TRANSFER_ENCODING_BYTES;
  protected static final String CONTENT_TYPE = "Content-Type: ";
  protected static final byte[] CONTENT_TYPE_BYTES;
  protected static final String CRLF = "\r\n";
  protected static final byte[] CRLF_BYTES;
  private static final byte[] DEFAULT_BOUNDARY_BYTES;
  protected static final String EXTRA = "--";
  protected static final byte[] EXTRA_BYTES;
  private static final Log LOG;
  protected static final String QUOTE = "\"";
  protected static final byte[] QUOTE_BYTES;
  static Class class$org$apache$commons$httpclient$methods$multipart$Part;
  private byte[] boundaryBytes;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$multipart$Part == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.multipart.Part");
      class$org$apache$commons$httpclient$methods$multipart$Part = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      BOUNDARY_BYTES = EncodingUtil.getAsciiBytes("----------------314159265358979323846");
      DEFAULT_BOUNDARY_BYTES = BOUNDARY_BYTES;
      CRLF_BYTES = EncodingUtil.getAsciiBytes("\r\n");
      QUOTE_BYTES = EncodingUtil.getAsciiBytes("\"");
      EXTRA_BYTES = EncodingUtil.getAsciiBytes("--");
      CONTENT_DISPOSITION_BYTES = EncodingUtil.getAsciiBytes("Content-Disposition: form-data; name=");
      CONTENT_TYPE_BYTES = EncodingUtil.getAsciiBytes("Content-Type: ");
      CHARSET_BYTES = EncodingUtil.getAsciiBytes("; charset=");
      CONTENT_TRANSFER_ENCODING_BYTES = EncodingUtil.getAsciiBytes("Content-Transfer-Encoding: ");
      return;
      localClass = class$org$apache$commons$httpclient$methods$multipart$Part;
    }
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

  public static String getBoundary()
  {
    return "----------------314159265358979323846";
  }

  public static long getLengthOfParts(Part[] paramArrayOfPart)
    throws IOException
  {
    return getLengthOfParts(paramArrayOfPart, DEFAULT_BOUNDARY_BYTES);
  }

  public static long getLengthOfParts(Part[] paramArrayOfPart, byte[] paramArrayOfByte)
    throws IOException
  {
    LOG.trace("getLengthOfParts(Parts[])");
    if (paramArrayOfPart == null)
      throw new IllegalArgumentException("Parts may not be null");
    long l1 = 0L;
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfPart.length)
        return l1 + EXTRA_BYTES.length + paramArrayOfByte.length + EXTRA_BYTES.length + CRLF_BYTES.length;
      paramArrayOfPart[i].setPartBoundary(paramArrayOfByte);
      long l2 = paramArrayOfPart[i].length();
      if (l2 < 0L)
        return -1L;
      l1 += l2;
      i += 1;
    }
  }

  public static void sendParts(OutputStream paramOutputStream, Part[] paramArrayOfPart)
    throws IOException
  {
    sendParts(paramOutputStream, paramArrayOfPart, DEFAULT_BOUNDARY_BYTES);
  }

  public static void sendParts(OutputStream paramOutputStream, Part[] paramArrayOfPart, byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfPart == null)
      throw new IllegalArgumentException("Parts may not be null");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      throw new IllegalArgumentException("partBoundary may not be empty");
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfPart.length)
      {
        paramOutputStream.write(EXTRA_BYTES);
        paramOutputStream.write(paramArrayOfByte);
        paramOutputStream.write(EXTRA_BYTES);
        paramOutputStream.write(CRLF_BYTES);
        return;
      }
      paramArrayOfPart[i].setPartBoundary(paramArrayOfByte);
      paramArrayOfPart[i].send(paramOutputStream);
      i += 1;
    }
  }

  public abstract String getCharSet();

  public abstract String getContentType();

  public abstract String getName();

  protected byte[] getPartBoundary()
  {
    if (this.boundaryBytes == null)
      return DEFAULT_BOUNDARY_BYTES;
    return this.boundaryBytes;
  }

  public abstract String getTransferEncoding();

  public boolean isRepeatable()
  {
    return true;
  }

  public long length()
    throws IOException
  {
    LOG.trace("enter length()");
    if (lengthOfData() < 0L)
      return -1L;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    sendStart(localByteArrayOutputStream);
    sendDispositionHeader(localByteArrayOutputStream);
    sendContentTypeHeader(localByteArrayOutputStream);
    sendTransferEncodingHeader(localByteArrayOutputStream);
    sendEndOfHeader(localByteArrayOutputStream);
    sendEnd(localByteArrayOutputStream);
    return localByteArrayOutputStream.size() + lengthOfData();
  }

  protected abstract long lengthOfData()
    throws IOException;

  public void send(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter send(OutputStream out)");
    sendStart(paramOutputStream);
    sendDispositionHeader(paramOutputStream);
    sendContentTypeHeader(paramOutputStream);
    sendTransferEncodingHeader(paramOutputStream);
    sendEndOfHeader(paramOutputStream);
    sendData(paramOutputStream);
    sendEnd(paramOutputStream);
  }

  protected void sendContentTypeHeader(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendContentTypeHeader(OutputStream out)");
    String str = getContentType();
    if (str != null)
    {
      paramOutputStream.write(CRLF_BYTES);
      paramOutputStream.write(CONTENT_TYPE_BYTES);
      paramOutputStream.write(EncodingUtil.getAsciiBytes(str));
      str = getCharSet();
      if (str != null)
      {
        paramOutputStream.write(CHARSET_BYTES);
        paramOutputStream.write(EncodingUtil.getAsciiBytes(str));
      }
    }
  }

  protected abstract void sendData(OutputStream paramOutputStream)
    throws IOException;

  protected void sendDispositionHeader(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendDispositionHeader(OutputStream out)");
    paramOutputStream.write(CONTENT_DISPOSITION_BYTES);
    paramOutputStream.write(QUOTE_BYTES);
    paramOutputStream.write(EncodingUtil.getAsciiBytes(getName()));
    paramOutputStream.write(QUOTE_BYTES);
  }

  protected void sendEnd(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendEnd(OutputStream out)");
    paramOutputStream.write(CRLF_BYTES);
  }

  protected void sendEndOfHeader(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendEndOfHeader(OutputStream out)");
    paramOutputStream.write(CRLF_BYTES);
    paramOutputStream.write(CRLF_BYTES);
  }

  protected void sendStart(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendStart(OutputStream out)");
    paramOutputStream.write(EXTRA_BYTES);
    paramOutputStream.write(getPartBoundary());
    paramOutputStream.write(CRLF_BYTES);
  }

  protected void sendTransferEncodingHeader(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendTransferEncodingHeader(OutputStream out)");
    String str = getTransferEncoding();
    if (str != null)
    {
      paramOutputStream.write(CRLF_BYTES);
      paramOutputStream.write(CONTENT_TRANSFER_ENCODING_BYTES);
      paramOutputStream.write(EncodingUtil.getAsciiBytes(str));
    }
  }

  void setPartBoundary(byte[] paramArrayOfByte)
  {
    this.boundaryBytes = paramArrayOfByte;
  }

  public String toString()
  {
    return getName();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.Part
 * JD-Core Version:    0.6.2
 */