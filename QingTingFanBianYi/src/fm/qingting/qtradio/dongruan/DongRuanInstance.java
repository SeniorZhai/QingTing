package fm.qingting.qtradio.dongruan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.neusoft.ssp.api.QTFM_NEW_RequestListener;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.CityItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.ClassItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.LabelItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.ProgramItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.RadioItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.RecentItem;
import com.neusoft.ssp.api.SSP_NEW_QT_FM_API.RecommendItem;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.NetWorkManage;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.DataLoadWrapper;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.PlayingProgramNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RecommendPlayingInfoNode;
import fm.qingting.qtradio.model.RecommendPlayingItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.qtradio.search.SearchHotKeyword;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import fm.qingting.utils.PlayProcessSyncUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class DongRuanInstance
  implements RootNode.IPlayInfoEventListener, InfoManager.ISubscribeEventListener
{
  private static DongRuanInstance instance;
  public final int AllList = 8;
  public final int CityList = 9;
  public final int CollectList = 1;
  public final int CollectProgramList = 2;
  public final int DimensionList = 10;
  public final int DownloadProgramList = 11;
  public final int DownloadRadioList = 12;
  public final int GetRadioPic = 13;
  public final int KeyWordList = 3;
  public final int LabelList = 21;
  public final int LiveRadioList = 14;
  public final int LivingRadio = 19;
  public final int PlayNextProgram = 4;
  public final int PlayOrPause = 5;
  public final int PlayPreProgram = 6;
  public final int PlayRadio = 15;
  public final int ProgramList = 16;
  public final int QUIT = 0;
  public final int RadioList = 17;
  public final int RecentList = 7;
  public final int ResultList = 18;
  public final int SelectList = 20;
  List<Node> listNodes;
  private InfoManager.ISubscribeEventListener mAllListener;
  private Object mAllUserData;
  private SSP_NEW_QT_FM_API mApi;
  private Attribute mAttribute;
  private CategoryNode mCategoryNode;
  Runnable mChannelRunnable;
  private Context mContext;
  private boolean mDebug = false;
  private Handler mDispatchHandler = new DispatchHandler(Looper.getMainLooper());
  private boolean mHasConnected = false;
  private InfoManager.ISubscribeEventListener mKeyListener;
  private Object mKeyUserData;
  private int mLabelClassId;
  private InfoManager.ISubscribeEventListener mLabelListener;
  private int mLabelSectionId;
  private Object mLabelUserData;
  Runnable mListRunnable;
  private QTFM_NEW_RequestListener mListener = new QTFM_NEW_RequestListener()
  {
    private DongRuanInstance.MaskHandler mMaskHandler = new DongRuanInstance.MaskHandler(DongRuanInstance.this);

    public void notifyAllList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(8, DongRuanInstance.this.mapParam));
    }

    public void notifyCityList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(9, DongRuanInstance.this.mapParam));
    }

    public void notifyCollectList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(1, DongRuanInstance.this.mapParam));
    }

    public void notifyCollectProgram(Object paramAnonymousObject, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("isCollect", Integer.valueOf(paramAnonymousInt1));
      DongRuanInstance.this.mapParam.put("radioId", Integer.valueOf(paramAnonymousInt2));
      DongRuanInstance.this.mapParam.put("type", Integer.valueOf(paramAnonymousInt3));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(2, DongRuanInstance.this.mapParam));
    }

    public void notifyConnectStatus(int paramAnonymousInt)
    {
      this.mMaskHandler.sendEmptyMessage(paramAnonymousInt);
    }

    public void notifyDownLoadProgramList(Object paramAnonymousObject, int paramAnonymousInt)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("radioId", Integer.valueOf(paramAnonymousInt));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(11, DongRuanInstance.this.mapParam));
    }

    public void notifyDownLoadRadioList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(12, DongRuanInstance.this.mapParam));
    }

    public void notifyExitApp(Object paramAnonymousObject)
    {
      DongRuanInstance.this.log("notifyExitApp:");
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(0, DongRuanInstance.this.mapParam));
    }

    public void notifyGetRadioPic(Object paramAnonymousObject, String paramAnonymousString, int paramAnonymousInt)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("thumb", paramAnonymousString);
      DongRuanInstance.this.mapParam.put("radioId", Integer.valueOf(paramAnonymousInt));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(13, DongRuanInstance.this.mapParam));
    }

    public void notifyKeyWordList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(3, DongRuanInstance.this.mapParam));
    }

    public void notifyLabelList(Object paramAnonymousObject, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("classId", Integer.valueOf(paramAnonymousInt1));
      DongRuanInstance.this.mapParam.put("sectionId", Integer.valueOf(paramAnonymousInt2));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(21, DongRuanInstance.this.mapParam));
    }

    public void notifyLiveRadioList(Object paramAnonymousObject, int paramAnonymousInt, String paramAnonymousString)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("classId", Integer.valueOf(paramAnonymousInt));
      DongRuanInstance.this.mapParam.put("attrId", paramAnonymousString);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(14, DongRuanInstance.this.mapParam));
    }

    public void notifyLivingRadio(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(19, DongRuanInstance.this.mapParam));
    }

    public void notifyNextProgram(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(4, DongRuanInstance.this.mapParam));
    }

    public void notifyPlayOrPause(Object paramAnonymousObject, int paramAnonymousInt)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("action", Integer.valueOf(paramAnonymousInt));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(5, DongRuanInstance.this.mapParam));
    }

    public void notifyPlayRadio(Object paramAnonymousObject, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("cid", Integer.valueOf(paramAnonymousInt1));
      DongRuanInstance.this.mapParam.put("pid", Integer.valueOf(paramAnonymousInt3));
      DongRuanInstance.this.mapParam.put("type", Integer.valueOf(paramAnonymousInt2));
      DongRuanInstance.this.mapParam.put("source", Integer.valueOf(paramAnonymousInt4));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(15, DongRuanInstance.this.mapParam));
    }

    public void notifyPreProgram(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(6, DongRuanInstance.this.mapParam));
    }

    public void notifyProgramList(Object paramAnonymousObject, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("radioId", Integer.valueOf(paramAnonymousInt1));
      DongRuanInstance.this.mapParam.put("size", Integer.valueOf(paramAnonymousInt3));
      DongRuanInstance.this.mapParam.put("type", Integer.valueOf(paramAnonymousInt2));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(16, DongRuanInstance.this.mapParam));
    }

    public void notifyRadioList(Object paramAnonymousObject, int paramAnonymousInt1, String paramAnonymousString, int paramAnonymousInt2)
    {
      DongRuanInstance.this.log("" + paramAnonymousObject.getClass());
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("classId", Integer.valueOf(paramAnonymousInt1));
      DongRuanInstance.this.mapParam.put("attrId", paramAnonymousString);
      DongRuanInstance.this.mapParam.put("size", Integer.valueOf(paramAnonymousInt2));
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(17, DongRuanInstance.this.mapParam));
    }

    public void notifyRecentList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(7, DongRuanInstance.this.mapParam));
    }

    public void notifyResultList(Object paramAnonymousObject, String paramAnonymousString)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mapParam.put("keyword", paramAnonymousString);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(18, DongRuanInstance.this.mapParam));
    }

    public void notifySelectList(Object paramAnonymousObject)
    {
      DongRuanInstance.this.mapParam.clear();
      DongRuanInstance.this.mapParam.put("userData", paramAnonymousObject);
      DongRuanInstance.this.mDispatchHandler.sendMessage(DongRuanInstance.this.mDispatchHandler.obtainMessage(20, DongRuanInstance.this.mapParam));
    }

    public void notifyWakeUp()
    {
      DongRuanInstance.this.sendPlayInfo();
    }
  };
  private String mLiveRadioAttrId;
  private int mLiveRadioCatId;
  private InfoManager.ISubscribeEventListener mLiveRadioListener;
  private InfoManager.ISubscribeEventListener mLiveRadioListener2;
  private Object mLiveRadioUserData;
  private InfoManager.ISubscribeEventListener mLivingListener;
  private Object mLivingUserData;
  private boolean mLoading;
  private OnMaskListener mMaskListener;
  private String mRadioAttrId;
  private int mRadioCatid;
  private InfoManager.ISubscribeEventListener mRadioListener;
  private int mRadioSize;
  private Object mRadioUserData;
  private String mResultKeyword;
  private InfoManager.ISubscribeEventListener mResultListener;
  private Object mResultUserData;
  private SearchNode mSearchResult;
  private Object mUserData;
  private HashMap<String, Object> mapBak = new HashMap();
  private HashMap<String, Object> mapParam = new HashMap();
  private int timeMillis = 5000;
  int times = 0;

  private List<List<RecommendItemNode>> getCustomCategoryList(List<List<RecommendItemNode>> paramList)
  {
    int k = 0;
    ArrayList localArrayList2 = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    SparseArray localSparseArray = new SparseArray();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject = InfoManager.getInstance().root().mLocalRecommendCategoryNode;
    if (localObject != null)
    {
      localObject = ((RecommendCategoryNode)localObject).lstRecMain;
      if ((localObject != null) && (((List)localObject).get(0) != null))
      {
        localObject = (List)((List)localObject).get(0);
        if ((localObject != null) && (((List)localObject).size() > 0))
        {
          i = localArrayList2.indexOf(Integer.valueOf(((RecommendItemNode)((List)localObject).get(0)).sectionId));
          if (i < 0)
            break label215;
          localSparseArray.put(i, localObject);
        }
      }
    }
    int i = 0;
    label133: int j;
    if (i < paramList.size())
    {
      localObject = (List)paramList.get(i);
      if (((List)localObject).size() > 0)
      {
        j = localArrayList2.indexOf(Integer.valueOf(((RecommendItemNode)((List)localObject).get(0)).sectionId));
        if (j < 0)
          break label237;
      }
      while (true)
      {
        try
        {
          localSparseArray.put(j, localObject);
          i += 1;
          break label133;
          label215: localArrayList1.add(localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          continue;
        }
        label237: localArrayList1.add(localException);
      }
    }
    paramList = new ArrayList();
    i = 0;
    while (true)
    {
      j = k;
      if (i >= localSparseArray.size())
        break;
      j = localSparseArray.keyAt(i);
      if (localSparseArray.get(j) != null)
        paramList.add(localSparseArray.get(j));
      i += 1;
    }
    while (j < localArrayList1.size())
    {
      if (localArrayList1.get(j) != null)
        paramList.add(localArrayList1.get(j));
      j += 1;
    }
    return paramList;
  }

  public static DongRuanInstance getInstance()
  {
    if (instance == null)
      instance = new DongRuanInstance();
    return instance;
  }

  private boolean hasResult()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (InfoManager.getInstance().root().mSearchNode.getResult(0) != null)
    {
      bool1 = bool2;
      if (InfoManager.getInstance().root().mSearchNode.getResult(0).size() > 0)
        bool1 = true;
    }
    return bool1;
  }

  private void log(String paramString)
  {
    if ((this.mDebug) && (paramString != null))
      Log.e("carplay", paramString);
  }

  private void notifyAllList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    log("notifyAllList ");
    this.mLoading = false;
    this.mAllUserData = paramHashMap.get("userData");
    paramHashMap = InfoManager.getInstance().root().getRecommendCategoryNode(0);
    if (paramHashMap == null)
    {
      if (this.mAllListener == null)
        this.mAllListener = new InfoManager.ISubscribeEventListener()
        {
          public void onNotification(String paramAnonymousString)
          {
            if ((paramAnonymousString.equalsIgnoreCase("RECV_RECOMMEND_INFO")) && (DongRuanInstance.this.mLoading))
            {
              DongRuanInstance.access$2102(DongRuanInstance.this, false);
              paramAnonymousString = InfoManager.getInstance().root().getRecommendCategoryNode(0);
              if (paramAnonymousString != null)
                DongRuanInstance.this.replayAllList(DongRuanInstance.this.mAllUserData, paramAnonymousString);
            }
            else
            {
              return;
            }
            DongRuanInstance.this.mApi.replyAllList(DongRuanInstance.this.mAllUserData, 1, 0, null, 0, null, 0, null);
          }

          public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
          {
          }
        };
      if (NetWorkManage.getInstance().getNetWorkType().equalsIgnoreCase("noNet"))
      {
        this.mApi.replyAllList(this.mAllUserData, 1, 0, null, 0, null, 0, null);
        log("no net");
        return;
      }
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyAllList(DongRuanInstance.this.mAllUserData, 1, 0, null, 0, null, 0, null);
          }
        }
      }
      , this.timeMillis);
      InfoManager.getInstance().loadRecommendInfo(0, this.mAllListener);
      log("loading");
      return;
    }
    log("invoke replayAllList");
    replayAllList(this.mAllUserData, paramHashMap);
    log("end replayAllList");
  }

  private void notifyCityList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    paramHashMap = paramHashMap.get("userData");
    log("notifyCityList ");
    List localList = InfoManager.getInstance().root().mContentCategory.mLiveNode.getRegionAttribute();
    if (localList == null)
    {
      log("return notifyCityList 1");
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      SSP_NEW_QT_FM_API.CityItem localCityItem = this.mApi.newCityItem();
      localCityItem.attrId = ("" + ((Attribute)localList.get(i)).id);
      localCityItem.cityName = ((Attribute)localList.get(i)).name;
      localArrayList.add(localCityItem);
      i += 1;
    }
    log("notifyCityList end: " + localArrayList.size() + " ");
    this.mApi.replyCityList(paramHashMap, 0, localArrayList.size(), localArrayList);
  }

  private void notifyCollectList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    List localList;
    do
    {
      do
      {
        do
          return;
        while (paramHashMap == null);
        log("notifyCollectList");
        paramHashMap = paramHashMap.get("userData");
      }
      while (paramHashMap == null);
      localList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
    }
    while (localList == null);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
      localRadioItem.radioId = Integer.valueOf(((ChannelNode)localList.get(i)).channelId).intValue();
      localRadioItem.type = ((ChannelNode)localList.get(i)).channelType;
      localRadioItem.radioName = ((ChannelNode)localList.get(i)).title;
      localRadioItem.newProgramName = ((ChannelNode)localList.get(i)).latest_program;
      localArrayList.add(localRadioItem);
      i += 1;
    }
    this.mApi.replyCollectList(paramHashMap, 0, localArrayList.size(), localArrayList);
  }

  private void notifyCollectProgram(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    final int k;
    do
    {
      return;
      final int i = ((Integer)paramHashMap.get("radioId")).intValue();
      final int j = ((Integer)paramHashMap.get("type")).intValue();
      k = ((Integer)paramHashMap.get("isCollect")).intValue();
      paramHashMap = ChannelHelper.getInstance().getChannel(i, j);
      if (paramHashMap == null)
      {
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(i, j);
            if (localChannelNode == null);
            do
            {
              return;
              if (k == 0)
              {
                InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode(localChannelNode);
                return;
              }
            }
            while (k != 1);
            InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode(localChannelNode);
          }
        }
        , 2000L);
        return;
      }
      if (k == 0)
      {
        InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode(paramHashMap);
        return;
      }
    }
    while (k != 1);
    InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode(paramHashMap);
  }

  private void notifyDownloadProgramList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    int j;
    Object localObject;
    do
    {
      do
      {
        return;
        j = ((Integer)paramHashMap.get("radioId")).intValue();
        paramHashMap = paramHashMap.get("userData");
        localObject = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(j);
      }
      while (localObject == null);
      localObject = ((ChannelNode)localObject).getAllLstProgramNode();
    }
    while (localObject == null);
    log("notifyDownLoadProgramList " + j);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < ((List)localObject).size())
    {
      SSP_NEW_QT_FM_API.ProgramItem localProgramItem = this.mApi.newProgramItem();
      localProgramItem.programId = ((ProgramNode)((List)localObject).get(i)).uniqueId;
      localProgramItem.programName = ((ProgramNode)((List)localObject).get(i)).title;
      localProgramItem.totalTime = ((ProgramNode)((List)localObject).get(i)).getDuration();
      localProgramItem.thumb = "local";
      localArrayList.add(localProgramItem);
      i += 1;
    }
    this.mApi.replyDownLoadProgramList(paramHashMap, 0, j, localArrayList.size(), localArrayList);
  }

  private void notifyDownloadRadioList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    paramHashMap = paramHashMap.get("userData");
    log("notifyDownLoadRadioList ");
    List localList = InfoManager.getInstance().root().mDownLoadInfoNode.getLstChannelNodes();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < localList.size())
    {
      SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
      localRadioItem.radioId = ((ChannelNode)localList.get(i)).channelId;
      localRadioItem.radioName = ((ChannelNode)localList.get(i)).title;
      localRadioItem.type = 1;
      localRadioItem.thumb = "local";
      if (((ChannelNode)localList.get(i)).getAllLstProgramNode() != null);
      for (localRadioItem.fileNum = ((ChannelNode)localList.get(i)).getAllLstProgramNode().size(); ; localRadioItem.fileNum = 0)
      {
        localArrayList.add(localRadioItem);
        i += 1;
        break;
      }
    }
    this.mApi.replyDownLoadRadioList(paramHashMap, 0, localArrayList.size(), localArrayList);
  }

  private void notifyGetRadioPic(final HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    final String str = (String)paramHashMap.get("thumb");
    final int i = ((Integer)paramHashMap.get("radioId")).intValue();
    final Object localObject = paramHashMap.get("userData");
    if ((str.contains("/")) || (str.contains(".")))
    {
      paramHashMap = str.split("/");
      if (paramHashMap.length > 0)
        if (paramHashMap.length == 1)
        {
          paramHashMap = paramHashMap[0];
          paramHashMap = paramHashMap.split("\\.");
          if (paramHashMap.length <= 0)
            break label246;
          paramHashMap = paramHashMap[0];
        }
    }
    Bitmap localBitmap;
    while (true)
    {
      if ((str != null) && (str.equalsIgnoreCase("local")))
      {
        localBitmap = BitmapResourceCache.getInstance().getResourceCache(this.mContext.getResources(), this, 2130837907);
        this.mApi.replyImageToCar(localObject, 0, i, paramHashMap, localBitmap);
      }
      log("notifyGetRadioPic " + str + " " + i);
      localBitmap = ImageLoader.getInstance(this.mContext).getImage(str, 400, 400);
      if (localBitmap != null)
        break label261;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Bitmap localBitmap2 = ImageLoader.getInstance(DongRuanInstance.this.mContext).getImage(str, 400, 400);
          Bitmap localBitmap1 = localBitmap2;
          if (localBitmap2 == null)
            localBitmap1 = BitmapResourceCache.getInstance().getResourceCache(DongRuanInstance.this.mContext.getResources(), this, 2130837907);
          DongRuanInstance.this.mApi.replyImageToCar(localObject, 0, i, paramHashMap, localBitmap1);
        }
      }
      , 2000L);
      return;
      paramHashMap = paramHashMap[(paramHashMap.length - 1)];
      break;
      label246: paramHashMap = str;
      continue;
      paramHashMap = str;
      continue;
      paramHashMap = str;
    }
    label261: this.mApi.replyImageToCar(localObject, 0, i, paramHashMap, localBitmap);
  }

  private void notifyKeyWordList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    this.mKeyUserData = paramHashMap.get("userData");
    this.mLoading = false;
    paramHashMap = InfoManager.getInstance().root().mSearchNode.getHotKeywords();
    if ((paramHashMap == null) || (paramHashMap.size() == 0))
    {
      if (this.mKeyListener == null)
        this.mKeyListener = new InfoManager.ISubscribeEventListener()
        {
          public void onNotification(String paramAnonymousString)
          {
            if ((paramAnonymousString.equalsIgnoreCase("RSHK")) && (DongRuanInstance.this.mLoading))
            {
              DongRuanInstance.access$2102(DongRuanInstance.this, false);
              paramAnonymousString = InfoManager.getInstance().root().mSearchNode.getHotKeywords();
              if (paramAnonymousString != null)
                DongRuanInstance.this.replayKeyWordList(DongRuanInstance.this.mKeyUserData, paramAnonymousString);
            }
          }

          public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
          {
          }
        };
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyKeyWordList(DongRuanInstance.this.mKeyUserData, 0, 0, null);
          }
        }
      }
      , this.timeMillis);
      InfoManager.getInstance().loadHotSearchKeywords(this.mKeyListener);
      return;
    }
    replayKeyWordList(this.mKeyUserData, paramHashMap);
  }

  private void notifyLabelList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    while (paramHashMap == null)
      return;
    this.mLoading = false;
    this.mLabelUserData = paramHashMap.get("userData");
    this.mLabelClassId = ((Integer)paramHashMap.get("classId")).intValue();
    this.mLabelSectionId = ((Integer)paramHashMap.get("sectionId")).intValue();
    paramHashMap = InfoManager.getInstance().root().getRecommendCategoryNode(this.mLabelSectionId);
    if (paramHashMap == null)
    {
      if (this.mLabelListener == null)
        this.mLabelListener = new InfoManager.ISubscribeEventListener()
        {
          public void onNotification(String paramAnonymousString)
          {
            if ((paramAnonymousString.equalsIgnoreCase("RECV_RECOMMEND_INFO")) && (DongRuanInstance.this.mLoading))
            {
              DongRuanInstance.access$2102(DongRuanInstance.this, false);
              DongRuanInstance.this.log("have content");
              paramAnonymousString = InfoManager.getInstance().root().getRecommendCategoryNode(DongRuanInstance.this.mLabelSectionId);
              DongRuanInstance.this.replayLabelList(DongRuanInstance.this.mLabelUserData, DongRuanInstance.this.mLabelClassId, DongRuanInstance.this.mLabelSectionId, paramAnonymousString);
            }
          }

          public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
          {
          }
        };
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyLabelList(DongRuanInstance.this.mLabelUserData, 1, DongRuanInstance.this.mLabelClassId, DongRuanInstance.this.mLabelSectionId, 0, null);
            DongRuanInstance.this.log("no content");
          }
        }
      }
      , this.timeMillis);
      InfoManager.getInstance().loadRecommendInfo(this.mLabelSectionId, this.mLabelListener);
      return;
    }
    replayLabelList(this.mLabelUserData, this.mLabelClassId, this.mLabelSectionId, paramHashMap);
  }

  private void notifyLiveRadioList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    this.mLoading = false;
    this.mLiveRadioUserData = paramHashMap.get("userData");
    this.mLiveRadioCatId = 5;
    this.mLiveRadioAttrId = ((String)paramHashMap.get("attrId"));
    this.mAttribute = null;
    this.mCategoryNode = null;
    this.mLoading = false;
    paramHashMap = InfoManager.getInstance().root().mContentCategory.mLiveNode;
    List localList = paramHashMap.getRegionAttribute();
    int i = 0;
    if (i < localList.size())
      if (!this.mLiveRadioAttrId.equalsIgnoreCase(((Attribute)localList.get(i)).id + ""));
    while (true)
    {
      if (i != -1)
        this.mAttribute = ((Attribute)localList.get(i));
      if (this.mAttribute == null)
      {
        paramHashMap = paramHashMap.getRegionCategory();
        i = 0;
        label163: if (i >= paramHashMap.size())
          break label567;
        if ((((CategoryNode)paramHashMap.get(i)).mAttributesPath == null) || (!((CategoryNode)paramHashMap.get(i)).mAttributesPath.equalsIgnoreCase("" + this.mLiveRadioAttrId)));
      }
      while (true)
      {
        if (i != -1)
          this.mCategoryNode = ((CategoryNode)paramHashMap.get(i));
        while (true)
        {
          if ((this.mAttribute != null) || (this.mCategoryNode != null))
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                if (DongRuanInstance.this.mLoading)
                {
                  DongRuanInstance.access$2102(DongRuanInstance.this, false);
                  DongRuanInstance.this.mApi.replyLiveRadioList(DongRuanInstance.this.mLiveRadioUserData, 1, 5, DongRuanInstance.this.mLiveRadioAttrId, 0, null);
                }
              }
            }
            , this.timeMillis);
          if (this.mAttribute == null)
            break label452;
          paramHashMap = this.mAttribute.getLstChannels();
          if (paramHashMap != null)
            break label434;
          if (this.mLiveRadioListener == null)
            this.mLiveRadioListener = new InfoManager.ISubscribeEventListener()
            {
              public void onNotification(String paramAnonymousString)
              {
                if ((paramAnonymousString.equalsIgnoreCase("RECV_LIVE_CHANNELS_BYATTR")) && (DongRuanInstance.this.mLoading))
                {
                  DongRuanInstance.access$2102(DongRuanInstance.this, false);
                  paramAnonymousString = DongRuanInstance.this.mAttribute.getLstChannels();
                  DongRuanInstance.this.replyLiveRadioList(DongRuanInstance.this.mLiveRadioUserData, DongRuanInstance.this.mLiveRadioCatId, DongRuanInstance.this.mLiveRadioAttrId, paramAnonymousString);
                }
              }

              public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
              {
              }
            };
          if (!NetWorkManage.getInstance().getNetWorkType().equalsIgnoreCase("noNet"))
            break label391;
          this.mApi.replyLiveRadioList(this.mLiveRadioUserData, 1, 5, this.mLiveRadioAttrId, 0, null);
          return;
          i += 1;
          break;
          i += 1;
          break label163;
          this.mCategoryNode = null;
          continue;
          this.mCategoryNode = null;
        }
        label391: this.mLoading = true;
        ChannelHelper.getInstance().loadListLiveChannelNodes(this.mLiveRadioCatId, "" + this.mLiveRadioAttrId, this.mLiveRadioListener);
        return;
        label434: replyLiveRadioList(this.mLiveRadioUserData, this.mLiveRadioCatId, this.mLiveRadioAttrId, paramHashMap);
        return;
        label452: if (this.mCategoryNode == null)
          break;
        paramHashMap = this.mCategoryNode.getLstChannels();
        if (paramHashMap == null)
        {
          if (this.mLiveRadioListener2 == null)
            this.mLiveRadioListener2 = new InfoManager.ISubscribeEventListener()
            {
              public void onNotification(String paramAnonymousString)
              {
                if ((paramAnonymousString.equalsIgnoreCase("RECV_LIVE_CHANNELS_BYATTR")) && (DongRuanInstance.this.mLoading))
                {
                  DongRuanInstance.access$2102(DongRuanInstance.this, false);
                  paramAnonymousString = DongRuanInstance.this.mCategoryNode.getLstChannels();
                  DongRuanInstance.this.replyLiveRadioList(DongRuanInstance.this.mLiveRadioUserData, DongRuanInstance.this.mLiveRadioCatId, DongRuanInstance.this.mLiveRadioAttrId, paramAnonymousString);
                }
              }

              public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
              {
              }
            };
          if (NetWorkManage.getInstance().getNetWorkType().equalsIgnoreCase("noNet"))
          {
            this.mApi.replyLiveRadioList(this.mLiveRadioUserData, 1, 5, this.mLiveRadioAttrId, 0, null);
            return;
          }
          this.mLoading = true;
          ChannelHelper.getInstance().loadListLiveChannelNodes(this.mLiveRadioCatId, this.mLiveRadioAttrId, this.mLiveRadioListener2);
          return;
        }
        replyLiveRadioList(this.mLiveRadioUserData, this.mLiveRadioCatId, this.mLiveRadioAttrId, paramHashMap);
        return;
        label567: i = -1;
      }
      i = -1;
    }
  }

  private void notifyLivingRadio(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    while (paramHashMap == null)
      return;
    this.mLoading = false;
    this.mLivingUserData = paramHashMap.get("userData");
    paramHashMap = InfoManager.getInstance().root().mRecommendPlayingInfo.getCurrPlayingForShow();
    if (paramHashMap != null)
    {
      replayLivingRadio(this.mLivingUserData, paramHashMap);
      return;
    }
    if (this.mLivingListener == null)
    {
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyLivingRadio(DongRuanInstance.this.mLivingUserData, 1, 0, null);
          }
        }
      }
      , this.timeMillis);
      this.mLivingListener = new InfoManager.ISubscribeEventListener()
      {
        public void onNotification(String paramAnonymousString)
        {
          if ((paramAnonymousString.equalsIgnoreCase("RRPPI")) && (DongRuanInstance.this.mLoading))
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            paramAnonymousString = InfoManager.getInstance().root().mRecommendPlayingInfo.getCurrPlayingForShow();
            DongRuanInstance.this.replayLivingRadio(DongRuanInstance.this.mLivingUserData, paramAnonymousString);
          }
        }

        public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
        {
        }
      };
    }
    InfoManager.getInstance().loadRecommendPlayingProgramsInfo(this.mLivingListener);
    this.mApi.replyLivingRadio(this.mLivingUserData, 1, 0, null);
  }

  private void notifyPlayNextProgram()
  {
    if (!this.mHasConnected)
      return;
    log("startNext");
    PlayerAgent.getInstance().playNextForCar();
    log("endNext");
    sendPlayInfo();
    log("notifyNextProgram:");
  }

  private void notifyPlayOrPause(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    int i = ((Integer)paramHashMap.get("action")).intValue();
    if (i == 1)
      PlayerAgent.getInstance().stop();
    while (true)
    {
      sendPlayInfo();
      log("notifyPlayOrPause: " + i);
      return;
      if (i == 0)
      {
        paramHashMap = InfoManager.getInstance().root().getCurrentPlayingNode();
        PlayerAgent.getInstance().play(paramHashMap);
      }
    }
  }

  private void notifyPlayPreProgram()
  {
    if (!this.mHasConnected)
      return;
    PlayerAgent.getInstance().playPreForCar();
    sendPlayInfo();
    log("notifyPreProgram ");
  }

  private void notifyPlayRadio(final HashMap<String, Object> paramHashMap)
  {
    ProgramNode localProgramNode = null;
    if (!this.mHasConnected)
      return;
    final int j;
    final ChannelNode localChannelNode;
    Iterator localIterator;
    while (true)
    {
      try
      {
        InfoManager.getInstance().root().setPlayingNode(null);
        InfoManager.getInstance().root().setPlayingChannelNode(null);
        this.times += 1;
        int i = ((Integer)paramHashMap.get("cid")).intValue();
        j = ((Integer)paramHashMap.get("pid")).intValue();
        int k = ((Integer)paramHashMap.get("type")).intValue();
        ((Integer)paramHashMap.get("source")).intValue();
        localChannelNode = ChannelHelper.getInstance().getChannel(i, k);
        if (localChannelNode == null)
          break;
        if (localChannelNode.getAllLstProgramNode() == null)
        {
          InfoManager.getInstance().loadProgramsScheduleNode(localChannelNode, null);
          if ((localChannelNode.getAllLstProgramNode() == null) && (this.times < 10))
          {
            if (this.mListRunnable == null)
              this.mListRunnable = new Runnable()
              {
                public void run()
                {
                  DongRuanInstance.this.notifyPlayRadio(paramHashMap);
                }
              };
            new Handler().removeCallbacks(this.mListRunnable);
            new Handler().postDelayed(this.mListRunnable, 2000L);
          }
        }
        if (localChannelNode.channelType == 0)
        {
          ControllerManager.getInstance().redirectToPlayViewByNode(localChannelNode, true);
          sendPlayInfo();
          return;
        }
      }
      catch (Exception paramHashMap)
      {
        paramHashMap.printStackTrace();
        return;
      }
      if (localChannelNode.getAllLstProgramNode() != null)
      {
        localIterator = localChannelNode.getAllLstProgramNode().iterator();
        paramHashMap = localProgramNode;
      }
    }
    while (true)
      if (localIterator.hasNext())
      {
        localProgramNode = (ProgramNode)localIterator.next();
        if (localProgramNode.id == j)
          paramHashMap = localProgramNode;
      }
      else
      {
        if (paramHashMap == null)
        {
          DataLoadWrapper.loadVProgramInfo(j, new IResultRecvHandler()
          {
            public void onRecvResult(Result paramAnonymousResult, Object paramAnonymousObject1, IResultToken paramAnonymousIResultToken, Object paramAnonymousObject2)
            {
              if ((paramAnonymousResult.getSuccess()) && (paramAnonymousIResultToken.getType().equalsIgnoreCase("virtual_program_info")))
              {
                paramAnonymousResult = (ProgramNode)paramAnonymousResult.getData();
                if (paramAnonymousResult != null)
                {
                  localChannelNode.getAllLstProgramNode().add(paramAnonymousResult);
                  PlayerAgent.getInstance().play(paramAnonymousResult);
                  ControllerManager.getInstance().openPlayController(true, j);
                }
              }
            }
          });
          PlayerAgent.getInstance().play((Node)localChannelNode.getAllLstProgramNode().get(0));
          ControllerManager.getInstance().openPlayController(true, j);
          break;
        }
        PlayerAgent.getInstance().play(paramHashMap);
        ControllerManager.getInstance().openPlayController(true, j);
        break;
        if ((localChannelNode != null) || (this.times >= 10))
          break;
        if (this.mChannelRunnable == null)
          this.mChannelRunnable = new Runnable()
          {
            public void run()
            {
              DongRuanInstance.this.notifyPlayRadio(paramHashMap);
            }
          };
        new Handler().removeCallbacks(this.mChannelRunnable);
        new Handler().postDelayed(this.mChannelRunnable, 2000L);
        break;
      }
  }

  private void notifyProgramList(final HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    final int i;
    final int j;
    final int k;
    do
    {
      return;
      i = ((Integer)paramHashMap.get("radioId")).intValue();
      j = ((Integer)paramHashMap.get("type")).intValue();
      k = ((Integer)paramHashMap.get("size")).intValue();
      paramHashMap = paramHashMap.get("userData");
    }
    while (paramHashMap == null);
    log("notifyProgramList " + i + " " + j + " " + k);
    ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(i, j);
    if (localChannelNode != null)
    {
      replayProgramList(i, j, k, paramHashMap, localChannelNode);
      return;
    }
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(i, j);
        if (localChannelNode != null)
        {
          DongRuanInstance.this.replayProgramList(i, j, k, paramHashMap, localChannelNode);
          return;
        }
        DongRuanInstance.this.mApi.replyProgramList(paramHashMap, 1, "", i, -1, null, 0, null);
      }
    }
    , 2000L);
  }

  private void notifyRadioList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    this.mRadioCatid = ((Integer)paramHashMap.get("classId")).intValue();
    this.mRadioAttrId = ((String)paramHashMap.get("attrId"));
    this.mRadioSize = ((Integer)paramHashMap.get("size")).intValue();
    this.mRadioUserData = paramHashMap.get("userData");
    this.mLoading = false;
    log("notifyRadioList " + this.mRadioCatid + " " + this.mRadioAttrId + " " + this.mRadioSize);
    paramHashMap = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mRadioCatid, "" + this.mRadioAttrId);
    if ((paramHashMap == null) || (paramHashMap.size() <= this.mRadioSize))
    {
      if (this.mRadioListener == null)
        this.mRadioListener = new InfoManager.ISubscribeEventListener()
        {
          public void onNotification(String paramAnonymousString)
          {
            if ((paramAnonymousString.equalsIgnoreCase("RECV_VIRTUAL_CHANNELS_BYATTR")) && (DongRuanInstance.this.mLoading))
            {
              DongRuanInstance.access$2102(DongRuanInstance.this, false);
              paramAnonymousString = ChannelHelper.getInstance().getLstChannelsByAttrPath(DongRuanInstance.this.mRadioCatid, "" + DongRuanInstance.this.mRadioAttrId);
              DongRuanInstance.this.log("notifyRadioList 1: " + DongRuanInstance.this.mRadioCatid + " " + DongRuanInstance.this.mRadioAttrId + " " + DongRuanInstance.this.mRadioSize);
              if (paramAnonymousString != null)
              {
                DongRuanInstance.this.log("notifyRadioList success");
                DongRuanInstance.this.replayRadioList(DongRuanInstance.this.mRadioUserData, DongRuanInstance.this.mRadioCatid, DongRuanInstance.this.mRadioAttrId, DongRuanInstance.this.mRadioSize, paramAnonymousString);
              }
            }
            else
            {
              return;
            }
            DongRuanInstance.this.log("notifyRadioList failed");
            DongRuanInstance.this.mApi.replyRadioList(DongRuanInstance.this.mRadioUserData, 1, DongRuanInstance.this.mRadioCatid, DongRuanInstance.this.mRadioAttrId, 0, null);
          }

          public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
          {
          }
        };
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyRadioList(DongRuanInstance.this.mLiveRadioUserData, 1, DongRuanInstance.this.mLiveRadioCatId, DongRuanInstance.this.mLiveRadioAttrId, 0, null);
          }
        }
      }
      , this.timeMillis);
      ChannelHelper.getInstance().loadListVirtualChannelNodesById(this.mRadioCatid, "" + this.mRadioAttrId, this.mRadioListener);
      return;
    }
    replayRadioList(this.mRadioUserData, this.mRadioCatid, this.mRadioAttrId, this.mRadioSize, paramHashMap);
  }

  private void notifyRecentList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected)
      return;
    Object localObject = paramHashMap.get("userData");
    log("notifyRecentList ");
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < localList.size())
    {
      log("notifyRecentList: " + ((PlayHistoryNode)localList.get(i)).playNode.nodeName);
      SSP_NEW_QT_FM_API.RecentItem localRecentItem = this.mApi.newRecentItem();
      if (((PlayHistoryNode)localList.get(i)).playNode.nodeName.equalsIgnoreCase("program"))
        if (((ProgramNode)((PlayHistoryNode)localList.get(i)).playNode).channelType == 1)
        {
          localRecentItem.type = 1;
          localRecentItem.radioType = 1;
          paramHashMap = "VIRTUAL_CHANNEL ";
          label177: localRecentItem.id = Integer.valueOf(((ProgramNode)((PlayHistoryNode)localList.get(i)).playNode).id).intValue();
          localRecentItem.radioId = Integer.valueOf(((PlayHistoryNode)localList.get(i)).channelId).intValue();
          localRecentItem.name = ((ProgramNode)((PlayHistoryNode)localList.get(i)).playNode).title;
          localRecentItem.thumb = "local";
          localRecentItem.listenTime = 0L;
          paramHashMap = paramHashMap + localRecentItem.name;
          log("notifyRecentList: " + paramHashMap);
        }
      while (true)
      {
        localRecentItem.listenTime = ((int)((PlayHistoryNode)localList.get(i)).playTime);
        localArrayList.add(localRecentItem);
        i += 1;
        break;
        localRecentItem.type = 0;
        localRecentItem.type = 1;
        paramHashMap = "LIVE_CHANNEL ";
        break label177;
        if (((PlayHistoryNode)localList.get(i)).playNode.nodeName.equalsIgnoreCase("channel"))
        {
          localRecentItem.type = 0;
          localRecentItem.id = Integer.valueOf(((ProgramNode)((PlayHistoryNode)localList.get(i)).playNode).id).intValue();
          localRecentItem.radioId = Integer.valueOf(((PlayHistoryNode)localList.get(i)).channelId).intValue();
          localRecentItem.name = ((PlayHistoryNode)localList.get(i)).channelName;
          localRecentItem.thumb = "local";
          localRecentItem.listenTime = 0L;
        }
      }
    }
    this.mApi.replyRecentList(localObject, 0, localArrayList.size(), localArrayList);
  }

  private void notifySelectList(final HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    while (paramHashMap == null)
      return;
    paramHashMap = paramHashMap.get("userData");
    RecommendCategoryNode localRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(0);
    if (localRecommendCategoryNode == null)
    {
      InfoManager.getInstance().loadRecommendInfo(0, new InfoManager.ISubscribeEventListener()
      {
        public void onNotification(String paramAnonymousString)
        {
          if (paramAnonymousString.equalsIgnoreCase("RECV_RECOMMEND_INFO"))
          {
            paramAnonymousString = InfoManager.getInstance().root().getRecommendCategoryNode(0);
            DongRuanInstance.this.replaySelectList(paramHashMap, paramAnonymousString);
          }
        }

        public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
        {
        }
      });
      return;
    }
    replaySelectList(paramHashMap, localRecommendCategoryNode);
  }

  private void recvSearchResult(Object paramObject)
  {
    log("recvSearchResult");
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Object localObject2 = InfoManager.getInstance().root().mSearchNode;
    Object localObject3 = ((SearchNode)localObject2).getResult(3);
    Object localObject1 = ((SearchNode)localObject2).getResult(1);
    localObject2 = ((SearchNode)localObject2).getResult(4);
    if (localObject3 != null)
    {
      i = 0;
      while (i < ((List)localObject3).size())
      {
        SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
        localRadioItem.radioId = Integer.valueOf(((SearchItemNode)((List)localObject3).get(i)).channelId).intValue();
        localRadioItem.type = 0;
        localRadioItem.radioName = ((SearchItemNode)((List)localObject3).get(i)).name;
        localRadioItem.className = ((SearchItemNode)((List)localObject3).get(i)).catName;
        localRadioItem.thumb = ((SearchItemNode)((List)localObject3).get(i)).cover;
        if ((localRadioItem.thumb == null) || (localRadioItem.thumb.equalsIgnoreCase("")))
          localRadioItem.thumb = "local";
        localArrayList1.add(localRadioItem);
        i += 1;
      }
    }
    if (localObject1 != null)
    {
      i = 0;
      while (i < ((List)localObject1).size())
      {
        localObject3 = this.mApi.newRadioItem();
        ((SSP_NEW_QT_FM_API.RadioItem)localObject3).radioId = Integer.valueOf(((SearchItemNode)((List)localObject1).get(i)).channelId).intValue();
        ((SSP_NEW_QT_FM_API.RadioItem)localObject3).type = 1;
        ((SSP_NEW_QT_FM_API.RadioItem)localObject3).radioName = ((SearchItemNode)((List)localObject1).get(i)).name;
        ((SSP_NEW_QT_FM_API.RadioItem)localObject3).className = ((SearchItemNode)((List)localObject1).get(i)).catName;
        ((SSP_NEW_QT_FM_API.RadioItem)localObject3).thumb = ((SearchItemNode)((List)localObject1).get(i)).cover;
        if ((((SSP_NEW_QT_FM_API.RadioItem)localObject3).thumb == null) || (((SSP_NEW_QT_FM_API.RadioItem)localObject3).thumb.equalsIgnoreCase("")))
          ((SSP_NEW_QT_FM_API.RadioItem)localObject3).thumb = "local";
        localArrayList2.add(localObject3);
        i += 1;
      }
    }
    if (localObject2 != null)
    {
      i = 0;
      while (i < ((List)localObject2).size())
      {
        localObject1 = this.mApi.newProgramItem();
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).programId = Integer.valueOf(((SearchItemNode)((List)localObject2).get(i)).programId).intValue();
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).programName = ((SearchItemNode)((List)localObject2).get(i)).name;
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).radioName = ((SearchItemNode)((List)localObject2).get(i)).cName;
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).radioId = Integer.valueOf(((SearchItemNode)((List)localObject2).get(i)).channelId).intValue();
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).radioType = 1;
        ((SSP_NEW_QT_FM_API.ProgramItem)localObject1).thumb = "local";
        localArrayList3.add(localObject1);
        i += 1;
      }
    }
    int i = localArrayList1.size();
    int j = localArrayList2.size();
    int k = localArrayList3.size();
    this.mApi.replyResultList(paramObject, 0, i + j + k, localArrayList1.size(), localArrayList1, localArrayList2.size(), localArrayList2, localArrayList3.size(), localArrayList3);
  }

  private void replayAllList(Object paramObject, RecommendCategoryNode paramRecommendCategoryNode)
  {
    if (paramRecommendCategoryNode == null)
      return;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    paramRecommendCategoryNode = paramRecommendCategoryNode.lstRecMain;
    if (InfoManager.getInstance().getEnableCustomCategory())
    {
      paramRecommendCategoryNode = getCustomCategoryList(paramRecommendCategoryNode);
      if (paramRecommendCategoryNode.size() <= 0)
        break label984;
    }
    while (true)
    {
      int i;
      label67: SSP_NEW_QT_FM_API.ClassItem localClassItem;
      if (paramRecommendCategoryNode != null)
      {
        i = 0;
        if (i < paramRecommendCategoryNode.size())
        {
          if ((((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("精选")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("电台")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("读报")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("音乐")));
          while (true)
          {
            i += 1;
            break label67;
            break;
            localClassItem = this.mApi.newClassItem();
            localClassItem.classId = Integer.valueOf(((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).mCategoryId).intValue();
            localClassItem.className = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName;
            localClassItem.sectionId = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).sectionId;
            localArrayList1.add(localClassItem);
          }
        }
      }
      paramRecommendCategoryNode = InfoManager.getInstance().root().mContentCategory.mLiveNode.getLocalCategoryNode();
      if (paramRecommendCategoryNode != null)
      {
        paramRecommendCategoryNode = (Attribute)paramRecommendCategoryNode;
        if ((paramRecommendCategoryNode.name != null) && (!paramRecommendCategoryNode.name.endsWith("台")))
          paramRecommendCategoryNode.name += "台";
        localClassItem = this.mApi.newClassItem();
        localClassItem.classId = 5;
        localClassItem.attrId = ("" + paramRecommendCategoryNode.id);
        localClassItem.className = paramRecommendCategoryNode.name;
        localArrayList2.add(localClassItem);
        log("local_radio_name: " + localClassItem.className);
      }
      paramRecommendCategoryNode = InfoManager.getInstance().root().mContentCategory.mLiveNode.getLiveCategoryNodes();
      if (paramRecommendCategoryNode != null)
      {
        i = 0;
        if (i < paramRecommendCategoryNode.size())
        {
          localClassItem = this.mApi.newClassItem();
          if (((CategoryNode)paramRecommendCategoryNode.get(i)).nodeName.equalsIgnoreCase("category"))
          {
            String str = ((CategoryNode)paramRecommendCategoryNode.get(i)).mAttributesPath;
            if ((str == null) || (str.equalsIgnoreCase("1242")) || (str.equalsIgnoreCase("1243")))
              if (((CategoryNode)paramRecommendCategoryNode.get(i)).mAttributesPath != null)
                break label669;
          }
          label669: for (localClassItem.attrId = "-1"; ; localClassItem.attrId = ((CategoryNode)paramRecommendCategoryNode.get(i)).mAttributesPath)
          {
            localClassItem.classId = 5;
            localClassItem.className = ((CategoryNode)paramRecommendCategoryNode.get(i)).name;
            localArrayList2.add(localClassItem);
            i += 1;
            break;
          }
        }
      }
      paramRecommendCategoryNode = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes();
      if (paramRecommendCategoryNode != null)
      {
        i = 0;
        if (i < paramRecommendCategoryNode.size())
        {
          if ((((CategoryNode)paramRecommendCategoryNode.get(i)).name.equalsIgnoreCase("精选")) || (((CategoryNode)paramRecommendCategoryNode.get(i)).name.equalsIgnoreCase("电台")) || (((CategoryNode)paramRecommendCategoryNode.get(i)).name.equalsIgnoreCase("读报")));
          while (true)
          {
            i += 1;
            break;
            localClassItem = this.mApi.newClassItem();
            localClassItem.classId = Integer.valueOf(((CategoryNode)paramRecommendCategoryNode.get(i)).categoryId).intValue();
            localClassItem.sectionId = ((CategoryNode)paramRecommendCategoryNode.get(i)).sectionId;
            localClassItem.className = ((CategoryNode)paramRecommendCategoryNode.get(i)).name;
            localArrayList3.add(localClassItem);
          }
        }
      }
      log("notifyAllList end: " + localArrayList1.size() + " " + localArrayList2.size() + " " + localArrayList3.size());
      this.mApi.replyAllList(paramObject, 0, localArrayList1.size(), localArrayList1, localArrayList2.size(), localArrayList2, localArrayList3.size(), localArrayList3);
      return;
      label984: paramRecommendCategoryNode = null;
    }
  }

  private void replayKeyWordList(Object paramObject, List<SearchHotKeyword> paramList)
  {
    if (!this.mHasConnected)
      return;
    if (paramList == null)
      this.mApi.replyKeyWordList(paramObject, 0, 0, null);
    String[] arrayOfString = new String[paramList.size() + 1];
    int i = 0;
    while (i < paramList.size())
    {
      arrayOfString[i] = ((SearchHotKeyword)paramList.get(i)).keyword;
      i += 1;
    }
    log("notifyKeyWordList: " + arrayOfString.length);
    this.mApi.replyKeyWordList(paramObject, 0, arrayOfString.length - 1, arrayOfString);
  }

  private void replayLabelList(Object paramObject, int paramInt1, int paramInt2, RecommendCategoryNode paramRecommendCategoryNode)
  {
    if (paramRecommendCategoryNode == null)
    {
      this.mApi.replyLabelList(paramObject, 1, paramInt1, paramInt2, 0, null);
      return;
    }
    paramRecommendCategoryNode = paramRecommendCategoryNode.lstRecMain;
    if (paramRecommendCategoryNode != null)
    {
      ArrayList localArrayList1 = new ArrayList();
      int i = 0;
      while (i < paramRecommendCategoryNode.size())
      {
        SSP_NEW_QT_FM_API.LabelItem localLabelItem = this.mApi.newLabelItem();
        localLabelItem.attrName = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName;
        localLabelItem.attrId = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).mAttributesPath;
        ArrayList localArrayList2 = new ArrayList();
        int j = 0;
        if (j < ((List)paramRecommendCategoryNode.get(i)).size())
        {
          SSP_NEW_QT_FM_API.RecommendItem localRecommendItem = this.mApi.newRecommendItem();
          Object localObject = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode;
          if ((localObject instanceof ProgramNode))
          {
            localRecommendItem.radioId = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).channelId;
            localRecommendItem.type = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).channelType;
            localRecommendItem.id = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).id;
            localRecommendItem.name = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).name;
            localRecommendItem.thumb = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).getApproximativeThumb();
            localArrayList2.add(localRecommendItem);
          }
          while (true)
          {
            j += 1;
            break;
            if ((localObject instanceof ChannelNode))
            {
              localObject = (ChannelNode)localObject;
              localRecommendItem.radioId = ((ChannelNode)localObject).channelId;
              localRecommendItem.type = ((ChannelNode)localObject).channelType;
              localRecommendItem.id = ((ChannelNode)localObject).channelId;
              localRecommendItem.name = ((ChannelNode)localObject).title;
              localRecommendItem.thumb = ((ChannelNode)localObject).getApproximativeThumb();
              localArrayList2.add(localRecommendItem);
            }
          }
        }
        localLabelItem.recmdList = localArrayList2;
        localArrayList1.add(localLabelItem);
        i += 1;
      }
      this.mApi.replyLabelList(paramObject, 0, paramInt1, paramInt2, localArrayList1.size(), localArrayList1);
      return;
    }
    this.mApi.replyLabelList(paramObject, 1, paramInt1, paramInt2, 0, null);
  }

  private void replayLivingRadio(Object paramObject, List<RecommendPlayingItemNode> paramList)
  {
    if (paramList == null)
      return;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramList.size())
    {
      RecommendPlayingItemNode localRecommendPlayingItemNode = (RecommendPlayingItemNode)paramList.get(i);
      SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
      localRadioItem.radioId = localRecommendPlayingItemNode.channelId;
      localRadioItem.radioName = localRecommendPlayingItemNode.channelName;
      localRadioItem.playingRadio = localRecommendPlayingItemNode.programName;
      localRadioItem.thumb = localRecommendPlayingItemNode.thumb;
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localRadioItem.radioId));
      for (int j = 0; ; j = 1)
      {
        localRadioItem.isCollect = j;
        localArrayList.add(localRadioItem);
        i += 1;
        break;
      }
    }
    this.mApi.replyLivingRadio(paramObject, 0, localArrayList.size(), localArrayList);
  }

  private void replayProgramList(int paramInt1, int paramInt2, int paramInt3, Object paramObject, ChannelNode paramChannelNode)
  {
    this.mapBak.clear();
    String str = paramChannelNode.title;
    Object localObject2 = paramChannelNode.getApproximativeThumb();
    Object localObject1;
    if (localObject2 != null)
    {
      localObject1 = localObject2;
      if (!((String)localObject2).equalsIgnoreCase(""));
    }
    else
    {
      localObject1 = "local";
    }
    if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(paramChannelNode.channelId));
    for (int i = 0; (paramChannelNode.getAllLstProgramNode() != null) && (paramChannelNode.getAllLstProgramNode().size() > paramInt3); i = 1)
    {
      paramChannelNode = paramChannelNode.getAllLstProgramNode();
      localObject2 = new ArrayList();
      while (paramInt3 < paramChannelNode.size())
      {
        SSP_NEW_QT_FM_API.ProgramItem localProgramItem = this.mApi.newProgramItem();
        localProgramItem.programId = Integer.valueOf(((ProgramNode)paramChannelNode.get(paramInt3)).id).intValue();
        localProgramItem.programName = ((ProgramNode)paramChannelNode.get(paramInt3)).title;
        localProgramItem.updateTime = ((int)((ProgramNode)paramChannelNode.get(paramInt3)).getUpdateTime());
        log("update_time: " + localProgramItem.updateTime);
        localProgramItem.thumb = "local";
        ((List)localObject2).add(localProgramItem);
        paramInt3 += 1;
      }
    }
    this.mapBak.put("userData", paramObject);
    this.mapBak.put("radioId", Integer.valueOf(paramInt1));
    this.mapBak.put("type", Integer.valueOf(paramInt2));
    this.mapBak.put("size", Integer.valueOf(paramInt3));
    InfoManager.getInstance().loadProgramsScheduleNode(paramChannelNode, this);
    return;
    log("notifyProgramList end: " + ((List)localObject2).size());
    this.mApi.replyProgramList(paramObject, 0, str, paramInt1, i, (String)localObject1, ((List)localObject2).size(), (List)localObject2);
  }

  private void replayRadioList(Object paramObject, int paramInt1, String paramString, int paramInt2, List<ChannelNode> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramInt2 < paramList.size())
    {
      SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
      localRadioItem.type = 1;
      localRadioItem.radioId = Integer.valueOf(((ChannelNode)paramList.get(paramInt2)).channelId).intValue();
      localRadioItem.radioName = ((ChannelNode)paramList.get(paramInt2)).title;
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(((ChannelNode)paramList.get(paramInt2)).channelId));
      for (localRadioItem.isCollect = 0; ; localRadioItem.isCollect = 1)
      {
        localRadioItem.thumb = ((ChannelNode)paramList.get(paramInt2)).getApproximativeThumb();
        if ((localRadioItem.thumb == null) || (localRadioItem.thumb.equalsIgnoreCase("")))
          localRadioItem.thumb = "local";
        localArrayList.add(localRadioItem);
        paramInt2 += 1;
        break;
      }
    }
    log("notifyRadioList end :" + localArrayList.size());
    this.mApi.replyRadioList(paramObject, 0, paramInt1, paramString, localArrayList.size(), localArrayList);
  }

  private void replaySelectList(Object paramObject, RecommendCategoryNode paramRecommendCategoryNode)
  {
    if (paramRecommendCategoryNode == null)
      return;
    paramRecommendCategoryNode = paramRecommendCategoryNode.lstRecMain;
    if (paramRecommendCategoryNode != null)
    {
      ArrayList localArrayList1 = new ArrayList();
      int i = 0;
      if (i < paramRecommendCategoryNode.size())
      {
        SSP_NEW_QT_FM_API.LabelItem localLabelItem = this.mApi.newLabelItem();
        localLabelItem.attrName = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName;
        localLabelItem.attrId = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).mAttributesPath;
        if ((((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("精选")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("电台")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("读报")) || (((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(0)).belongName.equalsIgnoreCase("音乐")));
        while (true)
        {
          i += 1;
          break;
          ArrayList localArrayList2 = new ArrayList();
          int j = 0;
          if (j < ((List)paramRecommendCategoryNode.get(i)).size())
          {
            SSP_NEW_QT_FM_API.RecommendItem localRecommendItem = this.mApi.newRecommendItem();
            Object localObject = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode;
            if ((localObject instanceof ProgramNode))
            {
              localRecommendItem.radioId = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).channelId;
              localRecommendItem.type = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).channelType;
              localRecommendItem.id = ((ProgramNode)((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).mNode).id;
              localRecommendItem.name = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).name;
              localRecommendItem.thumb = ((RecommendItemNode)((List)paramRecommendCategoryNode.get(i)).get(j)).getApproximativeThumb();
              localArrayList2.add(localRecommendItem);
            }
            while (true)
            {
              j += 1;
              break;
              if ((localObject instanceof ChannelNode))
              {
                localObject = (ChannelNode)localObject;
                localRecommendItem.radioId = ((ChannelNode)localObject).channelId;
                localRecommendItem.type = ((ChannelNode)localObject).channelType;
                localRecommendItem.id = ((ChannelNode)localObject).channelId;
                localRecommendItem.name = ((ChannelNode)localObject).title;
                localRecommendItem.thumb = ((ChannelNode)localObject).getApproximativeThumb();
                localArrayList2.add(localRecommendItem);
              }
              else if ((localObject instanceof SpecialTopicNode))
              {
                localObject = (SpecialTopicNode)localObject;
                localRecommendItem.id = ((SpecialTopicNode)localObject).id;
                localRecommendItem.name = ((SpecialTopicNode)localObject).title;
                localRecommendItem.thumb = ((SpecialTopicNode)localObject).thumb;
                localArrayList2.add(localRecommendItem);
              }
            }
          }
          localLabelItem.recmdList = localArrayList2;
          localArrayList1.add(localLabelItem);
        }
      }
      this.mApi.replySelectList(paramObject, 0, localArrayList1.size(), localArrayList1);
      return;
    }
    this.mApi.replySelectList(paramObject, 1, 0, null);
  }

  private void replyLiveRadioList(Object paramObject, int paramInt, String paramString, List<ChannelNode> paramList)
  {
    if (paramList == null)
    {
      this.mApi.replyLiveRadioList(paramObject, 1, 5, paramString, 0, null);
      return;
    }
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramList.size())
    {
      SSP_NEW_QT_FM_API.RadioItem localRadioItem = this.mApi.newRadioItem();
      localRadioItem.radioId = Integer.valueOf(((ChannelNode)paramList.get(i)).channelId).intValue();
      localRadioItem.radioName = ((ChannelNode)paramList.get(i)).title;
      Node localNode = InfoManager.getInstance().root().mPlayingProgramInfo.getCurrentPlayingProgram((Node)paramList.get(i));
      if (localNode == null)
      {
        localRadioItem.playingRadio = "正在加载...";
        label137: if (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localRadioItem.radioId))
          break label259;
      }
      label259: for (localRadioItem.isCollect = 0; ; localRadioItem.isCollect = 1)
      {
        localRadioItem.thumb = ((ChannelNode)paramList.get(i)).getApproximativeThumb();
        if ((localRadioItem.thumb == null) || (localRadioItem.thumb.equalsIgnoreCase("")))
          localRadioItem.thumb = "local";
        localRadioItem.playingRadio = "";
        localArrayList.add(localRadioItem);
        i += 1;
        break;
        localRadioItem.playingRadio = ((PlayingProgramNode)localNode).programName;
        break label137;
      }
    }
    this.mApi.replyLiveRadioList(paramObject, 0, paramInt, paramString, localArrayList.size(), localArrayList);
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    log("init interface");
    this.mContext = paramContext;
    this.mApi = SSP_NEW_QT_FM_API.getInstance();
    this.mApi.setContext(paramContext);
    this.mApi.setListener(this.mListener);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
    log("starWork");
    this.mApi.startWork();
    if (((Activity)this.mContext).getIntent().getStringExtra("carNotfiyOpen") != null)
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          DongRuanInstance.this.mApi.replyWakeUp();
          DongRuanInstance.this.log("wakeup");
        }
      }
      , 2000L);
    InfoManager.getInstance().registerSubscribeEventListener(this, "RPS");
  }

  public void notifyResultList(HashMap<String, Object> paramHashMap)
  {
    if (!this.mHasConnected);
    do
    {
      return;
      this.mResultKeyword = ((String)paramHashMap.get("keyword"));
      this.mResultUserData = paramHashMap.get("userData");
      this.mLoading = false;
      log("notifyResultList " + this.mResultKeyword);
    }
    while (this.mResultKeyword == null);
    if (this.mResultListener == null)
    {
      this.mLoading = true;
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (DongRuanInstance.this.mLoading)
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            DongRuanInstance.this.mApi.replyResultList(DongRuanInstance.this.mResultUserData, 1, 0, 0, null, 0, null, 0, null);
          }
        }
      }
      , this.timeMillis);
      this.mResultListener = new InfoManager.ISubscribeEventListener()
      {
        public void onNotification(String paramAnonymousString)
        {
          if ((paramAnonymousString.equalsIgnoreCase("RSRL")) && (DongRuanInstance.this.mLoading))
          {
            DongRuanInstance.access$2102(DongRuanInstance.this, false);
            if (DongRuanInstance.this.hasResult())
              DongRuanInstance.this.recvSearchResult(DongRuanInstance.this.mResultUserData);
          }
          else
          {
            return;
          }
          DongRuanInstance.this.mApi.replyResultList(DongRuanInstance.this.mResultUserData, 1, 0, 0, null, 0, null, 0, null);
        }

        public void onRecvDataException(String paramAnonymousString, InfoManager.DataExceptionStatus paramAnonymousDataExceptionStatus)
        {
        }
      };
    }
    InfoManager.getInstance().loadSearch(this.mResultKeyword, this.mResultListener);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RPS"))
      if (this.mapBak.size() > 0)
        notifyProgramList(this.mapBak);
    do
    {
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("RECV_RECOMMEND_INFO"))
          break;
        Log.e("onNotification", "RECV_RECOMMEND_INFO");
      }
      while (InfoManager.getInstance().root().getRecommendCategoryNode(0) == null);
      notifyAllList(this.mapBak);
      return;
    }
    while (!paramString.equalsIgnoreCase("RECV_LIVE_CHANNELS_BYATTR"));
    notifyLiveRadioList(this.mapBak);
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    sendPlayInfo();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void pause()
  {
    if (!this.mHasConnected)
      return;
    this.mApi.sendPlayOrPause(1);
  }

  public void play()
  {
    if (!this.mHasConnected)
      return;
    this.mApi.sendPlayOrPause(0);
  }

  public void sendPlayInfo()
  {
    int i1 = 1;
    if (!this.mHasConnected);
    int i;
    label111: label121: 
    do
    {
      do
      {
        return;
        localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      }
      while (localObject == null);
      if (((Node)localObject).nodeName.equalsIgnoreCase("program"))
      {
        localObject = (ProgramNode)localObject;
        ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel((ProgramNode)localObject);
        int k;
        int m;
        if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localChannelNode) == true)
        {
          i = 0;
          j = ((ProgramNode)localObject).getDuration();
          if (((ProgramNode)localObject).title.equalsIgnoreCase("节目单加载中..."))
            j = 0;
          if (j != 0)
            break label217;
          ((ProgramNode)localObject).title = "节目单加载中...";
          k = 0;
          if (((ProgramNode)localObject).channelType != 0)
            break label228;
          m = 1;
          if (!PlayerAgent.getInstance().isPlaying())
            break label234;
        }
        for (int n = 0; ; n = 1)
        {
          SSP_NEW_QT_FM_API localSSP_NEW_QT_FM_API = this.mApi;
          int i2 = (int)((ProgramNode)localObject).getAbsoluteStartTime();
          int i3 = (int)((ProgramNode)localObject).getAbsoluteEndTime();
          String str1 = ((ProgramNode)localObject).title;
          String str2 = ((ProgramNode)localObject).getBroadCasterNames();
          int i4 = ((ProgramNode)localObject).channelId;
          if (m != 0)
            i1 = 0;
          localSSP_NEW_QT_FM_API.sendProgramInfo("pushInfo", 0, i, n, k, j, i2, i3, str1, str2, i4, i1, localChannelNode.title);
          return;
          i = 1;
          break;
          k = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
          break label111;
          m = 0;
          break label121;
        }
      }
    }
    while (!((Node)localObject).nodeName.equalsIgnoreCase("channel"));
    label217: label228: label234: Object localObject = (ChannelNode)localObject;
    if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject) == true)
    {
      i = 0;
      if (!PlayerAgent.getInstance().isPlaying())
        break label352;
    }
    label352: for (int j = 0; ; j = 1)
    {
      this.mApi.sendProgramInfo("pushInfo", 0, i, j, PlayProcessSyncUtil.getInstance().getCurrentPlayTime(), PlayProcessSyncUtil.getInstance().getTotalLength(), PlayProcessSyncUtil.getInstance().getCurrentPlayTime(), PlayProcessSyncUtil.getInstance().getTotalLength(), "节目单加载中", ((ChannelNode)localObject).getBroadCasterNames(), 0, 0, ((ChannelNode)localObject).title);
      return;
      i = 1;
      break;
    }
  }

  public void setOnMaskListener(OnMaskListener paramOnMaskListener)
  {
    this.mMaskListener = paramOnMaskListener;
  }

  private class DispatchHandler extends Handler
  {
    public DispatchHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      try
      {
        switch (paramMessage.what)
        {
        case 0:
          EventDispacthManager.getInstance().dispatchAction("QTquit", null);
          return;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 11:
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 20:
        case 21:
        case 10:
        }
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        DongRuanInstance.this.log("crash in handle");
        return;
      }
      DongRuanInstance.this.notifyCollectList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyCollectProgram((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyKeyWordList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyPlayNextProgram();
      return;
      DongRuanInstance.this.notifyPlayOrPause((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyPlayPreProgram();
      return;
      DongRuanInstance.this.notifyRecentList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyAllList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyCityList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyDownloadProgramList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyDownloadRadioList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyGetRadioPic((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyLiveRadioList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.times = 0;
      DongRuanInstance.this.notifyPlayRadio((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyProgramList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyRadioList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyResultList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyLivingRadio((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifySelectList((HashMap)paramMessage.obj);
      return;
      DongRuanInstance.this.notifyLabelList((HashMap)paramMessage.obj);
      return;
    }
  }

  class MaskHandler extends Handler
  {
    public MaskHandler()
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.what == 0)
      {
        if (DongRuanInstance.this.mMaskListener != null)
        {
          Log.e("Neusoft", "connect");
          InfoManager.getInstance().setConnectNuesoft(true);
          DongRuanInstance.this.mMaskListener.onMask(true);
        }
        DongRuanInstance.access$5202(DongRuanInstance.this, true);
      }
      while (paramMessage.what != 1)
        return;
      if (DongRuanInstance.this.mMaskListener != null)
      {
        Log.e("Neusoft", "disconnect");
        InfoManager.getInstance().setConnectNuesoft(false);
        DongRuanInstance.this.mMaskListener.onMask(false);
      }
      DongRuanInstance.access$5202(DongRuanInstance.this, false);
    }
  }

  public static abstract interface OnMaskListener
  {
    public abstract void onMask(boolean paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.dongruan.DongRuanInstance
 * JD-Core Version:    0.6.2
 */