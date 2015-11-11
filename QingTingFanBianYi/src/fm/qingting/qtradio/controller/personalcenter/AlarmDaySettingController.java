package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.clock.daysetting.AlarmDaySettingView;

public class AlarmDaySettingController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTopView;
  private AlarmDaySettingView mainView;
  private boolean repeat = true;

  public AlarmDaySettingController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "daysetting";
    this.mainView = new AlarmDaySettingView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("重复"));
    this.barTopView.setRightItem(2);
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  private void confirmDay(int paramInt)
  {
    dispatchEvent("day", Integer.valueOf(paramInt));
    ControllerManager.getInstance().popLastController();
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("day"))
      this.mainView.update(paramString, paramObject);
    boolean bool;
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("isRepeat"));
      this.mainView.update(paramString, paramObject);
      bool = ((Boolean)paramObject).booleanValue();
    }
    while (bool == this.repeat);
    this.repeat = bool;
    paramObject = this.barTopView;
    if (this.repeat);
    for (paramString = "重复"; ; paramString = "不重复")
    {
      paramObject.setTitleItem(new NavigationBarItem(paramString));
      return;
    }
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2:
    case 3:
    }
    Object localObject;
    do
    {
      return;
      ControllerManager.getInstance().popLastController();
      return;
      localObject = this.mainView.getValue("day", null);
    }
    while (localObject == null);
    confirmDay(((Integer)localObject).intValue());
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("chooseRepeat"))
    {
      boolean bool = ((Boolean)paramObject2).booleanValue();
      if (bool != this.repeat)
      {
        this.repeat = bool;
        paramString = this.barTopView;
        if (!this.repeat)
          break label61;
      }
    }
    label61: for (paramObject1 = "重复"; ; paramObject1 = "不重复")
    {
      paramString.setTitleItem(new NavigationBarItem(paramObject1));
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.AlarmDaySettingController
 * JD-Core Version:    0.6.2
 */