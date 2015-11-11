package fm.qingting.qtradio.view.education.shareguide;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareInfoNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.utils.QTMSGManage;

public class ShareGuideView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(96, 96, 24, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkLayout = this.standardLayout.createChildLT(40, 40, 0, 50, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout jumpLayout = this.standardLayout.createChildLT(160, 50, 545, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ShareCheckBoxElement mCheckBoxElement;
  private TextViewElement mJumpElement;
  private RecommendItemNode mNode;
  private NetImageViewElement mPicElement;
  private ButtonViewElement mPlayButtonViewElement;
  private TextViewElement mShareElement;
  private ImageViewElement mSloganElement;
  private TextViewElement mTitleElement;
  private final ViewLayout picLayout = this.standardLayout.createChildLT(362, 362, 179, 40, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout roundLayout = this.standardLayout.createChildLT(10, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout shareLayout = this.standardLayout.createChildLT(300, 50, 10, 50, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout sloganLayout = this.standardLayout.createChildLT(330, 74, 195, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(400, 100, 160, 14, ViewLayout.SCALE_FLAG_SLTCW);

  public ShareGuideView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    int i = hashCode();
    this.mNode = InfoManager.getInstance().root().mShareInfoNode.getRecommendItemNode();
    this.mPicElement = new NetImageViewElement(paramContext);
    this.mPicElement.setLoadingImageRes(2130837907);
    this.mPicElement.setDefaultImageRes(2130837907);
    this.mPicElement.setRoundCorner(true);
    this.mPicElement.setRoundCornerColor(SkinManager.getBackgroundColor());
    this.mPicElement.setOnElementClickListener(this);
    if (this.mNode != null)
      this.mPicElement.setImageUrl(this.mNode.getApproximativeThumb(362, 362));
    addElement(this.mPicElement, i);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    if (this.mNode != null)
      this.mTitleElement.setText(this.mNode.name, false);
    this.mTitleElement.setMaxLineLimit(3);
    addElement(this.mTitleElement);
    this.mJumpElement = new TextViewElement(paramContext);
    this.mJumpElement.setColor(SkinManager.getTextColorSubInfo());
    this.mJumpElement.setText("跳过>>", false);
    this.mJumpElement.setMaxLineLimit(1);
    this.mJumpElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mJumpElement);
    this.mJumpElement.setOnElementClickListener(this);
    this.mShareElement = new TextViewElement(paramContext);
    this.mShareElement.setColor(SkinManager.getTextColorSubInfo());
    this.mShareElement.setText("让好友们也听一听", false);
    this.mShareElement.setMaxLineLimit(1);
    addElement(this.mShareElement);
    this.mSloganElement = new ImageViewElement(paramContext);
    this.mSloganElement.setImageRes(2130837807);
    addElement(this.mSloganElement, i);
    this.mPlayButtonViewElement = new ButtonViewElement(paramContext);
    this.mPlayButtonViewElement.setBackground(2130837806, 2130837805);
    this.mPlayButtonViewElement.setOnElementClickListener(this);
    addElement(this.mPlayButtonViewElement, i);
    this.mCheckBoxElement = new ShareCheckBoxElement(paramContext);
    this.mCheckBoxElement.check(false);
    addElement(this.mCheckBoxElement);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    boolean bool = false;
    if (!InfoManager.getInstance().root().mShareInfoNode.hasUpdate())
      SharedCfg.getInstance().setRecommendShareUpdate(false);
    if (((this.mPlayButtonViewElement == paramViewElement) || (this.mPicElement == paramViewElement)) && (this.mNode != null))
    {
      ControllerManager.getInstance().openControllerByRecommendNode(this.mNode);
      if (!this.mCheckBoxElement.isChecked())
      {
        dispatchActionEvent("recommendShare", null);
        QTMSGManage.getInstance().sendStatistcsMessage("PlayRecommendShare", this.mNode.name + "_" + String.valueOf(bool));
      }
    }
    while ((this.mJumpElement != paramViewElement) || (this.mNode == null))
      while (true)
      {
        return;
        dispatchActionEvent("recommendShare", this.mNode);
        bool = true;
        QTMSGManage.getInstance().sendStatistcsMessage("ClickRecommendShare", this.mNode.name);
      }
    dispatchActionEvent("jumpShare", null);
    QTMSGManage.getInstance().sendStatistcsMessage("SkipRecommendShare", this.mNode.name);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.standardLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.jumpLayout.scaleToBounds(this.standardLayout);
    this.shareLayout.scaleToBounds(this.standardLayout);
    this.sloganLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.checkLayout.scaleToBounds(this.standardLayout);
    paramInt1 = (this.standardLayout.height - this.shareLayout.getBottom() - this.picLayout.height) / 2 - this.picLayout.topMargin;
    this.mPicElement.measure(this.picLayout.leftMargin, paramInt1, this.picLayout.getRight(), this.picLayout.height + paramInt1);
    this.mPicElement.setRoundCorner(this.roundLayout.width);
    this.mTitleElement.measure(this.titleLayout.leftMargin, this.picLayout.height + paramInt1 + this.titleLayout.topMargin, this.titleLayout.getRight(), this.picLayout.height + paramInt1 + this.titleLayout.getBottom());
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mJumpElement.measure(this.jumpLayout.leftMargin, (this.picLayout.height - this.jumpLayout.height) / 2 + paramInt1, this.jumpLayout.getRight(), (this.picLayout.height + this.jumpLayout.height) / 2 + paramInt1);
    this.mJumpElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mSloganElement.measure(this.sloganLayout.leftMargin, paramInt1 - this.sloganLayout.getBottom(), this.sloganLayout.getRight(), paramInt1 - this.sloganLayout.topMargin);
    this.mPlayButtonViewElement.measure(this.picLayout.getRight() - this.buttonLayout.getRight(), this.picLayout.height + paramInt1 - this.buttonLayout.getBottom(), this.picLayout.getRight() - this.buttonLayout.leftMargin, paramInt1 + this.picLayout.height - this.buttonLayout.topMargin);
    this.mCheckBoxElement.measure(this.checkLayout.leftMargin, this.standardLayout.height - this.checkLayout.getBottom(), this.checkLayout.getRight(), this.standardLayout.height - this.checkLayout.topMargin);
    this.mShareElement.measure(this.checkLayout.getRight() + this.shareLayout.leftMargin, this.standardLayout.height - this.checkLayout.topMargin - (this.checkLayout.height + this.shareLayout.height) / 2, this.checkLayout.getRight() + this.shareLayout.width + this.shareLayout.leftMargin, this.standardLayout.height - this.checkLayout.topMargin - (this.checkLayout.height - this.shareLayout.height) / 2);
    this.mShareElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    paramInt1 = (this.standardLayout.width - this.mCheckBoxElement.getWidth() - this.mShareElement.getWidth() - this.shareLayout.leftMargin) / 2;
    this.mCheckBoxElement.setTranslationX(paramInt1);
    this.mShareElement.setTranslationX(paramInt1);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.shareguide.ShareGuideView
 * JD-Core Version:    0.6.2
 */