package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.wrapper.AsyncSocketWrapper;
import fm.qingting.async.wrapper.DataEmitterWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public class Util
{
  static
  {
    if (!Util.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public static void emitAllData(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    Object localObject = null;
    while (!paramDataEmitter.isPaused())
    {
      DataCallback localDataCallback = paramDataEmitter.getDataCallback();
      localObject = localDataCallback;
      if (localDataCallback != null)
      {
        int i = paramByteBufferList.remaining();
        localObject = localDataCallback;
        if (i > 0)
        {
          localDataCallback.onDataAvailable(paramDataEmitter, paramByteBufferList);
          localObject = localDataCallback;
          if (i == paramByteBufferList.remaining())
          {
            localObject = localDataCallback;
            if (localDataCallback == paramDataEmitter.getDataCallback())
            {
              System.out.println("Data: " + paramByteBufferList.peekString());
              System.out.println("handler: " + localDataCallback);
              if (!$assertionsDisabled)
                throw new AssertionError();
              throw new RuntimeException("mDataHandler failed to consume data, yet remains the mDataHandler.");
            }
          }
        }
      }
    }
    if ((paramByteBufferList.remaining() != 0) && (!paramDataEmitter.isPaused()))
    {
      System.out.println("Data: " + paramByteBufferList.peekString());
      System.out.println("handler: " + localObject);
      if (!$assertionsDisabled)
        throw new AssertionError();
      throw new RuntimeException("mDataHandler failed to consume data, yet remains the mDataHandler.");
    }
  }

  public static void emitAllData(DataEmitter paramDataEmitter, ByteBuffer paramByteBuffer)
  {
    ByteBufferList localByteBufferList = new ByteBufferList();
    localByteBufferList.add(paramByteBuffer);
    emitAllData(paramDataEmitter, localByteBufferList);
    paramByteBuffer.position(paramByteBuffer.limit());
  }

  public static DataEmitter getWrappedDataEmitter(DataEmitter paramDataEmitter, Class paramClass)
  {
    if (paramClass.isInstance(paramDataEmitter))
      return paramDataEmitter;
    while ((paramDataEmitter instanceof DataEmitterWrapper))
    {
      AsyncSocket localAsyncSocket = ((AsyncSocketWrapper)paramDataEmitter).getSocket();
      paramDataEmitter = localAsyncSocket;
      if (paramClass.isInstance(localAsyncSocket))
        return localAsyncSocket;
    }
    return null;
  }

  public static AsyncSocket getWrappedSocket(AsyncSocket paramAsyncSocket, Class paramClass)
  {
    if (paramClass.isInstance(paramAsyncSocket))
      return paramAsyncSocket;
    while ((paramAsyncSocket instanceof AsyncSocketWrapper))
    {
      AsyncSocket localAsyncSocket = ((AsyncSocketWrapper)paramAsyncSocket).getSocket();
      paramAsyncSocket = localAsyncSocket;
      if (paramClass.isInstance(localAsyncSocket))
        return localAsyncSocket;
    }
    return null;
  }

  public static void pump(DataEmitter paramDataEmitter, DataSink paramDataSink, CompletedCallback paramCompletedCallback)
  {
    paramDataEmitter.setDataCallback(new DataCallback()
    {
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        this.val$sink.write(paramAnonymousByteBufferList);
        if (paramAnonymousByteBufferList.remaining() > 0)
          paramAnonymousDataEmitter.pause();
      }
    });
    paramDataSink.setWriteableCallback(new WritableCallback()
    {
      public void onWriteable()
      {
        this.val$emitter.resume();
      }
    });
    paramCompletedCallback = new CompletedCallback()
    {
      boolean reported;

      public void onCompleted(Exception paramAnonymousException)
      {
        if (this.reported)
          return;
        this.reported = true;
        this.val$callback.onCompleted(paramAnonymousException);
      }
    };
    paramDataEmitter.setEndCallback(paramCompletedCallback);
    paramDataSink.setClosedCallback(paramCompletedCallback);
  }

  public static void pump(File paramFile, DataSink paramDataSink, final CompletedCallback paramCompletedCallback)
  {
    if ((paramFile == null) || (paramDataSink == null));
    try
    {
      paramCompletedCallback.onCompleted(null);
      return;
      paramFile = new FileInputStream(paramFile);
      pump(paramFile, paramDataSink, new CompletedCallback()
      {
        public void onCompleted(Exception paramAnonymousException)
        {
          try
          {
            this.val$is.close();
            paramCompletedCallback.onCompleted(paramAnonymousException);
            return;
          }
          catch (IOException paramAnonymousException)
          {
            paramCompletedCallback.onCompleted(paramAnonymousException);
          }
        }
      });
      return;
    }
    catch (Exception paramFile)
    {
      paramCompletedCallback.onCompleted(paramFile);
    }
  }

  public static void pump(InputStream paramInputStream, final DataSink paramDataSink, final CompletedCallback paramCompletedCallback)
  {
    paramCompletedCallback = new CompletedCallback()
    {
      boolean reported;

      public void onCompleted(Exception paramAnonymousException)
      {
        if (this.reported)
          return;
        this.reported = true;
        this.val$callback.onCompleted(paramAnonymousException);
      }
    };
    paramInputStream = new WritableCallback()
    {
      byte[] buffer = new byte[8192];
      ByteBuffer pending = ByteBuffer.wrap(this.buffer);

      private void close()
      {
        try
        {
          this.val$is.close();
          return;
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }

      public void onWriteable()
      {
        try
        {
          int i;
          int j;
          do
          {
            if (this.pending.remaining() == 0)
            {
              i = this.val$is.read(this.buffer);
              if (i == -1)
              {
                close();
                paramCompletedCallback.onCompleted(null);
                return;
              }
              this.pending.position(0);
              this.pending.limit(i);
            }
            i = this.pending.remaining();
            paramDataSink.write(this.pending);
            j = this.pending.remaining();
          }
          while (i != j);
          return;
        }
        catch (Exception localException)
        {
          close();
          paramCompletedCallback.onCompleted(localException);
        }
      }
    };
    paramDataSink.setWriteableCallback(paramInputStream);
    paramDataSink.setClosedCallback(paramCompletedCallback);
    paramInputStream.onWriteable();
  }

  public static void stream(AsyncSocket paramAsyncSocket1, AsyncSocket paramAsyncSocket2, CompletedCallback paramCompletedCallback)
  {
    pump(paramAsyncSocket1, paramAsyncSocket2, paramCompletedCallback);
    pump(paramAsyncSocket2, paramAsyncSocket1, paramCompletedCallback);
  }

  public static void writeAll(final DataSink paramDataSink, ByteBufferList paramByteBufferList, final CompletedCallback paramCompletedCallback)
  {
    paramByteBufferList.remaining();
    paramByteBufferList = new WritableCallback()
    {
      public void onWriteable()
      {
        if (this.val$bb.remaining() > 0)
          paramDataSink.write(this.val$bb);
        if ((this.val$bb.remaining() == 0) && (paramCompletedCallback != null))
          paramCompletedCallback.onCompleted(null);
      }
    };
    paramDataSink.setWriteableCallback(paramByteBufferList);
    paramByteBufferList.onWriteable();
  }

  public static void writeAll(DataSink paramDataSink, byte[] paramArrayOfByte, CompletedCallback paramCompletedCallback)
  {
    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
    ByteBufferList localByteBufferList = new ByteBufferList();
    localByteBufferList.add(paramArrayOfByte);
    writeAll(paramDataSink, localByteBufferList, paramCompletedCallback);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.Util
 * JD-Core Version:    0.6.2
 */