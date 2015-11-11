package com.taobao.newxp.view.common;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.taobao.newxp.a.b;
import com.taobao.newxp.common.AlimmContext;

public class c
{
  private ViewGroup a;
  private Context b;
  private ImageView c;
  private RelativeLayout d;
  private boolean e;

  public c(Context paramContext, ViewGroup paramViewGroup)
  {
    this.a = paramViewGroup;
    this.b = paramContext;
    this.c = new ImageView(this.b);
    this.c.setImageDrawable(this.b.getResources().getDrawable(b.a(this.b)));
    this.e = false;
    int i = (int)AlimmContext.getAliContext().getAppUtils().a(60.0F);
    paramContext = new RelativeLayout.LayoutParams(i, i);
    paramContext.addRule(13);
    this.d = new RelativeLayout(this.b);
    this.d.addView(this.c, paramContext);
  }

  public void a()
  {
    if (this.e)
      return;
    this.e = true;
    Object localObject = new ViewGroup.LayoutParams(-1, -1);
    this.a.addView(this.d, (ViewGroup.LayoutParams)localObject);
    this.d.setVisibility(0);
    localObject = AnimationUtils.loadAnimation(this.b, com.taobao.newxp.a.a.c(this.b));
    ((Animation)localObject).setInterpolator(new LinearInterpolator());
    ((Animation)localObject).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        c.this.b();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.c.startAnimation((Animation)localObject);
  }

  public void b()
  {
    if (!this.e)
      return;
    this.e = false;
    this.a.removeView(this.d);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.c
 * JD-Core Version:    0.6.2
 */