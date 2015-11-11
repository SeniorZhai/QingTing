package fm.qingting.track.bean;

public class SelectChannel extends UserAction
{
  public String channelName;
  public int id;

  public SelectChannel(int paramInt, String paramString)
  {
    super(2, "select_channel");
    this.id = paramInt;
    this.channelName = paramString;
  }

  public String toString()
  {
    return addQuotes(this.name) + "," + addQuotes(new StringBuilder().append(this.channelName).append("---").append(this.id).toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.track.bean.SelectChannel
 * JD-Core Version:    0.6.2
 */