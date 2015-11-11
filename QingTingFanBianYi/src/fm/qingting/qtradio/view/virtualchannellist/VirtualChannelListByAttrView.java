package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
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
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class VirtualChannelListByAttrView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, UserDataUtil.UserDataResponse
{
  private CustomizedAdapter mAdapter;
  private List<Attribute> mAttributes;
  private IAdapterIViewFactory mFactory;
  private LoadMoreFootView mFootView;
  private LinearLayout mLinearLayout;
  private PullToRefreshListView mListView;
  private CategoryNode mNode;
  private MiniPlayerView mPlayerView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public VirtualChannelListByAttrView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new VirtualChannelItemView(VirtualChannelListByAttrView.this.getContext(), i);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
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
          VirtualChannelListByAttrView.this.mFootView.hideLoad();
        while ((VirtualChannelListByAttrView.this.mFootView.isAll()) || (VirtualChannelListByAttrView.this.mFootView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
          return;
        VirtualChannelListByAttrView.this.mFootView.showLoad();
        VirtualChannelListByAttrView.this.loadMore();
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
        if (VirtualChannelListByAttrView.this.mNode != null)
          ChannelHelper.getInstance().reloadListVirtualChannelNodesByAttr(VirtualChannelListByAttrView.this.mNode.categoryId, VirtualChannelListByAttrView.this.mAttributes, VirtualChannelListByAttrView.this);
      }
    });
    this.mPlayerView = new MiniPlayerView(paramContext);
    addView(this.mPlayerView);
  }

  private void loadMore()
  {
    if (this.mNode != null)
      ChannelHelper.getInstance().loadListVirtualChannelNodesByAttr(this.mNode.categoryId, this.mAttributes, this);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_VIRTUAL_CHANNELS_BYATTR" });
    this.mPlayerView.destroy();
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
    this.mLinearLayout.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mPlayerView);
    this.standardLayout.measureView(this.mLinearLayout);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    LoadMoreFootView localLoadMoreFootView;
    if (paramString.equalsIgnoreCase("RECV_VIRTUAL_CHANNELS_BYATTR"))
    {
      paramString = ChannelHelper.getInstance().getLstChannelsByAttr(this.mNode.categoryId, this.mAttributes);
      if (paramString != null)
      {
        UserDataUtil.request(this, paramString);
        this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
        this.mListView.onRefreshComplete();
        this.mFootView.hideLoad();
        if (ChannelHelper.getInstance().isFinished(this.mNode.categoryId, this.mAttributes))
          this.mFootView.setAll();
      }
      localLoadMoreFootView = this.mFootView;
      if (paramString != null)
        break label105;
    }
    label105: for (int i = 0; ; i = paramString.size())
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
    List localList = ChannelHelper.getInstance().getLstChannelsByAttr(this.mNode.categoryId, this.mAttributes);
    this.mAdapter.setData(ListUtils.convertToObjectList(localList));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (Attribute)paramObject;
      this.mAttributes = new ArrayList();
      this.mAttributes.add(paramString);
      paramString = ChannelHelper.getInstance().getLstChannelsByAttr(this.mNode.categoryId, this.mAttributes);
      if (paramString == null)
      {
        this.mAdapter.setData(null);
        this.mListView.setRefreshing();
      }
    }
    while (!paramString.equalsIgnoreCase("setNode"))
    {
      return;
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      if (ChannelHelper.getInstance().isFinished(this.mNode.categoryId, this.mAttributes))
        this.mFootView.setAll();
      this.mFootView.hideFootIfItemsNotEnough(paramString.size());
      this.mListView.onRefreshComplete();
      return;
    }
    this.mNode = ((CategoryNode)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.VirtualChannelListByAttrView
 * JD-Core Version:    0.6.2
 */