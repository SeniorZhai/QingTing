package fm.qingting.qtradio.view.popviews.categoryfilter;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class FilterTagView extends QtView
{
  private final ViewLayout boundLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 70, 720, 70, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.boundLayout.createChildLT(300, 40, 42, 30, ViewLayout.SCALE_FLAG_SLTCW);

  public FilterTagView(Context paramContext)
  {
    super(paramContext);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorRecommend());
    this.mNameElement.setMaxLineLimit(1);
    addElement(this.mNameElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.boundLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.boundLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.boundLayout.width, this.boundLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mNameElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.categoryfilter.FilterTagView
 * JD-Core Version:    0.6.2
 */