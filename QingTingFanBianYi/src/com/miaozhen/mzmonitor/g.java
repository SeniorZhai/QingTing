package com.miaozhen.mzmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

class g
{
  private static g d;
  private Context a;
  private boolean b;
  private LocationManager c;
  private final LocationListener e = new LocationListener()
  {
    public final void onLocationChanged(Location paramAnonymousLocation)
    {
      g.a(g.this, paramAnonymousLocation);
    }

    public final void onProviderDisabled(String paramAnonymousString)
    {
      g.this.a();
    }

    public final void onProviderEnabled(String paramAnonymousString)
    {
    }

    public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle)
    {
    }
  };

  private g(Context paramContext)
  {
    this.a = paramContext;
    this.b = false;
  }

  public static g a(Context paramContext)
  {
    try
    {
      if (d == null)
        d = new g(paramContext);
      paramContext = d;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  final void a()
  {
    if (this.c != null)
    {
      this.c.removeUpdates(this.e);
      this.c = null;
      this.b = false;
    }
  }

  public final void b(Context paramContext)
  {
    if (paramContext.getSharedPreferences("mzSdkProfilePrefs", 0).getBoolean("0a9896360edb4c54030c25b12f447fb0", false));
    do
    {
      return;
      if (this.b)
        break;
      this.c = ((LocationManager)paramContext.getSystemService("location"));
      paramContext = new Criteria();
      paramContext.setAltitudeRequired(false);
      paramContext.setBearingRequired(false);
      paramContext.setSpeedRequired(false);
      paramContext.setCostAllowed(false);
      paramContext = this.c.getBestProvider(paramContext, true);
    }
    while (paramContext == null);
    this.b = true;
    this.c.requestLocationUpdates(paramContext, 500L, 0.0F, this.e);
    new a().start();
    return;
    Log.d("MZSDK:20141030", "MZLocationManager is still running...");
  }

  final class a extends Thread
  {
    a()
    {
    }

    public final void run()
    {
      try
      {
        Thread.sleep(j.f(g.a(g.this)) * 1000);
        g.this.a();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.g
 * JD-Core Version:    0.6.2
 */