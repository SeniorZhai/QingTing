package fm.qingting.qtradio.log;

public class LogBean
{
  private String content;
  private long id;
  private LogMeta meta = null;
  private long time;
  private String type;

  public LogBean(long paramLong, String paramString1, String paramString2, LogMeta paramLogMeta)
  {
    this.time = paramLong;
    this.type = paramString1;
    this.content = paramString2;
    this.meta = paramLogMeta;
  }

  public LogBean(String paramString1, String paramString2)
  {
    this.type = paramString1;
    this.content = paramString2;
    this.time = System.currentTimeMillis();
  }

  public String getContent()
  {
    return this.content;
  }

  public long getId()
  {
    return this.id;
  }

  public LogMeta getMeta()
  {
    return this.meta;
  }

  public long getTime()
  {
    return this.time;
  }

  public String getType()
  {
    return this.type;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setMeta(LogMeta paramLogMeta)
  {
    this.meta = paramLogMeta;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public String toString()
  {
    return "(" + this.time + "," + this.type + "," + this.content + "," + this.meta + ")";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.log.LogBean
 * JD-Core Version:    0.6.2
 */