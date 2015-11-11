package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;
import java.util.ArrayList;
import java.util.List;

public class ImConversationsView extends ViewGroupViewImpl
{
  private CustomizedAdapter mAdapter;
  private EmptyTipsView mEmptyView;
  private IAdapterIViewFactory mFactory;
  private ListView mListView;

  public ImConversationsView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mEmptyView = new EmptyTipsView(paramContext, 7);
    addView(this.mEmptyView);
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new ConversationItemView(ImConversationsView.this.getContext(), i);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mListView = new ListView(paramContext);
    this.mListView.setEmptyView(this.mEmptyView);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setSelector(17170445);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mEmptyView.close(paramBoolean);
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mEmptyView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mListView.measure(paramInt1, paramInt2);
    this.mEmptyView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mAdapter.setData((List)paramObject);
    while (!paramString.equalsIgnoreCase("resetList"))
      return;
    this.mAdapter.notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ImConversationsView
 * JD-Core Version:    0.6.2
 */