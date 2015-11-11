package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.manager.EventDispacthManager.IActionEventHandler;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RingToneInfoNode;
import fm.qingting.qtradio.model.RingToneNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.clock.djringtone.DjRingtoneListView;
import java.util.List;

public class AlarmDjRingtoneSettingController extends ViewController
  implements INavigationBarListener, DownLoadInfoNode.IDownloadInfoEventListener, EventDispacthManager.IActionEventHandler
{
  private DjRingtoneListView mainView;

  public AlarmDjRingtoneSettingController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "djringtonesetting";
    this.mainView = new DjRingtoneListView(paramContext);
    attachView(this.mainView);
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("闹铃声"));
    paramContext.setRightItem(2);
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
    EventDispacthManager.getInstance().addListener(this);
    InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
  }

  private void confirmRingtone()
  {
    int i = ((Integer)this.mainView.getValue("checkIndex", null)).intValue();
    if (i == -1)
    {
      dispatchEvent("pickedRingtone", null);
      ControllerManager.getInstance().popLastController();
    }
    RingToneNode localRingToneNode;
    do
    {
      do
        return;
      while ((InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes == null) || (InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.size() < i));
      localRingToneNode = (RingToneNode)InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.get(i);
    }
    while (localRingToneNode == null);
    if (localRingToneNode.ringType.equalsIgnoreCase("online"))
    {
      EventDispacthManager.getInstance().dispatchAction("showRingtoneLoadingView", null);
      startDownload(localRingToneNode);
      return;
    }
    dispatchEvent("pickedRingtone", localRingToneNode);
    ControllerManager.getInstance().popLastController();
  }

  private void startDownload(Node paramNode)
  {
    InfoManager.getInstance().root().mDownLoadInfoNode.startDownLoadRing(paramNode);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mainView.update(paramString, InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes);
    while (!paramString.equalsIgnoreCase("setRingtone"))
      return;
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    EventDispacthManager.getInstance().removeListener(this);
    dispatchEvent("resetFlag", null);
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE)
      PlayerAgent.getInstance().stop();
    System.gc();
    super.controllerDidPopped();
  }

  public void onAction(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("retryDownload"))
      confirmRingtone();
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    do
    {
      do
        return;
      while ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("ringtone")));
      EventDispacthManager.getInstance().dispatchAction("ringtoneLoadComplete", null);
      dispatchEvent("pickedRingtone", paramNode);
      ControllerManager.getInstance().popLastController();
      return;
    }
    while ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("ringtone")));
    EventDispacthManager.getInstance().dispatchAction("ringtoneLoadFailed", null);
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      ControllerManager.getInstance().popLastController();
      return;
    case 3:
    }
    confirmRingtone();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("stopPreview"))
      if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE)
        PlayerAgent.getInstance().stop();
    do
    {
      int i;
      do
      {
        do
          return;
        while (!paramString.equalsIgnoreCase("startPreview"));
        i = ((Integer)paramObject2).intValue();
      }
      while ((i <= -1) || (InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes == null) || (InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.size() <= i));
      paramObject1 = (RingToneNode)InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.get(i);
    }
    while (paramObject1 == null);
    PlayerAgent.getInstance().play(paramObject1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.AlarmDjRingtoneSettingController
 * JD-Core Version:    0.6.2
 */