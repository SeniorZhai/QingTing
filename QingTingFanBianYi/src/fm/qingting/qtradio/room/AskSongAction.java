package fm.qingting.qtradio.room;

public class AskSongAction extends Action
{
  private String roomId;
  private int roomType;

  public AskSongAction()
  {
    this.actionType = 13;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public String getRoomId()
  {
    return this.roomId;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public void setContentInfo(int paramInt, String paramString)
  {
    this.roomType = paramInt;
    this.roomId = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.AskSongAction
 * JD-Core Version:    0.6.2
 */