package fm.qingting.qtradio.model;

public class PlayerInfo
{
  private static PlayerInfo instance = null;

  public static PlayerInfo getInstance()
  {
    try
    {
      if (instance == null)
        instance = new PlayerInfo();
      PlayerInfo localPlayerInfo = instance;
      return localPlayerInfo;
    }
    finally
    {
    }
  }

  public boolean isPlaying()
  {
    return false;
  }

  public boolean isPlayingProgramInfoNull()
  {
    return true;
  }

  public boolean isSameAlbumProgram()
  {
    return false;
  }

  public boolean isSameLiveProgram()
  {
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayerInfo
 * JD-Core Version:    0.6.2
 */