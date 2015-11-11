package fm.qingting.track.bean;

public class SelectSystemRadio extends UserAction
{
  public String localName;

  public SelectSystemRadio(String paramString)
  {
    super(6, "select_system_radio");
    this.localName = paramString;
  }

  public String toString()
  {
    return addQuotes(this.name) + "," + addQuotes(this.localName);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.track.bean.SelectSystemRadio
 * JD-Core Version:    0.6.2
 */