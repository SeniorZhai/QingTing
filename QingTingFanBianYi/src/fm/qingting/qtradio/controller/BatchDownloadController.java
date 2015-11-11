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
import fm.qingting.qtradio.view.scheduleview.BatchDownloadView;

public class BatchDownloadController extends ViewController
  implements INavigationBarListener, DownLoadInfoNode.IDownloadInfoEventListener
{
  private NavigationBarView barTopView;
  private int mChannelId;
  private Node mChannelNode;
  private boolean mCheckNew = false;
  private BatchDownloadView mainView;

  public BatchDownloadController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "batchdownload";
    this.mainView = new BatchDownloadView(paramContext);
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
      if (paramObject == null);
      do
      {
        return;
        this.mChannelNode = paramObject;
      }
      while (!paramObject.nodeName.equalsIgnoreCase("channel"));
      this.mainView.update(paramString, paramObject);
      this.mChannelId = ((ChannelNode)paramObject).channelId;
      return;
    }
    if (paramString.equalsIgnoreCase("checkNew"))
    {
      this.mainView.update(paramString, paramObject);
      this.mCheckNew = true;
      return;
    }
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    if ((this.mCheckNew) && (this.mChannelId != 0));
    super.controllerDidPopped();
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if ((paramInt == 1) || (paramInt == 2) || (paramInt == 4) || (paramInt == 8))
    {
      this.mainView.update("refreshList", null);
      if (paramInt == 8)
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
 * Qualified Name:     fm.qingting.qtradio.controller.BatchDownloadController
 * JD-Core Version:    0.6.2
 */