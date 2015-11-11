package com.mediav.ads.sdk.interfaces;

import java.util.ArrayList;

public abstract interface IMvVideoAdListener
{
  public abstract void onVideoAdLoadFailed();

  public abstract void onVideoAdLoadSucceeded(ArrayList<IMvVideoAd> paramArrayList);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvVideoAdListener
 * JD-Core Version:    0.6.2
 */