package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.b.g;
import com.intowow.sdk.f.e;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.j;

public class l extends k
{
  protected ImageButton T = null;
  protected ImageButton U = null;

  public l(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  protected com.intowow.sdk.i.c.b.a C()
  {
    if (!this.Q)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.l), this.h.a(e.a.k));
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(9);
    localLayoutParams.leftMargin = E();
    localLayoutParams.bottomMargin = F();
    com.intowow.sdk.i.c.b.a locala = new com.intowow.sdk.i.c.b.a(this.a, ((ADProfile.q)this.c.a(ADProfile.d.B)).f(), this.h.a(e.a.k));
    locala.setLayoutParams(localLayoutParams);
    return locala;
  }

  protected ImageButton D()
  {
    if (this.b == j.d)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.C), this.h.a(e.a.C));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (l.this.a != null)
          l.this.a.onBackPressed();
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b("btn_single_close_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("btn_single_close_at.png"), this.i.b("btn_single_close_nm.png")));
    return localImageButton;
  }

  protected int E()
  {
    return this.h.a(e.a.i);
  }

  protected int F()
  {
    return this.h.a(e.a.j);
  }

  protected int a()
  {
    return this.h.a(e.a.g);
  }

  protected void a(ViewGroup paramViewGroup)
  {
    if (this.Q)
    {
      this.J = C();
      paramViewGroup.addView(this.J);
      B();
    }
  }

  protected int b()
  {
    return this.h.a(e.a.h);
  }

  protected RelativeLayout.LayoutParams h()
  {
    return null;
  }

  protected RelativeLayout.LayoutParams l()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.c), this.h.a(e.a.d));
    localLayoutParams.addRule(13);
    return localLayoutParams;
  }

  protected View.OnClickListener x()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((l.this.x != null) && (!l.this.x.a(l.this.m)));
        label148: 
        while (true)
        {
          return;
          paramAnonymousView = l.this;
          boolean bool;
          if (l.this.L)
          {
            bool = false;
            paramAnonymousView.L = bool;
            if (!l.this.L)
              break label118;
            if (l.this.d != null)
              l.this.d.d();
            l.this.v();
          }
          while (true)
          {
            if (l.this.F == null)
              break label148;
            l.this.F.b();
            return;
            bool = true;
            break;
            label118: if (l.this.d != null)
              l.this.d.e();
            l.this.w();
          }
        }
      }
    };
  }

  protected View.OnClickListener y()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (l.this.d != null)
          l.this.d.f();
        l.this.w();
        if (l.this.F != null)
          l.this.F.d();
        l.this.p();
      }
    };
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.l
 * JD-Core Version:    0.6.2
 */