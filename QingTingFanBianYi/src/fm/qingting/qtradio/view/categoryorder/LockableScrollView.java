package fm.qingting.qtradio.view.categoryorder;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LockableScrollView extends ScrollView
{
  private boolean mLocked = false;

  public LockableScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public void lock(boolean paramBoolean)
  {
    this.mLocked = paramBoolean;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLocked)
      return false;
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.categoryorder.LockableScrollView
 * JD-Core Version:    0.6.2
 */