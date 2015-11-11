package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentCategoryNode extends Node
{
  private transient boolean hasRestored = false;
  public LiveNode mLiveNode = new LiveNode();
  private List<CategoryNode> mLstCategoryNodes = new ArrayList();
  public VirtualNode mVirtualNode = new VirtualNode();

  public ContentCategoryNode()
  {
    this.nodeName = "contentcategory";
  }

  public List<CategoryNode> getAllCategoryNodes()
  {
    return this.mLstCategoryNodes;
  }

  public boolean restoreChildFromDB()
  {
    int i = 0;
    if (this.hasRestored)
      return false;
    this.hasRestored = true;
    Object localObject = DataManager.getInstance().getData("getdb_content_category", null, null).getResult();
    if (((Result)localObject).getSuccess());
    for (localObject = (List)((Result)localObject).getData(); ; localObject = null)
    {
      if (localObject != null);
      while (true)
      {
        if (i < ((List)localObject).size())
        {
          if (((Node)((List)localObject).get(i)).nodeName.equalsIgnoreCase("live"))
          {
            this.mLiveNode.id = ((LiveNode)((List)localObject).get(i)).id;
            this.mLiveNode.restoreChildFromDB();
            this.mLiveNode.connectRadioNode();
          }
        }
        else
          return true;
        if (((Node)((List)localObject).get(i)).nodeName.equalsIgnoreCase("virtual"))
        {
          this.mVirtualNode.id = ((VirtualNode)((List)localObject).get(i)).id;
          this.mVirtualNode.restoreChildFromDB();
        }
        i += 1;
      }
    }
  }

  public boolean saveChildToDB()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.mLiveNode);
    localArrayList.add(this.mVirtualNode);
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", localArrayList);
    DataManager.getInstance().getData("insertdb_content_category", null, localHashMap);
    int i = 0;
    while (i < localArrayList.size())
    {
      ((Node)localArrayList.get(i)).saveChildToDB();
      i += 1;
    }
    return true;
  }

  public void updateVirtualCatNodes(List<CategoryNode> paramList)
  {
    this.mLstCategoryNodes.clear();
    if (this.mLiveNode.getRadioCategoryNode() != null)
      this.mLstCategoryNodes.add(this.mLiveNode.getRadioCategoryNode());
    int i = 0;
    while (i < paramList.size())
    {
      this.mLstCategoryNodes.add((CategoryNode)paramList.get(i));
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ContentCategoryNode
 * JD-Core Version:    0.6.2
 */