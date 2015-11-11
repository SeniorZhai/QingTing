package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.Gender;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class q
{
  private List<p> a = new ArrayList();
  private ah b = null;
  private ai c = null;
  private ak d = null;
  private ax e = null;
  private Context f = null;

  public q(Context paramContext)
  {
    this.f = paramContext;
  }

  private void a(Context paramContext)
  {
    try
    {
      this.c.a(AnalyticsConfig.getAppkey(paramContext));
      this.c.e(AnalyticsConfig.getChannel(paramContext));
      if ((AnalyticsConfig.mWrapperType != null) && (AnalyticsConfig.mWrapperVersion != null))
      {
        this.c.f(AnalyticsConfig.mWrapperType);
        this.c.g(AnalyticsConfig.mWrapperVersion);
      }
      this.c.c(bi.u(paramContext));
      this.c.a(bc.a);
      this.c.d("5.2.4");
      this.c.b(bi.d(paramContext));
      this.c.a(Integer.parseInt(bi.c(paramContext)));
      if (AnalyticsConfig.mVerticalType == 1)
      {
        this.c.c(AnalyticsConfig.mVerticalType);
        this.c.d("5.2.4.1");
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void b(Context paramContext)
  {
    try
    {
      this.d.f(bi.a());
      this.d.a(bi.f(paramContext));
      this.d.b(bi.g(paramContext));
      this.d.c(bi.p(paramContext));
      this.d.e(Build.MODEL);
      this.d.g("Android");
      this.d.h(Build.VERSION.RELEASE);
      paramContext = bi.r(paramContext);
      if (paramContext != null)
        this.d.a(new ba(paramContext[1], paramContext[0]));
      if ((AnalyticsConfig.GPU_RENDERER != null) && (AnalyticsConfig.GPU_VENDER != null));
      this.d.i(Build.BOARD);
      this.d.j(Build.BRAND);
      this.d.a(Build.TIME);
      this.d.k(Build.MANUFACTURER);
      this.d.l(Build.ID);
      this.d.m(Build.DEVICE);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void c(Context paramContext)
  {
    while (true)
    {
      try
      {
        String[] arrayOfString = bi.j(paramContext);
        if ("Wi-Fi".equals(arrayOfString[0]))
        {
          this.e.a(ag.c);
          if (!"".equals(arrayOfString[1]))
            this.e.e(arrayOfString[1]);
          this.e.c(bi.s(paramContext));
          arrayOfString = bi.n(paramContext);
          this.e.b(arrayOfString[0]);
          this.e.a(arrayOfString[1]);
          this.e.a(bi.m(paramContext));
          if ((AnalyticsConfig.sAge == 0) && (AnalyticsConfig.sGender == null) && (AnalyticsConfig.sId == null) && (AnalyticsConfig.sSource == null))
            break;
          paramContext = new bg();
          paramContext.a(AnalyticsConfig.sAge);
          paramContext.a(Gender.transGender(AnalyticsConfig.sGender));
          paramContext.a(AnalyticsConfig.sId);
          paramContext.b(AnalyticsConfig.sSource);
          this.e.a(paramContext);
          return;
        }
        if ("2G/3G".equals(arrayOfString[0]))
        {
          this.e.a(ag.b);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      this.e.a(ag.a);
    }
  }

  private String i()
  {
    return u.a(this.f).getString("session_id", null);
  }

  public Context a()
  {
    return this.f;
  }

  public void a(ah paramah)
  {
    try
    {
      this.b = paramah;
      return;
    }
    finally
    {
      paramah = finally;
    }
    throw paramah;
  }

  public void a(bf parambf)
  {
    String str = i();
    if (str == null)
      return;
    try
    {
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
        ((p)localIterator.next()).a(parambf, str);
    }
    finally
    {
    }
    this.a.clear();
    if (this.b != null)
    {
      parambf.a(this.b);
      this.b = null;
    }
    parambf.a(c());
    parambf.a(d());
    parambf.a(e());
    parambf.a(h());
    parambf.a(f());
    parambf.a(g());
  }

  public void a(p paramp)
  {
    try
    {
      this.a.add(paramp);
      return;
    }
    finally
    {
      paramp = finally;
    }
    throw paramp;
  }

  protected boolean a(int paramInt)
  {
    return true;
  }

  public int b()
  {
    try
    {
      int j = this.a.size();
      ah localah = this.b;
      int i = j;
      if (localah != null)
        i = j + 1;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public ai c()
  {
    try
    {
      if (this.c == null)
      {
        this.c = new ai();
        a(this.f);
      }
      ai localai = this.c;
      return localai;
    }
    finally
    {
    }
  }

  public ak d()
  {
    try
    {
      if (this.d == null)
      {
        this.d = new ak();
        b(this.f);
      }
      ak localak = this.d;
      return localak;
    }
    finally
    {
    }
  }

  public ax e()
  {
    try
    {
      if (this.e == null)
      {
        this.e = new ax();
        c(this.f);
      }
      ax localax = this.e;
      return localax;
    }
    finally
    {
    }
  }

  public at f()
  {
    try
    {
      at localat = h.b(this.f).a();
      return localat;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public as g()
  {
    try
    {
      as localas = h.a(this.f).b();
      return localas;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public aj h()
  {
    try
    {
      aj localaj = w.a(this.f);
      return localaj;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new aj();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.q
 * JD-Core Version:    0.6.2
 */