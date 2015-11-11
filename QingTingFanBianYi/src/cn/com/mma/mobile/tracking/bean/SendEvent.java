package cn.com.mma.mobile.tracking.bean;

import java.util.Map;

public class SendEvent
{
  public String muds = null;
  public Map<String, String> segmentation = null;
  private long timestamp = 0L;
  private String url = null;

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.bean.SendEvent
 * JD-Core Version:    0.6.2
 */