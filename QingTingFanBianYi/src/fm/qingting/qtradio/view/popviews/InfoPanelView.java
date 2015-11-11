package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class InfoPanelView extends QtView
{
  private final ViewLayout checkLayout = this.standardLayout.createChildLT(40, 40, 40, 165, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout contentLayout = this.standardLayout.createChildLT(640, 50, 40, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private CheckBoxElementInternal mCheckBoxElement;
  private TextViewElement mContentElement;
  private TextViewElement mTipElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 300, 720, 300, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout tipLayout = this.standardLayout.createChildLT(500, 50, 100, 160, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 50, 0, 20, ViewLayout.SCALE_FLAG_SLTCW);

  public InfoPanelView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getPopBgColor());
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setText("添加收藏", false);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTitleElement);
    this.mContentElement = new TextViewElement(paramContext);
    this.mContentElement.setMaxLineLimit(2);
    this.mContentElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mContentElement);
    this.mCheckBoxElement = new CheckBoxElementInternal(paramContext);
    addElement(this.mCheckBoxElement, paramInt);
    this.mTipElement = new TextViewElement(paramContext);
    this.mTipElement.setMaxLineLimit(1);
    this.mTipElement.setText("不再提示", false);
    this.mTipElement.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mTipElement);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("isChecked"))
      return Boolean.valueOf(this.mCheckBoxElement.isChecked());
    return super.getValue(paramString, paramObject);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.contentLayout.scaleToBounds(this.standardLayout);
    this.checkLayout.scaleToBounds(this.standardLayout);
    this.tipLayout.scaleToBounds(this.standardLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mContentElement.measure(this.contentLayout);
    this.mCheckBoxElement.measure(this.checkLayout);
    this.mTipElement.measure(this.tipLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mContentElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mTipElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (String)paramObject;
      this.mContentElement.setText(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.InfoPanelView
 * JD-Core Version:    0.6.2
 */