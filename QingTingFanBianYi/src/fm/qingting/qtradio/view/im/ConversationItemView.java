package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.BaseUserInfoPool;
import fm.qingting.qtradio.im.LatestMessages;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.playview.LineElement;

public class ConversationItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private static final String ME = "我:";
  private static final String UNKNOWNTIME = "";
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(90, 90, 30, 23, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(540, 45, 150, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(60, 36, 86, 26, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 135, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private Object mInfo;
  private TextViewElement mInfoElement;
  private LabelViewElement mLabelViewElement;
  private LineElement mLineElement;
  private TextViewElement mTimeElement;
  private TextViewElement mTitleElement;
  private final ViewLayout timeLayout = this.itemLayout.createChildLT(180, 45, 520, 17, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(390, 45, 150, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public ConversationItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setMaxLineLimit(1);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mTimeElement = new TextViewElement(paramContext);
    this.mTimeElement.setColor(SkinManager.getTextColorSubInfo());
    this.mTimeElement.setMaxLineLimit(1);
    this.mTimeElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    addElement(this.mTimeElement);
    this.mLabelViewElement = new LabelViewElement(paramContext);
    addElement(this.mLabelViewElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private final boolean isMine(String paramString)
  {
    if (InfoManager.getInstance().getUserProfile() == null)
      return false;
    return TextUtils.equals(InfoManager.getInstance().getUserProfile().getUserKey(), paramString);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    ControllerManager.getInstance().openImChatController(this.mInfo);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.timeLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTimeElement.measure(this.timeLayout);
    this.mLabelViewElement.measure(this.labelLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mTimeElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mInfo = paramObject;
      if (!(this.mInfo instanceof GroupInfo))
        break label233;
      paramString = (GroupInfo)this.mInfo;
      this.mAvatarElement.setDefaultImageRes(2130837826);
      this.mAvatarElement.setImageUrl(null);
      this.mTitleElement.setText(paramString.groupName, false);
      this.mLabelViewElement.setId(paramString.groupId);
      paramString = LatestMessages.getMessage(paramString.groupId);
      if (paramString == null)
        break label213;
      if (!isMine(paramString.mFromID))
        break label140;
      this.mInfoElement.setText("我:" + paramString.mMessage, false);
      this.mTimeElement.setText(LatestMessages.getTimestampBySecond(paramString.publish));
    }
    label140: label213: label233: 
    while (!(this.mInfo instanceof UserInfo))
    {
      while (true)
      {
        return;
        if ((paramString.mFromName != null) && (paramString.mFromName.length() > 0))
          this.mInfoElement.setText(paramString.mFromName + ":" + paramString.mMessage, false);
        else
          this.mInfoElement.setText(paramString.mMessage, false);
      }
      this.mInfoElement.setText("", false);
      this.mTimeElement.setText("");
      return;
    }
    paramString = (UserInfo)this.mInfo;
    this.mAvatarElement.setDefaultImageRes(2130837701);
    this.mAvatarElement.setImageUrl(BaseUserInfoPool.getAvatar(paramString.userKey));
    this.mTitleElement.setText(paramString.snsInfo.sns_name, false);
    this.mLabelViewElement.setId(paramString.userKey);
    paramString = LatestMessages.getMessage(paramString.userKey);
    if (paramString != null)
    {
      this.mInfoElement.setText(paramString.mMessage, false);
      this.mTimeElement.setText(LatestMessages.getTimestampBySecond(paramString.publish));
      return;
    }
    this.mInfoElement.setText("");
    this.mTimeElement.setText("");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ConversationItemView
 * JD-Core Version:    0.6.2
 */