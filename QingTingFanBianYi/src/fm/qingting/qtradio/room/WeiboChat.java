package fm.qingting.qtradio.room;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareBean;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.share.ProgramLocation;
import fm.qingting.qtradio.share.ShareUtil;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.social.SocialEventListener;
import fm.qingting.social.api.SinaWeiboApi;
import fm.qingting.social.auth.SinaWeiboAuth;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.TimeUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WeiboChat extends Chat
  implements IResultRecvHandler
{
  private static final String ParaCatid = "catid";
  private static final String ParaChannelid = "channelid";
  private static final String ParaDeviceid = "deviceid";
  private static final String ParaFrom = "from";
  private static final String ParaOs = "os";
  private static final String ParaPagetype = "pagetype";
  private static final String ParaPid = "pid";
  private static final String ParaSubcatid = "subcatid";
  private static final String ParaTimestamp = "timestamp";
  private static final String TAG = "WeiboChat";
  private static String TAIL = " (签到自@蜻蜓FM)";
  public static WeiboChat _instance = null;
  private int COMMENTS_COUNT = 10;
  private boolean hasRestoredFromDB = false;
  private boolean loginSuccess = false;
  private List<String> lstFlowerUser = new ArrayList();
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      case 4:
      default:
        return;
      case 1:
        Toast.makeText(InfoManager.getInstance().getContext(), "签到成功", 0).show();
        MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "Checkin", "succ");
        WeiboChat.this.sendCheckinLog();
        return;
      case 2:
        Toast.makeText(InfoManager.getInstance().getContext(), "签到失败", 0).show();
        MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "Checkin", "failed");
        return;
      case 3:
        Toast.makeText(InfoManager.getInstance().getContext(), "献花成功", 0).show();
        return;
      case 5:
      }
      QTMSGManage.getInstance().sendStatistcsMessage("publishComment");
    }
  };
  private String mRoomId = null;
  private String mSharedChannel;
  private String mSharedContentUrl;
  private String mSharedProgram;
  private String mSharedSentence;
  private String mUserId;
  private UserInfo mUserInfo;
  private String mWeiboId;
  private Map<String, String> mapComments = new HashMap();
  private Map<String, Boolean> mapFlowerInfo = new HashMap();
  private Map<String, String> mapRoomId = new HashMap();
  private Map<String, String> mapUserInfo = new HashMap();

  private boolean allowFlower(String paramString)
  {
    if (paramString == null)
      return false;
    int i = 0;
    while (true)
    {
      if (i >= this.lstFlowerUser.size())
        break label48;
      if (((String)this.lstFlowerUser.get(i)).equalsIgnoreCase(paramString))
        break;
      i += 1;
    }
    label48: return true;
  }

  private static String composeTrackUrl(String paramString, int paramInt1, int paramInt2, ProgramLocation paramProgramLocation)
  {
    if (paramProgramLocation == null)
      return paramString;
    String str1 = paramString;
    if (paramInt2 != 6);
    try
    {
      str1 = URLEncoder.encode(paramString, "utf-8");
      paramString = "http://tracker.qingting.fm/share_audio_app?pagetype=" + paramInt1 + "&" + "timestamp" + "=" + DateUtil.getCurrentSeconds() + "&" + "from" + "=" + paramInt2 + "&" + "os" + "=1" + "&" + "deviceid" + "=" + DeviceInfo.getUniqueId(InfoManager.getInstance().getContext());
      paramString = paramString + "&catid=" + paramProgramLocation.catId;
      paramString = paramString + "&channelid=" + paramProgramLocation.channelId;
      paramString = paramString + "&pid=" + paramProgramLocation.pId;
      return paramString + "&url=" + str1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        str2 = paramString;
      }
    }
    catch (NullPointerException paramString)
    {
      while (true)
        String str2 = "undef";
    }
  }

  private void deleteUserInfo()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "weibo");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
  }

  private static String getBroadcastors(ProgramNode paramProgramNode)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramProgramNode.lstBroadcaster != null)
    {
      paramProgramNode = paramProgramNode.lstBroadcaster.iterator();
      int i = 1;
      if (paramProgramNode.hasNext())
      {
        BroadcasterNode localBroadcasterNode = (BroadcasterNode)paramProgramNode.next();
        if ((localBroadcasterNode.weiboName == null) || (localBroadcasterNode.weiboName.trim().length() <= 0) || (localBroadcasterNode.weiboName.equalsIgnoreCase("未知")))
          break label135;
        if (i == 0)
          localStringBuffer.append(",");
        localStringBuffer.append("@" + localBroadcasterNode.weiboName + " ");
        i = 0;
      }
    }
    label135: 
    while (true)
    {
      break;
      return localStringBuffer.toString();
    }
  }

  private String getCheckInText(Node paramNode, boolean paramBoolean)
  {
    if (paramNode != null)
    {
      this.mSharedContentUrl = "http://qingting.fm";
      this.mSharedChannel = "";
      this.mSharedProgram = "";
      this.mSharedSentence = "";
      ProgramLocation localProgramLocation = new ProgramLocation();
      Object localObject;
      if ((paramNode instanceof ChannelNode))
      {
        localObject = (ChannelNode)paramNode;
        localProgramLocation.catId = ((ChannelNode)localObject).categoryId;
        localProgramLocation.channelId = ((ChannelNode)localObject).channelId;
        if (((ChannelNode)localObject).channelType == 0)
          this.mSharedContentUrl = ("http://qingting.fm/channels/" + ((ChannelNode)localObject).channelId);
        this.mSharedChannel = ((ChannelNode)localObject).title;
        if (paramBoolean)
          this.mSharedSentence = (getShareTitle(((ChannelNode)localObject).title, null) + " " + wrapPageUrl(this.mSharedContentUrl, localProgramLocation) + TAIL);
      }
      else if ((paramNode instanceof ProgramNode))
      {
        paramNode = (ProgramNode)paramNode;
        localProgramLocation.catId = paramNode.getCategoryId();
        localProgramLocation.pId = paramNode.id;
        if (!(paramNode.parent instanceof ChannelNode))
          break label494;
        localObject = (ChannelNode)paramNode.parent;
        localProgramLocation.channelId = ((ChannelNode)localObject).channelId;
        if (((ChannelNode)localObject).channelType != 0)
          break label451;
        this.mSharedContentUrl = ("http://qingting.fm/channels/" + ((ChannelNode)localObject).channelId + "/programs/" + paramNode.id + "/date/" + TimeUtil.msToDate6(paramNode.getAbsoluteStartTime() * 1000L));
        label298: this.mSharedChannel = ((ChannelNode)localObject).title;
        label307: this.mSharedProgram = paramNode.title;
        if (!paramBoolean)
          break label730;
        paramNode = getBroadcastors(paramNode);
        if ((paramNode == null) || (paramNode.length() <= 0))
          break label675;
        this.mSharedSentence = (getShareTitle(this.mSharedChannel, this.mSharedProgram) + " 主播:" + paramNode + " " + wrapPageUrl(this.mSharedContentUrl, localProgramLocation) + TAIL);
      }
      while (true)
      {
        return this.mSharedSentence;
        this.mSharedSentence = ("我正在  " + this.mSharedContentUrl + " 收听" + this.mSharedChannel + " (分享自@蜻蜓FM)");
        break;
        label451: this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
        break label298;
        label494: if ((paramNode.parent instanceof RecommendItemNode))
        {
          this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
          break label307;
        }
        if ((paramNode.parent instanceof RadioChannelNode))
        {
          localObject = (RadioChannelNode)paramNode.parent;
          localProgramLocation.channelId = ((RadioChannelNode)localObject).channelId;
          this.mSharedContentUrl = ("http://qingting.fm/channels/" + ((RadioChannelNode)localObject).channelId + " ");
          this.mSharedChannel = ((RadioChannelNode)localObject).channelName;
          break label307;
        }
        if (paramNode.channelType != 1)
          break label307;
        this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
        break label307;
        label675: this.mSharedSentence = (getShareTitle(this.mSharedChannel, this.mSharedProgram) + " " + wrapPageUrl(this.mSharedContentUrl, localProgramLocation) + TAIL);
        continue;
        label730: this.mSharedSentence = ("我正在  " + this.mSharedContentUrl + " 收听" + this.mSharedChannel + "里的" + this.mSharedProgram + " (分享自@蜻蜓FM)");
      }
    }
    return null;
  }

  private Context getContext()
  {
    return InfoManager.getInstance().getContext();
  }

  public static WeiboChat getInstance()
  {
    if (_instance == null)
      _instance = new WeiboChat();
    return _instance;
  }

  private String getPageUrl(ChannelNode paramChannelNode, ProgramNode paramProgramNode, String paramString)
  {
    if ((paramChannelNode == null) || (paramProgramNode == null) || (paramString == null))
      return null;
    int i = ShareUtil.getPlatFormNum(4);
    paramChannelNode = new ProgramLocation();
    paramChannelNode.catId = paramProgramNode.getCategoryId();
    paramChannelNode.pId = paramProgramNode.id;
    return ShareUtil.wrapPageUrl(paramString, i, paramChannelNode, InfoManager.getInstance().getContext(), 1);
  }

  private String getShareTitle(String paramString1, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString1 != null)
    {
      str1 = str2;
      if (!paramString1.equalsIgnoreCase(""))
        str1 = "" + "【" + paramString1 + "】";
    }
    paramString1 = str1;
    if (paramString2 != null)
      paramString1 = str1 + paramString2;
    return paramString1;
  }

  private String getSlogan()
  {
    InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    InfoManager.getInstance().root().getCurrentPlayingNode();
    return "(发自@蜻蜓fm )";
  }

  private void saveUserInfoToDB()
  {
    if (this.mUserInfo == null);
    while (this.mUserInfo.snsInfo.sns_id.equalsIgnoreCase(""))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("site", "weibo");
    DataManager.getInstance().getData("deletedb_user_info", null, localHashMap);
    localHashMap.put("userInfo", this.mUserInfo);
    DataManager.getInstance().getData("insertdb_user_info", null, localHashMap);
    SharedCfg.getInstance().setWeiboGender(this.mUserInfo.snsInfo.sns_gender);
    InfoManager.getInstance().setUserInfo(this.mUserInfo);
  }

  private void sendCheckinLog()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    ShareBean localShareBean = new ShareBean();
    localShareBean.platform = "weibo";
    if ((localObject != null) && (localChannelNode != null))
      if (((Node)localObject).nodeName.equalsIgnoreCase("program"))
      {
        localShareBean.channelType = ((ProgramNode)localObject).channelType;
        localShareBean.programId = ((ProgramNode)localObject).uniqueId;
        localShareBean.categoryId = localChannelNode.categoryId;
        localShareBean.channelId = localChannelNode.channelId;
        if ((this.mUserInfo != null) && (this.mUserInfo.snsInfo != null))
          localShareBean.snsId = this.mUserInfo.snsInfo.sns_id;
        localShareBean.time = (System.currentTimeMillis() / 1000L);
        localObject = QTLogger.getInstance().buildPublishLog(localShareBean);
        if (localObject != null)
          LogModule.getInstance().send("CheckIn", (String)localObject);
      }
    do
    {
      do
        return;
      while (localChannelNode == null);
      localShareBean.channelType = localChannelNode.channelType;
      localShareBean.categoryId = localChannelNode.categoryId;
      localShareBean.channelId = localChannelNode.channelId;
      localShareBean.snsId = this.mUserId;
      localShareBean.time = (System.currentTimeMillis() / 1000L);
      localObject = QTLogger.getInstance().buildPublishLog(localShareBean);
    }
    while (localObject == null);
    LogModule.getInstance().send("CheckIn", (String)localObject);
  }

  private void sendLiveRoomLog(int paramInt)
  {
  }

  private void updateUserInfo(String paramString)
  {
    if (paramString == null);
    while ((paramString == null) || (this.mapUserInfo.containsKey(paramString)) || (!WeiboAgent.getInstance().isSessionValid().booleanValue()))
      return;
    new HashMap().put("uid", paramString);
  }

  private static String wrapPageUrl(String paramString, ProgramLocation paramProgramLocation)
  {
    return composeTrackUrl(paramString, 1, 3, paramProgramLocation);
  }

  public void checkIn(int paramInt, String paramString)
  {
    if (!WeiboAgent.getInstance().isSessionValid().booleanValue());
    do
    {
      return;
      paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    }
    while (paramString == null);
    String str = paramString.getApproximativeThumb();
    SinaWeiboApi.checkIn(getContext(), getCheckInText(paramString, true), null, str, new SocialEventListener()
    {
      public void onCancel(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(WeiboChat.this.mHandler, 3, null);
        WeiboChat.this.mHandler.sendMessage(paramAnonymousObject);
      }

      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = Message.obtain(WeiboChat.this.mHandler, 1, null);
        WeiboChat.this.mHandler.sendMessage(paramAnonymousObject1);
      }

      public void onException(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(WeiboChat.this.mHandler, 2, null);
        WeiboChat.this.mHandler.sendMessage(paramAnonymousObject);
      }
    });
    sendLiveRoomLog(1);
  }

  public void comment(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.equalsIgnoreCase("")));
    while ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (!WeiboAgent.getInstance().isSessionValid().booleanValue()))
      return;
    String str1 = paramString2.replaceAll("@", " ");
    String str2 = getSlogan();
    paramString2 = str1;
    if (str2 != null)
    {
      paramString2 = str1;
      if (!str2.equalsIgnoreCase(""))
        paramString2 = str1 + str2;
    }
    SinaWeiboApi.comment(getContext(), paramString1, paramString2, Boolean.valueOf(true), new SocialEventListener()
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
      }
    });
  }

  public void exit()
  {
  }

  public void flower(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    String str;
    do
    {
      do
      {
        return;
        str = paramUserInfo.snsInfo.sns_name;
      }
      while ((str == null) || (str.equalsIgnoreCase("")) || (!allowFlower(paramUserInfo.snsInfo.sns_id)) || (!WeiboAgent.getInstance().isSessionValid().booleanValue()));
      paramUserInfo = getCheckInText(InfoManager.getInstance().root().getCurrentPlayingNode(), false);
    }
    while ((paramUserInfo == null) || (paramUserInfo.equalsIgnoreCase("")));
    paramUserInfo = "节目好精彩, 我向@" + str + " 献了一朵花[鲜花]! " + paramUserInfo;
    SinaWeiboApi.shareText(getContext(), paramUserInfo, "", "", new SocialEventListener()
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = Message.obtain(WeiboChat.this.mHandler, 3, null);
        WeiboChat.this.mHandler.sendMessage(paramAnonymousObject1);
      }

      public void onException(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(WeiboChat.this.mHandler, 4, null);
        WeiboChat.this.mHandler.sendMessage(paramAnonymousObject);
      }
    });
    sendLiveRoomLog(2);
  }

  public UserInfo getUserInfo()
  {
    if (!WeiboAgent.getInstance().isSessionValid().booleanValue())
      return null;
    if ((this.mUserInfo != null) && (this.mUserInfo.snsInfo.sns_id != null) && (!this.mUserInfo.snsInfo.sns_id.equalsIgnoreCase("")))
      return this.mUserInfo;
    if (this.mUserInfo == null)
      this.mUserInfo = WeiboAgent.getInstance().getUserInfo();
    return this.mUserInfo;
  }

  public void init()
  {
    if (this.hasRestoredFromDB);
    while (!WeiboAgent.getInstance().isSessionValid().booleanValue())
      return;
    this.mUserInfo = WeiboAgent.getInstance().getUserInfo();
    this.hasRestoredFromDB = true;
  }

  public boolean login()
  {
    if ((this.loginSuccess) && (WeiboAgent.getInstance().isSessionValid().booleanValue()))
      return true;
    if ((getInstance().getUserInfo() != null) && (WeiboAgent.getInstance().isSessionValid().booleanValue()))
    {
      QTChat.getInstance().login(getInstance().getUserInfo());
      updatePlayingProgramUserInfo();
      this.loginSuccess = true;
      return true;
    }
    return false;
  }

  public void logout()
  {
    WeiboAgent.getInstance().logout();
    this.loginSuccess = false;
    this.mUserId = null;
    this.mUserInfo = null;
    deleteUserInfo();
    InfoManager.getInstance().setUserInfo(null);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    SinaWeiboAuth.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onLoginSuccess()
  {
    getUserInfo();
    this.loginSuccess = true;
    ControllerManager.getInstance().dipatchEventToCurrentController("weibo_login_success");
    saveUserInfoToDB();
  }

  public void onRecvResult(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
  }

  public void reply(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (!paramString1.equalsIgnoreCase("")) && (paramString2 != null) && (!paramString2.equalsIgnoreCase("")) && (paramString3 != null) && (paramString3.equalsIgnoreCase("")));
  }

  public void send(String paramString)
  {
  }

  public void send(String paramString1, UserInfo paramUserInfo, String paramString2)
  {
  }

  public void setWeiboId(String paramString)
  {
    this.mWeiboId = paramString;
  }

  public void share(Node paramNode, String paramString)
  {
  }

  public void speakTo(UserInfo paramUserInfo, String paramString)
  {
    if ((paramUserInfo == null) || (paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      do
        return;
      while (!WeiboAgent.getInstance().isSessionValid().booleanValue());
      paramString = paramUserInfo.snsInfo.sns_id;
      paramUserInfo = paramUserInfo.snsInfo.sns_name;
    }
    while ((paramUserInfo == null) || (!paramUserInfo.equalsIgnoreCase("")));
  }

  public void updateBroadcasterWeiboName(Node paramNode)
  {
    if (paramNode == null);
  }

  public void updatePlayingProgramUserInfo()
  {
    if (!WeiboAgent.getInstance().isSessionValid().booleanValue());
    while (true)
    {
      return;
      Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("program")))
      {
        localObject = (ProgramNode)localObject;
        if ((((ProgramNode)localObject).lstBroadcaster != null) && (((ProgramNode)localObject).lstBroadcaster.size() != 0))
        {
          int i = 0;
          while (i < ((ProgramNode)localObject).lstBroadcaster.size())
          {
            updateUserInfo(((BroadcasterNode)((ProgramNode)localObject).lstBroadcaster.get(i)).weiboId);
            i += 1;
          }
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.WeiboChat
 * JD-Core Version:    0.6.2
 */