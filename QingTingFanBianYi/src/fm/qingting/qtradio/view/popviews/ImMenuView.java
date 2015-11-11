package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.BaseUserInfoPool;
import fm.qingting.qtradio.im.BaseUserInfoPool.AvatarAndGender;
import fm.qingting.qtradio.im.BaseUserInfoPool.OnBaseUserinfoPutListener;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.ImBlackList;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import java.util.ArrayList;
import java.util.List;

public class ImMenuView extends QtView
  implements BaseUserInfoPool.OnBaseUserinfoPutListener
{
  private final ViewLayout avatarLayout = this.standardLayout.createChildLT(80, 80, 320, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout buttonsLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonArrayElement mArrayElement;
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private IMMessage mMessage;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(700, 60, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ImMenuView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getNewPopBgColor(), SkinManager.getNewPopBgColor());
    addElement(this.mBg);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, i);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mNameElement);
    this.mArrayElement = new ButtonArrayElement(paramContext);
    addElement(this.mArrayElement);
    this.mArrayElement.setOnArrayClickListenrer(new ButtonArrayElement.OnButtonArrayClickListener()
    {
      public void OnArrayClick(ViewElement paramAnonymousViewElement, int paramAnonymousInt)
      {
        switch (ImMenuView.this.mArrayElement.getItem(paramAnonymousInt).getEventId())
        {
        case 4:
        default:
        case 0:
          do
          {
            return;
            ImMenuView.this.dispatchActionEvent("cancelPopWithoutAnimation", null);
            paramAnonymousViewElement = ControllerManager.getInstance().getLastViewController();
          }
          while ((paramAnonymousViewElement == null) || (!paramAnonymousViewElement.controllerName.equalsIgnoreCase("imchat")));
          paramAnonymousViewElement.config("atTa", ImMenuView.this.mMessage.mFromName);
          return;
        case 1:
          ImMenuView.this.dispatchActionEvent("cancelPopWithoutAnimation", null);
          ControllerManager.getInstance().openImChatController(ImMenuView.this.mMessage);
          return;
        case 2:
          ImMenuView.this.dispatchActionEvent("cancelPopWithoutAnimation", null);
          ControllerManager.getInstance().openImUserProfileController(ImMenuView.this.mMessage.mFromID);
          return;
        case 3:
          ImMenuView.this.dispatchActionEvent("cancelPopWithoutAnimation", null);
          EventDispacthManager.getInstance().dispatchAction("showblockremind", ImMenuView.this.mMessage);
          return;
        case 5:
          ImMenuView.this.dispatchActionEvent("cancelPopWithoutAnimation", null);
          ControllerManager.getInstance().openImReportController(ImMenuView.this.mMessage);
        case 6:
        }
        ImMenuView.this.dispatchActionEvent("cancelPop", null);
      }
    });
    BaseUserInfoPool.addListener(this);
  }

  private IMMessage createMessage(UserInfo paramUserInfo)
  {
    IMMessage localIMMessage = new IMMessage();
    localIMMessage.chatType = 1;
    localIMMessage.mFromAvatar = paramUserInfo.snsInfo.sns_avatar;
    localIMMessage.mFromName = paramUserInfo.snsInfo.sns_name;
    localIMMessage.mFromID = paramUserInfo.userKey;
    return localIMMessage;
  }

  private void setData(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Resources localResources = getResources();
    if (this.mMessage.isGroupMsg())
    {
      localArrayList.add(new ButtonItem(localResources.getString(2131492877), 0));
      localArrayList.add(new ButtonItem(localResources.getString(2131492878), 1));
    }
    localArrayList.add(new ButtonItem(localResources.getString(2131492879), 2));
    if (!ImBlackList.inBlackList(getContext(), this.mMessage.mFromID))
      localArrayList.add(new ButtonItem(localResources.getString(2131492880), 3));
    if (paramBoolean)
      localArrayList.add(new ButtonItem(localResources.getString(2131492881), 5));
    localArrayList.add(new ButtonItem(localResources.getString(2131492882), 6));
    this.mArrayElement.setButtons(localArrayList);
    requestLayout();
  }

  public void close(boolean paramBoolean)
  {
    BaseUserInfoPool.removeListener(this);
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.mBg.getTopMargin()))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onBaseInfoPut(String paramString, BaseUserInfoPool.AvatarAndGender paramAvatarAndGender)
  {
    if ((paramString != null) && (this.mMessage != null) && (paramString.equalsIgnoreCase(this.mMessage.mFromID)))
      this.mAvatarElement.setImageUrl(paramAvatarAndGender.getAvatar());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.buttonsLayout.scaleToBounds(this.standardLayout);
    int i = this.mArrayElement.getCount() * this.buttonsLayout.height;
    this.mArrayElement.measure(0, this.standardLayout.height - i, this.buttonsLayout.width, this.standardLayout.height);
    this.mNameElement.measure(this.nameLayout.leftMargin, this.standardLayout.height - i - this.nameLayout.height, this.nameLayout.getRight(), this.standardLayout.height - i);
    this.mAvatarElement.measure(this.avatarLayout.leftMargin, this.standardLayout.height - i - this.nameLayout.height - this.avatarLayout.height, this.avatarLayout.getRight(), this.standardLayout.height - i - this.nameLayout.height);
    this.mBg.measure(0, this.standardLayout.height - i - this.nameLayout.height - this.avatarLayout.getBottom(), this.standardLayout.width, this.standardLayout.height);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    boolean bool;
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      bool = true;
      if (!(paramObject instanceof IMMessage))
        break label110;
      this.mMessage = ((IMMessage)paramObject);
      paramString = this.mMessage.mFromAvatar;
      if ((paramString != null) && (paramString.length() != 0))
        break label145;
      paramString = BaseUserInfoPool.getAvatar(this.mMessage.mFromID);
      if ((paramString != null) && (paramString.length() != 0))
        break label134;
      BaseUserInfoPool.loadBaseInfo(this.mMessage.mFromID, null, IMAgent.getInstance());
      this.mAvatarElement.setImageUrl(paramString);
    }
    while (true)
    {
      this.mNameElement.setText(this.mMessage.mFromName);
      setData(bool);
      return;
      label110: if (!(paramObject instanceof UserInfo))
        break;
      bool = false;
      this.mMessage = createMessage((UserInfo)paramObject);
      break;
      label134: this.mAvatarElement.setImageUrl(paramString);
      continue;
      label145: this.mAvatarElement.setImageUrl(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ImMenuView
 * JD-Core Version:    0.6.2
 */