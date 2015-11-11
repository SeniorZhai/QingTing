package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import java.io.UnsupportedEncodingException;

public class StringRequest extends Request<String>
{
  private final Response.Listener<String> mListener;

  public StringRequest(int paramInt, String paramString, Response.Listener<String> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString, paramErrorListener);
    this.mListener = paramListener;
  }

  public StringRequest(String paramString, Response.Listener<String> paramListener, Response.ErrorListener paramErrorListener)
  {
    this(0, paramString, paramListener, paramErrorListener);
  }

  protected void deliverResponse(String paramString)
  {
    this.mListener.onResponse(paramString);
  }

  protected Response<String> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      String str1 = new String(paramNetworkResponse.data, HttpHeaderParser.parseCharset(paramNetworkResponse.headers));
      return Response.success(str1, HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        String str2 = new String(paramNetworkResponse.data);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.StringRequest
 * JD-Core Version:    0.6.2
 */