package fm.qingting.async.http;

import android.os.Handler;
import fm.qingting.async.AsyncSSLException;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ConnectCallback;
import fm.qingting.async.future.Cancellable;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.FutureCallback;
import fm.qingting.async.future.SimpleFuture;
import fm.qingting.async.http.callback.HttpConnectCallback;
import fm.qingting.async.http.callback.RequestCallback;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import fm.qingting.async.parser.AsyncParser;
import fm.qingting.async.parser.ByteBufferListParser;
import fm.qingting.async.parser.JSONObjectParser;
import fm.qingting.async.parser.StringParser;
import fm.qingting.async.stream.OutputStreamDataCallback;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public class AsyncHttpClient
{
  private static final String LOGTAG = "AsyncHttp";
  private static AsyncHttpClient mDefaultInstance;
  ArrayList<AsyncHttpClientMiddleware> mMiddleware = new ArrayList();
  AsyncServer mServer;
  AsyncSocketMiddleware socketMiddleware;
  AsyncSSLSocketMiddleware sslSocketMiddleware;

  static
  {
    if (!AsyncHttpClient.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AsyncHttpClient(AsyncServer paramAsyncServer)
  {
    this.mServer = paramAsyncServer;
    paramAsyncServer = new AsyncSocketMiddleware(this);
    this.socketMiddleware = paramAsyncServer;
    insertMiddleware(paramAsyncServer);
    paramAsyncServer = new AsyncSSLSocketMiddleware(this);
    this.sslSocketMiddleware = paramAsyncServer;
    insertMiddleware(paramAsyncServer);
  }

  private <T> SimpleFuture<T> execute(AsyncHttpRequest paramAsyncHttpRequest, final AsyncParser<T> paramAsyncParser, final RequestCallback<T> paramRequestCallback)
  {
    FutureAsyncHttpResponse localFutureAsyncHttpResponse = new FutureAsyncHttpResponse(null);
    final SimpleFuture localSimpleFuture = new SimpleFuture();
    execute(paramAsyncHttpRequest, 0, localFutureAsyncHttpResponse, new HttpConnectCallback()
    {
      public void onConnectCompleted(Exception paramAnonymousException, final AsyncHttpResponse paramAnonymousAsyncHttpResponse)
      {
        if (paramAnonymousException != null)
        {
          AsyncHttpClient.this.invoke(this.val$handler, paramRequestCallback, localSimpleFuture, paramAnonymousAsyncHttpResponse, paramAnonymousException, null);
          return;
        }
        AsyncHttpClient.this.invokeConnect(paramRequestCallback, paramAnonymousAsyncHttpResponse);
        paramAnonymousAsyncHttpResponse.getHeaders().getContentLength();
        paramAnonymousException = paramAsyncParser.parse(paramAnonymousAsyncHttpResponse).setCallback(new FutureCallback()
        {
          public void onCompleted(Exception paramAnonymous2Exception, T paramAnonymous2T)
          {
            AsyncHttpClient.this.invoke(AsyncHttpClient.6.this.val$handler, AsyncHttpClient.6.this.val$callback, AsyncHttpClient.6.this.val$ret, paramAnonymousAsyncHttpResponse, paramAnonymous2Exception, paramAnonymous2T);
          }
        });
        localSimpleFuture.setParent(paramAnonymousException);
      }
    });
    localSimpleFuture.setParent(localFutureAsyncHttpResponse);
    return localSimpleFuture;
  }

  private void execute(final AsyncHttpRequest paramAsyncHttpRequest, final int paramInt, final FutureAsyncHttpResponse paramFutureAsyncHttpResponse, final HttpConnectCallback paramHttpConnectCallback)
  {
    if (this.mServer.isAffinityThread())
    {
      executeAffinity(paramAsyncHttpRequest, paramInt, paramFutureAsyncHttpResponse, paramHttpConnectCallback);
      return;
    }
    this.mServer.post(new Runnable()
    {
      public void run()
      {
        AsyncHttpClient.this.executeAffinity(paramAsyncHttpRequest, paramInt, paramFutureAsyncHttpResponse, paramHttpConnectCallback);
      }
    });
  }

  private void executeAffinity(final AsyncHttpRequest paramAsyncHttpRequest, final int paramInt, final FutureAsyncHttpResponse paramFutureAsyncHttpResponse, final HttpConnectCallback paramHttpConnectCallback)
  {
    assert (this.mServer.isAffinityThread());
    if (paramInt > 5)
      reportConnectedCompleted(paramFutureAsyncHttpResponse, new Exception("too many redirects"), null, paramAsyncHttpRequest, paramHttpConnectCallback);
    do
    {
      return;
      final URI localURI = paramAsyncHttpRequest.getUri();
      final AsyncHttpClientMiddleware.OnRequestCompleteData localOnRequestCompleteData = new AsyncHttpClientMiddleware.OnRequestCompleteData();
      paramAsyncHttpRequest.executionTime = System.currentTimeMillis();
      localOnRequestCompleteData.request = paramAsyncHttpRequest;
      paramAsyncHttpRequest.logd("Executing request.");
      localOnRequestCompleteData.connectCallback = new ConnectCallback()
      {
        Object scheduled = null;

        public void onConnectCompleted(Exception paramAnonymousException, AsyncSocket paramAnonymousAsyncSocket)
        {
          if (paramFutureAsyncHttpResponse.isCancelled())
          {
            if (paramAnonymousAsyncSocket != null)
              paramAnonymousAsyncSocket.close();
            return;
          }
          localOnRequestCompleteData.socket = paramAnonymousAsyncSocket;
          Iterator localIterator = AsyncHttpClient.this.mMiddleware.iterator();
          while (localIterator.hasNext())
            ((AsyncHttpClientMiddleware)localIterator.next()).onSocket(localOnRequestCompleteData);
          paramFutureAsyncHttpResponse.socket = paramAnonymousAsyncSocket;
          if (paramAnonymousException != null)
          {
            AsyncHttpClient.this.reportConnectedCompleted(paramFutureAsyncHttpResponse, paramAnonymousException, null, paramAsyncHttpRequest, paramHttpConnectCallback);
            return;
          }
          new AsyncHttpResponseImpl(paramAsyncHttpRequest)
          {
            public AsyncSocket detachSocket()
            {
              AsyncHttpClient.2.this.val$request.logd("Detaching socket");
              AsyncSocket localAsyncSocket = getSocket();
              if (localAsyncSocket == null)
                return null;
              localAsyncSocket.setWriteableCallback(null);
              localAsyncSocket.setClosedCallback(null);
              localAsyncSocket.setEndCallback(null);
              localAsyncSocket.setDataCallback(null);
              setSocket(null);
              return localAsyncSocket;
            }

            protected void onHeadersReceived()
            {
              try
              {
                if (AsyncHttpClient.2.this.val$cancel.isCancelled())
                  return;
                if (AsyncHttpClient.2.this.scheduled != null)
                  AsyncHttpClient.this.mServer.removeAllCallbacks(AsyncHttpClient.2.this.scheduled);
                AsyncHttpClient.2.this.val$request.logv("Received headers: " + this.mHeaders.getHeaders().toHeaderString());
                AsyncHttpClient.2.this.val$data.headers = this.mHeaders;
                Iterator localIterator = AsyncHttpClient.this.mMiddleware.iterator();
                while (localIterator.hasNext())
                  ((AsyncHttpClientMiddleware)localIterator.next()).onHeadersReceived(AsyncHttpClient.2.this.val$data);
              }
              catch (Exception localException)
              {
                AsyncHttpClient.this.reportConnectedCompleted(AsyncHttpClient.2.this.val$cancel, localException, null, AsyncHttpClient.2.this.val$request, AsyncHttpClient.2.this.val$callback);
                return;
              }
              this.mHeaders = AsyncHttpClient.2.this.val$data.headers;
            }

            protected void report(Exception paramAnonymous2Exception)
            {
              if (AsyncHttpClient.2.this.val$cancel.isCancelled());
              while (true)
              {
                return;
                Object localObject;
                if ((paramAnonymous2Exception instanceof AsyncSSLException))
                {
                  AsyncHttpClient.2.this.val$request.loge("SSL Exception", paramAnonymous2Exception);
                  localObject = (AsyncSSLException)paramAnonymous2Exception;
                  AsyncHttpClient.2.this.val$request.onHandshakeException((AsyncSSLException)localObject);
                  if (((AsyncSSLException)localObject).getIgnore());
                }
                else
                {
                  localObject = getSocket();
                  if (localObject != null)
                  {
                    super.report(paramAnonymous2Exception);
                    if (((!((AsyncSocket)localObject).isOpen()) || (paramAnonymous2Exception != null)) && (getHeaders() == null) && (paramAnonymous2Exception != null))
                      AsyncHttpClient.this.reportConnectedCompleted(AsyncHttpClient.2.this.val$cancel, paramAnonymous2Exception, null, AsyncHttpClient.2.this.val$request, AsyncHttpClient.2.this.val$callback);
                    AsyncHttpClient.2.this.val$data.exception = paramAnonymous2Exception;
                    paramAnonymous2Exception = AsyncHttpClient.this.mMiddleware.iterator();
                    while (paramAnonymous2Exception.hasNext())
                      ((AsyncHttpClientMiddleware)paramAnonymous2Exception.next()).onRequestComplete(AsyncHttpClient.2.this.val$data);
                  }
                }
              }
            }

            public void setDataEmitter(DataEmitter paramAnonymous2DataEmitter)
            {
              AsyncHttpClient.2.this.val$data.bodyEmitter = paramAnonymous2DataEmitter;
              paramAnonymous2DataEmitter = AsyncHttpClient.this.mMiddleware.iterator();
              while (paramAnonymous2DataEmitter.hasNext())
                ((AsyncHttpClientMiddleware)paramAnonymous2DataEmitter.next()).onBodyDecoder(AsyncHttpClient.2.this.val$data);
              this.mHeaders = AsyncHttpClient.2.this.val$data.headers;
              super.setDataEmitter(AsyncHttpClient.2.this.val$data.bodyEmitter);
              RawHeaders localRawHeaders = this.mHeaders.getHeaders();
              if (((localRawHeaders.getResponseCode() == 301) || (localRawHeaders.getResponseCode() == 302)) && (AsyncHttpClient.2.this.val$request.getFollowRedirect()))
              {
                URI localURI = URI.create(localRawHeaders.get("Location"));
                if (localURI != null)
                {
                  paramAnonymous2DataEmitter = localURI;
                  if (localURI.getScheme() != null);
                }
                else
                {
                  paramAnonymous2DataEmitter = URI.create(AsyncHttpClient.2.this.val$uri.toString().substring(0, AsyncHttpClient.2.this.val$uri.toString().length() - AsyncHttpClient.2.this.val$uri.getPath().length()) + localRawHeaders.get("Location"));
                }
                paramAnonymous2DataEmitter = new AsyncHttpRequest(paramAnonymous2DataEmitter, AsyncHttpClient.2.this.val$request.getMethod());
                paramAnonymous2DataEmitter.executionTime = AsyncHttpClient.2.this.val$request.executionTime;
                paramAnonymous2DataEmitter.logLevel = AsyncHttpClient.2.this.val$request.logLevel;
                paramAnonymous2DataEmitter.LOGTAG = AsyncHttpClient.2.this.val$request.LOGTAG;
                AsyncHttpClient.2.this.val$request.logi("Redirecting");
                paramAnonymous2DataEmitter.logi("Redirected");
                AsyncHttpClient.this.execute(paramAnonymous2DataEmitter, AsyncHttpClient.2.this.val$redirectCount + 1, AsyncHttpClient.2.this.val$cancel, AsyncHttpClient.2.this.val$callback);
                setDataCallback(new NullDataCallback());
                return;
              }
              AsyncHttpClient.2.this.val$request.logv("Final (post cache response) headers: " + this.mHeaders.getHeaders().toHeaderString());
              AsyncHttpClient.this.reportConnectedCompleted(AsyncHttpClient.2.this.val$cancel, null, this, AsyncHttpClient.2.this.val$request, AsyncHttpClient.2.this.val$callback);
            }
          }
          .setSocket(paramAnonymousAsyncSocket);
        }
      };
      paramAsyncHttpRequest = this.mMiddleware.iterator();
      while (paramAsyncHttpRequest.hasNext())
      {
        paramHttpConnectCallback = ((AsyncHttpClientMiddleware)paramAsyncHttpRequest.next()).getSocket(localOnRequestCompleteData);
        if (paramHttpConnectCallback != null)
        {
          localOnRequestCompleteData.socketCancellable = paramHttpConnectCallback;
          paramFutureAsyncHttpResponse.setParent(paramHttpConnectCallback);
          return;
        }
      }
    }
    while ($assertionsDisabled);
    throw new AssertionError();
  }

  public static AsyncHttpClient getDefaultInstance()
  {
    if (mDefaultInstance == null)
      mDefaultInstance = new AsyncHttpClient(AsyncServer.getDefault());
    return mDefaultInstance;
  }

  private <T> void invoke(Handler paramHandler, final RequestCallback<T> paramRequestCallback, final SimpleFuture<T> paramSimpleFuture, final AsyncHttpResponse paramAsyncHttpResponse, final Exception paramException, final T paramT)
  {
    paramRequestCallback = new Runnable()
    {
      public void run()
      {
        AsyncHttpClient.this.invokeWithAffinity(paramRequestCallback, paramSimpleFuture, paramAsyncHttpResponse, paramException, paramT);
      }
    };
    if (paramHandler == null)
    {
      this.mServer.post(paramRequestCallback);
      return;
    }
    AsyncServer.post(paramHandler, paramRequestCallback);
  }

  private void invokeConnect(RequestCallback paramRequestCallback, AsyncHttpResponse paramAsyncHttpResponse)
  {
    if (paramRequestCallback != null)
      paramRequestCallback.onConnect(paramAsyncHttpResponse);
  }

  private void invokeProgress(RequestCallback paramRequestCallback, AsyncHttpResponse paramAsyncHttpResponse, int paramInt1, int paramInt2)
  {
    if (paramRequestCallback != null)
      paramRequestCallback.onProgress(paramAsyncHttpResponse, paramInt1, paramInt2);
  }

  private <T> void invokeWithAffinity(RequestCallback<T> paramRequestCallback, SimpleFuture<T> paramSimpleFuture, AsyncHttpResponse paramAsyncHttpResponse, Exception paramException, T paramT)
  {
    boolean bool;
    if (paramException != null)
    {
      bool = paramSimpleFuture.setComplete(paramException);
      if (bool)
        break label30;
    }
    label30: 
    while (paramRequestCallback == null)
    {
      return;
      bool = paramSimpleFuture.setComplete(paramT);
      break;
    }
    paramRequestCallback.onCompleted(paramException, paramAsyncHttpResponse, paramT);
  }

  private void reportConnectedCompleted(FutureAsyncHttpResponse paramFutureAsyncHttpResponse, Exception paramException, AsyncHttpResponseImpl paramAsyncHttpResponseImpl, AsyncHttpRequest paramAsyncHttpRequest, HttpConnectCallback paramHttpConnectCallback)
  {
    assert (paramHttpConnectCallback != null);
    if (paramException != null)
      paramAsyncHttpRequest.loge("Connection error", paramException);
    for (boolean bool = paramFutureAsyncHttpResponse.setComplete(paramException); bool; bool = paramFutureAsyncHttpResponse.setComplete(paramAsyncHttpResponseImpl))
    {
      paramHttpConnectCallback.onConnectCompleted(paramException, paramAsyncHttpResponseImpl);
      if (($assertionsDisabled) || (paramException != null) || (paramAsyncHttpResponseImpl.getSocket() == null) || (paramAsyncHttpResponseImpl.getDataCallback() != null))
        return;
      throw new AssertionError();
      paramAsyncHttpRequest.logd("Connection successful");
    }
    if (paramAsyncHttpResponseImpl != null)
    {
      paramAsyncHttpResponseImpl.setDataCallback(new NullDataCallback());
      paramAsyncHttpResponseImpl.close();
    }
  }

  @Deprecated
  public Future<JSONObject> execute(AsyncHttpRequest paramAsyncHttpRequest, JSONObjectCallback paramJSONObjectCallback)
  {
    return executeJSONObject(paramAsyncHttpRequest, paramJSONObjectCallback);
  }

  @Deprecated
  public Future<String> execute(AsyncHttpRequest paramAsyncHttpRequest, StringCallback paramStringCallback)
  {
    return executeString(paramAsyncHttpRequest, paramStringCallback);
  }

  public Future<AsyncHttpResponse> execute(AsyncHttpRequest paramAsyncHttpRequest, HttpConnectCallback paramHttpConnectCallback)
  {
    FutureAsyncHttpResponse localFutureAsyncHttpResponse = new FutureAsyncHttpResponse(null);
    execute(paramAsyncHttpRequest, 0, localFutureAsyncHttpResponse, paramHttpConnectCallback);
    return localFutureAsyncHttpResponse;
  }

  @Deprecated
  public Future<File> execute(AsyncHttpRequest paramAsyncHttpRequest, String paramString, FileCallback paramFileCallback)
  {
    return executeFile(paramAsyncHttpRequest, paramString, paramFileCallback);
  }

  public Future<AsyncHttpResponse> execute(String paramString, HttpConnectCallback paramHttpConnectCallback)
  {
    return execute(new AsyncHttpGet(URI.create(paramString)), paramHttpConnectCallback);
  }

  public Future<AsyncHttpResponse> execute(URI paramURI, HttpConnectCallback paramHttpConnectCallback)
  {
    return execute(new AsyncHttpGet(paramURI), paramHttpConnectCallback);
  }

  public Future<ByteBufferList> executeByteBufferList(AsyncHttpRequest paramAsyncHttpRequest, DownloadCallback paramDownloadCallback)
  {
    return execute(paramAsyncHttpRequest, new ByteBufferListParser(), paramDownloadCallback);
  }

  public Future<File> executeFile(AsyncHttpRequest paramAsyncHttpRequest, String paramString)
  {
    return executeFile(paramAsyncHttpRequest, paramString, null);
  }

  public Future<File> executeFile(AsyncHttpRequest paramAsyncHttpRequest, final String paramString, final FileCallback paramFileCallback)
  {
    final Handler localHandler = paramAsyncHttpRequest.getHandler();
    paramString = new File(paramString);
    paramString.getParentFile().mkdirs();
    try
    {
      final BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(paramString), 8192);
      final FutureAsyncHttpResponse localFutureAsyncHttpResponse = new FutureAsyncHttpResponse(null);
      final SimpleFuture local4 = new SimpleFuture()
      {
        public void cancelCleanup()
        {
          try
          {
            ((AsyncHttpResponse)localFutureAsyncHttpResponse.get()).setDataCallback(new NullDataCallback());
            ((AsyncHttpResponse)localFutureAsyncHttpResponse.get()).close();
            try
            {
              label37: localBufferedOutputStream.close();
              label44: paramString.delete();
              return;
            }
            catch (Exception localException1)
            {
              break label44;
            }
          }
          catch (Exception localException2)
          {
            break label37;
          }
        }
      };
      local4.setParent(localFutureAsyncHttpResponse);
      execute(paramAsyncHttpRequest, 0, localFutureAsyncHttpResponse, new HttpConnectCallback()
      {
        int mDownloaded = 0;

        public void onConnectCompleted(Exception paramAnonymousException, final AsyncHttpResponse paramAnonymousAsyncHttpResponse)
        {
          if (paramAnonymousException != null);
          try
          {
            localBufferedOutputStream.close();
            label11: paramString.delete();
            AsyncHttpClient.this.invoke(localHandler, paramFileCallback, local4, paramAnonymousAsyncHttpResponse, paramAnonymousException, null);
            return;
            AsyncHttpClient.this.invokeConnect(paramFileCallback, paramAnonymousAsyncHttpResponse);
            final int i = paramAnonymousAsyncHttpResponse.getHeaders().getContentLength();
            paramAnonymousAsyncHttpResponse.setDataCallback(new OutputStreamDataCallback(localBufferedOutputStream)
            {
              public void onDataAvailable(DataEmitter paramAnonymous2DataEmitter, ByteBufferList paramAnonymous2ByteBufferList)
              {
                AsyncHttpClient.5 local5 = AsyncHttpClient.5.this;
                local5.mDownloaded += paramAnonymous2ByteBufferList.remaining();
                super.onDataAvailable(paramAnonymous2DataEmitter, paramAnonymous2ByteBufferList);
                AsyncHttpClient.this.invokeProgress(AsyncHttpClient.5.this.val$callback, paramAnonymousAsyncHttpResponse, AsyncHttpClient.5.this.mDownloaded, i);
              }
            });
            paramAnonymousAsyncHttpResponse.setEndCallback(new CompletedCallback()
            {
              public void onCompleted(Exception paramAnonymous2Exception)
              {
                try
                {
                  AsyncHttpClient.5.this.val$fout.close();
                  label10: if (paramAnonymous2Exception != null)
                  {
                    AsyncHttpClient.5.this.val$file.delete();
                    AsyncHttpClient.this.invoke(AsyncHttpClient.5.this.val$handler, AsyncHttpClient.5.this.val$callback, AsyncHttpClient.5.this.val$ret, paramAnonymousAsyncHttpResponse, paramAnonymous2Exception, null);
                    return;
                  }
                }
                catch (IOException paramAnonymous2Exception)
                {
                  break label10;
                  AsyncHttpClient.this.invoke(AsyncHttpClient.5.this.val$handler, AsyncHttpClient.5.this.val$callback, AsyncHttpClient.5.this.val$ret, paramAnonymousAsyncHttpResponse, null, AsyncHttpClient.5.this.val$file);
                }
              }
            });
            return;
          }
          catch (IOException localIOException)
          {
            break label11;
          }
        }
      });
      return local4;
    }
    catch (FileNotFoundException paramAsyncHttpRequest)
    {
      paramString = new SimpleFuture();
      paramString.setComplete(paramAsyncHttpRequest);
    }
    return paramString;
  }

  public Future<JSONObject> executeJSONObject(AsyncHttpRequest paramAsyncHttpRequest)
  {
    return executeJSONObject(paramAsyncHttpRequest, null);
  }

  public Future<JSONObject> executeJSONObject(AsyncHttpRequest paramAsyncHttpRequest, JSONObjectCallback paramJSONObjectCallback)
  {
    return execute(paramAsyncHttpRequest, new JSONObjectParser(), paramJSONObjectCallback);
  }

  public Future<String> executeString(AsyncHttpRequest paramAsyncHttpRequest)
  {
    return executeString(paramAsyncHttpRequest, null);
  }

  public Future<String> executeString(AsyncHttpRequest paramAsyncHttpRequest, StringCallback paramStringCallback)
  {
    return execute(paramAsyncHttpRequest, new StringParser(), paramStringCallback);
  }

  @Deprecated
  public Future<ByteBufferList> get(String paramString, DownloadCallback paramDownloadCallback)
  {
    return getByteBufferList(paramString, paramDownloadCallback);
  }

  @Deprecated
  public Future<JSONObject> get(String paramString, JSONObjectCallback paramJSONObjectCallback)
  {
    return executeJSONObject(new AsyncHttpGet(paramString), paramJSONObjectCallback);
  }

  @Deprecated
  public Future<String> get(String paramString, StringCallback paramStringCallback)
  {
    return executeString(new AsyncHttpGet(paramString), paramStringCallback);
  }

  @Deprecated
  public Future<File> get(String paramString1, String paramString2, FileCallback paramFileCallback)
  {
    return executeFile(new AsyncHttpGet(paramString1), paramString2, paramFileCallback);
  }

  public Future<ByteBufferList> getByteBufferList(String paramString)
  {
    return getByteBufferList(paramString, null);
  }

  public Future<ByteBufferList> getByteBufferList(String paramString, DownloadCallback paramDownloadCallback)
  {
    return executeByteBufferList(new AsyncHttpGet(paramString), paramDownloadCallback);
  }

  public Future<File> getFile(String paramString1, String paramString2)
  {
    return getFile(paramString1, paramString2, null);
  }

  public Future<File> getFile(String paramString1, String paramString2, FileCallback paramFileCallback)
  {
    return executeFile(new AsyncHttpGet(paramString1), paramString2, paramFileCallback);
  }

  public Future<JSONObject> getJSONObject(String paramString)
  {
    return getJSONObject(paramString, null);
  }

  public Future<JSONObject> getJSONObject(String paramString, JSONObjectCallback paramJSONObjectCallback)
  {
    return executeJSONObject(new AsyncHttpGet(paramString), paramJSONObjectCallback);
  }

  public ArrayList<AsyncHttpClientMiddleware> getMiddleware()
  {
    return this.mMiddleware;
  }

  public AsyncSSLSocketMiddleware getSSLSocketMiddleware()
  {
    return this.sslSocketMiddleware;
  }

  public AsyncServer getServer()
  {
    return this.mServer;
  }

  public AsyncSocketMiddleware getSocketMiddleware()
  {
    return this.socketMiddleware;
  }

  public Future<String> getString(String paramString)
  {
    return executeString(new AsyncHttpGet(paramString), null);
  }

  public Future<String> getString(String paramString, StringCallback paramStringCallback)
  {
    return executeString(new AsyncHttpGet(paramString), paramStringCallback);
  }

  public void insertMiddleware(AsyncHttpClientMiddleware paramAsyncHttpClientMiddleware)
  {
    this.mMiddleware.add(0, paramAsyncHttpClientMiddleware);
  }

  public Future<WebSocket> websocket(final AsyncHttpRequest paramAsyncHttpRequest, final String paramString, final WebSocketConnectCallback paramWebSocketConnectCallback)
  {
    WebSocketImpl.addWebSocketUpgradeHeaders(paramAsyncHttpRequest, paramString);
    paramString = new SimpleFuture();
    paramString.setParent(execute(paramAsyncHttpRequest, new HttpConnectCallback()
    {
      public void onConnectCompleted(Exception paramAnonymousException, AsyncHttpResponse paramAnonymousAsyncHttpResponse)
      {
        if (paramAnonymousException != null)
          if ((paramString.setComplete(paramAnonymousException)) && (paramWebSocketConnectCallback != null))
            paramWebSocketConnectCallback.onCompleted(paramAnonymousException, null);
        do
        {
          return;
          while (paramWebSocketConnectCallback == null)
            do
            {
              paramAnonymousAsyncHttpResponse = WebSocketImpl.finishHandshake(paramAsyncHttpRequest.getHeaders().getHeaders(), paramAnonymousAsyncHttpResponse);
              if (paramAnonymousAsyncHttpResponse != null)
                break;
            }
            while (!paramString.setComplete(new Exception("Unable to complete websocket handshake")));
          paramWebSocketConnectCallback.onCompleted(paramAnonymousException, paramAnonymousAsyncHttpResponse);
          return;
        }
        while (paramString.setComplete(paramAnonymousAsyncHttpResponse));
      }
    }));
    return paramString;
  }

  public Future<WebSocket> websocket(String paramString1, String paramString2, WebSocketConnectCallback paramWebSocketConnectCallback)
  {
    assert (paramWebSocketConnectCallback != null);
    return websocket(new AsyncHttpGet(paramString1), paramString2, paramWebSocketConnectCallback);
  }

  public static abstract class DownloadCallback extends AsyncHttpClient.RequestCallbackBase<ByteBufferList>
  {
  }

  public static abstract class FileCallback extends AsyncHttpClient.RequestCallbackBase<File>
  {
  }

  private class FutureAsyncHttpResponse extends SimpleFuture<AsyncHttpResponse>
  {
    public Object scheduled;
    public AsyncSocket socket;

    private FutureAsyncHttpResponse()
    {
    }

    public boolean cancel()
    {
      if (!super.cancel())
        return false;
      if (this.socket != null)
        this.socket.close();
      if (this.scheduled != null)
        AsyncHttpClient.this.mServer.removeAllCallbacks(this.scheduled);
      return true;
    }
  }

  public static abstract class JSONObjectCallback extends AsyncHttpClient.RequestCallbackBase<JSONObject>
  {
  }

  public static abstract class RequestCallbackBase<T>
    implements RequestCallback<T>
  {
    public void onConnect(AsyncHttpResponse paramAsyncHttpResponse)
    {
    }

    public void onProgress(AsyncHttpResponse paramAsyncHttpResponse, int paramInt1, int paramInt2)
    {
    }
  }

  public static abstract class StringCallback extends AsyncHttpClient.RequestCallbackBase<String>
  {
  }

  public static abstract interface WebSocketConnectCallback
  {
    public abstract void onCompleted(Exception paramException, WebSocket paramWebSocket);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpClient
 * JD-Core Version:    0.6.2
 */