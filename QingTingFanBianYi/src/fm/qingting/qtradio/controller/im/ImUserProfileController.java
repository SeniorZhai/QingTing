package fm.qingting.qtradio.controller.im;

import android.content.Context;
import android.text.TextUtils;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.UserProfileManager;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.im.profile.UserProfileView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;

public class ImUserProfileController extends ViewController
  implements INavigationBarListener, RootNode.IInfoUpdateEventListener
{
  private NavigationBarView mBarTopView;
  private boolean mIsMe = false;
  private String mUserKey;
  private UserProfileView mainView;

  public ImUserProfileController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "userprofile";
    this.mainView = new UserProfileView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("个人资料"));
    this.mBarTopView.setLeftItem(0);
    this.mBarTopView.setRightItem("设置");
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 3);
  }

  private void setData()
  {
    int i = 0;
    this.mainView.update("setData", UserProfileManager.getInstance().getUserProfile(this.mUserKey));
    boolean bool;
    NavigationBarView localNavigationBarView;
    if (this.mIsMe)
    {
      bool = false;
      localNavigationBarView = this.mBarTopView;
      if (!bool)
        break label59;
    }
    while (true)
    {
      localNavigationBarView.setRightItemVisibility(i);
      return;
      bool = IMContacts.getInstance().hasWatchedUser(this.mUserKey);
      break;
      label59: i = 4;
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mUserKey = ((String)paramObject);
      this.mIsMe = TextUtils.equals(InfoManager.getInstance().getUserProfile().getUserKey(), this.mUserKey);
      paramString = UserProfileManager.getInstance().getUserProfile(this.mUserKey);
      if (this.mIsMe)
      {
        this.mBarTopView.setRightItemVisibility(4);
        if (paramString != null)
          break label119;
        UserProfileManager.getInstance().loadUserInfo(this.mUserKey, this);
        QTMSGManage.getInstance().sendStatistcsMessage("imuserprofile");
        paramString = QTLogger.getInstance().buildEnterIMLog(4);
        if (paramString != null)
          LogModule.getInstance().send("IMUI", paramString);
      }
    }
    label119: 
    while (!paramString.equalsIgnoreCase("resetData"))
      while (true)
      {
        return;
        this.mBarTopView.setRightItemVisibility(0);
        continue;
        setData();
      }
    setData();
  }

  public void controllerDidPopped()
  {
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(3, this);
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 3)
      setData();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
    while (paramInt != 3)
      return;
    ControllerManager.getInstance().openImUserSettingController(this.mUserKey);
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("followuser"))
    {
      paramObject1 = UserProfileManager.getInstance().getUserProfile(this.mUserKey);
      if (paramObject1 != null)
        InfoManager.getInstance().getUserProfile().followUser(paramObject1.getUserInfo());
    }
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("sendMessage"));
      paramObject1 = UserProfileManager.getInstance().getUserProfile(this.mUserKey);
    }
    while (paramObject1 == null);
    paramString = ControllerManager.getInstance().getControllerUnderneath();
    if ((paramString != null) && (paramString.controllerName.equalsIgnoreCase("imchat")))
    {
      paramString = (String)paramString.getValue("getTalkingId", null);
      if ((paramString != null) && (paramString.equalsIgnoreCase(paramObject1.getUserKey())))
      {
        ControllerManager.getInstance().popLastController();
        return;
      }
    }
    ControllerManager.getInstance().openImChatController(paramObject1.getUserInfo());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImUserProfileController
 * JD-Core Version:    0.6.2
 */