package fm.qingting.qtradio.view.podcaster;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.internal.LoadingLayout2;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class RotateLoadingLayout extends LoadingLayout2
{
  static final int ROTATION_ANIMATION_DURATION = 1200;
  private final ViewLayout imageLayout = this.standardLayout.createChildLT(50, 50, 335, 225, ViewLayout.SCALE_FLAG_SLTCW);
  private final Animation mRotateAnimation = new RotateAnimation(0.0F, 720.0F, 1, 0.5F, 1, 0.5F);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 366, 720, 366, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(200, 50, 260, 275, ViewLayout.SCALE_FLAG_SLTCW);

  public RotateLoadingLayout(Context paramContext)
  {
    super(paramContext);
    this.mRotateAnimation.setInterpolator(new LinearInterpolator());
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

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.textLayout.layoutView(this.mHeaderText);
    this.imageLayout.layoutView(this.mHeaderImage);
    this.imageLayout.layoutView(this.mPullImage);
    this.imageLayout.layoutView(this.mReleaseImage);
  }

  protected void onLoadingDrawableSet(Drawable paramDrawable)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.imageLayout.scaleToBounds(this.standardLayout);
    this.textLayout.measureView(this.mHeaderText);
    this.imageLayout.measureView(this.mHeaderImage);
    this.imageLayout.measureView(this.mPullImage);
    this.imageLayout.measureView(this.mReleaseImage);
    this.mHeaderText.setTextSize(0, SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
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
 * Qualified Name:     fm.qingting.qtradio.view.podcaster.RotateLoadingLayout
 * JD-Core Version:    0.6.2
 */