package fm.qingting.qtradio.model;

import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.qtradio.ad.AdConfig;
import fm.qingting.qtradio.ad.AdPos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertisementInfoNode extends Node
{
  private AdvertisementItemNode mCurrPlayingAdvertisement;
  public List<AdConfig> mLstAMAdConfigs;
  public List<AdConfig> mLstAdConfigs;
  private List<AdPos> mLstAds;
  private Map<String, AdvertisementItemNode> mMapAdvertisements = new HashMap();

  public AdvertisementInfoNode()
  {
    this.nodeName = "advertisementinfo";
  }

  private AdPos getAdPos(String paramString)
  {
    if (paramString == null)
      return null;
    if (this.mLstAds != null)
    {
      int i = 0;
      while (i < this.mLstAds.size())
      {
        if (((AdPos)this.mLstAds.get(i)).posid.equalsIgnoreCase(paramString))
          return (AdPos)this.mLstAds.get(i);
        i += 1;
      }
    }
    return null;
  }

  public void addAdvertisement(String paramString, AdvertisementItemNode paramAdvertisementItemNode)
  {
    if (paramString == null)
      return;
    AdPos localAdPos = getAdPos(paramString);
    if (localAdPos != null)
    {
      paramAdvertisementItemNode.parent = localAdPos;
      if (localAdPos.isSplash())
        paramAdvertisementItemNode.getImage();
    }
    this.mMapAdvertisements.put(paramString, paramAdvertisementItemNode);
  }

  public List<AdConfig> getADAMConfigs()
  {
    return this.mLstAMAdConfigs;
  }

  public List<AdConfig> getADConfigs()
  {
    return this.mLstAdConfigs;
  }

  public AdvertisementItemNode getAdvertisement(int paramInt)
  {
    if (this.mLstAds != null)
    {
      int i = 0;
      while (i < this.mLstAds.size())
      {
        if ((((AdPos)this.mLstAds.get(i)).isADChannel()) && (((AdPos)this.mLstAds.get(i)).channelId == paramInt))
          return getAdvertisement(((AdPos)this.mLstAds.get(i)).posid);
        i += 1;
      }
    }
    return null;
  }

  public AdvertisementItemNode getAdvertisement(String paramString)
  {
    return (AdvertisementItemNode)this.mMapAdvertisements.get(paramString);
  }

  public AdPos getAudioAdPos(int paramInt1, int paramInt2)
  {
    if (this.mLstAds != null)
    {
      int i = 0;
      while (i < this.mLstAds.size())
      {
        if (((AdPos)this.mLstAds.get(i)).hitAudioAd(paramInt1, paramInt2))
          return (AdPos)this.mLstAds.get(i);
        i += 1;
      }
    }
    return null;
  }

  public AdvertisementItemNode getAudioAdv(int paramInt1, int paramInt2)
  {
    AdPos localAdPos = getAudioAdPos(paramInt1, paramInt2);
    if (localAdPos != null)
      return getAdvertisement(localAdPos.posid);
    return null;
  }

  public AdPos getBannerAdPos(int paramInt1, int paramInt2)
  {
    if (this.mLstAds != null)
    {
      int i = 0;
      while (i < this.mLstAds.size())
      {
        if (((AdPos)this.mLstAds.get(i)).hitBannerAd(paramInt1, paramInt2))
          return (AdPos)this.mLstAds.get(i);
        i += 1;
      }
    }
    return null;
  }

  public AdvertisementItemNode getCurrPlayingAdv()
  {
    return this.mCurrPlayingAdvertisement;
  }

  public List<AdPos> getLstAds()
  {
    return this.mLstAds;
  }

  public AdPos getSection(int paramInt)
  {
    int i;
    if (this.mLstAds != null)
    {
      i = 0;
      if (i < this.mLstAds.size())
        if (!((AdPos)this.mLstAds.get(i)).hitSectionAd(paramInt));
    }
    while (true)
    {
      if (i == -1)
      {
        return null;
        i += 1;
        break;
      }
      return (AdPos)this.mLstAds.get(i);
      i = -1;
    }
  }

  public AdPos getSplashAdPos()
  {
    if (this.mLstAds != null)
    {
      int i = 0;
      while (i < this.mLstAds.size())
      {
        if (((AdPos)this.mLstAds.get(i)).isSplash())
          return (AdPos)this.mLstAds.get(i);
        i += 1;
      }
    }
    return null;
  }

  public AdvertisementItemNode getSplashAdvertisement()
  {
    AdPos localAdPos = getSplashAdPos();
    if (localAdPos != null)
      return getAdvertisement(localAdPos.posid);
    return null;
  }

  public boolean hasAdPos()
  {
    return (this.mLstAds != null) && (this.mLstAds.size() > 0);
  }

  public void sendMessage(String paramString, AdvertisementItemNode paramAdvertisementItemNode)
  {
    MobclickAgent.onEvent(InfoManager.getInstance().getContext(), paramString, paramAdvertisementItemNode.id);
    TCAgent.onEvent(InfoManager.getInstance().getContext(), paramString, paramAdvertisementItemNode.id);
  }

  public void setADAMConfigs(List<AdConfig> paramList)
  {
    this.mLstAMAdConfigs = paramList;
  }

  public void setADConfigs(List<AdConfig> paramList)
  {
    this.mLstAdConfigs = paramList;
  }

  public void setCurrPlayingAdv(AdvertisementItemNode paramAdvertisementItemNode)
  {
    this.mCurrPlayingAdvertisement = paramAdvertisementItemNode;
  }

  public void setLstAdsPos(List<AdPos> paramList)
  {
    this.mLstAds = paramList;
    if (paramList != null)
    {
      int i = 0;
      while (i < paramList.size())
      {
        if (((AdPos)paramList.get(i)).isADChannel())
          InfoManager.getInstance().loadAdvertisement((AdPos)paramList.get(i), ((AdPos)paramList.get(i)).channelId, null);
        i += 1;
      }
    }
  }

  public class EventType
  {
    public static final String SECTIONAD_CLICK = "SectionADClick";
    public static final String SECTIONAD_CLOSE = "SectionADCLOSE";
    public static final String SECTOINAD_NOINTERSTING = "SectionAdNoIntersting";

    public EventType()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AdvertisementInfoNode
 * JD-Core Version:    0.6.2
 */