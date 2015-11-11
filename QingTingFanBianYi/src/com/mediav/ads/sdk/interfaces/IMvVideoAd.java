package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import org.json.JSONObject;

public abstract interface IMvVideoAd
{
  public abstract JSONObject getContent();

  public abstract void onAdClicked(Activity paramActivity, int paramInt, IMvVideoAdOnClickListener paramIMvVideoAdOnClickListener);

  public abstract void onAdPlayExit(int paramInt);

  public abstract void onAdPlayFinshed(int paramInt);

  public abstract void onAdPlayStarted();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvVideoAd
 * JD-Core Version:    0.6.2
 */