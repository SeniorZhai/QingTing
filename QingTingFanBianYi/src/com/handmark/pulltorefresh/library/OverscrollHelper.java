package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(9)
public final class OverscrollHelper
{
  static final float DEFAULT_OVERSCROLL_SCALE = 1.0F;
  static final String LOG_TAG = "OverscrollHelper";

  static boolean isAndroidOverScrollEnabled(View paramView)
  {
    return paramView.getOverScrollMode() != 2;
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, float paramFloat, boolean paramBoolean)
  {
    PullToRefreshBase.Mode localMode;
    switch (1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[paramPullToRefreshBase.getPullToRefreshScrollDirection().ordinal()])
    {
    default:
      int i = paramPullToRefreshBase.getScrollY();
      paramInt2 = paramInt4;
      paramInt1 = paramInt3;
      paramInt3 = i;
      if ((paramPullToRefreshBase.isPullToRefreshOverScrollEnabled()) && (!paramPullToRefreshBase.isRefreshing()))
      {
        localMode = paramPullToRefreshBase.getMode();
        if ((!localMode.permitsPullToRefresh()) || (paramBoolean) || (paramInt1 == 0))
          break label213;
        paramInt1 += paramInt2;
        if (paramInt1 >= 0 - paramInt6)
          break label134;
        if (localMode.showHeaderLoadingLayout())
        {
          if (paramInt3 == 0)
            paramPullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
          paramPullToRefreshBase.setHeaderScroll((int)((paramInt3 + paramInt1) * paramFloat));
        }
      }
      break;
    case 1:
    }
    label134: label181: label213: 
    while ((!paramBoolean) || (PullToRefreshBase.State.OVERSCROLLING != paramPullToRefreshBase.getState()))
    {
      do
      {
        do
        {
          return;
          paramInt3 = paramPullToRefreshBase.getScrollX();
          break;
          if (paramInt1 <= paramInt5 + paramInt6)
            break label181;
        }
        while (!localMode.showFooterLoadingLayout());
        if (paramInt3 == 0)
          paramPullToRefreshBase.setState(PullToRefreshBase.State.OVERSCROLLING, new boolean[0]);
        paramPullToRefreshBase.setHeaderScroll((int)((paramInt3 + paramInt1 - paramInt5) * paramFloat));
        return;
      }
      while ((Math.abs(paramInt1) > paramInt6) && (Math.abs(paramInt1 - paramInt5) > paramInt6));
      paramPullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
      return;
    }
    paramPullToRefreshBase.setState(PullToRefreshBase.State.RESET, new boolean[0]);
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    overScrollBy(paramPullToRefreshBase, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, 0, 1.0F, paramBoolean);
  }

  public static void overScrollBy(PullToRefreshBase<?> paramPullToRefreshBase, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    overScrollBy(paramPullToRefreshBase, paramInt1, paramInt2, paramInt3, paramInt4, 0, paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.OverscrollHelper
 * JD-Core Version:    0.6.2
 */