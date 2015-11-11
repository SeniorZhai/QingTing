package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.BlackListItem;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;

public class BlockedItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(90, 90, 30, 23, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout deleteLayout = this.itemLayout.createChildLT(50, 50, 630, 43, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private ButtonViewElement mDeleteElement;
  private BlackListItem mDtc;
  private TextViewElement mTitleElement;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(470, 45, 150, 45, ViewLayout.SCALE_FLAG_SLTCW);

  public BlockedItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setMaxLineLimit(1);
    addElement(this.mTitleElement);
    this.mDeleteElement = new ButtonViewElement(paramContext);
    this.mDeleteElement.setBackground(2130837753, 2130837752);
    addElement(this.mDeleteElement);
    this.mDeleteElement.setOnElementClickListener(this);
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
    EventDispacthManager.getInstance().dispatchAction("showBlockRemovePop", this.mDtc);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.deleteLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mDeleteElement.measure(this.deleteLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mDtc = ((BlackListItem)paramObject);
      this.mTitleElement.setText(this.mDtc.name, false);
      this.mAvatarElement.setImageUrl(this.mDtc.avatar);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.BlockedItemView
 * JD-Core Version:    0.6.2
 */