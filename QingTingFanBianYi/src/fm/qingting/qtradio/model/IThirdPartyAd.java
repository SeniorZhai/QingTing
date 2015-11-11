package fm.qingting.qtradio.model;

public abstract interface IThirdPartyAd
{
  public abstract int getCategoryId();

  public abstract String getPlatformName();

  public abstract void onClick();

  public abstract void onShow();

  public abstract void setCategoryId(int paramInt);

  public abstract AdvertisementItemNode toAdvertisementItemNode();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.IThirdPartyAd
 * JD-Core Version:    0.6.2
 */