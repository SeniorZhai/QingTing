package fm.qingting.qtradio.ad;

import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import java.util.Map;

public class AdPos extends Node
{
  public int adtype = 0;
  public int audioPos = 0;
  public int bannerPos = -1;
  public int categoryId = 0;
  public int channelId = -1;
  public String posdesc;
  public String posid;
  public String posquery;
  public int sectionPosition = 0;

  public AdPos()
  {
    this.nodeName = "adpos";
  }

  public boolean hitAudioAd(int paramInt1, int paramInt2)
  {
    return (isAudio()) && (this.audioPos == paramInt2) && ((paramInt1 == this.categoryId) || (this.categoryId == -1));
  }

  public boolean hitBannerAd(int paramInt1, int paramInt2)
  {
    return (isBanner()) && (paramInt2 == this.bannerPos) && (paramInt1 == this.categoryId);
  }

  public boolean hitSectionAd(int paramInt)
  {
    return (isSection()) && (paramInt == this.categoryId);
  }

  public boolean isADChannel()
  {
    return this.adtype == 4;
  }

  public boolean isAudio()
  {
    return this.adtype == 3;
  }

  public boolean isBanner()
  {
    return this.adtype == 2;
  }

  public boolean isEndAudio()
  {
    return this.audioPos == 5;
  }

  public boolean isFrontAudio()
  {
    return this.audioPos == 4;
  }

  public boolean isSection()
  {
    return this.adtype == 5;
  }

  public boolean isSplash()
  {
    return this.adtype == 1;
  }

  public void onNodeUpdated(Object paramObject, Map<String, String> paramMap, String paramString)
  {
    Object localObject = null;
    if (paramString.equalsIgnoreCase("ADD_ADVERTISEMENT_INFO"))
    {
      if (paramMap == null)
        break label111;
      paramObject = (String)paramMap.get("zone");
    }
    for (paramMap = (String)paramMap.get("posquery"); ; paramMap = localObject)
    {
      if ((paramObject != null) && (paramMap != null) && (paramObject.equalsIgnoreCase(this.posid)) && (paramMap.equalsIgnoreCase(this.posquery)) && (this.parent != null) && (this.parent.nodeName.equalsIgnoreCase("recommendcategory")) && (isBanner()))
        ((RecommendCategoryNode)this.parent).insertBannerAdvertisement();
      return;
      label111: paramObject = null;
    }
  }

  public void parseDesc()
  {
    try
    {
      if ((this.posdesc != null) && (!this.posdesc.equalsIgnoreCase("")))
      {
        if (this.posdesc.equalsIgnoreCase("0"))
        {
          this.adtype = 1;
          return;
        }
        String[] arrayOfString = this.posdesc.split("/");
        if (arrayOfString != null)
        {
          this.adtype = Integer.valueOf(arrayOfString[1]).intValue();
          if (this.adtype == 0)
          {
            this.adtype = 1;
            return;
          }
          if (this.adtype == 1)
            this.adtype = 2;
          while (true)
          {
            this.categoryId = Integer.valueOf(arrayOfString[2]).intValue();
            if (!isBanner())
              break;
            this.bannerPos = Integer.valueOf(arrayOfString[3]).intValue();
            return;
            if (this.adtype == 2)
              this.adtype = 3;
            else if (this.adtype == 4)
              this.adtype = 4;
            else if (this.adtype == 5)
              this.adtype = 5;
          }
          if (isAudio())
          {
            if (arrayOfString[3].equalsIgnoreCase("begin"))
            {
              this.audioPos = 4;
              return;
            }
            this.audioPos = 5;
            return;
          }
          if (isADChannel())
          {
            this.channelId = Integer.valueOf(arrayOfString[3]).intValue();
            return;
          }
          if (isSection())
          {
            this.sectionPosition = Integer.valueOf(arrayOfString[3]).intValue();
            if (this.sectionPosition > 0)
              this.sectionPosition -= 1;
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.AdPos
 * JD-Core Version:    0.6.2
 */