package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.social.MiniFavNode;
import fm.qingting.utils.LifeTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionNode extends Node
{
  private int mHasUpdateDB = 0;
  private List<ChannelNode> mLstFavouriteNodes = null;
  public String mTitle = "已收藏电台";
  private boolean needToWriteToDB = false;

  public CollectionNode()
  {
    this.nodeName = "collection";
  }

  private void deleteAllCollection()
  {
    DataManager.getInstance().getData("delete_favourite_channels", null, null);
  }

  private void updateNode(Node paramNode)
  {
    if (paramNode == null)
      break label4;
    while (true)
    {
      label4: return;
      if (paramNode.nodeName.equalsIgnoreCase("channel"))
      {
        paramNode = (ChannelNode)paramNode;
        if (this.mLstFavouriteNodes == null)
          break;
        int i = 0;
        while (i < this.mLstFavouriteNodes.size())
        {
          if (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId == paramNode.channelId)
          {
            ((ChannelNode)this.mLstFavouriteNodes.get(i)).updatePartialInfo(paramNode);
            return;
          }
          i += 1;
        }
      }
    }
  }

  public void WriteToDB()
  {
    if (!needToWriteToDB())
      return;
    this.mHasUpdateDB = ((int)(System.currentTimeMillis() / 1000L));
    updateFavouriteChannels();
    deleteAllCollection();
    this.needToWriteToDB = false;
    HashMap localHashMap = new HashMap();
    localHashMap.put("channels", this.mLstFavouriteNodes);
    localHashMap.put("total", Integer.valueOf(this.mLstFavouriteNodes.size()));
    DataManager.getInstance().getData("insert_favourite_channels", null, localHashMap);
    InfoManager.getInstance().root().setInfoUpdate(0);
  }

  public void addFavNode(Node paramNode)
  {
    addFavNode(paramNode, true);
  }

  public void addFavNode(Node paramNode, boolean paramBoolean)
  {
    if (paramNode == null);
    do
    {
      do
        return;
      while ((isExisted(paramNode)) || (!paramNode.nodeName.equalsIgnoreCase("channel")));
      paramNode = ((ChannelNode)paramNode).clone();
      paramNode.viewTime = (System.currentTimeMillis() / 1000L);
      paramNode.parent = this;
      this.mLstFavouriteNodes.add(0, paramNode);
      this.needToWriteToDB = true;
      InfoManager.getInstance().root().setInfoUpdate(0);
    }
    while (!paramBoolean);
    EventDispacthManager.getInstance().dispatchAction("showToast", "已添加" + paramNode.title + "到收藏中");
  }

  public void clear()
  {
    if (this.mLstFavouriteNodes != null)
    {
      this.mLstFavouriteNodes.clear();
      this.needToWriteToDB = true;
      InfoManager.getInstance().root().setInfoUpdate(0);
    }
  }

  public void deleteFavNode(Node paramNode)
  {
    if (paramNode == null)
      break label4;
    while (true)
    {
      label4: return;
      if (this.mLstFavouriteNodes != null)
      {
        this.needToWriteToDB = true;
        if (!paramNode.nodeName.equalsIgnoreCase("channel"))
          break;
        int j = ((ChannelNode)paramNode).channelId;
        int i = this.mLstFavouriteNodes.size() - 1;
        while (i >= 0)
        {
          if ((((ChannelNode)this.mLstFavouriteNodes.get(i)).nodeName.equalsIgnoreCase("channel")) && (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId == j))
          {
            this.mLstFavouriteNodes.remove(i);
            InfoManager.getInstance().root().setInfoUpdate(0);
            return;
          }
          i -= 1;
        }
      }
    }
  }

  public List<ChannelNode> getFavouriteNodes()
  {
    if (this.mLstFavouriteNodes == null)
    {
      this.mLstFavouriteNodes = ((List)DataManager.getInstance().getData("get_favourite_channels", null, null).getResult().getData());
      if (this.mLstFavouriteNodes == null)
        this.mLstFavouriteNodes = new ArrayList();
      int i = 0;
      while (i < this.mLstFavouriteNodes.size())
      {
        ((ChannelNode)this.mLstFavouriteNodes.get(i)).parent = this;
        i += 1;
      }
    }
    return this.mLstFavouriteNodes;
  }

  public boolean hasDelOldCollection()
  {
    if (InfoManager.getInstance().getContext() != null)
      return SharedCfg.getInstance().getHasDeleteOldCollection();
    return false;
  }

  public int hasUpdateDB()
  {
    return this.mHasUpdateDB;
  }

  public void init()
  {
    if (!LifeTime.isFirstLaunchAfterInstall)
    {
      InfoManager.getInstance().registerNodeEventListener(this, "ADD_VIRTUAL_CHANNEL_INFO");
      getFavouriteNodes();
      return;
    }
    this.mLstFavouriteNodes = new ArrayList();
  }

  public boolean isEmpty()
  {
    return this.mLstFavouriteNodes.size() <= 0;
  }

  public boolean isExisted(int paramInt)
  {
    if (this.mLstFavouriteNodes != null)
    {
      int i = this.mLstFavouriteNodes.size() - 1;
      while (i >= 0)
      {
        if (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId == paramInt)
          return true;
        i -= 1;
      }
    }
    return false;
  }

  public boolean isExisted(Node paramNode)
  {
    if (paramNode == null);
    while (this.mLstFavouriteNodes == null)
      return false;
    if (paramNode.nodeName.equalsIgnoreCase("channel"));
    for (int i = ((ChannelNode)paramNode).channelId; ; i = 0)
    {
      int j = this.mLstFavouriteNodes.size() - 1;
      while (j >= 0)
      {
        if ((((ChannelNode)this.mLstFavouriteNodes.get(j)).nodeName.equalsIgnoreCase("channel")) && (((ChannelNode)this.mLstFavouriteNodes.get(j)).channelId == i))
          return true;
        j -= 1;
      }
      break;
    }
  }

  public void mergeWithFavNodes(List<MiniFavNode> paramList)
  {
    int n = 0;
    int j = 0;
    if (paramList == null);
    while (true)
    {
      return;
      int i;
      Object localObject;
      if ((this.mLstFavouriteNodes == null) || (this.mLstFavouriteNodes.size() == 0))
      {
        if (this.mLstFavouriteNodes == null)
          this.mLstFavouriteNodes = new ArrayList();
        i = 0;
        while (j < paramList.size())
        {
          localObject = ((MiniFavNode)paramList.get(j)).covertToChannelNode();
          ((ChannelNode)localObject).parent = this;
          if (localObject != null)
          {
            this.mLstFavouriteNodes.add(localObject);
            i = 1;
          }
          j += 1;
        }
      }
      while (i != 0)
      {
        this.needToWriteToDB = true;
        return;
        localObject = new ArrayList();
        i = 0;
        while (i < paramList.size())
        {
          ChannelNode localChannelNode = ((MiniFavNode)paramList.get(i)).covertToChannelNode();
          localChannelNode.parent = this;
          ((List)localObject).add(localChannelNode);
          i += 1;
        }
        i = 0;
        j = 0;
        if (i < this.mLstFavouriteNodes.size())
        {
          int k = 0;
          while (true)
          {
            int m = j;
            if (k < ((List)localObject).size())
            {
              if (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId != ((ChannelNode)((List)localObject).get(k)).channelId)
                break label299;
              m = j + 1;
            }
            label299: 
            do
            {
              if (k >= ((List)localObject).size())
                ((List)localObject).add(this.mLstFavouriteNodes.get(i));
              i += 1;
              j = m;
              break;
              if (((ChannelNode)this.mLstFavouriteNodes.get(i)).mViewTime == 0L)
                break label360;
              m = j;
            }
            while (((ChannelNode)this.mLstFavouriteNodes.get(i)).mViewTime < ((ChannelNode)((List)localObject).get(k)).mViewTime);
            label360: k += 1;
          }
        }
        i = n;
        if (j != ((List)localObject).size())
        {
          this.mLstFavouriteNodes.clear();
          this.mLstFavouriteNodes = ((List)localObject);
          i = 1;
        }
      }
    }
  }

  public boolean needToWriteToDB()
  {
    return this.needToWriteToDB;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (Node)paramObject;
    if ((paramObject == null) || (paramString == null));
    while ((paramString.equalsIgnoreCase("ADD_LIVE_CHANNEL_INFO")) || (!paramString.equalsIgnoreCase("ADD_VIRTUAL_CHANNEL_INFO")))
      return;
    updateNode(paramObject);
  }

  public void removeOldFavoredVirtual()
  {
    getFavouriteNodes();
    int i = this.mLstFavouriteNodes.size() - 1;
    while (i >= 0)
    {
      ChannelNode localChannelNode = (ChannelNode)this.mLstFavouriteNodes.get(i);
      if (localChannelNode.channelType == 1)
        deleteFavNode(localChannelNode);
      i -= 1;
    }
  }

  public void sortCollectionNodeByPlayOrder()
  {
    getFavouriteNodes();
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    if ((localList != null) && (this.mLstFavouriteNodes != null))
    {
      int i = 0;
      int j = 0;
      if (i < localList.size())
      {
        int m = 0;
        while (true)
        {
          int k = j;
          if (m < this.mLstFavouriteNodes.size())
          {
            k = j;
            if (j < this.mLstFavouriteNodes.size())
            {
              if (((PlayHistoryNode)localList.get(i)).channelId != ((ChannelNode)this.mLstFavouriteNodes.get(m)).channelId)
                break label206;
              if (j != m)
              {
                ChannelNode localChannelNode = (ChannelNode)this.mLstFavouriteNodes.get(m);
                this.mLstFavouriteNodes.add(j, localChannelNode);
                if (j <= m)
                  break label189;
                this.mLstFavouriteNodes.remove(m);
              }
            }
          }
          while (true)
          {
            k = j + 1;
            i += 1;
            j = k;
            break;
            label189: this.mLstFavouriteNodes.remove(m + 1);
          }
          label206: m += 1;
        }
      }
    }
  }

  public void updateChannelsInfo()
  {
    int i = 0;
    if (this.mLstFavouriteNodes != null)
    {
      int j = 0;
      while (j < this.mLstFavouriteNodes.size())
      {
        if (((ChannelNode)this.mLstFavouriteNodes.get(j)).channelType == 1)
          InfoManager.getInstance().loadVirtualChannelNode(((ChannelNode)this.mLstFavouriteNodes.get(j)).channelId, this);
        j += 1;
        i += 1;
      }
    }
  }

  public void updateFavouriteChannels()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.CollectionNode
 * JD-Core Version:    0.6.2
 */