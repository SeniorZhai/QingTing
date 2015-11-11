package fm.qingting.qtradio.view.search;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.adapter.IReusableCollection;
import fm.qingting.framework.view.IView;
import java.util.List;

class SearchSectionAdapter extends BaseAdapter
{
  static final String NEEDBOTTOMLINE = "nbl";
  protected List<SearchSectionItem> data;
  protected ISearchSectionFactory factory;

  public SearchSectionAdapter(List<SearchSectionItem> paramList, ISearchSectionFactory paramISearchSectionFactory)
  {
    this.data = paramList;
    this.factory = paramISearchSectionFactory;
  }

  private static boolean isContent(int paramInt)
  {
    return (paramInt >= 1) && (paramInt <= 2);
  }

  private boolean needBottomLine(int paramInt1, int paramInt2)
  {
    if (isContent(paramInt1))
    {
      SearchSectionItem localSearchSectionItem = getItem(paramInt2 + 1);
      if (localSearchSectionItem == null);
      while (localSearchSectionItem.type == 0)
        return false;
    }
    do
    {
      return true;
      if (paramInt1 == 5)
        break;
    }
    while ((paramInt1 != 0) || (getItem(paramInt2 + 1) != null));
    return false;
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
    return this.data.size();
  }

  public List<SearchSectionItem> getData()
  {
    return this.data;
  }

  public SearchSectionItem getItem(int paramInt)
  {
    if (this.data == null);
    while (this.data.size() <= paramInt)
      return null;
    return (SearchSectionItem)this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    SearchSectionItem localSearchSectionItem = getItem(paramInt);
    if (localSearchSectionItem == null)
      return 0;
    return localSearchSectionItem.type;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = null;
    SearchSectionItem localSearchSectionItem = getItem(paramInt);
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
        paramViewGroup = this.factory.createView(localSearchSectionItem.type);
      paramView = paramViewGroup.getView();
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      if (localSearchSectionItem != null)
      {
        paramViewGroup.update("nbl", Boolean.valueOf(needBottomLine(localSearchSectionItem.type, paramInt)));
        paramViewGroup.update("content", localSearchSectionItem.data);
      }
      return paramView;
      paramViewGroup = (IView)paramView.getTag();
    }
  }

  public int getViewTypeCount()
  {
    return 7;
  }

  public void setData(List<SearchSectionItem> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setFactory(ISearchSectionFactory paramISearchSectionFactory)
  {
    this.factory = paramISearchSectionFactory;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchSectionAdapter
 * JD-Core Version:    0.6.2
 */