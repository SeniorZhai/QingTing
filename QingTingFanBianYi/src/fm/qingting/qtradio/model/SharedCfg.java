package fm.qingting.qtradio.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;

public enum SharedCfg
{
  INSTANCE;

  public static final String FEATURE_ALARM = "feature_alarm";
  public static final String FEATURE_CHECKIN = "feature_checkin";
  public static final String FEATURE_COMMENT = "feature_comment";
  public static final String FEATURE_DOWNLOAD = "feature_download";
  public static final String FEATURE_DOWNLOAD_ONDEMAND = "feature_download_ondemand";
  public static final String FEATURE_MYCOLLECTION = "feature_mycollection";
  public static final String FEATURE_REPLAY = "feature_replay";
  public static final String FEATURE_SCHEDULE = "feature_schedule";
  public static final String FEATURE_SPEAKER = "feature_speaker";
  public static final String FEATURE_SUBSCRIBE = "feature_subscribe";
  public static final String FEATURE_SUBSCRIBE_NOVEL = "feature_subscribe_novel";
  public static final String FEATURE_SUBSCRIBE_PODCAST = "feature_subscribe_podcast";
  private static final String KEY_ADD_VIRTUAL_CHANNEL = "KEY_ADD_VIRTUAL_CHANNEL";
  private static final String KEY_APP_FIRST = "key_appfirst";
  private static final String KEY_AUTO_PLAY_AFTER_START = "KEY_AUTO_PLAY_AFTER_START";
  private static final String KEY_CITY = "key_city";
  private static final String KEY_DOWNLOAD_DURATION_TODAY = "key_download_duration_today";
  private static final String KEY_DOWNLOAD_PROGRAM_TODAY = "key_download_program_today";
  private static final String KEY_DOWNLOAD_TODAY = "key_download_today";
  private static final String KEY_DRAGBTN = "key_dragbtn";
  private static final String KEY_ENABLEMUSICRADIO = "key_musicradio";
  private static final String KEY_ENABLE_MEDIA_CONTROLL = "KEY_ENABLE_MEDIA_CONTROLL";
  private static final String KEY_ENABLE_MOBILE_NETWORK = "KEY_ENABLE_MOBILE_NETWORK";
  private static final String KEY_FAV_NUMBER = "favnumber";
  private static final String KEY_FMPlAYINDEX = "key_fmplayindex";
  private static final String KEY_HAS_DEL_OLD_COLLECTION = "key_has_del_old_collection";
  private static final String KEY_HEADSETPLUG = "key_headsetplug";
  private static final String KEY_HELPVIEW_STAGE = "helpviewstage";
  private static final String KEY_LATEST_VERSION = "key_latest_version";
  private static final String KEY_LOACAL_FIRST = "key_localfirst";
  private static final String KEY_LOCALRINGS_VERSION = "KEY_LOCALRINGS_VERSION";
  private static final String KEY_LOCAL_NOTICE = "key_localnotice";
  private static final String KEY_MOBILE_FIRST_ALERT = "KEY_MOBILE_FIRST_ALERT";
  private static final String KEY_MOBILE_PLAY_ALERT = "KEY_MOBILE_PLAY_ALERT";
  private static final String KEY_MOBILE_PLAY_OR_STOP = "KEY_MOBILE_PLAY_OR_STOP";
  private static final String KEY_MUTI_RATE = "key_mult_rate";
  private static final String KEY_NOTICE = "key_notice";
  private static final String KEY_OFFLINEDB_VERSION = "KEY_OFFLINEDB_VERSION";
  private static final String KEY_PLAYER = "key_player";
  private static final String KEY_PLAY_STATION = "playstation";
  private static final String KEY_PLAY_STATION_2MIN = "playstation2min";
  private static final String KEY_RECORD_PRE = "key_recordpre";
  private static final String KEY_SAVE_BATTERY = "key_save_battery";
  private static final String KEY_SCANCOMPLETE = "key_scancomplete";
  private static final String KEY_SCAN_STATION = "scanstation";
  private static final String KEY_SHARE_TO_TENCENT = "key_share_to_tencent";
  private static final String KEY_SHARE_TO_WEIBO = "key_share_to_weibo";
  private static final String KEY_SHOWWEBVIEWUSER = "key_showwebview";
  private static final String KEY_START_SWITCH = "key_startswitch";
  private static final String KEY_TENCENT_ACCESSTOKEN = "key_tencent_accesstoken";
  private static final String KEY_TENCENT_EXPIRES = "key_tencent_expires";
  private static final String KEY_TENCENT_OPENID = "key_tencent_openid";
  private static final String KEY_TIMES_LAUNCH_APP = "timespulllaunchapp";
  private static final String KEY_TIMES_PULL_QUICKVIEW = "timespullquickview";
  private static final String KEY_TIPS_VERSION = "KEY_TIPS_VERSION";
  private static final String KEY_UPGRADE_TIME = "key_popup_upgrade_reminder";
  private static final String KEY_USED_IN_WIFI = "KEY_USED_IN_WIFI";
  private static final String KEY_VERSION = "key_vertion";
  private static final String KEY_VERSIONSCANDED = "key_vertionscaned";
  private static final String KEY_WIFI_POLICY = "wifipolicy";
  private static final String KEY_YPOSITION = "key_yposition";
  private static final String KEY_YSTATE = "key_ystate";
  private static final String Key_has_upgraded_collection_2013_11_14 = "Key_has_upgraded_collection_2013_11_14";
  private static final String Key_old_favored_virtual_delete = "Key_old_favored_virtual_delete";
  private static final String LOCALRINGS_VERSION = "2.0";
  private static final String OFFLINEDB_VERSION = "8.3";
  private final String ALARM_ADDED = "alarm_added";
  private final String ALARM_REMINDED = "alarm_reminded";
  private final String APOLLO_Duration = "APOLLO_Duration";
  private final String APOLLO_START_TIME = "APOLLO_START_TIME";
  private final String AUDIOQUALITY = "audioquality";
  private final String BOOTSTRAP_CNT = "BOOTSTRAP_CNT";
  private final String BOOTSTRAP_MAX = "BOOTSTRAPMAX";
  private final String BUBBLE_CHANNELS_LIST = "bubble_channel_list";
  private final String BUBBLE_PROGRAM = "bubble_program";
  private final String BUBBLE_PROGRAM_LIST = "bubble_program_list";
  private final String CHECKINBYDAY = "checkin_by_day";
  private final String DEV_MODE = "dev_mode";
  private final String ENABLE_ADVERTISEMENT = "enable_advertisement";
  private final String FEEDBACK_CATEGORY = "feedback_category";
  private final int FIRST = 1;
  private final String FIRST_PLAYCONTROLLER = "first2playcontroller";
  private final String FIRST_PLAYVIEW = "first2playview_v2";
  private final String FORCE_LOGIN = "FORCE_LOGIN";
  private final String GUIDE_COLLECTION = "guide_collection";
  private final String HASALARMCLICKED = "hasalarmclicked";
  private final String HASCLICKEDPLUS = "hasclickedplus";
  private final String HASCOLLECTIONCLICKED = "hascollectionclicked";
  private final String HASLOGCLICKED = "haslogclicked";
  private final String HOME_PAGE_IS_PLAY_VIEW = "HOME_PAGE_IS_PLAY_VIEW";
  private final String IRE_CHANGE = "IRE_CHANGE";
  private final String IS_APOLLO = "IS_APOLLO";
  private final String KEY_ADD_COLLECTION = "KEY_ADD_COLLECTION";
  private final String KEY_ADMASTER_URL = "KEY_ADMASTER_URL";
  private final String KEY_APOLLO_APPSTART = "key_apollo_app_start";
  private final String KEY_AT_ME_LASTEST_TIME = "key_atme_latest_time";
  private final String KEY_AUTO_RESERVE_DURATION = "KEY_AUTO_RESERVE_DURATION";
  private final String KEY_AUTO_SEEK = "KEY_AUTO_SEEK";
  private final String KEY_CATEGORY_ORDER = "key_category_order";
  private final String KEY_CHANNELNAME = "channelname";
  private final String KEY_CHAT_NICKNAME = "chat_nickname";
  private final String KEY_CHOOSE_GENDER = "KEY_CHOOSE_GENDER";
  private final String KEY_CHOOSE_STUDENT = "KEY_CHOOSE_STUDENT";
  private final String KEY_CHOOSE_USER = "KEY_CHOOSE_USER";
  private final String KEY_DEFAULT_COLLECTION = "KEY_DEFAULT_COLLECTION";
  private final String KEY_DISPLAY_AD = "KEY_DISPLAY_AD";
  private final String KEY_DOUBLE_BACK_QUIT = "key_double_back_quit";
  private final String KEY_DRAG_HELP_SHOWED = "drag_help_showed";
  private final String KEY_EDUCATION_COLLAPSE = "key_education_collapse";
  private final String KEY_EDUCATION_SORT = "key_education_sort";
  private final String KEY_ENABLEORIGINAL = "key_original";
  private final String KEY_ENABLE_AUDIO_TIPS = "KEY_ENABLE_AUDIO_TIPS";
  private final String KEY_ENABLE_AUTO_SEEK = "KEY_ENABLE_AUTO_SEEK";
  private final String KEY_ENABLE_BILLBOARD = "KEY_ENABLE_BILLBOARD";
  private final String KEY_ENABLE_CLOCK_SEARCH = "key_enable_clock_search";
  private final String KEY_ENABLE_DEL_CHANNEL_CACHE = "KEY_ENABLE_DEL_CHANNEL_CACHE";
  private final String KEY_ENABLE_EASTER_EGG = "key_enable_easter_egg";
  private final String KEY_ENABLE_FOREIGN_CHANNELS = "KEY_ENABLE_FOREIGN_CHANNELS";
  private final String KEY_ENABLE_LIVE_PUSH = "KEY_ENABLE_LIVE_PUSH";
  private final String KEY_ENABLE_NAVIGATION = "KEY_ENABLE_NAVIGATION";
  private final String KEY_ENABLE_NEW_DOWNLOAD = "KEY_ENABLE_NEW_DOWNLOAD";
  private final String KEY_ENABLE_RECPAGE = "KEY_ENABLE_RECPAGE";
  private final String KEY_FEEDBACK_CONTACTINFO = "key_feedback_contactinfo";
  private final String KEY_FILTER_LIVEROOM = "KEY_FILTER_LIVEROOM";
  private final String KEY_FLOWER_CNT = "key_flower_cnt";
  private final String KEY_FLOWER_LASTEST_UPDATE_TIME = "key_flower_latest_update_time";
  private final String KEY_FUCK_360 = "KEY_FUCK_360";
  private final String KEY_IS_ONDEMAND = "is_ondemand";
  private final String KEY_LAST_DAY = "lastday";
  private final String KEY_LOCAL_ADVERTISEMENT_PIC = "KEY_LOCAL_ADVERTISEMENT_PIC";
  private final String KEY_LOCAL_NOTIFY = "KEY_LOCAL_NOTIFY";
  private final String KEY_LOCAL_RECOMMEND_BEGIN_MAX = "KEY_LOCAL_RECOMMEND_BEGIN_MAX";
  private final String KEY_LOCAL_RECOMMEND_BEGIN_MIN = "KEY_LOCAL_RECOMMEND_BEGIN_MIN";
  private final String KEY_LOCAL_RECOMMEND_DELAYED = "KEY_LOCAL_RECOMMEND_DELAYED";
  private final String KEY_LOCAL_RECOMMEND_DURATION = "key_local_recommend_duration";
  private final String KEY_LOCAL_RECOMMEND_IGNORED = "KEY_LOCAL_RECOMMEND_IGNORED";
  private final String KEY_NEED_CLEAR_HTTPURL = "KEY_NEED_CLEAR_HTTPURL";
  private final String KEY_NET_ADVERTISEMENT_PIC = "KEY_NET_ADVERTISEMENT_PIC";
  private final String KEY_NEW_CDN = "KEY_NEW_CDN";
  private final String KEY_NOT_RELISTEN_VIEW = "not_relisten_view";
  private String KEY_PLAY_INFO = "KEY_LAST_PLAY_INFO";
  private final String KEY_POSITION_CITY = "city";
  private final String KEY_POSITION_REGION = "region";
  private final String KEY_QQ_ACCESS_TOKEN = "key_qq_access_token";
  private final String KEY_QQ_EXPIRE_TIME = "key_qq_expire_time";
  private final String KEY_QQ_GENDER = "key_qq_gender";
  private final String KEY_QQ_OPEN_ID = "key_qq_open_id";
  private final String KEY_QQ_USER_KEY = "key_qq_user_key";
  private final String KEY_RAND_SELECT_INDEX = "KEY_RAND_SELECT_INDEX";
  private final String KEY_RECENT_KEYWORDS = "KEY_RECENT_KEYWORDS";
  private final String KEY_RECOMMEND_SHARE_FLAG = "KEY_RECOMMEND_SHARE_FLAG";
  private final String KEY_RECOMMEND_SHARE_LOGIN = "KEY_RECOMMEND_SHARE_LOGIN";
  private final String KEY_RECOMMEND_SHARE_UPDATE = "KEY_RECOMMEND_SHARE_UPDATE";
  private final String KEY_RECOMMEND_SHARE_UPDATE_TIME = "KEY_RECOMMEND_SHARE_UPDATE_TIME";
  private final String KEY_RECV_NOTIFICATION_NET = "KEY_RECV_NOTIFICATION_NET";
  private final String KEY_RELISTEN_INDEX = "relisten_index";
  private final String KEY_REMIND_GROUP = "key_remind_group";
  private final String KEY_REVERSE_CHANNEL_IDS = "KEY_REVERSE_CHANNEL_IDS";
  private final String KEY_SAVED_CHANNEL_ID = "saved_channel_id";
  private final String KEY_SAVED_CHANNEL_NAME = "saved_program_channel_name";
  private final String KEY_SAVED_PAGE = "saved_page";
  private final String KEY_SAVED_PROGRAM_CATEGORY_CID = "saved_program_category_cid";
  private final String KEY_SAVED_PROGRAM_CATEGORY_NAME = "saved_program_category_name";
  private final String KEY_SAVED_PROGRAM_CATEGORY_TYPE = "saved_program_category_type";
  private final String KEY_SHOWED_GUIDE = "key_showed_guide";
  private final String KEY_SHOW_CONTENT_PAGE = "KEY_SHOW_CONTENT_PAGE";
  private final String KEY_SINGLE_NAME = "single_name";
  private final String KEY_SPEAKER_STATE = "speakerstate";
  private final String KEY_SPLASH_LANDING = "KEY_SPLASH_LANDING";
  private final String KEY_SUB_NOTIFICATION = "KEY_SUB_NOTIFICATION";
  private final String KEY_SWITCH_RECORD = "switch_record";
  private final String KEY_TENCENT_GENDER = "KEY_TENCENT_GENDER";
  private final String KEY_TENCENT_SOCIAL_USER_KEY = "KEY_TENCENT_SOCIAL_USER_KEY";
  private final String KEY_WECHAT_ACCESS_TOKEN = "key_wechat_access_token";
  private final String KEY_WECHAT_GENDER = "key_wechat_gender";
  private final String KEY_WECHAT_OPEN_ID = "key_wechat_open_id";
  private final String KEY_WECHAT_REFRESH_TOKEN = "key_wechat_refresh_token";
  private final String KEY_WECHAT_USER_KEY = "key_wechat_user_key";
  private final String KEY_WEIBO_GENDER = "KEY_WEIBO_GENDER";
  private final String KEY_WEIBO_SOCIAL_USER_KEY = "KEY_WEIBO_SOCIAL_USER_KEY";
  private final String KEY_WO_API_TOKEN = "key_wo_api_token";
  public String KEY_WO_BIND_CALL_NUMBER = "key_bind_call_number";
  private final String KEY_WO_TRIED_SECONDS = "key_wo_tried_seconds";
  private final String KEY_WO_TRY_DATE = "key_wo_try_date";
  private final String LOCAL_LIVEROOM_LIST = "local_liveroom_list";
  private final int MaxConut = 10;
  private final String NEW_CLEAR_HTTPURL = "1.0";
  private final String NEW_USER = "NEW_USER";
  private final String PLAYVIEW_BROADCAST_CLICKED = "playview_title_clicked";
  private final String PLAYVIEW_TITLE_CLICKED = "playview_title_clicked";
  private final String QTADID = "qtadid";
  private final int SECOND = 2;
  private final String SHORTCUT_ADDED = "shortcut_added";
  private final String SHOWDELETECONFIRM = "showdeleteconfirm";
  private final String TAOBAO_CHANGE = "TAOBAO_CHANGE";
  private final int THREE = 3;
  private final String WEIBO_HASMOVEDDATA = "weibo_hasmoveddata";
  private boolean allowAllBubble = false;
  private boolean allowLocalLiveRoom = false;
  private boolean disableLocalLiveRoom = false;
  private SharedPreferences.Editor editor;
  private final String key_boot_time = "key_boot_time";
  private final String key_flow_day_stamp = "key_flow_day_stamp";
  private final String key_jd_ad_delete_time = "key_jd_ad_delete_time";
  private final String key_last_pop_flow_time = "key_last_flow_pop_time";
  private final String key_wo_qt_alert_time = "key_wo_qt_alert_time";
  private final String key_wo_qt_day_stamp = "key_wo_qt_day_stamp";
  private int mChannelType = 0;
  private int mContentType = 0;
  private long mLatestBootstrapTime;
  private List<String> mLstAllowBubble;
  private List<String> mLstAllowChannelBubble;
  private List<String> mLstFilter;
  private List<String> mLstLocalLiveRoom;
  private String mPlayCategoryId = null;
  private String mPlayChannelId = null;
  private String mPlayParentId = null;
  private String mProgramId = null;
  private SharedPreferences sharedPreference;

