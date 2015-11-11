package fm.qingting.framework.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomizedAdapter extends BaseAdapter
{
  public static final String ITEM_CALLBACK = "itemCallback";
  public static final String SET_DATA_TYPE = "content";
  protected ILayoutParamsBuilder builder;
  protected List<Object> data;
  protected IEventHandler eventHandler;
  protected IAdapterIViewFactory factory;
  protected String idKey;

  public CustomizedAdapter(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    this.data = paramList;
    this.factory = paramIAdapterIViewFactory;
  }

  public static List<Map<String, Object>> object2Map(List<? extends Object> paramList, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList == null);
    while (true)
    {
      return localArrayList;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put(paramString, paramList.next());
        localArrayList.add(localHashMap);
      }
    }
  }

  public void addItem(Object paramObject)
  {
    if (this.data != null)
      this.data.add(paramObject);
  }

  public void addItems(List<Object> paramList)
  {
    int i;
    if (this.data != null)
      i = 0;
    while (true)
    {
      if (i >= paramList.size())
        return;
      addItem(paramList.get(i));
      i += 1;
    }
  }

  public void build(List<Object> paramList, IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    this.factory = paramIAdapterIViewFactory;
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

  public List<Object> getData()
  {
    return this.data;
  }

  public Object getItem(int paramInt)
  {
    if (this.data == null);
    while (this.data.size() <= paramInt)
      return null;
    return this.data.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Object localObject = null;
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
        paramViewGroup = this.factory.createView(paramInt);
      paramView = paramViewGroup.getView();
      paramView.setTag(paramViewGroup);
    }
    while (true)
    {
      paramViewGroup.setEventHandler(null);
      localObject = getItem(paramInt);
      paramViewGroup.setEventHandler(new ViewEventHandler(paramInt));
      if (localObject != null)
        paramViewGroup.update("content", localObject);
      if (this.builder != null)
      {
        paramViewGroup = this.builder.getLayoutParams();
        if (paramViewGroup != null)
          paramView.setLayoutParams(paramViewGroup);
      }
      return paramView;
      paramViewGroup = (IView)paramView.getTag();
    }
  }

  public void setData(List<Object> paramList)
  {
    this.data = paramList;
    notifyDataSetChanged();
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setFactory(IAdapterIViewFactory paramIAdapterIViewFactory)
  {
    this.factory = paramIAdapterIViewFactory;
  }

  public void setIDKey(String paramString)
  {
    this.idKey = paramString;
  }

  public void setLayoutParamsBuilder(ILayoutParamsBuilder paramILayoutParamsBuilder)
  {
    this.builder = paramILayoutParamsBuilder;
  }

  public void updateValue(int paramInt, String paramString, Object paramObject)
  {
    updateValue(paramInt, paramString, paramObject, true);
  }

  public void updateValue(int paramInt, String paramString, Object paramObject, boolean paramBoolean)
  {
    if (paramInt >= this.data.size());
    do
    {
      return;
      this.data.set(paramInt, paramObject);
    }
    while (!paramBoolean);
    notifyDataSetChanged();
  }

  protected class ViewEventHandler
    implements IEventHandler
  {
    int _position;

    public ViewEventHandler(int arg2)
    {
      int i;
      this._position = i;
    }

    public void onEvent(Object paramObject1, String paramString, Object paramObject2)
    {
      if (CustomizedAdapter.this.eventHandler == null)
        return;
      CustomizedAdapter.this.eventHandler.onEvent(this, "itemCallback", new ItemParam(paramString, this._position, paramObject1, paramObject2));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.adapter.CustomizedAdapter
 * JD-Core Version:    0.6.2
 */