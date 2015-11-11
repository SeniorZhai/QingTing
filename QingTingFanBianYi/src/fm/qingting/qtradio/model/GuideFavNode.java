package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuideFavNode extends Node
{
  private transient boolean hasRestoredCategory = false;
  private transient boolean hasRestoredSuccess = false;
  private List<Node> mLstGuideFavNodes;

  public GuideFavNode()
  {
    this.nodeName = "guidefav";
  }

  public List<Node> getGuideFavLst()
  {
    return this.mLstGuideFavNodes;
  }

  public void init()
  {
    restoreFromDB();
  }

  public boolean restoreFromDB()
  {
    int i = 0;
    List localList = null;
    if (this.hasRestoredCategory)
      return this.hasRestoredSuccess;
    this.hasRestoredCategory = true;
    Result localResult = DataManager.getInstance().getData("getdb_allfav_node", null, null).getResult();
    if (localResult.getSuccess())
      localList = (List)localResult.getData();
    if ((localList != null) && (localList.size() > 0))
    {
      this.mLstGuideFavNodes = localList;
      while (i < this.mLstGuideFavNodes.size())
      {
        ((Node)this.mLstGuideFavNodes.get(i)).parent = this;
        i += 1;
      }
    }
    for (this.hasRestoredSuccess = true; ; this.hasRestoredSuccess = false)
      return this.hasRestoredSuccess;
  }

  public void setGuideFavLst(List<Node> paramList)
  {
    if (paramList == null)
      return;
    this.mLstGuideFavNodes = paramList;
    paramList = new Message();
    paramList.what = 13;
    paramList.obj = this;
    InfoManager.getInstance().getDataStoreHandler().sendMessage(paramList);
  }

  public void updateToDB()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", this.mLstGuideFavNodes);
    DataManager.getInstance().getData("updatedb_allfav_node", null, localHashMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.GuideFavNode
 * JD-Core Version:    0.6.2
 */