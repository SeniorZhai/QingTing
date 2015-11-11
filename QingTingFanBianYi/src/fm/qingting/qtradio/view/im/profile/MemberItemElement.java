package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;

class MemberItemElement extends ViewElement
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 460, 42, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(90, 90, 0, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(350, 45, 110, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(520, 120, 520, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrowElement;
  private RoundAvatarElement mAvatarElement;
  private TextViewElement mInfoElement;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(350, 45, 110, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public MemberItemElement(Context paramContext)
  {
    super(paramContext);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    this.mAvatarElement.setBelonging(this);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setBelonging(this);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    this.mInfoElement.setBelonging(this);
    this.mArrowElement = new ImageViewElement(paramContext);
    this.mArrowElement.setImageRes(2130837697);
    this.mArrowElement.setBelonging(this);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mAvatarElement.setTranslationX(i);
    this.mAvatarElement.setTranslationY(j);
    this.mNameElement.setTranslationX(i);
    this.mNameElement.setTranslationY(j);
    this.mInfoElement.setTranslationX(i);
    this.mInfoElement.setTranslationY(j);
    this.mArrowElement.setTranslationX(i);
    this.mArrowElement.setTranslationY(j);
    this.mAvatarElement.draw(paramCanvas);
    this.mNameElement.draw(paramCanvas);
    this.mInfoElement.draw(paramCanvas);
    this.mArrowElement.draw(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mArrowElement.measure(this.arrowLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
  }

  public void setData(UserInfo paramUserInfo)
  {
    this.mAvatarElement.setImageUrl(paramUserInfo.snsInfo.sns_avatar);
    this.mNameElement.setText(paramUserInfo.snsInfo.sns_name, false);
    this.mInfoElement.setText(paramUserInfo.snsInfo.signature);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.MemberItemElement
 * JD-Core Version:    0.6.2
 */