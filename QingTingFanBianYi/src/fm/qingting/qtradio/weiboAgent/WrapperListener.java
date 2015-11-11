package fm.qingting.qtradio.weiboAgent;

import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.social.SocialEventListener;

class WrapperListener extends SocialEventListener
{
  private CloudCenter.OnLoginEventListerner mListener;

  public WrapperListener(CloudCenter.OnLoginEventListerner paramOnLoginEventListerner)
  {
    this.mListener = paramOnLoginEventListerner;
  }

  public void onComplete(Object paramObject1, Object paramObject2)
  {
    WeiboAgent.getInstance().sendWeiboOnceLogin();
    if (this.mListener != null)
      this.mListener.onLoginSuccessed(1);
  }

  public void onException(Object paramObject)
  {
    if (this.mListener != null)
      this.mListener.onLoginFailed(1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.weiboAgent.WrapperListener
 * JD-Core Version:    0.6.2
 */