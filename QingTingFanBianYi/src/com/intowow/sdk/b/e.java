package com.intowow.sdk.b;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.intowow.sdk.ADEventListener;
import com.intowow.sdk.SplashAD;
import com.intowow.sdk.SplashAD.ActivityType;
import com.intowow.sdk.SplashAD.SplashAdListener;
import com.intowow.sdk.SplashAdActivity.IAdActivity;
import com.intowow.sdk.a.b.b;
import com.intowow.sdk.a.l;
import com.intowow.sdk.i.b.a.a;
import com.intowow.sdk.j.o;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.triggerresponse.TriggerResponse;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class e
  implements h.a
{
  private static e a = null;
  private Context b = null;
  private com.intowow.sdk.i.a c = null;
  private g d = null;
  private d e = null;
  private com.intowow.sdk.i.b.b f = null;
  private com.intowow.sdk.f.b g = null;
  private h h = null;
  private a i = null;
  private k j = null;
  private c k = null;
  private com.intowow.sdk.d.a l = null;
  private SplashAD.SplashAdListener m = null;
  private com.intowow.sdk.i.b.a n = null;
  private b o = null;
  private int p = 0;
  private boolean q = false;
  private ExecutorService r = null;
  private WeakReference<ADEventListener> s = null;
  private SplashAdActivity.IAdActivity t = null;
  private final h.b[] u = { h.b.d, h.b.e, h.b.f };

  private e(Context paramContext)
  {
    com.intowow.sdk.j.h.a("I2WAPI");
    this.b = paramContext.getApplicationContext();
    com.intowow.sdk.j.k.a(this.b);
    com.intowow.sdk.j.c.a(this.b);
    this.h = new h();
    this.c = new com.intowow.sdk.i.a();
    this.d = new g(this.b);
    this.g = new com.intowow.sdk.f.b(this.b, this.h);
    new o(this.g).a();
    this.e = new d();
    this.f = new com.intowow.sdk.i.b.b(this.g, this.e);
    this.j = new k(this.b, this.h, this.g);
    this.i = new a(this.j, this.g, this.c);
    this.k = new c(this.g, this.h);
    if ((com.intowow.sdk.a.e.a) || (this.g.m() != null))
    {
      com.intowow.sdk.a.e.a = true;
      this.l = new com.intowow.sdk.d.a(this.b, this.g, this.j);
      this.j.a(this.l);
      this.h.a(new a(null));
    }
    this.h.a(this.g);
    this.h.a(this.j);
    this.h.a(this.i);
    u();
    this.n = new com.intowow.sdk.i.b.a(new a.a()
    {
      public void a()
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("type", h.b.d.ordinal());
        e.e(e.this).a(localBundle);
      }

      public void a(int paramAnonymousInt)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("type", h.b.e.ordinal());
        localBundle.putInt("duration", paramAnonymousInt);
        e.e(e.this).a(localBundle);
      }
    });
    this.o = new b();
    this.o.a();
    paramContext = new Bundle();
    paramContext.putInt("type", h.b.b.ordinal());
    this.h.a(paramContext);
    this.r = Executors.newSingleThreadExecutor();
  }

  public static e a(Context paramContext)
  {
    if (a == null)
      a = new e(paramContext);
    return a;
  }

  private void a(int paramInt)
  {
    this.k.a();
  }

  private void u()
  {
    while (true)
    {
      int i1;
      try
      {
        Object localObject = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localObject == null)
        {
          i1 = 1;
          if (this.p != i1)
          {
            this.p = i1;
            localObject = new Bundle();
            ((Bundle)localObject).putInt("type", h.b.u.ordinal());
            ((Bundle)localObject).putInt("network_type", this.p);
            this.h.a((Bundle)localObject);
          }
        }
        else
        {
          if (((NetworkInfo)localObject).isConnectedOrConnecting())
          {
            i1 = ((NetworkInfo)localObject).getType();
            if (i1 != 1)
              break label228;
            i1 = 2;
            continue;
            if (i1 == 0)
            {
              i1 = ((TelephonyManager)this.j.b().getSystemService("phone")).getNetworkType();
              switch (i1)
              {
              default:
                i1 = 0;
                break;
              case 1:
              case 2:
              case 4:
              case 7:
              case 11:
                i1 = 3;
                break;
              case 3:
              case 5:
              case 6:
              case 8:
              case 9:
              case 10:
              case 12:
              case 14:
              case 15:
                i1 = 4;
                break;
              case 13:
                i1 = 5;
                break;
              }
            }
            i1 = 0;
            continue;
          }
          i1 = 1;
          continue;
        }
      }
      catch (Exception localException)
      {
      }
      return;
      label228: if (i1 == 6)
        i1 = 5;
    }
  }

  private void v()
  {
  }

  private void w()
  {
    com.intowow.sdk.a.j localj = this.g.F();
    if ((localj != null) && (localj.e != null))
      this.k.a(localj.e.c());
  }

  public SplashAD a(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean2) && (Math.abs(System.currentTimeMillis() - this.g.q()) < f()))
      return null;
    if (c(paramString))
      return null;
    try
    {
      String str = a(paramString, 1);
      ADProfile localADProfile = this.i.a(paramString, paramString, 1, null);
      if (localADProfile == null)
        return null;
      paramContext = new SplashAD(paramContext, SplashAD.ActivityType.SINGLE_OFFER);
      try
      {
        paramContext.setup(localADProfile, paramString, str, paramBoolean1);
        return paramContext;
      }
      catch (Exception paramString)
      {
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public b.b a(String paramString)
  {
    com.intowow.sdk.model.g localg = b(paramString);
    if ((this.g != null) && (this.g.F() != null))
      return this.g.F().a(localg, paramString);
    return null;
  }

  public String a(String paramString, int paramInt)
  {
    String str = System.currentTimeMillis();
    str = str.substring(str.length() - 8);
    if ((this.g == null) || (this.g.f() == null) || (!this.g.v()))
      return str;
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.r.ordinal());
    localBundle.putString("placement", paramString);
    localBundle.putInt("place", paramInt);
    localBundle.putString("token", str);
    this.h.a(localBundle);
    return str;
  }

  public List<h.b> a()
  {
    return Arrays.asList(this.u);
  }

  public void a(final int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, final com.intowow.sdk.h.j paramj, final TriggerResponse paramTriggerResponse)
  {
    this.k.a(paramInt1, paramInt2, paramString1, paramString2, paramString3, paramString4, paramj);
    if ((this.s != null) && ((paramj == com.intowow.sdk.h.j.h) || (paramj == com.intowow.sdk.h.j.i)))
      this.r.execute(new Runnable()
      {
        public void run()
        {
          while (true)
          {
            try
            {
              if (paramj == com.intowow.sdk.h.j.h)
              {
                if ((e.f(e.this) == null) || (e.f(e.this).get() == null))
                  break;
                ((ADEventListener)e.f(e.this).get()).onAdImpression(paramInt1);
                return;
              }
              if ((paramj != com.intowow.sdk.h.j.i) || (e.f(e.this) == null) || (e.f(e.this).get() == null))
                break;
              ADEventListener localADEventListener = (ADEventListener)e.f(e.this).get();
              String str2 = paramInt1;
              if (paramTriggerResponse != null)
              {
                String str1 = paramTriggerResponse.a();
                localADEventListener.onAdClick(str2, str1);
                return;
              }
            }
            catch (Exception localException)
            {
              return;
            }
            Object localObject = null;
          }
        }
      });
  }

  public void a(int paramInt, long paramLong)
  {
    this.e.a(paramInt, String.valueOf(paramLong));
  }

  public void a(long paramLong)
  {
    if (this.g != null)
      this.g.a(paramLong);
  }

  public void a(Activity paramActivity)
  {
    if (paramActivity == null)
      return;
    new com.intowow.sdk.b.a.b(com.intowow.sdk.a.e.a, paramActivity, this.h, this.g).a();
  }

  public void a(Bundle paramBundle)
  {
    h.b localb = h.b.values()[paramBundle.getInt("type")];
    if (localb == h.b.d)
      v();
    do
    {
      return;
      if (localb == h.b.e)
      {
        a(paramBundle.getInt("duration"));
        return;
      }
    }
    while (localb != h.b.f);
    w();
  }

  public void a(ADEventListener paramADEventListener)
  {
    this.s = new WeakReference(paramADEventListener);
  }

  public void a(SplashAD.SplashAdListener paramSplashAdListener)
  {
    this.m = paramSplashAdListener;
  }

  public void a(SplashAdActivity.IAdActivity paramIAdActivity)
  {
    this.t = paramIAdActivity;
  }

  public void a(com.intowow.sdk.a.j paramj)
  {
    this.e.a(paramj);
  }

  public void a(String paramString, long paramLong)
  {
    this.f.a(paramString, paramLong);
  }

  public void a(String paramString, g.a parama, int paramInt1, int paramInt2)
  {
    paramString = com.intowow.sdk.h.a.a(paramString, parama, paramInt1, paramInt2);
    this.h.a(paramString);
  }

  public void a(String paramString, JSONObject paramJSONObject)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.v.ordinal());
    localBundle.putString("event_type", paramString);
    localBundle.putString("event_props", paramJSONObject.toString());
    this.h.a(localBundle);
  }

  public void a(boolean paramBoolean)
  {
    this.g.b(paramBoolean);
  }

  public com.intowow.sdk.model.g b(String paramString)
  {
    return this.g.f(paramString);
  }

  public void b()
  {
    this.n.a();
  }

  public void b(SplashAD.SplashAdListener paramSplashAdListener)
  {
    if ((this.m == null) || (paramSplashAdListener == null));
    while (this.m != paramSplashAdListener)
      return;
    this.m = null;
  }

  public void c()
  {
    this.n.b();
  }

  public boolean c(String paramString)
  {
    return this.f.a(paramString);
  }

  public boolean d()
  {
    if ((this.g == null) || (this.g.F() == null))
      return false;
    if (this.g.N())
      return true;
    return this.g.F().b();
  }

  public long e()
  {
    if ((this.g == null) || (this.g.F() == null))
      return 3600000L;
    if (this.g.N())
      return 0L;
    return this.g.F().g();
  }

  public long f()
  {
    if ((this.g == null) || (this.g.F() == null))
      return 300000L;
    if (this.g.N())
      return 0L;
    return this.g.F().h();
  }

  public boolean g()
  {
    return Math.abs(System.currentTimeMillis() - this.g.q()) < e();
  }

  public void h()
  {
    if (this.t != null)
      this.t.closeActivity();
  }

  public void i()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", h.b.j.ordinal());
    this.h.a(localBundle);
  }

  public com.intowow.sdk.i.a j()
  {
    return this.c;
  }

  public g k()
  {
    return this.d;
  }

  public SplashAD.SplashAdListener l()
  {
    return this.m;
  }

  public boolean m()
  {
    return this.q;
  }

  public void n()
  {
    this.q = true;
  }

  public com.intowow.sdk.model.k o()
  {
    return com.intowow.sdk.model.k.valueOf(this.g.t());
  }

  public void p()
  {
    this.g.u();
  }

  public boolean q()
  {
    return this.g.N();
  }

  public String r()
  {
    return this.e.a().d();
  }

  public com.intowow.sdk.a.j s()
  {
    return this.e.a();
  }

  public k.a t()
  {
    if (this.j == null)
      return null;
    return this.j.c();
  }

  private class a
    implements h.a
  {
    private a()
    {
    }

    private void a(String paramString)
    {
      if (e.c(e.this) != null)
        e.c(e.this).a(paramString);
    }

    public List<h.b> a()
    {
      return Arrays.asList(h.b.values());
    }

    public void a(Bundle paramBundle)
    {
      h.b localb = h.b.values()[paramBundle.getInt("type")];
      int i;
      int j;
      int k;
      String str1;
      switch (b()[localb.ordinal()])
      {
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 13:
      case 19:
      default:
        a(String.format("Receive message[%s]", new Object[] { localb }));
      case 11:
        return;
      case 5:
        a(String.format("SESSION_END : duration(%d)", new Object[] { Integer.valueOf(paramBundle.getInt("duration")) }));
        return;
      case 17:
        i = paramBundle.getInt("adid");
        j = paramBundle.getInt("place");
        k = paramBundle.getInt("ad_version");
        int m = paramBundle.getInt("creative_id");
        str1 = paramBundle.getString("placement");
        paramBundle = (TriggerResponse)paramBundle.getParcelable("response");
        a(String.format("%s : adid(%d), adVersion(%d), creativeId(%d), place(%d), placement(%s), action(%s) value(%s)", new Object[] { localb, Integer.valueOf(i), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(j), str1, paramBundle.c(), paramBundle.b() }));
        return;
      case 20:
        i = paramBundle.getInt("adid");
        j = paramBundle.getInt("duration");
        k = paramBundle.getInt("percentage");
        a(String.format("%s : adid(%d), adVersion(%d), creativeId(%d), duration(%d), percentage(%d)", new Object[] { localb, Integer.valueOf(i), Integer.valueOf(paramBundle.getInt("ad_version")), Integer.valueOf(paramBundle.getInt("creative_id")), Integer.valueOf(j), Integer.valueOf(k) }));
        return;
      case 18:
        a(String.format("%s : placement(%s), place(%d)", new Object[] { localb, paramBundle.getString("placement"), Integer.valueOf(paramBundle.getInt("place")) }));
        return;
      case 15:
        a(String.format("%s : placement(%s)", new Object[] { localb, paramBundle.getString("placement") }));
        return;
      case 22:
        str1 = paramBundle.getString("event_props");
        String str2 = paramBundle.getString("event_type");
        paramBundle = str1;
        if (str1 == null)
          paramBundle = "NULL";
        a(String.format("%s : type(%s), props(%s)", new Object[] { localb, str2, paramBundle }));
        return;
      case 21:
        a(String.format("%s : type(%s)", new Object[] { localb, com.intowow.sdk.h.k.c(paramBundle.getInt("network_type")) }));
        return;
      case 16:
        a(String.format("%s : strategy(%s)", new Object[] { localb, paramBundle.getString("download_strategy") }));
        return;
      case 12:
        a(String.format("%s : ADID(%d)", new Object[] { localb, Integer.valueOf(paramBundle.getInt("adid")) }));
        return;
      case 14:
      }
      a(String.format("%s : DEBUG_URL(%s)", new Object[] { localb, paramBundle.getString("snapshot_url") }));
    }
  }

  private class b extends BroadcastReceiver
  {
    public b()
    {
      try
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        e.a(e.this).registerReceiver(this, localIntentFilter);
        return;
      }
      catch (Exception this$1)
      {
      }
    }

    public void a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE"))
        e.b(e.this);
    }
  }

  static abstract interface c
  {
    public abstract boolean a(ADProfile paramADProfile);
  }

  public class d
  {
    private SparseArray<String> b = null;
    private ConcurrentHashMap<String, Object> c = null;
    private com.intowow.sdk.a.j d = null;

    public d()
    {
    }

    private long b(String paramString)
    {
      paramString = this.c.get(paramString);
      if (paramString != null)
        return Long.parseLong(paramString.toString());
      return -1L;
    }

    public long a(String paramString)
    {
      try
      {
        long l2 = b(paramString);
        long l1 = l2;
        if (l2 == -1L)
          if (e.d(e.this) == null)
            break label56;
        label56: for (l1 = e.d(e.this).i(paramString); ; l1 = 0L)
        {
          a(paramString, Long.valueOf(l1));
          return l1;
        }
      }
      finally
      {
      }
      throw paramString;
    }

    public com.intowow.sdk.a.j a()
    {
      try
      {
        if ((this.d == null) && (e.d(e.this) != null))
          this.d = e.d(e.this).F();
        com.intowow.sdk.a.j localj = this.d;
        return localj;
      }
      finally
      {
      }
    }

    public void a(int paramInt, String paramString)
    {
      try
      {
        this.b.put(paramInt, paramString);
        return;
      }
      finally
      {
        paramString = finally;
      }
      throw paramString;
    }

    public void a(com.intowow.sdk.a.j paramj)
    {
      try
      {
        this.d = paramj;
        return;
      }
      finally
      {
        paramj = finally;
      }
      throw paramj;
    }

    public void a(String paramString, Object paramObject)
    {
      try
      {
        this.c.put(paramString, paramObject);
        return;
      }
      finally
      {
        paramString = finally;
      }
      throw paramString;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.e
 * JD-Core Version:    0.6.2
 */