package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.settingviews.SettingListView;

public class SettingController extends ViewController
  implements INavigationBarListener
{
  private SettingListView mainView;

  public SettingController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "setting";
    this.mainView = new SettingListView(paramContext);
    attachView(this.mainView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("更多设置"));
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mainView.update(paramString, paramObject);
    while (!paramString.equalsIgnoreCase("loginSuccess"))
      return;
    this.mainView.update(paramString, paramObject);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.SettingController
 * JD-Core Version:    0.6.2
 */