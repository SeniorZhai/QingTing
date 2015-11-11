package fm.qingting.qtradio.view.moreContentView;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.TextView;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.utils.QTMSGManage;

public class UserTitleView extends ViewGroupViewImpl
{
  private final ViewLayout avatarLayout = this.standardLayout.createChildLT(154, 154, 283, 60, ViewLayout.SCALE_FLAG_SLTCW);
  private AvatarView mAvatarView;
  private TextView mName;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(720, 50, 0, 220, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 304, 720, 304, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public UserTitleView(Context paramContext)
  {
    super(paramContext);
    setBackgroundResource(2130838014);
    this.mAvatarView = new AvatarView(paramContext);
    addView(this.mAvatarView);
    this.mName = new TextView(paramContext);
    this.mName.setTextColor(-1);
    this.mName.setGravity(17);
    addView(this.mName);
    this.mAvatarView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CloudCenter.getInstance().isLogin(false))
        {
          ControllerManager.getInstance().openImUserProfileController(InfoManager.getInstance().getUserProfile().getUserKey());
          return;
        }
        EventDispacthManager.getInstance().dispatchAction("showLogin", null);
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "avatarclicked");
      }
    });
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.avatarLayout.layoutView(this.mAvatarView);
    this.nameLayout.layoutView(this.mName);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.avatarLayout.measureView(this.mAvatarView);
    this.nameLayout.measureView(this.mName);
    this.mName.setTextSize(0, SkinManager.getInstance().getRecommendTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setUser"))
    {
      paramString = (UserProfile)paramObject;
      if (paramString.getUserInfo() == null)
      {
        this.mAvatarView.setImageUrl(null);
        this.mName.setText("请点击头像登录");
      }
    }
    else
    {
      return;
    }
    paramObject = paramString.getUserInfo().snsInfo.sns_avatar;
    this.mAvatarView.setImageUrl(paramObject);
    this.mName.setText(paramString.getUserInfo().snsInfo.sns_name);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.UserTitleView
 * JD-Core Version:    0.6.2
 */