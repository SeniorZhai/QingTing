package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import java.util.List;

public class AlarmDaySettingAdapter extends CustomizedAdapter
{
  private final int UncheckMask = 127;
  private final int checkMask = 128;
  private int mCheckList = 0;
  private final int workDayMask = 31;

  public AlarmDaySettingAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  private int convert2CheckList(int paramInt)
  {
    if (paramInt == -1)
      return 128;
    if (paramInt == 0)
      return 31;
    return (paramInt & 0x2) << 5 | paramInt >> 2;
  }

  private boolean isChecked(int paramInt)
  {
    return (this.mCheckList & pow(paramInt)) > 0;
  }

  private int pow(int paramInt)
  {
    int j = 1;
    int i = 0;
    while (i < paramInt)
    {
      j <<= 1;
      i += 1;
    }
    return j;
  }

  private void repeatChanged(String paramString, boolean paramBoolean)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(this, paramString, Boolean.valueOf(paramBoolean));
  }

  public void checkIndex(int paramInt)
  {
    if (paramInt == getCount() - 1)
      if (!isChecked(paramInt))
      {
        this.mCheckList = pow(paramInt);
        repeatChanged("chooseRepeat", false);
      }
    while (true)
    {
      notifyDataSetChanged();
      return;
      this.mCheckList ^= pow(paramInt);
      continue;
      if ((this.mCheckList == 0) || ((this.mCheckList & 0x80) > 0))
        repeatChanged("chooseRepeat", true);
      this.mCheckList &= 127;
      this.mCheckList ^= pow(paramInt);
    }
  }

  public int getCheckList()
  {
    return this.mCheckList;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    paramView.update("checkstate", Boolean.valueOf(isChecked(paramInt)));
    return paramView.getView();
  }

  public void init(int paramInt)
  {
    this.mCheckList = convert2CheckList(paramInt);
    notifyDataSetChanged();
  }

  public void resetCheck()
  {
    this.mCheckList = 0;
    notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.AlarmDaySettingAdapter
 * JD-Core Version:    0.6.2
 */