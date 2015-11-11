package com.tendcloud.tenddata;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

public class l
  implements Application.ActivityLifecycleCallbacks
{
  private j a;

  public l(j paramj)
  {
    this.a = paramj;
  }

  public void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
  }

  public void onActivityDestroyed(Activity paramActivity)
  {
  }

  public void onActivityPaused(Activity paramActivity)
  {
    this.a.c(paramActivity, paramActivity.getLocalClassName());
  }

  public void onActivityResumed(Activity paramActivity)
  {
    this.a.b(paramActivity, paramActivity.getLocalClassName());
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
 * Qualified Name:     com.tendcloud.tenddata.l
 * JD-Core Version:    0.6.2
 */