package fm.qingting.qtradio.view.education.balloon;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class TipView extends QtView
{
  private ImageViewElement mBgElement;
  private TextViewElement mTipElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(240, 78, 240, 78, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(240, 40, 0, 28, ViewLayout.SCALE_FLAG_SLTCW);

  public TipView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mBgElement = new ImageViewElement(paramContext);
    this.mBgElement.setImageRes(2130837598);
    addElement(this.mBgElement, i);
    this.mTipElement = new TextViewElement(paramContext);
    this.mTipElement.setColor(-1);
    this.mTipElement.setMaxLineLimit(1);
    this.mTipElement.setText("查看所有分类");
    this.mTipElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mTipElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.mBgElement.measure(this.standardLayout);
    this.mTipElement.measure(this.textLayout);
    this.mTipElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.balloon.TipView
 * JD-Core Version:    0.6.2
 */