package fm.qingting.qtradio.push.bean;

public class ChannelUpdateInfoBean extends PushBean
{
  private int contentType;
  private long updateTime;

  public ChannelUpdateInfoBean(String paramString1, String paramString2, long paramLong, String paramString3, String paramString4)
  {
    super(null, null, paramString1, paramString2, paramString4, paramString3);
    this.updateTime = paramLong;
    this.push_type = PushType.ContentUpdate;
  }

  public String getCatId()
  {
    return this.catId;
  }

  public String getChannelId()
  {
    return this.channelId;
  }

  public int getContentType()
  {
    return this.contentType;
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

  public long getUpdateTime()
  {
    return this.updateTime;
  }

  public void setCatId(String paramString)
  {
    this.catId = paramString;
  }

  public void setChannelId(String paramString)
  {
    this.channelId = paramString;
  }

  public void setContentType(int paramInt)
  {
    this.contentType = paramInt;
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

  public void setUpdateTime(long paramLong)
  {
    this.updateTime = paramLong;
  }

  public String toString()
  {
    return this.catId + "," + this.parentId + "," + this.channelId + "," + this.name + "," + this.pid + "," + this.pname;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.bean.ChannelUpdateInfoBean
 * JD-Core Version:    0.6.2
 */