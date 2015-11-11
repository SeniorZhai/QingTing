package fm.qingting.async.http;

import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataSink;
import fm.qingting.async.FilteredDataEmitter;
import fm.qingting.async.LineEmitter;
import fm.qingting.async.LineEmitter.StringCallback;
import fm.qingting.async.NullDataCallback;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.http.filter.ChunkedOutputFilter;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import java.nio.ByteBuffer;

abstract class AsyncHttpResponseImpl extends FilteredDataEmitter
  implements AsyncHttpResponse
{
  boolean mCompleted = false;
  private boolean mFirstWrite = true;
  LineEmitter.StringCallback mHeaderCallback = new LineEmitter.StringCallback()
  {
    private RawHeaders mRawHeaders = new RawHeaders();

    public void onStringAvailable(String paramAnonymousString)
    {
      try
      {
        if (this.mRawHeaders.getStatusLine() == null)
        {
          this.mRawHeaders.setStatusLine(paramAnonymousString);
          return;
        }
        if (!"\r".equals(paramAnonymousString))
        {
          this.mRawHeaders.addLine(paramAnonymousString);
          return;
        }
      }
      catch (Exception paramAnonymousString)
      {
        AsyncHttpResponseImpl.this.report(paramAnonymousString);
        return;
      }
      AsyncHttpResponseImpl.this.mHeaders = new ResponseHeaders(AsyncHttpResponseImpl.this.mRequest.getUri(), this.mRawHeaders);
      AsyncHttpResponseImpl.this.onHeadersReceived();
      if (AsyncHttpResponseImpl.this.mSocket != null)
      {
        paramAnonymousString = HttpUtil.getBodyDecoder(AsyncHttpResponseImpl.this.mSocket, this.mRawHeaders, false);
        AsyncHttpResponseImpl.this.setDataEmitter(paramAnonymousString);
      }
    }
  };
  ResponseHeaders mHeaders;
  private CompletedCallback mReporter = new CompletedCallback()
  {
    public void onCompleted(Exception paramAnonymousException)
    {
      if ((paramAnonymousException != null) && (!AsyncHttpResponseImpl.this.mCompleted))
      {
        AsyncHttpResponseImpl.this.report(new Exception("connection closed before response completed."));
        return;
      }
      AsyncHttpResponseImpl.this.report(paramAnonymousException);
    }
  };
  private AsyncHttpRequest mRequest;
  DataSink mSink;
  private AsyncSocket mSocket;
  private AsyncHttpRequestBody mWriter;

  static
  {
    if (!AsyncHttpResponseImpl.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  public AsyncHttpResponseImpl(AsyncHttpRequest paramAsyncHttpRequest)
  {
    this.mRequest = paramAsyncHttpRequest;
  }

  private void assertContent()
  {
    if (!this.mFirstWrite);
    do
    {
      return;
      this.mFirstWrite = false;
      assert (this.mRequest.getHeaders().getHeaders().get("Content-Type") != null);
    }
    while (($assertionsDisabled) || (this.mRequest.getHeaders().getHeaders().get("Transfer-Encoding") != null) || (this.mRequest.getHeaders().getContentLength() != -1));
    throw new AssertionError();
  }

  public void close()
  {
    this.mSink.close();
  }

  public void end()
  {
    write(ByteBuffer.wrap(new byte[0]));
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mSink.getClosedCallback();
  }

  public ResponseHeaders getHeaders()
  {
    return this.mHeaders;
  }

  public AsyncServer getServer()
  {
    return this.mSocket.getServer();
  }

  public AsyncSocket getSocket()
  {
    return this.mSocket;
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mSink.getWriteableCallback();
  }

  public boolean isOpen()
  {
    return this.mSink.isOpen();
  }

  protected abstract void onHeadersReceived();

  protected void report(Exception paramException)
  {
    super.report(paramException);
    this.mSocket.setDataCallback(new NullDataCallback()
    {
      public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
      {
        super.onDataAvailable(paramAnonymousDataEmitter, paramAnonymousByteBufferList);
        AsyncHttpResponseImpl.this.mSocket.close();
      }
    });
    this.mSocket.setWriteableCallback(null);
    this.mSocket.setClosedCallback(null);
    this.mSocket.setEndCallback(null);
    this.mCompleted = true;
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mSink.setClosedCallback(paramCompletedCallback);
  }

  void setSocket(AsyncSocket paramAsyncSocket)
  {
    this.mSocket = paramAsyncSocket;
    if (this.mSocket == null)
      return;
    this.mWriter = this.mRequest.getBody();
    if (this.mWriter != null)
    {
      this.mRequest.getHeaders().setContentType(this.mWriter.getContentType());
      if (this.mWriter.length() != -1)
      {
        this.mRequest.getHeaders().setContentLength(this.mWriter.length());
        this.mSink = this.mSocket;
      }
    }
    while (true)
    {
      Util.writeAll(paramAsyncSocket, this.mRequest.getRequestString().getBytes(), new CompletedCallback()
      {
        public void onCompleted(Exception paramAnonymousException)
        {
          if (AsyncHttpResponseImpl.this.mWriter != null)
            AsyncHttpResponseImpl.this.mWriter.write(AsyncHttpResponseImpl.this.mRequest, AsyncHttpResponseImpl.this);
        }
      });
      LineEmitter localLineEmitter = new LineEmitter();
      paramAsyncSocket.setDataCallback(localLineEmitter);
      localLineEmitter.setLineCallback(this.mHeaderCallback);
      this.mSocket.setEndCallback(this.mReporter);
      this.mSocket.setClosedCallback(new CompletedCallback()
      {
        public void onCompleted(Exception paramAnonymousException)
        {
        }
      });
      return;
      this.mRequest.getHeaders().getHeaders().set("Transfer-Encoding", "Chunked");
      this.mSink = new ChunkedOutputFilter(this.mSocket);
      continue;
      this.mSink = this.mSocket;
    }
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mSink.setWriteableCallback(paramWritableCallback);
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    assertContent();
    this.mSink.write(paramByteBufferList);
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    assertContent();
    this.mSink.write(paramByteBuffer);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpResponseImpl
 * JD-Core Version:    0.6.2
 */