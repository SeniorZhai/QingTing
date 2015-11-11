package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import java.util.List;

public class SingleCheckAdapter extends CustomizedAdapter
{
  private int mCheckIndex = 0;

  public SingleCheckAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  public void checkIndex(int paramInt)
  {
    if (this.mCheckIndex == paramInt)
      return;
    this.mCheckIndex = paramInt;
    notifyDataSetChanged();
  }

  public int getIndex()
  {
    return this.mCheckIndex;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    if (this.mCheckIndex == paramInt);
    for (boolean bool = true; ; bool = false)
    {
      paramView.update("checkstate", Boolean.valueOf(bool));
      return paramView.getView();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.SingleCheckAdapter
 * JD-Core Version:    0.6.2
 */