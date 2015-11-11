package fm.qingting.social.auth;

import android.content.Context;
import fm.qingting.social.ISocialEventListener;

class CallbackRunnable
  implements Runnable
{
  private ISocialEventListener mListener;
  private Boolean mSuccess;

  public CallbackRunnable(Context paramContext, ISocialEventListener paramISocialEventListener, Boolean paramBoolean)
  {
    this.mListener = paramISocialEventListener;
    this.mSuccess = paramBoolean;
  }

  public void run()
  {
    if (this.mSuccess.booleanValue())
      if (this.mListener != null)
        this.mListener.onComplete(null, null);
    while (this.mListener == null)
      return;
    this.mListener.onCancel(null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.auth.CallbackRunnable
 * JD-Core Version:    0.6.2
 */