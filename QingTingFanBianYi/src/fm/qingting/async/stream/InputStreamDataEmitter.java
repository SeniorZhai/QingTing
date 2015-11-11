package fm.qingting.async.stream;

import fm.qingting.async.AsyncServer;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class InputStreamDataEmitter
  implements DataEmitter
{
  DataCallback callback;
  CompletedCallback endCallback;
  InputStream inputStream;
  boolean paused;
  ByteBufferList pending = new ByteBufferList();
  Runnable pumper = new Runnable()
  {
    public void run()
    {
      boolean bool;
      do
      {
        int i;
        try
        {
          if (!InputStreamDataEmitter.this.pending.isEmpty())
          {
            Util.emitAllData(InputStreamDataEmitter.this, InputStreamDataEmitter.this.pending);
            if (!InputStreamDataEmitter.this.pending.isEmpty())
              return;
          }
          ByteBuffer localByteBuffer = ByteBufferList.obtain(8192);
          i = InputStreamDataEmitter.this.inputStream.read(localByteBuffer.array());
          if (-1 == i)
          {
            InputStreamDataEmitter.this.report(null);
            return;
          }
        }
        catch (Exception localException)
        {
          InputStreamDataEmitter.this.report(localException);
          return;
        }
        localException.limit(i);
        InputStreamDataEmitter.this.pending.add(localException);
        Util.emitAllData(InputStreamDataEmitter.this, InputStreamDataEmitter.this.pending);
        if (InputStreamDataEmitter.this.pending.remaining() != 0)
          break;
        bool = InputStreamDataEmitter.this.isPaused();
      }
      while (!bool);
    }
  };
  AsyncServer server;

  public InputStreamDataEmitter(AsyncServer paramAsyncServer, InputStream paramInputStream)
  {
    this.server = paramAsyncServer;
    this.inputStream = paramInputStream;
    doResume();
  }

  private void doResume()
  {
    this.server.post(this.pumper);
  }

  private void report(Exception paramException)
  {
    try
    {
      this.inputStream.close();
      label7: if (this.endCallback != null)
        this.endCallback.onCompleted(paramException);
      return;
    }
    catch (Exception paramException)
    {
      break label7;
    }
  }

  public void close()
  {
    report(null);
    try
    {
      this.inputStream.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public DataCallback getDataCallback()
  {
    return this.callback;
  }

  public CompletedCallback getEndCallback()
  {
    return this.endCallback;
  }

  public AsyncServer getServer()
  {
    return this.server;
  }

  public boolean isChunked()
  {
    return false;
  }

  public boolean isPaused()
  {
    return this.paused;
  }

  public void pause()
  {
    this.paused = true;
  }

  public void resume()
  {
    this.paused = false;
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.callback = paramDataCallback;
  }

  public void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.endCallback = paramCompletedCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.stream.InputStreamDataEmitter
 * JD-Core Version:    0.6.2
 */