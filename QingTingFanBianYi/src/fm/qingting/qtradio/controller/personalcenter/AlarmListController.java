package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.AlarmInfo;
import fm.qingting.qtradio.model.AlarmInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.clock.AlarmView;
import java.util.List;

public class AlarmListController extends ViewController
  implements INavigationBarListener, IEventHandler
{
  private AlarmView alarmListView;
  private NavigationBarView barTopView;
  private boolean inManageMode = false;

  public AlarmListController(Context paramContext)
  {
    this(paramContext, true);
  }

  public AlarmListController(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    this.controllerName = "alarmlist";
    this.alarmListView = new AlarmView(paramContext);
    attachView(this.alarmListView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    if (paramBoolean)
      this.barTopView.setRightItem("编辑");
    this.barTopView.setTitleItem(new NavigationBarItem("闹钟"));
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  private void addAlarm()
  {
    ControllerManager.getInstance().openAlarmAddController(this);
  }

  private void openSettingController(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.size()))
      return;
    ControllerManager.getInstance().openAlarmSettingController((AlarmInfo)InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.get(paramInt), this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.alarmListView.update("setData", InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms);
  }

  public void controllerDidPopped()
  {
    this.alarmListView.close(false);
    super.controllerDidPopped();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("refreshList"))
    {
      this.alarmListView.update(paramString, InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms);
      if ((InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms != null) && (InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.size() > 0))
        this.barTopView.setRightItem("编辑");
    }
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
    if (this.inManageMode)
    {
      this.barTopView.setRightItem("编辑");
      this.alarmListView.update("hideManage", null);
      if (this.inManageMode)
        break label95;
    }
    label95: for (boolean bool = true; ; bool = false)
    {
      this.inManageMode = bool;
      return;
      this.barTopView.setRightItem("取消");
      this.alarmListView.update("showManage", null);
      break;
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("select"))
      openSettingController(((Integer)paramObject2).intValue());
    do
    {
      int i;
      do
      {
        do
        {
          return;
          if (paramString.equalsIgnoreCase("onclick"))
          {
            if (this.inManageMode)
            {
              this.barTopView.setRightItem("编辑");
              this.alarmListView.update("hideManage", null);
              this.inManageMode = false;
            }
            addAlarm();
            return;
          }
        }
        while (!paramString.equalsIgnoreCase("delete"));
        i = ((Integer)paramObject2).intValue();
      }
      while ((i < 0) || (i >= InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.size()));
      InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.removeAlarm(i);
      this.alarmListView.update("refreshList", null);
    }
    while ((InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms != null) && (InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.size() != 0));
    this.barTopView.setRightItem("编辑");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.AlarmListController
 * JD-Core Version:    0.6.2
 */