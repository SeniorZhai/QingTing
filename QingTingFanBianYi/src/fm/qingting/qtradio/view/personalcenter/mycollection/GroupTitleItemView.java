package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

public class GroupTitleItemView extends QtView
{
  private final ViewLayout indentLayout = this.itemLayout.createChildLT(6, 35, 20, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 68, 720, 68, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 67, ViewLayout.SCALE_FLAG_SLTCW);
  private LineElement mIndentLine;
  private TextViewElement mTitleElement;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(720, 45, 35, 9, ViewLayout.SCALE_FLAG_SLTCW);

  public GroupTitleItemView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTitleElement);
    this.mIndentLine = new LineElement(paramContext);
    this.mIndentLine.setOrientation(0);
    this.mIndentLine.setColor(SkinManager.getTextColorHighlight2());
    addElement(this.mIndentLine);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.indentLayout.scaleToBounds(this.itemLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mTitleElement.measure(this.titleLayout);
    this.mIndentLine.measure(this.indentLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void setTitle(String paramString)
  {
    TextViewElement localTextViewElement = this.mTitleElement;
    String str = paramString;
    if (paramString == null)
      str = "";
    localTextViewElement.setText(str);
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.GroupTitleItemView
 * JD-Core Version:    0.6.2
 */