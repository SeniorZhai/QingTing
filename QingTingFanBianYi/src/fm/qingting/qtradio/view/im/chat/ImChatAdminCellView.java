package fm.qingting.qtradio.view.im.chat;

import android.content.Context;
import android.graphics.Point;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.view.chatroom.FlowerInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.utils.InputMethodUtil;
import fm.qingting.utils.QTMSGManage;

public class ImChatAdminCellView extends QtView
{
  private ViewLayout avatarBgLayout = this.itemLayout.createChildLT(86, 86, 32, 32, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout avatarLayout = this.itemLayout.createChildLT(80, 80, 35, 35, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(150, 160, 150, 160, 0, 0, ViewLayout.FILL);
  private ImageViewElement mAvatarBgElement;
  private RoundAvatarElement mAvatarElement;
  private ImageViewElement mMaskElement;
  private TextViewElement mNameElement;
  private UserInfo mUserInfo;
  private ViewLayout maskLayout = this.itemLayout.createChildLT(150, 150, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout nameLayout = this.itemLayout.createChildLT(120, 40, 15, 120, ViewLayout.SCALE_FLAG_SLTCW);

  public ImChatAdminCellView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mAvatarBgElement = new ImageViewElement(paramContext);
    this.mAvatarBgElement.setImageRes(2130837591);
    addElement(this.mAvatarBgElement, i);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    addElement(this.mAvatarElement, hashCode());
    this.mAvatarElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (ImChatAdminCellView.this.mUserInfo != null)
        {
          InputMethodUtil.hideSoftInput(ImChatAdminCellView.this);
          EventDispacthManager.getInstance().dispatchAction("showimmenu", ImChatAdminCellView.this.mUserInfo);
        }
      }
    });
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setColor(SkinManager.getBackgroundColor());
    addElement(this.mNameElement);
    this.mMaskElement = new ImageViewElement(paramContext);
    this.mMaskElement.setImageRes(2130837593);
    addElement(this.mMaskElement, i);
    this.mMaskElement.setVisible(4);
    this.mMaskElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (!CloudCenter.getInstance().isLogin(false))
        {
          paramAnonymousViewElement = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymous2Int)
            {
            }

            public void onLoginSuccessed(int paramAnonymous2Int)
            {
              ImChatAdminCellView.this.flower();
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramAnonymousViewElement);
          return;
        }
        ImChatAdminCellView.this.flower();
      }
    });
  }

  private void actualFlowerTo()
  {
    dispatchActionEvent("flowerToAdmin", this.mUserInfo);
  }

  private void doFlowerAction()
  {
    if (!CloudCenter.getInstance().isLogin(false))
    {
      CloudCenter.OnLoginEventListerner local3 = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          ImChatAdminCellView.this.actualFlowerTo();
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", local3);
      return;
    }
    actualFlowerTo();
  }

  private void flower()
  {
    if (this.mUserInfo == null)
      return;
    if (!FlowerInfo.allowFlower(this.mUserInfo))
    {
      Toast.makeText(getContext(), "您献花频率过快，请稍侯10秒.", 1).show();
      return;
    }
    doFlowerAction();
    Point localPoint = new Point();
    localPoint.set((getLeft() + getRight()) / 2, getTop() + this.avatarLayout.getTop() + this.avatarLayout.height / 2);
    dispatchActionEvent("flowerToPoint", localPoint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarBgLayout.scaleToBounds(this.itemLayout);
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.maskLayout.scaleToBounds(this.itemLayout);
    this.mAvatarBgElement.measure(this.avatarBgLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mMaskElement.measure(this.maskLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("resetState"))
      if (this.mMaskElement.getVisiblity() == 0)
      {
        this.mNameElement.setText(this.mUserInfo.snsInfo.sns_name, false);
        this.mMaskElement.setVisible(4);
      }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("changeFlowerState"))
      {
        if (this.mMaskElement.getVisiblity() == 0)
        {
          this.mNameElement.setText(this.mUserInfo.snsInfo.sns_name, false);
          this.mMaskElement.setVisible(4);
          return;
        }
        this.mNameElement.setText("点击送花", false);
        this.mMaskElement.setVisible(0);
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickflower");
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setData"));
    this.mUserInfo = ((UserInfo)paramObject);
    this.mAvatarElement.setImageUrl(this.mUserInfo.snsInfo.sns_avatar);
    this.mNameElement.setText(this.mUserInfo.snsInfo.sns_name);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.chat.ImChatAdminCellView
 * JD-Core Version:    0.6.2
 */