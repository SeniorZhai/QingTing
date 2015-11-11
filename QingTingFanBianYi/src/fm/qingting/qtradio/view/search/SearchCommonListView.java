package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipUtil;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;
import fm.qingting.utils.InputMethodUtil.InputStateDelegate;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class SearchCommonListView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener
{
  private CustomizedAdapter mAdapter;
  private InputMethodUtil.InputStateDelegate mDelegate;
  private EmptyTipsView mEmptyView;
  private IAdapterIViewFactory mFactory;
  private LoadMoreFootView mFooterView;
  private ListView mListView;
  private int mType;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public SearchCommonListView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mType = paramInt;
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (SearchCommonListView.this.mType)
        {
        default:
          return null;
        case 2:
          return new SearchItemPodcasterView(SearchCommonListView.this.getContext(), this.val$hash);
        case 3:
          return new SearchItemChannelView(SearchCommonListView.this.getContext(), this.val$hash);
        case 4:
          return new SearchItemEpisodeView(SearchCommonListView.this.getContext(), this.val$hash);
        case 1:
        }
        return new SearchItemShowView(SearchCommonListView.this.getContext(), this.val$hash);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mEmptyView = new EmptyTipsView(paramContext, 8);
    addView(this.mEmptyView);
    this.mListView = new ListView(paramContext);
    this.mListView.setEmptyView(this.mEmptyView);
    this.mFooterView = new LoadMoreFootView(paramContext);
    this.mListView.addFooterView(this.mFooterView);
    this.mListView.setCacheColorHint(0);
    this.mListView.setSelector(new ColorDrawable(0));
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousInt2 == paramAnonymousInt3)
          SearchCommonListView.this.mFooterView.hideLoad();
        while ((SearchCommonListView.this.mFooterView.isAll()) || (SearchCommonListView.this.mFooterView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
          return;
        SearchCommonListView.this.mFooterView.showLoad();
        SearchCommonListView.this.loadMore();
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if ((paramAnonymousInt == 1) && (SearchCommonListView.this.mDelegate != null))
          SearchCommonListView.this.mDelegate.closeIfNeed();
      }
    });
  }

  private void loadMore()
  {
    String str = InfoManager.getInstance().root().mSearchNode.getLastKeyword();
    InfoManager.getInstance().loadMoreSearchByType(str, this.mType, this);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mEmptyView.close(paramBoolean);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RSLOADMORE" });
    super.close(paramBoolean);
  }

  public List<SearchItemNode> getResultList()
  {
    return InfoManager.getInstance().root().mSearchNode.getResult(this.mType);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.standardLayout.layoutView(this.mListView);
    this.standardLayout.layoutView(this.mEmptyView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mListView);
    this.standardLayout.measureView(this.mEmptyView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    int j = 0;
    label52: label74: LoadMoreFootView localLoadMoreFootView;
    if (paramString.equalsIgnoreCase("RSLOADMORE"))
    {
      paramString = getResultList();
      if (paramString != null)
        break label92;
      i = 0;
      if ((i % 30 == 0) && (i != InfoManager.getInstance().root().mSearchNode.getTotalCountByType(this.mType)))
        break label102;
      i = 1;
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      if (i == 0)
        break label107;
      this.mFooterView.setAll();
      localLoadMoreFootView = this.mFooterView;
      if (paramString != null)
        break label117;
    }
    label92: label102: label107: label117: for (int i = j; ; i = paramString.size())
    {
      localLoadMoreFootView.hideFootIfItemsNotEnough(i);
      return;
      i = paramString.size();
      break;
      i = 0;
      break label52;
      this.mFooterView.unsetAll();
      break label74;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  protected void setInputStateDelegate(InputMethodUtil.InputStateDelegate paramInputStateDelegate)
  {
    this.mDelegate = paramInputStateDelegate;
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 0;
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mEmptyView.setTitle(EmptyTipUtil.getTitle(8));
      this.mEmptyView.setContent(EmptyTipUtil.getContent(8));
      paramString = getResultList();
      this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
      if (paramString == null)
      {
        i = 0;
        if ((i % 30 == 0) && (i != InfoManager.getInstance().root().mSearchNode.getTotalCountByType(this.mType)))
          break label127;
        i = 1;
        if (i == 0)
          break label132;
        this.mFooterView.setAll();
        paramObject = this.mFooterView;
        if (paramString != null)
          break label142;
        i = j;
        paramObject.hideFootIfItemsNotEnough(i);
      }
    }
    label127: 
    while (!paramString.equalsIgnoreCase("loading"))
      while (true)
      {
        return;
        int i = paramString.size();
        continue;
        i = 0;
        continue;
        this.mFooterView.unsetAll();
        continue;
        i = paramString.size();
      }
    label132: label142: this.mEmptyView.setTitle("正在搜索");
    this.mEmptyView.setContent(null);
    this.mAdapter.setData(null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchCommonListView
 * JD-Core Version:    0.6.2
 */