package fm.qingting.qtradio.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import fm.qingting.framework.data.DBHelper;
import fm.qingting.framework.data.IDBHelperDelegate;
import fm.qingting.framework.net.HTTPConnection;
import fm.qingting.framework.net.UrlAttr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DBManager
  implements IDBHelperDelegate
{
  public static final String ACTIVITY = "activity";
  public static final String ALARM = "alarm";
  public static final String ALARMINFOS = "alarmInfos";
  public static final String ALLFAVCATEGORYNODES = "favCategoryNodes";
  public static final String AUTHORITEMS = "authorItems";
  public static final String BILLBOARDNODES = "billboardNodes";
  public static final String CATEGORY = "category";
  public static final String CATEGORYATTRIBUTES = "categoryAttributes";
  public static final String CATEGORYNODES = "categoryNodes";
  public static final String CHANNELNODES = "channelNodesv6";
  public static final String COMMONRECORDS = "commonRecords";
  public static final String CONTENTCATEGORY = "contentcategory";
  public static final String ColNameUrl = "url";
  public static final String ColNameUrlAttr = "urlattr";
  public static final String DOWNLOADINGPROGRAMNODES = "downloadingprogramNodes";
  public static final String FAVOURITECHANNELS = "favouritechannels";
  public static final String FMFREQ = "fmfreq";
  public static final String FREQCHANNELS = "freqChannels";
  public static final String FRONTPAGENODES = "frontpageNodes";
  public static final String GENERICOBJS = "genericObjs";
  public static final String H5BEANDS = "h5beans";
  public static final String IMBLACK = "imBlack";
  public static final String IMCONTACTS = "imContacts";
  public static final String IMDATABASE = "imDatabase";
  public static final String IMUSERINFO = "imUserInfo";
  public static final String MEDIANCENTERS = "mediaCenterDS";
  public static final String NOTIFICATION = "notification";
  public static final String PLAYEDMETA = "playedMeta";
  public static final String PLAYHISTORY = "playhistory";
  public static final String PLAYLIST = "playList";
  public static final String PODCASTERFOLLOW = "podcasterFollow";
  public static final String PODCASTERS = "podcastersInfo";
  public static final String PREDOWNLOADINGPROGRAMNODES = "predownloadingprogramNodes";
  public static final String PROFILE = "profile";
  public static final String PROGRAMNODES = "programNodes";
  public static final String PROGRAMNODESREV = "programNodesRev";
  public static final String PULLLIST = "pullList";
  public static final String PULLMSGCONFIG = "pullMsgConfig";
  public static final String PULLMSGSTATE = "pullMsgState";
  public static final String QTRADIO = "qtradio";
  public static final String RADIONODES = "radioNodes";
  public static final String RECOMMENDCATEGORYNODES = "recCategoryNodes";
  public static final String RESERVE = "reserve";
  public static final String RESERVEPROGRAM = "reserveprogram";
  public static final String Record = "record";
  public static final String SEARCHHISTROY = "searchhistroy";
  public static final String SHARETABLE = "shareTable";
  public static final String SOCIALUSER = "socialuser";
  public static final String SUBCATEGORYNODES = "subcategoryNodes";
  public static final String TableNameUrlAttr = "urlattrs";
  public static final String URLATTR = "url_attr";
  public static final String USERINFOS = "userinfos";
  public static final String WEIBO = "weibo";
  private static DBManager instance;
  private static Map<String, UrlAttr> urlAttrMap = new HashMap();
  public static final boolean useConnectionPool = true;
  private final int MAXPOOLSIZE = 8;
  private DBHelper _alarmInfoHelper;
  private DBHelper _allFavCategoryHelper;
  private DBHelper _billboardHelper;
  private DBHelper _categoryAttributesHelper;
  private DBHelper _categoryNodesHelper;
  private DBHelper _channelNodesHelper;
  private DBHelper _commonRecordsHelper;
  private DBHelper _contentCategoryHelper;
  private Context _context;
  private DBHelper _datacenterHelper;
  private DBHelper _downloadingNodesHelper;
  private DBHelper _favouritechannelsHelper;
  private DBHelper _freqChannelsHelper;
  private DBHelper _frontPageHelper;
  private DBHelper _genericObjHelper;
  private DBHelper _h5BeansHelper;
  private DBHelper _imBlackDataHelper;
  private DBHelper _imContactsDataHelper;
  private DBHelper _imDataHelper;
  private DBHelper _imUserHelper;
  private DBHelper _playedMetaDataHelper;
  private DBHelper _playhistoryHelper;
  private DBHelper _playlistDataHelper;
  private DBHelper _podcasterFollowHelper;
  private DBHelper _podcasterHelper;
  private DBHelper _predownloadingNodesHelper;
  private DBHelper _profileHelp;
  private DBHelper _programNodesHelper;
  private DBHelper _programNodesRevHelper;
  private DBHelper _pullMsgStateHelper;
  private DBHelper _pulllistDataHelper;
  private DBHelper _qtradioHelp;
  private DBHelper _radioHelper;
  private DBHelper _recCategoryHelper;
  private DBHelper _recordHelp;
  private DBHelper _reserveHelp;
  private DBHelper _reserveProgramHelper;
  private DBHelper _shareTableHelper;
  private DBHelper _socialUserHelper;
  private DBHelper _subcategoryNodesHelper;
  private DBHelper _urlAttrHelper;
  private DBHelper _userInfosHelper;
  private DBHelper _weiboHelp;
  private List<Map<String, Object>> accessToken;
  private List<Map<String, Object>> alarm;
  private List<Map<String, Object>> flower;
  private HashMap<String, SQLDbCache> mSqlCaches = new HashMap();
  private List<Map<String, Object>> profile;
  private List<Map<String, Object>> signin;

  private void clearConnectionPool()
  {
    Iterator localIterator = this.mSqlCaches.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      localObject = (SQLDbCache)this.mSqlCaches.get(localObject);
      if (localObject != null)
      {
        localObject = ((SQLDbCache)localObject).getDb();
        if (localObject != null)
          ((SQLiteDatabase)localObject).close();
      }
    }
    this.mSqlCaches.clear();
  }

  private void createAlarmInfoTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("alarmInfo", "VARCHAR(512)");
    localHashMap1.put("alarmInfos", localHashMap2);
    this._alarmInfoHelper = new DBHelper(localHashMap1, null, this._context, "alarmInfos", null, 1, this);
  }

  private void createBillboardTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("BillboardItemNode", "VARCHAR(256)");
    localHashMap2.put("node", "VARCHAR(1000)");
    localHashMap2.put("nodeName", "VARCHAR(32)");
    localHashMap2.put("type", "int");
    localHashMap1.put("billboardNodes", localHashMap2);
    this._billboardHelper = new DBHelper(localHashMap1, null, this._context, "billboardNodes", null, 1, this);
  }

  private void createCategoryAttributesTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("catid", "int");
    localHashMap2.put("attrs", "VARCHAR(1000)");
    localHashMap1.put("categoryAttributes", localHashMap2);
    this._categoryAttributesHelper = new DBHelper(localHashMap1, null, this._context, "categoryAttributes", null, 1, this);
  }

  private void createCategoryNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("categoryNode", "VARCHAR(1000)");
    localHashMap2.put("id", "int");
    localHashMap2.put("parentId", "int");
    localHashMap1.put("categoryNodes", localHashMap2);
    this._categoryNodesHelper = new DBHelper(localHashMap1, null, this._context, "categoryNodes", null, 1, this);
  }

  private void createChannelNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("catid", "int");
    localHashMap2.put("type", "int");
    localHashMap2.put("channelid", "int");
    localHashMap2.put("channelNode", "VARCHAR(1000)");
    localHashMap2.put("key", "VARCHAR(32)");
    localHashMap1.put("channelNodes", localHashMap2);
    this._channelNodesHelper = new DBHelper(localHashMap1, null, this._context, "channelNodesv6", null, 1, this);
  }

  private void createCommonRecordsTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("key", "VARCHAR(64)");
    localHashMap2.put("type", "VARCHAR(16)");
    localHashMap2.put("value", "VARCHAR(64)");
    localHashMap1.put("commonRecords", localHashMap2);
    this._commonRecordsHelper = new DBHelper(localHashMap1, null, this._context, "commonRecords", null, 1, this);
  }

  private void createContentCategoryTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("nodename", "VARCHAR(10)");
    localHashMap2.put("name", "VARCHAR(10)");
    localHashMap2.put("type", "VARCHAR(10)");
    localHashMap2.put("id", "int");
    localHashMap1.put("contentcategory", localHashMap2);
    this._contentCategoryHelper = new DBHelper(localHashMap1, null, this._context, "contentcategory", null, 1, this);
  }

  private void createDataCenterTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("type", "VARCHAR(32)");
    localHashMap2.put("pinginfo", "VARCHAR(512)");
    localHashMap1.put("mediaCenterDS", localHashMap2);
    this._datacenterHelper = new DBHelper(localHashMap1, null, this._context, "mediaCenterDS", null, 1, this);
  }

  private void createDownloadingProgramNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(16)");
    localHashMap2.put("programNode", "VARCHAR(1000)");
    localHashMap1.put("downloadingNodes", localHashMap2);
    this._downloadingNodesHelper = new DBHelper(localHashMap1, null, this._context, "downloadingprogramNodes", null, 1, this);
  }

  private void createFavouriteChannelTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("channelNode", "VARCHAR(1000)");
    localHashMap1.put("favouritechannels", localHashMap2);
    this._favouritechannelsHelper = new DBHelper(localHashMap1, null, this._context, "favouritechannels", null, 1, this);
  }

  private void createFreqChannelTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("city", "VARCHAR(32)");
    localHashMap2.put("channelid", "int");
    localHashMap2.put("channelname", "VARCHAR(64)");
    localHashMap2.put("freq", "VARCHAR(16)");
    localHashMap1.put("freqChannels", localHashMap2);
    this._freqChannelsHelper = new DBHelper(localHashMap1, null, this._context, "freqChannels", null, 1, this);
  }

  private void createFrontPageTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("RecommendItemNode", "VARCHAR(1000)");
    localHashMap2.put("node", "VARCHAR(1000)");
    localHashMap2.put("nodeName", "VARCHAR(32)");
    localHashMap2.put("type", "int");
    localHashMap1.put("frontpageNodes", localHashMap2);
    this._frontPageHelper = new DBHelper(localHashMap1, null, this._context, "frontpageNodes", null, 1, this);
  }

  private void createGenericObjTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("type", "VARCHAR(32)");
    localHashMap2.put("value", "VARCHAR(1000)");
    localHashMap1.put("genericObjs", localHashMap2);
    this._genericObjHelper = new DBHelper(localHashMap1, null, this._context, "genericObjs", null, 1, this);
  }

  private void createH5BeanTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("type", "int");
    localHashMap2.put("bean", "VARCHAR(256)");
    localHashMap1.put("h5beans", localHashMap2);
    this._h5BeansHelper = new DBHelper(localHashMap1, null, this._context, "h5beans", null, 1, this);
  }

  private void createIMContacts()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("type", "VARCHAR(4)");
    localHashMap2.put("info", "VARCHAR(512)");
    localHashMap2.put("key", "VARCHAR(128)");
    localHashMap1.put("imContacts", localHashMap2);
    this._imContactsDataHelper = new DBHelper(localHashMap1, null, this._context, "imContacts", null, 1, this);
  }

  private void createIMDatabase()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("msgSeq", "long");
    localHashMap2.put("msgId", "VARCHAR(100)");
    localHashMap2.put("fromContactId", "VARCHAR(100)");
    localHashMap2.put("toContactId", "VARCHAR(100)");
    localHashMap2.put("contentType", "int");
    localHashMap2.put("content", "VARCHAR(600)");
    localHashMap2.put("timestamp", "long");
    localHashMap2.put("avatar", "VARCHAR(500)");
    localHashMap2.put("name", "VARCHAR(32)");
    localHashMap1.put("userMessage", localHashMap2);
    localHashMap2 = new HashMap();
    localHashMap2.put("msgSeq", "long");
    localHashMap2.put("msgId", "VARCHAR(100)");
    localHashMap2.put("groupId", "VARCHAR(100)");
    localHashMap2.put("fromContactId", "VARCHAR(100)");
    localHashMap2.put("contentType", "int");
    localHashMap2.put("content", "VARCHAR(600)");
    localHashMap2.put("timestamp", "long");
    localHashMap2.put("avatar", "VARCHAR(500)");
    localHashMap2.put("name", "VARCHAR(32)");
    localHashMap1.put("groupMessage", localHashMap2);
    localHashMap2 = new HashMap();
    localHashMap2.put("im_group_message_index", "groupMessage(groupId)");
    this._imDataHelper = new DBHelper(localHashMap1, localHashMap2, this._context, "imDatabase", null, 1, this);
  }

  private void createIMUserInfo()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(64)");
    localHashMap2.put("avatar", "VARCHAR(500)");
    localHashMap2.put("name", "VARCHAR(32)");
    localHashMap2.put("gender", "VARCHAR(8)");
    localHashMap1.put("imUserInfo", localHashMap2);
    this._imUserHelper = new DBHelper(localHashMap1, null, this._context, "imUserInfo", null, 1, this);
  }

  private void createPlayHistoryTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("nodeName", "VARCHAR(32)");
    localHashMap2.put("playNode", "VARCHAR(1000)");
    localHashMap2.put("time", "long");
    localHashMap2.put("catId", "int");
    localHashMap2.put("subCatId", "int");
    localHashMap2.put("channelId", "int");
    localHashMap2.put("channelName", "VARCHAR(64)");
    localHashMap2.put("playPosition", "long");
    localHashMap2.put("channelThumb", "VARCHAR(256)");
    localHashMap1.put("playhistory", localHashMap2);
    this._playhistoryHelper = new DBHelper(localHashMap1, null, this._context, "playhistory", null, 2, this);
  }

  private void createPlayListTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("type", "int");
    localHashMap2.put("node", "VARCHAR(1000)");
    localHashMap1.put("playList", localHashMap2);
    this._playlistDataHelper = new DBHelper(localHashMap1, null, this._context, "playList", null, 1, this);
  }

  private void createPlayedMetaTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(32)");
    localHashMap2.put("playedMetaData", "VARCHAR(512)");
    localHashMap1.put("playedMetaData", localHashMap2);
    this._playedMetaDataHelper = new DBHelper(localHashMap1, null, this._context, "playedMeta", null, 1, this);
  }

  private void createPodcasterFollowTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("podcasterId", "integer");
    localHashMap2.put("followTime", "long");
    localHashMap2.put("userKey", "VARCHAR(32)");
    localHashMap2.put("updateTime", "long");
    localHashMap2.put("data", "VARCHAR(1000)");
    localHashMap1.put("myPodcaster", localHashMap2);
    this._podcasterFollowHelper = new DBHelper(localHashMap1, null, this._context, "podcasterFollow", null, 1, this);
  }

  private void createPodcasterInfoTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("podcasterId", "integer PRIMARY KEY");
    localHashMap2.put("data", "VARCHAR(1000)");
    localHashMap1.put("podcastersInfo", localHashMap2);
    this._podcasterHelper = new DBHelper(localHashMap1, null, this._context, "podcastersInfo", null, 1, this);
  }

  private void createPreDownloadProgramNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(16)");
    localHashMap2.put("programNode", "VARCHAR(1000)");
    localHashMap1.put("predownloadingNodes", localHashMap2);
    this._predownloadingNodesHelper = new DBHelper(localHashMap1, null, this._context, "predownloadingprogramNodes", null, 1, this);
  }

  private void createProgramNodeRevTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("cid", "int");
    localHashMap2.put("pid", "int");
    localHashMap2.put("dw", "int");
    localHashMap2.put("programNode", "VARCHAR(1000)");
    localHashMap1.put("programNodesRev", localHashMap2);
    this._programNodesRevHelper = new DBHelper(localHashMap1, null, this._context, "programNodesRev", null, 1, this);
  }

  private void createProgramNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("cid", "int");
    localHashMap2.put("pid", "int");
    localHashMap2.put("dw", "int");
    localHashMap2.put("programNode", "VARCHAR(1000)");
    localHashMap1.put("programNodes", localHashMap2);
    this._programNodesHelper = new DBHelper(localHashMap1, null, this._context, "programNodes", null, 1, this);
  }

  private void createPullListTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "int");
    localHashMap2.put("type", "int");
    localHashMap2.put("node", "VARCHAR(1000)");
    localHashMap1.put("pullList", localHashMap2);
    this._pulllistDataHelper = new DBHelper(localHashMap1, null, this._context, "pullList", null, 1, this);
  }

  private void createPullMsgStateTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(32)");
    localHashMap2.put("state", "int");
    localHashMap1.put("pullMsgState", localHashMap2);
    this._pullMsgStateHelper = new DBHelper(localHashMap1, null, this._context, "pullMsgState", null, 1, this);
  }

  private void createRadioNodesTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("radioChannelNode", "VARCHAR(256)");
    localHashMap2.put("id", "int");
    localHashMap1.put("radioNodes", localHashMap2);
    this._radioHelper = new DBHelper(localHashMap1, null, this._context, "radioNodes", null, 1, this);
  }

  private void createRecCategoryTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("RecommendItemNode", "VARCHAR(1000)");
    localHashMap2.put("node", "VARCHAR(1000)");
    localHashMap2.put("nodeName", "VARCHAR(32)");
    localHashMap2.put("type", "int");
    localHashMap2.put("catid", "VARCHAR(32)");
    localHashMap1.put("recCategoryNodes", localHashMap2);
    this._recCategoryHelper = new DBHelper(localHashMap1, null, this._context, "recCategoryNodes", null, 1, this);
  }

  private void createReserveProgramTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("time", "long");
    localHashMap2.put("reserveProgram", "VARCHAR(1000)");
    localHashMap2.put("channelId", "VARCHAR(32)");
    localHashMap2.put("programName", "VARCHAR(64)");
    localHashMap2.put("programId", "VARCHAR(32)");
    localHashMap1.put("reserveprogram", localHashMap2);
    this._reserveProgramHelper = new DBHelper(localHashMap1, null, this._context, "reserveprogram", null, 1, this);
  }

  private void createSocialUserProfileTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("userKey", "VARCHAR(64)");
    localHashMap2.put("userProfile", "VARCHAR(1024)");
    localHashMap1.put("socialUsers", localHashMap2);
    this._socialUserHelper = new DBHelper(localHashMap1, null, this._context, "socialuser", null, 1, this);
  }

  private void createSubCategoryNodeTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("id", "VARCHAR(30)");
    localHashMap2.put("subcategoryNode", "VARCHAR(1000)");
    localHashMap2.put("parentId", "VARCHAR(30)");
    localHashMap1.put("subcategoryNodes", localHashMap2);
    this._subcategoryNodesHelper = new DBHelper(localHashMap1, null, this._context, "subcategoryNodes", null, 1, this);
  }

  private void createUrlAttrTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("url", "VARCHAR(300)");
    localHashMap2.put("urlattr", "VARCHAR(500)");
    localHashMap1.put("urlattrs", localHashMap2);
    this._urlAttrHelper = new DBHelper(localHashMap1, null, this._context, "url_attr", null, 1, this);
  }

  private void createUserInfoTable()
  {
    if (this._context == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("sns_id", "VARCHAR(128)");
    localHashMap2.put("sns_site", "VARCHAR(64)");
    localHashMap2.put("sns_name", "VARCHAR(64)");
    localHashMap2.put("sns_account", "VARCHAR(64)");
    localHashMap2.put("sns_avatar", "VARCHAR(256)");
    localHashMap1.put("userInfos", localHashMap2);
    this._userInfosHelper = new DBHelper(localHashMap1, null, this._context, "userinfos", null, 1, this);
  }

  public static DBManager getInstance()
  {
    try
    {
      if (instance == null)
        instance = new DBManager();
      DBManager localDBManager = instance;
      return localDBManager;
    }
    finally
    {
    }
  }

  private SQLiteDatabase getReadableDbInternal(String paramString)
  {
    if (paramString.equalsIgnoreCase("url_attr"))
      return this._urlAttrHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("favCategoryNodes"))
      return this._allFavCategoryHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("recCategoryNodes"))
      return this._recCategoryHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("qtradio"))
      return this._qtradioHelp.getReadableDatabase();
    if (paramString.equalsIgnoreCase("profile"))
      return this._profileHelp.getReadableDatabase();
    if (paramString.equalsIgnoreCase("alarm"));
    do
    {
      do
      {
        return null;
        if (paramString.equalsIgnoreCase("billboardNodes"))
          return this._billboardHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("shareTable"))
          return this._shareTableHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("pullMsgState"))
          return this._pullMsgStateHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("playedMeta"))
          return this._playedMetaDataHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("playList"))
          return this._playlistDataHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("genericObjs"))
          return this._genericObjHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("pullList"))
          return this._pulllistDataHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("imContacts"))
          return this._imContactsDataHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("imUserInfo"))
          return this._imUserHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("imDatabase"))
          return this._imDataHelper.getReadableDatabase();
        if (paramString.equalsIgnoreCase("imBlack"))
          return this._imBlackDataHelper.getReadableDatabase();
      }
      while ((paramString.equalsIgnoreCase("fmfreq")) || (paramString.equalsIgnoreCase("notification")) || (paramString.equalsIgnoreCase("activity")));
      if (paramString.equalsIgnoreCase("predownloadingprogramNodes"))
        return this._predownloadingNodesHelper.getReadableDatabase();
      if (paramString.equalsIgnoreCase("downloadingprogramNodes"))
        return this._downloadingNodesHelper.getReadableDatabase();
      if (paramString.equalsIgnoreCase("reserve"))
        return this._reserveHelp.getReadableDatabase();
      if (paramString.equalsIgnoreCase("record"))
        return this._recordHelp.getReadableDatabase();
    }
    while (paramString.equalsIgnoreCase("searchhistroy"));
    if (paramString.equalsIgnoreCase("favouritechannels"))
      return this._favouritechannelsHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("playhistory"))
      return this._playhistoryHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("reserveprogram"))
      return this._reserveProgramHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("contentcategory"))
      return this._contentCategoryHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("categoryNodes"))
      return this._categoryNodesHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("categoryAttributes"))
      return this._categoryAttributesHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("subcategoryNodes"))
      return this._subcategoryNodesHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("channelNodesv6"))
      return this._channelNodesHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("frontpageNodes"))
      return this._frontPageHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("radioNodes"))
      return this._radioHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("mediaCenterDS"))
      return this._datacenterHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("freqChannels"))
      return this._freqChannelsHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("alarmInfos"))
      return this._alarmInfoHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("userinfos"))
      return this._userInfosHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("podcastersInfo"))
      return this._podcasterHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("podcasterFollow"))
      return this._podcasterFollowHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("commonRecords"))
      return this._commonRecordsHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("socialuser"))
      return this._socialUserHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("programNodes"))
      return this._programNodesHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("programNodesRev"))
      return this._programNodesRevHelper.getReadableDatabase();
    if (paramString.equalsIgnoreCase("h5beans"))
      return this._h5BeansHelper.getReadableDatabase();
    return this._weiboHelp.getReadableDatabase();
  }

  private SQLiteDatabase getWritableDbInternal(String paramString)
  {
    if (paramString.equalsIgnoreCase("url_attr"))
      return this._urlAttrHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("favCategoryNodes"))
      return this._allFavCategoryHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("recCategoryNodes"))
      return this._recCategoryHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("qtradio"))
      return this._qtradioHelp.getWritableDatabase();
    if (paramString.equalsIgnoreCase("profile"))
      return this._profileHelp.getWritableDatabase();
    if (paramString.equalsIgnoreCase("shareTable"))
      return this._shareTableHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("alarm"));
    do
    {
      do
      {
        return null;
        if (paramString.equalsIgnoreCase("pullMsgState"))
          return this._pullMsgStateHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("playedMeta"))
          return this._playedMetaDataHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("billboardNodes"))
          return this._billboardHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("imBlack"))
          return this._imBlackDataHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("playList"))
          return this._playlistDataHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("genericObjs"))
          return this._genericObjHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("pullList"))
          return this._pulllistDataHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("imContacts"))
          return this._imContactsDataHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("imUserInfo"))
          return this._imUserHelper.getWritableDatabase();
        if (paramString.equalsIgnoreCase("imDatabase"))
          return this._imDataHelper.getWritableDatabase();
      }
      while ((paramString.equalsIgnoreCase("fmfreq")) || (paramString.equalsIgnoreCase("notification")) || (paramString.equalsIgnoreCase("activity")));
      if (paramString.equalsIgnoreCase("downloadingprogramNodes"))
        return this._downloadingNodesHelper.getWritableDatabase();
      if (paramString.equalsIgnoreCase("predownloadingprogramNodes"))
        return this._predownloadingNodesHelper.getWritableDatabase();
      if (paramString.equalsIgnoreCase("reserve"))
        return this._reserveHelp.getWritableDatabase();
      if (paramString.equalsIgnoreCase("record"))
        return this._recordHelp.getWritableDatabase();
    }
    while (paramString.equalsIgnoreCase("searchhistroy"));
    if (paramString.equalsIgnoreCase("favouritechannels"))
      return this._favouritechannelsHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("playhistory"))
      return this._playhistoryHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("reserveprogram"))
      return this._reserveProgramHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("contentcategory"))
      return this._contentCategoryHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("categoryNodes"))
      return this._categoryNodesHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("categoryAttributes"))
      return this._categoryAttributesHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("subcategoryNodes"))
      return this._subcategoryNodesHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("channelNodesv6"))
      return this._channelNodesHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("frontpageNodes"))
      return this._frontPageHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("radioNodes"))
      return this._radioHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("mediaCenterDS"))
      return this._datacenterHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("freqChannels"))
      return this._freqChannelsHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("alarmInfos"))
      return this._alarmInfoHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("userinfos"))
      return this._userInfosHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("podcastersInfo"))
      return this._podcasterHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("podcasterFollow"))
      return this._podcasterFollowHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("commonRecords"))
      return this._commonRecordsHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("socialuser"))
      return this._socialUserHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("programNodes"))
      return this._programNodesHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("programNodesRev"))
      return this._programNodesRevHelper.getWritableDatabase();
    if (paramString.equalsIgnoreCase("h5beans"))
      return this._h5BeansHelper.getWritableDatabase();
    return this._weiboHelp.getWritableDatabase();
  }

  private void moveData(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
  }

  private void movePlayhistory(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL("alter table playhistory add column channelThumb");
      return;
    }
    catch (Exception paramSQLiteDatabase)
    {
    }
  }

  private void removeIdealConnection()
  {
    if (this.mSqlCaches.size() < 8);
    Object localObject1;
    label110: 
    do
    {
      return;
      long l1 = -9223372036854775808L;
      localObject1 = null;
      long l3 = System.currentTimeMillis();
      Iterator localIterator = this.mSqlCaches.keySet().iterator();
      if (localIterator.hasNext())
      {
        localObject2 = (String)localIterator.next();
        SQLDbCache localSQLDbCache = (SQLDbCache)this.mSqlCaches.get(localObject2);
        if (localSQLDbCache != null)
        {
          long l2 = localSQLDbCache.getTimeInterval(l3);
          if (l1 >= l2)
            break label110;
          l1 = l2;
          localObject1 = localObject2;
        }
        while (true)
        {
          break;
          this.mSqlCaches.remove(localObject2);
        }
      }
    }
    while (localObject1 == null);
    Object localObject2 = (SQLDbCache)this.mSqlCaches.get(localObject1);
    if (localObject2 != null)
    {
      localObject2 = ((SQLDbCache)localObject2).getDb();
      if (localObject2 != null)
        ((SQLiteDatabase)localObject2).close();
    }
    this.mSqlCaches.remove(localObject1);
  }

  private void storeUrlAttrToDB()
  {
    SQLiteDatabase localSQLiteDatabase = getInstance().getWritableDB("url_attr");
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from urlattrs");
      Gson localGson = new Gson();
      Iterator localIterator = urlAttrMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localSQLiteDatabase.execSQL("insert into urlattrs(url, urlattr) values(?, ?)", new Object[] { str, localGson.toJson((UrlAttr)urlAttrMap.get(str)) });
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    localException.setTransactionSuccessful();
    localException.endTransaction();
  }

  public void forceClearUrlAttr()
  {
    SQLiteDatabase localSQLiteDatabase = getInstance().getWritableDB("url_attr");
    try
    {
      localSQLiteDatabase.execSQL("delete from urlattrs");
      urlAttrMap.clear();
      HTTPConnection.setUrlAttrMap(urlAttrMap);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public SQLiteDatabase getReadableDB(String paramString)
  {
    Object localObject = (SQLDbCache)this.mSqlCaches.get(paramString);
    if (localObject != null)
    {
      if (!((SQLDbCache)localObject).isWritable())
      {
        ((SQLDbCache)localObject).setTime(System.currentTimeMillis());
        if (((SQLDbCache)localObject).getDb() != null)
          return ((SQLDbCache)localObject).getDb();
        localObject = getReadableDbInternal(paramString);
        this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, false, System.currentTimeMillis()));
        removeIdealConnection();
        return localObject;
      }
      this.mSqlCaches.remove(paramString);
      localObject = getReadableDbInternal(paramString);
      this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, false, System.currentTimeMillis()));
      removeIdealConnection();
      return localObject;
    }
    localObject = getReadableDbInternal(paramString);
    this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, false, System.currentTimeMillis()));
    removeIdealConnection();
    return localObject;
  }

  public SQLiteDatabase getWritableDB(String paramString)
  {
    Object localObject = (SQLDbCache)this.mSqlCaches.get(paramString);
    if (localObject != null)
    {
      if (((SQLDbCache)localObject).isWritable())
      {
        ((SQLDbCache)localObject).setTime(System.currentTimeMillis());
        if (((SQLDbCache)localObject).getDb() != null)
          return ((SQLDbCache)localObject).getDb();
        localObject = getWritableDbInternal(paramString);
        this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, true, System.currentTimeMillis()));
        removeIdealConnection();
        return localObject;
      }
      this.mSqlCaches.remove(paramString);
      localObject = getWritableDbInternal(paramString);
      this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, true, System.currentTimeMillis()));
      removeIdealConnection();
      return localObject;
    }
    localObject = getWritableDbInternal(paramString);
    this.mSqlCaches.put(paramString, new SQLDbCache((SQLiteDatabase)localObject, true, System.currentTimeMillis()));
    removeIdealConnection();
    return localObject;
  }

  public void init(Context paramContext)
  {
    if (this._context != null)
      return;
    this._context = paramContext;
    createFavouriteChannelTable();
    createPlayHistoryTable();
    createReserveProgramTable();
    createContentCategoryTable();
    createCategoryNodeTable();
    createCategoryAttributesTable();
    createSubCategoryNodeTable();
    createChannelNodeTable();
    createDataCenterTable();
    createFrontPageTable();
    createRadioNodesTable();
    createFreqChannelTable();
    createAlarmInfoTable();
    createUserInfoTable();
    createCommonRecordsTable();
    createH5BeanTable();
    createUrlAttrTable();
    createSocialUserProfileTable();
    createProgramNodeTable();
    createProgramNodeRevTable();
    createPullMsgStateTable();
    createPullListTable();
    createPlayedMetaTable();
    createPlayListTable();
    createBillboardTable();
    createDownloadingProgramNodeTable();
    createPreDownloadProgramNodeTable();
    createRecCategoryTable();
    createGenericObjTable();
    createIMDatabase();
    createIMContacts();
    createIMUserInfo();
    createPodcasterInfoTable();
    createPodcasterFollowTable();
    paramContext = new HashMap();
    HashMap localHashMap = new HashMap();
    localHashMap.put("key", "VARCHAR(50)");
    localHashMap.put("value", "VARCHAR(500)");
    paramContext.put("profile", localHashMap);
    this._profileHelp = new DBHelper(paramContext, null, this._context, "profile", null, 1, this);
    paramContext = new HashMap();
    localHashMap = new HashMap();
    localHashMap.put("token", "VARCHAR(50)");
    localHashMap.put("expires", "double");
    localHashMap.put("openid", "VARCHAR(50)");
    localHashMap.put("type", "VARCHAR(20)");
    paramContext.put("accessToken", localHashMap);
    this._weiboHelp = new DBHelper(paramContext, null, this._context, "weibo", null, 2, this);
  }

  public void loadUrlAttrfromDB()
  {
    Object localObject = getInstance().getReadableDB("url_attr");
    try
    {
      localObject = ((SQLiteDatabase)localObject).rawQuery("select * from urlattrs", null);
      Gson localGson = new Gson();
      while (((Cursor)localObject).moveToNext())
      {
        String str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("url"));
        UrlAttr localUrlAttr = (UrlAttr)localGson.fromJson(((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("urlattr")), UrlAttr.class);
        urlAttrMap.put(str, localUrlAttr);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    localException.close();
    HTTPConnection.setUrlAttrMap(urlAttrMap);
  }

  public boolean onCreate(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    return false;
  }

  public void onCreateComplete(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
  }

  public boolean onUpgrade(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
    if ((paramString != null) && (paramString.equalsIgnoreCase("playhistory")) && (paramInt1 == 1) && (paramInt2 == 2))
    {
      movePlayhistory(paramSQLiteDatabase);
      return true;
    }
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if (((paramInt1 <= 3) || (paramInt1 >= 9) || (!paramString.equalsIgnoreCase("qtradio"))) || (((paramInt1 > 1) && (paramInt1 < 9) && (paramString.equalsIgnoreCase("qtradio"))) || (paramString.equalsIgnoreCase("profile"))))
    {
      localObject1 = paramSQLiteDatabase.rawQuery("select key, value from profile", null);
      while (((Cursor)localObject1).moveToNext())
      {
        localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("key"));
        localObject3 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("value"));
        if (this.profile == null)
          this.profile = new ArrayList();
        HashMap localHashMap = new HashMap();
        localHashMap.put("key", localObject2);
        localHashMap.put("value", localObject3);
        this.profile.add(localHashMap);
      }
      ((Cursor)localObject1).close();
      if (!paramString.equalsIgnoreCase("profile"))
        moveData(getWritableDB("profile"), "profile");
    }
    int i;
    if (((paramInt1 > 4) && (paramInt1 < 9) && (paramString.equalsIgnoreCase("qtradio"))) || (paramString.equalsIgnoreCase("alarm")))
    {
      localObject1 = paramSQLiteDatabase.rawQuery("select available, cycle, time, type, program from alarm", null);
      while (((Cursor)localObject1).moveToNext())
      {
        paramInt2 = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("available"));
        i = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("cycle"));
        int j = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("time"));
        int k = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("type"));
        localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("program"));
        if (this.alarm == null)
          this.alarm = new ArrayList();
        localObject3 = new HashMap();
        ((Map)localObject3).put("available", Integer.valueOf(paramInt2));
        ((Map)localObject3).put("cycle", Integer.valueOf(i));
        ((Map)localObject3).put("time", Integer.valueOf(j));
        ((Map)localObject3).put("type", Integer.valueOf(k));
        ((Map)localObject3).put("program", localObject2);
        this.alarm.add(localObject3);
      }
      ((Cursor)localObject1).close();
      if (!paramString.equalsIgnoreCase("alarm"))
        moveData(getWritableDB("alarm"), "alarm");
    }
    if (((paramInt1 == 8) && (paramString.equalsIgnoreCase("qtradio"))) || (paramString.equalsIgnoreCase("weibo")))
      if ((paramInt1 != 8) || (!paramString.equalsIgnoreCase("qtradio")))
        break label1212;
    label1212: for (paramInt2 = 1; ; paramInt2 = 0)
    {
      if ((paramInt1 == 1) && (paramString.equalsIgnoreCase("weibo")));
      for (paramInt1 = 1; ; paramInt1 = 0)
      {
        if (paramInt1 != 0);
        long l;
        for (localObject1 = "select token, expires from accessToken"; ; localObject1 = "select token, expires, openid, type from accessToken")
        {
          localObject1 = paramSQLiteDatabase.rawQuery((String)localObject1, null);
          while (((Cursor)localObject1).moveToNext())
          {
            localObject3 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("token"));
            l = ((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndex("expires"));
            if (this.accessToken == null)
              this.accessToken = new ArrayList();
            localObject2 = new HashMap();
            ((Map)localObject2).put("token", localObject3);
            ((Map)localObject2).put("expires", Long.valueOf(l));
            if (paramInt1 == 0)
            {
              localObject3 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("type"));
              ((Map)localObject2).put("openid", ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("openid")));
              ((Map)localObject2).put("type", localObject3);
            }
            this.accessToken.add(localObject2);
          }
        }
        ((Cursor)localObject1).close();
        localObject1 = paramSQLiteDatabase.rawQuery("select id, lasttime from signin", null);
        if (((Cursor)localObject1).moveToNext())
        {
          localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("id"));
          if (paramInt2 != 0);
          for (l = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("lasttime")); ; l = ()((Cursor)localObject1).getDouble(((Cursor)localObject1).getColumnIndex("lasttime")))
          {
            if (this.signin == null)
              this.signin = new ArrayList();
            localObject3 = new HashMap();
            ((Map)localObject3).put("id", localObject2);
            ((Map)localObject3).put("lasttime", Long.valueOf(l));
            this.signin.add(localObject3);
            break;
          }
        }
        ((Cursor)localObject1).close();
        paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("select id, lasttime, diggcount, total from flowers", null);
        if (paramSQLiteDatabase.moveToNext())
        {
          localObject1 = paramSQLiteDatabase.getString(paramSQLiteDatabase.getColumnIndex("id"));
          if (paramInt2 != 0);
          for (l = paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("lasttime")); ; l = ()paramSQLiteDatabase.getDouble(paramSQLiteDatabase.getColumnIndex("lasttime")))
          {
            paramInt1 = paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("diggcount"));
            i = paramSQLiteDatabase.getInt(paramSQLiteDatabase.getColumnIndex("total"));
            if (this.flower == null)
              this.flower = new ArrayList();
            localObject2 = new HashMap();
            ((Map)localObject2).put("id", localObject1);
            ((Map)localObject2).put("lasttime", Long.valueOf(l));
            ((Map)localObject2).put("diggcount", Integer.valueOf(paramInt1));
            ((Map)localObject2).put("total", Integer.valueOf(i));
            this.flower.add(localObject2);
            break;
          }
        }
        paramSQLiteDatabase.close();
        if (!paramString.equalsIgnoreCase("weibo"))
          moveData(getWritableDB("weibo"), "weibo");
        return false;
      }
    }
  }

  public void onUpgradeComplete(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt1, int paramInt2)
  {
  }

  public void quit()
  {
    storeUrlAttrToDB();
    clearConnectionPool();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.DBManager
 * JD-Core Version:    0.6.2
 */