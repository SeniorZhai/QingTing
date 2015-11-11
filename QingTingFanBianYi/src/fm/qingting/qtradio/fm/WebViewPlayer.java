package fm.qingting.qtradio.fm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import cn.com.mma.mobile.tracking.api.Countly;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miaozhen.mzmonitor.MZMonitor;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.qtradio.ad.platforms.mediav.MediaVAgent;
import fm.qingting.qtradio.ad.platforms.mediav.model.IMediaVListener;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVNativeAd;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVRequest;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.jd.data.JDApi;
import fm.qingting.qtradio.jd.data.JDApi.ErrorCode;
import fm.qingting.qtradio.jd.data.JDApi.OnResponseListener;
import fm.qingting.qtradio.jd.data.Response;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramScheduleList;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.share.ShareUtil;
import fm.qingting.social.SocialEventListener;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ThirdTracker;
import java.util.ArrayList;
import java.util.List;

public class WebViewPlayer
  implements IMediaVListener
{
  private static WebViewPlayer _instance;
  private final int ADV_JD = 0;
  private final int ADV_MEDIAV = 1;
  private final int ADV_OMD = 2;
  private final int EVENT_QT = 1;
  private final int EVENT_THIRD = 0;
  private final int MONITOR_ADMASTER = 1;
  private final int MONITOR_MIAOZHEN = 0;
  private final int MONITOR_MMA = 2;
  private final int REDIRECT_CHANNEL = 0;
  private final int REDIRECT_DOWNLOAD = 5;
  private final int REDIRECT_IM_CHAT = 9;
  private final int REDIRECT_LISTCHANNEL_ATTR = 8;
  private final int REDIRECT_LIVECATEGORY = 3;
  private final int REDIRECT_NOVEL_CATEGORY = 7;
  private final int REDIRECT_PAGE = 1;
  private final int REDIRECT_PODCASTER = 4;
  private final int REDIRECT_RECOMMENDCATEGORY = 6;
  private final int REDIRECT_RESET_FILTER = 11;
  private final int REDIRECT_SPEICAL_TOPIC = 10;
  private final int REDIRECT_VIRTUALCATEGORY = 2;
  private String _attrs = null;
  private String _callBack;
  private String _callBackJs;
  private String _callBackParams;
  private String _catid;
  private String _channelid;
  private int _channeltype;
  private String _cname;
  private String _desc;
  private boolean _enterchat;
  private int _groupId = 0;
  private String _loadImage;
  private String _pageTitle = "";
  private String _pageUrl = null;
  private int _playSource = -1;
  private int _podcasterId = 0;
  private String _programid;
  private int _sectionId = 0;
  private String _shareContent = "";
  private String _shareImage = null;
  private String _shareTitle = "";
  private String _shareUrl = "";
  private String _source;
  private String _thumb;
  private int _topicId = 0;
  private WebView _webview;
  private final Handler adCallbackHandler = new Handler();
  private Runnable adCallbackRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doADCallback();
    }
  };
  private final Handler adHandler = new Handler();
  private Runnable adRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doAD();
    }
  };
  private final Handler addPlaySourceHandler = new Handler();
  private Runnable addPlaySourceRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.addPlaySource();
    }
  };
  private String backUrl = null;
  private final Handler callbackHandler = new Handler();
  private Runnable callbackRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doCallback();
    }
  };
  private final Handler clickHandler = new Handler();
  private Runnable clickRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doClick();
    }
  };
  private boolean disableLongClick = false;
  private final Handler loadImageHandler = new Handler();
  private Runnable loadImageRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doLoadImage();
    }
  };
  private Context mContext;
  private List<WebViewADBean> mLstADBean = new ArrayList();
  private List<WebViewActionBean> mLstClickBean = new ArrayList();
  private List<WebViewEventBean> mLstEventBean = new ArrayList();
  private List<WebViewMonitorBean> mLstMonitorBean = new ArrayList();
  private List<WebViewActionBean> mLstShowBean = new ArrayList();
  private long mMediaVUpdateTime = 0L;
  public boolean mPreventParentTouch = false;
  private final Handler monitorHandler = new Handler();
  private Runnable monitorRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doMonitor();
    }
  };
  private final Handler playHandler = new Handler();
  private Runnable playRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doPlay();
    }
  };
  private final Handler redirectHandler = new Handler();
  private Runnable redirectRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doRedirect();
    }
  };
  private int redirectType = 0;
  private final Handler reportHandler = new Handler();
  private Runnable reportRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doReport();
    }
  };
  private String shareCallback = null;
  SocialEventListener shareCallbackListener = new SocialEventListener()
  {
    public void onCancel(Object paramAnonymousObject)
    {
    }

    public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
    {
      WebViewPlayer.this.invokeCallBack(null);
    }

    public void onException(Object paramAnonymousObject)
    {
    }
  };
  private String shareCallbackParam = null;
  private final Handler shareHandler = new Handler();
  private Runnable shareRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doShare();
    }
  };
  private int shareType = 0;
  private final Handler showHandler = new Handler();
  private Runnable showRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doShow();
    }
  };
  private final Handler stopHandler = new Handler();
  private Runnable stopRunnable = new Runnable()
  {
    public void run()
    {
      WebViewPlayer.this.doStop();
    }
  };
  private WechatReceiver wcReceiver;

  private void addPlaySource()
  {
    if (this._playSource != -1)
      PlayerAgent.getInstance().addPlaySource(this._playSource);
  }

  private void doAD()
  {
    int i = 0;
    if (i < this.mLstADBean.size())
    {
      if (!((WebViewADBean)this.mLstADBean.get(i)).hasRequested)
      {
        if (((WebViewADBean)this.mLstADBean.get(i)).adType != 0)
          break label88;
        ((WebViewADBean)this.mLstADBean.get(i)).hasRequested = true;
        JDApi.request(new JDApi.OnResponseListener()
        {
          public void onResponse(Response paramAnonymousResponse)
          {
            int i;
            if (paramAnonymousResponse.getErrorCode() == JDApi.ErrorCode.SUCCESS)
              i = 0;
            while (true)
            {
              if (i < WebViewPlayer.this.mLstADBean.size())
              {
                if (((WebViewPlayer.WebViewADBean)WebViewPlayer.this.mLstADBean.get(i)).adType == 0)
                  ((WebViewPlayer.WebViewADBean)WebViewPlayer.this.mLstADBean.get(i)).rawData = paramAnonymousResponse.getRawData();
              }
              else
              {
                paramAnonymousResponse = (ArrayList)paramAnonymousResponse.getData();
                ThirdTracker.getInstance().setJDAdv(paramAnonymousResponse, true);
                WebViewPlayer.this.adCallbackHandler.postDelayed(WebViewPlayer.this.adCallbackRunnable, 1L);
                return;
              }
              i += 1;
            }
          }
        });
      }
      while (true)
      {
        i += 1;
        break;
        label88: if (((WebViewADBean)this.mLstADBean.get(i)).adType == 1)
        {
          ((WebViewADBean)this.mLstADBean.get(i)).hasRequested = true;
          loadMediaVAd();
        }
        else if (((WebViewADBean)this.mLstADBean.get(i)).adType == 2)
        {
          InfoManager.getInstance().loadAdvFromThirdParty(((WebViewADBean)this.mLstADBean.get(i)).categoryId);
        }
      }
    }
  }

  private void doADCallback()
  {
    int i = 0;
    if (i < this.mLstADBean.size())
    {
      if ((((WebViewADBean)this.mLstADBean.get(i)).hasRequested) && (((WebViewADBean)this.mLstADBean.get(i)).rawData != null) && (!((WebViewADBean)this.mLstADBean.get(i)).rawData.equalsIgnoreCase("")) && (((WebViewADBean)this.mLstADBean.get(i)).callback != null))
      {
        str = "javascript:" + ((WebViewADBean)this.mLstADBean.get(i)).callback;
        if (((WebViewADBean)this.mLstADBean.get(i)).callbackParams != null)
          break label219;
      }
      label219: for (String str = str + "("; ; str = str + "('" + ((WebViewADBean)this.mLstADBean.get(i)).callbackParams + "'")
      {
        str = str + ")";
        if ((this._webview != null) && (str != null))
          this._webview.loadUrl(str);
        i += 1;
        break;
      }
    }
  }

  private void doCallback()
  {
    if (this.shareCallback == null)
      return;
    this._callBackJs = "javascript:";
    this._callBackJs += this.shareCallback;
    if (this.shareCallbackParam == null);
    for (this._callBackJs += "("; ; this._callBackJs = (this._callBackJs + "('" + this.shareCallbackParam + "'"))
    {
      this._callBackJs += ")";
      if ((this._webview == null) || (this._callBackJs == null))
        break;
      this._webview.loadUrl(this._callBackJs);
      return;
    }
  }

  private void doClick()
  {
    int i = 0;
    while (i < this.mLstClickBean.size())
    {
      if (((WebViewActionBean)this.mLstClickBean.get(i)).adType == 1)
      {
        int j = 0;
        while (j < this.mLstADBean.size())
        {
          if ((((WebViewADBean)this.mLstADBean.get(j)).adType == 1) && (((WebViewActionBean)this.mLstClickBean.get(i)).categoryId == ((WebViewADBean)this.mLstADBean.get(j)).categoryId) && (((WebViewADBean)this.mLstADBean.get(j)).rawObj != null))
          {
            ((MediaVNativeAd)((WebViewADBean)this.mLstADBean.get(j)).rawObj).onClick();
            return;
          }
          j += 1;
        }
      }
      i += 1;
    }
    this.mLstClickBean.clear();
  }

  private void doLoadImage()
  {
    if ((this._loadImage != null) && (!this._loadImage.equalsIgnoreCase("")))
      ImageLoader.getInstance(InfoManager.getInstance().getContext()).getImage(this._loadImage, 200, 200);
  }

  private void doMonitor()
  {
    int i = 0;
    if (i < this.mLstMonitorBean.size())
    {
      if (((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorType == 0)
      {
        String str = ThirdTracker.getInstance().macroReplace(((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorUrl);
        MZMonitor.adTrack(InfoManager.getInstance().getContext(), str);
      }
      while (true)
      {
        i += 1;
        break;
        if (((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorType == 1)
        {
          if (((WebViewMonitorBean)this.mLstMonitorBean.get(i)).actionType == Constants.ADV_IMPRESSION)
            Countly.sharedInstance().onExpose(((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorUrl);
          else if (((WebViewMonitorBean)this.mLstMonitorBean.get(i)).actionType == Constants.ADV_CLICK)
            Countly.sharedInstance().onClick(((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorUrl);
        }
        else if (((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorType == 2)
          ThirdTracker.getInstance().monitorMMA(((WebViewMonitorBean)this.mLstMonitorBean.get(i)).monitorUrl);
      }
    }
    this.mLstMonitorBean.clear();
  }

  private void doPlay()
  {
    if ((this._source != null) && (!this._source.equalsIgnoreCase("")))
    {
      PlayerAgent.getInstance().stop();
      PlayerAgent.getInstance().play(this._source);
    }
    Object localObject;
    do
    {
      do
      {
        do
          return;
        while ((this._channelid == null) || (this._channelid.equalsIgnoreCase("")));
        if (this._enterchat)
          InfoManager.getInstance().addChatRoom(this._channelid);
        if (this._programid == null)
          this._programid = "0";
        localObject = ProgramHelper.getInstance().getProgramSchedule(Integer.valueOf(this._channelid).intValue(), this._channeltype, false);
      }
      while (localObject == null);
      localObject = ((ProgramScheduleList)localObject).getProgramNode(Integer.valueOf(this._programid).intValue());
    }
    while (localObject == null);
    if (this._playSource != -1)
      PlayerAgent.getInstance().addPlaySource(this._playSource);
    PlayerAgent.getInstance().play((Node)localObject);
  }

  private void doRedirect()
  {
    try
    {
      if (this._playSource != 0)
        PlayerAgent.getInstance().addPlaySource(this._playSource);
      switch (this.redirectType)
      {
      case 0:
        if ((this._channelid != null) && (!this._channelid.equalsIgnoreCase("")))
        {
          ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(Integer.valueOf(this._channelid).intValue(), this._channeltype);
          ChannelNode localChannelNode1 = localChannelNode2;
          if (localChannelNode2 == null)
          {
            localChannelNode1 = localChannelNode2;
            if (this._catid != null)
            {
              localChannelNode1 = localChannelNode2;
              if (!this._catid.equalsIgnoreCase(""))
                localChannelNode1 = ChannelHelper.getInstance().getFakeVirtualChannel(Integer.valueOf(this._channelid).intValue(), Integer.valueOf(this._catid).intValue(), "专辑");
            }
          }
          if (localChannelNode1 != null)
            ControllerManager.getInstance().openChannelDetailControllerWithoutDamaku(localChannelNode1);
          if ((this._programid != null) && (!this._programid.equalsIgnoreCase("")) && (this._catid != null) && (!this._catid.equalsIgnoreCase("")) && (this._channelid != null) && (!this._channelid.equalsIgnoreCase("")))
          {
            PlayerAgent.getInstance().playAndLoadData(Integer.valueOf(this._catid).intValue(), Integer.valueOf(this._channelid).intValue(), Integer.valueOf(this._programid).intValue(), this._channeltype, "专辑");
            return;
          }
        }
        break;
      case 1:
        doRedirectPage();
        return;
      case 2:
        doRedirectVirtualCateogry();
        return;
      case 3:
        doRedirectLiveCategory();
        return;
      case 4:
        doRedirectPodcaster();
        return;
      case 5:
        doRedirectDownload();
        return;
      case 6:
        doRedirectRecommendCategory();
        return;
      case 7:
        doRedirectNovelAllContentController();
        return;
      case 8:
        doRedirectChannelListByAttr();
        return;
      case 9:
        doRedirectIMChat();
        return;
      case 10:
        doRedirectSpecialTopic();
        return;
      case 11:
        doRedirectResetFilter();
        return;
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void doRedirectChannelListByAttr()
  {
    if ((this._catid != null) && (!this._catid.equalsIgnoreCase("")))
      ControllerManager.getInstance().openVirtualCategoryAllContentController(Integer.valueOf(this._catid).intValue(), this._sectionId);
  }

  private void doRedirectDownload()
  {
    if ((this._channelid != null) && (!this._channelid.equalsIgnoreCase("")))
    {
      ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(Integer.valueOf(this._channelid).intValue(), this._channeltype);
      if (localChannelNode != null)
        ControllerManager.getInstance().redirectToBatchDownloadView(localChannelNode, false, true);
    }
  }

  private void doRedirectIMChat()
  {
    if (this._groupId != 0)
      ControllerManager.getInstance().openImChatController(String.valueOf(this._groupId));
  }

  private void doRedirectLiveCategory()
  {
    if ((this._catid == null) || (this._catid.equalsIgnoreCase("")))
      return;
    ControllerManager.getInstance().openTraditionalChannelsView(Integer.valueOf(this._catid).intValue());
  }

  private void doRedirectNovelAllContentController()
  {
    if ((this._catid != null) && (!this._catid.equalsIgnoreCase("")))
      ControllerManager.getInstance().openNovelAllContentController(Integer.valueOf(this._catid).intValue());
  }

  private void doRedirectPage()
  {
    if ((this._pageUrl != null) && (!this._pageUrl.equalsIgnoreCase("")))
      ControllerManager.getInstance().redirectToActiviyByUrl(this._pageUrl, this._pageTitle, true);
  }

  private void doRedirectPodcaster()
  {
    if (this._podcasterId != 0)
    {
      UserInfo localUserInfo = PodcasterHelper.getInstance().getPodcaster(this._podcasterId);
      if (localUserInfo != null)
        ControllerManager.getInstance().openPodcasterInfoController(localUserInfo);
    }
  }

  private void doRedirectRecommendCategory()
  {
    ControllerManager.getInstance().openDiscoverCategoryController(this._sectionId);
  }

  private void doRedirectResetFilter()
  {
    ControllerManager.getInstance().openVirtualCategoryAllContentController(this._sectionId, this._attrs);
  }

  private void doRedirectSpecialTopic()
  {
    ControllerManager.getInstance().openSpecialTopicController(this._topicId);
  }

  private void doRedirectVirtualCateogry()
  {
    ControllerManager.getInstance().openVirtualCategoryAllContentController(this._sectionId);
  }

  private void doReport()
  {
    int i = 0;
    if (i < this.mLstEventBean.size())
    {
      if (((WebViewEventBean)this.mLstEventBean.get(i)).eventType == 0)
        if ((((WebViewEventBean)this.mLstEventBean.get(i)).eventValue != null) && (!((WebViewEventBean)this.mLstEventBean.get(i)).eventValue.equalsIgnoreCase("")))
          QTMSGManage.getInstance().sendStatistcsMessage(((WebViewEventBean)this.mLstEventBean.get(i)).eventName, ((WebViewEventBean)this.mLstEventBean.get(i)).eventValue);
      while (true)
      {
        i += 1;
        break;
        QTMSGManage.getInstance().sendStatistcsMessage(((WebViewEventBean)this.mLstEventBean.get(i)).eventName);
        continue;
        if ((((WebViewEventBean)this.mLstEventBean.get(i)).eventType == 1) && (((WebViewEventBean)this.mLstEventBean.get(i)).eventValue != null) && (!((WebViewEventBean)this.mLstEventBean.get(i)).eventValue.equalsIgnoreCase("")))
          LogModule.getInstance().send(((WebViewEventBean)this.mLstEventBean.get(i)).eventName, ((WebViewEventBean)this.mLstEventBean.get(i)).eventValue);
      }
    }
    this.mLstEventBean.clear();
  }

  private void doShare()
  {
    if (this._shareUrl != null)
    {
      ShareUtil.shareToPlatform(this._shareUrl, this._shareTitle, this._shareContent, this._shareImage, this.shareType, this.shareCallbackListener);
      return;
    }
    if ((this._channelid != null) && (!this._channelid.equalsIgnoreCase("")))
    {
      ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(Integer.valueOf(this._channelid).intValue(), this._channeltype);
      if (localChannelNode != null)
      {
        EventDispacthManager.getInstance().dispatchAction("shareChoose", localChannelNode);
        return;
      }
      EventDispacthManager.getInstance().dispatchAction("shareChoose", InfoManager.getInstance().root().getCurrentPlayingNode());
      return;
    }
    EventDispacthManager.getInstance().dispatchAction("shareChoose", InfoManager.getInstance().root().getCurrentPlayingNode());
  }

  private void doShow()
  {
    int i = 0;
    while (i < this.mLstShowBean.size())
    {
      if (((WebViewActionBean)this.mLstShowBean.get(i)).adType == 1)
      {
        int j = 0;
        while (j < this.mLstADBean.size())
        {
          if ((((WebViewADBean)this.mLstADBean.get(j)).adType == 1) && (((WebViewActionBean)this.mLstShowBean.get(i)).categoryId == ((WebViewADBean)this.mLstADBean.get(j)).categoryId) && (((WebViewADBean)this.mLstADBean.get(j)).rawObj != null))
          {
            ((MediaVNativeAd)((WebViewADBean)this.mLstADBean.get(j)).rawObj).onShow();
            return;
          }
          j += 1;
        }
      }
      i += 1;
    }
    this.mLstShowBean.clear();
  }

  private void doStop()
  {
    PlayerAgent.getInstance().stop();
  }

  public static WebViewPlayer getInstance()
  {
    try
    {
      if (_instance == null)
        _instance = new WebViewPlayer();
      WebViewPlayer localWebViewPlayer = _instance;
      return localWebViewPlayer;
    }
    finally
    {
    }
  }

  private void invokeCallBack(String paramString)
  {
    if (this._callBack == null)
      return;
    this._callBackJs = "javascript:";
    this._callBackJs += this._callBack;
    if (paramString == null);
    for (this._callBackJs += "(null"; ; this._callBackJs = (this._callBackJs + "('" + paramString + "'"))
    {
      this._callBackJs += ")";
      if ((this._webview == null) || (this._callBackJs == null))
        break;
      this._webview.loadUrl(this._callBackJs);
      return;
    }
  }

  private void loadMediaVAd()
  {
    if (System.currentTimeMillis() / 1000L - this.mMediaVUpdateTime < 50L)
      return;
    int i = 0;
    label23: Object localObject;
    Activity localActivity;
    int j;
    if (i < this.mLstADBean.size())
      if (((WebViewADBean)this.mLstADBean.get(i)).adType == 1)
      {
        if (((WebViewADBean)this.mLstADBean.get(i)).categoryId == 0)
          break label191;
        localObject = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(((WebViewADBean)this.mLstADBean.get(i)).categoryId);
        if (localObject != null)
        {
          localActivity = (Activity)InfoManager.getInstance().getContext();
          MediaVAgent.getInstance(localActivity).addListener(this);
          String str = ((CategoryNode)localObject).name;
          j = ((CategoryNode)localObject).categoryId;
          int k = ((CategoryNode)localObject).categoryId;
          localObject = new MediaVRequest(null, new String[] { str }, j, String.valueOf(k));
          MediaVAgent.getInstance(localActivity).loadAds((MediaVRequest)localObject);
        }
      }
    while (true)
    {
      i += 1;
      break label23;
      break;
      label191: localActivity = (Activity)InfoManager.getInstance().getContext();
      MediaVAgent.getInstance(localActivity).addListener(this);
      j = ((WebViewADBean)this.mLstADBean.get(i)).categoryId;
      localObject = new MediaVRequest(null, new String[] { "首页" }, 0, String.valueOf(j));
      MediaVAgent.getInstance(localActivity).loadAds((MediaVRequest)localObject);
    }
  }

  private void parseADInfo(String paramString, WebViewADBean paramWebViewADBean)
  {
    if ((paramWebViewADBean == null) || (paramString == null))
      return;
    while (true)
    {
      int i;
      try
      {
        paramString = JSON.parseObject(paramString);
        paramWebViewADBean.adType = paramString.getIntValue("type");
        paramWebViewADBean.categoryId = paramString.getIntValue("category_id");
        i = 0;
        if (i < this.mLstADBean.size())
        {
          if ((((WebViewADBean)this.mLstADBean.get(i)).adType == paramWebViewADBean.adType) && (((WebViewADBean)this.mLstADBean.get(i)).categoryId == paramWebViewADBean.categoryId))
            ((WebViewADBean)this.mLstADBean.get(i)).hasRequested = false;
        }
        else
        {
          this.mLstADBean.add(paramWebViewADBean);
          return;
        }
      }
      catch (Exception paramString)
      {
        return;
      }
      i += 1;
    }
  }

  private void parseActionInfo(String paramString, WebViewActionBean paramWebViewActionBean)
  {
    if ((paramWebViewActionBean == null) || (paramString == null));
    while (true)
    {
      return;
      try
      {
        paramString = JSON.parseObject(paramString);
        paramWebViewActionBean.adType = paramString.getIntValue("type");
        paramWebViewActionBean.actionType = paramString.getIntValue("action");
        paramWebViewActionBean.categoryId = paramString.getIntValue("category_id");
        if (paramWebViewActionBean.actionType == Constants.ADV_CLICK)
        {
          this.mLstClickBean.add(paramWebViewActionBean);
          return;
        }
        if (paramWebViewActionBean.actionType == Constants.ADV_IMPRESSION)
        {
          this.mLstShowBean.add(paramWebViewActionBean);
          return;
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private void parseEventInfo(String paramString, WebViewEventBean paramWebViewEventBean)
  {
    if ((paramWebViewEventBean == null) || (paramString == null));
    while (true)
    {
      return;
      try
      {
        paramString = JSON.parseObject(paramString);
        paramWebViewEventBean.eventType = paramString.getIntValue("type");
        paramWebViewEventBean.eventName = paramString.getString("event_name");
        paramWebViewEventBean.eventValue = paramString.getString("event_value");
        if ((paramWebViewEventBean.eventName != null) && (!paramWebViewEventBean.eventName.equalsIgnoreCase("")))
        {
          this.mLstEventBean.add(paramWebViewEventBean);
          return;
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private void parseMonitorInfo(String paramString, WebViewMonitorBean paramWebViewMonitorBean)
  {
    if ((paramWebViewMonitorBean == null) || (paramString == null));
    while (true)
    {
      return;
      try
      {
        paramString = JSON.parseObject(paramString);
        paramWebViewMonitorBean.monitorType = paramString.getIntValue("type");
        paramWebViewMonitorBean.monitorUrl = paramString.getString("monitor_url");
        if ((paramWebViewMonitorBean.monitorUrl != null) && (!paramWebViewMonitorBean.monitorUrl.equalsIgnoreCase("")))
        {
          this.mLstMonitorBean.add(paramWebViewMonitorBean);
          return;
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private void parseResInfo(String paramString)
  {
    while (true)
    {
      try
      {
        this._shareUrl = null;
        this._catid = null;
        this._channelid = null;
        this._programid = null;
        this._groupId = 0;
        this._sectionId = 0;
        this._podcasterId = 0;
        if (paramString == null)
          return;
        paramString = JSON.parseObject(paramString);
        this._catid = paramString.getString("category_id");
        this._channelid = paramString.getString("channel_id");
        this._channeltype = paramString.getIntValue("channel_type");
        this._programid = paramString.getString("program_id");
        this.redirectType = paramString.getIntValue("redirect");
        this._cname = paramString.getString("cname");
        this._thumb = paramString.getString("thumb");
        this._desc = paramString.getString("desc");
        this._source = paramString.getString("source");
        this._playSource = paramString.getIntValue("play_source");
        this._pageUrl = paramString.getString("page_url");
        this._pageTitle = paramString.getString("page_title");
        this._sectionId = paramString.getIntValue("section_id");
        this._podcasterId = paramString.getIntValue("podcaster_id");
        this._attrs = paramString.getString("attribute_id");
        if (paramString.getIntValue("enter_chat") == 1)
        {
          this._enterchat = true;
          this.shareType = paramString.getIntValue("share");
          this._shareContent = paramString.getString("shareContent");
          this._shareImage = paramString.getString("shareImage");
          this._shareTitle = paramString.getString("shareTitle");
          this._shareUrl = paramString.getString("shareUrl");
          this._groupId = paramString.getIntValue("group_id");
          this._topicId = paramString.getIntValue("topic_id");
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      this._enterchat = false;
    }
  }

  @JavascriptInterface
  public void AddPlaySource(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 != null);
    try
    {
      this._playSource = JSON.parseObject(paramString1).getIntValue("_playSource");
      label18: this.addPlaySourceHandler.postDelayed(this.addPlaySourceRunnable, 1L);
      return;
    }
    catch (Exception paramString1)
    {
      break label18;
    }
  }

  @JavascriptInterface
  public void Click(String paramString1, String paramString2, String paramString3)
  {
    parseActionInfo(paramString1, new WebViewActionBean(null));
    this.clickHandler.postDelayed(this.clickRunnable, 1L);
  }

  @JavascriptInterface
  public void LoadAD(String paramString1, String paramString2, String paramString3)
  {
    WebViewADBean localWebViewADBean = new WebViewADBean(null);
    localWebViewADBean.callback = paramString2;
    localWebViewADBean.callbackParams = paramString3;
    localWebViewADBean.hasRequested = false;
    parseADInfo(paramString1, localWebViewADBean);
    this.adHandler.postDelayed(this.adRunnable, 1L);
  }

  @JavascriptInterface
  public void LoadImage(String paramString1, String paramString2, String paramString3)
  {
    this._loadImage = paramString1;
    this.loadImageHandler.postDelayed(this.loadImageRunnable, 1L);
  }

  @JavascriptInterface
  public void Monitor(String paramString1, String paramString2, String paramString3)
  {
    parseMonitorInfo(paramString1, new WebViewMonitorBean(null));
    this.monitorHandler.postDelayed(this.monitorRunnable, 1L);
  }

  @JavascriptInterface
  public void Play(String paramString1, String paramString2, String paramString3)
  {
    this._callBack = paramString2;
    this._callBackParams = paramString3;
    parseResInfo(paramString1);
    this.playHandler.postDelayed(this.playRunnable, 1L);
  }

  @JavascriptInterface
  public void Redirect(String paramString1, String paramString2, String paramString3)
  {
    this._callBack = paramString2;
    this._callBackParams = paramString3;
    parseResInfo(paramString1);
    this.redirectHandler.postDelayed(this.redirectRunnable, 1L);
  }

  @JavascriptInterface
  public void RegisterCallback(String paramString1, String paramString2)
  {
    this.shareCallback = paramString1;
    this.shareCallbackParam = paramString2;
  }

  @JavascriptInterface
  public void Report(String paramString1, String paramString2, String paramString3)
  {
    parseEventInfo(paramString1, new WebViewEventBean(null));
    this.reportHandler.postDelayed(this.reportRunnable, 1L);
  }

  @JavascriptInterface
  public void Share(String paramString1, String paramString2, String paramString3)
  {
    this._callBack = paramString2;
    this._callBackParams = paramString3;
    parseResInfo(paramString1);
    this.shareHandler.postDelayed(this.shareRunnable, 1L);
  }

  @JavascriptInterface
  public void Show(String paramString1, String paramString2, String paramString3)
  {
    parseActionInfo(paramString1, new WebViewActionBean(null));
    this.showHandler.postDelayed(this.showRunnable, 1L);
  }

  @JavascriptInterface
  public void Stop()
  {
    this.stopHandler.postDelayed(this.stopRunnable, 1L);
  }

  public void callback()
  {
    this.callbackHandler.postDelayed(this.callbackRunnable, 1L);
  }

  public boolean disableLongClick()
  {
    return this.disableLongClick;
  }

  public String getBackPolicy()
  {
    return this.backUrl;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
    this.wcReceiver = new WechatReceiver();
    paramContext = new IntentFilter();
    paramContext.addAction("android.intent.action.WECHAT_RESP");
    this.mContext.registerReceiver(this.wcReceiver, paramContext);
  }

  public void onMediaVNativeAdResponse(MediaVRequest paramMediaVRequest, ArrayList<MediaVNativeAd> paramArrayList)
  {
    int i;
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
      i = 0;
    while (true)
    {
      if (i < this.mLstADBean.size())
      {
        if ((((WebViewADBean)this.mLstADBean.get(i)).adType == 1) && (((WebViewADBean)this.mLstADBean.get(i)).hasRequested) && (paramMediaVRequest.getRequestId().equalsIgnoreCase(String.valueOf(((WebViewADBean)this.mLstADBean.get(i)).categoryId))))
        {
          paramMediaVRequest = (MediaVNativeAd)paramArrayList.get(0);
          ((WebViewADBean)this.mLstADBean.get(i)).rawData = paramMediaVRequest.toJson();
          ((WebViewADBean)this.mLstADBean.get(i)).rawObj = paramMediaVRequest;
        }
      }
      else
      {
        this.adCallbackHandler.postDelayed(this.adCallbackRunnable, 1L);
        return;
      }
      i += 1;
    }
  }

  @JavascriptInterface
  public void preventParentTouchEvent()
  {
    this.mPreventParentTouch = true;
    this._webview.requestDisallowInterceptTouchEvent(true);
  }

  public void release()
  {
    try
    {
      if (this.wcReceiver != null)
      {
        this.mContext.unregisterReceiver(this.wcReceiver);
        this.wcReceiver = null;
      }
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
    }
  }

  @JavascriptInterface
  public void setDisableLongClick(int paramInt)
  {
    if (paramInt == 1)
    {
      this.disableLongClick = true;
      return;
    }
    this.disableLongClick = false;
  }

  @JavascriptInterface
  public void setShareInfo(String paramString)
  {
    parseResInfo(paramString);
  }

  public void setWebview(WebView paramWebView)
  {
    this._webview = paramWebView;
  }

  @JavascriptInterface
  public void setbackPolicy(String paramString)
  {
    this.backUrl = paramString;
  }

  private class WebViewADBean
  {
    public int adType;
    public String callback;
    public String callbackParams;
    public int categoryId;
    public boolean hasRequested;
    public String rawData;
    public Object rawObj;

    private WebViewADBean()
    {
    }
  }

  private class WebViewActionBean
  {
    public int actionType;
    public int adType;
    public int categoryId;

    private WebViewActionBean()
    {
    }
  }

  private class WebViewEventBean
  {
    public String eventName;
    public int eventType;
    public String eventValue;

    private WebViewEventBean()
    {
    }
  }

  private class WebViewMonitorBean
  {
    public int actionType;
    public int monitorType;
    public String monitorUrl;

    private WebViewMonitorBean()
    {
    }
  }

  class WechatReceiver extends BroadcastReceiver
  {
    WechatReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null);
      while (true)
      {
        return;
        try
        {
          if ((paramIntent.getAction().equalsIgnoreCase("android.intent.action.WECHAT_RESP")) && ((WebViewPlayer.this.shareType == 0) || (WebViewPlayer.this.shareType == 1)))
          {
            WebViewPlayer.this.invokeCallBack(null);
            return;
          }
        }
        catch (Exception paramContext)
        {
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fm.WebViewPlayer
 * JD-Core Version:    0.6.2
 */