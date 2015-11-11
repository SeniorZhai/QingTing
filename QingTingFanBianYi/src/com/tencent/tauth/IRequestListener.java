package com.tencent.tauth;

import com.tencent.utils.HttpUtils.HttpStatusException;
import com.tencent.utils.HttpUtils.NetworkUnavailableException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract interface IRequestListener
{
  public abstract void onComplete(JSONObject paramJSONObject);

  public abstract void onConnectTimeoutException(ConnectTimeoutException paramConnectTimeoutException);

  public abstract void onHttpStatusException(HttpUtils.HttpStatusException paramHttpStatusException);

  public abstract void onIOException(IOException paramIOException);

  public abstract void onJSONException(JSONException paramJSONException);

  public abstract void onMalformedURLException(MalformedURLException paramMalformedURLException);

  public abstract void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException paramNetworkUnavailableException);

  public abstract void onSocketTimeoutException(SocketTimeoutException paramSocketTimeoutException);

  public abstract void onUnknowException(Exception paramException);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.IRequestListener
 * JD-Core Version:    0.6.2
 */