package fm.qingting.qtradio.model;

public class BroadcasterNode
{
  public String avatar;
  public int id;
  public String nick;
  public String qqId;
  public String qqName;
  public int ringToneId;
  public String weiboId;
  public String weiboName;

  public void update(BroadcasterNode paramBroadcasterNode)
  {
    if (paramBroadcasterNode == null)
      return;
    this.id = paramBroadcasterNode.id;
    this.nick = paramBroadcasterNode.nick;
    this.weiboId = paramBroadcasterNode.weiboId;
    this.weiboName = paramBroadcasterNode.weiboName;
    this.qqId = paramBroadcasterNode.qqId;
    this.qqName = paramBroadcasterNode.qqName;
    this.avatar = paramBroadcasterNode.avatar;
    this.ringToneId = paramBroadcasterNode.ringToneId;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.BroadcasterNode
 * JD-Core Version:    0.6.2
 */