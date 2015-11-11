package fm.qingting.qtradio.model;

public class Broadcaster
{
  public String bgphoto = "";
  public int digcount = -2147483648;
  public boolean hasRadio = false;
  public String id = "";
  public boolean isVip = false;
  public String nick = "";
  public String onDemandCatid = null;
  public String onDemandCid = null;
  public String vname = "";
  public String vuid = "";

  public Broadcaster()
  {
  }

  public Broadcaster(String paramString1, String paramString2)
  {
    this.nick = paramString1;
    this.vuid = paramString2;
  }

  public Broadcaster(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, int paramInt, String paramString5)
  {
    this.id = paramString1;
    this.nick = paramString2;
    this.vuid = paramString3;
    this.vname = paramString4;
    this.isVip = paramBoolean;
    this.digcount = paramInt;
    this.bgphoto = paramString5;
  }

  public void Clone(Broadcaster paramBroadcaster)
  {
    this.isVip = paramBroadcaster.isVip;
    this.id = paramBroadcaster.id;
    this.nick = paramBroadcaster.nick;
    this.vuid = paramBroadcaster.vuid;
    this.vname = paramBroadcaster.vname;
    this.digcount = paramBroadcaster.digcount;
    this.bgphoto = paramBroadcaster.bgphoto;
    this.hasRadio = paramBroadcaster.hasRadio;
    this.onDemandCid = paramBroadcaster.onDemandCid;
    this.onDemandCatid = paramBroadcaster.onDemandCatid;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.Broadcaster
 * JD-Core Version:    0.6.2
 */