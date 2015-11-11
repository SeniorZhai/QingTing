package fm.qingting.async.wrapper;

import fm.qingting.async.AsyncSocket;

public abstract interface AsyncSocketWrapper extends AsyncSocket, DataEmitterWrapper
{
  public abstract AsyncSocket getSocket();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.wrapper.AsyncSocketWrapper
 * JD-Core Version:    0.6.2
 */