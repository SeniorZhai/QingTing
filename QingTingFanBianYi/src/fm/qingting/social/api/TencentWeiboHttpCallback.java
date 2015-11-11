package fm.qingting.social.api;

import com.tencent.weibo.sdk.android.network.HttpCallback;
import fm.qingting.social.ISocialEventListener;

class TencentWeiboHttpCallback
  implements HttpCallback
{
  private ISocialEventListener mRealListener;

  public TencentWeiboHttpCallback(ISocialEventListener paramISocialEventListener)
  {
    this.mRealListener = paramISocialEventListener;
  }

  public void onResult(Object paramObject)
  {
    if (this.mRealListener != null)
      this.mRealListener.onComplete(paramObject, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.TencentWeiboHttpCallback
 * JD-Core Version:    0.6.2
 */