package fm.qingting.framework.event;

public class AppEvent
{
  public Object param;
  public Object source;
  public String type;

  public AppEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    this.source = paramObject1;
    this.type = paramString;
    this.param = paramObject2;
  }

  public String toString()
  {
    String str = "";
    if ((this.param instanceof String))
      str = (String)this.param;
    return this.source + " " + this.type + " " + str;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.event.AppEvent
 * JD-Core Version:    0.6.2
 */