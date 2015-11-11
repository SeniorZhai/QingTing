package fm.qingting.async.http;

import java.net.URI;

public class AsyncHttpPost extends AsyncHttpRequest
{
  public static final String METHOD = "POST";

  public AsyncHttpPost(String paramString)
  {
    this(URI.create(paramString));
  }

  public AsyncHttpPost(URI paramURI)
  {
    super(paramURI, "POST");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.AsyncHttpPost
 * JD-Core Version:    0.6.2
 */