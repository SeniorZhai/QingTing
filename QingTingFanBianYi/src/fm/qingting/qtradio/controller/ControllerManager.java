package fm.qingting.qtradio.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.controller.ISwitchAnimation;
import fm.qingting.framework.controller.NavigationController;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.animation.MoveLtoRUncoverAnimation;
import fm.qingting.qtradio.animation.MoveRtoLSwitchAnimation;
import fm.qingting.qtradio.animation.SearchSpecialEnterAnimation;
import fm.qingting.qtradio.animation.SearchSpecialExitAnimation;
import fm.qingting.qtradio.controller.chatRoom.ChatHistoryController;
import fm.qingting.qtradio.controller.chatRoom.ChatRoomcontroller;
import fm.qingting.qtradio.controller.chatRoom.OnlineMembersController;
import fm.qingting.qtradio.controller.groupselect.GroupWebViewController;
import fm.qingting.qtradio.controller.groupselect.WemartController;
import fm.qingting.qtradio.controller.im.ImBlackListController;
import fm.qingting.qtradio.controller.im.ImChatController;
import fm.qingting.qtradio.controller.im.ImContactSpecificController;
import fm.qingting.qtradio.controller.im.ImContactsController;
import fm.qingting.qtradio.controller.im.ImConversationsController;
import fm.qingting.qtradio.controller.im.ImGroupMemberListController;
import fm.qingting.qtradio.controller.im.ImGroupProfileController;
import fm.qingting.qtradio.controller.im.ImGroupSettingController;
import fm.qingting.qtradio.controller.im.ImMyGroupsController;
import fm.qingting.qtradio.controller.im.ImUserProfileController;
import fm.qingting.qtradio.controller.im.ImUserSettingController;
import fm.qingting.qtradio.controller.im.ReportController;
import fm.qingting.qtradio.controller.personalcenter.AboutQtController;
import fm.qingting.qtradio.controller.personalcenter.AlarmDaySettingController;
import fm.qingting.qtradio.controller.personalcenter.AlarmDjRingtoneSettingController;
import fm.qingting.qtradio.controller.personalcenter.AlarmListController;
import fm.qingting.qtradio.controller.personalcenter.AlarmSettingController;
import fm.qingting.qtradio.controller.personalcenter.AudioQualitySettingController;
import fm.qingting.qtradio.controller.personalcenter.ContactInfoController;
import fm.qingting.qtradio.controller.personalcenter.DownloadDirSettingController;
import fm.qingting.qtradio.controller.personalcenter.FaqController;
import fm.qingting.qtradio.controller.personalcenter.FeedbackController;
import fm.qingting.qtradio.controller.personalcenter.HiddenFeaturesController;
import fm.qingting.qtradio.controller.personalcenter.MyDownloadController;
import fm.qingting.qtradio.controller.personalcenter.MyDownloadProgramController;
import fm.qingting.qtradio.controller.personalcenter.MyPodcasterController;
import fm.qingting.qtradio.controller.personalcenter.PlayGameController;
import fm.qingting.qtradio.controller.personalcenter.PlayHistoryController;
import fm.qingting.qtradio.controller.personalcenter.PushMessageSettingController;
import fm.qingting.qtradio.controller.personalcenter.ReserveInfoController;
import fm.qingting.qtradio.controller.personalcenter.RingChannelPickController;
import fm.qingting.qtradio.controller.virtual.ChannelDetailController;
import fm.qingting.qtradio.controller.virtual.MyCollectionController;
import fm.qingting.qtradio.controller.virtual.NovelDetailController;
import fm.qingting.qtradio.controller.virtual.UploadVoiceController;
import fm.qingting.qtradio.doubleclick.DoubleClick;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.LinkManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.AdvertisementItemNode3rdParty;
import fm.qingting.qtradio.model.AlarmInfo;
import fm.qingting.qtradio.model.AlarmInfoNode;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.Attributes;
import fm.qingting.qtradio.model.BillboardItemNode;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.GameBean;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RecommendPlayingItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.qtradio.model.advertisement.QTAdvertisementInfo;
import fm.qingting.qtradio.model.advertisement.QTCoupon;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.qtradio.room.TencentChat;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboChat;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.chatroom.chatlist.ChatItem;
import fm.qingting.qtradio.view.groupselect.GroupWebView;
import fm.qingting.qtradio.view.groupselect.WemartWebView;
import fm.qingting.qtradio.wo.WoFaqViewController;
import fm.qingting.qtradio.wo.WoQtController;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ThirdTracker;
import java.util.Iterator;
import java.util.List;

public class ControllerManager
{
  private static ControllerManager _instance;
  private boolean hasPlayedLastChannel = false;
  private int mChannelSource = 0;
  public Context mContext;
  private NavigationController navigationController = null;

  private ViewController createController(String paramString)
  {
    if (paramString.equalsIgnoreCase("categorylist"))
      return new CategoryListController(this.mContext);
    if (paramString.equalsIgnoreCase("channellist"))
      return new ChannelsListController(this.mContext);
    if (paramString.equalsIgnoreCase("radiochannellist"))
      return new RadioChannelsListController(this.mContext);
    if (paramString.equalsIgnoreCase("mainplayview"))
      return PlayController.getInstance(this.mContext);
    if (paramString.equalsIgnoreCase("danmakumainplayview"))
      return DanmakuPlayController.getInstance(this.mContext);
    if (paramString.equalsIgnoreCase("setting"))
      return new SettingController(this.mContext);
    if (paramString.equalsIgnoreCase("noveldetail"))
      return new NovelDetailController(this.mContext);
    if (paramString.equalsIgnoreCase("channeldetail"))
      return new ChannelDetailController(this.mContext);
    if (paramString.equalsIgnoreCase("batchDownload"))
      return new BatchDownloadController(this.mContext);
    if (paramString.equalsIgnoreCase("batchdownload_tradition"))
      return new BatchDownloadTraditionController(this.mContext);
    if (paramString.equalsIgnoreCase("mycollection"))
      return new MyCollectionController(this.mContext);
    if (paramString.equalsIgnoreCase("playhistory"))
      return new PlayHistoryController(this.mContext);
    if (paramString.equalsIgnoreCase("playgame"))
      return new PlayGameController(this.mContext);
    if (paramString.equalsIgnoreCase("search"))
      return new SearchController(this.mContext);
    if (paramString.equalsIgnoreCase("alarmsetting"))
      return new AlarmSettingController(this.mContext);
    if (paramString.equalsIgnoreCase("downloadprogram"))
      return new MyDownloadProgramController(this.mContext);
    if (paramString.equalsIgnoreCase("hiddenfeatures"))
      return new HiddenFeaturesController(this.mContext);
    if (paramString.equalsIgnoreCase("contacts"))
      return new ImContactsController(this.mContext);
    if (paramString.equalsIgnoreCase("conversations"))
      return new ImConversationsController(this.mContext);
    if (paramString.equalsIgnoreCase("userprofile"))
      return new ImUserProfileController(this.mContext);
    if (paramString.equalsIgnoreCase("groupprofile"))
      return new ImGroupProfileController(this.mContext);
    if (paramString.equalsIgnoreCase("imchat"))
      return new ImChatController(this.mContext);
    if (paramString.equalsIgnoreCase("groupmemberlist"))
      return new ImGroupMemberListController(this.mContext);
    if (paramString.equalsIgnoreCase("groupsetting"))
      return new ImGroupSettingController(this.mContext);
    if (paramString.equalsIgnoreCase("mygroups"))
      return new ImMyGroupsController(this.mContext);
    if (paramString.equalsIgnoreCase("contactspecific"))
      return new ImContactSpecificController(this.mContext);
    if (paramString.equalsIgnoreCase("usersetting"))
      return new ImUserSettingController(this.mContext);
    if (paramString.equalsIgnoreCase("playlist"))
      return new PlayListController(this.mContext);
    if (paramString.equalsIgnoreCase("danmakuplaylist"))
      return new DanmakuPlayListController(this.mContext);
    if (paramString.equalsIgnoreCase("traschedule"))
      return new TraScheduleController(this.mContext);
    if (paramString.equalsIgnoreCase("timerSetting"))
      return new TimerPickController(this.mContext);
    if (paramString.equalsIgnoreCase("play"))
      return PlayController.getInstance(this.mContext);
    if (paramString.equalsIgnoreCase("report"))
      return new ReportController(this.mContext);
    if (paramString.equalsIgnoreCase("myreserve"))
      return new ReserveInfoController(this.mContext);
    if (paramString.equalsIgnoreCase("blockedmembers"))
      return new ImBlackListController(this.mContext);
    if (paramString.equalsIgnoreCase("virtualchannellist"))
      return new VirtualChannelListController(this.mContext);
    if (paramString.equalsIgnoreCase("specialtopic"))
      return new SpecialTopicController(this.mContext);
    if (paramString.equalsIgnoreCase("podcasterinfo"))
      return new PodcasterInfoController(this.mContext);
    if (paramString.equalsIgnoreCase("mypodcaster"))
      return new MyPodcasterController(this.mContext);
    if (paramString.equalsIgnoreCase(DanmakuSendController.NAME))
      return new DanmakuSendController(this.mContext);
    return null;
  }

