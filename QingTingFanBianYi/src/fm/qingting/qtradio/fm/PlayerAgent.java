package fm.qingting.qtradio.fm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.qtradio.QTRadioService;
import fm.qingting.qtradio.dongruan.DongRuanInstance;
import fm.qingting.qtradio.doubleclick.DoubleClick;
import fm.qingting.qtradio.fmdriver.FMManager;
import fm.qingting.qtradio.fmdriver.FMcontrol;
import fm.qingting.qtradio.fmdriver.IFMControlEventListener;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.INETEventListener;
import fm.qingting.qtradio.manager.NetWorkManage;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.AUDIO_QUALITY_MODE;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayedMetaData;
import fm.qingting.qtradio.model.PlayedMetaInfo;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.ReserveInfoNode;
import fm.qingting.qtradio.model.RingToneNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.PlayMode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.remotecontrol.RemoteControl;
import fm.qingting.utils.PlaybackMonitor;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlayerAgent
  implements IFMControlEventListener, INETEventListener, InfoManager.ISubscribeEventListener
{
  private static final String Tag = "PlayerAgent";
  private static PlayerAgent instance;
  private long FromPlayToPlaying = 0L;
  private final int MIN_AD_PROGRAM_DURATION = 300;
  private final int UPDATE_PLAYSTATUS = 1;
  private Context _context;
  private long beginFromPlay = 0L;
  private long beginPlay = 0L;
  private ServiceConnection conn = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      paramAnonymousComponentName = null;
      PlayerAgent.this.setServiceControl(IServiceControl.Stub.asInterface(paramAnonymousIBinder));
      if (PlayerAgent.this.mEventHandler != null)
        PlayerAgent.this.mEventHandler.onEvent(null, "serviceConnected", null);
      PlaybackMonitor.getInstance();
      paramAnonymousIBinder = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((paramAnonymousIBinder != null) && (paramAnonymousIBinder.nodeName.equalsIgnoreCase("program")))
      {
        ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        if (localChannelNode != null)
          paramAnonymousComponentName = localChannelNode.getApproximativeThumb();
        paramAnonymousIBinder = (ProgramNode)paramAnonymousIBinder;
        PlayerAgent.this.setPlayingChannel(paramAnonymousIBinder.getCategoryId(), paramAnonymousIBinder.channelId, paramAnonymousIBinder.id, paramAnonymousIBinder.uniqueId, paramAnonymousIBinder.channelType, paramAnonymousComponentName, paramAnonymousIBinder.getChannelName(), paramAnonymousIBinder.getProgramType());
      }
    }

    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      PlayerAgent.this.releaseServiceControl();
    }
  };
  private int currPlayState = 30583;
  private final int defaultBufferTime = 2000;
  private Runnable doSeek = new Runnable()
  {
    public void run()
    {
      try
      {
        if (PlayerAgent.this.iService == null)
          return;
        PlayerAgent.this.iService.seek(PlayerAgent.this.seekTime);
        if (PlayerAgent.this.hasAutoSeek)
        {
          InfoManager.getInstance().root().setInfoUpdate(1);
          PlayerAgent.this.clearSeekState();
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
      }
    }
  };
  private boolean firstTips = false;
  private boolean hasAutoSeek = false;
  private boolean hasPlayed = false;
  private boolean hasRecoveredFromCrash = false;
  private IServiceControl iService = null;
  private Set<WeakReference<IMediaEventListener>> listeners = new HashSet();
  private boolean liveStream = true;
  private boolean mAutoPlay = false;
  private boolean mConnected = false;
  private IEventHandler mEventHandler;
  private String mLastNetType = "";
  private int mLoadedAndPlayCid = 0;
  private int mLoadedAndPlayPid = 0;
  private PlayControllReceiver mPlayControllReceiver;
  private int mPlaySource = 0;
  private String mPlayingSourceUrls = "";
  private int mShouldPlay = 0;
  private Map<String, Integer> mapBufferCnt = new HashMap();
  private int playDuration = 0;
  private boolean preemptiveFlag = false;
  private WeakReference<IMediaEventListener> preemptiveListener = null;
  private String preloaded_source;
  private PlayStatus ps = new PlayStatus(0, 2147483647L, 0L, 2000L, 0L);
  private QTReceiver recevier;
  private boolean recvDoPlay = false;
  private Handler seekHandler = new Handler();
  private int seekTime = 0;
  private String source;

  private PlayerAgent()
  {
    FMcontrol.getInstance().addListener(this);
    NetWorkManage.getInstance().addListener(this);
  }

  private void _pause()
  {
    if (this.iService != null);
    try
    {
      this.iService.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  private boolean _play(String paramString)
  {
    if ((this.iService == null) || ((paramString == null) && (this.source == null)));
    while ((this.source != null) && (this.source.equalsIgnoreCase(paramString)) && (this.currPlayState == 4096))
      return false;
    long l1 = queryPosition();
    long l2 = queryDuration();
    if (paramString != null)
      this.source = paramString;
    if ((this.source != null) && (!this.source.equalsIgnoreCase(this.preloaded_source)))
      setSource(this.source);
    dispatchPlayState(4101);
    autoReserve();
    this.beginPlay = (System.currentTimeMillis() / 1000L);
    this.beginFromPlay = System.currentTimeMillis();
    this.recvDoPlay = false;
    this.mConnected = false;
    try
    {
      if (((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.PLAY_END_ADVERTISEMENT)) && (InfoManager.getInstance().getTaobaoAudioAdv()))
        TaobaoAgent.getInstance().stopAD();
      this.hasPlayed = true;
      this.iService.play();
      PlayedMetaInfo.getInstance().addPlayedMeta(InfoManager.getInstance().root().getCurrentPlayingNode(), (int)l1, (int)l2);
      if (!this.liveStream)
      {
        this.playDuration = queryDuration();
        return true;
      }
    }
    catch (RemoteException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        continue;
        this.playDuration = 0;
      }
    }
  }

  private void _playCache(String paramString)
  {
    try
    {
      int i = queryPosition();
      setSource(paramString);
      this.iService.play();
      this.iService.seek(i);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  private void _resume()
  {
    if (this.iService != null)
    {
      try
      {
        dispatchPlayState(4101);
        this.beginFromPlay = System.currentTimeMillis();
        if (!InfoManager.getInstance().getShowMobileNetworkSetting())
          break label105;
        if ((this.source.startsWith("file:")) || (InfoManager.getInstance().hasWifi()))
        {
          this.iService.resume();
          return;
        }
        if (InfoManager.getInstance().useMobileNetwork())
        {
          this.iService.resume();
          EventDispacthManager.getInstance().dispatchAction("showToast", "播放5分钟需要消耗1M流量，请注意使用");
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
        return;
      }
      this.mShouldPlay = 2;
      return;
      label105: this.iService.resume();
    }
  }

  private void _stop()
  {
    if (this.iService != null);
    try
    {
      this.iService.stop();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  private void autoSeek(int paramInt)
  {
    PlayedMetaData localPlayedMetaData = PlayedMetaInfo.getInstance().getPlayedMeta(paramInt);
    if (localPlayedMetaData != null)
    {
      this.hasAutoSeek = true;
      asyncSeek(localPlayedMetaData.position);
    }
  }

  private void clearSeekState()
  {
    this.hasAutoSeek = false;
  }

  private void dispatchMediaEvent(int paramInt, Object paramObject)
  {
    notifyDongruan(paramObject);
    Object localObject1;
    if ((this.preemptiveFlag) && (this.preemptiveListener != null))
    {
      localObject1 = (IMediaEventListener)this.preemptiveListener.get();
      if (localObject1 != null)
        ((IMediaEventListener)localObject1).onPlayStatusUpdated((PlayStatus)paramObject);
    }
    label67: Object localObject2;
    IMediaEventListener localIMediaEventListener;
    do
    {
      return;
      localObject1 = new HashSet();
      ((HashSet)localObject1).addAll(this.listeners);
      localObject1 = ((HashSet)localObject1).iterator();
      do
        while (true)
        {
          if (!((Iterator)localObject1).hasNext())
            break label301;
          localObject2 = (WeakReference)((Iterator)localObject1).next();
          localIMediaEventListener = (IMediaEventListener)((WeakReference)localObject2).get();
          if (localIMediaEventListener != null)
            break;
          this.listeners.remove(localObject2);
        }
      while (((paramInt != 1) || (paramObject == null) || (this.currPlayState == 0)) && (((PlayStatus)paramObject).state != 4101));
      if (((PlayStatus)paramObject).state == 4116)
      {
        this.recvDoPlay = true;
        return;
      }
      if (((PlayStatus)paramObject).state != 4096)
        break label303;
    }
    while (!this.recvDoPlay);
    this.mConnected = true;
    SystemPlayer.getInstance().stop();
    if (this.beginFromPlay > 0L)
    {
      this.FromPlayToPlaying = (System.currentTimeMillis() - this.beginFromPlay);
      if ((this.FromPlayToPlaying > 0L) && (this.FromPlayToPlaying < 1000000L))
      {
        localObject2 = QTLogger.getInstance().buildListeneringQualityLog(InfoManager.getInstance().root().getCurrentPlayingNode(), this.FromPlayToPlaying / 1000.0D, 0, queryPlayingUrl());
        if (localObject2 != null)
          LogModule.getInstance().send("PlayExperience", (String)localObject2);
      }
      this.beginFromPlay = 0L;
    }
    while (true)
    {
      localIMediaEventListener.onPlayStatusUpdated((PlayStatus)paramObject);
      break label67;
      label301: break;
      label303: if ((paramInt == 1) && (paramObject != null) && (((PlayStatus)paramObject).state == 4098))
      {
        localObject2 = queryPlayingUrl();
        if (localObject2 != null)
          if (this.mapBufferCnt.containsKey(localObject2))
          {
            int i = ((Integer)this.mapBufferCnt.get(localObject2)).intValue();
            this.mapBufferCnt.put(localObject2, Integer.valueOf(i + 1));
          }
          else
          {
            this.mapBufferCnt.put(localObject2, Integer.valueOf(1));
          }
      }
    }
  }

  private void dispatchMediaEventInFM(int paramInt, Object paramObject)
  {
    Object localObject = new HashSet();
    ((HashSet)localObject).addAll(this.listeners);
    localObject = ((HashSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      WeakReference localWeakReference = (WeakReference)((Iterator)localObject).next();
      IMediaEventListener localIMediaEventListener = (IMediaEventListener)localWeakReference.get();
      if (localIMediaEventListener == null)
        this.listeners.remove(localWeakReference);
      else
        localIMediaEventListener.onPlayStatusUpdated((PlayStatus)paramObject);
    }
  }

  private void dispatchPlayState(int paramInt)
  {
    this.ps.state = paramInt;
    this.ps.bufferLength = 0L;
    this.ps.bufferTime = 2000L;
    this.ps.duration = 0L;
    this.ps.time = 0L;
    dispatchMediaEvent(1, this.ps);
  }

  private void exit()
  {
    if (this.iService == null)
      return;
    try
    {
      this.iService.exit();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public static PlayerAgent getInstance()
  {
    try
    {
      if (instance == null)
        instance = new PlayerAgent();
      PlayerAgent localPlayerAgent = instance;
      return localPlayerAgent;
    }
    finally
    {
    }
  }

  private void initPlayControll()
  {
    this.mPlayControllReceiver = new PlayControllReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.UPDATE_PLAY_INFO_QT");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PAUSE");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PLAY");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PLAY_PRE");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PLAY_NEXT");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PLAY_PRE_CATEGORY");
    localIntentFilter.addAction("fm.qingting.qtradio.CAR_PLAY_NEXT_CATEGORY");
    localIntentFilter.setPriority(2);
    this._context.registerReceiver(this.mPlayControllReceiver, localIntentFilter);
  }

  private void notifyDongruan(Object paramObject)
  {
    DongRuanInstance.getInstance().sendPlayInfo();
    if ((paramObject instanceof PlayStatus));
    switch (((PlayStatus)paramObject).state)
    {
    default:
      DongRuanInstance.getInstance().pause();
      return;
    case 4096:
    case 4097:
    }
    DongRuanInstance.getInstance().play();
  }

  private boolean playFrontAudioAdv(Node paramNode)
  {
    if (paramNode == null)
      return false;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      if (((ProgramNode)paramNode).channelType == 0)
        return false;
      if (InfoManager.getInstance().enableAudioAdv((ProgramNode)paramNode))
      {
        int i = ((ProgramNode)paramNode).getCategoryId();
        String str = InfoManager.getInstance().getFrontAudioAdv(i, ((ProgramNode)paramNode).channelId);
        if (((ProgramNode)paramNode).duration < 300.0D)
          return false;
        if (getInstance().playSource(str))
        {
          InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT);
          return true;
        }
        if ((InfoManager.getInstance().enableTaobaoAudioAdv()) && (TaobaoAgent.getInstance().playAD(false)))
          return true;
      }
      else if (InfoManager.getInstance().enableTaobaoAudioAdv())
      {
        if (((ProgramNode)paramNode).duration < 300.0D)
          return false;
        if (TaobaoAgent.getInstance().playAD(false))
          return true;
      }
    }
    return false;
  }

  private String queryPlayingUrl()
  {
    if (this.iService == null)
      return null;
    try
    {
      String str = this.iService.queryPlayingUrl();
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private void releasePlayControll()
  {
    try
    {
      if (this.mPlayControllReceiver != null)
      {
        this._context.unregisterReceiver(this.mPlayControllReceiver);
        this.mPlayControllReceiver = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void releaseServiceControl()
  {
    this.iService = null;
  }

  private boolean requestAudioFocus()
  {
    return true;
  }

  private void setLoadedAndPlayId(int paramInt1, int paramInt2)
  {
    this.mLoadedAndPlayPid = paramInt2;
    this.mLoadedAndPlayCid = paramInt1;
  }

  private void setSource(String paramString)
  {
    if ((this.iService == null) || (paramString == null));
    while ((this.preloaded_source != null) && (this.preloaded_source.equalsIgnoreCase(paramString)))
      return;
    try
    {
      _stop();
      this.preloaded_source = paramString;
      this.iService.setSource(this.preloaded_source);
      return;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void addABTestCategory(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (this.iService != null)
        this.iService.addABTestCategory(paramInt1, paramInt2, paramInt3);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void addABTestProgram(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      if (this.iService != null)
        this.iService.addABTestProgram(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void addMediaEventListener(IMediaEventListener paramIMediaEventListener)
  {
    removeMediaEventListener(paramIMediaEventListener);
    this.listeners.add(new WeakReference(paramIMediaEventListener));
  }

  public void addPlaySource(int paramInt)
  {
    this.mPlaySource = paramInt;
  }

  public void addPreemptiveListener(IMediaEventListener paramIMediaEventListener)
  {
    this.preemptiveFlag = true;
    this.preemptiveListener = new WeakReference(paramIMediaEventListener);
  }

  public void asyncSeek(int paramInt)
  {
    if ((this.iService == null) || (paramInt < 5))
      return;
    this.seekTime = (paramInt - 5);
    this.seekHandler.removeCallbacks(this.doSeek);
    if (InfoManager.getInstance().hasWifi())
    {
      this.seekHandler.postDelayed(this.doSeek, 1000L);
      return;
    }
    this.seekHandler.postDelayed(this.doSeek, 2000L);
  }

  public void autoReserve()
  {
    if ((this.beginPlay != 0L) && (InfoManager.getInstance().getAutoReserve()) && (System.currentTimeMillis() / 1000L - this.beginPlay > InfoManager.getInstance().getAutoReserveMinDuration()))
    {
      Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")) && (!InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(localNode)))
        InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.autoReserveNextProgram((ProgramNode)localNode);
    }
  }

  public void delPreemptiveListener()
  {
    this.preemptiveFlag = false;
    this.preemptiveListener = null;
  }

  public void dispatchPlayStateInFM(int paramInt)
  {
    this.ps.state = paramInt;
    this.ps.bufferLength = 0L;
    this.ps.bufferTime = 2000L;
    this.ps.duration = 0L;
    this.ps.time = 0L;
    dispatchMediaEventInFM(1, this.ps);
  }

  public void forceSetSource()
  {
    if ((this.iService == null) || (this.source == null));
    while (true)
    {
      return;
      try
      {
        Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        boolean bool = isPlaying();
        stop();
        this.preloaded_source = this.source;
        setSource(this.preloaded_source);
        if (bool)
        {
          play(localNode);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public int getCurrentPlayStatus()
  {
    return this.currPlayState;
  }

  public String getHttpProxy()
  {
    try
    {
      if (this.iService != null)
        return this.iService.getHttpProxy();
      return "";
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return "";
  }

  public boolean getIsViaWoProxy()
  {
    boolean bool = false;
    try
    {
      if (this.iService != null)
        bool = this.iService.getIsViaWoProxy();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return false;
  }

  public int getProgramPlayInfo(int paramInt)
  {
    if (this.iService == null)
      return 0;
    try
    {
      paramInt = this.iService.queryPlayInfo(paramInt);
      return paramInt;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return 0;
  }

  public String getSource()
  {
    if (this.iService == null)
      return null;
    try
    {
      String str = this.iService.getSource();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return null;
  }

  public boolean hasConnected()
  {
    return this.mConnected;
  }

  public void init(Context paramContext)
  {
    this._context = paramContext;
    paramContext.bindService(new Intent(this._context, QTRadioService.class), this.conn, 1);
    this.recevier = new QTReceiver();
    paramContext = new IntentFilter();
    paramContext.addAction("android.intent.action.UPDATE_PLAY_STATUS");
    this._context.registerReceiver(this.recevier, paramContext);
    initPlayControll();
    this.preloaded_source = null;
    this.source = null;
    this.mLastNetType = NetWorkManage.getInstance().getNetWorkType();
  }

  public boolean isLiveStream()
  {
    return this.liveStream;
  }

  public boolean isPause()
  {
    return this.currPlayState == 1;
  }

  public boolean isPlayerPlay()
  {
    if (this.iService == null)
      return false;
    try
    {
      boolean bool = this.iService.isPlayerPlay();
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public boolean isPlaying()
  {
    return this.currPlayState == 4096;
  }

  public boolean isPreemptive()
  {
    return this.preemptiveFlag;
  }

  public void onHeadsetPlugged()
  {
  }

  public void onHeadsetUnplugged()
  {
  }

  public void onMobilesState(boolean paramBoolean)
  {
    if ((InfoManager.getInstance().root().isOpenFm()) && (!paramBoolean))
      if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
      {
        localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        if ((localNode == null) || (!localNode.nodeName.equalsIgnoreCase("radiochannel")))
          break label78;
        FMManager.getInstance().tune(Integer.valueOf(((RadioChannelNode)localNode).freq).intValue());
      }
    label78: 
    while ((!paramBoolean) || (InfoManager.getInstance().root().currentPlayMode() != RootNode.PlayMode.FMPLAY))
    {
      Node localNode;
      do
      {
        do
          return;
        while (localNode == null);
        localNode = localNode.parent;
      }
      while ((localNode == null) || (!localNode.nodeName.equalsIgnoreCase("radiochannel")));
      FMManager.getInstance().tune(Integer.valueOf(((RadioChannelNode)localNode).freq).intValue());
      return;
    }
    FMManager.getInstance().turnOff();
  }

  public void onNetChanged(String paramString)
  {
    if (paramString == null);
    do
    {
      do
      {
        do
          return;
        while ((!InfoManager.getInstance().getMutiRate()) || (paramString.equalsIgnoreCase("nonet")) || (paramString.equalsIgnoreCase(this.mLastNetType)));
        this.mLastNetType = paramString;
      }
      while ((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARMPLAY) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE) || (InfoManager.getInstance().getAudioQualitySetting() != InfoManager.AUDIO_QUALITY_MODE.SMART.ordinal()) || (!isPlaying()) || (!InfoManager.getInstance().hasConnectedNetwork()));
      paramString = InfoManager.getInstance().root().getCurrentPlayingNode();
    }
    while ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("program")) && (((ProgramNode)paramString).isDownloadProgram()));
    getInstance().stop();
    getInstance().play(paramString);
    if (InfoManager.getInstance().hasWifi())
    {
      Toast.makeText(this._context, "您正处于wifi网络，自动切换至高音质模式", 1).show();
      return;
    }
    Toast.makeText(this._context, "您正处于移动网络，自动切换至省流量模式", 1).show();
  }

  public void onNotification(String paramString)
  {
    if ((paramString.equalsIgnoreCase("RVPI")) && (this.mLoadedAndPlayCid != 0) && (this.mLoadedAndPlayPid != 0))
    {
      paramString = ChannelHelper.getInstance().getChannel(this.mLoadedAndPlayCid, 1);
      if ((paramString != null) && (!paramString.hasEmptyProgramSchedule()))
      {
        paramString = paramString.getProgramNode(this.mLoadedAndPlayPid);
        if (paramString != null)
          play(paramString);
      }
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void play()
  {
    try
    {
      if (this.mShouldPlay == 1)
      {
        this.hasPlayed = true;
        this.iService.play();
        return;
      }
      if (this.mShouldPlay == 2)
      {
        this.iService.resume();
        return;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void play(Node paramNode)
  {
    if (paramNode == null);
    Object localObject2;
    do
    {
      return;
      requestAudioFocus();
      stopFM();
      setLoadedAndPlayId(0, 0);
      if (!paramNode.nodeName.equalsIgnoreCase("channel"))
        break label379;
      localObject2 = (ChannelNode)paramNode;
      if (((ChannelNode)localObject2).channelType != 1)
        break;
      paramNode = ((ChannelNode)localObject2).getAllLstProgramNode();
    }
    while ((paramNode == null) || (paramNode.size() <= 0));
    play((Node)paramNode.get(0));
    return;
    InfoManager.getInstance().root().setPlayingChannelNode(paramNode);
    Object localObject1 = ((ChannelNode)localObject2).getSourceUrl();
    Object localObject3;
    if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
    {
      localObject3 = ((ChannelNode)localObject2).getProgramNodeByTime(System.currentTimeMillis());
      paramNode = (Node)localObject1;
      if (localObject3 != null)
      {
        paramNode = (Node)localObject1;
        if (((ProgramNode)localObject3).getCurrPlayStatus() == 1)
          paramNode = ((ProgramNode)localObject3).getSourceUrl();
      }
      if (!playFrontAudioAdv((Node)localObject3))
      {
        InfoManager.getInstance().root().setPlayMode();
        _play(paramNode);
      }
      this.liveStream = true;
      if (localObject3 != null)
      {
        InfoManager.getInstance().root().setPlayingNode((Node)localObject3);
        RemoteControl.getInstance().updateProgramInfo(this._context, (ChannelNode)localObject2, (ProgramNode)localObject3);
      }
    }
    while (true)
    {
      this.currPlayState = 4096;
      InfoManager.getInstance().runSellApps();
      DoubleClick.getInstance().visitButton("播放前");
      return;
      InfoManager.getInstance().root().setPlayingNode((Node)localObject2);
      continue;
      if (this.hasRecoveredFromCrash)
        this.hasRecoveredFromCrash = false;
      localObject1 = ((ChannelNode)localObject2).getProgramNodeByTime(System.currentTimeMillis());
      label271: label558: label597: if (localObject1 != null)
      {
        if (((ProgramNode)localObject1).getCurrPlayStatus() == 3)
        {
          paramNode = ((ProgramNode)localObject1).getSourceUrl();
          if ((paramNode == null) || (paramNode.equalsIgnoreCase("")))
            break label369;
          if (!playFrontAudioAdv((Node)localObject1))
          {
            InfoManager.getInstance().root().setPlayMode();
            if ((_play(paramNode)) && (((ProgramNode)localObject1).getCurrPlayStatus() == 3))
              autoSeek(((ProgramNode)localObject1).id);
          }
          if (((ProgramNode)localObject1).getCurrPlayStatus() != 3)
            break label371;
        }
        label369: label371: for (this.liveStream = false; ; this.liveStream = true)
        {
          InfoManager.getInstance().root().setPlayingNode((Node)localObject1);
          RemoteControl.getInstance().updateProgramInfo(this._context, (ChannelNode)localObject2, (ProgramNode)localObject1);
          break;
          paramNode = ((ProgramNode)localObject1).getSourceUrl();
          break label271;
          break;
        }
        label379: if (paramNode.nodeName.equalsIgnoreCase("program"))
        {
          localObject2 = (ProgramNode)paramNode;
          localObject3 = ChannelHelper.getInstance().getChannel((ProgramNode)localObject2);
          if (localObject3 != null)
            InfoManager.getInstance().root().setPlayingChannelNode((Node)localObject3);
          localObject1 = "";
          if (((ProgramNode)localObject2).getCurrPlayStatus() == 3)
          {
            if (!((ProgramNode)localObject2).isDownloadProgram())
              localObject1 = InfoManager.getInstance().root().getLocalProgramSource((ProgramNode)localObject2);
            if (localObject1 != null)
            {
              paramNode = (Node)localObject1;
              if (!((String)localObject1).equalsIgnoreCase(""));
            }
            else
            {
              paramNode = PlayCacheAgent.getInstance().getCache((ProgramNode)localObject2);
              if (paramNode == null)
                break label597;
            }
            if ((paramNode == null) || (paramNode.equalsIgnoreCase("")))
              break label619;
            if ((this.currPlayState == 1) && ((this.source == null) || (this.source.equalsIgnoreCase(paramNode))))
              break label621;
            if (!playFrontAudioAdv((Node)localObject2))
            {
              InfoManager.getInstance().root().setPlayMode();
              if ((_play(paramNode)) && (((ProgramNode)localObject2).getCurrPlayStatus() == 3))
                autoSeek(((ProgramNode)localObject2).id);
            }
            if (((ProgramNode)localObject2).getCurrPlayStatus() != 3)
              break label628;
          }
          for (this.liveStream = false; ; this.liveStream = true)
          {
            InfoManager.getInstance().root().setPlayingNode((Node)localObject2);
            RemoteControl.getInstance().updateProgramInfo(this._context, (ChannelNode)localObject3, (ProgramNode)localObject2);
            break;
            PlayCacheAgent.getInstance().cacheNode((Node)localObject2);
            paramNode = ((ProgramNode)localObject2).getSourceUrl();
            break label478;
            paramNode = ((ProgramNode)localObject2).getSourceUrl();
            break label478;
            break;
            _resume();
            break label558;
          }
        }
        label478: label619: label621: label628: if (paramNode.nodeName.equalsIgnoreCase("ringtone"))
        {
          playRingTone(paramNode);
          InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARM_PLAY_ONLINE);
        }
      }
    }
  }

  public void play(Node paramNode, boolean paramBoolean)
  {
    if (paramNode == null);
    Object localObject2;
    do
    {
      return;
      requestAudioFocus();
      stopFM();
      if (!paramNode.nodeName.equalsIgnoreCase("channel"))
        break;
      localObject2 = (ChannelNode)paramNode;
    }
    while (((ChannelNode)localObject2).channelType == 1);
    InfoManager.getInstance().root().setPlayingChannelNode(paramNode);
    Object localObject1 = ((ChannelNode)localObject2).getSourceUrl();
    if ((localObject1 != null) && (!((String)localObject1).equalsIgnoreCase("")))
    {
      ProgramNode localProgramNode = ((ChannelNode)localObject2).getProgramNodeByTime(System.currentTimeMillis());
      paramNode = (Node)localObject1;
      if (localProgramNode != null)
      {
        paramNode = (Node)localObject1;
        if (localProgramNode.getCurrPlayStatus() == 1)
          paramNode = localProgramNode.getSourceUrl();
      }
      _play(paramNode);
      this.liveStream = true;
      if (localProgramNode != null)
        InfoManager.getInstance().root().setPlayingNode(localProgramNode);
    }
    while (true)
    {
      this.currPlayState = 4096;
      InfoManager.getInstance().runSellApps();
      return;
      InfoManager.getInstance().root().setPlayingNode((Node)localObject2);
      continue;
      if (this.hasRecoveredFromCrash)
        this.hasRecoveredFromCrash = false;
      localObject1 = ((ChannelNode)localObject2).getProgramNodeByTime(System.currentTimeMillis());
      if (localObject1 != null)
      {
        if (((ProgramNode)localObject1).getCurrPlayStatus() == 3)
        {
          paramNode = ((ProgramNode)localObject1).getSourceUrl();
          label198: if ((paramNode == null) || (paramNode.equalsIgnoreCase("")))
            break label267;
          if ((_play(paramNode)) && (((ProgramNode)localObject1).getCurrPlayStatus() == 3))
            autoSeek(((ProgramNode)localObject1).id);
          if (((ProgramNode)localObject1).getCurrPlayStatus() != 3)
            break label269;
        }
        label267: label269: for (this.liveStream = false; ; this.liveStream = true)
        {
          InfoManager.getInstance().root().setPlayingNode((Node)localObject1);
          break;
          paramNode = ((ProgramNode)localObject1).getSourceUrl();
          break label198;
          break;
        }
        if (paramNode.nodeName.equalsIgnoreCase("program"))
        {
          localObject2 = (ProgramNode)paramNode;
          paramNode = ChannelHelper.getInstance().getChannel((ProgramNode)localObject2);
          if (paramNode != null)
            InfoManager.getInstance().root().setPlayingChannelNode(paramNode);
          localObject1 = "";
          if (((ProgramNode)localObject2).getCurrPlayStatus() == 3)
          {
            if (!((ProgramNode)localObject2).isDownloadProgram())
              localObject1 = InfoManager.getInstance().root().getLocalProgramSource((ProgramNode)localObject2);
            if (localObject1 != null)
            {
              paramNode = (Node)localObject1;
              if (!((String)localObject1).equalsIgnoreCase(""));
            }
            else
            {
              paramNode = PlayCacheAgent.getInstance().getCache((ProgramNode)localObject2);
              if (paramNode == null)
                break label472;
            }
            label379: if ((paramNode == null) || (paramNode.equalsIgnoreCase("")))
              break label497;
            if ((this.currPlayState == 1) && ((this.source == null) || (this.source.equalsIgnoreCase(paramNode))))
              break label499;
            if ((_play(paramNode)) && (((ProgramNode)localObject2).getCurrPlayStatus() == 3))
              autoSeek(((ProgramNode)localObject2).id);
            label444: if (((ProgramNode)localObject2).getCurrPlayStatus() != 3)
              break label506;
          }
          label472: label497: label499: label506: for (this.liveStream = false; ; this.liveStream = true)
          {
            InfoManager.getInstance().root().setPlayingNode((Node)localObject2);
            break;
            PlayCacheAgent.getInstance().cacheNode((Node)localObject2);
            paramNode = ((ProgramNode)localObject2).getSourceUrl();
            break label379;
            paramNode = ((ProgramNode)localObject2).getSourceUrl();
            break label379;
            break;
            _resume();
            break label444;
          }
        }
        if (paramNode.nodeName.equalsIgnoreCase("ringtone"))
        {
          playRingTone(paramNode);
          InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARM_PLAY_ONLINE);
        }
      }
    }
  }

  public void play(String paramString)
  {
    try
    {
      InfoManager.getInstance().root().setPlayMode(RootNode.PlayMode.ALARM_PLAY_ONLINE);
      if ((this.iService != null) && (paramString != null) && (!paramString.equalsIgnoreCase("")))
      {
        this.source = paramString;
        this.iService.stop();
        this.iService.setSource(paramString);
        this.iService.play();
      }
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void playAndLoadData(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
  {
    if (paramInt3 != 0);
    for (int i = 1; ; i = 0)
    {
      Object localObject;
      if (paramInt1 == DownLoadInfoNode.mDownloadId)
      {
        localObject = InfoManager.getInstance().root().mDownLoadInfoNode.getChannelNode(paramInt2);
        if (localObject != null)
          break label141;
        if (paramInt4 != 1)
          break label121;
        paramString = ChannelHelper.getInstance().getFakeVirtualChannel(paramInt2, paramInt1, paramString);
      }
      while (true)
      {
        label52: if (paramString != null)
        {
          InfoManager.getInstance().root().setPlayingChannelNode(paramString);
          if (!paramString.hasEmptyProgramSchedule())
            break label165;
          InfoManager.getInstance().loadProgramsScheduleNode(paramString, null);
        }
        label121: 
        do
        {
          do
          {
            if (i != 0)
            {
              setLoadedAndPlayId(paramInt2, paramInt3);
              InfoManager.getInstance().loadProgramInfo(paramString, paramInt3, this);
            }
            return;
            localObject = ChannelHelper.getInstance().getChannel(paramInt2, paramInt4);
            break;
            if (paramInt4 != 0)
              break label189;
            paramString = ChannelHelper.getInstance().getFakeLiveChannel(paramInt2, paramInt1, paramString);
            break label52;
            if (((ChannelNode)localObject).channelType != 0)
              break label189;
            play((Node)localObject);
            i = 0;
            paramString = (String)localObject;
            break label52;
          }
          while (paramInt3 == 0);
          localObject = paramString.getProgramNode(paramInt3);
        }
        while (localObject == null);
        label141: label165: play((Node)localObject);
        return;
        label189: paramString = (String)localObject;
      }
    }
  }

  public void playCurrCache(int paramInt, String paramString)
  {
    try
    {
      if (InfoManager.getInstance().root().currentPlayMode() != RootNode.PlayMode.PLAY_END_ADVERTISEMENT)
      {
        if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT)
          return;
        Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")) && (paramInt == ((ProgramNode)localNode).resId))
        {
          _playCache(paramString);
          return;
        }
      }
    }
    catch (Exception paramString)
    {
    }
  }

  public void playNext()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (localObject != null)
    {
      if (((Node)localObject).nextSibling == null)
        break label30;
      play(((Node)localObject).nextSibling);
    }
    label30: 
    do
    {
      ChannelNode localChannelNode;
      do
      {
        return;
        localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while ((localChannelNode == null) || (localChannelNode.hasEmptyProgramSchedule()) || (localChannelNode.getAllLstProgramNode() == null) || (!((Node)localObject).nodeName.equalsIgnoreCase("program")));
      localObject = localChannelNode.getProgramNodeByProgramId(((ProgramNode)localObject).id);
    }
    while ((localObject == null) || (((Node)localObject).nextSibling == null));
    play(((Node)localObject).nextSibling);
  }

  public void playNextCategory()
  {
    Object localObject2 = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Object localObject1 = InfoManager.getInstance().root().getCurrentPlayingNode();
    List localList = InfoManager.getInstance().root().getRecommendCategoryNode(0).lstRecMain;
    if ((localList == null) || (localList.size() == 0))
      return;
    if (localObject2 == null)
    {
      play(((RecommendItemNode)((List)localList.get(0)).get(0)).mNode);
      return;
    }
    int i = 0;
    while (i < localList.size())
    {
      int j = 0;
      while (j < ((List)localList.get(i)).size())
      {
        if (((RecommendItemNode)((List)localList.get(i)).get(j)).mCategoryId == ((ChannelNode)localObject2).categoryId)
        {
          i = (i + 1) % localList.size();
          localObject2 = ((RecommendItemNode)((List)localList.get(i)).get(0)).mNode;
          if (localObject1 == localObject2)
            localObject1 = ((RecommendItemNode)((List)localList.get((i + 1) % localList.size())).get(0)).mNode;
          while (true)
          {
            play((Node)localObject1);
            return;
            localObject1 = localObject2;
            if (!((Node)localObject2).nodeName.equalsIgnoreCase("program"))
            {
              localObject1 = localObject2;
              if (!((Node)localObject2).nodeName.equalsIgnoreCase("channel"))
                localObject1 = ((RecommendItemNode)((List)localList.get((i + 1) % localList.size())).get(0)).mNode;
            }
          }
        }
        j += 1;
      }
      i += 1;
    }
    play(((RecommendItemNode)((List)localList.get(0)).get(0)).mNode);
  }

  public void playNextForCar()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (localObject != null)
    {
      if (((ChannelNode)localObject).channelType != 0)
        break label47;
      if (((ChannelNode)localObject).nextSibling != null)
        play(((ChannelNode)localObject).nextSibling);
    }
    label47: 
    do
    {
      do
      {
        do
          return;
        while (((ChannelNode)localObject).channelType != 1);
        if (localNode.nextSibling != null)
        {
          play(localNode.nextSibling);
          return;
        }
      }
      while ((((ChannelNode)localObject).hasEmptyProgramSchedule()) || (((ChannelNode)localObject).getAllLstProgramNode() == null) || (!localNode.nodeName.equalsIgnoreCase("program")));
      localObject = ((ChannelNode)localObject).getProgramNodeByProgramId(((ProgramNode)localNode).id);
    }
    while ((localObject == null) || (((Node)localObject).nextSibling == null));
    play(((Node)localObject).nextSibling);
  }

  public void playPreCategory()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    List localList = InfoManager.getInstance().root().getRecommendCategoryNode(0).lstRecMain;
    if ((localList == null) || (localList.size() == 0))
      return;
    if (localObject == null)
    {
      play(((RecommendItemNode)((List)localList.get(0)).get(0)).mNode);
      return;
    }
    int i = 0;
    while (i < localList.size())
    {
      int j = 0;
      while (j < ((List)localList.get(i)).size())
      {
        if ((((RecommendItemNode)((List)localList.get(i)).get(j)).mCategoryId == ((ChannelNode)localObject).categoryId) && (i >= 0))
        {
          j = (i - 1 + localList.size()) % localList.size();
          Node localNode = ((RecommendItemNode)((List)localList.get(j)).get(0)).mNode;
          if (localNode == localObject)
            localObject = ((RecommendItemNode)((List)localList.get((i - 1 + localList.size()) % localList.size())).get(0)).mNode;
          while (true)
          {
            play((Node)localObject);
            return;
            localObject = localNode;
            if (!localNode.nodeName.equalsIgnoreCase("program"))
            {
              localObject = localNode;
              if (!localNode.nodeName.equalsIgnoreCase("channel"))
                localObject = ((RecommendItemNode)((List)localList.get((j - 1 + localList.size()) % localList.size())).get(0)).mNode;
            }
          }
        }
        j += 1;
      }
      i += 1;
    }
    play(((RecommendItemNode)((List)localList.get(0)).get(0)).mNode);
  }

  public void playPreForCar()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if (localObject != null)
    {
      if (((ChannelNode)localObject).channelType != 0)
        break label47;
      if (((ChannelNode)localObject).prevSibling != null)
        play(((ChannelNode)localObject).prevSibling);
    }
    label47: 
    do
    {
      do
      {
        do
          return;
        while (((ChannelNode)localObject).channelType != 1);
        if (localNode.prevSibling != null)
        {
          play(localNode.prevSibling);
          return;
        }
      }
      while ((((ChannelNode)localObject).hasEmptyProgramSchedule()) || (((ChannelNode)localObject).getAllLstProgramNode() == null) || (!localNode.nodeName.equalsIgnoreCase("program")));
      localObject = ((ChannelNode)localObject).getProgramNodeByProgramId(((ProgramNode)localNode).id);
    }
    while ((localObject == null) || (((Node)localObject).prevSibling == null));
    play(((Node)localObject).prevSibling);
  }

  public void playRingTone(Node paramNode)
  {
    if (paramNode == null)
      return;
    requestAudioFocus();
    stopFM();
    if (paramNode.nodeName.equalsIgnoreCase("ringtone"))
    {
      if ((this.currPlayState == 4096) && (InfoManager.getInstance().root().getCurrentPlayingNode() != null))
        stop();
      playSource(((RingToneNode)paramNode).getListenOnLineUrl());
      this.liveStream = false;
    }
    this.currPlayState = 4096;
  }

  public boolean playSource(String paramString)
  {
    if ((this.iService == null) || (paramString == null) || (paramString.equalsIgnoreCase("")))
      return false;
    try
    {
      setSource(paramString);
      this.iService.play();
      return true;
    }
    catch (RemoteException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  public int queryDuration()
  {
    if (this.iService == null)
      return -1;
    try
    {
      int i = this.iService.queryDuration();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return -1;
  }

  public boolean queryIsLiveStream()
  {
    if (this.iService == null)
      return false;
    try
    {
      boolean bool = this.iService.queryIsLiveStream();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return false;
  }

  public int queryPosition()
  {
    if (this.iService == null)
      return -1;
    try
    {
      int i = this.iService.queryPosition();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return -1;
  }

  public void recoverRecvPlay(boolean paramBoolean)
  {
    this.recvDoPlay = paramBoolean;
  }

  public void recoverSource(String paramString)
  {
    if ((this.iService == null) || (paramString == null));
    while ((this.preloaded_source != null) && (this.preloaded_source.equalsIgnoreCase(paramString)))
      return;
    this.hasRecoveredFromCrash = true;
    try
    {
      this.source = paramString;
      this.preloaded_source = paramString;
      this.iService.setSource(this.preloaded_source);
      return;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void removeMediaEventListener(IMediaEventListener paramIMediaEventListener)
  {
    Object localObject = new HashSet();
    ((HashSet)localObject).addAll(this.listeners);
    localObject = ((HashSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      WeakReference localWeakReference = (WeakReference)((Iterator)localObject).next();
      IMediaEventListener localIMediaEventListener = (IMediaEventListener)localWeakReference.get();
      if ((localIMediaEventListener == null) || (localIMediaEventListener == paramIMediaEventListener))
        this.listeners.remove(localWeakReference);
    }
  }

  public void replay(Node paramNode)
  {
    if (paramNode == null)
      return;
    requestAudioFocus();
    stopFM();
    ProgramNode localProgramNode;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      localProgramNode = (ProgramNode)paramNode;
      paramNode = ChannelHelper.getInstance().getChannel(localProgramNode);
      if (paramNode != null)
        InfoManager.getInstance().root().setPlayingChannelNode(paramNode);
      String str = "";
      if (localProgramNode.getCurrPlayStatus() != 3)
        break label199;
      if (!localProgramNode.isDownloadProgram())
        str = InfoManager.getInstance().root().getLocalProgramSource(localProgramNode);
      if (str != null)
      {
        paramNode = str;
        if (!str.equalsIgnoreCase(""));
      }
      else
      {
        paramNode = PlayCacheAgent.getInstance().getCache(localProgramNode);
        if (paramNode == null)
          break label183;
      }
      if ((this.currPlayState == 1) && ((this.source == null) || (this.source.equalsIgnoreCase(paramNode))))
        break label207;
      if (_play(paramNode))
        autoSeek(localProgramNode.id);
      label152: if (localProgramNode.getCurrPlayStatus() != 3)
        break label214;
    }
    label183: label199: label207: label214: for (this.liveStream = false; ; this.liveStream = true)
    {
      InfoManager.getInstance().root().setPlayingNode(localProgramNode);
      this.currPlayState = 4096;
      return;
      PlayCacheAgent.getInstance().cacheNode(localProgramNode);
      paramNode = localProgramNode.getSourceUrl();
      break;
      paramNode = localProgramNode.getSourceUrl();
      break;
      _resume();
      break label152;
    }
  }

  public long replayByTime(Node paramNode, long paramLong)
  {
    return -1L;
  }

  public void reset()
  {
    this.playDuration = 0;
    this.currPlayState = 30583;
    this.liveStream = true;
    stop();
    stopFM();
    exit();
    releasePlayControll();
    this.iService = null;
  }

  public void saveBattery(boolean paramBoolean)
  {
    if (this.iService == null)
      return;
    if (paramBoolean)
      try
      {
        this.iService.enableOpt();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    this.iService.disableOpt();
  }

  public void seek(float paramFloat)
  {
    if (this.iService == null);
    while ((InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.PLAY_END_ADVERTISEMENT) || (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT))
      return;
    this.playDuration = queryDuration();
    int j = (int)(this.playDuration * paramFloat);
    int i = j;
    if (j >= this.playDuration)
      i = this.playDuration - 5;
    j = i;
    if (i < 0)
      j = 0;
    this.seekTime = j;
    this.seekHandler.removeCallbacks(this.doSeek);
    this.seekHandler.postDelayed(this.doSeek, 1000L);
  }

  public void seekPosition(int paramInt)
  {
    if (this.iService == null)
      return;
    this.playDuration = queryDuration();
    if (paramInt >= this.playDuration)
      paramInt = this.playDuration - 5;
    while (true)
    {
      int i = paramInt;
      if (paramInt < 0)
        i = 0;
      try
      {
        this.iService.seek(i);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        localRemoteException.printStackTrace();
        return;
      }
    }
  }

  public void sendBufferLog()
  {
    Iterator localIterator = this.mapBufferCnt.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      int i = ((Integer)localEntry.getValue()).intValue();
      if (i > 0)
      {
        String str = QTLogger.getInstance().buildListeneringQualityLog(InfoManager.getInstance().root().getCurrentPlayingNode(), 0.0D, i, (String)localEntry.getKey());
        if (str != null)
          LogModule.getInstance().send("PlayExperience", str);
        localEntry.setValue(Integer.valueOf(0));
      }
    }
    this.mapBufferCnt.clear();
  }

  public void setBufferTime(float paramFloat)
  {
    if (this.iService == null)
      return;
    try
    {
      this.iService.setBufferTime(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public void setCurrPlayState(int paramInt)
  {
    this.currPlayState = paramInt;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.mEventHandler = paramIEventHandler;
  }

  public boolean setHttpProxy(String paramString)
  {
    boolean bool = false;
    try
    {
      if (this.iService != null)
        bool = this.iService.setHttpProxy(paramString);
      return bool;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }

  public boolean setIsViaWoProxy(boolean paramBoolean)
  {
    boolean bool = false;
    try
    {
      if (this.iService != null)
        bool = this.iService.setIsViaWoProxy(paramBoolean);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return false;
  }

  public void setLiveStream(boolean paramBoolean)
  {
    this.liveStream = paramBoolean;
  }

  public void setLocation(String paramString1, String paramString2)
  {
    try
    {
      this.iService.setLocation(paramString1, paramString2);
      return;
    }
    catch (Exception paramString1)
    {
    }
  }

  public void setPlayingChannel(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString1, String paramString2, int paramInt6)
  {
    SharedCfg.getInstance().setLastPlayInfo(paramInt1, paramInt2, paramInt3, paramInt5);
    if (this.iService != null);
    try
    {
      this.iService.setPlayingChannel(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, this.mPlaySource, paramString1, paramString2, paramInt6);
      return;
    }
    catch (RemoteException paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  public void setPlayingChannelThumb(String paramString)
  {
    if (this.iService != null);
    try
    {
      this.iService.setPlayingChannelThumb(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void setServiceControl(IServiceControl paramIServiceControl)
  {
    this.iService = paramIServiceControl;
  }

  public void setSource(Node paramNode)
  {
    String str = null;
    if (paramNode == null);
    do
    {
      return;
      if (!paramNode.nodeName.equalsIgnoreCase("channel"))
        break label165;
      if (((ChannelNode)paramNode).channelType != 1)
        break;
      paramNode = ((ChannelNode)paramNode).getProgramNodeByTime(System.currentTimeMillis());
    }
    while ((paramNode == null) || (paramNode.channelType == 1));
    paramNode = paramNode.getSourceUrl();
    while (true)
    {
      label59: Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if (localObject != null)
      {
        if (((Node)localObject).nodeName.equalsIgnoreCase("channel"))
          str = ((ChannelNode)localObject).getSourceUrl();
        while (true)
        {
          if ((str == null) || (paramNode == null))
            break label235;
          if (str.equalsIgnoreCase(paramNode))
            break;
          getInstance().setSource(paramNode);
          return;
          localObject = ((ChannelNode)paramNode).getSourceUrl();
          ProgramNode localProgramNode = ((ChannelNode)paramNode).getProgramNodeByTime(System.currentTimeMillis());
          paramNode = (Node)localObject;
          if (localProgramNode != null)
          {
            paramNode = (Node)localObject;
            if (localProgramNode.getCurrPlayStatus() == 1)
              paramNode = localProgramNode.getSourceUrl();
          }
          break label59;
          label165: if (!paramNode.nodeName.equalsIgnoreCase("program"))
            break label259;
          if (((ProgramNode)paramNode).getCurrPlayStatus() == 3)
          {
            paramNode = ((ProgramNode)paramNode).getSourceUrl();
            break label59;
          }
          paramNode = ((ProgramNode)paramNode).getSourceUrl();
          break label59;
          if (((Node)localObject).nodeName.equalsIgnoreCase("program"))
            str = ((ProgramNode)localObject).getSourceUrl();
        }
        label235: if (paramNode == null)
          break;
        getInstance().setSource(paramNode);
        return;
      }
      if (paramNode == null)
        break;
      getInstance().setSource(paramNode);
      return;
      label259: paramNode = null;
    }
  }

  public void setVolume(float paramFloat)
  {
    if (this.iService == null)
      return;
    try
    {
      this.iService.setVolume(paramFloat);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public void setWillPlayNode(Node paramNode)
  {
    if (paramNode == null)
      return;
    setSource(paramNode);
    InfoManager.getInstance().root().setWillPlayNode(paramNode);
  }

  public void startFM(RadioChannelNode paramRadioChannelNode)
  {
    if (paramRadioChannelNode != null)
    {
      if (InfoManager.getInstance().root().isOpenFm())
        FMManager.getInstance().turnOff();
      FMManager.getInstance().tune(Integer.valueOf(paramRadioChannelNode.freq).intValue());
      getInstance().dispatchPlayStateInFM(4096);
      InfoManager.getInstance().root().setPlayingNode(paramRadioChannelNode);
      InfoManager.getInstance().root().tuneFM(true);
    }
  }

  public void startQuitTimer()
  {
    if (this.iService == null)
      return;
    try
    {
      this.iService.startQuitTimer();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public void stop()
  {
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.ALARM_PLAY_ONLINE)
    {
      dispatchPlayState(0);
      _stop();
      this.currPlayState = 0;
    }
    do
    {
      return;
      if ((InfoManager.getInstance().root().currentPlayMode() != RootNode.PlayMode.PLAY_END_ADVERTISEMENT) && (InfoManager.getInstance().root().currentPlayMode() != RootNode.PlayMode.PLAY_FRONT_ADVERTISEMENT))
        break;
      dispatchPlayState(0);
      _pause();
      this.currPlayState = 1;
    }
    while (!InfoManager.getInstance().getTaobaoAudioAdv());
    TaobaoAgent.getInstance().stopAD();
    return;
    this.mConnected = false;
    dispatchPlayState(0);
    SystemPlayer.getInstance().stop();
    autoReserve();
    this.beginPlay = 0L;
    int i = queryPosition();
    int j = queryDuration();
    if ((this.liveStream) || (!this.hasPlayed))
      _stop();
    for (this.currPlayState = 0; ; this.currPlayState = 1)
    {
      PlayedMetaInfo.getInstance().addPlayedMeta(InfoManager.getInstance().root().getCurrentPlayingNode(), i, j);
      return;
      _pause();
    }
  }

  public void stopFM()
  {
    if (InfoManager.getInstance().root().currentPlayMode() == RootNode.PlayMode.FMPLAY)
    {
      FMManager.getInstance().turnOff();
      InfoManager.getInstance().root().tuneFM(false);
    }
  }

  public void stopQuitTimer()
  {
    if (this.iService == null)
      return;
    try
    {
      this.iService.stopQuitTimer();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }

  public void stopWithoutDispatchState()
  {
    long l = queryPosition();
    int i = queryDuration();
    if ((this.liveStream) || (!this.hasPlayed))
      _stop();
    for (this.currPlayState = 0; ; this.currPlayState = 1)
    {
      PlayedMetaInfo.getInstance().addPlayedMeta(InfoManager.getInstance().root().getCurrentPlayingNode(), (int)l, i);
      return;
      _pause();
    }
  }

  public void unbindService()
  {
    try
    {
      this._context.unbindService(this.conn);
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      try
      {
        while (true)
        {
          if (this.recevier != null)
          {
            this._context.unregisterReceiver(this.recevier);
            this.recevier = null;
          }
          return;
          localIllegalArgumentException1 = localIllegalArgumentException1;
          localIllegalArgumentException1.printStackTrace();
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        localIllegalArgumentException2.printStackTrace();
      }
    }
  }

  public boolean unsetHttpProxy()
  {
    boolean bool = false;
    try
    {
      if (this.iService != null)
        bool = this.iService.unsetHttpProxy();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
    return false;
  }

  public void updatePlayStatus(PlayStatus paramPlayStatus)
  {
    if (paramPlayStatus.state == 8192)
    {
      this.currPlayState = 0;
      paramPlayStatus = QTLogger.getInstance().buildResourceUnavailLog(InfoManager.getInstance().root().getCurrentPlayingNode());
      if (paramPlayStatus != null)
        QTLogger.getInstance().sendResourceUnavailLog(paramPlayStatus);
    }
  }

  public void updateRePlayInfoByNode(Node paramNode)
  {
    if (paramNode == null);
    String str;
    do
    {
      do
        return;
      while (!paramNode.nodeName.equalsIgnoreCase("program"));
      str = ((ProgramNode)paramNode).getSourceUrl();
    }
    while ((str == null) || (str.equalsIgnoreCase("")));
    this.preloaded_source = str;
    this.source = str;
    this.liveStream = false;
    InfoManager.getInstance().root().setPlayingNode(paramNode);
  }

  class PlayControllReceiver extends BroadcastReceiver
  {
    PlayControllReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null);
      label538: 
      while (true)
      {
        return;
        if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PAUSE"))
        {
          MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "pause");
          if (PlayerAgent.this.isPlaying())
            PlayerAgent.this.stop();
        }
        else
        {
          if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY"))
          {
            MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "play");
            paramContext = InfoManager.getInstance().root().getCurrentPlayingNode();
            if (paramContext != null)
            {
              PlayerAgent.this.play(paramContext);
              return;
            }
            paramContext = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
            PlayerAgent.this.play(paramContext);
            return;
          }
          if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_NEXT"))
          {
            MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "playnext");
            PlayerAgent.this.playNextForCar();
            return;
          }
          if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_PRE"))
          {
            MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "playpre");
            PlayerAgent.this.playPreForCar();
            return;
          }
          if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_NEXT_CATEGORY"))
          {
            MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "playnextcat");
            PlayerAgent.this.playNextCategory();
            label218: paramContext = paramIntent.getExtras();
            if (paramContext != null)
            {
              paramContext = paramContext.keySet();
              if (paramContext != null)
                paramContext = paramContext.iterator();
            }
          }
          else
          {
            while (true)
            {
              if (!paramContext.hasNext())
                break label538;
              paramIntent = (String)paramContext.next();
              if (paramIntent != null)
              {
                if (paramIntent.equalsIgnoreCase("toggleplay"))
                {
                  abortBroadcast();
                  if (PlayerAgent.this.isPlaying())
                  {
                    PlayerAgent.this.stop();
                    continue;
                    if (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_PRE_CATEGORY"))
                      break label218;
                    MobclickAgent.onEvent(PlayerAgent.this._context, "fujia", "playprecat");
                    PlayerAgent.this.playPreCategory();
                    break label218;
                  }
                  paramIntent = InfoManager.getInstance().root().getCurrentPlayingNode();
                  if (paramIntent == null)
                    continue;
                  PlayerAgent.this.play(paramIntent);
                  continue;
                }
                if (paramIntent.equalsIgnoreCase("playpre"))
                {
                  abortBroadcast();
                  paramIntent = InfoManager.getInstance().root().getCurrentPlayingNode();
                  if (paramIntent == null)
                    continue;
                  if (paramIntent.nodeName.equalsIgnoreCase("program"))
                  {
                    paramContext = ((ProgramNode)paramIntent).getPrevSibling();
                    if ((paramContext != null) && (paramContext.getCurrPlayStatus() == 2))
                      break;
                    PlayerAgent.this.play(paramContext);
                    return;
                  }
                  PlayerAgent.this.play(paramIntent.prevSibling);
                  continue;
                }
                if (paramIntent.equalsIgnoreCase("playnext"))
                {
                  abortBroadcast();
                  paramIntent = InfoManager.getInstance().root().getCurrentPlayingNode();
                  if (paramIntent == null)
                    continue;
                  if (paramIntent.nodeName.equalsIgnoreCase("program"))
                  {
                    paramContext = ((ProgramNode)paramIntent).getNextSibling();
                    if ((paramContext != null) && (paramContext.getCurrPlayStatus() == 2))
                      break;
                    PlayerAgent.this.play(paramContext);
                    return;
                  }
                  PlayerAgent.this.play(paramIntent.nextSibling);
                  continue;
                }
                if (paramIntent.equalsIgnoreCase("eof"))
                  abortBroadcast();
              }
            }
          }
        }
      }
    }
  }

  class QTReceiver extends BroadcastReceiver
  {
    QTReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getExtras();
      paramIntent = paramContext.keySet().iterator();
      while (true)
      {
        Object localObject;
        if (paramIntent.hasNext())
        {
          localObject = (String)paramIntent.next();
          if (((String)localObject).equals("playstatus"))
          {
            localObject = (PlayStatus)paramContext.get((String)localObject);
            if (localObject != null)
            {
              int i = ((PlayStatus)localObject).getPlayingState();
              int j = ((PlayStatus)localObject).getState();
              if (PlayerAgent.this.currPlayState == 4096)
              {
                if (i != 0)
                  break label112;
                if ((j == 2) && (!PlayerAgent.this.liveStream))
                  PlayerAgent.this.dispatchMediaEvent(1, localObject);
              }
            }
          }
        }
        else
        {
          return;
          label112: PlayerAgent.this.dispatchMediaEvent(1, localObject);
          PlayerAgent.this.updatePlayStatus((PlayStatus)localObject);
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fm.PlayerAgent
 * JD-Core Version:    0.6.2
 */