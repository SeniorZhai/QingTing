package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.ImBlackList;
import fm.qingting.qtradio.view.im.BlackListView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import java.util.List;

public class ImBlackListController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private BlackListView mainView;

  public ImBlackListController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "blockedmembers";
    this.mainView = new BlackListView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("黑名单"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramObject = ImBlackList.getBlackList(getContext());
      if (paramObject == null)
        this.mBarTopView.setTitleItem(new NavigationBarItem("黑名单(0)"));
    }
    while (!paramString.equalsIgnoreCase("resetData"))
    {
      return;
      i = paramObject.size();
      this.mBarTopView.setTitleItem(new NavigationBarItem("黑名单(" + i + ")"));
      this.mainView.update(paramString, paramObject);
      return;
    }
    paramObject = ImBlackList.getBlackList(getContext());
    if (paramObject == null)
    {
      this.mBarTopView.setTitleItem(new NavigationBarItem("黑名单(0)"));
      return;
    }
    int i = paramObject.size();
    this.mBarTopView.setTitleItem(new NavigationBarItem("黑名单(" + i + ")"));
    this.mainView.update(paramString, paramObject);
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImBlackListController
 * JD-Core Version:    0.6.2
 */