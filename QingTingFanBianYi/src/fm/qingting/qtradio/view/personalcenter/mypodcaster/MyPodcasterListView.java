package fm.qingting.qtradio.view.personalcenter.mypodcaster;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.qtradio.helper.PodcasterHelper;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.MutiCheckManageableAdapter;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPodcasterListView extends ListViewImpl
  implements IEventHandler
{
  private MutiCheckManageableAdapter adapter;
  private IAdapterIViewFactory factory;
  private int mFirstPosition = 0;
  private boolean mIsLogin;
  private UserProfile mUserProfile;
  private int mVisibleCnt = 0;
  private List<Object> podcasterList;

  public MyPodcasterListView(Context paramContext, final int paramInt)
  {
    super(paramContext);
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new MyPodcasterItemView(MyPodcasterListView.this.getContext(), paramInt);
      }
    };
    this.podcasterList = new ArrayList();
    this.adapter = new MutiCheckManageableAdapter(this.podcasterList, this.factory);
    this.adapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(new ColorDrawable(0));
    MiniPlayerPlaceHolder.wrapListView(paramContext, this);
    setAdapter(this.adapter);
    setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        MyPodcasterListView.access$002(MyPodcasterListView.this, paramAnonymousInt1);
        MyPodcasterListView.access$102(MyPodcasterListView.this, paramAnonymousInt2);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
    this.mIsLogin = CloudCenter.getInstance().isLogin(false);
    if (this.mIsLogin)
    {
      this.mUserProfile = InfoManager.getInstance().getUserProfile();
      return;
    }
    this.mUserProfile = null;
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
      paramObject1 = (ItemParam)paramObject2;
      int i = paramObject1.position;
      if (paramObject1.type.equalsIgnoreCase("select"))
        return;
      this.adapter.checkIndex(i);
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  public void update(String paramString, Object paramObject)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    int i;
    if (paramString.equalsIgnoreCase("setdata"))
    {
      paramString = localObject1;
      if (this.mIsLogin)
      {
        paramString = localObject1;
        if (this.mUserProfile != null)
        {
          paramString = localObject1;
          if (this.mUserProfile.getUserInfo() != null)
          {
            paramString = localObject1;
            if (!TextUtils.isEmpty(this.mUserProfile.getUserInfo().snsInfo.sns_id))
              paramString = PodcasterHelper.getInstance().getAllMyPodcaster(this.mUserProfile.getUserInfo().snsInfo.sns_id);
          }
        }
      }
      this.podcasterList.clear();
      if (paramString != null)
      {
        paramString = paramString.iterator();
        while (paramString.hasNext())
        {
          i = ((Integer)paramString.next()).intValue();
          this.podcasterList.add(PodcasterHelper.getInstance().getPodcaster(i));
        }
      }
      this.adapter.setData(this.podcasterList);
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("invalidateList"))
      {
        invalidateVisibleChildren();
        return;
      }
      if (paramString.equalsIgnoreCase("refreshList"))
      {
        paramString = localObject2;
        if (this.mIsLogin)
        {
          paramString = localObject2;
          if (this.mUserProfile != null)
          {
            paramString = localObject2;
            if (this.mUserProfile.getUserInfo() != null)
            {
              paramString = localObject2;
              if (!TextUtils.isEmpty(this.mUserProfile.getUserInfo().snsInfo.sns_id))
                paramString = PodcasterHelper.getInstance().getAllMyPodcaster(this.mUserProfile.getUserInfo().snsInfo.sns_id);
            }
          }
        }
        this.podcasterList.clear();
        if (paramString != null)
        {
          paramString = paramString.iterator();
          while (paramString.hasNext())
          {
            i = ((Integer)paramString.next()).intValue();
            this.podcasterList.add(PodcasterHelper.getInstance().getPodcaster(i));
          }
        }
        this.adapter.setData(this.podcasterList);
        this.adapter.resetCheck();
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
    }
    while (!paramString.equalsIgnoreCase("selectAll"));
    if (((Boolean)paramObject).booleanValue())
    {
      this.adapter.checkAll();
      return;
    }
    this.adapter.resetCheck();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mypodcaster.MyPodcasterListView
 * JD-Core Version:    0.6.2
 */