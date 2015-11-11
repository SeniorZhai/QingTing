package fm.qingting.qtradio.model;

import fm.qingting.qtradio.helper.ChannelHelper;
import java.util.List;

public class Attribute extends Node
{
  public int id = 0;
  public String name = "";

  public Attribute()
  {
    this.nodeName = "attribute";
  }

  public int getCatid()
  {
    if ((this.parent != null) && (this.parent.nodeName.equalsIgnoreCase("category")))
      return ((CategoryNode)this.parent).categoryId;
    return 0;
  }

  public List<ChannelNode> getLstChannels()
  {
    int i = getCatid();
    if (i != 0)
      return ChannelHelper.getInstance().getLstChannelsByAttrPath(i, String.valueOf(this.id));
    return null;
  }

  public List<ChannelNode> getLstLiveChannels(boolean paramBoolean)
  {
    if ((this.parent != null) && (this.parent.nodeName.equalsIgnoreCase("category")) && (((CategoryNode)this.parent).isLiveCategory()))
      return ChannelHelper.getInstance().getLstLiveChannelsByAttrPath(((CategoryNode)this.parent).categoryId, String.valueOf(this.id), paramBoolean);
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Attribute
 * JD-Core Version:    0.6.2
 */