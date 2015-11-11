package fm.qingting.qtradio.view.popviews;

public class ButtonItem
{
  public static final int EVENT_ATTA = 0;
  public static final int EVENT_BLOCK = 3;
  public static final int EVENT_CANCEL = 6;
  public static final int EVENT_DELETE = 4;
  public static final int EVENT_PROFILE = 2;
  public static final int EVENT_REPORT = 5;
  public static final int EVENT_SENDMESSAGE = 1;
  public static final int HIGHLIGHT = 1;
  public static final int NORMAL = 0;
  private int mEventId;
  private String mName;
  private int mType = 0;

  public ButtonItem(String paramString, int paramInt)
  {
    this.mName = paramString;
    this.mEventId = paramInt;
  }

  public ButtonItem(String paramString, int paramInt1, int paramInt2)
  {
    this.mName = paramString;
    this.mEventId = paramInt1;
    this.mType = paramInt2;
  }

  int getEventId()
  {
    return this.mEventId;
  }

  String getName()
  {
    return this.mName;
  }

  int getType()
  {
    return this.mType;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ButtonItem
 * JD-Core Version:    0.6.2
 */