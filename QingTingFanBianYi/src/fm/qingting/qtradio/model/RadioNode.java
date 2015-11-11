package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.fmdriver.IFMEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RadioNode extends Node
  implements IFMEventListener, InfoManager.INodeEventListener
{
  private boolean hasRefreshRadio = false;
  private boolean hasRestoreByCity = false;
  private boolean hasRestoreFromDB = false;
  public transient List<Integer> lstFreqs = new ArrayList();
  public FreqChannelInfoNode mFreqChannelInfoNode = new FreqChannelInfoNode();
  public transient List<Node> mLstChannelNodes = new ArrayList();
  public String mTitle = "系统收音机";
  private Map<String, List<IRadioEventListener>> mapSubscribeEventListeners = new HashMap();

  public RadioNode()
  {
    this.nodeName = "radio";
    init();
  }

  private void addFreq(int paramInt)
  {
    this.lstFreqs.add(Integer.valueOf(paramInt));
    delDefaultNode();
    RadioChannelNode localRadioChannelNode = new RadioChannelNode();
    localRadioChannelNode.parent = this;
    localRadioChannelNode.freq = String.valueOf(paramInt);
    localRadioChannelNode.channelName = ("FM" + String.valueOf(Float.valueOf((float)(paramInt * 0.001D))));
    this.mLstChannelNodes.add(localRadioChannelNode);
    if (this.parent == null)
      this.parent = InfoManager.getInstance().root().mContentCategory.mLiveNode;
  }

  private void arrangeRadios()
  {
    if (this.mLstChannelNodes == null);
    int i;
    int j;
    label24: RadioChannelNode localRadioChannelNode1;
    RadioChannelNode localRadioChannelNode2;
    do
    {
      return;
      i = 0;
      int m = this.mLstChannelNodes.size();
      j = 0;
      if (j >= m)
        break;
      localRadioChannelNode1 = (RadioChannelNode)this.mLstChannelNodes.get(j);
      localRadioChannelNode2 = (RadioChannelNode)this.mLstChannelNodes.get(i);
    }
    while (localRadioChannelNode1 == null);
    int k;
    if (!localRadioChannelNode1.channelName.startsWith("FM"))
    {
      if (j != i)
      {
        this.mLstChannelNodes.set(j, localRadioChannelNode2);
        this.mLstChannelNodes.set(i, localRadioChannelNode1);
      }
      j += 1;
      k = i + 1;
      i = j;
      j = k;
    }
    while (true)
    {
      k = i;
      i = j;
      j = k;
      break label24;
      break;
      k = j + 1;
      j = i;
      i = k;
    }
  }

  private void delDefaultNode()
  {
    if ((this.mLstChannelNodes == null) || (this.mLstChannelNodes.size() == 0));
    while (((RadioChannelNode)this.mLstChannelNodes.get(0)).channelId != 0)
      return;
    this.mLstChannelNodes.remove(0);
  }

  private void dispatchSubscribeEvent(String paramString)
  {
    if (this.mapSubscribeEventListeners.containsKey(paramString))
    {
      List localList = (List)this.mapSubscribeEventListeners.get(paramString);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        ((IRadioEventListener)localList.get(i)).onRadioNotification(paramString);
        i += 1;
      }
    }
  }

  private void init()
  {
    FMManager.getInstance().addListener(this);
  }

  private boolean isExisted(int paramInt)
  {
    boolean bool2 = false;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < this.lstFreqs.size())
      {
        if (paramInt == ((Integer)this.lstFreqs.get(i)).intValue())
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  private void registerRadioEventListener(IRadioEventListener paramIRadioEventListener, String paramString)
  {
    int i;
    if ((paramIRadioEventListener != null) && (paramString != null))
    {
      if (this.mapSubscribeEventListeners.containsKey(paramString))
      {
        localObject = (List)this.mapSubscribeEventListeners.get(paramString);
        i = 0;
      }
    }
    else
    {
      while (i < ((List)localObject).size())
      {
        if (((List)localObject).get(i) == paramIRadioEventListener)
          return;
        i += 1;
      }
      ((List)this.mapSubscribeEventListeners.get(paramString)).add(paramIRadioEventListener);
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(paramIRadioEventListener);
    this.mapSubscribeEventListeners.put(paramString, localObject);
  }

  public void addDefaultNode()
  {
    if ((this.mLstChannelNodes == null) || (this.mLstChannelNodes.size() > 0));
    do
    {
      return;
      RadioChannelNode localRadioChannelNode = new RadioChannelNode();
      localRadioChannelNode.channelId = 0;
      localRadioChannelNode.channelName = "暂无免流量电台";
      localRadioChannelNode.freq = String.valueOf("97700");
      localRadioChannelNode.parent = this;
      this.mLstChannelNodes.add(localRadioChannelNode);
    }
    while (this.parent != null);
    this.parent = InfoManager.getInstance().root().mContentCategory.mLiveNode;
  }

  public void addLocalChannels(List<Node> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      if ((this.mLstChannelNodes != null) && (!hasAvailableChannel()))
      {
        delDefaultNode();
        this.mLstChannelNodes.clear();
        int i = 0;
        while (i < paramList.size())
        {
          if (((Node)paramList.get(i)).nodeName.equalsIgnoreCase("channel"))
          {
            RadioChannelNode localRadioChannelNode = new RadioChannelNode();
            localRadioChannelNode.parent = this;
            localRadioChannelNode.freq = String.valueOf((int)(Float.valueOf(((ChannelNode)paramList.get(i)).freq).floatValue() * 1000.0F));
            localRadioChannelNode.channelName = ((ChannelNode)paramList.get(i)).title;
            this.mLstChannelNodes.add(localRadioChannelNode);
          }
          i += 1;
        }
      }
    }
  }

  public void deleteFreqInDB()
  {
    DataManager.getInstance().getData("deletedb_radio_node", null, null);
  }

  public boolean hasAvailableChannel()
  {
    if ((this.mLstChannelNodes == null) || (this.mLstChannelNodes.size() == 0))
      return false;
    return ((RadioChannelNode)this.mLstChannelNodes.get(0)).channelId != 0;
  }

  public boolean isAvailable()
  {
    return FMManager.getInstance().isAvailable();
  }

  public void loadRadioInfo(IRadioEventListener paramIRadioEventListener)
  {
    if (this.lstFreqs.size() == 0)
      return;
    String str = "";
    int i = 0;
    while (true)
    {
      int j = ((Integer)this.lstFreqs.get(i)).intValue();
      str = str + String.valueOf(Float.valueOf((float)(j * 0.001D)));
      i += 1;
      if (i >= this.lstFreqs.size())
      {
        InfoManager.getInstance().loadRadioInfo(str);
        if (paramIRadioEventListener == null)
          break;
        registerRadioEventListener(paramIRadioEventListener, "RRI");
        return;
      }
      str = str + ",";
    }
  }

  public void loadRadioNodes(IRadioEventListener paramIRadioEventListener)
  {
    PlayerAgent.getInstance().stop();
    FMManager.getInstance().scan();
    if (paramIRadioEventListener != null)
    {
      registerRadioEventListener(paramIRadioEventListener, "RRAC");
      registerRadioEventListener(paramIRadioEventListener, "RRACC");
      registerRadioEventListener(paramIRadioEventListener, "RHP");
      registerRadioEventListener(paramIRadioEventListener, "RHUP");
    }
  }

  public void onAudioQualityStatus(int paramInt)
  {
  }

  public void onChannelFound(int paramInt)
  {
    if (!isExisted(paramInt))
    {
      this.hasRefreshRadio = true;
      addFreq(paramInt);
      dispatchSubscribeEvent("RRAC");
    }
  }

  public void onFMOff()
  {
  }

  public void onFMOn()
  {
  }

  public void onHeadsetPlugged()
  {
    dispatchSubscribeEvent("RHP");
  }

  public void onHeadsetUnplugged()
  {
    dispatchSubscribeEvent("RHUP");
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (Node)paramObject;
    if ((paramObject == null) || (paramString == null));
    while ((!paramString.equalsIgnoreCase("AFC")) || (!paramObject.nodeName.equalsIgnoreCase("freqchannelinfo")))
      return;
    this.mFreqChannelInfoNode = ((FreqChannelInfoNode)paramObject);
  }

  public void onScanComplete(boolean paramBoolean)
  {
    addDefaultNode();
    dispatchSubscribeEvent("RRACC");
  }

  public void onScanStarted()
  {
  }

  public void onTune(int paramInt)
  {
  }

  public void restoreFromDB()
  {
    List localList = null;
    if (this.hasRestoreFromDB);
    do
    {
      return;
      this.hasRestoreFromDB = true;
      Result localResult = DataManager.getInstance().getData("getdb_radio_node", null, null).getResult();
      if (localResult.getSuccess())
        localList = (List)localResult.getData();
      if ((localList != null) && (localList.size() != 0))
        this.mLstChannelNodes = localList;
    }
    while ((this.mLstChannelNodes == null) || (this.mLstChannelNodes.size() == 0));
    int i = 0;
    while (i < this.mLstChannelNodes.size())
    {
      ((Node)this.mLstChannelNodes.get(i)).parent = this;
      this.lstFreqs.add(Integer.valueOf(((RadioChannelNode)this.mLstChannelNodes.get(i)).freq));
      i += 1;
    }
    arrangeRadios();
  }

  public void restoreFromDBByCity(String paramString)
  {
    if ((paramString == null) || (this.hasRestoreByCity));
    while (true)
    {
      return;
      if (!hasAvailableChannel())
      {
        Object localObject = new HashMap();
        ((Map)localObject).put("city", paramString);
        paramString = DataManager.getInstance().getData("getdb_freq_channels", null, (Map)localObject).getResult();
        if (paramString.getSuccess());
        for (paramString = (List)paramString.getData(); (paramString != null) && (paramString.size() != 0); paramString = null)
        {
          this.mLstChannelNodes.clear();
          int i = 0;
          try
          {
            while (i < paramString.size())
            {
              localObject = new RadioChannelNode();
              ((RadioChannelNode)localObject).parent = this;
              ((RadioChannelNode)localObject).freq = String.valueOf(Integer.valueOf((int)(Float.valueOf(((FreqChannel)paramString.get(i)).channelFreq).floatValue() * 1000.0F)));
              ((RadioChannelNode)localObject).channelName = ((FreqChannel)paramString.get(i)).channelName;
              ((RadioChannelNode)localObject).channelId = ((FreqChannel)paramString.get(i)).channelId;
              this.mLstChannelNodes.add(localObject);
              this.lstFreqs.add(Integer.valueOf(((RadioChannelNode)localObject).freq));
              i += 1;
            }
            this.hasRestoreByCity = true;
            return;
          }
          catch (Exception paramString)
          {
            return;
          }
        }
      }
    }
  }

  public void saveFreqChannelToDB()
  {
    if (this.mFreqChannelInfoNode == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("freqs", this.mFreqChannelInfoNode);
    DataManager.getInstance().getData("insertdb_freq_channels", null, localHashMap);
  }

  public void saveToDB()
  {
    if ((this.mLstChannelNodes == null) || (this.mLstChannelNodes.size() == 0));
    while ((!hasAvailableChannel()) || (!this.hasRefreshRadio))
      return;
    delDefaultNode();
    deleteFreqInDB();
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", this.mLstChannelNodes);
    DataManager.getInstance().getData("insertdb_radio_node", null, localHashMap);
  }

  public void updateRadioInfo(List<Node> paramList)
  {
    if (paramList == null)
      return;
    int i = 0;
    if (i < paramList.size())
    {
      String str = ((RadioChannelNode)paramList.get(i)).freq;
      int j = 0;
      while (true)
      {
        if (j < this.mLstChannelNodes.size())
        {
          if (((RadioChannelNode)this.mLstChannelNodes.get(j)).freq.equalsIgnoreCase(str))
          {
            ((RadioChannelNode)this.mLstChannelNodes.get(j)).channelName = ((RadioChannelNode)paramList.get(i)).channelName;
            ((RadioChannelNode)this.mLstChannelNodes.get(j)).channelId = ((RadioChannelNode)paramList.get(i)).channelId;
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    arrangeRadios();
    dispatchSubscribeEvent("RRI");
  }

  public static abstract interface IRadioEventListener
  {
    public static final String RECV_HEADSET_PLUGGED = "RHP";
    public static final String RECV_HEADSET_UNPLUGGED = "RHUP";
    public static final String RECV_RADIO_CHANNEL = "RRAC";
    public static final String RECV_RADIO_CHANNEL_COMPLETE = "RRACC";
    public static final String RECV_RADIO_INFO = "RRI";

    public abstract void onRadioNotification(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RadioNode
 * JD-Core Version:    0.6.2
 */