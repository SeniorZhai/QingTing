package com.android.volley;

public abstract interface RetryPolicy
{
  public abstract int getCurrentRetryCount();

  public abstract int getCurrentTimeout();

  public abstract void retry(VolleyError paramVolleyError)
    throws VolleyError;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.RetryPolicy
 * JD-Core Version:    0.6.2
 */