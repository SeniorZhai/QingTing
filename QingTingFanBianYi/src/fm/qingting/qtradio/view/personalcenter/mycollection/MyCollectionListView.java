package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import fm.qingting.framework.adapter.CustomizedAdapter.ViewEventHandler;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.MutiCheckManageableAdapter;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyCollectionListView extends ListViewImpl
  implements IEventHandler
{
  private MyCollectionAdapter adapter;
  private int mFirstPosition = 0;
  private int mHash;
  private Button mLoginBtn;
  private RelativeLayout mLoginView = (RelativeLayout)inflate(getContext(), 2130903043, null);
  private int mVisibleCnt = 0;

  public MyCollectionListView(Context paramContext, int paramInt)
  {
    super(paramContext);
    addHeaderView(this.mLoginView);
    this.mHash = paramInt;
    this.adapter = new MyCollectionAdapter(new ArrayList());
    this.adapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(new ColorDrawable(0));
    setAdapter(this.adapter);
    setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        MyCollectionListView.access$002(MyCollectionListView.this, paramAnonymousInt1);
        MyCollectionListView.access$102(MyCollectionListView.this, paramAnonymousInt2);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
    this.mLoginBtn = ((Button)this.mLoginView.findViewById(2131230740));
    this.mLoginBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EventDispacthManager.getInstance().dispatchAction("showlogin", null);
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "login_collection");
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
        {
          localObject = localList.get(i);
          if ((localObject instanceof ChannelNode))
            paramObject.add(localObject);
        }
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

  public void setLoginVisibility(int paramInt)
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)this.mLoginView.findViewById(2131230738);
    this.mLoginView.setVisibility(paramInt);
    localRelativeLayout.setVisibility(paramInt);
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
        Log.d("Collectionlistview", "show manager:" + paramObject);
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
        paramString = (List)paramObject;
        this.adapter.setData(paramString);
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

  private class MyCollectionAdapter extends MutiCheckManageableAdapter
  {
    private List<ChannelNode> mLiveChannelLst = new ArrayList();
    private List<ChannelNode> mVirtualChannelLst = new ArrayList();

    public MyCollectionAdapter()
    {
      super(null);
      fillLists(localList);
    }

    private void fillLists(List<Object> paramList)
    {
      this.mLiveChannelLst.clear();
      this.mVirtualChannelLst.clear();
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          Object localObject = paramList.next();
          if ((localObject instanceof ChannelNode))
          {
            localObject = (ChannelNode)localObject;
            if (((ChannelNode)localObject).channelType == 0)
              this.mLiveChannelLst.add(localObject);
            else if (((ChannelNode)localObject).channelType == 1)
              this.mVirtualChannelLst.add(localObject);
          }
        }
      }
    }

    private View newView(int paramInt)
    {
      if (paramInt == 0)
        return new MyCollectionItemView2(MyCollectionListView.this.getContext(), MyCollectionListView.this.mHash);
      if (paramInt == 1)
        return new GroupTitleItemView(MyCollectionListView.this.getContext());
      if (paramInt == 2)
        return new CustomSectionView(MyCollectionListView.this.getContext());
      return null;
    }

    public int getCount()
    {
      int j = 0;
      if (this.mLiveChannelLst.size() > 0)
        j = 0 + (this.mLiveChannelLst.size() + 1);
      int i = j;
      if (this.mVirtualChannelLst.size() > 0)
        i = j + (this.mVirtualChannelLst.size() + 1);
      j = i;
      if (this.mLiveChannelLst.size() > 0)
      {
        j = i;
        if (this.mVirtualChannelLst.size() > 0)
          j = i + 1;
      }
      return j;
    }

    public List<Object> getData()
    {
      ArrayList localArrayList = new ArrayList();
      if (this.mLiveChannelLst.size() > 0)
      {
        localArrayList.add("");
        localArrayList.addAll(this.mLiveChannelLst);
      }
      if (this.mVirtualChannelLst.size() > 0)
      {
        if (this.mLiveChannelLst.size() > 0)
          localArrayList.add("");
        localArrayList.add("");
        localArrayList.addAll(this.mVirtualChannelLst);
      }
      return localArrayList;
    }

    public Object getItem(int paramInt)
    {
      int i = 0;
      if (this.mLiveChannelLst.size() > 0)
      {
        int j = this.mLiveChannelLst.size() + 2;
        if (paramInt == 0)
          return "电台";
        if (paramInt <= this.mLiveChannelLst.size())
          return this.mLiveChannelLst.get(paramInt - 1);
        i = j;
        if (paramInt == j - 1)
          return "分割线";
      }
      paramInt -= i;
      if (paramInt == 0)
        return "专辑";
      if (paramInt <= this.mVirtualChannelLst.size())
        return this.mVirtualChannelLst.get(paramInt - 1);
      return null;
    }

    public int getItemViewType(int paramInt)
    {
      int j;
      if (this.mLiveChannelLst.size() > 0)
      {
        j = this.mLiveChannelLst.size() + 2;
        if (paramInt != 0);
      }
      do
      {
        return 1;
        if (paramInt <= this.mLiveChannelLst.size())
          return 0;
        int i = j;
        if (paramInt == j - 1)
        {
          return 2;
          i = 0;
        }
        paramInt -= i;
      }
      while (paramInt == 0);
      if (paramInt <= this.mVirtualChannelLst.size())
        return 0;
      return 0;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = getItemViewType(paramInt);
      paramViewGroup = getItem(paramInt);
      if (paramView == null)
        paramView = newView(i);
      while (true)
      {
        MyCollectionItemView2 localMyCollectionItemView2;
        if (paramView != null)
        {
          if (i != 0)
            break label110;
          localMyCollectionItemView2 = (MyCollectionItemView2)paramView;
          localMyCollectionItemView2.update("content", paramViewGroup);
          localMyCollectionItemView2.update("checkstate", Boolean.valueOf(isChecked(paramInt)));
          if (!this.showDeleteButton)
            break label99;
          localMyCollectionItemView2.update("showManage", Integer.valueOf(this.mCheckOffset));
          localMyCollectionItemView2.setEventHandler(new CustomizedAdapter.ViewEventHandler(this, paramInt));
        }
        label99: label110: 
        do
        {
          return paramView;
          localMyCollectionItemView2.update("hideManage", null);
          break;
          if (i == 1)
          {
            ((GroupTitleItemView)paramView).setTitle((String)paramViewGroup);
            return paramView;
          }
        }
        while (i != 2);
        paramViewGroup = (CustomSectionView)paramView;
        return paramView;
      }
    }

    public int getViewTypeCount()
    {
      return 3;
    }

    public void setData(List<Object> paramList)
    {
      fillLists(paramList);
      super.setData(paramList);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.MyCollectionListView
 * JD-Core Version:    0.6.2
 */