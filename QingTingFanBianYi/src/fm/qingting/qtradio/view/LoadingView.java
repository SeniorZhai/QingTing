package fm.qingting.qtradio.view;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class LoadingView extends ViewGroupViewImpl
{
  static final int ROTATION_ANIMATION_DURATION = 1200;
  private final ViewLayout imageLayout = this.standardLayout.createChildLT(50, 50, 335, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageView mHeaderImage;
  private final Animation mRotateAnimation = new RotateAnimation(0.0F, 720.0F, 1, 0.5F, 1, 0.5F);
  private TextView mTextView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(200, 50, 260, 70, ViewLayout.SCALE_FLAG_SLTCW);

  public LoadingView(Context paramContext)
  {
    super(paramContext);
    this.mRotateAnimation.setInterpolator(new LinearInterpolator());
    this.mRotateAnimation.setDuration(1200L);
    this.mRotateAnimation.setRepeatCount(-1);
    this.mRotateAnimation.setRepeatMode(1);
    this.mHeaderImage = new ImageView(paramContext);
    this.mHeaderImage.setBackgroundResource(2130837795);
    this.mHeaderImage.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mHeaderImage);
    this.mTextView = new TextView(paramContext);
    this.mTextView.setTextColor(SkinManager.getTextColorThirdLevel());
    this.mTextView.setGravity(17);
    this.mTextView.setText("努力加载中...");
    addView(this.mTextView);
    startLoading();
  }

  private void startLoading()
  {
    this.mHeaderImage.startAnimation(this.mRotateAnimation);
  }

  private void stopLoading()
  {
    this.mHeaderImage.clearAnimation();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.imageLayout.layoutView(this.mHeaderImage);
    this.textLayout.layoutView(this.mTextView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.imageLayout.scaleToBounds(this.standardLayout);
    this.textLayout.measureView(this.mTextView);
    this.imageLayout.measureView(this.mHeaderImage);
    this.mTextView.setTextSize(0, SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    if (paramInt == 8)
      stopLoading();
    while (true)
    {
      super.onVisibilityChanged(paramView, paramInt);
      return;
      startLoading();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.LoadingView
 * JD-Core Version:    0.6.2
 */