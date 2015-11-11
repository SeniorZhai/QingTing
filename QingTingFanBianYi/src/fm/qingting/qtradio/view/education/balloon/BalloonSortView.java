package fm.qingting.qtradio.view.education.balloon;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class BalloonSortView extends ViewGroupViewImpl
{
  private SortTipView mTipView;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(260, 100, 260, 100, 0, 0, ViewLayout.FILL);
  private ViewLayout tipLayout = this.standardLayout.createChildLT(260, 78, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public BalloonSortView(Context paramContext)
  {
    super(paramContext);
    this.mTipView = new SortTipView(paramContext);
    addView(this.mTipView);
    paramContext = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 0.1F);
    paramContext.setInterpolator(new AccelerateDecelerateInterpolator());
    paramContext.setRepeatMode(2);
    paramContext.setRepeatCount(-1);
    paramContext.setDuration(250L);
    this.mTipView.startAnimation(paramContext);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.tipLayout.layoutView(this.mTipView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tipLayout.scaleToBounds(this.standardLayout);
    this.tipLayout.measureView(this.mTipView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.balloon.BalloonSortView
 * JD-Core Version:    0.6.2
 */