  public static SharedCfg getInstance()
  {
    return INSTANCE;
  }

  private String getLocalRecommndProgramIgnored()
  {
    return this.sharedPreference.getString("KEY_LOCAL_RECOMMEND_IGNORED", "");
  }

  private void initBubbleList()
  {
    Object localObject;
    if (this.mLstAllowBubble == null)
    {
      this.mLstAllowBubble = new ArrayList();
      localObject = this.sharedPreference.getString("bubble_program_list", "");
      if (!((String)localObject).equalsIgnoreCase("all"))
        break label50;
      this.allowAllBubble = true;
    }
    while (true)
    {
      return;
      label50: localObject = ((String)localObject).split("_");
      int i = 0;
      while (i < localObject.length)
      {
        this.mLstAllowBubble.add(localObject[i]);
        i += 1;
      }
    }
  }

  private void initChannelBubbleList()
  {
    Object localObject;
    if (this.mLstAllowChannelBubble == null)
    {
      this.mLstAllowChannelBubble = new ArrayList();
      localObject = this.sharedPreference.getString("bubble_channel_list", "");
      if (!((String)localObject).equalsIgnoreCase("all"))
        break label50;
      this.allowAllBubble = true;
    }
    while (true)
    {
      return;
      label50: localObject = ((String)localObject).split("_");
      int i = 0;
      while (i < localObject.length)
      {
        this.mLstAllowChannelBubble.add(localObject[i]);
        i += 1;
      }
    }
  }

