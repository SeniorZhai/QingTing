package com.handmark.pulltorefresh.library;

import android.view.View;
import android.view.animation.Interpolator;

public abstract interface IPullToRefresh<T extends View>
{
  public abstract boolean demo();

  public abstract PullToRefreshBase.Mode getCurrentMode();

  public abstract boolean getFilterTouchEvents();

  public abstract ILoadingLayout getLoadingLayoutProxy();

  public abstract ILoadingLayout getLoadingLayoutProxy(boolean paramBoolean1, boolean paramBoolean2);

  public abstract PullToRefreshBase.Mode getMode();

  public abstract T getRefreshableView();

  public abstract boolean getShowViewWhileRefreshing();

  public abstract PullToRefreshBase.State getState();

  public abstract boolean isPullToRefreshEnabled();

  public abstract boolean isPullToRefreshOverScrollEnabled();

  public abstract boolean isRefreshing();

  public abstract boolean isScrollingWhileRefreshingEnabled();

  public abstract void onRefreshComplete();

  public abstract void setFilterTouchEvents(boolean paramBoolean);

  public abstract void setMode(PullToRefreshBase.Mode paramMode);

  public abstract void setOnPullEventListener(PullToRefreshBase.OnPullEventListener<T> paramOnPullEventListener);

  public abstract void setOnRefreshListener(PullToRefreshBase.OnRefreshListener2<T> paramOnRefreshListener2);

  public abstract void setOnRefreshListener(PullToRefreshBase.OnRefreshListener<T> paramOnRefreshListener);

  public abstract void setPullToRefreshOverScrollEnabled(boolean paramBoolean);

  public abstract void setRefreshing();

  public abstract void setRefreshing(boolean paramBoolean);

  public abstract void setScrollAnimationInterpolator(Interpolator paramInterpolator);

  public abstract void setScrollingWhileRefreshingEnabled(boolean paramBoolean);

  public abstract void setShowViewWhileRefreshing(boolean paramBoolean);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.IPullToRefresh
 * JD-Core Version:    0.6.2
 */