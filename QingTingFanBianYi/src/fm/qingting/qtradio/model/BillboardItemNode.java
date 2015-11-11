package fm.qingting.qtradio.model;

public class BillboardItemNode extends Node
{
  public String desc = "";
  public transient int mClickCnt;
  public transient Node mNode;
  public String name = "";
  public int parentId;
  public String parentType;

  public BillboardItemNode()
  {
    this.nodeName = "billboarditem";
  }

  public Node getDetail()
  {
    return this.mNode;
  }

  public void setDetail(Node paramNode)
  {
    if (paramNode != null)
      this.mNode = paramNode;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.BillboardItemNode
 * JD-Core Version:    0.6.2
 */