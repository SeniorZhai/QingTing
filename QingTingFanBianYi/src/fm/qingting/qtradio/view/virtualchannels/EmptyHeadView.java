package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class EmptyHeadView extends ViewImpl
{
  private int mExtraHeight = 0;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 315, 720, 315, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public EmptyHeadView(Context paramContext)
  {
    super(paramContext);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height + this.mExtraHeight);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("extraHeight"))
    {
      this.mExtraHeight = ((Integer)paramObject).intValue();
      requestLayout();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.EmptyHeadView
 * JD-Core Version:    0.6.2
 */