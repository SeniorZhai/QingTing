package fm.qingting.qtradio.im.info;

import fm.qingting.qtradio.room.UserInfo;
import java.util.List;

public class GroupInfo
{
  public String groupDesc;
  public String groupId;
  public String groupName;
  public String groupThumb;
  public transient List<UserInfo> lstAdmins;
  public transient List<UserInfo> lstManagers;
  public int userCnt;

  public void update(GroupInfo paramGroupInfo)
  {
    if (paramGroupInfo == null)
      return;
    this.groupId = paramGroupInfo.groupId;
    this.groupName = paramGroupInfo.groupName;
    this.userCnt = paramGroupInfo.userCnt;
    this.groupDesc = paramGroupInfo.groupDesc;
    this.lstAdmins = paramGroupInfo.lstAdmins;
    this.lstManagers = paramGroupInfo.lstManagers;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.info.GroupInfo
 * JD-Core Version:    0.6.2
 */