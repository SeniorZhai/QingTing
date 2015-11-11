package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface RequestEntity
{
  public abstract long getContentLength();

  public abstract String getContentType();

  public abstract boolean isRepeatable();

  public abstract void writeRequest(OutputStream paramOutputStream)
    throws IOException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.RequestEntity
 * JD-Core Version:    0.6.2
 */