package com.intowow.sdk.i.c.a;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Random;

public class c extends RelativeLayout
  implements com.intowow.sdk.i.c.c.b
{
  private final a[] a = new a[4];
  private long b = 0L;
  private long c = 0L;
  private boolean d = true;

  public c(Context paramContext)
  {
    super(paramContext);
  }

  public static c a(Activity paramActivity, b paramb, RelativeLayout.LayoutParams paramLayoutParams)
  {
    c localc = new c(paramActivity);
    localc.setLayoutParams(paramLayoutParams);
    int i = 0;
    if (i >= 4);
    while (true)
      while (true)
      {
        localc.setVisibility(8);
        return localc;
        try
        {
          paramLayoutParams = new a(paramActivity);
          paramLayoutParams.a(paramb);
          RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramb.f(), paramb.e());
          localLayoutParams.addRule(12);
          paramLayoutParams.setLayoutParams(localLayoutParams);
          paramLayoutParams.setId(i + 1);
          if (i > 0)
          {
            localLayoutParams.addRule(1, i);
            localLayoutParams.leftMargin = (-paramb.h());
          }
          while (true)
          {
            localc.a(i, paramLayoutParams);
            i += 1;
            break;
            localLayoutParams.addRule(9);
          }
        }
        catch (Exception paramActivity)
        {
        }
      }
  }

  public void a()
  {
    int i = 0;
    while (true)
    {
      if (i >= 4)
        return;
      if (this.a[i] != null)
      {
        this.a[i].c();
        this.a[i].invalidate();
      }
      i += 1;
    }
  }

  public void a(int paramInt)
  {
    long l = System.currentTimeMillis();
    if (l - this.c > 190L)
    {
      paramInt = 0;
      if (paramInt >= 4)
        this.c = l;
    }
    else if (System.currentTimeMillis() - this.b >= 40L)
    {
      if (!this.d)
        break label97;
      if (getVisibility() != 0)
        setVisibility(0);
    }
    while (true)
    {
      this.b = System.currentTimeMillis();
      return;
      if (this.a[paramInt] != null)
        this.a[paramInt].d();
      paramInt += 1;
      break;
      label97: if (getVisibility() != 8)
        setVisibility(8);
    }
  }

  public void a(int paramInt, a parama)
  {
    this.a[paramInt] = parama;
    addView(parama);
  }

  public void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
    if (this.d);
    for (int i = 0; ; i = 8)
    {
      setVisibility(i);
      return;
    }
  }

  public void b()
  {
    int i = 0;
    while (true)
    {
      if (i >= 4)
        return;
      try
      {
        if (this.a[i] != null)
          this.a[i].a();
        i += 1;
      }
      finally
      {
      }
    }
  }

  public void c()
  {
    int i = 0;
    while (true)
    {
      if (i >= 4)
        return;
      try
      {
        if (this.a[i] != null)
          this.a[i].b();
        i += 1;
      }
      finally
      {
      }
    }
  }

  public void e()
  {
  }

  public void f()
  {
  }

  private static class a extends View
  {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private a g = null;
    private b h = null;
    private Bitmap i = null;
    private Bitmap j = null;
    private Bitmap k = null;
    private float l = 0.0F;
    private float m = 0.0F;
    private float n = 0.0F;
    private RectF o = null;
    private RectF p = null;
    private RectF q = null;

    public a(Context paramContext)
    {
      super();
    }

    private Bitmap a(Drawable paramDrawable)
    {
      if (paramDrawable != null)
      {
        paramDrawable = ((BitmapDrawable)paramDrawable).getBitmap();
        if ((paramDrawable != null) && (!paramDrawable.isRecycled()))
          return paramDrawable;
      }
      return null;
    }

    public void a()
    {
      if (this.h == null)
        return;
      this.k = a(this.h.l());
    }

    public void a(b paramb)
    {
      this.h = paramb;
      if (this.h == null)
        return;
      this.i = a(this.h.i());
      this.j = a(this.h.j());
      this.k = a(this.h.l());
      this.o = new RectF();
      this.p = new RectF();
      this.q = new RectF();
      this.d = this.h.f();
      this.e = this.h.g();
      this.f = this.h.e();
      this.a = this.h.d();
      this.b = this.h.d();
      this.c = (this.f - this.b - this.h.g());
      this.l = this.b;
    }

    public void b()
    {
      if (this.h == null)
        return;
      this.k = a(this.h.k());
    }

    public void c()
    {
      try
      {
        clearAnimation();
        this.l = this.b;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public boolean d()
    {
      try
      {
        final int i1 = this.b + new Random().nextInt(this.c);
        this.g = new a(this, i1, this.a, 150L);
        this.g.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            c.a.a(c.a.this, i1);
          }

          public void onAnimationRepeat(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnonymousAnimation)
          {
          }
        });
        startAnimation(this.g);
        return true;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    protected void onDraw(Canvas paramCanvas)
    {
      super.onDraw(paramCanvas);
      this.m = (this.e * 2 + this.l);
      this.n = (this.f - this.m);
      if ((this.i != null) && (!this.i.isRecycled()))
      {
        this.o.set(0.0F, this.n, this.d, this.n + this.e);
        paramCanvas.drawBitmap(this.i, null, this.o, null);
      }
      if ((this.k != null) && (!this.k.isRecycled()))
      {
        this.p.set(0.0F, this.n + this.e, this.d, this.n + this.e + this.l);
        paramCanvas.drawBitmap(this.k, null, this.p, null);
      }
      if ((this.j != null) && (!this.j.isRecycled()))
      {
        this.q.set(0.0F, this.n + this.e + this.l, this.d, this.f);
        paramCanvas.drawBitmap(this.j, null, this.q, null);
      }
    }

    private class a extends Animation
    {
      private int b = 0;
      private int c = 0;
      private int d = 0;

      public a(View paramInt1, int paramInt2, int paramLong, long arg5)
      {
        Object localObject;
        setDuration(localObject);
        this.b = paramInt2;
        this.c = paramLong;
        this.d = (this.b - this.c);
      }

      protected void applyTransformation(float paramFloat, Transformation paramTransformation)
      {
        super.applyTransformation(paramFloat, paramTransformation);
        if (paramFloat < 1.0F)
        {
          c.a.a(c.a.this, this.c + (int)(this.d * paramFloat));
          c.a.this.invalidate();
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.a.c
 * JD-Core Version:    0.6.2
 */