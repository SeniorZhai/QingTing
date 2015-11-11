package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.WritableCallback;
import java.nio.ByteBuffer;

public class BufferedDataSink
  implements DataSink
{
  boolean closePending;
  boolean endPending;
  DataSink mDataSink;
  int mMaxBuffer = 2147483647;
  ByteBufferList mPendingWrites;
  WritableCallback mWritable;

  static
  {
    if (!BufferedDataSink.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public BufferedDataSink(DataSink paramDataSink)
  {
    this.mDataSink = paramDataSink;
    this.mDataSink.setWriteableCallback(new WritableCallback()
    {
      public void onWriteable()
      {
        BufferedDataSink.this.writePending();
      }
    });
  }

  private void writePending()
  {
    if (this.mPendingWrites != null)
    {
      this.mDataSink.write(this.mPendingWrites);
      if (this.mPendingWrites.remaining() == 0)
      {
        this.mPendingWrites = null;
        if (this.endPending)
          this.mDataSink.end();
        if (this.closePending)
          this.mDataSink.close();
      }
    }
    if ((this.mPendingWrites == null) && (this.mWritable != null))
      this.mWritable.onWriteable();
  }

  public void close()
  {
    if (this.mPendingWrites != null)
    {
      this.closePending = true;
      return;
    }
    this.mDataSink.close();
  }

  public void end()
  {
    if (this.mPendingWrites != null)
    {
      this.endPending = true;
      return;
    }
    this.mDataSink.end();
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mDataSink.getClosedCallback();
  }

  public DataSink getDataSink()
  {
    return this.mDataSink;
  }

  public int getMaxBuffer()
  {
    return this.mMaxBuffer;
  }

  public AsyncServer getServer()
  {
    return this.mDataSink.getServer();
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mWritable;
  }

  public boolean isBuffering()
  {
    return this.mPendingWrites != null;
  }

  public boolean isOpen()
  {
    return (!this.closePending) && (this.mDataSink.isOpen());
  }

  public int remaining()
  {
    if (this.mPendingWrites == null)
      return 0;
    return this.mPendingWrites.remaining();
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mDataSink.setClosedCallback(paramCompletedCallback);
  }

  public void setMaxBuffer(int paramInt)
  {
    assert (paramInt >= 0);
    this.mMaxBuffer = paramInt;
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mWritable = paramWritableCallback;
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    write(paramByteBufferList, false);
  }

  protected void write(ByteBufferList paramByteBufferList, boolean paramBoolean)
  {
    if (this.mPendingWrites == null)
      this.mDataSink.write(paramByteBufferList);
    if (paramByteBufferList.remaining() > 0)
    {
      int i = Math.min(paramByteBufferList.remaining(), this.mMaxBuffer);
      if (paramBoolean)
        i = paramByteBufferList.remaining();
      if (i > 0)
      {
        if (this.mPendingWrites == null)
          this.mPendingWrites = new ByteBufferList();
        paramByteBufferList.get(this.mPendingWrites, i);
      }
    }
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    if (this.mPendingWrites == null)
      this.mDataSink.write(paramByteBuffer);
    if (paramByteBuffer.remaining() > 0)
    {
      int i = Math.min(paramByteBuffer.remaining(), this.mMaxBuffer);
      if (i > 0)
      {
        if (this.mPendingWrites == null)
          this.mPendingWrites = new ByteBufferList();
        byte[] arrayOfByte = new byte[i];
        paramByteBuffer.get(arrayOfByte);
        this.mPendingWrites.add(ByteBuffer.wrap(arrayOfByte));
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.BufferedDataSink
 * JD-Core Version:    0.6.2
 */