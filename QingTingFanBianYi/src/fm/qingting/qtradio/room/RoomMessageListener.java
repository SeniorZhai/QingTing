package fm.qingting.qtradio.room;

public abstract interface RoomMessageListener
{
  public abstract void onConnect();

  public abstract void onConnectFailure();

  public abstract void onDisconnect();

  public abstract void onMessage();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.RoomMessageListener
 * JD-Core Version:    0.6.2
 */