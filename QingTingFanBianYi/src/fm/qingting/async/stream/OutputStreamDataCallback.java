package fm.qingting.async.stream;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class OutputStreamDataCallback
  implements DataCallback, CompletedCallback
{
  private OutputStream mOutput;

  public OutputStreamDataCallback(OutputStream paramOutputStream)
  {
    this.mOutput = paramOutputStream;
  }

  public void close()
  {
    try
    {
      this.mOutput.close();
      return;
    }
    catch (IOException localIOException)
    {
      onCompleted(localIOException);
    }
  }

  public OutputStream getOutputStream()
  {
    return this.mOutput;
  }

  public void onCompleted(Exception paramException)
  {
    paramException.printStackTrace();
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    try
    {
      while (paramByteBufferList.size() > 0)
      {
        paramDataEmitter = paramByteBufferList.remove();
        this.mOutput.write(paramDataEmitter.array(), paramDataEmitter.arrayOffset() + paramDataEmitter.position(), paramDataEmitter.remaining());
      }
    }
    catch (Exception paramDataEmitter)
    {
      onCompleted(paramDataEmitter);
      paramByteBufferList.clear();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.stream.OutputStreamDataCallback
 * JD-Core Version:    0.6.2
 */