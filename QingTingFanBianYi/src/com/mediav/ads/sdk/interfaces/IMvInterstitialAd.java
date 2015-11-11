package com.mediav.ads.sdk.interfaces;

import android.app.Activity;

public abstract interface IMvInterstitialAd
{
  public abstract void closeAds();

  public abstract void setAdEventListener(Object paramObject);

  public abstract void showAds(Activity paramActivity);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvInterstitialAd
 * JD-Core Version:    0.6.2
 */