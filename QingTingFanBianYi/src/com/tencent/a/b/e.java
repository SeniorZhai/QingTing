package com.tencent.a.b;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.Iterator;

public final class e
{
  private static LocationManager b = null;
  private static float d = 0.0F;
  private Context a = null;
  private a c = null;
  private c e = null;
  private b f = null;
  private boolean g = false;
  private byte[] h = new byte[0];
  private int i = 1024;
  private long j = 0L;
  private boolean k = false;
  private int l = 0;
  private int m = 0;

  private void b()
  {
    this.m = 0;
    this.l = 0;
    Object localObject = b.getGpsStatus(null);
    if (localObject == null);
    while (true)
    {
      return;
      int n = ((GpsStatus)localObject).getMaxSatellites();
      localObject = ((GpsStatus)localObject).getSatellites().iterator();
      if (localObject != null)
        while ((((Iterator)localObject).hasNext()) && (this.l <= n))
        {
          this.l += 1;
          if (((GpsSatellite)((Iterator)localObject).next()).usedInFix())
            this.m += 1;
        }
    }
  }

  public final void a()
  {
    synchronized (this.h)
    {
      if (!this.g)
        return;
      if ((b != null) && (this.c != null))
      {
        b.removeGpsStatusListener(this.c);
        b.removeUpdates(this.c);
      }
      this.g = false;
      return;
    }
  }

  public final boolean a(c paramc, Context paramContext)
  {
    synchronized (this.h)
    {
      if (this.g)
        return true;
      if ((paramContext == null) || (paramc == null))
        return false;
      this.a = paramContext;
      this.e = paramc;
      try
      {
        b = (LocationManager)this.a.getSystemService("location");
        this.c = new a((byte)0);
        if (b != null)
        {
          paramc = this.c;
          if (paramc != null);
        }
        else
        {
          return false;
        }
      }
      catch (Exception paramc)
      {
        return false;
      }
      try
      {
        b.requestLocationUpdates("gps", 1000L, 0.0F, this.c);
        b.addGpsStatusListener(this.c);
        if (b.isProviderEnabled("gps"));
        for (this.i = 4; ; this.i = 0)
        {
          this.g = true;
          return this.g;
        }
      }
      catch (Exception paramc)
      {
        return false;
      }
    }
  }

  final class a
    implements GpsStatus.Listener, LocationListener
  {
    private a()
    {
    }

    public final void onGpsStatusChanged(int paramInt)
    {
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        e.a(e.this);
        return;
        e.a(e.this, 1);
        continue;
        e.a(e.this, 0);
        continue;
        e.a(e.this, 2);
      }
    }

    public final void onLocationChanged(Location paramLocation)
    {
      int j = 0;
      double d1;
      double d2;
      int i;
      if (paramLocation != null)
      {
        d1 = paramLocation.getLatitude();
        d2 = paramLocation.getLongitude();
        i = j;
        if (d1 != 29.999998211860657D)
        {
          if (d2 != 103.99999916553497D)
            break label49;
          i = j;
        }
        if (i != 0)
          break label136;
      }
      label49: 
      do
      {
        return;
        i = j;
        if (Math.abs(d1) < 1.0E-008D)
          break;
        i = j;
        if (Math.abs(d2) < 1.0E-008D)
          break;
        i = j;
        if (d1 < -90.0D)
          break;
        i = j;
        if (d1 > 90.0D)
          break;
        i = j;
        if (d2 < -180.0D)
          break;
        i = j;
        if (d2 > 180.0D)
          break;
        i = 1;
        break;
        e.a(e.this, System.currentTimeMillis());
        e.a(e.this);
        e.a(e.this, 2);
        e.a(e.this, new e.b(e.this, paramLocation, e.b(e.this), e.c(e.this), e.d(e.this), e.e(e.this)));
      }
      while (e.f(e.this) == null);
      label136: e.f(e.this).a(e.g(e.this));
    }

    public final void onProviderDisabled(String paramString)
    {
      if (paramString != null);
      try
      {
        boolean bool = paramString.equals("gps");
        if (!bool);
        do
        {
          return;
          e.b(e.this, e.c(e.this, 0));
          e.d(e.this, 0);
        }
        while (e.f(e.this) == null);
        e.f(e.this).a(e.d(e.this));
        return;
      }
      catch (Exception paramString)
      {
      }
    }

    public final void onProviderEnabled(String paramString)
    {
      if (paramString != null);
      try
      {
        boolean bool = paramString.equals("gps");
        if (!bool);
        do
        {
          return;
          e.d(e.this, 4);
        }
        while (e.f(e.this) == null);
        e.f(e.this).a(e.d(e.this));
        return;
      }
      catch (Exception paramString)
      {
      }
    }

    public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  }

  public final class b
    implements Cloneable
  {
    private Location a = null;
    private long b = 0L;
    private int c = 0;

    public b(Location paramInt1, int paramInt2, int paramInt3, int paramLong, long arg6)
    {
      if (paramInt1 != null)
      {
        this.a = new Location(paramInt1);
        this.c = paramInt3;
        Object localObject;
        this.b = localObject;
      }
    }

    public final boolean a()
    {
      if (this.a == null);
      while (((this.c > 0) && (this.c < 3)) || (System.currentTimeMillis() - this.b > 30000L))
        return false;
      return true;
    }

    public final Location b()
    {
      return this.a;
    }

    public final Object clone()
    {
      try
      {
        b localb = (b)super.clone();
        if (this.a != null)
          localb.a = new Location(this.a);
        return localb;
      }
      catch (Exception localException)
      {
        while (true)
          Object localObject = null;
      }
    }
  }

  public static abstract interface c
  {
    public abstract void a(int paramInt);

    public abstract void a(e.b paramb);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.e
 * JD-Core Version:    0.6.2
 */