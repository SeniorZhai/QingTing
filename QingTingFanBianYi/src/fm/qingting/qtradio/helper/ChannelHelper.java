package fm.qingting.qtradio.helper;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelHelper extends Node
{
  private static ChannelHelper _instance = null;
  private final int ERROR_CHANNELS = 1;
  private final int LIVE_CHANNELS_PAGE_SIZE = 1000;
  private final int VIRTUAL_CHANNELS_PAGE_SIZE = 30;
  private SparseArray<IDataChangeObserver> mObservers;
  public Map<String, List<ChannelNode>> mapChannelNodes = new HashMap();
  public Map<String, Integer> mapChannelPages = new HashMap();
  private SparseArray<ChannelNode> mapLiveChannels = new SparseArray();
  private Map<String, Integer> mapTotalChannelNodes = new HashMap();
  public Map<String, Boolean> mapUpdateChannels = new HashMap();
  private SparseArray<ChannelNode> mapVirtualChannels = new SparseArray();

  private ChannelHelper()
  {
    this.nodeName = "channelhelper";
  }

  private void addChannels(List<ChannelNode> paramList, int paramInt1, String paramString, int paramInt2)
  {
    if (paramList == null);
    ChannelNode localChannelNode;
    do
    {
      return;
      if (paramInt1 == 1)
      {
        paramInt1 = 0;
        if (paramInt1 < paramList.size())
        {
          localChannelNode = (ChannelNode)this.mapVirtualChannels.get(((ChannelNode)paramList.get(paramInt1)).channelId);
          if (localChannelNode != null)
            localChannelNode.updatePartialInfo((ChannelNode)paramList.get(paramInt1));
          while (true)
          {
            paramInt1 += 1;
            break;
            this.mapVirtualChannels.put(((ChannelNode)paramList.get(paramInt1)).channelId, paramList.get(paramInt1));
          }
        }
      }
      else
      {
        paramInt1 = 0;
        if (paramInt1 < paramList.size())
        {
          localChannelNode = (ChannelNode)this.mapLiveChannels.get(((ChannelNode)paramList.get(paramInt1)).channelId);
          if (localChannelNode != null)
            localChannelNode.updatePartialInfo((ChannelNode)paramList.get(paramInt1));
          while (true)
          {
            paramInt1 += 1;
            break;
            this.mapLiveChannels.put(((ChannelNode)paramList.get(paramInt1)).channelId, paramList.get(paramInt1));
          }
        }
      }
    }
    while (paramString == null);
    if (this.mapChannelNodes.get(paramString) == null)
    {
      this.mapChannelNodes.put(paramString, paramList);
      return;
    }
    paramString = (List)this.mapChannelNodes.get(paramString);
    paramInt1 = paramString.size();
    if ((paramInt2 == paramInt1) && (paramInt1 > 0))
    {
      localChannelNode = (ChannelNode)paramString.get(paramString.size() - 1);
      localChannelNode.nextSibling = ((Node)paramList.get(0));
      ((ChannelNode)paramList.get(0)).prevSibling = localChannelNode;
      paramString.addAll(paramList);
      return;
    }
    paramInt1 = 0;
    label319: if (paramInt1 < paramList.size())
    {
      paramInt2 = 0;
      label332: if (paramInt2 >= paramString.size())
        break label462;
      if (((ChannelNode)paramString.get(paramInt2)).channelId != ((ChannelNode)paramList.get(paramInt1)).channelId)
        break label436;
      ((ChannelNode)paramString.get(paramInt2)).updatePartialInfo((ChannelNode)paramList.get(paramInt1));
    }
    label436: label445: label462: for (paramInt2 = 1; ; paramInt2 = 0)
    {
      if (paramInt2 == 0)
      {
        if (paramInt1 >= paramString.size())
          break label445;
        paramString.add(paramInt1, paramList.get(paramInt1));
      }
      while (true)
      {
        paramInt1 += 1;
        break label319;
        break;
        paramInt2 += 1;
        break label332;
        paramString.add(paramList.get(paramInt1));
      }
    }
  }

  private boolean allowReadCache(String paramString)
  {
    if (!InfoManager.getInstance().hasConnectedNetwork());
    while (this.mapUpdateChannels.get(paramString) != null)
      return true;
    this.mapUpdateChannels.put(paramString, Boolean.valueOf(true));
    return false;
  }

  private String buildAttrs(List<Attribute> paramList)
  {
    Object localObject2;
    if ((paramList == null) || (paramList.size() == 0))
    {
      localObject2 = null;
      return localObject2;
    }
    Object localObject1 = "";
    int i = 0;
    while (true)
    {
      localObject2 = localObject1;
      if (i >= paramList.size())
        break;
      localObject2 = (String)localObject1 + ((Attribute)paramList.get(i)).id;
      localObject1 = localObject2;
      if (i < paramList.size() - 1)
        localObject1 = (String)localObject2 + "/";
      i += 1;
    }
  }

  private String buildKey(int paramInt, String paramString)
  {
    String str2 = String.valueOf(paramInt);
    String str1 = str2;
    if (paramString != null)
    {
      str1 = str2;
      if (!paramString.equalsIgnoreCase(""))
      {
        str1 = str2;
        if (!paramString.equalsIgnoreCase("0"))
        {
          str1 = str2 + "/";
          str1 = str1 + paramString;
        }
      }
    }
    return str1;
  }

  private String buildKey(int paramInt, List<Attribute> paramList)
  {
    String str1 = String.valueOf(paramInt);
    if (paramList != null)
    {
      Collections.sort(paramList, new AttributeComparator());
      paramInt = 0;
      while (true)
      {
        str2 = str1;
        if (paramInt >= paramList.size())
          break;
        str1 = str1 + "/";
        str1 = str1 + ((Attribute)paramList.get(paramInt)).id;
        paramInt += 1;
      }
    }
    String str2 = str1;
    return str2;
  }

  private void dispatch2Observer(ChannelNode paramChannelNode)
  {
    if (this.mObservers == null);
    IDataChangeObserver localIDataChangeObserver;
    do
    {
      return;
      localIDataChangeObserver = (IDataChangeObserver)this.mObservers.get(paramChannelNode.channelId);
    }
    while (localIDataChangeObserver == null);
    localIDataChangeObserver.onChannelNodeInfoUpdate(paramChannelNode);
  }

  private ChannelNode getChannelFromDB(int paramInt1, int paramInt2)
  {
    ChannelNode localChannelNode = null;
    Object localObject = new HashMap();
    ((Map)localObject).put("channelid", Integer.valueOf(paramInt1));
    ((Map)localObject).put("type", Integer.valueOf(paramInt2));
    localObject = DataManager.getInstance().getData("GETDB_CHANNEL_INFO", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
      localChannelNode = (ChannelNode)((Result)localObject).getData();
    return localChannelNode;
  }

  public static ChannelHelper getInstance()
  {
    if (_instance == null)
      _instance = new ChannelHelper();
    return _instance;
  }

  private List<ChannelNode> getLstChannelsFromDB(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return null;
      HashMap localHashMap = new HashMap();
      localHashMap.put("key", paramString);
      paramString = DataManager.getInstance().getData("GETDB_CHANNEL_NODE", null, localHashMap).getResult();
    }
    while (!paramString.getSuccess());
    return (List)paramString.getData();
  }

  private int getTotalChannelNodes(String paramString)
  {
    if ((paramString != null) && (this.mapTotalChannelNodes.get(paramString) != null))
      return ((Integer)this.mapTotalChannelNodes.get(paramString)).intValue();
    return 0;
  }

  private void incPageByKey(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    int i = getPageByKey(paramString);
    this.mapChannelPages.put(paramString, Integer.valueOf(i + 1));
  }

  private void updateChannel(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("node", paramChannelNode);
    localHashMap.put("channelid", Integer.valueOf(paramChannelNode.channelId));
    DataManager.getInstance().getData("UPDATEDB_A_CHANNEL_NODE", null, localHashMap);
  }

  public void addObserver(int paramInt, IDataChangeObserver paramIDataChangeObserver)
  {
    if (this.mObservers == null)
      this.mObservers = new SparseArray();
    this.mObservers.put(paramInt, paramIDataChangeObserver);
  }

  public ChannelNode getChannel(int paramInt1, int paramInt2)
  {
    Object localObject;
    if (paramInt2 == 0)
    {
      localObject = (ChannelNode)this.mapLiveChannels.get(paramInt1);
      if (localObject == null);
    }
    else
    {
      do
      {
        return localObject;
        if (paramInt2 != 1)
          break;
        localChannelNode = (ChannelNode)this.mapVirtualChannels.get(paramInt1);
        localObject = localChannelNode;
      }
      while (localChannelNode != null);
    }
    ChannelNode localChannelNode = getChannelFromDB(paramInt1, paramInt2);
    if (localChannelNode != null)
    {
      if (paramInt2 != 1)
        break label107;
      this.mapVirtualChannels.put(paramInt1, localChannelNode);
    }
    while (true)
    {
      if (paramInt2 == 0)
        InfoManager.getInstance()._loadLiveChannelNode(paramInt1, this);
      localObject = localChannelNode;
      if (paramInt2 != 1)
        break;
      InfoManager.getInstance().loadVirtualChannelNode(paramInt1, this);
      return localChannelNode;
      label107: if (paramInt2 == 0)
        this.mapLiveChannels.put(paramInt1, localChannelNode);
    }
  }

  public ChannelNode getChannel(int paramInt1, int paramInt2, String paramString)
  {
    Object localObject;
    if (paramInt2 == 0)
    {
      localObject = (ChannelNode)this.mapLiveChannels.get(paramInt1);
      if (localObject == null);
    }
    else
    {
      do
      {
        return localObject;
        if (paramInt2 != 1)
          break;
        localChannelNode = (ChannelNode)this.mapVirtualChannels.get(paramInt1);
        localObject = localChannelNode;
      }
      while (localChannelNode != null);
    }
    ChannelNode localChannelNode = getChannelFromDB(paramInt1, paramInt2);
    if (localChannelNode != null)
    {
      if ((localChannelNode.title != null) && (localChannelNode.title.equalsIgnoreCase("电台故障")) && (paramString != null) && (!paramString.equalsIgnoreCase("")))
        localChannelNode.title = paramString;
      if (paramInt2 != 1)
        break label153;
      this.mapVirtualChannels.put(paramInt1, localChannelNode);
    }
    while (true)
    {
      if (paramInt2 == 0)
        InfoManager.getInstance()._loadLiveChannelNode(paramInt1, this);
      localObject = localChannelNode;
      if (paramInt2 != 1)
        break;
      InfoManager.getInstance().loadVirtualChannelNode(paramInt1, this);
      return localChannelNode;
      label153: if (paramInt2 == 0)
        this.mapLiveChannels.put(paramInt1, localChannelNode);
    }
  }

  public ChannelNode getChannel(ProgramNode paramProgramNode)
  {
    Object localObject;
    if (paramProgramNode == null)
    {
      localObject = null;
      return localObject;
    }
    if (paramProgramNode.isDownloadProgram())
      return InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramProgramNode.channelId);
    if ((paramProgramNode.channelType == 1) || (paramProgramNode.mLiveInVirtual));
    for (ChannelNode localChannelNode = (ChannelNode)this.mapVirtualChannels.get(paramProgramNode.channelId); ; localChannelNode = (ChannelNode)this.mapLiveChannels.get(paramProgramNode.channelId))
    {
      localObject = localChannelNode;
      if (localChannelNode != null)
        break;
      if (!paramProgramNode.mLiveInVirtual)
        break label103;
      return getChannelFromDB(paramProgramNode.channelId, 1);
    }
    label103: return getChannelFromDB(paramProgramNode.channelId, paramProgramNode.channelType);
  }

  public ChannelNode getFakeChannel(int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramInt3 == 0)
      return getFakeLiveChannel(paramInt1, paramInt2, paramString);
    return getFakeVirtualChannel(paramInt1, paramInt2, paramString);
  }

  public ChannelNode getFakeLiveChannel(int paramInt1, int paramInt2, String paramString)
  {
    if (this.mapLiveChannels.get(paramInt1) != null)
      return (ChannelNode)this.mapLiveChannels.get(paramInt1);
    ChannelNode localChannelNode = new ChannelNode();
    localChannelNode.channelId = paramInt1;
    localChannelNode.title = paramString;
    localChannelNode.channelType = 0;
    if (localChannelNode.title == null)
      localChannelNode.title = "电台";
    localChannelNode.categoryId = paramInt2;
    this.mapLiveChannels.put(paramInt1, localChannelNode);
    InfoManager.getInstance()._loadLiveChannelNode(paramInt1, this);
    return localChannelNode;
  }

  public ChannelNode getFakeVirtualChannel(int paramInt1, int paramInt2, String paramString)
  {
    if (this.mapVirtualChannels.get(paramInt1) != null)
      return (ChannelNode)this.mapVirtualChannels.get(paramInt1);
    if (paramInt2 == DownLoadInfoNode.mDownloadId)
    {
      localChannelNode = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramInt1);
      if (localChannelNode != null)
      {
        this.mapVirtualChannels.put(paramInt1, localChannelNode);
        return localChannelNode;
      }
    }
    ChannelNode localChannelNode = new ChannelNode();
    localChannelNode.channelId = paramInt1;
    localChannelNode.title = paramString;
    localChannelNode.channelType = 1;
    if (localChannelNode.title == null)
      localChannelNode.title = "蜻蜓fm";
    localChannelNode.categoryId = paramInt2;
    this.mapVirtualChannels.put(paramInt1, localChannelNode);
    InfoManager.getInstance().loadVirtualChannelNode(paramInt1, this);
    return localChannelNode;
  }

  public List<ChannelNode> getLstChannelsByAttr(int paramInt, List<Attribute> paramList)
  {
    return getLstChannelsByKey(buildKey(paramInt, paramList));
  }

  public List<ChannelNode> getLstChannelsByAttrPath(int paramInt, String paramString)
  {
    return getLstChannelsByKey(buildKey(paramInt, paramString));
  }

  public List<ChannelNode> getLstChannelsByKey(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      if (this.mapChannelNodes.get(paramString) == null)
        break label37;
      localObject1 = (List)this.mapChannelNodes.get(paramString);
    }
    label37: List localList;
    do
    {
      do
      {
        do
        {
          return localObject1;
          localObject1 = localObject2;
        }
        while (!allowReadCache(paramString));
        localList = getLstChannelsFromDB(paramString);
        localObject1 = localObject2;
      }
      while (localList == null);
      localObject1 = localObject2;
    }
    while (localList.size() == 0);
    this.mapChannelNodes.put(paramString, localList);
    return localList;
  }

  public List<ChannelNode> getLstChannelsByKey(String paramString, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      if (this.mapChannelNodes.get(paramString) == null)
        break label39;
      localObject1 = (List)this.mapChannelNodes.get(paramString);
    }
    label39: List localList;
    do
    {
      do
      {
        do
        {
          return localObject1;
          localObject1 = localObject2;
        }
        while (!paramBoolean);
        localList = getLstChannelsFromDB(paramString);
        localObject1 = localObject2;
      }
      while (localList == null);
      localObject1 = localObject2;
    }
    while (localList.size() == 0);
    this.mapChannelNodes.put(paramString, localList);
    return localList;
  }

  public List<ChannelNode> getLstLiveChannelsByAttrPath(int paramInt, String paramString, boolean paramBoolean)
  {
    String str = buildKey(paramInt, paramString);
    List localList2 = getLstChannelsByKey(str);
    List localList1;
    if (localList2 != null)
    {
      localList1 = localList2;
      if (localList2.size() != 0);
    }
    else
    {
      loadListLiveChannelNodes(paramInt, paramString, null);
      localList1 = getLstChannelsByKey(str, paramBoolean);
    }
    return localList1;
  }

  public ChannelNode getNextChannel(int paramInt1, int paramInt2)
  {
    ChannelNode localChannelNode = getChannel(paramInt1, paramInt2);
    if (localChannelNode != null)
      return (ChannelNode)localChannelNode.nextSibling;
    return null;
  }

  public int getPageByAttr(int paramInt, List<Attribute> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return 0;
    return getPageByKey(buildKey(paramInt, paramList));
  }

  public int getPageByAttrPath(int paramInt, String paramString)
  {
    return getPageByKey(buildKey(paramInt, paramString));
  }

  public int getPageByKey(String paramString)
  {
    if (paramString != null)
    {
      if (this.mapChannelPages.get(paramString) != null)
        return ((Integer)this.mapChannelPages.get(paramString)).intValue();
      List localList = getLstChannelsByKey(paramString);
      if (localList != null)
      {
        this.mapChannelPages.put(paramString, Integer.valueOf(localList.size() / 30));
        return localList.size() / 30;
      }
    }
    return 0;
  }

  public ChannelNode getPrevChannel(int paramInt1, int paramInt2)
  {
    ChannelNode localChannelNode = getChannel(paramInt1, paramInt2);
    if (localChannelNode != null)
      return (ChannelNode)localChannelNode.prevSibling;
    return null;
  }

  public void init()
  {
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_CATEGORY_ALL_CHANNELS");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_VIRTUAL_CHANNELS_BYATTR");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_LIVE_CHANNELS_BYATTR");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_SPECIAL_TOPIC_CHANNELS");
  }

  public boolean isFinished(int paramInt, String paramString)
  {
    paramString = buildKey(paramInt, paramString);
    if (paramString != null)
    {
      List localList = getLstChannelsByKey(paramString);
      paramInt = getTotalChannelNodes(paramString);
      if ((localList != null) && (paramInt > 0) && (localList.size() + 1 > paramInt))
        return true;
    }
    return false;
  }

  public boolean isFinished(int paramInt, List<Attribute> paramList)
  {
    paramList = buildKey(paramInt, paramList);
    if (paramList != null)
    {
      List localList = getLstChannelsByKey(paramList);
      paramInt = getTotalChannelNodes(paramList);
      if ((localList != null) && (paramInt > 0) && (localList.size() + 1 > paramInt))
        return true;
    }
    return false;
  }

  public void loadListLiveChannelNodes(int paramInt, String paramString, InfoManager.ISubscribeEventListener paramISubscribeEventListener)
  {
    InfoManager.getInstance().loadListLiveChannelsByAttr(paramInt, paramString, 1, 1000, paramISubscribeEventListener);
  }

  public void loadListVirtualChannelNodesByAttr(int paramInt, List<Attribute> paramList, InfoManager.ISubscribeEventListener paramISubscribeEventListener)
  {
    String str = buildKey(paramInt, paramList);
    int i = getPageByKey(str);
    int j = getTotalChannelNodes(str);
    if ((j > 0) && (i * 30 >= j))
      return;
    incPageByKey(str);
    InfoManager.getInstance().loadListVirtualChannelsByAttr(paramInt, buildAttrs(paramList), i + 1, 30, false, paramISubscribeEventListener);
  }

  public void loadListVirtualChannelNodesById(int paramInt, String paramString, InfoManager.ISubscribeEventListener paramISubscribeEventListener)
  {
    String str = buildKey(paramInt, paramString);
    int i = getPageByKey(str);
    int j = getTotalChannelNodes(str);
    if ((j > 0) && (i * 30 >= j))
      return;
    incPageByKey(str);
    InfoManager.getInstance().loadListVirtualChannelsByAttr(paramInt, paramString, i + 1, 30, false, paramISubscribeEventListener);
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    if (paramObject == null);
    do
    {
      do
      {
        do
        {
          return;
          if (!paramString.equalsIgnoreCase("ADD_VIRTUAL_CHANNEL_INFO"))
            break;
          paramObject = (Node)paramObject;
        }
        while ((paramObject == null) || (!paramObject.nodeName.equalsIgnoreCase("channel")));
        paramString = (ChannelNode)this.mapVirtualChannels.get(((ChannelNode)paramObject).channelId);
        if (paramString != null)
          paramString.updatePartialInfo((ChannelNode)paramObject);
        while (true)
        {
          dispatch2Observer((ChannelNode)paramObject);
          updateChannel((ChannelNode)paramObject);
          paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
          if ((paramString == null) || (paramString.channelId != ((ChannelNode)paramObject).channelId))
            break;
          PlayerAgent.getInstance().setPlayingChannelThumb(((ChannelNode)paramObject).getApproximativeThumb());
          paramString.updateAllInfo((ChannelNode)paramObject);
          return;
          this.mapVirtualChannels.put(((ChannelNode)paramObject).channelId, (ChannelNode)paramObject);
        }
      }
      while (!paramString.equalsIgnoreCase("ADD_LIVE_CHANNEL_INFO"));
      paramObject = (Node)paramObject;
    }
    while ((paramObject == null) || (!paramObject.nodeName.equalsIgnoreCase("channel")));
    paramString = (ChannelNode)this.mapLiveChannels.get(((ChannelNode)paramObject).channelId);
    if (paramString != null)
      paramString.updatePartialInfo((ChannelNode)paramObject);
    while (true)
    {
      dispatch2Observer((ChannelNode)paramObject);
      updateChannel((ChannelNode)paramObject);
      return;
      this.mapLiveChannels.put(((ChannelNode)paramObject).channelId, (ChannelNode)paramObject);
    }
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    Object localObject;
    int i;
    int j;
    String str;
    if (paramString.equalsIgnoreCase("ADD_CATEGORY_ALL_CHANNELS"))
    {
      paramObject = (List)paramObject;
      if ((paramObject.size() > 0) && (paramMap != null))
      {
        paramString = (String)paramMap.get("page");
        localObject = (String)paramMap.get("pagesize");
        i = Integer.valueOf(paramString).intValue();
        j = Integer.valueOf((String)localObject).intValue();
        localObject = (ChannelNode)paramObject.get(0);
        paramString = buildKey(((ChannelNode)localObject).categoryId, "");
        str = (String)paramMap.get("code");
        paramMap = (String)paramMap.get("message");
        if ((str != null) && (paramMap != null) && (str.equalsIgnoreCase("total")))
          this.mapTotalChannelNodes.put(paramString, Integer.valueOf(paramMap));
        addChannels(paramObject, ((ChannelNode)localObject).channelType, paramString, (i - 1) * j);
        paramObject = new Message();
        paramObject.what = 9;
        paramObject.obj = paramString;
        InfoManager.getInstance().getDataStoreHandler().sendMessage(paramObject);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (!paramString.equalsIgnoreCase("ADD_LIVE_CHANNELS_BYATTR"))
              break;
            paramObject = (List)paramObject;
          }
          while ((paramObject.size() <= 0) || (paramMap == null));
          paramMap = (String)paramMap.get("attr");
          paramMap = buildKey(((ChannelNode)paramObject.get(0)).categoryId, paramMap);
          addChannels(paramObject, 0, paramMap, 0);
          paramObject = new Message();
          paramObject.what = 9;
          paramObject.obj = paramMap;
          InfoManager.getInstance().getDataStoreHandler().sendMessage(paramObject);
          return;
          if (!paramString.equalsIgnoreCase("ADD_VIRTUAL_CHANNELS_BYATTR"))
            break;
          paramObject = (List)paramObject;
        }
        while ((paramObject.size() <= 0) || (paramMap == null));
        paramString = (String)paramMap.get("page");
        str = (String)paramMap.get("pagesize");
        localObject = (String)paramMap.get("attr");
        i = Integer.valueOf(paramString).intValue();
        j = Integer.valueOf(str).intValue();
        paramString = (ChannelNode)paramObject.get(0);
        localObject = buildKey(paramString.categoryId, (String)localObject);
        str = (String)paramMap.get("code");
        paramMap = (String)paramMap.get("message");
        if ((str != null) && (paramMap != null) && (str.equalsIgnoreCase("total")))
          this.mapTotalChannelNodes.put(localObject, Integer.valueOf(paramMap));
        addChannels(paramObject, paramString.channelType, (String)localObject, (i - 1) * j);
        return;
      }
      while (!paramString.equalsIgnoreCase("ADD_SPECIAL_TOPIC_CHANNELS"));
      paramObject = (List)paramObject;
    }
    while ((paramObject.size() <= 0) || (paramMap == null));
    addChannels(paramObject, 1, String.valueOf(Integer.valueOf((String)paramMap.get("id")).intValue() + 1000001), 0);
  }

  public void reloadListVirtualChannelNodesByAttr(int paramInt, List<Attribute> paramList, InfoManager.ISubscribeEventListener paramISubscribeEventListener)
  {
    String str = buildKey(paramInt, paramList);
    if (getPageByKey(str) + 1 == 1)
      incPageByKey(str);
    InfoManager.getInstance().loadListVirtualChannelsByAttr(paramInt, buildAttrs(paramList), 1, 30, true, paramISubscribeEventListener);
  }

  public void reloadListVirtualChannelNodesById(int paramInt, String paramString, InfoManager.ISubscribeEventListener paramISubscribeEventListener)
  {
    String str = buildKey(paramInt, paramString);
    if (getPageByKey(str) + 1 == 1)
      incPageByKey(str);
    InfoManager.getInstance().loadListVirtualChannelsByAttr(paramInt, paramString, 1, 30, true, paramISubscribeEventListener);
  }

  public void removeObserver(int paramInt)
  {
    if (this.mObservers == null)
      return;
    this.mObservers.remove(paramInt);
  }

  public void setChannel(ChannelNode paramChannelNode, boolean paramBoolean)
  {
    if (paramChannelNode == null);
    do
    {
      do
        return;
      while (paramChannelNode.isDownloadChannel());
      if (paramChannelNode.channelType != 1)
        break;
      this.mapVirtualChannels.put(paramChannelNode.channelId, paramChannelNode);
    }
    while (!paramBoolean);
    InfoManager.getInstance().loadVirtualChannelNode(paramChannelNode.channelId, this);
    return;
    this.mapLiveChannels.put(paramChannelNode.channelId, paramChannelNode);
  }

  public void setChannelsByAttr(List<ChannelNode> paramList, int paramInt1, String paramString, int paramInt2)
  {
    if ((paramList == null) || (paramString == null))
      return;
    String str = paramInt1 + "/" + paramString;
    if (paramInt2 == 1)
    {
      this.mapChannelNodes.put(str, paramList);
      return;
    }
    paramString = (List)this.mapChannelNodes.get(str);
    if (paramString != null)
    {
      paramString.addAll(paramList);
      paramList = paramString;
    }
    while (true)
    {
      this.mapChannelNodes.put(str, paramList);
      return;
    }
  }

  public void updateChannel(String paramString)
  {
    if (paramString == null);
    List localList;
    do
    {
      return;
      localList = getLstChannelsByKey(paramString);
    }
    while ((localList == null) || (localList.size() == 0));
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", localList);
    localHashMap.put("key", paramString);
    DataManager.getInstance().getData("UPDATEDB_CHANNEL_NODE", null, localHashMap);
  }

  class AttributeComparator
    implements Comparator<Attribute>
  {
    AttributeComparator()
    {
    }

    public int compare(Attribute paramAttribute1, Attribute paramAttribute2)
    {
      if (paramAttribute1.id > paramAttribute2.id)
        return 1;
      if (paramAttribute1.id < paramAttribute2.id)
        return -1;
      return 0;
    }
  }

  public static abstract interface IDataChangeObserver
  {
    public abstract void onChannelNodeInfoUpdate(ChannelNode paramChannelNode);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.ChannelHelper
 * JD-Core Version:    0.6.2
 */