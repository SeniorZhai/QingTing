package fm.qingting.qtradio.ad.platforms.mediav.model;

import java.util.ArrayList;

public abstract interface IMediaVListener
{
  public abstract void onMediaVNativeAdResponse(MediaVRequest paramMediaVRequest, ArrayList<MediaVNativeAd> paramArrayList);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.platforms.mediav.model.IMediaVListener
 * JD-Core Version:    0.6.2
 */