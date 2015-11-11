package fm.qingting.async;

import android.util.Log;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.callback.WritableCallback;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class AsyncNetworkSocket
  implements AsyncSocket
{
  boolean closeReported;
  private ChannelWrapper mChannel;
  CompletedCallback mClosedHander;
  private CompletedCallback mCompletedCallback;
  DataCallback mDataHandler;
  boolean mEndReported;
  private SelectionKey mKey;
  boolean mPaused = false;
  Exception mPendingEndException;
  private AsyncServer mServer;
  int mToAlloc = 0;
  WritableCallback mWriteableHandler;
  int maxAlloc;
  private ByteBufferList pending;
  InetSocketAddress socketAddress;

  static
  {
    if (!AsyncNetworkSocket.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  private void handleRemaining(int paramInt)
  {
    if (paramInt > 0)
    {
      assert (!this.mChannel.isChunked());
      this.mKey.interestOps(5);
      return;
    }
    this.mKey.interestOps(1);
  }

  private void spitPending()
  {
    if (this.pending != null)
    {
      Util.emitAllData(this, this.pending);
      if (this.pending.remaining() == 0)
        this.pending = null;
    }
  }

  void attach(DatagramChannel paramDatagramChannel)
    throws IOException
  {
    this.mChannel = new DatagramChannelWrapper(paramDatagramChannel);
    this.maxAlloc = 8192;
  }

  void attach(SocketChannel paramSocketChannel, InetSocketAddress paramInetSocketAddress)
    throws IOException
  {
    this.socketAddress = paramInetSocketAddress;
    this.maxAlloc = 262144;
    this.mChannel = new SocketChannelWrapper(paramSocketChannel);
  }

  public void close()
  {
    closeInternal();
    reportClose(null);
  }

  public void closeInternal()
  {
    this.mKey.cancel();
    try
    {
      this.mChannel.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public void end()
  {
    this.mChannel.shutdownOutput();
  }

  ChannelWrapper getChannel()
  {
    return this.mChannel;
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mClosedHander;
  }

  public DataCallback getDataCallback()
  {
    return this.mDataHandler;
  }

  public CompletedCallback getEndCallback()
  {
    return this.mCompletedCallback;
  }

  public int getLocalPort()
  {
    return this.mChannel.getLocalPort();
  }

  public InetSocketAddress getRemoteAddress()
  {
    return this.socketAddress;
  }

  public AsyncServer getServer()
  {
    return this.mServer;
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mWriteableHandler;
  }

  public boolean isChunked()
  {
    return this.mChannel.isChunked();
  }

  public boolean isOpen()
  {
    return (this.mChannel.isConnected()) && (this.mKey.isValid());
  }

  public boolean isPaused()
  {
    return this.mPaused;
  }

  public void onDataWritable()
  {
    assert (this.mWriteableHandler != null);
    this.mWriteableHandler.onWriteable();
  }

  int onReadable()
  {
    int k = 1;
    int i = 0;
    int m = 0;
    int j = 0;
    spitPending();
    if (this.mPaused);
    label218: 
    do
    {
      return j;
      j = m;
      ByteBufferList localByteBufferList;
      while (true)
      {
        int n;
        try
        {
          ByteBuffer localByteBuffer = ByteBufferList.obtain(Math.min(Math.max(this.mToAlloc, 4096), this.maxAlloc));
          j = m;
          n = this.mChannel.read(localByteBuffer);
          if (n < 0)
          {
            j = m;
            closeInternal();
            if (n <= 0)
              break label218;
            j = i;
            this.mToAlloc = (n * 2);
            j = i;
            localByteBuffer.limit(localByteBuffer.position());
            j = i;
            localByteBuffer.position(0);
            j = i;
            localByteBufferList = new ByteBufferList(new ByteBuffer[] { localByteBuffer });
            j = i;
            Util.emitAllData(this, localByteBufferList);
            j = i;
            if (localByteBuffer.remaining() == 0)
              break label218;
            j = i;
            if ($assertionsDisabled)
              break;
            j = i;
            if (this.pending == null)
              break;
            j = i;
            throw new AssertionError();
          }
        }
        catch (Exception localException)
        {
          closeInternal();
          reportEndPending(localException);
          reportClose(localException);
          return j;
        }
        i = 0 + n;
        k = 0;
      }
      j = i;
      this.pending = localByteBufferList;
      j = i;
    }
    while (k == 0);
    j = i;
    reportEndPending(null);
    j = i;
    reportClose(null);
    return i;
  }

  public void pause()
  {
    if (this.mServer.getAffinity() != Thread.currentThread())
      this.mServer.run(new Runnable()
      {
        public void run()
        {
          AsyncNetworkSocket.this.pause();
        }
      });
    while (this.mPaused)
      return;
    this.mPaused = true;
    try
    {
      this.mKey.interestOps(this.mKey.interestOps() & 0xFFFFFFFE);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void reportClose(Exception paramException)
  {
    if (this.closeReported);
    do
    {
      return;
      this.closeReported = true;
    }
    while (this.mClosedHander == null);
    this.mClosedHander.onCompleted(paramException);
    this.mClosedHander = null;
  }

  void reportEnd(Exception paramException)
  {
    if (this.mEndReported);
    do
    {
      return;
      this.mEndReported = true;
      if (this.mCompletedCallback != null)
      {
        this.mCompletedCallback.onCompleted(paramException);
        return;
      }
    }
    while (paramException == null);
    Log.e("NIO", "Unhandled exception", paramException);
  }

  void reportEndPending(Exception paramException)
  {
    if (this.pending != null)
    {
      this.mPendingEndException = paramException;
      return;
    }
    reportEnd(paramException);
  }

  public void resume()
  {
    if (this.mServer.getAffinity() != Thread.currentThread())
      this.mServer.run(new Runnable()
      {
        public void run()
        {
          AsyncNetworkSocket.this.resume();
        }
      });
    while (true)
    {
      return;
      if (!this.mPaused)
        continue;
      this.mPaused = false;
      try
      {
        this.mKey.interestOps(this.mKey.interestOps() | 0x1);
        label58: spitPending();
        if (isOpen())
          continue;
        reportEndPending(this.mPendingEndException);
        return;
      }
      catch (Exception localException)
      {
        break label58;
      }
    }
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mClosedHander = paramCompletedCallback;
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mDataHandler = paramDataCallback;
  }

  public void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.mCompletedCallback = paramCompletedCallback;
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mWriteableHandler = paramWritableCallback;
  }

  void setup(AsyncServer paramAsyncServer, SelectionKey paramSelectionKey)
  {
    this.mServer = paramAsyncServer;
    this.mKey = paramSelectionKey;
  }

  public void write(final ByteBufferList paramByteBufferList)
  {
    if (this.mServer.getAffinity() != Thread.currentThread())
      this.mServer.run(new Runnable()
      {
        public void run()
        {
          AsyncNetworkSocket.this.write(paramByteBufferList);
        }
      });
    do
    {
      return;
      if (this.mChannel.isConnected())
        break;
    }
    while (($assertionsDisabled) || (!this.mChannel.isChunked()));
    throw new AssertionError();
    try
    {
      ByteBuffer[] arrayOfByteBuffer = paramByteBufferList.getAllArray();
      this.mChannel.write(arrayOfByteBuffer);
      paramByteBufferList.addAll(arrayOfByteBuffer);
      handleRemaining(paramByteBufferList.remaining());
      return;
    }
    catch (Exception paramByteBufferList)
    {
      close();
      reportEndPending(paramByteBufferList);
      reportClose(paramByteBufferList);
    }
  }

  public void write(final ByteBuffer paramByteBuffer)
  {
    if (this.mServer.getAffinity() != Thread.currentThread())
      this.mServer.run(new Runnable()
      {
        public void run()
        {
          AsyncNetworkSocket.this.write(paramByteBuffer);
        }
      });
    while (true)
    {
      return;
      try
      {
        if (!this.mChannel.isConnected())
        {
          if (($assertionsDisabled) || (!this.mChannel.isChunked()))
            continue;
          throw new AssertionError();
        }
      }
      catch (Exception paramByteBuffer)
      {
        close();
        reportEndPending(paramByteBuffer);
        reportClose(paramByteBuffer);
        return;
      }
    }
    this.mChannel.write(paramByteBuffer);
    handleRemaining(paramByteBuffer.remaining());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.AsyncNetworkSocket
 * JD-Core Version:    0.6.2
 */