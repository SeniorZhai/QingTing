package fm.qingting.async.stream;

import fm.qingting.async.AsyncServer;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataSink;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.WritableCallback;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class OutputStreamDataSink
  implements DataSink
{
  boolean closeReported;
  CompletedCallback mClosedCallback;
  OutputStream mStream;
  WritableCallback mWritable;

  public OutputStreamDataSink()
  {
  }

  public OutputStreamDataSink(OutputStream paramOutputStream)
  {
    setOutputStream(paramOutputStream);
  }

  public void close()
  {
    try
    {
      if (this.mStream != null)
        this.mStream.close();
      reportClose(null);
      return;
    }
    catch (IOException localIOException)
    {
      reportClose(localIOException);
    }
  }

  public void end()
  {
    close();
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mClosedCallback;
  }

  public OutputStream getOutputStream()
  {
    return this.mStream;
  }

  public AsyncServer getServer()
  {
    return AsyncServer.getDefault();
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mWritable;
  }

  public boolean isOpen()
  {
    return this.closeReported;
  }

  public void reportClose(Exception paramException)
  {
    if (this.closeReported);
    do
    {
      return;
      this.closeReported = true;
    }
    while (this.mClosedCallback == null);
    this.mClosedCallback.onCompleted(paramException);
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mClosedCallback = paramCompletedCallback;
  }

  public void setOutputStream(OutputStream paramOutputStream)
  {
    this.mStream = paramOutputStream;
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mWritable = paramWritableCallback;
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    try
    {
      while (paramByteBufferList.size() > 0)
      {
        ByteBuffer localByteBuffer = paramByteBufferList.remove();
        this.mStream.write(localByteBuffer.array(), localByteBuffer.arrayOffset() + localByteBuffer.position(), localByteBuffer.remaining());
        ByteBufferList.reclaim(localByteBuffer);
      }
    }
    catch (IOException localIOException)
    {
      reportClose(localIOException);
      paramByteBufferList.clear();
    }
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    try
    {
      this.mStream.write(paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining());
      paramByteBuffer.position(0);
      paramByteBuffer.limit(0);
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        reportClose(localIOException);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.stream.OutputStreamDataSink
 * JD-Core Version:    0.6.2
 */