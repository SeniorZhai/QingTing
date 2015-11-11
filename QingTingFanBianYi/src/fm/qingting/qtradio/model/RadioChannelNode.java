package fm.qingting.qtradio.model;

import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.ProgramHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RadioChannelNode extends Node
{
  public transient int ContentType = 0;
  public int channelId;
  public String channelName;
  public String freq;
  public transient boolean hasLoadPrograms = false;
  public transient List<ProgramNode> lstProgramNodes = new ArrayList();
  public transient ProgramNode programNodeError = null;

  public RadioChannelNode()
  {
    this.nodeName = "radiochannel";
    init();
  }

  private void init()
  {
    this.freq = "";
    this.channelId = 0;
    this.channelName = "FM";
  }

  public ChannelNode convertToChannelNode()
  {
    return ChannelHelper.getInstance().getChannel(this.channelId, 0);
  }

  public ProgramNode getCurrentPlayingProgramNode(long paramLong)
  {
    if (this.lstProgramNodes != null)
    {
      paramLong /= 1000L;
      int i = 0;
      while (i < this.lstProgramNodes.size())
      {
        long l1 = ((ProgramNode)this.lstProgramNodes.get(i)).getAbsoluteStartTime();
        long l2 = ((ProgramNode)this.lstProgramNodes.get(i)).getAbsoluteEndTime();
        if ((l1 <= paramLong) && (l2 > paramLong))
          return (ProgramNode)this.lstProgramNodes.get(i);
        i += 1;
      }
    }
    return ProgramHelper.getInstance().getProgramTempNode();
  }

  public String getProgramsDuraion()
  {
    return String.valueOf(Calendar.getInstance().get(7));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RadioChannelNode
 * JD-Core Version:    0.6.2
 */