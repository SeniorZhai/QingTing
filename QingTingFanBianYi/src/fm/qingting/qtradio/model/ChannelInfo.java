package fm.qingting.qtradio.model;

public class ChannelInfo
{
  private int channelid;
  private String endtime;
  private String starttime;
  private String wid;

  public ChannelInfo(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.wid = paramString1;
    this.channelid = paramInt;
    this.starttime = paramString2;
    this.endtime = paramString3;
  }

  public int getChannelid()
  {
    return this.channelid;
  }

  public String getEndtime()
  {
    return this.endtime;
  }

  public String getStarttime()
  {
    return this.starttime;
  }

  public String getWid()
  {
    return this.wid;
  }

  public void setChannelid(int paramInt)
  {
    this.channelid = paramInt;
  }

  public void setEndtime(String paramString)
  {
    this.endtime = paramString;
  }

  public void setStarttime(String paramString)
  {
    this.starttime = paramString;
  }

  public void setWid(String paramString)
  {
    this.wid = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ChannelInfo
 * JD-Core Version:    0.6.2
 */