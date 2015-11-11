package fm.qingting.qtradio.room;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareBean;
import fm.qingting.qtradio.share.ProgramLocation;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.social.ISocialEventListener;
import fm.qingting.social.SocialEventListener;
import fm.qingting.social.api.TencentWeiboApi;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.TimeUtil;
import fm.qingting.utils.ViewCaptureUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class TencentChat extends Chat
{
  private static String BODY = "节目不错，点个赞/强>>";
  private static String HEAD;
  private static final String ParaCatid = "catid";
  private static final String ParaChannelid = "channelid";
  private static final String ParaDeviceid = "deviceid";
  private static final String ParaFrom = "from";
  private static final String ParaOs = "os";
  private static final String ParaPagetype = "pagetype";
  private static final String ParaPid = "pid";
  private static final String ParaSubcatid = "subcatid";
  private static final String ParaTimestamp = "timestamp";
  private static String TAIL = " (签到自@蜻蜓fm)";
  public static TencentChat _instance = null;
  private boolean loginSuccess = false;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage == null)
        return;
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        Toast.makeText(InfoManager.getInstance().getContext(), "签到成功", 0).show();
        MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "Checkin", "succ");
        TencentChat.this.sendCheckInLog();
        return;
      case 2:
        Toast.makeText(InfoManager.getInstance().getContext(), "签到失败", 0).show();
        MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "Checkin", "failed");
        return;
      case 3:
        Toast.makeText(InfoManager.getInstance().getContext(), "献花成功", 0).show();
        return;
      case 4:
      }
      Toast.makeText(InfoManager.getInstance().getContext(), "献花失败", 0).show();
    }
  };
  private String mRoomId = null;
  private String mSharedChannel;
  private String mSharedContentUrl;
  private String mSharedProgram;
  private String mSharedSentence;
  private Map<String, String> mapUserInfo = new HashMap();

  static
  {
    HEAD = "记录蜻蜓陪伴我走过的每一天，此刻我在听";
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

  private String getCheckInLabel()
  {
    return "\"" + TimeUtil.msToDate9(System.currentTimeMillis()) + "签到\"";
  }

  private String getCheckInText(Node paramNode)
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
        this.mSharedSentence = (getShareTitle(((ChannelNode)localObject).title, null) + " " + wrapPageUrl(this.mSharedContentUrl, localProgramLocation) + TAIL);
      }
      if ((paramNode instanceof ProgramNode))
      {
        paramNode = (ProgramNode)paramNode;
        localProgramLocation.catId = paramNode.getCategoryId();
        localProgramLocation.pId = paramNode.id;
        if (!(paramNode.parent instanceof ChannelNode))
          break label407;
        localObject = (ChannelNode)paramNode.parent;
        localProgramLocation.channelId = ((ChannelNode)localObject).channelId;
        if (((ChannelNode)localObject).channelType != 0)
          break label365;
        this.mSharedContentUrl = ("http://qingting.fm/channels/" + ((ChannelNode)localObject).channelId + "/programs/" + paramNode.id + "/date/" + TimeUtil.msToDate6(paramNode.getAbsoluteStartTime() * 1000L));
        this.mSharedChannel = ((ChannelNode)localObject).title;
      }
      while (true)
      {
        this.mSharedProgram = paramNode.title;
        this.mSharedSentence = (getCheckInLabel() + HEAD + getShareTitle(this.mSharedChannel, this.mSharedProgram) + " " + BODY + wrapPageUrl(this.mSharedContentUrl, localProgramLocation) + TAIL);
        return this.mSharedSentence;
        label365: this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
        break;
        label407: if ((paramNode.parent instanceof RecommendItemNode))
        {
          this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
        }
        else if ((paramNode.parent instanceof RadioChannelNode))
        {
          localObject = (RadioChannelNode)paramNode.parent;
          localProgramLocation.channelId = ((RadioChannelNode)localObject).channelId;
          this.mSharedContentUrl = ("http://qingting.fm/channels/" + ((RadioChannelNode)localObject).channelId + " ");
          this.mSharedChannel = ((RadioChannelNode)localObject).channelName;
        }
        else if (paramNode.channelType == 1)
        {
          localProgramLocation.channelId = paramNode.channelId;
          this.mSharedContentUrl = ("http://qingting.fm/vchannels/" + paramNode.channelId + "/programs/" + paramNode.id);
        }
      }
    }
    return null;
  }

  private String getCheckinImagePath()
  {
    return ViewCaptureUtil.getViewPath();
  }

  private Context getContext()
  {
    return InfoManager.getInstance().getContext();
  }

  public static TencentChat getInstance()
  {
    if (_instance == null)
      _instance = new TencentChat();
    return _instance;
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

  private void sendCheckInLog()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    ShareBean localShareBean = new ShareBean();
    localShareBean.platform = "tencent";
    if ((localObject != null) && (localChannelNode != null))
      if (((Node)localObject).nodeName.equalsIgnoreCase("program"))
      {
        localShareBean.channelType = ((ProgramNode)localObject).channelType;
        localShareBean.programId = ((ProgramNode)localObject).id;
        localShareBean.categoryId = localChannelNode.categoryId;
        localShareBean.channelId = localChannelNode.channelId;
        localShareBean.snsId = TencentAgent.getInstance().getUserSnsId();
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
      localShareBean.snsId = TencentAgent.getInstance().getUserSnsId();
      localShareBean.time = (System.currentTimeMillis() / 1000L);
      localObject = QTLogger.getInstance().buildPublishLog(localShareBean);
    }
    while (localObject == null);
    LogModule.getInstance().send("CheckIn", (String)localObject);
  }

  private void sendLiveRoomLog(int paramInt)
  {
  }

  private static String wrapPageUrl(String paramString, ProgramLocation paramProgramLocation)
  {
    return composeTrackUrl(paramString, 1, 4, paramProgramLocation);
  }

  public void checkIn(int paramInt, String paramString)
  {
    if (!TencentWeiboApi.isSessionValid(getContext()).booleanValue());
    String str;
    do
    {
      do
      {
        return;
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while (paramString == null);
      str = getCheckInText(paramString);
    }
    while ((str == null) || (str.equalsIgnoreCase("")));
    Object localObject = paramString.getApproximativeThumb();
    if (localObject != null)
    {
      paramString = (String)localObject;
      if (!((String)localObject).equalsIgnoreCase(""));
    }
    else
    {
      paramString = getCheckinImagePath();
      if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
      paramString = null;
    }
    localObject = new SocialEventListener()
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = Message.obtain(TencentChat.this.mHandler, 1, null);
        TencentChat.this.mHandler.sendMessage(paramAnonymousObject1);
      }

      public void onException(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(TencentChat.this.mHandler, 2, null);
        TencentChat.this.mHandler.sendMessage(paramAnonymousObject);
      }
    };
    TencentWeiboApi.shareImage(getContext(), str, paramString, (ISocialEventListener)localObject);
    sendLiveRoomLog(1);
  }

  public void comment(String paramString1, String paramString2)
  {
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
        str = paramUserInfo.snsInfo.sns_id;
        paramUserInfo = paramUserInfo.snsInfo.sns_name;
      }
      while ((paramUserInfo == null) || (paramUserInfo.equalsIgnoreCase("")) || (!TencentAgent.getInstance().isSessionValid().booleanValue()));
      str = getCheckInText(InfoManager.getInstance().root().getCurrentPlayingNode());
    }
    while ((str == null) || (str.equalsIgnoreCase("")));
    paramUserInfo = "节目好精彩, 我向" + paramUserInfo + " 献了一朵花[鲜花]! " + str;
    TencentWeiboApi.shareText(getContext(), paramUserInfo, new SocialEventListener()
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = Message.obtain(TencentChat.this.mHandler, 3, null);
        TencentChat.this.mHandler.sendMessage(paramAnonymousObject1);
      }

      public void onException(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(TencentChat.this.mHandler, 4, null);
        TencentChat.this.mHandler.sendMessage(paramAnonymousObject);
      }
    });
    sendLiveRoomLog(2);
  }

  public UserInfo getUserInfo()
  {
    return TencentAgent.getInstance().getUserInfo();
  }

  public void join(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString1 == null));
    while (TencentAgent.getInstance().isSessionValid().booleanValue())
      return;
  }

  public boolean login()
  {
    if ((this.loginSuccess) && (TencentAgent.getInstance().isSessionValid().booleanValue()))
      return true;
    if ((getUserInfo() != null) && (TencentAgent.getInstance().isSessionValid().booleanValue()))
    {
      QTChat.getInstance().login(getInstance().getUserInfo());
      this.loginSuccess = true;
      return true;
    }
    return false;
  }

  public void logout()
  {
    TencentAgent.getInstance().logout();
    this.loginSuccess = false;
    InfoManager.getInstance().setUserInfo(null);
  }

  public void reply(String paramString1, String paramString2, String paramString3)
  {
  }

  public void send(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    TencentAgent.getInstance().addTencentWeiboWithoutPic(paramString);
    sendLiveRoomLog(3);
  }

  public void send(String paramString1, UserInfo paramUserInfo, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (paramString2 == null) || (paramString2.equalsIgnoreCase("")) || (paramUserInfo == null))
      return;
    paramString1 = paramString1 + " //";
    paramString1 = paramString1 + paramUserInfo.snsInfo.sns_name;
    paramString1 = paramString1 + ": ";
    paramString1 = paramString1 + paramString2;
    TencentAgent.getInstance().addTencentWeiboWithoutPic(paramString1);
    sendLiveRoomLog(3);
  }

  public void share(Node paramNode, String paramString)
  {
  }

  public void speakTo(UserInfo paramUserInfo, String paramString)
  {
    if ((paramUserInfo == null) || (paramString == null));
    String str;
    do
    {
      do
        return;
      while (!TencentAgent.getInstance().isSessionValid().booleanValue());
      str = paramUserInfo.snsInfo.sns_id;
      paramUserInfo = paramUserInfo.snsInfo.sns_name;
    }
    while (!this.mapUserInfo.containsKey(str));
    paramUserInfo = String.format("@%s , %s (发自@蜻蜓FM http://qingting.fm/?os=android&from=weibo&version=%s&action=speakTo )", new Object[] { (String)this.mapUserInfo.get(str), paramString, InfoManager.getInstance().getContext().getString(2131492874) });
    TencentAgent.getInstance().addTencentWeiboWithoutPic(paramUserInfo);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.TencentChat
 * JD-Core Version:    0.6.2
 */