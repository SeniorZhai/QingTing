package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.a.a.a.a;
import com.a.c.b;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.o;
import com.intowow.sdk.model.j;
import java.util.ArrayList;
import java.util.List;

public class e extends d
{
  protected static ADProfile.d[] A = { ADProfile.d.w, ADProfile.d.x, ADProfile.d.y };
  protected static ADProfile.d[] B = { ADProfile.d.g, ADProfile.d.i, ADProfile.d.k };
  protected static ADProfile.d[] C = { ADProfile.d.h, ADProfile.d.j, ADProfile.d.l };
  protected long D = 1000L;
  protected int E = 2000;
  protected int F = 0;
  protected int G = 0;
  protected int H = 0;
  protected ImageButton I = null;
  protected List<RelativeLayout> J = null;
  protected LinearLayout K = null;
  protected Runnable L = new Runnable()
  {
    public void run()
    {
      if (e.this.a != null)
      {
        final View localView = e.this.K.getChildAt(0);
        localView.clearAnimation();
        e.a locala = new e.a(e.this, (RelativeLayout)localView, e.this.H, e.this.G, e.this.E);
        locala.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymous2Animation)
          {
            paramAnonymous2Animation = e.this;
            paramAnonymous2Animation.F += 1;
            paramAnonymous2Animation = e.this;
            paramAnonymous2Animation.G += e.this.H;
            if (e.this.F + 1 == e.this.J.size())
            {
              ((LinearLayout.LayoutParams)localView.getLayoutParams()).leftMargin = 0;
              e.this.F = 0;
              e.this.G = 0;
              localView.requestLayout();
              localView.invalidate();
            }
            e.this.j.postDelayed(e.this.L, e.this.D);
          }

          public void onAnimationRepeat(Animation paramAnonymous2Animation)
          {
          }

          public void onAnimationStart(Animation paramAnonymous2Animation)
          {
            e.this.a(e.this.F, e.this.F + 1);
          }
        });
        e.this.K.startAnimation(locala);
      }
    }
  };

  public e(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  protected ImageButton a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    if (paramInt3 > 0)
      localLayoutParams.rightMargin = paramInt3;
    if (paramInt4 > 0)
      localLayoutParams.bottomMargin = paramInt4;
    localLayoutParams.addRule(paramInt5);
    localLayoutParams.addRule(12);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setId(4096);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setBackgroundDrawable(this.i.b(paramString1));
    localImageButton.setOnTouchListener(n.a(this.i.b(paramString2), this.i.b(paramString1)));
    localImageButton.setOnClickListener(this.e);
    return localImageButton;
  }

  protected ImageButton a(int paramInt, String paramString1, String paramString2)
  {
    if (this.b == j.d)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt, paramInt);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setBackgroundDrawable(this.i.b(paramString1));
    localImageButton.setOnTouchListener(n.a(this.i.b(paramString2), this.i.b(paramString1)));
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (e.this.a != null)
          e.this.a.onBackPressed();
      }
    });
    return localImageButton;
  }

  protected ImageView a(ADProfile.d paramd, int paramInt1, int paramInt2, int paramInt3)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    localLayoutParams.leftMargin = paramInt3;
    ImageView localImageView = new ImageView(this.a);
    localImageView.setLayoutParams(localLayoutParams);
    localImageView.setId(4098);
    a(paramd, localImageView);
    return localImageView;
  }

  protected RelativeLayout a(ADProfile.d paramd1, ADProfile.d paramd2, ADProfile.d paramd3)
  {
    return null;
  }

  protected TextView a(ADProfile.d paramd, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    localLayoutParams.leftMargin = paramInt4;
    localLayoutParams.addRule(6, paramInt3);
    localLayoutParams.addRule(1, paramInt3);
    TextView localTextView = new TextView(this.a);
    localTextView.setPadding(0, 0, 0, 0);
    n.a(localTextView, 4099, ((ADProfile.o)this.c.a(paramd)).d(), paramInt5, paramInt6, localLayoutParams, true, true, 0);
    return localTextView;
  }

  protected TextView a(ADProfile.d paramd, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    localLayoutParams.leftMargin = paramInt3;
    localLayoutParams.addRule(3, paramInt4);
    localLayoutParams.addRule(1, paramInt5);
    TextView localTextView = new TextView(this.a);
    localTextView.setPadding(0, 0, 0, 0);
    n.a(localTextView, 0, ((ADProfile.o)this.c.a(paramd)).d(), paramInt6, paramInt7, localLayoutParams, true, true, 2);
    return localTextView;
  }

  protected com.intowow.sdk.i.c.a a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, int paramInt4)
  {
    Object localObject = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    ((RelativeLayout.LayoutParams)localObject).addRule(paramInt3);
    if (paramInt4 > 0)
      ((RelativeLayout.LayoutParams)localObject).bottomMargin = paramInt4;
    localObject = a(paramInt1, paramInt2, (RelativeLayout.LayoutParams)localObject);
    if (paramBoolean1)
      a(ADProfile.d.m, (ImageView)localObject);
    if (paramBoolean2)
      ((com.intowow.sdk.i.c.a)localObject).setOnClickListener(this.e);
    return localObject;
  }

  protected void a(int paramInt1, int paramInt2)
  {
    final View localView1 = this.K.getChildAt(paramInt1);
    View localView2 = this.K.getChildAt(paramInt2);
    b.a(localView1).g(0.0F).a(this.E).a(new a.a()
    {
      public void onAnimationCancel(com.a.a.a paramAnonymousa)
      {
      }

      public void onAnimationEnd(com.a.a.a paramAnonymousa)
      {
        com.a.c.a.a(localView1, 1.0F);
      }

      public void onAnimationRepeat(com.a.a.a paramAnonymousa)
      {
      }

      public void onAnimationStart(com.a.a.a paramAnonymousa)
      {
      }
    }).a();
    com.a.c.a.a(localView2, 0.0F);
    b.a(localView2).g(1.0F).a(this.E).a();
  }

  protected void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, paramInt2);
    localLayoutParams.addRule(paramInt3);
    localLayoutParams.addRule(12);
    if (paramBoolean)
    {
      localLayoutParams.leftMargin = paramInt4;
      this.K = new LinearLayout(this.a);
      this.K.setLayoutParams(localLayoutParams);
      this.K.setOrientation(0);
      paramInt1 = 0;
    }
    while (true)
    {
      if (paramInt1 >= this.J.size())
      {
        return;
        localLayoutParams.bottomMargin = paramInt4;
        break;
      }
      this.K.addView((View)this.J.get(paramInt1));
      paramInt1 += 1;
    }
  }

  protected RelativeLayout b(int paramInt1, int paramInt2)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(paramInt1, paramInt2);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
    localRelativeLayout.setLayoutParams(localLayoutParams);
    return localRelativeLayout;
  }

  public boolean c()
  {
    boolean bool = true;
    if (!super.c())
      bool = false;
    while (this.K.getChildCount() <= 1)
      return bool;
    this.j.postDelayed(this.L, this.D);
    return true;
  }

  public boolean d()
  {
    boolean bool2 = true;
    boolean bool1;
    if (!super.d())
      bool1 = false;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.a == null);
      bool1 = bool2;
    }
    while (this.K.getChildCount() <= 1);
    this.j.removeCallbacks(this.L);
    return true;
  }

  protected RelativeLayout h()
  {
    return null;
  }

  protected void i()
  {
    this.J = new ArrayList();
    Object localObject1 = null;
    Object localObject2 = h();
    if (localObject2 != null)
    {
      this.J.add(localObject2);
      localObject1 = h();
    }
    int i = 0;
    int j = 0;
    while (true)
    {
      if (i >= A.length)
      {
        if ((localObject1 != null) && (j != 0))
          this.J.add(localObject1);
        return;
      }
      RelativeLayout localRelativeLayout = a(A[i], B[i], C[i]);
      localObject2 = localObject1;
      if (localRelativeLayout != null)
      {
        int k = 1;
        this.J.add(localRelativeLayout);
        j = k;
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = a(A[i], B[i], C[i]);
          j = k;
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
  }

  protected class a extends Animation
  {
    private RelativeLayout b;
    private int c = 0;
    private int d = 0;

    public a(RelativeLayout paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      int i;
      setDuration(i);
      this.b = paramInt1;
      this.c = paramInt2;
      this.d = paramInt3;
      if (this.d != 0)
        this.d = (-this.d);
    }

    protected void applyTransformation(float paramFloat, Transformation paramTransformation)
    {
      super.applyTransformation(paramFloat, paramTransformation);
      if (paramFloat < 1.0F)
      {
        int i = this.d;
        int j = (int)(this.c * paramFloat);
        ((LinearLayout.LayoutParams)this.b.getLayoutParams()).leftMargin = (i - j);
        this.b.requestLayout();
        this.b.invalidate();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.e
 * JD-Core Version:    0.6.2
 */