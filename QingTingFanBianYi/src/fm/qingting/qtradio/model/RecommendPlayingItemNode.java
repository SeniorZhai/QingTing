package fm.qingting.qtradio.model;

import java.util.regex.Pattern;

public class RecommendPlayingItemNode extends Node
{
  public int channelId;
  public String channelName;
  public int channelType;
  public String endTime;
  public String programName;
  public int programid;
  public int ratingStar = -1;
  private int relativeEndTime = -1;
  private int relativeStartTime = -1;
  public int resId;
  public String startTime;
  public String thumb;

  public RecommendPlayingItemNode()
  {
    this.nodeName = "recommendplayingitem";
  }

  public int endTime()
  {
    if (-1 == this.relativeEndTime);
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.endTime);
      if (arrayOfString.length >= 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.relativeEndTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label53: if (this.relativeEndTime < startTime())
        this.relativeEndTime += 86400;
      return this.relativeEndTime;
    }
    catch (Exception localException)
    {
      break label53;
    }
  }

  public int startTime()
  {
    if (-1 == this.relativeStartTime);
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.startTime);
      if (arrayOfString.length >= 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.relativeStartTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label53: return this.relativeStartTime;
    }
    catch (Exception localException)
    {
      break label53;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RecommendPlayingItemNode
 * JD-Core Version:    0.6.2
 */