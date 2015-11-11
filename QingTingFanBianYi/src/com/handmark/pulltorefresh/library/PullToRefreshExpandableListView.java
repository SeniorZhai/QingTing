package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

public class PullToRefreshExpandableListView extends PullToRefreshAdapterViewBase<ExpandableListView>
{
  public PullToRefreshExpandableListView(Context paramContext)
  {
    super(paramContext);
  }

  public PullToRefreshExpandableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullToRefreshExpandableListView(Context paramContext, PullToRefreshBase.Mode paramMode)
  {
    super(paramContext, paramMode);
  }

  public PullToRefreshExpandableListView(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.AnimationStyle paramAnimationStyle)
  {
    super(paramContext, paramMode, paramAnimationStyle);
  }

  protected ExpandableListView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (Build.VERSION.SDK_INT >= 9);
    for (paramContext = new InternalExpandableListViewSDK9(paramContext, paramAttributeSet); ; paramContext = new InternalExpandableListView(paramContext, paramAttributeSet))
    {
      paramContext.setId(16908298);
      return paramContext;
    }
  }

  public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection()
  {
    return PullToRefreshBase.Orientation.VERTICAL;
  }

  class InternalExpandableListView extends ExpandableListView
    implements EmptyViewMethodAccessor
  {
    public InternalExpandableListView(Context paramAttributeSet, AttributeSet arg3)
    {
      super(localAttributeSet);
    }

    public void setEmptyView(View paramView)
    {
      PullToRefreshExpandableListView.this.setEmptyView(paramView);
    }

    public void setEmptyViewInternal(View paramView)
    {
      super.setEmptyView(paramView);
    }
  }

  @TargetApi(9)
  final class InternalExpandableListViewSDK9 extends PullToRefreshExpandableListView.InternalExpandableListView
  {
    public InternalExpandableListViewSDK9(Context paramAttributeSet, AttributeSet arg3)
    {
      super(paramAttributeSet, localAttributeSet);
    }

    protected boolean overScrollBy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, boolean paramBoolean)
    {
      boolean bool = super.overScrollBy(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramBoolean);
      OverscrollHelper.overScrollBy(PullToRefreshExpandableListView.this, paramInt1, paramInt3, paramInt2, paramInt4, paramBoolean);
      return bool;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.PullToRefreshExpandableListView
 * JD-Core Version:    0.6.2
 */