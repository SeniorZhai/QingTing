package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.ViewGroup;

public abstract interface IBridge
{
  public abstract void activityDestroy(Activity paramActivity);

  public abstract Object getBanner(ViewGroup paramViewGroup, Activity paramActivity, String paramString, Boolean paramBoolean);

  public abstract Object getFloatingBanner(Activity paramActivity, String paramString, Boolean paramBoolean, Integer paramInteger1, Integer paramInteger2);

  public abstract Object getInterstitial(Activity paramActivity, String paramString, Boolean paramBoolean);

  public abstract Object getNativeAdLoader(Activity paramActivity, String paramString, IMvNativeAdListener paramIMvNativeAdListener, Boolean paramBoolean);

  public abstract ServiceBridge getServiceBridge(Service paramService);

  public abstract void getSplashAd(ViewGroup paramViewGroup, Activity paramActivity, String paramString, IMvAdEventListener paramIMvAdEventListener, Boolean paramBoolean1, Boolean paramBoolean2);

  public abstract Object getVideoAdLoader(Context paramContext, String paramString, IMvVideoAdListener paramIMvVideoAdListener, Boolean paramBoolean);

  public abstract void setLandingPageView(IMvLandingPageView paramIMvLandingPageView);

  public abstract void setLogSwitch(boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.IBridge
 * JD-Core Version:    0.6.2
 */