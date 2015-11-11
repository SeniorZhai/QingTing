package fm.qingting.qtradio.view.chatroom.chatlist;

public class ChatItem
{
  public static final int GRAND_LEFT = 16;
  public static final int GRAND_RIGHT = 0;
  public static final int GRAND_TIMESTAMP = 32;
  public static final int MASK = 240;
  public static final int TYPE_COUNT = 3;
  public static final int TYPE_MINE_ANSWERNAME = 3;
  public static final int TYPE_MINE_ASKNAME = 2;
  public static final int TYPE_MINE_COMMON = 1;
  public static final int TYPE_MINE_DJ = 4;
  public static final int TYPE_TIMESTAMP = 32;
  public static final int TYPE_USER_ANSWERNAME = 19;
  public static final int TYPE_USER_ASKNAME = 18;
  public static final int TYPE_USER_COMMON = 17;
  public static final int TYPE_USER_DJ = 20;
  public Object data;
  public final int type;

  public ChatItem(int paramInt, Object paramObject)
  {
    this.type = paramInt;
    this.data = paramObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.chatlist.ChatItem
 * JD-Core Version:    0.6.2
 */