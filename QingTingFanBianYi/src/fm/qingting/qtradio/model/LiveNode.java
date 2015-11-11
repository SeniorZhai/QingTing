package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.fmdriver.FMManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiveNode extends Node
{
  private transient boolean hasRestored = false;
  private transient boolean hasRestoredLiveCategory = false;
  public int id = 1;
  private transient Node mLocalAttrNode;
  private List<CategoryNode> mLstContentCategoryNodes = new ArrayList();
  private List<CategoryNode> mLstLiveCategoryNodes;
  private List<CategoryNode> mLstLiveCategoryNodesToDB;
  private List<CategoryNode> mLstRegionCategoryNodes = new ArrayList();
  private CategoryNode mRadioCategoryNode;
  public transient RadioNode mRadioNode;
  public String name = "广播电台";
  public String type = "channel";

  public LiveNode()
  {
    this.nodeName = "live";
    init();
  }

  private void buildCategory()
  {
    if ((this.mLstContentCategoryNodes.size() > 0) && (this.mLstRegionCategoryNodes.size() > 0));
    List localList;
    do
    {
      return;
      localList = getLiveCategoryNodes();
    }
    while (localList == null);
    int i = 0;
    label36: if (i < localList.size())
    {
      if (!((CategoryNode)localList.get(i)).isLiveContentCategory())
        break label86;
      this.mLstContentCategoryNodes.add(localList.get(i));
    }
    while (true)
    {
      i += 1;
      break label36;
      break;
      label86: if (((CategoryNode)localList.get(i)).isLiveRegionCategory())
        this.mLstRegionCategoryNodes.add(localList.get(i));
    }
  }

  private void init()
  {
    if (FMManager.getInstance().isAvailable())
      this.mRadioNode = new RadioNode();
    this.mRadioCategoryNode = new CategoryNode();
    this.mRadioCategoryNode.parentId = this.id;
    this.mRadioCategoryNode.categoryId = 5;
    this.mRadioCategoryNode.name = "直播电台";
    this.mRadioCategoryNode.type = 0;
    this.mRadioCategoryNode.hasChild = 1;
    this.mRadioCategoryNode.parent = this;
  }

  public void connectRadioNode()
  {
    if (this.mRadioNode != null)
    {
      this.mRadioNode.parent = this;
      this.mRadioNode.restoreFromDB();
      QTLocation localQTLocation = InfoManager.getInstance().getCurrentLocation();
      if ((localQTLocation != null) && (localQTLocation.city != null))
        this.mRadioNode.restoreFromDBByCity(localQTLocation.city);
    }
  }

  public CategoryNode getCategoryNode(int paramInt)
  {
    int j = 0;
    if (this.mLstRegionCategoryNodes == null)
      getRegionCategory();
    int i;
    if (this.mLstRegionCategoryNodes != null)
    {
      i = 0;
      while (i < this.mLstRegionCategoryNodes.size())
      {
        if (((CategoryNode)this.mLstRegionCategoryNodes.get(i)).categoryId == paramInt)
          return (CategoryNode)this.mLstRegionCategoryNodes.get(i);
        i += 1;
      }
    }
    if (this.mLstLiveCategoryNodes == null)
      getLiveCategoryNodes();
    if (this.mLstLiveCategoryNodes != null)
    {
      i = j;
      while (i < this.mLstLiveCategoryNodes.size())
      {
        if (((CategoryNode)this.mLstLiveCategoryNodes.get(i)).categoryId == paramInt)
          return (CategoryNode)this.mLstLiveCategoryNodes.get(i);
        i += 1;
      }
    }
    return null;
  }

  public List<Attribute> getContentAttribute()
  {
    List localList = getLstAttributes();
    if (localList == null)
      return null;
    int i = 0;
    while (i < localList.size())
    {
      if (((Attributes)localList.get(i)).id == 91)
        return ((Attributes)localList.get(i)).mLstAttribute;
      i += 1;
    }
    return null;
  }

  public List<CategoryNode> getContentCategory()
  {
    if (this.mLstContentCategoryNodes.size() > 0)
      return this.mLstContentCategoryNodes;
    buildCategory();
    return this.mLstContentCategoryNodes;
  }

  public List<CategoryNode> getLiveCategoryNodes()
  {
    if (this.mLstLiveCategoryNodes == null)
      restoreLiveCategory();
    return this.mLstLiveCategoryNodes;
  }

  public Node getLocalCategoryNode()
  {
    if (this.mLocalAttrNode != null)
      return this.mLocalAttrNode;
    QTLocation localQTLocation = InfoManager.getInstance().getCurrentLocation();
    if (localQTLocation != null)
      setRegion(localQTLocation.region);
    return this.mLocalAttrNode;
  }

  public List<Attributes> getLstAttributes()
  {
    if (this.mRadioCategoryNode != null)
      return this.mRadioCategoryNode.getLstAttributes(true);
    return null;
  }

  public List<ChannelNode> getLstChannelByRegionAndContent(CategoryNode paramCategoryNode, Attribute paramAttribute)
  {
    if ((paramCategoryNode != null) && (paramAttribute == null))
      return paramCategoryNode.getLstChannels();
    if ((paramCategoryNode == null) && (paramAttribute != null))
      return paramAttribute.getLstChannels();
    if ((paramCategoryNode != null) && (paramAttribute != null))
    {
      paramAttribute = paramAttribute.getLstLiveChannels(true);
      paramCategoryNode = paramCategoryNode.getLstLiveChannels(true);
      if ((paramAttribute != null) && (paramCategoryNode != null))
      {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        if (i < paramAttribute.size())
        {
          int j = 0;
          while (true)
          {
            if (j < paramCategoryNode.size())
            {
              if (((ChannelNode)paramAttribute.get(i)).channelId == ((ChannelNode)paramCategoryNode.get(j)).channelId)
                localArrayList.add(paramAttribute.get(i));
            }
            else
            {
              i += 1;
              break;
            }
            j += 1;
          }
        }
        if (localArrayList.size() > 0)
          return localArrayList;
      }
    }
    return null;
  }

  public List<ChannelNode> getLstChannelByRegionAndContent(CategoryNode paramCategoryNode1, CategoryNode paramCategoryNode2)
  {
    if ((paramCategoryNode1 != null) && (paramCategoryNode2 == null))
      return paramCategoryNode1.getLstChannels();
    if ((paramCategoryNode1 == null) && (paramCategoryNode2 != null))
      return paramCategoryNode2.getLstChannels();
    if ((paramCategoryNode1 != null) && (paramCategoryNode2 != null))
    {
      paramCategoryNode2 = paramCategoryNode2.getLstLiveChannels(true);
      paramCategoryNode1 = paramCategoryNode1.getLstLiveChannels(true);
      if ((paramCategoryNode2 != null) && (paramCategoryNode1 != null))
      {
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        if (i < paramCategoryNode2.size())
        {
          int j = 0;
          while (true)
          {
            if (j < paramCategoryNode1.size())
            {
              if (((ChannelNode)paramCategoryNode2.get(i)).channelId == ((ChannelNode)paramCategoryNode1.get(j)).channelId)
                localArrayList.add(paramCategoryNode2.get(i));
            }
            else
            {
              i += 1;
              break;
            }
            j += 1;
          }
        }
        if (localArrayList.size() > 0)
          return localArrayList;
      }
    }
    return null;
  }

  public CategoryNode getRadioCategoryNode()
  {
    return this.mRadioCategoryNode;
  }

  public List<Attribute> getRegionAttribute()
  {
    int j = 0;
    List localList = getLstAttributes();
    if (localList == null)
      return null;
    int i = 0;
    while (i < localList.size())
    {
      if (((Attributes)localList.get(i)).id == 20)
      {
        if ((((Attributes)localList.get(i)).mLstAttribute != null) && (InfoManager.getInstance().disableGD()));
        while (true)
        {
          if (j < ((Attributes)localList.get(i)).mLstAttribute.size())
          {
            if (((Attribute)((Attributes)localList.get(i)).mLstAttribute.get(j)).name.equalsIgnoreCase("广东"))
              ((Attributes)localList.get(i)).mLstAttribute.remove(j);
          }
          else
            return ((Attributes)localList.get(i)).mLstAttribute;
          j += 1;
        }
      }
      i += 1;
    }
    return null;
  }

  public List<CategoryNode> getRegionCategory()
  {
    if (this.mLstRegionCategoryNodes.size() > 0)
      return this.mLstRegionCategoryNodes;
    buildCategory();
    return this.mLstRegionCategoryNodes;
  }

  public boolean isRadioCategoryNode(CategoryNode paramCategoryNode)
  {
    if (paramCategoryNode == null);
    while (paramCategoryNode.categoryId != this.mRadioCategoryNode.categoryId)
      return false;
    return true;
  }

  public boolean restoreChildFromDB()
  {
    List localList = null;
    if (this.hasRestored)
      return false;
    this.hasRestored = true;
    Object localObject = new HashMap();
    ((Map)localObject).put("parentid", Integer.valueOf(this.id));
    localObject = DataManager.getInstance().getData("getdb_category_node", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
      localList = (List)((Result)localObject).getData();
    return (localList != null) && (localList.size() > 0);
  }

  public boolean restoreLiveCategory()
  {
    List localList = null;
    if (this.hasRestoredLiveCategory)
      return false;
    this.hasRestoredLiveCategory = true;
    Object localObject = new HashMap();
    ((Map)localObject).put("parentid", Integer.valueOf(this.id));
    localObject = DataManager.getInstance().getData("getdb_category_node", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
      localList = (List)((Result)localObject).getData();
    if ((localList != null) && (localList.size() > 0))
    {
      this.mLstLiveCategoryNodes = localList;
      return true;
    }
    this.mLstLiveCategoryNodes = new ArrayList();
    return false;
  }

  public boolean saveChildToDB()
  {
    return false;
  }

  public void setRegion(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      List localList = getRegionAttribute();
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          if (((Attribute)localList.get(i)).name.contains(paramString))
            this.mLocalAttrNode = ((Node)localList.get(i));
          i += 1;
        }
      }
    }
  }

  public void updateLiveCategory(List<CategoryNode> paramList)
  {
    if (paramList == null)
      return;
    int i = 0;
    while (i < paramList.size())
    {
      ((CategoryNode)paramList.get(i)).parentId = this.id;
      i += 1;
    }
    if ((this.mLstLiveCategoryNodes == null) || (this.mLstLiveCategoryNodes.size() == 0))
      this.mLstLiveCategoryNodes = paramList;
    this.mLstLiveCategoryNodesToDB = paramList;
    paramList = new Message();
    paramList.what = 8;
    paramList.obj = this;
    InfoManager.getInstance().getDataStoreHandler().sendMessage(paramList);
  }

  public void updateLiveCategoryToDB()
  {
    if (this.mLstLiveCategoryNodesToDB != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("nodes", this.mLstLiveCategoryNodesToDB);
      localHashMap.put("parentid", Integer.valueOf(this.id));
      DataManager.getInstance().getData("updatedb_category_node", null, localHashMap);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.LiveNode
 * JD-Core Version:    0.6.2
 */