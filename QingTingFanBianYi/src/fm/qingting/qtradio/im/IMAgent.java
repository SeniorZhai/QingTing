package fm.qingting.qtradio.im;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.data.IMDatabaseDS;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.INETEventListener;
import fm.qingting.qtradio.manager.NetWorkManage;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboChat;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.utils.ImageUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.RangeRandom;
import fm.qingting.websocket.WebSocketClient;
import fm.qingting.websocket.WebSocketClient.Listener;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.message.BasicNameValuePair;

public class IMAgent
  implements IResultRecvHandler, INETEventListener
{
  private static long MAX_RecvMsg_INTERVAL = 0L;
  private static final String TAG = "IMAgent2";
  private static IMAgent instance;
  private static long mLastRecvMsgTime = 0L;
  private final int CONNECT_CHATROOM_FAILED = 2;
  private int CONNECT_INTERVAL = 20;
  private int IM_HISTORY_SIZE = 100;
  private int MSG_CNT_PER_PAGE = 10;
  private int PING_INTERVAL = 300;
  private final String PROTOCOL_HEAD = "ws://";
  private final int RECV_LIST_MSG = 1;
  private final int RECV_SINGLE_MSG = 0;
  private long SEND_NOTIFICATION_INTERVAL = 2000L;
  private boolean autoGet = false;
  private boolean autoJoin = false;
  private boolean autoLogin = false;
  private Handler connectHandler = new Handler();
  private String connectUrl;
  private boolean connected = false;
  private boolean connecting = false;
  private Runnable doConnect = new Runnable()
  {
    public void run()
    {
      IMAgent.this.connect();
    }
  };
  private long lastPingTime = 0L;
  private boolean loginSuccess = false;
  private List<String> lstHasCheckin = new ArrayList();
  private int mAddProgramId = 0;
  private int mAddTime = 0;
  private int mBarraeProgramId = 0;
  private Context mContext;
  private String mCurrentGroupId;
  private List<UserInfo> mCurrentGroupUsers;
  private Handler mDispatchHandler = new DispatchHandler(Looper.getMainLooper());
  private JSONObject mJsonMsg = new JSONObject();
  private String mLastFromId = null;
  private String mLastNetType = "";
  private List<String> mLstEnableGroups = new ArrayList();
  private List<IMMessage> mLstImageBarrage = null;
  private IMMessage mLstSendImageBarrage = null;
  private List<String> mLstTempWatchGroups = new ArrayList();
  private List<IMMessage> mLstTextBarrage = null;
  private String mPingMsg = null;
  private int mRecvMsgCnt = 0;
  private long mRecvMsgTime = 0L;
  private int mTotalUnReadMsgCnt = 0;
  private WebSocketClient mWebSocket;
  private Map<String, UserInfo> mapATUser = new HashMap();
  private Map<String, GroupInfo> mapGroupInfo = new HashMap();
  private Map<String, List<IMEventListener>> mapIMEventListeners = new HashMap();
  private Map<String, List<IMMessage>> mapMessage = new HashMap();
  private Map<String, Integer> mapUnReadMsgCnt = new HashMap();
  private int maxHistoryRecords = 30;
  private int maxOnlineUsers = 1000;
  private int recordCnt = 0;

  private IMAgent()
  {
    NetWorkManage.getInstance().addListener(this);
    this.mLastNetType = NetWorkManage.getInstance().getNetWorkType();
    initUnReadMsg();
  }

  private void _connect()
  {
    if ((this.connectUrl == null) || (this.connectUrl.equalsIgnoreCase("")))
      getImServer();
    do
    {
      do
      {
        return;
        if (!this.connecting)
          break;
      }
      while (this.mContext == null);
      return;
    }
    while ((this.connected) && (this.mWebSocket != null));
    try
    {
      this.connecting = true;
      List localList = Arrays.asList(new BasicNameValuePair[] { new BasicNameValuePair("Cookie", "session=abcd") });
      this.mWebSocket = new WebSocketClient(URI.create(this.connectUrl), new WebSocketClient.Listener()
      {
        public void onConnect()
        {
          IMAgent.this.handleConnect();
        }

        public void onDisconnect(int paramAnonymousInt, String paramAnonymousString)
        {
          IMAgent.this.handleConnectFailure();
        }

        public void onError(Exception paramAnonymousException)
        {
          IMAgent.this.handleConnectFailure();
        }

        public void onMessage(String paramAnonymousString)
        {
          IMAgent.this.handleMessage(paramAnonymousString);
        }

        public void onMessage(byte[] paramAnonymousArrayOfByte)
        {
        }
      }
      , localList);
      this.mWebSocket.connect();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void addGroupInfo(GroupInfo paramGroupInfo)
  {
    if ((paramGroupInfo == null) || (paramGroupInfo.groupId == null))
      return;
    this.mapGroupInfo.put(paramGroupInfo.groupId, paramGroupInfo);
  }

  private void addMsgToDS(IMMessage paramIMMessage)
  {
    if (paramIMMessage == null)
      return;
    String str;
    Object localObject;
    if (paramIMMessage.isGroupMsg())
    {
      str = paramIMMessage.mFromGroupId;
      localObject = (List)this.mapMessage.get(str);
      if (localObject == null)
        break label67;
      ((List)localObject).add(paramIMMessage);
    }
    while (true)
    {
      if (!paramIMMessage.isGroupMsg())
        break label98;
      IMDatabaseDS.getInstance().appendGroupMessage(paramIMMessage, false);
      return;
      str = paramIMMessage.mFromID;
      break;
      label67: localObject = new ArrayList();
      ((List)localObject).add(paramIMMessage);
      this.mapMessage.put(str, localObject);
    }
    label98: IMDatabaseDS.getInstance().appendPrivateMessage(paramIMMessage, false);
  }

  private boolean canHandle(String paramString, int paramInt)
  {
    if (paramString == null);
    do
    {
      do
      {
        do
        {
          return false;
          if (paramInt != 0)
            break;
        }
        while (isSelf(paramString));
        return true;
      }
      while (paramInt != 1);
      if (hasInTempWatchGroups(paramString) != -1)
        return true;
    }
    while (!IMContacts.getInstance().hasWatchedGroup(paramString));
    return true;
  }

  private void dispatchIMEvent(String paramString, IMMessage paramIMMessage)
  {
    if (this.mapIMEventListeners.containsKey(paramString))
    {
      List localList = (List)this.mapIMEventListeners.get(paramString);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        ((IMEventListener)localList.get(i)).onIMEvent(paramString, paramIMMessage);
        i += 1;
      }
    }
  }

  private void dispatchIMEvent(String paramString, List<IMMessage> paramList)
  {
    if (this.mapIMEventListeners.containsKey(paramString))
    {
      List localList = (List)this.mapIMEventListeners.get(paramString);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        ((IMEventListener)localList.get(i)).onIMListMsg(paramString, paramList);
        i += 1;
      }
    }
  }

  private byte[] getFileByPath(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return null;
    try
    {
      paramString = ImageUtil.getCompressImage(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  private void getImServer()
  {
    UserInfo localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
    if ((localUserInfo != null) && (localUserInfo.userKey != null) && (!localUserInfo.userKey.equalsIgnoreCase("")))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("from", localUserInfo.userKey);
      DataManager.getInstance().getData("GET_IM_SERVER", this, localHashMap);
    }
  }

  public static IMAgent getInstance()
  {
    if (instance == null)
      instance = new IMAgent();
    return instance;
  }

  private IMMessage getLatestGroupMessage(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = (List)this.mapMessage.get(paramString);
    if (paramString == null)
      return null;
    if (paramString.size() == 0)
      return null;
    return (IMMessage)paramString.get(paramString.size() - 1);
  }

  private IMMessage getLatestUserMessage(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = (List)this.mapMessage.get(paramString);
    if (paramString == null)
      return null;
    if (paramString.size() == 0)
      return null;
    return (IMMessage)paramString.get(paramString.size() - 1);
  }

  private String getMsgId()
  {
    Object localObject = InfoManager.getInstance().getUserProfile().getUserInfo();
    if (localObject != null)
    {
      localObject = ((UserInfo)localObject).userKey;
      if (localObject != null)
      {
        long l = System.currentTimeMillis();
        return (String)localObject + l;
      }
    }
    return null;
  }

  private void handleConnectFailure()
  {
    this.connected = false;
    this.connecting = false;
    this.loginSuccess = false;
    if (this.mWebSocket != null)
      this.mWebSocket.disconnect();
    if (!InfoManager.getInstance().hasConnectedNetwork())
    {
      this.mDispatchHandler.sendMessage(this.mDispatchHandler.obtainMessage(2, null));
      return;
    }
    asyncConnect();
  }

  private void handleMessage(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return;
      try
      {
        paramString = (JSONObject)JSON.parse(paramString);
        String str = paramString.getString("event");
        if (str != null)
        {
          if (str.equalsIgnoreCase("login"))
          {
            recvLogin();
            return;
          }
          Object localObject;
          if (str.equalsIgnoreCase("peer"))
          {
            if (this.loginSuccess)
            {
              str = paramString.getString("from");
              if (canHandle(str, 0))
              {
                localObject = new IMMessage();
                ((IMMessage)localObject).mFromID = str;
                ((IMMessage)localObject).mFromName = paramString.getString("fromName");
                ((IMMessage)localObject).mGender = paramString.getString("fromGender");
                ((IMMessage)localObject).mMsgId = paramString.getString("id");
                recvMsg(0, paramString.getJSONArray("body"), (IMMessage)localObject);
              }
            }
          }
          else if ((str.equalsIgnoreCase("group")) && (this.loginSuccess))
          {
            str = paramString.getString("from");
            if (!isSelf(str))
            {
              localObject = paramString.getString("to");
              if (canHandle((String)localObject, 1))
              {
                IMMessage localIMMessage = new IMMessage();
                localIMMessage.mFromID = str;
                localIMMessage.mFromGroupId = ((String)localObject);
                localIMMessage.mFromName = paramString.getString("fromName");
                localIMMessage.mGender = paramString.getString("fromGender");
                localIMMessage.mMsgId = paramString.getString("id");
                recvMsg(1, paramString.getJSONArray("body"), localIMMessage);
                return;
              }
            }
          }
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private int hasInTempWatchGroups(String paramString)
  {
    int j;
    if (paramString == null)
    {
      j = -1;
      return j;
    }
    int i = 0;
    while (true)
    {
      if (i >= this.mLstTempWatchGroups.size())
        break label52;
      j = i;
      if (((String)this.mLstTempWatchGroups.get(i)).equalsIgnoreCase(paramString))
        break;
      i += 1;
    }
    label52: return -1;
  }

  private boolean isSelf(String paramString)
  {
    if (paramString == null);
    UserInfo localUserInfo;
    do
    {
      return false;
      localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
    }
    while ((localUserInfo == null) || (localUserInfo.userKey == null) || (!localUserInfo.userKey.equalsIgnoreCase(paramString)));
    return true;
  }

  private boolean isSetBlack(IMMessage paramIMMessage)
  {
    if (paramIMMessage == null);
    do
    {
      do
      {
        do
          return false;
        while (paramIMMessage.isGroupMsg());
        if (!IMUtil.isSetBlack(paramIMMessage.mMessage))
          break;
      }
      while (!CloudCenter.getInstance().isLiveRoomAdmin(paramIMMessage.mFromID));
      if (InfoManager.getInstance().getUserProfile().getUserInfo() != null)
        InfoManager.getInstance().getUserProfile().getUserInfo().setBlocked(true);
      return true;
    }
    while (!ImBlackList.inBlackList(this.mContext, paramIMMessage.mFromID));
    return true;
  }

  private void login()
  {
    try
    {
      Object localObject = InfoManager.getInstance().getUserProfile().getUserInfo();
      if ((localObject != null) && (this.mWebSocket != null))
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("event", "login");
        localJSONObject.put("from", ((UserInfo)localObject).userKey);
        localObject = localJSONObject.toString();
        if (localObject != null)
          this.mWebSocket.send((String)localObject);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private boolean needSendMsgNotification(IMMessage paramIMMessage)
  {
    if (paramIMMessage == null);
    String str;
    do
    {
      do
      {
        return false;
        if (paramIMMessage.isGroupMsg())
          return hasEnabledGroup(paramIMMessage.mFromGroupId);
        if (!IMUtil.isSetBlack(paramIMMessage.mMessage))
          break;
      }
      while (InfoManager.getInstance().getUserProfile().getUserInfo() == null);
      InfoManager.getInstance().getUserProfile().getUserInfo().setBlocked(true);
      return false;
      str = paramIMMessage.mFromID;
    }
    while (ControllerManager.getInstance().isActive(paramIMMessage.chatType, str));
    return true;
  }

  private void recvLogin()
  {
    this.loginSuccess = true;
  }

  private void recvMsg(int paramInt, JSONArray paramJSONArray, IMMessage paramIMMessage)
  {
    if ((paramJSONArray != null) && (paramIMMessage != null));
    try
    {
      if (paramJSONArray.size() == 1)
      {
        IMMessage.parseData(paramJSONArray.getJSONObject(0), paramIMMessage);
        paramIMMessage.chatType = paramInt;
        if (paramIMMessage != null)
        {
          if (isSetBlack(paramIMMessage))
            return;
          if ((paramIMMessage.mFromAvatar == null) || (paramIMMessage.mFromAvatar.equalsIgnoreCase("")))
            paramIMMessage.mFromAvatar = getUserAvatar(paramIMMessage.mFromID);
          if ((paramIMMessage.mFromGroupId != null) && (!paramIMMessage.mFromGroupId.equalsIgnoreCase("")) && (paramIMMessage.mGroupName == null) && (paramIMMessage.isGroupMsg()))
          {
            paramJSONArray = getGroupInfo(paramIMMessage.mFromGroupId);
            if (paramJSONArray != null)
              paramIMMessage.mGroupName = paramJSONArray.groupName;
          }
          long l = InfoManager.getInstance().getMsgSeq() + 1L;
          paramIMMessage.msgSeq = l;
          InfoManager.getInstance().setMsgSeq(l);
          this.mRecvMsgCnt += 1;
          if (!paramIMMessage.isGroupMsg())
          {
            if (InfoManager.getInstance().getUserProfile() != null)
              paramIMMessage.mToUserId = InfoManager.getInstance().getUserProfile().getUserKey();
            IMContacts.getInstance().addRecentContacts(paramIMMessage.buildUserInfo());
            paramJSONArray = (Integer)this.mapUnReadMsgCnt.get(paramIMMessage.mFromID);
            if (paramJSONArray == null)
              this.mapUnReadMsgCnt.put(paramIMMessage.mFromID, Integer.valueOf(1));
            while (true)
            {
              this.mTotalUnReadMsgCnt += 1;
              addMsgToDS(paramIMMessage);
              this.mDispatchHandler.sendMessage(this.mDispatchHandler.obtainMessage(0, paramIMMessage));
              if (!needSendMsgNotification(paramIMMessage))
                break;
              sendNotification(paramIMMessage);
              return;
              paramInt = paramJSONArray.intValue();
              this.mapUnReadMsgCnt.put(paramIMMessage.mFromID, Integer.valueOf(paramInt + 1));
            }
          }
          IMContacts.getInstance().addRecentContacts(paramIMMessage.mFromGroupId);
          paramJSONArray = (Integer)this.mapUnReadMsgCnt.get(paramIMMessage.mFromGroupId);
          if (paramJSONArray == null)
            this.mapUnReadMsgCnt.put(paramIMMessage.mFromGroupId, Integer.valueOf(1));
          while (true)
          {
            this.mTotalUnReadMsgCnt += 1;
            break;
            paramInt = paramJSONArray.intValue();
            this.mapUnReadMsgCnt.put(paramIMMessage.mFromGroupId, Integer.valueOf(paramInt + 1));
          }
        }
      }
      return;
    }
    catch (Exception paramJSONArray)
    {
    }
  }

  private void saveEnableGroup()
  {
    String str = null;
    int i = 0;
    if (i < this.mLstEnableGroups.size())
    {
      if (str != null);
      for (str = str + "_"; ; str = "")
      {
        str = str + (String)this.mLstEnableGroups.get(i);
        i += 1;
        break;
      }
    }
    if (str != null)
      GlobalCfg.getInstance(this.mContext).setEnableGroups(str);
  }

  private void sendNotification(IMMessage paramIMMessage)
  {
  }

  public void addBarrageInfo(int paramInt1, int paramInt2)
  {
    this.mAddProgramId = paramInt1;
    this.mAddTime = paramInt2;
  }

  public void addBaseUserInfo(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    BaseUserInfoPool.putBaseInfo(paramString1, paramString2, paramString3);
  }

  public void addGroup(String paramString)
  {
    if (paramString == null)
      return;
    int i = 0;
    while (true)
    {
      if (i >= this.mLstTempWatchGroups.size())
        break label36;
      if (hasInTempWatchGroups(paramString) != -1)
        break;
      i += 1;
    }
    label36: this.mLstTempWatchGroups.add(paramString);
  }

  public boolean allowRecvMsg()
  {
    long l = System.currentTimeMillis();
    if ((l - mLastRecvMsgTime) / 1000L > MAX_RecvMsg_INTERVAL)
    {
      mLastRecvMsgTime = l;
      return true;
    }
    return false;
  }

  public void asyncConnect()
  {
    this.connectHandler.removeCallbacks(this.doConnect);
    long l = RangeRandom.Random(this.CONNECT_INTERVAL);
    this.connectHandler.postDelayed(this.doConnect, l * 1000L);
  }

  public void clearNotificationMsg()
  {
    this.mLastFromId = null;
    this.mRecvMsgCnt = 0;
    this.mRecvMsgTime = 0L;
  }

  public boolean clearUnreadCnt(String paramString)
  {
    if (paramString == null)
      return false;
    if (this.mapUnReadMsgCnt.get(paramString) == null)
      return false;
    int i = ((Integer)this.mapUnReadMsgCnt.get(paramString)).intValue();
    this.mTotalUnReadMsgCnt -= i;
    this.mapUnReadMsgCnt.put(paramString, Integer.valueOf(0));
    return true;
  }

  public void connect()
  {
    getImServer();
  }

  public void disableGroup(String paramString)
  {
    int i = 0;
    while (true)
    {
      if (i < this.mLstEnableGroups.size())
      {
        if (((String)this.mLstEnableGroups.get(i)).equalsIgnoreCase(paramString))
        {
          this.mLstEnableGroups.remove(i);
          saveEnableGroup();
        }
      }
      else
        return;
      i += 1;
    }
  }

  public void enableGroup(String paramString)
  {
    if (paramString == null);
    while (hasEnableGroups(paramString))
      return;
    this.mLstEnableGroups.add(paramString);
    saveEnableGroup();
  }

  public String getATTaName(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("@")))
    {
      String str = "";
      int i = 0;
      while (true)
      {
        if ((i >= paramString.length()) || (paramString.charAt(i) == ' '))
        {
          if (str.length() <= 1)
            break;
          return str;
        }
        str = str + paramString.charAt(i);
        i += 1;
      }
    }
    return null;
  }

  public int getAddBarrageTime()
  {
    return this.mAddTime;
  }

  public String getCheckinText()
  {
    switch ((int)RangeRandom.Random(5L))
    {
    default:
    case 0:
    case 1:
    case 2:
      do
      {
        do
        {
          return "签个到，大家好呀";
          str = InfoManager.getInstance().getCurrentCity();
        }
        while ((str == null) || (str.equalsIgnoreCase("")));
        str = "我在" + str;
        return str + "签个到,大家好呀";
        return "大家好,有人吗?";
        str = InfoManager.getInstance().getCurrentCity();
      }
      while ((str == null) || (str.equalsIgnoreCase("")));
      String str = "大家好,有" + str;
      return str + "的吗?";
    case 3:
      return "哈喽,大家好";
    case 4:
    }
    return "各位帅哥美女好";
  }

  public GroupInfo getGroupInfo(String paramString)
  {
    if (paramString == null)
      return null;
    return (GroupInfo)this.mapGroupInfo.get(paramString);
  }

  public List<UserInfo> getGroupMembers(String paramString)
  {
    if (TextUtils.equals(paramString, this.mCurrentGroupId))
      return this.mCurrentGroupUsers;
    return null;
  }

  public List<IMMessage> getImageBarrage(int paramInt)
  {
    if (this.mBarraeProgramId == paramInt)
      return this.mLstImageBarrage;
    return null;
  }

  public IMMessage getSendImageBarrage()
  {
    return this.mLstSendImageBarrage;
  }

  public List<IMMessage> getTxtBarrage(int paramInt)
  {
    if (this.mBarraeProgramId == paramInt)
      return this.mLstTextBarrage;
    return null;
  }

  public int getUnreadCnt()
  {
    if (this.mTotalUnReadMsgCnt < 0)
      return 0;
    return this.mTotalUnReadMsgCnt;
  }

  public int getUnreadCnt(String paramString)
  {
    if (paramString == null);
    while (this.mapUnReadMsgCnt.get(paramString) == null)
      return 0;
    return ((Integer)this.mapUnReadMsgCnt.get(paramString)).intValue();
  }

  public String getUserAvatar(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    return BaseUserInfoPool.getAvatar(paramString);
  }

  public UserInfo getUserInfoByName(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      return (UserInfo)this.mapATUser.get(paramString);
    return null;
  }

  public void handleConnect()
  {
    this.connected = true;
    this.connecting = false;
    if (!this.loginSuccess)
    {
      login();
      this.autoLogin = false;
    }
  }

  public boolean hasCheckIn(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      int i = 0;
      while (i < this.lstHasCheckin.size())
      {
        if (((String)this.lstHasCheckin.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public boolean hasEnableGroups(String paramString)
  {
    if ((paramString == null) || (this.mLstEnableGroups == null));
    while (true)
    {
      return false;
      int i = 0;
      while (i < this.mLstEnableGroups.size())
      {
        if (((String)this.mLstEnableGroups.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public boolean hasEnabledGroup(String paramString)
  {
    if ((paramString == null) || (this.mLstEnableGroups == null));
    while (true)
    {
      return false;
      int i = 0;
      while (i < this.mLstEnableGroups.size())
      {
        if (((String)this.mLstEnableGroups.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public void initEnableGroups()
  {
    Object localObject = GlobalCfg.getInstance(this.mContext).getEnableGroups();
    if (localObject != null)
    {
      localObject = ((String)localObject).split("_");
      if (localObject != null)
        break label28;
    }
    while (true)
    {
      return;
      label28: int i = 0;
      while (i < localObject.length)
      {
        this.mLstEnableGroups.add(localObject[i]);
        i += 1;
      }
    }
  }

  public void initGroup()
  {
    List localList = IMContacts.getInstance().getWatchedGroupContacts();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        this.mapGroupInfo.put(((GroupInfo)localList.get(i)).groupId, localList.get(i));
        i += 1;
      }
    }
  }

  public void initService(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void initUnReadMsg()
  {
    Object localObject2 = GlobalCfg.getInstance(this.mContext).getUnReadID();
    Object localObject1 = GlobalCfg.getInstance(this.mContext).getUnReadCnt();
    if ((localObject2 != null) && (localObject1 != null))
    {
      localObject2 = ((String)localObject2).split("_");
      if (localObject2 != null)
        break label43;
    }
    while (true)
    {
      return;
      label43: localObject1 = ((String)localObject1).split("_");
      if ((localObject1 != null) && (localObject1.length == localObject2.length))
      {
        int i = 0;
        while (i < localObject2.length)
        {
          if ((localObject2[i] != null) && (localObject2[i].length() > 0) && (localObject1[i] != null) && (localObject1[i].length() > 0))
          {
            int j = Integer.valueOf(localObject1[i]).intValue();
            this.mTotalUnReadMsgCnt += j;
            this.mapUnReadMsgCnt.put(localObject2[i], Integer.valueOf(j));
          }
          i += 1;
        }
      }
    }
  }

  public boolean isCheckin(int paramInt)
  {
    return paramInt == 1;
  }

  public void leaveGroup(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return;
      i = hasInTempWatchGroups(paramString);
    }
    while (i == -1);
    this.mLstTempWatchGroups.remove(i);
  }

  public void likeBarrage(IMMessage paramIMMessage)
  {
    if (!CloudCenter.getInstance().isLogin(false))
      Toast.makeText(this.mContext, "登录后才能点赞", 1).show();
    while ((paramIMMessage == null) || (paramIMMessage.mMsgId == null))
      return;
    new HashMap().put("id", paramIMMessage.mMsgId);
    DataManager.getInstance().getData("POST_LIKE_BARRAGE", this, null);
  }

  public void loadBarrage(int paramInt, RootNode.IInfoUpdateEventListener paramIInfoUpdateEventListener)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", String.valueOf(paramInt));
    this.mBarraeProgramId = paramInt;
    DataManager.getInstance().getData("GET_PROGRAM_BARRAGE", this, localHashMap);
    if (paramIInfoUpdateEventListener != null)
      InfoManager.getInstance().root().registerInfoUpdateListener(paramIInfoUpdateEventListener, 8);
  }

  public void loadGroupInfo(String paramString, RootNode.IInfoUpdateEventListener paramIInfoUpdateEventListener)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      IMDataLoadWrapper.loadGroupInfo(paramString, this);
    if (paramIInfoUpdateEventListener != null)
      InfoManager.getInstance().root().registerInfoUpdateListener(paramIInfoUpdateEventListener, 6);
  }

  public void loadGroupMembers(String paramString, int paramInt1, int paramInt2)
  {
    this.mCurrentGroupId = paramString;
    IMDataLoadWrapper.loadGroupUserList(paramString, paramInt1, paramInt2, this);
  }

  public void loadLatestGroupMsgFromNet(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString1.equalsIgnoreCase("")) || (paramString2.equalsIgnoreCase("")))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("groupId", paramString1);
    localHashMap.put("msgId", paramString2);
    DataManager.getInstance().getData("get_im_group_latest_msg", this, localHashMap);
  }

  public IMMessage loadLatestMsg(String paramString, int paramInt)
  {
    if (paramString == null)
      return null;
    List localList = (List)this.mapMessage.get(paramString);
    int i;
    if (localList == null)
    {
      if (paramInt == 1)
        localList = IMDatabaseDS.getInstance().getGroupConversation(paramString, this.MSG_CNT_PER_PAGE);
      while (localList != null)
      {
        Collections.reverse(localList);
        i = 0;
        while (true)
          if (i < localList.size())
          {
            if ((((IMMessage)localList.get(i)).mFromAvatar == null) || (((IMMessage)localList.get(i)).mFromAvatar.equalsIgnoreCase("")))
              ((IMMessage)localList.get(i)).mFromAvatar = getUserAvatar(((IMMessage)localList.get(i)).mFromID);
            i += 1;
            continue;
            if (paramInt != 0)
              break label276;
            UserInfo localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
            if (localUserInfo == null)
              break label276;
            localList = IMDatabaseDS.getInstance().getPrivateConversation(localUserInfo.userKey, paramString, this.MSG_CNT_PER_PAGE);
            break;
          }
        this.mapMessage.put(paramString, localList);
      }
    }
    while (true)
    {
      if (localList != null)
      {
        i = localList.size();
        if (i > 0)
        {
          if (paramInt == 1)
            loadLatestGroupMsgFromNet(((IMMessage)localList.get(i - 1)).mFromGroupId, ((IMMessage)localList.get(i - 1)).mMsgId);
          while (true)
          {
            return (IMMessage)localList.get(i - 1);
            if (paramInt != 0);
          }
        }
      }
      return null;
      label276: break;
    }
  }

  public void loadLatestPeerMsgFromNet()
  {
    UserInfo localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
    if (localUserInfo == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("userid", localUserInfo.userKey);
    DataManager.getInstance().getData("get_im_peer_latest_msg", this, localHashMap);
  }

  public List<IMMessage> loadMoreGroupMsgFromDB(String paramString, int paramInt)
  {
    int i = 0;
    if (paramString == null)
      paramString = null;
    do
    {
      return paramString;
      if (InfoManager.getInstance().getUserProfile().getUserInfo() == null)
        return null;
      List localList = (List)this.mapMessage.get(paramString);
      if (localList == null)
      {
        localList = IMDatabaseDS.getInstance().getGroupConversation(paramString, this.MSG_CNT_PER_PAGE);
        if (localList == null)
          break;
        Collections.reverse(localList);
        paramInt = i;
        while (paramInt < localList.size())
        {
          if ((((IMMessage)localList.get(paramInt)).mFromAvatar == null) || (((IMMessage)localList.get(paramInt)).mFromAvatar.equalsIgnoreCase("")))
            ((IMMessage)localList.get(paramInt)).mFromAvatar = getUserAvatar(((IMMessage)localList.get(paramInt)).mFromID);
          paramInt += 1;
        }
        this.mapMessage.put(paramString, localList);
        return localList;
      }
      if (localList.size() <= 0)
        break;
      if (((IMMessage)localList.get(0)).msgSeq == paramInt)
      {
        paramString = IMDatabaseDS.getInstance().getGroupConversationLessThan(paramString, this.MSG_CNT_PER_PAGE, paramInt);
        if ((paramString == null) || (paramString.size() <= 0))
          break;
        Collections.reverse(paramString);
        localList.addAll(0, paramString);
        paramInt = 0;
        while (paramInt < localList.size())
        {
          if ((((IMMessage)localList.get(paramInt)).mFromAvatar == null) || (((IMMessage)localList.get(paramInt)).mFromAvatar.equalsIgnoreCase("")))
            ((IMMessage)localList.get(paramInt)).mFromAvatar = getUserAvatar(((IMMessage)localList.get(paramInt)).mFromID);
          paramInt += 1;
        }
        return paramString;
      }
      paramString = localList;
    }
    while (paramInt == -1);
    return null;
  }

  public List<IMMessage> loadMoreGroupMsgFromNet(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      paramString2 = null;
    List localList;
    do
    {
      return paramString2;
      if (paramString2 != null)
        break label89;
      localList = (List)this.mapMessage.get(paramString1);
      if (localList == null)
        break;
      paramString2 = localList;
    }
    while (localList.size() > 0);
    paramString2 = new HashMap();
    paramString2.put("group", paramString1);
    paramString2.put("fetchsize", String.valueOf(this.IM_HISTORY_SIZE));
    DataManager.getInstance().getData("GET_IM_HISTORY_FROM_SERVER", this, paramString2);
    label89: return null;
  }

  public void loadMoreGroupMsgFromNet(String paramString, int paramInt)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("group", paramString);
    localHashMap.put("fetchsize", String.valueOf(paramInt));
    DataManager.getInstance().getData("GET_IM_HISTORY_FROM_SERVER", this, localHashMap);
  }

  public List<IMMessage> loadMoreUserMsgFromDB(String paramString, int paramInt)
  {
    int i = 0;
    if (paramString == null)
      paramString = null;
    do
    {
      return paramString;
      UserInfo localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
      if (localUserInfo == null)
        return null;
      List localList = (List)this.mapMessage.get(paramString);
      if (localList == null)
      {
        localList = IMDatabaseDS.getInstance().getPrivateConversation(localUserInfo.userKey, paramString, this.MSG_CNT_PER_PAGE);
        if (localList == null)
          break;
        Collections.reverse(localList);
        paramInt = i;
        while (paramInt < localList.size())
        {
          if ((((IMMessage)localList.get(paramInt)).mFromAvatar == null) || (((IMMessage)localList.get(paramInt)).mFromAvatar.equalsIgnoreCase("")))
            ((IMMessage)localList.get(paramInt)).mFromAvatar = getUserAvatar(((IMMessage)localList.get(paramInt)).mFromID);
          paramInt += 1;
        }
        this.mapMessage.put(paramString, localList);
        return localList;
      }
      if (localList.size() <= 0)
        break;
      if (((IMMessage)localList.get(0)).msgSeq == paramInt)
      {
        paramString = IMDatabaseDS.getInstance().getPrivateConversationLessThan(localUserInfo.userKey, paramString, this.MSG_CNT_PER_PAGE, paramInt);
        if ((paramString == null) || (paramString.size() <= 0))
          break;
        Collections.reverse(paramString);
        localList.addAll(0, paramString);
        paramInt = 0;
        while (paramInt < localList.size())
        {
          if ((((IMMessage)localList.get(paramInt)).mFromAvatar == null) || (((IMMessage)localList.get(paramInt)).mFromAvatar.equalsIgnoreCase("")))
            ((IMMessage)localList.get(paramInt)).mFromAvatar = getUserAvatar(((IMMessage)localList.get(paramInt)).mFromID);
          paramInt += 1;
        }
        return paramString;
      }
      paramString = localList;
    }
    while (paramInt == -1);
    return null;
  }

  public void loadMoreUserMsgFromNet(String paramString)
  {
    if (paramString == null);
    UserInfo localUserInfo;
    do
    {
      return;
      localUserInfo = InfoManager.getInstance().getUserProfile().getUserInfo();
    }
    while (localUserInfo == null);
    HashMap localHashMap = new HashMap();
    localHashMap.put("sender", paramString);
    localHashMap.put("receiver", localUserInfo.userKey);
    DataManager.getInstance().getData("get_im_peer_history_from_server", this, localHashMap);
  }

  public void logout()
  {
    this.connected = false;
    this.connecting = false;
    this.loginSuccess = false;
    if (this.mWebSocket != null)
      this.mWebSocket.disconnect();
  }

  public void onNetChanged(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      if ((paramString.equalsIgnoreCase("nonet")) || (paramString.equalsIgnoreCase(this.mLastNetType)))
      {
        this.mLastNetType = paramString;
        return;
      }
      this.mLastNetType = paramString;
    }
    while (this.connected);
    _connect();
  }

  public void onRecvResult(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
    paramObject1 = paramIResultToken.getType();
    int i;
    if (paramResult.getSuccess())
    {
      if (paramObject1.equalsIgnoreCase("get_group_info"))
      {
        paramResult = (GroupInfo)paramResult.getData();
        if (paramResult != null)
        {
          addGroupInfo(paramResult);
          InfoManager.getInstance().root().setInfoUpdate(6);
          IMContacts.getInstance().updateGroupInfo(paramResult.groupId);
        }
      }
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (!paramObject1.equalsIgnoreCase("GET_PROGRAM_BARRAGE"))
                break;
              paramObject1 = (String)((Map)paramObject2).get("id");
              paramResult = (List)paramResult.getData();
            }
            while ((paramResult == null) || (paramObject1 == null) || (Integer.valueOf(paramObject1).intValue() != this.mBarraeProgramId) || (paramResult.size() != 2));
            this.mLstTextBarrage = ((List)paramResult.get(0));
            this.mLstImageBarrage = ((List)paramResult.get(1));
            InfoManager.getInstance().root().setInfoUpdate(8);
            return;
            if (paramObject1.equalsIgnoreCase("POST_PROGRAM_BARRAGE"))
            {
              this.mLstSendImageBarrage = ((IMMessage)paramResult.getData());
              InfoManager.getInstance().root().setInfoUpdate(9);
              Toast.makeText(this.mContext, "发送弹幕成功", 1).show();
              return;
            }
            if (!paramObject1.equalsIgnoreCase("get_group_users"))
              break;
            paramResult = (List)paramResult.getData();
          }
          while (paramResult == null);
          this.mCurrentGroupUsers = paramResult;
          i = 0;
          while (i < paramResult.size())
          {
            paramObject1 = (UserInfo)paramResult.get(i);
            BaseUserInfoPool.putBaseInfo(paramObject1.userKey, paramObject1.snsInfo.sns_avatar, paramObject1.snsInfo.sns_gender);
            i += 1;
          }
          InfoManager.getInstance().root().setInfoUpdate(7);
          return;
          if (!paramObject1.equalsIgnoreCase("GET_IM_SERVER"))
            break;
          paramResult = (String)paramResult.getData();
        }
        while ((paramResult == null) || (paramResult.equalsIgnoreCase("")));
        this.connectUrl = paramResult;
        _connect();
        return;
        if (!paramObject1.equalsIgnoreCase("GET_IM_HISTORY_FROM_SERVER"))
          break;
        paramObject1 = (String)((HashMap)paramObject2).get("group");
        paramResult = (List)paramResult.getData();
      }
      while ((paramObject1 == null) || (paramResult == null) || (paramResult.size() <= 0));
      paramIResultToken = getLatestGroupMessage(paramObject1);
      if (paramIResultToken == null)
        break label1366;
    }
    label1366: for (long l1 = paramIResultToken.publish; ; l1 = 0L)
    {
      boolean bool = IMContacts.getInstance().hasWatchedGroup(paramObject1);
      i = 0;
      long l2;
      while (i < paramResult.size())
      {
        paramIResultToken = (IMMessage)paramResult.get(i);
        if ((paramIResultToken.isGroupMsg()) && (l1 < paramIResultToken.publish))
        {
          if (bool)
            LatestMessages.putMessage(paramIResultToken.mFromGroupId, paramIResultToken);
          l2 = InfoManager.getInstance().getMsgSeq() + 1L;
          paramIResultToken.msgSeq = l2;
          InfoManager.getInstance().setMsgSeq(l2);
          if (paramIResultToken.mGroupName == null)
          {
            paramObject2 = (GroupInfo)this.mapGroupInfo.get(paramIResultToken.mFromGroupId);
            if (paramObject2 != null)
              paramIResultToken.mGroupName = paramObject2.groupName;
          }
        }
        i += 1;
      }
      paramIResultToken = (List)this.mapMessage.get(paramObject1);
      if ((paramIResultToken == null) || (paramIResultToken.size() == 0))
      {
        this.mapMessage.put(paramObject1, paramResult);
        dispatchIMEvent("RECV_LIST_MSG", paramResult);
      }
      while (true)
      {
        IMDatabaseDS.getInstance().appendListGroupMessage(paramResult, false);
        return;
        paramIResultToken.addAll(paramResult);
        dispatchIMEvent("RECV_LIST_MSG", paramResult);
      }
      if (paramObject1.equalsIgnoreCase("get_im_base_user_info"))
      {
        paramObject1 = (String)((HashMap)paramObject2).get("user");
        paramResult = (UserInfo)paramResult.getData();
        if ((paramResult == null) || (paramObject1 == null))
          break;
        BaseUserInfoPool.putBaseInfo(paramObject1, paramResult.snsInfo.sns_avatar, paramResult.snsInfo.sns_gender);
        return;
      }
      int j;
      if (paramObject1.equalsIgnoreCase("get_im_peer_latest_msg"))
      {
        paramObject1 = (String)((HashMap)paramObject2).get("userid");
        paramResult = (List)paramResult.getData();
        if ((paramResult == null) || (paramResult.size() <= 0))
          break;
        i = 0;
        while (i < paramResult.size())
        {
          paramIResultToken = (UnreadMessagesFromNet)paramResult.get(i);
          j = paramIResultToken.getCount();
          paramIResultToken = paramIResultToken.getMessage();
          if ((j > 0) && (paramIResultToken != null))
          {
            paramObject2 = getLatestUserMessage(paramObject1);
            if ((paramObject2 == null) || (paramObject2.publish < paramIResultToken.publish))
            {
              if ((paramIResultToken.mFromAvatar == null) || (paramIResultToken.mFromAvatar.equalsIgnoreCase("")))
                paramIResultToken.mFromAvatar = getUserAvatar(paramIResultToken.mFromID);
              paramIResultToken.mToUserId = paramObject1;
              LatestMessages.putMessage(paramIResultToken.mFromID, paramIResultToken);
              saveUnReadMsg(paramIResultToken.mFromID, j);
              IMContacts.getInstance().addRecentUserContacts(paramIResultToken.mFromID, paramIResultToken.mFromName, paramIResultToken.mFromAvatar);
              loadMoreUserMsgFromNet(paramIResultToken.mFromID);
            }
          }
          i += 1;
        }
        storeUnReadMsgCnt();
        return;
      }
      if (paramObject1.equalsIgnoreCase("get_im_group_latest_msg"))
      {
        paramObject1 = (String)((HashMap)paramObject2).get("groupId");
        paramIResultToken = (UnreadMessagesFromNet)paramResult.getData();
        if (paramIResultToken == null)
          break;
        paramResult = paramIResultToken.getMessage();
        j = paramIResultToken.getCount();
        if ((paramObject1 == null) || (paramResult == null) || (j <= 0))
          break;
        if (paramResult.mGroupName == null)
        {
          paramIResultToken = (GroupInfo)this.mapGroupInfo.get(paramResult.mFromGroupId);
          if (paramIResultToken != null)
            paramResult.mGroupName = paramIResultToken.groupName;
        }
        if ((paramResult.mMessage != null) && (!paramResult.mMessage.equalsIgnoreCase("")));
        for (i = 1; ; i = 0)
        {
          l1 = 0L;
          paramIResultToken = getLatestGroupMessage(paramObject1);
          if (paramIResultToken != null)
            l1 = paramIResultToken.publish;
          if (i != 0)
            LatestMessages.putMessage(paramObject1, paramResult);
          IMContacts.getInstance().addRecentContacts(paramObject1);
          if (l1 >= paramResult.publish)
            break;
          saveUnReadMsg(paramObject1, j);
          storeUnReadMsgCnt();
          loadMoreGroupMsgFromNet(paramObject1, j);
          return;
        }
      }
      if (!paramObject1.equalsIgnoreCase("get_im_peer_history_from_server"))
        break;
      paramObject1 = (String)((HashMap)paramObject2).get("sender");
      paramResult = (List)paramResult.getData();
      if ((paramObject1 == null) || (paramResult == null) || (paramResult.size() <= 0))
        break;
      paramIResultToken = getLatestUserMessage(paramObject1);
      if (paramIResultToken != null);
      for (l1 = paramIResultToken.publish; ; l1 = 0L)
      {
        i = 0;
        while (i < paramResult.size())
        {
          paramIResultToken = (IMMessage)paramResult.get(i);
          if (l1 < paramIResultToken.publish)
          {
            LatestMessages.putMessage(paramIResultToken.mFromID, paramIResultToken);
            if ((paramIResultToken.mFromAvatar == null) || (paramIResultToken.mFromAvatar.equalsIgnoreCase("")))
              paramIResultToken.mFromAvatar = getUserAvatar(paramIResultToken.mFromID);
            l2 = InfoManager.getInstance().getMsgSeq() + 1L;
            paramIResultToken.msgSeq = l2;
            InfoManager.getInstance().setMsgSeq(l2);
          }
          i += 1;
        }
        paramIResultToken = (List)this.mapMessage.get(paramObject1);
        if ((paramIResultToken == null) || (paramIResultToken.size() == 0))
          this.mapMessage.put(paramObject1, paramResult);
        while (true)
        {
          IMDatabaseDS.getInstance().appendListPrivateMessage(paramResult, false);
          return;
          paramIResultToken.addAll(paramResult);
        }
        if (!paramObject1.equalsIgnoreCase("POST_PROGRAM_BARRAGE"))
          break;
        Toast.makeText(this.mContext, "发送弹幕失败", 1).show();
        return;
      }
    }
  }

  public void ping()
  {
    if ((this.mWebSocket != null) && (this.loginSuccess))
      try
      {
        long l = System.currentTimeMillis() / 1000L;
        if (l - this.lastPingTime < this.PING_INTERVAL)
          return;
        this.lastPingTime = l;
        if (this.mPingMsg == null)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("event", "ping");
          localJSONObject.put("from", InfoManager.getInstance().getUserProfile().getUserInfo().userKey);
          this.mPingMsg = localJSONObject.toJSONString();
        }
        if (this.mPingMsg != null)
        {
          this.mWebSocket.send(this.mPingMsg);
          return;
        }
      }
      catch (Exception localException)
      {
      }
  }

  public void registerIMEventListener(IMEventListener paramIMEventListener, String paramString)
  {
    int i;
    if ((paramIMEventListener != null) && (paramString != null))
    {
      if (this.mapIMEventListeners.containsKey(paramString))
      {
        localObject = (List)this.mapIMEventListeners.get(paramString);
        i = 0;
      }
    }
    else
    {
      while (i < ((List)localObject).size())
      {
        if (((List)localObject).get(i) == paramIMEventListener)
          return;
        i += 1;
      }
      ((List)this.mapIMEventListeners.get(paramString)).add(paramIMEventListener);
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(paramIMEventListener);
    this.mapIMEventListeners.put(paramString, localObject);
  }

  public void saveUnReadMsg(String paramString, int paramInt)
  {
    if (paramString == null)
      return;
    Integer localInteger = (Integer)this.mapUnReadMsgCnt.get(paramString);
    if (localInteger != null);
    for (localInteger = Integer.valueOf(localInteger.intValue() + paramInt); ; localInteger = Integer.valueOf(1))
    {
      this.mTotalUnReadMsgCnt += paramInt;
      this.mapUnReadMsgCnt.put(paramString, localInteger);
      return;
    }
  }

  public boolean sendBarrage(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return false;
    if (paramString1 != null);
    try
    {
      if (InfoManager.getInstance().getMaxWordsInLiveRoom() < paramString1.length())
      {
        Toast.makeText(this.mContext, "超出字数范围,字数最长为" + InfoManager.getInstance().getMaxWordsInLiveRoom() + "个", 1).show();
        return false;
      }
      if (SharedCfg.getInstance().hitFilter(paramString1))
      {
        Toast.makeText(this.mContext, "消息中包含敏感词,敬请三思", 1).show();
        return false;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("content", paramString1);
      localHashMap.put("timePoint", String.valueOf(paramInt2));
      paramString1 = InfoManager.getInstance().getUserProfile().getUserInfo();
      if (paramString1 == null)
      {
        localHashMap.put("senderId", InfoManager.getInstance().getDeviceId());
        localHashMap.put("senderName", "");
        localHashMap.put("senderAvatar", "");
        localHashMap.put("senderGender", "f");
      }
      while (true)
      {
        localHashMap.put("image", paramString2);
        localHashMap.put("file", getFileByPath(paramString2));
        localHashMap.put("programId", String.valueOf(paramInt1));
        DataManager.getInstance().getData("POST_PROGRAM_BARRAGE", this, localHashMap);
        break;
        localHashMap.put("senderId", paramString1.userId);
        localHashMap.put("senderName", paramString1.snsInfo.sns_name);
        localHashMap.put("senderAvatar", paramString1.snsInfo.sns_avatar);
        localHashMap.put("senderGender", paramString1.snsInfo.sns_gender);
      }
    }
    catch (Exception paramString1)
    {
    }
    return true;
  }

  public boolean sendBarrage(String paramString1, String paramString2)
  {
    return sendBarrage(this.mAddProgramId, paramString1, paramString2, this.mAddTime);
  }

  public boolean sendFeedbackMessage(String paramString, GroupInfo paramGroupInfo)
  {
    if (paramString != null);
    try
    {
      if (!paramString.equalsIgnoreCase(""))
      {
        if (paramGroupInfo == null)
          return false;
        if (InfoManager.getInstance().getUserProfile().getUserInfo() != null)
        {
          if (InfoManager.getInstance().getUserProfile().getUserInfo().isBlocked())
          {
            Toast.makeText(this.mContext, "该账号被其它用户举报,您可以在新浪微博上@蜻蜓fm 投诉", 1).show();
            return false;
          }
          if (InfoManager.getInstance().getMaxWordsInLiveRoom() < paramString.length())
          {
            Toast.makeText(this.mContext, "超出字数范围,字数最长为" + InfoManager.getInstance().getMaxWordsInLiveRoom() + "个", 1).show();
            return false;
          }
          if (SharedCfg.getInstance().hitFilter(paramString))
          {
            Toast.makeText(this.mContext, "消息中包含敏感词,敬请三思", 1).show();
            return false;
          }
          if (this.mWebSocket != null)
          {
            paramString = IMMessage.buildIMMessage(paramString, InfoManager.getInstance().getUserProfile().getUserInfo(), paramGroupInfo);
            if (paramString != null)
            {
              String str = getMsgId();
              if (str != null)
                this.mJsonMsg.put("id", str);
              this.mJsonMsg.put("event", "group");
              this.mJsonMsg.put("fromGender", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_gender);
              this.mJsonMsg.put("from", InfoManager.getInstance().getUserProfile().getUserInfo().userKey);
              this.mJsonMsg.put("fromName", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name);
              this.mJsonMsg.put("to", paramGroupInfo.groupId);
              paramGroupInfo = new JSONArray();
              paramGroupInfo.add(paramString);
              this.mJsonMsg.put("body", paramGroupInfo);
              paramString = this.mJsonMsg.toJSONString();
              if (paramString != null)
              {
                this.mWebSocket.send(paramString);
                return true;
              }
            }
          }
          else
          {
            _connect();
          }
        }
      }
      return false;
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  public boolean sendGroupMsg(String paramString, GroupInfo paramGroupInfo, int paramInt)
  {
    if (paramString != null);
    try
    {
      if ((!paramString.equalsIgnoreCase("")) && (paramGroupInfo != null))
      {
        if (InfoManager.getInstance().getUserProfile().getUserInfo() == null)
          return false;
        if (InfoManager.getInstance().getUserProfile().getUserInfo().isBlocked())
        {
          Toast.makeText(this.mContext, "该账号被其它用户举报,您可以在新浪微博上@蜻蜓fm 投诉", 1).show();
          return false;
        }
        if (InfoManager.getInstance().getMaxWordsInLiveRoom() < paramString.length())
        {
          Toast.makeText(this.mContext, "超出字数范围,字数最长为" + InfoManager.getInstance().getMaxWordsInLiveRoom() + "个", 1).show();
          return false;
        }
        if (SharedCfg.getInstance().hitFilter(paramString))
        {
          Toast.makeText(this.mContext, "消息中包含敏感词,敬请三思", 1).show();
          return false;
        }
        if ((paramGroupInfo != null) && (paramGroupInfo.groupId != null) && (isCheckin(paramInt)))
        {
          if (hasCheckIn(paramGroupInfo.groupId))
            return false;
          this.lstHasCheckin.add(paramGroupInfo.groupId);
        }
        if (this.mWebSocket != null)
        {
          Object localObject1 = IMMessage.buildIMMessage(paramString, InfoManager.getInstance().getUserProfile().getUserInfo(), paramGroupInfo);
          if (localObject1 != null)
          {
            String str = getMsgId();
            if (str != null)
              this.mJsonMsg.put("id", str);
            this.mJsonMsg.put("event", "group");
            this.mJsonMsg.put("fromGender", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_gender);
            this.mJsonMsg.put("from", InfoManager.getInstance().getUserProfile().getUserInfo().userKey);
            this.mJsonMsg.put("fromName", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name);
            this.mJsonMsg.put("to", paramGroupInfo.groupId);
            Object localObject2 = new JSONArray();
            ((JSONArray)localObject2).add(localObject1);
            this.mJsonMsg.put("body", localObject2);
            localObject2 = this.mJsonMsg.toJSONString();
            if (localObject2 != null)
            {
              this.mWebSocket.send((String)localObject2);
              paramString = new IMMessage();
              paramString.mFromID = InfoManager.getInstance().getUserProfile().getUserInfo().userKey;
              paramString.mFromName = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
              paramString.mFromAvatar = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_avatar;
              paramString.mFromGroupId = paramGroupInfo.groupId;
              paramString.mMsgId = str;
              IMMessage.parseData((JSONObject)localObject1, paramString);
              long l = InfoManager.getInstance().getMsgSeq() + 1L;
              paramString.msgSeq = l;
              InfoManager.getInstance().setMsgSeq(l);
              LatestMessages.putMessage(paramString.mFromGroupId, paramString);
              localObject1 = (List)this.mapMessage.get(paramGroupInfo.groupId);
              if (localObject1 != null)
                ((List)localObject1).add(paramString);
              while (true)
              {
                IMDatabaseDS.getInstance().appendGroupMessage(paramString, false);
                paramString = QTLogger.getInstance().buildIMSendGroupLog(paramString.mFromID, paramString.mFromGroupId);
                if (paramString != null)
                  LogModule.getInstance().send("GroupMsg", paramString);
                QTMSGManage.getInstance().sendStatistcsMessage("groupMsg", "send");
                return true;
                localObject1 = new ArrayList();
                ((List)localObject1).add(paramString);
                this.mapMessage.put(paramGroupInfo.groupId, localObject1);
              }
            }
          }
          paramGroupInfo = InfoManager.getInstance().getWeiboIdByGroupId(paramGroupInfo.groupId);
          if (paramGroupInfo != null)
            WeiboChat.getInstance().comment(paramGroupInfo, paramString);
        }
        else
        {
          _connect();
          Toast.makeText(this.mContext, "未连接上直播间，发送消息失败", 1).show();
        }
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  public boolean sendUserMsg(String paramString, UserInfo paramUserInfo, int paramInt)
  {
    if (paramString != null);
    try
    {
      if ((!paramString.equalsIgnoreCase("")) && (paramUserInfo != null))
      {
        if (isSelf(paramUserInfo.userKey))
          return false;
        if (InfoManager.getInstance().getUserProfile().getUserInfo().isBlocked())
        {
          Toast.makeText(this.mContext, "该账号被其它用户举报,您可以在新浪微博上@蜻蜓fm 投诉", 1).show();
          return false;
        }
        if (InfoManager.getInstance().getMaxWordsInLiveRoom() < paramString.length())
        {
          Toast.makeText(this.mContext, "超出字数范围,字数最长为" + InfoManager.getInstance().getMaxWordsInLiveRoom() + "个", 1).show();
          return false;
        }
        if (SharedCfg.getInstance().hitFilter(paramString))
        {
          Toast.makeText(this.mContext, "消息中包含敏感词,敬请三思", 1).show();
          return false;
        }
        if (this.mWebSocket != null)
        {
          paramString = IMMessage.buildIMMessage(paramString, InfoManager.getInstance().getUserProfile().getUserInfo(), paramUserInfo);
          if (paramString != null)
          {
            String str = getMsgId();
            if (str != null)
              this.mJsonMsg.put("id", str);
            this.mJsonMsg.put("event", "peer");
            this.mJsonMsg.put("fromName", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name);
            this.mJsonMsg.put("fromGender", InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_gender);
            this.mJsonMsg.put("from", InfoManager.getInstance().getUserProfile().getUserInfo().userKey);
            this.mJsonMsg.put("to", paramUserInfo.userKey);
            Object localObject = new JSONArray();
            ((JSONArray)localObject).add(paramString);
            this.mJsonMsg.put("body", localObject);
            localObject = this.mJsonMsg.toJSONString();
            if (localObject != null)
            {
              this.mWebSocket.send((String)localObject);
              localObject = new IMMessage();
              ((IMMessage)localObject).mFromID = InfoManager.getInstance().getUserProfile().getUserInfo().userKey;
              ((IMMessage)localObject).mFromName = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_name;
              ((IMMessage)localObject).mFromAvatar = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_avatar;
              IMMessage.parseData(paramString, (IMMessage)localObject);
              ((IMMessage)localObject).mToUserId = paramUserInfo.userKey;
              ((IMMessage)localObject).mMsgId = str;
              long l = InfoManager.getInstance().getMsgSeq() + 1L;
              ((IMMessage)localObject).msgSeq = l;
              InfoManager.getInstance().setMsgSeq(l);
              paramString = (List)this.mapMessage.get(paramUserInfo.userKey);
              if (paramString != null)
                paramString.add(localObject);
              while (true)
              {
                LatestMessages.putMessage(((IMMessage)localObject).mToUserId, (IMMessage)localObject);
                IMDatabaseDS.getInstance().appendPrivateMessage((IMMessage)localObject, false);
                paramString = QTLogger.getInstance().buildIMSendUserLog(((IMMessage)localObject).mFromID, ((IMMessage)localObject).mToUserId);
                if (paramString != null)
                  LogModule.getInstance().send("UserMsg", paramString);
                QTMSGManage.getInstance().sendStatistcsMessage("userMsg", "send");
                return true;
                paramString = new ArrayList();
                paramString.add(localObject);
                this.mapMessage.put(paramUserInfo.userKey, paramString);
              }
            }
          }
        }
        else
        {
          _connect();
          Toast.makeText(this.mContext, "未连接上直播间，发送消息失败", 1).show();
        }
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  public void setUser(String paramString, UserInfo paramUserInfo)
  {
    if ((paramString != null) && (paramUserInfo != null) && (!paramString.equalsIgnoreCase("")))
      this.mapATUser.put(paramString, paramUserInfo);
  }

  public void storeUnReadMsgCnt()
  {
    if (this.mapUnReadMsgCnt.size() > 0)
    {
      Iterator localIterator = this.mapUnReadMsgCnt.entrySet().iterator();
      Object localObject1 = "";
      Object localObject2 = "";
      int i = 0;
      while (localIterator.hasNext())
      {
        Object localObject3 = (Map.Entry)localIterator.next();
        int j = ((Integer)((Map.Entry)localObject3).getValue()).intValue();
        localObject3 = (String)localObject2 + (String)((Map.Entry)localObject3).getKey();
        String str = (String)localObject1 + String.valueOf(j);
        localObject2 = str;
        localObject1 = localObject3;
        if (i != this.mapUnReadMsgCnt.size() - 1)
        {
          localObject1 = (String)localObject3 + "_";
          localObject2 = str + "_";
        }
        i += 1;
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
      GlobalCfg.getInstance(this.mContext).setUnReadCnt((String)localObject1);
      GlobalCfg.getInstance(this.mContext).setUnReadID((String)localObject2);
    }
  }

  public void unRegisterIMEventListener(String paramString, IMEventListener paramIMEventListener)
  {
    int i;
    if ((paramIMEventListener != null) && (this.mapIMEventListeners.containsKey(paramString)))
    {
      paramString = (List)this.mapIMEventListeners.get(paramString);
      if (paramString != null)
        i = 0;
    }
    while (true)
    {
      if (i < paramString.size())
      {
        if (paramString.get(i) == paramIMEventListener)
          paramString.remove(i);
      }
      else
        return;
      i += 1;
    }
  }

  private class DispatchHandler extends Handler
  {
    public DispatchHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      do
      {
        return;
        IMAgent.this.dispatchIMEvent("RECV_SINGLE_MSG", (IMMessage)paramMessage.obj);
        return;
        IMAgent.this.dispatchIMEvent("RECV_LIST_MSG", (List)paramMessage.obj);
        return;
      }
      while (IMAgent.this.mContext == null);
      Toast.makeText(IMAgent.this.mContext, "遇上了点网络问题，连接直播间失败。", 1).show();
    }
  }

  public static abstract interface IMEventListener
  {
    public abstract boolean onIMEvent(String paramString, IMMessage paramIMMessage);

    public abstract boolean onIMListMsg(String paramString, List<IMMessage> paramList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.IMAgent
 * JD-Core Version:    0.6.2
 */