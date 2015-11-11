package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramScheduleList extends Node
{
  public int channelId;
  private transient boolean hasUpdated = false;
  public List<ProgramSchedule> mLstProgramsScheduleNodes = new ArrayList();
  public int type;

  public ProgramScheduleList(int paramInt)
  {
    this.nodeName = "programschedulelist";
    this.type = paramInt;
  }

  public boolean addProgramNode(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return false;
    if (this.mLstProgramsScheduleNodes.size() == 0)
    {
      ProgramSchedule localProgramSchedule = new ProgramSchedule();
      this.mLstProgramsScheduleNodes.add(localProgramSchedule);
    }
    return ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(0)).addProgramNode(paramProgramNode);
  }

  public void delProgramNode(int paramInt)
  {
    if (this.mLstProgramsScheduleNodes.size() == 0)
      return;
    ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(0)).delProgramNode(paramInt);
  }

  public List<ProgramNode> getLstProgramNode(int paramInt)
  {
    int i = 0;
    while (i < this.mLstProgramsScheduleNodes.size())
    {
      if (((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).dayOfWeek == paramInt)
        return ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes;
      i += 1;
    }
    return null;
  }

  public ProgramNode getProgramNode(int paramInt)
  {
    int i = 0;
    while (i < this.mLstProgramsScheduleNodes.size())
    {
      int j = 0;
      while (j < ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes.size())
      {
        if (((ProgramNode)((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes.get(j)).id == paramInt)
          return (ProgramNode)((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes.get(j);
        j += 1;
      }
      i += 1;
    }
    return null;
  }

  public ProgramNode getProgramNodeByTime(long paramLong)
  {
    int j = TimeUtil.getDayofWeek(paramLong);
    int i = 0;
    while (i < this.mLstProgramsScheduleNodes.size())
    {
      if (((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).dayOfWeek == j)
        return ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).getProgramNodeByTime(paramLong);
      i += 1;
    }
    return null;
  }

  public void setChannelId(int paramInt)
  {
    this.channelId = paramInt;
    int i = 0;
    while (i < this.mLstProgramsScheduleNodes.size())
    {
      int j = 0;
      while (j < ((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes.size())
      {
        ((ProgramNode)((ProgramSchedule)this.mLstProgramsScheduleNodes.get(i)).mLstProgramNodes.get(j)).channelId = paramInt;
        j += 1;
      }
      i += 1;
    }
  }

  public void updateToDB(int paramInt)
  {
    if ((this.type == 0) || (this.hasUpdated));
    List localList;
    do
    {
      return;
      localList = getLstProgramNode(0);
    }
    while ((localList == null) || (localList.size() == 0));
    this.hasUpdated = true;
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(this.channelId));
    localHashMap.put("nodes", localList);
    localHashMap.put("size", Integer.valueOf(localList.size()));
    if (paramInt == 0)
    {
      DataManager.getInstance().getData("updatedb_program_node", null, localHashMap);
      return;
    }
    DataManager.getInstance().getData("updatedb_program_node_rev", null, localHashMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramScheduleList
 * JD-Core Version:    0.6.2
 */