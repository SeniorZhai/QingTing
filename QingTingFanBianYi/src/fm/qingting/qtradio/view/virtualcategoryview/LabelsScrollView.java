package fm.qingting.qtradio.view.virtualcategoryview;

import android.content.Context;
import fm.qingting.framework.view.HorizontalScrollViewImpl;

class LabelsScrollView extends HorizontalScrollViewImpl
{
  private LabelsView mLabelsView;

  public LabelsScrollView(Context paramContext)
  {
    super(paramContext);
    setHorizontalScrollBarEnabled(false);
    this.mLabelsView = new LabelsView(paramContext);
    addView(this.mLabelsView);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mLabelsView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualcategoryview.LabelsScrollView
 * JD-Core Version:    0.6.2
 */