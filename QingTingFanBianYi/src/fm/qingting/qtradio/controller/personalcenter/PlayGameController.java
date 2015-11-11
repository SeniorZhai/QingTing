package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.playgame.PlayGameView;
import java.util.List;

public class PlayGameController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTempView;
  private PlayGameView gamesView;

  public PlayGameController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "playgame";
    this.gamesView = new PlayGameView(paramContext);
    attachView(this.gamesView);
    this.barTempView = new NavigationBarView(paramContext);
    this.barTempView.setLeftItem(0);
    this.barTempView.setBarListener(this);
    setNavigationBar(this.barTempView);
  }

  private void setData()
  {
    int j = 0;
    List localList = InfoManager.getInstance().getLstGameBean();
    NavigationBarView localNavigationBarView;
    if (localList.size() > 0)
    {
      i = 1;
      localNavigationBarView = this.barTempView;
      if (i == 0)
        break label54;
    }
    label54: for (int i = j; ; i = 4)
    {
      localNavigationBarView.setRightItemVisibility(i);
      this.gamesView.update("setData", localList);
      return;
      i = 0;
      break;
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.barTempView.setTitleItem(new NavigationBarItem("蜻蜓游乐场"));
      setData();
    }
  }

  public void controllerDidPopped()
  {
    this.gamesView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    case 3:
    default:
      return;
    case 2:
    }
    ControllerManager.getInstance().popLastController();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("emptynow"));
    while (!paramString.equalsIgnoreCase("hideManage"))
      return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.PlayGameController
 * JD-Core Version:    0.6.2
 */