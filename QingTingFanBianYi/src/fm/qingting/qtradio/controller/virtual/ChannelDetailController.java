package fm.qingting.qtradio.controller.virtual;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.RecorderManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.virtualchannels.ChannelDetailView;
import fm.qingting.utils.QTMSGManage;

public class ChannelDetailController extends ViewController
  implements INavigationBarListener
{
  public static final String NAME = "channeldetail";
  private NavigationBarView barTopView;
  private ChannelNode mNode;
  private ChannelDetailView mainView;

  public ChannelDetailController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "channeldetail";
    this.mainView = new ChannelDetailView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setRecordItem("录音");
    this.barTopView.setRightItemVisibility(4);
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  private void setNavi()
  {
    if (this.mNode == null)
      return;
    this.barTopView.setTitleItem(new NavigationBarItem(this.mNode.title));
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((ChannelNode)paramObject);
      this.mainView.update(paramString, paramObject);
      setNavi();
      if (this.mNode.channelId == 66106)
        QTMSGManage.getInstance().sendStatistcsMessage("opencampusblabla");
      QTMSGManage.getInstance().sendStatistcsMessage("AlbumViewAction", "enter");
      if (this.mNode.recordEnable)
        config("refreshUploadView", null);
    }
    int i;
    do
    {
      do
      {
        return;
        i = this.mNode.audienceCnt;
      }
      while (i <= 0);
      float f;
      if (i > 10000)
        f = i / 1000 * 1000 / 10000.0F;
      for (paramString = "听众:" + String.valueOf(f) + "万"; ; paramString = "听众:" + String.valueOf(i))
      {
        this.barTopView.setRightItem(paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("refresh"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("refreshUploadView"))
      {
        if ((this.mNode.recordEnable) && (!RecorderManager.getInstance().isUploading()))
          this.barTopView.setRightItemVisibility(0);
        while (true)
        {
          this.mainView.update("refreshUploadView", Integer.valueOf(this.mNode.channelId));
          return;
          this.barTopView.setRightItemVisibility(4);
        }
      }
      if (!paramString.equalsIgnoreCase("syncdata"))
        break;
    }
    while (this.mNode == null);
    paramString = InfoManager.getInstance().root().getCurrentPlayingNode();
    int j;
    if (paramString != null)
    {
      if (!paramString.nodeName.equalsIgnoreCase("program"))
        break label417;
      paramString = paramString.parent;
      if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("channel")))
      {
        i = ((ChannelNode)paramString).categoryId;
        j = ((ChannelNode)paramString).channelType;
        if ((i == this.mNode.categoryId) && (j == this.mNode.channelType) && (((ChannelNode)paramString).channelId != this.mNode.channelId))
        {
          this.mNode = ((ChannelNode)paramString);
          setNavi();
          this.mainView.update("setData", this.mNode);
        }
      }
    }
    while (true)
    {
      setNavi();
      return;
      label417: if (paramString.nodeName.equalsIgnoreCase("channel"))
      {
        i = ((ChannelNode)paramString).categoryId;
        j = ((ChannelNode)paramString).channelType;
        if ((i == this.mNode.categoryId) && (j == this.mNode.channelType) && (((ChannelNode)paramString).channelId != this.mNode.channelId))
        {
          this.mNode = ((ChannelNode)paramString);
          setNavi();
          this.mainView.update("setData", this.mNode);
        }
      }
    }
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("channelNode"))
      return this.mNode;
    return super.getValue(paramString, paramObject);
  }

  public boolean hasMiniPlayer()
  {
    return true;
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 2:
    case 3:
    }
    do
    {
      return;
      ControllerManager.getInstance().popLastController();
      return;
    }
    while ((this.mNode == null) || (!this.mNode.recordEnable));
    ControllerManager.getInstance().openUploadVoiceController(this.mNode);
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("resetNavi"))
      setNavi();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.virtual.ChannelDetailController
 * JD-Core Version:    0.6.2
 */