package fm.qingting.qtradio.qtfmplayer;

public class PlayerMode
{
  public static int FMMode = 2;
  public static int FMMpegMode = 1;
  public static int FMREPLAY = 3;
  private static PlayerMode instance;
  private int model = 0;

  public static PlayerMode getInstance()
  {
    if (instance == null)
      instance = new PlayerMode();
    return instance;
  }

  public int getModel()
  {
    return this.model;
  }

  public void setMode(int paramInt)
  {
    this.model = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.qtfmplayer.PlayerMode
 * JD-Core Version:    0.6.2
 */