package fm.qingting.qtradio.model;

public class Downloadobject
{
  public String downloadurl = null;
  public String programName = null;
  public int size = 0;
  public String uuid = null;

  public Downloadobject(String paramString1, String paramString2, String paramString3)
  {
    this.uuid = paramString1;
    this.programName = paramString2;
    this.downloadurl = paramString3;
  }

  public void setFileSize(int paramInt)
  {
    this.size = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Downloadobject
 * JD-Core Version:    0.6.2
 */