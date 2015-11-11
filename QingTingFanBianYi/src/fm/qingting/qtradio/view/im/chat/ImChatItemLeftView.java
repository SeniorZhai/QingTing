package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.BaseUserInfoPool;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.chatroom.chatlist.BubbleLeftViewElement;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.utils.InputMethodUtil;

public class ImChatItemLeftView extends QtView
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(94, 94, 22, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bubbleLayout = this.itemLayout.createChildLT(540, 94, 130, 50, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout genderLayout = this.itemLayout.createChildLT(19, 18, 156, 16, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 124, 720, 124, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private BubbleLeftViewElement mBubbleLeftViewElement;
  private IMMessage mCustomData;
  private ImageViewElement mDefaultElement;
  private ImageViewElement mGenderElement;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(500, 30, 180, 10, ViewLayout.SCALE_FLAG_SLTCW);

  public ImChatItemLeftView(Context paramContext, int paramInt, boolean paramBoolean)
  {
    super(paramContext);
    this.mDefaultElement = new ImageViewElement(paramContext);
    this.mDefaultElement.setImageRes(2130837591);
    addElement(this.mDefaultElement, paramInt);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    addElement(this.mAvatarElement, paramInt);
    if (paramBoolean)
      this.mAvatarElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
      {
        public void onElementClick(ViewElement paramAnonymousViewElement)
        {
          if (ImChatItemLeftView.this.mCustomData != null)
          {
            InputMethodUtil.hideSoftInput(ImChatItemLeftView.this.getView());
            EventDispacthManager.getInstance().dispatchAction("showimmenu", ImChatItemLeftView.this.mCustomData);
          }
        }
      });
    this.mGenderElement = new ImageViewElement(paramContext);
    this.mGenderElement.setImageRes(2130837578);
    addElement(this.mGenderElement, paramInt);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    addElement(this.mNameElement);
    this.mBubbleLeftViewElement = new BubbleLeftViewElement(paramContext);
    addElement(this.mBubbleLeftViewElement, paramInt);
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
    int j = this.mBubbleLeftViewElement.getTopMargin() + this.mBubbleLeftViewElement.getHeight() + this.genderLayout.topMargin;
    int i = j;
    if (j < this.itemLayout.height)
      i = this.itemLayout.height;
    return i;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.genderLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.bubbleLayout.scaleToBounds(this.itemLayout);
    this.mDefaultElement.measure(this.avatarLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mGenderElement.measure(this.genderLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mBubbleLeftViewElement.measure(this.bubbleLayout);
    setMeasuredDimension(this.itemLayout.width, getThisHeight());
  }

  public void update(String paramString, Object paramObject)
  {
    int i = 2130837578;
    Object localObject;
    if (paramString.equalsIgnoreCase("content"))
    {
      if (paramObject == null)
        return;
      localObject = (ChatItem)paramObject;
      this.mCustomData = ((IMMessage)((ChatItem)localObject).data);
      paramObject = this.mCustomData.mGender;
      paramString = this.mCustomData.mFromAvatar;
      if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
        break label345;
      paramString = BaseUserInfoPool.getAvatar(this.mCustomData.mFromID);
      if (paramString != null)
        break label339;
      i = 1;
      BaseUserInfoPool.loadBaseInfo(this.mCustomData.mFromID, this.mCustomData.mFromGroupId, IMAgent.getInstance());
    }
    while (true)
    {
      String str1 = this.mCustomData.mFromName;
      String str2 = this.mCustomData.mMessage;
      this.mAvatarElement.setImageUrl(paramString);
      if ((paramObject == null) || (paramObject.equalsIgnoreCase("n")))
      {
        paramString = BaseUserInfoPool.getGender(this.mCustomData.mFromID);
        if ((paramString == null) && (i == 0))
          BaseUserInfoPool.loadBaseInfo(this.mCustomData.mFromID, this.mCustomData.mFromGroupId, IMAgent.getInstance());
      }
      while (true)
      {
        paramObject = this.mGenderElement;
        if ((paramString == null) || (paramString.equalsIgnoreCase("m")));
        for (i = 2130837588; ; i = 2130837578)
        {
          paramObject.setImageRes(i);
          this.mNameElement.setText(str1, false);
          this.mBubbleLeftViewElement.setBubbleResource(getBubbleResource(((ChatItem)localObject).type));
          this.mBubbleLeftViewElement.setText(str2);
          requestLayout();
          return;
        }
        if (!paramString.equalsIgnoreCase("invalidateAvatar"))
          break;
        paramString = (String)paramObject;
        if ((paramString == null) || (!paramString.equalsIgnoreCase(this.mCustomData.mFromID)))
          break;
        paramObject = BaseUserInfoPool.getGender(paramString);
        localObject = this.mGenderElement;
        if ((paramObject == null) || (paramObject.equalsIgnoreCase("m")))
          i = 2130837588;
        ((ImageViewElement)localObject).setImageRes(i);
        paramString = BaseUserInfoPool.getAvatar(paramString);
        this.mAvatarElement.setImageUrl(paramString);
        return;
        paramString = paramObject;
      }
      label339: i = 0;
      continue;
      label345: i = 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatItemLeftView
 * JD-Core Version:    0.6.2
 */