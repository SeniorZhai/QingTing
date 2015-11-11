package fm.qingting.qtradio.controller.chatRoom;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.room.GetOnlineUsersAction;
import fm.qingting.qtradio.room.Room;
import fm.qingting.qtradio.room.RoomDataCenter;
import fm.qingting.qtradio.room.RoomDataCenter.IRoomDataEventListener;
import fm.qingting.qtradio.room.RoomManager;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.view.chatroom.onlinemembersview.OnlineMembersListView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class OnlineMembersController extends ViewController
  implements INavigationBarListener, RoomDataCenter.IRoomDataEventListener
{
  private NavigationBarView barTopView;
  private OnlineMembersListView mainView;
  private String roomId;

  public OnlineMembersController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "online";
    this.mainView = new OnlineMembersListView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("在线(0)"));
    setNavigationBar(this.barTopView);
    this.barTopView.setBarListener(this);
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLROU");
  }

  private void openUserProfile(final UserInfo paramUserInfo)
  {
    if (!CloudCenter.getInstance().isLogin(false))
    {
      paramUserInfo = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          ControllerManager.getInstance().redirectToUsersProfileView(paramUserInfo);
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", paramUserInfo);
      return;
    }
    ControllerManager.getInstance().redirectToUsersProfileView(paramUserInfo);
  }

  public void config(String paramString, Object paramObject)
  {
    if ((!paramString.equalsIgnoreCase("setData")) || (paramObject == null));
    do
    {
      return;
      this.roomId = ((String)paramObject);
      paramObject = new GetOnlineUsersAction();
      paramObject.setConnectRoomId(this.roomId, 1);
      RoomManager.getInstance().getRoomByType(1).doAction(paramObject);
    }
    while (RoomDataCenter.getInstance().getRoomUsersByType(1, this.roomId) == null);
    paramObject = "在线(" + RoomDataCenter.getInstance().getRoomUsersCntByType(1, this.roomId) + ")";
    this.barTopView.setTitleItem(new NavigationBarItem(paramObject));
    this.mainView.update(paramString, RoomDataCenter.getInstance().getRoomUsersByType(1, this.roomId));
  }

  public void controllerDidPopped()
  {
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLROU", this);
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }

  public void onRoomData(String paramString)
  {
    if ((!paramString.equalsIgnoreCase("RLROU")) || (RoomDataCenter.getInstance().getRoomUsersByType(1, this.roomId) == null))
      return;
    paramString = "在线(" + RoomDataCenter.getInstance().getRoomUsersCntByType(1, this.roomId) + ")";
    this.barTopView.setTitleItem(new NavigationBarItem(paramString));
    this.mainView.update("setData", RoomDataCenter.getInstance().getRoomUsersByType(1, this.roomId));
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("talkWithIt"))
    {
      dispatchEvent(paramString, paramObject2);
      ControllerManager.getInstance().popLastController();
    }
    while (!paramString.equalsIgnoreCase("lookItsInfo"))
      return;
    openUserProfile((UserInfo)paramObject2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.chatRoom.OnlineMembersController
 * JD-Core Version:    0.6.2
 */