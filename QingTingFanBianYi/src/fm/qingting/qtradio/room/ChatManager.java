package fm.qingting.qtradio.room;

public class ChatManager
{
  private static ChatManager _instance = null;

  public static ChatManager getInstance()
  {
    if (_instance == null)
      _instance = new ChatManager();
    return _instance;
  }

  public Chat getRawChat()
  {
    return new Chat();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.ChatManager
 * JD-Core Version:    0.6.2
 */