package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.a.a.a.a;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.l;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.j;

public class q extends m
{
  private int U = 0;
  private int V = 0;
  private int W = 0;
  private float X = 0.0F;
  private float Y = 0.0F;
  private float Z = 0.0F;
  private boolean aa = false;
  private boolean ab = false;
  private boolean ac = false;
  private RelativeLayout ad = null;
  private ImageView ae = null;
  private ImageButton af = null;
  private ImageButton ag = null;

  public q(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.Q = true;
    this.h = com.intowow.sdk.f.e.a(this.a);
  }

  private void I()
  {
    com.a.c.b.a(this.E).a(90.0F);
    int i = this.h.a(e.a.x);
    float f = Math.abs((this.h.a(e.a.y) - i) * 0.5F);
    com.a.c.b.a(this.af).a(90.0F).d(-f).b(f);
    f = Math.abs((this.h.a(e.a.z) - this.h.a(e.a.A)) * 0.5F);
    com.a.c.b.a(this.ag).a(90.0F).d(-f).b(-f);
    com.a.c.b.a(this.J).a(90.0F);
  }

  private ImageButton J()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.z), this.h.a(e.a.A));
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(9);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setBackgroundDrawable(this.i.b("btn_single_close_nm.png"));
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (q.a(q.this))
          return;
        q.a(q.this, true);
        q.b(q.this);
      }
    });
    localImageButton.setVisibility(8);
    a(ADProfile.d.p, localImageButton);
    return localImageButton;
  }

  private void K()
  {
    this.ab = true;
    this.L = false;
    D();
    this.E.a(false);
    N();
  }

  private ImageButton L()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.x), this.h.a(e.a.y));
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (q.a(q.this))
          return;
        q.b(q.this);
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b("btn_done_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("btn_done_at.png"), this.i.b("btn_done_nm.png")));
    localImageButton.setVisibility(8);
    return localImageButton;
  }

  private RelativeLayout M()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.O), this.g.a(f.a.P));
    localLayoutParams.addRule(14);
    localLayoutParams.topMargin = this.g.a(f.a.Q);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
    localRelativeLayout.setLayoutParams(localLayoutParams);
    localRelativeLayout.setBackgroundColor(Color.parseColor("#adadad"));
    return localRelativeLayout;
  }

  private void N()
  {
    float f1 = 1.0F;
    float f4;
    float f3;
    float f2;
    if (!this.aa)
    {
      f4 = 90.0F;
      f3 = this.X;
      f2 = this.Y;
      f1 = this.Z;
    }
    while (true)
    {
      this.C.clearAnimation();
      com.a.c.b.a(this.C).a(500L).a(f4).d(f3).e(f2).f(f1);
      com.a.c.b.a(this.z).a(500L).a(f4).d(f3).e(f2).f(f1).a(new a.a()
      {
        public void onAnimationCancel(com.a.a.a paramAnonymousa)
        {
          q.b(q.this, false);
        }

        public void onAnimationEnd(com.a.a.a paramAnonymousa)
        {
          boolean bool = true;
          if (!q.c(q.this))
          {
            n.a(new View[] { q.this.E, q.e(q.this), q.f(q.this), q.this.J });
            q.this.E.a(true);
            if (q.this.s != null)
              q.this.s.a(false);
          }
          while (true)
          {
            paramAnonymousa = q.this;
            if (q.c(q.this))
              bool = false;
            q.c(paramAnonymousa, bool);
            q.b(q.this, false);
            if (q.h(q.this))
            {
              q.this.e.onClick(q.f(q.this));
              q.a(q.this, false);
            }
            return;
            n.a(new View[] { q.this.F, q.g(q.this) });
            q.this.E.setVisibility(8);
            q.this.E.a(false);
            if (q.this.s != null)
            {
              if (q.this.s.a)
                com.a.c.a.a(q.this.D, 1.0F);
              q.this.s.a(true);
            }
          }
        }

        public void onAnimationRepeat(com.a.a.a paramAnonymousa)
        {
        }

        public void onAnimationStart(com.a.a.a paramAnonymousa)
        {
          n.b(new View[] { q.this.E, q.e(q.this), q.f(q.this), q.this.J, q.this.C, q.this.F, q.g(q.this) });
          q.this.E.a(false);
          if (q.this.s != null)
            q.this.s.a(false);
        }
      });
      return;
      f3 = 0.0F;
      f4 = 0.0F;
      f2 = 1.0F;
    }
  }

  private RelativeLayout.LayoutParams O()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.U, this.V);
    localLayoutParams.addRule(14);
    localLayoutParams.topMargin = this.W;
    return localLayoutParams;
  }

  private ImageView P()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.g), this.g.a(f.a.h));
    ImageView localImageView = new ImageView(this.a);
    localImageView.setLayoutParams(localLayoutParams);
    localImageView.setOnClickListener(this.e);
    return localImageView;
  }

  protected com.intowow.sdk.i.c.b.a F()
  {
    if (!this.Q)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.o), this.g.a(f.a.p));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    localLayoutParams.topMargin = H();
    com.intowow.sdk.i.c.b.a locala = new com.intowow.sdk.i.c.b.a(this.a, ((ADProfile.q)this.c.a(ADProfile.d.B)).f(), this.g.a(f.a.n));
    locala.setLayoutParams(localLayoutParams);
    return locala;
  }

  protected int H()
  {
    return this.h.a(e.a.B);
  }

  protected com.intowow.sdk.i.c.b a(int paramInt1, int paramInt2)
  {
    com.intowow.sdk.i.c.b localb = new com.intowow.sdk.i.c.b(this.a, paramInt1, paramInt2);
    localb.setSurfaceTextureListener(new TextureView.SurfaceTextureListener()
    {
      public void onSurfaceTextureAvailable(SurfaceTexture paramAnonymousSurfaceTexture, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        q.this.A = new Surface(paramAnonymousSurfaceTexture);
        q.this.K = true;
        if ((!q.this.M) && (q.this.P))
          q.this.p();
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
    localb.setLayoutParams(O());
    localb.setOnClickListener(x());
    return localb;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.ad = M();
    this.z = a(this.U, this.V);
    this.F = z();
    this.F.c();
    this.F.setVisibility(0);
    this.T = C();
    this.ae = P();
    a(ADProfile.d.m, this.ae);
    this.af = L();
    this.ag = J();
    this.J = F();
    String str = com.intowow.sdk.b.e.a(this.a).r();
    if (!l.a(str))
    {
      int i = this.g.a(f.a.aa);
      int j = this.g.a(f.a.W);
      int k = this.g.a(f.a.X);
      int m = this.g.a(f.a.Y);
      int n = this.g.a(f.a.Z);
      int i1 = this.g.a(f.a.V);
      int i2 = this.g.a(f.a.T);
      int i3 = this.g.a(f.a.U);
      this.D = a(j, k, m, n, n);
      ((RelativeLayout.LayoutParams)this.D.getLayoutParams()).addRule(1, -1);
      this.s = a(str, new View[] { this.D }, i, i1, i2, i3);
    }
    n.a(paramRelativeLayout, new View[] { this.ae, this.ad, this.D, this.s, this.T, this.z, this.F, this.E, this.ag, this.J, this.C, this.af });
    B();
    I();
  }

  protected com.intowow.sdk.i.c.a i()
  {
    return a(this.U, this.V, O());
  }

  protected boolean k()
  {
    return true;
  }

  protected void n()
  {
    super.n();
    float f;
    if (!this.aa)
    {
      n.a(new View[] { this.ad });
      n.b(new View[] { this.E, this.af, this.ag, this.J });
      this.E.a(false);
      if (this.s != null)
      {
        com.intowow.sdk.i.c.a locala = this.D;
        if (this.s.a)
        {
          f = 1.0F;
          com.a.c.a.a(locala, f);
          this.s.a(true);
        }
      }
      else
      {
        this.L = true;
      }
    }
    while (true)
    {
      u();
      return;
      f = 0.0F;
      break;
      n.b(new View[] { this.F, this.ad });
      n.a(new View[] { this.E, this.af, this.ag, this.J });
      this.E.a(true);
      if (this.s != null)
        this.s.a(false);
    }
  }

  protected void o()
  {
    super.o();
    this.ab = false;
    this.ac = false;
    this.L = true;
    this.F.a(this.aa);
    this.E.a(false);
  }

  protected View.OnClickListener x()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (q.a(q.this))
          return;
        q.this.D();
        if (!q.c(q.this))
        {
          q.b(q.this, true);
          q.this.F.a(true);
          q.this.F.setVisibility(8);
          q.d(q.this);
          return;
        }
        q.this.E.a(true);
      }
    };
  }

  protected com.intowow.sdk.i.c.a.a z()
  {
    Object localObject = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject).addRule(14);
    ((RelativeLayout.LayoutParams)localObject).topMargin = this.g.a(f.a.S);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.R), this.g.a(f.a.R));
    localObject = com.intowow.sdk.i.c.a.a.a(this.a, this.i, (RelativeLayout.LayoutParams)localObject, localLayoutParams);
    ((com.intowow.sdk.i.c.a.a)localObject).setVisibility(0);
    return localObject;
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new q(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.q
 * JD-Core Version:    0.6.2
 */