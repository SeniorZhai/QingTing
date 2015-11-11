package fm.qingting.qtradio.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareBean;
import fm.qingting.qtradio.model.ShareObjectNode;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.social.ISocialEventListener;
import fm.qingting.social.SocialEventListener;
import fm.qingting.social.api.QQApi;
import fm.qingting.social.api.QZoneApi;
import fm.qingting.social.api.SinaWeiboApi;
import fm.qingting.social.api.TencentWeiboApi;
import fm.qingting.social.api.WechatApi;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.TimeUtil;
import fm.qingting.utils.ViewCaptureUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

public class ShareUtil
{
  private static final String Default_Desc = "有声世界,无限精彩";
  public static final int PAGETYPE_AUDIO = 2;
  public static final int PAGETYPE_WEBPAGE = 1;
  public static final int PLATFORM_MOMENT = 1;
  public static final int PLATFORM_QQ = 3;
  public static final int PLATFORM_QZONE = 2;
  public static final int PLATFORM_SINA = 4;
  public static final int PLATFORM_TENCENT = 5;
  public static final int PLATFORM_WEIXIN = 0;
  private static final String ParaCatid = "catid";
  private static final String ParaChannelid = "channelid";
  private static final String ParaDeviceid = "deviceid";
  private static final String ParaFrom = "from";
  private static final String ParaOs = "os";
  private static final String ParaPagetype = "pagetype";
  private static final String ParaPid = "pid";
  private static final String ParaTargetType = "targettype";
  private static final String ParaTid = "tid";
  private static final String ParaTimestamp = "timestamp";
  private static final String ShareChannelTemplate = "我觉得%s不错哟";
  private static final String ShareContentCustom = "";
  private static final String SharePodcasterTemplate = "我觉得%s的节目不错哟";
  private static final String ShareProgramTemplate = "我正在收听%s";
  private static final String ShareTopicTemplate = "我觉得%s不错哟";
  private static String TAIL_TENCENT = " (分享自@蜻蜓fm)";
  private static String TAIL_WEIBO;
  public static final int TARGETTYPE_ACTIVITY = 4;
  public static final int TARGETTYPE_CHANNEL = 2;
  public static final int TARGETTYPE_PODCASTER = 5;
  public static final int TARGETTYPE_PROGRAM = 1;
  public static final int TARGETTYPE_TOPIC = 3;
  private static final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage == null);
      Context localContext;
      do
      {
        return;
        localContext = InfoManager.getInstance().getContext();
      }
      while (localContext == null);
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        Toast.makeText(localContext, "分享成功", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "succ_" + ShareUtil.mSharePlatformName);
        ShareUtil.sendShareText(ShareUtil.mSharePlatformName, ShareUtil.mShareNode);
        return;
      case 2:
        Toast.makeText(localContext, "分享失败", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "failed_" + ShareUtil.mSharePlatformName);
        ShareUtil.sendShareText(ShareUtil.mSharePlatformName, ShareUtil.mShareNode);
        return;
      case 3:
        Toast.makeText(localContext, "分享取消", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "cancel_" + ShareUtil.mSharePlatformName);
        return;
      case 4:
        Toast.makeText(localContext, "邀请成功", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "succ_" + ShareUtil.mSharePlatformName);
        return;
      case 5:
        Toast.makeText(localContext, "邀请失败", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "failed_" + ShareUtil.mSharePlatformName);
        return;
      case 6:
        Toast.makeText(localContext, "取消邀请", 0).show();
        MobclickAgent.onEvent(localContext, "ShareResult", "cancel_" + ShareUtil.mSharePlatformName);
        return;
      case 7:
      }
      Toast.makeText(localContext, "请先安装或更新微信", 0).show();
    }
  };
  private static Node mShareNode;
  private static String mSharePlatformName;

  static
  {
    TAIL_WEIBO = " (分享自@蜻蜓FM)";
  }

  private static String composeTrackUrl(String paramString, int paramInt1, int paramInt2, int paramInt3, ProgramLocation paramProgramLocation, Context paramContext)
  {
    String str = paramString;
    if (paramString.startsWith("http"))
      str = paramString.substring(paramString.indexOf("/", 8));
    StringBuilder localStringBuilder = new StringBuilder().append("http://share.qingting.fm").append(str);
    if (str.indexOf("?") == -1);
    for (paramString = "?"; ; paramString = "&")
    {
      str = paramString + "pagetype" + "=" + paramInt1 + "&" + "targettype" + "=" + paramInt2 + "&" + "timestamp" + "=" + DateUtil.getCurrentSeconds() + "&" + "from" + "=" + paramInt3 + "&" + "os" + "=1" + "&" + "deviceid" + "=" + DeviceInfo.getUniqueId(paramContext) + "&av=6";
      paramContext = str;
      if (paramProgramLocation != null)
      {
        paramString = str;
        if (paramProgramLocation.catId != 0)
          paramString = str + "&catid=" + paramProgramLocation.catId;
        paramContext = paramString;
        if (paramProgramLocation.channelId != 0)
          paramContext = paramString + "&channelid=" + paramProgramLocation.channelId;
        paramString = paramContext;
        if (paramProgramLocation.pId != 0)
          paramString = paramContext + "&pid=" + paramProgramLocation.pId;
        paramContext = paramString;
        if (paramProgramLocation.tId != 0)
          paramContext = paramString + "&tid=" + paramProgramLocation.tId;
      }
      return paramContext;
    }
  }

  private static String getBroadcastors(ChannelNode paramChannelNode, ProgramNode paramProgramNode)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramChannelNode != null) && (paramChannelNode.lstPodcasters != null))
    {
      paramChannelNode = paramChannelNode.lstPodcasters.iterator();
      while (paramChannelNode.hasNext())
      {
        UserInfo localUserInfo = (UserInfo)paramChannelNode.next();
        if ((localUserInfo.snsInfo != null) && (localUserInfo.snsInfo.sns_account.trim().length() > 0) && (localUserInfo.snsInfo.sns_name.trim().length() > 0))
          localStringBuffer.append(" ,@" + localUserInfo.snsInfo.sns_name.trim());
      }
    }
    if ((paramProgramNode != null) && (paramProgramNode.lstBroadcaster != null))
    {
      paramChannelNode = paramProgramNode.lstBroadcaster.iterator();
      while (paramChannelNode.hasNext())
      {
        paramProgramNode = (BroadcasterNode)paramChannelNode.next();
        if ((paramProgramNode.weiboName != null) && (paramProgramNode.weiboName.trim().length() > 0) && (!paramProgramNode.weiboName.equalsIgnoreCase("未知")))
          localStringBuffer.append(" ,@" + paramProgramNode.weiboName.trim());
      }
    }
    if (localStringBuffer.length() > 0)
      return " 主播：" + localStringBuffer.substring(2);
    return "";
  }

  public static int getPlatFormNum(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0;
    case 0:
      return 1;
    case 1:
      return 2;
    case 4:
      return 3;
    case 5:
      return 4;
    case 3:
      return 5;
    case 2:
    }
    return 6;
  }

  public static String getPreMessage(Node paramNode)
  {
    String str = "";
    if ((paramNode instanceof ChannelNode))
      str = String.format("我正在收听《%s》~", new Object[] { ((ChannelNode)paramNode).title });
    do
    {
      return str;
      if ((paramNode instanceof ProgramNode))
        return String.format("我正在收听《%s》~", new Object[] { ((ProgramNode)paramNode).title });
      if ((paramNode instanceof SpecialTopicNode))
        return String.format("我正在浏览《%s》~", new Object[] { ((SpecialTopicNode)paramNode).title });
      if ((paramNode instanceof UserInfo))
        return String.format("我觉得【%s】的节目不错~", new Object[] { ((UserInfo)paramNode).podcasterName });
    }
    while (!(paramNode instanceof ActivityNode));
    paramNode = (ActivityNode)paramNode;
    return getShareTitle(paramNode.name, paramNode.infoTitle);
  }

  private static Bitmap getShareBitmap(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (!paramString.equalsIgnoreCase(""))
        localObject1 = ImageLoader.getInstance(paramContext).getImage(paramString, 200, 200);
    }
    if (localObject1 != null)
      return localObject1;
    return BitmapFactory.decodeResource(paramContext.getResources(), 2130837804);
  }

  private static ShareInfoBean getShareInfoBean(Node paramNode, int paramInt, Context paramContext, String paramString)
  {
    if (paramNode == null)
      return null;
    int j = getPlatFormNum(paramInt);
    int i = 0;
    ShareInfoBean localShareInfoBean = new ShareInfoBean();
    localShareInfoBean.content = "";
    localShareInfoBean.path = new ProgramLocation();
    Object localObject1;
    if ((paramNode instanceof ChannelNode))
    {
      localObject1 = (ChannelNode)paramNode;
      localShareInfoBean.path.catId = ((ChannelNode)localObject1).categoryId;
      localShareInfoBean.path.channelId = ((ChannelNode)localObject1).channelId;
      localShareInfoBean.parentCover = ((ChannelNode)localObject1).getApproximativeThumb();
      if ((paramString == null) || (paramString.equalsIgnoreCase("")))
        localShareInfoBean.content = String.format("我觉得%s不错哟", new Object[] { getShareTitle(((ChannelNode)localObject1).title, null) });
      localShareInfoBean.content += getBroadcastors((ChannelNode)localObject1, null);
      if (((ChannelNode)localObject1).channelType != 0)
        break label327;
      localShareInfoBean.pageUrl = ("/channels/" + ((ChannelNode)localObject1).channelId);
      localShareInfoBean.playUrl = ("/live/" + ((ChannelNode)localObject1).resId + ".m3u8");
      localShareInfoBean.description = ((ChannelNode)localObject1).title;
      localShareInfoBean.title = ((ChannelNode)localObject1).title;
      i = 2;
    }
    ChannelNode localChannelNode;
    while ((paramNode instanceof ProgramNode))
    {
      localObject1 = (ProgramNode)paramNode;
      localShareInfoBean.path.catId = ((ProgramNode)localObject1).getCategoryId();
      localShareInfoBean.path.channelId = ((ProgramNode)localObject1).channelId;
      localShareInfoBean.path.pId = ((ProgramNode)localObject1).id;
      localChannelNode = ChannelHelper.getInstance().getChannel((ProgramNode)localObject1);
      if (localChannelNode == null)
      {
        return null;
        label327: localShareInfoBean.pageUrl = ("/vchannels/" + ((ChannelNode)localObject1).channelId);
        localShareInfoBean.title = ((ChannelNode)localObject1).title;
        localShareInfoBean.description = ((ChannelNode)localObject1).desc;
        i = 2;
      }
      else
      {
        if ((paramString == null) || (paramString.equalsIgnoreCase("")))
          localShareInfoBean.content = String.format("我正在收听%s", new Object[] { getShareTitle(localChannelNode.title, ((ProgramNode)localObject1).title) });
        localShareInfoBean.content += getBroadcastors(localChannelNode, (ProgramNode)localObject1);
        log(localShareInfoBean.content);
        localShareInfoBean.parentCover = localChannelNode.getApproximativeThumb();
        if ((localShareInfoBean.parentCover == null) && (((ProgramNode)localObject1).isDownloadProgram()))
        {
          localObject2 = ChannelHelper.getInstance().getChannel(((ProgramNode)localObject1).channelId, 1);
          if (localObject2 != null)
            localShareInfoBean.parentCover = ((ChannelNode)localObject2).getApproximativeThumb();
        }
        localShareInfoBean.title = ((ProgramNode)localObject1).title;
        localShareInfoBean.description = localChannelNode.title;
        if (localChannelNode.channelType != 0)
          break label1032;
        localShareInfoBean.pageUrl = ("/channels/" + localChannelNode.channelId + "/from/" + TimeUtil.msToDate6(((ProgramNode)localObject1).getAbsoluteStartTime() * 1000L) + "/to/" + TimeUtil.msToDate6(((ProgramNode)localObject1).getAbsoluteEndTime() * 1000L));
        if ((localChannelNode.channelType != 0) && (!((ProgramNode)localObject1).mLiveInVirtual))
          break label1088;
        Object localObject2 = new StringBuilder().append("/cache/");
        if (((ProgramNode)localObject1).resId <= 0)
          break label1078;
        i = ((ProgramNode)localObject1).resId;
        label666: localShareInfoBean.playUrl = (i + ".m3u8" + "?start=" + TimeUtil.msToDate10(((ProgramNode)localObject1).getAbsoluteStartTime() * 1000L) + "&end=" + TimeUtil.msToDate10(((ProgramNode)localObject1).getAbsoluteEndTime() * 1000L));
        i = 1;
      }
    }
    label732: if ((paramNode instanceof SpecialTopicNode))
    {
      i = 3;
      paramNode = (SpecialTopicNode)paramNode;
      localShareInfoBean.pageUrl = ("/topics/" + paramNode.getApiId());
      localShareInfoBean.title = paramNode.title;
      localShareInfoBean.parentCover = paramNode.thumb;
      if ((paramString == null) || (paramString.equalsIgnoreCase("")))
        localShareInfoBean.content = String.format("我觉得%s不错哟", new Object[] { getShareTitle(paramNode.title, null) });
      localShareInfoBean.description = paramNode.desc;
    }
    while (true)
    {
      localShareInfoBean.pageUrl = composeTrackUrl(localShareInfoBean.pageUrl, 1, i, j, localShareInfoBean.path, paramContext);
      if (localShareInfoBean.playUrl != null)
        localShareInfoBean.playUrl = composeTrackUrl(localShareInfoBean.playUrl, 2, i, j, localShareInfoBean.path, paramContext);
      localShareInfoBean.content = (localShareInfoBean.content + " " + localShareInfoBean.pageUrl + getTail(paramInt));
      if ((paramInt == 4) && (localShareInfoBean.content != null))
      {
        localShareInfoBean.content += " ";
        localShareInfoBean.content += InfoManager.getInstance().getShareTag();
      }
      if (localShareInfoBean.description == null)
        localShareInfoBean.description = "有声世界,无限精彩";
      return localShareInfoBean;
      label1032: localShareInfoBean.pageUrl = ("/vchannels/" + ((ProgramNode)localObject1).channelId + "/programs/" + ((ProgramNode)localObject1).id);
      break;
      label1078: i = localChannelNode.resId;
      break label666;
      label1088: if ((((ProgramNode)localObject1).lstAudioPath != null) && (((ProgramNode)localObject1).lstAudioPath.size() > 0))
      {
        localShareInfoBean.playUrl = ("/" + (String)((ProgramNode)localObject1).lstAudioPath.get(((ProgramNode)localObject1).lstAudioPath.size() - 1));
        i = 1;
        break label732;
      }
      localObject1 = ((ProgramNode)localObject1).getSharedSourcePath();
      if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
      {
        localShareInfoBean.playUrl = ((String)localObject1);
        i = 1;
        break label732;
      }
      return null;
      if ((paramNode instanceof UserInfo))
      {
        i = 5;
        paramNode = (UserInfo)paramNode;
        localShareInfoBean.pageUrl = ("/podcasters/" + paramNode.podcasterId);
        localShareInfoBean.title = ("蜻蜓FM主播: " + paramNode.podcasterName);
        localShareInfoBean.parentCover = paramNode.snsInfo.sns_avatar;
        if ((paramString == null) || (paramString.equalsIgnoreCase("")))
          localShareInfoBean.content = String.format("我觉得%s的节目不错哟", new Object[] { getShareTitle(paramNode.podcasterName, null) });
        if ((paramNode.snsInfo.sns_account.trim().length() > 0) && (paramNode.snsInfo.sns_name.trim().length() > 0))
          localShareInfoBean.content = (localShareInfoBean.content + "@" + paramNode.snsInfo.sns_name);
        localShareInfoBean.description = ("主播" + paramNode.podcasterName + "已经入驻蜻蜓FM了,赶紧来听听TA的节目吧");
      }
      else if ((paramNode instanceof ActivityNode))
      {
        localShareInfoBean.pageUrl = ((ActivityNode)paramNode).contentUrl;
        localShareInfoBean.title = ((ActivityNode)paramNode).name;
        localShareInfoBean.description = ((ActivityNode)paramNode).infoTitle;
        if ((localShareInfoBean.description == null) || (localShareInfoBean.description.equalsIgnoreCase("")))
          localShareInfoBean.description = localShareInfoBean.title;
        localShareInfoBean.content = (" " + localShareInfoBean.pageUrl + getTail(paramInt));
        localShareInfoBean.playUrl = null;
        localShareInfoBean.parentCover = ((ActivityNode)paramNode).infoUrl;
        return localShareInfoBean;
      }
    }
  }

  private static String getShareTitle(String paramString1, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString1 != null)
    {
      str1 = str2;
      if (!paramString1.equalsIgnoreCase(""))
        str1 = "" + "【" + normalizeTitle(paramString1) + "】";
    }
    paramString1 = str1;
    if (paramString2 != null)
      paramString1 = str1 + normalizeTitle(paramString2);
    return paramString1;
  }

  private static String getTail(int paramInt)
  {
    if (paramInt == 4)
      return TAIL_WEIBO;
    return TAIL_TENCENT;
  }

  public static String getTypeString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "分享";
    case 0:
      return "微信好友";
    case 1:
      return "微信朋友圈";
    case 2:
      return "QQ空间";
    case 3:
      return "QQ好友";
    case 4:
      return "新浪微博";
    case 5:
    }
    return "腾讯微博";
  }

  public static void inviteByPlatform(Context paramContext, Node paramNode, int paramInt)
  {
    if (paramNode == null)
      return;
    shareToPlatform(paramContext, paramNode, paramInt, Boolean.valueOf(true));
  }

  public static void inviteByPlatformIm(Context paramContext, int paramInt, String paramString)
  {
    paramString = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (paramString == null)
      return;
    shareToPlatform(paramContext, paramString, paramInt, Boolean.valueOf(true));
  }

  private static void log(String paramString)
  {
  }

  private static String normalizeTitle(String paramString)
  {
    return paramString.trim();
  }

  private static void sendLog(Node paramNode, int paramInt)
  {
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      paramNode = QTLogger.getInstance().buildShareLog((ProgramNode)paramNode, paramInt);
      if (paramNode != null)
        LogModule.getInstance().send(Constants.ShareLogType, paramNode);
    }
    do
    {
      do
      {
        do
          return;
        while ((!paramNode.nodeName.equalsIgnoreCase("channel")) || (!((ChannelNode)paramNode).isLiveChannel()));
        paramNode = ((ChannelNode)paramNode).getProgramNodeByTime(System.currentTimeMillis());
      }
      while ((paramNode == null) || (!paramNode.available));
      paramNode = QTLogger.getInstance().buildShareLog(paramNode, paramInt);
    }
    while (paramNode == null);
    LogModule.getInstance().send(Constants.ShareLogType, paramNode);
  }

  private static void sendShareText(String paramString, Node paramNode)
  {
    if ((paramNode == null) || (paramString == null));
    label278: 
    while (true)
    {
      return;
      ShareBean localShareBean = new ShareBean();
      localShareBean.platform = paramString;
      if (InfoManager.getInstance().getUserProfile().getUserInfo() != null);
      for (paramString = InfoManager.getInstance().getUserProfile().getUserInfo().snsInfo.sns_id; ; paramString = null)
      {
        if (paramNode == null)
          break label278;
        if (paramNode.nodeName.equalsIgnoreCase("program"))
        {
          localShareBean.channelType = ((ProgramNode)paramNode).channelType;
          localShareBean.programId = ((ProgramNode)paramNode).uniqueId;
          if (localShareBean.channelType == 0)
          {
            paramNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
            if (paramNode == null)
              break;
            localShareBean.categoryId = paramNode.categoryId;
          }
          for (localShareBean.channelId = paramNode.channelId; ; localShareBean.channelId = ((ProgramNode)paramNode).channelId)
          {
            localShareBean.snsId = paramString;
            localShareBean.time = (System.currentTimeMillis() / 1000L);
            paramString = QTLogger.getInstance().buildPublishLog(localShareBean);
            if (paramString == null)
              break;
            LogModule.getInstance().send("Share", paramString);
            return;
            localShareBean.categoryId = ((ProgramNode)paramNode).getCategoryId();
          }
        }
        if (!paramNode.nodeName.equalsIgnoreCase("channel"))
          break;
        localShareBean.channelType = ((ChannelNode)paramNode).channelType;
        localShareBean.categoryId = ((ChannelNode)paramNode).categoryId;
        localShareBean.channelId = ((ChannelNode)paramNode).channelId;
        localShareBean.snsId = paramString;
        localShareBean.time = (System.currentTimeMillis() / 1000L);
        paramString = QTLogger.getInstance().buildPublishLog(localShareBean);
        if (paramString == null)
          break;
        LogModule.getInstance().send("Share", paramString);
        return;
      }
    }
  }

  public static void shareToPlatform(Context paramContext, Node paramNode, int paramInt)
  {
    shareToPlatform(paramContext, paramNode, paramInt, Boolean.valueOf(false));
  }

  public static void shareToPlatform(Context paramContext, Node paramNode, int paramInt, Boolean paramBoolean)
  {
    if (paramNode == null)
      return;
    Object localObject2;
    Object localObject1;
    Object localObject4;
    String str;
    Object localObject5;
    SocialEventListener local3;
    while (true)
    {
      try
      {
        if (!(paramNode instanceof ShareObjectNode))
          break;
        localObject3 = (ShareObjectNode)paramNode;
        localObject2 = ((ShareObjectNode)localObject3).message;
        localObject1 = ((ShareObjectNode)localObject3).node;
        paramInt = ((ShareObjectNode)localObject3).type;
        localObject4 = getShareInfoBean((Node)localObject1, paramInt, paramContext, (String)localObject2);
        ((ShareInfoBean)localObject4).content = ((String)localObject2 + ((ShareInfoBean)localObject4).content);
        localObject2 = ((ShareInfoBean)localObject4).pageUrl;
        str = ((ShareInfoBean)localObject4).playUrl;
        localObject5 = ((ShareInfoBean)localObject4).title;
        localObject3 = "http://qingting.fm";
        if ((((ShareInfoBean)localObject4).parentCover != null) && (!((ShareInfoBean)localObject4).parentCover.equalsIgnoreCase("")))
        {
          localObject1 = ((ShareInfoBean)localObject4).parentCover;
          local3 = new SocialEventListener()
          {
            public void onCancel(Object paramAnonymousObject)
            {
              if ((ShareUtil.mSharePlatformName == "weibo") || (ShareUtil.mSharePlatformName == "tencent"))
              {
                paramAnonymousObject = ShareUtil.mHandler;
                if (!this.val$isInvite.booleanValue())
                  break label49;
              }
              label49: for (int i = 6; ; i = 3)
              {
                paramAnonymousObject = Message.obtain(paramAnonymousObject, i, null);
                ShareUtil.mHandler.sendMessage(paramAnonymousObject);
                return;
              }
            }

            public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
            {
              if (ShareUtil.mSharePlatformName == "weibo")
              {
                WeiboAgent.getInstance().onSocialLogin(paramAnonymousObject1);
                paramAnonymousObject1 = ShareUtil.mHandler;
                if (!this.val$isInvite.booleanValue())
                  break label65;
              }
              label65: for (int i = 4; ; i = 1)
              {
                paramAnonymousObject1 = Message.obtain(paramAnonymousObject1, i, null);
                ShareUtil.mHandler.sendMessage(paramAnonymousObject1);
                return;
                if (ShareUtil.mSharePlatformName != "tencent")
                  break;
                TencentAgent.getInstance().onSocialLogin(paramAnonymousObject1);
                break;
              }
            }

            public void onException(Object paramAnonymousObject)
            {
              if (this.val$isInvite.booleanValue());
              for (int i = 5; ; i = 2)
              {
                paramAnonymousObject = Message.obtain(ShareUtil.mHandler, i, null);
                ShareUtil.mHandler.sendMessage(paramAnonymousObject);
                return;
              }
            }
          };
          mShareNode = paramNode;
          if (!mShareNode.nodeName.equalsIgnoreCase("specialtopic"))
            break label792;
          QTMSGManage.getInstance().sendStatistcsMessage("sharespecialtopic", String.valueOf(paramInt));
          break label792;
          mSharePlatformName = "wechat";
          if (str != null)
            break label237;
          WechatApi.shareWebPage(paramContext, (String)localObject2, (String)localObject5, ((ShareInfoBean)localObject4).description, getShareBitmap(paramContext, (String)localObject1), Boolean.valueOf(false), local3);
          sendLog(paramNode, Constants.SHARE_WECHAT);
          return;
        }
      }
      catch (Exception paramContext)
      {
        log(paramContext.toString());
        return;
      }
      localObject1 = "http://s1.qingting.fm/images/qt_logo.jpg";
      continue;
      label237: WechatApi.shareAudio(paramContext, (String)localObject2, str, (String)localObject5, ((ShareInfoBean)localObject4).description, getShareBitmap(paramContext, (String)localObject1), Boolean.valueOf(false), local3);
    }
    mSharePlatformName = "wechatfriend";
    if (str == null)
      WechatApi.shareWebPage(paramContext, (String)localObject2, (String)localObject5, ((ShareInfoBean)localObject4).description, getShareBitmap(paramContext, (String)localObject1), Boolean.valueOf(true), local3);
    while (true)
    {
      sendLog(paramNode, Constants.SHARE_MOMENTS);
      return;
      WechatApi.shareAudio(paramContext, (String)localObject2, str, (String)localObject5, ((ShareInfoBean)localObject4).description, getShareBitmap(paramContext, (String)localObject1), Boolean.valueOf(true), local3);
    }
    mSharePlatformName = "qzone";
    Object localObject3 = ((ShareInfoBean)localObject4).description;
    if (localObject2 == null)
    {
      paramBoolean = "http://qingting.fm";
      label363: QZoneApi.share(paramContext, (String)localObject5, (String)localObject3, paramBoolean, (String)localObject1, "蜻蜓FM", local3);
      sendLog(paramNode, Constants.SHARE_QZONE);
      return;
      mSharePlatformName = "qqfriend";
      localObject4 = ((ShareInfoBean)localObject4).description;
      if (localObject2 != null)
        break label839;
      paramBoolean = (Boolean)localObject3;
      label408: QQApi.share(paramContext, (String)localObject5, (String)localObject4, (String)localObject1, paramBoolean, str, local3);
      sendLog(paramNode, Constants.SHARE_QQ);
      return;
      localObject1 = ((ShareInfoBean)localObject4).parentCover;
      paramBoolean = (Boolean)localObject1;
      if (localObject1 == null)
      {
        localObject2 = ControllerManager.getInstance().getLastViewController();
        paramBoolean = (Boolean)localObject1;
        if (((ViewController)localObject2).controllerName.equalsIgnoreCase("mainplayview"))
        {
          ViewCaptureUtil.setScreenView(((ViewController)localObject2).getView());
          ViewCaptureUtil.captureViewPath();
          paramBoolean = ViewCaptureUtil.getViewPath();
        }
      }
      mSharePlatformName = "weibo";
      if (paramBoolean == null)
        break label845;
      localObject1 = paramBoolean;
      if (paramBoolean == "")
        break label845;
      label508: if (((String)localObject1).startsWith("http"))
        SinaWeiboApi.shareImage(paramContext, ((ShareInfoBean)localObject4).content, (String)localObject1, "", "", local3);
      while (true)
      {
        sendLog(paramNode, Constants.SHARE_WEIBO);
        return;
        SinaWeiboApi.shareLocalImage(paramContext, ((ShareInfoBean)localObject4).content, (String)localObject1, local3);
      }
      mSharePlatformName = "tencent";
      localObject2 = ((ShareInfoBean)localObject4).playUrl;
      paramBoolean = ((ShareInfoBean)localObject4).content;
      if ((localObject2 != null) && (((String)localObject2).length() > 80) && (paramBoolean != null))
        if (paramBoolean == null)
        {
          paramInt = -1;
          localObject3 = paramBoolean.substring(0, paramInt);
          paramBoolean = paramBoolean.substring(paramInt);
          if (paramBoolean.indexOf(" ") <= 0)
            break label853;
          localObject1 = paramBoolean.substring(0, paramBoolean.indexOf(" "));
          paramBoolean = paramBoolean.substring(paramBoolean.indexOf(" "));
        }
    }
    while (true)
    {
      localObject5 = new SocialEventListener()
      {
        public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
        {
          paramAnonymousObject1 = (ModelResult)paramAnonymousObject1;
          if (paramAnonymousObject1 == null);
          do
          {
            do
              return;
            while (!paramAnonymousObject1.isSuccess());
            paramAnonymousObject1 = (JSONObject)JSON.parse(paramAnonymousObject1.getObj().toString());
          }
          while (paramAnonymousObject1 == null);
          paramAnonymousObject1 = paramAnonymousObject1.getJSONObject("data");
          paramAnonymousObject1 = "http://url.cn/" + paramAnonymousObject1.getString("short_url");
          paramAnonymousObject2 = (String)getValue("message");
          String str = (String)getValue("suffix");
          SocialEventListener local1 = new SocialEventListener()
          {
            public void onComplete(Object paramAnonymous2Object1, Object paramAnonymous2Object2)
            {
              paramAnonymous2Object1 = (ModelResult)paramAnonymous2Object1;
              if (paramAnonymous2Object1.isSuccess())
              {
                paramAnonymous2Object1 = ((JSONObject)JSON.parse(paramAnonymous2Object1.getObj().toString())).getJSONObject("data");
                paramAnonymous2Object1 = "http://url.cn/" + paramAnonymous2Object1.getString("short_url");
                paramAnonymous2Object2 = (String)getValue("message");
                String str = (String)getValue("suffix");
                paramAnonymous2Object1 = paramAnonymous2Object2 + paramAnonymous2Object1 + str;
                paramAnonymous2Object2 = (String)getValue("playUrl");
                TencentWeiboApi.shareMusic(ShareUtil.4.this.val$context, paramAnonymous2Object1, paramAnonymous2Object2, (String)getValue("title"), "蜻蜓FM", (SocialEventListener)getValue("listener"));
              }
            }
          };
          local1.setValue("message", paramAnonymousObject2);
          local1.setValue("suffix", str);
          local1.setValue("title", getValue("title"));
          local1.setValue("playUrl", paramAnonymousObject1);
          local1.setValue("listener", getValue("listener"));
          paramAnonymousObject1 = (String)getValue("contentLink");
          try
          {
            paramAnonymousObject2 = URLEncoder.encode(paramAnonymousObject1, "UTF-8");
            paramAnonymousObject1 = paramAnonymousObject2;
            TencentWeiboApi.getShortLink(this.val$context, paramAnonymousObject1, local1);
            return;
          }
          catch (UnsupportedEncodingException paramAnonymousObject2)
          {
            while (true)
              paramAnonymousObject2.printStackTrace();
          }
        }
      };
      ((SocialEventListener)localObject5).setValue("message", localObject3);
      ((SocialEventListener)localObject5).setValue("contentLink", localObject1);
      ((SocialEventListener)localObject5).setValue("suffix", paramBoolean);
      ((SocialEventListener)localObject5).setValue("title", ((ShareInfoBean)localObject4).title);
      ((SocialEventListener)localObject5).setValue("playUrl", localObject2);
      ((SocialEventListener)localObject5).setValue("listener", local3);
      while (true)
      {
        try
        {
          paramBoolean = URLEncoder.encode((String)localObject2, "UTF-8");
          TencentWeiboApi.getShortLink(paramContext, paramBoolean, (ISocialEventListener)localObject5);
          sendLog(paramNode, Constants.SHARE_TENCENT);
          return;
          paramInt = paramBoolean.indexOf("http://share.qingting.fm/");
        }
        catch (UnsupportedEncodingException paramBoolean)
        {
          paramBoolean.printStackTrace();
          paramBoolean = (Boolean)localObject2;
          continue;
        }
        TencentAgent.getInstance().publishTencentWeibo((ShareInfoBean)localObject4);
      }
      localObject2 = "";
      localObject1 = paramNode;
      break;
      label792: switch (paramInt)
      {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      return;
      paramBoolean = (Boolean)localObject2;
      break label363;
      label839: paramBoolean = (Boolean)localObject2;
      break label408;
      label845: localObject1 = "http://s1.qingting.fm/images/qt_logo.jpg";
      break label508;
      label853: localObject1 = paramBoolean;
    }
  }

  public static void shareToPlatform(String paramString, final int paramInt1, int paramInt2)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      if (WeiboAgent.getInstance().isLoggedIn().booleanValue())
      {
        SinaWeiboApi.shareText(InfoManager.getInstance().getContext(), paramString, null, null, new SocialEventListener()
        {
          public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
          {
            paramAnonymousObject1 = Message.obtain(ShareUtil.mHandler, paramInt1, null);
            ShareUtil.mHandler.sendMessage(paramAnonymousObject1);
          }

          public void onException(Object paramAnonymousObject)
          {
            paramAnonymousObject = Message.obtain(ShareUtil.mHandler, this.val$failCode, null);
            ShareUtil.mHandler.sendMessage(paramAnonymousObject);
          }
        });
        return;
      }
    }
    while (!TencentAgent.getInstance().isLoggedIn().booleanValue());
    TencentWeiboApi.shareText(InfoManager.getInstance().getContext(), paramString, new SocialEventListener()
    {
      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        paramAnonymousObject1 = Message.obtain(ShareUtil.mHandler, paramInt1, null);
        ShareUtil.mHandler.sendMessage(paramAnonymousObject1);
      }

      public void onException(Object paramAnonymousObject)
      {
        paramAnonymousObject = Message.obtain(ShareUtil.mHandler, this.val$failCode, null);
        ShareUtil.mHandler.sendMessage(paramAnonymousObject);
      }
    });
  }

  public static void shareToPlatform(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, SocialEventListener paramSocialEventListener)
  {
    Context localContext = InfoManager.getInstance().getContext();
    if (localContext == null)
      return;
    switch (paramInt)
    {
    case 2:
    default:
      return;
    case 0:
      WechatApi.shareWebPage(localContext, paramString1, paramString2, paramString3, getShareBitmap(localContext, paramString4), Boolean.valueOf(false), paramSocialEventListener);
      return;
    case 1:
      WechatApi.shareWebPage(localContext, paramString1, paramString2, paramString3, getShareBitmap(localContext, paramString4), Boolean.valueOf(true), paramSocialEventListener);
      return;
    case 3:
      QQApi.share(localContext, paramString2, paramString3, paramString4, paramString1, paramString1, paramSocialEventListener);
      return;
    case 4:
    }
    if ((paramString4 == null) || (paramString4.equalsIgnoreCase("")));
    for (paramString2 = "http://s1.qingting.fm/images/qt_logo.jpg"; ; paramString2 = paramString4)
    {
      paramString4 = paramString3;
      if (paramString3 == null)
        paramString4 = "";
      paramString3 = paramString4 + " ";
      paramString1 = paramString3 + paramString1;
      paramString1 = paramString1 + " (分享自@蜻蜓fm)";
      if ((paramString2 == null) || (!paramString2.startsWith("http")))
        break;
      SinaWeiboApi.shareImage(localContext, paramString1, paramString2, "", "", paramSocialEventListener);
      return;
    }
  }

  public static String wrapPageUrl(String paramString, int paramInt1, ProgramLocation paramProgramLocation, Context paramContext, int paramInt2)
  {
    return composeTrackUrl(paramString, 1, 1, paramInt1, paramProgramLocation, paramContext);
  }

  public void shareUrlToMoments(String paramString)
  {
    if (paramString == null)
      return;
    WechatApi.shareUrlToMoments(InfoManager.getInstance().getContext(), paramString, "", "", null, new ISocialEventListener()
    {
      public void onCancel(Object paramAnonymousObject)
      {
      }

      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
      }

      public void onException(Object paramAnonymousObject)
      {
      }
    });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.share.ShareUtil
 * JD-Core Version:    0.6.2
 */