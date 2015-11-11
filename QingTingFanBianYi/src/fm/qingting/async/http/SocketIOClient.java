package fm.qingting.async.http;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.future.Future;
import fm.qingting.async.future.SimpleFuture;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

public class SocketIOClient
{
  CompletedCallback closedCallback;
  boolean connected;
  boolean disconnected;
  EventCallback eventCallback;
  Handler handler;
  int heartbeat;
  AsyncHttpClient httpClient;
  JSONCallback jsonCallback;
  String sessionUrl;
  StringCallback stringCallback;
  WebSocket webSocket;

  private SocketIOClient(Handler paramHandler, int paramInt, String paramString, AsyncHttpClient paramAsyncHttpClient)
  {
    this.handler = paramHandler;
    this.heartbeat = paramInt;
    this.sessionUrl = paramString;
    this.httpClient = paramAsyncHttpClient;
  }

  private void attach(final SocketIOConnectCallback paramSocketIOConnectCallback, final FutureImpl paramFutureImpl)
  {
    this.webSocket.setDataCallback(new NullDataCallback());
    this.webSocket.setClosedCallback(new CompletedCallback()
    {
      public void onCompleted(final Exception paramAnonymousException)
      {
        final boolean bool = SocketIOClient.this.disconnected;
        SocketIOClient.this.disconnected = true;
        SocketIOClient.this.webSocket = null;
        paramAnonymousException = new Runnable()
        {
          public void run()
          {
            if (!SocketIOClient.this.connected)
            {
              localObject = SocketIOClient.6.this.val$callback;
              if (paramAnonymousException == null)
              {
                localException = new Exception("connection failed");
                ((SocketIOClient.SocketIOConnectCallback)localObject).onConnectCompleted(localException, null);
              }
            }
            while ((bool) || (SocketIOClient.this.closedCallback == null))
              while (true)
              {
                return;
                localException = paramAnonymousException;
              }
            Object localObject = SocketIOClient.this.closedCallback;
            if (paramAnonymousException == null);
            for (Exception localException = new Exception("connection failed"); ; localException = paramAnonymousException)
            {
              ((CompletedCallback)localObject).onCompleted(localException);
              return;
            }
          }
        };
        if (SocketIOClient.this.handler != null)
        {
          AsyncServer.post(SocketIOClient.this.handler, paramAnonymousException);
          return;
        }
        paramAnonymousException.run();
      }
    });
    this.webSocket.setStringCallback(new WebSocket.StringCallback()
    {
      public void onStringAvailable(String paramAnonymousString)
      {
        while (true)
          try
          {
            localObject1 = paramAnonymousString.split(":", 4);
            switch (Integer.parseInt(localObject1[0]))
            {
            case 6:
              throw new Exception("unknown code");
            case 8:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            }
          }
          catch (Exception paramAnonymousString)
          {
            Object localObject1;
            SocketIOClient.this.webSocket.close();
            if (!SocketIOClient.this.connected)
            {
              SocketIOClient.reportError(paramFutureImpl, SocketIOClient.this.handler, paramSocketIOConnectCallback, paramAnonymousString);
              return;
              if (!SocketIOClient.this.connected)
                throw new Exception("received disconnect before client connect");
              SocketIOClient.this.disconnected = true;
              SocketIOClient.this.webSocket.close();
              if (SocketIOClient.this.closedCallback == null)
                continue;
              if (SocketIOClient.this.handler != null)
              {
                AsyncServer.post(SocketIOClient.this.handler, new Runnable()
                {
                  public void run()
                  {
                    SocketIOClient.this.closedCallback.onCompleted(null);
                  }
                });
                return;
              }
              SocketIOClient.this.closedCallback.onCompleted(null);
              return;
              if (SocketIOClient.this.connected)
                throw new Exception("received duplicate connect event");
              if (!paramFutureImpl.setComplete(SocketIOClient.this))
                throw new Exception("request canceled");
              SocketIOClient.this.connected = true;
              SocketIOClient.this.setupHeartbeat();
              paramSocketIOConnectCallback.onConnectCompleted(null, SocketIOClient.this);
              return;
              SocketIOClient.this.webSocket.send("2::");
              return;
              if (!SocketIOClient.this.connected)
                throw new Exception("received message before client connect");
              paramAnonymousString = localObject1[1];
              localObject1 = localObject1[3];
              if (!"".equals(paramAnonymousString))
                SocketIOClient.this.webSocket.send(String.format("6:::%s", new Object[] { paramAnonymousString }));
              if (SocketIOClient.this.stringCallback == null)
                continue;
              if (SocketIOClient.this.handler != null)
              {
                AsyncServer.post(SocketIOClient.this.handler, new Runnable()
                {
                  public void run()
                  {
                    SocketIOClient.this.stringCallback.onString(this.val$dataString);
                  }
                });
                return;
              }
              SocketIOClient.this.stringCallback.onString((String)localObject1);
              return;
              if (!SocketIOClient.this.connected)
                throw new Exception("received message before client connect");
              paramAnonymousString = localObject1[1];
              localObject1 = new JSONObject(localObject1[3]);
              if (!"".equals(paramAnonymousString))
                SocketIOClient.this.webSocket.send(String.format("6:::%s", new Object[] { paramAnonymousString }));
              if (SocketIOClient.this.jsonCallback == null)
                continue;
              if (SocketIOClient.this.handler != null)
              {
                AsyncServer.post(SocketIOClient.this.handler, new Runnable()
                {
                  public void run()
                  {
                    SocketIOClient.this.jsonCallback.onJSON(this.val$jsonMessage);
                  }
                });
                return;
              }
              SocketIOClient.this.jsonCallback.onJSON((JSONObject)localObject1);
              return;
              if (!SocketIOClient.this.connected)
                throw new Exception("received message before client connect");
              paramAnonymousString = localObject1[1];
              Object localObject2 = new JSONObject(localObject1[3]);
              localObject1 = ((JSONObject)localObject2).getString("name");
              localObject2 = ((JSONObject)localObject2).optJSONArray("args");
              if (!"".equals(paramAnonymousString))
                SocketIOClient.this.webSocket.send(String.format("6:::%s", new Object[] { paramAnonymousString }));
              if (SocketIOClient.this.eventCallback == null)
                continue;
              if (SocketIOClient.this.handler != null)
              {
                AsyncServer.post(SocketIOClient.this.handler, new Runnable()
                {
                  public void run()
                  {
                    SocketIOClient.this.eventCallback.onEvent(this.val$event, this.val$args);
                  }
                });
                return;
              }
              SocketIOClient.this.eventCallback.onEvent((String)localObject1, (JSONArray)localObject2);
              return;
              throw new Exception(paramAnonymousString);
            }
            SocketIOClient.this.disconnected = true;
            if (SocketIOClient.this.closedCallback != null)
            {
              SocketIOClient.this.closedCallback.onCompleted(paramAnonymousString);
              return;
            }
          }
      }
    });
  }

