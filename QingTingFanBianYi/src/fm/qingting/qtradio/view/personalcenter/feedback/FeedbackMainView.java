package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class FeedbackMainView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout bottomLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 106, 720, 1200, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private FeedbackListView chatListView;
  private FeedbackInputView inputFrameView;
  private int scrollY = 0;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public FeedbackMainView(Context paramContext)
  {
    super(paramContext);
    this.chatListView = new FeedbackListView(paramContext);
    this.chatListView.setEventHandler(this);
    addView(this.chatListView);
    this.inputFrameView = new FeedbackInputView(paramContext);
    addView(this.inputFrameView);
    this.inputFrameView.setEventHandler(this);
  }

  private void layoutViews(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      this.chatListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.bottomLayout.height);
      this.inputFrameView.layout(0, this.standardLayout.height - this.bottomLayout.height, this.standardLayout.width, this.standardLayout.height);
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
        localIllegalStateException.printStackTrace();
    }
  }

  public void close(boolean paramBoolean)
  {
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("sendDiscuss"))
      dispatchActionEvent(paramString, paramObject2);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("scrollTo"))
      {
        this.chatListView.scrollTo(0, this.scrollY);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("needAccount"));
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layoutViews(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.bottomLayout.scaleToBounds(this.standardLayout);
    this.bottomLayout.measureView(this.inputFrameView);
    this.chatListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.bottomLayout.height, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.chatListView.update(paramString, paramObject);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("refresh"))
      {
        this.chatListView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("closeKeyboard"));
    this.inputFrameView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackMainView
 * JD-Core Version:    0.6.2
 */