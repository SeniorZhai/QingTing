package fm.qingting.qtradio.social;

import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;

public class MiniFavNode extends Node
{
  public int categoryId;
  public int channelType;
  public int contentType;
  public int id;
  public String name;
  public int parentId;
  public long time;

  public MiniFavNode()
  {
    this.nodeName = "minifav";
  }

  public ChannelNode covertToChannelNode()
  {
    ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(this.id, this.channelType);
    ChannelNode localChannelNode1 = localChannelNode2;
    if (localChannelNode2 == null)
    {
      if (this.channelType != 1)
        break label58;
      localChannelNode1 = ChannelHelper.getInstance().getFakeVirtualChannel(this.id, this.categoryId, this.name);
    }
    while (true)
    {
      localChannelNode1.viewTime = this.time;
      return localChannelNode1;
      label58: localChannelNode1 = localChannelNode2;
      if (this.channelType == 0)
        localChannelNode1 = ChannelHelper.getInstance().getFakeLiveChannel(this.id, this.categoryId, this.name);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.social.MiniFavNode
 * JD-Core Version:    0.6.2
 */