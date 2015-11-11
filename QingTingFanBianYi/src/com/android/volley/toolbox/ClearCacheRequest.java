package com.android.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.Response;

public class ClearCacheRequest extends Request<Object>
{
  private final Cache mCache;
  private final Runnable mCallback;

  public ClearCacheRequest(Cache paramCache, Runnable paramRunnable)
  {
    super(0, null, null);
    this.mCache = paramCache;
    this.mCallback = paramRunnable;
  }

  protected void deliverResponse(Object paramObject)
  {
  }

  public Request.Priority getPriority()
  {
    return Request.Priority.IMMEDIATE;
  }

  public boolean isCanceled()
  {
    this.mCache.clear();
    if (this.mCallback != null)
      new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
    return true;
  }

  protected Response<Object> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.ClearCacheRequest
 * JD-Core Version:    0.6.2
 */