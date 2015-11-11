package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RadioNode;
import fm.qingting.qtradio.view.channelcategoryview.RadioChannelView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import java.util.List;

public class RadioChannelsListController extends ViewController
  implements INavigationBarListener, InfoManager.ISubscribeEventListener
{
  private NavigationBarView barTempView;
  private RadioChannelView channelsView;
  private RadioNode mNode;

  public RadioChannelsListController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "radiochannellist";
    this.channelsView = new RadioChannelView(paramContext);
    attachView(this.channelsView);
    this.barTempView = new NavigationBarView(getContext());
    this.barTempView.setLeftItem(0);
    this.barTempView.setBarListener(this);
    setNavigationBar(this.barTempView);
  }

  private void setData(List<Node> paramList)
  {
    this.channelsView.update("setData", paramList);
  }

  private void setNavigationBar()
  {
    if (this.mNode == null)
      return;
    this.barTempView.setTitleItem(new NavigationBarItem(this.mNode.mTitle));
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((RadioNode)paramObject);
      setNavigationBar();
      if (this.mNode != null)
        break label29;
    }
    label29: 
    do
    {
      return;
      paramString = this.mNode.mLstChannelNodes;
    }
    while (paramString == null);
    setData(paramString);
  }

  public void controllerDidPopped()
  {
    this.channelsView.close(false);
    this.barTempView.close(false);
    super.controllerDidPopped();
  }

  public boolean hasMiniPlayer()
  {
    return true;
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    case 3:
    default:
      return;
    case 2:
    }
    ControllerManager.getInstance().popLastController();
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_LIVE_CHANNELS_BYATTR"));
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.RadioChannelsListController
 * JD-Core Version:    0.6.2
 */