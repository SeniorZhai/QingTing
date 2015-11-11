package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.utils.ScreenConfiguration;

public class DiscoverItemTagView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(17, 30, 683, 23, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 56, 720, 56, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(5, 32, 18, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrow;
  private ImageViewElement mLabel;
  private TextViewElement mMore;
  private RecommendItemNode mNode;
  private TextViewElement mTag;
  private final ViewLayout moreLayout = this.itemLayout.createChildLT(150, 40, 523, 18, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout tagLayout = this.itemLayout.createChildLT(540, 40, 38, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverItemTagView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLabel = new ImageViewElement(paramContext);
    this.mLabel.setImageRes(2130837723);
    addElement(this.mLabel);
    this.mTag = new TextViewElement(paramContext);
    this.mTag.setColor(SkinManager.getTextColorNormal());
    this.mTag.setMaxLineLimit(1);
    this.mTag.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mTag);
    this.mTag.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    this.mMore = new TextViewElement(paramContext);
    this.mMore.setColor(SkinManager.getTextColorHighlight());
    this.mMore.setMaxLineLimit(1);
    this.mMore.setText("更多", false);
    this.mMore.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    addElement(this.mMore);
    this.mMore.setOnElementClickListener(this);
    this.mArrow = new ImageViewElement(paramContext);
    this.mArrow.setImageRes(2130837698);
    addElement(this.mArrow, paramInt);
    this.mArrow.setOnElementClickListener(this);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    PlayerAgent.getInstance().addPlaySource(34);
    if (this.mNode.mCategoryId == 521);
    ControllerManager.getInstance().openVirtualCategoryAllContentController(this.mNode.mCategoryId, this.mNode);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tagLayout.scaleToBounds(this.itemLayout);
    this.moreLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.mArrow.measure(this.arrowLayout);
    this.mLabel.measure(this.labelLayout.leftMargin, this.labelLayout.topMargin, this.labelLayout.getRight(), this.labelLayout.getBottom());
    this.mTag.measure(this.tagLayout);
    this.mTag.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mMore.measure(this.moreLayout);
    this.mMore.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((RecommendItemNode)paramObject);
      this.mTag.setText(this.mNode.belongName, false);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverItemTagView
 * JD-Core Version:    0.6.2
 */