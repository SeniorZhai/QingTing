package fm.qingting.qtradio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqChannelInfoNode extends Node
  implements InfoManager.INodeEventListener
{
  public transient Map<String, List<FreqChannel>> mapFreqChannel = new HashMap();

  public FreqChannelInfoNode()
  {
    this.nodeName = "freqchannelinfo";
  }

  public List<FreqChannel> getFreqInfoByCity(String paramString)
  {
    if (paramString == null);
    while (!this.mapFreqChannel.containsKey(paramString))
      return null;
    return (List)this.mapFreqChannel.get(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.FreqChannelInfoNode
 * JD-Core Version:    0.6.2
 */