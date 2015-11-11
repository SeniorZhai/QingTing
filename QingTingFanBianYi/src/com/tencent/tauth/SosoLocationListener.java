package com.tencent.tauth;

import android.location.Location;
import com.tencent.a.a.a.b;
import com.tencent.a.a.a.d;
import com.tencent.b.a.g;

public class SosoLocationListener extends b
{
  private LbsAgent.OnGetLocationListener listener;

  public SosoLocationListener(LbsAgent.OnGetLocationListener paramOnGetLocationListener)
  {
    super(1, 0, 0, 8);
    this.listener = paramOnGetLocationListener;
  }

  public void onLocationDataUpdate(byte[] paramArrayOfByte, int paramInt)
  {
    super.onLocationDataUpdate(paramArrayOfByte, paramInt);
    g.c("openSDK_LOG", "location: onLocationDataUpdate = " + paramArrayOfByte);
  }

  public void onLocationUpdate(d paramd)
  {
    g.c("openSDK_LOG", "location: onLocationUpdate = " + paramd);
    super.onLocationUpdate(paramd);
    if (paramd == null);
    Location localLocation;
    do
    {
      return;
      localLocation = new Location("passive");
      localLocation.setLatitude(paramd.b);
      localLocation.setLongitude(paramd.c);
    }
    while (this.listener == null);
    this.listener.onLocationUpdate(localLocation);
  }

  public void onStatusUpdate(int paramInt)
  {
    g.c("openSDK_LOG", "location: onStatusUpdate = " + paramInt);
    super.onStatusUpdate(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.tauth.SosoLocationListener
 * JD-Core Version:    0.6.2
 */