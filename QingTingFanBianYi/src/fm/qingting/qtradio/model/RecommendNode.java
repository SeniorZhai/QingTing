package fm.qingting.qtradio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendNode extends Node
{
  public List<RecommendConfigureNode> mLstConfigureNodes;
  private Map<Integer, List<RecommendItemNode>> recForCategory = new HashMap();
  public String title;

  public RecommendNode()
  {
    this.nodeName = "recommend";
  }

  public List<RecommendItemNode> getRecommendForCategory(int paramInt)
  {
    return (List)this.recForCategory.get(Integer.valueOf(paramInt));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RecommendNode
 * JD-Core Version:    0.6.2
 */