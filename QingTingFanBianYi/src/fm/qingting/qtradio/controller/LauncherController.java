package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.view.LaunchView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class LauncherController extends ViewController
  implements INavigationBarListener
{
  private LaunchView mainView;

  public LauncherController(Context paramContext)
  {
    super(paramContext);
    this.mainView = new LaunchView(paramContext, InfoManager.getInstance().enableAdvertisement());
    attachView(this.mainView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setBackgroundResource(0);
    paramContext.setLeftItem(0);
    paramContext.setBarListener(this);
    setNavigationBarMode(INavigationSetting.Mode.OVERLAY);
    setNavigationBar(paramContext);
  }

  public void controllerDidPopped()
  {
    super.controllerDidPopped();
    this.mainView.close(false);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.LauncherController
 * JD-Core Version:    0.6.2
 */