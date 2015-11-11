package fm.qingting.qtradio.controller.chatRoom;

public class ConversationIncrement
{
  private int increment = 0;
  private long last_createTime = 0L;
  private long read_createTime = 0L;

  public void addIncrement()
  {
    this.increment += 1;
  }

  public int getIncrement()
  {
    return this.increment;
  }

  public long getLast_createTime()
  {
    return this.last_createTime;
  }

  public long getRead_createTime()
  {
    return this.read_createTime;
  }

  public void markAsRead()
  {
    this.read_createTime = this.last_createTime;
    this.increment = 0;
  }

  public void setIncrement(int paramInt)
  {
    this.increment = paramInt;
  }

  public void setLast_createTime(long paramLong)
  {
    this.last_createTime = paramLong;
  }

  public void setRead_createTime(long paramLong)
  {
    this.read_createTime = paramLong;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.chatRoom.ConversationIncrement
 * JD-Core Version:    0.6.2
 */