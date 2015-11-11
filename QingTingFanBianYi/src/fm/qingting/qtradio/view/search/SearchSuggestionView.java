package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.QTMSGManage;

public class SearchSuggestionView extends ViewGroupViewImpl
  implements IEventHandler
{
  private CustomizedAdapter mAdapter;
  private ListView mListView;
  private SearchView mSearchView;

  public SearchSuggestionView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-723465);
    this.mAdapter = new CustomizedAdapter(null, new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new SearchSuggestionItemView(SearchSuggestionView.this.getContext(), this.val$hash);
      }
    });
    this.mAdapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setCacheColorHint(0);
    this.mListView.setSelector(17170445);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if ((paramAnonymousInt == 1) && (SearchSuggestionView.this.mSearchView != null))
          SearchSuggestionView.this.mSearchView.closeKeyBoard();
      }
    });
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (String)((ItemParam)paramObject2).param;
      QTMSGManage.getInstance().sendStatistcsMessage("search_suggestion");
      this.mSearchView.submitQuery(paramObject1);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mListView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void setSubscribeListener(SearchView paramSearchView)
  {
    this.mSearchView = paramSearchView;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mAdapter.setData(ListUtils.convertToObjectList(InfoManager.getInstance().root().mSearchNode.getSuggestions()));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchSuggestionView
 * JD-Core Version:    0.6.2
 */