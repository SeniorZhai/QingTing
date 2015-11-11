package com.mediav.ads.sdk.interfaces;

import org.json.JSONObject;

public abstract interface IMvNativeAd
{
  public abstract JSONObject getContent();

  public abstract void onAdClicked();

  public abstract void onAdShowed();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvNativeAd
 * JD-Core Version:    0.6.2
 */