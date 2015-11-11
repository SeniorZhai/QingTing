package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.im.profile.GroupSettingView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class ImGroupSettingController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private String mGroupId;
  private GroupSettingView mainView;

  public ImGroupSettingController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "groupsetting";
    this.mainView = new GroupSettingView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("群组设置"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mGroupId = ((String)paramObject);
      this.mainView.update(paramString, this.mGroupId);
    }
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
    if (paramString.equalsIgnoreCase("quitgroup"))
      if (this.mGroupId != null)
      {
        InfoManager.getInstance().getUserProfile().unfollowGroup(this.mGroupId);
        ControllerManager.getInstance().popLastController();
        paramObject1 = ControllerManager.getInstance().getLastViewController();
        if (paramObject1.controllerName.equalsIgnoreCase("groupprofile"))
          paramObject1.config("resetData", null);
      }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("disablegroup"))
      {
        IMAgent.getInstance().disableGroup(this.mGroupId);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("enableGroup"));
    IMAgent.getInstance().enableGroup(this.mGroupId);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImGroupSettingController
 * JD-Core Version:    0.6.2
 */