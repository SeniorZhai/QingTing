package fm.qingting.qtradio.view.chatroom.chatlist;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.room.ChatData;
import fm.qingting.qtradio.room.CustomData;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboData;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;

public class ChatroomItemRightView extends QtView
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(94, 94, 604, 5, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bubbleLayout = this.itemLayout.createChildLT(540, 94, 50, 16, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 124, 720, 124, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private BubbleRightViewElement mBubbleRightViewElement;
  private ImageViewElement mDefaultElement;

  public ChatroomItemRightView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mDefaultElement = new ImageViewElement(paramContext);
    this.mDefaultElement.setImageRes(2130837591);
    addElement(this.mDefaultElement, paramInt);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    addElement(this.mAvatarElement, paramInt);
    this.mBubbleRightViewElement = new BubbleRightViewElement(paramContext);
    addElement(this.mBubbleRightViewElement, paramInt);
  }

  private int getBubbleResource(int paramInt)
  {
    switch (paramInt)
    {
    case 19:
    default:
      return 2130837567;
    case 3:
      return 2130837571;
    case 2:
      return 2130837572;
    case 1:
      return 2130837573;
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
    this.mDefaultElement.measure(this.avatarLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mBubbleRightViewElement.measure(this.bubbleLayout);
    setMeasuredDimension(this.itemLayout.width, getThisHeight());
  }

  public void update(String paramString, Object paramObject)
  {
    Object localObject = null;
    if ((!paramString.equalsIgnoreCase("content")) || (paramObject == null))
      return;
    ChatItem localChatItem = (ChatItem)paramObject;
    paramString = (CustomData)localChatItem.data;
    if (paramString.type == 1)
    {
      paramString = (ChatData)paramString;
      paramObject = paramString.user.snsInfo.sns_avatar;
      paramString = paramString.content;
    }
    while (true)
    {
      this.mAvatarElement.setImageUrl(paramObject);
      this.mBubbleRightViewElement.setBubbleResource(getBubbleResource(localChatItem.type));
      this.mBubbleRightViewElement.setText(paramString);
      requestLayout();
      return;
      if (paramString.type == 2)
      {
        paramString = (WeiboData)paramString;
        paramObject = paramString.user.snsInfo.sns_avatar;
        paramString = paramString.content;
      }
      else
      {
        paramString = null;
        paramObject = localObject;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.chatlist.ChatroomItemRightView
 * JD-Core Version:    0.6.2
 */