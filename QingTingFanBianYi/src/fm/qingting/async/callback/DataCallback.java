package fm.qingting.async.callback;

import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;

public abstract interface DataCallback
{
  public abstract void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.callback.DataCallback
 * JD-Core Version:    0.6.2
 */