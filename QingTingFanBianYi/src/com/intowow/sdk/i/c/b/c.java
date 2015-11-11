package com.intowow.sdk.i.c.b;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import com.a.a.a.a;

public class c extends TextView
  implements com.intowow.sdk.i.c.c.b
{
  public boolean a = false;
  public boolean b = true;
  public boolean c = false;
  public boolean d = true;
  public boolean e = false;
  public View[] f = null;
  private long g = 0L;

  public c(Context paramContext)
  {
    super(paramContext);
  }

  public void a()
  {
    int i = 0;
    clearAnimation();
    this.c = false;
    this.g = 0L;
    this.d = true;
    View[] arrayOfView;
    int j;
    if (!this.a)
    {
      this.e = true;
      com.a.c.a.d(this, 0.0F);
      if (this.f != null)
      {
        arrayOfView = this.f;
        j = arrayOfView.length;
      }
    }
    while (true)
    {
      if (i >= j)
        return;
      com.a.c.a.a(arrayOfView[i], 0.0F);
      i += 1;
    }
  }

  public void a(int paramInt)
  {
    int i = 0;
    paramInt = 0;
    label11: if (!this.b)
      break label11;
    while (true)
    {
      return;
      if (!this.a)
      {
        long l1 = System.currentTimeMillis();
        long l2 = l1 - this.g;
        if (this.g == 0L)
        {
          this.e = false;
          this.g = l1;
          if (this.f != null)
          {
            arrayOfView = this.f;
            i = arrayOfView.length;
          }
          while (true)
          {
            if (paramInt >= i)
            {
              com.a.c.a.d(this, 0.0F);
              return;
            }
            com.a.c.a.a(arrayOfView[paramInt], 0.0F);
            paramInt += 1;
          }
        }
        if ((!this.c) && (l2 >= 3000L))
        {
          this.c = true;
          com.a.c.b.a(this).c(-getWidth()).a(1200L).a(new AccelerateInterpolator()).a(new a.a()
          {
            public void onAnimationCancel(com.a.a.a paramAnonymousa)
            {
              c.this.e = true;
            }

            public void onAnimationEnd(com.a.a.a paramAnonymousa)
            {
              if (!c.this.e)
                c.this.a = true;
            }

            public void onAnimationRepeat(com.a.a.a paramAnonymousa)
            {
            }

            public void onAnimationStart(com.a.a.a paramAnonymousa)
            {
            }
          });
          return;
        }
        if ((!this.c) || (!this.d) || (l2 < 3400L))
          break;
        this.d = false;
        if (this.f == null)
          break;
        View[] arrayOfView = this.f;
        int j = arrayOfView.length;
        paramInt = i;
        while (paramInt < j)
        {
          com.a.c.b.a(arrayOfView[paramInt]).g(1.0F).a(1000L);
          paramInt += 1;
        }
      }
    }
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
    if (this.b);
    for (int i = 0; ; i = 8)
    {
      setVisibility(i);
      if (!this.a)
      {
        if (this.b)
          break;
        a();
      }
      return;
    }
    com.a.c.a.d(this, 0.0F);
  }

  public void e()
  {
  }

  public void f()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.b.c
 * JD-Core Version:    0.6.2
 */