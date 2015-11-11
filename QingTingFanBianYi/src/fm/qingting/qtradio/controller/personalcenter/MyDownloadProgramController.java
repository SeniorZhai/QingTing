package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadProgramView;

public class MyDownloadProgramController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTopView;
  private boolean inManageMode = false;
  private ChannelNode mChannelNode;
  private MyDownloadProgramView mainView;

  public MyDownloadProgramController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "downloadprogram";
    this.mainView = new MyDownloadProgramView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setRightItem("删除");
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramObject = (ChannelNode)paramObject;
      if ((paramObject != null) && (!paramObject.hasEmptyProgramSchedule()));
    }
    else
    {
      return;
    }
    this.mChannelNode = paramObject;
    this.barTopView.setTitleItem(new NavigationBarItem(paramObject.title));
    this.mainView.setChannel(paramObject);
    this.mainView.update(paramString, paramObject.getAllLstProgramNode());
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("channelNode"))
      return this.mChannelNode;
    return super.getValue(paramString, paramObject);
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
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.MyDownloadProgramController
 * JD-Core Version:    0.6.2
 */