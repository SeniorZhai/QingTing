package fm.qingting.qtradio.ad.platforms.mediav.model;

import fm.qingting.qtradio.ad.AdPos;

public class MediaVRequest
{
  private AdPos mAdPos;
  private int mCategoryId = 0;
  private String[] mKeywords;
  private String mRequestId;

  public MediaVRequest(AdPos paramAdPos, String[] paramArrayOfString)
  {
    this.mAdPos = paramAdPos;
    this.mKeywords = paramArrayOfString;
  }

  public MediaVRequest(AdPos paramAdPos, String[] paramArrayOfString, int paramInt, String paramString)
  {
    this.mAdPos = paramAdPos;
    this.mRequestId = paramString;
    this.mCategoryId = paramInt;
  }

  public AdPos getAdPos()
  {
    return this.mAdPos;
  }

  public int getCategoryId()
  {
    return this.mCategoryId;
  }

  public String[] getKeywords()
  {
    return this.mKeywords;
  }

  public String getRequestId()
  {
    return this.mRequestId;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.platforms.mediav.model.MediaVRequest
 * JD-Core Version:    0.6.2
 */