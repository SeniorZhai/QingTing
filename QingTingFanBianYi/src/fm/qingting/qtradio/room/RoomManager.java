package fm.qingting.qtradio.room;

import java.util.ArrayList;
import java.util.List;

public class RoomManager
{
  private static RoomManager _instance = null;
  private UserInfo currUser;
  private List<Room> lstRoom = new ArrayList();

  public static RoomManager getInstance()
  {
    if (_instance == null)
      _instance = new RoomManager();
    return _instance;
  }

  public void SetUserInfo(UserInfo paramUserInfo)
  {
    this.currUser = paramUserInfo;
  }

  public Room getRoomByType(int paramInt)
  {
    int i = 0;
    while (i < this.lstRoom.size())
    {
      if (((Room)this.lstRoom.get(i)).type == paramInt)
        return (Room)this.lstRoom.get(i);
      i += 1;
    }
    if (paramInt == 1)
    {
      LiveRoom localLiveRoom = new LiveRoom();
      this.lstRoom.add(localLiveRoom);
      return localLiveRoom;
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.RoomManager
 * JD-Core Version:    0.6.2
 */