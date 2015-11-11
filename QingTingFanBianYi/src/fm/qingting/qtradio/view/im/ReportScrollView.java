package fm.qingting.qtradio.view.im;

import android.content.Context;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ScrollViewImpl;

class ReportScrollView extends ScrollViewImpl
  implements IEventHandler
{
  private ReportDetailView mDetailView;

  public ReportScrollView(Context paramContext)
  {
    super(paramContext);
    this.mDetailView = new ReportDetailView(paramContext);
    addView(this.mDetailView);
    this.mDetailView.setEventHandler(this);
  }

  public void close(boolean paramBoolean)
  {
    this.mDetailView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("getTypeIndex"))
      return this.mDetailView.getValue(paramString, paramObject);
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mDetailView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportScrollView
 * JD-Core Version:    0.6.2
 */