package fm.qingting.qtradio.view.virtualchannels;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
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
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.qtradio.view.LoadMoreListView;
import fm.qingting.qtradio.view.LoadMoreListView.OnCrossTopListener;
import fm.qingting.qtradio.view.LoadMoreListView.onLoadMoreListener;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChannelDetailViewNew extends ViewGroupViewImpl
  implements IEventHandler, RootNode.IPlayInfoEventListener, InfoManager.ISubscribeEventListener, DownLoadInfoNode.IDownloadInfoEventListener
{
  private final boolean DEBUG = false;
  private int NovelCnt = 99;
  private final ViewLayout coverLayout = this.standardLayout.createChildLT(720, 420, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ChannelDetailTagView listTitleView;
  private CustomizedAdapter mAdapter;
  private ChannelNode mChannelNode;
  private ProgramIntervalChooseViewGroup mChooseView;
  private EmptyHeadView mEmptyHeadView;
  private boolean mExpand = false;
  private IAdapterIViewFactory mFactory;
  private boolean mFirstTime = true;
  private int mLastOffset = 0;
  private LoadMoreListView mListView;
  private MiniPlayerView mMiniView;
  private int mPageStart = 1;
  private final ViewLayout operateLayout = this.standardLayout.createChildLT(720, 105, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tagLayout = this.standardLayout.createChildLT(720, 68, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ChannelDetailCoverView thumbView;

  public ChannelDetailViewNew(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    final int i = hashCode();
    this.mListView = new LoadMoreListView(paramContext);
    this.mEmptyHeadView = new EmptyHeadView(paramContext);
    this.mListView.addHeaderView(this.mEmptyHeadView);
    addView(this.mListView);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    this.mListView.setOnLoadMoreListener(new LoadMoreListView.onLoadMoreListener()
    {
      public void onLoadMore(int paramAnonymousInt)
      {
        ChannelDetailViewNew.this.loadMore(paramAnonymousInt);
      }
    });
    this.mListView.setOnCrossTopListener(new LoadMoreListView.OnCrossTopListener()
    {
      public void onCrossTop(int paramAnonymousInt)
      {
        ChannelDetailViewNew.this.moveHead(paramAnonymousInt);
      }
    });
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new ChannelDetailItemView(ChannelDetailViewNew.this.getContext(), i);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mListView.setAdapter(this.mAdapter);
    this.mChooseView = new ProgramIntervalChooseViewGroup(paramContext);
    this.mChooseView.setEventHandler(this);
    addView(this.mChooseView);
    this.thumbView = new ChannelDetailCoverView(paramContext);
    addView(this.thumbView);
    this.listTitleView = new ChannelDetailTagView(paramContext);
    this.listTitleView.setTagName("节目列表");
    this.listTitleView.setArrow();
    this.listTitleView.setEventHandler(this);
    addView(this.listTitleView);
    this.mMiniView = new MiniPlayerView(paramContext);
    addView(this.mMiniView);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
    InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
  }

  private void handleAutoPlay(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null);
    do
    {
      Object localObject1;
      do
      {
        do
          return;
        while ((!paramChannelNode.autoPlay) || (paramChannelNode.hasEmptyProgramSchedule()));
        Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
        Object localObject2 = null;
        localObject1 = localObject2;
        if (localNode != null)
        {
          localObject1 = localObject2;
          if (localNode.nodeName.equalsIgnoreCase("program"))
            localObject1 = localNode.parent;
        }
      }
      while ((localObject1 != null) && (((Node)localObject1).nodeName.equalsIgnoreCase("channel")) && (((ChannelNode)localObject1).channelId == paramChannelNode.channelId));
      paramChannelNode = paramChannelNode.getLstProgramNode(Calendar.getInstance().get(7));
    }
    while ((paramChannelNode == null) || (paramChannelNode.size() <= 0) || (PlayerAgent.getInstance().isPlaying()));
    PlayerAgent.getInstance().play((Node)paramChannelNode.get(0));
  }

  private void initData()
  {
    if (this.mChannelNode == null);
    while (!this.mChannelNode.nodeName.equalsIgnoreCase("channel"))
      return;
    if (this.mChannelNode.hasEmptyProgramSchedule())
    {
      InfoManager.getInstance().loadProgramsScheduleByRange(this.mChannelNode.channelId, this.mPageStart - 1, this);
      return;
    }
    setProgramListByRange(true);
  }

  private void loadMore(int paramInt)
  {
    log("loadMore:" + this.mAdapter.getCount() + "_start:" + this.mPageStart);
    InfoManager.getInstance().loadProgramsScheduleByRange(this.mChannelNode.channelId, this.mPageStart + this.mAdapter.getCount(), this);
  }

  private void log(String paramString)
  {
  }

  @TargetApi(11)
  private void moveHead(int paramInt)
  {
    int i = 0;
    if (this.mLastOffset == paramInt)
      return;
    this.mLastOffset = paramInt;
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.thumbView.setTranslationY(paramInt);
      ProgramIntervalChooseViewGroup localProgramIntervalChooseViewGroup = this.mChooseView;
      if (this.mExpand)
        i = this.mChooseView.getMeasuredHeight();
      localProgramIntervalChooseViewGroup.setTranslationY(i + paramInt);
      this.listTitleView.setTranslationY(paramInt);
      return;
    }
    this.thumbView.layout(0, paramInt, this.standardLayout.width, this.coverLayout.height + paramInt);
    this.mChooseView.layout(0, this.coverLayout.height + this.tagLayout.height - this.mChooseView.getMeasuredHeight() + paramInt, this.standardLayout.width, this.coverLayout.height + this.tagLayout.height + paramInt);
    this.listTitleView.layout(0, this.coverLayout.height + paramInt, this.standardLayout.width, this.coverLayout.height + paramInt + this.tagLayout.height);
  }

  private void sendProgramsShowLog(List<ProgramNode> paramList, ChannelNode paramChannelNode)
  {
    if ((paramList == null) || (paramChannelNode == null));
    do
    {
      do
        return;
      while (paramChannelNode.channelType == 0);
      paramList = QTLogger.getInstance().buildProgramsShowLog(paramList, paramChannelNode.categoryId, paramChannelNode.categoryId, paramChannelNode.channelId, InfoManager.getInstance().getNovelPageSize(), paramChannelNode.isNovelChannel());
    }
    while (paramList == null);
    LogModule.getInstance().send("ProgramsShowV6", paramList);
  }

  private boolean setProgramList()
  {
    if ((this.mChannelNode == null) || (!this.mChannelNode.nodeName.equalsIgnoreCase("channel")));
    int i;
    List localList;
    do
    {
      do
        return false;
      while (this.mChannelNode.hasEmptyProgramSchedule());
      i = Calendar.getInstance().get(7);
      localList = this.mChannelNode.getLstProgramNode(i);
    }
    while (localList == null);
    int j;
    if (localList.size() > 0)
    {
      Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
      {
        i = 0;
        if (i < localList.size())
          if (((ProgramNode)localNode).uniqueId == ((ProgramNode)localList.get(i)).uniqueId)
          {
            j = i;
            if (localNode.prevSibling == null)
            {
              j = i;
              if (localNode.nextSibling == null)
              {
                localNode.prevSibling = ((ProgramNode)localList.get(i)).prevSibling;
                localNode.nextSibling = ((ProgramNode)localList.get(i)).nextSibling;
                j = i;
              }
            }
            label185: handleAutoPlay(this.mChannelNode);
          }
      }
    }
    while (true)
    {
      this.mAdapter.setData(ListUtils.convertToObjectList(localList));
      this.mListView.cancelLoadState();
      if ((this.mFirstTime) && (j != -1) && (this.mListView != null))
      {
        this.mFirstTime = false;
        this.mListView.setSelection(j);
      }
      return true;
      i += 1;
      break;
      j = -1;
      break label185;
      j = -1;
    }
  }

  private boolean setProgramListByRange(boolean paramBoolean)
  {
    if ((this.mChannelNode == null) || (!this.mChannelNode.nodeName.equalsIgnoreCase("channel")));
    while (this.mChannelNode.hasEmptyProgramSchedule())
      return false;
    ArrayList localArrayList = new ArrayList();
    if (this.mChannelNode.getCurrLstProgramNodes(this.mPageStart, localArrayList, 50) == null)
    {
      this.mListView.setVisibility(4);
      InfoManager.getInstance().loadProgramsScheduleByRange(this.mChannelNode.channelId, this.mPageStart, this);
      return false;
    }
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    int j;
    int i;
    if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
    {
      j = ((ProgramNode)localNode).uniqueId;
      i = 0;
      if (i < localArrayList.size())
        if (j != ((ProgramNode)localArrayList.get(i)).uniqueId);
    }
    while (true)
    {
      handleAutoPlay(this.mChannelNode);
      j = localArrayList.size();
      if ((j < this.NovelCnt) && (((ProgramNode)localArrayList.get(j - 1)).sequence < this.mChannelNode.programCnt));
      log("setProgramListByRange:" + localArrayList.size());
      sendProgramsShowLog(localArrayList, this.mChannelNode);
      this.mAdapter.setData(ListUtils.convertToObjectList(localArrayList));
      this.mListView.setVisibility(0);
      this.mListView.cancelLoadState();
      if (paramBoolean)
        this.mListView.setSelection(0);
      while (true)
      {
        return true;
        i += 1;
        break;
        if ((this.mFirstTime) && (i != -1) && (this.mListView != null))
        {
          this.mFirstTime = false;
          this.mListView.setSelection(i);
        }
      }
      i = -1;
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mMiniView.destroy();
    this.thumbView.close(paramBoolean);
    this.mChooseView.close(paramBoolean);
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if ((paramString.equalsIgnoreCase("list")) && (this.mAdapter != null))
      return this.mAdapter.getData();
    return super.getValue(paramString, paramObject);
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

  @TargetApi(11)
  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    dispatchActionEvent(paramString, paramObject2);
    if (paramString.equalsIgnoreCase("expand"))
      if (this.mExpand)
        if (!this.mExpand)
        {
          this.mExpand = bool1;
          if (!QtApiLevelManager.isApiLevelSupported(11))
            break label83;
          this.mChooseView.setTranslationY(this.mLastOffset);
          this.mEmptyHeadView.update("extraHeight", Integer.valueOf(0));
        }
    label83: label221: 
    while (!paramString.equalsIgnoreCase("jumptopoint"))
    {
      while (true)
      {
        return;
        bool1 = false;
        continue;
        this.mChooseView.layout(0, this.mLastOffset + this.coverLayout.height + this.tagLayout.height - this.mChooseView.getMeasuredHeight(), this.standardLayout.width, this.mLastOffset + this.coverLayout.height + this.tagLayout.height);
      }
      if (!this.mExpand)
      {
        bool1 = bool2;
        this.mExpand = bool1;
        if (!QtApiLevelManager.isApiLevelSupported(11))
          break label221;
        this.mChooseView.setTranslationY(this.mLastOffset + this.mChooseView.getMeasuredHeight());
      }
      while (true)
      {
        this.mEmptyHeadView.update("extraHeight", Integer.valueOf(this.mChooseView.getMeasuredHeight()));
        return;
        bool1 = false;
        break;
        this.mChooseView.layout(0, this.mLastOffset + this.coverLayout.height + this.tagLayout.height, this.standardLayout.width, this.mLastOffset + this.mChooseView.getMeasuredHeight() + this.coverLayout.height + this.tagLayout.height);
      }
    }
    this.mPageStart = ((Integer)paramObject2).intValue();
    log("jumpto:" + this.mPageStart);
    setProgramListByRange(true);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, this.operateLayout.height + this.tagLayout.height, this.standardLayout.width, this.standardLayout.height - this.mMiniView.getHeightWithoutShadow());
    this.coverLayout.layoutView(this.thumbView);
    this.listTitleView.layout(0, this.coverLayout.height, this.standardLayout.width, this.coverLayout.height + this.tagLayout.height);
    this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
    this.mChooseView.layout(0, this.coverLayout.height + this.tagLayout.height - this.mChooseView.getMeasuredHeight(), this.standardLayout.width, this.coverLayout.height + this.tagLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.coverLayout.scaleToBounds(this.standardLayout);
    this.operateLayout.scaleToBounds(this.standardLayout);
    this.tagLayout.scaleToBounds(this.standardLayout);
    this.mListView.setCrossScope(this.coverLayout.height - this.operateLayout.height);
    this.standardLayout.measureView(this.mMiniView);
    this.coverLayout.measureView(this.thumbView);
    this.tagLayout.measureView(this.listTitleView);
    this.standardLayout.measureView(this.mChooseView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.mMiniView.getHeightWithoutShadow() - this.operateLayout.height - this.tagLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RPS"))
      setProgramListByRange(false);
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    if ((paramInt == 1) && (this.mAdapter != null))
      this.mAdapter.notifyDataSetChanged();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  @TargetApi(11)
  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (ChannelNode)paramObject;
      if (this.mChannelNode != paramString);
    }
    while (!paramString.equalsIgnoreCase("expand"))
    {
      return;
      this.mChannelNode = paramString;
      this.thumbView.update("setData", paramObject);
      this.mChooseView.update("setData", Integer.valueOf(this.mChannelNode.programCnt));
      this.listTitleView.setCount(this.mChannelNode.programCnt);
      initData();
      return;
    }
    this.mExpand = true;
    if (QtApiLevelManager.isApiLevelSupported(11))
      this.mChooseView.setTranslationY(this.mLastOffset + this.mChooseView.getMeasuredHeight());
    while (true)
    {
      this.mEmptyHeadView.update("extraHeight", Integer.valueOf(this.mChooseView.getMeasuredHeight()));
      return;
      this.mChooseView.layout(0, this.mLastOffset + this.coverLayout.height + this.tagLayout.height, this.standardLayout.width, this.mLastOffset + this.mChooseView.getMeasuredHeight() + this.coverLayout.height + this.tagLayout.height);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailViewNew
 * JD-Core Version:    0.6.2
 */