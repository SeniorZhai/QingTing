package com.intowow.sdk.b;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.intowow.sdk.g.c;
import com.intowow.sdk.g.f;
import com.intowow.sdk.h.i;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.n;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

public class k
  implements h.a
{
  private Context a = null;
  private a b = null;
  private h c = null;
  private com.intowow.sdk.e.b d = null;
  private com.intowow.sdk.f.b e = null;
  private j f = null;
  private d g = null;
  private i h = null;
  private com.intowow.sdk.d.a i = null;
  private b j = null;
  private SparseArray<b> k = null;
  private Handler l = null;
  private HandlerThread m = null;
  private c n = null;
  private com.intowow.sdk.g.e o = new com.intowow.sdk.g.e(this);
  private c p = new c(this);
  private com.intowow.sdk.g.b q = new com.intowow.sdk.g.b(this);
  private com.intowow.sdk.g.a r = new com.intowow.sdk.g.a(this);
  private com.intowow.sdk.g.d s = new com.intowow.sdk.g.d(this);
  private f t = new f(this);
  private final h.b[] u = { h.b.b, h.b.c, h.b.d, h.b.e, h.b.h, h.b.f, h.b.g, h.b.i, h.b.o, h.b.j, h.b.l, h.b.n, h.b.m, h.b.p, h.b.q, h.b.r, h.b.s, h.b.t, h.b.u, h.b.v };

  public k(Context paramContext, h paramh, com.intowow.sdk.f.b paramb)
  {
    this.a = paramContext;
    this.c = paramh;
    this.e = paramb;
    this.b = new a();
    this.g = new d();
    long l1 = 314572800L;
    long l2 = 52428800L;
    if (this.e.F() != null)
    {
      l1 = this.e.F().k();
      l2 = this.e.F().l();
    }
    this.d = new com.intowow.sdk.e.b(this.a, l1, l2);
    this.f = new j(this);
    this.h = new i(this);
    this.j = new b(this);
    this.m = new HandlerThread("SchedulerThread", 10);
    this.m.start();
    this.l = new Handler(this.m.getLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        k.b localb = (k.b)k.a(k.this).get(paramAnonymousMessage.what);
        if (localb != null)
          localb.a(paramAnonymousMessage);
      }
    };
    this.l.post(new Runnable()
    {
      public void run()
      {
        k.b(k.this).a();
      }
    });
    u();
  }

  private void u()
  {
    this.k = new SparseArray();
    this.k.put(h.b.b.ordinal(), this.o);
    this.k.put(h.b.c.ordinal(), this.o);
    this.k.put(h.b.l.ordinal(), this.o);
    this.k.put(h.b.n.ordinal(), this.o);
    this.k.put(h.b.m.ordinal(), this.o);
    this.k.put(h.b.d.ordinal(), this.q);
    this.k.put(h.b.e.ordinal(), this.q);
    this.k.put(h.b.j.ordinal(), this.q);
    this.k.put(h.b.h.ordinal(), this.p);
    this.k.put(h.b.f.ordinal(), this.p);
    this.k.put(h.b.g.ordinal(), this.p);
    this.k.put(h.b.i.ordinal(), this.p);
    this.k.put(h.b.o.ordinal(), this.p);
    this.k.put(h.b.q.ordinal(), this.r);
    this.k.put(h.b.r.ordinal(), this.r);
    this.k.put(h.b.s.ordinal(), this.r);
    this.k.put(h.b.t.ordinal(), this.r);
    this.k.put(h.b.u.ordinal(), this.s);
    this.k.put(h.b.p.ordinal(), this.s);
    this.k.put(h.b.v.ordinal(), this.t);
  }

  public int a(String paramString)
  {
    return this.r.a(paramString);
  }

  public List<h.b> a()
  {
    return Arrays.asList(this.u);
  }

  public void a(Bundle paramBundle)
  {
    Message localMessage = Message.obtain(this.l, paramBundle.getInt("type"));
    localMessage.setData(paramBundle);
    localMessage.sendToTarget();
  }

  public void a(c paramc)
  {
    this.n = paramc;
  }

  public void a(com.intowow.sdk.d.a parama)
  {
    this.i = parama;
    this.h.a(this.i);
  }

  public void a(ADProfile paramADProfile)
  {
    this.e.a(paramADProfile, ADProfile.n.b);
    JSONObject localJSONObject = com.intowow.sdk.h.a.a(paramADProfile);
    this.h.a(localJSONObject);
    com.intowow.sdk.j.j.a(this.e, this.c, paramADProfile);
    if (this.n != null)
      this.n.a(paramADProfile);
  }

  public Context b()
  {
    return this.a;
  }

  public a c()
  {
    return this.b;
  }

  public Handler d()
  {
    return this.l;
  }

  public h e()
  {
    return this.c;
  }

  public com.intowow.sdk.f.b f()
  {
    return this.e;
  }

  public d g()
  {
    return this.g;
  }

  public com.intowow.sdk.e.b h()
  {
    return this.d;
  }

  public b i()
  {
    return this.j;
  }

  public i j()
  {
    return this.h;
  }

  public int k()
  {
    if (this.e != null)
      return this.e.h();
    return -1;
  }

  public j l()
  {
    return this.f;
  }

  public com.intowow.sdk.d.a m()
  {
    return this.i;
  }

  public void n()
  {
    this.o.a();
  }

  public void o()
  {
    this.q.a();
  }

  public Map<String, Integer> p()
  {
    return this.r.a();
  }

  public com.intowow.sdk.a.j q()
  {
    if (this.e != null)
      return this.e.F();
    return null;
  }

  public ExecutorService r()
  {
    return this.r.b();
  }

  public Object s()
  {
    return this.r.c();
  }

  public void t()
  {
    try
    {
      com.intowow.sdk.triggerresponse.d locald = com.intowow.sdk.triggerresponse.e.a(this);
      com.intowow.sdk.triggerresponse.a.a(com.intowow.sdk.a.e.a, locald);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static class a
  {
    private int a = 0;
    private a b = a.c;
    private String c = null;
    private String d = null;

    public int a()
    {
      return this.a;
    }

    public void a(int paramInt)
    {
      this.a = paramInt;
    }

    public void a(a parama)
    {
      this.b = parama;
    }

    public void a(String paramString)
    {
      this.c = paramString;
    }

    public a b()
    {
      return this.b;
    }

    public void b(String paramString)
    {
      this.d = paramString;
    }

    public String c()
    {
      return this.c;
    }

    public String d()
    {
      return this.d;
    }

    public static enum a
    {
    }
  }

  public static abstract interface b
  {
    public abstract void a(Message paramMessage);
  }

  public static abstract interface c
  {
    public abstract void a(ADProfile paramADProfile);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.k
 * JD-Core Version:    0.6.2
 */