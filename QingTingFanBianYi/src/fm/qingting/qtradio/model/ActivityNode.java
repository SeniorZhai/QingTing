package fm.qingting.qtradio.model;

import java.util.List;

public class ActivityNode extends Node
{
  public int categoryId;
  public int channelId;
  public List<String> clickTracking;
  public String contentUrl;
  public String desc;
  public boolean hasShared = true;
  public int id;
  public List<String> imageTracking;
  public String infoTitle;
  public String infoUrl;
  public String name = "活动";
  public String network = "";
  public boolean putUserInfo = true;
  public String titleIconUrl;
  public String type;
  public int updatetime;
  public boolean useLocalWebview = false;

  public ActivityNode()
  {
    this.nodeName = "activity";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ActivityNode
 * JD-Core Version:    0.6.2
 */