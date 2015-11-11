package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.b.g;
import com.intowow.sdk.f.d;
import com.intowow.sdk.f.d.a;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.j;

public class m extends k
{
  protected ImageButton T = null;

  public m(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  protected ImageButton C()
  {
    if (this.b == j.d)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.l), this.g.a(f.a.l));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (m.this.a != null)
          m.this.a.onBackPressed();
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b("btn_single_close_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("btn_single_close_at.png"), this.i.b("btn_single_close_nm.png")));
    return localImageButton;
  }

  protected void D()
  {
    if ((this.x != null) && (!this.x.a(this.m)));
    label83: label104: 
    while (true)
    {
      return;
      boolean bool;
      if (this.L)
      {
        bool = false;
        this.L = bool;
        if (!this.L)
          break label83;
        if (this.d != null)
          this.d.d();
        v();
      }
      while (true)
      {
        if (this.F == null)
          break label104;
        this.F.b();
        return;
        bool = true;
        break;
        if (this.d != null)
          this.d.e();
        w();
      }
    }
  }

  protected int E()
  {
    switch (G()[this.b.ordinal()])
    {
    default:
      return this.f.a(d.a.c);
    case 3:
    case 4:
    }
    return 0;
  }

  protected com.intowow.sdk.i.c.b.a F()
  {
    if (!this.Q)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.f.a(d.a.J), this.f.a(d.a.I));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    localLayoutParams.topMargin = this.f.a(d.a.G);
    localLayoutParams.rightMargin = (this.f.a(d.a.H) + E());
    localLayoutParams.addRule(14);
    com.intowow.sdk.i.c.b.a locala = new com.intowow.sdk.i.c.b.a(this.a, ((ADProfile.q)this.c.a(ADProfile.d.B)).f(), this.f.a(d.a.I));
    locala.setLayoutParams(localLayoutParams);
    return locala;
  }

  protected RelativeLayout.LayoutParams h()
  {
    return null;
  }

  protected RelativeLayout.LayoutParams l()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.j), this.g.a(f.a.k));
    localLayoutParams.addRule(13);
    return localLayoutParams;
  }

  protected View.OnClickListener x()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        m.this.D();
      }
    };
  }

  protected View.OnClickListener y()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (m.this.d != null)
          m.this.d.f();
        if (m.this.F != null)
          m.this.F.d();
        m.this.p();
      }
    };
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.m
 * JD-Core Version:    0.6.2
 */