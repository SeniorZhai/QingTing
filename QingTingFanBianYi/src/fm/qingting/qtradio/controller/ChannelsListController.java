package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RadioNode;
import fm.qingting.qtradio.view.channelcategoryview.TraChannelView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import java.util.List;

public class ChannelsListController extends ViewController
  implements INavigationBarListener, InfoManager.ISubscribeEventListener
{
  private NavigationBarView barTempView;
  private TraChannelView channelsView;
  private Node mNode;

  public ChannelsListController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "channellist";
    this.channelsView = new TraChannelView(paramContext);
    attachView(this.channelsView);
    this.barTempView = new NavigationBarView(getContext());
    this.barTempView.setLeftItem(0);
    this.barTempView.setBarListener(this);
    setNavigationBar(this.barTempView);
  }

  private void setData(List<ChannelNode> paramList)
  {
    this.channelsView.update("setData", paramList);
  }

  private void setNavigationBar()
  {
    if (this.mNode == null)
      return;
    Object localObject = null;
    if (this.mNode.nodeName.equalsIgnoreCase("category"))
    {
      String str = ((CategoryNode)this.mNode).name;
      localObject = str;
      if (str != null)
      {
        localObject = str;
        if (!str.endsWith("台"))
          localObject = str + "台";
      }
    }
    while (true)
    {
      this.barTempView.setTitleItem(new NavigationBarItem((String)localObject));
      return;
      if (this.mNode.nodeName.equalsIgnoreCase("attribute"))
        localObject = ((Attribute)this.mNode).name;
      else if (this.mNode.nodeName.equalsIgnoreCase("radio"))
        localObject = ((RadioNode)this.mNode).mTitle;
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((Node)paramObject);
      setNavigationBar();
      if (this.mNode != null)
        break label29;
    }
    label29: 
    do
    {
      do
      {
        return;
        if (this.mNode.nodeName.equalsIgnoreCase("attribute"))
        {
          paramString = ((Attribute)this.mNode).getLstChannels();
          if (paramString == null)
          {
            ChannelHelper.getInstance().loadListLiveChannelNodes(((Attribute)this.mNode).getCatid(), String.valueOf(((Attribute)this.mNode).id), this);
            return;
          }
          setData(paramString);
          return;
        }
        if (this.mNode.nodeName.equalsIgnoreCase("category"))
        {
          paramString = ((CategoryNode)this.mNode).getLstChannels();
          if (paramString == null)
          {
            ChannelHelper.getInstance().loadListLiveChannelNodes(((CategoryNode)this.mNode).categoryId, ((CategoryNode)this.mNode).mAttributesPath, this);
            return;
          }
          setData(paramString);
          return;
        }
      }
      while (!this.mNode.nodeName.equalsIgnoreCase("radio"));
      paramString = ((RadioNode)this.mNode).mLstChannelNodes;
    }
    while (paramString == null);
    this.channelsView.update("setData", paramString);
  }

  public void controllerDidPopped()
  {
    this.channelsView.close(false);
    this.barTempView.close(false);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_LIVE_CHANNELS_BYATTR" });
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
    if (paramString.equalsIgnoreCase("RECV_LIVE_CHANNELS_BYATTR"))
    {
      if (!this.mNode.nodeName.equalsIgnoreCase("attribute"))
        break label45;
      paramString = ((Attribute)this.mNode).getLstChannels();
      if (paramString != null)
        setData(paramString);
    }
    label45: 
    do
    {
      do
        return;
      while (!this.mNode.nodeName.equalsIgnoreCase("category"));
      paramString = ((CategoryNode)this.mNode).getLstChannels();
    }
    while (paramString == null);
    setData(paramString);
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.ChannelsListController
 * JD-Core Version:    0.6.2
 */