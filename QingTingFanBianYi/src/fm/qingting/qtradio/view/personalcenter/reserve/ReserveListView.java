package fm.qingting.qtradio.view.personalcenter.reserve;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.model.MutiCheckManageableAdapter;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReserveListView extends ListViewImpl
  implements IEventHandler
{
  private MutiCheckManageableAdapter adapter = new MutiCheckManageableAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new ReserveItemView(ReserveListView.this.getContext(), this.val$hash);
    }
  };
  private int mFirstPosition = 0;
  private int mVisibleCnt = 0;

  public ReserveListView(Context paramContext)
  {
    super(paramContext);
    this.adapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this);
    setAdapter(this.adapter);
    setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        ReserveListView.access$002(ReserveListView.this, paramAnonymousInt1);
        ReserveListView.access$102(ReserveListView.this, paramAnonymousInt2);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
  }

  private void invalidateVisibleChildren()
  {
    int i = this.mFirstPosition;
    while (i < this.mFirstPosition + this.mVisibleCnt)
    {
      View localView = getChildAt(i);
      if (localView != null)
        localView.invalidate();
      i += 1;
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    Object localObject = null;
    if (paramString.equalsIgnoreCase("hasCheckedIndexs"))
      paramObject = Boolean.valueOf(this.adapter.hasCheckedIndexs());
    do
    {
      List localList;
      do
      {
        do
        {
          return paramObject;
          if (!paramString.equalsIgnoreCase("deletelist"))
            break;
          paramString = this.adapter.getCheckList();
          localList = this.adapter.getData();
          paramObject = localObject;
        }
        while (paramString == null);
        paramObject = localObject;
      }
      while (localList == null);
      paramObject = new ArrayList();
      while (paramString.hasNext())
      {
        int i = ((Integer)paramString.next()).intValue();
        if ((i >= 0) && (i < localList.size()))
          paramObject.add(localList.get(i));
      }
      return paramObject;
      paramObject = localObject;
    }
    while (!paramString.equalsIgnoreCase("allData"));
    return this.adapter.getData();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      int i = ((ItemParam)paramObject2).position;
      this.adapter.checkIndex(i);
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("invalidateList"))
      invalidateVisibleChildren();
    do
    {
      return;
      if (paramString.equalsIgnoreCase("refreshList"))
      {
        this.adapter.notifyDataSetChanged();
        return;
      }
      if (paramString.equalsIgnoreCase("resetData"))
      {
        this.adapter.setData((List)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("showManage"))
      {
        this.adapter.showManage(((Integer)paramObject).intValue());
        return;
      }
      if (paramString.equalsIgnoreCase("hideManage"))
      {
        this.adapter.hideManage();
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        this.adapter.setData((List)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("changeProcessState"))
      {
        this.adapter.notifyDataSetChanged();
        return;
      }
      if (paramString.equalsIgnoreCase("resetCheckList"))
      {
        this.adapter.resetCheck();
        return;
      }
      if (paramString.equalsIgnoreCase("selectAll"))
      {
        if (((Boolean)paramObject).booleanValue())
        {
          this.adapter.checkAll();
          return;
        }
        this.adapter.resetCheck();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("delete"));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.reserve.ReserveListView
 * JD-Core Version:    0.6.2
 */