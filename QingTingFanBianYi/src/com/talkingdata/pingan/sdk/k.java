package com.talkingdata.pingan.sdk;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class k
  implements Application.ActivityLifecycleCallbacks
{
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
  }

  public void onActivityDestroyed(Activity paramActivity)
  {
  }

  public void onActivityPaused(Activity paramActivity)
  {
    q.a(new String[] { "Lifecycle callback onActivityPaused." });
    PAAgent.onPageEnd(paramActivity, paramActivity.getLocalClassName());
  }

  public void onActivityResumed(Activity paramActivity)
  {
    q.a(new String[] { "Lifecycle callback onActivityResumed." });
    PAAgent.onPageStart(paramActivity, paramActivity.getLocalClassName());
  }

  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle)
  {
  }

  public void onActivityStarted(Activity paramActivity)
  {
  }

  public void onActivityStopped(Activity paramActivity)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.k
 * JD-Core Version:    0.6.2
 */