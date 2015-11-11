package fm.qingting.qtradio.controller.virtual;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.RecorderManager;
import fm.qingting.qtradio.manager.RecorderManager.RecorderHandler;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.popviews.AlertParam;
import fm.qingting.qtradio.view.popviews.AlertParam.Builder;
import fm.qingting.qtradio.view.popviews.AlertParam.OnButtonClickListener;
import fm.qingting.qtradio.view.virtualchannels.UploadVoiceView;

public class UploadVoiceController extends ViewController
  implements INavigationBarListener, InfoManager.ISubscribeEventListener, RecorderManager.RecorderHandler
{
  private NavigationBarView barTopView;
  private ChannelNode mNode;
  private UploadVoiceView mainView;

  public UploadVoiceController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "uploadvoice";
    this.mainView = new UploadVoiceView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setTitleItem(new NavigationBarItem("录音"));
    this.barTopView.setLeftItem(0);
    this.barTopView.setRightItem("重录", SkinManager.getUploadPageElementColor(), Color.parseColor("#2bcfc0"));
    this.barTopView.setRightItemVisibility(4);
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mNode = ((ChannelNode)paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      performStop(true);
      return;
    case 3:
    }
    restartRecording();
  }

  public void onMonitorAmpChanged(float paramFloat)
  {
    this.mainView.update("onAmpChanged", Float.valueOf(paramFloat));
  }

  public void onNotification(String paramString)
  {
  }

  public void onRecordingSecond(Long paramLong)
  {
    this.mainView.update("setRecordingTimeSec", paramLong);
  }

  public void onRecordingStart()
  {
    this.barTopView.setRightItemVisibility(4);
    this.mainView.update("setStep", Integer.valueOf(1));
  }

  public void onRecordingStop()
  {
    this.barTopView.setRightItemVisibility(0);
    this.mainView.update("setStep", Integer.valueOf(2));
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onReplaySecond(Long paramLong)
  {
    this.mainView.update("setReplayTimeMSec", paramLong);
  }

  public void onReplayStop()
  {
    onRecordingStop();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("startRecording"))
      RecorderManager.getInstance().startRecording(this);
    if (paramString.equalsIgnoreCase("stopRecording"))
      RecorderManager.getInstance().stopRecording();
    if (paramString.equalsIgnoreCase("startReplay"))
      RecorderManager.getInstance().startReplay(this);
    if (paramString.equalsIgnoreCase("pauseReplay"))
      RecorderManager.getInstance().pauseReplay();
    if (paramString.equalsIgnoreCase("publishRecording"))
      this.mainView.update("showInput", null);
    if (paramString.equalsIgnoreCase("uploadVoice"))
    {
      ControllerManager.getInstance().popLastController();
      RecorderManager.getInstance().uploadCachedFile((String)paramObject2);
    }
  }

  public boolean performInit()
  {
    boolean bool = false;
    String str = RecorderManager.getInstance().init(String.valueOf(this.mNode.channelId), 180);
    if (str.equalsIgnoreCase(""))
    {
      PlayerAgent.getInstance().stop();
      bool = true;
    }
    do
    {
      return bool;
      if (str.equalsIgnoreCase("requireMem"))
        Toast.makeText(getContext(), "手机存储空间不足，请清理后再进行录音。", 0).show();
      if (str.equalsIgnoreCase("requireNet"))
        Toast.makeText(getContext(), "当前网络不可用，请确认联网后再进行录音。", 0).show();
    }
    while (!str.equalsIgnoreCase("requireLogin"));
    EventDispacthManager.getInstance().dispatchAction("showLogin", null);
    return false;
  }

  public void performStop(boolean paramBoolean)
  {
    this.mainView.update("hideInput", null);
    RecorderManager.getInstance().cleanup();
    if (RecorderManager.getInstance().getCachedFileDurationMSec() > 5000L)
    {
      onRecordingStop();
      AlertParam localAlertParam = new AlertParam.Builder().setMessage("您的录音还未发布，是否要保存录音？").addButton("不保存").addButton("保存").setListener(new AlertParam.OnButtonClickListener()
      {
        public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          switch (paramAnonymousInt)
          {
          default:
            return;
          case 0:
            EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
            RecorderManager.getInstance().deleteCachedFile();
            ControllerManager.getInstance().popLastController();
            EventDispacthManager.getInstance().dispatchAction("refreshUploadView", null);
            return;
          case 1:
          }
          EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
          ControllerManager.getInstance().popLastController();
          EventDispacthManager.getInstance().dispatchAction("refreshUploadView", null);
        }
      }).create();
      EventDispacthManager.getInstance().dispatchAction("showAlert", localAlertParam);
    }
    do
    {
      return;
      restartRecording();
    }
    while (!paramBoolean);
    ControllerManager.getInstance().popLastController();
    EventDispacthManager.getInstance().dispatchAction("refreshUploadView", null);
  }

  public void restartRecording()
  {
    RecorderManager.getInstance().cleanup();
    RecorderManager.getInstance().deleteCachedFile();
    this.barTopView.setRightItemVisibility(4);
    this.mainView.update("setStep", Integer.valueOf(0));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.virtual.UploadVoiceController
 * JD-Core Version:    0.6.2
 */