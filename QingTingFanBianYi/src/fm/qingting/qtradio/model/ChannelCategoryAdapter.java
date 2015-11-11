package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import java.util.List;

public class ChannelCategoryAdapter extends CustomizedAdapter
{
  private int fmIndex = -1;
  private boolean showHeadSet = false;

  public ChannelCategoryAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    if ((this.showHeadSet) && (paramInt == this.fmIndex))
    {
      paramView.update("refreshHeadSet", Boolean.valueOf(true));
      this.showHeadSet = false;
    }
    while (true)
    {
      return paramView.getView();
      paramView.update("refreshHeadSet", Boolean.valueOf(false));
    }
  }

  public void showHeadSet(int paramInt)
  {
    this.showHeadSet = true;
    this.fmIndex = paramInt;
    notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ChannelCategoryAdapter
 * JD-Core Version:    0.6.2
 */