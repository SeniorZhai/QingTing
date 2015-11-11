package fm.qingting.qtradio.controller;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.Action;
import fm.qingting.qtradio.room.CheckInAction;
import fm.qingting.qtradio.room.GetHistoryAction;
import fm.qingting.qtradio.room.Room;
import fm.qingting.qtradio.room.RoomManager;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.view.playview.PlayView;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ViewCaptureUtil;
import java.util.List;

public class PlayController extends ViewController
  implements RootNode.IPlayInfoEventListener, InfoManager.ISubscribeEventListener
{
  private static final long TIME_STAMP = 2592000000L;
  private static PlayController _instance;
  private ActivityNode mActivityNode;
  private boolean mAutoPlay = false;
  private boolean mHasLoadProgramSchedule = false;
  private int mLoadProgramId = 0;
  private PlayView mainView;

  private PlayController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "mainplayview";
    setNavigationBarMode(INavigationSetting.Mode.FULLSCREEN);
    this.mainView = new PlayView(paramContext);
    ViewCaptureUtil.setScreenView(this.mainView);
    attachView(this.mainView);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 0);
  }

  private void autoPlay()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localObject != null)
    {
      if (((ChannelNode)localObject).channelType != 0)
        break label97;
      if (!((ChannelNode)localObject).hasEmptyProgramSchedule())
        break label49;
      PlayerAgent.getInstance().play((Node)localObject);
      this.mHasLoadProgramSchedule = true;
      InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)localObject, this);
    }
    label49: label97: 
    do
    {
      Node localNode;
      do
      {
        return;
        localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
      }
      while ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)localNode).channelId == ((ChannelNode)localObject).channelId));
      PlayerAgent.getInstance().play((Node)localObject);
      return;
      if (((ChannelNode)localObject).hasEmptyProgramSchedule())
      {
        this.mAutoPlay = true;
        this.mHasLoadProgramSchedule = true;
        InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)localObject, this);
        return;
      }
      localObject = (ProgramNode)((ChannelNode)localObject).getAllLstProgramNode().get(0);
    }
    while (localObject == null);
    PlayerAgent.getInstance().play((Node)localObject);
    chooseNodetoSet();
  }

  private void checkin()
  {
    if (!CloudCenter.getInstance().isLogin(true))
    {
      localObject = new CloudCenter.OnLoginEventListerner()
      {
        public void onLoginFailed(int paramAnonymousInt)
        {
        }

        public void onLoginSuccessed(int paramAnonymousInt)
        {
          if (InfoManager.getInstance().getEnableNewCheckin() == 1)
            QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestOldFin");
          MobclickAgent.onEvent(PlayController.this.getContext(), "PlayViewCheckIS");
          if ((WeiboAgent.getInstance().isSessionValid().booleanValue()) || (TencentAgent.getInstance().isSessionValid().booleanValue()))
          {
            ViewCaptureUtil.setScreenView(PlayController.this.view);
            ViewCaptureUtil.captureViewPath();
          }
          CheckInAction localCheckInAction = new CheckInAction();
          localCheckInAction.setContentInfo(1, 0);
          RoomManager.getInstance().getRoomByType(1).doAction(localCheckInAction);
        }
      };
      EventDispacthManager.getInstance().dispatchAction("showLogin", localObject);
      return;
    }
    if (InfoManager.getInstance().getEnableNewCheckin() == 1)
      QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestOldFin");
    MobclickAgent.onEvent(getContext(), "PlayViewCheckIS");
    if ((WeiboAgent.getInstance().isSessionValid().booleanValue()) || (TencentAgent.getInstance().isSessionValid().booleanValue()))
    {
      ViewCaptureUtil.setScreenView(this.view);
      ViewCaptureUtil.captureViewPath();
    }
    Object localObject = new CheckInAction();
    ((CheckInAction)localObject).setContentInfo(1, 0);
    RoomManager.getInstance().getRoomByType(1).doAction((Action)localObject);
  }

  private void chooseNodetoSet()
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (localNode == null)
    {
      localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if ((localObject != null) && (((ChannelNode)localObject).hasEmptyProgramSchedule()))
      {
        this.mHasLoadProgramSchedule = true;
        InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)localObject, this);
      }
    }
    do
    {
      do
      {
        return;
        this.mainView.update("setProgramNode", localNode);
        this.mHasLoadProgramSchedule = false;
        localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while (localObject == null);
      if (((ChannelNode)localObject).hasEmptyProgramSchedule())
      {
        this.mHasLoadProgramSchedule = true;
        this.mainView.update("setProgramNode", ProgramHelper.getInstance().getProgramTempNode());
        InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)localObject, this);
        return;
      }
      if (localNode.nodeName.equalsIgnoreCase("channel"))
      {
        localNode = null;
        if (((ChannelNode)localObject).isLiveChannel())
          localObject = ((ChannelNode)localObject).getProgramNodeByTime(System.currentTimeMillis());
        while (localObject != null)
        {
          this.mainView.update("setProgramNode", localObject);
          return;
          List localList = ((ChannelNode)localObject).getAllLstProgramNode();
          localObject = localNode;
          if (localList != null)
          {
            localObject = localNode;
            if (localList.size() > 0)
              localObject = (Node)localList.get(0);
          }
        }
        this.mainView.update("setProgramNode", ProgramHelper.getInstance().getProgramTempNode());
        return;
      }
    }
    while ((!localNode.nodeName.equalsIgnoreCase("program")) || (((ProgramNode)localNode).available));
    Object localObject = ((ChannelNode)localObject).getProgramNodeByTime(System.currentTimeMillis());
    if (localObject != null)
    {
      this.mainView.update("setProgramNode", localObject);
      return;
    }
    this.mainView.update("setProgramNode", ProgramHelper.getInstance().getProgramTempNode());
  }

  private void execAchilles()
  {
    if (this.mActivityNode == null)
    {
      this.mActivityNode = new ActivityNode();
      this.mActivityNode.id = 1;
      this.mActivityNode.name = "活动";
      this.mActivityNode.type = "1";
      this.mActivityNode.updatetime = 25200;
      this.mActivityNode.infoUrl = null;
      this.mActivityNode.infoTitle = "";
      this.mActivityNode.desc = null;
      this.mActivityNode.titleIconUrl = null;
      this.mActivityNode.network = null;
      this.mActivityNode.putUserInfo = false;
    }
    this.mActivityNode.contentUrl = InfoManager.getInstance().getAchillesUrl();
    if (this.mActivityNode.contentUrl == null)
    {
      InfoManager.getInstance().setAchilles(false);
      return;
    }
    ControllerManager.getInstance().redirectToActivityViewByNode(this.mActivityNode);
  }

  public static PlayController getInstance(Context paramContext)
  {
    if (_instance == null)
      _instance = new PlayController(paramContext);
    return _instance;
  }

  private void joinChat()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localObject == null);
    while (true)
    {
      return;
      if (((ChannelNode)localObject).groupId == 0)
      {
        int i = ((ChannelNode)localObject).channelId;
        if (((ChannelNode)localObject).channelType == 1)
        {
          localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
          if ((localObject == null) || (!((Node)localObject).nodeName.equalsIgnoreCase("program")) || (((ProgramNode)localObject).getCurrPlayStatus() != 1));
        }
        for (localObject = String.valueOf(((ProgramNode)localObject).channelId); localObject != null; localObject = String.valueOf(i))
        {
          GetHistoryAction localGetHistoryAction = new GetHistoryAction();
          localGetHistoryAction.setConnectUrl(InfoManager.getInstance().chatServer, (String)localObject, 1);
          RoomManager.getInstance().getRoomByType(1).doAction(localGetHistoryAction);
          return;
        }
      }
    }
  }

  private void openChatRoom()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("program")));
    for (ProgramNode localProgramNode = (ProgramNode)localObject; ; localProgramNode = null)
    {
      if (localProgramNode == null)
        return;
      localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if (localProgramNode.channelType == 0)
        localObject = IMAgent.getInstance().getGroupInfo(String.valueOf(localProgramNode.groupId));
      while (true)
        if ((localObject != null) && (((GroupInfo)localObject).groupId != null) && (((GroupInfo)localObject).groupId.length() > 0) && (InfoManager.getInstance().enableSocial(((GroupInfo)localObject).groupId)))
        {
          ControllerManager.getInstance().openImGroupProfileController(((GroupInfo)localObject).groupId);
          return;
          if (localProgramNode.groupId != 0)
            localObject = IMAgent.getInstance().getGroupInfo(String.valueOf(localProgramNode.groupId));
          else if (localObject != null)
            localObject = IMAgent.getInstance().getGroupInfo(String.valueOf(((ChannelNode)localObject).groupId));
        }
        else
        {
          if (!QtApiLevelManager.isApiLevelSupported(8))
          {
            Toast.makeText(getContext(), "非常抱歉直播间只能在Android2.2版本以上使用", 0).show();
            return;
          }
          ControllerManager.getInstance().openChatRoom("", null, null, localProgramNode, new Object[0]);
          return;
          localObject = null;
        }
    }
  }

  private void setProgramNode(int paramInt)
  {
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localChannelNode != null)
    {
      if (localChannelNode.hasEmptyProgramSchedule())
      {
        this.mLoadProgramId = paramInt;
        InfoManager.getInstance().loadProgramsScheduleNode(localChannelNode, null);
        InfoManager.getInstance().loadProgramInfo(localChannelNode, paramInt, this);
      }
    }
    else
      return;
    ProgramNode localProgramNode = localChannelNode.getProgramNode(Integer.valueOf(paramInt).intValue());
    if (localProgramNode != null)
    {
      PlayerAgent.getInstance().play(localProgramNode);
      chooseNodetoSet();
      return;
    }
    this.mLoadProgramId = paramInt;
    InfoManager.getInstance().loadProgramInfo(localChannelNode, paramInt, this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (InfoManager.getInstance().getFlowPop())
    {
      SharedCfg.getInstance().setLastPopFlowTime();
      EventDispacthManager.getInstance().dispatchAction("flow", null);
    }
    if (paramString.equalsIgnoreCase("setData"))
    {
      chooseNodetoSet();
      QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "enter");
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("programid"))
      {
        setProgramNode(((Integer)paramObject).intValue());
        return;
      }
      if (paramString.equalsIgnoreCase("autoplay"))
      {
        int i = ((Integer)paramObject).intValue();
        if (i == 0)
        {
          autoPlay();
          return;
        }
        setProgramNode(i);
        return;
      }
      if (paramString.equalsIgnoreCase("liftSomeViews"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("resetSomeViews"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("showSchedule"));
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    super.controllerDidPopped();
  }

  public void controllerDidPushed()
  {
    joinChat();
    if (InfoManager.getInstance().enterChatroom(InfoManager.getInstance().root().getCurrentPlayingChannelNode()))
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          PlayController.this.openChatRoom();
        }
      }
      , 2000L);
    while (!InfoManager.getInstance().enableAchilles())
      return;
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        PlayController.this.execAchilles();
      }
    }
    , 2000L);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("progressPosition"))
      return this.mainView.getValue(paramString, paramObject);
    return super.getValue(paramString, paramObject);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RPS"))
    {
      paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if ((this.mHasLoadProgramSchedule) && (paramString != null) && (!paramString.hasEmptyProgramSchedule()))
      {
        if (paramString.channelType != 0)
          break label141;
        paramString = paramString.getProgramNodeByTime(System.currentTimeMillis());
        if (paramString != null)
        {
          if (!this.mAutoPlay)
            break label88;
          this.mAutoPlay = false;
          PlayerAgent.getInstance().play(paramString);
          this.mainView.update("setProgramNode", paramString);
        }
      }
    }
    label88: 
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if ((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARMPLAY) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE))
            {
              PlayerAgent.getInstance().play(paramString);
              break;
            }
            InfoManager.getInstance().root().setPlayingNode(paramString);
            break;
            paramString = (ProgramNode)paramString.getAllLstProgramNode().get(0);
          }
          while (paramString == null);
          if (this.mAutoPlay)
          {
            this.mAutoPlay = false;
            PlayerAgent.getInstance().play(paramString);
          }
          while (true)
          {
            chooseNodetoSet();
            return;
            if ((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARMPLAY) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE))
              PlayerAgent.getInstance().play(paramString);
          }
        }
        while ((!paramString.equalsIgnoreCase("RVPI")) || (this.mLoadProgramId == 0));
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while ((paramString == null) || (paramString.hasEmptyProgramSchedule()));
      paramString = paramString.getProgramNode(Integer.valueOf(this.mLoadProgramId).intValue());
    }
    while (paramString == null);
    label141: this.mLoadProgramId = 0;
    PlayerAgent.getInstance().play(paramString);
    chooseNodetoSet();
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    switch (paramInt)
    {
    case 0:
    default:
      return;
    case 1:
    }
    chooseNodetoSet();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("checkin"))
      checkin();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.PlayController
 * JD-Core Version:    0.6.2
 */