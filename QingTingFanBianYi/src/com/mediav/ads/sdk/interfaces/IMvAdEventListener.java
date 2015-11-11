package com.mediav.ads.sdk.interfaces;

public abstract interface IMvAdEventListener
{
  public abstract void onAdviewClicked();

  public abstract void onAdviewClosed();

  public abstract void onAdviewDestroyed();

  public abstract void onAdviewDismissedLandpage();

  public abstract void onAdviewGotAdFail();

  public abstract void onAdviewGotAdSucceed();

  public abstract void onAdviewIntoLandpage();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IMvAdEventListener
 * JD-Core Version:    0.6.2
 */