package fm.qingting.qtradio.controller.chatRoom;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramTopicInfoNode;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.Action;
import fm.qingting.qtradio.room.AskSongAction;
import fm.qingting.qtradio.room.AskSongTogetherAction;
import fm.qingting.qtradio.room.ChatData;
import fm.qingting.qtradio.room.CheckInAction;
import fm.qingting.qtradio.room.CustomData;
import fm.qingting.qtradio.room.FlowerAction;
import fm.qingting.qtradio.room.GetHistoryAction;
import fm.qingting.qtradio.room.GetTopicAction;
import fm.qingting.qtradio.room.JoinAction;
import fm.qingting.qtradio.room.LeaveAction;
import fm.qingting.qtradio.room.LoginAction;
import fm.qingting.qtradio.room.Room;
import fm.qingting.qtradio.room.RoomDataCenter;
import fm.qingting.qtradio.room.RoomDataCenter.IRoomDataEventListener;
import fm.qingting.qtradio.room.RoomDataCenter.IRoomStateListener;
import fm.qingting.qtradio.room.RoomManager;
import fm.qingting.qtradio.room.SendAction;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.SpeakToAction;
import fm.qingting.qtradio.room.TellSongAction;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboData;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.view.chatroom.ActionsFloatView.ChatAction;
import fm.qingting.qtradio.view.chatroom.ChatActionsView.ChatActionType;
import fm.qingting.qtradio.view.chatroom.ChatRoomMainView;
import fm.qingting.qtradio.view.chatroom.FlowerInfo;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.qtradio.weiboAgent.WeiboAgent.WeiboDataType;
import fm.qingting.utils.ExpressionUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ViewCaptureUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lfm.qingting.qtradio.room.CustomData;>;
import java.util.Locale;
import java.util.Map;

