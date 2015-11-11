package fm.qingting.qtradio.model;

import android.util.Log;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.helper.ChannelHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayHistoryInfoNode extends Node
{
  private static final int MAX_PLAY_COUNT = 20;
  static final String TAG = "PlayHistoryInfoNode";
  private List<PlayHistoryNode> mLstPlayNodes = null;
  public String mTitle = "最近收听";
  private boolean needToWriteToDB = false;

  public PlayHistoryInfoNode()
  {
    this.nodeName = "playhistoryinfo";
  }

  private void deletePlayHistory()
  {
    DataManager.getInstance().getData("deletedb_play_history", null, null);
  }

  private int findCatIdByNode(Node paramNode)
  {
    if (paramNode == null);
    while (!paramNode.nodeName.equalsIgnoreCase("program"))
      return 0;
    return ((ProgramNode)paramNode).getCategoryId();
  }

  private String findChannelNameByNode(Node paramNode)
  {
    if (paramNode == null);
    while (!paramNode.nodeName.equalsIgnoreCase("program"))
      return null;
    return ((ProgramNode)paramNode).getChannelName();
  }

  private String findChannelThumbByNode(ProgramNode paramProgramNode)
  {
    paramProgramNode = ChannelHelper.getInstance().getChannel(paramProgramNode.channelId, paramProgramNode.channelType);
    if (paramProgramNode != null)
      return paramProgramNode.getApproximativeThumb();
    return null;
  }

  public void WriteToDB()
  {
    if (!needToWriteToDB());
    while (this.mLstPlayNodes == null)
      return;
    this.needToWriteToDB = false;
    HashMap localHashMap = new HashMap();
    localHashMap.put("playhistory", this.mLstPlayNodes);
    DataManager.getInstance().getData("delinsertdb_play_history", null, localHashMap);
  }

  public void addPlayHistoryNode(Node paramNode, String paramString)
  {
    if (paramNode == null);
    do
    {
      return;
      if (this.mLstPlayNodes == null)
        this.mLstPlayNodes = new ArrayList();
    }
    while (!paramNode.nodeName.equalsIgnoreCase("program"));
    if (this.mLstPlayNodes.size() == 20)
      this.mLstPlayNodes.remove(19);
    int i = isExisted(paramNode);
    if (i != -1)
      this.mLstPlayNodes.remove(i);
    PlayHistoryNode localPlayHistoryNode = new PlayHistoryNode();
    localPlayHistoryNode.playNode = paramNode;
    localPlayHistoryNode.playTime = (System.currentTimeMillis() / 1000L);
    localPlayHistoryNode.categoryId = findCatIdByNode(paramNode);
    localPlayHistoryNode.channelName = findChannelNameByNode(paramNode);
    localPlayHistoryNode.channelId = ((ProgramNode)paramNode).channelId;
    localPlayHistoryNode.playContent = ((ProgramNode)paramNode).channelType;
    paramNode = findChannelThumbByNode((ProgramNode)paramNode);
    if (paramNode == null);
    while (true)
    {
      localPlayHistoryNode.channelThumb = paramString;
      this.mLstPlayNodes.add(0, localPlayHistoryNode);
      this.needToWriteToDB = true;
      return;
      paramString = paramNode;
    }
  }

  public void delPlayHistoryNode(Node paramNode)
  {
    if (paramNode == null);
    while ((!paramNode.nodeName.equalsIgnoreCase("program")) && (!paramNode.nodeName.equalsIgnoreCase("ondemandprogram")))
      return;
    int i = isExisted(paramNode);
    if (i != -1)
      this.mLstPlayNodes.remove(i);
    this.needToWriteToDB = true;
  }

  public void deleteAll()
  {
    if (this.mLstPlayNodes != null)
      this.mLstPlayNodes.clear();
    deletePlayHistory();
    this.needToWriteToDB = true;
  }

  public String getLatestHistoryInfo()
  {
    Object localObject2;
    if ((this.mLstPlayNodes == null) || (this.mLstPlayNodes.size() == 0))
      localObject2 = "暂无记录，快去收听喜欢的内容吧";
    PlayHistoryNode localPlayHistoryNode;
    Object localObject1;
    do
    {
      return localObject2;
      localObject2 = "";
      localPlayHistoryNode = (PlayHistoryNode)this.mLstPlayNodes.get(0);
      localObject1 = localObject2;
      if (localPlayHistoryNode.playNode != null)
      {
        localObject1 = localObject2;
        if (localPlayHistoryNode.playNode.nodeName.equalsIgnoreCase("program"))
          localObject1 = "" + ((ProgramNode)localPlayHistoryNode.playNode).title;
      }
      localObject2 = localObject1;
    }
    while (localPlayHistoryNode.channelName == null);
    return (String)localObject1 + "-" + localPlayHistoryNode.channelName;
  }

  public List<PlayHistoryNode> getPlayHistoryNodes()
  {
    if (this.mLstPlayNodes == null)
    {
      Result localResult = DataManager.getInstance().getData("getdb_play_history", null, null).getResult();
      if (localResult.getSuccess())
        this.mLstPlayNodes = ((List)localResult.getData());
      if (this.mLstPlayNodes == null)
        this.mLstPlayNodes = new ArrayList();
    }
    return this.mLstPlayNodes;
  }

  public Node getPlayNode(int paramInt1, int paramInt2)
  {
    if (this.mLstPlayNodes != null)
    {
      int i = 0;
      while (i < this.mLstPlayNodes.size())
      {
        if ((((PlayHistoryNode)this.mLstPlayNodes.get(i)).channelId == paramInt1) && (((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode != null))
          if (((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode.nodeName.equalsIgnoreCase("program"))
          {
            if (((ProgramNode)((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode).id == paramInt2)
              return ((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode;
          }
          else if (((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode.nodeName.equalsIgnoreCase("channel"))
            return ((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode;
        i += 1;
      }
    }
    return null;
  }

  public void init()
  {
    getPlayHistoryNodes();
  }

  public int isExisted(Node paramNode)
  {
    int j;
    if (paramNode == null)
      j = -1;
    int i;
    do
    {
      return j;
      if (this.mLstPlayNodes == null)
        break label197;
      i = 0;
      if (i >= this.mLstPlayNodes.size())
        break label197;
      if (((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode != null)
        break;
      j = i;
    }
    while (((PlayHistoryNode)this.mLstPlayNodes.get(i)).channelId == ((ProgramNode)paramNode).channelId);
    label163: 
    do
    {
      do
      {
        do
        {
          i += 1;
          break;
        }
        while ((!((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode.nodeName.equalsIgnoreCase(paramNode.nodeName)) || (!paramNode.nodeName.equalsIgnoreCase("program")));
        if (!((ProgramNode)paramNode).isLiveProgram())
          break label163;
      }
      while (((PlayHistoryNode)this.mLstPlayNodes.get(i)).channelId != ((ProgramNode)paramNode).channelId);
      return i;
    }
    while (((ProgramNode)((PlayHistoryNode)this.mLstPlayNodes.get(i)).playNode).channelId != ((ProgramNode)paramNode).channelId);
    return i;
    label197: return -1;
  }

  public void mergeFromCloud(List<PlayHistoryNode> paramList)
  {
    Log.d("PlayHistoryInfoNode", "sym:开始合并播放历史记录");
    if ((paramList == null) || (paramList.size() == 0))
      return;
    if (this.mLstPlayNodes == null)
      this.mLstPlayNodes = new ArrayList();
    int i;
    label101: int k;
    int j;
    label135: int m;
    if (this.mLstPlayNodes.size() == 0)
    {
      Log.d("PlayHistoryInfoNode", "sym:本地无记录，添加" + paramList.size() + "条播放记录");
      this.mLstPlayNodes.addAll(paramList);
      i = 1;
      if (i != 0)
        this.needToWriteToDB = true;
    }
    else if (this.mLstPlayNodes.size() < 20)
    {
      k = 0;
      j = 0;
      i = 0;
      if (k >= paramList.size())
        break label351;
      PlayHistoryNode localPlayHistoryNode1 = (PlayHistoryNode)paramList.get(k);
      m = 0;
      label161: if (m >= this.mLstPlayNodes.size())
        break label345;
      PlayHistoryNode localPlayHistoryNode2 = (PlayHistoryNode)this.mLstPlayNodes.get(m);
      if (localPlayHistoryNode1.channelId == localPlayHistoryNode2.channelId)
      {
        m = 1;
        label204: if (m != 0)
          break label330;
        this.mLstPlayNodes.add(localPlayHistoryNode1);
        m = j + 1;
        if (this.mLstPlayNodes.size() < 20)
          break label287;
        i = 1;
      }
    }
    while (true)
    {
      Log.d("PlayHistoryInfoNode", "sym:本地有记录，添加" + m + "条播放记录");
      break label101;
      m += 1;
      break label161;
      label287: j = 1;
      i = m;
      while (true)
      {
        k += 1;
        m = j;
        j = i;
        i = m;
        break label135;
        Log.d("PlayHistoryInfoNode", "sym:本地记录已满，添加0条播放记录");
        i = 0;
        break label101;
        break;
        label330: m = i;
        i = j;
        j = m;
      }
      label345: m = 0;
      break label204;
      label351: m = j;
    }
  }

  public boolean needToWriteToDB()
  {
    return this.needToWriteToDB;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayHistoryInfoNode
 * JD-Core Version:    0.6.2
 */