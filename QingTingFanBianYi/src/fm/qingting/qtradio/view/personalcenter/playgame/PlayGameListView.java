package fm.qingting.qtradio.view.personalcenter.playgame;

import android.content.Context;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.model.MutiCheckManageableAdapter;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import java.util.ArrayList;
import java.util.List;

public class PlayGameListView extends ListViewImpl
{
  private MutiCheckManageableAdapter adapter = new MutiCheckManageableAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new PlayGameItemView(PlayGameListView.this.getContext(), this.val$hash);
    }
  };

  public PlayGameListView(Context paramContext)
  {
    super(paramContext);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this);
    setAdapter(this.adapter);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.adapter.setData((List)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.playgame.PlayGameListView
 * JD-Core Version:    0.6.2
 */