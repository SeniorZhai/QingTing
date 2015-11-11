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
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class VirtualChannelListAllView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, UserDataUtil.UserDataResponse
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new VirtualChannelItemView(VirtualChannelListAllView.this.getContext(), this.val$hash);
    }
  };
  private LoadMoreFootView mFootView;
  private LinearLayout mLinearLayout;
  private PullToRefreshListView mListView;
  private CategoryNode mNode;

  public VirtualChannelListAllView(Context paramContext)
  {
    super(paramContext);
    this.mLinearLayout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903040, null));
    this.mListView = ((PullToRefreshListView)this.mLinearLayout.findViewById(2131230731));
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setSelector(17170445);
    this.mFootView = new LoadMoreFootView(paramContext);
    this.mListView.addListFooterView(this.mFootView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, (ListView)this.mListView.getRefreshableView());
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousInt2 == paramAnonymousInt3)
          VirtualChannelListAllView.this.mFootView.hideLoad();
        while ((VirtualChannelListAllView.this.mFootView.isAll()) || (VirtualChannelListAllView.this.mFootView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
          return;
        VirtualChannelListAllView.this.mFootView.showLoad();
        VirtualChannelListAllView.this.loadMore();
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
        if (VirtualChannelListAllView.this.mNode != null)
          ChannelHelper.getInstance().reloadListVirtualChannelNodesById(VirtualChannelListAllView.this.mNode.categoryId, "", VirtualChannelListAllView.this);
      }
    });
  }

  private void loadMore()
  {
    if (this.mNode != null)
      ChannelHelper.getInstance().loadListVirtualChannelNodesById(this.mNode.categoryId, "", this);
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
      paramString = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.categoryId, "");
      this.mListView.onRefreshComplete();
      this.mFootView.hideLoad();
      if (ChannelHelper.getInstance().isFinished(this.mNode.categoryId, ""))
        this.mFootView.setAll();
      UserDataUtil.request(this, paramString);
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      localLoadMoreFootView = this.mFootView;
      if (paramString != null)
        break label97;
    }
    label97: for (int i = 0; ; i = paramString.size())
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
    List localList = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.categoryId, "");
    this.mAdapter.setData(ListUtils.convertToObjectList(localList));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((CategoryNode)paramObject);
      paramString = ChannelHelper.getInstance().getLstChannelsByAttrPath(this.mNode.categoryId, "");
      if (paramString == null)
        this.mListView.setRefreshing();
    }
    else
    {
      return;
    }
    this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
    this.mListView.onRefreshComplete();
    if (ChannelHelper.getInstance().isFinished(this.mNode.categoryId, ""))
      this.mFootView.setAll();
    this.mFootView.hideFootIfItemsNotEnough(paramString.size());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.VirtualChannelListAllView
 * JD-Core Version:    0.6.2
 */