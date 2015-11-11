package fm.qingting.qtradio.push.bean;

public class PushBean
{
  public String catId;
  public String channelId;
  public String name;
  public String parentId;
  public String pid;
  public String pname;
  public PushType push_type;

  public PushBean(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.catId = paramString1;
    this.parentId = paramString2;
    this.channelId = paramString3;
    this.name = paramString4;
    this.pid = paramString5;
    this.pname = paramString6;
  }

  public String toString()
  {
    return "[" + this.catId + "," + this.parentId + "][" + this.channelId + "," + this.name + "][" + this.pid + "," + this.pname + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.bean.PushBean
 * JD-Core Version:    0.6.2
 */