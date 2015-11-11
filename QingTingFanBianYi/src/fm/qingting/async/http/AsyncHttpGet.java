package fm.qingting.async.http;

import java.net.URI;

public class AsyncHttpGet extends AsyncHttpRequest
{
  public static final String METHOD = "GET";

  public AsyncHttpGet(String paramString)
  {
    super(URI.create(paramString), "GET");
  }

  public AsyncHttpGet(URI paramURI)
  {
    super(paramURI, "GET");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpGet
 * JD-Core Version:    0.6.2
 */