package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.playhistory.PlayHistoryView;
import java.util.List;

public class PlayHistoryController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTempView;
  private PlayHistoryView channelsView;
  private boolean inManageMode = false;

  public PlayHistoryController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "playhistory";
    this.channelsView = new PlayHistoryView(paramContext);
    attachView(this.channelsView);
    this.barTempView = new NavigationBarView(paramContext);
    this.barTempView.setLeftItem(0);
    this.barTempView.setRightItem("编辑");
    this.barTempView.setBarListener(this);
    setNavigationBar(this.barTempView);
  }

  private void setData()
  {
    int j = 0;
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    NavigationBarView localNavigationBarView;
    if (localList.size() > 0)
    {
      i = 1;
      localNavigationBarView = this.barTempView;
      if (i == 0)
        break label63;
    }
    label63: for (int i = j; ; i = 4)
    {
      localNavigationBarView.setRightItemVisibility(i);
      this.channelsView.update("setData", localList);
      return;
      i = 0;
      break;
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.barTempView.setTitleItem(new NavigationBarItem(InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.mTitle));
      setData();
    }
  }

  public void controllerDidPopped()
  {
    this.channelsView.close(false);
    super.controllerDidPopped();
  }

  public void controllerReappeared()
  {
    setData();
    super.controllerReappeared();
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      ControllerManager.getInstance().popLastController();
      return;
    case 3:
    }
    Object localObject = this.channelsView;
    String str;
    if (this.inManageMode)
    {
      str = "hideManage";
      ((PlayHistoryView)localObject).update(str, null);
      localObject = this.barTempView;
      if (!this.inManageMode)
        break label96;
      str = "编辑";
      label68: ((NavigationBarView)localObject).setRightItem(str);
      if (this.inManageMode)
        break label102;
    }
    label96: label102: for (boolean bool = true; ; bool = false)
    {
      this.inManageMode = bool;
      return;
      str = "showManage";
      break;
      str = "取消";
      break label68;
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("emptynow"))
    {
      this.channelsView.update("hideManage", null);
      this.barTempView.setRightItem("编辑");
      this.barTempView.setRightItemVisibility(4);
      this.inManageMode = false;
    }
    while (!paramString.equalsIgnoreCase("hideManage"))
      return;
    this.channelsView.update("hideManage", null);
    this.barTempView.setRightItem("编辑");
    this.inManageMode = false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.PlayHistoryController
 * JD-Core Version:    0.6.2
 */