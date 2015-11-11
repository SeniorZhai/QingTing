package fm.qingting.qtradio.view.im;

import android.content.Context;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.LinearLayoutViewImpl;

class ReportDetailView extends LinearLayoutViewImpl
  implements IEventHandler
{
  private ReportConfirmView mConfirmView;
  private ReportDescView mDescView;
  private ReportTagView mReportContent;
  private EntrySectionView mSectionView;

  public ReportDetailView(Context paramContext)
  {
    super(paramContext);
    setOrientation(1);
    this.mReportContent = new ReportTagView(paramContext);
    addView(this.mReportContent);
    this.mDescView = new ReportDescView(paramContext);
    addView(this.mDescView);
    this.mSectionView = new EntrySectionView(paramContext);
    addView(this.mSectionView);
    this.mConfirmView = new ReportConfirmView(paramContext);
    addView(this.mConfirmView);
    this.mConfirmView.setEventHandler(this);
  }

  public void close(boolean paramBoolean)
  {
    this.mReportContent.close(paramBoolean);
    this.mDescView.close(paramBoolean);
    this.mSectionView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("getTypeIndex"))
      return this.mSectionView.getValue(paramString, paramObject);
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    dispatchActionEvent(paramString, this.mSectionView.getValue("getTypeIndex", null));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mDescView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportDetailView
 * JD-Core Version:    0.6.2
 */