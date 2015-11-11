package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.BaseUserInfoPool;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.utils.TimeUtil;

public class ReportDescView extends QtView
{
  private final ViewLayout avatarLayout = this.standardLayout.createChildLT(67, 67, 40, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bgLayout = this.standardLayout.createChildLT(680, 180, 20, 36, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout contentLayout = this.standardLayout.createChildLT(660, 45, 30, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout dateLayout = this.standardLayout.createChildLT(300, 45, 140, 31, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private TextViewElement mContentElement;
  private TextViewElement mDateElement;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(400, 45, 120, 31, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 200, 720, 200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ReportDescView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(-2565928, -2565928);
    addElement(this.mBg);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(-10789792);
    addElement(this.mNameElement);
    this.mDateElement = new TextViewElement(paramContext);
    this.mDateElement.setColor(-10789792);
    this.mDateElement.setMaxLineLimit(1);
    addElement(this.mDateElement);
    this.mContentElement = new TextViewElement(paramContext);
    this.mContentElement.setMaxLineLimit(1);
    this.mContentElement.setColor(-10789792);
    addElement(this.mContentElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.dateLayout.scaleToBounds(this.standardLayout);
    this.contentLayout.scaleToBounds(this.standardLayout);
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.avatarLayout.scaleToBounds(this.standardLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mDateElement.measure(this.dateLayout);
    this.mContentElement.measure(this.contentLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mDateElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mContentElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mDateElement.setTranslationX(this.mNameElement.getWidth());
    this.mBg.measure(this.bgLayout.leftMargin, 0, this.bgLayout.getRight(), this.avatarLayout.getBottom() + this.mContentElement.getHeight() + this.avatarLayout.topMargin);
    setMeasuredDimension(this.standardLayout.width, this.avatarLayout.getBottom() + this.mContentElement.getHeight() + this.bgLayout.topMargin + this.avatarLayout.topMargin);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (IMMessage)paramObject;
      this.mNameElement.setText(paramString.mFromName, false);
      this.mDateElement.setText(TimeUtil.msToDate2(paramString.publish * 1000L), false);
      this.mContentElement.setText(paramString.mMessage);
      paramObject = paramString.mFromAvatar;
      if ((paramObject != null) && (paramObject.length() != 0))
        break label93;
      paramString = BaseUserInfoPool.getAvatar(paramString.mFromID);
      if (paramString != null)
        this.mAvatarElement.setImageUrl(paramString);
    }
    return;
    label93: this.mAvatarElement.setImageUrl(paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportDescView
 * JD-Core Version:    0.6.2
 */