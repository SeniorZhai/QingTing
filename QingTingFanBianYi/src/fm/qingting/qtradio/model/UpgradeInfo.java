package fm.qingting.qtradio.model;

public class UpgradeInfo
{
  public String msg = "";
  public boolean upgradeFromUM = false;
  public String url = "";

  public boolean shouldUpgrade()
  {
    if ((this.url != null) && (!this.url.equalsIgnoreCase("")) && (this.msg != null) && (!this.msg.equalsIgnoreCase("")));
    for (int i = 1; ; i = 0)
      return i | this.upgradeFromUM;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.UpgradeInfo
 * JD-Core Version:    0.6.2
 */