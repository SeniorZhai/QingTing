package fm.qingting.qtradio.logger;

import com.lmax.disruptor.EventFactory;

public class ValueEvent
{
  public static final EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory()
  {
    public ValueEvent newInstance()
    {
      return new ValueEvent();
    }
  };
  private String type;
  private String value;

  public ValueEvent()
  {
    this.type = "";
    this.value = "";
  }

  public ValueEvent(String paramString1, String paramString2)
  {
    this.type = paramString1;
    this.value = paramString2;
  }

  public String getType()
  {
    return this.type;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public void setValue(String paramString)
  {
    this.value = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.logger.ValueEvent
 * JD-Core Version:    0.6.2
 */