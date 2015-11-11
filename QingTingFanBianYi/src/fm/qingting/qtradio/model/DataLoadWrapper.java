package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import java.util.HashMap;
import java.util.Map;

public class DataLoadWrapper
{
  public static void loadLiveChannelNode(int paramInt, IResultRecvHandler paramIResultRecvHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", String.valueOf(paramInt));
    DataManager.getInstance().getData("GET_LIVE_CHANNEL_INFO", paramIResultRecvHandler, localHashMap);
  }

  public static void loadSearch(String paramString, int paramInt, IResultRecvHandler paramIResultRecvHandler)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("k", paramString);
    localHashMap.put("groups", "all");
    localHashMap.put("curpage", "1");
    localHashMap.put("pagesize", String.valueOf(paramInt));
    localHashMap.put("deviceid", InfoManager.getInstance().getDeviceId());
    DataManager.getInstance().getData("new_search", paramIResultRecvHandler, localHashMap);
  }

  public static void loadUserTids(int paramInt, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramIResultRecvHandler == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("num", String.valueOf(paramInt));
    DataManager.getInstance().getData("get_user_tids", paramIResultRecvHandler, localHashMap);
  }

  public static void loadVChannelInfo(int paramInt, IResultRecvHandler paramIResultRecvHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", String.valueOf(paramInt));
    DataManager.getInstance().getData("GET_VIRTUAL_CHANNEL_INFO", paramIResultRecvHandler, localHashMap);
  }

  public static void loadVProgramInfo(int paramInt, IResultRecvHandler paramIResultRecvHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", String.valueOf(paramInt));
    DataManager.getInstance().getData("GET_VIRTUAL_PROGRAM_INFO", paramIResultRecvHandler, localHashMap);
  }

  public static void loadVirtualProgramsScheduleNode(int paramInt1, int paramInt2, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramIResultRecvHandler == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", String.valueOf(paramInt1));
    localHashMap.put("pagesize", String.valueOf(30));
    localHashMap.put("page", String.valueOf(paramInt2));
    DataManager.getInstance().getData("GET_VIRTUAL_PROGRAM_SCHEDULE", paramIResultRecvHandler, localHashMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.DataLoadWrapper
 * JD-Core Version:    0.6.2
 */