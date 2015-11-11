package fm.qingting.qtradio.model;

import android.content.Context;
import android.os.Environment;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GlobalCfg
{
  public static final String KEY_COLLECTION_REMIND_TIME = "CollectionRemindTime";
  private static GlobalCfg instance;
  private String KEY_ACTIVE_USER_KEY = "KEY_ACTIVE_USER_KEY";
  private final String KEY_ACTIVITY_START = "KEY_ACTIVITY_START";
  private final String KEY_ALARM_ABSOLUTE_ALARMTIME = "KEY_ALARM_ABSOLUTE_ALARMTIME";
  private final String KEY_ALARM_ALARMTIME = "KEY_ALARM_ALARMTIME";
  private final String KEY_ALARM_CATEGORYID = "KEY_ALARM_CATEGORYID";
  private final String KEY_ALARM_CHANNELID = "KEY_ALARM_CHANNELID";
  private final String KEY_ALARM_CHANNELNAME = "KEY_ALARM_CHANNELNAME";
  private final String KEY_ALARM_DAYOFWEEK = "KEY_ALARM_DAYOFWEEK";
  private final String KEY_ALARM_PARENTID = "KEY_ALARM_PARENTID";
  private final String KEY_ALARM_PROGRAMID = "KEY_ALARM_PROGRAMID";
  private final String KEY_ALARM_RINGTONEID = "KEY_ALARM_RINGTONEID";
  private final String KEY_ALARM_SHOUT = "KEY_ALARM_SHOUT";
  private final String KEY_ALARM_TYPE = "KEY_ALARM_TYPE";
  private final String KEY_APP_FIRST_START = "KEY_APP_FIRST_START";
  private String KEY_CHANNEL_UPDATE = "KEY_CHANNEL_UPDATE";
  private String KEY_CONTINUE_LISTEN = "KEY_CONTINUE_LISTEN";
  private String KEY_DISBALE_GROUPS = "KEY_DISBALE_GROUPS";
  private final String KEY_DOWNLOAD_PATH = "key_download_path";
  private String KEY_DO_IRE = "KEY_DO_IRE";
  private String KEY_DO_PROMETHEUS_TIME = "KEY_DO_PROMETHEUS_TIME";
  private String KEY_DO_SHIELD_SELECT_TIME = "KEY_DO_SHIELD_SELECT_TIME";
  private String KEY_DO_SHIELD_TIME = "KEY_DO_SHIELD_TIME";
  private String KEY_ENABLE_GROUPS = "KEY_ENABLE_GROUPS";
  private final String KEY_ENABLE_PULL = "KEY_ENABLE_PULL";
  private final String KEY_FLOAT_WINDOW = "KEY_FLOAT_WINDOW";
  private final String KEY_GETUI_CLIENT_ID = "KEY_GETUI_CLIENT_ID";
  private String KEY_IM_MSG_SEQ = "KEY_IM_MSG_SEQ";
  private String KEY_IM_MSG_UNREAD = "KEY_IM_MSG_UNREAD";
  private String KEY_IM_MSG_UNREAD_ID = "KEY_IM_MSG_UNREAD_ID";
  private final String KEY_INTEREST_CATEGORY_ID = "KEY_INTEREST_CATEGORY_ID";
  private final String KEY_INTEREST_CHANNEL_ID = "KEY_INTEREST_CHANNEL_ID";
  private final String KEY_INTEREST_DAY_OF_YEAR = "KEY_INTEREST_DAY_OF_YEAR";
  private final String KEY_INTEREST_NOTIFY = "KEY_INTEREST_NOTIFY";
  private final String KEY_INTEREST_PROGRAM_BEGIN = "KEY_INTEREST_PROGRAM_BEGIN";
  private final String KEY_INTEREST_PROGRAM_DURATION = "KEY_INTEREST_PROGRAM_DURATION";
  private final String KEY_INTEREST_PROGRAM_NAME = "KEY_INTEREST_PROGRAM_NAME";
  private final String KEY_INTEREST_SHOUTED = "KEY_INTEREST_SHOUTED";
  private final String KEY_IRESEARCH = "KEY_IRESEARCH";
  private final String KEY_IS_APOLLO = "KEY_IS_APOLLO";
  private final String KEY_LOCAL_CITY = "KEY_LOCAL_CITY";
  private final String KEY_LOCAL_IP = "KEY_LOCAL_IP";
  private final String KEY_LOCAL_NOTIFY = "KEY_LOCAL_NOTIFY";
  private final String KEY_LOCAL_RECOMMEND = "KEY_LOCAL_RECOMMEND";
  private final String KEY_LOCAL_RECOMMEND_TOPIC = "KEY_LOCAL_RECOMMEND_TOPIC";
  private final String KEY_LOCAL_REGION = "KEY_LOCAL_REGION";
  private final String KEY_LOCATION_REGION = "KEY_LOCATION_REGION";
  private String KEY_LOGIN_USERS = "KEY_LOGIN_USERS";
  private final String KEY_OPENQT_DAY_OF_YEAR = "KEY_OPENQT_DAY_OF_YEAR";
  private final String KEY_PLAYEDMETADATA_DURATION = "KEY_PLAYEDMETADATA_DURATION";
  private final String KEY_PLAYEDMETADATA_ID = "KEY_PLAYEDMETADATA_ID";
  private final String KEY_PLAYEDMETADATA_POS = "KEY_PLAYEDMETADATA_POS";
  private final String KEY_PLAY_CONTROLL = "KEY_PLAY_CONTROLL";
  private final String KEY_QUIT_TIME = "KEY_QUIT_TIME";
  private final String KEY_RECV_NET_NOTIFICATION = "KEY_RECV_NET_NOTIFICATION";
  private final String KEY_RECV_PULL_MESSAGE = "KEY_RECV_PULL_MESSAGE";
  private String KEY_RECV_PUSH_MSG_TASK = "KEY_RECV_PUSH_MSG_TASK";
  private final String KEY_RECV_REPLY_DY = "KEY_RECV_REPLY_DAYOFYEAR";
  private final String KEY_RECV_REPLY_MSG = "KEY_RECV_REPLY_MSG";
  private String KEY_SELL_APPS = "KEY_SELL_APPS";
  private String KEY_SELL_APPS_INSTALL = "KEY_SELL_APPS_INSTALL";
  private String KEY_SELL_APPS_PACKAGE = "KEY_SELL_APPS_PACKAGE";
  private String KEY_SELL_APPS_START = "KEY_SELL_APPS_START";
  private final String KEY_SENDAPPS_INFO = "KEY_SENDAPPS_INFO";
  private final String KEY_SERVICE_KILL = "KEY_SERVICE_KILL";
  private String KEY_SHIELD_SELECT_USER = "KEY_SHIELD_SELECT_USER";
  private final String kEY_FLOAT_STATE = "KEY_FLOAT_STATE";
  private Map<String, String> mMapDBCache = new HashMap();
  private boolean mUseCache = false;

  public GlobalCfg(Context paramContext)
  {
    if (paramContext == null);
  }

  public static GlobalCfg getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new GlobalCfg(paramContext);
    return instance;
  }

  private boolean useCache()
  {
    return this.mUseCache;
  }

  public void changeDownloadPath(String paramString)
  {
    setValueToDB("key_download_path", "string", paramString);
  }

  public void clearCache()
  {
    this.mMapDBCache.clear();
  }

  public boolean enableIRE()
  {
    String str = getValueFromDB("KEY_IRESEARCH");
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Boolean.valueOf(str).booleanValue();
    return false;
  }

  public String getAccounts()
  {
    return getValueFromDB(this.KEY_LOGIN_USERS);
  }

  public String getActiveUserKey()
  {
    return getValueFromDB(this.KEY_ACTIVE_USER_KEY);
  }

  public long getActivityStartTime()
  {
    String str = getValueFromDB("KEY_ACTIVITY_START");
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public long getAlarmAbsoluteTime()
  {
    String str = getValueFromDB("KEY_ALARM_ABSOLUTE_ALARMTIME");
    if (str != null)
      return Long.valueOf(str).longValue();
    return 9223372036854775807L;
  }

  public String getAlarmCategoryId()
  {
    return getValueFromDB("KEY_ALARM_CATEGORYID");
  }

  public String getAlarmChannelId()
  {
    return getValueFromDB("KEY_ALARM_CHANNELID");
  }

  public String getAlarmChannelName()
  {
    return getValueFromDB("KEY_ALARM_CHANNELNAME");
  }

  public int getAlarmDayOfWeek()
  {
    String str = getValueFromDB("KEY_ALARM_DAYOFWEEK");
    if ((str != null) && (!str.equalsIgnoreCase("")) && (!str.equalsIgnoreCase("null")))
      return Integer.valueOf(str).intValue();
    return 0;
  }

  public String getAlarmParentId()
  {
    return getValueFromDB("KEY_ALARM_PARENTID");
  }

  public String getAlarmProgramId()
  {
    return getValueFromDB("KEY_ALARM_PROGRAMID");
  }

  public String getAlarmRingToneId()
  {
    return getValueFromDB("KEY_ALARM_RINGTONEID");
  }

  public boolean getAlarmShouted()
  {
    String str = getValueFromDB("KEY_ALARM_SHOUT");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public long getAlarmTime()
  {
    String str = getValueFromDB("KEY_ALARM_ALARMTIME");
    if (str != null)
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public String getAlarmType()
  {
    return getValueFromDB("KEY_ALARM_TYPE");
  }

  public boolean getAliasPush()
  {
    String str = getValueFromDB("ALIAS_PUSH_SWITCH");
    if ((str == null) || (str.equalsIgnoreCase("")))
      return true;
    return Boolean.valueOf(str).booleanValue();
  }

  public long getAppFirstStartTime()
  {
    String str = getValueFromDB("KEY_APP_FIRST_START");
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public boolean getAutoReserve()
  {
    String str = getValueFromDB("USE_AUTO_RESERVE");
    if ((str == null) || (str.equalsIgnoreCase("")))
      return true;
    return Boolean.valueOf(str).booleanValue();
  }

  public long getChannelUpdateTime()
  {
    String str = getValueFromDB(this.KEY_CHANNEL_UPDATE);
    if (str != null)
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public long getContinueListenTime()
  {
    String str = getValueFromDB(this.KEY_CONTINUE_LISTEN);
    if (str != null)
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public String getDisableGroups()
  {
    return getValueFromDB(this.KEY_DISBALE_GROUPS);
  }

  public int getDoIRE()
  {
    String str = getValueFromDB(this.KEY_DO_IRE);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public long getDoPrometheusTime()
  {
    String str = getValueFromDB(this.KEY_DO_PROMETHEUS_TIME);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public long getDoShieldSelectTime()
  {
    String str = getValueFromDB(this.KEY_DO_SHIELD_SELECT_TIME);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public long getDoShieldTime()
  {
    String str = getValueFromDB(this.KEY_DO_SHIELD_TIME);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public String getDownloadPath()
  {
    String str = getValueFromDB("key_download_path");
    Object localObject;
    if (str != null)
    {
      localObject = str;
      if (!str.equalsIgnoreCase(""));
    }
    else
    {
      localObject = Environment.getExternalStorageDirectory();
      localObject = ((File)localObject).getPath() + File.separator + "QTDownloadRadio";
      changeDownloadPath((String)localObject);
    }
    return localObject;
  }

  public String getEnableGroups()
  {
    return getValueFromDB(this.KEY_ENABLE_GROUPS);
  }

  public int getEnablePull()
  {
    String str = getValueFromDB("KEY_ENABLE_PULL");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return 0;
  }

  public boolean getFloatState()
  {
    String str = getValueFromDB("KEY_FLOAT_STATE");
    return (str == null) || (!str.equalsIgnoreCase("false"));
  }

  public boolean getFloatWindow()
  {
    String str = getValueFromDB("KEY_FLOAT_WINDOW");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public String getGeTuiClientID()
  {
    return getValueFromDB("KEY_GETUI_CLIENT_ID");
  }

  public boolean getGlobalPush()
  {
    String str = getValueFromDB("GLOBAL_PUSH_SWITCH");
    if ((str == null) || (str.equalsIgnoreCase("")))
      return true;
    return Boolean.valueOf(str).booleanValue();
  }

  public String getInterestCategoryId()
  {
    return getValueFromDB("KEY_INTEREST_CATEGORY_ID");
  }

  public String getInterestChannelId()
  {
    return getValueFromDB("KEY_INTEREST_CHANNEL_ID");
  }

  public int getInterestDayOfYear()
  {
    String str = getValueFromDB("KEY_INTEREST_DAY_OF_YEAR");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public long getInterestNotify()
  {
    String str = getValueFromDB("KEY_INTEREST_NOTIFY");
    if (str != null)
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public int getInterestProgramBegin()
  {
    String str = getValueFromDB("KEY_INTEREST_PROGRAM_BEGIN");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public long getInterestProgramDuration()
  {
    String str = getValueFromDB("KEY_INTEREST_PROGRAM_DURATION");
    if (str != null)
      return Long.valueOf(str).longValue();
    return -1L;
  }

  public String getInterestProgramName()
  {
    return getValueFromDB("KEY_INTEREST_PROGRAM_NAME");
  }

  public boolean getInterestShouted()
  {
    String str = getValueFromDB("KEY_INTEREST_SHOUTED");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public String getLocalCity()
  {
    return getValueFromDB("KEY_LOCAL_CITY");
  }

  public String getLocalIp()
  {
    return getValueFromDB("KEY_LOCAL_IP");
  }

  public String getLocalNotify()
  {
    return getValueFromDB("KEY_LOCAL_NOTIFY");
  }

  public boolean getLocalRecommend()
  {
    String str = getValueFromDB("KEY_LOCAL_RECOMMEND");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public boolean getLocalRecommendNeedTopic()
  {
    String str = getValueFromDB("KEY_LOCAL_RECOMMEND_TOPIC");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public String getLocalRegion()
  {
    return getValueFromDB("KEY_LOCAL_REGION");
  }

  public String getLocationRegion()
  {
    String str = getValueFromDB("KEY_LOCATION_REGION");
    if (str != null)
      return str;
    return null;
  }

  public boolean getMediaControll()
  {
    String str = getValueFromDB("KEY_PLAY_CONTROLL");
    return (str == null) || (str.equalsIgnoreCase("true"));
  }

  public long getMsgSeq()
  {
    String str = getValueFromDB(this.KEY_IM_MSG_SEQ);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public int getOpenDayOfYear()
  {
    String str = getValueFromDB("KEY_OPENQT_DAY_OF_YEAR");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public int getPlayedMetaProgramDuration()
  {
    String str = getValueFromDB("KEY_PLAYEDMETADATA_DURATION");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public String getPlayedMetaProgramId()
  {
    return getValueFromDB("KEY_PLAYEDMETADATA_ID");
  }

  public int getPlayedMetaProgramPos()
  {
    String str = getValueFromDB("KEY_PLAYEDMETADATA_POS");
    if (str != null)
      return Integer.valueOf(str).intValue();
    return -1;
  }

  public boolean getPullMessage()
  {
    String str = getValueFromDB("KEY_RECV_PULL_MESSAGE");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public long getQuitTime()
  {
    String str = getValueFromDB("KEY_QUIT_TIME");
    if (str != null)
      return Long.valueOf(str).longValue();
    return 9223372036854775807L;
  }

  public boolean getRecvNotification()
  {
    String str = getValueFromDB("KEY_RECV_NET_NOTIFICATION");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public String getRecvPushMsgTask()
  {
    return getValueFromDB(this.KEY_RECV_PUSH_MSG_TASK);
  }

  public String getRecvReply()
  {
    return getValueFromDB("KEY_RECV_REPLY_MSG");
  }

  public String getRecvReplyDayOfYear()
  {
    return getValueFromDB("KEY_RECV_REPLY_DAYOFYEAR");
  }

  public String getSellAppsPackage()
  {
    return getValueFromDB(this.KEY_SELL_APPS_PACKAGE);
  }

  public long getSellAppsStart()
  {
    String str = getValueFromDB(this.KEY_SELL_APPS_START);
    if ((str != null) && (!str.equalsIgnoreCase("")))
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public String getSellUrl()
  {
    return getValueFromDB(this.KEY_SELL_APPS);
  }

  public String getSellUrlInstall()
  {
    return getValueFromDB(this.KEY_SELL_APPS_INSTALL);
  }

  public long getSendAppsTime()
  {
    String str = getValueFromDB("KEY_SENDAPPS_INFO");
    if (str != null)
      return Long.valueOf(str).longValue();
    return 0L;
  }

  public boolean getServiceKill()
  {
    boolean bool2 = false;
    String str = getValueFromDB("KEY_SERVICE_KILL");
    boolean bool1 = bool2;
    if (str != null)
    {
      bool1 = bool2;
      if (str.equalsIgnoreCase("true"))
        bool1 = true;
    }
    return bool1;
  }

  public boolean getShieldSelectUser()
  {
    String str = getValueFromDB(this.KEY_SHIELD_SELECT_USER);
    return (str == null) || (!str.equalsIgnoreCase("false"));
  }

  public String getUnReadCnt()
  {
    return getValueFromDB(this.KEY_IM_MSG_UNREAD);
  }

  public String getUnReadID()
  {
    return getValueFromDB(this.KEY_IM_MSG_UNREAD_ID);
  }

  public boolean getUseXiaomiPush()
  {
    String str = getValueFromDB("USE_XIAOMI_PUSH");
    if ((str == null) || (str.equalsIgnoreCase("")))
      return false;
    return Boolean.valueOf(str).booleanValue();
  }

  public String getValueFromDB(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      if ((useCache()) && (this.mMapDBCache.containsKey(paramString)))
        return (String)this.mMapDBCache.get(paramString);
      localObject = new HashMap();
      ((Map)localObject).put("key", paramString);
      localObject = DataManager.getInstance().getData("getdb_common_record", null, (Map)localObject).getResult();
    }
    while (!((Result)localObject).getSuccess());
    String str = (String)((Result)localObject).getData();
    Object localObject = str;
    if (str != null)
    {
      localObject = str;
      if (str.equalsIgnoreCase("null"))
        localObject = null;
    }
    if (useCache())
      this.mMapDBCache.put(paramString, localObject);
    return localObject;
  }

  public boolean isApollo()
  {
    String str = getValueFromDB("KEY_IS_APOLLO");
    return (str != null) && (str.equalsIgnoreCase("true"));
  }

  public boolean isPushLiveSwitchEnabled()
  {
    String str = getValueFromDB("PUSH_LIVE_SWITCH");
    if ((str == null) || (str.equalsIgnoreCase("")))
      return false;
    return Boolean.valueOf(str).booleanValue();
  }

  public boolean isPushSwitchEnabled()
  {
    String str = getValueFromDB("PUSH_MODULE_SWITCH");
    if (str == null)
      return true;
    return Boolean.valueOf(str).booleanValue();
  }

  public void saveGeTuiClientID(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", "KEY_GETUI_CLIENT_ID");
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put("KEY_GETUI_CLIENT_ID", paramString);
  }

  public void saveValueToDB()
  {
    if (this.mMapDBCache.size() == 0)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("items", this.mMapDBCache);
    DataManager.getInstance().getData("updatedb_common_grouprecord", null, localHashMap);
  }

  public void setActiveUserKey(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_ACTIVE_USER_KEY);
    localHashMap.put("value", paramString);
    localHashMap.put("type", "String");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_ACTIVE_USER_KEY, paramString);
  }

  public void setActivityStartTime(long paramLong)
  {
    setValueToDB("KEY_ACTIVITY_START", "long", String.valueOf(paramLong));
  }

  public void setAlarmAbsoluteTime(long paramLong)
  {
    setValueToDB("KEY_ALARM_ABSOLUTE_ALARMTIME", "long", String.valueOf(paramLong));
  }

  public void setAlarmCategoryId(int paramInt)
  {
    setValueToDB("KEY_ALARM_CATEGORYID", "Integer", String.valueOf(paramInt));
  }

  public void setAlarmChannelId(String paramString)
  {
    setValueToDB("KEY_ALARM_CHANNELID", "String", paramString);
  }

  public void setAlarmChannelName(String paramString)
  {
    setValueToDB("KEY_ALARM_CHANNELNAME", "String", paramString);
  }

  public void setAlarmDayOfWeek(int paramInt)
  {
    setValueToDB("KEY_ALARM_DAYOFWEEK", "int", String.valueOf(paramInt));
  }

  public void setAlarmParentId(String paramString)
  {
    setValueToDB("KEY_ALARM_PARENTID", "String", paramString);
  }

  public void setAlarmProgramId(String paramString)
  {
    setValueToDB("KEY_ALARM_PROGRAMID", "String", paramString);
  }

  public void setAlarmRingToneId(String paramString)
  {
    setValueToDB("KEY_ALARM_RINGTONEID", "String", paramString);
  }

  public void setAlarmShouted(boolean paramBoolean)
  {
    setValueToDB("KEY_ALARM_SHOUT", "boolean", String.valueOf(paramBoolean));
  }

  public void setAlarmTime(long paramLong)
  {
    setValueToDB("KEY_ALARM_ALARMTIME", "long", String.valueOf(paramLong));
  }

  public void setAlarmType(int paramInt)
  {
    setValueToDB("KEY_ALARM_TYPE", "int", String.valueOf(paramInt));
  }

  public void setAliasPush(boolean paramBoolean)
  {
    setValueToDB("ALIAS_PUSH_SWITCH", "boolean", String.valueOf(paramBoolean));
  }

  public void setApollo(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_IS_APOLLO");
    localHashMap.put("value", String.valueOf(paramBoolean));
    localHashMap.put("type", "boolean");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put("KEY_IS_APOLLO", String.valueOf(paramBoolean));
  }

  public void setAppFirstStartTime(long paramLong)
  {
    if (getAppFirstStartTime() == 0L)
      setValueToDB("KEY_APP_FIRST_START", "long", String.valueOf(paramLong));
  }

  public void setAutoReserve(boolean paramBoolean)
  {
    setValueToDB("USE_AUTO_RESERVE", "boolean", String.valueOf(paramBoolean));
  }

  public void setChannelUpdateTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_CHANNEL_UPDATE);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_CHANNEL_UPDATE, String.valueOf(paramLong));
  }

  public void setContinueListenTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_CONTINUE_LISTEN);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_CONTINUE_LISTEN, String.valueOf(paramLong));
  }

  public void setCreateAccountSucc(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_LOGIN_USERS);
      localHashMap.put("value", String.valueOf(paramString));
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_LOGIN_USERS, paramString);
  }

  public void setDisableGroups(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_DISBALE_GROUPS);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_DISBALE_GROUPS, paramString);
  }

  public void setDoIRE(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_DO_IRE);
    localHashMap.put("value", String.valueOf(paramInt));
    localHashMap.put("type", "int");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_DO_IRE, String.valueOf(paramInt));
  }

  public void setDoPrometheusTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_DO_PROMETHEUS_TIME);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_DO_PROMETHEUS_TIME, String.valueOf(paramLong));
  }

  public void setDoShieldSelectTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_DO_SHIELD_SELECT_TIME);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_DO_SHIELD_SELECT_TIME, String.valueOf(paramLong));
  }

  public void setDoShieldTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_DO_SHIELD_TIME);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_DO_SHIELD_TIME, String.valueOf(paramLong));
  }

  public void setEnableGroups(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_ENABLE_GROUPS);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_ENABLE_GROUPS, paramString);
  }

  public void setEnableIRE(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_IRESEARCH");
    localHashMap.put("value", String.valueOf(paramBoolean));
    localHashMap.put("type", "boolean");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put("KEY_IRESEARCH", String.valueOf(paramBoolean));
  }

  public void setEnablePull(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setValueToDB("KEY_ENABLE_PULL", "int", "1");
      return;
    }
    setValueToDB("KEY_ENABLE_PULL", "int", "0");
  }

  public void setFloatState(boolean paramBoolean)
  {
    setValueToDB("KEY_FLOAT_STATE", "String", String.valueOf(paramBoolean));
  }

  public void setFloatWindow(boolean paramBoolean)
  {
    setValueToDB("KEY_FLOAT_WINDOW", "String", String.valueOf(paramBoolean));
  }

  public void setGlobalPush(boolean paramBoolean)
  {
    setValueToDB("GLOBAL_PUSH_SWITCH", "boolean", String.valueOf(paramBoolean));
  }

  public void setInterestCategoryId(String paramString)
  {
    setValueToDB("KEY_INTEREST_CATEGORY_ID", "String", paramString);
  }

  public void setInterestChannelId(String paramString)
  {
    setValueToDB("KEY_INTEREST_CHANNEL_ID", "String", paramString);
  }

  public void setInterestDayOfYear(int paramInt)
  {
    setValueToDB("KEY_INTEREST_DAY_OF_YEAR", "int", String.valueOf(paramInt));
  }

  public void setInterestNotify(long paramLong)
  {
    setValueToDB("KEY_INTEREST_NOTIFY", "long", String.valueOf(paramLong));
  }

  public void setInterestProgramBegin(int paramInt)
  {
    setValueToDB("KEY_INTEREST_PROGRAM_BEGIN", "int", String.valueOf(paramInt));
  }

  public void setInterestProgramDuration(long paramLong)
  {
    setValueToDB("KEY_INTEREST_PROGRAM_DURATION", "long", String.valueOf(paramLong));
  }

  public void setInterestProgramName(String paramString)
  {
    if (paramString == null)
      return;
    setValueToDB("KEY_INTEREST_PROGRAM_NAME", "String", paramString.trim());
  }

  public void setInterestShouted(boolean paramBoolean)
  {
    setValueToDB("KEY_INTEREST_SHOUTED", "boolean", String.valueOf(paramBoolean));
  }

  public void setLocalCity(String paramString)
  {
    if (paramString == null)
      return;
    setValueToDB("KEY_LOCAL_CITY", "String", paramString);
  }

  public void setLocalIp(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", "KEY_LOCAL_IP");
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put("KEY_LOCAL_IP", paramString);
  }

  public void setLocalNotify(String paramString)
  {
    setValueToDB("KEY_LOCAL_NOTIFY", "String", paramString);
  }

  public void setLocalRecommend(boolean paramBoolean)
  {
    setValueToDB("KEY_LOCAL_RECOMMEND", "boolean", String.valueOf(paramBoolean));
  }

  public void setLocalRecommendNeedTopic(boolean paramBoolean)
  {
    setValueToDB("KEY_LOCAL_RECOMMEND_TOPIC", "boolean", String.valueOf(paramBoolean));
  }

  public void setLocalRegion(String paramString)
  {
    if (paramString == null)
      return;
    setValueToDB("KEY_LOCAL_REGION", "String", paramString);
  }

  public void setLocationRegion(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_LOCATION_REGION");
    localHashMap.put("value", paramString);
    localHashMap.put("type", "String");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setMediaControll(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_PLAY_CONTROLL");
    localHashMap.put("value", String.valueOf(paramBoolean));
    localHashMap.put("type", "boolean");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setMsgSeq(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_IM_MSG_SEQ);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_IM_MSG_SEQ, String.valueOf(paramLong));
  }

  public void setOpenDay(int paramInt)
  {
    setValueToDB("KEY_OPENQT_DAY_OF_YEAR", "int", String.valueOf(paramInt));
  }

  public void setPlayedMetaProgramDuration(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_PLAYEDMETADATA_DURATION");
    localHashMap.put("value", String.valueOf(paramInt));
    localHashMap.put("type", "int");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setPlayedMetaProgramId(String paramString)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_PLAYEDMETADATA_ID");
    localHashMap.put("value", paramString);
    localHashMap.put("type", "String");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setPlayedMetaProgramPos(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_PLAYEDMETADATA_POS");
    localHashMap.put("value", String.valueOf(paramInt));
    localHashMap.put("type", "int");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setPullMessage(boolean paramBoolean)
  {
    setValueToDB("KEY_RECV_PULL_MESSAGE", "boolean", String.valueOf(paramBoolean));
  }

  public void setPushLiveSwitch(boolean paramBoolean)
  {
    setValueToDB("PUSH_LIVE_SWITCH", "boolean", String.valueOf(paramBoolean));
  }

  public void setPushSwitch(boolean paramBoolean)
  {
    setValueToDB("PUSH_MODULE_SWITCH", "boolean", String.valueOf(paramBoolean));
  }

  public void setQuitTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_QUIT_TIME");
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setRecvNotification(boolean paramBoolean)
  {
    setValueToDB("KEY_RECV_NET_NOTIFICATION", "boolean", String.valueOf(paramBoolean));
  }

  public void setRecvPushMsgTask(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_RECV_PUSH_MSG_TASK);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_RECV_PUSH_MSG_TASK, paramString);
  }

  public void setRecvReply(String paramString)
  {
    setValueToDB("KEY_RECV_REPLY_MSG", "String", paramString);
  }

  public void setRecvReplyDayOfYear(int paramInt)
  {
    setValueToDB("KEY_RECV_REPLY_DAYOFYEAR", "String", String.valueOf(paramInt));
  }

  public void setSellAppsPackage(String paramString)
  {
    setValueToDB(this.KEY_SELL_APPS_PACKAGE, "String", paramString);
  }

  public void setSellAppsStart(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_SELL_APPS_START);
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_SELL_APPS_START, String.valueOf(paramLong));
  }

  public void setSellUrl(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_SELL_APPS);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_SELL_APPS, paramString);
  }

  public void setSellUrlInstall(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_SELL_APPS_INSTALL);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_SELL_APPS_INSTALL, paramString);
  }

  public void setSendAppsTime(long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_SENDAPPS_INFO");
    localHashMap.put("value", String.valueOf(paramLong));
    localHashMap.put("type", "long");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put("KEY_SENDAPPS_INFO", String.valueOf(paramLong));
  }

  public void setServiceKill(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "KEY_SERVICE_KILL");
    localHashMap.put("value", String.valueOf(paramBoolean));
    localHashMap.put("type", "boolean");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
  }

  public void setShieldSelectUser(boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", this.KEY_SHIELD_SELECT_USER);
    localHashMap.put("value", String.valueOf(paramBoolean));
    localHashMap.put("type", "boolean");
    DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    if (useCache())
      this.mMapDBCache.put(this.KEY_SHIELD_SELECT_USER, String.valueOf(paramBoolean));
  }

  public void setUnReadCnt(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_IM_MSG_UNREAD);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_IM_MSG_UNREAD, paramString);
  }

  public void setUnReadID(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", this.KEY_IM_MSG_UNREAD_ID);
      localHashMap.put("value", paramString);
      localHashMap.put("type", "String");
      DataManager.getInstance().getData("updatedb_common_record", null, localHashMap);
    }
    while (!useCache());
    this.mMapDBCache.put(this.KEY_IM_MSG_UNREAD_ID, paramString);
  }

  public void setUseCache(boolean paramBoolean)
  {
    this.mUseCache = paramBoolean;
  }

  public void setUseXiaomiPush(boolean paramBoolean)
  {
    setValueToDB("USE_XIAOMI_PUSH", "boolean", String.valueOf(paramBoolean));
  }

  public void setValueToDB(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      return;
    String str = paramString3;
    if (paramString3 != null)
    {
      str = paramString3;
      if (paramString3.equalsIgnoreCase("null"))
        str = null;
    }
    if (useCache())
    {
      this.mMapDBCache.put(paramString1, str);
      return;
    }
    paramString3 = new HashMap();
    paramString3.put("key", paramString1);
    paramString3.put("value", str);
    paramString3.put("type", paramString2);
    DataManager.getInstance().getData("updatedb_common_record", null, paramString3);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.GlobalCfg
 * JD-Core Version:    0.6.2
 */