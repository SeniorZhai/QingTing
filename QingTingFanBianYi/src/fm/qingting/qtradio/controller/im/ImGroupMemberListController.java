package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.view.im.GroupMemberListView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;

public class ImGroupMemberListController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private GroupMemberListView mainView;

  public ImGroupMemberListController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "groupmemberlist";
    this.mainView = new GroupMemberListView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("群组成员"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mainView.update(paramString, paramObject);
      QTMSGManage.getInstance().sendStatistcsMessage("imgroupmember");
      paramString = QTLogger.getInstance().buildEnterIMLog(6);
      if (paramString != null)
        LogModule.getInstance().send("IMUI", paramString);
    }
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImGroupMemberListController
 * JD-Core Version:    0.6.2
 */