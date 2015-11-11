package fm.qingting.qtradio.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.controller.INavigationEventListener;
import fm.qingting.framework.controller.NavigationController;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.manager.EventDispacthManager.IActionEventHandler;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.controller.FrontPageController;
import fm.qingting.qtradio.controller.virtual.UploadVoiceController;
import fm.qingting.qtradio.dongruan.DongRuanInstance;
import fm.qingting.qtradio.dongruan.DongRuanInstance.OnMaskListener;
import fm.qingting.qtradio.dongruan.DongRuanMaskView;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.manager.CollectionRemindManager;
import fm.qingting.qtradio.manager.EducationManager;
import fm.qingting.qtradio.manager.LinkManager;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.manager.advertisement.AdvertisementManage;
import fm.qingting.qtradio.manager.advertisement.UMengLogger;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.NavigationSettingController;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareObjectNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.model.advertisement.QTCoupon;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.share.ShareUtil;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.view.education.shareguide.ShareGuideView;
import fm.qingting.qtradio.view.popviews.AlertParam.Builder;
import fm.qingting.qtradio.view.popviews.AlertParam.OnButtonClickListener;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.RecommendStatisticsUtil;
import fm.qingting.utils.ScreenConfiguration;
import java.util.List;

public class MainView extends ViewGroupViewImpl
  implements IEventHandler, EventDispacthManager.IActionEventHandler, INavigationEventListener
{
  private static final int BUBBLE_SCALE_BOTTOMGAP = 2;
  private static final int BUBBLE_SCALE_FILL = 0;
  private static final int BUBBLE_SCALE_TOPGAP = 1;
  public static boolean sDoubleBackQuit = false;
  private final ViewLayout bottomLayout = this.standardLayout.createChildLT(720, 93, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private FrameLayout container;
  private int mBubbleScaleType = 0;
  private QtBubbleView mBubbleView;
  private long mLastBackTime = 0L;
  private String mLastViewName = "";
  private DongRuanMaskView mMaskView;
  private FrontPageController mRootController;
  private ShareGuideView mShareGuideView;
  private ShareObjectNode mShareObject = new ShareObjectNode();
  private boolean mShowingShare = false;
  private final ViewLayout naviLayout = this.standardLayout.createChildLT(720, 86, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NavigationController navigationController;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private int viewHeight = 1200;
  private int viewWidth = 720;

  public MainView(Context paramContext)
  {
    super(paramContext);
    EventDispacthManager.getInstance().addListener(this);
    buildViews();
    OnlineUpdateHelper.getInstance();
    paramContext = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    if ((paramContext != null) && (paramContext.size() > 1) && (InfoManager.getInstance().getTopHistory()))
      EventDispacthManager.getInstance().dispatchAction("topPlayHistory", null);
  }

  private void buildViews()
  {
    this.navigationController = new NavigationController(getContext());
    this.navigationController.setNavigationSetting(new NavigationSettingController());
    this.navigationController.setNavigationEventListener(this);
    this.container = this.navigationController.getViewContainer();
    addView(this.container);
    this.mRootController = new FrontPageController(getContext());
    this.mRootController.config("setData", null);
    this.navigationController.setRootController(this.mRootController, null);
    ControllerManager.getInstance().setContext(getContext());
    ControllerManager.getInstance().setNavigationController(this.navigationController);
    if (InfoManager.getInstance().enableRecommendShare())
    {
      this.mShareGuideView = new ShareGuideView(getContext());
      this.mShareGuideView.setEventHandler(this);
      addView(this.mShareGuideView);
      this.mShowingShare = true;
      MobclickAgent.onEvent(getContext(), "showShare", "addShare");
    }
    this.mBubbleView = new QtBubbleView(getContext());
    this.mBubbleView.setEventHandler(this);
    addView(this.mBubbleView);
    this.mBubbleView.setVisibility(8);
    EducationManager.getInstance().init(getContext());
    sDoubleBackQuit = SharedCfg.getInstance().getDoubleBackToQuit();
    this.mMaskView = new DongRuanMaskView(getContext());
    this.mMaskView.setVisibility(4);
    addView(this.mMaskView);
    DongRuanInstance.getInstance().setOnMaskListener(new DongRuanInstance.OnMaskListener()
    {
      public void onMask(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          InfoManager.getInstance().setConnectDongRuan(true);
          QTMSGManage.getInstance().sendStatistcsMessage("Dongruan", "connect");
          MainView.this.bringChildToFront(MainView.this.mMaskView);
          if (EducationManager.getInstance().isShown())
            EducationManager.getInstance().cancelTip();
          MainView.this.mMaskView.setVisibility(0);
          return;
        }
        MainView.this.mMaskView.setVisibility(4);
        QTMSGManage.getInstance().sendStatistcsMessage("Dongruan", "disconnect");
      }
    });
  }

  private void cancelBubble()
  {
    cancelBubble(true);
  }

  @TargetApi(11)
  private void cancelBubble(boolean paramBoolean)
  {
    EventDispacthManager.getInstance().dispatchAction("hideMiniplayerTrangle", null);
    if ((paramBoolean) && (this.mBubbleView.isAnimationAvailable()))
    {
      startDimBackAnimation();
      this.mBubbleView.hide();
    }
    while (true)
    {
      InfoManager.getInstance().setBindRecommendShare(false);
      return;
      this.mBubbleView.clearAnimation();
      this.mBubbleView.onViewHidden();
      this.mBubbleView.setVisibility(8);
      switch (this.mBubbleView.getBubbleType())
      {
      default:
        break;
      case 7:
        changePlayViewState(true);
        break;
      case 13:
        radiocastMessage("qt_action_stopservice");
        break;
      case 18:
        resetFilterStateIfNeed();
      }
    }
  }

  private void changePlayViewState(boolean paramBoolean)
  {
  }

  private int getBubbleHeight()
  {
    int i;
    if (this.mBubbleScaleType == 1)
    {
      i = getBubbleTopOffset();
      if (this.mBubbleScaleType != 2)
        break label46;
    }
    label46: for (int j = this.standardLayout.height - this.bottomLayout.height; ; j = this.standardLayout.height)
    {
      return j - i;
      i = 0;
      break;
    }
  }

  private int getBubbleTopOffset()
  {
    if (QtApiLevelManager.isApiLevelSupported(19))
      return ScreenConfiguration.getNaviHeight() + NaviUtil.getStatusBarHeight(getResources());
    return 0;
  }

  private void handleBackAction()
  {
    if (isBubbleShowing())
    {
      cancelBubble();
      return;
    }
    boolean bool = ((Boolean)getValue("isRoot", null)).booleanValue();
    String str = (String)getValue("topControllerName", null);
    if (bool)
    {
      if (sDoubleBackQuit)
      {
        long l = SystemClock.uptimeMillis();
        if (l - this.mLastBackTime < 3000L)
        {
          dispatchActionEvent("immediateQuit", null);
          return;
        }
        Toast.makeText(getContext(), "再按一次退出", 0).show();
        this.mLastBackTime = l;
        return;
      }
      dispatchActionEvent("showQuitAlert", null);
      return;
    }
    if ((str != null) && (str.equalsIgnoreCase("uploadvoice")))
    {
      ((UploadVoiceController)this.navigationController.getLastViewController()).performStop(true);
      return;
    }
    update("performPop", null);
  }

  private void handleOnPause()
  {
    String str = (String)getValue("topControllerName", null);
    if ((str != null) && (str.equalsIgnoreCase("uploadvoice")))
      ((UploadVoiceController)this.navigationController.getLastViewController()).performStop(false);
  }

  private boolean isBubbleShowing()
  {
    return this.mBubbleView.isShown();
  }

  private void layoutBubble(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      requestLayout();
      return;
    }
    int i;
    if (this.mBubbleScaleType == 1)
    {
      i = getBubbleTopOffset();
      if (this.mBubbleScaleType != 2)
        break label66;
    }
    label66: for (int j = this.standardLayout.height - this.bottomLayout.height; ; j = this.standardLayout.height)
    {
      this.mBubbleView.layout(0, i, this.viewWidth, j);
      return;
      i = 0;
      break;
    }
  }

  private void markPageChanged(ViewController paramViewController)
  {
    if (paramViewController != null)
    {
      if (!this.mLastViewName.equalsIgnoreCase(""))
        MobclickAgent.onPageEnd(this.mLastViewName);
      this.mLastViewName = paramViewController.controllerName;
      MobclickAgent.onPageStart(this.mLastViewName);
    }
  }

  private void popController()
  {
    ControllerManager.getInstance().popLastController();
  }

  private void radiocastMessage(String paramString)
  {
    if (getContext() != null)
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("qtactionkey", paramString);
      localIntent.setAction("fm.qingting.radio.qt_ui_toservice");
      getContext().sendBroadcast(localIntent);
    }
  }

  private void redirectToGroupChat(GroupInfo paramGroupInfo)
  {
    if (CloudCenter.getInstance().isLogin(false))
    {
      InfoManager.getInstance().getUserProfile().followGroup(paramGroupInfo.groupId);
      ControllerManager.getInstance().openImChatController(paramGroupInfo);
      return;
    }
    ControllerManager.getInstance().openImGroupProfileController(paramGroupInfo.groupId);
  }

  private void resetFilterStateIfNeed()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if ((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("vcacc")))
      localViewController.config("resetFilterState", null);
  }

  private void setFlagOnTopViewChanged(ViewController paramViewController)
  {
    if (paramViewController == null);
    while (true)
    {
      return;
      if (paramViewController.controllerName.equalsIgnoreCase("frontpage"))
      {
        paramViewController = paramViewController.getValue("currentIndex", null);
        if (paramViewController != null)
          if (((Integer)paramViewController).intValue() == 0)
            RecommendStatisticsUtil.INSTANCE.resume();
      }
      while (EducationManager.getInstance().isShown())
      {
        EducationManager.getInstance().cancelTip();
        return;
        RecommendStatisticsUtil.INSTANCE.pause();
        continue;
        RecommendStatisticsUtil.INSTANCE.pause();
        continue;
        RecommendStatisticsUtil.INSTANCE.pause();
      }
    }
  }

  private void setFlagOnTopViewChanged(boolean paramBoolean)
  {
    ViewController localViewController = this.navigationController.getLastViewController();
    if (localViewController == null);
    do
    {
      return;
      if (localViewController.controllerName.equalsIgnoreCase("frontpage"))
      {
        Object localObject = localViewController.getValue("currentIndex", null);
        if (localObject != null)
          if (((Integer)localObject).intValue() == 0)
            RecommendStatisticsUtil.INSTANCE.resume();
        while (true)
        {
          localViewController.config("refreshView", null);
          return;
          RecommendStatisticsUtil.INSTANCE.pause();
          continue;
          RecommendStatisticsUtil.INSTANCE.pause();
        }
      }
    }
    while ((!localViewController.controllerName.equalsIgnoreCase("channeldetail")) || (!paramBoolean));
    localViewController.config("syncdata", null);
  }

  private void share(Node paramNode)
  {
    ShareUtil.shareToPlatform(getContext(), paramNode, 0);
  }

  private void shareSdkShare(ShareObjectNode paramShareObjectNode)
  {
    if (paramShareObjectNode == null)
      return;
    Object localObject = paramShareObjectNode.node;
    if ((localObject instanceof ChannelNode))
      localObject = ((ChannelNode)localObject).getApproximativeThumb();
    while (true)
    {
      if (localObject != null)
        ImageLoader.getInstance(getContext()).getImage((String)localObject, 200, 200);
      showBubble(4, paramShareObjectNode);
      QTMSGManage.getInstance().sendStatistcsMessage("SharePopView");
      return;
      if ((localObject instanceof ProgramNode))
      {
        localObject = ChannelHelper.getInstance().getChannel((ProgramNode)localObject);
        if (localObject != null)
          localObject = ((ChannelNode)localObject).getApproximativeThumb();
      }
      else if ((localObject instanceof SpecialTopicNode))
      {
        localObject = ((SpecialTopicNode)localObject).thumb;
      }
      else if ((localObject instanceof UserInfo))
      {
        localObject = ((UserInfo)localObject).snsInfo.sns_avatar;
      }
      else if ((localObject instanceof ActivityNode))
      {
        localObject = ((ActivityNode)localObject).infoUrl;
      }
      else
      {
        localObject = null;
      }
    }
  }

  private void showAlert(String paramString)
  {
    if (paramString == null)
      return;
    update("showAlert", new AlertParam.Builder().setMessage(paramString).addButton("继续播放").addButton("停止播放").addForbidBox().setListener(new AlertParam.OnButtonClickListener()
    {
      public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        switch (paramAnonymousInt)
        {
        default:
        case 0:
        case 1:
        }
        do
        {
          do
          {
            return;
            MainView.this.update("cancelBubble", null);
            PlayerAgent.getInstance().play();
          }
          while (!paramAnonymousBoolean);
          InfoManager.getInstance().setMobileAlert(false);
          InfoManager.getInstance().setMobilePlay(true);
          return;
          MainView.this.update("cancelBubble", null);
          PlayerAgent.getInstance().stop();
        }
        while (!paramAnonymousBoolean);
        InfoManager.getInstance().setMobileAlert(false);
        InfoManager.getInstance().setMobilePlay(false);
      }
    }).create());
  }

  private void showBubble(int paramInt, Object paramObject)
  {
    LinkManager.cancelLinkIfExists(getContext());
    this.mBubbleView.update("dimin", null);
    int i;
    switch (paramInt)
    {
    default:
      i = 0;
    case 7:
    case 24:
    case 18:
    }
    while (true)
    {
      if (i != this.mBubbleScaleType)
      {
        this.mBubbleScaleType = i;
        layoutBubble(true);
      }
      this.mBubbleView.setViewByType(paramInt, paramObject);
      this.mBubbleView.setVisibility(0);
      return;
      i = 2;
      changePlayViewState(false);
      continue;
      i = 1;
    }
  }

  private void showDownloadAlert(String paramString)
  {
    if (paramString == null)
      return;
    update("showAlert", new AlertParam.Builder().setMessage(paramString).addButton("设置").addButton("下次再说").setListener(new AlertParam.OnButtonClickListener()
    {
      public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
      {
        switch (paramAnonymousInt)
        {
        default:
          return;
        case 0:
          MainView.this.update("cancelBubble", null);
          ControllerManager.getInstance().openSettingController();
          return;
        case 1:
        }
        MainView.this.update("cancelBubble", null);
      }
    }).create());
  }

  @Deprecated
  private void showSchedule()
  {
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localChannelNode == null);
    while (!localChannelNode.nodeName.equalsIgnoreCase("channel"))
      return;
    localChannelNode = (ChannelNode)localChannelNode;
    if (localChannelNode.hasEmptyProgramSchedule())
    {
      Toast.makeText(getContext(), "节目单正在加载中", 0).show();
      return;
    }
    showBubble(7, localChannelNode);
  }

  private void startDimBackAnimation()
  {
    this.mBubbleView.update("dimout", null);
  }

  public void destroy()
  {
    if (this.navigationController != null)
    {
      this.navigationController.destroy();
      this.navigationController = null;
    }
    if (this.mRootController != null)
    {
      this.mRootController.destroy();
      this.mRootController = null;
    }
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    int i = paramKeyEvent.getKeyCode();
    if (this.navigationController.getLastViewController().getView().getView().dispatchKeyEvent(paramKeyEvent))
      return true;
    if ((i == 24) || (i == 25))
      return false;
    if ((i == 82) && (paramKeyEvent.getAction() == 1))
    {
      ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
      if (this.mShowingShare)
        return true;
      if (isBubbleShowing())
        cancelBubble();
      while (true)
      {
        return true;
        if (EducationManager.getInstance().isShown())
          EducationManager.getInstance().cancelTip();
        showBubble(8, null);
      }
    }
    if (i == 4)
    {
      if (paramKeyEvent.getAction() == 1)
      {
        if (EducationManager.getInstance().isShown())
          EducationManager.getInstance().cancelTip();
        handleBackAction();
        return true;
      }
    }
    else if (i == 84)
      return true;
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyEventPreIme(KeyEvent paramKeyEvent)
  {
    return super.dispatchKeyEventPreIme(paramKeyEvent);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("isRoot"))
    {
      if (this.navigationController.getLastViewController() == this.mRootController);
      for (boolean bool = true; ; bool = false)
        return Boolean.valueOf(bool);
    }
    if ((paramString.equalsIgnoreCase("topControllerName")) && (this.navigationController.getLastViewController() != null))
      return this.navigationController.getLastViewController().controllerName;
    return null;
  }

  public void onAction(final String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("voice_view"))
      showBubble(15, null);
    do
    {
      do
      {
        do
        {
          Object localObject;
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    while (true)
                    {
                      return;
                      if (paramString.equalsIgnoreCase("scanFm"))
                      {
                        showBubble(11, null);
                        return;
                      }
                      if (paramString.equalsIgnoreCase("scanUpdate"))
                      {
                        if ((isBubbleShowing()) && (this.mBubbleView.getBubbleType() == 11))
                          showBubble(11, paramObject);
                      }
                      else
                      {
                        if (paramString.equalsIgnoreCase("scanCancel"))
                        {
                          cancelBubble();
                          return;
                        }
                        if (paramString.equalsIgnoreCase("showFeedbackPop"))
                        {
                          showBubble(10, paramObject);
                          return;
                        }
                        if (paramString.equalsIgnoreCase("showRingtoneLoadingView"))
                        {
                          showBubble(9, null);
                          return;
                        }
                        if (paramString.equalsIgnoreCase("ringtoneLoadComplete"))
                        {
                          showBubble(9, Boolean.valueOf(true));
                          return;
                        }
                        if (paramString.equalsIgnoreCase("ringtoneLoadFailed"))
                        {
                          showBubble(9, Boolean.valueOf(false));
                          return;
                        }
                        if (paramString.equalsIgnoreCase("showAlarmRemind"))
                        {
                          showBubble(5, new AlertParam.Builder().addButton("不再提醒").addButton("设定闹钟").setMessage("设定自己喜欢的广播作为闹钟，在熟悉的声音中醒来。").setListener(new AlertParam.OnButtonClickListener()
                          {
                            public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
                            {
                              switch (paramAnonymousInt)
                              {
                              default:
                                return;
                              case 0:
                                MainView.this.cancelBubble();
                                return;
                              case 1:
                              }
                              MainView.this.cancelBubble();
                              ControllerManager.getInstance().redirectToAddAlarmViewByRemind();
                            }
                          }).create());
                          return;
                        }
                        if (paramString.equalsIgnoreCase("showGroupRemind"))
                        {
                          paramString = (GroupInfo)paramObject;
                          if (paramString != null)
                            showBubble(5, new AlertParam.Builder().addButton("下次再说").addButton("加入群聊").setMessage(InfoManager.getInstance().getAddGroupSlogan()).setListener(new AlertParam.OnButtonClickListener()
                            {
                              public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
                              {
                                switch (paramAnonymousInt)
                                {
                                default:
                                  return;
                                case 0:
                                  MainView.this.cancelBubble();
                                  return;
                                case 1:
                                }
                                MainView.this.cancelBubble();
                                MainView.this.redirectToGroupChat(paramString);
                              }
                            }).create());
                        }
                        else
                        {
                          if (paramString.equalsIgnoreCase("showWoQtAlert"))
                          {
                            showBubble(5, new AlertParam.Builder().addButton("取消").addButton("去绑定").setMessage("如果您曾开通过蜻蜓FM-包流量畅听，如您想要继续使用，需要您重新绑定手机号码。").setListener(new AlertParam.OnButtonClickListener()
                            {
                              public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
                              {
                                switch (paramAnonymousInt)
                                {
                                default:
                                  return;
                                case 0:
                                  MainView.this.cancelBubble();
                                  return;
                                case 1:
                                }
                                MainView.this.cancelBubble();
                                ControllerManager.getInstance().redirectToWoQtView();
                              }
                            }).create());
                            return;
                          }
                          if (paramString.equalsIgnoreCase("showLogin"))
                          {
                            showBubble(12, paramObject);
                            return;
                          }
                          if (paramString.equalsIgnoreCase("showSchedule"))
                          {
                            if ((isBubbleShowing()) && ((this.mBubbleView.getBubbleType() == 7) || (this.mBubbleView.getBubbleType() == 24)))
                            {
                              cancelBubble();
                              return;
                            }
                            showSchedule();
                            return;
                          }
                          if (paramString.equalsIgnoreCase("toPlayView"))
                          {
                            if ((isBubbleShowing()) && ((this.mBubbleView.getBubbleType() == 7) || (this.mBubbleView.getBubbleType() == 24)))
                            {
                              cancelBubble();
                              return;
                            }
                            ControllerManager.getInstance().redirectToPlayViewByNode();
                            return;
                          }
                          if (paramString.equalsIgnoreCase("hideSchedule"))
                          {
                            if ((isBubbleShowing()) && ((this.mBubbleView.getBubbleType() == 7) || (this.mBubbleView.getBubbleType() == 24)))
                              cancelBubble();
                          }
                          else
                          {
                            if (paramString.equalsIgnoreCase("showoperatepop"))
                            {
                              showBubble(6, paramObject);
                              return;
                            }
                            if (paramString.equalsIgnoreCase("shareChoose"))
                            {
                              paramString = new ShareObjectNode();
                              paramString.node = ((Node)paramObject);
                              shareSdkShare(paramString);
                              return;
                            }
                            if (paramString.equalsIgnoreCase("shareToPlatform"))
                            {
                              cancelBubble();
                              share((Node)paramObject);
                              return;
                            }
                            if (paramString.equalsIgnoreCase("shareToMessage"))
                            {
                              cancelBubble();
                              showBubble(32, paramObject);
                              return;
                            }
                            if (paramString.equalsIgnoreCase("showToast"))
                            {
                              if (paramObject != null)
                              {
                                paramString = (String)paramObject;
                                try
                                {
                                  Toast.makeText(getContext(), paramString, 0).show();
                                  return;
                                }
                                catch (OutOfMemoryError paramString)
                                {
                                  paramString.printStackTrace();
                                  return;
                                }
                              }
                            }
                            else
                            {
                              if (paramString.equalsIgnoreCase("refreshUploadView"))
                              {
                                paramString = (String)paramObject;
                                if ((paramString != null) && (paramString.length() > 0))
                                  Toast.makeText(getContext(), paramString, 0).show();
                                this.navigationController.getLastViewController().config("refreshUploadView", null);
                                return;
                              }
                              if (paramString.equalsIgnoreCase("alertSetting"))
                              {
                                if (paramObject != null)
                                  showAlert((String)paramObject);
                              }
                              else if (paramString.equalsIgnoreCase("alertSettingdownload"))
                              {
                                if (paramObject != null)
                                  showDownloadAlert((String)paramObject);
                              }
                              else
                              {
                                if (paramString.equalsIgnoreCase("cancelPop"))
                                {
                                  cancelBubble();
                                  return;
                                }
                                if (paramString.equalsIgnoreCase("showAlert"))
                                {
                                  update(paramString, paramObject);
                                  return;
                                }
                                if (!paramString.equalsIgnoreCase("showEducationFav"))
                                  break;
                                paramString = this.navigationController.getLastViewController();
                                if ((!paramString.controllerName.equalsIgnoreCase("imchat")) && (!paramString.controllerName.equalsIgnoreCase("chatroom")) && (!isBubbleShowing()))
                                {
                                  if ((paramObject != null) && ((paramObject instanceof ChannelNode)));
                                  for (paramString = (ChannelNode)paramObject; (paramString != null) && (!paramString.isDownloadChannel()) && (!isBubbleShowing()) && (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(paramString)) && (CollectionRemindManager.shoudShowRemind(getContext(), paramString.channelId)); paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode())
                                  {
                                    paramObject = CollectionRemindManager.getSource();
                                    QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteDisplay", paramObject);
                                    showBubble(20, paramString);
                                    return;
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                    if (!paramString.equalsIgnoreCase("showChannelInfo"))
                      break;
                  }
                  while (paramObject == null);
                  showBubble(3, paramObject);
                  return;
                }
                while (paramString.equalsIgnoreCase("cancelTip"));
                if (paramString.equalsIgnoreCase("sendErrorLog"))
                {
                  paramString = (String)paramObject;
                  QTMSGManage.getInstance().sendStatistcsMessage("textelementerror", paramString);
                  return;
                }
                if (!paramString.equalsIgnoreCase("showSettingView"))
                  break;
              }
              while (this.navigationController == null);
              if (this.navigationController.getLastViewController() != this.mRootController)
                this.navigationController.popToRootViewController(true);
              this.mRootController.config("showSettingView", null);
              return;
              if (paramString.equalsIgnoreCase("showError"))
              {
                paramString = (String)paramObject;
                paramObject = new MainHandler(Looper.getMainLooper());
                localObject = Message.obtain();
                Bundle localBundle = new Bundle();
                localBundle.putString("log", paramString);
                ((Message)localObject).setData(localBundle);
                paramObject.sendMessage((Message)localObject);
                return;
              }
              if (paramString.equalsIgnoreCase("quit"))
              {
                dispatchActionEvent(paramString, paramObject);
                return;
              }
              if (paramString.equalsIgnoreCase("showChatUserAction"))
              {
                showBubble(14, paramObject);
                return;
              }
              if (paramString.equalsIgnoreCase("selectdir"))
              {
                dispatchActionEvent(paramString, paramObject);
                return;
              }
              if (paramString.equalsIgnoreCase("showfmtest"))
              {
                showBubble(11, null);
                return;
              }
              if (!paramString.equalsIgnoreCase("showLink"))
                break;
            }
            while ((paramObject == null) || (this.navigationController.isAnimating()));
            if (!isBubbleShowing())
              break;
            paramString = LinkManager.getLastNode();
          }
          while ((paramString != null) && (paramString.id.equalsIgnoreCase(((RecommendItemNode)paramObject).id)));
          paramString = ControllerManager.getInstance().getLastViewController();
          if (paramString.controllerName.equalsIgnoreCase("mainplayview"))
          {
            localObject = (Point)paramString.getValue("progressPosition", paramObject);
            paramString.config("liftSomeViews", null);
            LinkManager.showLink(getContext(), (RecommendItemNode)paramObject, true, (Point)localObject);
            return;
          }
        }
        while (!paramString.hasMiniPlayer());
        LinkManager.showLink(getContext(), (RecommendItemNode)paramObject, false, null);
        return;
        if (paramString.equalsIgnoreCase("showimmenu"))
        {
          showBubble(1, paramObject);
          return;
        }
        if (paramString.equalsIgnoreCase("QTquit"))
        {
          dispatchActionEvent("quit", null);
          return;
        }
        if (paramString.equalsIgnoreCase("showblockremind"))
        {
          showBubble(16, paramObject);
          return;
        }
        if (paramString.equalsIgnoreCase("showBlockRemovePop"))
        {
          showBubble(17, paramObject);
          return;
        }
        if (paramString.equalsIgnoreCase("toggleCategoryFilter"))
        {
          if ((isBubbleShowing()) && (this.mBubbleView.getBubbleType() == 18))
          {
            cancelBubble();
            return;
          }
          showBubble(18, paramObject);
          return;
        }
        if (!paramString.equalsIgnoreCase("hideCategoryFilterIfExist"))
          break;
      }
      while ((!isBubbleShowing()) || (this.mBubbleView.getBubbleType() != 18));
      cancelBubble();
      return;
      if (paramString.equalsIgnoreCase("openFeedback"))
      {
        cancelBubble();
        ControllerManager.getInstance().openFeedBackController();
        return;
      }
      if (paramString.equalsIgnoreCase("closerecentplay"))
      {
        ControllerManager.getInstance().updateRecentPlayOnChannelDetail(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("onlineUpgrade"))
      {
        showBubble(22, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("toast"))
      {
        showBubble(23, paramObject);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
          }
        }
        , 2000L);
        return;
      }
      if (paramString.equalsIgnoreCase("topPlayHistory"))
      {
        if ((isBubbleShowing()) && (this.mBubbleView.getBubbleType() == 24))
        {
          cancelBubble();
          return;
        }
        showBubble(24, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("flow"))
      {
        if (isBubbleShowing())
        {
          cancelBubble();
          return;
        }
        showBubble(25, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("redirectToSina"))
      {
        cancelBubble();
        if (paramObject == null)
        {
          CloudCenter.getInstance().login(1, null);
          return;
        }
        CloudCenter.getInstance().login(1, (CloudCenter.OnLoginEventListerner)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("redirectToTencent"))
      {
        cancelBubble();
        if (paramObject == null)
        {
          CloudCenter.getInstance().login(2, null);
          return;
        }
        CloudCenter.getInstance().login(2, (CloudCenter.OnLoginEventListerner)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("redirectToQQ"))
      {
        cancelBubble();
        if (paramObject == null)
        {
          CloudCenter.getInstance().login(5, null);
          return;
        }
        CloudCenter.getInstance().login(5, (CloudCenter.OnLoginEventListerner)paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("redirectToWechat"));
    cancelBubble();
    if (paramObject == null)
    {
      CloudCenter.getInstance().login(6, null);
      return;
    }
    CloudCenter.getInstance().login(6, (CloudCenter.OnLoginEventListerner)paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("showQuitAlert"))
    {
      cancelBubble(false);
      dispatchActionEvent(paramString, paramObject2);
    }
    while (true)
    {
      return;
      if (paramString.equalsIgnoreCase("quit"))
      {
        cancelBubble();
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("playAtBack"))
      {
        cancelBubble();
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("cancelPop"))
      {
        cancelBubble();
        return;
      }
      if (paramString.equalsIgnoreCase("startDimBackAnimation"))
      {
        startDimBackAnimation();
        return;
      }
      if (paramString.equalsIgnoreCase("havealookatalarm"))
      {
        paramObject1 = (String)paramObject2;
        QTMSGManage.getInstance().sendStatistcsMessage("alarm_enter", paramObject1);
        cancelBubble();
        ControllerManager.getInstance().redirectToAddAlarmViewByRemind();
        return;
      }
      if (paramString.equalsIgnoreCase("addringtone"))
      {
        cancelBubble();
        return;
      }
      if (paramString.equalsIgnoreCase("scanCancel"))
      {
        if (InfoManager.getInstance().root().mContentCategory.mLiveNode.mRadioNode == null)
          continue;
        InfoManager.getInstance().root().mContentCategory.mLiveNode.mRadioNode.addDefaultNode();
        return;
      }
      if (paramString.equalsIgnoreCase("download"))
      {
        cancelBubble();
        paramString = (Node)paramObject2;
        if (!InfoManager.getInstance().root().mDownLoadInfoNode.addToDownloadList(paramString))
          continue;
        paramObject1 = "开始下载";
        if (paramString.nodeName.equalsIgnoreCase("program"))
          paramObject1 = "开始下载" + ((ProgramNode)paramString).title;
        Toast.makeText(getContext(), paramObject1, 0).show();
        return;
      }
      if (paramString.equalsIgnoreCase("closeAdView"))
        try
        {
          radiocastMessage("qt_action_stopservice");
          cancelBubble();
          return;
        }
        catch (Exception paramObject1)
        {
          return;
        }
      if (paramString.equalsIgnoreCase("openADRegin"));
      try
      {
        radiocastMessage("qt_action_stopservice");
        cancelBubble();
        label329: ControllerManager.getInstance().openADwebviewController(AdvertisementManage.get_qtAdvertisementInfo(), new QTCoupon(), "0");
        return;
        if (paramString.equalsIgnoreCase("showReplaySchedule"))
        {
          cancelBubble();
          return;
        }
        if (paramString.equalsIgnoreCase("showReserveSchedule"))
        {
          cancelBubble();
          return;
        }
        if (paramString.equalsIgnoreCase("shareToPlatform"))
        {
          cancelBubble();
          share((Node)paramObject2);
          return;
        }
        if (paramString.equalsIgnoreCase("shareToMessage"))
        {
          cancelBubble();
          showBubble(32, paramObject2);
          return;
        }
        if (paramString.equalsIgnoreCase("jumpShare"))
        {
          if (this.mShareGuideView == null)
            continue;
          removeView(this.mShareGuideView);
          this.mShareGuideView.close(false);
          this.mShareGuideView = null;
          this.mShowingShare = false;
          return;
        }
        if (paramString.equalsIgnoreCase("recommendShare"))
        {
          if (this.mShareGuideView != null)
          {
            removeView(this.mShareGuideView);
            this.mShareGuideView.close(false);
            this.mShareGuideView = null;
            this.mShowingShare = false;
          }
          paramObject1 = (RecommendItemNode)paramObject2;
          if (paramObject1 == null)
            continue;
          this.mShareObject.node = paramObject1.mNode;
          if (WeiboAgent.getInstance().isLoggedIn().booleanValue())
          {
            QTMSGManage.getInstance().sendStatistcsMessage("SendRecommendShare", "weibo");
            this.mShareObject.type = 4;
            share(this.mShareObject);
            return;
          }
          if (TencentAgent.getInstance().isLoggedIn().booleanValue())
          {
            QTMSGManage.getInstance().sendStatistcsMessage("SendRecommendShare", "tencent");
            this.mShareObject.type = 5;
            share(this.mShareObject);
            return;
          }
          if (SharedCfg.getInstance().getRecommendShareLogin())
            continue;
          QTMSGManage.getInstance().sendStatistcsMessage("BindRecommendShare", "bind");
          InfoManager.getInstance().setBindRecommendShare(true);
          showBubble(12, null);
          SharedCfg.getInstance().setRecommendShareLogin(true);
          return;
        }
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      catch (Exception paramObject1)
      {
        break label329;
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.container.layout(0, 0, this.viewWidth, this.viewHeight);
    this.mMaskView.layout(0, 0, this.viewWidth, this.viewHeight);
    layoutBubble(false);
    if (this.mShareGuideView != null)
      this.mShareGuideView.layout(0, 0, this.viewWidth, this.viewHeight);
    if (paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.viewWidth = View.MeasureSpec.getSize(paramInt1);
    this.viewHeight = View.MeasureSpec.getSize(paramInt2);
    ScreenConfiguration.setViewParam(this.viewWidth, this.viewHeight);
    SkinManager.getInstance().calculateFontSize(this.viewWidth);
    this.standardLayout.scaleToBounds(this.viewWidth, this.viewHeight);
    EducationManager.getInstance().measure(this.standardLayout);
    this.naviLayout.scaleToBounds(this.standardLayout);
    this.bottomLayout.scaleToBounds(this.standardLayout);
    paramInt2 = View.MeasureSpec.makeMeasureSpec(this.viewHeight, 1073741824);
    this.container.measure(paramInt1, paramInt2);
    this.mBubbleView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(getBubbleHeight(), 1073741824));
    this.mMaskView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(getBubbleHeight(), 1073741824));
    if (this.mShareGuideView != null)
      this.mShareGuideView.measure(paramInt1, paramInt2);
    setMeasuredDimension(this.viewWidth, this.viewHeight);
  }

  public void onPopControllers(List<ViewController> paramList, boolean paramBoolean)
  {
    if ((paramList != null) && (paramList.size() > 0) && (((ViewController)paramList.get(0)).controllerName.equalsIgnoreCase("mainplayview")))
    {
      paramBoolean = InfoManager.getInstance().root().getHasChangedChannel();
      InfoManager.getInstance().root().clearHasChangedChannel();
    }
    while (true)
    {
      setFlagOnTopViewChanged(paramBoolean);
      markPageChanged(this.navigationController.getLastViewController());
      return;
      paramBoolean = false;
    }
  }

  public void onPushController(ViewController paramViewController, boolean paramBoolean)
  {
    setFlagOnTopViewChanged(paramViewController);
    markPageChanged(paramViewController);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    LinkManager.cancelLinkIfExists(getContext());
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("performPop"))
    {
      popController();
      ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (paramString.equalsIgnoreCase("onPause"))
              {
                handleOnPause();
                return;
              }
              if (paramString.equalsIgnoreCase("cancelBubble"))
              {
                cancelBubble();
                return;
              }
              if (!paramString.equalsIgnoreCase("refresh"))
                break;
              if (this.container != null)
                this.container.invalidate();
              paramString = this.navigationController.getLastViewController();
            }
            while ((paramString == null) || (!paramString.controllerName.equalsIgnoreCase("frontpage")));
            paramString = paramString.getValue("currentIndex", null);
          }
          while ((paramString == null) || (((Integer)paramString).intValue() != 0));
          RecommendStatisticsUtil.INSTANCE.resume();
          return;
          if (!paramString.equalsIgnoreCase("showadBubble"))
            break;
        }
        while ((this.navigationController == null) || (this.navigationController.getLastViewController() == null) || (this.navigationController.getLastViewController().controllerName == null));
        if (this.navigationController.getLastViewController().controllerName.equalsIgnoreCase("mainplayview"))
        {
          showBubble(13, null);
          UMengLogger.sendmessage(getContext(), "adPlay", AdvertisementManage.getInstance().currentADKey, 1);
          return;
        }
        UMengLogger.sendmessage(getContext(), "adPlay", AdvertisementManage.getInstance().currentADKey, 2);
        return;
        if (paramString.equalsIgnoreCase("openadwebview"))
        {
          ControllerManager.getInstance().openADwebviewController((String)paramObject);
          return;
        }
        if (paramString.equalsIgnoreCase("showAlert"))
        {
          showBubble(5, paramObject);
          return;
        }
        if (!paramString.equalsIgnoreCase("removeShare"))
          break;
      }
      while (this.mShareGuideView == null);
      removeView(this.mShareGuideView);
      this.mShareGuideView.close(false);
      this.mShareGuideView = null;
      this.mShowingShare = false;
      MobclickAgent.onEvent(getContext(), "showShare", "remove");
      return;
    }
    while (!paramString.equalsIgnoreCase("resortCategoryList"));
    this.mRootController.config(paramString, paramObject);
  }

  private class MainHandler extends Handler
  {
    public MainHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      paramMessage = paramMessage.getData().getString("log");
      MainView.this.showBubble(2, paramMessage);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.MainView
 * JD-Core Version:    0.6.2
 */