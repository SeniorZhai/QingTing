package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.Layout.Alignment;
import fm.qingting.framework.view.AbsCheckBoxElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class CheckElement extends AbsCheckBoxElement
{
  private ViewLayout checkLayout = this.itemLayout.createChildLT(60, 60, 2, 4, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout imageLayout = this.itemLayout.createChildLT(206, 206, 5, 4, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(216, 280, 216, 280, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mCheckElement;
  private final Paint mCirclePaint = new Paint();
  private ImageViewElement mImageViewElement;
  private TextViewElement mNameElement;
  private ViewLayout nameLayout = this.itemLayout.createChildLT(206, 40, 5, 240, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout strokeWidthLayout = this.itemLayout.createChildLT(2, 2, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public CheckElement(Context paramContext)
  {
    super(paramContext);
    this.mImageViewElement = new ImageViewElement(paramContext);
    this.mImageViewElement.setBelonging(this);
    this.mCheckElement = new ImageViewElement(paramContext);
    this.mCheckElement.setImageRes(2130838000);
    this.mCheckElement.setBelonging(this);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setBelonging(this);
    this.mCirclePaint.setStyle(Paint.Style.STROKE);
  }

  private void drawCircle(Canvas paramCanvas)
  {
    Paint localPaint = this.mCirclePaint;
    if (isChecked());
    for (int i = SkinManager.getTextColorHighlight(); ; i = SkinManager.getTextColorSubInfo())
    {
      localPaint.setColor(i);
      paramCanvas.drawCircle(this.mImageViewElement.getLeftMargin() + this.mImageViewElement.getWidth() / 2.0F, this.mImageViewElement.getTopMargin() + this.mImageViewElement.getHeight() / 2.0F, this.mImageViewElement.getWidth() / 2.0F, this.mCirclePaint);
      return;
    }
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mImageViewElement.setTranslationX(i);
    this.mCheckElement.setTranslationX(i);
    this.mNameElement.setTranslationX(i);
    this.mImageViewElement.setTranslationY(j);
    this.mCheckElement.setTranslationY(j);
    this.mNameElement.setTranslationY(j);
    this.mImageViewElement.draw(paramCanvas);
    drawCircle(paramCanvas);
    if (isChecked())
      this.mCheckElement.draw(paramCanvas);
    this.mNameElement.draw(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.imageLayout.scaleToBounds(this.itemLayout);
    this.checkLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.strokeWidthLayout.scaleToBounds(this.itemLayout);
    this.mImageViewElement.measure(this.imageLayout);
    this.mCheckElement.measure(this.checkLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mCirclePaint.setStrokeWidth(this.strokeWidthLayout.width);
  }

  void setParam(String paramString, int paramInt)
  {
    this.mNameElement.setText(paramString);
    this.mImageViewElement.setImageRes(paramInt);
  }

  public void toggle(boolean paramBoolean)
  {
    if (isChecked())
      return;
    super.toggle(paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.CheckElement
 * JD-Core Version:    0.6.2
 */