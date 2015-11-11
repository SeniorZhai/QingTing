package org.apache.commons.httpclient.methods.multipart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FilePart extends PartBase
{
  public static final String DEFAULT_CHARSET = "ISO-8859-1";
  public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
  public static final String DEFAULT_TRANSFER_ENCODING = "binary";
  protected static final String FILE_NAME = "; filename=";
  private static final byte[] FILE_NAME_BYTES;
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$methods$multipart$FilePart;
  private PartSource source;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$multipart$FilePart == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.multipart.FilePart");
      class$org$apache$commons$httpclient$methods$multipart$FilePart = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      FILE_NAME_BYTES = EncodingUtil.getAsciiBytes("; filename=");
      return;
      localClass = class$org$apache$commons$httpclient$methods$multipart$FilePart;
    }
  }

  public FilePart(String paramString, File paramFile)
    throws FileNotFoundException
  {
    this(paramString, new FilePartSource(paramFile), null, null);
  }

  public FilePart(String paramString1, File paramFile, String paramString2, String paramString3)
    throws FileNotFoundException
  {
    this(paramString1, new FilePartSource(paramFile), paramString2, paramString3);
  }

  public FilePart(String paramString1, String paramString2, File paramFile)
    throws FileNotFoundException
  {
    this(paramString1, new FilePartSource(paramString2, paramFile), null, null);
  }

  public FilePart(String paramString1, String paramString2, File paramFile, String paramString3, String paramString4)
    throws FileNotFoundException
  {
    this(paramString1, new FilePartSource(paramString2, paramFile), paramString3, paramString4);
  }

  public FilePart(String paramString, PartSource paramPartSource)
  {
    this(paramString, paramPartSource, null, null);
  }

  public FilePart(String paramString1, PartSource paramPartSource, String paramString2, String paramString3)
  {
    super(paramString1, str, paramString2, "binary");
    if (paramPartSource == null)
      throw new IllegalArgumentException("Source may not be null");
    this.source = paramPartSource;
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

  protected PartSource getSource()
  {
    LOG.trace("enter getSource()");
    return this.source;
  }

  protected long lengthOfData()
    throws IOException
  {
    LOG.trace("enter lengthOfData()");
    return this.source.getLength();
  }

  protected void sendData(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendData(OutputStream out)");
    if (lengthOfData() == 0L)
    {
      LOG.debug("No data to send.");
      return;
    }
    byte[] arrayOfByte = new byte[4096];
    InputStream localInputStream = this.source.createInputStream();
    try
    {
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i < 0)
          return;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      localInputStream.close();
    }
    throw paramOutputStream;
  }

  protected void sendDispositionHeader(OutputStream paramOutputStream)
    throws IOException
  {
    LOG.trace("enter sendDispositionHeader(OutputStream out)");
    super.sendDispositionHeader(paramOutputStream);
    String str = this.source.getFileName();
    if (str != null)
    {
      paramOutputStream.write(FILE_NAME_BYTES);
      paramOutputStream.write(Part.QUOTE_BYTES);
      paramOutputStream.write(EncodingUtil.getAsciiBytes(str));
      paramOutputStream.write(Part.QUOTE_BYTES);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.FilePart
 * JD-Core Version:    0.6.2
 */