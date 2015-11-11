package com.handmark.pulltorefresh.library;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.handmark.pulltorefresh.library.internal.LoadingLayout2;
import java.util.HashSet;
import java.util.Iterator;

public class LoadingLayoutProxy
  implements ILoadingLayout
{
  private final HashSet<LoadingLayout2> mLoadingLayouts = new HashSet();

  public void addLayout(LoadingLayout2 paramLoadingLayout2)
  {
    if (paramLoadingLayout2 != null)
      this.mLoadingLayouts.add(paramLoadingLayout2);
  }

  public void setLastUpdatedLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setLastUpdatedLabel(paramCharSequence);
  }

  public void setLoadingDrawable(Drawable paramDrawable)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setLoadingDrawable(paramDrawable);
  }

  public void setPullLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setPullLabel(paramCharSequence);
  }

  public void setRefreshingLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setRefreshingLabel(paramCharSequence);
  }

  public void setReleaseLabel(CharSequence paramCharSequence)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setReleaseLabel(paramCharSequence);
  }

  public void setTextTypeface(Typeface paramTypeface)
  {
    Iterator localIterator = this.mLoadingLayouts.iterator();
    while (localIterator.hasNext())
      ((LoadingLayout2)localIterator.next()).setTextTypeface(paramTypeface);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.LoadingLayoutProxy
 * JD-Core Version:    0.6.2
 */