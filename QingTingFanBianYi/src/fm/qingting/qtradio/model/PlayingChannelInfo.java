package fm.qingting.qtradio.model;

import java.util.ArrayList;
import java.util.List;

public class PlayingChannelInfo
{
  public String PlayingProgram = null;
  public String channelid = "";
  public List<String> djnames = new ArrayList();

  public PlayingChannelInfo()
  {
  }

  public PlayingChannelInfo(String paramString1, String paramString2, List<String> paramList)
  {
    this.channelid = paramString1;
    this.PlayingProgram = paramString2;
    this.djnames = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayingChannelInfo
 * JD-Core Version:    0.6.2
 */