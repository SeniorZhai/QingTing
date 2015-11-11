package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadView;
import fm.qingting.utils.QTMSGManage;

public class MyDownloadController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTopView;
  private boolean inManageMode = false;
  private MyDownloadView mainView;

  public MyDownloadController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "mydownload";
    QTMSGManage.getInstance().sendStatistcsMessage("download_haveContent");
    this.mainView = new MyDownloadView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("我的下载"));
    this.barTopView.setRightItem("删除");
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mainView.update(paramString, null);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
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
      this.barTopView.setRightItem("删除");
      this.mainView.update("hideManage", null);
      if (this.inManageMode)
        break label95;
    }
    label95: for (boolean bool = true; ; bool = false)
    {
      this.inManageMode = bool;
      return;
      this.barTopView.setRightItem("取消");
      this.mainView.update("showManage", null);
      break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.MyDownloadController
 * JD-Core Version:    0.6.2
 */