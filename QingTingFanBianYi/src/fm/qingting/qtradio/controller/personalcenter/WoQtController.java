package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.wo.WoQtView;

public class WoQtController extends ViewController
  implements INavigationBarListener, IEventHandler
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

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
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

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.WoQtController
 * JD-Core Version:    0.6.2
 */