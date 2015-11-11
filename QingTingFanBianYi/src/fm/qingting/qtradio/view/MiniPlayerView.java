package fm.qingting.qtradio.view;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.manager.EventDispacthManager.IActionEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.controller.personalcenter.MyDownloadProgramController;
import fm.qingting.qtradio.controller.virtual.ChannelDetailController;
import fm.qingting.qtradio.fm.IMediaEventListener;
import fm.qingting.qtradio.fm.PlayStatus;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.view.playview.AbsPlayButtonElement.State;
import fm.qingting.qtradio.wo.WoApiRequest;
import fm.qingting.qtradio.wo.WoApiRequest.OnOpenListener;
import fm.qingting.utils.OnPlayProcessListener;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class MiniPlayerView extends QtView
  implements IMediaEventListener, EventDispacthManager.IActionEventHandler, RootNode.IPlayInfoEventListener, OnPlayProcessListener, ViewElement.OnElementClickListener, WoApiRequest.OnOpenListener, InfoManager.ISubscribeEventListener
{
  private static final int ICON_SIZE = 92;
  private static final int sShadowHeight = 9;
  private final ViewLayout channelTitleLayout = this.standardLayout.createChildLT(430, 36, 100, 57, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout historyLayout = this.standardLayout.createChildLT(92, 92, 620, 9, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private TextViewElement mChannelTitleTe;
  private boolean mHasTop;
  private ButtonViewElement mHistoryElement;
  private ButtonViewElement mMenuElement;
  private PlayButtonElement mPlayElement;
  private ProcessBarElement mProcessBarElement;
  private TextViewElement mProgramTitleTe;
  private ImageViewElement mStateElement;
  private ImageViewElement mTrangleElement;
  private final ViewLayout menuLayout = this.standardLayout.createChildLT(92, 92, 0, 9, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout playLayout = this.standardLayout.createChildLT(92, 92, 530, 9, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout processLayout = this.standardLayout.createChildLT(720, 4, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout programTitleLayout = this.standardLayout.createChildLT(430, 36, 100, 21, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 102, 720, 102, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout stateLayout = this.standardLayout.createChildLT(50, 26, 112, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout trangleLayout = this.standardLayout.createChildLT(20, 10, 652, 9, ViewLayout.SCALE_FLAG_SLTCW);

  public MiniPlayerView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    setBackgroundResource(2130837853);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(855638016, 0);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mProcessBarElement = new ProcessBarElement(paramContext);
    addElement(this.mProcessBarElement);
    this.mMenuElement = new ButtonViewElement(paramContext);
    this.mMenuElement.setBackground(2130837843, 2130837841);
    addElement(this.mMenuElement, i);
    this.mMenuElement.setOnElementClickListener(this);
    this.mPlayElement = new PlayButtonElement(paramContext);
    addElement(this.mPlayElement, i);
    this.mPlayElement.setOnElementClickListener(this);
    this.mHistoryElement = new ButtonViewElement(paramContext);
    this.mHistoryElement.setBackground(2130837850, 2130837849);
    addElement(this.mHistoryElement, i);
    this.mHistoryElement.setOnElementClickListener(this);
    this.mTrangleElement = new ImageViewElement(paramContext);
    this.mTrangleElement.setImageRes(2130837852);
    addElement(this.mTrangleElement, i);
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    if ((localList != null) && (localList.size() > 1) && (ControllerManager.getInstance().getLastViewController() == null) && (InfoManager.getInstance().getTopHistory()))
    {
      this.mTrangleElement.setVisible(0);
      this.mHasTop = true;
      this.mStateElement = new ImageViewElement(paramContext);
      this.mStateElement.setImageRes(2130837851);
      this.mProgramTitleTe = new TextViewElement(paramContext);
      this.mProgramTitleTe.setMaxLineLimit(1);
      this.mProgramTitleTe.setColor(-10461088);
      addElement(this.mProgramTitleTe);
      this.mChannelTitleTe = new TextViewElement(paramContext);
      this.mChannelTitleTe.setMaxLineLimit(1);
      this.mChannelTitleTe.setColor(-1721737120);
      addElement(this.mChannelTitleTe);
      PlayerAgent.getInstance().addMediaEventListener(this);
      InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
      InfoManager.getInstance().registerSubscribeEventListener(this, "RPS");
      PlayProcessSyncUtil.getInstance().addListener(this);
      refreshPlayingInfo();
      changeViewStateByPlayState(getCurrentPlayStatus());
      if (!WoApiRequest.hasOpen())
        break label657;
      this.mStateElement.setImageRes(2130838008);
    }
    while (true)
    {
      EventDispacthManager.getInstance().addListener(this);
      return;
      this.mTrangleElement.setVisible(4);
      this.mHasTop = false;
      break;
      label657: WoApiRequest.addListener(this);
    }
  }

  private void changeViewStateByPlayState(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 1))
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.PLAY);
      return;
    }
    if (paramInt == 8192)
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.ERROR);
      return;
    }
    if ((paramInt == 4098) || (paramInt == 4101))
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.BUFFER);
      return;
    }
    this.mPlayElement.setState(AbsPlayButtonElement.State.PAUSE);
  }

  private int getCurrentPlayStatus()
  {
    int i = PlayerAgent.getInstance().getCurrentPlayStatus();
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
      if (!InfoManager.getInstance().root().isOpenFm());
    do
    {
      return 4096;
      return 0;
      if ((i == 30583) || (i == 0) || (i == 1))
        return 0;
    }
    while (i == 4096);
    return i;
  }

  private void playOrStop()
  {
    int j = 0;
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
      if (InfoManager.getInstance().root().isOpenFm())
      {
        PlayerAgent.getInstance().dispatchPlayStateInFM(0);
        FMManager.getInstance().turnOff();
        InfoManager.getInstance().root().tuneFM(false);
        QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "stop");
      }
    Node localNode;
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "play");
            localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
          }
          while (localNode == null);
          if (localNode.nodeName.equalsIgnoreCase("radiochannel"))
          {
            PlayerAgent.getInstance().startFM((RadioChannelNode)localNode);
            return;
          }
        }
        while (!localNode.nodeName.equalsIgnoreCase("program"));
        localNode = localNode.parent;
      }
      while ((localNode == null) || (!localNode.nodeName.equalsIgnoreCase("radiochannel")));
      PlayerAgent.getInstance().startFM((RadioChannelNode)localNode);
      return;
      if (PlayerAgent.getInstance().isPlaying())
      {
        PlayerAgent.getInstance().stop();
        QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "stop");
        return;
      }
      QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "play");
      localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    }
    while (localNode == null);
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
    {
      int i;
      if (localNode.nodeName.equalsIgnoreCase("radiochannel"))
        i = Integer.valueOf(((RadioChannelNode)localNode).freq).intValue();
      while (true)
      {
        if (i != 0)
          FMManager.getInstance().tune(i);
        PlayerAgent.getInstance().dispatchPlayStateInFM(4096);
        InfoManager.getInstance().root().setPlayingNode(localNode);
        InfoManager.getInstance().root().tuneFM(true);
        return;
        i = j;
        if (localNode.nodeName.equalsIgnoreCase("program"))
        {
          i = j;
          if (localNode.parent != null)
          {
            i = j;
            if (localNode.parent.nodeName.equalsIgnoreCase("radiochannel"))
              i = Integer.valueOf(((RadioChannelNode)localNode.parent).freq).intValue();
          }
        }
      }
    }
    PlayerAgent.getInstance().play(localNode);
  }

  private void refreshPlayingInfo()
  {
    Object localObject1 = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    String str1 = "节目加载中";
    String str2 = "节目加载中";
    if (localObject1 != null)
      str1 = ((ChannelNode)localObject1).title;
    Object localObject2;
    if (localNode == null)
    {
      if (ControllerManager.getInstance().playedLastChannel())
      {
        localObject1 = "节目加载中";
        localObject2 = str1;
        this.mProgramTitleTe.setText((String)localObject1);
        this.mChannelTitleTe.setText((String)localObject2);
      }
      return;
    }
    if (localNode.nodeName.equalsIgnoreCase("channel"))
    {
      str1 = ((ChannelNode)localNode).title;
      if (((ChannelNode)localNode).isLiveChannel())
      {
        localObject1 = ((ChannelNode)localNode).getProgramNodeByTime(System.currentTimeMillis());
        if (localObject1 == null)
          break label216;
      }
    }
    label216: for (localObject1 = ((ProgramNode)localObject1).title; ; localObject1 = "节目加载中")
    {
      localObject2 = str1;
      break;
      localObject2 = ((ChannelNode)localNode).getAllLstProgramNode();
      localObject1 = str2;
      if (localObject2 != null)
      {
        localObject1 = str2;
        if (((List)localObject2).size() > 0)
          localObject1 = ((ProgramNode)((List)localObject2).get(0)).title;
      }
      localObject2 = str1;
      break;
      localObject2 = str1;
      localObject1 = str2;
      if (!localNode.nodeName.equalsIgnoreCase("program"))
        break;
      localObject1 = ((ProgramNode)localNode).title;
      localObject2 = str1;
      break;
    }
  }

  private void showSchedule()
  {
    Object localObject1 = InfoManager.getInstance().root().getCurrentPlayingNode();
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localChannelNode.isLiveChannel())
    {
      EventDispacthManager.getInstance().dispatchAction("showSchedule", localObject1);
      QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "menu");
      return;
    }
    if ((localObject1 instanceof ProgramNode));
    for (boolean bool = ((ProgramNode)localObject1).isDownloadProgram; ; bool = false)
    {
      if (bool)
      {
        localObject1 = ControllerManager.getInstance().getLastViewController();
        if ((localObject1 instanceof MyDownloadProgramController))
        {
          localObject1 = (ChannelNode)((MyDownloadProgramController)localObject1).getValue("channelNode", null);
          if ((localObject1 != null) && (((ChannelNode)localObject1).channelId == localChannelNode.channelId))
          {
            Toast.makeText(getContext(), "已在节目列表页面", 0).show();
            return;
          }
        }
        ControllerManager.getInstance().redirectToDownloadProgramsController(localChannelNode);
        break;
      }
      Object localObject2 = ControllerManager.getInstance().getLastViewController();
      if ((localObject2 instanceof ChannelDetailController))
      {
        localObject2 = (ChannelNode)((ChannelDetailController)localObject2).getValue("channelNode", null);
        if ((localObject2 != null) && (((ChannelNode)localObject2).channelId == localChannelNode.channelId))
        {
          Toast.makeText(getContext(), "已在节目列表页面", 0).show();
          return;
        }
      }
      ControllerManager.getInstance().openChannelDetailControllerWithoutDamaku((Node)localObject1);
      break;
    }
  }

  private void stop()
  {
    if ((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARMPLAY) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE))
    {
      PlayerAgent.getInstance().stop();
      return;
    }
    PlayerAgent.getInstance().stop();
  }

  private void toPlayView()
  {
    EventDispacthManager.getInstance().dispatchAction("toPlayView", null);
    QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "other");
  }

  public void destroy()
  {
    PlayProcessSyncUtil.getInstance().removeListener(this);
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    PlayerAgent.getInstance().removeMediaEventListener(this);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RPS" });
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    EventDispacthManager.getInstance().removeListener(this);
    WoApiRequest.removeListener(this);
  }

  public final int getHeightWithoutShadow()
  {
    return this.standardLayout.height - this.menuLayout.topMargin;
  }

  public void onAction(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("hideMiniplayerTrangle"))
      this.mTrangleElement.setVisible(4);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    this.mTrangleElement.setVisible(4);
    if (paramViewElement == this.mPlayElement)
    {
      EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
      if (this.mPlayElement.getState() == AbsPlayButtonElement.State.BUFFER)
      {
        stop();
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "play");
      }
    }
    while (true)
    {
      if (this.mHasTop)
      {
        this.mHasTop = false;
        QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "minitopplayhistory");
      }
      return;
      playOrStop();
      break;
      if (paramViewElement == this.mHistoryElement)
      {
        EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
        ControllerManager.getInstance().openPlayHistoryController();
        QTMSGManage.getInstance().sendStatistcsMessage("miniplayerclick", "playhistory");
      }
      else if (paramViewElement == this.mMenuElement)
      {
        showSchedule();
      }
      else if (paramViewElement == this.mBg)
      {
        toPlayView();
      }
    }
  }

  public void onManualSeek()
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.processLayout.scaleToBounds(this.standardLayout);
    this.stateLayout.scaleToBounds(this.standardLayout);
    this.menuLayout.scaleToBounds(this.standardLayout);
    this.channelTitleLayout.scaleToBounds(this.standardLayout);
    this.playLayout.scaleToBounds(this.standardLayout);
    this.historyLayout.scaleToBounds(this.standardLayout);
    this.programTitleLayout.scaleToBounds(this.standardLayout);
    this.trangleLayout.scaleToBounds(this.standardLayout);
    this.mProcessBarElement.measure(0, this.standardLayout.height - this.processLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mBg.measure(0, this.menuLayout.topMargin, this.standardLayout.width, this.standardLayout.height - this.processLayout.height);
    this.mMenuElement.measure(this.menuLayout);
    this.mStateElement.measure(this.stateLayout);
    this.mChannelTitleTe.measure(this.channelTitleLayout);
    this.mPlayElement.measure(this.playLayout);
    this.mHistoryElement.measure(this.historyLayout);
    this.mProgramTitleTe.measure(this.programTitleLayout);
    this.mTrangleElement.measure(this.trangleLayout);
    this.mProgramTitleTe.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mChannelTitleTe.setTextSize(SkinManager.getInstance().getFontSize22px());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    if ((paramString != null) && (paramString.equalsIgnoreCase("RPS")))
      refreshPlayingInfo();
  }

  public void onOpen()
  {
    this.mStateElement.setImageRes(2130838008);
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    if (paramInt == 1)
      refreshPlayingInfo();
  }

  public void onPlayStatusUpdated(PlayStatus paramPlayStatus)
  {
    if (paramPlayStatus.state == 16384)
      return;
    refreshPlayingInfo();
    changeViewStateByPlayState(paramPlayStatus.state);
  }

  public void onProcessChanged()
  {
    this.mProcessBarElement.setProcess(PlayProcessSyncUtil.getInstance().getCurrentPlayRatio());
  }

  public void onProcessMaxChanged()
  {
  }

  public void onProgressPause()
  {
  }

  public void onProgressResume()
  {
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.MiniPlayerView
 * JD-Core Version:    0.6.2
 */