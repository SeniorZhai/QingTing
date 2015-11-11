package fm.qingting.qtradio.model;

import java.util.List;

public class RingToneNode extends Node
{
  private String belongRadio;
  public String broadcast_time;
  public transient Download downloadInfo;
  public int downloadnum;
  public int duration = 0;
  private int mAvailableUrlIndex = 0;
  private BroadcasterNode mBroadcaster;
  public String mDownLoadPath;
  private String mDownLoadUrls;
  private String mListenOnLineUrls;
  public String mTranscode;
  public String ringDesc;
  public String ringToneAlbumId = "1";
  public String ringToneId;
  public String ringType = "";
  public String title = "";
  public String type;
  public String updatetime;

  public RingToneNode()
  {
    this.nodeName = "ringtone";
  }

  public boolean belongToBroadcaster(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while ((this.mBroadcaster == null) || (this.mBroadcaster.id != Integer.valueOf(paramString).intValue()))
      return false;
    return true;
  }

  public String getBelongRadio()
  {
    return this.belongRadio;
  }

  public BroadcasterNode getBroadcaster()
  {
    if (this.mBroadcaster != null)
      return this.mBroadcaster;
    this.mBroadcaster = InfoManager.getInstance().root().mRingToneInfoNode.getBroadcasterByRoneId(this.ringToneId);
    return this.mBroadcaster;
  }

  public String getDownLoadUrl()
  {
    if (this.mDownLoadUrls != null)
      return this.mDownLoadUrls;
    if (this.mDownLoadPath != null)
      return getNextDownLoadUrl();
    return this.mDownLoadUrls;
  }

  public int getDuration()
  {
    if (this.duration != 0)
      return this.duration;
    if (this.broadcast_time != null)
    {
      this.duration = Integer.valueOf(this.broadcast_time).intValue();
      return this.duration;
    }
    return 0;
  }

  public String getListenOnLineUrl()
  {
    if ((this.mListenOnLineUrls == null) && (this.mTranscode != null))
      this.mListenOnLineUrls = MediaCenter.getInstance().getPlayUrls("virutalchannel", this.mTranscode, 24, 0);
    return this.mListenOnLineUrls;
  }

  public String getNextDownLoadUrl()
  {
    List localList = MediaCenter.getInstance().getPingInfo("virutalchannel");
    String str2 = "";
    this.mAvailableUrlIndex += 1;
    String str1 = str2;
    if (localList != null)
    {
      int i = this.mAvailableUrlIndex;
      str1 = str2;
      if (i < localList.size())
      {
        str1 = "http://" + ((PingInfoV6)localList.get(i)).getDomainIP();
        str1 = str1 + this.mDownLoadPath;
      }
    }
    this.mDownLoadUrls = str1;
    return str1;
  }

  public void setBelongRadio(String paramString)
  {
    this.belongRadio = paramString;
  }

  public void setBroadcaster(BroadcasterNode paramBroadcasterNode)
  {
    this.mBroadcaster = paramBroadcasterNode;
  }

  public void setListenOnLineUrl(String paramString)
  {
    this.mListenOnLineUrls = paramString;
  }

  public void updateRingTone(RingToneNode paramRingToneNode)
  {
    if (paramRingToneNode == null)
      return;
    this.ringToneAlbumId = paramRingToneNode.ringToneAlbumId;
    this.ringToneId = paramRingToneNode.ringToneId;
    this.ringType = paramRingToneNode.ringType;
    this.updatetime = paramRingToneNode.updatetime;
    this.title = paramRingToneNode.title;
    this.mAvailableUrlIndex = paramRingToneNode.mAvailableUrlIndex;
    this.broadcast_time = paramRingToneNode.broadcast_time;
    this.duration = paramRingToneNode.duration;
    this.mListenOnLineUrls = paramRingToneNode.mListenOnLineUrls;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RingToneNode
 * JD-Core Version:    0.6.2
 */