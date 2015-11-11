package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.timer.TimerPickView;

public class TimerPickController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mTopView;
  private TimerPickView mainView;

  public TimerPickController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "timerSetting";
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setLeftItem(0);
    this.mTopView.setTitleItem(new NavigationBarItem("定时关闭"));
    setNavigationBar(this.mTopView);
    this.mTopView.setBarListener(this);
    this.mainView = new TimerPickView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mainView.update(paramString, paramObject);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.TimerPickController
 * JD-Core Version:    0.6.2
 */