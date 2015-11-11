package fm.qingting.qtradio.model;

import android.view.View;
import android.view.ViewGroup;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MutiCheckAdapter extends CustomizedAdapter
{
  private final HashSet<Integer> mCheckPositions = new HashSet();

  public MutiCheckAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    super(paramList, paramIAdapterIViewFactory);
  }

  private void dispatchStateChanged(boolean paramBoolean)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(null, "stateChanged", Boolean.valueOf(paramBoolean));
  }

  private boolean isChecked(int paramInt)
  {
    return this.mCheckPositions.contains(Integer.valueOf(paramInt));
  }

  public void checkAll()
  {
    int j = getCount();
    int i = 0;
    while (i < j)
    {
      this.mCheckPositions.add(Integer.valueOf(i));
      i += 1;
    }
    notifyDataSetChanged();
    if (j > 0)
      dispatchStateChanged(true);
  }

  public void checkIndex(int paramInt)
  {
    if (this.mCheckPositions.contains(Integer.valueOf(paramInt)))
    {
      this.mCheckPositions.remove(Integer.valueOf(paramInt));
      if (this.mCheckPositions.size() == 0)
        dispatchStateChanged(false);
    }
    while (true)
    {
      notifyDataSetChanged();
      return;
      dispatchStateChanged(true);
      continue;
      this.mCheckPositions.add(Integer.valueOf(paramInt));
      dispatchStateChanged(true);
    }
  }

  public void checkIndexs(List<Integer> paramList)
  {
    int i = 0;
    if (i < paramList.size())
    {
      int j = ((Integer)paramList.get(i)).intValue();
      if (this.mCheckPositions.contains(Integer.valueOf(j)))
      {
        this.mCheckPositions.remove(Integer.valueOf(j));
        if (this.mCheckPositions.size() == 0)
          dispatchStateChanged(false);
      }
      while (true)
      {
        notifyDataSetChanged();
        i += 1;
        break;
        dispatchStateChanged(true);
        continue;
        this.mCheckPositions.add(Integer.valueOf(j));
        dispatchStateChanged(true);
      }
    }
  }

  public Iterator<Integer> getCheckList()
  {
    return this.mCheckPositions.iterator();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramView = (IView)super.getView(paramInt, paramView, paramViewGroup).getTag();
    paramView.update("checkstate", Boolean.valueOf(isChecked(paramInt)));
    return paramView.getView();
  }

  public void initCheck(int paramInt1, int paramInt2)
  {
    if (paramInt1 <= paramInt2)
    {
      if (this.mCheckPositions.contains(Integer.valueOf(paramInt1)))
      {
        this.mCheckPositions.remove(Integer.valueOf(paramInt1));
        if (this.mCheckPositions.size() == 0)
          dispatchStateChanged(false);
      }
      while (true)
      {
        paramInt1 += 1;
        break;
        dispatchStateChanged(true);
        continue;
        this.mCheckPositions.add(Integer.valueOf(paramInt1));
        dispatchStateChanged(true);
      }
    }
    notifyDataSetChanged();
  }

  public void resetCheck()
  {
    if (this.mCheckPositions.size() > 0)
    {
      this.mCheckPositions.clear();
      notifyDataSetChanged();
      dispatchStateChanged(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.MutiCheckAdapter
 * JD-Core Version:    0.6.2
 */