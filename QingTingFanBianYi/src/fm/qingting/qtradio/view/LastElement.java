package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Canvas;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class LastElement extends ViewElement
{
  private ViewLayout arrowLayout = this.itemLayout.createChildLT(19, 32, 0, 4, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(300, 40, 300, 40, 0, 0, ViewLayout.FILL);
  private ImageViewElement mArrow;
  private TextViewElement mName;
  private ViewLayout textLayout = this.itemLayout.createChildLT(200, 40, 35, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public LastElement(Context paramContext)
  {
    super(paramContext);
    this.mArrow = new ImageViewElement(paramContext);
    this.mArrow.setImageRes(2130837997);
    this.mArrow.setBelonging(this);
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(1);
    this.mName.setColor(-8355712);
    this.mName.setText("上一步", false);
    this.mName.setBelonging(this);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mArrow.setTranslationX(i);
    this.mArrow.setTranslationY(j);
    this.mName.setTranslationX(i);
    this.mName.setTranslationY(j);
    this.mArrow.draw(paramCanvas);
    this.mName.draw(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.textLayout.scaleToBounds(this.itemLayout);
    this.mArrow.measure(this.arrowLayout);
    this.mName.measure(this.textLayout);
    this.mName.setTextSize(SkinManager.getInstance().getMiddleTextSize());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.LastElement
 * JD-Core Version:    0.6.2
 */