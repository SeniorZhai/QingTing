package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;

public class ByteArrayRequestEntity
  implements RequestEntity
{
  private byte[] content;
  private String contentType;

  public ByteArrayRequestEntity(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, null);
  }

  public ByteArrayRequestEntity(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.content = paramArrayOfByte;
    this.contentType = paramString;
  }

  public byte[] getContent()
  {
    return this.content;
  }

  public long getContentLength()
  {
    return this.content.length;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public void writeRequest(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.content);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.ByteArrayRequestEntity
 * JD-Core Version:    0.6.2
 */