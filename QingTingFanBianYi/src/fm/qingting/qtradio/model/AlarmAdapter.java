package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import java.util.List;

public class AlarmAdapter extends CustomizedAdapter
{
  private boolean inManageMode = false;

  public AlarmAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    if (getCount() == 1)
    {
      paramView.update("all", null);
      if (!this.inManageMode)
        break label104;
      paramView.update("showManage", null);
    }
    while (true)
    {
      return paramView.getView();
      if (paramInt == 0)
      {
        paramView.update("first", null);
        break;
      }
      if (paramInt == getCount() - 1)
      {
        paramView.update("last", null);
        break;
      }
      paramView.update("normal", null);
      break;
      label104: paramView.update("hideManage", null);
    }
  }

  public void hideManage()
  {
    if (!this.inManageMode)
      return;
    this.inManageMode = false;
    notifyDataSetChanged();
  }

  public void showManage()
  {
    if (this.inManageMode)
      return;
    this.inManageMode = true;
    notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlarmAdapter
 * JD-Core Version:    0.6.2
 */