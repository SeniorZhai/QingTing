package fm.qingting.qtradio.view.frontpage.discover;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.adapter.ILayoutParamsBuilder;
import fm.qingting.framework.adapter.IReusableCollection;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.model.AdvertisementItemNode3rdParty;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

class SectionAdapter extends BaseAdapter
{
  static final String NEEDBOTTOMLINE = "nbl";
  protected ILayoutParamsBuilder builder;
  protected List<SectionItem> data;
  protected IEventHandler eventHandler;
  protected ISectionFactory factory;
  protected String idKey;

  public SectionAdapter(List<SectionItem> paramList, ISectionFactory paramISectionFactory)
  {
    this.data = paramList;
    this.factory = paramISectionFactory;
  }

  private boolean needBottomLine(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 3)
    {
      SectionItem localSectionItem = getItem(paramInt2 + 1);
      if (localSectionItem == null);
      while (localSectionItem.type == 0)
        return false;
    }
    return true;
  }

  public void build(List<SectionItem> paramList, ISectionFactory paramISectionFactory)
  {
    this.factory = paramISectionFactory;
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
      return 0;
    return localSectionItem.type;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    SectionItem localSectionItem = getItem(paramInt);
    if (paramView == null)
      if ((paramViewGroup == null) || (!(paramViewGroup instanceof IReusableCollection)))
        break label206;
    label206: for (paramViewGroup = (IView)((IReusableCollection)paramViewGroup).getReusableItem(null); ; paramViewGroup = null)
    {
      paramView = paramViewGroup;
      if (paramViewGroup == null)
        paramView = this.factory.createView(localSectionItem.type);
      paramViewGroup = paramView.getView();
      paramViewGroup.setTag(paramView);
      Object localObject = paramView;
      while (true)
      {
        ((IView)localObject).setEventHandler(null);
        if (localSectionItem != null)
        {
          ((IView)localObject).update("nbl", Boolean.valueOf(needBottomLine(localSectionItem.type, paramInt)));
          ((IView)localObject).update("content", localSectionItem.data);
          if ((localSectionItem.data != null) && ((localSectionItem.data instanceof AdvertisementItemNode3rdParty)))
          {
            ((AdvertisementItemNode3rdParty)localSectionItem.data).onShow();
            QTMSGManage.getInstance().sendStatistcsMessage("mediavAd", "show_ad");
          }
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
    return 9;
  }

  public void setData(List<SectionItem> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setFactory(ISectionFactory paramISectionFactory)
  {
    this.factory = paramISectionFactory;
  }

  public void setIDKey(String paramString)
  {
    this.idKey = paramString;
  }

  public void setLayoutParamsBuilder(ILayoutParamsBuilder paramILayoutParamsBuilder)
  {
    this.builder = paramILayoutParamsBuilder;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.SectionAdapter
 * JD-Core Version:    0.6.2
 */