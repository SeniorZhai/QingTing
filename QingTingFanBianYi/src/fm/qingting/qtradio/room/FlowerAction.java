package fm.qingting.qtradio.room;

public class FlowerAction extends Action
{
  private UserInfo flowerUser;
  private int platformType;
  private int roomType;

  public FlowerAction()
  {
    this.actionType = 7;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public UserInfo getFlowerUser()
  {
    return this.flowerUser;
  }

  public int getPlatformType()
  {
    return this.platformType;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public void setContentInfo(int paramInt1, int paramInt2, UserInfo paramUserInfo)
  {
    this.roomType = paramInt1;
    this.platformType = paramInt2;
    this.flowerUser = paramUserInfo;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.FlowerAction
 * JD-Core Version:    0.6.2
 */