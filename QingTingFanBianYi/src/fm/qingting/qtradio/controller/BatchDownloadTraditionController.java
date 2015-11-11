package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.manager.CollectionRemindManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.scheduleview.BatchDownloadTraditionView;

public class BatchDownloadTraditionController extends ViewController
  implements INavigationBarListener, DownLoadInfoNode.IDownloadInfoEventListener
{
  private NavigationBarView barTopView;
  private ChannelNode mChannelNode;
  private BatchDownloadTraditionView mainView;

  public BatchDownloadTraditionController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "batchdownload_tradition";
    this.mainView = new BatchDownloadTraditionView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("批量下载"));
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
    InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramObject = (Node)paramObject;
      if (paramObject != null)
        break label19;
    }
    label19: 
    while (!paramObject.nodeName.equalsIgnoreCase("channel"))
      return;
    this.mChannelNode = ((ChannelNode)paramObject);
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    super.controllerDidPopped();
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if ((paramInt == 1) || (paramInt == 2) || (paramInt == 4) || (paramInt == 8))
    {
      this.mainView.update("refreshList", null);
      if ((paramInt == 8) && (this.mChannelNode != null))
      {
        CollectionRemindManager.setSource(1);
        EventDispacthManager.getInstance().dispatchAction("showEducationFav", this.mChannelNode);
      }
    }
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.BatchDownloadTraditionController
 * JD-Core Version:    0.6.2
 */