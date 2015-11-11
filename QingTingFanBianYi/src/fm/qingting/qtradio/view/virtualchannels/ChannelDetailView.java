package fm.qingting.qtradio.view.virtualchannels;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.ChannelHelper.IDataChangeObserver;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.PlayedMetaData;
import fm.qingting.qtradio.model.PlayedMetaInfo;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChannelDetailView extends ViewGroupViewImpl
  implements RootNode.IPlayInfoEventListener, InfoManager.ISubscribeEventListener, DownLoadInfoNode.IDownloadInfoEventListener, ChannelHelper.IDataChangeObserver, IEventHandler
{
  static final String TAG = "ChannelDetailView";
  private static List<Integer> userCancelChannels = new ArrayList();
  private final ViewLayout coverLayout = this.standardLayout.createChildLT(720, 484, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private CustomizedAdapter mAdapter;
  private ChannelNode mChannelNode;
  private ChannelDetailCoverView mCoverView;
  private IAdapterIViewFactory mFactory;
  private boolean mFirstTime = true;
  private LoadMoreFootView mFooterView;
  private RotateLoadingLayout mHeaderLayout;
  private int mLastOffset = 0;
  private LinearLayout mLinearLayout;
  private PullToRefreshListView mListView;
  private MiniPlayerView mMiniView;
  private int mOrder;
  private PlayedMetaData mRecentIncompleteProgram;
  private PlayHistoryNode mRecentNode;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout suspendLayout = this.standardLayout.createChildLT(720, 169, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChannelDetailView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor_item());
    final int i = hashCode();
    this.mLinearLayout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903061, null));
    this.mListView = ((PullToRefreshListView)this.mLinearLayout.findViewById(2131230731));
    this.mHeaderLayout = new RotateLoadingLayout(paramContext);
    this.mListView.addListHeaderView(this.mHeaderLayout);
    this.mFooterView = new LoadMoreFootView(paramContext);
    this.mFooterView.setBackgroundColor(SkinManager.getBackgroundColor_item());
    this.mListView.addListFooterView(this.mFooterView);
    this.mListView.setSelector(17170445);
    MiniPlayerPlaceHolder.wrapListView(paramContext, (ListView)this.mListView.getRefreshableView());
    addView(this.mLinearLayout);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        int i;
        int j;
        if ((paramAnonymousInt1 == 0) || (paramAnonymousInt1 == 1))
        {
          paramAnonymousAbsListView = paramAnonymousAbsListView.getChildAt(0);
          if (paramAnonymousAbsListView != null)
          {
            i = paramAnonymousAbsListView.getTop();
            j = ChannelDetailView.this.getMaxTranslationY();
            if ((i <= 0) && (i >= j))
              ChannelDetailView.this.moveHead(i);
          }
          if (paramAnonymousInt2 != paramAnonymousInt3)
            break label113;
          ChannelDetailView.this.hideFooterView();
        }
        label113: 
        while ((ChannelDetailView.this.mFooterView.isAll()) || (ChannelDetailView.this.mFooterView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
        {
          return;
          paramAnonymousAbsListView = paramAnonymousAbsListView.getChildAt(0);
          if (paramAnonymousAbsListView == null)
            break;
          i = paramAnonymousAbsListView.getTop();
          j = ChannelDetailView.this.getMaxTranslationY();
          if (i < j)
            break;
          ChannelDetailView.this.moveHead(j);
          break;
        }
        ChannelDetailView.this.mFooterView.showLoad();
        ChannelDetailView.this.loadMore(paramAnonymousInt3 - 1);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new ChannelDetailItemView(ChannelDetailView.this.getContext(), i);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener()
    {
      public void onRefresh(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase)
      {
        InfoManager.getInstance().reloadVirtualProgramsSchedule(ChannelDetailView.this.mChannelNode, ChannelDetailView.this);
      }
    });
    this.mListView.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener()
    {
      public void onPullEvent(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase, PullToRefreshBase.State paramAnonymousState, PullToRefreshBase.Mode paramAnonymousMode)
      {
        switch (ChannelDetailView.5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[paramAnonymousState.ordinal()])
        {
        default:
          return;
        case 1:
          ChannelDetailView.this.mHeaderLayout.reset();
          return;
        case 2:
          ChannelDetailView.this.mHeaderLayout.pullToRefresh();
          return;
        case 3:
          ChannelDetailView.this.mHeaderLayout.releaseToRefresh();
          return;
        case 4:
        case 5:
        }
        ChannelDetailView.this.mHeaderLayout.refreshing();
      }
    });
    this.mCoverView = new ChannelDetailCoverView(paramContext);
    this.mCoverView.setEventHandler(this);
    addView(this.mCoverView);
    this.mMiniView = new MiniPlayerView(paramContext);
    addView(this.mMiniView);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
    InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
  }

  private int getMaxTranslationY()
  {
    return this.suspendLayout.height - this.coverLayout.height;
  }

  private void handleAutoPlay(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null);
    Object localObject;
    do
    {
      do
      {
        do
          return;
        while (((!paramChannelNode.autoPlay) && (!paramChannelNode.autoPlay())) || (paramChannelNode.hasEmptyProgramSchedule()));
        localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      }
      while ((localObject != null) && (((ChannelNode)localObject).channelId == paramChannelNode.channelId));
      localObject = paramChannelNode.getAllLstProgramNode();
    }
    while ((localObject == null) || (((List)localObject).size() <= 0) || ((PlayerAgent.getInstance().isPlaying()) && (!paramChannelNode.autoPlay())));
    PlayerAgent.getInstance().play((Node)((List)localObject).get(0));
  }

  private void hideFooterView()
  {
    this.mFooterView.hideLoad();
  }

  private void initData()
  {
    if (this.mChannelNode == null)
      return;
    if (this.mListView != null)
      this.mListView.setSelection(0);
    if (this.mChannelNode.hasEmptyProgramSchedule())
    {
      this.mListView.setRefreshing();
      this.mCoverView.setButtonEnable(false);
      return;
    }
    setProgramList(this.mChannelNode.getAllLstProgramNode());
  }

  private void loadMore(int paramInt)
  {
    InfoManager.getInstance().loadProgramsScheduleNode(this.mChannelNode, this);
  }

  @TargetApi(11)
  private void moveHead(int paramInt)
  {
    if (this.mLastOffset == paramInt)
      return;
    this.mLastOffset = paramInt;
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mCoverView.setTranslationY(paramInt);
      return;
    }
    this.mCoverView.layout(0, paramInt, this.standardLayout.width, this.coverLayout.height + paramInt);
  }

  private void sendProgramsShowLog(List<ProgramNode> paramList, ChannelNode paramChannelNode)
  {
    if ((paramList == null) || (paramChannelNode == null));
    do
    {
      do
        return;
      while (paramChannelNode.channelType == 0);
      paramList = QTLogger.getInstance().buildProgramsShowLog(paramList, paramChannelNode.categoryId, paramChannelNode.categoryId, paramChannelNode.channelId, InfoManager.getInstance().getProgramPageSize(), paramChannelNode.isNovelChannel());
    }
    while (paramList == null);
    LogModule.getInstance().send("ProgramsShowV6", paramList);
  }

  private boolean setProgramList(List<ProgramNode> paramList)
  {
    if (paramList == null)
      return false;
    Object localObject1 = PlayedMetaInfo.getInstance().getPlayedMetadata();
    int i;
    if ((this.mChannelNode != null) && (userCancelChannels.contains(Integer.valueOf(this.mChannelNode.channelId))))
    {
      i = 1;
      if (ControllerManager.getInstance().getChannelSource() != 1)
        break label182;
    }
    Object localObject2;
    label182: for (int j = 1; ; j = 0)
    {
      this.mRecentIncompleteProgram = null;
      if ((i != 0) || (j != 0) || (localObject1 == null) || (((List)localObject1).size() <= 0))
        break label188;
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PlayedMetaData)((Iterator)localObject1).next();
        if ((this.mChannelNode.channelId == ((PlayedMetaData)localObject2).channelId) && (((PlayedMetaData)localObject2).position > 5) && (((PlayedMetaData)localObject2).position < ((PlayedMetaData)localObject2).duration - 5) && ((this.mRecentIncompleteProgram == null) || (((PlayedMetaData)localObject2).playedTime > this.mRecentIncompleteProgram.playedTime)))
          this.mRecentIncompleteProgram = ((PlayedMetaData)localObject2);
      }
      i = 0;
      break;
    }
    label188: this.mRecentNode = null;
    if (this.mRecentIncompleteProgram != null)
    {
      localObject1 = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (PlayHistoryNode)((Iterator)localObject1).next();
        if (((ProgramNode)((PlayHistoryNode)localObject2).playNode).id == this.mRecentIncompleteProgram.programId)
          this.mRecentNode = ((PlayHistoryNode)localObject2);
      }
    }
    localObject1 = new ArrayList();
    if (paramList.size() > 0)
    {
      sendProgramsShowLog(paramList, this.mChannelNode);
      localObject2 = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localObject2 != null) && (((Node)localObject2).nodeName.equalsIgnoreCase("program")))
      {
        i = 0;
        if (i < paramList.size())
          if (((ProgramNode)localObject2).id == ((ProgramNode)paramList.get(i)).id)
          {
            if ((this.mRecentNode != null) && (((ProgramNode)localObject2).id == ((ProgramNode)this.mRecentNode.playNode).id))
              this.mRecentNode = null;
            j = i;
            if (((Node)localObject2).prevSibling == null)
            {
              j = i;
              if (((Node)localObject2).nextSibling == null)
              {
                ((Node)localObject2).prevSibling = ((ProgramNode)paramList.get(i)).prevSibling;
                ((Node)localObject2).nextSibling = ((ProgramNode)paramList.get(i)).nextSibling;
                j = i;
              }
            }
            label456: handleAutoPlay(this.mChannelNode);
          }
      }
    }
    while (true)
    {
      this.mListView.onRefreshComplete();
      this.mFooterView.hideLoad();
      if (this.mRecentNode != null)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("resumerecent_display");
        ((List)localObject1).add(this.mRecentNode);
      }
      ((List)localObject1).addAll(paramList);
      this.mAdapter.setData(ListUtils.convertToObjectList((List)localObject1));
      if ((this.mFirstTime) && (j != -1) && (this.mListView != null))
      {
        this.mFirstTime = false;
        this.mListView.setSelection(j);
      }
      return true;
      i += 1;
      break;
      j = -1;
      break label456;
      j = -1;
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mMiniView.destroy();
    this.mCoverView.close(paramBoolean);
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RPS", "RECV_RELOAD_PROGRAMS_SCHEDULE" });
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if ((paramString.equalsIgnoreCase("list")) && (this.mAdapter != null))
      return this.mAdapter.getData();
    return super.getValue(paramString, paramObject);
  }

  public void onChannelNodeInfoUpdate(ChannelNode paramChannelNode)
  {
    if ((this.mChannelNode != null) && (this.mChannelNode.channelId == paramChannelNode.channelId))
    {
      this.mChannelNode.updateAllInfo(paramChannelNode);
      this.mCoverView.update("setData", this.mChannelNode);
      dispatchActionEvent("resetNavi", null);
    }
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if ((paramInt == 8) || (paramInt == 1) || (paramInt == 4))
    {
      if ((this.mChannelNode == null) || (paramNode == null) || (paramNode.parent == null) || (!paramNode.parent.nodeName.equalsIgnoreCase("channel")))
        break label78;
      if (this.mChannelNode.channelId == ((ChannelNode)paramNode.parent).channelId)
        this.mAdapter.notifyDataSetChanged();
    }
    label78: 
    while ((this.mChannelNode == null) || (paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("program")) || (this.mChannelNode.channelId != ((ProgramNode)paramNode).channelId))
      return;
    this.mAdapter.notifyDataSetChanged();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("changeOrder"))
    {
      this.mOrder = ((Integer)paramObject2).intValue();
      InfoManager.getInstance().root().setProgramListOrder(this.mChannelNode.channelId, this.mOrder);
      this.mListView.setRefreshing();
      this.mCoverView.setButtonEnable(false);
    }
    while (!paramString.equalsIgnoreCase("showChapters"))
      return;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLinearLayout.layout(0, this.suspendLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.coverLayout.layoutView(this.mCoverView);
    this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.coverLayout.scaleToBounds(this.standardLayout);
    this.suspendLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.mMiniView);
    this.coverLayout.measureView(this.mCoverView);
    this.mLinearLayout.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.suspendLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RPS"))
      if (setProgramList(this.mChannelNode.getAllLstProgramNode()));
    while (!paramString.equalsIgnoreCase("RECV_RELOAD_PROGRAMS_SCHEDULE"))
      return;
    setProgramList(this.mChannelNode.reloadAllLstProgramNode());
    this.mListView.onRefreshComplete();
    this.mCoverView.setButtonEnable(true);
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    if ((paramInt == 1) && (this.mAdapter != null))
      this.mAdapter.notifyDataSetChanged();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
    Log.e("ChannelDetailView", "sym:" + paramString + paramDataExceptionStatus);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (ChannelNode)paramObject;
      if (this.mChannelNode != paramString);
    }
    do
    {
      do
      {
        return;
        this.mChannelNode = paramString;
        ChannelHelper.getInstance().addObserver(this.mChannelNode.channelId, this);
        this.mOrder = InfoManager.getInstance().root().getProgramListOrder(paramString.channelId);
        this.mCoverView.update("setData", paramObject);
        initData();
        return;
        if (!paramString.equalsIgnoreCase("refreshUploadView"))
          break;
      }
      while (!QtApiLevelManager.isApiLevelSupported(19));
      return;
      if (paramString.equalsIgnoreCase("closerecentplay"))
      {
        if (this.mChannelNode != null)
          userCancelChannels.add(Integer.valueOf(this.mChannelNode.channelId));
        initData();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setpodcasterinfo"));
    this.mCoverView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailView
 * JD-Core Version:    0.6.2
 */