  public static Future<SocketIOClient> connect(final AsyncHttpClient paramAsyncHttpClient, final SocketIORequest paramSocketIORequest, final SocketIOConnectCallback paramSocketIOConnectCallback)
  {
    if (Looper.myLooper() == null);
    for (final Handler localHandler = null; ; localHandler = new Handler())
    {
      FutureImpl localFutureImpl = new FutureImpl(null);
      paramSocketIORequest.setHandler(null);
      localFutureImpl.setParent(paramAsyncHttpClient.executeString(paramSocketIORequest, new AsyncHttpClient.StringCallback()
      {
        public void onCompleted(Exception paramAnonymousException, AsyncHttpResponse paramAnonymousAsyncHttpResponse, String paramAnonymousString)
        {
          int i = 0;
          if (paramAnonymousException != null)
          {
            SocketIOClient.reportError(this.val$ret, localHandler, paramSocketIOConnectCallback, paramAnonymousException);
            return;
          }
          try
          {
            paramAnonymousException = paramAnonymousString.split(":");
            paramAnonymousAsyncHttpResponse = paramAnonymousException[0];
            if (!"".equals(paramAnonymousException[1]))
              i = Integer.parseInt(paramAnonymousException[1]) / 2 * 1000;
            if (!new HashSet(Arrays.asList(paramAnonymousException[3].split(","))).contains("websocket"))
              throw new Exception("websocket not supported");
          }
          catch (Exception paramAnonymousException)
          {
            SocketIOClient.reportError(this.val$ret, localHandler, paramSocketIOConnectCallback, paramAnonymousException);
            return;
          }
          paramAnonymousException = paramSocketIORequest.getUri().toString() + "websocket/" + paramAnonymousAsyncHttpResponse + "/";
          new SocketIOClient(localHandler, i, paramAnonymousException, paramAsyncHttpClient, null).reconnect(paramSocketIOConnectCallback, this.val$ret);
        }
      }));
      return localFutureImpl;
    }
  }

  public static Future<SocketIOClient> connect(AsyncHttpClient paramAsyncHttpClient, String paramString, SocketIOConnectCallback paramSocketIOConnectCallback)
  {
    return connect(paramAsyncHttpClient, new SocketIORequest(paramString), paramSocketIOConnectCallback);
  }

  private void emitRaw(int paramInt, String paramString)
  {
    this.webSocket.send(String.format("%d:::%s", new Object[] { Integer.valueOf(paramInt), paramString }));
  }

  private Future<SocketIOClient> reconnect(SocketIOConnectCallback paramSocketIOConnectCallback)
  {
    FutureImpl localFutureImpl = new FutureImpl(null);
    reconnect(paramSocketIOConnectCallback, localFutureImpl);
    return localFutureImpl;
  }

