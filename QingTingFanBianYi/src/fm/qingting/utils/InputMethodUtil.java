package fm.qingting.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtil
{
  public static boolean hideSoftInput(View paramView)
  {
    if (paramView == null)
      return false;
    return ((InputMethodManager)paramView.getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramView.getApplicationWindowToken(), 0);
  }

  public static boolean showSoftInput(View paramView)
  {
    if (paramView == null)
      return false;
    return ((InputMethodManager)paramView.getContext().getSystemService("input_method")).showSoftInput(paramView, 0);
  }

  public static abstract interface InputStateDelegate
  {
    public abstract void closeIfNeed();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.InputMethodUtil
 * JD-Core Version:    0.6.2
 */