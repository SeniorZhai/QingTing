package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import fm.qingting.framework.view.AbsCheckBoxElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

class SmallCheckElement extends AbsCheckBoxElement
{
  private ViewLayout checkLayout;
  private ViewLayout imageLayout;
  private ViewLayout itemLayout;
  private ImageViewElement mCheckElement;
  private ImageViewElement mImageViewElement;
  private ImageViewElement mMarkElement;
  private boolean mMarkEnabled;
  private TextViewElement mNameElement;
  private ViewLayout markLayout;
  private ViewLayout nameLayout;

  public SmallCheckElement(Context paramContext)
  {
    super(paramContext);
    int j;
    label24: ViewLayout localViewLayout;
    if (ScreenConfiguration.isUltraWideScreen())
    {
      i = 76;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label320;
      j = 76;
      this.itemLayout = ViewLayout.createViewLayoutWithBoundsLT(180, i, 180, j, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.itemLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label327;
      i = 8;
      label59: this.imageLayout = localViewLayout.createChildLT(150, 60, 15, i, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.itemLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label333;
      i = 0;
      label91: this.checkLayout = localViewLayout.createChildLT(40, 40, 5, i, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.itemLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label338;
      i = 0;
      label121: this.markLayout = localViewLayout.createChildLT(48, 48, 131, i, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.itemLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label343;
    }
    label320: label327: label333: label338: label343: for (int i = 18; ; i = 20)
    {
      this.nameLayout = localViewLayout.createChildLT(150, 40, 15, i, ViewLayout.SCALE_FLAG_SLTCW);
      this.mMarkEnabled = false;
      this.mImageViewElement = new ImageViewElement(paramContext);
      this.mImageViewElement.setImageRes(2130837536);
      this.mImageViewElement.setBelonging(this);
      this.mNameElement = new TextViewElement(paramContext);
      this.mNameElement.setMaxLineLimit(1);
      this.mNameElement.setColor(SkinManager.getTextColorSubInfo());
      this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mNameElement.setBelonging(this);
      this.mCheckElement = new ImageViewElement(paramContext);
      this.mCheckElement.setImageRes(2130838001);
      this.mCheckElement.setBelonging(this);
      this.mMarkElement = new ImageViewElement(paramContext);
      this.mMarkElement.setImageRes(2130837999);
      this.mMarkElement.setBelonging(this);
      return;
      i = 80;
      break;
      j = 80;
      break label24;
      i = 10;
      break label59;
      i = 2;
      break label91;
      i = 2;
      break label121;
    }
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mImageViewElement.setTranslationX(i);
    this.mCheckElement.setTranslationX(i);
    this.mNameElement.setTranslationX(i);
    this.mMarkElement.setTranslationX(i);
    this.mImageViewElement.setTranslationY(j);
    this.mCheckElement.setTranslationY(j);
    this.mNameElement.setTranslationY(j);
    this.mMarkElement.setTranslationY(j);
    this.mImageViewElement.draw(paramCanvas);
    this.mNameElement.draw(paramCanvas);
    if (isChecked())
      this.mCheckElement.draw(paramCanvas);
    if (this.mMarkEnabled)
      this.mMarkElement.draw(paramCanvas);
  }

  String getLabel()
  {
    return this.mNameElement.getText();
  }

  protected void onCheckChanged(boolean paramBoolean)
  {
    Object localObject = this.mNameElement;
    if (isChecked())
    {
      i = SkinManager.getTextColorHighlight();
      ((TextViewElement)localObject).setColor(i);
      localObject = this.mImageViewElement;
      if (!isChecked())
        break label54;
    }
    label54: for (int i = 2130837538; ; i = 2130837536)
    {
      ((ImageViewElement)localObject).setImageRes(i);
      super.onCheckChanged(paramBoolean);
      return;
      i = SkinManager.getTextColorSubInfo();
      break;
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.imageLayout.scaleToBounds(this.itemLayout);
    this.checkLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.markLayout.scaleToBounds(this.itemLayout);
    this.mImageViewElement.measure(this.imageLayout);
    this.mCheckElement.measure(this.checkLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mMarkElement.measure(this.markLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
  }

  void setMarkEnabled(boolean paramBoolean)
  {
    this.mMarkEnabled = paramBoolean;
    ImageViewElement localImageViewElement = this.mMarkElement;
    if (paramBoolean);
    for (int i = 0; ; i = 4)
    {
      localImageViewElement.setVisible(i);
      return;
    }
  }

  void setParam(String paramString)
  {
    this.mNameElement.setText(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.SmallCheckElement
 * JD-Core Version:    0.6.2
 */