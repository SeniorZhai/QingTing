package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.manager.SkinManager;

public class ReportView extends ViewGroupViewImpl
  implements IEventHandler
{
  private ReportScrollView mScrollView;
  private ReportSuccessView mSuccessView;

  public ReportView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mScrollView = new ReportScrollView(paramContext);
    addView(this.mScrollView);
    this.mScrollView.setEventHandler(this);
    this.mSuccessView = new ReportSuccessView(paramContext);
    addView(this.mSuccessView);
    this.mSuccessView.setVisibility(4);
  }

  public void close(boolean paramBoolean)
  {
    this.mScrollView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mScrollView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mSuccessView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mScrollView.measure(paramInt1, paramInt2);
    this.mSuccessView.measure(paramInt1, paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mScrollView.update(paramString, paramObject);
    while (!paramString.equalsIgnoreCase("reportSuccess"))
      return;
    this.mSuccessView.setVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportView
 * JD-Core Version:    0.6.2
 */