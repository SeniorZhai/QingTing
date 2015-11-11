package com.intowow.sdk.i.a;

import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.I2WAPI;
import com.intowow.sdk.SplashAD.SplashAdListener;
import com.intowow.sdk.SplashAdActivity.IAdActivity;
import com.intowow.sdk.b.g;
import com.intowow.sdk.f.d;
import com.intowow.sdk.f.d.a;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.i.c.c.c;
import com.intowow.sdk.i.c.c.r;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.i;

public class b
  implements SplashAdActivity.IAdActivity
{
  private ViewGroup a = null;
  private ImageButton b = null;
  private boolean c = false;
  private boolean d = false;
  private boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private ADProfile h = null;
  private String i = null;
  private String j = null;
  private String k = null;
  private com.intowow.sdk.i.c.c.a l = null;
  private int m = -1;
  private g n = null;
  private Handler o = null;
  private FragmentActivity p = null;
  private Runnable q = new Runnable()
  {
    public void run()
    {
      b.a(b.this, false);
      b.this.onStart();
    }
  };

  public b(FragmentActivity paramFragmentActivity)
  {
    this.p = paramFragmentActivity;
    if (this.p.getIntent() != null)
    {
      paramFragmentActivity = this.p.getIntent().getExtras();
      if (paramFragmentActivity != null)
        this.m = paramFragmentActivity.getInt("ENTER_ANIM_ID", -1);
    }
  }

  private void a(Bundle paramBundle)
  {
    boolean bool = true;
    if (paramBundle == null)
      return;
    this.d = paramBundle.getBoolean(a.a.toString());
    if (this.d)
    {
      this.j = paramBundle.getString(a.d.toString());
      this.k = paramBundle.getString(a.f.toString());
      this.i = paramBundle.getString(a.c.toString());
      if (paramBundle.getInt(a.e.toString(), 0) != 1)
        break label112;
    }
    while (true)
    {
      this.f = bool;
      this.h = ((ADProfile)paramBundle.getParcelable(a.b.toString()));
      paramBundle.clear();
      return;
      label112: bool = false;
    }
  }

  private void a(RelativeLayout paramRelativeLayout)
  {
    this.l = r.a(this.h.g()).a(this.p, com.intowow.sdk.model.j.c, this.h, new com.intowow.sdk.i.c.c.c.a()
    {
      public void a()
      {
        b.a(b.this, com.intowow.sdk.h.j.h);
      }

      public void b()
      {
      }

      public void c()
      {
        b.b(b.this, true);
        if ((b.a(b.this)) && (b.b(b.this) != null) && (b.b(b.this).C()))
          b.this.closeActivity();
        b.a(b.this, com.intowow.sdk.h.j.i);
      }

      public void d()
      {
        b.a(b.this, com.intowow.sdk.h.j.l);
      }

      public void e()
      {
        b.a(b.this, com.intowow.sdk.h.j.m);
      }

      public void f()
      {
        b.a(b.this, com.intowow.sdk.h.j.n);
      }

      public void g()
      {
        if (b.c(b.this) != null)
          b.this.closeActivity();
      }

      public void h()
      {
      }

      public void i()
      {
      }
    });
    this.l.a(this.n);
    this.l.a(this.i);
    this.l.a(com.intowow.sdk.b.e.a(this.p).a(this.i));
    this.l.a(paramRelativeLayout);
  }

  private void a(d paramd, com.intowow.sdk.f.a parama)
  {
    b();
    ViewGroup localViewGroup = b(paramd, parama);
    localViewGroup.setId(4097);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramd.a(d.a.m), paramd.a(d.a.n));
    localLayoutParams.addRule(14);
    localLayoutParams.addRule(3, 4097);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.p);
    localRelativeLayout.setLayoutParams(localLayoutParams);
    a(localRelativeLayout);
    localLayoutParams = new RelativeLayout.LayoutParams(paramd.a(d.a.m), paramd.a(d.a.n) + paramd.a(d.a.p));
    localLayoutParams.addRule(14);
    localLayoutParams.addRule(10);
    localLayoutParams.topMargin = paramd.a(d.a.o);
    paramd = new ImageView(this.p);
    paramd.setImageDrawable(parama.b("bg_mask.9.png"));
    paramd.setScaleType(ImageView.ScaleType.FIT_XY);
    paramd.setLayoutParams(localLayoutParams);
    this.a.addView(localViewGroup);
    this.a.addView(localRelativeLayout);
    this.a.addView(paramd);
    this.p.setContentView(this.a);
  }

  private void a(com.intowow.sdk.f.e parame, com.intowow.sdk.f.a parama)
  {
    b();
    boolean bool = a(this.h, d.a(this.p).a());
    int i1;
    if (bool)
    {
      i1 = parame.b();
      if (!bool)
        break label197;
    }
    label197: for (int i2 = parame.c(); ; i2 = parame.a(e.a.h))
    {
      Object localObject = new RelativeLayout.LayoutParams(i1, i2);
      ((RelativeLayout.LayoutParams)localObject).addRule(13);
      RelativeLayout localRelativeLayout = new RelativeLayout(this.p);
      localRelativeLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
      a(localRelativeLayout);
      this.a.addView(localRelativeLayout);
      if (!bool)
      {
        parame = new RelativeLayout.LayoutParams(parame.a(e.a.g), parame.a(e.a.h));
        parame.addRule(13);
        localObject = new ImageView(this.p);
        ((ImageView)localObject).setImageDrawable(parama.b("bg_mask.9.png"));
        ((ImageView)localObject).setScaleType(ImageView.ScaleType.FIT_XY);
        ((ImageView)localObject).setLayoutParams(parame);
        this.a.addView((View)localObject);
      }
      this.p.setContentView(this.a);
      return;
      i1 = parame.a(e.a.g);
      break;
    }
  }

  private void a(f paramf, com.intowow.sdk.f.a parama)
  {
    b();
    boolean bool = a(this.h, d.a(this.p).a());
    int i1;
    if (bool)
    {
      i1 = paramf.b();
      if (!bool)
        break label197;
    }
    label197: for (int i2 = paramf.c(); ; i2 = paramf.a(f.a.h))
    {
      Object localObject = new RelativeLayout.LayoutParams(i1, i2);
      ((RelativeLayout.LayoutParams)localObject).addRule(13);
      RelativeLayout localRelativeLayout = new RelativeLayout(this.p);
      localRelativeLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
      a(localRelativeLayout);
      this.a.addView(localRelativeLayout);
      if (!bool)
      {
        paramf = new RelativeLayout.LayoutParams(paramf.a(f.a.g), paramf.a(f.a.h));
        paramf.addRule(13);
        localObject = new ImageView(this.p);
        ((ImageView)localObject).setImageDrawable(parama.b("bg_mask.9.png"));
        ((ImageView)localObject).setScaleType(ImageView.ScaleType.FIT_XY);
        ((ImageView)localObject).setLayoutParams(paramf);
        this.a.addView((View)localObject);
      }
      this.p.setContentView(this.a);
      return;
      i1 = paramf.a(f.a.g);
      break;
    }
  }

  private void a(com.intowow.sdk.h.j paramj)
  {
    if (this.h == null)
      return;
    com.intowow.sdk.b.e.a(this.p).a(this.h.e(), 1, this.i, this.j, "*", this.k, paramj, this.h.a("*", paramj));
  }

  private boolean a(ADProfile paramADProfile, com.intowow.sdk.f.c.a parama)
  {
    return (parama != com.intowow.sdk.f.c.a.d) && (ADProfile.i.b(paramADProfile.g()));
  }

  private ViewGroup b(d paramd, com.intowow.sdk.f.a parama)
  {
    com.intowow.sdk.f.c.a locala = paramd.a();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramd.a(d.a.m), paramd.a(d.a.p));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(14);
    localLayoutParams.topMargin = paramd.a(d.a.o);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.p);
    localRelativeLayout.setLayoutParams(localLayoutParams);
    switch (a()[locala.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      paramd = new RelativeLayout.LayoutParams(paramd.a(d.a.q), paramd.a(d.a.q));
      paramd.addRule(11);
      paramd.addRule(15);
      this.b = new ImageButton(this.p);
      this.b.setBackgroundColor(0);
      this.b.setScaleType(ImageView.ScaleType.FIT_XY);
      this.b.setPadding(0, 0, 0, 0);
      this.b.setLayoutParams(paramd);
      this.b.setImageDrawable(parama.b("btn_close_nm.png"));
      this.b.setOnTouchListener(n.a(parama.b("btn_close_at.png"), parama.b("btn_close_nm.png")));
      this.b.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          b.this.closeActivity();
        }
      });
      localRelativeLayout.addView(this.b);
      return localRelativeLayout;
      localRelativeLayout.setBackgroundDrawable(parama.b("topbar178.png"));
      continue;
      localRelativeLayout.setBackgroundDrawable(parama.b("topbar167.png"));
      continue;
      localRelativeLayout.setBackgroundDrawable(parama.b("topbar.png"));
      continue;
      localRelativeLayout.setBackgroundDrawable(parama.b("topbar15.png"));
    }
  }

  private void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    this.a = new RelativeLayout(this.p);
    this.a.setBackgroundColor(-16777216);
    this.a.setPadding(0, 0, 0, 0);
    this.a.setLayoutParams(localLayoutParams);
  }

  public void closeActivity()
  {
    if (this.c)
      return;
    this.c = true;
    com.intowow.sdk.j.j.a(this.p, this.h, this.i);
    SplashAD.SplashAdListener localSplashAdListener = com.intowow.sdk.b.e.a(this.p).l();
    if (localSplashAdListener != null)
      localSplashAdListener.onClosed();
    this.p.finish();
  }

  public void onBackPressed()
  {
    this.o.removeCallbacks(this.q);
    if (this.l != null)
      this.l.d();
    if (this.n != null)
      this.n = null;
    closeActivity();
  }

  public void onCreate(Bundle paramBundle)
  {
    boolean bool2 = true;
    this.p.requestWindowFeature(1);
    this.o = new Handler();
    this.p.getWindow().setFlags(1024, 1024);
    a(paramBundle);
    if (this.j == null)
      this.j = String.valueOf(System.currentTimeMillis());
    boolean bool1;
    if (this.h == null)
    {
      paramBundle = this.p.getIntent().getExtras();
      this.i = paramBundle.getString("PLACEMENT");
      this.k = paramBundle.getString("TOKEN");
      if (paramBundle.getInt("IS_AUTO_CLOSE_WHEN_ENGAGED") != 1)
        break label295;
      bool1 = true;
    }
    while (true)
    {
      this.f = bool1;
      paramBundle = this.p.getIntent();
      paramBundle.setExtrasClassLoader(ADProfile.class.getClassLoader());
      this.h = ((ADProfile)paramBundle.getParcelableExtra("PROFILE"));
      if (this.n == null)
        this.n = new g(this.p);
      int i1;
      if (ADProfile.i.a(this.h.g()))
        if (ADProfile.i.c(this.h.g()))
        {
          this.p.setRequestedOrientation(0);
          a(com.intowow.sdk.f.e.a(this.p), com.intowow.sdk.f.a.a(this.p));
          i1 = 0;
          label228: if ((i1 == 0) || (this.m == -1) || (this.d))
            break label363;
          bool1 = bool2;
          label251: this.e = bool1;
          if (!this.e);
        }
      try
      {
        long l1 = AnimationUtils.loadAnimation(this.p, this.m).getDuration() - 200L;
        if (l1 <= 0L)
        {
          this.e = false;
          return;
          label295: bool1 = false;
          continue;
          this.p.setRequestedOrientation(1);
          a(f.a(this.p), com.intowow.sdk.f.a.a(this.p));
          i1 = 1;
          break label228;
          this.p.setRequestedOrientation(1);
          a(d.a(this.p), com.intowow.sdk.f.a.a(this.p));
          i1 = 1;
          break label228;
          label363: bool1 = false;
          break label251;
        }
        else
        {
          this.o.postDelayed(this.q, l1);
          return;
        }
      }
      catch (Resources.NotFoundException paramBundle)
      {
        this.e = false;
      }
    }
  }

  public void onDestroy()
  {
    this.p = null;
  }

  public void onPause()
  {
    this.o.removeCallbacks(this.q);
    if (this.l != null)
    {
      this.l.d();
      this.l.f();
    }
    I2WAPI.onActivityPause(this.p.getApplicationContext());
    com.intowow.sdk.b.e.a(this.p).a(this.i, System.currentTimeMillis());
  }

  public void onResume()
  {
    this.g = false;
    I2WAPI.onActivityResume(this.p.getApplicationContext());
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i1 = 1;
    paramBundle.putBoolean(a.a.toString(), true);
    paramBundle.putString(a.d.toString(), this.j);
    paramBundle.putString(a.c.toString(), this.i);
    paramBundle.putString(a.f.toString(), this.k);
    String str = a.e.toString();
    if (this.f);
    while (true)
    {
      paramBundle.putInt(str, i1);
      if (this.h != null)
        paramBundle.putParcelable(a.b.toString(), this.h);
      return;
      i1 = 0;
    }
  }

  public void onStart()
  {
    if ((!this.e) && (this.l != null))
    {
      this.l.c();
      this.l.e();
    }
  }

  public void onStop()
  {
    if ((this.f) && (this.g))
      closeActivity();
  }

  static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.a.b
 * JD-Core Version:    0.6.2
 */