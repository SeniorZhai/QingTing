package fm.qingting.async.http.server;

import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.BufferedDataSink;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.http.filter.ChunkedOutputFilter;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.json.JSONObject;

public class AsyncHttpServerResponseImpl
  implements AsyncHttpServerResponse
{
  ChunkedOutputFilter mChunker;
  private int mContentLength = -1;
  boolean mEnded;
  boolean mHasWritten = false;
  private boolean mHeadWritten = false;
  private ResponseHeaders mHeaders = new ResponseHeaders(null, this.mRawHeaders);
  private RawHeaders mRawHeaders = new RawHeaders();
  AsyncHttpServerRequestImpl mRequest;
  BufferedDataSink mSink;
  AsyncSocket mSocket;

  static
  {
    if (!AsyncHttpServerResponseImpl.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  AsyncHttpServerResponseImpl(AsyncSocket paramAsyncSocket, AsyncHttpServerRequestImpl paramAsyncHttpServerRequestImpl)
  {
    this.mSocket = paramAsyncSocket;
    this.mSink = new BufferedDataSink(paramAsyncSocket);
    this.mRequest = paramAsyncHttpServerRequestImpl;
    this.mRawHeaders.set("Connection", "Keep-Alive");
  }

  private void writeInternal(ByteBufferList paramByteBufferList)
  {
    assert (!this.mEnded);
    initFirstWrite();
    this.mChunker.write(paramByteBufferList);
  }

  private void writeInternal(ByteBuffer paramByteBuffer)
  {
    initFirstWrite();
    this.mChunker.write(paramByteBuffer);
  }

  public void close()
  {
    end();
    if (this.mChunker != null)
    {
      this.mChunker.close();
      return;
    }
    this.mSink.close();
  }

  public void end()
  {
    if (this.mRawHeaders.get("Transfer-Encoding") == null)
    {
      send("text/html", "");
      onEnd();
      return;
    }
    initFirstWrite();
    this.mChunker.setMaxBuffer(2147483647);
    this.mChunker.write(new ByteBufferList());
    onEnd();
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
    initFirstWrite();
    return this.mChunker.getWriteableCallback();
  }

  void initFirstWrite()
  {
    if (this.mHasWritten)
      return;
    assert (this.mContentLength < 0);
    assert (this.mRawHeaders.getStatusLine() != null);
    this.mRawHeaders.set("Transfer-Encoding", "Chunked");
    writeHead();
    this.mSink.setMaxBuffer(0);
    this.mHasWritten = true;
    this.mChunker = new ChunkedOutputFilter(this.mSink);
  }

  public boolean isOpen()
  {
    return this.mSink.isOpen();
  }

  public void onCompleted(Exception paramException)
  {
    if (paramException != null)
      paramException.printStackTrace();
    end();
  }

  protected void onEnd()
  {
    this.mEnded = true;
  }

  public void redirect(String paramString)
  {
    responseCode(302);
    this.mRawHeaders.set("Location", paramString);
    end();
  }

  protected void report(Exception paramException)
  {
  }

  public void responseCode(int paramInt)
  {
    String str = AsyncHttpServer.getResponseCodeDescription(paramInt);
    this.mRawHeaders.setStatusLine(String.format("HTTP/1.1 %d %s", new Object[] { Integer.valueOf(paramInt), str }));
  }

  public void send(String paramString)
  {
    responseCode(200);
    send("text/html", paramString);
  }

  public void send(String paramString1, String paramString2)
  {
    try
    {
      if (this.mRawHeaders.getStatusLine() == null)
        responseCode(200);
      if ((!$assertionsDisabled) && (this.mContentLength >= 0))
        throw new AssertionError();
    }
    catch (UnsupportedEncodingException paramString1)
    {
      if (!$assertionsDisabled)
      {
        throw new AssertionError();
        byte[] arrayOfByte = paramString2.getBytes("UTF-8");
        this.mContentLength = arrayOfByte.length;
        this.mRawHeaders.set("Content-Length", Integer.toString(arrayOfByte.length));
        this.mRawHeaders.set("Content-Type", paramString1);
        writeHead();
        this.mSink.write(ByteBuffer.wrap(paramString2.getBytes()));
        onEnd();
      }
    }
  }

  public void send(JSONObject paramJSONObject)
  {
    send("application/json", paramJSONObject.toString());
  }

  public void sendFile(File paramFile)
  {
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramFile);
      this.mRawHeaders.set("Content-Type", AsyncHttpServer.getContentType(paramFile.getAbsolutePath()));
      responseCode(200);
      Util.pump(localFileInputStream, this, new CompletedCallback()
      {
        public void onCompleted(Exception paramAnonymousException)
        {
          AsyncHttpServerResponseImpl.this.end();
        }
      });
      return;
    }
    catch (FileNotFoundException paramFile)
    {
      responseCode(404);
      end();
    }
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mSink.setClosedCallback(paramCompletedCallback);
  }

  public void setContentType(String paramString)
  {
    assert (!this.mHeadWritten);
    this.mRawHeaders.set("Content-Type", paramString);
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    initFirstWrite();
    this.mChunker.setWriteableCallback(paramWritableCallback);
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    if (paramByteBufferList.remaining() == 0)
      return;
    writeInternal(paramByteBufferList);
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.remaining() == 0)
      return;
    writeInternal(paramByteBuffer);
  }

  public void writeHead()
  {
    assert (!this.mHeadWritten);
    this.mHeadWritten = true;
    this.mSink.write(ByteBuffer.wrap(this.mRawHeaders.toHeaderString().getBytes()));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.AsyncHttpServerResponseImpl
 * JD-Core Version:    0.6.2
 */