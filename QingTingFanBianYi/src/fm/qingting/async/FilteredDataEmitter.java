package fm.qingting.async;

import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.wrapper.DataEmitterWrapper;

public class FilteredDataEmitter extends DataEmitterBase
  implements DataEmitter, DataCallback, DataEmitterWrapper, DataTrackingEmitter
{
  DataEmitter mEmitter;
  int totalRead;
  DataTrackingEmitter.DataTracker tracker;

  public void close()
  {
    this.mEmitter.close();
  }

  public int getBytesRead()
  {
    return this.totalRead;
  }

  public DataEmitter getDataEmitter()
  {
    return this.mEmitter;
  }

  public DataTrackingEmitter.DataTracker getDataTracker()
  {
    return this.tracker;
  }

  public AsyncServer getServer()
  {
    return this.mEmitter.getServer();
  }

  public boolean isChunked()
  {
    return this.mEmitter.isChunked();
  }

  public boolean isPaused()
  {
    return this.mEmitter.isPaused();
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    if (paramByteBufferList != null)
      this.totalRead += paramByteBufferList.remaining();
    Util.emitAllData(this, paramByteBufferList);
    if (paramByteBufferList != null)
      this.totalRead -= paramByteBufferList.remaining();
    if ((this.tracker != null) && (paramByteBufferList != null))
      this.tracker.onData(this.totalRead);
  }

  public void pause()
  {
    this.mEmitter.pause();
  }

  public void resume()
  {
    this.mEmitter.resume();
  }

  public void setDataEmitter(DataEmitter paramDataEmitter)
  {
    if (this.mEmitter != null)
      this.mEmitter.setDataCallback(null);
    this.mEmitter = paramDataEmitter;
    this.mEmitter.setDataCallback(this);
    this.mEmitter.setEndCallback(new CompletedCallback()
    {
      public void onCompleted(Exception paramAnonymousException)
      {
        FilteredDataEmitter.this.report(paramAnonymousException);
      }
    });
  }

  public void setDataTracker(DataTrackingEmitter.DataTracker paramDataTracker)
  {
    this.tracker = paramDataTracker;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.FilteredDataEmitter
 * JD-Core Version:    0.6.2
 */