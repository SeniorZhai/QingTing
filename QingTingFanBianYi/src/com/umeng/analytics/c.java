package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.onlineconfig.UmengOnlineConfigureListener;
import com.umeng.analytics.onlineconfig.a;
import java.util.HashMap;
import java.util.Map;
import u.aly.ac;
import u.aly.bj;
import u.aly.k;
import u.aly.m;
import u.aly.n;
import u.aly.t;
import u.aly.u;
import u.aly.v;
import u.aly.z;

public class c
  implements t
{
  private final a a = new a();
  private Context b = null;
  private b c;
  private m d = new m();
  private z e = new z();
  private v f = new v();
  private n g;
  private k h;
  private boolean i = false;

  c()
  {
    this.d.a(this);
  }

  private void f(Context paramContext)
  {
    if (!this.i)
    {
      this.b = paramContext.getApplicationContext();
      this.g = new n(this.b);
      this.h = k.a(this.b);
      this.i = true;
    }
  }

  private void g(Context paramContext)
  {
    this.f.c(paramContext);
    if (this.c != null)
      this.c.a();
  }

  private void h(Context paramContext)
  {
    this.f.d(paramContext);
    this.e.a(paramContext);
    if (this.c != null)
      this.c.b();
    this.h.b();
  }

  public void a(int paramInt)
  {
    AnalyticsConfig.mVerticalType = paramInt;
  }

  void a(Context paramContext)
  {
    if (paramContext == null)
    {
      bj.b("MobclickAgent", "unexpected null context in onResume");
      return;
    }
    this.a.a(paramContext);
    try
    {
      k.a(paramContext).a(this.a);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  void a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    if (paramContext == null)
    {
      bj.b("MobclickAgent", "unexpected null context in reportError");
      return;
    }
    try
    {
      if (!this.i)
        f(paramContext);
      this.h.a(new ac(paramString).a(false));
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void a(Context paramContext, final String paramString1, final String paramString2)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      d.a(new e()
      {
        public void a()
        {
          c.a(c.this).a(paramString1, paramString2);
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  public void a(Context paramContext, String paramString1, String paramString2, long paramLong, int paramInt)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      this.g.a(paramString1, paramString2, paramLong, paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  public void a(Context paramContext, String paramString, HashMap<String, Object> paramHashMap)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      this.g.a(paramString, paramHashMap);
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void a(Context paramContext, final String paramString1, final HashMap<String, Object> paramHashMap, final String paramString2)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      d.a(new e()
      {
        public void a()
        {
          c.a(c.this).a(paramString1, paramHashMap, paramString2);
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void a(Context paramContext, String paramString, Map<String, Object> paramMap, long paramLong)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      this.g.a(paramString, paramMap, paramLong);
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void a(Context paramContext, Throwable paramThrowable)
  {
    if ((paramContext == null) || (paramThrowable == null))
      return;
    try
    {
      if (!this.i)
        f(paramContext);
      this.h.a(new ac(paramThrowable).a(false));
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  public void a(b paramb)
  {
    this.c = paramb;
  }

  void a(UmengOnlineConfigureListener paramUmengOnlineConfigureListener)
  {
    this.a.a(paramUmengOnlineConfigureListener);
  }

  void a(String paramString)
  {
    if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN);
    try
    {
      this.e.a(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void a(String paramString1, String paramString2)
  {
    AnalyticsConfig.mWrapperType = paramString1;
    AnalyticsConfig.mWrapperVersion = paramString2;
  }

  public void a(Throwable paramThrowable)
  {
    try
    {
      this.e.a();
      if (this.b != null)
      {
        if ((paramThrowable != null) && (this.h != null))
          this.h.b(new ac(paramThrowable));
        h(this.b);
        u.a(this.b).edit().commit();
      }
      d.a();
      return;
    }
    catch (Exception paramThrowable)
    {
      bj.a("MobclickAgent", "Exception in onAppCrash", paramThrowable);
    }
  }

  void b(final Context paramContext)
  {
    if (paramContext == null)
    {
      bj.b("MobclickAgent", "unexpected null context in onResume");
      return;
    }
    if (AnalyticsConfig.ACTIVITY_DURATION_OPEN)
      this.e.a(paramContext.getClass().getName());
    try
    {
      if (!this.i)
        f(paramContext);
      d.a(new e()
      {
        public void a()
        {
          c.a(c.this, paramContext.getApplicationContext());
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "Exception occurred in Mobclick.onResume(). ", paramContext);
    }
  }

  void b(Context paramContext, final String paramString1, final String paramString2)
  {
    try
    {
      d.a(new e()
      {
        public void a()
        {
          c.a(c.this).b(paramString1, paramString2);
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void b(String paramString)
  {
    if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN);
    try
    {
      this.e.b(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  void c(final Context paramContext)
  {
    if (paramContext == null)
    {
      bj.b("MobclickAgent", "unexpected null context in onPause");
      return;
    }
    if (AnalyticsConfig.ACTIVITY_DURATION_OPEN)
      this.e.b(paramContext.getClass().getName());
    try
    {
      if (!this.i)
        f(paramContext);
      d.a(new e()
      {
        public void a()
        {
          c.b(c.this, paramContext.getApplicationContext());
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "Exception occurred in Mobclick.onRause(). ", paramContext);
    }
  }

  void c(Context paramContext, final String paramString1, final String paramString2)
  {
    try
    {
      d.a(new e()
      {
        public void a()
        {
          c.a(c.this).c(paramString1, paramString2);
        }
      });
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void d(Context paramContext)
  {
    try
    {
      if (!this.i)
        f(paramContext);
      this.h.a();
      return;
    }
    catch (Exception paramContext)
    {
      bj.b("MobclickAgent", "", paramContext);
    }
  }

  void e(Context paramContext)
  {
    try
    {
      this.e.a();
      h(paramContext);
      u.a(paramContext).edit().commit();
      d.a();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.c
 * JD-Core Version:    0.6.2
 */