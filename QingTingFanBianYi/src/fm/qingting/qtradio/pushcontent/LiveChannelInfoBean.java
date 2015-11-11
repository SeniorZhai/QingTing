package fm.qingting.qtradio.pushcontent;

public class LiveChannelInfoBean
{
  private long broadcastEndTime;
  private long broadcastTime;
  private String catId;
  private String channelId;
  private String name;
  private String parentId;
  private String pid;
  private String pname;
  private String topic;

  public LiveChannelInfoBean()
  {
  }

  public LiveChannelInfoBean(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, String paramString5)
  {
    this.channelId = paramString1;
    this.name = paramString2;
    this.pname = paramString3;
    this.pid = paramString4;
    this.topic = paramString5;
    this.broadcastTime = paramLong;
  }

  public long getBroadcastEndTime()
  {
    return this.broadcastEndTime;
  }

  public long getBroadcastTime()
  {
    return this.broadcastTime;
  }

  public String getCatId()
  {
    return this.catId;
  }

  public String getChannelId()
  {
    return this.channelId;
  }

  public String getName()
  {
    return this.name;
  }

  public String getParentId()
  {
    return this.parentId;
  }

  public String getPid()
  {
    return this.pid;
  }

  public String getPname()
  {
    return this.pname;
  }

  public String getTopic()
  {
    return this.topic;
  }

  public void setBroadcastEndTime(long paramLong)
  {
    this.broadcastEndTime = paramLong;
  }

  public void setBroadcastTime(long paramLong)
  {
    this.broadcastTime = paramLong;
  }

  public void setCatId(String paramString)
  {
    this.catId = paramString;
  }

  public void setChannelId(String paramString)
  {
    this.channelId = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setParentId(String paramString)
  {
    this.parentId = paramString;
  }

  public void setPid(String paramString)
  {
    this.pid = paramString;
  }

  public void setPname(String paramString)
  {
    this.pname = paramString;
  }

  public void setTopic(String paramString)
  {
    this.topic = paramString;
  }

  public String toString()
  {
    return this.catId + "," + this.parentId + "," + this.channelId + "," + this.name + "," + this.pid + "," + this.pname + "," + this.topic;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushcontent.LiveChannelInfoBean
 * JD-Core Version:    0.6.2
 */