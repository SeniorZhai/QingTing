package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.im.profile.GroupProfileView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class ImGroupProfileController extends ViewController
  implements INavigationBarListener, RootNode.IInfoUpdateEventListener
{
  private NavigationBarView mBarTopView;
  private String mGroupId;
  private GroupInfo mInfo;
  private GroupProfileView mainView;

  public ImGroupProfileController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "groupprofile";
    this.mainView = new GroupProfileView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("群资料"));
    this.mBarTopView.setLeftItem(0);
    this.mBarTopView.setRightItem("设置");
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 6);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 7);
  }

  private void setGroupInfo(GroupInfo paramGroupInfo)
  {
    if (paramGroupInfo == null)
      return;
    this.mainView.update("setData", paramGroupInfo);
    boolean bool = IMContacts.getInstance().hasWatchedGroup(paramGroupInfo.groupId);
    paramGroupInfo = this.mBarTopView;
    if (bool);
    for (int i = 0; ; i = 4)
    {
      paramGroupInfo.setRightItemVisibility(i);
      return;
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mGroupId = ((String)paramObject);
      this.mInfo = IMAgent.getInstance().getGroupInfo(this.mGroupId);
      if (this.mInfo == null)
      {
        IMAgent.getInstance().loadGroupInfo(this.mGroupId, this);
        paramString = IMAgent.getInstance().getGroupMembers(this.mGroupId);
        if ((paramString != null) && (paramString.size() > 0))
          this.mainView.update("setUsers", paramString);
        IMAgent.getInstance().loadGroupMembers(this.mGroupId, 1, 200);
        QTMSGManage.getInstance().sendStatistcsMessage("imgroupprofile");
        paramString = QTLogger.getInstance().buildEnterIMLog(5);
        if (paramString != null)
          LogModule.getInstance().send("IMUI", paramString);
      }
    }
    while (!paramString.equalsIgnoreCase("resetData"))
      while (true)
      {
        return;
        setGroupInfo(this.mInfo);
      }
    setGroupInfo(this.mInfo);
  }

  public void controllerDidPopped()
  {
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(6, this);
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(7, this);
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 6)
    {
      this.mInfo = IMAgent.getInstance().getGroupInfo(this.mGroupId);
      setGroupInfo(this.mInfo);
    }
    while (paramInt != 7)
      return;
    this.mainView.update("setUsers", IMAgent.getInstance().getGroupMembers(this.mGroupId));
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
    while ((paramInt != 3) || (this.mInfo == null))
      return;
    ControllerManager.getInstance().openImGroupSettingController(this.mInfo.groupId);
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("useraction"))
    {
      if (!IMContacts.getInstance().hasWatchedGroup(this.mGroupId))
        break label89;
      paramObject1 = ControllerManager.getInstance().getControllerUnderneath();
      if ((paramObject1 != null) && (paramObject1.controllerName.equalsIgnoreCase("imchat")))
      {
        paramObject1 = (String)paramObject1.getValue("getTalkingId", null);
        if ((paramObject1 != null) && (paramObject1.equalsIgnoreCase(this.mGroupId)))
          ControllerManager.getInstance().popLastController();
      }
    }
    else
    {
      return;
    }
    ControllerManager.getInstance().openImChatController(this.mInfo);
    return;
    label89: InfoManager.getInstance().getUserProfile().followGroup(this.mGroupId);
    this.mainView.update("setJoined", null);
    this.mBarTopView.setRightItemVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImGroupProfileController
 * JD-Core Version:    0.6.2
 */