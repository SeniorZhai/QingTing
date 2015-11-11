package fm.qingting.qtradio.room;

public class SendAction extends Action
{
  private ChatData mData;
  private int roomType;

  public SendAction()
  {
    this.actionType = 1;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public ChatData getData()
  {
    return this.mData;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public void setContentInfo(int paramInt, ChatData paramChatData)
  {
    this.roomType = paramInt;
    this.mData = paramChatData;
  }

  public void setContentInfo(String paramString, int paramInt)
  {
    this.roomType = paramInt;
    this.mData = new ChatData();
    this.mData.content = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.SendAction
 * JD-Core Version:    0.6.2
 */