package fm.qingting.qtradio.model;

import fm.qingting.utils.TimeUtil;
import java.util.List;
import java.util.Map;

public class SpecialTopicNode extends Node
{
  private long _updateTime = 0L;
  public int categoryId;
  public int channelStar = -99;
  public String create_time;
  public String desc;
  public int id;
  private transient List<ChannelNode> mLstChannels;
  public String sub_title;
  public String thumb;
  public String title;
  public String update_time;

  public SpecialTopicNode()
  {
    this.nodeName = "specialtopic";
  }

  public int getApiId()
  {
    return this.id - 1000001;
  }

  public String getKey()
  {
    return String.valueOf(this.id);
  }

  public long getUpdateTime()
  {
    if (this.update_time == null)
      return 0L;
    if (this._updateTime > 0L)
      return this._updateTime;
    this._updateTime = TimeUtil.dateToMS(this.update_time);
    return this._updateTime;
  }

  public List<ChannelNode> getlstChannels()
  {
    return this.mLstChannels;
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    if (paramString.equalsIgnoreCase("ADD_SPECIAL_TOPIC"))
    {
      paramObject = (SpecialTopicNode)paramObject;
      if (paramObject.id == this.id)
        updatePartialInfo(paramObject);
    }
  }

  public void setChannels(List<ChannelNode> paramList)
  {
    this.mLstChannels = paramList;
  }

  public void updatePartialInfo(SpecialTopicNode paramSpecialTopicNode)
  {
    this.id = paramSpecialTopicNode.id;
    this.title = paramSpecialTopicNode.title;
    this.sub_title = paramSpecialTopicNode.sub_title;
    this.desc = paramSpecialTopicNode.desc;
    this.create_time = paramSpecialTopicNode.create_time;
    this.update_time = paramSpecialTopicNode.update_time;
    this.thumb = paramSpecialTopicNode.thumb;
    this.categoryId = paramSpecialTopicNode.categoryId;
    this._updateTime = paramSpecialTopicNode._updateTime;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.SpecialTopicNode
 * JD-Core Version:    0.6.2
 */