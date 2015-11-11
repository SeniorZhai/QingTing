package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.a.b.a;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.g;
import com.intowow.sdk.model.ADProfile.h;
import com.intowow.sdk.model.ADProfile.h.a;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.j;

public class p extends m
{
  private ImageButton U = null;
  private ADProfile.h.a V = null;
  private Runnable W = new Runnable()
  {
    public void run()
    {
      if ((p.this.a != null) && (p.this.d != null))
        p.this.d.g();
    }
  };

  public p(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  private ImageButton I()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.e), this.g.a(f.a.f));
    localLayoutParams.addRule(3, 987654);
    localLayoutParams.addRule(14);
    localLayoutParams.topMargin = this.g.a(f.a.a);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (p.this.a != null)
          p.this.a.onBackPressed();
      }
    });
    localImageButton.setVisibility(8);
    localImageButton.setBackgroundDrawable(this.i.b("arrive_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("arrive_at.png"), this.i.b("arrive_nm.png")));
    return localImageButton;
  }

  private ImageButton J()
  {
    if (this.c.q().a(ADProfile.g.g))
    {
      this.V = this.c.q().a();
      float f1 = a() / 720.0F;
      float f2 = b() / 1280.0F;
      localLayoutParams = new RelativeLayout.LayoutParams((int)(this.V.c() * f1), (int)(this.V.d() * f2));
      localLayoutParams.addRule(10);
      localLayoutParams.addRule(9);
      localLayoutParams.leftMargin = ((int)(f1 * this.V.a()));
      localLayoutParams.topMargin = ((int)(this.V.b() * f2));
      localImageButton = new ImageButton(this.a);
      localImageButton.setLayoutParams(localLayoutParams);
      localImageButton.setBackgroundDrawable(null);
      return localImageButton;
    }
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.w), this.g.a(f.a.x));
    localLayoutParams.addRule(12);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    a(ADProfile.d.p, localImageButton);
    return localImageButton;
  }

  protected ImageButton C()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.c), this.g.a(f.a.d));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (p.this.a != null)
          p.this.a.onBackPressed();
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b("btn_skip_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("btn_skip_at.png"), this.i.b("btn_skip_nm.png")));
    return localImageButton;
  }

  protected com.intowow.sdk.i.c.b.a F()
  {
    if (!this.Q)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.o), this.g.a(f.a.n));
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(9);
    localLayoutParams.leftMargin = H();
    localLayoutParams.bottomMargin = H();
    com.intowow.sdk.i.c.b.a locala = new com.intowow.sdk.i.c.b.a(this.a, ((ADProfile.q)this.c.a(ADProfile.d.B)).f(), this.g.a(f.a.n));
    locala.setLayoutParams(localLayoutParams);
    return locala;
  }

  protected int H()
  {
    return this.g.a(f.a.m);
  }

  protected int a()
  {
    return this.g.a(f.a.g);
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.U = J();
    this.U.setOnClickListener(this.e);
    this.T = C();
    if (j())
    {
      this.I = I();
      this.B.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (p.this.a != null)
            p.this.a.onBackPressed();
        }
      });
    }
    if (this.Q)
      this.J = F();
    B();
    n.a(paramRelativeLayout, new View[] { this.z, this.U, this.E, this.D, this.s, this.J, this.F, this.C, this.B, this.H, this.I, this.T });
  }

  protected int b()
  {
    return this.g.a(f.a.h);
  }

  protected RelativeLayout.LayoutParams h()
  {
    return new RelativeLayout.LayoutParams(a(), b());
  }

  protected boolean j()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.t != null)
    {
      bool1 = bool2;
      if (this.t.a == ADProfile.a.c)
        bool1 = true;
    }
    return bool1;
  }

  protected boolean k()
  {
    return (this.t == null) || (this.t.a == ADProfile.a.a);
  }

  protected RelativeLayout.LayoutParams l()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.j), this.g.a(f.a.k));
    localLayoutParams.addRule(14);
    localLayoutParams.topMargin = this.g.a(f.a.b);
    return localLayoutParams;
  }

  protected void m()
  {
    super.m();
    if (this.t != null)
    {
      if ((this.t.a != ADProfile.a.b) || (this.d == null))
        break label70;
      long l = this.t.b;
      if (l <= 0L)
        break label60;
      this.j.postDelayed(this.W, l);
    }
    label60: label70: 
    do
    {
      do
      {
        return;
        this.d.g();
        return;
      }
      while (this.t.a != ADProfile.a.c);
      if (this.T != null)
        this.T.setVisibility(8);
      t();
      r();
    }
    while (this.I == null);
    this.I.setVisibility(0);
  }

  protected void n()
  {
    super.n();
    u();
    if (this.T != null)
      this.T.setVisibility(0);
    if (this.U != null)
      this.U.setVisibility(0);
    if (this.I != null)
      this.I.setVisibility(8);
  }

  protected void o()
  {
    super.o();
    if (this.U != null)
      this.U.setVisibility(8);
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new p(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.p
 * JD-Core Version:    0.6.2
 */