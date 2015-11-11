package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class VirtualChannelListView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, UserDataUtil.UserDataResponse
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new VirtualChannelItemView(VirtualChannelListView.this.getContext(), this.val$hash);
    }
  };
  private LoadMoreFootView mFooterView;
  private LinearLayout mLinearLayout;
  private PullToRefreshListView mListView;
  private RecommendItemNode mNode;

  public VirtualChannelListView(Context paramContext)
  {
    super(paramContext);
    this.mLinearLayout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903040, null));
    this.mListView = ((PullToRefreshListView)this.mLinearLayout.findViewById(2131230731));
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setSelector(17170445);
    this.mFooterView = new LoadMoreFootView(paramContext);
    this.mListView.addListFooterView(this.mFooterView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, (ListView)this.mListView.getRefreshableView());
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousInt2 == paramAnonymousInt3)
          VirtualChannelListView.this.mFooterView.hideLoad();
        while ((VirtualChannelListView.this.mFooterView.isAll()) || (VirtualChannelListView.this.mFooterView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
          return;
        VirtualChannelListView.this.mFooterView.showLoad();
        VirtualChannelListView.this.loadMore();
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mLinearLayout);
    this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener()
    {
      public void onRefresh(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase)
      {
        if (VirtualChannelListView.this.mNode != null)
          ChannelHelper.getInstance().reloadListVirtualChannelNodesById(VirtualChannelListView.this.mNode.mCategoryId, VirtualChannelListView.this.mNode.mAttributesPath, VirtualChannelListView.this);
      }
    });
  }

  private void loadMore()
  {
    if (this.mNode == null)
      return;
    ChannelHelper.getInstance().loadListVirtualChannelNodesById(this.mNode.mCategoryId, this.mNode.mAttributesPath, this);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_VIRTUAL_CHANNELS_BYATTR" });
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLinearLayout.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mLinearLayout.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    LoadMoreFootView localLoadMoreFootView;
    if (paramString.equalsIgnoreCase("RECV_VIRTUAL_CHANNELS_BYATTR"))
    {
      paramString = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.mCategoryId, this.mNode.mAttributesPath);
      this.mListView.onRefreshComplete();
      this.mFooterView.hideLoad();
      if (ChannelHelper.getInstance().isFinished(this.mNode.mCategoryId, this.mNode.mAttributesPath))
        this.mFooterView.setAll();
      UserDataUtil.request(this, paramString);
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      localLoadMoreFootView = this.mFooterView;
      if (paramString != null)
        break label107;
    }
    label107: for (int i = 0; ; i = paramString.size())
    {
      localLoadMoreFootView.hideFootIfItemsNotEnough(i);
      return;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onResponse()
  {
    List localList = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.mCategoryId, this.mNode.mAttributesPath);
    this.mAdapter.setData(ListUtils.convertToObjectList(localList));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((RecommendItemNode)paramObject);
      paramString = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.mCategoryId, this.mNode.mAttributesPath);
      if (paramString == null)
        this.mListView.setRefreshing();
    }
    else
    {
      return;
    }
    if (ChannelHelper.getInstance().isFinished(this.mNode.mCategoryId, this.mNode.mAttributesPath))
      this.mFooterView.setAll();
    UserDataUtil.request(this, paramString);
    this.mFooterView.hideFootIfItemsNotEnough(paramString.size());
    this.mListView.onRefreshComplete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.VirtualChannelListView
 * JD-Core Version:    0.6.2
 */