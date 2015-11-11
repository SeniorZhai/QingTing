package fm.qingting.qtradio.search;

import fm.qingting.qtradio.model.Node;

public class SearchItemNode extends Node
{
  public int audience_count = 0;
  public String broadcasters;
  public String cName;
  public String catName;
  public int categoryId;
  public int channelId;
  public int channelType = 1;
  public int contentType = 0;
  public String cover;
  public String desc;
  public String dimensionName;
  public String freqs;
  public int groupType = 0;
  public String name;
  public String podcasterAvatar;
  public String podcasterDescription;
  public int podcasterFan_num;
  public int podcasterId;
  public String podcasterNickName;
  public String podcasterTitle;
  public int programId;
  public int star = 6;
  public double totalScore = 0.0D;

  public SearchItemNode()
  {
    this.nodeName = "searchitem";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.search.SearchItemNode
 * JD-Core Version:    0.6.2
 */