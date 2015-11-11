package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class RotateLoadingLayout2 extends LoadingLayout2
{
  static final int ROTATION_ANIMATION_DURATION = 1200;
  private final Animation mRotateAnimation = new RotateAnimation(0.0F, 720.0F, 1, 0.5F, 1, 0.5F);

  public RotateLoadingLayout2(Context paramContext)
  {
    super(paramContext);
    this.mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
    this.mRotateAnimation.setDuration(1200L);
    this.mRotateAnimation.setRepeatCount(-1);
    this.mRotateAnimation.setRepeatMode(1);
  }

  private void resetImageRotation()
  {
  }

  protected int getDefaultDrawableResId()
  {
    return 2130837795;
  }

  protected void onLoadingDrawableSet(Drawable paramDrawable)
  {
  }

  protected void onPullImpl(float paramFloat)
  {
  }

  protected void pullToRefreshImpl()
  {
    this.mPullImage.setVisibility(0);
    this.mReleaseImage.setVisibility(4);
    this.mHeaderImage.setVisibility(4);
  }

  protected void refreshingImpl()
  {
    this.mPullImage.setVisibility(4);
    this.mReleaseImage.setVisibility(4);
    this.mHeaderImage.setVisibility(0);
    this.mHeaderImage.startAnimation(this.mRotateAnimation);
  }

  protected void releaseToRefreshImpl()
  {
    this.mPullImage.setVisibility(4);
    this.mReleaseImage.setVisibility(0);
    this.mHeaderImage.setVisibility(4);
  }

  protected void resetImpl()
  {
    this.mHeaderImage.clearAnimation();
    resetImageRotation();
  }

  public void setLastUpdatedLabel(CharSequence paramCharSequence)
  {
  }

  public void setLoadingDrawable(Drawable paramDrawable)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.RotateLoadingLayout2
 * JD-Core Version:    0.6.2
 */