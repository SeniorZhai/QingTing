package fm.qingting.qtradio.view.link;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.LinkManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.utils.QTMSGManage;

public class CustomLinkView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout arrowLayout = this.standardLayout.createChildLT(20, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout avatarLayout = this.standardLayout.createChildLT(116, 116, 17, 17, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout cancelLayout = this.standardLayout.createChildLT(64, 64, 650, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.standardLayout.createChildLT(60, 34, 150, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private LinkArrowElement mArrowElement;
  private int mArrowX;
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBgElement;
  private ImageViewElement mCancelElement;
  private ButtonViewElement mLabelElement;
  private RecommendItemNode mNode;
  private TextViewElement mTitleElement;
  private final ViewLayout roundLayout = this.standardLayout.createChildLT(3, 3, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 150, 720, 150, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(500, 150, 150, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public CustomLinkView(Context paramContext, int paramInt)
  {
    super(paramContext);
    int i = hashCode();
    this.mBgElement = new ButtonViewElement(paramContext);
    this.mBgElement.setBackgroundColor(-1308622848, -1308622848);
    this.mBgElement.setOnElementClickListener(this);
    addElement(this.mBgElement);
    this.mAvatarElement = new NetImageViewElement(paramContext);
    addElement(this.mAvatarElement, i);
    this.mLabelElement = new ButtonViewElement(paramContext);
    this.mLabelElement.setBackgroundColor(SkinManager.getBackgroundColor(), SkinManager.getBackgroundColor());
    this.mLabelElement.setRoundCorner(true);
    this.mLabelElement.setTextColor(SkinManager.getTextColorNormal());
    this.mLabelElement.setText("音频");
    addElement(this.mLabelElement, i);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(2);
    this.mTitleElement.setColor(-1);
    this.mTitleElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mTitleElement.setFirstIndentation(2);
    addElement(this.mTitleElement);
    this.mCancelElement = new ImageViewElement(paramContext);
    this.mCancelElement.setImageRes(2130837931);
    addElement(this.mCancelElement, i);
    this.mArrowElement = new LinkArrowElement(paramContext, paramInt);
    addElement(this.mArrowElement);
    if (this.mArrowX == 0)
      this.mArrowElement.setVisible(4);
    this.mCancelElement.setOnElementClickListener(this);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mCancelElement)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("showLink", "close");
      LinkManager.cancelLinkIfExists(getContext());
    }
    while (paramViewElement != this.mBgElement)
      return;
    QTMSGManage.getInstance().sendStatistcsMessage("showLink", "click");
    ControllerManager.getInstance().openControllerByRecommendNode(this.mNode);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.standardLayout);
    this.labelLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.cancelLayout.scaleToBounds(this.standardLayout);
    this.arrowLayout.scaleToBounds(this.standardLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mLabelElement.measure(this.labelLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mCancelElement.measure(this.cancelLayout);
    this.mLabelElement.setRoundCornerRadius(this.roundLayout.width);
    this.mLabelElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    paramInt2 = this.standardLayout.height;
    paramInt1 = this.mTitleElement.getHeight();
    if (paramInt1 < this.titleLayout.height)
    {
      paramInt1 = (paramInt2 - paramInt1) / 2;
      this.mTitleElement.setTranslationY(paramInt1);
      this.mLabelElement.setTranslationY(paramInt1);
    }
    this.mBgElement.measure(0, 0, this.standardLayout.width, paramInt2);
    paramInt1 = paramInt2;
    if (this.mArrowX > 0)
    {
      this.mArrowElement.measure(this.mArrowX - this.arrowLayout.width / 2, paramInt2, this.mArrowX + this.arrowLayout.width / 2, this.arrowLayout.height + paramInt2);
      paramInt1 = paramInt2 + this.arrowLayout.height;
    }
    setMeasuredDimension(this.standardLayout.width, paramInt1);
  }

  public void update(String paramString, Object paramObject)
  {
    if ((!paramString.equalsIgnoreCase("setData")) || (paramObject == null))
      return;
    this.mNode = ((RecommendItemNode)paramObject);
    this.mAvatarElement.setImageUrl(this.mNode.getApproximativeThumb(116, 116));
    this.mTitleElement.setText(this.mNode.name, true);
    if ((this.mNode.mNode instanceof ActivityNode))
    {
      this.mAvatarElement.setDefaultImageRes(2130837757);
      this.mLabelElement.setText("链接");
      return;
    }
    this.mAvatarElement.setDefaultImageRes(2130837756);
    this.mLabelElement.setText("音频");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.link.CustomLinkView
 * JD-Core Version:    0.6.2
 */