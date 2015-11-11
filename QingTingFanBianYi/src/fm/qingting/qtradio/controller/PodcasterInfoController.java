package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.podcaster.PodcasterInfoView;
import fm.qingting.utils.QTMSGManage;

public class PodcasterInfoController extends ViewController
  implements INavigationBarListener
{
  public static final String NAME = "podcasterinfo";
  private PodcasterInfoView mInfoView;
  private UserInfo mPodcasterInfo = null;
  private NavigationBarView mTopView;

  public PodcasterInfoController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "podcasterinfo";
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setBackgroundResource(0);
    setNavigationBar(this.mTopView);
    this.mTopView.setLeftItem(0);
    this.mTopView.setRightItem(4);
    this.mTopView.setBarListener(this);
    setNavigationBarMode(INavigationSetting.Mode.OVERLAY);
    this.mInfoView = new PodcasterInfoView(paramContext);
    attachView(this.mInfoView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      if ((paramObject instanceof UserInfo))
        this.mPodcasterInfo = ((UserInfo)paramObject);
      this.mInfoView.update(paramString, paramObject);
    }
  }

  public void controllerDidPopped()
  {
    this.mInfoView.update("setlastestprogramid", null);
    this.mInfoView.close(false);
  }

  public boolean hasMiniPlayer()
  {
    return true;
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
    while (paramInt != 3)
      return;
    if (CloudCenter.getInstance().isLogin(true))
      QTMSGManage.getInstance().sendStatistcsMessage("sharepodcaster", "sharechoose");
    while (true)
    {
      EventDispacthManager.getInstance().dispatchAction("shareChoose", this.mPodcasterInfo);
      return;
      QTMSGManage.getInstance().sendStatistcsMessage("sharepodcaster", "unlogin");
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("showTitle"))
    {
      paramObject1 = (String)paramObject2;
      this.mTopView.setTitleItem(new NavigationBarItem(paramObject1));
    }
    while (!paramString.equalsIgnoreCase("hideTitle"))
      return;
    this.mTopView.setTitleItem(new NavigationBarItem(null));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.PodcasterInfoController
 * JD-Core Version:    0.6.2
 */