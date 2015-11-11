package fm.qingting.qtradio.model;

public class WeiboRecordData
{
  private String DJID;
  private String DJName;
  private String channelName;
  private String fromPage;
  private String programID;
  private String programName;
  private String sendType;

  public void Clone(WeiboRecordData paramWeiboRecordData)
  {
    this.programName = paramWeiboRecordData.programName;
    this.programID = paramWeiboRecordData.programID;
    this.channelName = paramWeiboRecordData.channelName;
    this.DJName = paramWeiboRecordData.DJName;
    this.DJID = paramWeiboRecordData.DJID;
    this.sendType = paramWeiboRecordData.sendType;
    this.fromPage = paramWeiboRecordData.fromPage;
  }

  public String getChannelName()
  {
    return this.channelName;
  }

  public String getDJID()
  {
    return this.DJID;
  }

  public String getDJName()
  {
    return this.DJName;
  }

  public String getFromPage()
  {
    return this.fromPage;
  }

  public String getProgramID()
  {
    return this.programID;
  }

  public String getProgramName()
  {
    return this.programName;
  }

  public String getSendType()
  {
    return this.sendType;
  }

  public void setChannelName(String paramString)
  {
    this.channelName = paramString;
  }

  public void setDJID(String paramString)
  {
    this.DJID = paramString;
  }

  public void setDJName(String paramString)
  {
    this.DJName = paramString;
  }

  public void setFromPage(String paramString)
  {
    this.fromPage = paramString;
  }

  public void setProgramID(String paramString)
  {
    this.programID = paramString;
  }

  public void setProgramName(String paramString)
  {
    this.programName = paramString;
  }

  public void setSendType(String paramString)
  {
    this.sendType = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.WeiboRecordData
 * JD-Core Version:    0.6.2
 */