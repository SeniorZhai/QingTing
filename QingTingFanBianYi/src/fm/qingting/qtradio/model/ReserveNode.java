package fm.qingting.qtradio.model;

public class ReserveNode extends Node
{
  public int channelId;
  public String programName;
  public Node reserveNode;
  public long reserveTime;
  public int uniqueId;

  public ReserveNode()
  {
    this.nodeName = "reserve";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ReserveNode
 * JD-Core Version:    0.6.2
 */