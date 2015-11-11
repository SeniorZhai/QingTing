package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayedMetaInfo
{
  private static final int MIN_DURATION = 360;
  private static PlayedMetaInfo _instance = null;
  private transient boolean hasRestoredPlayedMeta = false;
  private List<PlayedMetaData> mLstPlayedMetaData;
  private boolean needWriteToDB = false;

  private PlayedMetaInfo()
  {
    init();
  }

  private void deletePlayedMeta(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > this.mLstPlayedMetaData.size()))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("playedMeta", this.mLstPlayedMetaData.get(paramInt));
    DataManager.getInstance().getData("deletedb_playedmeta", null, localHashMap);
    this.mLstPlayedMetaData.remove(paramInt);
  }

  private int getExistedIndex(int paramInt)
  {
    if (this.mLstPlayedMetaData != null)
    {
      int i = 0;
      while (i < this.mLstPlayedMetaData.size())
      {
        if (((PlayedMetaData)this.mLstPlayedMetaData.get(i)).programId == paramInt)
          return i;
        i += 1;
      }
    }
    return -1;
  }

  public static PlayedMetaInfo getInstance()
  {
    if (_instance == null)
      _instance = new PlayedMetaInfo();
    return _instance;
  }

  private boolean restoredPlayedMetaFromDB()
  {
    List localList = null;
    if (this.hasRestoredPlayedMeta)
      return this.hasRestoredPlayedMeta;
    this.hasRestoredPlayedMeta = true;
    Result localResult = DataManager.getInstance().getData("getdb_playedmeta", null, null).getResult();
    if (localResult.getSuccess())
      localList = (List)localResult.getData();
    if (localList != null)
      this.mLstPlayedMetaData = localList;
    if (this.mLstPlayedMetaData == null)
      this.mLstPlayedMetaData = new ArrayList();
    return true;
  }

  public boolean addPlayeMeta(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = getExistedIndex(paramInt1);
    if (paramInt1 != -1)
    {
      if (paramInt2 <= paramInt3 - 5)
        break label25;
      deletePlayedMeta(paramInt1);
    }
    while (true)
    {
      return false;
      label25: PlayedMetaData localPlayedMetaData = (PlayedMetaData)this.mLstPlayedMetaData.get(paramInt1);
      localPlayedMetaData.position = paramInt2;
      localPlayedMetaData.duration = paramInt3;
      HashMap localHashMap = new HashMap();
      localHashMap.put("playedMeta", localPlayedMetaData);
      DataManager.getInstance().getData("updatedb_playedmeta", null, localHashMap);
    }
  }

  public boolean addPlayedMeta(Node paramNode, int paramInt1, int paramInt2)
  {
    if ((paramNode == null) || (paramInt1 < 20) || (paramInt1 > paramInt2) || (paramInt2 < 360));
    int i;
    do
    {
      do
        return false;
      while ((!paramNode.nodeName.equalsIgnoreCase("program")) || (this.mLstPlayedMetaData == null) || (((ProgramNode)paramNode).channelType == 0));
      i = ((ProgramNode)paramNode).uniqueId;
      if (paramInt1 > paramInt2 - 5)
      {
        deletePlayedMetaById(i);
        return false;
      }
      i = getExistedIndex(i);
      if (i == -1)
        break;
    }
    while (((PlayedMetaData)this.mLstPlayedMetaData.get(i)).position == paramInt1);
    ((PlayedMetaData)this.mLstPlayedMetaData.get(i)).playedTime = (System.currentTimeMillis() / 1000L);
    ((PlayedMetaData)this.mLstPlayedMetaData.get(i)).position = paramInt1;
    paramNode = new HashMap();
    paramNode.put("playedMeta", this.mLstPlayedMetaData.get(i));
    DataManager.getInstance().getData("updatedb_playedmeta", null, paramNode);
    return false;
    PlayedMetaData localPlayedMetaData = new PlayedMetaData();
    localPlayedMetaData.uniqueId = ((ProgramNode)paramNode).uniqueId;
    localPlayedMetaData.channelId = ((ProgramNode)paramNode).channelId;
    localPlayedMetaData.programId = ((ProgramNode)paramNode).uniqueId;
    localPlayedMetaData.playedTime = (System.currentTimeMillis() / 1000L);
    localPlayedMetaData.position = paramInt1;
    localPlayedMetaData.duration = paramInt2;
    this.mLstPlayedMetaData.add(localPlayedMetaData);
    paramNode = new HashMap();
    paramNode.put("playedMeta", localPlayedMetaData);
    DataManager.getInstance().getData("insertdb_playedmeta", null, paramNode);
    return false;
  }

  public PlayedMetaData deletePlayedMetaById(int paramInt)
  {
    paramInt = getExistedIndex(paramInt);
    if (paramInt != -1)
      deletePlayedMeta(paramInt);
    return null;
  }

  public PlayedMetaData getPlayedMeta(int paramInt)
  {
    paramInt = getExistedIndex(paramInt);
    if (paramInt != -1)
      return (PlayedMetaData)this.mLstPlayedMetaData.get(paramInt);
    return null;
  }

  public List<PlayedMetaData> getPlayedMetadata()
  {
    if ((this.mLstPlayedMetaData == null) || (this.mLstPlayedMetaData.size() == 0))
      init();
    return this.mLstPlayedMetaData;
  }

  public void init()
  {
    restoredPlayedMetaFromDB();
  }

  public boolean needWriteToDB()
  {
    return this.needWriteToDB;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayedMetaInfo
 * JD-Core Version:    0.6.2
 */