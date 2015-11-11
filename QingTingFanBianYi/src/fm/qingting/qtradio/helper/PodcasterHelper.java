package fm.qingting.qtradio.helper;

import android.text.TextUtils;
import android.util.Pair;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.room.UserInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PodcasterHelper extends Node
{
  private static PodcasterHelper _instance = null;
  private Map<Integer, UserInfo> mapPodcasters = new HashMap();

  private PodcasterHelper()
  {
    this.nodeName = "podcasterhelper";
  }

  private void addPodcaster(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      return;
    Object localObject = (UserInfo)this.mapPodcasters.get(Integer.valueOf(paramUserInfo.podcasterId));
    if (localObject == null)
      this.mapPodcasters.put(Integer.valueOf(paramUserInfo.podcasterId), paramUserInfo);
    while (true)
    {
      localObject = new HashMap();
      ((Map)localObject).put("userInfo", paramUserInfo);
      DataManager.getInstance().getData("UPDATEDB_PODCASTER_INFO", null, (Map)localObject);
      return;
      ((UserInfo)localObject).updateUserInfo(paramUserInfo);
    }
  }

  private void addPodcasterChannels(int paramInt, List<ChannelNode> paramList)
  {
    UserInfo localUserInfo = (UserInfo)this.mapPodcasters.get(Integer.valueOf(paramInt));
    if (localUserInfo == null)
    {
      localUserInfo = new UserInfo();
      localUserInfo.podcasterId = paramInt;
    }
    while (true)
    {
      localUserInfo.setChannelNodes(paramList);
      int i = 0;
      while (i < paramList.size())
      {
        if (((ChannelNode)paramList.get(i)).lstPodcasters == null)
        {
          ((ChannelNode)paramList.get(i)).lstPodcasters = new ArrayList();
          ((ChannelNode)paramList.get(i)).lstPodcasters.add(localUserInfo);
        }
        i += 1;
      }
      this.mapPodcasters.put(Integer.valueOf(paramInt), localUserInfo);
      return;
    }
  }

  private void addPodcasterPrograms(int paramInt, List<ProgramNode> paramList)
  {
    UserInfo localUserInfo2 = (UserInfo)this.mapPodcasters.get(Integer.valueOf(paramInt));
    UserInfo localUserInfo1 = localUserInfo2;
    if (localUserInfo2 == null)
    {
      localUserInfo1 = new UserInfo();
      localUserInfo1.podcasterId = paramInt;
    }
    localUserInfo1.setProgramNodes(paramList);
  }

  public static PodcasterHelper getInstance()
  {
    try
    {
      if (_instance == null)
      {
        _instance = new PodcasterHelper();
        _instance.init();
      }
      PodcasterHelper localPodcasterHelper = _instance;
      return localPodcasterHelper;
    }
    finally
    {
    }
  }

  private UserInfo getPodcasterFromDB(int paramInt)
  {
    UserInfo localUserInfo = null;
    Object localObject = new HashMap();
    ((Map)localObject).put("pid", Integer.valueOf(paramInt));
    localObject = DataManager.getInstance().getData("GETDB_PODCASTER_INFO", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
      localUserInfo = (UserInfo)((Result)localObject).getData();
    return localUserInfo;
  }

  private void updateLastestProgramId(UserInfo paramUserInfo)
  {
    UserInfo localUserInfo = (UserInfo)this.mapPodcasters.get(Integer.valueOf(paramUserInfo.podcasterId));
    if (localUserInfo == null)
    {
      this.mapPodcasters.put(Integer.valueOf(paramUserInfo.podcasterId), paramUserInfo);
      return;
    }
    localUserInfo.lastestUpdateTime = paramUserInfo.lastestUpdateTime;
  }

  public boolean addMyPodcaster(int paramInt, String paramString, long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("pid", Integer.valueOf(paramInt));
    localHashMap.put("ukey", paramString);
    localHashMap.put("uptime", Long.valueOf(paramLong));
    return DataManager.getInstance().getData("INSERTDB_PODCASTER_FOLLOW_INFO", null, localHashMap).getResult().getSuccess();
  }

  public List<Integer> getAllMyPodcaster(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = new HashMap();
    ((Map)localObject2).put("ukey", paramString);
    localObject2 = DataManager.getInstance().getData("GETDB_ALL_PODCASTER_FOLLOW_INFO", null, (Map)localObject2).getResult();
    paramString = (String)localObject1;
    if (((Result)localObject2).getSuccess())
    {
      localObject1 = (List)((Result)localObject2).getData();
      paramString = new ArrayList();
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Pair)((Iterator)localObject1).next();
        int i = ((Integer)((Pair)localObject2).first).intValue();
        long l = ((Long)((Pair)localObject2).second).longValue();
        localObject2 = new UserInfo();
        ((UserInfo)localObject2).podcasterId = i;
        ((UserInfo)localObject2).lastestUpdateTime = l;
        updateLastestProgramId((UserInfo)localObject2);
        paramString.add(Integer.valueOf(i));
      }
    }
    return paramString;
  }

  public UserInfo getPodcaster(int paramInt)
  {
    UserInfo localUserInfo2 = (UserInfo)this.mapPodcasters.get(Integer.valueOf(paramInt));
    if ((localUserInfo2 == null) || (TextUtils.isEmpty(localUserInfo2.podcasterName)))
    {
      localUserInfo1 = getPodcasterFromDB(paramInt);
      if (localUserInfo2 != null)
        localUserInfo2.updateUserInfo(localUserInfo1);
    }
    UserInfo localUserInfo1 = localUserInfo2;
    if (localUserInfo2 == null)
    {
      localUserInfo1 = new UserInfo();
      localUserInfo1.podcasterId = paramInt;
      localUserInfo1.podcasterName = "加载中";
      localUserInfo1.isPodcaster = true;
      InfoManager.getInstance().loadPodcasterBaseInfo(localUserInfo1.podcasterId, null);
    }
    this.mapPodcasters.put(Integer.valueOf(localUserInfo1.podcasterId), localUserInfo1);
    return localUserInfo1;
  }

  public void init()
  {
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_PODCASTER_BASE");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_PODCASTER_CHANNELS");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_PODCASTER_DETAIL");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_PODCASTER_LATEST");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_MY_PODCASTER_LIST");
  }

  public boolean isMyPodcaster(int paramInt, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("pid", Integer.valueOf(paramInt));
    localHashMap.put("ukey", paramString);
    paramString = DataManager.getInstance().getData("GETDB_PODCASTER_FOLLOW_INFO", null, localHashMap).getResult();
    if (paramString.getSuccess())
      return ((Boolean)paramString.getData()).booleanValue();
    return false;
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    if (paramString.equalsIgnoreCase("ADD_PODCASTER_BASE"))
      addPodcaster((UserInfo)paramObject);
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (!paramString.equalsIgnoreCase("ADD_PODCASTER_CHANNELS"))
              break;
          }
          while (paramMap == null);
          paramMap = (String)paramMap.get("id");
        }
        while ((paramMap == null) || (paramMap.equalsIgnoreCase("")));
        addPodcasterChannels(Integer.valueOf(paramMap).intValue(), (List)paramObject);
        return;
      }
      while ((paramString.equalsIgnoreCase("ADD_PODCASTER_DETAIL")) || (!paramString.equalsIgnoreCase("ADD_PODCASTER_LATEST")) || (paramMap == null));
      paramMap = (String)paramMap.get("id");
    }
    while ((paramMap == null) || (paramMap.equalsIgnoreCase("")));
    addPodcasterPrograms(Integer.valueOf(paramMap).intValue(), (List)paramObject);
  }

  public boolean removeMyPodcaster(int paramInt, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("pid", Integer.valueOf(paramInt));
    localHashMap.put("ukey", paramString);
    paramString = DataManager.getInstance().getData("DELETEDB_PODCASTER_FOLLOW_INFO", null, localHashMap).getResult();
    if (paramString.getSuccess())
      return ((Boolean)paramString.getData()).booleanValue();
    return false;
  }

  public boolean updateMyPodcasterLastestProgramId(int paramInt, String paramString, long paramLong)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("pid", Integer.valueOf(paramInt));
    localHashMap.put("ukey", paramString);
    localHashMap.put("uptime", Long.valueOf(paramLong));
    return DataManager.getInstance().getData("UPDATEDB_PODCASTER_LATEST_PROGRAME", null, localHashMap).getResult().getSuccess();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.PodcasterHelper
 * JD-Core Version:    0.6.2
 */