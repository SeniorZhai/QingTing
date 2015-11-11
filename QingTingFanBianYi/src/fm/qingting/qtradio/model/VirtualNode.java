package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VirtualNode extends Node
{
  public static final int FAKE_RADIO_CATEGORY = 99998;
  public static final int FAKE_RECOMMEND_CATEGORY = 99999;
  private final int FAKE_RADIO = 9999;
  private final int FAKE_RECOMMEND = 0;
  private boolean hasAddLocalCategory = false;
  private boolean hasRestored = false;
  public int id = 0;
  private CategoryNode mLocalCategoryNode;
  private List<CategoryNode> mLocalCategoryNodes;
  private List<CategoryNode> mLstCategoryNodes;
  private List<CategoryNode> mLstCategoryNodesToDB;
  public String name = "所有内容";
  public String type = "virtual";

  public VirtualNode()
  {
    this.nodeName = "virtual";
    init();
  }

  private void init()
  {
  }

  public void addLocalCategory()
  {
    int j = 0;
    String str = InfoManager.getInstance().getCurrentRegion();
    int i;
    if ((str != null) && (this.mLocalCategoryNodes != null) && (!this.hasAddLocalCategory))
      i = 0;
    while (true)
    {
      if (i < this.mLocalCategoryNodes.size())
      {
        if (str.contains(((CategoryNode)this.mLocalCategoryNodes.get(i)).name))
        {
          this.mLocalCategoryNode = ((CategoryNode)this.mLocalCategoryNodes.get(i));
          i = j;
        }
      }
      else
      {
        while (i < this.mLstCategoryNodes.size())
        {
          if (this.mLocalCategoryNode.categoryId == ((CategoryNode)this.mLstCategoryNodes.get(i)).categoryId)
          {
            this.hasAddLocalCategory = true;
            return;
          }
          i += 1;
        }
        this.mLstCategoryNodes.add(2, this.mLocalCategoryNode);
        if (this.mLstCategoryNodesToDB != null)
          updateVirtualCategory(this.mLstCategoryNodesToDB);
        InfoManager.getInstance().dispatchSubscribeEvent("RECV_LOCAL_CATEGORY");
        this.hasAddLocalCategory = true;
        return;
      }
      i += 1;
    }
  }

  public int getCatIdBySecId(int paramInt)
  {
    getLstCategoryNodes();
    if (this.mLstCategoryNodes == null);
    while (true)
    {
      return 0;
      int i = 0;
      while (i < this.mLstCategoryNodes.size())
      {
        if (((CategoryNode)this.mLstCategoryNodes.get(i)).sectionId == paramInt)
          return ((CategoryNode)this.mLstCategoryNodes.get(i)).categoryId;
        i += 1;
      }
    }
  }

  public CategoryNode getCategoryNode(int paramInt)
  {
    getLstCategoryNodes();
    if (this.mLstCategoryNodes == null)
      return null;
    int i = 0;
    while (i < this.mLstCategoryNodes.size())
    {
      if (((CategoryNode)this.mLstCategoryNodes.get(i)).sectionId == paramInt)
        return (CategoryNode)this.mLstCategoryNodes.get(i);
      i += 1;
    }
    return null;
  }

  public CategoryNode getCategoryNodeByCatid(int paramInt)
  {
    getLstCategoryNodes();
    if (this.mLstCategoryNodes == null)
      return null;
    int i = 0;
    while (i < this.mLstCategoryNodes.size())
    {
      if (((CategoryNode)this.mLstCategoryNodes.get(i)).categoryId == paramInt)
        return (CategoryNode)this.mLstCategoryNodes.get(i);
      i += 1;
    }
    return null;
  }

  public CategoryNode getLocalCategoryNode()
  {
    return this.mLocalCategoryNode;
  }

  public List<CategoryNode> getLstCategoryNodes()
  {
    if (this.mLstCategoryNodes == null)
      restoreChildFromDB();
    if ((this.mLstCategoryNodes != null) && (this.mLstCategoryNodes.size() > 0) && (((CategoryNode)this.mLstCategoryNodes.get(0)).sectionId != 0))
    {
      CategoryNode localCategoryNode = new CategoryNode();
      localCategoryNode.sectionId = 9999;
      localCategoryNode.name = "电台";
      localCategoryNode.categoryId = 99998;
      this.mLstCategoryNodes.add(0, localCategoryNode);
      localCategoryNode = new CategoryNode();
      localCategoryNode.sectionId = 0;
      localCategoryNode.categoryId = 99999;
      localCategoryNode.name = "精选";
      this.mLstCategoryNodes.add(0, localCategoryNode);
    }
    return this.mLstCategoryNodes;
  }

  public int getSecIdByCatId(int paramInt)
  {
    getLstCategoryNodes();
    if (this.mLstCategoryNodes == null);
    while (true)
    {
      return 0;
      int i = 0;
      while (i < this.mLstCategoryNodes.size())
      {
        if (((CategoryNode)this.mLstCategoryNodes.get(i)).categoryId == paramInt)
          return ((CategoryNode)this.mLstCategoryNodes.get(i)).sectionId;
        i += 1;
      }
    }
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (List)paramObject;
    if ((paramObject == null) || (paramObject.size() == 0));
    while (!paramString.equalsIgnoreCase("AVCAL"))
      return;
    if (this.mLstCategoryNodes == null)
    {
      this.mLstCategoryNodes = paramObject;
      paramObject = this.mLstCategoryNodes.iterator();
      while (paramObject.hasNext())
        ((Node)paramObject.next()).parent = this;
    }
    this.mLstCategoryNodes = paramObject;
    int i = 0;
    while (i < this.mLstCategoryNodes.size())
    {
      ((CategoryNode)this.mLstCategoryNodes.get(i)).parent = this;
      i += 1;
    }
    InfoManager.getInstance().getDataStoreHandler().sendEmptyMessage(8);
    InfoManager.getInstance().root().mContentCategory.updateVirtualCatNodes(this.mLstCategoryNodes);
  }

  public boolean restoreChildFromDB()
  {
    List localList = null;
    int i = 0;
    if (this.hasRestored);
    do
    {
      return false;
      this.hasRestored = true;
      Object localObject = new HashMap();
      ((Map)localObject).put("parentid", Integer.valueOf(this.id));
      localObject = DataManager.getInstance().getData("getdb_category_node", null, (Map)localObject).getResult();
      if (((Result)localObject).getSuccess())
        localList = (List)((Result)localObject).getData();
    }
    while ((localList == null) || (localList.size() <= 0));
    this.mLstCategoryNodes = localList;
    while (i < this.mLstCategoryNodes.size())
    {
      ((CategoryNode)this.mLstCategoryNodes.get(i)).parent = this;
      i += 1;
    }
    return true;
  }

  public void setLstLocalCategory(List<CategoryNode> paramList)
  {
    this.mLocalCategoryNodes = paramList;
    addLocalCategory();
  }

  public void updateVirtualCategory(List<CategoryNode> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    this.mLstCategoryNodesToDB = paramList;
    if (this.mLocalCategoryNode != null)
      this.mLstCategoryNodesToDB.add(0, this.mLocalCategoryNode);
    paramList = new Message();
    paramList.what = 1;
    paramList.obj = this;
    InfoManager.getInstance().getDataStoreHandler().sendMessage(paramList);
  }

  public void updateVirtualCategoryToDB()
  {
    if ((this.mLstCategoryNodesToDB != null) && (this.mLstCategoryNodesToDB.size() > 0))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("nodes", this.mLstCategoryNodesToDB);
      localHashMap.put("parentid", Integer.valueOf(this.id));
      DataManager.getInstance().getData("updatedb_category_node", null, localHashMap);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.VirtualNode
 * JD-Core Version:    0.6.2
 */