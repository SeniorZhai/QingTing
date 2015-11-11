package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class SharePopView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout containerLayout = this.standardLayout.createChildLT(720, 420, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ShareScrollView mActionsContainerView;
  private OtherActionsView mOtherActionsView;
  private final ViewLayout scrollLayout = this.containerLayout.createChildLT(720, 220, 0, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public SharePopView(Context paramContext)
  {
    super(paramContext);
    this.mOtherActionsView = new OtherActionsView(paramContext);
    addView(this.mOtherActionsView);
    this.mOtherActionsView.setEventHandler(this);
    this.mActionsContainerView = new ShareScrollView(paramContext);
    addView(this.mActionsContainerView);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.standardLayout.height - this.containerLayout.height))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("cancelPop"))
      dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mOtherActionsView.layout(0, this.standardLayout.height - this.containerLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mActionsContainerView.layout(0, this.standardLayout.height - this.containerLayout.height + this.scrollLayout.topMargin, this.standardLayout.width, this.standardLayout.height - this.containerLayout.height + this.scrollLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.containerLayout.scaleToBounds(this.standardLayout);
    this.scrollLayout.scaleToBounds(this.containerLayout);
    this.containerLayout.measureView(this.mOtherActionsView);
    this.scrollLayout.measureView(this.mActionsContainerView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mActionsContainerView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.SharePopView
 * JD-Core Version:    0.6.2
 */