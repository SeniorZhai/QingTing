package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;

public class BufferedDataEmitter
  implements DataEmitter, DataCallback
{
  ByteBufferList mBuffers = new ByteBufferList();
  DataCallback mDataCallback;
  DataEmitter mEmitter;
  CompletedCallback mEndCallback;
  Exception mEndException;
  boolean mEnded = false;
  private boolean mPaused;

  public BufferedDataEmitter(DataEmitter paramDataEmitter)
  {
    this.mEmitter = paramDataEmitter;
    this.mEmitter.setDataCallback(this);
    this.mEmitter.setEndCallback(new CompletedCallback()
    {
      public void onCompleted(Exception paramAnonymousException)
      {
        BufferedDataEmitter.this.mEnded = true;
        BufferedDataEmitter.this.mEndException = paramAnonymousException;
        if ((BufferedDataEmitter.this.mBuffers.remaining() == 0) && (BufferedDataEmitter.this.mEndCallback != null))
          BufferedDataEmitter.this.mEndCallback.onCompleted(paramAnonymousException);
      }
    });
  }

  public void close()
  {
    this.mEmitter.close();
  }

  public DataCallback getDataCallback()
  {
    return this.mDataCallback;
  }

  public CompletedCallback getEndCallback()
  {
    return this.mEndCallback;
  }

  public AsyncServer getServer()
  {
    return this.mEmitter.getServer();
  }

  public boolean isChunked()
  {
    return false;
  }

  public boolean isPaused()
  {
    return this.mPaused;
  }

  public void onDataAvailable()
  {
    if ((this.mDataCallback != null) && (!this.mPaused) && (this.mBuffers.remaining() > 0))
      this.mDataCallback.onDataAvailable(this, this.mBuffers);
    if ((this.mEnded) && (this.mBuffers.remaining() == 0))
      this.mEndCallback.onCompleted(this.mEndException);
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    paramByteBufferList.get(this.mBuffers);
    onDataAvailable();
  }

  public void pause()
  {
    this.mPaused = true;
  }

  public void resume()
  {
    if (!this.mPaused)
      return;
    this.mPaused = false;
    onDataAvailable();
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mDataCallback = paramDataCallback;
  }

  public void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.mEndCallback = paramCompletedCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.BufferedDataEmitter
 * JD-Core Version:    0.6.2
 */