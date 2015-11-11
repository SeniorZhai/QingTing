package fm.qingting.async;

import fm.qingting.async.callback.DataCallback;

public class NullDataCallback
  implements DataCallback
{
  public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
  {
    paramByteBufferList.clear();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.NullDataCallback
 * JD-Core Version:    0.6.2
 */