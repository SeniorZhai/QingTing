package com.google.android.apps.analytics;

import android.util.Log;
import java.io.IOException;
import java.net.Socket;
import org.apache.http.Header;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.params.BasicHttpParams;

class PipelinedRequester
{
  private static final int RECEIVE_BUFFER_SIZE = 8192;
  Callbacks callbacks;
  boolean canPipeline = true;
  DefaultHttpClientConnection connection = new DefaultHttpClientConnection();
  HttpHost host;
  int lastStatusCode;
  SocketFactory socketFactory;

  public PipelinedRequester(HttpHost paramHttpHost)
  {
    this(paramHttpHost, new PlainSocketFactory());
  }

  public PipelinedRequester(HttpHost paramHttpHost, SocketFactory paramSocketFactory)
  {
    this.host = paramHttpHost;
    this.socketFactory = paramSocketFactory;
  }

  private void closeConnection()
  {
    if ((this.connection != null) && (this.connection.isOpen()));
    try
    {
      this.connection.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  private void maybeOpenConnection()
    throws IOException
  {
    if ((this.connection == null) || (!this.connection.isOpen()))
    {
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      Socket localSocket = this.socketFactory.createSocket();
      localSocket = this.socketFactory.connectSocket(localSocket, this.host.getHostName(), this.host.getPort(), null, 0, localBasicHttpParams);
      localSocket.setReceiveBufferSize(8192);
      this.connection.bind(localSocket, localBasicHttpParams);
    }
  }

  public void addRequest(HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
    throws HttpException, IOException
  {
    maybeOpenConnection();
    this.connection.sendRequestHeader(paramHttpEntityEnclosingRequest);
    this.connection.sendRequestEntity(paramHttpEntityEnclosingRequest);
  }

  public void finishedCurrentRequests()
  {
    closeConnection();
  }

  public void installCallbacks(Callbacks paramCallbacks)
  {
    this.callbacks = paramCallbacks;
  }

  public void sendRequests()
    throws IOException, HttpException
  {
    this.connection.flush();
    HttpConnectionMetrics localHttpConnectionMetrics = this.connection.getMetrics();
    do
    {
      HttpResponse localHttpResponse;
      if (localHttpConnectionMetrics.getResponseCount() < localHttpConnectionMetrics.getRequestCount())
      {
        localHttpResponse = this.connection.receiveResponseHeader();
        if (!localHttpResponse.getStatusLine().getProtocolVersion().greaterEquals(HttpVersion.HTTP_1_1))
        {
          this.callbacks.pipelineModeChanged(false);
          this.canPipeline = false;
        }
        Header[] arrayOfHeader = localHttpResponse.getHeaders("Connection");
        if (arrayOfHeader != null)
        {
          int j = arrayOfHeader.length;
          int i = 0;
          while (i < j)
          {
            if ("close".equalsIgnoreCase(arrayOfHeader[i].getValue()))
            {
              this.callbacks.pipelineModeChanged(false);
              this.canPipeline = false;
            }
            i += 1;
          }
        }
        this.lastStatusCode = localHttpResponse.getStatusLine().getStatusCode();
        if (this.lastStatusCode != 200)
        {
          this.callbacks.serverError(this.lastStatusCode);
          closeConnection();
        }
      }
      else
      {
        return;
      }
      this.connection.receiveResponseEntity(localHttpResponse);
      localHttpResponse.getEntity().consumeContent();
      this.callbacks.requestSent();
      if (GoogleAnalyticsTracker.getInstance().getDebug())
        Log.v("GoogleAnalyticsTracker", "HTTP Response Code: " + localHttpResponse.getStatusLine().getStatusCode());
    }
    while (this.canPipeline);
    closeConnection();
  }

  static abstract interface Callbacks
  {
    public abstract void pipelineModeChanged(boolean paramBoolean);

    public abstract void requestSent();

    public abstract void serverError(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.PipelinedRequester
 * JD-Core Version:    0.6.2
 */