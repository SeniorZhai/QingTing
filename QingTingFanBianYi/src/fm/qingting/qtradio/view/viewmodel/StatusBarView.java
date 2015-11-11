package fm.qingting.qtradio.view.viewmodel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewImpl;

class StatusBarView extends ViewImpl
{
  @TargetApi(19)
  public StatusBarView(Context paramContext)
  {
    super(paramContext);
    setBackground(paramContext.getResources().getDrawable(2130837872));
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.viewmodel.StatusBarView
 * JD-Core Version:    0.6.2
 */