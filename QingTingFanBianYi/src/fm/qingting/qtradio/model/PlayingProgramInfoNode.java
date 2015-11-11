package fm.qingting.qtradio.model;

import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.social.MiniFavNode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayingProgramInfoNode extends Node
{
  private Map<Integer, Node> mapPlayingProgram = new HashMap();

  public PlayingProgramInfoNode()
  {
    this.nodeName = "playingprograminfo";
  }

  private Node getCurrentPlayingProgramById(int paramInt)
  {
    if (this.mapPlayingProgram.containsKey(Integer.valueOf(paramInt)))
    {
      Node localNode2 = (Node)this.mapPlayingProgram.get(Integer.valueOf(paramInt));
      Node localNode1 = localNode2;
      if (hasOutOfDate(localNode2))
      {
        this.mapPlayingProgram.remove(Integer.valueOf(paramInt));
        localNode1 = null;
      }
      return localNode1;
    }
    return null;
  }

  private int getRelativeTime(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(11);
    return localCalendar.get(12) * 60 + i * 60 * 60;
  }

  private boolean hasOutOfDate(Node paramNode)
  {
    if (paramNode == null)
      return true;
    if (paramNode.nodeName.equalsIgnoreCase("playingprogram"))
    {
      int i = getRelativeTime(System.currentTimeMillis());
      if ((((PlayingProgramNode)paramNode).endTime() > i) && (((PlayingProgramNode)paramNode).startTime() < i))
        return false;
    }
    return true;
  }

  private void updatePlayingProgram(int paramInt, Node paramNode)
  {
    if (paramNode == null);
    do
    {
      return;
      if (!this.mapPlayingProgram.containsKey(Integer.valueOf(paramInt)))
        break;
    }
    while (!hasOutOfDate((Node)this.mapPlayingProgram.get(Integer.valueOf(paramInt))));
    this.mapPlayingProgram.remove(Integer.valueOf(paramInt));
    this.mapPlayingProgram.put(Integer.valueOf(paramInt), paramNode);
    return;
    this.mapPlayingProgram.put(Integer.valueOf(paramInt), paramNode);
  }

  public Node getCurrentPlayingProgram(int paramInt, String paramString)
  {
    Object localObject1 = getCurrentPlayingProgramById(paramInt);
    if (localObject1 == null)
    {
      long l = System.currentTimeMillis() / 1000L;
      Object localObject2 = ProgramHelper.getInstance().getProgramSchedule(paramInt, 0, true);
      if (localObject2 != null)
      {
        localObject2 = ((ProgramScheduleList)localObject2).getProgramNodeByTime(l);
        if (localObject2 != null)
        {
          localObject1 = new PlayingProgramNode();
          ((PlayingProgramNode)localObject1).channelId = paramInt;
          ((PlayingProgramNode)localObject1).channelName = paramString;
          ((PlayingProgramNode)localObject1).programName = ((ProgramNode)localObject2).title;
          ((PlayingProgramNode)localObject1).broadcastTime = ((ProgramNode)localObject2).startTime;
          ((PlayingProgramNode)localObject1).duration = ((ProgramNode)localObject2).getDuration();
          if (((ProgramNode)localObject2).lstBroadcaster != null)
          {
            ((PlayingProgramNode)localObject1).lstbroadcastersName = new ArrayList();
            int i = 0;
            while (i < ((ProgramNode)localObject2).lstBroadcaster.size())
            {
              ((PlayingProgramNode)localObject1).lstbroadcastersName.add(((BroadcasterNode)((ProgramNode)localObject2).lstBroadcaster.get(i)).nick);
              i += 1;
            }
          }
          this.mapPlayingProgram.put(Integer.valueOf(paramInt), localObject1);
          return localObject1;
        }
      }
    }
    return localObject1;
  }

  public Node getCurrentPlayingProgram(Node paramNode)
  {
    if (paramNode == null)
      return null;
    Object localObject1;
    int j;
    Object localObject2;
    int i;
    if (paramNode.nodeName.equalsIgnoreCase("channel"))
    {
      localObject1 = (ChannelNode)paramNode;
      j = ((ChannelNode)localObject1).channelId;
      paramNode = ((ChannelNode)localObject1).title;
      localObject2 = getCurrentPlayingProgramById(j);
      if (localObject2 == null)
      {
        localObject1 = ((ChannelNode)localObject1).getProgramNodeByTime(System.currentTimeMillis());
        if ((localObject1 != null) && (((ProgramNode)localObject1).available))
        {
          localObject2 = new PlayingProgramNode();
          ((PlayingProgramNode)localObject2).channelId = j;
          ((PlayingProgramNode)localObject2).channelName = paramNode;
          ((PlayingProgramNode)localObject2).programName = ((ProgramNode)localObject1).title;
          ((PlayingProgramNode)localObject2).broadcastTime = ((ProgramNode)localObject1).startTime;
          ((PlayingProgramNode)localObject2).duration = ((ProgramNode)localObject1).getDuration();
          if (((ProgramNode)localObject1).lstBroadcaster != null)
          {
            ((PlayingProgramNode)localObject2).lstbroadcastersName = new ArrayList();
            i = 0;
            while (i < ((ProgramNode)localObject1).lstBroadcaster.size())
            {
              ((PlayingProgramNode)localObject2).lstbroadcastersName.add(((BroadcasterNode)((ProgramNode)localObject1).lstBroadcaster.get(i)).nick);
              i += 1;
            }
          }
          this.mapPlayingProgram.put(Integer.valueOf(j), localObject2);
          return localObject2;
        }
        return null;
      }
      return localObject2;
    }
    if (paramNode.nodeName.equalsIgnoreCase("minifav"))
    {
      paramNode = (MiniFavNode)paramNode;
      if (paramNode.contentType == 0)
        return getCurrentPlayingProgramById(paramNode.id);
    }
    else if (paramNode.nodeName.equalsIgnoreCase("radiochannel"))
    {
      localObject1 = (RadioChannelNode)paramNode;
      j = ((RadioChannelNode)localObject1).channelId;
      paramNode = ((RadioChannelNode)localObject1).channelName;
      localObject2 = getCurrentPlayingProgramById(j);
      if (localObject2 == null)
      {
        localObject1 = ((RadioChannelNode)localObject1).getCurrentPlayingProgramNode(System.currentTimeMillis());
        if ((localObject1 != null) && (((ProgramNode)localObject1).available))
        {
          localObject2 = new PlayingProgramNode();
          ((PlayingProgramNode)localObject2).channelId = j;
          ((PlayingProgramNode)localObject2).channelName = paramNode;
          ((PlayingProgramNode)localObject2).programName = ((ProgramNode)localObject1).title;
          ((PlayingProgramNode)localObject2).broadcastTime = ((ProgramNode)localObject1).startTime;
          ((PlayingProgramNode)localObject2).duration = ((ProgramNode)localObject1).getDuration();
          ((PlayingProgramNode)localObject2).lstbroadcastersName = new ArrayList();
          i = 0;
          while (i < ((ProgramNode)localObject1).lstBroadcaster.size())
          {
            ((PlayingProgramNode)localObject2).lstbroadcastersName.add(((BroadcasterNode)((ProgramNode)localObject1).lstBroadcaster.get(i)).nick);
            i += 1;
          }
          this.mapPlayingProgram.put(Integer.valueOf(j), localObject2);
          return localObject2;
        }
        return null;
      }
      return localObject2;
    }
    return null;
  }

  public boolean isExist(int paramInt)
  {
    return this.mapPlayingProgram.containsKey(Integer.valueOf(paramInt));
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (List)paramObject;
    if ((paramObject == null) || (paramString == null));
    while (true)
    {
      return;
      if (paramString.equalsIgnoreCase("ACPP"))
      {
        int i = 0;
        while (i < paramObject.size())
        {
          if (((Node)paramObject.get(i)).nodeName.equalsIgnoreCase("playingprogram"))
            updatePlayingProgram(((PlayingProgramNode)paramObject.get(i)).channelId, ((PlayingProgramNode)paramObject.get(i)).clone());
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayingProgramInfoNode
 * JD-Core Version:    0.6.2
 */