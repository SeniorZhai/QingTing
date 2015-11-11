package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class NovelTagView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 72, 720, 72, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(5, 32, 18, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mLabel;
  private TextViewElement mTag;
  private final ViewLayout tagLayout = this.itemLayout.createChildLT(540, 40, 38, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public NovelTagView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mLabel = new ImageViewElement(paramContext);
    this.mLabel.setImageRes(2130837723);
    addElement(this.mLabel);
    this.mTag = new TextViewElement(paramContext);
    this.mTag.setColor(SkinManager.getTextColorNormal());
    this.mTag.setMaxLineLimit(1);
    this.mTag.setText("内容分类");
    this.mTag.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mTag);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tagLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.mLabel.measure(this.labelLayout.leftMargin, this.labelLayout.topMargin, this.labelLayout.getRight(), this.labelLayout.getBottom());
    this.mTag.measure(this.tagLayout);
    this.mTag.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.NovelTagView
 * JD-Core Version:    0.6.2
 */