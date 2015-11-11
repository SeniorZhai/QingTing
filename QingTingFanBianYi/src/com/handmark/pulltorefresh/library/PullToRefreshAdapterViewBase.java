package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.IndicatorLayout;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T>
  implements AbsListView.OnScrollListener
{
  private View mEmptyView;
  private IndicatorLayout mIndicatorIvBottom;
  private IndicatorLayout mIndicatorIvTop;
  private boolean mLastItemVisible;
  private PullToRefreshBase.OnLastItemVisibleListener mOnLastItemVisibleListener;
  private AbsListView.OnScrollListener mOnScrollListener;
  private boolean mScrollEmptyView = true;
  private boolean mShowIndicator;

  public PullToRefreshAdapterViewBase(Context paramContext)
  {
    super(paramContext);
    ((AbsListView)this.mRefreshableView).setOnScrollListener(this);
  }

  public PullToRefreshAdapterViewBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ((AbsListView)this.mRefreshableView).setOnScrollListener(this);
  }

  public PullToRefreshAdapterViewBase(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
    ((AbsListView)this.mRefreshableView).setOnScrollListener(this);
  }

  public PullToRefreshAdapterViewBase(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
    ((AbsListView)this.mRefreshableView).setOnScrollListener(this);
  }

  private void addIndicatorViews()
  {
    Object localObject = getMode();
    FrameLayout localFrameLayout = getRefreshableViewWrapper();
    if ((((PullToRefreshBase.Mode)localObject).showHeaderLoadingLayout()) && (this.mIndicatorIvTop == null))
    {
      this.mIndicatorIvTop = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
      localLayoutParams.rightMargin = getResources().getDimensionPixelSize(2131361808);
      localLayoutParams.gravity = 53;
      localFrameLayout.addView(this.mIndicatorIvTop, localLayoutParams);
    }
    do
      while ((((PullToRefreshBase.Mode)localObject).showFooterLoadingLayout()) && (this.mIndicatorIvBottom == null))
      {
        this.mIndicatorIvBottom = new IndicatorLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END);
        localObject = new FrameLayout.LayoutParams(-2, -2);
        ((FrameLayout.LayoutParams)localObject).rightMargin = getResources().getDimensionPixelSize(2131361808);
        ((FrameLayout.LayoutParams)localObject).gravity = 85;
        localFrameLayout.addView(this.mIndicatorIvBottom, (ViewGroup.LayoutParams)localObject);
        return;
        if ((!((PullToRefreshBase.Mode)localObject).showHeaderLoadingLayout()) && (this.mIndicatorIvTop != null))
        {
          localFrameLayout.removeView(this.mIndicatorIvTop);
          this.mIndicatorIvTop = null;
        }
      }
    while ((((PullToRefreshBase.Mode)localObject).showFooterLoadingLayout()) || (this.mIndicatorIvBottom == null));
    localFrameLayout.removeView(this.mIndicatorIvBottom);
    this.mIndicatorIvBottom = null;
  }

  private static FrameLayout.LayoutParams convertEmptyViewLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    FrameLayout.LayoutParams localLayoutParams = null;
    if (paramLayoutParams != null)
    {
      localLayoutParams = new FrameLayout.LayoutParams(paramLayoutParams);
      if ((paramLayoutParams instanceof LinearLayout.LayoutParams))
        localLayoutParams.gravity = ((LinearLayout.LayoutParams)paramLayoutParams).gravity;
    }
    else
    {
      return localLayoutParams;
    }
    localLayoutParams.gravity = 17;
    return localLayoutParams;
  }

  private boolean getShowIndicatorInternal()
  {
    return (this.mShowIndicator) && (isPullToRefreshEnabled());
  }

  private boolean isFirstItemVisible()
  {
    Object localObject = ((AbsListView)this.mRefreshableView).getAdapter();
    if ((localObject == null) || (((Adapter)localObject).isEmpty()))
      return true;
    if (((AbsListView)this.mRefreshableView).getFirstVisiblePosition() <= 1)
    {
      localObject = ((AbsListView)this.mRefreshableView).getChildAt(0);
      if (localObject != null)
        return ((View)localObject).getTop() >= ((AbsListView)this.mRefreshableView).getTop();
    }
    return false;
  }

  private boolean isLastItemVisible()
  {
    Object localObject = ((AbsListView)this.mRefreshableView).getAdapter();
    if ((localObject == null) || (((Adapter)localObject).isEmpty()))
      return true;
    int j = ((AbsListView)this.mRefreshableView).getCount();
    int i = ((AbsListView)this.mRefreshableView).getLastVisiblePosition();
    if (i >= j - 1 - 1)
    {
      j = ((AbsListView)this.mRefreshableView).getFirstVisiblePosition();
      localObject = ((AbsListView)this.mRefreshableView).getChildAt(i - j);
      if (localObject != null)
        return ((View)localObject).getBottom() <= ((AbsListView)this.mRefreshableView).getBottom();
    }
    return false;
  }

  private void removeIndicatorViews()
  {
    if (this.mIndicatorIvTop != null)
    {
      getRefreshableViewWrapper().removeView(this.mIndicatorIvTop);
      this.mIndicatorIvTop = null;
    }
    if (this.mIndicatorIvBottom != null)
    {
      getRefreshableViewWrapper().removeView(this.mIndicatorIvBottom);
      this.mIndicatorIvBottom = null;
    }
  }

  private void updateIndicatorViewsVisibility()
  {
    if (this.mIndicatorIvTop != null)
    {
      if ((isRefreshing()) || (!isReadyForPullStart()))
        break label77;
      if (!this.mIndicatorIvTop.isVisible())
        this.mIndicatorIvTop.show();
    }
    label77: 
    do
      while (true)
      {
        if (this.mIndicatorIvBottom != null)
        {
          if ((isRefreshing()) || (!isReadyForPullEnd()))
            break;
          if (!this.mIndicatorIvBottom.isVisible())
            this.mIndicatorIvBottom.show();
        }
        return;
        if (this.mIndicatorIvTop.isVisible())
          this.mIndicatorIvTop.hide();
      }
    while (!this.mIndicatorIvBottom.isVisible());
    this.mIndicatorIvBottom.hide();
  }

  public void addListFooterView(View paramView)
  {
    ((ListView)this.mRefreshableView).addFooterView(paramView);
  }

  public void addListHeaderView(View paramView)
  {
    ((ListView)this.mRefreshableView).addHeaderView(paramView);
  }

  public View getListChildAt(int paramInt)
  {
    return ((ListView)this.mRefreshableView).getChildAt(paramInt);
  }

  public int getListChildCnt()
  {
    return ((ListView)this.mRefreshableView).getChildCount();
  }

  public boolean getShowIndicator()
  {
    return this.mShowIndicator;
  }

  protected void handleStyledAttributes(TypedArray paramTypedArray)
  {
    if (!isPullToRefreshOverScrollEnabled());
    for (boolean bool = true; ; bool = false)
    {
      this.mShowIndicator = paramTypedArray.getBoolean(5, bool);
      return;
    }
  }

  protected boolean isReadyForPullEnd()
  {
    return isLastItemVisible();
  }

  protected boolean isReadyForPullStart()
  {
    return isFirstItemVisible();
  }

  protected void onPullToRefresh()
  {
    super.onPullToRefresh();
    if (getShowIndicatorInternal());
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.mIndicatorIvBottom.pullToRefresh();
      return;
    case 2:
    }
    this.mIndicatorIvTop.pullToRefresh();
  }

  protected void onRefreshing(boolean paramBoolean)
  {
    super.onRefreshing(paramBoolean);
    if (getShowIndicatorInternal())
      updateIndicatorViewsVisibility();
  }

  protected void onReleaseToRefresh()
  {
    super.onReleaseToRefresh();
    if (getShowIndicatorInternal());
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()])
    {
    default:
      return;
    case 1:
      this.mIndicatorIvBottom.releaseToRefresh();
      return;
    case 2:
    }
    this.mIndicatorIvTop.releaseToRefresh();
  }

  protected void onReset()
  {
    super.onReset();
    if (getShowIndicatorInternal())
      updateIndicatorViewsVisibility();
  }

  public final void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.mOnLastItemVisibleListener != null)
      if ((paramInt3 <= 0) || (paramInt1 + paramInt2 < paramInt3 - 1))
        break label64;
    label64: for (boolean bool = true; ; bool = false)
    {
      this.mLastItemVisible = bool;
      if (getShowIndicatorInternal())
        updateIndicatorViewsVisibility();
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
      return;
    }
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((this.mEmptyView != null) && (!this.mScrollEmptyView))
      this.mEmptyView.scrollTo(-paramInt1, -paramInt2);
  }

  public final void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((paramInt == 0) && (this.mOnLastItemVisibleListener != null) && (this.mLastItemVisible))
      this.mOnLastItemVisibleListener.onLastItemVisible();
    if (this.mOnScrollListener != null)
      this.mOnScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
  }

  public void removeListHeaderView(View paramView)
  {
    ((ListView)this.mRefreshableView).removeHeaderView(paramView);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    ((AdapterView)this.mRefreshableView).setAdapter(paramListAdapter);
  }

  public final void setEmptyView(View paramView)
  {
    FrameLayout localFrameLayout = getRefreshableViewWrapper();
    if (paramView != null)
    {
      paramView.setClickable(true);
      Object localObject = paramView.getParent();
      if ((localObject != null) && ((localObject instanceof ViewGroup)))
        ((ViewGroup)localObject).removeView(paramView);
      localObject = convertEmptyViewLayoutParams(paramView.getLayoutParams());
      if (localObject != null)
        localFrameLayout.addView(paramView, (ViewGroup.LayoutParams)localObject);
    }
    else
    {
      if (!(this.mRefreshableView instanceof EmptyViewMethodAccessor))
        break label93;
      ((EmptyViewMethodAccessor)this.mRefreshableView).setEmptyViewInternal(paramView);
    }
    while (true)
    {
      this.mEmptyView = paramView;
      return;
      localFrameLayout.addView(paramView);
      break;
      label93: ((AbsListView)this.mRefreshableView).setEmptyView(paramView);
    }
  }

  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    ((AbsListView)this.mRefreshableView).setOnItemClickListener(paramOnItemClickListener);
  }

  public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener paramOnLastItemVisibleListener)
  {
    this.mOnLastItemVisibleListener = paramOnLastItemVisibleListener;
  }

  public final void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.mOnScrollListener = paramOnScrollListener;
  }

  public final void setScrollEmptyView(boolean paramBoolean)
  {
    this.mScrollEmptyView = paramBoolean;
  }

  public void setSelection(int paramInt)
  {
    ((AdapterView)this.mRefreshableView).setSelection(paramInt);
  }

  public void setSelectionFromTop(int paramInt1, int paramInt2)
  {
    ((ListView)this.mRefreshableView).setSelectionFromTop(paramInt1, paramInt2);
  }

  public void setSelector(int paramInt)
  {
    ((ListView)this.mRefreshableView).setSelector(paramInt);
  }

  public void setShowIndicator(boolean paramBoolean)
  {
    this.mShowIndicator = paramBoolean;
    if (getShowIndicatorInternal())
    {
      addIndicatorViews();
      return;
    }
    removeIndicatorViews();
  }

  public void setTranscriptMode(int paramInt)
  {
    ((ListView)this.mRefreshableView).setTranscriptMode(paramInt);
  }

  protected void updateUIForMode()
  {
    super.updateUIForMode();
    if (getShowIndicatorInternal())
    {
      addIndicatorViews();
      return;
    }
    removeIndicatorViews();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase
 * JD-Core Version:    0.6.2
 */