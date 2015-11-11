package fm.qingting.qtradio.room;

public class SpeakToAction extends Action
{
  private String message;
  private int platformType;
  private int roomType;
  private UserInfo user;

  public SpeakToAction()
  {
    this.actionType = 8;
  }

  public int getActionType()
  {
    return this.actionType;
  }

  public String getMessage()
  {
    return this.message;
  }

  public int getPlatformType()
  {
    return this.platformType;
  }

  public int getRoomType()
  {
    return this.roomType;
  }

  public UserInfo getUserInfo()
  {
    return this.user;
  }

  public void setContentInfo(int paramInt1, int paramInt2, UserInfo paramUserInfo, String paramString)
  {
    this.roomType = paramInt1;
    this.platformType = paramInt2;
    this.message = paramString;
    this.user = paramUserInfo;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.SpeakToAction
 * JD-Core Version:    0.6.2
 */