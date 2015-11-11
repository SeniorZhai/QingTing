package fm.qingting.qtradio.view.moreContentView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.adapter.IReusableCollection;
import fm.qingting.framework.view.IView;
import java.util.List;

public class SectionAdapter extends BaseAdapter
{
  protected List<SectionItem> data;
  protected ISectionAdapterIViewFactory factory;

  public SectionAdapter(List<SectionItem> paramList, ISectionAdapterIViewFactory paramISectionAdapterIViewFactory)
  {
    this.data = paramList;
    this.factory = paramISectionAdapterIViewFactory;
  }

  private boolean needBottomLine(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0);
    SectionItem localSectionItem;
    do
    {
      return false;
      localSectionItem = getItem(paramInt2 + 1);
    }
    while ((localSectionItem != null) && (localSectionItem.type != 1));
    return true;
  }

  public void build(List<SectionItem> paramList, ISectionAdapterIViewFactory paramISectionAdapterIViewFactory)
  {
    this.factory = paramISectionAdapterIViewFactory;
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

  public List<SectionItem> getData()
  {
    return this.data;
  }

  public SectionItem getItem(int paramInt)
  {
    if (this.data == null);
    while (this.data.size() <= paramInt)
      return null;
    return (SectionItem)this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    SectionItem localSectionItem = getItem(paramInt);
    if (localSectionItem == null)
      return 1;
    return localSectionItem.type;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = null;
    SectionItem localSectionItem = getItem(paramInt);
    if (paramView == null)
    {
      paramView = localObject;
      if (paramViewGroup != null)
      {
        paramView = localObject;
        if ((paramViewGroup instanceof IReusableCollection))
          paramView = (IView)((IReusableCollection)paramViewGroup).getReusableItem(null);
      }
      paramViewGroup = paramView;
      if (paramView == null)
        paramViewGroup = this.factory.createView(localSectionItem.type);
      paramView = paramViewGroup.getView();
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      if (localSectionItem != null)
      {
        paramViewGroup.update("needBottomLine", Boolean.valueOf(needBottomLine(localSectionItem.type, paramInt)));
        paramViewGroup.update("content", localSectionItem.data);
      }
      return paramView;
      paramViewGroup = (IView)paramView.getTag();
    }
  }

  public int getViewTypeCount()
  {
    return 2;
  }

  public void setData(List<SectionItem> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setFactory(ISectionAdapterIViewFactory paramISectionAdapterIViewFactory)
  {
    this.factory = paramISectionAdapterIViewFactory;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.SectionAdapter
 * JD-Core Version:    0.6.2
 */