package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.hiddenfeatures.HiddenFeaturesView;

public class HiddenFeaturesController extends ViewController
  implements INavigationBarListener
{
  private HiddenFeaturesView mFeaturesView;

  public HiddenFeaturesController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "hiddenfeatures";
    NavigationBarView localNavigationBarView = new NavigationBarView(paramContext);
    localNavigationBarView.setLeftItem(0);
    localNavigationBarView.setTitleItem(new NavigationBarItem("Hidden Features"));
    setNavigationBar(localNavigationBarView);
    localNavigationBarView.setBarListener(this);
    this.mFeaturesView = new HiddenFeaturesView(paramContext);
    attachView(this.mFeaturesView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mFeaturesView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mFeaturesView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.HiddenFeaturesController
 * JD-Core Version:    0.6.2
 */