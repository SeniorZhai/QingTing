package fm.qingting.qtradio.model;

public class Download
{
  public static final int COMPLETE = 3;
  public static final int FAILED = 4;
  public static final int INIT = 0;
  public static final int PROGRESSING = 1;
  public static final int UNFINISHED = 2;
  public int channelId;
  public int contentType = 1;
  public long downloadEndTime = 0L;
  public String downloadPath;
  public long downloadStartTime = 0L;
  public long downloadTime = 0L;
  public String fileName;
  public int fileSize = 0;
  public String id = "";
  public int lastProgress = 0;
  public int progress = 0;
  public int sequence = 0;
  public int state;
  public int type;
  public int updateTime = 0;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Download
 * JD-Core Version:    0.6.2
 */