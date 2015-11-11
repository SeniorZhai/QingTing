package fm.qingting.qtradio.model;

public class AdvertisementItemNode3rdParty extends AdvertisementItemNode
{
  public static final String NAME = "thiradpartyadvertisement";
  private IThirdPartyAd mRawAd;

  public AdvertisementItemNode3rdParty(IThirdPartyAd paramIThirdPartyAd)
  {
    this.nodeName = "thiradpartyadvertisement";
    this.mRawAd = paramIThirdPartyAd;
    dumpAdInfo();
  }

  private void dumpAdInfo()
  {
    if (this.mRawAd == null);
    AdvertisementItemNode localAdvertisementItemNode;
    do
    {
      return;
      localAdvertisementItemNode = this.mRawAd.toAdvertisementItemNode();
    }
    while (localAdvertisementItemNode == null);
    this.audioPath = localAdvertisementItemNode.audioPath;
    this.desc = localAdvertisementItemNode.desc;
    this.duration = localAdvertisementItemNode.duration;
    this.endTime = localAdvertisementItemNode.endTime;
    this.height = localAdvertisementItemNode.height;
    this.id = localAdvertisementItemNode.id;
    this.image = localAdvertisementItemNode.image;
    this.internal_catid = localAdvertisementItemNode.internal_catid;
    this.internal_channelid = localAdvertisementItemNode.internal_channelid;
    this.internal_landing = localAdvertisementItemNode.internal_landing;
    this.interval = localAdvertisementItemNode.interval;
    this.interval_channeltype = localAdvertisementItemNode.interval_channeltype;
    this.interval_programid = localAdvertisementItemNode.interval_programid;
    this.isSplash = localAdvertisementItemNode.isSplash;
    this.landing = localAdvertisementItemNode.landing;
    setTitle(localAdvertisementItemNode.getTitle());
    setParent(localAdvertisementItemNode.getParent());
    this.resType = localAdvertisementItemNode.resType;
    this.skin = localAdvertisementItemNode.skin;
    this.splash_landing = localAdvertisementItemNode.splash_landing;
    this.startTime = localAdvertisementItemNode.startTime;
    this.useLocalWebview = localAdvertisementItemNode.useLocalWebview;
    this.mTracker = localAdvertisementItemNode.mTracker;
  }

  public IThirdPartyAd getRawAd()
  {
    return this.mRawAd;
  }

  public void onClick()
  {
    if (this.mRawAd != null);
    try
    {
      this.mRawAd.onClick();
      label16: super.onClick();
      return;
    }
    catch (Exception localException)
    {
      break label16;
    }
  }

  public void onShow()
  {
    if (this.mRawAd != null);
    try
    {
      this.mRawAd.onShow();
      label16: super.onShow();
      return;
    }
    catch (Exception localException)
    {
      break label16;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AdvertisementItemNode3rdParty
 * JD-Core Version:    0.6.2
 */