package fm.qingting.qtradio.view.im;

import android.content.Context;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.LinearLayoutViewImpl;

class EntrySectionView extends LinearLayoutViewImpl
  implements IEventHandler
{
  private final String[] REPORT_ENTRY = { "欺诈骗钱", "色情暴力", "广告骚扰", "其他" };
  private int mCheckIndex = 0;
  private ReportItemView[] mItemViews;

  public EntrySectionView(Context paramContext)
  {
    super(paramContext);
    int j = hashCode();
    setBackgroundColor(-1);
    setOrientation(1);
    this.mItemViews = new ReportItemView[this.REPORT_ENTRY.length];
    int i = 0;
    while (i < this.REPORT_ENTRY.length)
    {
      ReportItemView localReportItemView = new ReportItemView(paramContext, j);
      localReportItemView.update("setData", this.REPORT_ENTRY[i]);
      localReportItemView.setEventHandler(this);
      addView(localReportItemView);
      this.mItemViews[i] = localReportItemView;
      i += 1;
    }
    this.mItemViews[0].update("checkState", Boolean.valueOf(true));
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("getTypeIndex"))
      return Integer.valueOf(this.mCheckIndex);
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("check"))
    {
      int i = 0;
      if (i < this.mItemViews.length)
      {
        if (paramObject1 == this.mItemViews[i])
        {
          this.mItemViews[i].update("checkState", Boolean.valueOf(true));
          this.mCheckIndex = i;
        }
        while (true)
        {
          i += 1;
          break;
          this.mItemViews[i].update("checkState", Boolean.valueOf(false));
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.EntrySectionView
 * JD-Core Version:    0.6.2
 */