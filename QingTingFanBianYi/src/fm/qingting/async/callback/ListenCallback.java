package fm.qingting.async.callback;

import fm.qingting.async.AsyncServerSocket;
import fm.qingting.async.AsyncSocket;

public abstract interface ListenCallback extends CompletedCallback
{
  public abstract void onAccepted(AsyncSocket paramAsyncSocket);

  public abstract void onListening(AsyncServerSocket paramAsyncServerSocket);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.callback.ListenCallback
 * JD-Core Version:    0.6.2
 */