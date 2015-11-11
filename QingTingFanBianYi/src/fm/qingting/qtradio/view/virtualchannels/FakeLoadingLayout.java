package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

public class FakeLoadingLayout extends LoadingLayout
{
  public FakeLoadingLayout(Context paramContext, PullToRefreshBase.Mode paramMode, PullToRefreshBase.Orientation paramOrientation, TypedArray paramTypedArray)
  {
    super(paramContext, paramMode, paramOrientation, paramTypedArray);
  }

  protected int getDefaultDrawableResId()
  {
    return 2130837595;
  }

  public void onLoadingDrawableSet(Drawable paramDrawable)
  {
  }

  protected void onPullImpl(float paramFloat)
  {
  }

  protected void pullToRefreshImpl()
  {
  }

  protected void refreshingImpl()
  {
  }

  protected void releaseToRefreshImpl()
  {
  }

  protected void resetImpl()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.FakeLoadingLayout
 * JD-Core Version:    0.6.2
 */