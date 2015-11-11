package fm.qingting.qtradio.view.categoryorder;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

class SectionTagView extends QtView
{
  private ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private LineElement mLineElement;
  private TextViewElement mTitle;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 110, 720, 110, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout titleLayout = this.standardLayout.createChildLT(600, 60, 40, 25, ViewLayout.SCALE_FLAG_SLTCW);

  public SectionTagView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mTitle = new TextViewElement(paramContext);
    this.mTitle.setColor(SkinManager.getTextColorNormal());
    this.mTitle.setMaxLineLimit(1);
    addElement(this.mTitle);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLineElement.measure(0, this.standardLayout.height - this.lineLayout.height, this.lineLayout.width, this.standardLayout.height);
    this.mTitle.measure(this.titleLayout);
    this.mTitle.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  protected void setTitle(String paramString)
  {
    this.mTitle.setText(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.categoryorder.SectionTagView
 * JD-Core Version:    0.6.2
 */