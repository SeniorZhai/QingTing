package fm.qingting.qtradio.model.advertisement;

import java.io.Serializable;

public class QTAdvertisementInfo
  implements Serializable
{
  private static final long serialVersionUID = -145445545454545465L;
  public String adDescription;
  public String adDownload_url;
  public String adID;
  public String adLogo_url;
  public String adThumb;
  public String adTitle;

  public QTAdvertisementInfo()
  {
  }

  public QTAdvertisementInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.adID = paramString1;
    this.adTitle = paramString2;
    this.adThumb = paramString3;
    this.adDownload_url = paramString4;
    this.adLogo_url = paramString5;
    this.adDescription = paramString6;
  }

  public String geturlparameter()
  {
    return "/ad?adid=" + this.adID + "";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.advertisement.QTAdvertisementInfo
 * JD-Core Version:    0.6.2
 */