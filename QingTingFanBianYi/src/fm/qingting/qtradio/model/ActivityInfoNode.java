package fm.qingting.qtradio.model;

import java.util.List;

public class ActivityInfoNode extends Node
{
  public String id = "area";
  private List<Node> mLstActivityNodes;
  public String name = "专区";

  public ActivityInfoNode()
  {
    this.nodeName = "activityinfo";
  }

  public List<Node> getActivityList()
  {
    return this.mLstActivityNodes;
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    if ((paramObject == null) || (paramString == null));
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("AACTL"));
      paramObject = (List)paramObject;
    }
    while ((paramObject.size() == 0) && (this.mLstActivityNodes != null));
    this.mLstActivityNodes = paramObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ActivityInfoNode
 * JD-Core Version:    0.6.2
 */