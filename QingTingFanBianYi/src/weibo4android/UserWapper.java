package weibo4android;

import java.io.Serializable;
import java.util.List;

public class UserWapper
  implements Serializable
{
  private static final long serialVersionUID = -3119107701303920284L;
  private long nextCursor;
  private long previousCursor;
  private List<User> users;

  public UserWapper(List<User> paramList, long paramLong1, long paramLong2)
  {
    this.users = paramList;
    this.previousCursor = paramLong1;
    this.nextCursor = paramLong2;
  }

  public long getNextCursor()
  {
    return this.nextCursor;
  }

  public long getPreviousCursor()
  {
    return this.previousCursor;
  }

  public List<User> getUsers()
  {
    return this.users;
  }

  public void setNextCursor(long paramLong)
  {
    this.nextCursor = paramLong;
  }

  public void setPreviousCursor(long paramLong)
  {
    this.previousCursor = paramLong;
  }

  public void setUsers(List<User> paramList)
  {
    this.users = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.UserWapper
 * JD-Core Version:    0.6.2
 */