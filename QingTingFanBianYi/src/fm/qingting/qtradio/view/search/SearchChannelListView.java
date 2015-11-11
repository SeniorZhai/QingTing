package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
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
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.view.LoadMoreFootView;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipUtil;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;
import fm.qingting.utils.InputMethodUtil.InputStateDelegate;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SearchChannelListView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private InputMethodUtil.InputStateDelegate mDelegate;
  private EmptyTipsView mEmptyView;
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new SearchItemChannelView(SearchChannelListView.this.getContext(), this.val$hash);
    }
  };
  private LoadMoreFootView mFooterView;
  private ListView mListView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public SearchChannelListView(Context paramContext)
  {
    super(paramContext);
    this.mEmptyView = new EmptyTipsView(paramContext, 8);
    addView(this.mEmptyView);
    this.mListView = new ListView(paramContext);
    this.mListView.setEmptyView(this.mEmptyView);
    this.mFooterView = new LoadMoreFootView(paramContext);
    this.mListView.setCacheColorHint(0);
    this.mListView.setSelector(new ColorDrawable(0));
    this.mListView.setDivider(null);
    this.mListView.addFooterView(this.mFooterView);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousInt2 == paramAnonymousInt3)
          SearchChannelListView.this.mFooterView.hideLoad();
        while ((SearchChannelListView.this.mFooterView.isAll()) || (SearchChannelListView.this.mFooterView.isLoading()) || (paramAnonymousInt1 + paramAnonymousInt2 < paramAnonymousInt3))
          return;
        SearchChannelListView.this.mFooterView.showLoad();
        SearchChannelListView.this.loadMore();
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if ((paramAnonymousInt == 1) && (SearchChannelListView.this.mDelegate != null))
          SearchChannelListView.this.mDelegate.closeIfNeed();
      }
    });
  }

  private void invalidateCurrentPlayingProgram()
  {
    int j = this.mListView.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = this.mListView.getChildAt(i);
      if ((localView != null) && ((localView instanceof IView)))
        ((IView)localView).update("ip", null);
      i += 1;
    }
  }

  private void loadMore()
  {
    String str = InfoManager.getInstance().root().mSearchNode.getLastKeyword();
    InfoManager.getInstance().loadMoreSearchByType(str, 3, this);
  }

  private void loadPlayingProgram(List<SearchItemNode> paramList)
  {
    if (paramList == null)
      return;
    String str = "";
    Iterator localIterator = paramList.iterator();
    paramList = str;
    label17: int i;
    if (localIterator.hasNext())
    {
      i = ((SearchItemNode)localIterator.next()).channelId;
      if (InfoManager.getInstance().root().mPlayingProgramInfo.isExist(i))
        break label135;
      if (paramList.equalsIgnoreCase(""))
        paramList = paramList + i;
    }
    label135: 
    while (true)
    {
      break label17;
      paramList = paramList + "," + i;
      continue;
      if (paramList.equalsIgnoreCase(""))
        break;
      InfoManager.getInstance().loadCurrentPlayingPrograms(paramList, this);
      return;
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mEmptyView.close(paramBoolean);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RCPPL", "RSLOADMORE" });
    super.close(paramBoolean);
  }

  public List<SearchItemNode> getResultList()
  {
    return InfoManager.getInstance().root().mSearchNode.getResult(3);
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
    if (paramString.equalsIgnoreCase("RCPPL"))
      invalidateCurrentPlayingProgram();
    while (!paramString.equalsIgnoreCase("RSLOADMORE"))
      return;
    paramString = getResultList();
    this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
    label74: label85: LoadMoreFootView localLoadMoreFootView;
    if (paramString == null)
    {
      i = 0;
      if ((i % 30 == 0) && (i != InfoManager.getInstance().root().mSearchNode.getTotalCountByType(3)))
        break label113;
      i = 1;
      if (i == 0)
        break label118;
      this.mFooterView.setAll();
      localLoadMoreFootView = this.mFooterView;
      if (paramString != null)
        break label128;
    }
    label128: for (int i = j; ; i = paramString.size())
    {
      localLoadMoreFootView.hideFootIfItemsNotEnough(i);
      return;
      i = paramString.size();
      break;
      label113: i = 0;
      break label74;
      label118: this.mFooterView.unsetAll();
      break label85;
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
        if ((i % 30 == 0) && (i != InfoManager.getInstance().root().mSearchNode.getTotalCountByType(3)))
          break label129;
        i = 1;
        if (i == 0)
          break label134;
        this.mFooterView.setAll();
        paramObject = this.mFooterView;
        if (paramString != null)
          break label144;
        i = j;
        paramObject.hideFootIfItemsNotEnough(i);
        loadPlayingProgram(paramString);
      }
    }
    label129: label134: label144: 
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
    this.mEmptyView.setTitle("正在搜索");
    this.mEmptyView.setContent(null);
    this.mAdapter.setData(null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchChannelListView
 * JD-Core Version:    0.6.2
 */