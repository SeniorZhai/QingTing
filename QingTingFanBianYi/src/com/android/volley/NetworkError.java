package com.android.volley;

public class NetworkError extends VolleyError
{
  public NetworkError()
  {
  }

  public NetworkError(NetworkResponse paramNetworkResponse)
  {
    super(paramNetworkResponse);
  }

  public NetworkError(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.NetworkError
 * JD-Core Version:    0.6.2
 */