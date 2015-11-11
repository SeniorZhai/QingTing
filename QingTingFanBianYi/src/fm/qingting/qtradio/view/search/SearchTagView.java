package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

public class SearchTagView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 56, 720, 56, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mTag;
  private final ViewLayout tagLayout = this.itemLayout.createChildLT(540, 40, 40, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchTagView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mTag = new TextViewElement(paramContext);
    this.mTag.setColor(SkinManager.getTextColorNormal());
    this.mTag.setMaxLineLimit(1);
    this.mTag.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mTag);
    this.mTag.expandHotPot(ScreenConfiguration.getCustomExtraBound());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tagLayout.scaleToBounds(this.itemLayout);
    this.mTag.measure(this.tagLayout);
    this.mTag.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      paramString = (String)paramObject;
      this.mTag.setText(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchTagView
 * JD-Core Version:    0.6.2
 */