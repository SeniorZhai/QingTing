package fm.qingting.qtradio.view.personalcenter.clock.djringtone;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.view.IView;
import java.util.List;

public class DjRingtoneAdapter extends CustomizedAdapter
{
  private int checkList = -1;
  private int playingIndex = -1;

  public DjRingtoneAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    if (this.checkList == paramInt)
    {
      paramView.update("check", null);
      if (this.playingIndex != paramInt)
        break label74;
    }
    label74: for (boolean bool = true; ; bool = false)
    {
      paramView.update("setPlay", Boolean.valueOf(bool));
      return paramView.getView();
      paramView.update("unCheck", null);
      break;
    }
  }

  public void initCheck(int paramInt)
  {
    this.checkList = paramInt;
  }

  public void setCheck(int paramInt)
  {
    this.checkList = paramInt;
    notifyDataSetChanged();
  }

  public void setPlayingIndex(int paramInt)
  {
    this.playingIndex = paramInt;
    notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.djringtone.DjRingtoneAdapter
 * JD-Core Version:    0.6.2
 */