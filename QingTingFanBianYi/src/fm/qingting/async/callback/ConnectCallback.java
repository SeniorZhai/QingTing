package fm.qingting.async.callback;

import fm.qingting.async.AsyncSocket;

public abstract interface ConnectCallback
{
  public abstract void onConnectCompleted(Exception paramException, AsyncSocket paramAsyncSocket);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.callback.ConnectCallback
 * JD-Core Version:    0.6.2
 */