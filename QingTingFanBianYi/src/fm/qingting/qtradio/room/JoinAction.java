package fm.qingting.qtradio.room;

import fm.qingting.qtradio.model.Node;

public class JoinAction extends Action
{
  private String connectUrl;
  private Node mTopic;
  private int maxHistoryRecord = -1;
  private String roomId;
  private int roomType;

  public JoinAction()
  {
    this.actionType = 2;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public String getConnectUrl()
  {
    return this.connectUrl;
  }

  public Node getProgramTopic()
  {
    return this.mTopic;
  }

  public int getRecordCount()
  {
    return this.maxHistoryRecord;
  }

  public String getRoomId()
  {
    return this.roomId;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public void setConnectUrl(String paramString1, String paramString2, int paramInt, Node paramNode)
  {
    this.connectUrl = paramString1;
    this.roomId = paramString2;
    this.roomType = paramInt;
    this.mTopic = paramNode;
  }

  public void setRecordCount(int paramInt)
  {
    this.maxHistoryRecord = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.JoinAction
 * JD-Core Version:    0.6.2
 */