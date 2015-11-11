package fm.qingting.async.http;

import android.os.Bundle;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ConnectCallback;
import fm.qingting.async.callback.ContinuationCallback;
import fm.qingting.async.future.Cancellable;
import fm.qingting.async.future.Continuation;
import fm.qingting.async.future.SimpleCancellable;
import fm.qingting.async.future.TransformFuture;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

public class AsyncSocketMiddleware extends SimpleMiddleware
{
  boolean connectAllAddresses;
  AsyncHttpClient mClient;
  private Hashtable<String, HashSet<AsyncSocket>> mSockets = new Hashtable();
  int port;
  InetSocketAddress proxyAddress;
  String proxyHost;
  int proxyPort;
  String scheme;

  public AsyncSocketMiddleware(AsyncHttpClient paramAsyncHttpClient)
  {
    this(paramAsyncHttpClient, "http", 80);
  }

  public AsyncSocketMiddleware(AsyncHttpClient paramAsyncHttpClient, String paramString, int paramInt)
  {
    this.mClient = paramAsyncHttpClient;
    this.scheme = paramString;
    this.port = paramInt;
  }

  private void idleSocket(final AsyncSocket paramAsyncSocket)
  {
    paramAsyncSocket.setEndCallback(null);
    paramAsyncSocket.setWriteableCallback(null);
    paramAsyncSocket.setDataCallback(new NullDataCallback()
    {
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        super.onDataAvailable(paramAnonymousDataEmitter, paramAnonymousByteBufferList);
        paramAnonymousByteBufferList.clear();
        paramAsyncSocket.close();
      }
    });
  }

  private void recycleSocket(final AsyncSocket paramAsyncSocket, final AsyncHttpRequest paramAsyncHttpRequest)
  {
    if (paramAsyncSocket == null)
      return;
    Object localObject = paramAsyncHttpRequest.getUri();
    String str = computeLookup((URI)localObject, getSchemePort((URI)localObject), paramAsyncHttpRequest);
    try
    {
      localObject = (HashSet)this.mSockets.get(str);
      paramAsyncHttpRequest = (AsyncHttpRequest)localObject;
      if (localObject == null)
      {
        paramAsyncHttpRequest = new HashSet();
        this.mSockets.put(str, paramAsyncHttpRequest);
      }
      paramAsyncHttpRequest.add(paramAsyncSocket);
      paramAsyncSocket.setClosedCallback(new CompletedCallback()
      {
        public void onCompleted(Exception paramAnonymousException)
        {
          try
          {
            paramAsyncHttpRequest.remove(paramAsyncSocket);
            paramAsyncSocket.setClosedCallback(null);
            return;
          }
          finally
          {
          }
          throw paramAnonymousException;
        }
      });
      return;
    }
    finally
    {
    }
    throw paramAsyncSocket;
  }

  String computeLookup(URI paramURI, int paramInt, AsyncHttpRequest paramAsyncHttpRequest)
  {
    if (this.proxyHost != null);
    for (String str = this.proxyHost + ":" + this.proxyPort; ; str = "")
    {
      if (paramAsyncHttpRequest.proxyHost != null)
        str = paramAsyncHttpRequest.getProxyHost() + ":" + paramAsyncHttpRequest.proxyPort;
      return paramURI.getScheme() + "//" + paramURI.getHost() + ":" + paramInt + "?proxy=" + str;
    }
  }

  public void disableProxy()
  {
    this.proxyPort = -1;
    this.proxyHost = null;
    this.proxyAddress = null;
  }

  public void enableProxy(String paramString, int paramInt)
  {
    this.proxyHost = paramString;
    this.proxyPort = paramInt;
    this.proxyAddress = null;
  }

  public boolean getConnectAllAddresses()
  {
    return this.connectAllAddresses;
  }

  public int getConnectionPoolCount()
  {
    try
    {
      Iterator localIterator = this.mSockets.values().iterator();
      for (int i = 0; localIterator.hasNext(); i = ((HashSet)localIterator.next()).size() + i);
      return i;
    }
    finally
    {
    }
  }

  public int getSchemePort(URI paramURI)
  {
    if (!paramURI.getScheme().equals(this.scheme))
      return -1;
    if (paramURI.getPort() == -1)
      return this.port;
    return paramURI.getPort();
  }

  public Cancellable getSocket(final AsyncHttpClientMiddleware.GetSocketData paramGetSocketData)
  {
    final URI localURI = paramGetSocketData.request.getUri();
    final int j = getSchemePort(paramGetSocketData.request.getUri());
    if (j == -1)
      return null;
    Object localObject = computeLookup(localURI, j, paramGetSocketData.request);
    paramGetSocketData.state.putBoolean(getClass().getCanonicalName() + ".owned", true);
    try
    {
      localObject = (HashSet)this.mSockets.get(localObject);
      if (localObject != null)
      {
        Iterator localIterator = ((HashSet)localObject).iterator();
        while (localIterator.hasNext())
        {
          final AsyncSocket localAsyncSocket = (AsyncSocket)localIterator.next();
          if (localAsyncSocket.isOpen())
          {
            ((HashSet)localObject).remove(localAsyncSocket);
            localAsyncSocket.setClosedCallback(null);
            this.mClient.getServer().post(new Runnable()
            {
              public void run()
              {
                paramGetSocketData.request.logd("Reusing keep-alive socket");
                paramGetSocketData.connectCallback.onConnectCompleted(null, localAsyncSocket);
              }
            });
            paramGetSocketData = new SimpleCancellable();
            return paramGetSocketData;
          }
        }
      }
    }
    finally
    {
    }
    if ((!this.connectAllAddresses) || (this.proxyHost != null) || (paramGetSocketData.request.getProxyHost() != null))
    {
      paramGetSocketData.request.logd("Connecting socket");
      int i;
      if (paramGetSocketData.request.getProxyHost() != null)
      {
        localObject = paramGetSocketData.request.getProxyHost();
        i = paramGetSocketData.request.getProxyPort();
        paramGetSocketData.request.getHeaders().getHeaders().setStatusLine(paramGetSocketData.request.getProxyRequestLine().toString());
      }
      while (true)
      {
        return this.mClient.getServer().connectSocket((String)localObject, i, wrapCallback(paramGetSocketData.connectCallback, localURI, j));
        if (this.proxyHost != null)
        {
          localObject = this.proxyHost;
          i = this.proxyPort;
          paramGetSocketData.request.getHeaders().getHeaders().setStatusLine(paramGetSocketData.request.getProxyRequestLine().toString());
        }
        else
        {
          localObject = localURI.getHost();
          i = j;
        }
      }
    }
    paramGetSocketData.request.logv("Resolving domain and connecting to all available addresses");
    return new TransformFuture()
    {
      Exception lastException;

      protected void transform(InetAddress[] paramAnonymousArrayOfInetAddress)
        throws Exception
      {
        Continuation localContinuation = new Continuation(new CompletedCallback()
        {
          public void onCompleted(Exception paramAnonymous2Exception)
          {
            if (AsyncSocketMiddleware.2.this.lastException == null)
              AsyncSocketMiddleware.2.this.lastException = new Exception("Unable to connect to remote address");
            AsyncSocketMiddleware.2.this.setComplete(AsyncSocketMiddleware.2.this.lastException);
          }
        });
        int j = paramAnonymousArrayOfInetAddress.length;
        int i = 0;
        while (i < j)
        {
          localContinuation.add(new ContinuationCallback()
          {
            public void onContinue(Continuation paramAnonymous2Continuation, final CompletedCallback paramAnonymous2CompletedCallback)
              throws Exception
            {
              AsyncSocketMiddleware.this.mClient.getServer().connectSocket(new InetSocketAddress(this.val$address, AsyncSocketMiddleware.2.this.val$port), AsyncSocketMiddleware.this.wrapCallback(new ConnectCallback()
              {
                static
                {
                  if (!AsyncSocketMiddleware.class.desiredAssertionStatus());
                  for (boolean bool = true; ; bool = false)
                  {
                    $assertionsDisabled = bool;
                    return;
                  }
                }

                public void onConnectCompleted(Exception paramAnonymous3Exception, AsyncSocket paramAnonymous3AsyncSocket)
                {
                  assert (!AsyncSocketMiddleware.2.this.isDone());
                  if (paramAnonymous3Exception != null)
                  {
                    AsyncSocketMiddleware.2.this.lastException = paramAnonymous3Exception;
                    paramAnonymous2CompletedCallback.onCompleted(null);
                  }
                  do
                  {
                    return;
                    if ((AsyncSocketMiddleware.2.this.isDone()) || (AsyncSocketMiddleware.2.this.isCancelled()))
                    {
                      AsyncSocketMiddleware.2.this.val$data.request.logd("Recycling extra socket leftover from cancelled operation");
                      AsyncSocketMiddleware.this.idleSocket(paramAnonymous3AsyncSocket);
                      AsyncSocketMiddleware.this.recycleSocket(paramAnonymous3AsyncSocket, AsyncSocketMiddleware.2.this.val$data.request);
                      return;
                    }
                  }
                  while (!AsyncSocketMiddleware.2.this.setComplete(null, paramAnonymous3AsyncSocket));
                  AsyncSocketMiddleware.2.this.val$data.connectCallback.onConnectCompleted(paramAnonymous3Exception, paramAnonymous3AsyncSocket);
                }
              }
              , AsyncSocketMiddleware.2.this.val$uri, AsyncSocketMiddleware.2.this.val$port));
            }
          });
          i += 1;
        }
        localContinuation.start();
      }
    }
    .from(this.mClient.getServer().getAllByName(localURI.getHost()));
  }

  public void onRequestComplete(AsyncHttpClientMiddleware.OnRequestCompleteData paramOnRequestCompleteData)
  {
    if (!paramOnRequestCompleteData.state.getBoolean(getClass().getCanonicalName() + ".owned", false))
      return;
    idleSocket(paramOnRequestCompleteData.socket);
    if ((paramOnRequestCompleteData.exception != null) || (!paramOnRequestCompleteData.socket.isOpen()))
    {
      paramOnRequestCompleteData.socket.close();
      return;
    }
    String str = paramOnRequestCompleteData.headers.getConnection();
    if ((str == null) || (!"keep-alive".toLowerCase().equals(str.toLowerCase())))
    {
      paramOnRequestCompleteData.socket.close();
      return;
    }
    paramOnRequestCompleteData.request.logd("Recycling keep-alive socket");
    recycleSocket(paramOnRequestCompleteData.socket, paramOnRequestCompleteData.request);
  }

  public void setConnectAllAddresses(boolean paramBoolean)
  {
    this.connectAllAddresses = paramBoolean;
  }

  protected ConnectCallback wrapCallback(ConnectCallback paramConnectCallback, URI paramURI, int paramInt)
  {
    return paramConnectCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncSocketMiddleware
 * JD-Core Version:    0.6.2
 */