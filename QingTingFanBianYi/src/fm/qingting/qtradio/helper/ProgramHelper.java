package fm.qingting.qtradio.helper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramSchedule;
import fm.qingting.qtradio.model.ProgramScheduleList;
import fm.qingting.qtradio.model.RootNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramHelper extends Node
{
  static final String TAG = "ProgramHelper";
  private static ProgramHelper _instance = null;
  private Map<Integer, ProgramScheduleList[]> mapProgramNodes = new HashMap();
  public Map<Integer, Boolean> mapUpdatePrograms = new HashMap();
  public transient ProgramNode programNodeTemp;

  private ProgramHelper()
  {
    this.nodeName = "programhelper";
  }

  private boolean addProgramSchedule(ProgramScheduleList paramProgramScheduleList, Map<String, String> paramMap)
  {
    if (paramProgramScheduleList == null);
    int j;
    int k;
    int m;
    int n;
    do
    {
      do
      {
        return false;
        if (paramMap == null)
        {
          Log.e("ProgramHelper", "sym:mapParam == null，这是什么鬼？!");
          return false;
        }
        localObject = (String)paramMap.get("id");
      }
      while (localObject == null);
      j = Integer.valueOf((String)localObject).intValue();
      paramProgramScheduleList.setChannelId(j);
      k = Integer.valueOf((String)paramMap.get("order")).intValue();
      m = Integer.valueOf((String)paramMap.get("page")).intValue();
      n = Integer.valueOf((String)paramMap.get("pagesize")).intValue();
      localObject = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(j));
      Log.d("ProgramHelper", "sym:ProgramHelper更新缓存");
      if ((localObject == null) || (localObject[k] == null) || (localObject[k].type != 1))
        break;
      paramMap = paramProgramScheduleList.getLstProgramNode(0);
    }
    while ((paramMap == null) || (paramMap.size() == 0));
    int i = 0;
    while (i < paramMap.size())
    {
      ((ProgramNode)paramMap.get(i)).channelId = j;
      i += 1;
    }
    Object localObject = localObject[k].getLstProgramNode(0);
    i = ((List)localObject).size() / n;
    if ((((List)localObject).size() == 0) || (i == 0))
      return setProgramSchedule(j, k, paramProgramScheduleList);
    ProgramNode localProgramNode;
    if (m == 1)
    {
      Log.d("ProgramHelper", "sym:下拉刷新专辑id=" + j);
      localProgramNode = (ProgramNode)((List)localObject).get(0);
      i = 0;
      label305: if (i >= paramMap.size())
        break label981;
      if (((ProgramNode)paramMap.get(i)).uniqueId != localProgramNode.uniqueId);
    }
    while (true)
    {
      if (i == -1)
      {
        return setProgramSchedule(j, k, paramProgramScheduleList);
        i += 1;
        break label305;
      }
      Log.d("ProgramHelper", "sym:更新" + i + "条");
      i = 0;
      if (i < paramMap.size())
      {
        j = 0;
        label411: if ((j >= ((List)localObject).size()) || (j >= paramMap.size()))
          break label975;
        if (((ProgramNode)paramMap.get(i)).uniqueId != ((ProgramNode)((List)localObject).get(j)).uniqueId);
      }
      label573: label704: label969: label975: for (j = 1; ; j = 0)
      {
        if (j == 0)
        {
          if (i >= ((List)localObject).size())
            break label573;
          paramProgramScheduleList = (ProgramNode)paramMap.get(i);
          localProgramNode = (ProgramNode)((List)localObject).get(i);
          paramProgramScheduleList.nextSibling = localProgramNode;
          paramProgramScheduleList.prevSibling = localProgramNode.prevSibling;
          if (localProgramNode.prevSibling != null)
            localProgramNode.prevSibling.nextSibling = paramProgramScheduleList;
          localProgramNode.prevSibling = paramProgramScheduleList;
          ((List)localObject).add(i, paramProgramScheduleList);
        }
        while (true)
        {
          i += 1;
          break;
          j += 1;
          break label411;
          paramProgramScheduleList = (ProgramNode)paramMap.get(i);
          localProgramNode = (ProgramNode)((List)localObject).get(((List)localObject).size() - 1);
          paramProgramScheduleList.nextSibling = null;
          localProgramNode.nextSibling = paramProgramScheduleList;
          paramProgramScheduleList.prevSibling = localProgramNode;
          ((List)localObject).add(localProgramNode);
        }
        if (m > i)
        {
          Log.d("ProgramHelper", String.format("sym:加载更多,id=%d,page=%d,order=%d", new Object[] { Integer.valueOf(j), Integer.valueOf(m), Integer.valueOf(k) }));
          paramProgramScheduleList = (ProgramNode)((List)localObject).get(((List)localObject).size() - 1);
          if (paramProgramScheduleList == null)
            break label969;
          i = 0;
          if (i >= paramMap.size())
            break label969;
          if (((ProgramNode)paramMap.get(i)).uniqueId != paramProgramScheduleList.uniqueId);
        }
        while (true)
        {
          if (i == -1)
          {
            Log.d("ProgramHelper", "sym:节目单无重合");
            paramProgramScheduleList = (Node)paramMap.get(0);
            paramProgramScheduleList.prevSibling = ((Node)((List)localObject).get(((List)localObject).size() - 1));
            ((ProgramNode)((List)localObject).get(((List)localObject).size() - 1)).nextSibling = paramProgramScheduleList;
            ((List)localObject).addAll(paramMap);
          }
          while (true)
          {
            return true;
            i += 1;
            break label704;
            i += 1;
            Log.d("ProgramHelper", "sym:节目单重合长度" + i);
            if (i >= paramMap.size())
              break;
            paramProgramScheduleList = (ProgramNode)((List)localObject).get(((List)localObject).size() - 1);
            localProgramNode = (ProgramNode)paramMap.get(i);
            paramProgramScheduleList.nextSibling = localProgramNode;
            localProgramNode.prevSibling = paramProgramScheduleList;
            while (i < paramMap.size())
            {
              ((List)localObject).add(paramMap.get(i));
              i += 1;
            }
            continue;
            setProgramSchedule(j, k, paramProgramScheduleList);
            continue;
            setProgramSchedule(j, k, paramProgramScheduleList);
          }
          i = -1;
        }
      }
      label981: i = -1;
    }
  }

  private boolean allowReadCache(int paramInt)
  {
    return !InfoManager.getInstance().hasConnectedNetwork();
  }

  public static ProgramHelper getInstance()
  {
    if (_instance == null)
      _instance = new ProgramHelper();
    return _instance;
  }

  private boolean restoreProgramSchedule(int paramInt1, int paramInt2)
  {
    Log.d("ProgramHelper", String.format("sym:尝试加载节目单数据库缓存，id=%d，order=%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
    Object localObject2 = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(paramInt1));
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new ProgramScheduleList[2];
      this.mapProgramNodes.put(Integer.valueOf(paramInt1), localObject1);
    }
    localObject2 = new HashMap();
    ((Map)localObject2).put("id", Integer.valueOf(paramInt1));
    if (paramInt2 == 0)
    {
      localObject2 = DataManager.getInstance().getData("getdb_program_node", null, (Map)localObject2);
      localObject2 = ((IResultToken)localObject2).getResult();
      if ((localObject2 == null) || (!((Result)localObject2).getSuccess()))
        break label319;
    }
    label319: for (localObject2 = (List)((Result)localObject2).getData(); ; localObject2 = null)
    {
      if ((localObject2 == null) || (((List)localObject2).size() == 0))
      {
        return false;
        localObject2 = DataManager.getInstance().getData("getdb_program_node_rev", null, (Map)localObject2);
        break;
      }
      ProgramScheduleList localProgramScheduleList = new ProgramScheduleList(1);
      localProgramScheduleList.channelId = ((ProgramNode)((List)localObject2).get(0)).channelId;
      ProgramSchedule localProgramSchedule = new ProgramSchedule();
      localProgramSchedule.mLstProgramNodes = ((List)localObject2);
      localProgramSchedule.dayOfWeek = 0;
      localProgramScheduleList.mLstProgramsScheduleNodes.add(localProgramSchedule);
      localObject1[paramInt2] = localProgramScheduleList;
      paramInt1 = 0;
      localObject1 = null;
      while (paramInt1 < ((List)localObject2).size())
      {
        if (localObject1 != null)
        {
          ((Node)localObject1).nextSibling = ((Node)((List)localObject2).get(paramInt1));
          ((ProgramNode)((List)localObject2).get(paramInt1)).prevSibling = ((Node)localObject1);
        }
        localObject1 = (Node)((List)localObject2).get(paramInt1);
        paramInt1 += 1;
      }
      return true;
    }
  }

  private boolean setProgramSchedule(int paramInt1, int paramInt2, Node paramNode)
  {
    if (paramNode == null)
      return false;
    Object localObject2 = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(paramInt1));
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new ProgramScheduleList[2];
      this.mapProgramNodes.put(Integer.valueOf(paramInt1), localObject1);
    }
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      localObject1 = localObject1[paramInt2];
      if ((localObject1 != null) || (((ProgramNode)paramNode).channelType != 1))
        break label195;
      localObject1 = new ProgramScheduleList(1);
    }
    label195: 
    while (true)
    {
      if ((localObject1 != null) && (((ProgramScheduleList)localObject1).type == 1))
        ((ProgramScheduleList)localObject1).addProgramNode((ProgramNode)paramNode);
      while (true)
      {
        return true;
        if (paramNode.nodeName.equalsIgnoreCase("programschedulelist"))
        {
          localObject2 = (ProgramScheduleList)paramNode;
          ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
          if ((localChannelNode != null) && (localChannelNode.channelId == paramInt1))
          {
            Log.d("ProgramHelper", "sym:设置当前播放的节目单");
            localChannelNode.setProgramScheduleList((ProgramScheduleList)paramNode);
          }
          localObject1[paramInt2] = localObject2;
        }
      }
    }
  }

  private void udpateToDB(int paramInt1, int paramInt2)
  {
    ProgramScheduleList[] arrayOfProgramScheduleList = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(paramInt1));
    if ((arrayOfProgramScheduleList == null) || (arrayOfProgramScheduleList[paramInt2] == null) || (arrayOfProgramScheduleList[paramInt2].channelId == 0) || (arrayOfProgramScheduleList[paramInt2].type == 0))
      return;
    Message localMessage = new Message();
    localMessage.what = 5;
    localMessage.obj = arrayOfProgramScheduleList[paramInt2];
    localMessage.arg1 = paramInt2;
    InfoManager.getInstance().getDataStoreHandler().sendMessage(localMessage);
  }

  public ProgramScheduleList getProgramSchedule(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = InfoManager.getInstance().root().getProgramListOrder(paramInt1);
    Log.d("ProgramHelper", String.format("sym:获取节目单id=%d，order=%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(i) }));
    ProgramScheduleList[] arrayOfProgramScheduleList = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(paramInt1));
    if ((arrayOfProgramScheduleList == null) && (paramInt2 == 1) && ((allowReadCache(paramInt1)) || (paramBoolean)) && (restoreProgramSchedule(paramInt1, i)))
      arrayOfProgramScheduleList = (ProgramScheduleList[])this.mapProgramNodes.get(Integer.valueOf(paramInt1));
    for (ProgramScheduleList localProgramScheduleList = arrayOfProgramScheduleList[i]; ; localProgramScheduleList = null)
    {
      if (arrayOfProgramScheduleList != null)
        localProgramScheduleList = arrayOfProgramScheduleList[i];
      return localProgramScheduleList;
    }
  }

  public ProgramNode getProgramTempNode()
  {
    if (this.programNodeTemp != null)
      return this.programNodeTemp;
    this.programNodeTemp = new ProgramNode();
    this.programNodeTemp.title = "节目单加载中";
    this.programNodeTemp.startTime = "00:00";
    this.programNodeTemp.endTime = "23:59";
    this.programNodeTemp.available = false;
    this.programNodeTemp.uniqueId = 0;
    this.programNodeTemp.duration = this.programNodeTemp.getDuration();
    this.programNodeTemp.parent = this;
    return this.programNodeTemp;
  }

  public void init()
  {
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_VIRTUAL_PROGRAMS_SCHEDULE");
    InfoManager.getInstance().registerNodeEventListener(this, "ADD_LIVE_PROGRAMS_SCHEDULE");
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    int i = 0;
    paramObject = (Node)paramObject;
    if (paramObject == null);
    boolean bool;
    do
    {
      return;
      if (!paramString.equalsIgnoreCase("ADD_VIRTUAL_PROGRAMS_SCHEDULE"))
        break;
      Log.d("ProgramHelper", "sym:加载更多专辑节目单成功");
      bool = addProgramSchedule((ProgramScheduleList)paramObject, paramMap);
    }
    while ((paramObject == null) || (!bool));
    if (paramMap != null);
    for (i = Integer.valueOf((String)paramMap.get("order")).intValue(); ; i = 0)
    {
      udpateToDB(((ProgramScheduleList)paramObject).channelId, i);
      return;
      if (paramString.equalsIgnoreCase("ADD_LIVE_PROGRAMS_SCHEDULE"))
      {
        Log.d("ProgramHelper", "sym:获取到电台节目单成功");
        if (paramMap == null)
          break label236;
      }
      label236: for (i = Integer.valueOf((String)paramMap.get("id")).intValue(); ; i = 0)
      {
        paramMap = (ProgramScheduleList)paramObject;
        paramMap.channelId = i;
        setProgramSchedule(paramMap.channelId, 0, paramObject);
        return;
        if (!paramString.equalsIgnoreCase("ADD_RELOAD_VIRTUAL_PROGRAMS_SCHEDULE"))
          break;
        Log.d("ProgramHelper", "sym:重新获取专辑节目单成功");
        addProgramSchedule((ProgramScheduleList)paramObject, paramMap);
        if ((paramObject == null) || (!paramObject.nodeName.equalsIgnoreCase("programschedulelist")))
          break;
        if (paramMap != null)
          i = Integer.valueOf((String)paramMap.get("order")).intValue();
        udpateToDB(((ProgramScheduleList)paramObject).channelId, i);
        return;
      }
    }
  }

  public void udpateToDB(ProgramScheduleList paramProgramScheduleList)
  {
    int i = paramProgramScheduleList.channelId;
    udpateToDB(i, InfoManager.getInstance().root().getProgramListOrder(i));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.ProgramHelper
 * JD-Core Version:    0.6.2
 */