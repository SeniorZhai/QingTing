package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.a.b.a;
import com.intowow.sdk.a.b.b;
import com.intowow.sdk.b.g;
import com.intowow.sdk.f.d;
import com.intowow.sdk.f.d.a;
import com.intowow.sdk.f.f;
import com.intowow.sdk.i.c.b.c;
import com.intowow.sdk.j.k;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.j;
import com.intowow.sdk.model.ADProfile.k;
import com.intowow.sdk.model.j;
import java.util.List;

public abstract class a
{
  protected Activity a = null;
  protected j b = j.a;
  protected ADProfile c = null;
  protected c.a d = null;
  protected View.OnClickListener e = null;
  protected d f = null;
  protected f g = null;
  protected com.intowow.sdk.f.e h = null;
  protected com.intowow.sdk.f.a i = null;
  protected Handler j = null;
  protected boolean k = false;
  protected g l = null;
  protected String m = null;
  protected int n = 1;
  protected long o = -1L;
  protected String p = null;
  protected String q = null;
  protected b.b r = null;
  protected c s = null;
  protected b.a t = null;
  protected List<b> u = null;
  protected final int v = -2;
  protected final int w = -1;
  private boolean x = false;
  private boolean y = false;
  private boolean z = false;

  public a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    this.a = paramActivity;
    this.b = paramj;
    this.c = paramADProfile;
    this.d = parama;
    this.e = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (a.this.d != null)
          a.this.d.c();
      }
    };
    this.h = com.intowow.sdk.f.e.a(this.a);
    this.g = f.a(this.a);
    this.f = d.a(paramActivity);
    this.i = com.intowow.sdk.f.a.a(paramActivity);
    this.j = new Handler();
    this.l = com.intowow.sdk.b.e.a(this.a).k();
    this.m = String.format("%s_%d_%d", new Object[] { paramj, Integer.valueOf(paramADProfile.e()), Long.valueOf(System.currentTimeMillis()) });
  }

  protected int a()
  {
    switch (g()[this.b.ordinal()])
    {
    case 3:
    default:
      return this.f.a(d.a.m);
    case 4:
      return this.f.a(d.a.u);
    case 2:
    }
    return this.f.a(d.a.d);
  }

  protected com.intowow.sdk.i.c.a a(int paramInt1, int paramInt2, RelativeLayout.LayoutParams paramLayoutParams)
  {
    com.intowow.sdk.i.c.a locala = new com.intowow.sdk.i.c.a(this.a, paramInt1, paramInt2);
    locala.setScaleType(ImageView.ScaleType.FIT_START);
    locala.setLayoutParams(paramLayoutParams);
    return locala;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    this.x = true;
  }

  public void a(b.b paramb)
  {
    this.r = paramb;
  }

  public void a(g paramg)
  {
    this.l = paramg;
  }

  protected void a(ADProfile.d paramd, ImageView paramImageView)
  {
    String str = ((ADProfile.k)this.c.a(paramd)).e();
    paramd = this.c.e() + "_" + str;
    str = k.a(this.a).a() + str;
    n.a(this.a, str, paramImageView, paramd, true);
  }

  protected void a(ADProfile.j paramj)
  {
    if (this.r != null)
    {
      this.t = this.r.a(paramj);
      if ((this.t != null) && (this.t.a == ADProfile.a.b))
      {
        this.o = this.t.b;
        if (this.o != -1L)
          this.u.add(com.intowow.sdk.i.c.b.b.a(this.o, this.d));
      }
    }
  }

  public void a(String paramString)
  {
    this.p = paramString;
  }

  protected int b()
  {
    switch (g()[this.b.ordinal()])
    {
    case 3:
    default:
      return this.f.a(d.a.n);
    case 4:
      return this.f.a(d.a.v);
    case 2:
    }
    return this.f.a(d.a.e);
  }

  public boolean c()
  {
    if (!this.x);
    do
    {
      return true;
      if (this.k)
        return false;
      this.k = true;
    }
    while (this.d == null);
    this.d.a();
    return true;
  }

  public boolean d()
  {
    if (!this.x);
    do
    {
      return true;
      if (!this.k)
        return false;
      this.k = false;
    }
    while (this.d == null);
    this.d.b();
    return true;
  }

  public boolean e()
  {
    boolean bool = true;
    if (this.y)
      bool = false;
    do
    {
      return bool;
      this.y = true;
      if ((!this.z) && (this.p != null))
      {
        this.z = true;
        com.intowow.sdk.b.e.a(this.a).a(this.p, System.currentTimeMillis());
      }
    }
    while (this.d == null);
    this.d.h();
    return true;
  }

  public boolean f()
  {
    if (!this.y)
      return false;
    this.y = false;
    if (this.d != null)
      this.d.i();
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.a
 * JD-Core Version:    0.6.2
 */