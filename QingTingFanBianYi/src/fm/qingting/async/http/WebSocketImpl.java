package fm.qingting.async.http;

import android.util.Base64;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.BufferedDataSink;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import fm.qingting.async.http.server.AsyncHttpServerRequest;
import fm.qingting.async.http.server.AsyncHttpServerResponse;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.UUID;

public class WebSocketImpl
  implements WebSocket
{
  static final String MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
  private boolean hasClosed = false;
  private DataCallback mDataCallback;
  CompletedCallback mExceptionCallback;
  HybiParser mParser;
  BufferedDataSink mSink;
  private AsyncSocket mSocket;
  private WebSocket.StringCallback mStringCallback;
  private LinkedList<ByteBufferList> pending;

  public WebSocketImpl(AsyncSocket paramAsyncSocket)
  {
    this.mSocket = paramAsyncSocket;
    this.mSink = new BufferedDataSink(this.mSocket);
  }

  public WebSocketImpl(AsyncHttpServerRequest paramAsyncHttpServerRequest, AsyncHttpServerResponse paramAsyncHttpServerResponse)
  {
    this(paramAsyncHttpServerRequest.getSocket());
    String str = paramAsyncHttpServerRequest.getHeaders().getHeaders().get("Sec-WebSocket-Key");
    str = SHA1(str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11");
    paramAsyncHttpServerRequest.getHeaders().getHeaders().get("Origin");
    paramAsyncHttpServerResponse.responseCode(101);
    paramAsyncHttpServerResponse.getHeaders().getHeaders().set("Upgrade", "WebSocket");
    paramAsyncHttpServerResponse.getHeaders().getHeaders().set("Connection", "Upgrade");
    paramAsyncHttpServerResponse.getHeaders().getHeaders().set("Sec-WebSocket-Accept", str);
    paramAsyncHttpServerResponse.writeHead();
    setupParser(false);
  }

  private static String SHA1(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes("iso-8859-1"), 0, paramString.length());
      paramString = Base64.encodeToString(localMessageDigest.digest(), 0);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  private void addAndEmit(ByteBufferList paramByteBufferList)
  {
    if (this.pending == null)
    {
      Util.emitAllData(this, paramByteBufferList);
      if (paramByteBufferList.remaining() > 0)
      {
        this.pending = new LinkedList();
        this.pending.add(paramByteBufferList);
      }
    }
    do
    {
      return;
      while (!isPaused())
      {
        paramByteBufferList = (ByteBufferList)this.pending.remove();
        Util.emitAllData(this, paramByteBufferList);
        if (paramByteBufferList.remaining() > 0)
          this.pending.add(0, paramByteBufferList);
      }
    }
    while (this.pending.size() != 0);
    this.pending = null;
  }

  public static void addWebSocketUpgradeHeaders(AsyncHttpRequest paramAsyncHttpRequest, String paramString)
  {
    RawHeaders localRawHeaders = paramAsyncHttpRequest.getHeaders().getHeaders();
    String str = UUID.randomUUID().toString();
    localRawHeaders.set("Sec-WebSocket-Version", "13");
    localRawHeaders.set("Sec-WebSocket-Key", str);
    localRawHeaders.set("Connection", "Upgrade");
    localRawHeaders.set("Upgrade", "websocket");
    if (paramString != null)
      localRawHeaders.set("Sec-WebSocket-Protocol", paramString);
    localRawHeaders.set("Pragma", "no-cache");
    localRawHeaders.set("Cache-Control", "no-cache");
    paramAsyncHttpRequest.getHeaders().setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.15 Safari/537.36");
  }

  public static WebSocket finishHandshake(RawHeaders paramRawHeaders, AsyncHttpResponse paramAsyncHttpResponse)
  {
    if (paramAsyncHttpResponse == null);
    String str;
    do
    {
      do
      {
        do
          return null;
        while ((paramAsyncHttpResponse.getHeaders().getHeaders().getResponseCode() != 101) || (!"websocket".equalsIgnoreCase(paramAsyncHttpResponse.getHeaders().getHeaders().get("Upgrade"))));
        str = paramAsyncHttpResponse.getHeaders().getHeaders().get("Sec-WebSocket-Accept");
      }
      while (str == null);
      paramRawHeaders = paramRawHeaders.get("Sec-WebSocket-Key");
    }
    while ((paramRawHeaders == null) || (!str.equalsIgnoreCase(SHA1(paramRawHeaders + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").trim())));
    paramRawHeaders = new WebSocketImpl(paramAsyncHttpResponse.detachSocket());
    paramRawHeaders.setupParser(true);
    return paramRawHeaders;
  }

  private void setupParser(boolean paramBoolean)
  {
    this.mParser = new HybiParser(this.mSocket)
    {
      protected void onDisconnect(int paramAnonymousInt, String paramAnonymousString)
      {
        WebSocketImpl.this.mSocket.close();
        WebSocketImpl.access$302(WebSocketImpl.this, true);
      }

      protected void onMessage(String paramAnonymousString)
      {
        if (WebSocketImpl.this.mStringCallback != null)
          WebSocketImpl.this.mStringCallback.onStringAvailable(paramAnonymousString);
      }

      protected void onMessage(byte[] paramAnonymousArrayOfByte)
      {
        WebSocketImpl.this.addAndEmit(new ByteBufferList(paramAnonymousArrayOfByte));
      }

      protected void report(Exception paramAnonymousException)
      {
        if (WebSocketImpl.this.mExceptionCallback != null)
          WebSocketImpl.this.mExceptionCallback.onCompleted(paramAnonymousException);
      }

      protected void sendFrame(byte[] paramAnonymousArrayOfByte)
      {
        WebSocketImpl.this.mSink.write(ByteBuffer.wrap(paramAnonymousArrayOfByte));
      }
    };
    this.mParser.setMasking(paramBoolean);
    if (this.mSocket.isPaused())
      this.mSocket.resume();
  }

  public void close()
  {
    this.hasClosed = true;
    this.mSocket.close();
  }

  public void end()
  {
    this.mSocket.end();
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mSocket.getClosedCallback();
  }

  public DataCallback getDataCallback()
  {
    return this.mDataCallback;
  }

  public CompletedCallback getEndCallback()
  {
    return this.mExceptionCallback;
  }

  public AsyncServer getServer()
  {
    return this.mSocket.getServer();
  }

  public AsyncSocket getSocket()
  {
    return this.mSocket;
  }

  public WebSocket.StringCallback getStringCallback()
  {
    return this.mStringCallback;
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mSink.getWriteableCallback();
  }

  public boolean isBuffering()
  {
    return this.mSink.remaining() > 0;
  }

  public boolean isChunked()
  {
    return false;
  }

  public boolean isOpen()
  {
    return this.mSocket.isOpen();
  }

  public boolean isPaused()
  {
    return this.mSocket.isPaused();
  }

  public void pause()
  {
    this.mSocket.pause();
  }

  public void resume()
  {
    this.mSocket.resume();
  }

  public void send(String paramString)
  {
    this.mSink.write(ByteBuffer.wrap(this.mParser.frame(paramString)));
  }

  public void send(byte[] paramArrayOfByte)
  {
    this.mSink.write(ByteBuffer.wrap(this.mParser.frame(paramArrayOfByte)));
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mSocket.setClosedCallback(paramCompletedCallback);
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mDataCallback = paramDataCallback;
  }

  public void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.mExceptionCallback = paramCompletedCallback;
  }

  public void setStringCallback(WebSocket.StringCallback paramStringCallback)
  {
    this.mStringCallback = paramStringCallback;
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mSink.setWriteableCallback(paramWritableCallback);
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    byte[] arrayOfByte = new byte[paramByteBufferList.remaining()];
    paramByteBufferList.get(arrayOfByte);
    paramByteBufferList.clear();
    send(arrayOfByte);
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    byte[] arrayOfByte = new byte[paramByteBuffer.remaining()];
    paramByteBuffer.get(arrayOfByte);
    paramByteBuffer.position(0);
    paramByteBuffer.limit(0);
    send(arrayOfByte);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.WebSocketImpl
 * JD-Core Version:    0.6.2
 */