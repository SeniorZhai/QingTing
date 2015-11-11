package fm.qingting.qtradio.room;

public class ChatData extends CustomData
{
  public int askForSongCnt = 0;
  public String commentid;
  public int conentType = 0;
  public String content;
  public String id;
  public String replyedContent;
  public UserInfo toUser;
  public UserInfo user;

  public ChatData()
  {
    this.type = 1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.ChatData
 * JD-Core Version:    0.6.2
 */