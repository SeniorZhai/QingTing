package fm.qingting.qtradio.data;

public class WeiboProfile
{
  private static WeiboProfile instance;
  private String appKey = null;
  private String appSecret = null;
  private String redirectUrl = "http://weibo.callback.qingting.fm";
  private String topWid = "";
  private String uniad = "";

  public static WeiboProfile getInstance()
  {
    if (instance == null)
      instance = new WeiboProfile();
    return instance;
  }

  public String getAppKey()
  {
    return this.appKey;
  }

  public String getAppSecret()
  {
    return this.appSecret;
  }

  public String getGlobalTopWid()
  {
    return this.topWid;
  }

  public String getRedirectUrl()
  {
    return this.redirectUrl;
  }

  public String getUniad()
  {
    return this.uniad;
  }

  public void setAppKey(String paramString)
  {
    this.appKey = paramString;
  }

  public void setAppSecret(String paramString)
  {
    this.appSecret = paramString;
  }

  public void setGlobalTopWid(String paramString)
  {
    this.topWid = paramString;
  }

  public void setRedirectUrl(String paramString)
  {
    this.redirectUrl = paramString;
  }

  public void setUniad(String paramString)
  {
    this.uniad = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.WeiboProfile
 * JD-Core Version:    0.6.2
 */