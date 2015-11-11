package fm.qingting.qtradio.model;

import android.os.Handler;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillboardNode extends Node
{
  private transient boolean hasRestoredChannelItems = false;
  private transient boolean hasRestoredChannelSuccess = false;
  private transient boolean hasRestoredProgramItems = false;
  private transient boolean hasRestoredProgramSuccess = false;
  private transient List<BillboardItemNode> mLstChannelNodes;
  private transient List<BillboardItemNode> mLstProgramNodes;

  public BillboardNode()
  {
    this.nodeName = "billboard";
  }

  public List<BillboardItemNode> getLstBillboardChannel()
  {
    return this.mLstChannelNodes;
  }

  public List<BillboardItemNode> getLstBillboardProgram()
  {
    return this.mLstProgramNodes;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    if (paramString == null);
    do
    {
      do
      {
        do
        {
          return;
          if (!paramString.equalsIgnoreCase("ABCS"))
            break;
          paramObject = (List)paramObject;
        }
        while ((paramObject == null) || (paramObject.size() == 0));
        this.mLstChannelNodes = paramObject;
        i = 0;
        while (i < this.mLstChannelNodes.size())
        {
          ((BillboardItemNode)this.mLstChannelNodes.get(i)).parent = this;
          i += 1;
        }
        paramObject = new Message();
        paramObject.what = 1;
        paramObject.obj = this;
        InfoManager.getInstance().getDataStoreHandler().sendMessage(paramObject);
        return;
      }
      while (!paramString.equalsIgnoreCase("ABPS"));
      paramObject = (List)paramObject;
    }
    while ((paramObject == null) || (paramObject.size() == 0));
    this.mLstProgramNodes = paramObject;
    int i = 0;
    while (i < this.mLstProgramNodes.size())
    {
      ((BillboardItemNode)this.mLstProgramNodes.get(i)).parent = this;
      i += 1;
    }
    paramObject = new Message();
    paramObject.what = 2;
    paramObject.obj = this;
    InfoManager.getInstance().getDataStoreHandler().sendMessage(paramObject);
  }

  public boolean restoreChannelFromDB()
  {
    List localList = null;
    int i = 0;
    boolean bool2 = false;
    boolean bool1;
    if (this.hasRestoredChannelItems)
      bool1 = this.hasRestoredChannelSuccess;
    do
    {
      do
      {
        return bool1;
        this.hasRestoredChannelItems = true;
        Object localObject = new HashMap();
        ((Map)localObject).put("type", Integer.valueOf(1));
        localObject = DataManager.getInstance().getData("getdb_billboarditem", null, (Map)localObject).getResult();
        if (((Result)localObject).getSuccess())
          localList = (List)((Result)localObject).getData();
        if ((localList != null) && (localList.size() != 0))
        {
          this.hasRestoredChannelSuccess = true;
          this.mLstChannelNodes = localList;
        }
        bool1 = bool2;
      }
      while (this.mLstChannelNodes == null);
      bool1 = bool2;
    }
    while (this.mLstChannelNodes.size() == 0);
    while (i < this.mLstChannelNodes.size())
    {
      ((BillboardItemNode)this.mLstChannelNodes.get(i)).parent = this;
      i += 1;
    }
    return true;
  }

  public boolean restoreProgramFromDB()
  {
    List localList = null;
    int i = 0;
    boolean bool2 = false;
    boolean bool1;
    if (this.hasRestoredProgramItems)
      bool1 = this.hasRestoredProgramSuccess;
    do
    {
      do
      {
        return bool1;
        this.hasRestoredProgramItems = true;
        Object localObject = new HashMap();
        ((Map)localObject).put("type", Integer.valueOf(2));
        localObject = DataManager.getInstance().getData("getdb_billboarditem", null, (Map)localObject).getResult();
        if (((Result)localObject).getSuccess())
          localList = (List)((Result)localObject).getData();
        if ((localList != null) && (localList.size() != 0))
        {
          this.hasRestoredProgramSuccess = true;
          this.mLstProgramNodes = localList;
        }
        bool1 = bool2;
      }
      while (this.mLstProgramNodes == null);
      bool1 = bool2;
    }
    while (this.mLstProgramNodes.size() == 0);
    while (i < this.mLstProgramNodes.size())
    {
      ((BillboardItemNode)this.mLstProgramNodes.get(i)).parent = this;
      i += 1;
    }
    return true;
  }

  public void updateToDB(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("type", Integer.valueOf(paramInt));
    if (paramInt == 1)
      localHashMap.put("nodes", this.mLstChannelNodes);
    while (true)
    {
      DataManager.getInstance().getData("updatedb_billboarditem", null, localHashMap);
      do
        return;
      while (paramInt != 2);
      localHashMap.put("nodes", this.mLstProgramNodes);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.BillboardNode
 * JD-Core Version:    0.6.2
 */