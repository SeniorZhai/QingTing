package fm.qingting.async.http;

import fm.qingting.async.future.Cancellable;

public class SimpleMiddleware
  implements AsyncHttpClientMiddleware
{
  public Cancellable getSocket(AsyncHttpClientMiddleware.GetSocketData paramGetSocketData)
  {
    return null;
  }

  public void onBodyDecoder(AsyncHttpClientMiddleware.OnBodyData paramOnBodyData)
  {
  }

  public void onHeadersReceived(AsyncHttpClientMiddleware.OnHeadersReceivedData paramOnHeadersReceivedData)
  {
  }

  public void onRequestComplete(AsyncHttpClientMiddleware.OnRequestCompleteData paramOnRequestCompleteData)
  {
  }

  public void onSocket(AsyncHttpClientMiddleware.OnSocketData paramOnSocketData)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.SimpleMiddleware
 * JD-Core Version:    0.6.2
 */