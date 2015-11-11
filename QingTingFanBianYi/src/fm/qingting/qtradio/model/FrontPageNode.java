package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrontPageNode extends Node
{
  private boolean hasRestoredFromDB = false;
  public transient boolean mPaging = false;
  private List<RecommendItemNode> mRecommendListRecommendItemNodes = new ArrayList();
  private List<RecommendItemNode> mShowListRecommendItemNodes = new ArrayList();
  private String mTitle;

  public FrontPageNode()
  {
    this.nodeName = "frontpage";
  }

  public void addToRecommendList(RecommendItemNode paramRecommendItemNode)
  {
    this.mRecommendListRecommendItemNodes.add(paramRecommendItemNode);
  }

  public void addToShowList(RecommendItemNode paramRecommendItemNode)
  {
    this.mShowListRecommendItemNodes.add(paramRecommendItemNode);
  }

  public List<RecommendItemNode> getRecommendList()
  {
    return this.mRecommendListRecommendItemNodes;
  }

  public List<RecommendItemNode> getShowList()
  {
    return this.mShowListRecommendItemNodes;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public void release()
  {
    if (this.mRecommendListRecommendItemNodes != null)
      this.mRecommendListRecommendItemNodes.clear();
    if (this.mShowListRecommendItemNodes != null)
      this.mShowListRecommendItemNodes.clear();
  }

  public boolean restoreRecommendItemNodeFromDB()
  {
    if (this.hasRestoredFromDB)
      return true;
    this.hasRestoredFromDB = true;
    FrontPageNode localFrontPageNode = (FrontPageNode)DataManager.getInstance().getData("getdb_frontpage_node", null, null).getResult().getData();
    if (localFrontPageNode != null)
    {
      if (this.mRecommendListRecommendItemNodes.size() == 0)
        this.mRecommendListRecommendItemNodes = localFrontPageNode.mRecommendListRecommendItemNodes;
      if (this.mShowListRecommendItemNodes.size() == 0)
        this.mShowListRecommendItemNodes = localFrontPageNode.mShowListRecommendItemNodes;
      return true;
    }
    return false;
  }

  public void saveRecommendItemNodeToDB()
  {
    if (this.mShowListRecommendItemNodes.size() == 0)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("banner", this.mRecommendListRecommendItemNodes);
    if (this.mShowListRecommendItemNodes.size() > 10)
    {
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (i < 10)
      {
        localArrayList.add(this.mShowListRecommendItemNodes.get(i));
        i += 1;
      }
      localHashMap.put("showlist", localArrayList);
    }
    while (true)
    {
      DataManager.getInstance().getData("delinsertdb_frontpage_node", null, localHashMap).getResult();
      return;
      localHashMap.put("showlist", this.mShowListRecommendItemNodes);
    }
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.FrontPageNode
 * JD-Core Version:    0.6.2
 */