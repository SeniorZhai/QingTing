package fm.qingting.qtradio.model;

import android.util.Log;
import fm.qingting.qtradio.helper.ChannelHelper;

public class PlayHistoryNode extends Node
  implements InfoManager.ISubscribeEventListener
{
  static final String TAG = "PlayHistoryNode";
  public int categoryId;
  public int channelId;
  public String channelName;
  public String channelThumb;
  public long playContent = 0L;
  public Node playNode;
  public long playTime;
  public int programId;
  public int subCatId;

  public PlayHistoryNode()
  {
    this.nodeName = "playhistory";
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RVPI"))
    {
      Log.d("PlayHistoryNode", "sym:获取到" + this.channelName + "的programNode:" + this.programId);
      this.playNode = ChannelHelper.getInstance().getChannel(this.channelId, (int)this.playContent, this.channelName).getProgramNode(this.programId);
      this.playNode.parent = this;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.PlayHistoryNode
 * JD-Core Version:    0.6.2
 */