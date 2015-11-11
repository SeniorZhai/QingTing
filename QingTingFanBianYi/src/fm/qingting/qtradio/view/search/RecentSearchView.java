package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;

public class RecentSearchView extends ViewGroupViewImpl
  implements IEventHandler
{
  private CustomizedAdapter mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
  private IAdapterIViewFactory mFactory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new RecentSearchItemView(RecentSearchView.this.getContext(), this.val$hash);
    }
  };
  private ListView mListView;
  private RecentSearchTagView mTagView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tagLayout = this.standardLayout.createChildLT(720, 68, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public RecentSearchView(Context paramContext)
  {
    super(paramContext);
    this.mAdapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setCacheColorHint(0);
    this.mListView.setSelector(17170445);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mTagView = new RecentSearchTagView(paramContext);
    this.mTagView.setEventHandler(this);
    addView(this.mTagView);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      paramString = (String)paramObject1.param;
      if (paramObject1.type.equalsIgnoreCase("searchRecent"))
        dispatchActionEvent(paramObject1.type, paramString);
    }
    while (!paramString.equalsIgnoreCase("clearRecent"))
      return;
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.tagLayout.layoutView(this.mTagView);
    this.mListView.layout(0, this.tagLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tagLayout.scaleToBounds(this.standardLayout);
    this.tagLayout.measureView(this.mTagView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.tagLayout.height, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mAdapter.setData(ListUtils.convertToObjectList(InfoManager.getInstance().root().mSearchNode.getRecentKeywords()));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.RecentSearchView
 * JD-Core Version:    0.6.2
 */