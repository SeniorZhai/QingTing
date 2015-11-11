package fm.qingting.async;

import fm.qingting.async.callback.DataCallback;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileDataEmitter extends DataEmitterBase
{
  DataCallback callback;
  FileChannel channel;
  File file;
  boolean paused;
  ByteBufferList pending = new ByteBufferList();
  Runnable pumper = new Runnable()
  {
    public void run()
    {
      boolean bool;
      do
      {
        try
        {
          if (FileDataEmitter.this.channel == null)
            FileDataEmitter.this.channel = new FileInputStream(FileDataEmitter.this.file).getChannel();
          if (!FileDataEmitter.this.pending.isEmpty())
          {
            Util.emitAllData(FileDataEmitter.this, FileDataEmitter.this.pending);
            if (!FileDataEmitter.this.pending.isEmpty())
              return;
          }
          ByteBuffer localByteBuffer = ByteBufferList.obtain(8192);
          if (-1 == FileDataEmitter.this.channel.read(localByteBuffer))
          {
            FileDataEmitter.this.report(null);
            return;
          }
        }
        catch (Exception localException)
        {
          FileDataEmitter.this.report(localException);
          return;
        }
        localException.flip();
        FileDataEmitter.this.pending.add(localException);
        Util.emitAllData(FileDataEmitter.this, FileDataEmitter.this.pending);
        if (FileDataEmitter.this.pending.remaining() != 0)
          break;
        bool = FileDataEmitter.this.isPaused();
      }
      while (!bool);
    }
  };
  AsyncServer server;

  public FileDataEmitter(AsyncServer paramAsyncServer, File paramFile)
  {
    this.server = paramAsyncServer;
    this.file = paramFile;
    if (!paramAsyncServer.isAffinityThread());
    for (boolean bool = true; ; bool = false)
    {
      this.paused = bool;
      if (!this.paused)
        doResume();
      return;
    }
  }

  private void doResume()
  {
    this.server.post(this.pumper);
  }

  public void close()
  {
    try
    {
      this.channel.close();
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

  protected void report(Exception paramException)
  {
    try
    {
      this.channel.close();
      label7: super.report(paramException);
      return;
    }
    catch (Exception paramException)
    {
      break label7;
    }
  }

  public void resume()
  {
    this.paused = false;
    doResume();
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.callback = paramDataCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.FileDataEmitter
 * JD-Core Version:    0.6.2
 */