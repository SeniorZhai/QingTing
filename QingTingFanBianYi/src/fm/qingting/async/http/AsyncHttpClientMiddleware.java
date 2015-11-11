package fm.qingting.async.http;

import android.os.Bundle;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.callback.ConnectCallback;
import fm.qingting.async.future.Cancellable;
import fm.qingting.async.http.libcore.ResponseHeaders;

public abstract interface AsyncHttpClientMiddleware
{
  public abstract Cancellable getSocket(GetSocketData paramGetSocketData);

  public abstract void onBodyDecoder(OnBodyData paramOnBodyData);

  public abstract void onHeadersReceived(OnHeadersReceivedData paramOnHeadersReceivedData);

  public abstract void onRequestComplete(OnRequestCompleteData paramOnRequestCompleteData);

  public abstract void onSocket(OnSocketData paramOnSocketData);

  public static class GetSocketData
  {
    public ConnectCallback connectCallback;
    public AsyncHttpRequest request;
    public Cancellable socketCancellable;
    public Bundle state = new Bundle();
  }

  public static class OnBodyData extends AsyncHttpClientMiddleware.OnHeadersReceivedData
  {
    public DataEmitter bodyEmitter;
  }

  public static class OnHeadersReceivedData extends AsyncHttpClientMiddleware.OnSocketData
  {
    public ResponseHeaders headers;
  }

  public static class OnRequestCompleteData extends AsyncHttpClientMiddleware.OnBodyData
  {
    public Exception exception;
  }

  public static class OnSocketData extends AsyncHttpClientMiddleware.GetSocketData
  {
    public AsyncSocket socket;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpClientMiddleware
 * JD-Core Version:    0.6.2
 */