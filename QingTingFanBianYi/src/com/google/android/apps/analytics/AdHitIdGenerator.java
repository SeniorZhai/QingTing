package com.google.android.apps.analytics;

public class AdHitIdGenerator
{
  private boolean adMobSdkInstalled;

  public AdHitIdGenerator()
  {
    try
    {
      if (Class.forName("com.google.ads.AdRequest") != null);
      for (boolean bool = true; ; bool = false)
      {
        this.adMobSdkInstalled = bool;
        return;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      this.adMobSdkInstalled = false;
    }
  }

  AdHitIdGenerator(boolean paramBoolean)
  {
    this.adMobSdkInstalled = paramBoolean;
  }

  int getAdHitId()
  {
    if (!this.adMobSdkInstalled)
      return 0;
    return AdMobInfo.getInstance().generateAdHitId();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.AdHitIdGenerator
 * JD-Core Version:    0.6.2
 */