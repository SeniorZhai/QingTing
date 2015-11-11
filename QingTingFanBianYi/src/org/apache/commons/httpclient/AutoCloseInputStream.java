package org.apache.commons.httpclient;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class AutoCloseInputStream extends FilterInputStream
{
  private boolean selfClosed = false;
  private boolean streamOpen = true;
  private ResponseConsumedWatcher watcher = null;

  public AutoCloseInputStream(InputStream paramInputStream, ResponseConsumedWatcher paramResponseConsumedWatcher)
  {
    super(paramInputStream);
    this.watcher = paramResponseConsumedWatcher;
  }

  private void checkClose(int paramInt)
    throws IOException
  {
    if (paramInt == -1)
      notifyWatcher();
  }

  private boolean isReadAllowed()
    throws IOException
  {
    if ((!this.streamOpen) && (this.selfClosed))
      throw new IOException("Attempted read on closed stream.");
    return this.streamOpen;
  }

  private void notifyWatcher()
    throws IOException
  {
    if (this.streamOpen)
    {
      super.close();
      this.streamOpen = false;
      if (this.watcher != null)
        this.watcher.responseConsumed();
    }
  }

  public void close()
    throws IOException
  {
    if (!this.selfClosed)
    {
      this.selfClosed = true;
      notifyWatcher();
    }
  }

  public int read()
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
    {
      i = super.read();
      checkClose(i);
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
    {
      i = super.read(paramArrayOfByte);
      checkClose(i);
    }
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = -1;
    if (isReadAllowed())
    {
      i = super.read(paramArrayOfByte, paramInt1, paramInt2);
      checkClose(i);
    }
    return i;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.AutoCloseInputStream
 * JD-Core Version:    0.6.2
 */