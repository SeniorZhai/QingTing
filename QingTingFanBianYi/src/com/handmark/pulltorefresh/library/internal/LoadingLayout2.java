package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

public abstract class LoadingLayout2 extends ViewGroup
  implements ILoadingLayout
{
  static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
  static final String LOG_TAG = "PullToRefresh-LoadingLayout";
  private final ViewLayout imageLayout = this.standardLayout.createChildLT(50, 50, 335, 20, ViewLayout.SCALE_FLAG_SLTCW);
  protected final ImageView mHeaderImage;
  protected final TextView mHeaderText;
  protected final ImageView mPullImage;
  private CharSequence mPullLabel;
  private CharSequence mRefreshingLabel;
  protected final ImageView mReleaseImage;
  private CharSequence mReleaseLabel;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(200, 50, 260, 70, ViewLayout.SCALE_FLAG_SLTCW);

  public LoadingLayout2(Context paramContext)
  {
    super(paramContext);
    this.mHeaderText = new TextView(paramContext);
    this.mHeaderText.setTextColor(SkinManager.getTextColorThirdLevel());
    this.mHeaderText.setGravity(17);
    addView(this.mHeaderText);
    this.mPullImage = new ImageView(paramContext);
    this.mPullImage.setBackgroundResource(2130837796);
    this.mPullImage.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mPullImage);
    this.mReleaseImage = new ImageView(paramContext);
    this.mReleaseImage.setBackgroundResource(2130837797);
    this.mReleaseImage.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mReleaseImage);
    this.mHeaderImage = new ImageView(paramContext);
    this.mHeaderImage.setBackgroundResource(2130837795);
    this.mHeaderImage.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mHeaderImage);
    this.mPullLabel = paramContext.getString(2131492891);
    this.mRefreshingLabel = paramContext.getString(2131492893);
    this.mReleaseLabel = paramContext.getString(2131492892);
    reset();
  }

  public final int getContentSize()
  {
    return ScreenConfiguration.getPtrPullMaxHeight();
  }

  protected abstract int getDefaultDrawableResId();

  public final void hideAllViews()
  {
    if (this.mHeaderText.getVisibility() == 0)
      this.mHeaderText.setVisibility(4);
    if (this.mHeaderImage.getVisibility() == 0)
      this.mHeaderImage.setVisibility(4);
    if (this.mPullImage.getVisibility() == 0)
      this.mPullImage.setVisibility(4);
    if (this.mReleaseImage.getVisibility() == 0)
      this.mReleaseImage.setVisibility(4);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.textLayout.layoutView(this.mHeaderText);
    this.imageLayout.layoutView(this.mHeaderImage);
    this.imageLayout.layoutView(this.mPullImage);
    this.imageLayout.layoutView(this.mReleaseImage);
  }

  protected abstract void onLoadingDrawableSet(Drawable paramDrawable);

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.imageLayout.scaleToBounds(this.standardLayout);
    this.textLayout.measureView(this.mHeaderText);
    this.imageLayout.measureView(this.mHeaderImage);
    this.imageLayout.measureView(this.mPullImage);
    this.imageLayout.measureView(this.mReleaseImage);
    this.mHeaderText.setTextSize(0, SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public final void onPull(float paramFloat)
  {
    onPullImpl(paramFloat);
  }

  protected abstract void onPullImpl(float paramFloat);

  public final void pullToRefresh()
  {
    if (this.mHeaderText != null)
      this.mHeaderText.setText(this.mPullLabel);
    pullToRefreshImpl();
  }

  protected abstract void pullToRefreshImpl();

  public final void refreshing()
  {
    if (this.mHeaderText != null)
      this.mHeaderText.setText(this.mRefreshingLabel);
    refreshingImpl();
  }

  protected abstract void refreshingImpl();

  public final void releaseToRefresh()
  {
    if (this.mHeaderText != null)
      this.mHeaderText.setText(this.mReleaseLabel);
    releaseToRefreshImpl();
  }

  protected abstract void releaseToRefreshImpl();

  public final void reset()
  {
    if (this.mHeaderText != null)
      this.mHeaderText.setText(this.mPullLabel);
    resetImpl();
  }

  protected abstract void resetImpl();

  public void setPullLabel(CharSequence paramCharSequence)
  {
    this.mPullLabel = paramCharSequence;
  }

  public void setRefreshingLabel(CharSequence paramCharSequence)
  {
    this.mRefreshingLabel = paramCharSequence;
  }

  public void setReleaseLabel(CharSequence paramCharSequence)
  {
    this.mReleaseLabel = paramCharSequence;
  }

  public void setTextTypeface(Typeface paramTypeface)
  {
    this.mHeaderText.setTypeface(paramTypeface);
  }

  public final void showInvisibleViews()
  {
    if (4 == this.mHeaderText.getVisibility())
      this.mHeaderText.setVisibility(0);
    if (4 == this.mHeaderImage.getVisibility())
      this.mHeaderImage.setVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.LoadingLayout2
 * JD-Core Version:    0.6.2
 */