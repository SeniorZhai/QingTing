package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import android.text.TextUtils;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.mypodcaster.MyPodcasterView;
import java.util.List;

public class MyPodcasterController extends ViewController
  implements INavigationBarListener
{
  public static final String NAME = "mypodcaster";
  private NavigationBarView barTopView;
  private boolean inManageMode = false;
  private MyPodcasterView mainView;

  public MyPodcasterController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "mypodcaster";
    this.mainView = new MyPodcasterView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("我的主播"));
    this.barTopView.setRightItem("编辑");
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  public void config(String paramString, Object paramObject)
  {
    paramObject = null;
    paramString = paramObject;
    if (CloudCenter.getInstance().isLogin(false))
    {
      UserProfile localUserProfile = InfoManager.getInstance().getUserProfile();
      paramString = paramObject;
      if (localUserProfile.getUserInfo() != null)
      {
        paramString = paramObject;
        if (!TextUtils.isEmpty(localUserProfile.getUserInfo().snsInfo.sns_id))
          paramString = PodcasterHelper.getInstance().getAllMyPodcaster(localUserProfile.getUserInfo().snsInfo.sns_id);
      }
    }
    if ((paramString == null) || (paramString.size() == 0))
      this.barTopView.setRightItemVisibility(4);
    while (true)
    {
      this.mainView.update("setData", paramString);
      return;
      this.barTopView.setRightItemVisibility(0);
    }
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public boolean hasMiniPlayer()
  {
    return true;
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      ControllerManager.getInstance().popLastController();
      return;
    case 3:
    }
    Object localObject = this.mainView;
    String str;
    if (this.inManageMode)
    {
      str = "hideManage";
      ((MyPodcasterView)localObject).update(str, null);
      localObject = this.barTopView;
      if (!this.inManageMode)
        break label96;
      str = "编辑";
      label68: ((NavigationBarView)localObject).setRightItem(str);
      if (this.inManageMode)
        break label102;
    }
    label96: label102: for (boolean bool = true; ; bool = false)
    {
      this.inManageMode = bool;
      return;
      str = "showManage";
      break;
      str = "取消";
      break label68;
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("emptynow"))
    {
      this.mainView.update("hideManage", null);
      this.barTopView.setRightItem("编辑");
      this.barTopView.setRightItemVisibility(4);
    }
    while (!paramString.equalsIgnoreCase("notEmpty"))
      return;
    this.barTopView.setRightItemVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.MyPodcasterController
 * JD-Core Version:    0.6.2
 */