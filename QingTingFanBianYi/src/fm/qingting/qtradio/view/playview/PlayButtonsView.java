package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.doubleclick.DoubleClick;
import fm.qingting.qtradio.fm.IMediaEventListener;
import fm.qingting.qtradio.fm.PlayStatus;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.util.List;

public class PlayButtonsView extends QtView
  implements ViewElement.OnElementClickListener, IMediaEventListener, DownLoadInfoNode.IDownloadInfoEventListener
{
  private final ViewLayout downloadLayout = this.standardLayout.createChildLT(50, 50, 628, 66, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mDownloadElement;
  private ButtonViewElement mNextElement;
  private ProgramNode mNode;
  private PlayButtonElement mPlayElement;
  private ButtonViewElement mPreElement;
  private ButtonViewElement mScheduleElement;
  private final ViewLayout nextLayout = this.standardLayout.createChildLT(60, 60, 483, 60, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout playLayout = this.standardLayout.createChildLT(60, 60, 330, 61, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout preLayout = this.standardLayout.createChildLT(60, 60, 177, 61, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout scheduleLayout = this.standardLayout.createChildLT(50, 50, 42, 66, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 220, 720, 220, 0, 0, ViewLayout.FILL);

  public PlayButtonsView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    int j = ScreenConfiguration.getCustomExtraBound() * 2;
    this.mPlayElement = new PlayButtonElement(paramContext);
    addElement(this.mPlayElement, i);
    this.mPlayElement.expandHotPot(j);
    this.mPlayElement.setOnElementClickListener(this);
    this.mPreElement = new ButtonViewElement(paramContext);
    this.mPreElement.setBackground(2130837787, 2130837785, 2130837786);
    addElement(this.mPreElement, i);
    this.mPreElement.setOnElementClickListener(this);
    this.mPreElement.expandHotPot(j);
    this.mNextElement = new ButtonViewElement(paramContext);
    this.mNextElement.setBackground(2130837780, 2130837778, 2130837779);
    addElement(this.mNextElement, i);
    this.mNextElement.setOnElementClickListener(this);
    this.mNextElement.expandHotPot(j);
    this.mScheduleElement = new ButtonViewElement(paramContext);
    this.mScheduleElement.setBackground(2130837789, 2130837788);
    addElement(this.mScheduleElement, i);
    this.mScheduleElement.setOnElementClickListener(this);
    this.mScheduleElement.expandHotPot(j);
    this.mDownloadElement = new ButtonViewElement(paramContext);
    this.mDownloadElement.setBackground(2130837769, 2130837767);
    addElement(this.mDownloadElement, i);
    this.mDownloadElement.setOnElementClickListener(this);
    this.mDownloadElement.expandHotPot(j);
    PlayerAgent.getInstance().addMediaEventListener(this);
    i = PlayerAgent.getInstance().getCurrentPlayStatus();
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
      if (InfoManager.getInstance().root().isOpenFm())
        this.mPlayElement.setState(AbsPlayButtonElement.State.PAUSE);
    while (true)
    {
      InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
      return;
      this.mPlayElement.setState(AbsPlayButtonElement.State.PLAY);
      continue;
      if ((i == 30583) || (i == 0) || (i == 1))
        this.mPlayElement.setState(AbsPlayButtonElement.State.PLAY);
      else if (i == 4096)
        this.mPlayElement.setState(AbsPlayButtonElement.State.PAUSE);
      else if (i == 8192)
        this.mPlayElement.setState(AbsPlayButtonElement.State.ERROR);
      else if ((i == 4101) || (i == 4098))
        this.mPlayElement.setState(AbsPlayButtonElement.State.BUFFER);
    }
  }

  private void download()
  {
    QTMSGManage.getInstance().sendStatistcsMessage("downloadclick", "playview");
    ChannelNode localChannelNode1 = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localChannelNode1 != null)
    {
      if ((!localChannelNode1.isMusicChannel()) || (InfoManager.getInstance().allowDownloadMusic(localChannelNode1.channelId)))
        break label58;
      Toast.makeText(getContext(), "该节目暂时无法下载", 0).show();
    }
    label58: 
    do
    {
      return;
      if (localChannelNode1.isDownloadChannel())
      {
        ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(localChannelNode1.channelId, 1);
        if ((localChannelNode2 != null) && (!localChannelNode2.hasEmptyProgramSchedule()))
        {
          ControllerManager.getInstance().redirectToBatchDownloadView(localChannelNode2, false, true);
          return;
        }
      }
    }
    while (localChannelNode1.hasEmptyProgramSchedule());
    ControllerManager.getInstance().redirectToBatchDownloadView(localChannelNode1, false, true);
  }

  private void openSchedule()
  {
    dispatchActionEvent("showSchedule", null);
  }

  private void playNext()
  {
    Object localObject2 = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localObject2 != null) && (((Node)localObject2).nextSibling != null))
      if ((((Node)localObject2).nextSibling.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)((Node)localObject2).nextSibling).getCurrPlayStatus() != 2));
    Object localObject1;
    do
    {
      do
      {
        do
        {
          return;
          PlayerAgent.getInstance().play(((Node)localObject2).nextSibling);
          return;
        }
        while (localObject2 == null);
        localObject1 = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while ((localObject1 == null) || (((ChannelNode)localObject1).hasEmptyProgramSchedule()) || (((ChannelNode)localObject1).getAllLstProgramNode() == null) || (!((Node)localObject2).nodeName.equalsIgnoreCase("program")));
      localObject2 = ((ChannelNode)localObject1).getProgramNodeByProgramId(((ProgramNode)localObject2).id);
      if ((localObject2 != null) && (((Node)localObject2).nextSibling != null))
      {
        PlayerAgent.getInstance().play(((Node)localObject2).nextSibling);
        return;
      }
      localObject1 = (Node)((ChannelNode)localObject1).getAllLstProgramNode().get(0);
    }
    while (localObject1 == null);
    PlayerAgent.getInstance().play((Node)localObject1);
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
        return;
      }
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

  private void playPrev()
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localNode != null) && (localNode.prevSibling != null) && (localNode.prevSibling.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)localNode.prevSibling).getCurrPlayStatus() != 2))
      PlayerAgent.getInstance().play(localNode.prevSibling);
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

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    super.close(paramBoolean);
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if (((paramInt != 4) && (paramInt != 1)) || (this.mNode == null))
      return;
    if (this.mNode.isDownloadProgram())
      this.mDownloadElement.setBackground(2130837770, 2130837770);
    while (true)
    {
      invalidate();
      return;
      if (InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad(this.mNode) == 3)
        this.mDownloadElement.setBackground(2130837770, 2130837770);
      else
        this.mDownloadElement.setBackground(2130837769, 2130837767);
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mPlayElement)
      if (this.mPlayElement.getState() == AbsPlayButtonElement.State.BUFFER)
      {
        stop();
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "play");
      }
    do
    {
      return;
      playOrStop();
      break;
      if (paramViewElement == this.mPreElement)
      {
        playPrev();
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "playprev");
        return;
      }
      if (paramViewElement == this.mNextElement)
      {
        playNext();
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "playnext");
        return;
      }
      if (paramViewElement == this.mDownloadElement)
      {
        download();
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "download");
        DoubleClick.getInstance().visitButton("下载");
        return;
      }
    }
    while (paramViewElement != this.mScheduleElement);
    openSchedule();
    QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "openschedule");
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.playLayout.scaleToBounds(this.standardLayout);
    this.preLayout.scaleToBounds(this.standardLayout);
    this.nextLayout.scaleToBounds(this.standardLayout);
    this.scheduleLayout.scaleToBounds(this.standardLayout);
    this.downloadLayout.scaleToBounds(this.standardLayout);
    this.mPlayElement.measure(this.playLayout);
    this.mPreElement.measure(this.preLayout);
    this.mNextElement.measure(this.nextLayout);
    this.mScheduleElement.measure(this.scheduleLayout);
    this.mDownloadElement.measure(this.downloadLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onPlayStatusUpdated(PlayStatus paramPlayStatus)
  {
    int i = paramPlayStatus.state;
    if ((i == 0) || (i == 1))
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.PLAY);
      return;
    }
    if (i == 8192)
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.ERROR);
      return;
    }
    if ((i == 4098) || (i == 4101))
    {
      this.mPlayElement.setState(AbsPlayButtonElement.State.BUFFER);
      return;
    }
    this.mPlayElement.setState(AbsPlayButtonElement.State.PAUSE);
  }

  public void update(String paramString, Object paramObject)
  {
    boolean bool3 = false;
    boolean bool1;
    if (paramString.equalsIgnoreCase("setNode"))
    {
      this.mNode = ((ProgramNode)paramObject);
      if (!this.mNode.isDownloadProgram())
        break label205;
      this.mDownloadElement.setBackground(2130837770, 2130837770);
      paramString = ChannelHelper.getInstance().getChannel(this.mNode.channelId, 1);
      if ((paramString != null) && (paramString.hasEmptyProgramSchedule()))
        InfoManager.getInstance().loadProgramsScheduleNode(paramString, null);
      if (this.mNode.nextSibling == null)
        break label297;
      if ((!this.mNode.nextSibling.nodeName.equalsIgnoreCase("program")) || (((ProgramNode)this.mNode.nextSibling).getCurrPlayStatus() == 2))
        break label430;
      bool1 = true;
    }
    while (true)
    {
      label126: boolean bool2;
      if (this.mNode.prevSibling != null)
      {
        bool2 = bool3;
        if (this.mNode.prevSibling.nodeName.equalsIgnoreCase("program"))
        {
          bool2 = bool3;
          if (((ProgramNode)this.mNode.prevSibling).getCurrPlayStatus() == 3)
            bool2 = true;
        }
      }
      while (true)
      {
        this.mNextElement.setEnable(bool1);
        this.mPreElement.setEnable(bool2);
        invalidate();
        return;
        label205: if (InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad(this.mNode) == 3)
        {
          this.mDownloadElement.setBackground(2130837770, 2130837770);
          break;
        }
        if ((this.mNode.isMusicChannel()) && (!InfoManager.getInstance().allowDownloadMusic(this.mNode.channelId)))
        {
          this.mDownloadElement.setBackground(2130837768, 2130837768);
          break;
        }
        this.mDownloadElement.setBackground(2130837769, 2130837767);
        break;
        label297: paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        if ((paramString == null) || (paramString.hasEmptyProgramSchedule()) || (paramString.getAllLstProgramNode() == null))
          break label430;
        paramString = paramString.getProgramNodeByProgramId(this.mNode.id);
        if ((paramString == null) || (paramString.nextSibling == null))
          break label430;
        bool1 = true;
        break label126;
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        bool2 = bool3;
        if (paramString != null)
        {
          bool2 = bool3;
          if (!paramString.hasEmptyProgramSchedule())
          {
            bool2 = bool3;
            if (paramString.getAllLstProgramNode() != null)
            {
              paramString = paramString.getProgramNodeByProgramId(this.mNode.id);
              bool2 = bool3;
              if (paramString != null)
              {
                bool2 = bool3;
                if (paramString.prevSibling != null)
                  bool2 = true;
              }
            }
          }
        }
      }
      label430: bool1 = false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayButtonsView
 * JD-Core Version:    0.6.2
 */