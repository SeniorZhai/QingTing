package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner
{
  private Context mContext;

  public AsyncWeiboRunner(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public String request(String paramString1, WeiboParameters paramWeiboParameters, String paramString2)
    throws WeiboException
  {
    return HttpManager.openUrl(this.mContext, paramString1, paramString2, paramWeiboParameters);
  }

  public void requestAsync(String paramString1, WeiboParameters paramWeiboParameters, String paramString2, RequestListener paramRequestListener)
  {
    new RequestRunner(this.mContext, paramString1, paramWeiboParameters, paramString2, paramRequestListener).execute(new Void[1]);
  }

  @Deprecated
  public void requestByThread(final String paramString1, final WeiboParameters paramWeiboParameters, final String paramString2, final RequestListener paramRequestListener)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          String str = HttpManager.openUrl(AsyncWeiboRunner.this.mContext, paramString1, paramString2, paramWeiboParameters);
          if (paramRequestListener != null)
            paramRequestListener.onComplete(str);
          return;
        }
        catch (WeiboException localWeiboException)
        {
          while (paramRequestListener == null);
          paramRequestListener.onWeiboException(localWeiboException);
        }
      }
    }
    .start();
  }

  private static class AsyncTaskResult<T>
  {
    private WeiboException error;
    private T result;

    public AsyncTaskResult(WeiboException paramWeiboException)
    {
      this.error = paramWeiboException;
    }

    public AsyncTaskResult(T paramT)
    {
      this.result = paramT;
    }

    public WeiboException getError()
    {
      return this.error;
    }

    public T getResult()
    {
      return this.result;
    }
  }

  private static class RequestRunner extends AsyncTask<Void, Void, AsyncWeiboRunner.AsyncTaskResult<String>>
  {
    private final Context mContext;
    private final String mHttpMethod;
    private final RequestListener mListener;
    private final WeiboParameters mParams;
    private final String mUrl;

    public RequestRunner(Context paramContext, String paramString1, WeiboParameters paramWeiboParameters, String paramString2, RequestListener paramRequestListener)
    {
      this.mContext = paramContext;
      this.mUrl = paramString1;
      this.mParams = paramWeiboParameters;
      this.mHttpMethod = paramString2;
      this.mListener = paramRequestListener;
    }

    protected AsyncWeiboRunner.AsyncTaskResult<String> doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        paramArrayOfVoid = new AsyncWeiboRunner.AsyncTaskResult(HttpManager.openUrl(this.mContext, this.mUrl, this.mHttpMethod, this.mParams));
        return paramArrayOfVoid;
      }
      catch (WeiboException paramArrayOfVoid)
      {
      }
      return new AsyncWeiboRunner.AsyncTaskResult(paramArrayOfVoid);
    }

    protected void onPostExecute(AsyncWeiboRunner.AsyncTaskResult<String> paramAsyncTaskResult)
    {
      WeiboException localWeiboException = paramAsyncTaskResult.getError();
      if (localWeiboException != null)
      {
        this.mListener.onWeiboException(localWeiboException);
        return;
      }
      this.mListener.onComplete((String)paramAsyncTaskResult.getResult());
    }

    protected void onPreExecute()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.AsyncWeiboRunner
 * JD-Core Version:    0.6.2
 */