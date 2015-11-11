package com.intowow.sdk.i.c.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class a extends RelativeLayout
  implements com.intowow.sdk.i.c.c.b
{
  private boolean a = false;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private boolean e = true;
  private boolean f = false;
  private View g = null;
  private long h = 0L;
  private long i = 0L;

  public a(Context paramContext)
  {
    super(paramContext);
  }

  public static a a(Activity paramActivity, com.intowow.sdk.f.a parama, RelativeLayout.LayoutParams paramLayoutParams)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13);
    paramActivity = new a(paramActivity);
    paramActivity.setLayoutParams(localLayoutParams);
    paramActivity.a(parama, paramLayoutParams);
    paramActivity.setVisibility(8);
    return paramActivity;
  }

  public static a a(Activity paramActivity, com.intowow.sdk.f.a parama, RelativeLayout.LayoutParams paramLayoutParams1, RelativeLayout.LayoutParams paramLayoutParams2)
  {
    paramActivity = new a(paramActivity);
    paramActivity.setLayoutParams(paramLayoutParams1);
    paramActivity.a(parama, paramLayoutParams2);
    paramActivity.setVisibility(8);
    return paramActivity;
  }

  private void a(View paramView, float paramFloat)
  {
    com.a.c.a.b(paramView, paramFloat);
    com.a.c.a.c(paramView, paramFloat);
  }

  private void a(boolean paramBoolean, float paramFloat)
  {
    com.a.c.b.a(this.g).a(800L).e(paramFloat);
    com.a.c.b.a(this.g).a(800L).f(paramFloat);
  }

  private void b(boolean paramBoolean)
  {
    float f2 = 0.8F;
    while (true)
    {
      try
      {
        View localView = this.g;
        if (localView == null)
          return;
        if (paramBoolean)
        {
          f1 = 1.0F;
          if (!paramBoolean)
            break label60;
          a(this.g, f1);
          a(paramBoolean, f2);
          continue;
        }
      }
      finally
      {
      }
      float f1 = 0.8F;
      continue;
      label60: f2 = 1.0F;
    }
  }

  private void g()
  {
    try
    {
      boolean bool = this.d;
      if (bool);
      while (true)
      {
        return;
        this.d = true;
        this.a = true;
        this.b = false;
        if (this.g != null)
          this.g.clearAnimation();
        setVisibility(8);
        h();
        this.d = false;
      }
    }
    finally
    {
    }
  }

  private void h()
  {
    this.h = 0L;
    this.i = 0L;
  }

  private void i()
  {
    try
    {
      if (this.g != null)
      {
        this.g.clearAnimation();
        com.a.c.b.a(this.g).a(2000L).g(0.0F);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a()
  {
    g();
  }

  public void a(int paramInt)
  {
    boolean bool = false;
    if ((this.h == 0L) && (!this.b))
      this.a = false;
    if ((this.b) || (this.a));
    long l;
    do
    {
      return;
      l = System.currentTimeMillis();
      if (this.h == 0L)
      {
        this.d = false;
        if (!this.f)
          setVisibility(0);
        this.h = l;
        com.a.c.a.a(this.g, 1.0F);
      }
      if ((this.e) && (l - this.h >= 3000L))
      {
        this.a = true;
        i();
        return;
      }
    }
    while (l - this.i < 800L);
    this.i = l;
    if (this.c);
    while (true)
    {
      this.c = bool;
      b(this.c);
      return;
      bool = true;
    }
  }

  public void a(com.intowow.sdk.f.a parama, RelativeLayout.LayoutParams paramLayoutParams)
  {
    this.g = new View(getContext());
    this.g.setLayoutParams(paramLayoutParams);
    this.g.setBackgroundDrawable(parama.b("audio_tutorial_btn.png"));
    addView(this.g);
  }

  public void a(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }

  public void b()
  {
    try
    {
      boolean bool = this.e;
      if (!bool);
      while (true)
      {
        return;
        this.b = true;
        if (this.g != null)
          this.g.clearAnimation();
        setVisibility(8);
        h();
        this.a = true;
      }
    }
    finally
    {
    }
  }

  public void c()
  {
    this.e = false;
  }

  public void d()
  {
    try
    {
      this.b = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void e()
  {
  }

  public void f()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.a.a
 * JD-Core Version:    0.6.2
 */