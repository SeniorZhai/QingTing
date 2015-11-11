package fm.qingting.qtradio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramTopicInfoNode extends Node
{
  private Map<String, List<Node>> mapProgramTopics = new HashMap();

  public ProgramTopicInfoNode()
  {
    this.nodeName = "programtopicinfo";
  }

  private Node getCurrentProgramTopicById(String paramString1, String paramString2, long paramLong)
  {
    Object localObject3 = null;
    Object localObject1 = null;
    Object localObject2 = localObject1;
    if (paramString1 != null)
    {
      if (paramString2 != null)
        break label25;
      localObject2 = localObject1;
    }
    label25: int i;
    List localList;
    int j;
    do
    {
      do
      {
        do
        {
          return localObject2;
          i = -1;
          localObject2 = localObject1;
        }
        while (!this.mapProgramTopics.containsKey(paramString1));
        localList = (List)this.mapProgramTopics.get(paramString1);
        localObject2 = localObject1;
      }
      while (localList == null);
      j = 0;
      localObject1 = localObject3;
      localObject2 = localObject1;
    }
    while (j >= localList.size());
    localObject2 = (Node)localList.get(j);
    if (((ProgramTopicNode)localObject2).isValid(paramString1, paramString2, paramLong))
    {
      int k = ((ProgramTopicNode)localObject2).getScore();
      if (k > i)
      {
        localObject1 = localObject2;
        i = k;
      }
    }
    while (true)
    {
      j += 1;
      break;
    }
  }

  private void updateProgramTopic(List<Node> paramList, String paramString)
  {
    if ((paramString == null) || (paramList == null));
    do
    {
      return;
      if (!this.mapProgramTopics.containsKey(paramString))
        break;
      paramString = (List)this.mapProgramTopics.get(paramString);
    }
    while (paramString == null);
    int i = 0;
    label43: ProgramTopicNode localProgramTopicNode;
    int j;
    if (i < paramList.size())
    {
      localProgramTopicNode = (ProgramTopicNode)paramList.get(i);
      j = 0;
    }
    while (true)
    {
      if ((j >= paramString.size()) || (((ProgramTopicNode)paramString.get(j)).updateTopicInfo(localProgramTopicNode)))
      {
        if (j == paramString.size())
          paramString.add(localProgramTopicNode.cloneNode());
        i += 1;
        break label43;
        break;
      }
      j += 1;
    }
    this.mapProgramTopics.put(paramString, paramList);
  }

  public Node getCurrentProgramTopic(String paramString1, String paramString2, long paramLong)
  {
    if ((paramString1 == null) || (paramString2 == null));
    do
    {
      return null;
      paramString1 = getCurrentProgramTopicById(paramString2, paramString1, paramLong);
    }
    while (paramString1 == null);
    return paramString1;
  }

  public boolean isExist(String paramString)
  {
    return this.mapProgramTopics.containsKey(paramString);
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (List)paramObject;
    if ((paramObject == null) || (paramString == null));
    while ((!paramString.equalsIgnoreCase("ACPT")) || (paramObject.size() == 0))
      return;
    updateProgramTopic(paramObject, ((ProgramTopicNode)paramObject.get(0)).channelId);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramTopicInfoNode
 * JD-Core Version:    0.6.2
 */