  private void initFilterList()
  {
    if (this.mLstFilter == null)
    {
      this.mLstFilter = new ArrayList();
      Object localObject = this.sharedPreference.getString("KEY_FILTER_LIVEROOM", "");
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
      {
        localObject = ((String)localObject).split("_");
        int i = 0;
        while (i < localObject.length)
        {
          this.mLstFilter.add(localObject[i]);
          i += 1;
        }
      }
    }
  }

  private boolean isShowlocalNotice()
  {
    return getlocalNotice().equals("yes");
  }

  public boolean IsfirstEnterLocal()
  {
    if (getLocal().equals(getVertion()))
      return false;
    setLocal(getVertion());
    return true;
  }

  public void addBootstrapCnt()
  {
    int i = getBootstrapCnt();
    this.editor.putInt("BOOTSTRAP_CNT", i + 1);
    this.editor.commit();
  }

  public void addCheckedInProgramId(String paramString)
  {
    if (paramString == null)
      return;
    String str1 = "checkin_by_day" + TimeUtil.msToDate2(System.currentTimeMillis());
    String str2 = this.sharedPreference.getString(str1, "");
    if ((str2 == null) || (str2.equalsIgnoreCase("")));
    for (paramString = "_" + paramString + "_"; ; paramString = str2 + "_" + paramString + "_")
    {
      this.editor.putString(str1, paramString);
      this.editor.commit();
      return;
    }
  }

  public void addHelpViewStage()
  {
    int i = this.sharedPreference.getInt("helpviewstage", 0);
    this.editor.putInt("helpviewstage", i + 1);
    this.editor.commit();
  }

  public void addLaunchTimes()
  {
    int i = this.sharedPreference.getInt("timespulllaunchapp", 1);
    this.editor.putInt("timespulllaunchapp", i + 1);
    this.editor.commit();
  }

  public void addPlayStation()
  {
    int i = this.sharedPreference.getInt("playstation", 1000);
    this.editor.putInt("playstation", i + 1);
    this.editor.commit();
  }

  public void addPlayStation2min()
  {
    int i = this.sharedPreference.getInt("playstation2min", 0);
    this.editor.putInt("playstation2min", i + 1);
    this.editor.commit();
  }

  public void addScanedStationCount()
  {
    int i = this.sharedPreference.getInt("scanstation", 0);
    this.editor.putInt("scanstation", i + 1);
    this.editor.commit();
  }

  public boolean allowChannelBubble(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return false;
      if (this.allowAllBubble)
        return true;
      if (this.mLstAllowChannelBubble == null)
        initChannelBubbleList();
      paramString = paramString.trim();
      int i = 0;
      while (i < this.mLstAllowChannelBubble.size())
      {
        if (((String)this.mLstAllowChannelBubble.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public boolean allowProgramBubble(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return false;
      if (this.allowAllBubble)
        return true;
      if (this.mLstAllowBubble == null)
        initBubbleList();
      int i = 0;
      while (i < this.mLstAllowBubble.size())
      {
        if (((String)this.mLstAllowBubble.get(i)).equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public void clearAllCache()
  {
    this.editor.clear();
    this.editor.commit();
  }

  public void clearPlayStation()
  {
    this.editor.putInt("playstation", 0);
    this.editor.commit();
  }

  public void clearPlayStation2min()
  {
    this.editor.putInt("playstation2min", 0);
    this.editor.commit();
  }

  public void clearScanedStationCount()
  {
    this.editor.putInt("scanstation", 0);
    this.editor.commit();
  }

  public boolean containADid(String paramString)
  {
    return false;
  }

  public String getAdMasterUrl()
  {
    return this.sharedPreference.getString("KEY_ADMASTER_URL", null);
  }

  public boolean getAddCollection()
  {
    return this.sharedPreference.getBoolean("KEY_ADD_COLLECTION", false);
  }

  public boolean getAddVirtualChannel()
  {
    return this.sharedPreference.getBoolean("KEY_ADD_VIRTUAL_CHANNEL", false);
  }

  public String getAdids()
  {
    return this.sharedPreference.getString("qtadid", "");
  }

  public String getAdvertisementImage(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (!paramString.equalsIgnoreCase(""))
        localObject1 = this.sharedPreference.getString(paramString, null);
    }
    return localObject1;
  }

  public int getApolloAppStart()
  {
    return this.sharedPreference.getInt("key_apollo_app_start", 0);
  }

  public int getApolloDuration()
  {
    return this.sharedPreference.getInt("APOLLO_Duration", 0);
  }

  public int getApolloStartTime()
  {
    return this.sharedPreference.getInt("APOLLO_START_TIME", 0);
  }

  public String getApp()
  {
    return this.sharedPreference.getString(getVertion() + "key_appfirst", "");
  }

  public int getAppLocalCount()
  {
    return this.sharedPreference.getInt(getVertion() + "local", 0);
  }

  public int getAppStartCount()
  {
    return this.sharedPreference.getInt(getVertion() + "init", 0);
  }

  public long getAtMeLatestTime(String paramString)
  {
    return this.sharedPreference.getLong("key_atme_latest_time" + paramString, 0L);
  }

  public int getAudioQualitySetting()
  {
    return this.sharedPreference.getInt("audioquality", 0);
  }

  public boolean getAutoPlayAfterStart()
  {
    return this.sharedPreference.getBoolean("KEY_AUTO_PLAY_AFTER_START", false);
  }

  public int getAutoReserveDuration()
  {
    return this.sharedPreference.getInt("KEY_AUTO_RESERVE_DURATION", 120);
  }

  public boolean getAutoSeek()
  {
    return this.sharedPreference.getBoolean("KEY_AUTO_SEEK", true);
  }

  public int getBootstrapCnt()
  {
    return this.sharedPreference.getInt("BOOTSTRAP_CNT", 0);
  }

  public int getBootstrapMax()
  {
    return this.sharedPreference.getInt("BOOTSTRAPMAX", 0);
  }

  public String getCategoryOrderString()
  {
    return this.sharedPreference.getString("key_category_order", null);
  }

  public String getChannelName()
  {
    return this.sharedPreference.getString("channelname", "getDataError");
  }

  public int getChooseGender()
  {
    return this.sharedPreference.getInt("KEY_CHOOSE_GENDER", 0);
  }

  public int getChooseStudent()
  {
    return this.sharedPreference.getInt("KEY_CHOOSE_STUDENT", 0);
  }

  public int getChooseUser()
  {
    return this.sharedPreference.getInt("KEY_CHOOSE_USER", 0);
  }

  public String getCity()
  {
    return this.sharedPreference.getString(getVertion() + "key_city", "");
  }

  public String getCurrHttpUrlVersion()
  {
    return this.sharedPreference.getString("KEY_NEED_CLEAR_HTTPURL", "");
  }

  public boolean getDefaultCollection()
  {
    return this.sharedPreference.getBoolean("KEY_DEFAULT_COLLECTION", false);
  }

  public boolean getDeleteConfirm()
  {
    return this.sharedPreference.getBoolean("showdeleteconfirm", true);
  }

  public boolean getDevMode()
  {
    return this.sharedPreference.getBoolean("dev_mode", false);
  }

  public boolean getDisplayAD()
  {
    return this.sharedPreference.getBoolean("KEY_DISPLAY_AD", false);
  }

  public boolean getDoubleBackToQuit()
  {
    return this.sharedPreference.getBoolean("key_double_back_quit", false);
  }

  public int getDownloadCnt()
  {
    return this.sharedPreference.getInt("key_download_program_today", 0);
  }

  public long getDownloadDate()
  {
    return this.sharedPreference.getLong("key_download_today", 0L);
  }

  public int getDownloadDuration()
  {
    return this.sharedPreference.getInt("key_download_duration_today", 0);
  }

  public boolean getEnableAdvertisement()
  {
    return this.sharedPreference.getBoolean("enable_advertisement", false);
  }

  public String getEnableAudioTips()
  {
    return this.sharedPreference.getString("KEY_ENABLE_AUDIO_TIPS", "");
  }

  public boolean getEnableAutoSeek()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_AUTO_SEEK", true);
  }

  public boolean getEnableBillboard()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_BILLBOARD", false);
  }

  public boolean getEnableClockSearch()
  {
    return this.sharedPreference.getBoolean("key_enable_clock_search", false);
  }

  public boolean getEnableEasterEgg()
  {
    return this.sharedPreference.getBoolean("key_enable_easter_egg", false);
  }

  public boolean getEnableLivePush()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_LIVE_PUSH", false);
  }

  public boolean getEnableMediaControll()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_MEDIA_CONTROLL", true);
  }

  public boolean getEnableMobileNetwork()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_MOBILE_NETWORK", false);
  }

  public int getEnableMusicRadio()
  {
    return this.sharedPreference.getInt("key_musicradio", 0);
  }

  public boolean getEnableNavigation()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_NAVIGATION", false);
  }

