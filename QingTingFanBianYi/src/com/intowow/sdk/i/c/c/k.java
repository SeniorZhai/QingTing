package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.a.b.b;
import com.intowow.sdk.f.d.a;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.l;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.i;
import com.intowow.sdk.model.ADProfile.j;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class k extends a
{
  protected Surface A = null;
  protected com.intowow.sdk.i.c.a B = null;
  protected com.intowow.sdk.i.c.a C = null;
  protected com.intowow.sdk.i.c.a D = null;
  protected com.intowow.sdk.i.c.a.c E = null;
  protected com.intowow.sdk.i.c.a.a F = null;
  protected ImageButton G = null;
  protected ImageButton H = null;
  protected ImageButton I = null;
  protected com.intowow.sdk.i.c.b.a J = null;
  protected boolean K = false;
  protected boolean L = true;
  protected boolean M = false;
  protected boolean N = false;
  protected boolean O = false;
  protected boolean P = false;
  protected boolean Q = false;
  protected Runnable R = new Runnable()
  {
    public void run()
    {
      if ((k.this.x == null) || (k.this.A == null))
        return;
      if (k.this.x.a(k.this.m))
        k.this.x.b(k.this.m);
      ADProfile.q localq = (ADProfile.q)k.this.c.a(ADProfile.d.B);
      com.intowow.sdk.b.g localg = k.this.x;
      String str1 = k.this.m;
      String str2 = k.this.q;
      ADProfile localADProfile = k.this.c;
      int i = k.this.n;
      Surface localSurface = k.this.A;
      if (k.this.L);
      for (float f = 0.0F; ; f = 1.0F)
      {
        localg.a(str1, str2, localADProfile, i, localSurface, f, com.intowow.sdk.j.k.a(k.this.a).a() + localq.e(), k.this.k(), k.a(k.this), k.b(k.this), k.c(k.this));
        return;
      }
    }
  };
  protected Runnable S = new Runnable()
  {
    public void run()
    {
      int i = k.this.x.a();
      Iterator localIterator = k.this.u.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (k.this.j != null)
            k.this.j.postDelayed(k.this.S, 15L);
          return;
        }
        ((b)localIterator.next()).a(i);
      }
    }
  };
  private MediaPlayer.OnPreparedListener T = new MediaPlayer.OnPreparedListener()
  {
    public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
    {
      k.d(k.this);
    }
  };
  private MediaPlayer.OnCompletionListener U = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
    {
      k.a(k.this, paramAnonymousMediaPlayer);
    }
  };
  private MediaPlayer.OnErrorListener V = new MediaPlayer.OnErrorListener()
  {
    public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      return k.this.a(paramAnonymousMediaPlayer, paramAnonymousInt1, paramAnonymousInt2);
    }
  };
  private Runnable W = new Runnable()
  {
    public void run()
    {
      if ((k.this.x == null) || (k.this.j == null) || (!k.this.P))
        return;
      if ((k.this.x.a(k.this.m)) && (k.this.x.a() > 100))
      {
        k.this.n();
        return;
      }
      k.this.j.postDelayed(k.e(k.this), 33L);
    }
  };
  protected com.intowow.sdk.b.g x = null;
  protected b y = null;
  protected com.intowow.sdk.i.c.b z = null;

  public k(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.x = com.intowow.sdk.b.e.a(paramActivity).k();
    this.Q = D();
    this.u = new ArrayList();
  }

  private void C()
  {
    if ((this.j != null) && (this.P))
      this.j.postDelayed(this.W, 33L);
  }

  private boolean D()
  {
    if (this.b == j.d);
    com.intowow.sdk.f.c.a locala;
    do
    {
      do
      {
        do
        {
          return true;
          if (!ADProfile.i.a(this.c.g()))
            break;
        }
        while (ADProfile.i.c(this.c.g()));
        locala = this.g.a();
      }
      while ((locala != com.intowow.sdk.f.c.a.d) && (locala != com.intowow.sdk.f.c.a.c));
      return false;
      locala = this.f.a();
    }
    while ((locala != com.intowow.sdk.f.c.a.d) && (locala != com.intowow.sdk.f.c.a.c));
    return false;
  }

  private void a(MediaPlayer paramMediaPlayer)
  {
    m();
  }

  protected com.intowow.sdk.i.c.a.c A()
  {
    int i;
    Object localObject;
    label52: RelativeLayout.LayoutParams localLayoutParams;
    if (ADProfile.i.e == this.c.g())
    {
      i = 1;
      if (!ADProfile.i.a(this.c.g()))
        break label182;
      if (i == 0)
        break label123;
      localObject = new com.intowow.sdk.i.c.a.f(this.b, this.i, this.h);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, ((com.intowow.sdk.i.c.a.b)localObject).a());
      localLayoutParams.addRule(10);
      localLayoutParams.topMargin = ((com.intowow.sdk.i.c.a.b)localObject).c();
      if (i != 0)
        break label205;
      localLayoutParams.addRule(9);
      localLayoutParams.leftMargin = ((com.intowow.sdk.i.c.a.b)localObject).b();
    }
    while (true)
    {
      localObject = com.intowow.sdk.i.c.a.c.a(this.a, (com.intowow.sdk.i.c.a.b)localObject, localLayoutParams);
      ((com.intowow.sdk.i.c.a.c)localObject).setOnClickListener(x());
      return localObject;
      i = 0;
      break;
      label123: if (ADProfile.i.c(this.c.g()))
      {
        localObject = new com.intowow.sdk.i.c.a.d(this.b, this.i, this.h);
        break label52;
      }
      localObject = new com.intowow.sdk.i.c.a.e(this.b, this.i, this.g);
      break label52;
      label182: localObject = new com.intowow.sdk.i.c.a.g(this.b, this.i, this.f);
      break label52;
      label205: localLayoutParams.addRule(11);
      localLayoutParams.rightMargin = ((com.intowow.sdk.i.c.a.b)localObject).b();
    }
  }

  protected void B()
  {
    a(new b[] { this.E, this.J, this.F, this.y, this.s });
  }

  protected com.intowow.sdk.i.c.a a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Object localObject = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    ((RelativeLayout.LayoutParams)localObject).leftMargin = paramInt3;
    ((RelativeLayout.LayoutParams)localObject).topMargin = paramInt4;
    ((RelativeLayout.LayoutParams)localObject).addRule(1, paramInt5);
    localObject = a(paramInt1, paramInt2, (RelativeLayout.LayoutParams)localObject);
    ((com.intowow.sdk.i.c.a)localObject).setBackgroundDrawable(this.i.b("wifi_tag.png"));
    com.a.c.a.a((View)localObject, 0.0F);
    return localObject;
  }

  protected com.intowow.sdk.i.c.b.c a(String paramString, View[] paramArrayOfView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    com.intowow.sdk.i.c.b.c localc = new com.intowow.sdk.i.c.b.c(this.a);
    localc.f = paramArrayOfView;
    localc.setBackgroundDrawable(this.i.b("bg_label.png"));
    localc.setTextColor(-1);
    localc.setGravity(16);
    localc.setText(paramString);
    localc.setTextSize(0, paramInt2);
    localc.setPadding(paramInt3, paramInt3, paramInt4, paramInt3);
    localc.setLayoutParams(new RelativeLayout.LayoutParams(paramInt1, -2));
    if (paramInt1 == -2)
      localc.setSingleLine(true);
    return localc;
  }

  protected com.intowow.sdk.i.c.b a(int paramInt1, int paramInt2)
  {
    com.intowow.sdk.i.c.b localb = new com.intowow.sdk.i.c.b(this.a, paramInt1, paramInt2);
    localb.setSurfaceTextureListener(new TextureView.SurfaceTextureListener()
    {
      public void onSurfaceTextureAvailable(SurfaceTexture paramAnonymousSurfaceTexture, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        k.this.A = new Surface(paramAnonymousSurfaceTexture);
        k.this.K = true;
        if ((!k.this.M) && (k.this.P))
          k.this.p();
      }

      public boolean onSurfaceTextureDestroyed(SurfaceTexture paramAnonymousSurfaceTexture)
      {
        return false;
      }

      public void onSurfaceTextureSizeChanged(SurfaceTexture paramAnonymousSurfaceTexture, int paramAnonymousInt1, int paramAnonymousInt2)
      {
      }

      public void onSurfaceTextureUpdated(SurfaceTexture paramAnonymousSurfaceTexture)
      {
      }
    });
    localb.setOnClickListener(x());
    return localb;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    if (this.r != null)
      this.t = this.r.a(ADProfile.j.a);
    if (this.c.b(ADProfile.d.t))
    {
      this.C = i();
      a(ADProfile.d.t, this.C);
    }
    this.E = A();
    this.E.setId(5000);
    if (this.c.g() == ADProfile.i.e);
    do
    {
      do
      {
        return;
        this.z = a(a(), b());
        this.z.setLayoutParams(h());
        if (j())
        {
          this.B = i();
          this.B.setBackgroundColor(-16777216);
          if (ADProfile.i.a(this.c.g()))
            break;
          this.B.getBackground().setAlpha(50);
          this.B.setOnClickListener(y());
        }
        this.F = z();
      }
      while (!ADProfile.i.a(this.c.g()));
      paramRelativeLayout = com.intowow.sdk.b.e.a(this.a).r();
    }
    while (l.a(paramRelativeLayout));
    int i2;
    int j;
    int i;
    int m;
    int n;
    int i4;
    int i3;
    int i1;
    int k;
    if (!ADProfile.i.c(this.c.g()))
    {
      i2 = this.g.a(f.a.aa);
      j = this.g.a(f.a.W);
      i = this.g.a(f.a.X);
      m = this.g.a(f.a.Y);
      n = this.g.a(f.a.Z);
      i4 = this.g.a(f.a.V);
      i3 = this.g.a(f.a.T);
      i1 = this.g.a(f.a.U);
      k = i;
    }
    while (true)
    {
      this.D = a(j, k, m, n, 5000);
      com.a.c.a.a(this.E, 0.0F);
      this.s = a(paramRelativeLayout, new View[] { this.D, this.E }, i2, i4, i3, i1);
      return;
      this.B.getBackground().setAlpha(180);
      this.H = new ImageButton(this.a);
      this.H.setId(987654);
      this.H.setLayoutParams(l());
      this.H.setBackgroundDrawable(this.i.b("replay_nm.png"));
      this.H.setOnTouchListener(n.a(this.i.b("replay_at.png"), this.i.b("replay_nm.png")));
      this.H.setOnClickListener(y());
      s();
      break;
      j = this.h.a(e.a.Y);
      k = this.h.a(e.a.Z);
      m = this.h.a(e.a.aa);
      n = this.h.a(e.a.ab);
      i = this.h.a(e.a.X);
      i3 = this.h.a(e.a.V);
      i1 = this.h.a(e.a.W);
      i2 = -2;
      i4 = i;
    }
  }

  protected void a(b[] paramArrayOfb)
  {
    int j = paramArrayOfb.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      b localb = paramArrayOfb[i];
      if (localb != null)
        this.u.add(localb);
      i += 1;
    }
  }

  protected boolean a(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (this.O)
      o();
    return true;
  }

  public boolean c()
  {
    boolean bool2 = true;
    boolean bool1;
    if (!super.c())
      bool1 = false;
    do
    {
      do
      {
        do
        {
          return bool1;
          this.P = true;
          bool1 = bool2;
        }
        while (!this.K);
        t();
        bool1 = bool2;
      }
      while (this.x.a(this.m));
      bool1 = bool2;
    }
    while (this.M);
    p();
    return true;
  }

  public boolean d()
  {
    if (!super.d())
      return false;
    v();
    if (this.j != null)
    {
      this.j.removeCallbacks(this.R);
      this.j.removeCallbacks(this.W);
    }
    if (this.P)
      q();
    this.P = false;
    return true;
  }

  public boolean e()
  {
    if (!super.e())
      return false;
    Iterator localIterator;
    if ((this.u != null) && (this.u.size() > 0))
      localIterator = this.u.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return true;
      ((b)localIterator.next()).e();
    }
  }

  public boolean f()
  {
    if (!super.f())
      return false;
    Iterator localIterator;
    if ((this.u != null) && (this.u.size() > 0))
      localIterator = this.u.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return true;
      ((b)localIterator.next()).f();
    }
  }

  protected abstract RelativeLayout.LayoutParams h();

  protected com.intowow.sdk.i.c.a i()
  {
    return a(a(), b(), h());
  }

  protected boolean j()
  {
    return false;
  }

  protected boolean k()
  {
    return false;
  }

  protected RelativeLayout.LayoutParams l()
  {
    return null;
  }

  protected void m()
  {
    this.M = true;
    o();
  }

  protected void n()
  {
    this.O = true;
    if (this.j != null)
      this.j.post(this.S);
    if ((this.Q) && (this.J != null))
      this.J.setVisibility(0);
    if (this.D != null)
      this.D.setVisibility(0);
    s();
  }

  protected void o()
  {
    this.O = false;
    Iterator localIterator = this.u.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (this.j != null)
        {
          this.j.removeCallbacks(this.W);
          this.j.removeCallbacks(this.S);
        }
        if ((this.Q) && (this.J != null) && (!ADProfile.i.a(this.c.g())))
          this.J.setVisibility(8);
        return;
      }
      ((b)localIterator.next()).a();
    }
  }

  protected void p()
  {
    this.M = false;
    this.R.run();
  }

  protected void q()
  {
    if (this.x != null)
    {
      this.x.b(this.m);
      o();
    }
  }

  protected void r()
  {
    if (this.G != null)
      this.G.setVisibility(0);
    if (this.B != null)
      this.B.setVisibility(0);
    if (this.H != null)
      this.H.setVisibility(0);
  }

  protected void s()
  {
    if (this.G != null)
      this.G.setVisibility(8);
    if (this.B != null)
      this.B.setVisibility(8);
    if (this.H != null)
      this.H.setVisibility(8);
  }

  protected void t()
  {
    if (this.C != null)
      this.C.setVisibility(0);
  }

  protected void u()
  {
    if (this.C != null)
      this.C.setVisibility(8);
  }

  protected void v()
  {
    this.L = true;
    if (this.E != null)
      this.E.b();
    if ((this.x != null) && (this.x.a(this.m)))
      this.x.a(this.L);
  }

  protected void w()
  {
    this.L = false;
    if (this.E != null)
      this.E.c();
    if ((this.x != null) && (this.x.a(this.m)))
      this.x.a(this.L);
  }

  protected abstract View.OnClickListener x();

  protected abstract View.OnClickListener y();

  protected com.intowow.sdk.i.c.a.a z()
  {
    Object localObject;
    if (ADProfile.i.a(this.c.g()))
      if (ADProfile.i.c(this.c.g()))
        localObject = new RelativeLayout.LayoutParams(this.h.a(e.a.u), this.h.a(e.a.u));
    while (true)
    {
      localObject = com.intowow.sdk.i.c.a.a.a(this.a, this.i, (RelativeLayout.LayoutParams)localObject);
      ((com.intowow.sdk.i.c.a.a)localObject).setOnClickListener(x());
      return localObject;
      localObject = new RelativeLayout.LayoutParams(this.g.a(f.a.y), this.g.a(f.a.y));
      continue;
      localObject = new RelativeLayout.LayoutParams(this.f.a(d.a.ba), this.f.a(d.a.ba));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.k
 * JD-Core Version:    0.6.2
 */