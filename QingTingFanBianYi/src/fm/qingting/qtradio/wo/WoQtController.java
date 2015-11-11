package fm.qingting.qtradio.wo;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class WoQtController extends ViewController
  implements INavigationBarListener
{
  private WoQtView qtView;

  public WoQtController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "wo";
    this.qtView = new WoQtView(paramContext);
    attachView(this.qtView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("蜻蜓流量包"));
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
  }

  public boolean isHome()
  {
    return this.qtView.isHome();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
    {
      ControllerManager.getInstance().popLastController();
      ViewController localViewController = ControllerManager.getInstance().getFrontPageNewController();
      if (localViewController != null)
        localViewController.config("updateWoState", null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoQtController
 * JD-Core Version:    0.6.2
 */