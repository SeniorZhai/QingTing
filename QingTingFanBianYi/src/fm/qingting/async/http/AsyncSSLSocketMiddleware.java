package fm.qingting.async.http;

import fm.qingting.async.AsyncSSLSocketWrapper;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.callback.ConnectCallback;
import java.net.URI;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class AsyncSSLSocketMiddleware extends AsyncSocketMiddleware
{
  SSLContext sslContext;
  TrustManager[] trustManagers;

  public AsyncSSLSocketMiddleware(AsyncHttpClient paramAsyncHttpClient)
  {
    super(paramAsyncHttpClient, "https", 443);
  }

  public void setSSLContext(SSLContext paramSSLContext)
  {
    this.sslContext = paramSSLContext;
  }

  public void setTrustManagers(TrustManager[] paramArrayOfTrustManager)
  {
    this.trustManagers = paramArrayOfTrustManager;
  }

  protected ConnectCallback wrapCallback(final ConnectCallback paramConnectCallback, final URI paramURI, final int paramInt)
  {
    return new ConnectCallback()
    {
      public void onConnectCompleted(Exception paramAnonymousException, AsyncSocket paramAnonymousAsyncSocket)
      {
        if (paramAnonymousException == null)
        {
          paramConnectCallback.onConnectCompleted(paramAnonymousException, new AsyncSSLSocketWrapper(paramAnonymousAsyncSocket, paramURI.getHost(), paramInt, AsyncSSLSocketMiddleware.this.sslContext, AsyncSSLSocketMiddleware.this.trustManagers, true));
          return;
        }
        paramConnectCallback.onConnectCompleted(paramAnonymousException, paramAnonymousAsyncSocket);
      }
    };
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncSSLSocketMiddleware
 * JD-Core Version:    0.6.2
 */