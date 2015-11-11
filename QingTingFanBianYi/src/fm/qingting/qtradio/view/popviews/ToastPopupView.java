package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class ToastPopupView extends ViewGroupViewImpl
{
  private TextView mInfoView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ToastPopupView(Context paramContext)
  {
    super(paramContext);
    this.mInfoView = ((TextView)inflate(paramContext, 2130903066, null));
    addView(this.mInfoView);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = this.mInfoView.getMeasuredHeight();
    this.mInfoView.layout(paramInt1, paramInt4 - paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mInfoView.measure(paramInt1, 0);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ToastPopupView
 * JD-Core Version:    0.6.2
 */