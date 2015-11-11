package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.view.im.MyGroupsView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class ImMyGroupsController extends ViewController
  implements INavigationBarListener, RootNode.IInfoUpdateEventListener
{
  private NavigationBarView mBarTopView;
  private List<GroupInfo> mLstGroupInfo;
  private MyGroupsView mainView;

  public ImMyGroupsController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "mygroups";
    this.mainView = new MyGroupsView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("群组"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 6);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mainView.update(paramString, paramObject);
      paramString = (List)paramObject;
      if (paramString != null)
      {
        this.mLstGroupInfo = paramString;
        this.mBarTopView.setTitleItem(new NavigationBarItem("群组(" + this.mLstGroupInfo.size() + ")"));
        int i = 0;
        if (i < this.mLstGroupInfo.size())
        {
          if (this.mLstGroupInfo.get(i) == null);
          while (true)
          {
            i += 1;
            break;
            paramString = IMAgent.getInstance().getGroupInfo(((GroupInfo)this.mLstGroupInfo.get(i)).groupId);
            if (paramString == null)
              IMAgent.getInstance().loadGroupInfo(((GroupInfo)this.mLstGroupInfo.get(i)).groupId, this);
            else
              ((GroupInfo)this.mLstGroupInfo.get(i)).update(paramString);
          }
        }
      }
      QTMSGManage.getInstance().sendStatistcsMessage("imgrouplist");
      paramString = QTLogger.getInstance().buildEnterIMLog(3);
      if (paramString != null)
        LogModule.getInstance().send("IMUI", paramString);
    }
  }

  public void controllerDidPopped()
  {
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(6, this);
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onInfoUpdated(int paramInt)
  {
    if ((paramInt == 6) && (this.mLstGroupInfo != null))
    {
      paramInt = 0;
      while (paramInt < this.mLstGroupInfo.size())
      {
        GroupInfo localGroupInfo = IMAgent.getInstance().getGroupInfo(((GroupInfo)this.mLstGroupInfo.get(paramInt)).groupId);
        if ((localGroupInfo != null) && (((GroupInfo)this.mLstGroupInfo.get(paramInt)).lstAdmins == null))
          ((GroupInfo)this.mLstGroupInfo.get(paramInt)).update(localGroupInfo);
        paramInt += 1;
      }
      this.mainView.update("setData", this.mLstGroupInfo);
    }
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImMyGroupsController
 * JD-Core Version:    0.6.2
 */