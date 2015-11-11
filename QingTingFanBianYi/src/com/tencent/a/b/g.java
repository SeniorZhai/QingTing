package com.tencent.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class g
{
  private Context a = null;
  private WifiManager b = null;
  private a c = null;
  private Handler d = null;
  private Runnable e = new Runnable()
  {
    public final void run()
    {
      g.a(g.this);
    }
  };
  private int f = 1;
  private c g = null;
  private b h = null;
  private boolean i = false;
  private byte[] j = new byte[0];

  public final void a()
  {
    synchronized (this.j)
    {
      if (!this.i)
        return;
      if ((this.a == null) || (this.c == null))
        return;
    }
    try
    {
      this.a.unregisterReceiver(this.c);
      label50: this.d.removeCallbacks(this.e);
      this.i = false;
      return;
    }
    catch (Exception localException)
    {
      break label50;
    }
  }

  public final void a(long paramLong)
  {
    if ((this.d != null) && (this.i))
    {
      this.d.removeCallbacks(this.e);
      this.d.postDelayed(this.e, paramLong);
    }
  }

  public final boolean a(Context paramContext, c paramc, int paramInt)
  {
    synchronized (this.j)
    {
      if (this.i)
        return true;
      if ((paramContext == null) || (paramc == null))
        return false;
      this.d = new Handler(Looper.getMainLooper());
      this.a = paramContext;
      this.g = paramc;
      this.f = 1;
      try
      {
        this.b = ((WifiManager)this.a.getSystemService("wifi"));
        paramContext = new IntentFilter();
        this.c = new a();
        if (this.b != null)
        {
          paramc = this.c;
          if (paramc != null);
        }
        else
        {
          return false;
        }
        paramContext.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        paramContext.addAction("android.net.wifi.SCAN_RESULTS");
        this.a.registerReceiver(this.c, paramContext);
        a(0L);
        this.i = true;
        return this.i;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
  }

  public final boolean b()
  {
    return this.i;
  }

  public final boolean c()
  {
    if ((this.a == null) || (this.b == null))
      return false;
    return this.b.isWifiEnabled();
  }

  public final class a extends BroadcastReceiver
  {
    private int a = 4;
    private List<ScanResult> b = null;
    private boolean c = false;

    public a()
    {
    }

    private void a(List<ScanResult> paramList)
    {
      if (paramList == null)
        return;
      label48: ScanResult localScanResult;
      if (this.c)
      {
        if (this.b == null)
          this.b = new ArrayList();
        int j = this.b.size();
        paramList = paramList.iterator();
        int i;
        if (paramList.hasNext())
        {
          localScanResult = (ScanResult)paramList.next();
          i = 0;
        }
        while (true)
        {
          if (i < j)
          {
            if (((ScanResult)this.b.get(i)).BSSID.equals(localScanResult.BSSID))
              this.b.remove(i);
          }
          else
          {
            this.b.add(localScanResult);
            break label48;
            break;
          }
          i += 1;
        }
      }
      if (this.b == null)
        this.b = new ArrayList();
      while (true)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          localScanResult = (ScanResult)paramList.next();
          this.b.add(localScanResult);
        }
        break;
        this.b.clear();
      }
    }

    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED"))
      {
        this.a = paramIntent.getIntExtra("wifi_state", 4);
        if (g.b(g.this) != null)
          g.b(g.this).b(this.a);
      }
      if ((paramIntent.getAction().equals("android.net.wifi.SCAN_RESULTS")) || (paramIntent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")))
      {
        paramContext = null;
        if (g.c(g.this) != null)
          paramContext = g.c(g.this).getScanResults();
        if ((!paramIntent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) || ((paramContext != null) && ((paramContext == null) || (paramContext.size() != 0))));
      }
      else
      {
        return;
      }
      if ((!this.c) && (this.b != null) && (this.b.size() >= 4) && (paramContext != null) && (paramContext.size() <= 2))
      {
        a(paramContext);
        this.c = true;
        g.this.a(0L);
        return;
      }
      a(paramContext);
      this.c = false;
      g.a(g.this, new g.b(g.this, this.b, System.currentTimeMillis(), this.a));
      if (g.b(g.this) != null)
        g.b(g.this).a(g.d(g.this));
      g.this.a(g.e(g.this) * 20000L);
    }
  }

  public final class b
    implements Cloneable
  {
    private List<ScanResult> a = null;

    public b(long arg2, int arg4)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 17	java/lang/Object:<init>	()V
      //   4: aload_0
      //   5: aconst_null
      //   6: putfield 19	com/tencent/a/b/g$b:a	Ljava/util/List;
      //   9: aload_2
      //   10: ifnull +54 -> 64
      //   13: aload_0
      //   14: new 21	java/util/ArrayList
      //   17: dup
      //   18: invokespecial 22	java/util/ArrayList:<init>	()V
      //   21: putfield 19	com/tencent/a/b/g$b:a	Ljava/util/List;
      //   24: aload_2
      //   25: invokeinterface 28 1 0
      //   30: astore_1
      //   31: aload_1
      //   32: invokeinterface 34 1 0
      //   37: ifeq +27 -> 64
      //   40: aload_1
      //   41: invokeinterface 38 1 0
      //   46: checkcast 40	android/net/wifi/ScanResult
      //   49: astore_2
      //   50: aload_0
      //   51: getfield 19	com/tencent/a/b/g$b:a	Ljava/util/List;
      //   54: aload_2
      //   55: invokeinterface 44 2 0
      //   60: pop
      //   61: goto -30 -> 31
      //   64: return
    }

    public final List<ScanResult> a()
    {
      return this.a;
    }

    public final Object clone()
    {
      try
      {
        b localb = (b)super.clone();
        if (this.a != null)
        {
          localb.a = new ArrayList();
          localb.a.addAll(this.a);
        }
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
    public abstract void a(g.b paramb);

    public abstract void b(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.g
 * JD-Core Version:    0.6.2
 */