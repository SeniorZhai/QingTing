package fm.qingting.async.http.server;

import fm.qingting.async.AsyncSocket;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.http.AsyncHttpRequestBody;
import fm.qingting.async.http.Multimap;
import fm.qingting.async.http.libcore.RequestHeaders;
import java.util.regex.Matcher;

public abstract interface AsyncHttpServerRequest extends DataEmitter
{
  public abstract AsyncHttpRequestBody getBody();

  public abstract RequestHeaders getHeaders();

  public abstract Matcher getMatcher();

  public abstract String getMethod();

  public abstract String getPath();

  public abstract Multimap getQuery();

  public abstract AsyncSocket getSocket();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.AsyncHttpServerRequest
 * JD-Core Version:    0.6.2
 */