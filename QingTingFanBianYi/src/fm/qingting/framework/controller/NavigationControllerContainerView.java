package fm.qingting.framework.controller;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import fm.qingting.framework.view.FrameLayoutViewImpl;

class NavigationControllerContainerView extends FrameLayoutViewImpl
{
  public NavigationControllerContainerView(Context paramContext)
  {
    super(paramContext);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    int i = getChildCount();
    if (i > 0)
      return getChildAt(i - 1).dispatchKeyEvent(paramKeyEvent);
    return false;
  }

  public boolean dispatchKeyEventPreIme(KeyEvent paramKeyEvent)
  {
    int i = getChildCount();
    if (i > 0)
      return getChildAt(i - 1).dispatchKeyEventPreIme(paramKeyEvent);
    return false;
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = getChildCount();
    if (i > 0)
      getChildAt(i - 1).dispatchTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.NavigationControllerContainerView
 * JD-Core Version:    0.6.2
 */