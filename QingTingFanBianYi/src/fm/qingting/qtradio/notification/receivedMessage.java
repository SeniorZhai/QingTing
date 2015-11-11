package fm.qingting.qtradio.notification;

class receivedMessage
  implements Comparable<receivedMessage>
{
  public String catId = "";
  public String channelid = "";
  public String channelname = "";
  public String content = "";
  public long endTime;
  public int id = 0;
  public int msgType = -1;
  public String page = "";
  public String parentid = "";
  public String programId;
  public long startTime;
  public String title = "";

  public receivedMessage()
  {
  }

  receivedMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong, String paramString6, int paramInt)
  {
    this.title = paramString1;
    this.content = paramString2;
    this.channelname = paramString3;
    this.channelid = paramString4;
    this.parentid = paramString5;
    this.startTime = paramLong;
    this.page = paramString6;
    this.id = paramInt;
  }

  public int compareTo(receivedMessage paramreceivedMessage)
  {
    return paramreceivedMessage.id - this.id;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.receivedMessage
 * JD-Core Version:    0.6.2
 */