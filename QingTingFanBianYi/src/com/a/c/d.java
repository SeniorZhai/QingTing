package com.a.c;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import com.a.a.a.a;
import java.lang.ref.WeakReference;

class d extends b
{
  private final WeakReference<ViewPropertyAnimator> a;

  d(View paramView)
  {
    this.a = new WeakReference(paramView.animate());
  }

  public b a(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.rotation(paramFloat);
    return this;
  }

  public b a(long paramLong)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.setDuration(paramLong);
    return this;
  }

  public b a(Interpolator paramInterpolator)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.setInterpolator(paramInterpolator);
    return this;
  }

  public b a(final a.a parama)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
    {
      if (parama == null)
        localViewPropertyAnimator.setListener(null);
    }
    else
      return this;
    localViewPropertyAnimator.setListener(new Animator.AnimatorListener()
    {
      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        parama.onAnimationCancel(null);
      }

      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        parama.onAnimationEnd(null);
      }

      public void onAnimationRepeat(Animator paramAnonymousAnimator)
      {
        parama.onAnimationRepeat(null);
      }

      public void onAnimationStart(Animator paramAnonymousAnimator)
      {
        parama.onAnimationStart(null);
      }
    });
    return this;
  }

  public void a()
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.start();
  }

  public b b(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.translationX(paramFloat);
    return this;
  }

  public b c(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.translationXBy(paramFloat);
    return this;
  }

  public b d(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.translationY(paramFloat);
    return this;
  }

  public b e(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.scaleX(paramFloat);
    return this;
  }

  public b f(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.scaleY(paramFloat);
    return this;
  }

  public b g(float paramFloat)
  {
    ViewPropertyAnimator localViewPropertyAnimator = (ViewPropertyAnimator)this.a.get();
    if (localViewPropertyAnimator != null)
      localViewPropertyAnimator.alpha(paramFloat);
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.a.c.d
 * JD-Core Version:    0.6.2
 */