  private ViewController getController(String paramString)
  {
    int j = 0;
    Object localObject1 = this.navigationController.getAllControllers().iterator();
    int i = 0;
    if (((Iterator)localObject1).hasNext())
      if (((ViewController)((Iterator)localObject1).next()).controllerName.equalsIgnoreCase(paramString))
      {
        j = 1;
        localObject1 = this.navigationController.removeController(i);
      }
    for (i = j; ; i = j)
    {
      Object localObject2;
      if (i != 0)
      {
        localObject2 = localObject1;
        if (localObject1 != null);
      }
      else
      {
        localObject2 = createController(paramString);
      }
      return localObject2;
      i += 1;
      break;
      localObject1 = null;
    }
  }

  public static ControllerManager getInstance()
  {
    if (_instance == null)
      _instance = new ControllerManager();
    return _instance;
  }

  private boolean openDefaultAlarm()
  {
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(54, 386, 0, true, "CNR中国之声");
    return true;
  }

  private void pushControllerByProperAnimation(ViewController paramViewController)
  {
    LinkManager.cancelLinkIfExists(this.mContext);
    this.navigationController.pushViewController(paramViewController, true, new MoveRtoLSwitchAnimation(), new MoveLtoRUncoverAnimation(), "");
  }

  private void pushSearchControllerSpecial(ViewController paramViewController)
  {
    LinkManager.cancelLinkIfExists(this.mContext);
    this.navigationController.pushViewController(paramViewController, true, new SearchSpecialEnterAnimation(), new SearchSpecialExitAnimation(), "");
  }

  public boolean dipatchEventToCurrentController(String paramString)
  {
    if ((this.navigationController == null) || (paramString == null))
      return false;
    if (paramString.equalsIgnoreCase("weibo_login_success"))
      WeiboChat.getInstance().getUserInfo();
    while (true)
    {
      ViewController localViewController = this.navigationController.getLastViewController();
      if (((!paramString.equalsIgnoreCase("weibo_login_success")) && (!paramString.equalsIgnoreCase("tencent_login_success"))) || (localViewController == null) || (!localViewController.controllerName.equalsIgnoreCase("setting")))
        break;
      localViewController.config("loginSuccess", null);
      return false;
      if (paramString.equalsIgnoreCase("tencent_login_success"))
        TencentChat.getInstance().getUserInfo();
    }
  }

