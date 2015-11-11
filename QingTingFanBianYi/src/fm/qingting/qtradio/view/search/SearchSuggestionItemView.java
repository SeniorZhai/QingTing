package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.search.SearchItemNode;

public class SearchSuggestionItemView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 112, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private String mName;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 68, 60, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchSuggestionItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 16777215);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (SearchSuggestionItemView.this.mName != null)
          SearchSuggestionItemView.this.dispatchActionEvent("search", SearchSuggestionItemView.this.mName);
      }
    });
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mNameElement);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mNameElement.measure(this.nameLayout.leftMargin, (this.itemLayout.height - this.nameLayout.height) / 2, this.nameLayout.getRight(), (this.itemLayout.height + this.nameLayout.height) / 2);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mName = ((SearchItemNode)paramObject).name;
      this.mNameElement.setText(this.mName);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchSuggestionItemView
 * JD-Core Version:    0.6.2
 */