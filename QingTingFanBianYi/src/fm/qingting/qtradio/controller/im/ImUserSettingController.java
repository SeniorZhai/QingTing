package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.im.profile.UserSettingView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class ImUserSettingController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private String mGroupId;
  private UserSettingView mainView;

  public ImUserSettingController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "usersetting";
    this.mainView = new UserSettingView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("设置"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mGroupId = ((String)paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramString.equalsIgnoreCase("unfollow")) && (this.mGroupId != null))
    {
      InfoManager.getInstance().getUserProfile().unfollowUser(this.mGroupId);
      ControllerManager.getInstance().popLastController();
      paramObject1 = ControllerManager.getInstance().getLastViewController();
      if (paramObject1.controllerName.equalsIgnoreCase("userprofile"))
        paramObject1.config("resetData", null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImUserSettingController
 * JD-Core Version:    0.6.2
 */