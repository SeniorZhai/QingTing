package fm.qingting.qtradio.baidu;

import com.google.protobuf.ByteString;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import java.util.ArrayList;
import java.util.List;
import mobads.apiv5.BaiduMobadsApiV50.Ad;
import mobads.apiv5.BaiduMobadsApiV50.MaterialMeta;
import mobads.apiv5.BaiduMobadsApiV50.MaterialMeta.InteractionType;
import mobads.apiv5.BaiduMobadsApiV50.MobadsResponse;

public class BDResponse
{
  private List<AdvertisementItemNode> lstAdvNodes = null;
  private BaiduApi.BDResponseListener mListener = null;

  private AdvertisementItemNode parseAD(BaiduMobadsApiV50.Ad paramAd)
  {
    if (paramAd != null)
    {
      paramAd = paramAd.getMaterialMeta();
      if ((paramAd != null) && (paramAd.getInteractionType() != BaiduMobadsApiV50.MaterialMeta.InteractionType.DOWNLOAD));
    }
    else
    {
      return null;
    }
    AdvertisementItemNode localAdvertisementItemNode = new AdvertisementItemNode();
    localAdvertisementItemNode.landing = paramAd.getClickUrl();
    if (paramAd.getImageSrcCount() > 0)
      localAdvertisementItemNode.image = ((String)paramAd.getImageSrcList().get(0));
    if (paramAd.getDescriptionCount() > 0)
      localAdvertisementItemNode.desc = ("广告:" + paramAd.getTitle().toStringUtf8());
    if (paramAd.getWinNoticeUrlCount() > 0)
      localAdvertisementItemNode.imageTracking = paramAd.getWinNoticeUrlList();
    return localAdvertisementItemNode;
  }

  public List<AdvertisementItemNode> getAdvNodes()
  {
    return this.lstAdvNodes;
  }

  public BaiduApi.BDResponseListener getListener()
  {
    return this.mListener;
  }

  public List<AdvertisementItemNode> parseResponse(byte[] paramArrayOfByte)
  {
    while (true)
    {
      int i;
      try
      {
        paramArrayOfByte = BaiduMobadsApiV50.MobadsResponse.parseFrom(paramArrayOfByte);
        if (paramArrayOfByte != null)
        {
          paramArrayOfByte = paramArrayOfByte.getAdsList();
          if ((paramArrayOfByte != null) && (paramArrayOfByte.size() > 0))
          {
            this.lstAdvNodes = new ArrayList();
            i = 0;
            if (i < paramArrayOfByte.size())
            {
              AdvertisementItemNode localAdvertisementItemNode = parseAD((BaiduMobadsApiV50.Ad)paramArrayOfByte.get(i));
              if (localAdvertisementItemNode == null)
                break label93;
              this.lstAdvNodes.add(localAdvertisementItemNode);
              break label93;
            }
            paramArrayOfByte = this.lstAdvNodes;
            return paramArrayOfByte;
          }
        }
      }
      catch (Exception paramArrayOfByte)
      {
      }
      return null;
      label93: i += 1;
    }
  }

  public void setListener(BaiduApi.BDResponseListener paramBDResponseListener)
  {
    this.mListener = paramBDResponseListener;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.baidu.BDResponse
 * JD-Core Version:    0.6.2
 */