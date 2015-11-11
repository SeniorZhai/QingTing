package fm.qingting.qtradio.view.tab;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public abstract interface PageIndicator extends ViewPager.OnPageChangeListener
{
  public abstract void notifyDataSetChanged();

  public abstract void setCurrentItem(int paramInt);

  public abstract void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener);

  public abstract void setViewPager(ViewPager paramViewPager);

  public abstract void setViewPager(ViewPager paramViewPager, int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.tab.PageIndicator
 * JD-Core Version:    0.6.2
 */