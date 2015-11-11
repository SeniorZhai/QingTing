package com.mediav.ads.sdk.interfaces;

import java.util.HashSet;

public abstract interface IMvNativeAdLoader
{
  public abstract void clearAdAttributes();

  public abstract void clearKeywords();

  public abstract IMvNativeAd getAd();

  public abstract void loadAds();

  public abstract void loadAds(int paramInt);

  public abstract void setAdAttributes(IMvAdAttributes paramIMvAdAttributes);

  public abstract void setKeywords(HashSet<String> paramHashSet);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvNativeAdLoader
 * JD-Core Version:    0.6.2
 */