  public boolean getEnableNewDownload()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_NEW_DOWNLOAD", false);
  }

  public boolean getEnableOriginalSource()
  {
    return this.sharedPreference.getBoolean("key_original", true);
  }

  public boolean getEnableRecPage()
  {
    return this.sharedPreference.getBoolean("KEY_ENABLE_RECPAGE", false);
  }

  public boolean getEnableSubNotification()
  {
    return this.sharedPreference.getBoolean("KEY_SUB_NOTIFICATION", true);
  }

  public String getFMPlayIndex()
  {
    return this.sharedPreference.getString("key_fmplayindex", "");
  }

  public int getFavNumber()
  {
    return this.sharedPreference.getInt("favnumber", 0);
  }

  public String getFeedbackCategory()
  {
    return this.sharedPreference.getString("feedback_category", "其他问题");
  }

  public String getFeedbackContactInfo()
  {
    return this.sharedPreference.getString("key_feedback_contactinfo", null);
  }

  public int getFlowDayStamp()
  {
    return this.sharedPreference.getInt("key_flow_day_stamp", 15);
  }

  public long getFlowerChangeTime(String paramString)
  {
    return this.sharedPreference.getLong("key_flower_latest_update_time" + paramString, 0L);
  }

  public int getFlowerCnt(String paramString)
  {
    return this.sharedPreference.getInt("key_flower_cnt" + paramString, 10);
  }

  public long getForceLogin()
  {
    return this.sharedPreference.getLong("FORCE_LOGIN", 0L);
  }

  public boolean getFuck360()
  {
    return this.sharedPreference.getBoolean("KEY_FUCK_360", false);
  }

  public boolean getGuideShowed()
  {
    return this.sharedPreference.getBoolean("key_showed_guide", false);
  }

  public boolean getHasDeleteOldCollection()
  {
    return this.sharedPreference.getBoolean("key_has_del_old_collection", false);
  }

  public String getHeadSetCmd()
  {
    return this.sharedPreference.getString(getVertion() + "key_headsetplug", "null");
  }

  public int getHelpViewStage()
  {
    return this.sharedPreference.getInt("helpviewstage", 0);
  }

  public boolean getHomePageIsPlayView()
  {
    return false;
  }

  public int getIREChange()
  {
    return this.sharedPreference.getInt("IRE_CHANGE", 4);
  }

  public boolean getIfInRelisten()
  {
    return this.sharedPreference.getBoolean("not_relisten_view", false);
  }

  public boolean getIsOndemand()
  {
    return this.sharedPreference.getBoolean("is_ondemand", false);
  }

  public long getJdAdDeleteTime()
  {
    return this.sharedPreference.getLong("key_jd_ad_delete_time", 0L);
  }

  public String getLastAvailableCity()
  {
    return this.sharedPreference.getString("city", null);
  }

  public String getLastAvailableRegion()
  {
    return this.sharedPreference.getString("region", null);
  }

  public int getLastDay()
  {
    return this.sharedPreference.getInt("lastday", 1);
  }

  public int getLastDay_sendMessage(String paramString)
  {
    return this.sharedPreference.getInt(paramString, -1);
  }

  public String getLastPlayInfo()
  {
    return this.sharedPreference.getString(this.KEY_PLAY_INFO, null);
  }

  public long getLastPopFlowTime()
  {
    return this.sharedPreference.getLong("key_last_flow_pop_time", 0L);
  }

  public long getLastWoQtAlertTime()
  {
    return this.sharedPreference.getLong("key_wo_qt_alert_time", 0L);
  }

  public long getLatestBootstrapTime()
  {
    if (this.mLatestBootstrapTime == 0L)
      this.mLatestBootstrapTime = this.sharedPreference.getLong("key_boot_time", 0L);
    return this.mLatestBootstrapTime;
  }

  public String getLatestVersion()
  {
    return this.sharedPreference.getString("key_latest_version", "0.0.0");
  }

  public int getLaunchTimes()
  {
    return this.sharedPreference.getInt("timespulllaunchapp", 1);
  }

  public String getLocal()
  {
    return this.sharedPreference.getString(getVertion() + "key_localfirst", "");
  }

  public String getLocalAdvertisementPic()
  {
    return this.sharedPreference.getString("KEY_LOCAL_ADVERTISEMENT_PIC", null);
  }

  public String getLocalNotify()
  {
    return this.sharedPreference.getString("KEY_LOCAL_NOTIFY", "");
  }

  public int getLocalRecommendProgramBeginMax()
  {
    return this.sharedPreference.getInt("KEY_LOCAL_RECOMMEND_BEGIN_MAX", 0);
  }

  public int getLocalRecommendProgramBeginMin()
  {
    return this.sharedPreference.getInt("KEY_LOCAL_RECOMMEND_BEGIN_MIN", 0);
  }

  public int getLocalRecommendThreshold()
  {
    return this.sharedPreference.getInt("key_local_recommend_duration", 10);
  }

  public int getLocalRecommndProgramDelayed()
  {
    return this.sharedPreference.getInt("KEY_LOCAL_RECOMMEND_DELAYED", 0);
  }

  public String getLocalRingsVersion()
  {
    return this.sharedPreference.getString("KEY_LOCALRINGS_VERSION", "0");
  }

  public boolean getMobileAlert()
  {
    return this.sharedPreference.getBoolean("KEY_MOBILE_PLAY_ALERT", false);
  }

  public boolean getMobileFirstAlert()
  {
    return this.sharedPreference.getBoolean("KEY_MOBILE_FIRST_ALERT", false);
  }

  public boolean getMobilePlayOrStop()
  {
    return this.sharedPreference.getBoolean("KEY_MOBILE_PLAY_OR_STOP", true);
  }

  public boolean getMutiRate()
  {
    return this.sharedPreference.getBoolean("key_mult_rate", false);
  }

  public boolean getNeedEducationCollapse()
  {
    return this.sharedPreference.getBoolean("key_education_collapse", true);
  }

  public boolean getNeedEducationSort()
  {
    return this.sharedPreference.getBoolean("key_education_sort", true);
  }

  public String getNetAdvertisementPic()
  {
    return this.sharedPreference.getString("KEY_NET_ADVERTISEMENT_PIC", null);
  }

  public int getNetNotification()
  {
    return this.sharedPreference.getInt("KEY_RECV_NOTIFICATION_NET", 10);
  }

  public String getNewCDN()
  {
    return this.sharedPreference.getString("KEY_NEW_CDN", "qtmedia.b0.upaiyun.com");
  }

  public String getNewHTTPUrlVer()
  {
    getClass();
    return "1.0";
  }

  public String getNewOfflienDBVersion()
  {
    return "8.3";
  }

  public String getNewRingsVersion()
  {
    return "2.0";
  }

  public String getNickName()
  {
    return this.sharedPreference.getString("chat_nickname", "");
  }

  public String getNotice()
  {
    return this.sharedPreference.getString(getVertion() + "key_notice", "yes");
  }

  public String getOfflineDBVersion()
  {
    return this.sharedPreference.getString("KEY_OFFLINEDB_VERSION", "0");
  }

  public String getOndemandAlbumPlayState(String paramString)
  {
    paramString = this.sharedPreference.getString("play" + paramString, "");
    if (!paramString.equalsIgnoreCase(""))
      return paramString;
    return null;
  }

  public boolean getOnlyUsedInWifi()
  {
    return this.sharedPreference.getBoolean("KEY_USED_IN_WIFI", false);
  }

  public int getPlayStation()
  {
    return this.sharedPreference.getInt("playstation", 1000);
  }

  public int getPlayStation2min()
  {
    return this.sharedPreference.getInt("playstation2min", 0);
  }

  public int getPlayer()
  {
    return this.sharedPreference.getInt(getVertion() + "key_player", 0);
  }

  public int getPreCount()
  {
    return this.sharedPreference.getInt("timespullquickview", 0);
  }

  public String getQQAccessToken()
  {
    return this.sharedPreference.getString("key_qq_access_token", null);
  }

  public String getQQExpireTime()
  {
    return this.sharedPreference.getString("key_qq_expire_time", null);
  }

  public String getQQGender()
  {
    return this.sharedPreference.getString("key_qq_gender", "n");
  }

  public String getQQOpenId()
  {
    return this.sharedPreference.getString("key_qq_open_id", null);
  }

  public String getQQUserKey()
  {
    return this.sharedPreference.getString("key_qq_user_key", null);
  }

  public boolean getREcordPRE()
  {
    return this.sharedPreference.getBoolean("key_recordpre", false);
  }

  public int getRandSelectIndex()
  {
    return this.sharedPreference.getInt("KEY_RAND_SELECT_INDEX", -1);
  }

  public String getRecentKeyWords()
  {
    return this.sharedPreference.getString("KEY_RECENT_KEYWORDS", null);
  }

  public boolean getRecommendShare()
  {
    return this.sharedPreference.getBoolean("KEY_RECOMMEND_SHARE_FLAG", false);
  }

  public boolean getRecommendShareLogin()
  {
    return this.sharedPreference.getBoolean("KEY_RECOMMEND_SHARE_LOGIN", false);
  }

  public boolean getRecommendShareUpdate()
  {
    return this.sharedPreference.getBoolean("KEY_RECOMMEND_SHARE_UPDATE", false);
  }

  public long getRecommendShareUpdateTime()
  {
    return this.sharedPreference.getLong("KEY_RECOMMEND_SHARE_UPDATE_TIME", 0L);
  }

  public String getRecordName()
  {
    return this.sharedPreference.getString("switch_record", "");
  }

  public int getRelistenIndex()
  {
    return this.sharedPreference.getInt("relisten_index", 0);
  }

  public String getReverseChannelIds()
  {
    return this.sharedPreference.getString("KEY_REVERSE_CHANNEL_IDS", null);
  }

  public boolean getSaveBattery()
  {
    return this.sharedPreference.getBoolean("key_save_battery", false);
  }

  public String getScanCompleteCmd()
  {
    return this.sharedPreference.getString(getVertion() + "key_scancomplete", "null");
  }

  public int getScanedStationCount()
  {
    return this.sharedPreference.getInt("scanstation", 0);
  }

  public String getScanfm()
  {
    return this.sharedPreference.getString(getVertion() + "key_vertionscaned", "");
  }

  public boolean getShareToTencent()
  {
    return this.sharedPreference.getBoolean("key_share_to_tencent", false);
  }

  public boolean getShareToWeibo()
  {
    return this.sharedPreference.getBoolean("key_share_to_weibo", false);
  }

  public boolean getShowContentPage()
  {
    return this.sharedPreference.getBoolean("KEY_SHOW_CONTENT_PAGE", true);
  }

  public boolean getShowWebViewUser()
  {
    return this.sharedPreference.getBoolean("key_showwebview" + getVertion(), true);
  }

  public String getSingleName()
  {
    return this.sharedPreference.getString("single_name", "");
  }

  public boolean getSpeakerState()
  {
    return this.sharedPreference.getBoolean("speakerstate", false);
  }

  public String getSplashLanding()
  {
    return this.sharedPreference.getString("KEY_SPLASH_LANDING", null);
  }

  public boolean getStartSwitch()
  {
    return this.sharedPreference.getBoolean(getVertion() + "key_startswitch", false);
  }

  public int getTaoBaoChange()
  {
    return this.sharedPreference.getInt("TAOBAO_CHANGE", 2);
  }

  public String getTencentAccessToken()
  {
    return this.sharedPreference.getString("key_tencent_accesstoken", null);
  }

  public long getTencentExpires()
  {
    return this.sharedPreference.getLong("key_tencent_expires", 0L);
  }

  public String getTencentGender()
  {
    return this.sharedPreference.getString("KEY_TENCENT_GENDER", "n");
  }

  public String getTencentOpenId()
  {
    return this.sharedPreference.getString("key_tencent_openid", null);
  }

  public String getTencentSocialUserKey()
  {
    return this.sharedPreference.getString("KEY_TENCENT_SOCIAL_USER_KEY", "");
  }

  public String getTipsVersion()
  {
    return this.sharedPreference.getString("KEY_TIPS_VERSION", "0");
  }

  public long getUpgradeTime()
  {
    return this.sharedPreference.getLong("key_popup_upgrade_reminder", 0L);
  }

  public String getValue(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return null;
    return this.sharedPreference.getString(paramString, null);
  }

  public int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }

  public String getVertion()
  {
    return this.sharedPreference.getString("key_vertion", "");
  }

  public String getWeChatAccessToken()
  {
    return this.sharedPreference.getString("key_wechat_access_token", null);
  }

  public String getWeChatGender()
  {
    return this.sharedPreference.getString("key_wechat_gender", "n");
  }

  public String getWeChatOpenId()
  {
    return this.sharedPreference.getString("key_wechat_open_id", null);
  }

  public String getWeChatRefreshToken()
  {
    return this.sharedPreference.getString("key_wechat_refresh_token", null);
  }

  public String getWeChatUserKey()
  {
    return this.sharedPreference.getString("key_wechat_user_key", null);
  }

  public String getWeiboAuth(String paramString1, String paramString2)
  {
    return this.sharedPreference.getString(paramString1, paramString2);
  }

  public String getWeiboGender()
  {
    return this.sharedPreference.getString("KEY_WEIBO_GENDER", "n");
  }

  public String getWeiboSocialUserKey()
  {
    return this.sharedPreference.getString("KEY_WEIBO_SOCIAL_USER_KEY", "");
  }

  public int getWifiPolicy()
  {
    return this.sharedPreference.getInt("wifipolicy", -1);
  }

  public String getWoApiToken()
  {
    return this.sharedPreference.getString("key_wo_api_token", "");
  }

  public String getWoBindCallNumber()
  {
    return this.sharedPreference.getString(this.KEY_WO_BIND_CALL_NUMBER, "");
  }

  public int getWoQtAlertDayStamp()
  {
    return this.sharedPreference.getInt("key_wo_qt_day_stamp", 7);
  }

  public String getWoTriedTime()
  {
    return this.sharedPreference.getString("key_wo_tried_seconds", "");
  }

  public String getWoTryDate()
  {
    return this.sharedPreference.getString("key_wo_try_date", "");
  }

  public int getY()
  {
    return this.sharedPreference.getInt(getVertion() + "key_yposition", 0);
  }

  public boolean getYState()
  {
    return this.sharedPreference.getBoolean(getVertion() + "key_ystate", false);
  }

  public String getlocalNotice()
  {
    return this.sharedPreference.getString(getVertion() + "key_localnotice", "yes");
  }

  public boolean hasAddedAlarm()
  {
    return this.sharedPreference.getBoolean("alarm_added", false);
  }

  public boolean hasAddedShortcut()
  {
    return this.sharedPreference.getBoolean("shortcut_added", false);
  }

  public boolean hasAlarmClicked()
  {
    return this.sharedPreference.getBoolean("hasalarmclicked", false);
  }

  public boolean hasAlarmReminded()
  {
    return this.sharedPreference.getBoolean("alarm_reminded", false);
  }

  public boolean hasBroadcastClicked()
  {
    return this.sharedPreference.getBoolean("playview_title_clicked", false);
  }

  public boolean hasCheckedIn(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    String str;
    do
    {
      return false;
      str = "checkin_by_day" + TimeUtil.msToDate2(System.currentTimeMillis());
      str = this.sharedPreference.getString(str, "");
    }
    while ((str == null) || (!str.contains("-" + paramString + "_")));
    return true;
  }

  public boolean hasClickedLogIn()
  {
    return this.sharedPreference.getBoolean("haslogclicked", false);
  }

  public boolean hasClickedPlusInChatroom()
  {
    return this.sharedPreference.getBoolean("hasclickedplus", false);
  }

  public boolean hasCollectionClicked()
  {
    return this.sharedPreference.getBoolean("hascollectionclicked", false);
  }

  public boolean hasFilterShowed(String paramString)
  {
    if (paramString == null)
      return false;
    return this.sharedPreference.getBoolean(paramString, false);
  }

  public boolean hasGuideCollectionDone()
  {
    return this.sharedPreference.getBoolean("guide_collection", false);
  }

  public boolean hasNewFeatureFm()
  {
    return (isFeatureNew("feature_schedule")) || (isFeatureNew("feature_speaker"));
  }

  public boolean hasNewFeatureLive()
  {
    return (isFeatureNew("feature_mycollection")) || (isFeatureNew("feature_schedule")) || (isFeatureNew("feature_subscribe")) || (isFeatureNew("feature_replay")) || (isFeatureNew("feature_download")) || (isFeatureNew("feature_alarm"));
  }

  public boolean hasNewFeatureOndemand()
  {
    return (isFeatureNew("feature_checkin")) || (isFeatureNew("feature_download_ondemand")) || (isFeatureNew("feature_subscribe_novel")) || (isFeatureNew("feature_subscribe_podcast"));
  }

  public boolean hasNewFeatureReplay()
  {
    return (isFeatureNew("feature_mycollection")) || (isFeatureNew("feature_schedule")) || (isFeatureNew("feature_subscribe")) || (isFeatureNew("feature_replay")) || (isFeatureNew("feature_download")) || (isFeatureNew("feature_comment")) || (isFeatureNew("feature_alarm"));
  }

  public boolean hasRemindGroup()
  {
    return this.sharedPreference.getBoolean("key_remind_group", false);
  }

  public boolean hasTitleClicked()
  {
    return this.sharedPreference.getBoolean("playview_title_clicked", false);
  }

  public boolean hasWeiboDataMoved()
  {
    return this.sharedPreference.getBoolean("weibo_hasmoveddata", false);
  }

  public boolean hitFilter(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return false;
      initFilterList();
      if (this.mLstFilter != null)
      {
        int i = 0;
        while (i < this.mLstFilter.size())
        {
          if (paramString.contains((CharSequence)this.mLstFilter.get(i)))
            return true;
          i += 1;
        }
      }
    }
  }

  public void incApolloAppStart(int paramInt)
  {
    this.editor.putInt("key_apollo_app_start", paramInt);
    this.editor.commit();
  }

  public void init(Context paramContext)
  {
    this.sharedPreference = ((Activity)paramContext).getPreferences(0);
    this.editor = this.sharedPreference.edit();
  }

  public boolean isApollo()
  {
    return this.sharedPreference.getBoolean("IS_APOLLO", false);
  }

  public boolean isBaseCount()
  {
    return (getAppStartCount() == 1) || (getAppStartCount() == 2) || (getAppStartCount() == 3);
  }

  public boolean isCollectionUpgraded()
  {
    return this.sharedPreference.getBoolean("Key_has_upgraded_collection_2013_11_14", false);
  }

  public boolean isDrag()
  {
    return this.sharedPreference.getBoolean("key_dragbtn", false);
  }

  public boolean isDragHelpShowed()
  {
    return this.sharedPreference.getBoolean("drag_help_showed", false);
  }

  public boolean isFeatureNew(String paramString)
  {
    boolean bool = true;
    if ((paramString.equalsIgnoreCase("feature_mycollection")) || (paramString.equalsIgnoreCase("feature_schedule")) || (paramString.equalsIgnoreCase("feature_subscribe")) || (paramString.equalsIgnoreCase("feature_replay")) || (paramString.equalsIgnoreCase("feature_comment")) || (paramString.equalsIgnoreCase("feature_speaker")) || (paramString.equalsIgnoreCase("feature_checkin")) || (paramString.equalsIgnoreCase("feature_subscribe_novel")) || (paramString.equalsIgnoreCase("feature_subscribe_podcast")))
      bool = false;
    do
    {
      return bool;
      if (!paramString.equalsIgnoreCase("feature_alarm"))
        break;
    }
    while (!hasAddedAlarm());
    return false;
    return this.sharedPreference.getBoolean(paramString, true);
  }

  public boolean isFirst2PlayController()
  {
    return this.sharedPreference.getBoolean("first2playcontroller", true);
  }

  public boolean isFirst2Playview()
  {
    return this.sharedPreference.getBoolean("first2playview_v2", true);
  }

  public boolean isLocalBaseCount()
  {
    return (getAppLocalCount() == 1) || (getAppLocalCount() == 2) || (getAppLocalCount() == 3);
  }

  public boolean isLocalRecommendProgramIgnored(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return true;
      paramString = paramString.trim();
    }
    while (getLocalRecommndProgramIgnored().contains(paramString));
    return false;
  }

  public boolean isNeedLaunchLocal()
  {
    if ((!isScande()) && (isLocalBaseCount()) && (isShowlocalNotice()))
    {
      setlocalNotice("no");
      return true;
    }
    return false;
  }

  public boolean isNeedLaunchView()
  {
    return (!isScande()) && (isBaseCount()) && (!getStartSwitch());
  }

  public boolean isNewUser()
  {
    return this.sharedPreference.getBoolean("NEW_USER", true);
  }

  public boolean isNickAvailable()
  {
    return !this.sharedPreference.getString("chat_nickname", "").equalsIgnoreCase("");
  }

  public boolean isOldVirtualDeleted()
  {
    return this.sharedPreference.getBoolean("Key_old_favored_virtual_delete", false);
  }

  public boolean isScande()
  {
    return getScanfm().equalsIgnoreCase(getVertion());
  }

  public boolean isfirstOpenApp()
  {
    if (getApp().equalsIgnoreCase(getVertion()))
      return false;
    setApp(getVertion());
    return true;
  }

  public boolean isshow2Gor3GPS()
  {
    if (getNotice().equalsIgnoreCase("yes"))
    {
      setNotice("no");
      return true;
    }
    return false;
  }

  public void removeQQAccessToken()
  {
    this.editor.remove("key_qq_access_token");
    this.editor.commit();
  }

  public void removeQQExpiresTime()
  {
    this.editor.remove("key_qq_expire_time");
    this.editor.commit();
  }

  public void removeQQGender()
  {
    this.editor.remove("key_qq_gender");
    this.editor.commit();
  }

  public void removeQQOpenId()
  {
    this.editor.remove("key_qq_open_id");
    this.editor.commit();
  }

  public void removeQQUserKey()
  {
    this.editor.remove("key_qq_user_key");
    this.editor.commit();
  }

  public void removeWeChatAccessToken()
  {
    this.editor.remove("key_wechat_access_token");
    this.editor.commit();
  }

  public void removeWeChatGender()
  {
    this.editor.remove("key_wechat_gender");
    this.editor.commit();
  }

  public void removeWeChatOpenId()
  {
    this.editor.remove("key_wechat_open_id");
    this.editor.commit();
  }

  public void removeWeChatRefreshToken()
  {
    this.editor.remove("key_wechat_refresh_token");
    this.editor.commit();
  }

  public void removeWeChatUserKey()
  {
    this.editor.remove("key_wechat_user_key");
    this.editor.commit();
  }

  public void saveCategoryOrderString(String paramString)
  {
    this.editor.putString("key_category_order", paramString);
    this.editor.commit();
  }

  public void saveFeedBackContactInfo(String paramString)
  {
    String str = paramString;
    if (paramString == null)
      str = "";
    this.editor.putString("key_feedback_contactinfo", str);
    this.editor.commit();
  }

  public void saveOndemandAlbumPlayState(String paramString1, String paramString2)
  {
    this.editor.putString("play" + paramString1, paramString2);
    this.editor.commit();
  }

  public void saveValue(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")))
      return;
    this.editor.putString(paramString1, paramString2);
    this.editor.commit();
  }

  public void setAdMasterUrl(String paramString)
  {
    this.editor.putString("KEY_ADMASTER_URL", paramString);
    this.editor.commit();
  }

  public void setAddCollection(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ADD_COLLECTION", paramBoolean);
    this.editor.commit();
  }

  public void setAddVirtualChannel(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ADD_VIRTUAL_CHANNEL", paramBoolean);
    this.editor.commit();
  }

  public void setAdid(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getAdids());
    if (localStringBuilder.toString().contains(paramString))
      return;
    this.editor.putString("qtadid", new StringBuilder().append(paramString).append("**").toString());
    this.editor.commit();
  }

  public void setAdvertisementImage(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (!paramString1.equalsIgnoreCase("")))
    {
      this.editor.putString(paramString1, paramString2);
      this.editor.commit();
    }
  }

  public void setAlarmAdded()
  {
    this.editor.putBoolean("alarm_added", true);
    this.editor.commit();
  }

  public void setAlarmClicked()
  {
    this.editor.putBoolean("hasalarmclicked", true);
    this.editor.commit();
  }

  public void setAlarmReminded()
  {
    this.editor.putBoolean("alarm_reminded", true);
    this.editor.commit();
  }

  public void setApollo(boolean paramBoolean)
  {
    this.editor.putBoolean("IS_APOLLO", paramBoolean);
    this.editor.commit();
  }

  public void setApolloDuration(int paramInt)
  {
    this.editor.putInt("APOLLO_Duration", paramInt);
    this.editor.commit();
  }

  public void setApolloStartTime(int paramInt)
  {
    this.editor.putInt("APOLLO_START_TIME", paramInt);
    this.editor.commit();
  }

  public void setApp(String paramString)
  {
    this.editor.putString(getVertion() + "key_appfirst", paramString);
    this.editor.commit();
  }

  public void setAppLocalCount()
  {
    int i = getAppLocalCount();
    this.editor.putInt(getVertion() + "local", i + 1);
    this.editor.commit();
  }

  public void setAppLocalCountMax()
  {
    int i = getAppLocalCount();
    this.editor.putInt(getVertion() + "local", i + 10);
    this.editor.commit();
  }

  public void setAppStartCount()
  {
    int i = getAppStartCount();
    this.editor.putInt(getVertion() + "init", i + 1);
    this.editor.commit();
  }

  public void setAudioQualitySetting(int paramInt)
  {
    this.editor.putInt("audioquality", paramInt);
    this.editor.commit();
  }

  public void setAutoPlayAfterStart(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_AUTO_PLAY_AFTER_START", paramBoolean);
    this.editor.commit();
  }

  public void setAutoReserveDuration(int paramInt)
  {
    this.editor.putInt("KEY_AUTO_RESERVE_DURATION", paramInt);
    this.editor.commit();
  }

  public void setAutoSeek(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_AUTO_SEEK", paramBoolean);
    this.editor.commit();
  }

  public void setBootstrapMax(int paramInt)
  {
    this.editor.putInt("BOOTSTRAPMAX", paramInt);
    this.editor.commit();
  }

  public void setBootstrapTime(long paramLong)
  {
    this.mLatestBootstrapTime = this.sharedPreference.getLong("key_boot_time", 0L);
    this.editor.putLong("key_boot_time", paramLong);
    this.editor.commit();
  }

  public void setBroadcastClicked()
  {
    this.editor.putBoolean("playview_title_clicked", true);
    this.editor.commit();
  }

  public void setBubbleChannelList(String paramString)
  {
    this.editor.putString("bubble_channel_list", paramString);
    this.editor.commit();
  }

  public void setBubbleProgramList(String paramString)
  {
    this.editor.putString("bubble_program_list", paramString);
    this.editor.commit();
  }

  public void setChannelName(String paramString)
  {
    this.editor.putString("channelname", paramString);
    this.editor.commit();
  }

  public void setChooseGender(int paramInt)
  {
    this.editor.putInt("KEY_CHOOSE_GENDER", paramInt);
    this.editor.commit();
  }

  public void setChooseStudent(int paramInt)
  {
    this.editor.putInt("KEY_CHOOSE_STUDENT", paramInt);
    this.editor.commit();
  }

  public void setChooseUser(int paramInt)
  {
    this.editor.putInt("KEY_CHOOSE_USER", paramInt);
    this.editor.commit();
  }

  public void setCity(String paramString)
  {
    this.editor.putString(getVertion() + "key_city", paramString);
    this.editor.commit();
  }

  public void setCollectionClicked()
  {
    this.editor.putBoolean("hascollectionclicked", true);
    this.editor.commit();
  }

  public void setCollectionUpgraded()
  {
    this.editor.putBoolean("Key_has_upgraded_collection_2013_11_14", true);
    this.editor.commit();
  }

  public void setCurrentPosition(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    this.editor.putString("city", paramString1);
    this.editor.putString("region", paramString2);
    this.editor.commit();
  }

  public void setDRag(boolean paramBoolean)
  {
    this.editor.putBoolean("key_dragbtn", paramBoolean);
    this.editor.commit();
  }

  public void setDefaultCollection(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_DEFAULT_COLLECTION", paramBoolean);
    this.editor.commit();
  }

  public void setDeleteConfirm()
  {
    this.editor.putBoolean("showdeleteconfirm", false);
    this.editor.commit();
  }

  public void setDisplayAD(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_DISPLAY_AD", paramBoolean);
    this.editor.commit();
  }

  public void setDoubleBackToQuit(boolean paramBoolean)
  {
    this.editor.putBoolean("key_double_back_quit", paramBoolean);
    this.editor.commit();
  }

  public void setDownloadCnt(int paramInt)
  {
    this.editor.putInt("key_download_program_today", paramInt);
    this.editor.commit();
  }

  public void setDownloadDate(long paramLong)
  {
    this.editor.putLong("key_download_today", paramLong);
    this.editor.commit();
  }

  public void setDownloadDuration(int paramInt)
  {
    this.editor.putInt("key_download_duration_today", paramInt);
    this.editor.commit();
  }

  public void setDragHelpShowed()
  {
    this.editor.putBoolean("drag_help_showed", true);
    this.editor.commit();
  }

  public void setEducationCollspaseShowed()
  {
    this.editor.putBoolean("key_education_collapse", false);
    this.editor.commit();
  }

  public void setEducationSortShowed()
  {
    this.editor.putBoolean("key_education_sort", false);
    this.editor.commit();
  }

  public void setEnableAdvertisement(boolean paramBoolean)
  {
    this.editor.putBoolean("enable_advertisement", paramBoolean);
    this.editor.commit();
  }

  public void setEnableAudioTips(String paramString)
  {
    this.editor.putString("KEY_ENABLE_AUDIO_TIPS", paramString);
    this.editor.commit();
  }

  public void setEnableAutoSeek(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_AUTO_SEEK", paramBoolean);
    this.editor.commit();
  }

  public void setEnableBillboard(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_BILLBOARD", paramBoolean);
    this.editor.commit();
  }

  public void setEnableClockSearch(boolean paramBoolean)
  {
    this.editor.putBoolean("key_enable_clock_search", paramBoolean);
    this.editor.commit();
  }

  public void setEnableContentPage(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_SHOW_CONTENT_PAGE", paramBoolean);
    this.editor.commit();
  }

  public void setEnableDelChannelCache(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_DEL_CHANNEL_CACHE", paramBoolean);
    this.editor.commit();
  }

  public void setEnableEasterEgg(boolean paramBoolean)
  {
    this.editor.putBoolean("key_enable_easter_egg", paramBoolean);
    this.editor.commit();
  }

  public void setEnableForeignChannels(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_FOREIGN_CHANNELS", paramBoolean);
    this.editor.commit();
  }

  public void setEnableLivePush(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_LIVE_PUSH", paramBoolean);
    this.editor.commit();
  }

  public void setEnableMediaControll(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_MEDIA_CONTROLL", paramBoolean);
    this.editor.commit();
  }

  public void setEnableMobileNetwork(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_MOBILE_NETWORK", paramBoolean);
    this.editor.commit();
  }

  public void setEnableMusicRadio(boolean paramBoolean)
  {
    if (paramBoolean)
      this.editor.putInt("key_musicradio", 1);
    while (true)
    {
      this.editor.commit();
      return;
      this.editor.putInt("key_musicradio", 0);
    }
  }

  public void setEnableNavigation(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_NAVIGATION", paramBoolean);
    this.editor.commit();
  }

  public void setEnableNewDownload(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_NEW_DOWNLOAD", paramBoolean);
    this.editor.commit();
  }

  public void setEnableOriginalSource(boolean paramBoolean)
  {
    if (paramBoolean)
      this.editor.putBoolean("key_original", true);
    while (true)
    {
      this.editor.commit();
      return;
      this.editor.putBoolean("key_original", false);
    }
  }

  public void setEnableRecPage(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_ENABLE_RECPAGE", paramBoolean);
    this.editor.commit();
  }

  public void setEnableSubNotification(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_SUB_NOTIFICATION", paramBoolean);
    this.editor.commit();
  }

  public void setFMPlayIndex(String paramString)
  {
    this.editor.putString("key_fmplayindex", paramString);
    this.editor.commit();
  }

  public void setFavNumber(int paramInt)
  {
    this.editor.putInt("favnumber", paramInt);
    this.editor.commit();
  }

  public void setFeatureOld(String paramString)
  {
    this.editor.putBoolean(paramString, false);
    this.editor.commit();
  }

  public void setFeedbackCategory(String paramString)
  {
    this.editor.putString("feedback_category", paramString);
    this.editor.commit();
  }

  public void setFilterLiveRoom(String paramString)
  {
    this.editor.putString("KEY_FILTER_LIVEROOM", paramString);
    this.editor.commit();
  }

  public void setFilterShowed(String paramString)
  {
    if (paramString == null)
      return;
    this.editor.putBoolean(paramString, true);
    this.editor.commit();
  }

  public void setFirst2PlayController()
  {
    this.editor.putBoolean("first2playcontroller", false);
    this.editor.commit();
  }

  public void setFirst2Playview()
  {
    this.editor.putBoolean("first2playview_v2", false);
    this.editor.commit();
  }

  public void setFlowStamp(int paramInt)
  {
    this.editor.putInt("key_flow_day_stamp", paramInt);
    this.editor.commit();
  }

  public void setForceLogin(long paramLong)
  {
    this.editor.putLong("FORCE_LOGIN", paramLong);
    this.editor.commit();
  }

  public void setFuck360(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_FUCK_360", paramBoolean);
    this.editor.commit();
  }

  public void setGroupReminded()
  {
    this.editor.putBoolean("key_remind_group", true);
    this.editor.commit();
  }

  public void setGuideCollectionDone()
  {
    this.editor.putBoolean("guide_collection", true);
    this.editor.commit();
  }

  public void setGuideShowed()
  {
    this.editor.putBoolean("key_showed_guide", true);
    this.editor.commit();
  }

  public void setHTTPUrlVer(String paramString)
  {
    this.editor.putString("KEY_NEED_CLEAR_HTTPURL", paramString);
    this.editor.commit();
  }

  public void setHasDeleteOldCollection(boolean paramBoolean)
  {
    this.editor.putBoolean("key_has_del_old_collection", paramBoolean);
    this.editor.commit();
  }

  public void setHeadSetCmd(String paramString)
  {
    this.editor.putString(getVertion() + "key_headsetplug", paramString);
    this.editor.commit();
  }

  public void setHelpViewStage(int paramInt)
  {
    this.editor.putInt("helpviewstage", paramInt);
    this.editor.commit();
  }

  public void setHomePageIsPlayView(boolean paramBoolean)
  {
    this.editor.putBoolean("HOME_PAGE_IS_PLAY_VIEW", paramBoolean);
    this.editor.commit();
  }

  public void setIREChange(int paramInt)
  {
    this.editor.putInt("IRE_CHANGE", paramInt);
    this.editor.commit();
  }

  public void setIfInRelisten(boolean paramBoolean)
  {
    this.editor.putBoolean("not_relisten_view", paramBoolean);
    this.editor.commit();
  }

  public void setIsOndemand(boolean paramBoolean)
  {
    this.editor.putBoolean("is_ondemand", paramBoolean);
    this.editor.commit();
  }

  public void setJdAdDeleteTime(long paramLong)
  {
    this.editor.putLong("key_jd_ad_delete_time", paramLong);
    this.editor.commit();
  }

  public void setLastDay(int paramInt)
  {
    this.editor.putInt("lastday", paramInt);
    this.editor.commit();
  }

  public void setLastDay_sendMessage(String paramString, int paramInt)
  {
    this.editor.putInt(paramString, paramInt);
    this.editor.commit();
  }

  public void setLastPlayInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    String str = paramInt1 + "_" + paramInt2 + "_" + paramInt3 + "_" + paramInt4;
    this.editor.putString(this.KEY_PLAY_INFO, str);
    this.editor.commit();
  }

  public void setLastPopFlowTime()
  {
    this.editor.putLong("key_last_flow_pop_time", System.currentTimeMillis());
    this.editor.commit();
  }

  public void setLastWoQtAlertTime()
  {
    this.editor.putLong("key_wo_qt_alert_time", System.currentTimeMillis());
    this.editor.commit();
  }

  public void setLatestVersion(String paramString)
  {
    this.editor.putString("key_latest_version", paramString);
    this.editor.commit();
  }

  public void setLocal(String paramString)
  {
    this.editor.putString(getVertion() + "key_localfirst", paramString);
    this.editor.commit();
  }

  public void setLocalAdvertisementPic(String paramString)
  {
    this.editor.putString("KEY_LOCAL_ADVERTISEMENT_PIC", paramString);
    this.editor.commit();
  }

  public void setLocalNotify(String paramString)
  {
    this.editor.putString("KEY_LOCAL_NOTIFY", paramString);
    this.editor.commit();
  }

  public void setLocalRecommendProgramBeginMax(int paramInt)
  {
    this.editor.putInt("KEY_LOCAL_RECOMMEND_BEGIN_MAX", paramInt);
    this.editor.commit();
  }

  public void setLocalRecommendThreshold(int paramInt)
  {
    this.editor.putInt("key_local_recommend_duration", paramInt);
    this.editor.commit();
  }

  public void setLocalRecommndProgramBeginMin(int paramInt)
  {
    this.editor.putInt("KEY_LOCAL_RECOMMEND_BEGIN_MIN", paramInt);
    this.editor.commit();
  }

  public void setLocalRecommndProgramDelayed(int paramInt)
  {
    this.editor.putInt("KEY_LOCAL_RECOMMEND_DELAYED", paramInt);
    this.editor.commit();
  }

  public void setLocalRecommndProgramIgnored(String paramString)
  {
    this.editor.putString("KEY_LOCAL_RECOMMEND_IGNORED", paramString);
    this.editor.commit();
  }

  public void setLocalRingsVersion(String paramString)
  {
    if (paramString == null)
      return;
    this.editor.putString("KEY_LOCALRINGS_VERSION", paramString);
    this.editor.commit();
  }

  public void setLogInClicked()
  {
    this.editor.putBoolean("haslogclicked", true);
    this.editor.commit();
  }

  public void setMobileAlert(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_MOBILE_PLAY_ALERT", paramBoolean);
    this.editor.commit();
  }

  public void setMobileFirstAlert(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_MOBILE_FIRST_ALERT", paramBoolean);
    this.editor.commit();
  }

  public void setMobilePlayOrStop(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_MOBILE_PLAY_OR_STOP", paramBoolean);
    this.editor.commit();
  }

  public void setMutiRate(boolean paramBoolean)
  {
    this.editor.putBoolean("key_mult_rate", paramBoolean);
    this.editor.commit();
  }

  public void setNetAdvertisementPic(String paramString)
  {
    this.editor.putString("KEY_NET_ADVERTISEMENT_PIC", paramString);
    this.editor.commit();
  }

  public void setNetNotification(int paramInt)
  {
    this.editor.putInt("KEY_RECV_NOTIFICATION_NET", paramInt);
    this.editor.commit();
  }

  public void setNewCDN(String paramString)
  {
    this.editor.putString("KEY_NEW_CDN", paramString);
    this.editor.commit();
  }

  public void setNewUser(boolean paramBoolean)
  {
    this.editor.putBoolean("NEW_USER", paramBoolean);
    this.editor.commit();
  }

  public void setNickName(String paramString)
  {
    this.editor.putString("chat_nickname", paramString);
    this.editor.commit();
  }

  public void setNotice(String paramString)
  {
    this.editor.putString(getVertion() + "key_notice", paramString);
    this.editor.commit();
  }

  public void setOfflineDBVersion(String paramString)
  {
    if (paramString == null)
      return;
    this.editor.putString("KEY_OFFLINEDB_VERSION", paramString);
    this.editor.commit();
  }

  public void setOldVirtualDelete()
  {
    this.editor.putBoolean("Key_old_favored_virtual_delete", true);
    this.editor.commit();
  }

  public void setOnlyUsedInWifi(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_USED_IN_WIFI", paramBoolean);
    this.editor.commit();
  }

  public void setPlayer(int paramInt)
  {
    this.editor.putInt(getVertion() + "key_player", paramInt);
    this.editor.commit();
  }

  public void setPlusCLicked()
  {
    this.editor.putBoolean("hasclickedplus", true);
    this.editor.commit();
  }

  public void setQQAccessToken(String paramString)
  {
    this.editor.putString("key_qq_access_token", paramString);
    this.editor.commit();
  }

  public void setQQExpiresTime(String paramString)
  {
    this.editor.putString("key_qq_expire_time", paramString);
    this.editor.commit();
  }

  public void setQQGender(String paramString)
  {
    this.editor.putString("key_qq_gender", paramString);
    this.editor.commit();
  }

  public void setQQOpenId(String paramString)
  {
    this.editor.putString("key_qq_open_id", paramString);
    this.editor.commit();
  }

  public void setQQUserKey(String paramString)
  {
    this.editor.putString("key_qq_user_key", paramString);
    this.editor.commit();
  }

  public void setREcordPRE(boolean paramBoolean)
  {
    this.editor.putBoolean("key_recordpre", paramBoolean);
    this.editor.commit();
  }

  public void setRandSelectIndex(int paramInt)
  {
    this.editor.putInt("KEY_RAND_SELECT_INDEX", paramInt);
    this.editor.commit();
  }

  public void setRecentKeyWords(String paramString)
  {
    this.editor.putString("KEY_RECENT_KEYWORDS", paramString);
    this.editor.commit();
  }

  public void setRecommendShare(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_RECOMMEND_SHARE_FLAG", paramBoolean);
    this.editor.commit();
  }

  public void setRecommendShareLogin(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_RECOMMEND_SHARE_LOGIN", paramBoolean);
    this.editor.commit();
  }

  public void setRecommendShareUpdate(boolean paramBoolean)
  {
    this.editor.putBoolean("KEY_RECOMMEND_SHARE_UPDATE", paramBoolean);
    this.editor.commit();
  }

  public void setRecommendShareUpdateTime(long paramLong)
  {
    this.editor.putLong("KEY_RECOMMEND_SHARE_UPDATE_TIME", paramLong);
    this.editor.commit();
  }

  public void setRecordName(String paramString)
  {
    this.editor.putString("switch_record", paramString);
    this.editor.commit();
  }

  public void setRelistenIndex(int paramInt)
  {
    this.editor.putInt("relisten_index", paramInt);
    this.editor.commit();
  }

  public void setReverseChannelIds(String paramString)
  {
    this.editor.putString("KEY_REVERSE_CHANNEL_IDS", paramString);
    this.editor.commit();
  }

  public void setSaveBattery(boolean paramBoolean)
  {
    this.editor.putBoolean("key_save_battery", paramBoolean);
    this.editor.commit();
  }

  public void setScanCompleteCmd(String paramString)
  {
    this.editor.putString(getVertion() + "key_scancomplete", paramString);
    this.editor.commit();
  }

  public void setScanfm()
  {
    String str = getVertion();
    this.editor.putString(getVertion() + "key_vertionscaned", str);
    this.editor.commit();
  }

  public void setShareToTencent(boolean paramBoolean)
  {
    this.editor.putBoolean("key_share_to_tencent", paramBoolean);
    this.editor.commit();
  }

  public void setShareToWeibo(boolean paramBoolean)
  {
    this.editor.putBoolean("key_share_to_weibo", paramBoolean);
    this.editor.commit();
  }

  public void setShortcutAdded()
  {
    this.editor.putBoolean("shortcut_added", true);
    this.editor.commit();
  }

  public void setShowWebViewUser(boolean paramBoolean)
  {
    this.editor.putBoolean("key_showwebview" + getVertion(), paramBoolean);
    this.editor.commit();
  }

  public void setSingleName(String paramString)
  {
    this.editor.putString("single_name", paramString);
    this.editor.commit();
  }

  public void setSpeakerOn(boolean paramBoolean)
  {
    this.editor.putBoolean("speakerstate", paramBoolean);
    this.editor.commit();
  }

  public void setSplashLanding(String paramString)
  {
    this.editor.putString("KEY_SPLASH_LANDING", paramString);
    this.editor.commit();
  }

  public void setStartSwitch(boolean paramBoolean)
  {
    this.editor.putBoolean(getVertion() + "key_startswitch", paramBoolean);
    this.editor.commit();
  }

  public void setTaoBaoChange(int paramInt)
  {
    this.editor.putInt("TAOBAO_CHANGE", paramInt);
    this.editor.commit();
  }

  public void setTencentAccessToken(String paramString)
  {
    this.editor.putString("key_tencent_accesstoken", paramString);
    this.editor.commit();
  }

  public void setTencentExpires(long paramLong)
  {
    this.editor.putLong("key_tencent_expires", paramLong);
    this.editor.commit();
  }

  public void setTencentGender(String paramString)
  {
    this.editor.putString("KEY_TENCENT_GENDER", paramString);
    this.editor.commit();
  }

  public void setTencentOpenId(String paramString)
  {
    this.editor.putString("key_tencent_openid", paramString);
    this.editor.commit();
  }

  public void setTencentSocialUserKey(String paramString)
  {
    this.editor.putString("KEY_TENCENT_SOCIAL_USER_KEY", paramString);
    this.editor.commit();
  }

  public void setTipsVersion(String paramString)
  {
    if (paramString == null)
      return;
    this.editor.putString("KEY_TIPS_VERSION", paramString);
    this.editor.commit();
  }

  public void setTitleClicked()
  {
    this.editor.putBoolean("playview_title_clicked", true);
    this.editor.commit();
  }

  public void setUpgradeTime(long paramLong)
  {
    this.editor.putLong("key_popup_upgrade_reminder", paramLong);
    this.editor.commit();
  }

  public void setVertion(Context paramContext)
  {
    paramContext = "version" + String.valueOf(getVersionCode(paramContext));
    this.editor.putString("key_vertion", paramContext);
    this.editor.commit();
  }

  public void setWeChatAccessToken(String paramString)
  {
    this.editor.putString("key_wechat_access_token", paramString);
    this.editor.commit();
  }

  public void setWeChatGender(String paramString)
  {
    this.editor.putString("key_wechat_gender", paramString);
    this.editor.commit();
  }

  public void setWeChatOpenId(String paramString)
  {
    this.editor.putString("key_wechat_open_id", paramString);
    this.editor.commit();
  }

  public void setWeChatRefreshToken(String paramString)
  {
    this.editor.putString("key_wechat_refresh_token", paramString);
    this.editor.commit();
  }

  public void setWeChatUserKey(String paramString)
  {
    this.editor.putString("key_wechat_user_key", paramString);
    this.editor.commit();
  }

  public void setWeiboAuth(String paramString1, String paramString2)
  {
    this.editor.putString(paramString1, paramString2);
    this.editor.commit();
  }

  public void setWeiboGender(String paramString)
  {
    this.editor.putString("KEY_WEIBO_GENDER", paramString);
    this.editor.commit();
  }

  public void setWeiboMovedData()
  {
    this.editor.putBoolean("weibo_hasmoveddata", true);
    this.editor.commit();
  }

  public void setWeiboSocialUserKey(String paramString)
  {
    this.editor.putString("KEY_WEIBO_SOCIAL_USER_KEY", paramString);
    this.editor.commit();
  }

  public void setWifiPolicy(int paramInt)
  {
    this.editor.putInt("wifipolicy", paramInt);
    this.editor.commit();
  }

  public void setWoApiToken(String paramString)
  {
    this.editor.putString("key_wo_api_token", paramString);
    this.editor.commit();
  }

  public void setWoBindCallNumber(String paramString)
  {
    this.editor.putString(this.KEY_WO_BIND_CALL_NUMBER, paramString);
    this.editor.commit();
  }

  public void setWoQtAlertDayStamp(int paramInt)
  {
    this.editor.putInt("key_wo_qt_day_stamp", paramInt);
    this.editor.commit();
  }

  public void setWoTriedTime(String paramString)
  {
    this.editor.putString("key_wo_tried_seconds", paramString);
    this.editor.commit();
  }

  public void setWoTryDate(String paramString)
  {
    this.editor.putString("key_wo_try_date", paramString);
    this.editor.commit();
  }

  public void setY(int paramInt)
  {
    this.editor.putInt(getVertion() + "key_yposition", paramInt);
    this.editor.commit();
  }

  public void setYState(boolean paramBoolean)
  {
    this.editor.putBoolean(getVertion() + "key_ystate", paramBoolean);
    this.editor.commit();
  }

  public void setlocalNotice(String paramString)
  {
    this.editor.putString(getVertion() + "key_localnotice", paramString);
    this.editor.commit();
  }

  public void switchDevMode(boolean paramBoolean)
  {
    this.editor.putBoolean("dev_mode", paramBoolean);
    this.editor.commit();
  }

  public void updateAtMeLatestTime(String paramString, long paramLong)
  {
    this.editor.putLong("key_atme_latest_time" + paramString, paramLong);
    this.editor.commit();
  }

  public void updateFlowerChangeTime(String paramString, long paramLong)
  {
    this.editor.putLong("key_flower_latest_update_time" + paramString, paramLong);
    this.editor.commit();
  }

  public void updateFlowerCnt(String paramString, int paramInt)
  {
    this.editor.putInt("key_flower_cnt" + paramString, paramInt);
    this.editor.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.SharedCfg
 * JD-Core Version:    0.6.2
 */