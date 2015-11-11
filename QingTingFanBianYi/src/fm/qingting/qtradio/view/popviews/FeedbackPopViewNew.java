package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class FeedbackPopViewNew extends ViewGroupViewImpl
{
  private PanelView mPanelView;
  private final ViewLayout panelLayout = this.standardLayout.createChildLT(720, 550, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public FeedbackPopViewNew(Context paramContext)
  {
    super(paramContext);
    this.mPanelView = new PanelView(paramContext);
    addView(this.mPanelView);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.standardLayout.height - this.panelLayout.height))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mPanelView.layout(0, this.standardLayout.height - this.panelLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.panelLayout.scaleToBounds(this.standardLayout);
    this.panelLayout.measureView(this.mPanelView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.FeedbackPopViewNew
 * JD-Core Version:    0.6.2
 */