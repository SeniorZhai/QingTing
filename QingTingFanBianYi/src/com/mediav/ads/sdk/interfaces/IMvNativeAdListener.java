package com.mediav.ads.sdk.interfaces;

import java.util.ArrayList;

public abstract interface IMvNativeAdListener
{
  public abstract void onNativeAdLoadFailed();

  public abstract void onNativeAdLoadSucceeded(ArrayList<IMvNativeAd> paramArrayList);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvNativeAdListener
 * JD-Core Version:    0.6.2
 */