  public int getChannelSource()
  {
    return this.mChannelSource;
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public ViewController getControllerUnderneath()
  {
    int i = this.navigationController.getCount();
    if (i >= 2)
      return this.navigationController.getViewController(i - 2);
    return null;
  }

  public ViewController getFrontPageNewController()
  {
    ViewController localViewController = this.navigationController.getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("frontpage")))
      return localViewController;
    return null;
  }

  public ViewController getLastViewController()
  {
    if (this.navigationController != null)
      return this.navigationController.getLastViewController();
    return null;
  }

  public boolean isActive(int paramInt, String paramString)
  {
    if (paramString == null)
      return false;
    Object localObject = getLastViewController();
    if (localObject == null)
      return false;
    if (((ViewController)localObject).controllerName.equalsIgnoreCase("imchat"))
    {
      int i = ((Integer)((ImChatController)localObject).getValue("getTalkingType", null)).intValue();
      localObject = (String)((ImChatController)localObject).getValue("getTalkingId", null);
      if ((localObject != null) && (i == paramInt) && (((String)localObject).equalsIgnoreCase(paramString)))
        return true;
    }
    return false;
  }

  public boolean isTopController(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")) || (this.navigationController == null));
    ViewController localViewController;
    do
    {
      return false;
      localViewController = this.navigationController.getLastViewController();
    }
    while ((localViewController == null) || (!localViewController.controllerName.equalsIgnoreCase(paramString)));
    return true;
  }

  public void openADwebviewController(QTAdvertisementInfo paramQTAdvertisementInfo, QTCoupon paramQTCoupon, String paramString)
  {
    if ((paramQTAdvertisementInfo != null) && (paramQTCoupon == null));
  }

  public void openADwebviewController(String paramString)
  {
    paramString = new GroupWebViewController(getContext(), new GroupWebView(getContext(), paramString, true, false), null);
    paramString.controllerName = "qtadcontroller";
    pushControllerByProperAnimation(paramString);
  }

  public void openAboutQtController()
  {
    AboutQtController localAboutQtController = new AboutQtController(this.mContext);
    localAboutQtController.config("setData", null);
    pushControllerByProperAnimation(localAboutQtController);
  }

  public void openAlarmAddController(IEventHandler paramIEventHandler)
  {
    ViewController localViewController = getController("alarmsetting");
    localViewController.config("addalarm", null);
    localViewController.setEventHandler(paramIEventHandler);
    pushControllerByProperAnimation(localViewController);
  }

  public void openAlarmControllerListOrAdd()
  {
    Object localObject = InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms;
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      QTMSGManage.getInstance().sendStatistcsMessage("alarm_enter", "personel");
      localObject = new AlarmSettingController(getContext());
      ((AlarmSettingController)localObject).config("addalarm", null);
      ((AlarmSettingController)localObject).config("setDirect", null);
      pushControllerByProperAnimation((ViewController)localObject);
      return;
    }
    QTMSGManage.getInstance().sendStatistcsMessage("clickAlarm");
    QTMSGManage.getInstance().sendStatistcsMessage("alarm_enter", "personel");
    localObject = new AlarmListController(getContext(), true);
    ((ViewController)localObject).config("setData", null);
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openAlarmSettingController(AlarmInfo paramAlarmInfo, IEventHandler paramIEventHandler)
  {
    ViewController localViewController = getController("alarmsetting");
    localViewController.config("setData", paramAlarmInfo);
    localViewController.setEventHandler(paramIEventHandler);
    pushControllerByProperAnimation(localViewController);
  }

  public void openAudioQualitySettingController()
  {
    AudioQualitySettingController localAudioQualitySettingController = new AudioQualitySettingController(this.mContext);
    localAudioQualitySettingController.config("setData", null);
    pushControllerByProperAnimation(localAudioQualitySettingController);
  }

  public void openBlockedMembersController()
  {
    ViewController localViewController = getController("blockedmembers");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openCategoryListView(Node paramNode)
  {
    redirect2View("categorylist", paramNode);
  }

  public void openCategoryOrderManageController()
  {
    CategoryOrderManageController localCategoryOrderManageController = new CategoryOrderManageController(this.mContext);
    localCategoryOrderManageController.config("setData", null);
    pushControllerByProperAnimation(localCategoryOrderManageController);
  }

  public void openChannelDetailController(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString, boolean paramBoolean)
  {
    if (paramInt4 == 0)
    {
      openPlayController(paramInt1, paramInt2, paramInt3, paramInt4, paramString, paramBoolean);
      return;
    }
    getInstance().setChannelSource(0);
    ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(paramInt2, paramInt4);
    ChannelNode localChannelNode1 = localChannelNode2;
    if (localChannelNode2 == null)
    {
      if (paramInt4 != 1)
        break label99;
      localChannelNode1 = ChannelHelper.getInstance().getFakeVirtualChannel(paramInt2, paramInt1, paramString);
    }
    while (true)
    {
      redirect2View("channeldetail", localChannelNode1);
      if (!paramBoolean)
        break;
      paramString = localChannelNode1.getProgramNode(paramInt3);
      if (paramString == null)
        break;
      PlayerAgent.getInstance().play(paramString);
      return;
      label99: localChannelNode1 = localChannelNode2;
      if (paramInt4 == 0)
        localChannelNode1 = ChannelHelper.getInstance().getFakeLiveChannel(paramInt2, paramInt1, paramString);
    }
  }

  public void openChannelDetailController(Node paramNode)
  {
    if (paramNode == null)
      return;
    openChannelDetailController(paramNode, false, true);
  }

  public void openChannelDetailController(Node paramNode, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramNode == null)
      return;
    Object localObject2;
    Object localObject1;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
      if (((ProgramNode)paramNode).mLiveInVirtual)
      {
        localObject2 = ChannelHelper.getInstance().getChannel(((ProgramNode)paramNode).channelId, 1);
        localObject1 = localObject2;
        if (localObject2 != null)
          break label427;
        localObject1 = ChannelHelper.getInstance().getFakeVirtualChannel(((ProgramNode)paramNode).channelId, ((ProgramNode)paramNode).getCategoryId(), ((ProgramNode)paramNode).title);
      }
    label416: label427: 
    while (true)
    {
      if (((ChannelNode)localObject1).ratingStar == -1)
        ((ChannelNode)localObject1).ratingStar = ((ProgramNode)paramNode).channelRatingStar;
      if ((paramBoolean2) && (InfoManager.getInstance().enableBarrage(((ProgramNode)paramNode).channelId)))
      {
        openDamakuPlayController();
        label127: if (paramBoolean1)
        {
          if (!((ProgramNode)paramNode).mLiveInVirtual)
            break label305;
          if (((ProgramNode)paramNode).getCurrPlayStatus() != 2)
            PlayerAgent.getInstance().play(paramNode);
        }
      }
      while (true)
      {
        DoubleClick.getInstance().visitChannel(((ProgramNode)paramNode).channelId, ((ProgramNode)paramNode).getChannelName());
        return;
        localObject2 = ChannelHelper.getInstance().getChannel(((ProgramNode)paramNode).channelId, ((ProgramNode)paramNode).channelType);
        localObject1 = localObject2;
        if (localObject2 != null)
          break label427;
        localObject1 = ChannelHelper.getInstance().getFakeChannel(((ProgramNode)paramNode).channelId, ((ProgramNode)paramNode).getCategoryId(), ((ProgramNode)paramNode).title, ((ProgramNode)paramNode).channelType);
        break;
        localObject2 = InfoManager.getInstance().h5Channel(((ChannelNode)localObject1).channelId);
        if ((localObject2 != null) && (!((String)localObject2).equalsIgnoreCase("")))
        {
          redirectToActiviyByUrl((String)localObject2, ((ChannelNode)localObject1).title, false);
          break label127;
        }
        redirect2View("channeldetail", localObject1);
        break label127;
        label305: PlayerAgent.getInstance().play(paramNode);
      }
      if (!paramNode.nodeName.equalsIgnoreCase("channel"))
        break;
      localObject1 = InfoManager.getInstance().h5Channel(((ChannelNode)paramNode).channelId);
      if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
      {
        redirectToActiviyByUrl((String)localObject1, ((ChannelNode)paramNode).title, false);
        if (!paramBoolean1)
          break label416;
        ((ChannelNode)paramNode).setAutoPlay(true);
      }
      while (true)
      {
        DoubleClick.getInstance().visitChannel(((ChannelNode)paramNode).channelId, ((ChannelNode)paramNode).title);
        return;
        redirect2View("channeldetail", paramNode);
        break;
        ((ChannelNode)paramNode).setAutoPlay(false);
      }
    }
  }

  public void openChannelDetailControllerAndPlay(Node paramNode)
  {
    if (paramNode == null)
      return;
    openChannelDetailController(paramNode, true, true);
  }

  public void openChannelDetailControllerWithoutDamaku(Node paramNode)
  {
    if (paramNode == null)
      return;
    openChannelDetailController(paramNode, false, false);
  }

  public void openChannelListByAttributeController(CategoryNode paramCategoryNode, Attribute paramAttribute)
  {
    ViewController localViewController = getController("virtualchannellist");
    localViewController.config("setNode", paramCategoryNode);
    localViewController.config("setData", paramAttribute);
    pushControllerByProperAnimation(localViewController);
  }

  public void openChatHistoryController(String paramString, List<ChatItem> paramList)
  {
    ChatHistoryController localChatHistoryController = new ChatHistoryController(this.mContext);
    localChatHistoryController.config("setTitle", paramString);
    localChatHistoryController.config("setData", paramList);
    pushControllerByProperAnimation(localChatHistoryController);
  }

  public void openChatRoom(String paramString1, String paramString2, String paramString3, Node paramNode, Object[] paramArrayOfObject)
  {
    Object localObject1 = this.navigationController.getAllControllers().iterator();
    int i = 0;
    if (((Iterator)localObject1).hasNext())
      if (((ViewController)((Iterator)localObject1).next()).controllerName.equalsIgnoreCase("chatroom"))
      {
        localObject1 = (ChatRoomcontroller)this.navigationController.removeController(i);
        i = 1;
      }
    while (true)
    {
      Object localObject2;
      if (i != 0)
      {
        localObject2 = localObject1;
        if (localObject1 != null);
      }
      else
      {
        localObject2 = new ChatRoomcontroller(this.mContext);
      }
      localObject1 = paramString2;
      if (paramString2 == null)
        localObject1 = "startRoom";
      ((ChatRoomcontroller)localObject2).config((String)localObject1, paramNode);
      if ((paramString3 != null) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
        ((ChatRoomcontroller)localObject2).config(paramString3, paramArrayOfObject[0]);
      pushControllerByProperAnimation((ViewController)localObject2);
      if ((paramString1.equalsIgnoreCase("flower")) && (paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
        ((ChatRoomcontroller)localObject2).config("flower", paramArrayOfObject[0]);
      return;
      i += 1;
      break;
      i = 0;
      localObject1 = null;
    }
  }

  public void openChinaUnicomZone()
  {
    pushControllerByProperAnimation(new ChinaUnicomZoneController(this.mContext));
  }

  public void openControllerByBillboardItemNode(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("billboarditem")));
    do
    {
      do
      {
        return;
        paramNode = (BillboardItemNode)paramNode;
        paramNode.mClickCnt += 1;
      }
      while (paramNode.mNode == null);
      if (paramNode.mNode.nodeName.equalsIgnoreCase("channel"))
      {
        if (((ChannelNode)paramNode.mNode).isNovelChannel())
        {
          getInstance().setChannelSource(0);
          openNovelDetailView(paramNode.mNode);
          return;
        }
        if (((ChannelNode)paramNode.mNode).channelType == 1)
        {
          getInstance().setChannelSource(0);
          openChannelDetailController(paramNode.mNode);
          return;
        }
        redirectToPlayViewByNode(paramNode.mNode, true);
        return;
      }
    }
    while (!paramNode.mNode.nodeName.equalsIgnoreCase("program"));
    getInstance().setChannelSource(0);
    openChannelDetailController((ProgramNode)paramNode.mNode, true, true);
  }

  public void openControllerByPlayingProgramNode(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("recommendplayingitem")))
      return;
    redirectToPlayViewByType(0, 0, ((RecommendPlayingItemNode)paramNode).channelId, 0, 0, true, true);
  }

  public void openControllerByRecommendNode(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("recommenditem")));
    do
    {
      return;
      paramNode = (RecommendItemNode)paramNode;
      paramNode.mClickCnt += 1;
    }
    while ((paramNode.mNode == null) || (paramNode.mNode.nodeName.equalsIgnoreCase("category")));
    Object localObject;
    if (paramNode.mNode.nodeName.equalsIgnoreCase("channel"))
    {
      localObject = (ChannelNode)paramNode.mNode;
      if (paramNode.ratingStar != -1)
        ((ChannelNode)localObject).ratingStar = paramNode.ratingStar;
      if (((ChannelNode)localObject).isNovelChannel())
      {
        getInstance().setChannelSource(1);
        openNovelDetailView(paramNode.mNode);
        return;
      }
      if (((ChannelNode)localObject).channelType == 1)
      {
        getInstance().setChannelSource(1);
        openChannelDetailController(paramNode.mNode);
        return;
      }
      redirectToPlayViewByNode(paramNode.mNode, true);
      return;
    }
    if (paramNode.mNode.nodeName.equalsIgnoreCase("program"))
    {
      localObject = paramNode.parent;
      if ((localObject == null) || (!((Node)localObject).nodeName.equalsIgnoreCase("recommendcategory")))
        break label405;
    }
    label405: for (boolean bool = ((RecommendCategoryNode)localObject).isFrontpage(); ; bool = false)
    {
      if (bool)
        if (paramNode.categoryPos == 0)
          PlayerAgent.getInstance().addPlaySource(21);
      while (true)
      {
        ((ProgramNode)paramNode.mNode).setCategoryId(paramNode.mCategoryId);
        getInstance().setChannelSource(1);
        if (!PlayerAgent.getInstance().isPlaying())
          break;
        openChannelDetailController((ProgramNode)paramNode.mNode, false, true);
        return;
        PlayerAgent.getInstance().addPlaySource(22);
        continue;
        if (paramNode.categoryPos == 0)
          PlayerAgent.getInstance().addPlaySource(25);
        else
          PlayerAgent.getInstance().addPlaySource(36);
      }
      openChannelDetailController((ProgramNode)paramNode.mNode, true, true);
      return;
      if (paramNode.mNode.nodeName.equalsIgnoreCase("activity"))
      {
        MobclickAgent.onEvent(getContext(), "openActivityFromRecommend", paramNode.name);
        if ((paramNode.isAds) && (paramNode.mAdNode != null))
          paramNode.mAdNode.onClick();
        redirectToActivityViewByNode(paramNode.mNode);
        return;
      }
      if (!paramNode.mNode.nodeName.equalsIgnoreCase("specialtopic"))
        break;
      openSpecialTopicController((SpecialTopicNode)paramNode.mNode);
      return;
    }
  }

  public void openDamakuPlayController()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("program")))
    {
      int i = ((ProgramNode)localObject).id;
      QTMSGManage.getInstance().sendStatistcsMessage("danmaku_open", "" + i);
    }
    localObject = getController("danmakumainplayview");
    ((ViewController)localObject).config("setData", null);
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openDanmakuPlayListContoller(Drawable paramDrawable, List<ProgramNode> paramList)
  {
    ViewController localViewController = getController("danmakuplaylist");
    LinkManager.cancelLinkIfExists(this.mContext);
    localViewController.config("setBackground", paramDrawable);
    localViewController.config("setData", paramList);
    this.navigationController.pushViewController(localViewController, false);
  }

  public void openDanmakuSendController(Object paramObject)
  {
    ViewController localViewController = getController("danmakusend");
    localViewController.config("setData", paramObject);
    pushControllerByProperAnimation(localViewController);
  }

  public void openDaySettingController(int paramInt, IEventHandler paramIEventHandler)
  {
    AlarmDaySettingController localAlarmDaySettingController = new AlarmDaySettingController(getContext());
    localAlarmDaySettingController.config("day", Integer.valueOf(paramInt));
    localAlarmDaySettingController.setEventHandler(paramIEventHandler);
    pushControllerByProperAnimation(localAlarmDaySettingController);
  }

  public void openDiscoverCategoryController(int paramInt)
  {
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt);
    if (localCategoryNode == null)
      return;
    pushControllerByProperAnimation(new DiscoverCategoryController(this.mContext, localCategoryNode));
  }

  public void openDownloadDirSettingController()
  {
    DownloadDirSettingController localDownloadDirSettingController = new DownloadDirSettingController(this.mContext);
    localDownloadDirSettingController.config("setData", null);
    pushControllerByProperAnimation(localDownloadDirSettingController);
  }

  public void openFaqController()
  {
    FaqController localFaqController = new FaqController(this.mContext);
    localFaqController.config("setData", null);
    pushControllerByProperAnimation(localFaqController);
  }

  public void openFeedBackController()
  {
    FeedbackController localFeedbackController = new FeedbackController(this.mContext);
    localFeedbackController.config("setData", null);
    pushControllerByProperAnimation(localFeedbackController);
  }

  public void openFeedbackContactInfoController()
  {
    pushControllerByProperAnimation(new ContactInfoController(this.mContext));
  }

  public void openHiddenFeatureController()
  {
    ViewController localViewController = getController("hiddenfeatures");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImAddcontactController()
  {
    ViewController localViewController = getController("addcontact");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImChatController(Object paramObject)
  {
    ViewController localViewController = getController("imchat");
    localViewController.config("setData", paramObject);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImContactSpecificController(boolean paramBoolean)
  {
    ViewController localViewController = getController("contactspecific");
    localViewController.config("setData", Boolean.valueOf(paramBoolean));
    pushControllerByProperAnimation(localViewController);
  }

  public void openImContactsController()
  {
    ViewController localViewController = getController("contacts");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImConversationsController()
  {
    ViewController localViewController = getController("conversations");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImGroupMemberListController(List<UserInfo> paramList)
  {
    ViewController localViewController = getController("groupmemberlist");
    localViewController.config("setData", paramList);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImGroupProfileController(String paramString)
  {
    ViewController localViewController = getController("groupprofile");
    localViewController.config("setData", paramString);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImGroupSettingController(String paramString)
  {
    ViewController localViewController = getController("groupsetting");
    localViewController.config("setData", paramString);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImReportController(IMMessage paramIMMessage)
  {
    ViewController localViewController = getController("report");
    localViewController.config("setData", paramIMMessage);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImUserProfileController(String paramString)
  {
    if (paramString == null)
      return;
    ViewController localViewController = getController("userprofile");
    localViewController.config("setData", paramString);
    pushControllerByProperAnimation(localViewController);
  }

  public void openImUserSettingController(String paramString)
  {
    ViewController localViewController = getController("usersetting");
    localViewController.config("setData", paramString);
    pushControllerByProperAnimation(localViewController);
  }

  public void openJDShop(CommodityInfo paramCommodityInfo)
  {
    ThirdTracker.getInstance().sendThirdTrackLog("ThirdAdv", "1", 0, InfoManager.getInstance().getJdAdPosition(), Constants.ADV_CLICK, 0);
    redirectToActiviyByUrl(paramCommodityInfo.getShopUrl(), paramCommodityInfo.getTitle(), true);
  }

  public void openLauncherController()
  {
    pushControllerByProperAnimation(new LauncherController(this.mContext));
  }

  public void openMyDownloadController(String paramString)
  {
    paramString = new MyDownloadController(this.mContext);
    paramString.config("setData", null);
    pushControllerByProperAnimation(paramString);
  }

  public void openMyGroupsController(List<GroupInfo> paramList)
  {
    ViewController localViewController = getController("mygroups");
    localViewController.config("setData", paramList);
    pushControllerByProperAnimation(localViewController);
  }

  public void openMyPodcasterController()
  {
    ViewController localViewController = getController("mypodcaster");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openNovelAllContentController(int paramInt)
  {
    paramInt = InfoManager.getInstance().root().getSecIdByCatId(paramInt);
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt);
    if (localCategoryNode == null)
      return;
    NovelAllContentController localNovelAllContentController = new NovelAllContentController(this.mContext);
    localNovelAllContentController.config("setNode", localCategoryNode);
    pushControllerByProperAnimation(localNovelAllContentController);
  }

  public void openNovelAllContentController(CategoryNode paramCategoryNode)
  {
    NovelAllContentController localNovelAllContentController = new NovelAllContentController(this.mContext);
    localNovelAllContentController.config("setNode", paramCategoryNode);
    pushControllerByProperAnimation(localNovelAllContentController);
  }

  public void openNovelDetailView(Node paramNode)
  {
    openChannelDetailController(paramNode);
  }

  public void openOnlineMemberController(String paramString, IEventHandler paramIEventHandler)
  {
    OnlineMembersController localOnlineMembersController = new OnlineMembersController(getContext());
    localOnlineMembersController.setEventHandler(paramIEventHandler);
    localOnlineMembersController.config("setData", paramString);
    pushControllerByProperAnimation(localOnlineMembersController);
  }

  public void openPlayController(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString, boolean paramBoolean)
  {
    if (paramInt3 == 0)
    {
      openPlayController(paramInt1, paramInt2, paramInt4, paramBoolean, paramString);
      return;
    }
    ChannelNode localChannelNode1;
    ChannelNode localChannelNode2;
    if (paramInt1 == DownLoadInfoNode.mDownloadId)
    {
      localChannelNode1 = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramInt2);
      if (localChannelNode1 != null)
        break label126;
      if (paramInt4 != 1)
        break label102;
      localChannelNode2 = ChannelHelper.getInstance().getFakeVirtualChannel(paramInt2, paramInt1, paramString);
    }
    while (true)
    {
      if (localChannelNode2 != null)
        InfoManager.getInstance().root().setPlayingChannelNode(localChannelNode2);
      openPlayController(paramBoolean, paramInt3);
      return;
      localChannelNode1 = ChannelHelper.getInstance().getChannel(paramInt2, paramInt4, paramString);
      break;
      label102: localChannelNode2 = localChannelNode1;
      if (paramInt4 == 0)
      {
        localChannelNode2 = ChannelHelper.getInstance().getFakeLiveChannel(paramInt2, paramInt1, paramString);
        continue;
        label126: localChannelNode2 = localChannelNode1;
        if (paramBoolean)
        {
          localChannelNode2 = localChannelNode1;
          if (localChannelNode1.channelType == 0)
          {
            PlayerAgent.getInstance().play(localChannelNode1);
            localChannelNode2 = localChannelNode1;
          }
        }
      }
    }
  }

  public void openPlayController(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    if (paramInt3 == 0)
    {
      openPlayController(paramInt1, paramInt2, paramInt4, paramBoolean, null);
      return;
    }
    ChannelNode localChannelNode1;
    ChannelNode localChannelNode2;
    if (paramInt1 == DownLoadInfoNode.mDownloadId)
    {
      localChannelNode1 = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramInt2);
      localChannelNode2 = localChannelNode1;
      if (localChannelNode1 == null)
      {
        if (paramInt4 != 1)
          break label127;
        localChannelNode2 = ChannelHelper.getInstance().getFakeVirtualChannel(paramInt2, paramInt1, null);
      }
    }
    while (true)
    {
      if (localChannelNode2 != null)
      {
        InfoManager.getInstance().root().setPlayingChannelNode(localChannelNode2);
        if ((paramBoolean) && (localChannelNode2.channelType == 0))
          PlayerAgent.getInstance().play(localChannelNode2);
      }
      openPlayController(paramInt1, paramInt2, paramInt4, paramBoolean, null);
      return;
      localChannelNode1 = ChannelHelper.getInstance().getChannel(paramInt2, paramInt4);
      break;
      label127: localChannelNode2 = localChannelNode1;
      if (paramInt4 == 0)
        localChannelNode2 = ChannelHelper.getInstance().getFakeLiveChannel(paramInt2, paramInt1, null);
    }
  }

  public void openPlayController(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, String paramString)
  {
    if (paramInt2 == 0)
    {
      openPlayController(false, 0);
      return;
    }
    ChannelNode localChannelNode1;
    ChannelNode localChannelNode2;
    if (paramInt1 == DownLoadInfoNode.mDownloadId)
    {
      localChannelNode1 = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramInt2);
      localChannelNode2 = localChannelNode1;
      if (localChannelNode1 == null)
      {
        if (paramInt3 != 1)
          break label140;
        localChannelNode2 = ChannelHelper.getInstance().getFakeVirtualChannel(paramInt2, paramInt1, paramString);
      }
    }
    while (true)
      if (localChannelNode2 != null)
      {
        InfoManager.getInstance().root().setPlayingChannelNode(localChannelNode2);
        if ((paramBoolean) && (localChannelNode2.channelType == 0))
        {
          if ((localChannelNode2.getSourceUrl() != null) && (!localChannelNode2.getSourceUrl().equalsIgnoreCase("")))
          {
            PlayerAgent.getInstance().play(localChannelNode2);
            openPlayController(false, 0);
            return;
            localChannelNode1 = ChannelHelper.getInstance().getChannel(paramInt2, paramInt3, paramString);
            break;
            label140: localChannelNode2 = localChannelNode1;
            if (paramInt3 != 0)
              continue;
            localChannelNode2 = ChannelHelper.getInstance().getFakeLiveChannel(paramInt2, paramInt1, paramString);
            continue;
          }
          openPlayController(true, 0);
          return;
        }
      }
    openPlayController(paramBoolean, 0);
  }

  public void openPlayController(ProgramNode paramProgramNode, boolean paramBoolean)
  {
    if (paramProgramNode == null)
    {
      openPlayController(false, 0);
      return;
    }
    ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(paramProgramNode);
    if (localChannelNode != null)
      InfoManager.getInstance().root().setPlayingChannelNode(localChannelNode);
    while (true)
    {
      InfoManager.getInstance().root().setPlayingNode(paramProgramNode);
      openPlayController(paramBoolean, paramProgramNode.id);
      return;
      localChannelNode = ChannelHelper.getInstance().getFakeChannel(paramProgramNode.channelId, -1, paramProgramNode.title, paramProgramNode.channelType);
      if (localChannelNode != null)
        InfoManager.getInstance().root().setPlayingChannelNode(localChannelNode);
    }
  }

  public void openPlayController(String paramString)
  {
    String str = "mainplayview";
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Object localObject = str;
    if (localChannelNode != null)
    {
      localObject = str;
      if (!localChannelNode.isLiveChannel())
      {
        localObject = str;
        if (!localChannelNode.isDownloadChannel())
        {
          localObject = str;
          if (InfoManager.getInstance().enableBarrage(localChannelNode.channelId))
          {
            localObject = "danmakumainplayview";
            QTMSGManage.getInstance().sendStatistcsMessage("danmaku_open", paramString);
          }
        }
      }
    }
    localObject = getController((String)localObject);
    ((ViewController)localObject).config("programid", paramString);
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openPlayController(boolean paramBoolean, int paramInt)
  {
    String str = "mainplayview";
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Object localObject = str;
    if (localChannelNode != null)
    {
      localObject = str;
      if (!localChannelNode.isLiveChannel())
      {
        localObject = str;
        if (!localChannelNode.isDownloadChannel())
        {
          localObject = str;
          if (InfoManager.getInstance().enableBarrage(localChannelNode.channelId))
          {
            localObject = "danmakumainplayview";
            QTMSGManage.getInstance().sendStatistcsMessage("danmaku_open", "" + paramInt);
          }
        }
      }
    }
    localObject = getController((String)localObject);
    ((ViewController)localObject).config("setData", null);
    if (paramBoolean)
      ((ViewController)localObject).config("autoplay", Integer.valueOf(paramInt));
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openPlayGameController()
  {
    if ((this.navigationController.getLastViewController() instanceof PlayGameController))
    {
      popLastController();
      return;
    }
    ViewController localViewController = getController("playgame");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openPlayHistoryController()
  {
    if ((this.navigationController.getLastViewController() instanceof PlayHistoryController))
    {
      popLastController();
      return;
    }
    ViewController localViewController = getController("playhistory");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openPlayListContoller(Drawable paramDrawable, List<ProgramNode> paramList)
  {
    ViewController localViewController = getController("playlist");
    LinkManager.cancelLinkIfExists(this.mContext);
    localViewController.config("setBackground", paramDrawable);
    localViewController.config("setData", paramList);
    this.navigationController.pushViewController(localViewController, false);
  }

  public boolean openPlayViewForAlarm(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt1 == 0) || (paramInt2 == 0))
      return openDefaultAlarm();
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(paramInt1, paramInt2, paramInt3, paramInt4, "蜻蜓闹钟", true);
    return true;
  }

  public void openPodcasterInfoController(UserInfo paramUserInfo)
  {
    if (paramUserInfo != null)
    {
      String str = InfoManager.getInstance().h5Podcaster(paramUserInfo.podcasterId);
      if ((str != null) && (!str.equalsIgnoreCase("")))
      {
        localObject = paramUserInfo.podcasterName;
        paramUserInfo = (UserInfo)localObject;
        if (((String)localObject).equalsIgnoreCase("加载中"))
          paramUserInfo = null;
        redirectToActiviyByUrl(str, paramUserInfo, false);
        return;
      }
    }
    Object localObject = getController("podcasterinfo");
    ((ViewController)localObject).config("setData", paramUserInfo);
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openPushMessageController()
  {
    PushMessageSettingController localPushMessageSettingController = new PushMessageSettingController(this.mContext);
    localPushMessageSettingController.config("setData", null);
    pushControllerByProperAnimation(localPushMessageSettingController);
  }

  public void openRadioChannelsController(Node paramNode)
  {
    if (paramNode == null)
      return;
    redirect2View("radiochannellist", paramNode);
  }

  public void openReserveController()
  {
    ViewController localViewController = getController("myreserve");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openRingChannelSettingController(AlarmInfo paramAlarmInfo, ChannelNode paramChannelNode, IEventHandler paramIEventHandler)
  {
    RingChannelPickController localRingChannelPickController = new RingChannelPickController(getContext());
    localRingChannelPickController.setEventHandler(paramIEventHandler);
    if (paramAlarmInfo != null)
      localRingChannelPickController.config("setRingtone", Integer.valueOf(paramAlarmInfo.channelId));
    if (paramChannelNode != null)
      localRingChannelPickController.config("setRingChannel", paramChannelNode);
    localRingChannelPickController.config("setData", null);
    pushControllerByProperAnimation(localRingChannelPickController);
  }

  public void openRingtoneSettingController(AlarmInfo paramAlarmInfo, IEventHandler paramIEventHandler)
  {
    AlarmDjRingtoneSettingController localAlarmDjRingtoneSettingController = new AlarmDjRingtoneSettingController(getContext());
    localAlarmDjRingtoneSettingController.setEventHandler(paramIEventHandler);
    if (paramAlarmInfo != null)
      localAlarmDjRingtoneSettingController.config("setRingtone", paramAlarmInfo.ringToneId);
    localAlarmDjRingtoneSettingController.config("setData", null);
    pushControllerByProperAnimation(localAlarmDjRingtoneSettingController);
  }

  public void openSectionAd(AdvertisementItemNode paramAdvertisementItemNode)
  {
    if (paramAdvertisementItemNode != null)
    {
      if (!(paramAdvertisementItemNode instanceof AdvertisementItemNode3rdParty))
        break label19;
      ((AdvertisementItemNode3rdParty)paramAdvertisementItemNode).onClick();
    }
    label19: 
    do
    {
      return;
      paramAdvertisementItemNode = paramAdvertisementItemNode.convertToRecommendItem(0);
    }
    while (paramAdvertisementItemNode == null);
    getInstance().openControllerByRecommendNode(paramAdvertisementItemNode);
  }

  public void openSettingController()
  {
    ViewController localViewController = getController("setting");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openSpecialTopicController(int paramInt)
  {
    PlayerAgent.getInstance().addPlaySource(41);
    Object localObject = InfoManager.getInstance().h5SpecialTopic(paramInt);
    if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
    {
      redirectToActiviyByUrl((String)localObject, null, false);
      return;
    }
    localObject = getController("specialtopic");
    ((ViewController)localObject).config("setid", Integer.valueOf(paramInt));
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openSpecialTopicController(SpecialTopicNode paramSpecialTopicNode)
  {
    PlayerAgent.getInstance().addPlaySource(41);
    if (paramSpecialTopicNode == null)
      return;
    Object localObject = InfoManager.getInstance().h5SpecialTopic(paramSpecialTopicNode.getApiId());
    if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
    {
      redirectToActiviyByUrl((String)localObject, paramSpecialTopicNode.title, false);
      return;
    }
    localObject = getController("specialtopic");
    ((ViewController)localObject).config("setData", paramSpecialTopicNode);
    pushControllerByProperAnimation((ViewController)localObject);
  }

  public void openTimerSettingController()
  {
    ViewController localViewController = getController("timerSetting");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void openTraScheduleController(Drawable paramDrawable, ChannelNode paramChannelNode, int paramInt)
  {
    ViewController localViewController = getController("traschedule");
    LinkManager.cancelLinkIfExists(this.mContext);
    localViewController.config("setBackground", paramDrawable);
    localViewController.config("initState", Integer.valueOf(paramInt));
    localViewController.config("setData", paramChannelNode);
    this.navigationController.pushViewController(localViewController, false);
  }

  public void openTraditionalChannelsView(int paramInt)
  {
    openTraditionalChannelsView(InfoManager.getInstance().root().mContentCategory.mLiveNode.getCategoryNode(paramInt));
  }

  public void openTraditionalChannelsView(Node paramNode)
  {
    if (paramNode == null)
      return;
    redirect2View("channellist", paramNode);
  }

  public void openUmengRecommendApp()
  {
  }

  public void openUploadVoiceController(ChannelNode paramChannelNode)
  {
    UploadVoiceController localUploadVoiceController = new UploadVoiceController(getContext());
    localUploadVoiceController.config("setData", paramChannelNode);
    if (localUploadVoiceController.performInit())
      pushControllerByProperAnimation(localUploadVoiceController);
  }

  public void openVirtualCategoryAllContentController(int paramInt)
  {
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt);
    if (localCategoryNode == null)
      return;
    VirtualCategoryAllContentController localVirtualCategoryAllContentController = new VirtualCategoryAllContentController(this.mContext);
    localVirtualCategoryAllContentController.config("setNode", localCategoryNode);
    localVirtualCategoryAllContentController.config("setData", null);
    pushControllerByProperAnimation(localVirtualCategoryAllContentController);
  }

  public void openVirtualCategoryAllContentController(int paramInt1, int paramInt2)
  {
    paramInt1 = InfoManager.getInstance().root().getSecIdByCatId(paramInt1);
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt1);
    VirtualCategoryAllContentController localVirtualCategoryAllContentController = new VirtualCategoryAllContentController(this.mContext);
    localVirtualCategoryAllContentController.config("setNode", localCategoryNode);
    localVirtualCategoryAllContentController.config("setId", Integer.valueOf(paramInt2));
    localVirtualCategoryAllContentController.config("setData", null);
    pushControllerByProperAnimation(localVirtualCategoryAllContentController);
  }

  public void openVirtualCategoryAllContentController(int paramInt, RecommendItemNode paramRecommendItemNode)
  {
    paramInt = InfoManager.getInstance().root().getSecIdByCatId(paramInt);
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt);
    VirtualCategoryAllContentController localVirtualCategoryAllContentController = new VirtualCategoryAllContentController(this.mContext);
    localVirtualCategoryAllContentController.config("setNode", localCategoryNode);
    localVirtualCategoryAllContentController.config("setData", paramRecommendItemNode);
    pushControllerByProperAnimation(localVirtualCategoryAllContentController);
  }

  public void openVirtualCategoryAllContentController(int paramInt, String paramString)
  {
    CategoryNode localCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getCategoryNode(paramInt);
    if (localCategoryNode == null)
      break label21;
    while (true)
    {
      label21: return;
      if (paramString != null)
      {
        List localList = localCategoryNode.getLstAttributes(true);
        if (localList == null)
          break;
        paramInt = 0;
        while (paramInt < localList.size())
        {
          if (((Attributes)localList.get(paramInt)).mLstAttribute != null)
          {
            int i = 0;
            while (i < ((Attributes)localList.get(paramInt)).mLstAttribute.size())
            {
              if (paramString.contains(String.valueOf(((Attribute)((Attributes)localList.get(paramInt)).mLstAttribute.get(i)).id)))
              {
                openChannelListByAttributeController(localCategoryNode, (Attribute)((Attributes)localList.get(paramInt)).mLstAttribute.get(i));
                return;
              }
              i += 1;
            }
          }
          paramInt += 1;
        }
      }
    }
  }

  public void openVirtualCategoryAllContentController(CategoryNode paramCategoryNode)
  {
    if (InfoManager.getInstance().getRecommendCategoryBySecId(paramCategoryNode.sectionId) == null)
      return;
    VirtualCategoryAllContentController localVirtualCategoryAllContentController = new VirtualCategoryAllContentController(this.mContext);
    localVirtualCategoryAllContentController.config("setNode", paramCategoryNode);
    localVirtualCategoryAllContentController.config("setData", null);
    pushControllerByProperAnimation(localVirtualCategoryAllContentController);
  }

  public void openVirtualCategoryController(Node paramNode)
  {
    if (paramNode == null)
      return;
    ViewController localViewController = getController("virtualcategory");
    localViewController.config("setData", paramNode);
    pushControllerByProperAnimation(localViewController);
  }

  public void openWoController()
  {
    WoQtController localWoQtController = new WoQtController(this.mContext);
    localWoQtController.config("setData", null);
    pushControllerByProperAnimation(localWoQtController);
  }

  public void openWoFaqController()
  {
    pushControllerByProperAnimation(new WoFaqViewController(getContext()));
  }

  public boolean playedLastChannel()
  {
    return this.hasPlayedLastChannel;
  }

  public void popLastController()
  {
    if ((this.navigationController.getLastViewController() instanceof WoQtController))
    {
      if (((WoQtController)this.navigationController.getLastViewController()).isHome())
      {
        LinkManager.cancelLinkIfExists(this.mContext);
        this.navigationController.popViewController(true);
      }
      return;
    }
    if ((this.navigationController.getLastViewController() instanceof ChinaUnicomZoneController))
    {
      ((ChinaUnicomZoneController)this.navigationController.getLastViewController()).back();
      LinkManager.cancelLinkIfExists(this.mContext);
      this.navigationController.popViewController(true);
      return;
    }
    LinkManager.cancelLinkIfExists(this.mContext);
    this.navigationController.popViewController(true);
  }

  public void popLastControllerAndOpenParent()
  {
    int j = 0;
    int i;
    Object localObject;
    boolean bool;
    if ((InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms == null) || (InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.mLstAlarms.size() == 0))
    {
      i = 1;
      localObject = getContext();
      if (i != 0)
        break label163;
      bool = true;
      label57: localObject = new AlarmListController((Context)localObject, bool);
      ((AlarmListController)localObject).config("setData", null);
      this.navigationController.pushViewController((ViewController)localObject, true, new MoveLtoRUncoverAnimation(), new MoveLtoRUncoverAnimation(), "showPersonal");
      localObject = this.navigationController.getAllControllers().iterator();
      i = j;
    }
    while (true)
    {
      if (((Iterator)localObject).hasNext())
      {
        if (((ViewController)((Iterator)localObject).next()).controllerName.equalsIgnoreCase("alarmsetting"))
          this.navigationController.removeController(i).controllerDidPopped();
      }
      else
      {
        return;
        i = 0;
        break;
        label163: bool = false;
        break label57;
      }
      i += 1;
    }
  }

  public void popToRootController()
  {
    this.navigationController.popToRootViewController(true);
  }

  public void popToRootControllerUsingAnimation(ISwitchAnimation paramISwitchAnimation)
  {
    this.navigationController.popToRootViewControllerUsingAnimation(paramISwitchAnimation);
  }

  public void redirect2View(String paramString, Object paramObject)
  {
    try
    {
      paramString = getController(paramString);
      paramString.config("setData", paramObject);
      pushControllerByProperAnimation(paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void redirectPlayViewTimer()
  {
    openPlayController(false, 0);
  }

  public void redirectToActivityByGame(GameBean paramGameBean)
  {
    if (paramGameBean == null)
      return;
    ActivityNode localActivityNode = new ActivityNode();
    localActivityNode.id = 1;
    localActivityNode.name = paramGameBean.title;
    localActivityNode.type = "1";
    localActivityNode.updatetime = 25200;
    localActivityNode.infoUrl = null;
    localActivityNode.infoTitle = paramGameBean.title;
    localActivityNode.desc = paramGameBean.desc;
    localActivityNode.titleIconUrl = null;
    localActivityNode.network = null;
    localActivityNode.putUserInfo = false;
    localActivityNode.contentUrl = paramGameBean.url;
    localActivityNode.hasShared = paramGameBean.hasShared;
    pushControllerByProperAnimation(new GroupWebViewController(getContext(), new GroupWebView(getContext(), paramGameBean.url, false, true), localActivityNode));
  }

  public boolean redirectToActivityViewByNode(Node paramNode)
  {
    boolean bool = true;
    if (paramNode == null)
      bool = false;
    ActivityNode localActivityNode;
    do
    {
      return bool;
      if (!paramNode.nodeName.equalsIgnoreCase("activity"))
        break;
      localActivityNode = (ActivityNode)paramNode;
      if ((localActivityNode.channelId != 0) && (localActivityNode.categoryId != 0) && (localActivityNode.channelId != 0) && (localActivityNode.categoryId != 0))
      {
        redirectToPlayView(localActivityNode.categoryId, localActivityNode.channelId, 0);
        return true;
      }
    }
    while (localActivityNode.categoryId != 0);
    if ((localActivityNode.useLocalWebview) && (localActivityNode.contentUrl != null))
    {
      paramNode = new Intent("android.intent.action.VIEW", Uri.parse(localActivityNode.contentUrl));
      InfoManager.getInstance().getContext().startActivity(paramNode);
      return true;
    }
    MobclickAgent.onEvent(this.mContext, "openActivity", localActivityNode.name);
    if (localActivityNode.contentUrl != null)
    {
      if ((localActivityNode.network != null) && (!localActivityNode.network.equalsIgnoreCase("all")) && (!localActivityNode.network.equalsIgnoreCase("")) && (this.mContext != null))
        Toast.makeText(this.mContext, "亲，该活动需要在" + localActivityNode.network + "网络下访问。", 1).show();
      paramNode = new GroupWebViewController(getContext(), new GroupWebView(getContext(), localActivityNode.contentUrl, localActivityNode.putUserInfo, false), localActivityNode);
      paramNode.config("setTitle", localActivityNode.titleIconUrl);
    }
    while (true)
    {
      pushControllerByProperAnimation(paramNode);
      return false;
      paramNode = new GroupWebViewController(getContext(), new GroupWebView(getContext(), "http://qingting.fm", false, false), null);
      paramNode.config("setTitle", localActivityNode.titleIconUrl);
    }
  }

  public void redirectToActiviyByUrl(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null)
      return;
    ActivityNode localActivityNode = new ActivityNode();
    localActivityNode.id = 1;
    localActivityNode.name = "蜻蜓fm";
    if ((paramString2 != null) && (!paramString2.equalsIgnoreCase("")))
      localActivityNode.name = paramString2;
    localActivityNode.type = "1";
    localActivityNode.updatetime = 25200;
    localActivityNode.infoUrl = null;
    localActivityNode.infoTitle = "";
    localActivityNode.desc = "有声世界,无限精彩";
    localActivityNode.titleIconUrl = null;
    localActivityNode.network = null;
    localActivityNode.putUserInfo = false;
    localActivityNode.contentUrl = paramString1;
    localActivityNode.hasShared = paramBoolean;
    pushControllerByProperAnimation(new GroupWebViewController(getContext(), new GroupWebView(getContext(), paramString1, false, true), localActivityNode));
  }

  public void redirectToAddAlarmView(Node paramNode)
  {
    if (paramNode == null)
      return;
    QTMSGManage.getInstance().sendStatistcsMessage("alarm_enter", "3dots");
    AlarmSettingController localAlarmSettingController = new AlarmSettingController(getContext());
    if (paramNode.nodeName.equalsIgnoreCase("channel"))
      localAlarmSettingController.config("addalarmbyChannel", paramNode);
    while (true)
    {
      pushControllerByProperAnimation(localAlarmSettingController);
      return;
      if (!paramNode.nodeName.equalsIgnoreCase("program"))
        break;
      localAlarmSettingController.config("addalarmbyprogram", paramNode);
    }
  }

  public void redirectToAddAlarmViewByRemind()
  {
    int j = 0;
    Object localObject1 = this.navigationController.getLastViewController();
    if ((localObject1 != null) && (((ViewController)localObject1).controllerName.equalsIgnoreCase("alarmsetting")))
      return;
    localObject1 = this.navigationController.getAllControllers().iterator();
    int i = 0;
    if (((Iterator)localObject1).hasNext())
      if (((ViewController)((Iterator)localObject1).next()).controllerName.equalsIgnoreCase("alarmsetting"))
      {
        j = 1;
        localObject1 = (AlarmSettingController)this.navigationController.removeController(i);
      }
    for (i = j; ; i = j)
    {
      Object localObject2;
      if (i != 0)
      {
        localObject2 = localObject1;
        if (localObject1 != null);
      }
      else
      {
        localObject2 = new AlarmSettingController(getContext());
      }
      ((AlarmSettingController)localObject2).config("addalarm", null);
      pushControllerByProperAnimation((ViewController)localObject2);
      return;
      i += 1;
      break;
      localObject1 = null;
    }
  }

  public void redirectToAddAlarmViewByRingtone(BroadcasterNode paramBroadcasterNode, Node paramNode)
  {
    if (paramBroadcasterNode == null)
      return;
    QTMSGManage.getInstance().sendStatistcsMessage("alarm_enter", "broadcaster");
    AlarmSettingController localAlarmSettingController = new AlarmSettingController(getContext());
    localAlarmSettingController.addAlarmByRingtone(paramBroadcasterNode, paramNode);
    pushControllerByProperAnimation(localAlarmSettingController);
  }

  public void redirectToBatchDownloadView(Node paramNode, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramNode == null);
    while (true)
    {
      return;
      if ((InfoManager.getInstance().forceLogin()) && (!CloudCenter.getInstance().isLogin(false)))
      {
        long l1 = SharedCfg.getInstance().getForceLogin();
        long l2 = DateUtil.getCurrentMillis();
        if (DateUtil.isDifferentDayMs(l1, l2))
        {
          EventDispacthManager.getInstance().dispatchAction("showLogin", null);
          SharedCfg.getInstance().setForceLogin(l2);
          return;
        }
      }
      if (paramNode.nodeName.equalsIgnoreCase("channel"))
        paramNode = (ChannelNode)paramNode;
      while (paramNode != null)
      {
        if (paramNode.channelType == 0);
        for (Object localObject = "batchdownload_tradition"; ; localObject = "batchdownload")
        {
          localObject = getController((String)localObject);
          if (paramBoolean1)
            ((ViewController)localObject).config("checkNew", null);
          if (paramBoolean2)
            ((ViewController)localObject).config("checkNow", null);
          ((ViewController)localObject).config("setData", paramNode);
          pushControllerByProperAnimation((ViewController)localObject);
          return;
          if (!paramNode.nodeName.equalsIgnoreCase("program"))
            break label215;
          if ((paramNode.parent != null) && (paramNode.parent.nodeName.equalsIgnoreCase("channel")))
          {
            paramNode = (ChannelNode)paramNode.parent;
            break;
          }
          paramNode = InfoManager.getInstance().findChannelNodeByRecommendDetail(paramNode);
          break;
        }
        label215: paramNode = null;
      }
    }
  }

  public void redirectToDownloadProgramsController(ChannelNode paramChannelNode)
  {
    ViewController localViewController = getController("downloadprogram");
    localViewController.config("setData", paramChannelNode);
    pushControllerByProperAnimation(localViewController);
  }

  public void redirectToHistoryView()
  {
    ViewController localViewController = this.navigationController.getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("playhistory")))
      return;
    localViewController = getController("playhistory");
    localViewController.config("setData", InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes());
    pushControllerByProperAnimation(localViewController);
  }

  public void redirectToLocalWebView(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null)
      return;
    MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "localwebview", paramString2);
    pushControllerByProperAnimation(new LocalWebViewController(getContext(), paramString1, paramString2, paramBoolean));
  }

  public void redirectToMyCollectionView()
  {
    ViewController localViewController = this.navigationController.getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("mycollection")))
      return;
    localViewController = getController("mycollection");
    localViewController.config("setData", null);
    pushControllerByProperAnimation(localViewController);
  }

  public void redirectToPlayView(int paramInt1, int paramInt2, int paramInt3)
  {
    openPlayController(paramInt1, paramInt2, 0, true, null);
  }

  public boolean redirectToPlayViewById(int paramInt1, int paramInt2, int paramInt3)
  {
    openPlayController(paramInt1, paramInt2, paramInt3, true, null);
    return false;
  }

  public boolean redirectToPlayViewById(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt3 == 0)
      return redirectToPlayViewById(paramInt1, paramInt2, paramInt4);
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(paramInt1, paramInt2, paramInt4, true, null);
    return true;
  }

  public boolean redirectToPlayViewById(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2)
  {
    if (paramInt3 == 0)
      return redirectToPlayViewById(paramInt1, paramInt2, paramInt4, paramInt5);
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(paramInt1, paramInt2, paramInt3, paramInt4, true);
    return true;
  }

  public boolean redirectToPlayViewById(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(paramInt1, paramInt2, 0, true, null);
    return true;
  }

  public boolean redirectToPlayViewByNode()
  {
    openPlayController(false, 0);
    return true;
  }

  public boolean redirectToPlayViewByNode(Node paramNode, boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramNode == null) || (this.mContext == null))
      bool1 = false;
    Object localObject;
    label343: 
    do
    {
      do
      {
        do
        {
          return bool1;
          if (paramNode.nodeName.equalsIgnoreCase("activity"))
          {
            redirectToActivityViewByNode(paramNode);
            return true;
          }
          if (paramNode.nodeName.equalsIgnoreCase("radiochannel"))
          {
            if (paramBoolean)
              PlayerAgent.getInstance().startFM((RadioChannelNode)paramNode);
            paramNode = ChannelHelper.getInstance().getChannel(((RadioChannelNode)paramNode).channelId, 0);
            if (paramNode != null)
              InfoManager.getInstance().root().setPlayingChannelNode(paramNode);
            openPlayController(false, 0);
            return true;
          }
          if (paramNode.nodeName.equalsIgnoreCase("channel"))
          {
            ChannelHelper.getInstance().setChannel((ChannelNode)paramNode, false);
            InfoManager.getInstance().root().setPlayingChannelNode((ChannelNode)paramNode);
            if (paramBoolean)
              PlayerAgent.getInstance().play((ChannelNode)paramNode);
            localObject = InfoManager.getInstance().h5Channel(((ChannelNode)paramNode).channelId);
            if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
            {
              redirectToActiviyByUrl((String)localObject, ((ChannelNode)paramNode).title, false);
              return true;
            }
            openPlayController(paramBoolean, 0);
            return true;
          }
          if (paramNode.nodeName.equalsIgnoreCase("program"))
          {
            if (paramBoolean)
              PlayerAgent.getInstance().play(paramNode);
            openPlayController((ProgramNode)paramNode, false);
            return true;
          }
          bool1 = bool2;
        }
        while (!paramNode.nodeName.equalsIgnoreCase("playhistory"));
        localObject = ((PlayHistoryNode)paramNode).playNode;
        if (localObject == null)
          break;
        if (((Node)localObject).nodeName.equalsIgnoreCase("program"))
        {
          if (!((ProgramNode)localObject).isLiveProgram())
            break label343;
          if (paramBoolean)
          {
            paramNode = ChannelHelper.getInstance().getChannel(((ProgramNode)localObject).channelId, 0);
            PlayerAgent.getInstance().play(paramNode);
          }
        }
        while (((Node)localObject).nodeName.equalsIgnoreCase("program"))
        {
          openPlayController((ProgramNode)localObject, false);
          return true;
          if (paramBoolean)
            PlayerAgent.getInstance().play((Node)localObject);
        }
        bool1 = bool2;
      }
      while (!((Node)localObject).nodeName.equalsIgnoreCase("channel"));
      ChannelHelper.getInstance().setChannel((ChannelNode)localObject, false);
      InfoManager.getInstance().root().setPlayingChannelNode((ChannelNode)localObject);
      openPlayController(false, 0);
      return true;
      ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(((PlayHistoryNode)paramNode).channelId, 0);
      localObject = localChannelNode;
      if (localChannelNode == null)
      {
        localChannelNode = ChannelHelper.getInstance().getChannel(((PlayHistoryNode)paramNode).channelId, 1);
        localObject = localChannelNode;
        if (localChannelNode == null)
          localObject = ChannelHelper.getInstance().getFakeVirtualChannel(((PlayHistoryNode)paramNode).channelId, ((PlayHistoryNode)paramNode).categoryId, ((PlayHistoryNode)paramNode).channelName);
      }
      bool1 = bool2;
    }
    while (localObject == null);
    openPlayController(((ChannelNode)localObject).categoryId, ((ChannelNode)localObject).channelId, ((ChannelNode)localObject).channelType, true, ((ChannelNode)localObject).title);
    return true;
  }

  public boolean redirectToPlayViewByPlayList(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt3 == 0)
      return redirectToPlayViewById(paramInt1, paramInt2, paramInt4);
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    openPlayController(paramInt1, paramInt2, paramInt4, true, null);
    return true;
  }

  public boolean redirectToPlayViewByType(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (InfoManager.getInstance().root().mContentCategory == null)
      InfoManager.getInstance().initInfoTreeFromDB();
    if ((InfoManager.getInstance().root().mContentCategory == null) || ((paramInt4 == 0) && (InfoManager.getInstance().root().mContentCategory.mLiveNode == null)))
      return false;
    openPlayController(paramInt1, paramInt2, paramInt3, paramInt5, paramBoolean2);
    return false;
  }

  public boolean redirectToRecommendCategoryViewByCatid(int paramInt)
  {
    return false;
  }

  public boolean redirectToScheduleViewById(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3)
  {
    return false;
  }

  public void redirectToSearchView(String paramString, boolean paramBoolean)
  {
    ViewController localViewController = this.navigationController.getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("search")))
    {
      localViewController.config("setData", paramString);
      return;
    }
    localViewController = getController("search");
    localViewController.config("setData", paramString);
    if (paramBoolean)
    {
      pushSearchControllerSpecial(localViewController);
      return;
    }
    pushControllerByProperAnimation(localViewController);
  }

  public void redirectToSearchView(boolean paramBoolean)
  {
    redirectToSearchView(null, paramBoolean);
  }

  public void redirectToUsersProfileView(UserInfo paramUserInfo)
  {
  }

  public boolean redirectToViewBySearchNode(SearchItemNode paramSearchItemNode)
  {
    if (paramSearchItemNode == null)
      return false;
    PlayerAgent.getInstance().addPlaySource(5);
    Object localObject2;
    Object localObject1;
    if (paramSearchItemNode.groupType == 1)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("search_clickresult", "program");
      localObject2 = ChannelHelper.getInstance().getChannel(paramSearchItemNode.channelId, paramSearchItemNode.channelType);
      localObject1 = localObject2;
      if (localObject2 == null)
        localObject1 = ChannelHelper.getInstance().getFakeChannel(paramSearchItemNode.channelId, paramSearchItemNode.categoryId, paramSearchItemNode.cName, paramSearchItemNode.channelType);
      getInstance().setChannelSource(0);
      openChannelDetailController((Node)localObject1, false, true);
      PlayerAgent.getInstance().playAndLoadData(paramSearchItemNode.categoryId, paramSearchItemNode.channelId, paramSearchItemNode.programId, paramSearchItemNode.channelType, paramSearchItemNode.name);
    }
    while (true)
    {
      paramSearchItemNode = QTLogger.getInstance().buildSearchedClickLog(paramSearchItemNode);
      if (paramSearchItemNode == null)
        break;
      LogModule.getInstance().send("search_click_v6", paramSearchItemNode);
      return false;
      if (paramSearchItemNode.groupType == 0)
      {
        localObject1 = InfoManager.getInstance().h5Channel(paramSearchItemNode.channelId);
        if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
        {
          localObject2 = paramSearchItemNode.name;
          getInstance().redirectToActiviyByUrl((String)localObject1, (String)localObject2, false);
          localObject1 = ChannelHelper.getInstance().getChannel(paramSearchItemNode.channelId, 0);
          if (localObject1 != null)
            PlayerAgent.getInstance().play((Node)localObject1);
        }
        while (true)
        {
          QTMSGManage.getInstance().sendStatistcsMessage("search_clickresult", "channel");
          break;
          openPlayController(paramSearchItemNode.categoryId, paramSearchItemNode.channelId, paramSearchItemNode.programId, paramSearchItemNode.channelType, paramSearchItemNode.name, true);
        }
      }
      if (paramSearchItemNode.groupType == 2)
      {
        localObject2 = ChannelHelper.getInstance().getChannel(paramSearchItemNode.channelId, paramSearchItemNode.channelType);
        localObject1 = localObject2;
        if (localObject2 == null)
          localObject1 = ChannelHelper.getInstance().getFakeChannel(paramSearchItemNode.channelId, paramSearchItemNode.categoryId, paramSearchItemNode.cName, paramSearchItemNode.channelType);
        getInstance().setChannelSource(0);
        openChannelDetailController((Node)localObject1, false, true);
        QTMSGManage.getInstance().sendStatistcsMessage("search_clickresult", "channel");
      }
      else if (paramSearchItemNode.groupType == 3)
      {
        openPodcasterInfoController(PodcasterHelper.getInstance().getPodcaster(paramSearchItemNode.podcasterId));
        QTMSGManage.getInstance().sendStatistcsMessage("search_clickresult", "podcaster");
      }
    }
  }

  public void redirectToWemartByUrl(String paramString1, String paramString2, boolean paramBoolean)
  {
    if (paramString1 == null)
      return;
    ActivityNode localActivityNode = new ActivityNode();
    localActivityNode.id = 1;
    localActivityNode.name = "蜻蜓商城";
    if ((paramString2 != null) && (!paramString2.equalsIgnoreCase("")))
      localActivityNode.name = paramString2;
    localActivityNode.type = "1";
    localActivityNode.updatetime = 25200;
    localActivityNode.infoUrl = null;
    localActivityNode.infoTitle = "";
    localActivityNode.desc = "天天特价停不下来";
    localActivityNode.titleIconUrl = null;
    localActivityNode.network = null;
    localActivityNode.putUserInfo = false;
    localActivityNode.contentUrl = paramString1;
    localActivityNode.hasShared = paramBoolean;
    pushControllerByProperAnimation(new WemartController(getContext(), new WemartWebView(getContext(), paramString1, false), localActivityNode));
  }

  public void redirectToWoQtView()
  {
    int j = 0;
    Object localObject1 = this.navigationController.getLastViewController();
    if ((localObject1 != null) && (((ViewController)localObject1).controllerName.equalsIgnoreCase("wo")))
      return;
    localObject1 = this.navigationController.getAllControllers().iterator();
    int i = 0;
    if (((Iterator)localObject1).hasNext())
      if (((ViewController)((Iterator)localObject1).next()).controllerName.equalsIgnoreCase("wo"))
      {
        j = 1;
        localObject1 = (WoQtController)this.navigationController.removeController(i);
      }
    for (i = j; ; i = j)
    {
      Object localObject2;
      if (i != 0)
      {
        localObject2 = localObject1;
        if (localObject1 != null);
      }
      else
      {
        localObject2 = new WoQtController(getContext());
      }
      ((WoQtController)localObject2).config("setData", null);
      pushControllerByProperAnimation((ViewController)localObject2);
      return;
      i += 1;
      break;
      localObject1 = null;
    }
  }

  public void setChannelSource(int paramInt)
  {
    this.mChannelSource = paramInt;
  }

  public void setContext(Context paramContext)
  {
    this.mContext = paramContext;
    if (this.mContext != null);
  }

  public void setNavigationController(NavigationController paramNavigationController)
  {
    this.navigationController = paramNavigationController;
  }

  public void updateRecentPlayOnChannelDetail(String paramString)
  {
    ((ChannelDetailController)getController("channeldetail")).config(paramString, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.ControllerManager
 * JD-Core Version:    0.6.2
 */