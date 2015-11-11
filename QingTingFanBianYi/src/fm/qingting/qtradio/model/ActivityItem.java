package fm.qingting.qtradio.model;

import java.io.Serializable;

public class ActivityItem
  implements Serializable
{
  public String content;
  public String endTime;
  public String id;
  public String startTime;
  public String title;

  public ActivityItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.title = paramString1;
    this.content = paramString2;
    this.startTime = paramString3;
    this.endTime = paramString4;
    this.id = paramString5;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ActivityItem
 * JD-Core Version:    0.6.2
 */