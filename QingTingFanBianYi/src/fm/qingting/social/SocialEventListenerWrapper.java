package fm.qingting.social;

public class SocialEventListenerWrapper
  implements ISocialEventListener
{
  private ISocialEventListener mListener;

  public SocialEventListenerWrapper(ISocialEventListener paramISocialEventListener)
  {
    this.mListener = paramISocialEventListener;
  }

  public void onCancel(Object paramObject)
  {
    if (this.mListener != null)
      this.mListener.onCancel(paramObject);
  }

  public void onComplete(Object paramObject1, Object paramObject2)
  {
    if (this.mListener != null)
      this.mListener.onComplete(paramObject1, paramObject2);
  }

  public void onException(Object paramObject)
  {
    if (this.mListener != null)
      this.mListener.onException(paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.SocialEventListenerWrapper
 * JD-Core Version:    0.6.2
 */