package fm.qingting.qtradio.view.personalcenter.hiddenfeatures;

import android.content.Context;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.ArrayList;

public class HiddenFeaturesView extends ListViewImpl
{
  private CustomizedAdapter mAdapter;
  private IAdapterIViewFactory mFactory;

  public HiddenFeaturesView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new HiddenFeaturesItemView(HiddenFeaturesView.this.getContext(), this.val$hash);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    setAdapter(this.mAdapter);
  }

  private void initData()
  {
    this.mAdapter.setData(HiddenFeaturesConfig.getConfigList());
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      initData();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.hiddenfeatures.HiddenFeaturesView
 * JD-Core Version:    0.6.2
 */