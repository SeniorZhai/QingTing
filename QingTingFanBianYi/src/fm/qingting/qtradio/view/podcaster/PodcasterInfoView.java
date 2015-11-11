package fm.qingting.qtradio.view.podcaster;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.helper.PodcasterHelper;
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
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;
import fm.qingting.qtradio.view.virtualchannellist.VirtualChannelItemView;
import fm.qingting.qtradio.view.virtualchannels.ChannelDetailItemView;
import fm.qingting.qtradio.view.virtualchannels.ChannelDetailTagView;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PodcasterInfoView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, RootNode.IPlayInfoEventListener, DownLoadInfoNode.IDownloadInfoEventListener, UserDataUtil.UserDataResponse
{
  static final String TAG = "PodcasterInfoView";
  private PodcasterInfoListAdapter mAdapter;
  private int mBaseInfoState = 0;
  private int mChannelState = 0;
  private RotateLoadingLayout mHeaderLayout;
  private PodcasterInfoHeaderView mHeaderView;
  private int mLastOffset = 0;
  private LinearLayout mLinearLayout;
  private MiniPlayerView mMiniView;
  private UserInfo mPodcasterInfo;
  private int mProgramState = 0;
  private PullToRefreshListView mPtrListView;
  private int mStatusBarHeight = 0;
  private final ViewLayout naviLayout = this.standardLayout.createChildLT(720, 86, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public PodcasterInfoView(Context paramContext)
  {
    super(paramContext);
    if (QtApiLevelManager.isApiLevelSupported(19));
    for (int i = NaviUtil.getStatusBarHeight(getResources()); ; i = 0)
    {
      this.mStatusBarHeight = i;
      setBackgroundColor(SkinManager.getBackgroundColor_item());
      this.mLinearLayout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903061, null));
      this.mPtrListView = ((PullToRefreshListView)this.mLinearLayout.findViewById(2131230731));
      this.mPtrListView.setOnScrollListener(new AbsListView.OnScrollListener()
      {
        public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          Object localObject = null;
          if ((paramAnonymousInt1 == 0) || (paramAnonymousInt1 == 1))
          {
            paramAnonymousAbsListView = paramAnonymousAbsListView.getChildAt(0);
            if (paramAnonymousAbsListView != null)
            {
              paramAnonymousInt1 = paramAnonymousAbsListView.getTop();
              paramAnonymousInt2 = PodcasterInfoView.this.getMaxTranslationY();
              if ((paramAnonymousInt1 <= 0) && (paramAnonymousInt1 >= paramAnonymousInt2))
              {
                PodcasterInfoView.this.moveHead(paramAnonymousInt1);
                PodcasterInfoView.this.mHeaderView.update("setvisible", Boolean.valueOf(true));
                PodcasterInfoView.this.dispatchActionEvent("hideTitle", null);
              }
            }
          }
          do
          {
            do
            {
              return;
              paramAnonymousAbsListView = paramAnonymousAbsListView.getChildAt(0);
            }
            while (paramAnonymousAbsListView == null);
            paramAnonymousInt1 = paramAnonymousAbsListView.getTop();
            paramAnonymousInt2 = PodcasterInfoView.this.getMaxTranslationY();
          }
          while (paramAnonymousInt1 < paramAnonymousInt2);
          PodcasterInfoView.this.moveHead(paramAnonymousInt2);
          PodcasterInfoView.this.mHeaderView.update("setvisible", Boolean.valueOf(false));
          PodcasterInfoView localPodcasterInfoView = PodcasterInfoView.this;
          if (PodcasterInfoView.this.mPodcasterInfo == null);
          for (paramAnonymousAbsListView = localObject; ; paramAnonymousAbsListView = PodcasterInfoView.this.mPodcasterInfo.podcasterName)
          {
            localPodcasterInfoView.dispatchActionEvent("showTitle", paramAnonymousAbsListView);
            return;
          }
        }

        public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
        {
        }
      });
      i = this.mPtrListView.hashCode();
      this.mHeaderLayout = new RotateLoadingLayout(paramContext);
      this.mAdapter = new PodcasterInfoListAdapter(paramContext, i);
      this.mPtrListView.addListHeaderView(this.mHeaderLayout);
      MiniPlayerPlaceHolder.wrapListView(paramContext, (ListView)this.mPtrListView.getRefreshableView());
      this.mPtrListView.setAdapter(this.mAdapter);
      ((ListView)this.mPtrListView.getRefreshableView()).setVerticalScrollBarEnabled(false);
      ((ListView)this.mPtrListView.getRefreshableView()).setSelector(new ColorDrawable(0));
      this.mPtrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener()
      {
        public void onRefresh(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase)
        {
          PodcasterInfoView.access$602(PodcasterInfoView.this, 1);
          PodcasterInfoView.access$702(PodcasterInfoView.this, 1);
          PodcasterInfoView.access$802(PodcasterInfoView.this, 1);
          InfoManager.getInstance().loadPodcasterBaseInfo(PodcasterInfoView.this.mPodcasterInfo.podcasterId, PodcasterInfoView.this);
          InfoManager.getInstance().loadPodcasterChannels(PodcasterInfoView.this.mPodcasterInfo.podcasterId, PodcasterInfoView.this);
          InfoManager.getInstance().loadPodcasterLatestInfo(PodcasterInfoView.this.mPodcasterInfo.podcasterId, PodcasterInfoView.this);
        }
      });
      this.mPtrListView.setOnPullEventListener(new PullToRefreshBase.OnPullEventListener()
      {
        public void onPullEvent(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase, PullToRefreshBase.State paramAnonymousState, PullToRefreshBase.Mode paramAnonymousMode)
        {
          switch (PodcasterInfoView.4.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[paramAnonymousState.ordinal()])
          {
          default:
            return;
          case 1:
            PodcasterInfoView.this.mHeaderLayout.reset();
            return;
          case 2:
            PodcasterInfoView.this.mHeaderLayout.pullToRefresh();
            return;
          case 3:
            PodcasterInfoView.this.mHeaderLayout.releaseToRefresh();
            return;
          case 4:
          case 5:
          }
          PodcasterInfoView.this.mHeaderLayout.refreshing();
        }
      });
      addView(this.mLinearLayout);
      this.mHeaderView = new PodcasterInfoHeaderView(paramContext, hashCode());
      addView(this.mHeaderView);
      this.mMiniView = new MiniPlayerView(paramContext);
      addView(this.mMiniView);
      InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
      InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
      return;
    }
  }

  private int getMaxTranslationY()
  {
    return this.naviLayout.height + this.mStatusBarHeight - this.mHeaderView.getMeasuredHeight();
  }

  @TargetApi(11)
  private void moveHead(int paramInt)
  {
    if (this.mLastOffset == paramInt)
      return;
    this.mLastOffset = paramInt;
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mHeaderView.setTranslationY(paramInt);
      return;
    }
    this.mHeaderView.layout(0, paramInt, this.standardLayout.width, this.mHeaderView.getMeasuredHeight() + paramInt);
  }

  private void setPodcasterBaseInfo()
  {
    if ((this.mPodcasterInfo.podcasterName != null) && (this.mPodcasterInfo.podcasterName.equalsIgnoreCase("加载中")))
      InfoManager.getInstance().loadPodcasterBaseInfo(this.mPodcasterInfo.podcasterId, this);
  }

  private void setPodcasterChannels()
  {
    if (this.mPodcasterInfo.getChannelNodes() == null)
    {
      this.mChannelState = 1;
      InfoManager.getInstance().loadPodcasterChannels(this.mPodcasterInfo.podcasterId, this);
      return;
    }
    this.mChannelState = 2;
    this.mAdapter.setChannelList(this.mPodcasterInfo.getChannelNodes());
  }

  private void setPodcasterPrograms()
  {
    if (this.mPodcasterInfo.getProgramNodes() == null)
    {
      this.mProgramState = 1;
      InfoManager.getInstance().loadPodcasterLatestInfo(this.mPodcasterInfo.podcasterId, this);
      return;
    }
    this.mProgramState = 2;
    this.mAdapter.setRecentPrograms(this.mPodcasterInfo.getProgramNodes());
  }

  public void close(boolean paramBoolean)
  {
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mMiniView.destroy();
    this.mHeaderView.close(paramBoolean);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_PODCASTER_BASEINFO", "RECV_PODCASTER_CHANNELS", "RECV_PODCASTER_LATEST" });
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    super.close(paramBoolean);
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if ((paramInt == 8) || (paramInt == 1) || (paramInt == 4))
      this.mAdapter.notifyDataSetChanged();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mHeaderView.layout(0, 0, this.standardLayout.width, this.mHeaderView.getMeasuredHeight());
    this.mLinearLayout.layout(0, this.mStatusBarHeight + this.naviLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.naviLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.mMiniView);
    this.standardLayout.measureView(this.mHeaderView);
    this.mLinearLayout.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.naviLayout.height - this.mStatusBarHeight, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_BASEINFO"))
    {
      Log.d("PodcasterInfoView", "收到主播数据");
      this.mBaseInfoState = 2;
      this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
      this.mHeaderView.update("setData", this.mPodcasterInfo);
    }
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_CHANNELS"))
    {
      Log.d("PodcasterInfoView", "收到主播专辑列表数据");
      this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
      setPodcasterChannels();
      UserDataUtil.request(this, this.mPodcasterInfo.getChannelNodes());
    }
    while (true)
    {
      if ((this.mBaseInfoState == 2) && (this.mChannelState == 2) && (this.mProgramState == 2))
        this.mPtrListView.onRefreshComplete();
      return;
      if (paramString.equalsIgnoreCase("RECV_PODCASTER_LATEST"))
      {
        Log.d("PodcasterInfoView", "收到主播最新更新的节目数据");
        this.mPodcasterInfo = PodcasterHelper.getInstance().getPodcaster(this.mPodcasterInfo.podcasterId);
        setPodcasterPrograms();
      }
    }
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    if ((paramInt == 1) && (this.mAdapter != null))
      this.mAdapter.notifyDataSetChanged();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_BASEINFO"))
      this.mBaseInfoState = 2;
    if (paramString.equalsIgnoreCase("RECV_PODCASTER_CHANNELS"))
      this.mChannelState = 2;
    while (true)
    {
      if ((this.mBaseInfoState == 2) && (this.mChannelState == 2) && (this.mProgramState == 2))
        this.mPtrListView.onRefreshComplete();
      return;
      if (paramString.equalsIgnoreCase("RECV_PODCASTER_LATEST"))
        this.mProgramState = 2;
    }
  }

  public void onResponse()
  {
    setPodcasterChannels();
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      if ((paramObject instanceof UserInfo))
      {
        this.mPodcasterInfo = ((UserInfo)paramObject);
        if (this.mPodcasterInfo != null)
        {
          PodcasterHelper.getInstance();
          this.mBaseInfoState = 2;
          this.mHeaderView.update(paramString, paramObject);
          setPodcasterBaseInfo();
          setPodcasterChannels();
          UserDataUtil.request(this, this.mPodcasterInfo.getChannelNodes());
          setPodcasterPrograms();
          if ((this.mChannelState == 1) || (this.mProgramState == 1))
            this.mPtrListView.setRefreshing();
          PlayerAgent.getInstance().addPlaySource(44);
        }
      }
    while (true)
    {
      super.update(paramString, paramObject);
      return;
      if ((paramString.equalsIgnoreCase("setlastestprogramid")) && (CloudCenter.getInstance().isLogin(false)) && (this.mPodcasterInfo != null) && (this.mPodcasterInfo.getProgramNodes() != null) && (this.mPodcasterInfo.getProgramNodes().size() > 0))
      {
        long l = ((ProgramNode)this.mPodcasterInfo.getProgramNodes().get(0)).getUpdateTime();
        UserProfile localUserProfile = InfoManager.getInstance().getUserProfile();
        if ((localUserProfile != null) && (localUserProfile.getUserInfo() != null) && (!TextUtils.isEmpty(localUserProfile.getUserInfo().snsInfo.sns_id)))
        {
          PodcasterHelper.getInstance().updateMyPodcasterLastestProgramId(this.mPodcasterInfo.podcasterId, localUserProfile.getUserInfo().snsInfo.sns_id, l);
          InfoManager.getInstance().root().setInfoUpdate(10);
        }
      }
    }
  }

  class PodcasterInfoListAdapter extends BaseAdapter
  {
    private List<ChannelNode> mChannelList;
    private Context mContext;
    private int mHash;
    private List<ProgramNode> mRecentList;

    public PodcasterInfoListAdapter(Context paramInt, int arg3)
    {
      this.mContext = paramInt;
      this.mRecentList = new ArrayList();
      this.mChannelList = new ArrayList();
      int i;
      this.mHash = i;
    }

    private void bindView(int paramInt1, int paramInt2, View paramView)
    {
      Object localObject = getItem(paramInt1);
      if (paramInt2 == 0)
      {
        ((ChannelDetailTagView)paramView).setTagName((String)localObject);
        return;
      }
      if (paramInt2 == 1)
      {
        ((VirtualChannelItemView)paramView).update("content", localObject);
        return;
      }
      paramView = (ChannelDetailItemView)paramView;
      HashMap localHashMap = new HashMap();
      localHashMap.put("node", localObject);
      if (PodcasterInfoView.this.mPodcasterInfo.lastestUpdateTime == 0L)
      {
        localHashMap.put("remind", Boolean.valueOf(false));
        paramView.update("content", localHashMap);
        return;
      }
      if (((ProgramNode)localObject).getUpdateTime() > PodcasterInfoView.this.mPodcasterInfo.lastestUpdateTime);
      for (boolean bool = true; ; bool = false)
      {
        localHashMap.put("remind", Boolean.valueOf(bool));
        break;
      }
    }

    private View newView(int paramInt)
    {
      if (paramInt == 0)
        return new ChannelDetailTagView(this.mContext);
      if (paramInt == 1)
      {
        localObject = new VirtualChannelItemView(this.mContext, this.mHash);
        ((VirtualChannelItemView)localObject).setBelongToPodcasterInfo(true);
        return localObject;
      }
      Object localObject = new ChannelDetailItemView(this.mContext, this.mHash);
      ((ChannelDetailItemView)localObject).setBelongToPodcasterInfo(true);
      return localObject;
    }

    public int getCount()
    {
      int j = this.mChannelList.size();
      int i = j;
      if (j > 0)
        i = j + 1;
      int k = this.mRecentList.size();
      j = k;
      if (k > 0)
        j = k + 1;
      return i + j;
    }

    public Object getItem(int paramInt)
    {
      int j = this.mChannelList.size();
      int i = j;
      if (this.mChannelList.size() > 0)
      {
        if (paramInt == 0)
          return this.mChannelList.size() + "个专辑";
        if (paramInt <= this.mChannelList.size())
          return this.mChannelList.get(paramInt - 1);
        i = j + 1;
      }
      if (paramInt == i)
        return "最近更新";
      return this.mRecentList.get(paramInt - i - 1);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getItemViewType(int paramInt)
    {
      int j = this.mChannelList.size();
      int i = j;
      if (this.mChannelList.size() > 0)
        if (paramInt != 0);
      while (paramInt == i)
      {
        return 0;
        if (paramInt <= this.mChannelList.size())
          return 1;
        i = j + 1;
      }
      return 2;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = getItemViewType(paramInt);
      paramViewGroup = paramView;
      if (paramView == null)
        paramViewGroup = newView(i);
      bindView(paramInt, i, paramViewGroup);
      return paramViewGroup;
    }

    public int getViewTypeCount()
    {
      return 3;
    }

    public void setChannelList(List<ChannelNode> paramList)
    {
      this.mChannelList.clear();
      if (paramList != null)
        this.mChannelList.addAll(paramList);
      notifyDataSetChanged();
    }

    public void setRecentPrograms(List<ProgramNode> paramList)
    {
      this.mRecentList.clear();
      if (paramList != null)
        this.mRecentList.addAll(paramList);
      notifyDataSetChanged();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.podcaster.PodcasterInfoView
 * JD-Core Version:    0.6.2
 */