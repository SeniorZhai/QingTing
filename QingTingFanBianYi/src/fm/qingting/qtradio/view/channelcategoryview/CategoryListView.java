package fm.qingting.qtradio.view.channelcategoryview;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.view.LoadingView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class CategoryListView extends ViewGroupViewImpl
{
  private CustomizedAdapter adapter;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private LoadingView mLoadingView;
  private MiniPlayerView playerView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public CategoryListView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    setBackgroundColor(SkinManager.getCardColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new CategoryItemView(CategoryListView.this.getContext(), i);
      }
    };
    this.adapter = new CustomizedAdapter(new ArrayList(), this.factory);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setSelector(17170445);
    this.mLoadingView = new LoadingView(paramContext);
    addView(this.mLoadingView);
    this.mListView.setEmptyView(this.mLoadingView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    this.playerView = new MiniPlayerView(paramContext);
    addView(this.playerView);
  }

  private void initData(List<Node> paramList)
  {
    this.adapter.setData(ListUtils.convertToObjectList(paramList));
    this.mListView.setSelection(0);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.playerView.destroy();
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mLoadingView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.playerView.layout(0, this.standardLayout.height - this.playerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.standardLayout.measureView(this.playerView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.mLoadingView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void setActivate(boolean paramBoolean)
  {
    super.setActivate(paramBoolean);
    update("activate", Boolean.valueOf(paramBoolean));
  }

  public void update(String paramString, Object paramObject)
  {
    if ((!paramString.equalsIgnoreCase("setData")) || (paramObject == null))
      return;
    initData((List)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.channelcategoryview.CategoryListView
 * JD-Core Version:    0.6.2
 */