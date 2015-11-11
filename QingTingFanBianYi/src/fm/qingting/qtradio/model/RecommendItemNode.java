package fm.qingting.qtradio.model;

import android.text.TextUtils;
import fm.qingting.utils.ScreenConfiguration;
import fm.qingting.utils.TimeUtil;

public class RecommendItemNode extends Node
{
  public String belongName = "";
  public String briefName = "";
  public int categoryPos = 1;
  public String desc = "";
  public String id = "";
  public transient boolean isAds = false;
  public boolean isweb = false;
  private String largeThumb;
  public transient AdvertisementItemNode mAdNode = null;
  public String mAttributesPath;
  public int mCategoryId;
  public transient int mClickCnt = 0;
  public transient Node mNode;
  private long mUpdateTime = 0L;
  private String mediumThumb;
  public String name = "";
  public int ratingStar = -1;
  public int redirect = 0;
  public int redirectToVirtualChannels = 0;
  public int sectionId = 0;
  private transient long showLinkTime;
  private String smallThumb;
  public transient int time;
  public String update_time;

  public RecommendItemNode()
  {
    this.nodeName = "recommenditem";
  }

  private static boolean isEmpty(String paramString)
  {
    return TextUtils.isEmpty(paramString);
  }

  public String getApproximativeThumb()
  {
    return getApproximativeThumb(0, 0, false);
  }

  public String getApproximativeThumb(int paramInt1, int paramInt2)
  {
    return getApproximativeThumb(paramInt1, paramInt2, true);
  }

  public String getApproximativeThumb(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = Math.max(paramInt1, paramInt2);
    if (paramBoolean)
    {
      paramInt1 = ScreenConfiguration.sImageBoundMedium;
      if (!paramBoolean)
        break label51;
      paramInt2 = ScreenConfiguration.sImageBoundSmall;
    }
    while (true)
      if (i >= paramInt1)
      {
        if (!isEmpty(this.largeThumb))
        {
          return this.largeThumb;
          paramInt1 = 600;
          break;
          label51: paramInt2 = 300;
          continue;
        }
        if (!isEmpty(this.mediumThumb))
          return this.mediumThumb;
        return this.smallThumb;
      }
    if (i > paramInt2)
    {
      if (!isEmpty(this.mediumThumb))
        return this.mediumThumb;
      return this.smallThumb;
    }
    return this.smallThumb;
  }

  public Node getDetail()
  {
    return this.mNode;
  }

  public long getShowLink()
  {
    return this.showLinkTime;
  }

  public String getSmallThumb()
  {
    return this.smallThumb;
  }

  public long getUpdateTime()
  {
    if (this.update_time == null)
      return 0L;
    if (this.mUpdateTime > 0L)
      return this.mUpdateTime;
    this.mUpdateTime = TimeUtil.dateToMS(this.update_time);
    return this.mUpdateTime;
  }

  public boolean isRecommendShare()
  {
    return (this.parent != null) && (this.parent.nodeName.equalsIgnoreCase("recommendcategory")) && (((RecommendCategoryNode)this.parent).sectionId == Integer.valueOf(ShareInfoNode.SHARE_CATEGORY_ID).intValue());
  }

  public boolean noThumb()
  {
    return (this.smallThumb == null) && (this.mediumThumb == null) && (this.largeThumb == null);
  }

  public void setDetail(Node paramNode)
  {
    if (paramNode != null)
    {
      this.mNode = paramNode;
      this.mNode.parent = this;
    }
  }

  public void setLargeThumb(String paramString)
  {
    this.largeThumb = paramString;
  }

  public void setMediumThumb(String paramString)
  {
    this.mediumThumb = paramString;
  }

  public void setShowLink(long paramLong)
  {
    this.showLinkTime = paramLong;
  }

  public void setSmallThumb(String paramString)
  {
    this.smallThumb = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RecommendItemNode
 * JD-Core Version:    0.6.2
 */