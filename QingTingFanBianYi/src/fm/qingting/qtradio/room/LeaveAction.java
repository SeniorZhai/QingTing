package fm.qingting.qtradio.room;

public class LeaveAction extends Action
{
  private int platformType;
  private int roomType;

  public LeaveAction()
  {
    this.actionType = 10;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public int getPlatformType()
  {
    return this.platformType;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public void setContentInfo(int paramInt1, int paramInt2)
  {
    this.roomType = paramInt1;
    this.platformType = paramInt2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.LeaveAction
 * JD-Core Version:    0.6.2
 */