package com.tencent.tauth;

import android.content.Context;
import android.location.Location;
import com.tencent.a.a.a.a;

public class LbsAgent
{
  private static final String SOSO_VERIFY_CODE = "WQMPF-XMH66-ISQXP-OIGMM-BNL7M";
  private static final String SOSO_VERIFY_NAME = "OpenSdk";
  private SosoLocationListener sosoListener;

  public void removeUpdate()
  {
    a.a().b();
    this.sosoListener = null;
  }

  public void requestLocationUpdate(Context paramContext, OnGetLocationListener paramOnGetLocationListener)
  {
    this.sosoListener = new SosoLocationListener(paramOnGetLocationListener);
    a.a().a(paramContext, this.sosoListener);
  }

  public boolean verifyRegCode()
  {
    return a.a().a("OpenSdk", "WQMPF-XMH66-ISQXP-OIGMM-BNL7M");
  }

  public static abstract interface OnGetLocationListener
  {
    public abstract void onLocationUpdate(Location paramLocation);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.LbsAgent
 * JD-Core Version:    0.6.2
 */