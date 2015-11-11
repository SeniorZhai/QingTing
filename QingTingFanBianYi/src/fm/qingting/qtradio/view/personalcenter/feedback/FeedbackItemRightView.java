package fm.qingting.qtradio.view.personalcenter.feedback;

import android.content.Context;
import android.view.View.MeasureSpec;
import com.umeng.fb.model.Reply;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.chatroom.chatlist.BubbleRightViewElement;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;

public class FeedbackItemRightView extends QtView
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(94, 94, 604, 5, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bubbleLayout = this.itemLayout.createChildLT(540, 94, 50, 16, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 124, 720, 124, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private BubbleRightViewElement mBubbleRightViewElement;

  public FeedbackItemRightView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, paramInt);
    this.mBubbleRightViewElement = new BubbleRightViewElement(paramContext);
    addElement(this.mBubbleRightViewElement, paramInt);
  }

  private int getBubbleResource(int paramInt)
  {
    int i = 2130837572;
    switch (paramInt)
    {
    default:
      i = 2130837567;
    case 2:
    case 4:
      return i;
    case 3:
      return 2130837571;
    case 1:
      return 2130837573;
    case 19:
      return 2130837567;
    case 18:
      return 2130837568;
    case 17:
      return 2130837569;
    case 20:
    }
    return 2130837570;
  }

  private int getThisHeight()
  {
    int j = this.mBubbleRightViewElement.getTopMargin() + this.mBubbleRightViewElement.getHeight() + this.bubbleLayout.topMargin;
    int i = j;
    if (j < this.itemLayout.height)
      i = this.itemLayout.height;
    return i;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.bubbleLayout.scaleToBounds(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mBubbleRightViewElement.measure(this.bubbleLayout);
    setMeasuredDimension(this.itemLayout.width, getThisHeight());
  }

  public void update(String paramString, Object paramObject)
  {
    if ((!paramString.equalsIgnoreCase("content")) || (paramObject == null))
      return;
    ChatItem localChatItem = (ChatItem)paramObject;
    Reply localReply = (Reply)localChatItem.data;
    paramObject = null;
    paramString = paramObject;
    if (InfoManager.getInstance().getUserProfile() != null)
    {
      paramString = paramObject;
      if (InfoManager.getInstance().getUserProfile().getUserInfo() != null)
      {
        paramString = paramObject;
        if (InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo != null)
          paramString = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_avatar;
      }
    }
    paramObject = localReply.getContent();
    this.mAvatarElement.setImageUrl(paramString);
    this.mBubbleRightViewElement.setBubbleResource(getBubbleResource(localChatItem.type));
    this.mBubbleRightViewElement.setText(paramObject);
    requestLayout();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.feedback.FeedbackItemRightView
 * JD-Core Version:    0.6.2
 */