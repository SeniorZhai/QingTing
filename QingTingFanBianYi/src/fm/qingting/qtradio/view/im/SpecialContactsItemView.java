package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.UserProfile;

public class SpecialContactsItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 620, 50, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(92, 92, 29, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(470, 45, 150, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrowElement;
  private ImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private TextViewElement mInfoElement;
  private SpecialContactsItem mItem;
  private TextViewElement mTitleElement;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(470, 45, 150, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public SpecialContactsItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mAvatarElement = new ImageViewElement(paramContext);
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setMaxLineLimit(1);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mArrowElement = new ImageViewElement(paramContext);
    this.mArrowElement.setImageRes(2130837697);
    addElement(this.mArrowElement, paramInt);
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

  public void onElementClick(ViewElement paramViewElement)
  {
    switch (this.mItem.type)
    {
    default:
      return;
    case 0:
      ControllerManager.getInstance().openMyGroupsController(InfoManager.getInstance().getUserProfile().getGroups());
      return;
    case 1:
      ControllerManager.getInstance().openImContactSpecificController(true);
      return;
    case 2:
      ControllerManager.getInstance().openImContactSpecificController(false);
      return;
    case 3:
    }
    ControllerManager.getInstance().openBlockedMembersController();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mArrowElement.measure(this.arrowLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mItem = ((SpecialContactsItem)paramObject);
      this.mInfoElement.setText(this.mItem.getContactsDesc(), false);
      this.mTitleElement.setText(this.mItem.name, false);
      this.mAvatarElement.setImageRes(this.mItem.resource);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.SpecialContactsItemView
 * JD-Core Version:    0.6.2
 */