public class ChatRoomcontroller extends ViewController
  implements RoomDataCenter.IRoomDataEventListener, RoomDataCenter.IRoomStateListener, IEventHandler, RootNode.IPlayInfoEventListener, InfoManager.ISubscribeEventListener
{
  private String LiveRoomName;
  private final long TIME_INTERVAL = 300000L;
  private final Handler delayHandler = new Handler();
  private Runnable delayRunnable = new Runnable()
  {
    public void run()
    {
      ChatRoomcontroller.RemindMessage localRemindMessage = ChatRoomcontroller.this.mMsgPool.pickMessage();
      if (localRemindMessage != null)
      {
        ChatRoomcontroller.this.showMessage(localRemindMessage);
        return;
      }
      ChatRoomcontroller.this.hideMessage();
    }
  };
  private boolean hasDoneSpeak = true;
  private Calendar mCalendar;
  private ActionsFloatView.ChatAction mCurrentAction = ActionsFloatView.ChatAction.None;
  private int mDay;
  private HashSet<String> mDjIds;
  private boolean mFirstFilter = true;
  private long mLastAtMeTime;
  private int mLastIndexAtMe = -1;
  private MessagePool mMsgPool = new MessagePool(null);
  private ProgramNode mNode;
  private String mRoomId = "";
  private ChatRoomMainView mainView;
  private UserInfo reportObject;
  private Object speakParam;

  public ChatRoomcontroller(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "chatroom";
    setNavigationBarMode(INavigationSetting.Mode.FULLSCREEN);
    ExpressionUtil.getInstance().init(paramContext, hashCode());
    this.mainView = new ChatRoomMainView(paramContext);
    attachView(this.mainView);
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLRCD");
    RoomDataCenter.getInstance().registerRoomStateEventListener(this, "RLRJ");
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLRUE");
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLROU");
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLRT");
    RoomDataCenter.getInstance().registerRoomDataEventListener(this, "RLRAS");
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
    QTMSGManage.getInstance().sendStatistcsMessage("chatroom_enter");
  }

  private void addCollection()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localObject == null);
    do
    {
      do
      {
        return;
        if (!((Node)localObject).nodeName.equalsIgnoreCase("program"))
          break;
        localObject = ((Node)localObject).parent;
      }
      while ((localObject == null) || (!((Node)localObject).nodeName.equalsIgnoreCase("channel")));
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject))
      {
        InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode((Node)localObject);
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickcollection", "del");
        return;
      }
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode((Node)localObject);
      QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickcollection", "add");
      return;
    }
    while (!((Node)localObject).nodeName.equalsIgnoreCase("channel"));
    if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject))
    {
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode((Node)localObject);
      QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickcollection", "del");
      return;
    }
    InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode((Node)localObject);
    QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickcollection", "add");
  }

  private void answerSongName()
  {
    this.reportObject = null;
    if (CloudCenter.getInstance().isLogin(false))
    {
      this.mainView.update("reportSongName", null);
      return;
    }
    CloudCenter.OnLoginEventListerner local4 = new CloudCenter.OnLoginEventListerner()
    {
      public void onLoginFailed(int paramAnonymousInt)
      {
      }

      public void onLoginSuccessed(int paramAnonymousInt)
      {
        ChatRoomcontroller.this.mainView.update("reportSongName", null);
      }
    };
    EventDispacthManager.getInstance().dispatchAction("showLogin", local4);
  }

  private List<ChatItem> filterDataByUserInfo(List<CustomData> paramList, UserInfo paramUserInfo)
  {
    if (paramList != null);
    while (true)
    {
      int i;
      long l1;
      Object localObject1;
      Object localObject2;
      int k;
      long l2;
      int j;
      UserInfo localUserInfo;
      label194: long l3;
      int m;
      try
      {
        i = paramList.size();
        if (i == 0)
        {
          paramList = null;
          return paramList;
        }
        resetBaseTime();
        if (paramUserInfo != null)
          break label1323;
        this.mLastIndexAtMe = -1;
        l1 = 0L;
        paramUserInfo = null;
        if (!this.mFirstFilter)
          break label1294;
        String str;
        if ((InfoManager.getInstance().getUserProfile() != null) && (InfoManager.getInstance().getUserProfile().getUserInfo() != null))
        {
          localObject1 = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
          localObject2 = new ArrayList();
          k = 0;
          l2 = 0L;
          if (k >= paramList.size())
            continue;
          j = 17;
          localCustomData = (CustomData)paramList.get(k);
          if (localCustomData.type != 1)
            continue;
          localUserInfo = ((ChatData)localCustomData).user;
          str = localUserInfo.snsInfo.sns_id;
          if (!localUserInfo.userId.equalsIgnoreCase(InfoManager.getInstance().getDeviceId()))
            continue;
          if (((ChatData)localCustomData).conentType == 2)
          {
            i = 2;
            break label1287;
            if (localCustomData.createTime - l2 < 300000L)
              break label1280;
            l1 = localCustomData.createTime;
            ((List)localObject2).add(new ChatItem(i, localCustomData));
            k += 1;
            l2 = l1;
            Object localObject3;
            l1 = localObject3;
            continue;
          }
        }
        else
        {
          this.mFirstFilter = false;
          break label1294;
        }
        if (((ChatData)localCustomData).conentType == 1)
        {
          i = 3;
          break label1287;
        }
        if (((ChatData)localCustomData).conentType != 0)
          break label1273;
        if ((this.mDjIds == null) || (!this.mDjIds.contains(str)))
          break label1299;
        i = 4;
        break label1287;
        if (((ChatData)localCustomData).conentType == 2)
        {
          i = 18;
          break label1287;
        }
        if (((ChatData)localCustomData).conentType == 1)
        {
          i = 19;
          break label1287;
        }
        if (((ChatData)localCustomData).conentType != 0)
          break label1273;
        if ((this.mDjIds == null) || (!this.mDjIds.contains(str)))
          break label1305;
        i = 20;
        j = i;
        if (localObject1 == null)
          break label1273;
        j = i;
        if (((ChatData)localCustomData).content == null)
          break label1273;
        j = i;
        if (!((ChatData)localCustomData).content.contains("@" + (String)localObject1))
          break label1273;
        if (localObject2 != null)
          this.mLastIndexAtMe = (((List)localObject2).size() + 1);
        l1 = ((ChatData)localCustomData).createTime;
        paramUserInfo = ((ChatData)localCustomData).user.snsInfo.sns_name;
        break label1287;
        if (localCustomData.type != 2)
          break label1262;
        if (!((WeiboData)localCustomData).user.userId.equalsIgnoreCase(InfoManager.getInstance().getDeviceId()))
          break label1312;
        i = 1;
        l3 = l1;
        continue;
        if ((this.mLastIndexAtMe >= 0) && (l1 > this.mLastAtMeTime) && (paramUserInfo != null))
        {
          this.mLastAtMeTime = l1;
          SharedCfg.getInstance().updateAtMeLatestTime(this.mRoomId, this.mLastAtMeTime);
          this.mMsgPool.addMessage(new RemindMessage(ActionsFloatView.ChatAction.Remind, paramUserInfo));
        }
        this.mFirstFilter = false;
        paramList = (List<CustomData>)localObject2;
        continue;
        if (k >= paramList.size())
          break label1224;
        CustomData localCustomData = (CustomData)paramList.get(k);
        int n = 17;
        if (localCustomData.type == 1)
        {
          localObject2 = ((ChatData)localCustomData).user;
          localUserInfo = ((ChatData)localCustomData).toUser;
          j = 0;
          if (((UserInfo)localObject2).userId.equalsIgnoreCase(InfoManager.getInstance().getDeviceId()))
          {
            i = n;
            if (((ChatData)localCustomData).conentType == 0)
            {
              if (localUserInfo == null)
              {
                if ((((ChatData)localCustomData).content == null) || (!((ChatData)localCustomData).content.contains("@" + paramUserInfo.snsInfo.sns_name)))
                  break label1253;
                i = 1;
                break label1334;
              }
            }
            else
            {
              if (j == 0)
                break label1250;
              if (localObject1 != null)
                break label1247;
              localObject1 = new ArrayList();
              if (localCustomData.createTime - l1 < 300000L)
                break label1259;
              ((List)localObject1).add(new ChatItem(32, getTimestamp(localCustomData.createTime)));
              l1 = localCustomData.createTime;
              ((List)localObject1).add(new ChatItem(i, localCustomData));
              break label1348;
            }
            if (!localUserInfo.getUid().equalsIgnoreCase(paramUserInfo.getUid()))
              break label1253;
            i = 1;
            break label1334;
          }
          i = n;
          if (!((UserInfo)localObject2).getUid().equalsIgnoreCase(paramUserInfo.getUid()))
            continue;
          m = 1;
          if (((ChatData)localCustomData).conentType == 2)
          {
            i = 18;
            j = m;
            continue;
          }
          if (((ChatData)localCustomData).conentType == 1)
          {
            i = 19;
            j = m;
            continue;
          }
          j = m;
          i = n;
          if (((ChatData)localCustomData).conentType != 0)
            continue;
          i = 17;
          j = m;
          continue;
        }
        localObject2 = localObject1;
        l2 = l1;
        if (localCustomData.type != 2)
          break label1355;
        localObject2 = ((WeiboData)localCustomData).user;
        localUserInfo = ((WeiboData)localCustomData).toUser;
        if (!((UserInfo)localObject2).userId.equalsIgnoreCase(InfoManager.getInstance().getDeviceId()))
          break label1203;
        if (localUserInfo == null)
        {
          if ((((ChatData)localCustomData).content == null) || (!((ChatData)localCustomData).content.contains("@" + paramUserInfo.snsInfo.sns_name)))
            break label1241;
          i = 1;
          localObject2 = localObject1;
          l2 = l1;
          if (i == 0)
            break label1355;
          localObject2 = localObject1;
          if (localObject1 == null)
            localObject2 = new ArrayList();
          l2 = l1;
          if (localCustomData.createTime - l1 >= 300000L)
          {
            ((List)localObject2).add(new ChatItem(32, getTimestamp(localCustomData.createTime)));
            l2 = localCustomData.createTime;
          }
          ((List)localObject2).add(new ChatItem(2, localCustomData));
        }
      }
      finally
      {
      }
      if (localUserInfo.getUid().equalsIgnoreCase(paramUserInfo.getUid()))
      {
        i = 1;
        continue;
        label1203: if (((UserInfo)localObject2).getUid().equalsIgnoreCase(paramUserInfo.getUid()))
        {
          i = 1;
          continue;
          label1224: paramList = (List<CustomData>)localObject1;
          if (localObject1 != null)
            continue;
          paramList = new ArrayList();
        }
      }
      else
      {
        label1241: i = 0;
        continue;
        label1247: continue;
        label1250: break label1348;
        label1253: i = 0;
        break label1334;
        label1259: continue;
        label1262: i = 17;
        l3 = l1;
        continue;
        label1273: i = j;
        break label1287;
        label1280: l1 = l2;
        continue;
        while (true)
        {
          label1287: l3 = l1;
          break label194;
          label1294: localObject1 = null;
          break;
          label1299: i = 1;
        }
        label1305: i = 17;
        continue;
        label1312: i = 17;
        l3 = l1;
        continue;
        label1323: l1 = 0L;
        localObject1 = null;
        k = 0;
        continue;
        label1334: m = 1;
        j = i;
        i = m;
        continue;
        label1348: l2 = l1;
        localObject2 = localObject1;
        label1355: k += 1;
        localObject1 = localObject2;
        l1 = l2;
      }
    }
  }

  private void getBroadcastersId(ProgramNode paramProgramNode)
  {
    if (this.mDjIds != null)
    {
      this.mDjIds.clear();
      this.mDjIds = null;
    }
    paramProgramNode = paramProgramNode.lstBroadcaster;
    if ((paramProgramNode == null) || (paramProgramNode.size() == 0));
    while (true)
    {
      return;
      paramProgramNode = paramProgramNode.iterator();
      while (paramProgramNode.hasNext())
      {
        BroadcasterNode localBroadcasterNode = (BroadcasterNode)paramProgramNode.next();
        if ((localBroadcasterNode.weiboId != null) && (!localBroadcasterNode.weiboId.equalsIgnoreCase("")))
        {
          if (this.mDjIds == null)
            this.mDjIds = new HashSet();
          this.mDjIds.add(localBroadcasterNode.weiboId);
        }
      }
    }
  }

  private String getTimeInDay(int paramInt1, int paramInt2)
  {
    String str;
    if (paramInt1 < 6)
      str = "凌晨";
    while (true)
    {
      return String.format(Locale.CHINESE, "%s%02d:%02d", new Object[] { str, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
      if (paramInt1 < 12)
        str = "早上";
      else if (paramInt1 < 13)
        str = "中午";
      else if (paramInt1 < 18)
        str = "下午";
      else
        str = "晚上";
    }
  }

  private String getTimestamp(long paramLong)
  {
    this.mCalendar.setTimeInMillis(paramLong);
    int k = this.mCalendar.get(6);
    int i = this.mCalendar.get(11);
    int j = this.mCalendar.get(12);
    if (k == this.mDay)
      return getTimeInDay(i, j);
    if (k == this.mDay - 1)
      return "昨天 " + getTimeInDay(i, j);
    k = this.mCalendar.get(2);
    int m = this.mCalendar.get(5);
    return String.format(Locale.CHINESE, "%d月%d日 %s", new Object[] { Integer.valueOf(k), Integer.valueOf(m), getTimeInDay(i, j) });
  }

  private String getTopic()
  {
    GetTopicAction localGetTopicAction = new GetTopicAction();
    localGetTopicAction.setContentInfo(1, this.mRoomId);
    RoomManager.getInstance().getRoomByType(1).doAction(localGetTopicAction);
    return RoomDataCenter.getInstance().getRoomTopic(1, this.mRoomId);
  }

  private void hideMessage()
  {
    this.mCurrentAction = ActionsFloatView.ChatAction.None;
    this.mainView.update("hideMessage", null);
  }

  private boolean isAccountValid()
  {
    return CloudCenter.getInstance().isLogin(false);
  }

  private void leaveLiveRoom()
  {
    LeaveAction localLeaveAction = new LeaveAction();
    localLeaveAction.setContentInfo(1, 1);
    RoomManager.getInstance().getRoomByType(1).doAction(localLeaveAction);
  }

  private void openMemberController()
  {
    QTMSGManage.getInstance().sendStatistcsMessage("chat_clickOnline");
    this.mainView.update("closeKeyboard", null);
    ControllerManager.getInstance().openOnlineMemberController(this.mRoomId, this);
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

  private void requestSongName()
  {
    if (CloudCenter.getInstance().isLogin(false))
    {
      localObject = new AskSongAction();
      ((AskSongAction)localObject).setContentInfo(1, this.mRoomId);
      RoomManager.getInstance().getRoomByType(1).doAction((Action)localObject);
      return;
    }
    Object localObject = new CloudCenter.OnLoginEventListerner()
    {
      public void onLoginFailed(int paramAnonymousInt)
      {
      }

      public void onLoginSuccessed(int paramAnonymousInt)
      {
        AskSongAction localAskSongAction = new AskSongAction();
        localAskSongAction.setContentInfo(1, ChatRoomcontroller.this.mRoomId);
        RoomManager.getInstance().getRoomByType(1).doAction(localAskSongAction);
      }
    };
    EventDispacthManager.getInstance().dispatchAction("showLogin", localObject);
  }

  private void resetBaseTime()
  {
    if (this.mCalendar == null)
      this.mCalendar = Calendar.getInstance();
    this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    this.mDay = this.mCalendar.get(6);
  }

  private void setTopic(String paramString)
  {
    if (paramString == null)
      return;
    this.mMsgPool.addMessage(new RemindMessage(ActionsFloatView.ChatAction.Topic, paramString));
  }

  private void showMessage(RemindMessage paramRemindMessage)
  {
    this.mCurrentAction = paramRemindMessage.getAction();
    this.mainView.update(this.mCurrentAction.getMessageEventType(), paramRemindMessage.getData());
    startDelayTimer(this.mCurrentAction.getTimeLength());
  }

  private void startDelayTimer(long paramLong)
  {
    this.delayHandler.removeCallbacks(this.delayRunnable);
    this.delayHandler.postDelayed(this.delayRunnable, paramLong);
  }

  public void config(String paramString, Object paramObject)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("startRoom"))
    {
      this.mNode = ((ProgramNode)paramObject);
      getBroadcastersId(this.mNode);
      this.mRoomId = String.valueOf(InfoManager.getInstance().root().getCurrentPlayingChannelNode().channelId);
      if (this.mNode.channelType == 1)
      {
        this.mNode.getCurrPlayStatus();
        this.mRoomId = String.valueOf(this.mNode.channelId);
      }
      if ((this.mRoomId == null) || (this.mRoomId.equalsIgnoreCase("")))
        this.mainView.update("setHeadInfo", this.mNode);
    }
    label421: 
    do
    {
      do
      {
        do
        {
          return;
          FlowerInfo.checkFlowerCnt(this.mRoomId);
          this.mLastAtMeTime = SharedCfg.getInstance().getAtMeLatestTime(this.mRoomId);
          if (RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId) == null)
          {
            paramString = new GetHistoryAction();
            paramString.setConnectUrl(InfoManager.getInstance().chatServer, this.mRoomId, 1);
            RoomManager.getInstance().getRoomByType(1).doAction(paramString);
          }
          paramString = new JoinAction();
          paramString.setConnectUrl(InfoManager.getInstance().chatServer, this.mRoomId, 1, InfoManager.getInstance().root().mProgramTopicInfoNode.getCurrentProgramTopic(String.valueOf(this.mNode.id), this.mRoomId, System.currentTimeMillis() / 1000L));
          if (RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId) != null)
          {
            paramString.setRecordCount(0);
            i = 1;
          }
          RoomManager.getInstance().getRoomByType(1).doAction(paramString);
          if (RoomDataCenter.getInstance().getRoomTopic(1, this.mRoomId) != null)
            this.mMsgPool.addMessage(new RemindMessage(ActionsFloatView.ChatAction.Topic, RoomDataCenter.getInstance().getRoomTopic(1, this.mRoomId)));
          paramString = getTopic();
          if (paramString != null)
            setTopic(paramString);
          if (i != 0)
            this.mainView.update("setData", filterDataByUserInfo(RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId), null));
          this.mainView.update("setHeadInfo", this.mNode);
          return;
          if (paramString.equalsIgnoreCase("startLocalRoom"))
          {
            paramString = InfoManager.getInstance().getCurrentLocation();
            if (paramString != null)
            {
              String str = paramString.region;
              if (str != null)
              {
                paramString = str;
                if (!str.equalsIgnoreCase(""))
                  break label421;
              }
            }
            for (paramString = "火星"; ; paramString = "火星")
            {
              Toast.makeText(getContext(), "欢迎来到" + paramString + "同城直播间,", 0).show();
              paramString = (ProgramNode)paramObject;
              this.mRoomId = InfoManager.getInstance().getLocalRoomId();
              paramObject = new JoinAction();
              paramObject.setConnectUrl(InfoManager.getInstance().chatServer, this.mRoomId, 1, null);
              if (RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId) != null)
              {
                this.mainView.update("setData", filterDataByUserInfo(RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId), null));
                paramObject.setRecordCount(0);
              }
              RoomManager.getInstance().getRoomByType(1).doAction(paramObject);
              this.mainView.update("setLocalHeadInfo", paramString);
              return;
            }
          }
          if (paramString.equalsIgnoreCase("speakTodj"))
          {
            this.hasDoneSpeak = false;
            this.speakParam = paramObject;
            return;
          }
          if (paramString.equalsIgnoreCase("flower"))
          {
            onViewEvent(this, paramString, paramObject);
            return;
          }
          if (paramString.equalsIgnoreCase("sayToIt"))
          {
            this.mainView.update(paramString, paramObject);
            return;
          }
        }
        while ((!paramString.equalsIgnoreCase("showchathistory")) || (paramObject == null) || (!(paramObject instanceof CustomData)));
        paramString = (CustomData)paramObject;
      }
      while (paramString.type != 1);
      paramObject = (ChatData)paramString;
    }
    while (paramObject.user == null);
    paramString = paramObject.user.snsInfo.sns_name;
    paramObject = filterDataByUserInfo(RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId), paramObject.user);
    ControllerManager.getInstance().openChatHistoryController(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    leaveLiveRoom();
    this.mainView.close(false);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLRCD", this);
    RoomDataCenter.getInstance().unRegisterRoomStateEventListener("RLRJ", this);
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLRUE", this);
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLRT", this);
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLROU", this);
    RoomDataCenter.getInstance().unRegisterRoomDataEventListener("RLRAS", this);
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    super.controllerDidPopped();
  }

  public void controllerDidPushed()
  {
    if (!this.hasDoneSpeak)
      this.mainView.update("speakTodj", this.speakParam);
    this.hasDoneSpeak = true;
    super.controllerDidPushed();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("login_Success"))
      ControllerManager.getInstance().popLastController();
    do
    {
      return;
      if (paramString.equalsIgnoreCase("Auth_Cancel_Return"))
      {
        ControllerManager.getInstance().popLastController();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("talkWithIt"));
    this.mainView.update("sayToIt", paramObject2);
  }

  public void onNotification(String paramString)
  {
    if (paramString == "RCPT");
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    Node localNode;
    if (paramInt == 1)
    {
      localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")));
    }
    else
    {
      return;
    }
    this.mNode = ((ProgramNode)localNode);
    getBroadcastersId(this.mNode);
    this.mainView.update("setHeadInfo", this.mNode);
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onRoomData(String paramString)
  {
    if (paramString.equalsIgnoreCase("RLRCD"))
      this.mainView.update("setData", filterDataByUserInfo(RoomDataCenter.getInstance().getRoomDataByType(1, this.mRoomId), null));
    do
    {
      do
      {
        do
        {
          return;
          if (!paramString.equalsIgnoreCase("RLRUE"))
            break;
          paramString = RoomDataCenter.getInstance().getEnterUser(1, null);
        }
        while ((paramString == null) || (paramString.userId.equalsIgnoreCase(InfoManager.getInstance().getDeviceId())));
        this.mMsgPool.addMessage(new RemindMessage(ActionsFloatView.ChatAction.SayHi, paramString));
        return;
        if (paramString.equalsIgnoreCase("RLROU"))
        {
          EventDispacthManager.getInstance().dispatchAction("refreshSearchData", RoomDataCenter.getInstance().getRoomUsersByType(1, this.mRoomId));
          return;
        }
        if (!paramString.equalsIgnoreCase("RLRT"))
          break;
        paramString = RoomDataCenter.getInstance().getRoomTopic(1, this.mRoomId);
      }
      while (paramString == null);
      setTopic(paramString);
      return;
    }
    while ((!paramString.equalsIgnoreCase("RLRAS")) || (RoomDataCenter.getInstance().getAskSongInfo(1, this.mRoomId) == null));
    this.mMsgPool.addMessage(new RemindMessage(ActionsFloatView.ChatAction.RequestSn, RoomDataCenter.getInstance().getAskSongInfo(1, this.mRoomId)));
  }

  public void onRoomState(String paramString)
  {
    if (paramString.equalsIgnoreCase("RLRJ"))
    {
      paramString = new UserInfo();
      paramString.userId = InfoManager.getInstance().getDeviceId();
      paramString.snsInfo.sns_id = paramString.userId;
      LoginAction localLoginAction = new LoginAction();
      localLoginAction.setUserInfo(paramString, 1);
      RoomManager.getInstance().getRoomByType(1).doAction(localLoginAction);
    }
  }

  protected void onViewEvent(Object paramObject1, final String paramString, Object paramObject2)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("sendDiscuss"))
    {
      paramObject1 = (String)paramObject2;
      if ((paramObject1 != null) && (!paramObject1.trim().equalsIgnoreCase("")));
    }
    do
    {
      do
      {
        return;
        QTMSGManage.getInstance().sendStatistcsMessage("chat_sendDiscuss", "all");
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_all");
        paramString = new SendAction();
        paramString.setContentInfo(paramObject1, 1);
        RoomManager.getInstance().getRoomByType(1).doAction(paramString);
        paramObject1 = this.LiveRoomName;
      }
      while (paramObject1 == null);
      paramObject1 = paramObject1 + "_sendContent";
      QTMSGManage.getInstance().sendStatistcsMessage("ClickLiveRoom", paramObject1);
      return;
      if (paramString.equalsIgnoreCase("sendReply"))
      {
        QTMSGManage.getInstance().sendStatistcsMessage("chat_sendDiscuss", "single");
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_single");
        paramObject1 = (ChatData)paramObject2;
        paramString = new SendAction();
        paramString.setContentInfo(1, paramObject1);
        RoomManager.getInstance().getRoomByType(1).doAction(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("praise"))
      {
        paramObject1 = (ChatData)paramObject2;
        paramString = new SendAction();
        paramString.setContentInfo(1, paramObject1);
        RoomManager.getInstance().getRoomByType(1).doAction(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("playPoint"))
      {
        long l = ((ChatData)paramObject2).createTime;
        dispatchEvent("queryPosition", Long.valueOf(InfoManager.getInstance().root().replayCurrNodeByTime(this.mRoomId, l)));
        return;
      }
      if (paramString.equalsIgnoreCase("CheckIn"))
      {
        if (!isAccountValid())
        {
          paramObject1 = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymousInt)
            {
            }

            public void onLoginSuccessed(int paramAnonymousInt)
            {
              MobclickAgent.onEvent(ChatRoomcontroller.this.getContext(), "ChatRoomCheckIS");
              if ((WeiboAgent.getInstance().isSessionValid().booleanValue()) || (TencentAgent.getInstance().isSessionValid().booleanValue()))
                ViewCaptureUtil.captureViewPath();
              CheckInAction localCheckInAction = new CheckInAction();
              localCheckInAction.setContentInfo(1, 0);
              RoomManager.getInstance().getRoomByType(1).doAction(localCheckInAction);
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
          return;
        }
        MobclickAgent.onEvent(getContext(), "ChatRoomCheckIS");
        if ((WeiboAgent.getInstance().isSessionValid().booleanValue()) || (TencentAgent.getInstance().isSessionValid().booleanValue()))
          ViewCaptureUtil.captureViewPath();
        paramObject1 = new CheckInAction();
        paramObject1.setContentInfo(1, 0);
        RoomManager.getInstance().getRoomByType(1).doAction(paramObject1);
        return;
      }
      if (paramString.equalsIgnoreCase("sayhi"))
      {
        hideMessage();
        paramString = (UserInfo)paramObject2;
        if ((!WeiboAgent.getInstance().isSessionValid().booleanValue()) && (!TencentAgent.getInstance().isSessionValid().booleanValue()))
        {
          paramObject1 = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymousInt)
            {
            }

            public void onLoginSuccessed(int paramAnonymousInt)
            {
              ChatData localChatData = new ChatData();
              localChatData.toUser = paramString;
              localChatData.content = "你好啊:)";
              SendAction localSendAction = new SendAction();
              localSendAction.setContentInfo(1, localChatData);
              RoomManager.getInstance().getRoomByType(1).doAction(localSendAction);
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
          return;
        }
        paramObject1 = new ChatData();
        paramObject1.toUser = paramString;
        paramObject1.content = "你好啊:)";
        paramString = new SendAction();
        paramString.setContentInfo(1, paramObject1);
        RoomManager.getInstance().getRoomByType(1).doAction(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("asktoo"))
      {
        hideMessage();
        paramObject1 = new AskSongTogetherAction();
        paramObject1.setContentInfo(1, this.mRoomId);
        RoomManager.getInstance().getRoomByType(1).doAction(paramObject1);
        return;
      }
      if (paramString.equalsIgnoreCase("response"))
      {
        hideMessage();
        this.reportObject = ((UserInfo)paramObject2);
        this.mainView.update("reportSongName", null);
        return;
      }
      if (paramString.equalsIgnoreCase("scrollToFirstIndexAtMe"))
      {
        hideMessage();
        this.mainView.update(paramString, Integer.valueOf(this.mLastIndexAtMe));
        return;
      }
      if (paramString.equalsIgnoreCase("sendSn"))
      {
        paramObject1 = new TellSongAction();
        paramObject1.setContentInfo(1, this.mRoomId, this.reportObject, (String)paramObject2);
        RoomManager.getInstance().getRoomByType(1).doAction(paramObject1);
        return;
      }
      if (paramString.equalsIgnoreCase("tosay"))
      {
        paramObject1 = (Map)paramObject2;
        paramObject2 = (BroadcasterNode)paramObject1.get("dj");
        paramString = new UserInfo();
        paramString.userId = String.valueOf(paramObject2.id);
        paramString.snsInfo.sns_id = paramObject2.weiboId;
        paramString.snsInfo.sns_name = paramObject2.weiboName;
        paramObject2 = new SpeakToAction();
        if (WeiboAgent.getInstance().isSessionValid().booleanValue());
        while (true)
        {
          paramObject2.setContentInfo(1, i, paramString, (String)paramObject1.get("message"));
          RoomManager.getInstance().getRoomByType(1).doAction(paramObject2);
          return;
          i = 1;
        }
      }
      if (paramString.equalsIgnoreCase("followDj"))
      {
        paramObject1 = (BroadcasterNode)paramObject2;
        paramString = new HashMap();
        paramString.put("uid", paramObject1.weiboId);
        WeiboAgent.getInstance().sendWeibo(WeiboAgent.WeiboDataType.TO_ADD, paramString, null, null);
        return;
      }
      if (paramString.equalsIgnoreCase("flower"))
      {
        paramString = (BroadcasterNode)paramObject2;
        if (!isAccountValid())
        {
          paramObject1 = new CloudCenter.OnLoginEventListerner()
          {
            public void onLoginFailed(int paramAnonymousInt)
            {
            }

            public void onLoginSuccessed(int paramAnonymousInt)
            {
              UserInfo localUserInfo = new UserInfo();
              localUserInfo.userId = String.valueOf(paramString.id);
              localUserInfo.snsInfo.sns_id = paramString.weiboId;
              localUserInfo.snsInfo.sns_name = paramString.weiboName;
              FlowerAction localFlowerAction = new FlowerAction();
              localFlowerAction.setContentInfo(1, 0, localUserInfo);
              RoomManager.getInstance().getRoomByType(1).doAction(localFlowerAction);
            }
          };
          EventDispacthManager.getInstance().dispatchAction("showLogin", paramObject1);
          return;
        }
        paramObject1 = new UserInfo();
        paramObject1.userId = String.valueOf(paramString.id);
        paramObject1.snsInfo.sns_id = paramString.weiboId;
        paramObject1.snsInfo.sns_name = paramString.weiboName;
        paramString = new FlowerAction();
        paramString.setContentInfo(1, 0, paramObject1);
        RoomManager.getInstance().getRoomByType(1).doAction(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("exit"))
      {
        leaveLiveRoom();
        return;
      }
      if (paramString.equalsIgnoreCase("lookItsInfo"))
      {
        openUserProfile((UserInfo)paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("chatActionType"))
      {
        paramObject1 = (ChatActionsView.ChatActionType)paramObject2;
        switch (8.$SwitchMap$fm$qingting$qtradio$view$chatroom$ChatActionsView$ChatActionType[paramObject1.ordinal()])
        {
        default:
          return;
        case 1:
          addCollection();
          return;
        case 2:
          EventDispacthManager.getInstance().dispatchAction("shareChoose", InfoManager.getInstance().root().getCurrentPlayingNode());
          QTMSGManage.getInstance().sendStatistcsMessage("chatroom_clickshare");
          return;
        case 3:
          requestSongName();
          QTMSGManage.getInstance().sendStatistcsMessage("chatroom_asksong");
          return;
        case 4:
        }
        answerSongName();
        QTMSGManage.getInstance().sendStatistcsMessage("chatroom_reportsong");
        return;
      }
      if (paramString.equalsIgnoreCase("openonlinemember"))
      {
        openMemberController();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("clickback"));
    leaveLiveRoom();
    this.mainView.update("closeKeyboard", null);
    ControllerManager.getInstance().popLastController();
  }

  private class MessagePool
  {
    private HashSet<ChatRoomcontroller.RemindMessage> mPool = new HashSet();

    private MessagePool()
    {
    }

    protected void addMessage(ChatRoomcontroller.RemindMessage paramRemindMessage)
    {
      Object localObject;
      switch (ChatRoomcontroller.8.$SwitchMap$fm$qingting$qtradio$view$chatroom$ActionsFloatView$ChatAction[paramRemindMessage.getAction().ordinal()])
      {
      default:
        return;
      case 1:
        ChatRoomcontroller.this.showMessage(paramRemindMessage);
        return;
      case 2:
        if (ChatRoomcontroller.this.mCurrentAction == ActionsFloatView.ChatAction.Topic)
          if (this.mPool.size() > 0)
          {
            Iterator localIterator = this.mPool.iterator();
            localObject = null;
            label84: if (localIterator.hasNext())
            {
              ChatRoomcontroller.RemindMessage localRemindMessage = (ChatRoomcontroller.RemindMessage)localIterator.next();
              if (localRemindMessage.getAction() != ActionsFloatView.ChatAction.Remind)
                break label187;
              localObject = localRemindMessage;
            }
          }
        break;
      case 3:
      case 4:
      }
      label187: 
      while (true)
      {
        break label84;
        if (localObject != null)
          this.mPool.remove(localObject);
        this.mPool.add(paramRemindMessage);
        return;
        ChatRoomcontroller.this.showMessage(paramRemindMessage);
        return;
        if ((ChatRoomcontroller.this.mCurrentAction == ActionsFloatView.ChatAction.Topic) || (ChatRoomcontroller.this.mCurrentAction == ActionsFloatView.ChatAction.SayHi))
          break;
        ChatRoomcontroller.this.showMessage(paramRemindMessage);
        return;
      }
    }

    protected ChatRoomcontroller.RemindMessage pickMessage()
    {
      Object localObject2 = null;
      Object localObject1 = null;
      if (this.mPool.size() > 0)
      {
        Iterator localIterator = this.mPool.iterator();
        if (localIterator.hasNext())
        {
          ChatRoomcontroller.RemindMessage localRemindMessage = (ChatRoomcontroller.RemindMessage)localIterator.next();
          if (localObject1 == null)
            localObject2 = localRemindMessage;
          while (true)
          {
            localObject1 = localObject2;
            break;
            localObject2 = localRemindMessage;
            if (!localRemindMessage.priorityThan(localObject1))
              localObject2 = localObject1;
          }
        }
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          this.mPool.remove(localObject1);
          localObject2 = localObject1;
        }
      }
      return localObject2;
    }
  }

  private class RemindMessage
  {
    private ActionsFloatView.ChatAction mAction;
    private Object mData;
    private long mInsertTime;

    public RemindMessage(ActionsFloatView.ChatAction paramObject, Object arg3)
    {
      this.mAction = paramObject;
      Object localObject;
      this.mData = localObject;
      this.mInsertTime = System.currentTimeMillis();
    }

    protected ActionsFloatView.ChatAction getAction()
    {
      return this.mAction;
    }

    protected Object getData()
    {
      return this.mData;
    }

    protected long getTime()
    {
      return this.mInsertTime;
    }

    protected boolean priorityThan(RemindMessage paramRemindMessage)
    {
      boolean bool2 = true;
      boolean bool1;
      switch (ChatRoomcontroller.8.$SwitchMap$fm$qingting$qtradio$view$chatroom$ActionsFloatView$ChatAction[this.mAction.ordinal()])
      {
      default:
        bool1 = false;
      case 1:
      case 2:
      case 3:
      case 4:
      }
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          }
          while (this.mAction.ordinal() > paramRemindMessage.getAction().ordinal());
          if (this.mAction.ordinal() != paramRemindMessage.getAction().ordinal())
            break;
          bool1 = bool2;
        }
        while (this.mInsertTime < paramRemindMessage.getTime());
        return false;
        return false;
        if ((paramRemindMessage.getAction() == ActionsFloatView.ChatAction.Topic) || (paramRemindMessage.getAction() == ActionsFloatView.ChatAction.Remind))
          return false;
        bool1 = bool2;
      }
      while (this.mInsertTime < paramRemindMessage.getTime());
      return false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.chatRoom.ChatRoomcontroller
 * JD-Core Version:    0.6.2
 */