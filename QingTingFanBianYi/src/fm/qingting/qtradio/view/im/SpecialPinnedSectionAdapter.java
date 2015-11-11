package fm.qingting.qtradio.view.im;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.adapter.ILayoutParamsBuilder;
import fm.qingting.framework.adapter.IReusableCollection;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.view.pinnedsection.IPinnedAdapterIViewFactory;
import fm.qingting.qtradio.view.pinnedsection.PinnedSectionListAdapter;
import java.util.List;

public class SpecialPinnedSectionAdapter extends BaseAdapter
  implements PinnedSectionListAdapter
{
  protected ILayoutParamsBuilder builder;
  protected List<SpecialPinnedItem> data;
  protected IEventHandler eventHandler;
  protected IPinnedAdapterIViewFactory factory;
  protected String idKey;

  public SpecialPinnedSectionAdapter(List<SpecialPinnedItem> paramList, IPinnedAdapterIViewFactory paramIPinnedAdapterIViewFactory)
  {
    this.data = paramList;
    this.factory = paramIPinnedAdapterIViewFactory;
  }

  public void build(List<SpecialPinnedItem> paramList, IPinnedAdapterIViewFactory paramIPinnedAdapterIViewFactory)
  {
    this.factory = paramIPinnedAdapterIViewFactory;
    setData(paramList);
  }

  public void clear()
  {
    this.data.clear();
    notifyDataSetChanged();
  }

  public int getCount()
  {
    if (this.data == null)
      return 0;
    try
    {
      int i = this.data.size();
      return i;
    }
    catch (Exception localException)
    {
    }
    return 1;
  }

  public List<SpecialPinnedItem> getData()
  {
    return this.data;
  }

  public SpecialPinnedItem getItem(int paramInt)
  {
    if (this.data == null);
    while (this.data.size() <= paramInt)
      return null;
    return (SpecialPinnedItem)this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    SpecialPinnedItem localSpecialPinnedItem = getItem(paramInt);
    if (localSpecialPinnedItem == null)
      return 0;
    return localSpecialPinnedItem.type;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    SpecialPinnedItem localSpecialPinnedItem = getItem(paramInt);
    if (paramView == null)
      if ((paramViewGroup == null) || (!(paramViewGroup instanceof IReusableCollection)))
        break label166;
    label166: for (paramViewGroup = (IView)((IReusableCollection)paramViewGroup).getReusableItem(null); ; paramViewGroup = null)
    {
      paramView = paramViewGroup;
      if (paramViewGroup == null)
        paramView = this.factory.createView(localSpecialPinnedItem.type);
      paramViewGroup = paramView.getView();
      paramViewGroup.setTag(paramView);
      Object localObject = paramView;
      while (true)
      {
        ((IView)localObject).setEventHandler(null);
        if (localSpecialPinnedItem != null)
        {
          ((IView)localObject).update("content", localSpecialPinnedItem.data);
          if (localSpecialPinnedItem.type == 0)
            ((IView)localObject).update("position", Integer.valueOf(localSpecialPinnedItem.listPosition));
        }
        if (this.builder != null)
        {
          paramView = this.builder.getLayoutParams();
          if (paramView != null)
            paramViewGroup.setLayoutParams(paramView);
        }
        return paramViewGroup;
        localObject = (IView)paramView.getTag();
        paramViewGroup = paramView;
      }
    }
  }

  public int getViewTypeCount()
  {
    return 3;
  }

  public boolean isItemViewTypePinned(int paramInt)
  {
    return paramInt == 1;
  }

  public void setData(List<SpecialPinnedItem> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setFactory(IPinnedAdapterIViewFactory paramIPinnedAdapterIViewFactory)
  {
    this.factory = paramIPinnedAdapterIViewFactory;
  }

  public void setIDKey(String paramString)
  {
    this.idKey = paramString;
  }

  public void setLayoutParamsBuilder(ILayoutParamsBuilder paramILayoutParamsBuilder)
  {
    this.builder = paramILayoutParamsBuilder;
  }

  public void updateValue(int paramInt, String paramString, SpecialPinnedItem paramSpecialPinnedItem)
  {
    updateValue(paramInt, paramString, paramSpecialPinnedItem, true);
  }

  public void updateValue(int paramInt, String paramString, SpecialPinnedItem paramSpecialPinnedItem, boolean paramBoolean)
  {
    if (paramInt >= this.data.size());
    do
    {
      return;
      this.data.set(paramInt, paramSpecialPinnedItem);
    }
    while (!paramBoolean);
    notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.SpecialPinnedSectionAdapter
 * JD-Core Version:    0.6.2
 */