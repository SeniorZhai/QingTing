package fm.qingting.qtradio.helper;

import android.support.v4.view.ViewPager;
import fm.qingting.framework.view.IView;

public class ViewPagerReleaseHelper
{
  public static void release(ViewPager paramViewPager)
  {
    if (paramViewPager == null);
    while (true)
    {
      return;
      try
      {
        int j = paramViewPager.getChildCount();
        int i = 0;
        while (i < j)
        {
          ((IView)paramViewPager.getChildAt(i)).close(false);
          i += 1;
        }
      }
      catch (Exception paramViewPager)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.ViewPagerReleaseHelper
 * JD-Core Version:    0.6.2
 */