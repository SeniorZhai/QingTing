package fm.qingting.qtradio.dongruan;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;

public class DongRuanMaskView extends QtView
{
  private ImageViewElement mMaskImageView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public DongRuanMaskView(Context paramContext)
  {
    super(paramContext);
    this.mMaskImageView = new ImageViewElement(paramContext);
    this.mMaskImageView.setImageRes(2130837596);
    addElement(this.mMaskImageView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mMaskImageView.measure(this.standardLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.dongruan.DongRuanMaskView
 * JD-Core Version:    0.6.2
 */