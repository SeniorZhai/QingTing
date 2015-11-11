package fm.qingting.qtradio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.media.session.MediaSession.Callback;
import android.media.session.PlaybackState;
import android.media.session.PlaybackState.Builder;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import cn.com.mma.mobile.tracking.api.Countly;
import com.google.android.apps.analytics.GoogleAnalyticsTracker;
import com.ixintui.pushsdk.PushSdkApi;
import com.pingan.pinganwifi.AppGlobal;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import fm.qingting.downloadnew.HttpDownloadHelper;
import fm.qingting.framework.controller.StatisticsFMManage;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.NetDS;
import fm.qingting.framework.data.Result;
import fm.qingting.framework.data.ServerConfig;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.net.HTTPConnection;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.framework.utils.SystemInfo;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.abtest.ABTestConfig;
import fm.qingting.qtradio.abtest.ABTestItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.data.AlarmDS;
import fm.qingting.qtradio.data.ApiSign;
import fm.qingting.qtradio.data.AttributesDS;
import fm.qingting.qtradio.data.BillboardNodeDS;
import fm.qingting.qtradio.data.CategoryNodeDS;
import fm.qingting.qtradio.data.ChannelNodesDS;
import fm.qingting.qtradio.data.CommonDS;
import fm.qingting.qtradio.data.DBManager;
import fm.qingting.qtradio.data.DownloadingProgramDS;
import fm.qingting.qtradio.data.FavouriteChannelDS;
import fm.qingting.qtradio.data.FreqChannelsDS;
import fm.qingting.qtradio.data.H5BeanDS;
import fm.qingting.qtradio.data.IMContactsDS;
import fm.qingting.qtradio.data.IMDatabaseDS;
import fm.qingting.qtradio.data.IMUserInfoDS;
import fm.qingting.qtradio.data.MediaCenterDS;
import fm.qingting.qtradio.data.MyPodcasterDS;
import fm.qingting.qtradio.data.NotifyDS;
import fm.qingting.qtradio.data.PlayHistoryDS;
import fm.qingting.qtradio.data.PlayListDS;
import fm.qingting.qtradio.data.PlayedMetaDS;
import fm.qingting.qtradio.data.PodcasterDS;
import fm.qingting.qtradio.data.PreDownloadingProgramDS;
import fm.qingting.qtradio.data.ProfileDS;
import fm.qingting.qtradio.data.ProgramNodesDS;
import fm.qingting.qtradio.data.ProgramNodesRevDS;
import fm.qingting.qtradio.data.PullNodeDS;
import fm.qingting.qtradio.data.RadioNodesDS;
import fm.qingting.qtradio.data.RecommendCategoryNodeDS;
import fm.qingting.qtradio.data.ReserveProgramDS;
import fm.qingting.qtradio.data.SocialUserProfileDS;
import fm.qingting.qtradio.data.UserInfoDS;
import fm.qingting.qtradio.dongruan.DongRuanInstance;
import fm.qingting.qtradio.doubleclick.DoubleClick;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.fm.SystemPlayer;
import fm.qingting.qtradio.fm.TaobaoAgent;
import fm.qingting.qtradio.fm.WebViewPlayer;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.fmdriver.FMcontrol;
import fm.qingting.qtradio.headset.HeadSet;
import fm.qingting.qtradio.headset.MediaButtonReceiver;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.NetWorkManage;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.advertisement.AdvertisementManage;
import fm.qingting.qtradio.mobAgent.mobAgentOption;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.AlarmInfo;
import fm.qingting.qtradio.model.AlarmInfoNode;
import fm.qingting.qtradio.model.BillboardNode;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayedMetaInfo;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RadioNode;
import fm.qingting.qtradio.model.ReserveInfoNode;
import fm.qingting.qtradio.model.RingToneInfoNode;
import fm.qingting.qtradio.model.RingToneNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.FromType;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.model.ShareInfoNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.model.UpgradeInfo;
import fm.qingting.qtradio.model.advertisement.QTAdvertisementInfo;
import fm.qingting.qtradio.notification.QTAlarmReceiver;
import fm.qingting.qtradio.offline.OfflineManager;
import fm.qingting.qtradio.parser.NetParser;
import fm.qingting.qtradio.playlist.PlayListManager;
import fm.qingting.qtradio.push.bean.PushType;
import fm.qingting.qtradio.push.config.PushConfig;
import fm.qingting.qtradio.push.log.ClickNotificationLog;
import fm.qingting.qtradio.push.log.NDPushLog;
import fm.qingting.qtradio.pushcontent.PushLiveConfig;
import fm.qingting.qtradio.pushcontent.PushLiveLog;
import fm.qingting.qtradio.pushmessage.PushMessageLog;
import fm.qingting.qtradio.pushmessage.UmengPushIntentService;
import fm.qingting.qtradio.remotecontrol.RemoteControl;
import fm.qingting.qtradio.retain.RetainLog;
import fm.qingting.qtradio.ring.RingManager;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboChat;
import fm.qingting.qtradio.sensor.SpeedSensor;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.tencentAgent.QQAgent;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.view.LaunchView;
import fm.qingting.qtradio.view.MainView;
import fm.qingting.qtradio.view.UserGuideView;
import fm.qingting.qtradio.view.popviews.AlertParam;
import fm.qingting.qtradio.view.popviews.AlertParam.Builder;
import fm.qingting.qtradio.view.popviews.AlertParam.OnButtonClickListener;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.qtradio.weixin.WeixinAgent;
import fm.qingting.qtradio.wo.WoApiRequest;
import fm.qingting.qtradio.wo.WoNetEventListener;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.LifeTime;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.RecommendStatisticsUtil;
import fm.qingting.utils.ScreenConfiguration;
import fm.qingting.utils.ThirdAdv;
import fm.qingting.utils.ThirdTracker;
import fm.qingting.utils.Zeus;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class QTRadioActivity extends Activity
  implements IEventHandler, IResultRecvHandler, InfoManager.ISubscribeEventListener
{
  public static final int CROP_IMAGE_CAPTURE = 79;
  public static final int CROP_IMAGE_PICK = 83;
  private static int Maxfreq = 0;
  private static int Minfreq = 0;
  public static final int SELECT_PIC_BY_CAPTURE = 73;
  public static final int SELECT_PIC_BY_PICK_PHOTO = 71;
  private int MaxVolume = 15;
  private int MinVolume = 0;
  private long curScanTime = 0L;
  private long curnetplaytime = 0L;
  private int currentVolume = -1;
  private long gpsLocateStartTime;
  private String gpsLocation = null;
  private boolean hasLocal = false;
  private boolean hasOpenSpeaker = false;
  private boolean ignoreBootStrapResult = false;
  private boolean inited = false;
  private long ipLocateStartTime;
  private String ipLocation = null;
  private AudioManager mAudioManager;
  private boolean mCarPlay = false;
  private Context mContext;
  private boolean mDisplayAD = false;
  private LaunchView mLaunchView;
  private MediaSession mMediaSession;
  private boolean mNotifySwitchState = false;
  private boolean mOpenDongruan = true;
  private PushAgent mPushAgent;
  private boolean mShowAdvertisement = false;
  private UpgradeInfo mUpgradeInfo;
  private String mUpgradeName = "QTRadioUpgrade.apk";
  private MainView mainView;
  private MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver();
  private boolean osis6 = false;
  private boolean playedLastChannel = true;
  private QTLocation positionLocation = null;
  private long preScanTime = 0L;
  private long prenetplaytime = 0L;
  private final String quitAction = "fm.qingting.quit";
  private boolean readyToStart = false;
  private ServiceCommandReceiver serviceCommandReceiver;
  private final String startAction = "fm.qingting.start";
  private boolean started = false;
  private Runnable timingWake = new Runnable()
  {
    public void run()
    {
      QTRadioActivity.access$802(QTRadioActivity.this, true);
      if (InfoManager.getInstance().enableIClick())
      {
        if (!CrystalHelper.hasClosed())
        {
          CrystalHelper.setListener(new CrystalHelper.onCloseListener()
          {
            public void onClose()
            {
              QTRadioActivity.this.startMain();
            }
          });
          return;
        }
        QTRadioActivity.this.startMain();
        return;
      }
      QTRadioActivity.this.startMain();
    }
  };
  private GoogleAnalyticsTracker tracker;
  private Handler wakeHandler = new Handler();

  private void ShowDialogReportToUser()
  {
    DialogInterface.OnClickListener local5 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return;
        case -1:
        }
        QTRadioActivity.this.quit();
      }
    };
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
    localAlertDialog.setButton("退出", local5);
    localAlertDialog.setButton2("继续", local5);
    localAlertDialog.setMessage("抱歉，蜻蜓暂时不能正常工作，您可以加入蜻蜓官方客服QQ群(171440910)，我们会有专业工程师第一时间为您解决此问题");
    localAlertDialog.show();
  }

  private void acquireABTest()
  {
    InfoManager.getInstance().setStatusPage(true);
    String str = ABTest.getInstance().getOption(ABTestConfig.frontCollection.OptionName);
    InfoManager localInfoManager = InfoManager.getInstance();
    if ((str != null) && (str.equalsIgnoreCase(ABTestConfig.frontCollection.OptionA)));
    for (boolean bool = true; ; bool = false)
    {
      localInfoManager.setEnableFrontCollection(bool);
      if (SharedCfg.getInstance().getDefaultCollection())
        InfoManager.getInstance().setEnableFrontCollection(true);
      str = ABTest.getInstance().getOption(ABTestConfig.checkin.OptionName);
      if (str != null)
      {
        if (!str.equalsIgnoreCase(ABTestConfig.checkin.OptionA))
          break;
        InfoManager.getInstance().setEnableNewCheckin(1);
      }
      return;
    }
    InfoManager.getInstance().setEnableNewCheckin(2);
  }

  private void acquireAdvertisementParam()
  {
    try
    {
      String str = MobclickAgent.getConfigParams(this, "showAdvertisement");
      if ((str != null) && (!str.equalsIgnoreCase("")))
        InfoManager.getInstance().setAdvertisementRadio(Integer.valueOf(str).intValue());
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void acquireUmengConfig()
  {
    try
    {
      Object localObject = MobclickAgent.getConfigParams(this, "CollectionRemindTime");
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
        GlobalCfg.getInstance(this).setValueToDB("CollectionRemindTime", "integer", (String)localObject);
      localObject = MobclickAgent.getConfigParams(this, "allowBubbleChannels");
      SharedCfg.getInstance().setBubbleChannelList((String)localObject);
      String str2 = AppInfo.getChannelName(this);
      SharedCfg.getInstance().setMutiRate(true);
      SharedCfg.getInstance().setSaveBattery(true);
      localObject = MobclickAgent.getConfigParams(this, "filterStatement");
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
        SharedCfg.getInstance().setFilterLiveRoom((String)localObject);
      localObject = MobclickAgent.getConfigParams(this, "isNeedDisplayAD");
      if (localObject != null)
      {
        if (((String)localObject).equalsIgnoreCase("1"))
          this.mDisplayAD = true;
      }
      else
      {
        localObject = MobclickAgent.getConfigParams(this, "MaxWordsInLiveRoom");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setMaxWordsInLiveRoom(Integer.valueOf((String)localObject).intValue());
        InfoManager.getInstance().setAutoSeek(true);
        InfoManager.getInstance().setEnableAutoSeek(true);
        localObject = MobclickAgent.getConfigParams(this, "useFloatWindow");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2641;
          GlobalCfg.getInstance(this).setFloatWindow(true);
        }
        label213: InfoManager.getInstance().setShowRecommendApp(false);
        PushConfig.pullUmengConfig(this);
        PushLiveConfig.pullUmengConfig(this);
        localObject = MobclickAgent.getConfigParams(this, "autoReserveMinDuration");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setAutoReserveMinDuration(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "PushLiveSwitch");
        if (localObject != null)
        {
          if (!((String)localObject).equalsIgnoreCase("true"))
            break label2652;
          GlobalCfg.getInstance(this).setPushLiveSwitch(true);
        }
        label293: InfoManager.getInstance().setUsePlayCache(true);
        localObject = MobclickAgent.getConfigParams(this, "enterChatRoom");
        if (localObject != null)
          InfoManager.getInstance().setChatroom((String)localObject);
        InfoManager.getInstance().setEnableSetDNS(false);
        localObject = MobclickAgent.getConfigParams(this, "apple");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2663;
          InfoManager.getInstance().apple(true);
          localObject = MobclickAgent.getConfigParams(this, "appleImage");
          if (localObject != null)
            InfoManager.getInstance().setAppImage((String)localObject);
          localObject = MobclickAgent.getConfigParams(this, "appleDesc");
          if (localObject != null)
            InfoManager.getInstance().setAppDesc((String)localObject);
          localObject = MobclickAgent.getConfigParams(this, "appleUrl");
          if (localObject != null)
            InfoManager.getInstance().setAppleUrl((String)localObject);
        }
        label420: localObject = MobclickAgent.getConfigParams(this, "shareTag");
        if (localObject != null)
        {
          if (!((String)localObject).equalsIgnoreCase("#"))
            break label2673;
          InfoManager.getInstance().setShareTag("");
        }
        label451: localObject = MobclickAgent.getConfigParams(this, "enableGroup");
        if (localObject != null)
          InfoManager.getInstance().setEnableSocial((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "addGroupSlogan");
        if (localObject != null)
          InfoManager.getInstance().setAddGroupSlogan((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "linkDuration");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setLinkDuration(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "LiveRoomBlacklist");
        if (localObject != null)
          InfoManager.getInstance().setLiveRoomBlack((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "ZeusV2");
        if (localObject != null)
          Zeus.getInstance().setZeusUrl((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "ZeusV2Percent");
        if (localObject != null)
          Zeus.getInstance().setZeusPercent((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "TrackerRegions");
        if (localObject != null)
          ThirdTracker.getInstance().setTrackerRegions((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "ADTracker");
        if (localObject != null)
          ThirdTracker.getInstance().setADUrl((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "ADTrackerPercent");
        if (localObject != null)
          ThirdTracker.getInstance().setADPercent((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "MZTracker");
        if (localObject != null)
          ThirdTracker.getInstance().setMZUrl((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "MZTrackerPercent");
        if (localObject != null)
          ThirdTracker.getInstance().setMZPercent((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "Achilles");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
        {
          InfoManager.getInstance().setAchilles(true);
          localObject = MobclickAgent.getConfigParams(this, "AchillesUrl");
          if (localObject != null)
            InfoManager.getInstance().setAchillesUrl((String)localObject);
          localObject = MobclickAgent.getConfigParams(this, "AchillesPercent");
          if (localObject != null)
            InfoManager.getInstance().setAchillesPercent((String)localObject);
        }
        localObject = MobclickAgent.getConfigParams(this, "ApolloStartTime");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          SharedCfg.getInstance().setApolloStartTime(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "ApolloDuration");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          SharedCfg.getInstance().setApolloDuration(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "sellApps");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
        {
          localObject = MobclickAgent.getConfigParams(this, "sellAppVersion");
          if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")) && (Integer.valueOf((String)localObject).intValue() > AppInfo.getCurrentVersionCode(this)))
          {
            InfoManager.getInstance().setSellApps(true);
            localObject = MobclickAgent.getConfigParams(this, "sellAppsInfo");
            if (localObject != null)
              InfoManager.getInstance().setSellAppsInfo((String)localObject);
            localObject = MobclickAgent.getConfigParams(this, "sellAppsPackage");
            if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
              InfoManager.getInstance().setSellAppsPackage((String)localObject);
          }
        }
        localObject = MobclickAgent.getConfigParams(this, "enableAudioAdv");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2683;
          InfoManager.getInstance().setAudioAdv(true);
        }
        label989: localObject = MobclickAgent.getConfigParams(this, "taobaoAdv1");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2693;
          InfoManager.getInstance().setTaobaoAudioAdv(true);
        }
        label1026: localObject = MobclickAgent.getConfigParams(this, "taobaoAdvId");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          TaobaoAgent.getInstance().setADId((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "StudentABTest");
        if ((localObject != null) && (((String)localObject).equalsIgnoreCase("0")))
          SharedCfg.getInstance().setChooseStudent(0);
        localObject = MobclickAgent.getConfigParams(this, "doubleClickUrls");
        if (localObject != null)
        {
          DoubleClick.getInstance().setZeusUrl((String)localObject);
          localObject = MobclickAgent.getConfigParams(this, "doubleClickPercent");
          if (localObject != null)
            DoubleClick.getInstance().setZeusPercent((String)localObject);
        }
        localObject = MobclickAgent.getConfigParams(this, "doubleClickPattern");
        if (localObject != null)
        {
          DoubleClick.getInstance().setPattern((String)localObject);
          localObject = MobclickAgent.getConfigParams(this, "doubleClickConfig");
          if (localObject != null)
          {
            DoubleClick.getInstance().setConfigs((String)localObject);
            localObject = MobclickAgent.getConfigParams(this, "doubleClickConfigPercent");
            if (localObject != null)
              DoubleClick.getInstance().setPercents((String)localObject);
          }
        }
        localObject = MobclickAgent.getConfigParams(this, "ForceLogin");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2703;
          InfoManager.getInstance().setForceLogin(true);
        }
        label1216: localObject = MobclickAgent.getConfigParams(this, "chinaUnicomFlow");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2713;
          InfoManager.getInstance().setEnableWoQt(true);
        }
        label1253: localObject = MobclickAgent.getConfigParams(this, "chinaUnicomZone");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2723;
          InfoManager.getInstance().setChinaUnicomZone(true);
        }
        label1290: localObject = MobclickAgent.getConfigParams(this, "quickDownloadChannel");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2733;
          OnlineUpdateHelper.getInstance().setNeedQuickDownload(true);
        }
        label1327: localObject = MobclickAgent.getConfigParams(this, "pingan");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2743;
          InfoManager.getInstance().setEnablePingan(true);
        }
        label1364: localObject = MobclickAgent.getConfigParams(this, "ug_category_recommend");
        if (localObject != null)
          InfoManager.getInstance().setUserguideRecommend((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "IREChange");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          SharedCfg.getInstance().setIREChange(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "taobaoChange");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          SharedCfg.getInstance().setTaoBaoChange(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "AdvLoc");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).contains("all")))
            break label2753;
          InfoManager.getInstance().setADVLoc(true);
        }
        label1490: localObject = MobclickAgent.getConfigParams(this, "allowMusicDownload");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setAllowDownloadMusic((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "barrage");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setBarrage((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "defaultCollectionKey");
        String str1 = MobclickAgent.getConfigParams(this, "defaultCollectionValue");
        InfoManager.getInstance().setDefaultCollectionChannelId((String)localObject, str1);
        localObject = MobclickAgent.getConfigParams(this, "defaultSTKey");
        str1 = MobclickAgent.getConfigParams(this, "defaultSTValue");
        InfoManager.getInstance().setDefaultSpecialTopic((String)localObject, str1);
        localObject = MobclickAgent.getConfigParams(this, "advFromAirWave2");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().enableAirWave(true);
        localObject = MobclickAgent.getConfigParams(this, "advFromAirWaveCity");
        if (localObject != null)
        {
          str1 = InfoManager.getInstance().getCurrentCity();
          if (((str1 != null) && (!((String)localObject).contains(str1))) || (((String)localObject).equalsIgnoreCase("#")))
            InfoManager.getInstance().enableAirWaveCity(true);
        }
        localObject = MobclickAgent.getConfigParams(this, "advFromAirWaveShow");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("#")) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setAirWaveShow(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "advFromAirWaveClick");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("#")) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setAirWaveClick(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "JDADChannel");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2763;
          InfoManager.getInstance().sethasJdAd(true);
        }
        label1808: localObject = MobclickAgent.getConfigParams(this, "JDADPosition");
        if (localObject != null)
          InfoManager.getInstance().setJdAdPosition((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "topPlayHistory");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2773;
          InfoManager.getInstance().setTopHistory(true);
        }
        label1864: localObject = MobclickAgent.getConfigParams(this, "advJDCity");
        if (localObject != null)
        {
          str1 = InfoManager.getInstance().getCurrentCity();
          if (((str1 != null) && (!((String)localObject).contains(str1))) || (((String)localObject).equalsIgnoreCase("#")))
            InfoManager.getInstance().enableJDCity(true);
        }
        localObject = MobclickAgent.getConfigParams(this, "advJDShow");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("#")) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setJDShow(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "advJDClick2");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("#")) && (!((String)localObject).equalsIgnoreCase("")))
          InfoManager.getInstance().setJDClick(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "h7");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setEnableH5(true);
        localObject = MobclickAgent.getConfigParams(this, "h9");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setEnableRecommendH5(true);
        localObject = MobclickAgent.getConfigParams(this, "flowpop");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setFlowPop(true);
        localObject = MobclickAgent.getConfigParams(this, "advJDSeed");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          ThirdTracker.getInstance().setJDSeed(Integer.valueOf((String)localObject).intValue());
        localObject = MobclickAgent.getConfigParams(this, "programabtest2");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setEnableProgramABTest(true);
        localObject = MobclickAgent.getConfigParams(this, "game");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setEnablGame(true);
        localObject = MobclickAgent.getConfigParams(this, "openDongruan");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          this.mOpenDongruan = true;
        localObject = MobclickAgent.getConfigParams(this, "AdTrackLog");
        if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
          InfoManager.getInstance().setEnablAdTrack(true);
        localObject = MobclickAgent.getConfigParams(this, "advFromAirWaveCategory");
        if (localObject != null)
          InfoManager.getInstance().setAirwaveCategory((String)localObject);
        localObject = MobclickAgent.getConfigParams(this, "iclick");
        if (localObject == null)
          break label2798;
        if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
          break label2783;
        SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "1");
        label2355: localObject = MobclickAgent.getConfigParams(this, "irev2");
        if (localObject != null)
        {
          if (((String)localObject).equalsIgnoreCase("#"))
            break label2813;
          GlobalCfg.getInstance(this).setDoIRE(Integer.valueOf((String)localObject).intValue());
        }
        label2391: localObject = MobclickAgent.getConfigParams(this, "wemart");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2824;
          InfoManager.getInstance().setWemart(true);
        }
        label2428: localObject = MobclickAgent.getConfigParams(this, "DCChannel");
        if (localObject != null)
        {
          if ((!((String)localObject).contains(str2)) && (!((String)localObject).equalsIgnoreCase("all")))
            break label2834;
          InfoManager.getInstance().setEnableDC(true);
        }
        label2465: str1 = MobclickAgent.getConfigParams(this, "FlowDayStamp");
        if (str1 == null)
          break label2882;
        localObject = str1;
        if (str1.equalsIgnoreCase(""))
          break label2882;
        label2492: SharedCfg.getInstance().setFlowStamp(Integer.parseInt((String)localObject));
        str1 = MobclickAgent.getConfigParams(this, "WoQtAlertDayStamp");
        if (str1 == null)
          break label2889;
        localObject = str1;
        if (str1.equalsIgnoreCase(""))
          break label2889;
      }
      while (true)
      {
        SharedCfg.getInstance().setWoQtAlertDayStamp(Integer.parseInt((String)localObject));
        localObject = MobclickAgent.getConfigParams(this, "disableGD");
        if (localObject != null)
          if (((String)localObject).contains(str2))
            InfoManager.getInstance().setDisableGD(true);
        while (true)
        {
          localObject = MobclickAgent.getConfigParams(this, "adMgrConfig");
          if ((localObject != null) && ((((String)localObject).contains(str2)) || (((String)localObject).equalsIgnoreCase("all"))))
            InfoManager.getInstance().setEnableADMgr(true);
          localObject = MobclickAgent.getConfigParams(this, "onCellular");
          if (localObject == null)
            break label2896;
          if (!((String)localObject).equalsIgnoreCase("1"))
            break label2864;
          InfoManager.getInstance().setOnCellular(true);
          return;
          this.mDisplayAD = false;
          break;
          label2641: GlobalCfg.getInstance(this).setFloatWindow(false);
          break label213;
          label2652: GlobalCfg.getInstance(this).setPushLiveSwitch(false);
          break label293;
          label2663: InfoManager.getInstance().apple(false);
          break label420;
          label2673: InfoManager.getInstance().setShareTag((String)localObject);
          break label451;
          label2683: InfoManager.getInstance().setAudioAdv(false);
          break label989;
          label2693: InfoManager.getInstance().setTaobaoAudioAdv(false);
          break label1026;
          label2703: InfoManager.getInstance().setForceLogin(false);
          break label1216;
          label2713: InfoManager.getInstance().setEnableWoQt(false);
          break label1253;
          label2723: InfoManager.getInstance().setChinaUnicomZone(false);
          break label1290;
          label2733: OnlineUpdateHelper.getInstance().setNeedQuickDownload(false);
          break label1327;
          label2743: InfoManager.getInstance().setEnablePingan(false);
          break label1364;
          label2753: InfoManager.getInstance().setADVLoc(false);
          break label1490;
          label2763: InfoManager.getInstance().sethasJdAd(false);
          break label1808;
          label2773: InfoManager.getInstance().setTopHistory(false);
          break label1864;
          label2783: SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "0");
          break label2355;
          label2798: SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "0");
          break label2355;
          label2813: GlobalCfg.getInstance(this).setDoIRE(-1);
          break label2391;
          label2824: InfoManager.getInstance().setWemart(false);
          break label2428;
          label2834: InfoManager.getInstance().setEnableDC(false);
          break label2465;
          if (str2.contains("移动MM"))
            InfoManager.getInstance().setDisableGD(true);
        }
        label2864: if (!((String)localObject).equalsIgnoreCase("0"))
          break label2896;
        InfoManager.getInstance().setOnCellular(false);
        return;
        label2882: localObject = "15";
        break label2492;
        label2889: localObject = "7";
      }
      label2896: return;
    }
    catch (Exception localException)
    {
    }
  }

  private boolean addShortCut(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    if (SharedCfg.getInstance().hasAddedShortcut())
      return false;
    if (hasShortcut())
      return false;
    String str = "unknown";
    Object localObject1 = paramContext.getPackageManager();
    Object localObject3 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject3).addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject3, 1).iterator();
    int i;
    while (localIterator.hasNext())
    {
      localObject3 = (ResolveInfo)localIterator.next();
      if (TextUtils.equals(((ResolveInfo)localObject3).activityInfo.packageName, paramString))
      {
        str = ((ResolveInfo)localObject3).loadLabel((PackageManager)localObject1).toString();
        i = ((ResolveInfo)localObject3).activityInfo.applicationInfo.icon;
      }
    }
    for (localObject1 = ((ResolveInfo)localObject3).activityInfo.name; ; localObject1 = null)
    {
      if (TextUtils.isEmpty((CharSequence)localObject1))
        return false;
      localObject3 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
      ((Intent)localObject3).putExtra("android.intent.extra.shortcut.NAME", str);
      ((Intent)localObject3).putExtra("duplicate", false);
      localObject1 = new ComponentName(paramString, (String)localObject1);
      ((Intent)localObject3).putExtra("android.intent.extra.shortcut.INTENT", new Intent("android.intent.action.MAIN").setComponent((ComponentName)localObject1));
      if (TextUtils.equals(paramString, paramContext.getPackageName()))
        paramString = paramContext;
      while (true)
      {
        if (paramString != null)
          ((Intent)localObject3).putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramString, i));
        paramContext.sendBroadcast((Intent)localObject3);
        SharedCfg.getInstance().setShortcutAdded();
        return true;
        try
        {
          paramString = paramContext.createPackageContext(paramString, 3);
        }
        catch (PackageManager.NameNotFoundException paramString)
        {
          paramString.printStackTrace();
          paramString = localObject2;
        }
      }
      i = -1;
    }
  }

  private void cancelSystemAlarm()
  {
    try
    {
      Intent localIntent = new Intent("fm.qingting.alarmintent");
      localIntent.setClass(this, QTAlarmReceiver.class);
      Object localObject = PendingIntent.getBroadcast(this, 0, localIntent, 134217728);
      AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
      localAlarmManager.cancel((PendingIntent)localObject);
      localObject = new Intent("fm.qingting.reserveintent");
      localIntent.setClass(this, QTAlarmReceiver.class);
      localAlarmManager.cancel(PendingIntent.getBroadcast(this, 0, (Intent)localObject, 134217728));
      localObject = new Intent("fm.qingting.notifyintent");
      localIntent.setClass(this, QTAlarmReceiver.class);
      localAlarmManager.cancel(PendingIntent.getBroadcast(this, 0, (Intent)localObject, 134217728));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void change(String paramString)
  {
    Object localObject = paramString.getClass();
    try
    {
      Field localField1 = ((Class)localObject).getDeclaredField("value");
      Field localField2 = ((Class)localObject).getDeclaredField("count");
      localField2.setAccessible(true);
      localField1.setAccessible(true);
      int i = SharedCfg.getInstance().getIREChange();
      if (i == 0)
        return;
      localObject = paramString + "CM";
      int j = SharedCfg.getInstance().getBootstrapCnt() % i;
      if (j != 0)
      {
        i = 0;
        while (i < j)
        {
          localObject = (String)localObject + String.valueOf(i);
          i += 1;
        }
        localObject = ((String)localObject).toCharArray();
        localField2.set(paramString, Integer.valueOf(localObject.length));
        localField1.set(paramString, localObject);
        return;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  private void change6(String paramString)
  {
    Object localObject = paramString.getClass();
    int i;
    while (true)
    {
      try
      {
        localObject = ((Class)localObject).getDeclaredField("count");
        ((Field)localObject).setAccessible(true);
        if (SharedCfg.getInstance().getIREChange() <= 0)
          return;
        i = 0;
        String str = SharedCfg.getInstance().getValue("KEY_IRE_CHANGED_6");
        if ((str != null) && (!str.equalsIgnoreCase("")))
        {
          i = Integer.valueOf(str).intValue();
          if (i > 1)
            break;
          SharedCfg.getInstance().saveValue("KEY_IRE_CHANGED_6", String.valueOf(paramString.length()));
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      SharedCfg.getInstance().saveValue("KEY_IRE_CHANGED_6", String.valueOf(paramString.length()));
    }
    i -= 1;
    ((Field)localObject).set(paramString, Integer.valueOf(i));
    SharedCfg.getInstance().saveValue("KEY_IRE_CHANGED_6", String.valueOf(i));
  }

  private void checkHasReserveTask()
  {
    long l = InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.getReadyToInvokeReserveTask();
    if (l < 9223372036854775807L)
      try
      {
        AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
        PendingIntent localPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("fm.qingting.reserveintent"), 134217728);
        l = l * 1000L - 300000L;
        if (l < System.currentTimeMillis())
        {
          localAlarmManager.setRepeating(1, System.currentTimeMillis() + 10000L, 60000L, localPendingIntent);
          return;
        }
        localAlarmManager.setRepeating(0, l, 60000L, localPendingIntent);
        return;
      }
      catch (Exception localException)
      {
      }
  }

  private void checkIfFloatSwitchIsOn()
  {
    if ((GlobalCfg.getInstance(this.mContext).getFloatWindow()) && (GlobalCfg.getInstance(this.mContext).getFloatState()))
      LogModule.getInstance().send("FloatSwitchOn", QTLogger.getInstance().buildCommonLog());
  }

  private void checkIfPushable()
  {
    int j = 1;
    Object localObject = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
    if ((localObject != null) && (((List)localObject).size() > 0))
    {
      i = ((List)localObject).size();
      if (i >= 100)
      {
        localObject = "100xx";
        QTMSGManage.getInstance().sendStatistcsMessage("haveFavorChannel", (String)localObject);
      }
    }
    for (int i = 1; ; i = 0)
    {
      if (InfoManager.getInstance().getPushSwitch() == true)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("PushSwitchIsOn", "true");
        QTLogger.getInstance().sendOther("PushSwitchIsOn", "1");
      }
      while (true)
      {
        if ((i != 0) && (j != 0))
          QTMSGManage.getInstance().sendStatistcsMessage("UserIsPushable", "true");
        return;
        localObject = String.valueOf(i);
        break;
        j = 0;
      }
    }
  }

  private void checkLocation()
  {
  }

  private void checkSystem()
  {
    if (this.mLaunchView != null)
    {
      this.mLaunchView.close(false);
      this.mLaunchView = null;
    }
    if ((InfoManager.getInstance().getStatusPage()) && (!SharedCfg.getInstance().getGuideShowed()))
    {
      UserGuideView localUserGuideView = new UserGuideView(this.mContext);
      localUserGuideView.setEventHandler(this);
      setContentView(localUserGuideView);
    }
    do
    {
      return;
      showMainView();
    }
    while ((handleMessageOnCreate(getIntent())) || (!InfoManager.getInstance().hasConnectedNetwork()) || (InfoManager.getInstance().getDefaultSpecialTopic() == 0));
    ControllerManager.getInstance().openSpecialTopicController(InfoManager.getInstance().getDefaultSpecialTopic());
  }

  private void cropImageUri(Uri paramUri1, Uri paramUri2, int paramInt1, int paramInt2, int paramInt3)
  {
    Intent localIntent = new Intent("com.android.camera.action.CROP");
    localIntent.setDataAndType(paramUri1, "image/*");
    localIntent.putExtra("crop", "true");
    localIntent.putExtra("aspectX", 720);
    localIntent.putExtra("aspectY", 574);
    localIntent.putExtra("outputX", paramInt1);
    localIntent.putExtra("outputY", paramInt2);
    localIntent.putExtra("scale", true);
    localIntent.putExtra("output", paramUri2);
    localIntent.putExtra("return-data", false);
    localIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    localIntent.putExtra("noFaceDetection", true);
    startActivityForResult(localIntent, paramInt3);
  }

  private void dealPushMsg(Bundle paramBundle)
  {
    this.playedLastChannel = false;
    int i = InfoManager.getInstance().getNetWorkType();
    QTMSGManage.getInstance().sendStatistcsMessage("ContentUpdatePushClicked", String.valueOf(i));
    String str = paramBundle.getString("notify_type");
    if ((str != null) && (str.equalsIgnoreCase("push_live_channel")))
      PushLiveLog.sendClickNotification(this, paramBundle.getString("live_topic"), "launchApp");
    while (true)
    {
      switch (i)
      {
      case 1:
      }
      return;
      if (str.equalsIgnoreCase(PushType.ContentUpdate.name()))
        NDPushLog.sendNDClickLog(paramBundle, 1, PushType.ContentUpdate, this);
      else if (str.equalsIgnoreCase(PushType.Download.name()))
        NDPushLog.sendNDClickLog(paramBundle, 1, PushType.Download, this);
      else if (str.equalsIgnoreCase(PushType.Novel.name()))
        NDPushLog.sendNDClickLog(paramBundle, 1, PushType.Novel, this);
      else if (str.equalsIgnoreCase(PushType.ResumeProgram.name()))
        NDPushLog.sendNDClickLog(paramBundle, 1, PushType.ResumeProgram, this);
    }
  }

  private void doCropPhoto(int paramInt)
  {
    Uri localUri = null;
    if (paramInt == 79)
    {
      localUri = Uri.parse("file:///sdcard/qt_danmaku_crop_capture.jpg");
      if (localUri != null)
        break label36;
    }
    while (true)
    {
      return;
      if (paramInt != 83)
        break;
      localUri = Uri.parse("file:///sdcard/qt_danmaku_crop_capture.jpg");
      break;
      try
      {
        label36: Bitmap localBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), localUri);
        if (localBitmap != null)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("image", localBitmap);
          localHashMap.put("uri", localUri.getPath());
          ControllerManager.getInstance().openDanmakuSendController(localHashMap);
          return;
        }
      }
      catch (IOException localIOException)
      {
      }
    }
  }

  private void doMagic()
  {
    if (SharedCfg.getInstance().isApollo())
      setDisplayEffect();
    do
    {
      int i;
      do
      {
        return;
        if (SharedCfg.getInstance().isNewUser())
        {
          SharedCfg.getInstance().setApollo(true);
          GlobalCfg.getInstance(this).setApollo(true);
          setDisplayEffect();
          return;
        }
        i = SharedCfg.getInstance().getApolloDuration();
      }
      while (i == 0);
      int j = SharedCfg.getInstance().getApolloStartTime();
      long l1 = getTodaySec() + Long.valueOf(j).longValue();
      long l2 = Integer.valueOf(i).intValue();
      long l3 = System.currentTimeMillis() / 1000L;
      if ((l1 <= l3) && (l3 < l2 + l1))
      {
        SharedCfg.getInstance().setApollo(true);
        GlobalCfg.getInstance(this).setApollo(true);
      }
    }
    while (!SharedCfg.getInstance().isApollo());
    setDisplayEffect();
  }

  private void enableGPU()
  {
    try
    {
      getWindow().setFlags(16777216, 16777216);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private int getAlarmDayOfWeek(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar.get(7);
  }

  private long getTodaySec()
  {
    long l = System.currentTimeMillis();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(l);
    int i = localCalendar.get(11);
    int j = localCalendar.get(12);
    int k = localCalendar.get(13);
    return l / 1000L - i * 3600 - j * 60 - k;
  }

  private int getVolume()
  {
    int j = this.mAudioManager.getStreamVolume(3);
    int i = j;
    if (FMManager.getInstance().isAvailable())
    {
      i = j;
      if (!FMManager.getInstance().getName().equalsIgnoreCase("MiuiFM"));
    }
    try
    {
      i = this.mAudioManager.getStreamVolume(10);
      return i;
    }
    catch (Exception localException)
    {
      localException = localException;
      localException.printStackTrace();
      return j;
    }
    finally
    {
    }
    return j;
  }

  private void handleFloatIntent(int paramInt)
  {
    if (this.mainView != null)
      this.mainView.onEvent(this, "cancelPop", null);
    switch (paramInt)
    {
    default:
      return;
    case 32:
      ControllerManager.getInstance().openMyDownloadController("float");
      return;
    case 16:
      ControllerManager.getInstance().redirectToMyCollectionView();
      return;
    case 48:
      ControllerManager.getInstance().openPlayHistoryController();
      return;
    case 64:
      EventDispacthManager.getInstance().dispatchAction("showSettingView", null);
      return;
    case 80:
      this.playedLastChannel = false;
      handleListenNews();
      return;
    case 96:
      this.playedLastChannel = false;
      handleListenMusic();
      return;
    case 112:
    }
    this.playedLastChannel = false;
    handleListenWhatever();
  }

  private void handleIMMessage(IMMessage paramIMMessage)
  {
    if (!CloudCenter.getInstance().isLogin(false));
    while (paramIMMessage == null)
      return;
    IMAgent.getInstance().clearNotificationMsg();
    int i;
    if ((paramIMMessage.isGroupMsg()) && (paramIMMessage.mFromGroupId != null))
    {
      localList = IMContacts.getInstance().getRecentGroupContacts();
      if (localList != null)
      {
        i = 0;
        while (i < localList.size())
        {
          if (((GroupInfo)localList.get(i)).groupId.equalsIgnoreCase(paramIMMessage.mFromGroupId))
          {
            ControllerManager.getInstance().openImChatController(localList.get(i));
            return;
          }
          i += 1;
        }
      }
      ControllerManager.getInstance().openImChatController(paramIMMessage.buildGroupInfo());
    }
    while (paramIMMessage.isGroupMsg())
    {
      ControllerManager.getInstance().openImConversationsController();
      return;
    }
    List localList = IMContacts.getInstance().getRecentUserContacts();
    if (localList != null)
    {
      i = 0;
      while (i < localList.size())
      {
        if (((UserInfo)localList.get(i)).userKey.equalsIgnoreCase(paramIMMessage.mFromID))
        {
          ControllerManager.getInstance().openImChatController(localList.get(i));
          return;
        }
        i += 1;
      }
    }
    ControllerManager.getInstance().openImChatController(paramIMMessage.buildUserInfo());
  }

  private void handleListenMusic()
  {
  }

  private void handleListenNews()
  {
    ControllerManager.getInstance().redirectToPlayViewById(54, 386, 0, 0, 0, null, null);
  }

  private void handleListenWhatever()
  {
    Object localObject = null;
    if (InfoManager.getInstance().root().mBillboardNode.restoreChannelFromDB())
      localObject = InfoManager.getInstance().root().mBillboardNode.getLstBillboardChannel();
    if (localObject == null)
      handleListenMusic();
    do
    {
      int i;
      do
      {
        return;
        i = ((List)localObject).size();
      }
      while (i <= 0);
      localObject = (Node)((List)localObject).get((int)(Math.random() * 10.0D) % i);
    }
    while (localObject == null);
    ControllerManager.getInstance().openControllerByBillboardItemNode((Node)localObject);
  }

  private boolean handleMessageNew(Intent paramIntent)
  {
    if (paramIntent == null)
      return false;
    try
    {
      paramIntent = paramIntent.getExtras();
      if (paramIntent == null)
        return false;
      Object localObject = paramIntent.getString("notify_type");
      int i;
      if (localObject == null)
      {
        paramIntent = paramIntent.getBundle("fm.qingting.qtradio.float_jump");
        if (paramIntent != null)
        {
          i = paramIntent.getInt("floatjumptype");
          if (i != 0)
            handleFloatIntent(i);
        }
      }
      else
      {
        PushMessageLog.sendPushLog(this, paramIntent, "ClickGeTuiPushMsg");
        if (((String)localObject).equalsIgnoreCase("localpush_alarm"))
        {
          ControllerManager.getInstance().openAlarmControllerListOrAdd();
          QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "alarm");
          return true;
        }
        if (((String)localObject).equalsIgnoreCase("localpush_timing"))
        {
          ControllerManager.getInstance().redirectPlayViewTimer();
          QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "timing");
          return true;
        }
        if (((String)localObject).equalsIgnoreCase("localpush_replay"))
        {
          redirectToReplayView();
          QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "replay");
          return true;
        }
        if (((String)localObject).equalsIgnoreCase("localpush_liveroom"))
        {
          redirectToLiveRoom();
          QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "liveroom");
          return true;
        }
        if (((String)localObject).equalsIgnoreCase("local_umeng_reply"))
        {
          ControllerManager.getInstance().openFeedBackController();
          QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "umengreply");
          return true;
        }
        if (PushType.isPush((String)localObject))
        {
          dealPushMsg(paramIntent);
          return true;
        }
        if (((String)localObject).equalsIgnoreCase("push_live_channel"))
          PushLiveLog.sendClickNotification(this, paramIntent.getString("live_topic"), "inApp");
        String str;
        int j;
        int k;
        int m;
        int n;
        while (!((String)localObject).equalsIgnoreCase("im"))
        {
          str = paramIntent.getString("channelname");
          i = paramIntent.getInt("channelid");
          j = paramIntent.getInt("categoryid");
          k = paramIntent.getInt("programid");
          m = paramIntent.getInt("alarmType");
          n = paramIntent.getInt("contentType");
          paramIntent.getString("NOTIFICATION_TITLE");
          paramIntent = paramIntent.getString("program_name");
          if (i != 0)
            break;
          return false;
        }
        localObject = new IMMessage();
        ((IMMessage)localObject).chatType = paramIntent.getInt("chatType");
        ((IMMessage)localObject).mFromID = paramIntent.getString("fromUserId");
        ((IMMessage)localObject).mFromName = paramIntent.getString("fromName");
        ((IMMessage)localObject).mFromGroupId = paramIntent.getString("groupId");
        ((IMMessage)localObject).mMessage = paramIntent.getString("msg");
        ((IMMessage)localObject).publish = paramIntent.getLong("sendTime");
        ((IMMessage)localObject).mGroupName = paramIntent.getString("groupName");
        ((IMMessage)localObject).mFromAvatar = paramIntent.getString("fromAvatar");
        ((IMMessage)localObject).mToUserId = paramIntent.getString("toUserId");
        ((IMMessage)localObject).mGender = paramIntent.getString("fromGender");
        handleIMMessage((IMMessage)localObject);
        return true;
        this.playedLastChannel = false;
        if (((String)localObject).equalsIgnoreCase("reserve"))
        {
          InfoManager.getInstance().root().setFromType(RootNode.FromType.RESERVE);
          PlayerAgent.getInstance().addPlaySource(8);
          if (m == 1)
          {
            ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), k, m, 0, null, null);
            break label1135;
          }
          ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), 0, 0, 0, null, null);
          break label1135;
        }
        if (((String)localObject).equalsIgnoreCase("alarm"))
        {
          if (this.mAudioManager.getStreamVolume(3) < 10)
            this.mAudioManager.setStreamVolume(3, 10, 3);
          PlayerAgent.getInstance().addPlaySource(12);
          InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARMPLAY);
          if (k == 0)
            if (!InfoManager.getInstance().isNetworkAvailable())
            {
              InfoManager.getInstance().root().mRingToneInfoNode.setAvaliableRingId("0");
              PlayerAgent.getInstance().playRingTone(InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById("0"));
            }
          while (true)
          {
            InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
            InfoManager.getInstance().root().mRingToneInfoNode.setRingCatId(Integer.valueOf(j).intValue());
            InfoManager.getInstance().root().mRingToneInfoNode.setRingChannelId(Integer.valueOf(i).intValue());
            return true;
            InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
            ControllerManager.getInstance().openPlayViewForAlarm(j, i, k, Integer.valueOf(m).intValue());
            return true;
            InfoManager.getInstance().root().mRingToneInfoNode.setAvaliableRingId(String.valueOf(k));
            PlayerAgent.getInstance().playRingTone(InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById(String.valueOf(k)));
          }
        }
        if (((String)localObject).equalsIgnoreCase("pullmsg"))
        {
          if ((Integer.valueOf(n).intValue() == 1) || (Integer.valueOf(n).intValue() == 3) || (Integer.valueOf(n).intValue() == 2))
          {
            paramIntent = InfoManager.getInstance().root().getPullNodes();
            if ((paramIntent != null) && (paramIntent.size() > 0))
            {
              paramIntent = (Node)paramIntent.get(0);
              if ((paramIntent != null) && (paramIntent.nodeName.equalsIgnoreCase("program")))
              {
                ControllerManager.getInstance().setChannelSource(0);
                ControllerManager.getInstance().openChannelDetailControllerAndPlay((ProgramNode)paramIntent);
              }
            }
            while (true)
            {
              InfoManager.getInstance().root().delPullNodes();
              break;
              ControllerManager.getInstance().redirectToPlayViewById(j, i, k, 1, 0, null, null);
              continue;
              ControllerManager.getInstance().redirectToPlayViewById(j, i, k, 1, 0, null, null);
            }
          }
          if (Integer.valueOf(n).intValue() != 5)
            break label1140;
          ControllerManager.getInstance().redirectToPlayViewById(j, i, k, 0, 0, null, null);
          break label1140;
        }
        if (((String)localObject).equalsIgnoreCase("channelUpdate"))
        {
          ControllerManager.getInstance().setChannelSource(0);
          ControllerManager.getInstance().openChannelDetailController(j, i, k, 1, null, true);
          ClickNotificationLog.sendClickNotification(j, i, str, k, paramIntent, String.valueOf(2), String.valueOf(InfoManager.getInstance().getNetWorkType()), this);
          break label1135;
        }
        if (((String)localObject).equalsIgnoreCase("continueListen"))
        {
          ControllerManager.getInstance().setChannelSource(0);
          ControllerManager.getInstance().openChannelDetailController(j, i, k, 1, null, true);
          break label1135;
        }
        InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
        ControllerManager.getInstance().redirectToPlayViewById(j, i, k, 0, 0, null, null);
        break label1135;
      }
      return false;
      label1135: return true;
    }
    catch (Exception paramIntent)
    {
      return false;
    }
    label1140: return true;
  }

  private boolean handleMessageOnCreate(Intent paramIntent)
  {
    if (paramIntent == null)
      return false;
    if ((paramIntent.getAction() != null) && ((paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY")) || (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_NEXT")) || (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_PRE"))))
    {
      MobclickAgent.onEvent(this, "fujia", "startActivity");
      this.mCarPlay = true;
      return true;
    }
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null)
      return false;
    String str1 = paramIntent.getString("notify_type");
    int i;
    if (str1 == null)
    {
      paramIntent = paramIntent.getBundle("fm.qingting.qtradio.float_jump");
      if (paramIntent != null)
      {
        i = paramIntent.getInt("floatjumptype");
        if (i != 0)
          handleFloatIntent(i);
      }
      return false;
    }
    if (str1.equalsIgnoreCase("shield"))
    {
      MobclickAgent.onEvent(this, "shield");
      quit();
      return false;
    }
    PushMessageLog.sendPushLog(this, paramIntent, "ClickGeTuiPushMsg");
    setIntent(null);
    if (str1.equalsIgnoreCase("localpush_alarm"))
    {
      ControllerManager.getInstance().openAlarmControllerListOrAdd();
      QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "alarm");
      return true;
    }
    if (str1.equalsIgnoreCase("localpush_timing"))
    {
      ControllerManager.getInstance().redirectPlayViewTimer();
      QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "timing");
      return true;
    }
    if (str1.equalsIgnoreCase("localpush_replay"))
    {
      redirectToReplayView();
      QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "replay");
      return true;
    }
    if (str1.equalsIgnoreCase("localpush_liveroom"))
    {
      redirectToLiveRoom();
      QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "liveroom");
      return true;
    }
    if (str1.equalsIgnoreCase("local_umeng_reply"))
    {
      ControllerManager.getInstance().openFeedBackController();
      QTMSGManage.getInstance().sendStatistcsMessage("LocalPushMsg", "umengreply");
      return true;
    }
    if (PushType.isPush(str1))
    {
      dealPushMsg(paramIntent);
      return true;
    }
    if (str1.equalsIgnoreCase("push_live_channel"))
      PushLiveLog.sendClickNotification(this, paramIntent.getString("live_topic"), "launchApp");
    String str2;
    int j;
    int k;
    int m;
    int n;
    do
    {
      str2 = paramIntent.getString("program_name");
      localObject = paramIntent.getString("channelname");
      i = paramIntent.getInt("channelid");
      j = paramIntent.getInt("categoryid");
      k = paramIntent.getInt("programid");
      m = paramIntent.getInt("alarmType");
      n = paramIntent.getInt("contentType");
      paramIntent.getString("NOTIFICATION_TITLE");
      if (i != 0)
        break;
      return false;
      if (str1.equalsIgnoreCase("push_activity"))
      {
        localObject = new ActivityNode();
        ((ActivityNode)localObject).contentUrl = paramIntent.getString("ACTIVITY_CONTENTURL");
        ((ActivityNode)localObject).titleIconUrl = paramIntent.getString("ACTIVITY_TITLEICON");
        ((ActivityNode)localObject).infoUrl = paramIntent.getString("ACTIVITY_INFOURL");
        ControllerManager.getInstance().redirectToActivityViewByNode((Node)localObject);
        return true;
      }
    }
    while (!str1.equalsIgnoreCase("im"));
    this.playedLastChannel = false;
    Object localObject = new IMMessage();
    ((IMMessage)localObject).chatType = paramIntent.getInt("chatType");
    ((IMMessage)localObject).mFromID = paramIntent.getString("fromUserId");
    ((IMMessage)localObject).mFromName = paramIntent.getString("fromName");
    ((IMMessage)localObject).mFromGroupId = paramIntent.getString("groupId");
    ((IMMessage)localObject).mMessage = paramIntent.getString("msg");
    ((IMMessage)localObject).publish = paramIntent.getLong("sendTime");
    ((IMMessage)localObject).mGroupName = paramIntent.getString("groupName");
    ((IMMessage)localObject).mFromAvatar = paramIntent.getString("fromAvatar");
    ((IMMessage)localObject).mToUserId = paramIntent.getString("toUserId");
    ((IMMessage)localObject).mGender = paramIntent.getString("fromGender");
    handleIMMessage((IMMessage)localObject);
    return true;
    this.playedLastChannel = false;
    if (str1.equalsIgnoreCase("reserve"))
    {
      InfoManager.getInstance().root().setFromType(RootNode.FromType.RESERVE);
      PlayerAgent.getInstance().addPlaySource(8);
      if (m == 1)
        ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), k, m, 0, null, null);
      while (true)
      {
        return true;
        ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), 0, 0, 0, null, null);
      }
    }
    if (str1.equalsIgnoreCase("alarm"))
    {
      if (isAlarmFailed())
      {
        this.playedLastChannel = true;
        return false;
      }
      PlayerAgent.getInstance().addPlaySource(12);
      if (!InfoManager.getInstance().isNetworkAvailable())
      {
        paramIntent = "" + "offline_";
        n = Calendar.getInstance().get(11);
        paramIntent = paramIntent + String.valueOf(n);
        QTMSGManage.getInstance().sendStatistcsMessage("StartActivityByClock", paramIntent);
        if (localObject != null)
          QTMSGManage.getInstance().sendStatistcsMessage("ClockChannel", (String)localObject);
        if (this.mAudioManager.getStreamVolume(3) < 7)
          this.mAudioManager.setStreamVolume(3, 10, 3);
        InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARMPLAY);
        if (k != 0)
          break label1122;
        InfoManager.getInstance().root().mRingToneInfoNode.setAvaliableRingId("0");
        paramIntent = InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById("0");
        if (paramIntent != null)
        {
          SystemPlayer.getInstance().setSource(paramIntent.getListenOnLineUrl());
          SystemPlayer.getInstance().play();
        }
        ControllerManager.getInstance().openPlayViewForAlarm(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), k, Integer.valueOf(m).intValue());
      }
      while (true)
      {
        InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
        InfoManager.getInstance().root().mRingToneInfoNode.setRingCatId(Integer.valueOf(j).intValue());
        InfoManager.getInstance().root().mRingToneInfoNode.setRingProgramId(k);
        InfoManager.getInstance().root().mRingToneInfoNode.setRingChannelId(Integer.valueOf(i).intValue());
        InfoManager.getInstance().root().mRingToneInfoNode.setRingChannelType(Integer.valueOf(m).intValue());
        return true;
        if (MobileState.getNetWorkType(this) == 1)
        {
          paramIntent = "" + "wifi_";
          break;
        }
        paramIntent = "" + "mobile_";
        break;
        label1122: InfoManager.getInstance().root().mRingToneInfoNode.setRingProgramId(k);
        paramIntent = InfoManager.getInstance().root().mRingToneInfoNode.getRingNodeById("0");
        if (paramIntent != null)
        {
          SystemPlayer.getInstance().setSource(paramIntent.getListenOnLineUrl());
          SystemPlayer.getInstance().play();
        }
        ControllerManager.getInstance().openPlayViewForAlarm(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), k, Integer.valueOf(m).intValue());
      }
    }
    if (str1.equalsIgnoreCase("localrecommend"))
    {
      if (localObject != null)
        MobclickAgent.onEvent(this, "StartActivityByLocalRecommend2", (String)localObject);
      InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
      ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), 0, 0, 0, null, null);
      return true;
    }
    if (str1.equalsIgnoreCase("pullmsg"))
    {
      if ((Integer.valueOf(n).intValue() == 1) || (Integer.valueOf(n).intValue() == 3) || (Integer.valueOf(n).intValue() == 2))
      {
        paramIntent = InfoManager.getInstance().root().getPullNodes();
        if ((paramIntent != null) && (paramIntent.size() > 0))
        {
          paramIntent = (Node)paramIntent.get(0);
          if ((paramIntent != null) && (paramIntent.nodeName.equalsIgnoreCase("program")))
          {
            ControllerManager.getInstance().setChannelSource(0);
            ControllerManager.getInstance().openChannelDetailControllerAndPlay((ProgramNode)paramIntent);
            InfoManager.getInstance().root().delPullNodes();
          }
        }
      }
      while (true)
      {
        return true;
        ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), Integer.valueOf(k).intValue(), 1, 0, null, null);
        break;
        ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), Integer.valueOf(k).intValue(), 1, 0, null, null);
        continue;
        if (Integer.valueOf(n).intValue() == 5)
          ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), Integer.valueOf(k).intValue(), 0, 0, null, null);
      }
    }
    if (str1.equalsIgnoreCase("channelUpdate"))
    {
      ControllerManager.getInstance().setChannelSource(0);
      ControllerManager.getInstance().openChannelDetailController(j, i, k, 1, null, true);
      ClickNotificationLog.sendClickNotification(j, i, (String)localObject, k, str2, String.valueOf(1), String.valueOf(InfoManager.getInstance().getNetWorkType()), this);
    }
    while (true)
    {
      InfoManager.getInstance().root().setFromType(RootNode.FromType.NOTIFICATION);
      ControllerManager.getInstance().redirectToPlayViewById(Integer.valueOf(j).intValue(), Integer.valueOf(i).intValue(), Integer.valueOf(k).intValue(), Integer.valueOf(m).intValue(), 0, null, null);
      break;
      if (str1.equalsIgnoreCase("continueListen"))
      {
        ControllerManager.getInstance().setChannelSource(0);
        ControllerManager.getInstance().openChannelDetailController(j, i, k, 1, null, true);
      }
    }
  }

  private void initClientID()
  {
    loadClientID();
  }

  private void initConfig()
  {
    InfoManager.getInstance().setContext(this.mContext);
    InfoManager.getInstance().init();
    String str = DeviceInfo.getUniqueId(this.mContext);
    InfoManager.getInstance().setDeviceId(str);
    enableGPU();
    SharedCfg.getInstance().init(this);
    GlobalCfg.getInstance(this).setUseCache(true);
    DBManager.getInstance().init(this);
    NetWorkManage.getInstance().init(this);
    NetWorkManage.getInstance().register();
    this.tracker = GoogleAnalyticsTracker.getInstance();
    this.tracker.startNewSession("UA-30488419-1", 600, this);
    MobclickAgent.updateOnlineConfig(this);
    MobclickAgent.setSessionContinueMillis(300000L);
    if (SharedCfg.getInstance().isNewUser())
      SharedCfg.getInstance().setBootstrapMax(10);
    doMagic();
    TCAgent.init(this);
    TCAgent.LOG_ON = false;
    QTMSGManage.getInstance().initContext(this);
    SystemInfo.getInstance().init(this);
    float f = SystemInfo.getInstance().getMaxCpuFreq() / 1000.0F;
    if (f < 600.0F)
      getWindow().setFormat(4);
    while (true)
    {
      ScreenConfiguration.setScreenInfo(this.mContext);
      mobAgentOption.getInstance().init(this);
      return;
      if (f < 900.0F)
        getWindow().setFormat(4);
      else
        getWindow().setFormat(1);
    }
  }

  private void initDataSources()
  {
    DataManager.getInstance().addDataSource(NetDS.getInstance());
    NetDS.getInstance().addParser(new NetParser());
    DataManager.getInstance().addDataSource(MediaCenterDS.getInstance());
    DataManager.getInstance().addDataSource(AttributesDS.getInstance());
    DataManager.getInstance().addDataSource(NotifyDS.getInstance());
    DataManager.getInstance().addDataSource(ProfileDS.getInstance());
    DataManager.getInstance().addDataSourceProxy(ApiSign.getInstance());
    DataManager.getInstance().addDataSource(FavouriteChannelDS.getInstance());
    DataManager.getInstance().addDataSource(CategoryNodeDS.getInstance());
    DataManager.getInstance().addDataSource(PlayHistoryDS.getInstance());
    DataManager.getInstance().addDataSource(ChannelNodesDS.getInstance());
    DataManager.getInstance().addDataSource(RadioNodesDS.getInstance());
    DataManager.getInstance().addDataSource(AlarmDS.getInstance());
    DataManager.getInstance().addDataSource(CommonDS.getInstance());
    DataManager.getInstance().addDataSource(SocialUserProfileDS.getInstance());
    DataManager.getInstance().addDataSource(UserInfoDS.getInstance());
    DataManager.getInstance().addDataSource(ProgramNodesDS.getInstance());
    DataManager.getInstance().addDataSource(ProgramNodesRevDS.getInstance());
    DataManager.getInstance().addDataSource(PlayedMetaDS.getInstance());
    DataManager.getInstance().addDataSource(PlayListDS.getInstance());
    DataManager.getInstance().addDataSource(BillboardNodeDS.getInstance());
    DataManager.getInstance().addDataSource(DownloadingProgramDS.getInstance());
    DataManager.getInstance().addDataSource(PreDownloadingProgramDS.getInstance());
    DataManager.getInstance().addDataSource(RecommendCategoryNodeDS.getInstance());
    DataManager.getInstance().addDataSource(FreqChannelsDS.getInstance());
    DataManager.getInstance().addDataSource(ReserveProgramDS.getInstance());
    DataManager.getInstance().addDataSource(PullNodeDS.getInstance());
    DataManager.getInstance().addDataSource(IMContactsDS.getInstance());
    DataManager.getInstance().addDataSource(IMUserInfoDS.getInstance());
    DataManager.getInstance().addDataSource(IMDatabaseDS.getInstance());
    DataManager.getInstance().addDataSource(PodcasterDS.getInstance());
    DataManager.getInstance().addDataSource(MyPodcasterDS.getInstance());
    DataManager.getInstance().addDataSource(H5BeanDS.getInstance());
  }

  private void initIRE()
  {
    try
    {
      IRMonitor.getInstance(this).Init("833c6d6eb8031de1", "qingtingFM_android", false);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void initOthers()
  {
    WeiboAgent.getInstance().setContext(this);
    WeiboAgent.getInstance().init();
    WeiboChat.getInstance().init();
    TencentAgent.getInstance().init(this, "100387802");
    QQAgent.getInstance().init(this);
    WeixinAgent.getInstance().init(this);
    SharedCfg.getInstance().setVertion(this);
    SharedCfg.getInstance().setAppStartCount();
    SharedCfg.getInstance().setAppLocalCount();
    StatisticsFMManage.getInstance(this).setVertion(this);
    SharedCfg.getInstance().setlocalNotice("yes");
    SharedCfg.getInstance().setFMPlayIndex("");
    if (!InfoManager.getInstance().enableGenerateDB())
      OfflineManager.loadOfflineData(this);
    RingManager.loadRings(this);
  }

  // ERROR //
  private void initServiceAndView()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 2315	fm/qingting/qtradio/QTRadioActivity:registerServiceCommandReceiver	()V
    //   4: ldc2_w 2316
    //   7: lstore 4
    //   9: aload_0
    //   10: new 897	android/content/Intent
    //   13: dup
    //   14: aload_0
    //   15: ldc_w 2319
    //   18: invokespecial 2322	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   21: invokevirtual 2326	fm/qingting/qtradio/QTRadioActivity:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   24: pop
    //   25: lload 4
    //   27: lconst_1
    //   28: lsub
    //   29: lstore 4
    //   31: invokestatic 1770	fm/qingting/qtradio/fm/PlayerAgent:getInstance	()Lfm/qingting/qtradio/fm/PlayerAgent;
    //   34: aload_0
    //   35: invokevirtual 2327	fm/qingting/qtradio/fm/PlayerAgent:init	(Landroid/content/Context;)V
    //   38: invokestatic 1770	fm/qingting/qtradio/fm/PlayerAgent:getInstance	()Lfm/qingting/qtradio/fm/PlayerAgent;
    //   41: aload_0
    //   42: invokevirtual 2328	fm/qingting/qtradio/fm/PlayerAgent:setEventHandler	(Lfm/qingting/framework/event/IEventHandler;)V
    //   45: lload 4
    //   47: lconst_1
    //   48: lsub
    //   49: lstore 4
    //   51: lload 4
    //   53: lconst_1
    //   54: lsub
    //   55: lstore 6
    //   57: lload 6
    //   59: lstore 4
    //   61: lload 6
    //   63: lconst_0
    //   64: lcmp
    //   65: ifgt -56 -> 9
    //   68: lload 6
    //   70: lconst_0
    //   71: lcmp
    //   72: ifne +7 -> 79
    //   75: aload_0
    //   76: invokespecial 2330	fm/qingting/qtradio/QTRadioActivity:ShowDialogReportToUser	()V
    //   79: invokestatic 1536	fm/qingting/qtradio/im/IMAgent:getInstance	()Lfm/qingting/qtradio/im/IMAgent;
    //   82: aload_0
    //   83: invokevirtual 2333	fm/qingting/qtradio/im/IMAgent:initService	(Landroid/content/Context;)V
    //   86: aload_0
    //   87: aload_0
    //   88: ldc_w 2335
    //   91: invokevirtual 1045	fm/qingting/qtradio/QTRadioActivity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   94: checkcast 1464	android/media/AudioManager
    //   97: putfield 1462	fm/qingting/qtradio/QTRadioActivity:mAudioManager	Landroid/media/AudioManager;
    //   100: aload_0
    //   101: iconst_3
    //   102: invokevirtual 2338	fm/qingting/qtradio/QTRadioActivity:setVolumeControlStream	(I)V
    //   105: aload_0
    //   106: invokespecial 2341	fm/qingting/qtradio/QTRadioActivity:registerMediaButton	()V
    //   109: aload_0
    //   110: invokestatic 2346	fm/qingting/qtradio/headset/HeadSet:getInstance	(Landroid/content/Context;)Lfm/qingting/qtradio/headset/HeadSet;
    //   113: aload_0
    //   114: invokevirtual 2349	fm/qingting/qtradio/headset/HeadSet:registerReceiver	(Landroid/content/Context;)V
    //   117: invokestatic 2354	fm/qingting/qtradio/remotecontrol/RemoteControl:getInstance	()Lfm/qingting/qtradio/remotecontrol/RemoteControl;
    //   120: aload_0
    //   121: invokevirtual 2357	fm/qingting/qtradio/remotecontrol/RemoteControl:registerRemoteControl	(Landroid/content/Context;)V
    //   124: aload_0
    //   125: invokespecial 2359	fm/qingting/qtradio/QTRadioActivity:acquireUmengConfig	()V
    //   128: aload_0
    //   129: new 1485	fm/qingting/qtradio/view/MainView
    //   132: dup
    //   133: aload_0
    //   134: invokespecial 2360	fm/qingting/qtradio/view/MainView:<init>	(Landroid/content/Context;)V
    //   137: putfield 237	fm/qingting/qtradio/QTRadioActivity:mainView	Lfm/qingting/qtradio/view/MainView;
    //   140: aload_0
    //   141: getfield 237	fm/qingting/qtradio/QTRadioActivity:mainView	Lfm/qingting/qtradio/view/MainView;
    //   144: aload_0
    //   145: invokevirtual 2361	fm/qingting/qtradio/view/MainView:setEventHandler	(Lfm/qingting/framework/event/IEventHandler;)V
    //   148: aload_0
    //   149: getfield 1991	fm/qingting/qtradio/QTRadioActivity:tracker	Lcom/google/android/apps/analytics/GoogleAnalyticsTracker;
    //   152: ldc_w 2363
    //   155: invokevirtual 2366	com/google/android/apps/analytics/GoogleAnalyticsTracker:trackPageView	(Ljava/lang/String;)V
    //   158: aload_0
    //   159: invokespecial 2368	fm/qingting/qtradio/QTRadioActivity:initClientID	()V
    //   162: invokestatic 1472	fm/qingting/qtradio/fmdriver/FMManager:getInstance	()Lfm/qingting/qtradio/fmdriver/FMManager;
    //   165: invokevirtual 1475	fm/qingting/qtradio/fmdriver/FMManager:isAvailable	()Z
    //   168: ifeq +191 -> 359
    //   171: invokestatic 1178	fm/qingting/qtradio/logger/QTLogger:getInstance	()Lfm/qingting/qtradio/logger/QTLogger;
    //   174: ldc_w 403
    //   177: invokevirtual 2371	fm/qingting/qtradio/logger/QTLogger:setFMAvailable	(Ljava/lang/String;)V
    //   180: aload_0
    //   181: invokespecial 2374	fm/qingting/qtradio/QTRadioActivity:setWifiPolicy	()V
    //   184: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   187: invokevirtual 2377	fm/qingting/qtradio/model/InfoManager:startLocate	()V
    //   190: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   193: invokevirtual 2380	fm/qingting/qtradio/model/InfoManager:loadADLoc	()V
    //   196: aload_0
    //   197: invokespecial 2382	fm/qingting/qtradio/QTRadioActivity:cancelSystemAlarm	()V
    //   200: aload_0
    //   201: invokestatic 370	fm/qingting/qtradio/model/GlobalCfg:getInstance	(Landroid/content/Context;)Lfm/qingting/qtradio/model/GlobalCfg;
    //   204: invokestatic 1451	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   207: bipush 6
    //   209: invokevirtual 1459	java/util/Calendar:get	(I)I
    //   212: invokevirtual 2385	fm/qingting/qtradio/model/GlobalCfg:setOpenDay	(I)V
    //   215: aload_0
    //   216: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   219: invokevirtual 1213	fm/qingting/qtradio/model/InfoManager:getPushSwitch	()Z
    //   222: putfield 150	fm/qingting/qtradio/QTRadioActivity:mNotifySwitchState	Z
    //   225: aload_0
    //   226: getfield 161	fm/qingting/qtradio/QTRadioActivity:wakeHandler	Landroid/os/Handler;
    //   229: astore_1
    //   230: aload_0
    //   231: getfield 166	fm/qingting/qtradio/QTRadioActivity:timingWake	Ljava/lang/Runnable;
    //   234: astore_2
    //   235: getstatic 2390	fm/qingting/utils/LifeTime:isFirstLaunchAfterInstall	Z
    //   238: ifeq +133 -> 371
    //   241: ldc2_w 2391
    //   244: lstore 4
    //   246: aload_1
    //   247: aload_2
    //   248: lload 4
    //   250: invokevirtual 2396	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   253: pop
    //   254: return
    //   255: astore_1
    //   256: aload_0
    //   257: ldc_w 2398
    //   260: aload_0
    //   261: invokestatic 1959	fm/qingting/utils/DeviceInfo:getUniqueId	(Landroid/content/Context;)Ljava/lang/String;
    //   264: invokestatic 1868	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   267: aload_1
    //   268: invokevirtual 2399	java/lang/SecurityException:printStackTrace	()V
    //   271: aload_0
    //   272: ldc_w 2398
    //   275: invokestatic 2402	com/umeng/analytics/MobclickAgent:reportError	(Landroid/content/Context;Ljava/lang/String;)V
    //   278: goto -247 -> 31
    //   281: astore_1
    //   282: aload_0
    //   283: ldc_w 2398
    //   286: aload_0
    //   287: invokestatic 1959	fm/qingting/utils/DeviceInfo:getUniqueId	(Landroid/content/Context;)Ljava/lang/String;
    //   290: invokestatic 1868	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   293: aload_1
    //   294: invokevirtual 1108	java/lang/Exception:printStackTrace	()V
    //   297: aload_0
    //   298: ldc_w 2398
    //   301: invokestatic 2402	com/umeng/analytics/MobclickAgent:reportError	(Landroid/content/Context;Ljava/lang/String;)V
    //   304: goto -273 -> 31
    //   307: astore_1
    //   308: aload_0
    //   309: ldc_w 2404
    //   312: aload_0
    //   313: invokestatic 1959	fm/qingting/utils/DeviceInfo:getUniqueId	(Landroid/content/Context;)Ljava/lang/String;
    //   316: invokestatic 1868	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   319: aload_1
    //   320: invokevirtual 2399	java/lang/SecurityException:printStackTrace	()V
    //   323: aload_0
    //   324: ldc_w 2404
    //   327: invokestatic 2402	com/umeng/analytics/MobclickAgent:reportError	(Landroid/content/Context;Ljava/lang/String;)V
    //   330: goto -279 -> 51
    //   333: astore_1
    //   334: aload_0
    //   335: ldc_w 2404
    //   338: aload_0
    //   339: invokestatic 1959	fm/qingting/utils/DeviceInfo:getUniqueId	(Landroid/content/Context;)Ljava/lang/String;
    //   342: invokestatic 1868	com/umeng/analytics/MobclickAgent:onEvent	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   345: aload_1
    //   346: invokevirtual 1108	java/lang/Exception:printStackTrace	()V
    //   349: aload_0
    //   350: ldc_w 2404
    //   353: invokestatic 2402	com/umeng/analytics/MobclickAgent:reportError	(Landroid/content/Context;Ljava/lang/String;)V
    //   356: goto -305 -> 51
    //   359: invokestatic 1178	fm/qingting/qtradio/logger/QTLogger:getInstance	()Lfm/qingting/qtradio/logger/QTLogger;
    //   362: ldc_w 620
    //   365: invokevirtual 2371	fm/qingting/qtradio/logger/QTLogger:setFMAvailable	(Ljava/lang/String;)V
    //   368: goto -188 -> 180
    //   371: aload_0
    //   372: getfield 139	fm/qingting/qtradio/QTRadioActivity:mShowAdvertisement	Z
    //   375: ifeq +14 -> 389
    //   378: sipush 4000
    //   381: istore_3
    //   382: iload_3
    //   383: i2l
    //   384: lstore 4
    //   386: goto -140 -> 246
    //   389: sipush 1500
    //   392: istore_3
    //   393: goto -11 -> 382
    //   396: astore_1
    //   397: goto -311 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   9	25	255	java/lang/SecurityException
    //   9	25	281	java/lang/Exception
    //   31	45	307	java/lang/SecurityException
    //   31	45	333	java/lang/Exception
    //   79	86	396	java/lang/Exception
  }

  private boolean isAlarmFailed()
  {
    return (!GlobalCfg.getInstance(this.mContext).getAlarmShouted()) && (GlobalCfg.getInstance(this.mContext).getAlarmAbsoluteTime() < System.currentTimeMillis());
  }

  private void loadClientID()
  {
    String str = DeviceInfo.getUniqueId(this);
    HashMap localHashMap = new HashMap();
    localHashMap.put("deviceid", str);
    DataManager.getInstance().getData("bootstrap", this, localHashMap);
  }

  private void playAtBack()
  {
    this.mainView.update("cancelBubble", null);
    moveTaskToBack(true);
  }

  private void playLastChannel()
  {
    int i3 = 1;
    boolean bool = InfoManager.getInstance().getAutoPlayAfterStart();
    int i;
    Object localObject1;
    int n;
    int i1;
    int m;
    int j;
    if ((this.mCarPlay) || (bool))
    {
      i = 1;
      if (this.mCarPlay)
        this.playedLastChannel = true;
      if (this.playedLastChannel)
      {
        localObject1 = SharedCfg.getInstance().getLastPlayInfo();
        if (localObject1 == null)
          break label701;
        localObject1 = ((String)localObject1).split("_");
        if (localObject1 == null)
          break label701;
        n = Integer.valueOf(localObject1[0]).intValue();
        i1 = Integer.valueOf(localObject1[1]).intValue();
        m = Integer.valueOf(localObject1[2]).intValue();
        j = Integer.valueOf(localObject1[3]).intValue();
      }
    }
    while (true)
    {
      int k = i1;
      if (i1 == 0)
      {
        if (InfoManager.getInstance().getDefaultCollectionChannelId() == 0)
          break label687;
        localObject1 = ChannelHelper.getInstance().getChannel(InfoManager.getInstance().getDefaultCollectionChannelId(), 1);
        if (localObject1 == null)
          break label687;
        k = ((ChannelNode)localObject1).channelId;
        n = ((ChannelNode)localObject1).categoryId;
        InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)localObject1, null);
        j = 1;
      }
      while (true)
      {
        localObject1 = PlayerAgent.getInstance().getSource();
        int i2;
        if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
        {
          i1 = PlayerAgent.getInstance().queryPosition();
          PlayerAgent.getInstance().queryIsLiveStream();
          if (i1 > 5)
          {
            PlayerAgent.getInstance().recoverSource((String)localObject1);
            PlayerAgent.getInstance().recoverRecvPlay(true);
            PlayerAgent.getInstance().setCurrPlayState(4096);
            PlayerAgent.getInstance().dispatchPlayStateInFM(4096);
          }
          m = PlayerAgent.getInstance().getProgramPlayInfo(4);
          i2 = 1;
        }
        while (true)
        {
          label256: if (i2 != 0)
          {
            i = i3;
            if (this.mainView != null)
            {
              this.mainView.update("removeShare", null);
              i = i3;
            }
          }
          while (true)
          {
            label314: Object localObject2;
            if (n == DownLoadInfoNode.mDownloadId)
            {
              localObject1 = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(Integer.valueOf(k).intValue());
              localObject2 = localObject1;
              if (localObject1 == null)
                localObject2 = ChannelHelper.getInstance().getChannel(386, 0);
              if (localObject2 != null)
              {
                InfoManager.getInstance().root().setPlayingChannelNode((Node)localObject2);
                if (((ChannelNode)localObject2).channelType != 0)
                  break label437;
                InfoManager.getInstance().root().setWillPlayNode((Node)localObject2);
                label362: if (i2 != 0)
                  break label628;
                if (i != 0)
                  PlayerAgent.getInstance().play(InfoManager.getInstance().root().getCurrentPlayingNode());
                PlayerAgent.getInstance().addPlaySource(13);
              }
            }
            label437: label591: label628: 
            do
            {
              do
              {
                return;
                i = 0;
                break;
                if (j != 0)
                  break label678;
                m = 0;
                i1 = 0;
                i2 = 0;
                break label256;
                localObject1 = ChannelHelper.getInstance().getChannel(Integer.valueOf(k).intValue(), j);
                break label314;
                localObject1 = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayNode(Integer.valueOf(k).intValue(), m);
                if (localObject1 != null)
                {
                  InfoManager.getInstance().root().setWillPlayNode((Node)localObject1);
                  break label362;
                }
                localObject1 = PlayListManager.getInstance().getPlayList();
                if ((localObject1 != null) && (((List)localObject1).size() > 0))
                {
                  if (m != 0)
                  {
                    j = 0;
                    while (true)
                    {
                      if (j >= ((List)localObject1).size())
                        break label591;
                      if ((((Node)((List)localObject1).get(j)).nodeName.equalsIgnoreCase("program")) && (((ProgramNode)((List)localObject1).get(j)).id == m))
                      {
                        InfoManager.getInstance().root().setWillPlayNode((Node)((List)localObject1).get(j));
                        break;
                      }
                      j += 1;
                    }
                    break label362;
                  }
                  InfoManager.getInstance().root().setWillPlayNode((Node)((List)localObject1).get(0));
                  break label362;
                }
                InfoManager.getInstance().root().setWillPlayNode((Node)localObject2);
                break label362;
                PlayerAgent.getInstance().addPlaySource(0);
              }
              while (i1 <= 5);
              i = PlayerAgent.getInstance().queryDuration();
            }
            while (i <= 0);
            PlayerAgent.getInstance().setLiveStream(false);
            PlayProcessSyncUtil.getInstance().setTotalLength(i);
            PlayProcessSyncUtil.getInstance().setCurrentPlayTime(i1);
            return;
          }
          label678: i1 = 0;
          i2 = 0;
        }
        label687: j = 0;
        k = 386;
        n = 5;
      }
      label701: j = 0;
      m = 0;
      i1 = 0;
      n = 0;
    }
  }

  private void quit()
  {
    SharedCfg.getInstance().setNewUser(false);
    if ((this.mNotifySwitchState) && (!InfoManager.getInstance().getPushSwitch()))
      QTMSGManage.getInstance().sendStatistcsMessage("turnOffNotify");
    if (this.mShowAdvertisement)
    {
      AdvertisementItemNode localAdvertisementItemNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdvertisement();
      if (localAdvertisementItemNode != null)
      {
        localAdvertisementItemNode.getImage();
        localAdvertisementItemNode.onShow();
      }
    }
    InfoManager.getInstance().clearAllChannelUpdates();
    addShortCut(this, "fm.qingting.qtradio");
    RecommendStatisticsUtil.INSTANCE.sendLog();
    setSystemAlarm();
    checkHasReserveTask();
    try
    {
      stopService(new Intent(this, QTRadioService.class));
      stopService(new Intent(this, NotificationService.class));
      if ((!InfoManager.getInstance().root().mShareInfoNode.hasUpdate()) && (InfoManager.getInstance().hasWifi()))
        SharedCfg.getInstance().setRecommendShareUpdate(false);
      WebViewPlayer.getInstance().release();
      IMAgent.getInstance().storeUnReadMsgCnt();
    }
    catch (Exception localException2)
    {
      try
      {
        while (true)
        {
          stopService(new Intent(this, QTRadioAdvertService.class));
          label185: stopQuitTimer();
          this.playedLastChannel = true;
          try
          {
            InfoManager.getInstance().root().mPersonalCenterNode.alarmInfoNode.WriteToDB();
            InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.WriteToDB();
            InfoManager.getInstance().startNotificationInQuit();
            InfoManager.getInstance().sendAvailAlarmCnt();
            InfoManager.getInstance().root().mDownLoadInfoNode.saveDownloading();
            InfoManager.getInstance().saveMsgSeq();
            PlayerAgent.getInstance().sendBufferLog();
            label260: if (this.hasOpenSpeaker)
            {
              this.mAudioManager.setSpeakerphoneOn(false);
              this.hasOpenSpeaker = false;
            }
            if (FMManager.getInstance().isAvailable());
            try
            {
              if (FMManager.getInstance().isOn())
                FMManager.getInstance().turnOff();
              InfoManager.getInstance().stopLocate();
              InfoManager.getInstance().exitLiveRoom();
              PlayerAgent.getInstance().reset();
              sendBroadcastByAlarm();
              LifeTime.setLastQuitTimeMs(DateUtil.getCurrentMillis(), this);
              GlobalCfg.getInstance(this).saveValueToDB();
              GlobalCfg.getInstance(this).setUseCache(false);
              FMcontrol.getInstance().unregisterHeadsetPlugReceiver(this);
            }
            catch (Exception localException2)
            {
              try
              {
                InfoManager.getInstance().savePersonalOtherToDB();
                EventDispacthManager.getInstance().removeAll();
                InfoManager.getInstance().reset();
                HeadSet.getInstance(this).unRegisterReceiver(this);
                RemoteControl.getInstance().unregisterRemoteControl(this);
                MobclickAgent.onKillProcess(this);
                label392: restoreWifiPolicy();
                finish();
                Process.killProcess(Process.myPid());
                return;
                localException1 = localException1;
                localException1.printStackTrace();
                MobclickAgent.reportError(this, "stopService_failed");
                continue;
                localException2 = localException2;
                localException2.printStackTrace();
              }
              catch (Exception localException3)
              {
                break label392;
              }
            }
          }
          catch (Exception localException4)
          {
            break label260;
          }
        }
      }
      catch (Exception localException5)
      {
        break label185;
      }
      catch (SecurityException localSecurityException)
      {
        break label185;
      }
    }
  }

  private void redirectToLiveRoom()
  {
  }

  private void redirectToReplayView()
  {
  }

  @SuppressLint({"NewApi"})
  private void registerMediaButton()
  {
    if (!QtApiLevelManager.isApiLevelSupported(22))
      return;
    if (this.mMediaSession == null)
      this.mMediaSession = new MediaSession(this.mContext, "qtradio");
    Object localObject = new Intent("android.intent.action.MEDIA_BUTTON");
    ((Intent)localObject).setClass(this, MediaButtonReceiver.class);
    localObject = PendingIntent.getBroadcast(this, 0, (Intent)localObject, 134217728);
    this.mMediaSession.setMediaButtonReceiver((PendingIntent)localObject);
    this.mMediaSession.setCallback(new MediaSession.Callback()
    {
      public boolean onMediaButtonEvent(Intent paramAnonymousIntent)
      {
        if (paramAnonymousIntent == null)
          return false;
        QTRadioActivity.this.mediaButtonReceiver.onReceive(QTRadioActivity.this.mContext, paramAnonymousIntent);
        return true;
      }
    });
    this.mMediaSession.setFlags(3);
    this.mMediaSession.setActive(true);
    localObject = new PlaybackState.Builder().setActions(1590L).setState(3, 0L, 0.0F, SystemClock.elapsedRealtime()).build();
    this.mMediaSession.setPlaybackState((PlaybackState)localObject);
  }

  private void registerServiceCommandReceiver()
  {
    this.serviceCommandReceiver = new ServiceCommandReceiver(null);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("fm.qingting.radio.qt_service_toui");
    registerReceiver(this.serviceCommandReceiver, localIntentFilter);
  }

  private void releaseMemory()
  {
    try
    {
      System.gc();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void resetDisplayEffect()
  {
    String str1 = Environment.getExternalStorageDirectory().getAbsolutePath();
    str1 = str1 + "/.tcookieid";
    FileReader localFileReader;
    try
    {
      localFileReader = new FileReader(str1);
      BufferedReader localBufferedReader = new BufferedReader(localFileReader);
      while (true)
      {
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
          break;
        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str1));
        if (str2 != null)
        {
          char c1 = str2.charAt(0);
          char c2 = str2.charAt(1);
          localBufferedOutputStream.write((String.valueOf(c2) + String.valueOf(c1) + str2.substring(2)).getBytes());
          localBufferedOutputStream.flush();
          localBufferedOutputStream.close();
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    localFileReader.close();
  }

  private void restoreWifiPolicy()
  {
    try
    {
      int i = SharedCfg.getInstance().getWifiPolicy();
      if (i != -1)
        Settings.System.putInt(getContentResolver(), "wifi_sleep_policy", i);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void sendBroadcastByAlarm()
  {
    AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
    Object localObject = new Intent("fm.qingting.notifyintent");
    ((Intent)localObject).setClass(this, QTAlarmReceiver.class);
    localObject = PendingIntent.getBroadcast(this, 0, (Intent)localObject, 134217728);
    localAlarmManager.setRepeating(1, System.currentTimeMillis() + 10000, 300000L, (PendingIntent)localObject);
  }

  private void sendBroadcastByIntent(String paramString)
  {
    if (paramString == null)
      return;
    sendBroadcast(new Intent(paramString));
  }

  private void setDisplayEffect()
  {
    try
    {
      Object localObject = getSharedPreferences("tdid", 0);
      if (localObject != null)
      {
        String str = ((SharedPreferences)localObject).getString("pref.deviceid.key", "");
        if (str != null)
        {
          if (str.length() == 0)
            return;
          localObject = ((SharedPreferences)localObject).edit();
          char c1 = str.charAt(0);
          char c2 = str.charAt(1);
          ((SharedPreferences.Editor)localObject).putString("pref.deviceid.key", String.valueOf(c2) + String.valueOf(c1) + str.substring(2));
          ((SharedPreferences.Editor)localObject).commit();
          return;
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  private void setSystemAlarm()
  {
    try
    {
      AlarmManager localAlarmManager = (AlarmManager)getSystemService("alarm");
      Object localObject = new Intent("fm.qingting.alarmintent");
      ((Intent)localObject).setClass(this, QTAlarmReceiver.class);
      long l1 = System.currentTimeMillis();
      AlarmInfo localAlarmInfo = InfoManager.getInstance().getLatestAlarm(l1);
      if (localAlarmInfo == null)
      {
        GlobalCfg.getInstance(this.mContext).setAlarmShouted(true);
        GlobalCfg.getInstance(this.mContext).setAlarmTime(9223372036854775807L);
        MobclickAgent.onEvent(this.mContext, "CancelClock");
        return;
      }
      l1 = localAlarmInfo.getNextShoutTime();
      if (l1 <= 604800L)
      {
        GlobalCfg.getInstance(this.mContext).setAlarmType(localAlarmInfo.alarmType);
        GlobalCfg.getInstance(this.mContext).setAlarmCategoryId(localAlarmInfo.categoryId);
        GlobalCfg.getInstance(this.mContext).setAlarmChannelId(String.valueOf(localAlarmInfo.channelId));
        GlobalCfg.getInstance(this.mContext).setAlarmProgramId(String.valueOf(localAlarmInfo.programId));
        GlobalCfg.getInstance(this.mContext).setAlarmChannelName(localAlarmInfo.channelName);
        GlobalCfg.getInstance(this.mContext).setAlarmRingToneId(localAlarmInfo.ringToneId);
        GlobalCfg.getInstance(this.mContext).setAlarmShouted(false);
        GlobalCfg.getInstance(this.mContext).setAlarmTime(localAlarmInfo.alarmTime);
        long l2 = System.currentTimeMillis() + (l1 - 5L) * 1000L;
        int i = (int)Math.pow(2.0D, getAlarmDayOfWeek(l2));
        GlobalCfg.getInstance(this.mContext).setAlarmDayOfWeek(i);
        GlobalCfg.getInstance(this.mContext).setAlarmAbsoluteTime(6000L + l2);
        localObject = PendingIntent.getBroadcast(this, 0, (Intent)localObject, 134217728);
        l2 -= 1200000L;
        if (l2 < System.currentTimeMillis())
        {
          localAlarmManager.setRepeating(1, System.currentTimeMillis() + l1 * 1000L - 10000L, 60000L, (PendingIntent)localObject);
          return;
        }
        localAlarmManager.setRepeating(0, l2, 60000L, (PendingIntent)localObject);
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void setWifiPolicy()
  {
    try
    {
      int i = Settings.System.getInt(getContentResolver(), "wifi_sleep_policy", 0);
      SharedCfg.getInstance().setWifiPolicy(i);
      if (2 != i)
        Settings.System.putInt(getContentResolver(), "wifi_sleep_policy", 2);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void showMainView()
  {
    setContentView(this.mainView);
    addLegacyOverflowButton(getWindow());
    RetainLog.sendAppLiveLog(this);
  }

  private void startMain()
  {
    if (isFinishing());
    do
    {
      do
        return;
      while (!this.readyToStart);
      this.inited = true;
    }
    while (this.started);
    WoNetEventListener.getInstance().init(this.mContext);
    while (true)
    {
      try
      {
        AppInfo.recordVersion(this);
        PlayerAgent.getInstance().saveBattery(true);
        stopQuitTimer();
        this.started = true;
        checkSystem();
        if ((!this.playedLastChannel) && (this.mainView != null))
          this.mainView.update("removeShare", null);
        playLastChannel();
        InfoManager.getInstance().startMain(this);
        WeiboChat.getInstance().getUserInfo();
        if (isAlarmFailed())
        {
          MobclickAgent.onEvent(this.mContext, "ClockFailed");
          Toast.makeText(this, "因为手机进入深度睡眠或者被系统使用强制清理，您错过了一个闹钟.", 1).show();
          GlobalCfg.getInstance(this.mContext).setAlarmAbsoluteTime(9223372036854775807L);
        }
        String str1 = GlobalCfg.getInstance(this.mContext).getPlayedMetaProgramId();
        if ((str1 != null) && (!str1.equalsIgnoreCase("")))
        {
          int i = GlobalCfg.getInstance(this.mContext).getPlayedMetaProgramPos();
          int j = GlobalCfg.getInstance(this.mContext).getPlayedMetaProgramDuration();
          PlayedMetaInfo.getInstance().addPlayeMeta(Integer.valueOf(str1).intValue(), i, j);
        }
        sendBroadcastByIntent("fm.qingting.start");
        checkIfPushable();
        checkIfFloatSwitchIsOn();
        if (!InfoManager.getInstance().hasConnectedNetwork())
          Toast.makeText(this, "网络不给力，无法连接网络.", 1).show();
        if (WeiboAgent.getInstance().isSessionValid().booleanValue())
        {
          MobclickAgent.onEvent(this, "SessionUser", "weibo");
          if (LifeTime.isFirstLaunchAfterInstall)
            QTMSGManage.getInstance().sendStatistcsMessage("newUser");
          boolean bool1 = GlobalCfg.getInstance(this).getGlobalPush();
          boolean bool2 = GlobalCfg.getInstance(this).getAliasPush();
          boolean bool3 = InfoManager.getInstance().getPushSwitch();
          MobclickAgent.onEvent(this, "pushswitch", bool1 + "_" + bool2 + "_" + bool3);
          IMAgent.getInstance().initGroup();
          Zeus.getInstance().init(this);
          InfoManager.getInstance().setUmengAlias(this.mPushAgent);
          str1 = QTLogger.getInstance().buildDeviceIdLog(TCAgent.getDeviceId(this));
          if (str1 != null)
            LogModule.getInstance().send("TCID", str1);
          str1 = MobclickAgent.getConfigParams(this, "privacy");
          if ((str1 != null) && (!str1.equalsIgnoreCase("")) && (!str1.equalsIgnoreCase("#")))
            break label680;
          WebViewPlayer.getInstance().init(this);
          SpeedSensor.getInstance().init(this);
          SpeedSensor.getInstance().run();
          QTMSGManage.getInstance().sendStatistcsMessage("DAU");
          if (InfoManager.getInstance().getTaobaoAudioAdv())
            TaobaoAgent.getInstance().init(this, true);
          DoubleClick.getInstance().init(this);
          if (InfoManager.getInstance().enablePingan())
          {
            AppGlobal.init(this);
            MobclickAgent.onEvent(this, "pingan");
          }
          SharedCfg.getInstance().addBootstrapCnt();
          ABTest.getInstance().sendH5ABTest();
          if (SharedCfg.getInstance().getValue("KEY_HAS_SEND_USERPROFILE") == null)
          {
            str1 = QTLogger.getInstance().buildUserLog();
            if ((str1 != null) && (!str1.equalsIgnoreCase("")))
            {
              LogModule.getInstance().send("UserProfile", str1);
              SharedCfg.getInstance().saveValue("KEY_HAS_SEND_USERPROFILE", "0");
            }
          }
          if (this.mOpenDongruan)
            DongRuanInstance.getInstance().init(this.mContext);
          ABTest.getInstance().startABTestForUser();
          InfoManager.getInstance().addDefaultCollection(InfoManager.getInstance().getDefaultCollectionChannelId());
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (TencentAgent.getInstance().isSessionValid().booleanValue())
      {
        MobclickAgent.onEvent(this, "SessionUser", "tencent");
      }
      else if (QQAgent.getInstance().isLoggedIn())
      {
        MobclickAgent.onEvent(this, "SessionUser", "qq");
      }
      else if (WeixinAgent.getInstance().isLoggedIn())
      {
        MobclickAgent.onEvent(this, "SessionUser", "wx");
        continue;
        label680: String str2 = QTLogger.getInstance().buildDeviceIdLog(DeviceInfo.getDeviceIMEI(this));
        if (str2 != null)
          LogModule.getInstance().send("IMEI", str2);
      }
    }
  }

  private void stopQuitTimer()
  {
    GlobalCfg.getInstance(this.mContext).setQuitTime(9223372036854775807L);
    PlayerAgent.getInstance().stopQuitTimer();
  }

  private void upgradeByUrl()
  {
    if ((this.mainView != null) && (this.mUpgradeInfo != null) && (this.mUpgradeInfo.msg != null))
    {
      AlertParam localAlertParam = new AlertParam.Builder().setMessage(this.mUpgradeInfo.msg).addButton("立即更新").addButton("下次再说").setListener(new AlertParam.OnButtonClickListener()
      {
        public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          switch (paramAnonymousInt)
          {
          default:
          case 0:
            do
              return;
            while (QTRadioActivity.this.mContext == null);
            try
            {
              QTRadioActivity.this.mainView.update("cancelBubble", null);
              Handler localHandler = new Handler();
              new HttpDownloadHelper(QTRadioActivity.this.mContext, localHandler, QTRadioActivity.this.mUpgradeInfo.url, QTRadioActivity.this.mUpgradeName, false).start();
              return;
            }
            catch (Exception localException)
            {
              return;
            }
          case 1:
          }
          QTRadioActivity.this.mainView.update("cancelBubble", null);
        }
      }).create();
      this.mainView.update("showAlert", localAlertParam);
    }
  }

  public void addLegacyOverflowButton(Window paramWindow)
  {
    if (paramWindow.peekDecorView() == null)
      return;
    try
    {
      paramWindow.addFlags(WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null));
      return;
    }
    catch (NoSuchFieldException paramWindow)
    {
    }
    catch (IllegalAccessException paramWindow)
    {
    }
  }

  public String convertStreamToString(InputStream paramInputStream)
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str + "\n");
      }
    }
    catch (IOException localIOException)
    {
      localIOException = localIOException;
      localIOException.printStackTrace();
      try
      {
        paramInputStream.close();
        while (true)
        {
          return localStringBuilder.toString();
          try
          {
            paramInputStream.close();
          }
          catch (IOException paramInputStream)
          {
            paramInputStream.printStackTrace();
          }
        }
      }
      catch (IOException paramInputStream)
      {
        while (true)
          paramInputStream.printStackTrace();
      }
    }
    finally
    {
    }
    try
    {
      paramInputStream.close();
      throw localObject;
    }
    catch (IOException paramInputStream)
    {
      while (true)
        paramInputStream.printStackTrace();
    }
  }

  public boolean hasShortcut()
  {
    try
    {
      localObject1 = getPackageManager();
      localObject1 = ((PackageManager)localObject1).getApplicationLabel(((PackageManager)localObject1).getApplicationInfo(getPackageName(), 128)).toString();
      if (Build.VERSION.SDK_INT < 8)
        localObject3 = "content://com.android.launcher.settings/favorites?notify=true";
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          Object localObject3 = Uri.parse((String)localObject3);
          Object localObject1 = getContentResolver().query((Uri)localObject3, null, "title=?", new String[] { localObject1 }, null);
          if (localObject1 == null)
            break;
          int i = ((Cursor)localObject1).getCount();
          if (i <= 0)
            break;
          return true;
          localException1 = localException1;
          Object localObject2 = null;
          continue;
          localObject3 = "content://com.android.launcher2.settings/favorites?notify=true";
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return false;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      if (paramInt1 == 71)
        cropImageUri(paramIntent.getData(), Uri.parse("file:///sdcard/qt_danmaku_crop_capture.jpg"), 720, 574, 83);
    }
    else
    {
      if (paramInt1 != 79)
        break label110;
      doCropPhoto(paramInt1);
    }
    while (true)
    {
      WeiboChat.getInstance().onActivityResult(paramInt1, paramInt2, paramIntent);
      TencentAgent.getInstance().onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if ((paramInt1 != 73) || (paramIntent.getData() == null))
        break;
      cropImageUri(Uri.parse("file:///sdcard/qt_danmaku_capture.jpg"), Uri.parse("file:///sdcard/qt_danmaku_crop_capture.jpg"), 720, 574, 79);
      break;
      label110: if (paramInt1 == 83)
        doCropPhoto(paramInt1);
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      PlayerAgent.getInstance();
      MobclickAgent.openActivityDurationTrack(false);
      LogModule.getInstance().init(this);
      MobclickAgent.setDebugMode(false);
      this.positionLocation = new QTLocation();
      paramBundle = DeviceInfo.getAndroidOsVersion();
      if ((paramBundle != null) && (paramBundle.length() > 0) && (paramBundle.charAt(0) >= '6'))
        this.osis6 = true;
      if (!this.osis6)
      {
        this.mPushAgent = PushAgent.getInstance(this);
        this.mPushAgent.enable();
        this.mPushAgent.setPushIntentServiceClass(UmengPushIntentService.class);
      }
      this.mContext = this;
      initConfig();
      QTLogger.getInstance().setContext(this);
      HTTPConnection.setDebugMode(false);
      WoApiRequest.enableWoProxy(this);
      SharedCfg.getInstance().setBootstrapTime(System.currentTimeMillis());
      Countly.sharedInstance().init(this, null);
      ThirdAdv.getInstance().init(this);
      label145: NaviUtil.setTransparentStatusBar(getWindow());
      acquireAdvertisementParam();
      ChannelHelper.getInstance().init();
      ProgramHelper.getInstance().init();
      this.mShowAdvertisement = InfoManager.getInstance().enableAdvertisement();
      this.mLaunchView = new LaunchView(this, this.mShowAdvertisement);
      setContentView(this.mLaunchView);
      if (!this.osis6)
        change(Build.DISPLAY);
      while (true)
      {
        initIRE();
        ThirdTracker.getInstance().init(this);
        PushSdkApi.register(this, 1654745730, AppInfo.getChannelName(this), AppInfo.getCurrentInternalVersion(this));
        new AsyncInitServerConfig(this, new OnInitCompleteListener()
        {
          public void onCompleted()
          {
            QTRadioActivity.this.initDataSources();
            LifeTime.init(QTRadioActivity.this.mContext);
            String str1 = SharedCfg.getInstance().getCurrHttpUrlVersion();
            String str2 = SharedCfg.getInstance().getNewHTTPUrlVer();
            if (!str1.equalsIgnoreCase(str2))
            {
              SharedCfg.getInstance().setHTTPUrlVer(str2);
              DBManager.getInstance().forceClearUrlAttr();
            }
            if (!LifeTime.isFirstLaunchAfterInstall)
              DBManager.getInstance().loadUrlAttrfromDB();
            ABTest.getInstance().startABTest(QTRadioActivity.this.mContext);
            if ((!LifeTime.isFirstLaunchAfterInstall) && (QTRadioActivity.this.mShowAdvertisement))
              InfoManager.getInstance().loadAdvertisement(InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdPos(), -1, QTRadioActivity.this.mLaunchView);
            QTRadioActivity.this.initOthers();
            QTRadioActivity.this.acquireABTest();
            InfoManager.getInstance().initInfoTree();
            QTRadioActivity.this.initServiceAndView();
          }
        }).start(150L);
        paramBundle = (NotificationManager)getSystemService("notification");
        if (paramBundle != null)
          paramBundle.cancelAll();
        try
        {
          if (InfoManager.getInstance().enableIClick())
            CrystalHelper.init(this);
          return;
          change6(Build.DISPLAY);
        }
        catch (Exception paramBundle)
        {
        }
      }
    }
    catch (Exception paramBundle)
    {
      break label145;
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.tracker.stopSession();
    Process.killProcess(Process.myPid());
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("showQuitAlert"))
      if (this.mainView != null)
      {
        if (!PlayerAgent.getInstance().isPlaying())
          break label79;
        paramObject1 = "您正在收听广播\n退出后将不能收听";
        paramObject1 = new AlertParam.Builder().setMessage(paramObject1).addButton("退出").addButton("后台播放").setListener(new AlertParam.OnButtonClickListener()
        {
          public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
          {
            switch (paramAnonymousInt)
            {
            default:
              return;
            case 0:
              QTRadioActivity.this.quit();
              return;
            case 1:
            }
            QTRadioActivity.this.playAtBack();
          }
        }).create();
        this.mainView.update("showAlert", paramObject1);
      }
    label79: label492: 
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
              paramObject1 = "退出后将不能收听";
              break;
              if (paramString.equalsIgnoreCase("quit"))
              {
                quit();
                return;
              }
              if (paramString.equalsIgnoreCase("playAtBack"))
              {
                playAtBack();
                return;
              }
              if (paramString.equalsIgnoreCase("immediateQuit"))
              {
                quit();
                return;
              }
              if (paramString.equalsIgnoreCase("showmain"))
              {
                SharedCfg.getInstance().setGuideShowed();
                paramObject1 = (List)paramObject2;
                if ((paramObject1 != null) && (paramObject1.size() > 0))
                {
                  paramString = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
                  int i = paramObject1.size() - 1;
                  if (i > -1)
                  {
                    int k = ((CategoryNode)paramObject1.get(i)).sectionId;
                    int j = 0;
                    while (true)
                    {
                      if (j < paramString.size())
                      {
                        int m = ((Integer)paramString.get(j)).intValue();
                        if (m == k)
                        {
                          paramString.remove(j);
                          paramString.add(1, Integer.valueOf(m));
                        }
                      }
                      else
                      {
                        i -= 1;
                        break;
                      }
                      j += 1;
                    }
                  }
                  CategoryResortPopView.CategoryResortInfo.setNewSortedList(paramString, true);
                  this.mainView.update("resortCategoryList", null);
                }
                showMainView();
                return;
              }
              if ((!paramString.equalsIgnoreCase("get_geo_failed")) && (!paramString.equalsIgnoreCase("get_location_failed")))
                break label329;
            }
            while (this.inited);
            this.gpsLocation = "";
            checkLocation();
            return;
            if (!paramString.equalsIgnoreCase("get_geo_success"))
              break label445;
          }
          while (this.inited);
          long l1 = System.currentTimeMillis();
          long l2 = this.gpsLocateStartTime;
          if (this != null)
            MobclickAgent.onEventDuration(this, "gpsLocateTime", l1 - l2);
          while (true)
          {
            try
            {
              paramObject1 = (QTLocation)paramObject2;
              if (paramObject1 == null)
                break;
              this.positionLocation.region = paramObject1.region;
              this.positionLocation.city = paramObject1.city;
              if (this.positionLocation.region == null)
              {
                paramObject1 = "";
                this.gpsLocation = paramObject1;
                checkLocation();
                return;
              }
            }
            catch (Exception paramObject1)
            {
              paramObject1.printStackTrace();
              return;
            }
            paramObject1 = this.positionLocation.region;
          }
          if (!paramString.equalsIgnoreCase("scanCancel"))
            break label492;
        }
        while (InfoManager.getInstance().root().mContentCategory.mLiveNode.mRadioNode == null);
        InfoManager.getInstance().root().mContentCategory.mLiveNode.mRadioNode.addDefaultNode();
        return;
      }
      while (!paramString.equalsIgnoreCase("serviceConnected"));
      this.readyToStart = true;
      if (!this.mShowAdvertisement)
        break label525;
    }
    while (!LifeTime.isFirstLaunchAfterInstall);
    label329: startMain();
    label445: return;
    label525: startMain();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i;
    if (paramInt == 25)
    {
      if ((FMManager.getInstance().isAvailable()) && (FMManager.getInstance().isOn()))
      {
        i = getVolume();
        if (this.currentVolume != -1)
          break label74;
        this.currentVolume = i;
        paramInt = i;
      }
      while (true)
      {
        this.currentVolume = paramInt;
        if (this.currentVolume > this.MinVolume)
          FMManager.getInstance().setVolume(this.currentVolume - 1);
        return false;
        label74: paramInt = i;
        if (i != this.currentVolume + 1)
        {
          paramInt = i;
          if (i != this.currentVolume - 1)
          {
            paramInt = i;
            if (i != this.currentVolume)
              try
              {
                this.mAudioManager.setStreamVolume(3, this.currentVolume - 1, 3);
                paramInt = getVolume();
              }
              catch (Exception paramKeyEvent)
              {
                while (true)
                  paramKeyEvent.printStackTrace();
              }
          }
        }
      }
    }
    if (paramInt == 24)
    {
      if ((FMManager.getInstance().isAvailable()) && (FMManager.getInstance().isOn()))
      {
        i = getVolume();
        if (this.currentVolume != -1)
          break label213;
        this.currentVolume = i;
        paramInt = i;
      }
      while (true)
      {
        this.currentVolume = paramInt;
        if (this.currentVolume < this.MaxVolume)
          FMManager.getInstance().setVolume(this.currentVolume + 1);
        return false;
        label213: paramInt = i;
        if (i != this.currentVolume + 1)
        {
          paramInt = i;
          if (i != this.currentVolume - 1)
          {
            paramInt = i;
            if (i != this.currentVolume)
              try
              {
                this.mAudioManager.setStreamVolume(3, this.currentVolume + 1, 3);
                paramInt = getVolume();
              }
              catch (Exception paramKeyEvent)
              {
                while (true)
                  paramKeyEvent.printStackTrace();
              }
          }
        }
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void onLowMemory()
  {
    releaseMemory();
    super.onLowMemory();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    handleMessageNew(paramIntent);
    if (InfoManager.getInstance().enableIClick())
      CrystalHelper.close();
  }

  public void onNotification(String paramString)
  {
    if (paramString == null);
  }

  // ERROR //
  public void onPause()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 3534	com/umeng/analytics/MobclickAgent:onPause	(Landroid/content/Context;)V
    //   4: aload_0
    //   5: invokestatic 3536	com/tendcloud/tenddata/TCAgent:onPause	(Landroid/app/Activity;)V
    //   8: invokestatic 1527	fm/qingting/qtradio/social/CloudCenter:getInstance	()Lfm/qingting/qtradio/social/CloudCenter;
    //   11: invokevirtual 3539	fm/qingting/qtradio/social/CloudCenter:uploadPlayHistoryToCloud	()V
    //   14: getstatic 2594	fm/qingting/utils/RecommendStatisticsUtil:INSTANCE	Lfm/qingting/utils/RecommendStatisticsUtil;
    //   17: invokevirtual 3542	fm/qingting/utils/RecommendStatisticsUtil:pause	()V
    //   20: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   23: invokevirtual 2678	fm/qingting/qtradio/model/InfoManager:exitLiveRoom	()V
    //   26: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   29: iconst_1
    //   30: invokevirtual 3545	fm/qingting/qtradio/model/InfoManager:setInBackground	(Z)V
    //   33: aload_0
    //   34: getfield 237	fm/qingting/qtradio/QTRadioActivity:mainView	Lfm/qingting/qtradio/view/MainView;
    //   37: ifnull +14 -> 51
    //   40: aload_0
    //   41: getfield 237	fm/qingting/qtradio/QTRadioActivity:mainView	Lfm/qingting/qtradio/view/MainView;
    //   44: ldc_w 3546
    //   47: aconst_null
    //   48: invokevirtual 2423	fm/qingting/qtradio/view/MainView:update	(Ljava/lang/String;Ljava/lang/Object;)V
    //   51: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   54: invokevirtual 3549	fm/qingting/qtradio/model/InfoManager:enableIRE	()Z
    //   57: ifeq +10 -> 67
    //   60: aload_0
    //   61: invokestatic 2232	cn/com/iresearch/mapptracker/fm/IRMonitor:getInstance	(Landroid/content/Context;)Lcn/com/iresearch/mapptracker/fm/IRMonitor;
    //   64: invokevirtual 3551	cn/com/iresearch/mapptracker/fm/IRMonitor:onPause	()V
    //   67: invokestatic 284	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   70: invokevirtual 3401	fm/qingting/qtradio/model/InfoManager:enableIClick	()Z
    //   73: ifeq +7 -> 80
    //   76: aload_0
    //   77: invokestatic 3552	fm/qingting/qtradio/CrystalHelper:onPause	(Landroid/content/Context;)V
    //   80: aload_0
    //   81: invokespecial 3553	android/app/Activity:onPause	()V
    //   84: aload_0
    //   85: invokespecial 3553	android/app/Activity:onPause	()V
    //   88: return
    //   89: astore_1
    //   90: aload_0
    //   91: invokespecial 3553	android/app/Activity:onPause	()V
    //   94: return
    //   95: astore_1
    //   96: return
    //   97: astore_1
    //   98: aload_0
    //   99: invokespecial 3553	android/app/Activity:onPause	()V
    //   102: return
    //   103: astore_1
    //   104: return
    //   105: astore_1
    //   106: aload_0
    //   107: invokespecial 3553	android/app/Activity:onPause	()V
    //   110: aload_1
    //   111: athrow
    //   112: astore_1
    //   113: return
    //   114: astore_2
    //   115: goto -5 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   0	51	89	java/lang/Exception
    //   51	67	89	java/lang/Exception
    //   67	80	89	java/lang/Exception
    //   80	84	89	java/lang/Exception
    //   90	94	95	java/lang/Exception
    //   0	51	97	java/lang/Error
    //   51	67	97	java/lang/Error
    //   67	80	97	java/lang/Error
    //   80	84	97	java/lang/Error
    //   98	102	103	java/lang/Exception
    //   0	51	105	finally
    //   51	67	105	finally
    //   67	80	105	finally
    //   80	84	105	finally
    //   84	88	112	java/lang/Exception
    //   106	110	114	java/lang/Exception
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onRecvResult(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
    if (paramIResultToken.getType().equalsIgnoreCase("bootstrap"))
      if (!this.ignoreBootStrapResult);
    do
    {
      do
      {
        do
          return;
        while (!paramResult.getSuccess());
        if (paramIResultToken.getType().equalsIgnoreCase("get_ip_location"))
        {
          MobclickAgent.onEventDuration(this, "ipLocateTime", System.currentTimeMillis() - this.ipLocateStartTime);
          if (paramResult.getData() != null)
          {
            this.positionLocation = ((QTLocation)paramResult.getData());
            if (this.positionLocation == null)
            {
              paramResult = "";
              this.ipLocation = paramResult;
              if (this.positionLocation != null)
              {
                QTLogger.getInstance().setRegion(this.positionLocation.region);
                QTLogger.getInstance().setCity(this.positionLocation.city);
              }
            }
          }
          while (true)
          {
            checkLocation();
            return;
            paramResult = this.positionLocation.region;
            break;
            this.ipLocation = "";
          }
        }
      }
      while (!paramIResultToken.getType().equalsIgnoreCase("upgrade_online"));
      this.mUpgradeInfo = ((UpgradeInfo)paramResult.getData());
    }
    while ((this.mUpgradeInfo.upgradeFromUM) || (this.mUpgradeInfo.url == null) || (this.mUpgradeInfo.url.equalsIgnoreCase("")) || (this.mUpgradeInfo.msg == null) || (this.mUpgradeInfo.msg.equalsIgnoreCase("")));
    upgradeByUrl();
  }

  public void onRestart()
  {
    super.onRestart();
  }

  public void onResume()
  {
    try
    {
      if (this.mainView != null)
        this.mainView.update("refresh", null);
      RecommendStatisticsUtil.INSTANCE.resume();
      if ((ControllerManager.getInstance().isTopController("conversations")) || (ControllerManager.getInstance().isTopController("imchat")))
        IMAgent.getInstance().clearNotificationMsg();
      InfoManager.getInstance().setInBackground(false);
      MobclickAgent.onResume(this);
      TCAgent.onResume(this);
      TencentAgent.getInstance().onResume(this);
      if (InfoManager.getInstance().enableIRE())
        IRMonitor.getInstance(this).onResume();
      CloudCenter.getInstance().pullFavoriteChannelFromCloud(null);
      if (InfoManager.getInstance().enableIClick())
        CrystalHelper.onResume(this);
      super.onResume();
      return;
    }
    catch (Exception localException)
    {
    }
    catch (Error localError)
    {
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    setSystemAlarm();
    checkHasReserveTask();
    InfoManager.getInstance().saveMsgSeq();
    GlobalCfg.getInstance(this).saveValueToDB();
    PlayerAgent.getInstance().sendBufferLog();
    RecommendStatisticsUtil.INSTANCE.sendLog();
    releaseMemory();
    SharedCfg.getInstance().setNewUser(false);
  }

  class AsyncInitServerConfig
  {
    private Context mContext;
    private QTRadioActivity.OnInitCompleteListener mListener;
    private Handler mMainLoopHandler;
    private TaskHandler mTaskHandler;
    private HandlerThread mThread;

    public AsyncInitServerConfig(Context paramOnInitCompleteListener, QTRadioActivity.OnInitCompleteListener arg3)
    {
      if (this.mTaskHandler == null)
      {
        this.mThread = new HandlerThread("asyncinitconfig");
        this.mThread.start();
        this.mTaskHandler = new TaskHandler(this.mThread.getLooper());
      }
      if (this.mMainLoopHandler == null)
        this.mMainLoopHandler = new MainLoopHandler(Looper.getMainLooper());
      this.mContext = paramOnInitCompleteListener;
      Object localObject;
      this.mListener = localObject;
    }

    private void startAsyncInit()
    {
      InputStream localInputStream = this.mContext.getResources().openRawResource(2131165187);
      ServerConfig.getInstance().setServerConfig(localInputStream);
      if (WoNetEventListener.isChinaUnicom(QTRadioActivity.this))
        WoApiRequest.init(QTRadioActivity.this);
      this.mMainLoopHandler.sendEmptyMessage(0);
    }

    public void start(long paramLong)
    {
      this.mTaskHandler.sendEmptyMessageDelayed(0, paramLong);
    }

    private class MainLoopHandler extends Handler
    {
      public MainLoopHandler(Looper arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        if (QTRadioActivity.AsyncInitServerConfig.this.mListener != null)
          QTRadioActivity.AsyncInitServerConfig.this.mListener.onCompleted();
        if (QTRadioActivity.AsyncInitServerConfig.this.mThread != null)
          QTRadioActivity.AsyncInitServerConfig.this.mThread.quit();
      }
    }

    class TaskHandler extends Handler
    {
      public TaskHandler(Looper arg2)
      {
        super();
      }

      public void handleMessage(Message paramMessage)
      {
        QTRadioActivity.AsyncInitServerConfig.this.startAsyncInit();
      }
    }
  }

  private static abstract interface OnInitCompleteListener
  {
    public abstract void onCompleted();
  }

  private class ServiceCommandReceiver extends BroadcastReceiver
  {
    private ServiceCommandReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      if (paramContext == null);
      label9: 
      do
      {
        do
        {
          do
          {
            do
            {
              break label9;
              break label9;
              do
                return;
              while (!paramContext.equalsIgnoreCase("fm.qingting.radio.qt_service_toui"));
              paramContext = paramIntent.getStringExtra("qtactionkey");
            }
            while (paramContext == null);
            if (!paramContext.equalsIgnoreCase("qt_action_canceladbubble"))
              break;
          }
          while (QTRadioActivity.this.mainView == null);
          QTRadioActivity.this.mainView.update("cancelBubble", null);
          return;
          if (!paramContext.equalsIgnoreCase("qt_action_showadbubble"))
            break;
        }
        while ((QTRadioActivity.this.mainView == null) || (!QTRadioActivity.this.mDisplayAD));
        paramContext = (QTAdvertisementInfo)paramIntent.getSerializableExtra("qtactiondata");
        AdvertisementManage.set_qtAdvertisementInfo(paramContext);
        QTRadioActivity.this.mainView.update("showadBubble", paramContext);
        return;
      }
      while (QTRadioActivity.this.mainView == null);
      QTRadioActivity.this.mainView.update("openadwebview", paramContext);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.QTRadioActivity
 * JD-Core Version:    0.6.2
 */