  private void reconnect(final SocketIOConnectCallback paramSocketIOConnectCallback, final FutureImpl paramFutureImpl)
  {
    if (isConnected())
    {
      this.httpClient.getServer().post(new Runnable()
      {
        public void run()
        {
          paramFutureImpl.setComplete(new Exception("already connected"));
        }
      });
      return;
    }
    this.connected = false;
    this.disconnected = false;
    paramFutureImpl.setParent(this.httpClient.websocket(this.sessionUrl, null, new AsyncHttpClient.WebSocketConnectCallback()
    {
      public void onCompleted(Exception paramAnonymousException, WebSocket paramAnonymousWebSocket)
      {
        if (paramAnonymousException != null)
        {
          SocketIOClient.reportError(paramFutureImpl, SocketIOClient.this.handler, paramSocketIOConnectCallback, paramAnonymousException);
          return;
        }
        SocketIOClient.this.webSocket = paramAnonymousWebSocket;
        SocketIOClient.this.attach(paramSocketIOConnectCallback, paramFutureImpl);
      }
    }));
  }

  private static void reportError(FutureImpl paramFutureImpl, Handler paramHandler, SocketIOConnectCallback paramSocketIOConnectCallback, final Exception paramException)
  {
    if (!paramFutureImpl.setComplete(paramException));
    do
    {
      return;
      if (paramHandler != null)
      {
        AsyncServer.post(paramHandler, new Runnable()
        {
          public void run()
          {
            if (this.val$callback != null)
              this.val$callback.onConnectCompleted(paramException, null);
          }
        });
        return;
      }
    }
    while (paramSocketIOConnectCallback == null);
    paramSocketIOConnectCallback.onConnectCompleted(paramException, null);
  }

  public void disconnect()
  {
    this.webSocket.setStringCallback(null);
    this.webSocket.setDataCallback(null);
    this.webSocket.setClosedCallback(null);
    this.webSocket.close();
    this.webSocket = null;
  }

  public void emit(String paramString)
  {
    emitRaw(3, paramString);
  }

  public void emit(String paramString, JSONArray paramJSONArray)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("name", paramString);
      localJSONObject.put("args", paramJSONArray);
      emitRaw(5, localJSONObject.toString());
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void emit(JSONObject paramJSONObject)
  {
    emitRaw(4, paramJSONObject.toString());
  }

  public CompletedCallback getClosedCallback()
  {
    return this.closedCallback;
  }

  public EventCallback getEventCallback()
  {
    return this.eventCallback;
  }

  public JSONCallback getJSONCallback()
  {
    return this.jsonCallback;
  }

  public StringCallback getStringCallback()
  {
    return this.stringCallback;
  }

  public boolean isConnected()
  {
    return (this.connected) && (!this.disconnected) && (this.webSocket != null) && (this.webSocket.isOpen());
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.closedCallback = paramCompletedCallback;
  }

  public void setEventCallback(EventCallback paramEventCallback)
  {
    this.eventCallback = paramEventCallback;
  }

  public void setJSONCallback(JSONCallback paramJSONCallback)
  {
    this.jsonCallback = paramJSONCallback;
  }

  public void setStringCallback(StringCallback paramStringCallback)
  {
    this.stringCallback = paramStringCallback;
  }

  void setupHeartbeat()
  {
    new Runnable()
    {
      public void run()
      {
        if ((SocketIOClient.this.heartbeat <= 0) || (SocketIOClient.this.disconnected) || (!SocketIOClient.this.connected) || (this.val$ws != SocketIOClient.this.webSocket) || (this.val$ws == null) || (!this.val$ws.isOpen()))
          return;
        SocketIOClient.this.webSocket.send("2:::");
        SocketIOClient.this.webSocket.getServer().postDelayed(this, SocketIOClient.this.heartbeat);
      }
    }
    .run();
  }

  public static abstract interface EventCallback
  {
    public abstract void onEvent(String paramString, JSONArray paramJSONArray);
  }

  private static class FutureImpl extends SimpleFuture<SocketIOClient>
  {
  }

  public static abstract interface JSONCallback
  {
    public abstract void onJSON(JSONObject paramJSONObject);
  }

  public static abstract interface SocketIOConnectCallback
  {
    public abstract void onConnectCompleted(Exception paramException, SocketIOClient paramSocketIOClient);
  }

  public static class SocketIORequest extends AsyncHttpPost
  {
    String channel;

    public SocketIORequest(String paramString)
    {
      super();
      this.channel = Uri.parse(paramString).getPath();
      if (TextUtils.isEmpty(this.channel))
        this.channel = null;
    }

    public String getChannel()
    {
      return this.channel;
    }
  }

  public static abstract interface StringCallback
  {
    public abstract void onString(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.SocketIOClient
 * JD-Core Version:    0.6.2
 */