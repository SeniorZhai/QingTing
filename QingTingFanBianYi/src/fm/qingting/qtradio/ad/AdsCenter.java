package fm.qingting.qtradio.ad;

public class AdsCenter
{
  private static AdsCenter _instance = null;

  public static AdsCenter getInstance()
  {
    if (_instance == null)
      _instance = new AdsCenter();
    return _instance;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ad.AdsCenter
 * JD-Core Version:    0.6.2
 */