package fm.qingting.async.http.server;

import fm.qingting.async.AsyncSocket;
import fm.qingting.async.DataSink;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.http.libcore.ResponseHeaders;
import java.io.File;
import org.json.JSONObject;

public abstract interface AsyncHttpServerResponse extends DataSink, CompletedCallback
{
  public abstract void end();

  public abstract ResponseHeaders getHeaders();

  public abstract AsyncSocket getSocket();

  public abstract void onCompleted(Exception paramException);

  public abstract void redirect(String paramString);

  public abstract void responseCode(int paramInt);

  public abstract void send(String paramString);

  public abstract void send(String paramString1, String paramString2);

  public abstract void send(JSONObject paramJSONObject);

  public abstract void sendFile(File paramFile);

  public abstract void setContentType(String paramString);

  public abstract void writeHead();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.server.AsyncHttpServerResponse
 * JD-Core Version:    0.6.2
 */