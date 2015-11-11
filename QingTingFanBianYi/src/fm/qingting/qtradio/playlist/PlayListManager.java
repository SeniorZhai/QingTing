package fm.qingting.qtradio.playlist;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramScheduleList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayListManager
{
  private static PlayListManager _instance;
  private transient boolean hasRestoredItem = false;
  private transient boolean hasRestoredSuccess = false;
  private List<Node> mLstPlayNodes;
  private int mPlayId = 0;
  private transient boolean needToWrite = false;

  public static PlayListManager getInstance()
  {
    if (_instance == null)
      _instance = new PlayListManager();
    return _instance;
  }

  private void log(String paramString)
  {
  }

  public void asyncUpdate()
  {
    Message localMessage = new Message();
    localMessage.what = 10;
    localMessage.obj = this;
    InfoManager.getInstance().getDataStoreHandler().removeMessages(localMessage.what);
    InfoManager.getInstance().getDataStoreHandler().sendMessageDelayed(localMessage, 2000L);
  }

  public List<Node> getPlayList()
  {
    if (this.mLstPlayNodes == null)
    {
      restoreFromDB();
      if (this.mLstPlayNodes == null)
        this.mLstPlayNodes = new ArrayList();
    }
    return this.mLstPlayNodes;
  }

  public void init()
  {
    restoreFromDB();
  }

  public boolean restoreFromDB()
  {
    int i = 0;
    Object localObject2 = null;
    Object localObject1 = DataManager.getInstance().getData("getdb_playlist", null, null).getResult();
    if ((localObject1 != null) && (((Result)localObject1).getSuccess()));
    for (localObject1 = (List)((Result)localObject1).getData(); ; localObject1 = null)
    {
      if ((localObject1 == null) || (((List)localObject1).size() == 0))
        return false;
      this.mLstPlayNodes = ((List)localObject1);
      localObject1 = localObject2;
      while (i < this.mLstPlayNodes.size())
      {
        if (localObject1 != null)
        {
          ((Node)localObject1).nextSibling = ((Node)this.mLstPlayNodes.get(i));
          ((Node)this.mLstPlayNodes.get(i)).prevSibling = ((Node)localObject1);
        }
        localObject1 = (Node)this.mLstPlayNodes.get(i);
        i += 1;
      }
      return true;
    }
  }

  public void setPlayList(Node paramNode, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramNode == null);
    do
    {
      return;
      if (!paramNode.nodeName.equalsIgnoreCase("program"))
        break;
    }
    while (this.mPlayId == ((ProgramNode)paramNode).id);
    this.mPlayId = ((ProgramNode)paramNode).id;
    Object localObject1;
    while (true)
    {
      localObject1 = paramNode;
      if (paramNode.nextSibling == null)
      {
        localObject1 = paramNode;
        if (paramNode.prevSibling == null)
        {
          localObject1 = paramNode;
          if (!paramBoolean)
          {
            localObject1 = paramNode;
            if (paramNode.nodeName.equalsIgnoreCase("program"))
            {
              Object localObject2 = ProgramHelper.getInstance().getProgramSchedule(((ProgramNode)paramNode).channelId, ((ProgramNode)paramNode).channelType, true);
              localObject1 = paramNode;
              if (localObject2 != null)
              {
                localObject2 = ((ProgramScheduleList)localObject2).getProgramNode(((ProgramNode)paramNode).id);
                localObject1 = paramNode;
                if (localObject2 != null)
                  localObject1 = localObject2;
              }
            }
          }
        }
      }
      if (this.mLstPlayNodes == null)
        this.mLstPlayNodes = new ArrayList();
      this.mLstPlayNodes.clear();
      paramInt1 = 0;
      paramNode = (Node)localObject1;
      while ((paramNode != null) && (paramInt1 < paramInt2))
      {
        this.mLstPlayNodes.add(paramNode);
        paramNode = paramNode.nextSibling;
        paramInt1 += 1;
      }
      if (paramNode.nodeName.equalsIgnoreCase("channel"))
      {
        if (this.mPlayId == paramInt1)
          break;
        this.mPlayId = paramInt1;
      }
    }
    paramNode = ((Node)localObject1).prevSibling;
    paramInt1 = 0;
    while ((paramNode != null) && (paramInt1 < paramInt2))
    {
      this.mLstPlayNodes.add(0, paramNode);
      paramNode = paramNode.prevSibling;
      paramInt1 += 1;
    }
    this.needToWrite = true;
    if (paramBoolean)
    {
      updateToDB(false);
      return;
    }
    asyncUpdate();
  }

  public void updateToDB(boolean paramBoolean)
  {
    if ((this.mLstPlayNodes == null) || (!this.needToWrite));
    do
    {
      return;
      this.needToWrite = false;
      log("updateToDB: " + this.mPlayId);
      HashMap localHashMap = new HashMap();
      localHashMap.put("nodes", this.mLstPlayNodes);
      DataManager.getInstance().getData("updatedb_playlist", null, localHashMap);
    }
    while (!paramBoolean);
    InfoManager.getInstance().sendPlayList("playid", String.valueOf(this.mPlayId));
    InfoManager.getInstance().sendPlayList("playlistupdate", "true");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.playlist.PlayListManager
 * JD-Core Version:    0.6.2
 */