package fm.qingting.qtradio.model;

public class Topicinfo
{
  public String programid;
  public String wid;

  public Topicinfo(String paramString1, String paramString2)
  {
    this.wid = paramString1;
    this.programid = paramString2;
  }

  public String getProgramid()
  {
    return this.programid;
  }

  public String getWid()
  {
    return this.wid;
  }

  public void setProgramid(String paramString)
  {
    this.programid = paramString;
  }

  public void setWid(String paramString)
  {
    this.wid = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Topicinfo
 * JD-Core Version:    0.6.2
 */