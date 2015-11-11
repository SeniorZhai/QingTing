package fm.qingting.async;

import fm.qingting.async.callback.DataCallback;

public class DataEmitterReader
  implements DataCallback
{
  ByteBufferList mPendingData = new ByteBufferList();
  DataCallback mPendingRead;
  int mPendingReadLength;

  static
  {
    if (!DataEmitterReader.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  private boolean handlePendingData(DataEmitter paramDataEmitter)
  {
    if (this.mPendingReadLength > this.mPendingData.remaining())
      return false;
    DataCallback localDataCallback = this.mPendingRead;
    this.mPendingRead = null;
    localDataCallback.onDataAvailable(paramDataEmitter, this.mPendingData);
    return true;
  }

  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    assert (this.mPendingRead != null);
    do
    {
      int i = Math.min(paramByteBufferList.remaining(), this.mPendingReadLength - this.mPendingData.remaining());
      paramByteBufferList.get(this.mPendingData, i);
    }
    while ((handlePendingData(paramDataEmitter)) && (this.mPendingRead != null));
  }

  public void read(int paramInt, DataCallback paramDataCallback)
  {
    assert (this.mPendingRead == null);
    this.mPendingReadLength = paramInt;
    this.mPendingRead = paramDataCallback;
    this.mPendingData = new ByteBufferList();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.DataEmitterReader
 * JD-Core Version:    0.6.2
 */