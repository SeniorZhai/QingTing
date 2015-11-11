package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.LoadingLayout2;
import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout2;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView>
{
  private LoadingLayout2 mFooterLoadingView;
  private LoadingLayout2 mHeaderLoadingView;
  private boolean mListViewExtrasEnabled;
  private FrameLayout mLvFooterLoadingFrame;

  public PullToRefreshListView(Context paramContext)
  {
    super(paramContext);
  }

  public PullToRefreshListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullToRefreshListView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
  }

  public PullToRefreshListView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
  }

  protected ListView createListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (Build.VERSION.SDK_INT >= 9)
      return new InternalListViewSDK9(paramContext, paramAttributeSet);
    return new InternalListView(paramContext, paramAttributeSet);
  }

  protected LoadingLayoutProxy createLoadingLayoutProxy(boolean paramBoolean1, boolean paramBoolean2)
  {
    LoadingLayoutProxy localLoadingLayoutProxy = super.createLoadingLayoutProxy(paramBoolean1, paramBoolean2);
    if (this.mListViewExtrasEnabled)
    {
      PullToRefreshBase.Mode localMode = getMode();
      if ((paramBoolean1) && (localMode.showHeaderLoadingLayout()))
        localLoadingLayoutProxy.addLayout(this.mHeaderLoadingView);
      if ((paramBoolean2) && (localMode.showFooterLoadingLayout()))
        localLoadingLayoutProxy.addLayout(this.mFooterLoadingView);
    }
    return localLoadingLayoutProxy;
  }

  protected ListView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = createListView(paramContext, paramAttributeSet);
    paramContext.setId(16908298);
    return paramContext;
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.VERTICAL;
  }

  protected void handleStyledAttributes(TypedArray paramTypedArray)
  {
    super.handleStyledAttributes(paramTypedArray);
    this.mListViewExtrasEnabled = paramTypedArray.getBoolean(14, true);
    if (this.mListViewExtrasEnabled)
    {
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
      FrameLayout localFrameLayout = new FrameLayout(getContext());
      this.mHeaderLoadingView = new RotateLoadingLayout2(getContext());
      this.mHeaderLoadingView.setVisibility(8);
      localFrameLayout.addView(this.mHeaderLoadingView, localLayoutParams);
      ((ListView)this.mRefreshableView).addHeaderView(localFrameLayout, null, false);
      this.mLvFooterLoadingFrame = new FrameLayout(getContext());
      this.mFooterLoadingView = new RotateLoadingLayout2(getContext());
      this.mFooterLoadingView.setVisibility(8);
      this.mLvFooterLoadingFrame.addView(this.mFooterLoadingView, localLayoutParams);
      if (!paramTypedArray.hasValue(13))
        setScrollingWhileRefreshingEnabled(true);
    }
  }

  protected void onRefreshing(boolean paramBoolean)
  {
    Object localObject = ((ListView)this.mRefreshableView).getAdapter();
    if ((!this.mListViewExtrasEnabled) || (!getShowViewWhileRefreshing()) || (localObject == null) || (((ListAdapter)localObject).isEmpty()))
    {
      super.onRefreshing(paramBoolean);
      return;
    }
    super.onRefreshing(false);
    LoadingLayout2 localLoadingLayout21;
    LoadingLayout2 localLoadingLayout22;
    int i;
    int j;
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()])
    {
    default:
      localObject = getHeaderLayout();
      localLoadingLayout21 = this.mHeaderLoadingView;
      localLoadingLayout22 = this.mFooterLoadingView;
      i = getScrollY() + getHeaderSize();
      j = 0;
    case 1:
    case 2:
    }
    while (true)
    {
      ((LoadingLayout2)localObject).reset();
      ((LoadingLayout2)localObject).hideAllViews();
      localLoadingLayout22.setVisibility(8);
      localLoadingLayout21.setVisibility(0);
      localLoadingLayout21.refreshing();
      if (!paramBoolean)
        break;
      disableLoadingLayoutVisibilityChanges();
      setHeaderScroll(i);
      ((ListView)this.mRefreshableView).setSelection(j);
      smoothScrollTo(0);
      return;
      localObject = getFooterLayout();
      localLoadingLayout21 = this.mFooterLoadingView;
      localLoadingLayout22 = this.mHeaderLoadingView;
      j = ((ListView)this.mRefreshableView).getCount() - 1;
      i = getScrollY() - getFooterSize();
    }
  }

  protected void onReset()
  {
    int j = 0;
    int i = 1;
    if (!this.mListViewExtrasEnabled)
    {
      super.onReset();
      return;
    }
    LoadingLayout2 localLoadingLayout22;
    LoadingLayout2 localLoadingLayout21;
    int k;
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()])
    {
    default:
      localLoadingLayout22 = getHeaderLayout();
      localLoadingLayout21 = this.mHeaderLoadingView;
      k = -getHeaderSize();
      if (Math.abs(((ListView)this.mRefreshableView).getFirstVisiblePosition() - 0) > 1)
        break;
    case 1:
    case 2:
    }
    while (true)
    {
      if (localLoadingLayout21.getVisibility() == 0)
      {
        localLoadingLayout22.showInvisibleViews();
        localLoadingLayout21.setVisibility(8);
        if ((i != 0) && (getState() != PullToRefreshBase.State.MANUAL_REFRESHING))
        {
          ((ListView)this.mRefreshableView).setSelection(j);
          setHeaderScroll(k);
        }
      }
      super.onReset();
      return;
      localLoadingLayout22 = getFooterLayout();
      localLoadingLayout21 = this.mFooterLoadingView;
      j = ((ListView)this.mRefreshableView).getCount() - 1;
      k = getFooterSize();
      if (Math.abs(((ListView)this.mRefreshableView).getLastVisiblePosition() - j) <= 1);
      for (i = 1; ; i = 0)
        break;
      i = 0;
    }
  }

  protected class InternalListView extends ListView
    implements EmptyViewMethodAccessor
  {
    private boolean mAddedLvFooter = false;

    public InternalListView(Context paramAttributeSet, AttributeSet arg3)
    {
      super(localAttributeSet);
    }

    protected void dispatchDraw(Canvas paramCanvas)
    {
      try
      {
        super.dispatchDraw(paramCanvas);
        return;
      }
      catch (IndexOutOfBoundsException paramCanvas)
      {
        paramCanvas.printStackTrace();
      }
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
      try
      {
        boolean bool = super.dispatchTouchEvent(paramMotionEvent);
        return bool;
      }
      catch (IndexOutOfBoundsException paramMotionEvent)
      {
        paramMotionEvent.printStackTrace();
      }
      return false;
    }

    public void setAdapter(ListAdapter paramListAdapter)
    {
      if ((PullToRefreshListView.this.mLvFooterLoadingFrame != null) && (!this.mAddedLvFooter))
      {
        addFooterView(PullToRefreshListView.this.mLvFooterLoadingFrame, null, false);
        this.mAddedLvFooter = true;
      }
      super.setAdapter(paramListAdapter);
    }

    public void setEmptyView(View paramView)
    {
      PullToRefreshListView.this.setEmptyView(paramView);
    }

    public void setEmptyViewInternal(View paramView)
    {
      super.setEmptyView(paramView);
    }
  }

  @TargetApi(9)
  final class InternalListViewSDK9 extends PullToRefreshListView.InternalListView
  {
    public InternalListViewSDK9(Context paramAttributeSet, AttributeSet arg3)
    {
      super(paramAttributeSet, localAttributeSet);
    }

    protected boolean overScrollBy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
    {
      boolean bool = super.overScrollBy(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramBoolean);
      OverscrollHelper.overScrollBy(PullToRefreshListView.this, paramInt1, paramInt3, paramInt2, paramInt4, paramBoolean);
      return bool;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshListView
 * JD-Core Version:    0.6.2
 */