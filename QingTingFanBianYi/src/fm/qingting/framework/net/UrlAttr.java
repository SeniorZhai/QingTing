package fm.qingting.framework.net;

public class UrlAttr
{
  private String lastModified = null;

  public UrlAttr(String paramString)
  {
    this.lastModified = paramString;
  }

  public String getLastModified()
  {
    return this.lastModified;
  }

  public void setLastModified(String paramString)
  {
    this.lastModified = paramString;
  }

  public String toString()
  {
    return "Last-Modified:" + this.lastModified;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.net.UrlAttr
 * JD-Core Version:    0.6.2
 */