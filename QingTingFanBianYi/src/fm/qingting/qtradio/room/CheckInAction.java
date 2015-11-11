package fm.qingting.qtradio.room;

public class CheckInAction extends Action
{
  private int platformType;
  private int roomType;

  public CheckInAction()
  {
    this.actionType = 6;
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
 * Qualified Name:     fm.qingting.qtradio.room.CheckInAction
 * JD-Core Version:    0.6.2
 */