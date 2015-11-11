package fm.qingting.qtradio.ad.platforms.mediav.model;

import com.mediav.ads.sdk.interfaces.IMvNativeAd;
import fm.qingting.qtradio.ad.AdPos;
import fm.qingting.qtradio.ad.platforms.mediav.MediaVAgent;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.IThirdPartyAd;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.utils.ThirdTracker;
import org.json.JSONObject;

public class MediaVNativeAd
  implements IThirdPartyAd
{
  public AdPos adPos;
  public String buttonText;
  public String desc;
  public String extendText;
  public String logo;
  private int mCategoryId = 0;
  public IMvNativeAd rawAd;
  public String thumbnail;
  public String title;
  private boolean wasDisplayed = false;

  public int getCategoryId()
  {
    return this.mCategoryId;
  }

  public String getPlatformName()
  {
    return "AD_PLATFORM_MEDIAV";
  }

  public void onClick()
  {
    ThirdTracker.getInstance().sendThirdTrackLog("ThirdAdv", "2", 2, MediaVAgent.getAdPosition(), Constants.ADV_CLICK, this.mCategoryId);
    try
    {
      this.rawAd.onAdClicked();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onShow()
  {
    if (!this.wasDisplayed)
    {
      this.wasDisplayed = true;
      ThirdTracker.getInstance().sendThirdTrackLog("ThirdAdv", "2", 2, MediaVAgent.getAdPosition(), Constants.ADV_IMPRESSION, this.mCategoryId);
    }
    try
    {
      this.rawAd.onAdShowed();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void setCategoryId(int paramInt)
  {
    this.mCategoryId = paramInt;
  }

  public AdvertisementItemNode toAdvertisementItemNode()
  {
    AdvertisementItemNode localAdvertisementItemNode = new AdvertisementItemNode();
    localAdvertisementItemNode.id = "MediaVAd";
    localAdvertisementItemNode.isSplash = false;
    localAdvertisementItemNode.setTitle(this.title);
    localAdvertisementItemNode.setDescription(this.desc);
    localAdvertisementItemNode.desc = this.desc;
    localAdvertisementItemNode.image = this.logo;
    localAdvertisementItemNode.useLocalWebview = false;
    return localAdvertisementItemNode;
  }

  public String toJson()
  {
    try
    {
      Object localObject = new JSONObject();
      ((JSONObject)localObject).put("logo", this.logo);
      ((JSONObject)localObject).put("title", this.title);
      ((JSONObject)localObject).put("desc", this.desc);
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.platforms.mediav.model.MediaVNativeAd
 * JD-Core Version:    0.6.2
 */