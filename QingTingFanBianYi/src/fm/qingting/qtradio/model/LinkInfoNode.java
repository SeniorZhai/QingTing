package fm.qingting.qtradio.model;

import java.util.HashMap;
import java.util.Map;

public class LinkInfoNode extends Node
{
  private Map<Integer, RecommendItemNode> mapLinkInfo = new HashMap();
  private Map<Integer, Boolean> mapLoaded = new HashMap();

  public LinkInfoNode()
  {
    this.nodeName = "linkinfo";
  }

  public RecommendItemNode getLinkInfo(int paramInt)
  {
    return (RecommendItemNode)this.mapLinkInfo.get(Integer.valueOf(paramInt));
  }

  public boolean hasLoaded(int paramInt)
  {
    return this.mapLoaded.containsKey(Integer.valueOf(paramInt));
  }

  public boolean isExist(String paramString)
  {
    if (paramString == null)
      return false;
    return this.mapLinkInfo.containsKey(paramString);
  }

  public void onNodeUpdated(Object paramObject, String paramString)
  {
    paramObject = (Node)paramObject;
    if ((paramObject == null) || (paramString == null));
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("ALI"));
      paramString = ((RecommendItemNode)paramObject).id;
    }
    while ((paramString == null) || (paramString.equalsIgnoreCase("")));
    this.mapLinkInfo.put(Integer.valueOf(paramString), (RecommendItemNode)paramObject);
  }

  public void setLinkInfo(int paramInt, RecommendItemNode paramRecommendItemNode)
  {
    if (paramRecommendItemNode == null)
      return;
    this.mapLinkInfo.put(Integer.valueOf(paramInt), paramRecommendItemNode);
  }

  public void setLoaded(int paramInt)
  {
    this.mapLoaded.put(Integer.valueOf(paramInt), Boolean.valueOf(true));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.LinkInfoNode
 * JD-Core Version:    0.6.2
 */