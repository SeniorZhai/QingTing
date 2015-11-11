package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.utils.QTMSGManage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareInfoNode extends Node
{
  public static transient int SHARE_CATEGORY_ID = 72;
  private int RecommendPos = 0;
  private int ShareCategoryId = 72;
  private String ShareName = "share";
  private transient boolean hasRestored = false;
  private transient boolean hasRestoredSucc = false;
  private transient boolean hasUpdated = false;
  private RecommendCategoryNode mRecommendCategoryNode;
  private RecommendItemNode mRecommendItemNode;

  public ShareInfoNode()
  {
    this.nodeName = "shareinfo";
  }

  private boolean isEqual(RecommendItemNode paramRecommendItemNode)
  {
    if (paramRecommendItemNode == null);
    String str1;
    String str2;
    do
    {
      do
        return false;
      while (this.mRecommendItemNode == null);
      str1 = this.mRecommendItemNode.getApproximativeThumb();
      str2 = paramRecommendItemNode.getApproximativeThumb();
    }
    while ((str1 == null) || (str2 == null) || (!str1.equalsIgnoreCase(str2)) || (this.mRecommendItemNode.name == null) || (paramRecommendItemNode.name == null) || (!this.mRecommendItemNode.name.equalsIgnoreCase(paramRecommendItemNode.name)));
    return true;
  }

  public RecommendItemNode getRecommendItemNode()
  {
    if (this.mRecommendItemNode == null)
      restoreFromDB();
    return this.mRecommendItemNode;
  }

  public boolean hasUpdate()
  {
    return this.hasUpdated;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (Node)paramObject;
    if ((paramObject == null) || (paramString == null));
    while ((!paramString.equalsIgnoreCase("ASIN")) || (!paramObject.nodeName.equalsIgnoreCase("recommenditem")))
      return;
    if ((!isEqual((RecommendItemNode)paramObject)) && (((RecommendItemNode)paramObject).getApproximativeThumb() != null) && (((RecommendItemNode)paramObject).name != null))
    {
      this.mRecommendItemNode = ((RecommendItemNode)paramObject);
      QTMSGManage.getInstance().sendStatistcsMessage("ShareInfo", "getShareInfoUpdated");
      if (this.mRecommendCategoryNode == null)
      {
        this.mRecommendCategoryNode = new RecommendCategoryNode();
        this.mRecommendCategoryNode.sectionId = Integer.valueOf(this.ShareCategoryId).intValue();
        this.mRecommendCategoryNode.name = this.ShareName;
      }
      this.mRecommendCategoryNode.KeepNewRecommendItem(this.mRecommendItemNode, this.RecommendPos);
      this.hasUpdated = true;
      SharedCfg.getInstance().setRecommendShareUpdate(true);
      SharedCfg.getInstance().setRecommendShareUpdateTime(System.currentTimeMillis() / 1000L);
      paramObject = new Message();
      paramObject.what = 15;
      paramObject.obj = this.mRecommendCategoryNode;
      InfoManager.getInstance().getDataStoreHandler().sendMessage(paramObject);
      return;
    }
    QTMSGManage.getInstance().sendStatistcsMessage("ShareInfo", "getShareInfoNoUpdated");
    this.hasUpdated = false;
  }

  public boolean restoreFromDB()
  {
    if (this.hasRestored)
      return this.hasRestoredSucc;
    this.hasRestored = true;
    Object localObject = new HashMap();
    ((Map)localObject).put("id", Integer.valueOf(this.ShareCategoryId));
    ((Map)localObject).put("type", String.valueOf(this.RecommendPos));
    localObject = DataManager.getInstance().getData("getdb_reccategory_node", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
    {
      this.mRecommendCategoryNode = ((RecommendCategoryNode)((Result)localObject).getData());
      if (this.mRecommendCategoryNode != null)
      {
        this.mRecommendCategoryNode.sectionId = Integer.valueOf(this.ShareCategoryId).intValue();
        this.mRecommendCategoryNode.name = this.ShareName;
      }
      if ((this.mRecommendCategoryNode != null) && (this.mRecommendCategoryNode.getLstBanner() != null) && (this.mRecommendCategoryNode.getLstBanner().size() > 0))
      {
        this.mRecommendItemNode = ((RecommendItemNode)this.mRecommendCategoryNode.getLstBanner().get(0));
        this.mRecommendItemNode.parent = this.mRecommendCategoryNode;
      }
    }
    if (this.mRecommendItemNode != null)
    {
      this.hasRestoredSucc = true;
      return true;
    }
    this.hasRestoredSucc = false;
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ShareInfoNode
 * JD-Core Version:    0.6.2
 */