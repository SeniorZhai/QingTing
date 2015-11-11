package fm.qingting.social.api;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import fm.qingting.social.ISocialEventListener;

class SinaWeiboListener
  implements RequestListener
{
  private ISocialEventListener mListener;

  public SinaWeiboListener(ISocialEventListener paramISocialEventListener)
  {
    this.mListener = paramISocialEventListener;
  }

  public void onComplete(String paramString)
  {
    if (this.mListener != null)
      this.mListener.onComplete(paramString, null);
  }

  public void onWeiboException(WeiboException paramWeiboException)
  {
    if (this.mListener != null)
    {
      this.mListener.onException(null);
      paramWeiboException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.api.SinaWeiboListener
 * JD-Core Version:    0.6.2
 */